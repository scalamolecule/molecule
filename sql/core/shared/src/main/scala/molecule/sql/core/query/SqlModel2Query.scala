package molecule.sql.core.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast
import molecule.boilerplate.ast.Model
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.query.Model2Query
import molecule.sql.core.query.casting._
import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer


class SqlModel2Query[Tpl](elements0: List[Element])
  extends Model2Query
    with ResolveExprOne[Tpl]
    with ResolveExprSet[Tpl]
    with ResolveRef[Tpl]
//    with ResolveNestedPull[Tpl]
    with Base
//    with CastNestedBranch_[Tpl]
    with CastRow2Tpl_
//    with CastNestedOptBranch_[Tpl]
//    with CastNestedOptLeaf_[Tpl]
//    with Nest[Tpl]
    with NestOpt_[Tpl]

    with LambdasOne
    with LambdasSet
    with MoleculeLogging {


  final def getQuery(altElements: List[Element] = Nil): String = {
    val elements = if (altElements.isEmpty) elements0 else altElements
    validateQueryModel(elements)
    val elements1 = prepareElements(elements)

    // Recursively resolve molecule elements
    resolve(Nil, elements1)
    renderQuery
  }

  final private def renderQuery: String = {
    val indent = "        "
    val select1 = select.mkString(s",\n$indent")
    val from1   = from.distinct.mkString(s",\n$indent")
    val where1  = if (where.isEmpty) "" else "\n  WHERE " + where.mkString(s"\n$indent")
    s"""SELECT  $select1
       |  FROM  $from1$where1;""".stripMargin
  }

  private def prepareElements(elements: List[Element]): List[Element] = {
    @tailrec
    def prepare(elements: List[Element], acc: List[Element]): List[Element] = {
      elements match {
        case element :: tail =>
          element match {
            case a: Attr       => prepare(tail, acc :+ prepareAttr(a))
            case e: Composite  => prepare(tail, acc :+ prepareComposite(e))
            case n: Nested     => prepare(tail, acc :+ prepareNested(n))
            case n: NestedOpt  => prepare(tail, acc :+ prepareNestedOpt(n))
            case t: TxMetaData => prepare(tail, acc :+ prepareTxMetaData(t))
            case refOrBackRef  => prepare(tail, acc :+ refOrBackRef)
          }
        case Nil             => acc
      }
    }
    def prepareAttr(a: Attr): Attr = {
      availableAttrs += a.name
      if (a.ns == "_Generic" && a.attr == "tx") {
        addTxVar = true
      } else if (a.filterAttr.nonEmpty) {
        val fa = a.filterAttr.get
        if (fa.filterAttr.nonEmpty) {
          throw ModelError(s"Nested filter attributes not allowed in ${a.ns}.${a.attr}")
        }
        val filterAttr = fa.name
        filterAttrVars.get(filterAttr).fold {
          // Create datomic variable for this expression attribute
          filterAttrVars = filterAttrVars + (filterAttr -> vv)
        }(_ => throw ModelError(s"Can't refer to ambiguous filter attribute $filterAttr"))
        if (fa.ns == a.ns) {
          // Add adjacent filter attribute is lifted...
        } else if (fa.isInstanceOf[Mandatory]) {
          throw ModelError(s"Filter attribute $filterAttr pointing to other namespace should be tacit.")
        } else if (fa.op != V) {
          throw ModelError("Filtering inside cross-namespace attribute filter not allowed. Found:\n  " + fa)
        } else {
          // Expect expression attribute in other namespace
          expectedFilterAttrs += fa.name
        }
      }
      a
    }

    def prepareComposite(composite: Composite): Composite = Composite(prepare(composite.elements, Nil))
    def prepareNested(nested: Nested): Nested = Nested(nested.ref, prepare(nested.elements, Nil))
    def prepareNestedOpt(nested: NestedOpt): NestedOpt = NestedOpt(nested.ref, prepare(nested.elements, Nil))
    def prepareTxMetaData(t: TxMetaData): TxMetaData = {
      addTxVar = true
      TxMetaData(prepare(t.elements, Nil))
    }

    val elements1 = prepare(elements, Nil)

    if (expectedFilterAttrs.nonEmpty && expectedFilterAttrs.intersect(availableAttrs) != expectedFilterAttrs) {
      throw ModelError("Please add missing filter attributes:\n  " + expectedFilterAttrs.mkString("\n  "))
    }

    elements1
  }


  @tailrec
  final private def resolve(
    es: List[Var],
    elements: List[Element]
  ): List[Var] = elements match {
    case element :: tail => element match {
      case a: AttrOne                           =>
        from += a.ns
        a match {
          case a: AttrOneMan => resolve(resolveAttrOneMan(es, a), tail)
          case a: AttrOneOpt => resolve(resolveAttrOneOpt(es, a), tail)
          case a: AttrOneTac => resolve(resolveAttrOneTac(es, a), tail)
        }
      case a: AttrSet                           => a match {
        case a: AttrSetMan => resolve(resolveAttrSetMan(es, a), tail)
        case a: AttrSetOpt => resolve(resolveAttrSetOpt(es, a), tail)
        case a: AttrSetTac => resolve(resolveAttrSetTac(es, a), tail)
      }
      case ref: Ref                             => resolve(resolveRef(es, ref), tail)
      case _: BackRef                           => resolve(es.init, tail)
      case Composite(compositeElements)         => resolve(resolveComposite(compositeElements), tail)
      case Nested(ref, nestedElements)          => resolve(resolveNested(es, ref, nestedElements), tail)
      case NestedOpt(nestedRef, nestedElements) => resolve(resolveNestedOpt(es, nestedRef, nestedElements), tail)
      case TxMetaData(txElements)               => resolveTxMetaData(txElements)
      case other                                => unexpectedElement(other)
    }
    case Nil             => es
  }


  final private def resolveComposite(compositeElements: List[Element]): List[Var] = {
    aritiesComposite()
    // Use first entity id in each composite sub group
    resolve(List(firstEid), compositeElements)
  }

  final private def resolveNested(
    es: List[Var], ref: Ref, nestedElements: List[Element]
  ): List[Var] = {
    isNested = true
    if (isNestedOpt)
      noMixedNestedModes
    validateRefNs(ref, nestedElements)

    aritiesNested()
    resolve(resolveNestedRef(es, ref), nestedElements)
  }

  final private def resolveNestedOpt(
    es: List[Var], nestedRef: Ref, nestedElements: List[Element]
  ): List[Var] = {
    isNestedOpt = true
    if (isNested) {
      noMixedNestedModes
    }
    if (expectedFilterAttrs.nonEmpty) {
      throw ModelError("Filter attributes not allowed in optional nested data structure.")
    }
    validateRefNs(nestedRef, nestedElements)

    // On top level, move past nested pull date to tx meta data (if any)
    sortAttrIndex += 1

    aritiesNested()
    val e = es.last
    resolveNestedOptRef(e, nestedRef)
//    resolveNestedOptElements(e, nestedRef, nestedElements)
    es
  }

  final private def resolveTxMetaData(txElements: List[Element]): List[Var] = {
    isTxMetaData = true
    // Use txVar as first entity id var for composite elements
    firstEid = txVar
    resolve(List(txVar), txElements)
  }

  final private def validateRefNs(ref: Ref, nestedElements: List[Element]): Unit = {
    val refName  = ref.refAttr.capitalize
    val nestedNs = nestedElements.head match {
      case a: Attr       => a.ns
      case r: Ref        => r.ns
      case Composite(es) => es.head match {
        case a: Attr => a.ns
        case other   => unexpectedElement(other)
      }
      case other         => unexpectedElement(other)
    }
    if (ref.refNs != nestedNs)
      throw ModelError(s"`$refName` can only nest to `${ref.refNs}`. Found: `$nestedNs`")
  }

}