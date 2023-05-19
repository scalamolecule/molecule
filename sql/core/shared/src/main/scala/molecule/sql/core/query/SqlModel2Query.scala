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
    resolve(elements1)
    renderQuery
  }

  final private def renderQuery: String = {
    val select1 = select.mkString(s",\n  ")

    val joins1 = if (joins.isEmpty) "" else {
      val max1  = joins.map(_._1.length).max
      val max2  = joins.map(_._2.length).max
      val max3  = joins.map(_._3.length).max
      val max4  = joins.map(_._4.length).max
      val hasAs = joins.exists(_._3.nonEmpty)

      joins.map { case (join, table, as, lft, rgt) =>
        val join_  = join + padS(max1, join)
        val table_ = table + padS(max2, table)

        val as_    = if (hasAs) {
          if (as.isEmpty) padS(max3 + 4, "") else " AS " + as + padS(max3, as)
        } else ""

        val lft_   = lft + padS(max4, lft)
        s"$join_ $table_$as_ ON $lft_ = $rgt"
      }.mkString("\n", "\n", "")
    }

    val where1 = if (where.isEmpty) "" else {
      val max = where.map(_._1.length).max
      where.map { case (col, predicate) =>
        s"$col " + padS(max, col) + predicate
      }.mkString("\nWHERE\n  ", s" AND\n  ", "")
    }

    s"""SELECT DISTINCT
       |  $select1
       |FROM $from$joins1$where1;""".stripMargin

    //    """SELECT DISTINCT
    //      |  Ns.i,
    //      |  Ns_r1.s,
    //      |  R1_r2.i
    //      |FROM Ns
    //      |INNER JOIN R1 AS Ns_r1 ON Ns.r1 = Ns_r1.id
    //      |INNER JOIN R2 AS R1_r2 ON R1.r2 = R1_r2.id
    //      |WHERE
    //      |  Ns.i    IS NOT NULL AND
    //      |  Ns_r1.s IS NOT NULL AND
    //      |  R1_r2.i IS NOT NULL
    //      |""".stripMargin
  }
  /*
  WHERE
    Ns.i IS NOT NULL,
    Ns_spouse.i IS NOT NULL; [42000-214]
   */

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

  private var curTable = ""

  @tailrec
  final private def resolve(elements: List[Element]): Unit = elements match {
    case element :: tail => element match {
      case a: AttrOne                           =>
        if (from.isEmpty) {
          from += a.ns
        }
        //        curTable = a.ns
        a match {
          case a: AttrOneMan => resolveAttrOneMan(a, curTable); resolve(tail)
          case a: AttrOneOpt => resolveAttrOneOpt(a); resolve(tail)
          case a: AttrOneTac => resolveAttrOneTac(a); resolve(tail)
        }
      case a: AttrSet                           => a match {
        case a: AttrSetMan => resolveAttrSetMan(a); resolve(tail)
        case a: AttrSetOpt => resolveAttrSetOpt(a); resolve(tail)
        case a: AttrSetTac => resolveAttrSetTac(a); resolve(tail)
      }
      case ref: Ref                             =>
        //        curTable = ref.ns + "_" + ref.refAttr
        //        curTable = ref.ns
        //        curTable = if (ref.ns == ref.refNs) ref.ns + "_1" else ref.ns
        curTable = if (ref.ns == ref.refNs) ref.ns + "_1" else ""
        resolveRef(ref, curTable);
        resolve(tail)
      case _: BackRef                           => resolve(tail)
      case Composite(compositeElements)         => resolveComposite(compositeElements); resolve(tail)
      case Nested(ref, nestedElements)          => resolveNested(ref, nestedElements); resolve(tail)
      case NestedOpt(nestedRef, nestedElements) => resolveNestedOpt(nestedRef, nestedElements); resolve(tail)
      case TxMetaData(txElements)               => resolveTxMetaData(txElements)
      case other                                => unexpectedElement(other)
    }
    case Nil             => ()
  }


  final private def resolveComposite(compositeElements: List[Element]): Unit = {
    aritiesComposite()
    // Use first entity id in each composite sub group
    resolve(compositeElements)
  }

  final private def resolveNested(ref: Ref, nestedElements: List[Element]): Unit = {
    isNested = true
    if (isNestedOpt)
      noMixedNestedModes
    validateRefNs(ref, nestedElements)

    aritiesNested()
    resolveNestedRef(ref)
    resolve(nestedElements)
  }

  final private def resolveNestedOpt(nestedRef: Ref, nestedElements: List[Element]): Unit = {
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
    resolveNestedOptRef(nestedRef)
    //    resolveNestedOptElements(e, nestedRef, nestedElements)
  }

  final private def resolveTxMetaData(txElements: List[Element]): Unit = {
    isTxMetaData = true
    // Use txVar as first entity id var for composite elements
    firstEid = txVar
    resolve(txElements)
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