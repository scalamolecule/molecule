package molecule.db.common.query

import scala.collection.mutable.ListBuffer
import molecule.core.dataModel.Element
import molecule.core.util.MoleculeLogging
import molecule.db.common.marshalling.ConnProxy
import molecule.db.common.util.ModelUtils


abstract class Model2SqlQuery(elements0: List[Element])
  extends Model2Query
    with QueryExprSubQuery
    with QueryExprRef
    with ModelUtils
    with MoleculeLogging { self: QueryExpr & SqlQueryBase =>


  final def getSqlQuery(
    altElements: List[Element],
    optLimit: Option[Int],
    optOffset: Option[Int],
    optProxy: Option[ConnProxy]
  ): String = {
    val elements1 = if (altElements.isEmpty) elements0 else altElements
    optProxy.foreach(p => metaDb = p.metaDb)
    resolveElements(elements1)
    renderSqlQuery(optLimit, optOffset)
  }

  final def resolveElements(elements1: List[Element]): Unit = {
    from = getInitialEntity(elements1)
    prevRefs = Set(from)
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
    where.map { case (col, expr) => s"$col $expr" }
  }

  final def renderSqlQuery(
    optLimit: Option[Int],
    optOffset: Option[Int]
  ): String = {
    if (hasOptRef || hasOptEntityAttrs) {
      addPredicatesToLastLeftJoin()
    }
    val distinct_   = if (distinct) " DISTINCT" else ""
    val select_     = mkSelect
    val joins_      = mkJoins(1)
    val tempTables_ = mkTempTables
    val where_      = mkWhere
    val groupBy_    = mkGroupBy
    val having_     = mkHaving
    val orderBy_    = mkOrderBy
    val pagination_ = pagination(optLimit, optOffset)
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

  def mkJoins(indents: Int): String = {
    if (joins.isEmpty) "" else {
      val indent = "  " * indents
      val max    = joins.map(j => j._1.length + j._2.length + (if (j._3.isEmpty) 0 else j._3.length + 1)).max
      joins.map {
        case (join, table, as, predicates) =>
          val as_         = if (as.isEmpty) "" else " " + as
          val p           = padS(max, s"$join$table$as_")
          val predicates_ = predicates.length match {
            case 0 => ""
            case 1 => p + " ON " + predicates.head
            case _ => predicates.mkString(s"$p ON\n$indent  ", s" AND\n$indent  ", "")
          }
          s"$join $table$as_$predicates_"
      }.mkString(s"\n$indent", s"\n$indent", "")
    }
  }

  private def mkTempTables: String = {
    if (tempTables.isEmpty) "" else tempTables.mkString(",\n  ", ",\n  ", "")
  }

  def mkWhere: String = {
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
    if (having.isEmpty) "" else having.mkString("\nHAVING ", " AND ", "")
  }

  private def mkOrderBy: String = {
    if (orderBy.isEmpty) "" else {
      val coordinates = orderBy.sortBy(t => (t._1, t._2))
      val orderCols   = coordinates.map {
        case (_, _, col, dir) => col + dir
      }
      orderCols.mkString("\nORDER BY ", ", ", "")
    }
  }

  def pagination(optLimit: Option[Int], optOffset: Option[Int]): String = {
    val limit_ = if (!insideSubQuery && (isManNested || isOptNested)) {
      ""
    } else if (hardLimit != 0) {
      s"\nLIMIT $hardLimit"
    } else {
      optLimit.fold("")(limit => s"\nLIMIT " + limit)
    }

    val offset_ = if (!insideSubQuery && (isManNested || isOptNested)) "" else {
      optOffset.fold("")(offset => s"\nOFFSET " + offset)
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

  final def renderSubQuery(
    baseIndent: Int,
    subQueryAlias: Option[String],
    optLimit: Option[Int],
    optOffset: Option[Int],
    isImplicit: Boolean
  ): String = {
    // Only alias the column as "col" for implicit subqueries (comparison operations with Molecule_1)
    // Explicit subqueries (.sub) can return multiple columns and shouldn't have the alias
    if (isImplicit && select.nonEmpty) {
      val firstCol = select.head
      select.update(0, s"$firstCol AS col")
    }

    // Render the subquery using the normal rendering
    val subquerySql = renderSqlQuery(optLimit, optOffset)
      .stripSuffix(";")
      .trim

    // Indent subquery content
    val contentIndent = "  " * baseIndent
    val indentedLines = subquerySql.split("\n").map { line =>
      if (line.trim.isEmpty) line else contentIndent + line
    }

    // Opening/closing parens at no indent (they're already in the SELECT list which is indented)
    s"(\n${indentedLines.mkString("\n")}\n  )"
  }
}