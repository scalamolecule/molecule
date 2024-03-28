package molecule.sql.core.query

import molecule.base.ast._
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.marshalling.ConnProxy
import molecule.core.query.{Model2QueryBase, ResolveExpr}
import molecule.core.util.ModelUtils
import molecule.sql.core.query.casting._
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer


abstract class Model2SqlQuery[Tpl](elements0: List[Element])
  extends ResolveRef
    with CastNestedBranch_
    with CastRow2Tpl_
    with Nest[Tpl]
    with NestOpt[Tpl]
    with ModelUtils
    with MoleculeLogging { self: ResolveExpr with SqlQueryBase =>

  final def getSqlQuery(
    altElements: List[Element],
    optLimit: Option[Int],
    optOffset: Option[Int],
    optProxy: Option[ConnProxy]
  ): String = {
    val elements1 = if (altElements.isEmpty) elements0 else altElements
    optProxy.foreach(p => attrMap = p.attrMap)
    resolveElements(elements1)
    renderSqlQuery(optLimit, optOffset)
  }

  final private def resolveElements(elements1: List[Element]): Unit = {
    from = getInitialNs(elements1)
    prevRefNss = Set(from)
    path = List(from)
    preExts += path -> None
    val (elements2, _, _) = validateQueryModel(elements1, None, Some(handleRef), Some(handleBackRef))
    path = List(from)
    exts ++= preExts

    // Recursively resolve molecule elements
    resolve(elements2)
  }


  private[molecule] def getWhereClauses: ListBuffer[(String, String)] = {
    resolveElements(elements0)
    where
  }


  @tailrec
  final private def resolve(elements: List[Element]): Unit = elements match {
    case element :: tail => element match {
      case a: AttrOne                      =>
        if (a.attr == "id" && a.filterAttr.nonEmpty || a.attr != "id" && a.filterAttr.exists(_._3.attr == "id")) {
          throw ModelError(noIdFiltering)
        }
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

      case a: AttrSeq => a match {
        case a: AttrSeqMan => resolveAttrSeqMan(a); resolve(tail)
        case a: AttrSeqOpt => resolveAttrSeqOpt(a); resolve(tail)
        case a: AttrSeqTac => resolveAttrSeqTac(a); resolve(tail)
      }

      case a: AttrMap => a match {
        case a: AttrMapMan => resolveAttrMapMan(a); resolve(tail)
        case a: AttrMapOpt => resolveAttrMapOpt(a); resolve(tail)
        case a: AttrMapTac => resolveAttrMapTac(a); resolve(tail)
      }

      case ref: Ref                        => resolveRef0(ref, tail)
      case backRef: BackRef                => resolveBackRef(backRef, tail)
      case Nested(ref, nestedElements)     => resolveNested(ref, nestedElements, tail)
      case NestedOpt(ref, nestedElements)  => resolveNestedOpt(ref, nestedElements, tail)
    }
    case Nil             => ()
  }


  final private def resolveRef0(ref: Ref, tail: List[Element]): Unit = {
    val Ref(_, refAttr, refNs, card, _, _) = ref
    if (isNestedOpt && card == CardSet) {
      throw ModelError(
        "Only cardinality-one refs allowed in optional nested queries. Found: " + ref
      )
    }

    handleRef(refAttr, refNs)

    val singleOptSet = tail.length == 1 && tail.head.isInstanceOf[AttrSetOpt]
    resolveRef(ref, singleOptSet)
    resolve(tail)
  }

  final private def resolveBackRef(bRef: BackRef, tail: List[Element]): Unit = {
    if (isNestedMan || isNestedOpt) {
      val BackRef(backRef, _, _) = bRef
      tail.head match {
        case a: Attr => throw ModelError(
          s"Expected ref after backref _$backRef. " +
            s"Please add attribute ${a.ns}.${a.attr} to initial namespace ${a.ns} " +
            s"instead of after backref _$backRef."
        )
        case _       => ()
      }
    }
    handleBackRef()
    resolve(tail)
  }

  final private def resolveNested(ref: Ref, nestedElements: List[Element], tail: List[Element]): Unit = {
    level += 1
    isNestedMan = true
    if (isNestedOpt) {
      noMixedNestedModes
    }
    validateRefNs(ref, nestedElements)

    val Ref(_, refAttr, refNs, _, _, _) = ref
    handleRef(refAttr, refNs)

    aritiesNested()
    resolveNestedRef(ref)
    resolve(nestedElements)
    resolve(tail)
  }

  final private def resolveNestedOpt(ref: Ref, nestedElements: List[Element], tail: List[Element]): Unit = {
    level += 1
    isNestedOpt = true
    if (isNestedMan) {
      noMixedNestedModes
    }
    if (expectedFilterAttrs.nonEmpty) {
      throw ModelError("Filter attributes not allowed in optional nested queries.")
    }
    validateRefNs(ref, nestedElements)

    val Ref(_, refAttr, refNs, _, _, _) = ref
    handleRef(refAttr, refNs)

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


  final private def renderSqlQuery(
    optLimit: Option[Int],
    optOffset: Option[Int]
  ): String = {
    val distinct_ = if (distinct) " DISTINCT" else ""
    val select_   = (nestedIds ++ select).mkString(s",\n  ")

    val joins_      = if (joins.isEmpty) "" else {
      val max1  = joins.map(_._1.length).max
      val max2  = joins.map(_._2.length).max
      val max3  = joins.map(_._3.length).max
      val max4  = joins.map(_._4.length).max + 1
      val hasAs = joins.exists(_._3.nonEmpty)
      joins.map { case (join, table, as, lft, rgt) =>
        val join_     = join + padS(max1, join)
        val table_    = table + padS(max2, table)
        val as_       = if (hasAs) {
          if (as.isEmpty) padS(max3 + 4, "") else " AS " + as + padS(max3, as)
        } else ""
        val predicate = lft + padS(max4, lft) + rgt
        s"$join_ $table_$as_ ON $predicate"
      }.mkString("\n  ", "\n  ", "")
    }
    val tempTables_ = if (tempTables.isEmpty) "" else tempTables.mkString(",\n  ", ",\n  ", "")

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
          // Switch sort direction on top level
          case (0, _, col, "DESC") => col
          case (0, _, col, _)      => col + " DESC"
          // Nested sorts stay unchanged
          case (_, _, col, "DESC") => col + " DESC"
          case (_, _, col, _)      => col
        }
      } else {
        coordinates.map {
          case (_, _, col, dir) => col + dir
        }
      }
      orderCols.mkString("\nORDER BY ", ", ", "")
    }

    val pagination_ = pagination(optLimit, optOffset, isBackwards)

    s"""SELECT$distinct_
       |  $select_
       |FROM $from$joins_$tempTables_$where_$groupBy_$having_$orderBy_$pagination_;""".stripMargin
  }


  def pagination(optLimit: Option[Int], optOffset: Option[Int], isBackwards: Boolean): String = {
    val limit_ = if (isNestedMan || isNestedOpt) {
      ""
    } else if (hardLimit != 0) {
      s"\nLIMIT $hardLimit"
    } else {
      optLimit.fold("")(limit => s"\nLIMIT " + limit.abs)
    }

    val offset_ = if (isNestedMan || isNestedOpt) {
      ""
    } else {
      optOffset.fold("")(offset => s"\nOFFSET " + offset.abs)
    }

    s"$limit_$offset_"
  }

  final def getTotalCountQuery: String = {
    val table  = from
    val joins_ = if (joins.isEmpty) "" else {
      val max1  = joins.map(_._1.length).max
      val max2  = joins.map(_._2.length).max
      val max3  = joins.map(_._3.length).max
      val max4  = joins.map(_._4.length).max + 1
      val hasAs = joins.exists(_._3.nonEmpty)
      joins.map { case (join, table, as, lft, rgt) =>
        val join_     = join + padS(max1, join)
        val table_    = table + padS(max2, table)
        val as_       = if (hasAs) {
          if (as.isEmpty) padS(max3 + 4, "") else " AS " + as + padS(max3, as)
        } else ""
        val predicate = lft + padS(max4, lft) + rgt
        s"$join_ $table_$as_ ON $predicate"
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

    s"""SELECT COUNT($table.id)
       |FROM $table$joins_$where_$having_;""".stripMargin
  }
}