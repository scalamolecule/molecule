package molecule.db.common.query

import scala.collection.mutable.ListBuffer
import molecule.core.dataModel.*
import molecule.core.error.ModelError


trait QueryExprRef extends QueryExpr { self: Model2Query & SqlQueryBase =>


  override protected def queryRef(r: Ref, tail: List[Element]): Unit = {
    val Ref(ent, refAttr, ref, relationship, _, reverseRefAttr, _) = r
    checkOnlyOptRef()
    handleRef(refAttr, ref)

    val entExt = if (ent == ref)
      "" // self-joins
    else
      getOptExt(path.dropRight(2)).getOrElse("")

    relationship match {
      case ManyToOne =>
        val (refAs, refExt) = getOptExt().fold(("", ""))(ext => (ref + ext, ext))
        val joinPredicates  = ListBuffer.empty[String]
        val joinType        =
          if (insideOptEntity) {
            if (select.nonEmpty) {
              // Ensure clauses become join predicates
              where.foreach {
                case (attr, predicate) => joinPredicates += s"$attr $predicate"
              }
              where.clear()
            } else {
              // Optional entity attrs all tacit
              hasOptEntityAttrs = false
            }
            insideOptEntity = false
            "RIGHT"
          } else if (isOptNested || nestedOptRef) {
            "LEFT"
          } else {
            "INNER"
          }
        joins += ((s"$joinType JOIN", ref, refAs,
          List(s"$ent$entExt.$refAttr = $ref$refExt.id") ++ joinPredicates
        ))

      case OneToMany if isOptNested =>
        throw ModelError(
          s"Only cardinality-one refs allowed in optional nested queries ($ent...$refAttr)."
        )

      case OneToMany if nestedOptRef =>
        throw ModelError(
          s"Only cardinality-one refs allowed in optional ref queries ($ent...$refAttr)."
        )

      case OneToMany =>
        val (refAs, refExt) = getOptExt().fold(("", ""))(ext => (ref + ext, ext))
        val revRefAttr      = reverseRefAttr.getOrElse(throw Exception("Missing reverse ref attr"))
        joins += ((s"INNER JOIN", ref, refAs,
          List(s"$ent$entExt.id = $ref$refExt.$revRefAttr") //++ joinPredicates
        ))
    }
  }


  override protected def queryBackRef(
    backRef: BackRef, tail: List[Element]
  ): Unit = {
    checkOnlyOptRef()
    checkOnlyRefAfterOptEntity()
    if (isManNested || isOptNested) {
      val BackRef(bRef, _, _) = backRef
      tail.head match {
        case a: Attr => throw ModelError(
          s"Expected ref after backref _$bRef. " +
            s"Please add attribute ${a.ent}.${a.attr} to initial entity ${a.ent} " +
            s"instead of after backref _$bRef."
        )
        case _       => ()
      }
    }
    handleBackRef()
  }


  override protected def queryOptRef(
    r: Ref, optRefElements: List[Element]
  ): Unit = {
    checkOnlyRefAfterOptEntity()

    if (hasOptRef) {
      // transfer previous predicates from `where`
      addPredicatesToLastLeftJoin()
    }

    // Know where we should steal predicates from subsequent `where` additions
    whereSplit = where.length

    val Ref(ent, refAttr, ref, rel, _, optRevRef, _) = r
    handleRef(refAttr, ref)
    val revRef          = optRevRef.get
    val entExt          = getOptExt(path.dropRight(2)).getOrElse("")
    val (refAs, refExt) = getOptExt().fold(("", ""))(ext => (ref + ext, ext))

    val (lft, rgt) = if (rel == ManyToOne) (refAttr, "id") else ("id", revRef)
    joins += ((s"LEFT JOIN", ref, refAs, List(s"$ent$entExt.$lft = $ref$refExt.$rgt")))

    // Cast next nested/adjacent opt ref
    castStrategy = castStrategy.optRef(nestedOptRef)

    nestedOptRef = true

    // Recursively resolve optional nested elements
    resolve(optRefElements)

    nestedOptRef = false
    hasOptRef = true
  }


  override protected def queryOptEntity(attrs: List[Element]): Unit = {
    // Resolve optional entity casts
    resolve(attrs)

    // Flag for Right join
    insideOptEntity = true

    // Continue collecting casts in CastOptEntity
    castStrategy = castStrategy.optEntity

    // Assume optional entity attributes
    hasOptEntityAttrs = true
  }


