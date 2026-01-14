package molecule.db.common.query

import molecule.core.dataModel.*
import molecule.core.error.ModelError


trait QueryExprSubQuery extends QueryExpr { self: Model2Query & SqlQueryBase =>

  // To be implemented by database-specific query builders
  protected def buildSubQuerySqlWithCasts(
    subElements: List[Element],
    subQueryAlias: String,
    optLimit: Option[Int],
    optOffset: Option[Int],
    isImplicit: Boolean,
    isJoin: Boolean
  ): (String, List[Cast])

  // Base implementation for standard subquery handling
  // Database implementations can call this directly instead of using super
  protected def querySubQueryBase(subElements: List[Element], optLimit: Option[Int], optOffset: Option[Int], isJoin: Boolean): Unit = {
    subQueryIndex += 1
    val alias = subQueryAlias

    val wasInsideSubQuery = insideSubQuery
    insideSubQuery = true

    // Build subquery SQL and get casts from subquery builder
    // isImplicit = false for explicit .select/.join calls (can return multiple columns, no alias needed)
    val (subquerySql, subQueryCasts) = buildSubQuerySqlWithCasts(subElements, alias, optLimit, optOffset, isImplicit = false, isJoin)

    if (isJoin) {
      // .join() - Add as FROM clause join
      joinSubQuery(subquerySql, subQueryCasts, subElements, alias)
    } else {
      // .select() - Add as SELECT clause subquery (correlated)
      selectSubQuery(subquerySql, subQueryCasts, subElements)
    }

    insideSubQuery = wasInsideSubQuery
  }

  private def selectSubQuery(subquerySql: String, subQueryCasts: List[Cast], subElements: List[Element]): Unit = {
    val selectIndex = select.length
    select += subquerySql
    subQueryCasts.foreach(castStrategy.add)

    // Validate: Can only sort by first column in multi-column .select() subqueries
    // (SQL ROW types sort by their first field when used in ORDER BY)
    val nonTacitAttrs = subElements.collect { case a: Attr if !isTacit(a) => a }
    val sortedAttrs = subElements.collect { case a: Attr if a.sort.isDefined && !isTacit(a) => a }

    if (nonTacitAttrs.length > 1 && sortedAttrs.nonEmpty) {
      // Multi-column .select() subquery with sorting - only first attribute can be sorted
      val firstAttr = nonTacitAttrs.head
      if (sortedAttrs.head != firstAttr) {
        throw ModelError(
          "Can only sort by first attribute in .select() subqueries having multiple columns. " +
          s"Move sorted attribute '${sortedAttrs.head.attr}' to be first, before '${firstAttr.attr}'. " +
          "Alternatively, use .join() which allows sorting by any column."
        )
      }
    }

    // Propagate sorting from subquery attributes to outer query
    // For SELECT subqueries, we use column position to reference the subquery result
    subElements.foreach {
      case a: Attr if a.sort.isDefined =>
        val (dir, arity) = (a.sort.get.head, a.sort.get.substring(1, 2).toInt)
        val sortDir = if (dir == 'a') "" else " DESC"
        // Reference the SELECT clause position (1-based index in SQL)
        val selectPosition = (selectIndex + 1).toString
        orderBy += ((level, arity, selectPosition, sortDir))
      case _ => ()
    }
  }

  private def joinSubQuery(subquerySql: String, subQueryCasts: List[Cast], subElements: List[Element], alias: String): Unit = {

    // Extract join conditions from filter attributes in subElements
    val joinConditions = extractJoinConditions(subElements, alias)

    if (joinConditions.nonEmpty) {
      // INNER JOIN with ON clause
      val onClause = joinConditions.mkString(" AND ")
      from += s" INNER JOIN $subquerySql $alias ON $onClause"
    } else {
      // No join conditions found, use CROSS JOIN
      from += s" CROSS JOIN $subquerySql $alias"
    }

    // Add subquery columns to outer SELECT (listed individually, not as ROW)
    // The cast strategy will group them into a tuple for the result
    subElements.foreach {
      case a: Attr if !isTacit(a) =>
        // Add mandatory/returned attributes to outer SELECT (skip all tacit attributes)
        // For aggregates, use the alias (e.g., Ref_id_count)
        val columnRef = getSubqueryColumnRef(a, alias)
        select += columnRef

        // Propagate sorting from subquery attributes to outer query
        // This ensures that JOIN subquery results are sorted in the outer query
        a.sort.foreach { sort =>
          val (dir, arity) = (sort.head, sort.substring(1, 2).toInt)
          val sortDir = if (dir == 'a') "" else " DESC"
          orderBy += ((level, arity, columnRef, sortDir))
        }
      case _ => ()
    }

    // Add subquery column casts
    // For multiple columns, wrap them into a nested tuple
    if (subQueryCasts.length > 1) {
      val tupleCast = castMultipleColumns(subQueryCasts)
      castStrategy.add(tupleCast)
    } else {
      subQueryCasts.foreach(castStrategy.add)
    }
  }

  private def isTacit(attr: Attr): Boolean = attr match {
    case _: Tacit => true
    case _        => false
  }

  private def getSubqueryColumnRef(attr: Attr, subqueryAlias: String): String = {
    attr.op match {
      case AggrFn(_, fn, _, _, _) =>
        // For aggregates, reference the alias: subquery.Ent_attr_fn
        val col = s"${attr.ent}.${attr.attr}"
        val colAlias = col.replace('.', '_') + "_" + fn.toLowerCase
        s"$subqueryAlias.$colAlias"
      case _ =>
        // For regular attributes, reference the column name: subquery.attr
        s"$subqueryAlias.${attr.attr}"
    }
  }

  private def extractJoinConditions(subElements: List[Element], subqueryAlias: String): List[String] = {
    subElements.flatMap {
      case a: Attr if a.filterAttr.isDefined =>
        a.filterAttr.map {
          case (_, filterPath, filterAttr) =>
            // For JOIN subqueries, correlation is:
            // outer_table.outer_column = subquery_alias.subquery_column
            // The subquery column is the attribute in the subquery (a.attr)
            // The outer column is what it's being filtered by (filterAttr.attr)
            val outerTable = filterPath.lastOption.getOrElse(filterAttr.ent)
            val outerCol = filterAttr.attr
            val subqueryCol = a.attr
            s"$outerTable.$outerCol = $subqueryAlias.$subqueryCol"
        }
      case _ => None
    }
  }
}
