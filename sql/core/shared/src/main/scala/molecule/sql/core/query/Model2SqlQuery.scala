package molecule.sql.core.query

import molecule.base.ast.SchemaAST.CardSet
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.query.Model2Query
import molecule.core.util.ModelUtils
import molecule.sql.core.query.casting._
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer


class Model2SqlQuery[Tpl](elements0: List[Element])
  extends Model2Query
    with ResolveExprOne[Tpl]
    with ResolveExprSet[Tpl]
    with ResolveExprSetRefAttr[Tpl]
    with ResolveRef
    with SqlQueryBase
    with CastNestedBranch_
    with CastRow2Tpl_
    with Nest[Tpl]
    with NestOpt[Tpl]
    with LambdasOne
    with LambdasSet
    with ModelUtils
    with MoleculeLogging {

  final def getSqlQuery(
    altElements: List[Element],
    optLimit: Option[Int],
    optOffset: Option[Int]
  ): String = {
    val elements = if (altElements.isEmpty) elements0 else altElements
    validateQueryModel(elements)
    //    elements.foreach(println)

    from = getInitialNonGenericNs(elements)
    exts += from -> None

    val elements1 = prepareElements(elements)

    // Recursively resolve molecule elements
    resolve(elements1)
    renderSqlQuery(optLimit, optOffset)
  }

  final private def renderSqlQuery(
    optLimit: Option[Int],
    optOffset: Option[Int]
  ): String = {
    val distinct_ = if (distinct) " DISTINCT" else ""
    val select_   = (nestedIds ++ select).mkString(s",\n  ")

    val joins_ = if (joins.isEmpty) "" else {
      val max1  = joins.map(_._1.length).max
      val max2  = joins.map(_._2.length).max
      val max3  = joins.map(_._3.length).max
      val hasAs = joins.exists(_._3.nonEmpty)
      joins.map { case (join, table, as, predicates) =>
        val join_  = join + padS(max1, join)
        val table_ = table + padS(max2, table)
        val as_    = if (hasAs) {
          if (as.isEmpty) padS(max3 + 4, "") else " AS " + as + padS(max3, as)
        } else ""
        s"$join_ $table_$as_ ON $predicates"
      }.mkString("\n", "\n", "")
    }

    val notNulls = notNull.map(col => (col, "IS NOT NULL"))
    val allWhere = where ++ notNulls
    val where_   = if (allWhere.isEmpty) "" else {
      val max = allWhere.map(_._1.length).max
      allWhere.map {
        case ("", predicate)  => predicate
        case (col, predicate) => s"$col " + padS(max, col) + predicate
      }.mkString("\nWHERE\n  ", s" AND\n  ", "")
    }

    val groupBy_ = if (groupBy.isEmpty && !aggregate) "" else {
      val allGroupByCols = groupBy ++ (if (aggregate) groupByCols else Nil)
      if (allGroupByCols.isEmpty) "" else allGroupByCols.mkString("\nGROUP BY ", ", ", "")
    }

    val having_ = if (having.isEmpty) "" else having.mkString("\nHAVING ", ", ", "")

    val isBackwards = optLimit.isDefined && optLimit.get < 0 || optOffset.isDefined && optOffset.get < 0

    val orderBy_ = if (orderBy.isEmpty) {
      ""
    } else {
      val coordinates = orderBy.sortBy(t => (t._1, t._2))
      val orderCols   = if (isBackwards) {
        coordinates.map {
          case (_, _, col, "DESC") => col
          case (_, _, col, _)      => col + " DESC"
        }
      } else {
        coordinates.map {
          case (_, _, col, dir) => col + dir
        }
      }
      orderCols.mkString("\nORDER BY ", ", ", "")
    }

    val limit_ = if (isNested || isNestedOpt) {
      ""
    } else if (hardLimit != 0) {
      s"\nLIMIT $hardLimit"
    } else {
      optLimit.fold("")(limit => s"\nLIMIT " + (if (isBackwards) -limit else limit))
    }

    val offset_ = if (isNested || isNestedOpt) {
      ""
    } else {
      optOffset.fold("")(offset => s"\nOFFSET " + (if (isBackwards) -offset else offset))
    }

    s"""SELECT$distinct_
       |  $select_
       |FROM $from$joins_$where_$groupBy_$having_$orderBy_$limit_$offset_;""".stripMargin
  }


  final def getTotalCountQuery: String = {
    val joins_ = if (joins.isEmpty) "" else {
      val max1  = joins.map(_._1.length).max
      val max2  = joins.map(_._2.length).max
      val max3  = joins.map(_._3.length).max
      val hasAs = joins.exists(_._3.nonEmpty)
      joins.map { case (join, table, as, predicates) =>
        val join_  = join + padS(max1, join)
        val table_ = table + padS(max2, table)
        val as_    = if (hasAs) {
          if (as.isEmpty) padS(max3 + 4, "") else " AS " + as + padS(max3, as)
        } else ""
        s"$join_ $table_$as_ ON $predicates"
      }.mkString("\n", "\n", "")
    }

    val notNulls = notNull.map(col => (col, "IS NOT NULL"))
    val allWhere = where ++ notNulls
    val where_   = if (allWhere.isEmpty) "" else {
      val max = allWhere.map(_._1.length).max
      allWhere.map {
        case ("", predicate)  => predicate
        case (col, predicate) => s"$col " + padS(max, col) + predicate
      }.mkString("\nWHERE\n  ", s" AND\n  ", "")
    }
    val having_  = if (having.isEmpty) "" else having.mkString("\nHAVING ", ", ", "")

    s"""SELECT COUNT($from.id)
       |FROM $from$joins_$where_$having_;""".stripMargin
  }


  private[molecule] def getWhereClauses: ListBuffer[(String, String)] = {
    from = getInitialNonGenericNs(elements0)
    exts += from -> None
    resolve(elements0)
    where
  }


  private def prepareElements(elements: List[Element]): List[Element] = {
    @tailrec
    def prepare(elements: List[Element], acc: List[Element]): List[Element] = {
      elements match {
        case element :: tail =>
          element match {
            case a: Attr      => prepare(tail, acc :+ prepareAttr(a))
            case n: Nested    => prepare(tail, acc :+ prepareNested(n))
            case n: NestedOpt => prepare(tail, acc :+ prepareNestedOpt(n))
            case refOrBackRef => prepare(tail, acc :+ refOrBackRef)
          }
        case Nil             => acc
      }
    }
    def prepareAttr(a: Attr): Attr = {
      availableAttrs += a.name
      if (a.filterAttr.nonEmpty) {
        val fa = a.filterAttr.get
        if (fa.filterAttr.nonEmpty) {
          throw ModelError(s"Nested filter attributes not allowed in ${a.ns}.${a.attr}")
        }
        val filterAttr = fa.name
        filterAttrVars.get(filterAttr).fold {
          // Create datomic variable for this expression attribute
          filterAttrVars = filterAttrVars + (filterAttr -> a.name)
        }(_ => throw ModelError(s"Can't refer to ambiguous filter attribute $filterAttr"))
        //        if (filterAttrVars.contains(filterAttr)) {
        //          throw ModelError(s"Can't refer to ambiguous filter attribute $filterAttr")
        //        }

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

    def prepareNested(nested: Nested): Nested = Nested(nested.ref, prepare(nested.elements, Nil))
    def prepareNestedOpt(nested: NestedOpt): NestedOpt = NestedOpt(nested.ref, prepare(nested.elements, Nil))

    val elements1 = prepare(elements, Nil)

    if (expectedFilterAttrs.nonEmpty && expectedFilterAttrs.intersect(availableAttrs) != expectedFilterAttrs) {
      throw ModelError("Please add missing filter attributes:\n  " + expectedFilterAttrs.mkString("\n  "))
    }

    elements1
  }

  lazy val noIdFiltering = "Filter attributes not allowed to involve entity ids."

  @tailrec
  final private def resolve(elements: List[Element]): Unit = elements match {
    case element :: tail => element match {
      case a: AttrOne                      =>
        if (a.attr == "id" && a.filterAttr.nonEmpty || a.attr != "id" && a.filterAttr.exists(_.attr == "id"))
          throw ModelError(noIdFiltering)
        a match {
          case a: AttrOneMan => resolveAttrOneMan(a); resolve(tail)
          case a: AttrOneOpt => resolveAttrOneOpt(a); resolve(tail)
          case a: AttrOneTac => resolveAttrOneTac(a); resolve(tail)
        }
      case a: AttrSet if a.refNs.isDefined => a match {
        case a: AttrSetMan => resolveRefAttrSetMan(a); resolve(tail)
        case a: AttrSetOpt => resolveRefAttrSetOpt(a); resolve(tail)
        case a: AttrSetTac => resolveRefAttrSetTac(a); resolve(tail)
      }
      case a: AttrSet                      => a match {
        case a: AttrSetMan => resolveAttrSetMan(a); resolve(tail)
        case a: AttrSetOpt => resolveAttrSetOpt(a); resolve(tail)
        case a: AttrSetTac => resolveAttrSetTac(a); resolve(tail)
      }
      case ref: Ref                        => resolveRef0(ref, tail)
      case backRef: BackRef                => resolveBackRef(backRef, tail)
      case Nested(ref, nestedElements)     => resolveNested(ref, nestedElements, tail)
      case NestedOpt(ref, nestedElements)  => resolveNestedOpt(ref, nestedElements, tail)
    }
    case Nil             => ()
  }


  final private def resolveRef0(ref: Ref, tail: List[Element]): Unit = {
    val Ref(_, refAttr, refNs, card) = ref
    if (isNestedOpt && card == CardSet) {
      throw ModelError(
        "Only cardinality-one refs allowed in optional nested queries. Found: " + ref
      )
    }
    exts(refNs) = exts.get(refNs).fold(Option.empty[String])(_ => Some("_" + refAttr))
    resolveRef(ref)
    resolve(tail)
  }

  final private def resolveBackRef(bRef: BackRef, tail: List[Element]): Unit = {
    if (isNested || isNestedOpt) {
      val BackRef(backRef, _) = bRef
      tail.head match {
        case a: Attr => throw ModelError(
          s"Expected ref after backref _$backRef. " +
            s"Please add attribute ${a.ns}.${a.attr} to initial namespace ${a.ns} " +
            s"instead of after backref _$backRef."
        )
        case _       => ()
      }
    }
    resolve(tail)
  }

  final private def resolveNested(ref: Ref, nestedElements: List[Element], tail: List[Element]): Unit = {
    level += 1
    isNested = true
    if (isNestedOpt) {
      noMixedNestedModes
    }
    validateRefNs(ref, nestedElements)

    val Ref(_, refAttr, refNs, _) = ref
    exts(refNs) = exts.get(refNs).fold(Option.empty[String])(_ => Some("_" + refAttr))
    aritiesNested()
    resolveNestedRef(ref)
    resolve(nestedElements)
    resolve(tail)
  }

  final private def resolveNestedOpt(ref: Ref, nestedElements: List[Element], tail: List[Element]): Unit = {
    level += 1
    isNestedOpt = true
    if (isNested) {
      noMixedNestedModes
    }
    if (expectedFilterAttrs.nonEmpty) {
      throw ModelError("Filter attributes not allowed in optional nested data structure.")
    }
    validateRefNs(ref, nestedElements)

    val Ref(_, refAttr, refNs, _) = ref
    exts(refNs) = exts.get(refNs).fold(Option.empty[String])(_ => Some("_" + refAttr))
    aritiesNested()
    resolveNestedOptRef(ref)
    resolve(nestedElements)
    resolve(tail)
  }

  final private def validateRefNs(ref: Ref, nestedElements: List[Element]): Unit = {
    val refName  = ref.refAttr.capitalize
    val nestedNs = nestedElements.head match {
      case a: Attr => a.ns
      case r: Ref  => r.ns
      case other   => unexpectedElement(other)
    }
    if (ref.refNs != nestedNs) {
      throw ModelError(s"`$refName` can only nest to `${ref.refNs}`. Found: `$nestedNs`")
    }
  }
}