  override protected def querySubQuery(subElements: List[Element], optLimit: Option[Int], optOffset: Option[Int], isJoin: Boolean): Unit = {
    subQueryIndex += 1
    val alias = subQueryAlias

    // Validate: cannot aggregate IDs on related entities in subqueries
    validateNoRelatedIdAggregates(subElements)

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
        // For regular attributes, use the attribute name (e.g., entity)
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

    // Add subquery column casts (will be wrapped in ROW handler for tuple result)
    subQueryCasts.foreach(castStrategy.add)
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
        val alias = col.replace('.', '_') + "_" + fn.toLowerCase
        s"$subqueryAlias.$alias"
      case _ =>
        // For regular attributes, reference the column name: subquery.attr
        s"$subqueryAlias.${attr.attr}"
    }
  }

  private def extractJoinConditions(elements: List[Element], subqueryAlias: String): List[String] = {
    elements.flatMap {
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

  // To be implemented by database-specific query builders
  protected def buildSubQuerySqlWithCasts(
    subElements: List[Element],
    subQueryAlias: String,
    optLimit: Option[Int],
    optOffset: Option[Int],
    isImplicit: Boolean,
    isJoin: Boolean
  ): (String, List[Cast])


  override protected def queryNested(
    ref: Ref, nestedElements: List[Element]
  ): Unit = {
    isManNested = true
    checkOnlyRefAfterOptEntity()
    if (isOptNested) {
      noMixedNestedModes
    }
    resolveNested(ref, nestedElements, "INNER")
  }


  override protected def queryOptNested(
    ref: Ref, nestedElements: List[Element]
  ): Unit = {
    isOptNested = true
    checkOnlyRefAfterOptEntity()
    if (isManNested) {
      noMixedNestedModes
    }
    resolveNested(ref, nestedElements, "LEFT")
  }


  private def resolveNested(
    r: Ref, nestedElements: List[Element], joinType: String
  ): Unit = {
    noCardManyInsideOptRef()
    checkOnlyOptRef()

    val Ref(ent, refAttr, ref, relationship, _, optRevRefAttr, _) = r
    val revRefAttr                                                = optRevRefAttr.get

    level += 1
    validateRefEntity(r, nestedElements)

    handleRef(refAttr, ref)
    val entExt = getOptExt(path.dropRight(2)).getOrElse("")

    val (refAs, refExt) = getOptExt().fold(("", ""))(ext => (ref + ext, ext))
    joins += ((s"$joinType JOIN", ref, refAs,
      List(s"$ent$entExt.id = $ref$refExt.$revRefAttr") //++ joinPredicates
    ))

    val id = s"$ent.id"
    nestedIds += id
    groupByCols += id // if we later need to group by non-aggregated columns

    castStrategy = castStrategy.nest
    resolve(nestedElements)
  }

  private def validateNoRelatedIdAggregates(elements: List[Element]): Unit = {
    var hasRef = false
    var currentEntity = ""
    var idAggregateEntities = Set.empty[String]

    def checkElements(elems: List[Element]): Unit = {
      elems.foreach {
        case r: Ref =>
          hasRef = true
          currentEntity = r.ref

        case a: Attr if a.attr == "id" && isAggregate(a.op) =>
          if (currentEntity.nonEmpty) {
            idAggregateEntities += currentEntity
          } else {
            // First entity (before any Ref)
            idAggregateEntities += a.ent
          }
          currentEntity = a.ent

        case _ => ()
      }
    }

    checkElements(elements)

    // If we have a ref and multiple entities with ID aggregates, that's the problem
    if (hasRef && idAggregateEntities.size > 1) {
      throw ModelError(
        s"Cannot aggregate IDs on related entities in subqueries. " +
        s"Found ID aggregates on: ${idAggregateEntities.mkString(", ")}"
      )
    }
  }

  private def isAggregate(op: Op): Boolean = op match {
    case AggrFn(_, fn, _, _, _) =>
      fn == "count" || fn == "countDistinct" || fn == "sum" ||
      fn == "avg" || fn == "median" || fn == "variance" ||
      fn == "stddev" || fn == "min" || fn == "max"
    case _ => false
  }
}