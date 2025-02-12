package molecule.sql.core.query

import molecule.base.ast._
import molecule.base.error.ModelError
import molecule.core.ast.DataModel._
import molecule.core.query.{Model2Query, QueryExpr}


trait QueryExprRef extends QueryExpr { self: Model2Query with SqlQueryBase =>

  override protected def queryRef(r: Ref, tail: List[Element]): Unit = {
    val Ref(ent, refAttr, ref, card, _, _) = r
    if (isOptNested && card == CardSet) {
      throw ModelError(
        s"Only cardinality-one refs allowed in optional nested queries ($ent.$refAttr)."
      )
    }
    checkOnlyOptRef()
    handleRef(refAttr, ref)

    val entExt = if (ent == ref)
      "" // self-joins
    else
      getOptExt(path.dropRight(2)).getOrElse("")

    if (card == CardOne) {
      val (refAs, refExt) = getOptExt().fold(("", ""))(ext => (ref + ext, ext))
      val joinType        = if (optEntity) {
        "RIGHT"
      } else if (isOptNested || nestedOptRef) {
        "LEFT"
      } else {
        "INNER"
      }
      joins += ((s"$joinType JOIN", ref, refAs, List(s"$ent$entExt.$refAttr = $ref$refExt.id")))
    } else {
      if (nestedOptRef) {
        onlyCardOneInsideOptRef(r)
      }
      val singleOptSet = tail.length == 1 && tail.head.isInstanceOf[AttrSetOpt]
      val joinType     = if (singleOptSet) "LEFT" else "INNER"
      addJoins(ent, entExt, refAttr, ref, joinType)
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

    val Ref(ent, refAttr, ref, _, _, _) = r
    handleRef(refAttr, ref)

    val entExt          = getOptExt(path.dropRight(2)).getOrElse("")
    val (refAs, refExt) = getOptExt().fold(("", ""))(ext => (ref + ext, ext))
    joins += ((s"LEFT JOIN", ref, refAs, List(s"$ent$entExt.$refAttr = $ref$refExt.id")))

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
    optEntity = true

    // Continue collecting casts in CastOptEntity
    castStrategy = castStrategy.optEntity

    // Collect None too
    hasOptRef = true
  }


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
    if (expectedFilterAttrs.nonEmpty) {
      throw ModelError("Filter attributes not allowed in optional nested queries.")
    }
    resolveNested(ref, nestedElements, "LEFT")
  }


  private def resolveNested(
    r: Ref, nestedElements: List[Element], joinType: String
  ): Unit = {
    noCardManyInsideOptRef()
    checkOnlyOptRef()

    val Ref(ent, refAttr, ref, _, _, _) = r
    level += 1
    validateRefEntity(r, nestedElements)

    handleRef(refAttr, ref)
    val entExt = getOptExt(path.dropRight(2)).getOrElse("")
    addJoins(ent, entExt, refAttr, ref, joinType)

    val id = s"$ent.id"
    nestedIds += id
    groupByCols += id // if we later need to group by non-aggregated columns

    castStrategy = castStrategy.nest
    resolve(nestedElements)
  }

  private def addJoins(
    ent: String, entExt: String, refAttr: String, ref: String, joinType: String
  ): Unit = {
    val (refAs, refExt) = getOptExt().fold(("", ""))(ext => (ref + ext, ext))
    val joinTable       = ss(ent, refAttr, ref)
    val (id1, id2)      = if (ent == ref) ("1_id", "2_id") else ("id", "id")
    val eid1            = ss(ent, id1)
    val ref_id2         = ss(ref, id2)
    joins += ((s"$joinType JOIN", joinTable, "", List(s"$ent$entExt.id = $joinTable.$eid1")))
    joins += ((s"$joinType JOIN", ref, refAs, List(s"$joinTable.$ref_id2 = $ref$refExt.id")))
  }
}