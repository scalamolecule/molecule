package molecule.sql.core.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.query.Model2Query
import molecule.core.util.ModelUtils
import molecule.sql.core.query.casting._
import scala.annotation.tailrec


class SqlModel2Query[Tpl](elements0: List[Element])
  extends Model2Query
    with ResolveExprOne[Tpl]
    with ResolveExprSet[Tpl]
    with ResolveRef[Tpl]
    //    with ResolveNestedPull[Tpl]
    with Base
    //    with CastNestedBranch_
    with CastRow2Tpl_
    //    with CastNestedOptBranch_
    //    with CastNestedOptLeaf_
    //    with Nest[Tpl]
    with NestOpt_[Tpl]

    with LambdasOne
    with LambdasSet
    with ModelUtils
    with MoleculeLogging {

  private var curTable = ""

  final def getQuery(altElements: List[Element] = Nil): String = {
    val elements = if (altElements.isEmpty) elements0 else altElements
    validateQueryModel(elements)

    //    elements.foreach(println)

    from = getInitialNonGenericNs(elements)
    exts += from -> None

    val elements1 = prepareElements(elements)

    // Recursively resolve molecule elements
    resolve(elements1)
    renderQuery
  }

  final private def renderQuery: String = {
    val select_ = select.mkString(s",\n  ")

    val joins_ = if (joins.isEmpty) "" else {
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

    val where_ = if (where.isEmpty) "" else {
      val max = where.map(_._1.length).max
      where.map { case (col, predicate) =>
        s"$col " + padS(max, col) + predicate
      }.mkString("\nWHERE\n  ", s" AND\n  ", "")
    }

    val orderBy_ = if (orderBy.isEmpty) "" else {
      orderBy.map {
        case (_, col, dir) => col + dir
      }.mkString("\nORDER BY ", ", ", "")
    }

    val stmt =
      s"""SELECT DISTINCT
         |  $select_
         |FROM $from$joins_$where_$orderBy_;""".stripMargin

    //    println(stmt)

    """SELECT DISTINCT
      |  A.i,
      |  B.i,
      |  A.s
      |FROM A
      |INNER JOIN A_bb_B       ON A.id        = A_bb_B.A_id
      |INNER JOIN B            ON A_bb_B.B_id = B.id
      |WHERE
      |  A.i IS NOT NULL AND
      |  B.i IS NOT NULL AND
      |  A.s IS NOT NULL
      |""".stripMargin

    stmt
  }


  private def prepareElements(elements: List[Element]): List[Element] = {
    @tailrec
    def prepare(elements: List[Element], acc: List[Element]): List[Element] = {
      elements match {
        case element :: tail =>
          element match {
            case a: Attr      => prepare(tail, acc :+ prepareAttr(a))
            case e: Composite => prepare(tail, acc :+ prepareComposite(e))
            case n: Nested    => prepare(tail, acc :+ prepareNested(n))
            case n: NestedOpt => prepare(tail, acc :+ prepareNestedOpt(n))
            case t: TxData    => prepare(tail, acc :+ prepareTxData(t))
            case refOrBackRef => prepare(tail, acc :+ refOrBackRef)
          }
        case Nil             => acc
      }
    }
    def prepareAttr(a: Attr): Attr = {
      availableAttrs += a.name
      if (a.ns == "_Generic" && a.attr == "txId") {
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
    def prepareTxData(t: TxData): TxData = {
      addTxVar = true
      TxData(prepare(t.elements, Nil))
    }

    val elements1 = prepare(elements, Nil)

    if (expectedFilterAttrs.nonEmpty && expectedFilterAttrs.intersect(availableAttrs) != expectedFilterAttrs) {
      throw ModelError("Please add missing filter attributes:\n  " + expectedFilterAttrs.mkString("\n  "))
    }

    elements1
  }


  @tailrec
  final private def resolve(elements: List[Element]): Unit = elements match {
    case element :: tail => element match {
      case a: AttrOne                           =>
        a match {
          case a: AttrOneMan => resolveAttrOneMan(a); resolve(tail)
          case a: AttrOneOpt => resolveAttrOneOpt(a); resolve(tail)
          case a: AttrOneTac => resolveAttrOneTac(a); resolve(tail)
        }
      case a: AttrSet                           => a match {
        case a: AttrSetMan => resolveAttrSetMan(a); resolve(tail)
        case a: AttrSetOpt => resolveAttrSetOpt(a); resolve(tail)
        case a: AttrSetTac => resolveAttrSetTac(a); resolve(tail)
      }
      case ref: Ref                             =>
        exts(ref.refNs) = exts.get(ref.refNs).fold(Option.empty[String])(_ => Some("_" + ref.refAttr))
        resolveRef(ref)
        resolve(tail)
      case _: BackRef                           => resolve(tail)
      case Composite(compositeElements)         => resolveComposite(compositeElements); resolve(tail)
      case Nested(ref, nestedElements)          => resolveNested(ref, nestedElements); resolve(tail)
      case NestedOpt(nestedRef, nestedElements) => resolveNestedOpt(nestedRef, nestedElements); resolve(tail)
      case TxData(txElements)                   => resolveTxData(txElements)
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

    // On top level, move past nested pull date to tx data (if any)
    sortAttrIndex += 1

    aritiesNested()
    resolveNestedOptRef(nestedRef)
    //    resolveNestedOptElements(e, nestedRef, nestedElements)
  }

  final private def resolveTxData(txElements: List[Element]): Unit = {
    isTxData = true
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