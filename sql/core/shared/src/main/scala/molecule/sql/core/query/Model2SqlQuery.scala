package molecule.sql.core.query

import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.marshalling.ConnProxy
import molecule.core.query.{Model2Query, QueryExpr}
import molecule.core.util.ModelUtils
import molecule.sql.core.query.casting._
import scala.collection.mutable.ListBuffer


abstract class Model2SqlQuery[Tpl](elements0: List[Element])
  extends Model2Query with QueryExprRef
    with Nest[Tpl]
    with NestOpt[Tpl]
    with NestOptRef[Tpl]
    with ModelUtils
    with MoleculeLogging { self: QueryExpr with SqlQueryBase =>


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

  final protected def resolveElements(elements1: List[Element]): Unit = {
    from = getInitialNs(elements1)
    prevRefNss = Set(from)
    path = List(from)
    preExts += path -> None
    val (elements2, _, _) = validateQueryModel(
      elements1, None, Some(handleRef), Some(handleBackRef)
    )
    path = List(from)
    exts ++= preExts

    // Recursively resolve molecule elements
    resolve(elements2)
  }


  def getWhereClauses: ListBuffer[String] = {
    resolveElements(elements0)
    val clauses    = where.map {
      case (col, expr) => s"$col $expr"
    }
    //    println("------ joins --------")
    //    println(formattedJoins)
    val joinsExist = if (joins.isEmpty) Nil else
      List(
        s"""EXISTS (
           |  SELECT * FROM Ns
           |    ${mkJoins(2).trim}
           |)""".stripMargin)
    clauses ++ joinsExist
  }


  final private def renderSqlQuery(
    optLimit: Option[Int],
    optOffset: Option[Int]
  ): String = {
    if (hasOptRef) {
      addPredicatesToLastLeftJoin()
    }
    val isBackwards = optLimit.fold(false)(_ < 0) || optOffset.fold(false)(_ < 0)
    val distinct_   = if (distinct) " DISTINCT" else ""
    val select_     = mkSelect
    val joins_      = mkJoins(1)
    val tempTables_ = mkTempTables
    val where_      = mkWhere
    val groupBy_    = mkGroupBy
    val having_     = mkHaving
    val orderBy_    = mkOrderBy(isBackwards)
    val pagination_ = pagination(optLimit, optOffset, isBackwards)
    s"""SELECT$distinct_
       |  $select_
       |FROM $from$joins_$tempTables_$where_$groupBy_$having_$orderBy_$pagination_;""".stripMargin
  }

  private def mkSelect: String = {
    val select1 = if (select2.isEmpty) {
      select
    } else {
      select.zipWithIndex.map {
        case (s, i) => select2.get(i).fold(s)(_(joins.toList, groupByCols.toSet))
      }
    }
    (nestedIds ++ select1).mkString(s",\n  ")
  }

  protected def mkJoins(indents: Int): String = {
    if (joins.isEmpty) "" else {
      val indent = "  " * indents
      joins.map {
        case (join, table, as, predicates) =>
          val as_         = if (as.isEmpty) "" else " " + as
          val predicates_ = if (predicates.isEmpty) "" else
            predicates.mkString(s" ON\n$indent  ", s" AND\n$indent  ", "")
          s"$join $table$as_$predicates_"
      }.mkString(s"\n$indent", s"\n$indent", "")
    }
  }

  private def mkTempTables: String = {
    if (tempTables.isEmpty) "" else tempTables.mkString(",\n  ", ",\n  ", "")
  }

  private def mkWhere: String = {
    val allWhere = where
    if (allWhere.isEmpty) "" else {
      val max = allWhere.map(_._1.length).max
      allWhere.map {
        case ("", predicate)  => predicate
        case (col, predicate) => s"$col " + padS(max, col) + predicate
      }.mkString("\nWHERE\n  ", s" AND\n  ", "")
    }
  }

  private def mkGroupBy: String = {
    if (groupBy.isEmpty && !aggregate) "" else {
      val allGroupByCols = groupBy ++ (if (aggregate) groupByCols else Nil)
      if (allGroupByCols.isEmpty) "" else allGroupByCols.mkString("\nGROUP BY ", ", ", "")
    }
  }

  private def mkHaving: String = {
    if (having.isEmpty) "" else having.mkString("\nHAVING ", ", ", "")
  }

  private def mkOrderBy(isBackwards: Boolean): String = {
    if (orderBy.isEmpty) {
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
  }

  def pagination(optLimit: Option[Int], optOffset: Option[Int], isBackwards: Boolean): String = {
    val limit_ = if (isManNested || isOptNested) {
      ""
    } else if (hardLimit != 0) {
      s"\nLIMIT $hardLimit"
    } else {
      optLimit.fold("")(limit => s"\nLIMIT " + limit.abs)
    }

    val offset_ = if (isManNested || isOptNested) {
      ""
    } else {
      optOffset.fold("")(offset => s"\nOFFSET " + offset.abs)
    }

    s"$limit_$offset_"
  }


  final def getTotalCountQuery: String = {
    val table   = from
    val joins_  = mkJoins(1)
    val where_  = mkWhere
    val having_ = if (having.isEmpty) "" else having.mkString("\nHAVING ", ", ", "")
    s"""SELECT COUNT($table.id)
       |FROM $table$joins_$where_$having_;""".stripMargin
  }
}