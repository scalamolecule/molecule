package molecule.db.common.query

import molecule.core.dataModel.*
import molecule.core.error.ModelError
import molecule.db.common.query.casting.*
import molecule.db.common.query.casting.strategy.CastTuple


trait QueryExprSubQuery extends QueryExpr { self: Model2Query & SqlQueryBase =>

  // To be implemented by database-specific query builders - creates the DB-specific query builder
  protected def createSubQueryBuilder(subElements: List[Element]): Model2SqlQuery & SqlQueryBase

  // Hook methods for database-specific splitting behavior
  protected def shouldSplitSubquery(subElements: List[Element]): Boolean

  // Hook for DB-specific modifications to subquery builder
  protected def configureSubQueryBuilder(
    subQueryBuilder: Model2SqlQuery & SqlQueryBase,
    isImplicit: Boolean
  ): Unit = {
    // Default: no additional configuration beyond common setup
  }

  // Hook for DB-specific cast handling for multi-column subqueries
  protected def wrapMultiColumnCasts(
    subqueryCasts: List[Cast]
  ): List[Cast] = {
    // Default: wrap in tuple for multi-column results
    if (subqueryCasts.length > 1) {
      val tupleCast: Cast = (row: RS, startIndex: Int) => casting.CastTpl_.cast(subqueryCasts, startIndex)(row)
      List(tupleCast)
    } else {
      subqueryCasts
    }
  }

  // Common implementation for building subquery SQL with casts
  protected def buildSubQuerySqlWithCasts(
    subElements: List[Element],
    subQueryAlias: String,
    optLimit: Option[Int],
    optOffset: Option[Int],
    isImplicit: Boolean
  ): (String, List[Cast]) = {
    val subQueryBuilder = createSubQueryBuilder(subElements)
    subQueryBuilder.insideSubQuery = true
    // Only set insideJoinSubQuery for explicit .join() calls, not for implicit comparison subqueries
    subQueryBuilder.insideJoinSubQuery = !isImplicit
    subQueryBuilder.resolveElements(subElements)

    if (subQueryBuilder.hasManSubQueryAttr) {
      hasManSubQueryAttr = true
    }

    // Allow DB-specific configuration
    configureSubQueryBuilder(subQueryBuilder, isImplicit)

    val sql           = subQueryBuilder.renderSubQuery(2, Some(subQueryAlias), optLimit, optOffset, isImplicit)
    val subqueryCasts = subQueryBuilder.castStrategy match {
      case tuple: CastTuple => tuple.getCasts
      case _                => Nil
    }

    val casts = wrapMultiColumnCasts(subqueryCasts)
    (sql, casts)
  }

  protected def getOtherElements(subElements: List[Element]): List[Element] = {
    subElements.filter {
      case a: Attr if a.isInstanceOf[Tacit] => true // Tacit attrs for WHERE clauses
      case _: Ref                           => true // Ref elements for JOIN relationships
      case _                                => false
    }
  }

  protected def buildSubqueryElements(otherElements: List[Element], attr: Attr): List[Element] = {
    otherElements :+ attr
  }

  protected def handleAttrSorting(attr: Attr, allAttrs: List[Attr]): Unit = {
    attr.sort.foreach { sort =>
      val (dir, arity)   = (sort.head, sort.substring(1, 2).toInt)
      val sortDir        = if (dir == 'a') "" else " DESC"
      val selectPosition = select.length.toString
      orderBy += ((level, arity, selectPosition, sortDir))
    }
  }

  // Common implementation for splitting subqueries into multiple scalar subqueries
  protected def querySplitSubQueries(
    subElements: List[Element],
    optLimit: Option[Int],
    optOffset: Option[Int]
  ): Unit = {
    val nonTacitAttrs = subElements.collect { case a: Attr if !a.isInstanceOf[Tacit] => a }
    val allCasts      = scala.collection.mutable.ListBuffer.empty[Cast]
    val otherElements = getOtherElements(subElements)

    nonTacitAttrs.foreach { attr =>
      subQueryIndex += 1
      val alias = subQueryAlias

      val wasInsideSubQuery = insideSubQuery
      insideSubQuery = true

      // Build subquery with this attribute + all tacit/ref elements
      val subqueryElements             = buildSubqueryElements(otherElements, attr)
      val (subquerySql, subQueryCasts) = buildSubQuerySqlWithCasts(
        subqueryElements, alias, optLimit, optOffset, isImplicit = false
      )

      select += subquerySql
      allCasts ++= subQueryCasts

      // Propagate sorting if needed
      handleAttrSorting(attr, nonTacitAttrs)

      insideSubQuery = wasInsideSubQuery
    }

    // Group all casts into a single tuple cast
    val tupleCast: Cast = (row: RS, startIndex: Int) => casting.CastTpl_.cast(allCasts.toList, startIndex)(row)
    castStrategy.add(tupleCast)
  }

  // Base implementation for standard subquery handling
  // Database implementations can call this directly instead of using super
  protected def querySubQueryBase(
    subElements: List[Element],
    optLimit: Option[Int],
    optOffset: Option[Int]
  ): Unit = {
    subQueryIndex += 1
    val alias = subQueryAlias

    val wasInsideSubQuery = insideSubQuery
    insideSubQuery = true

    // Build subquery SQL and get casts from subquery builder
    // isImplicit = false for explicit .join() calls (can return multiple columns, no alias needed)
    val (subquerySql, subQueryCasts) = buildSubQuerySqlWithCasts(
      subElements, alias, optLimit, optOffset, isImplicit = false
    )

    // .join() - Add as FROM clause join
    joinSubQuery(subquerySql, subQueryCasts, subElements, alias)

    insideSubQuery = wasInsideSubQuery
  }

  private def joinSubQuery(
    subquerySql: String,
    subQueryCasts: List[Cast],
    subElements: List[Element],
    alias: String
  ): Unit = {

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
      case a: Attr if !a.isInstanceOf[Tacit] =>
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
      castStrategy.add((row: RS, startIndex: Int) => CastTpl_.cast(subQueryCasts, startIndex)(row))
    } else {
      subQueryCasts.foreach(castStrategy.add)
    }
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
