package molecule.db.common.query

import scala.collection.mutable.ListBuffer
import molecule.core.dataModel.*
import molecule.core.error.ModelError


trait QueryExprRef extends QueryExpr { self: Model2Query & SqlQueryBase =>

  protected def queryRefOLD(r: Ref, tail: List[Element]): Unit = {
    val Ref(ent, refAttr, ref, card, _, _, _) = r
    if (isOptNested && card == SetValue) {
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

    if (card == OneValue) {
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
    } else {
      if (nestedOptRef) {
        onlyOneValueInsideOptRef(r)
      }
      val singleOptSet = tail.length == 1 && tail.head.isInstanceOf[AttrSetOpt]
      val joinType     = if (singleOptSet) "LEFT" else "INNER"
      addJoins(ent, entExt, refAttr, ref, joinType)
    }
  }

  override protected def queryRef(r: Ref, tail: List[Element]): Unit = {
    val Ref(ent, refAttr, ref, relationship, _, _, reverseRefAttr) = r
    if (isOptNested && relationship == ManyToMany) {
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


    println(s"path: $path")


    relationship match {
      case ManyToMany =>
        if (nestedOptRef) {
          onlyOneValueInsideOptRef(r)
        }
        val singleOptSet = tail.length == 1 && tail.head.isInstanceOf[AttrSetOpt]
        val joinType     = if (singleOptSet) "LEFT" else "INNER"
        addJoins(ent, entExt, refAttr, ref, joinType)

      case ManyToOne =>
        val (refAs, refExt) = getOptExt().fold(("", ""))(ext => (ref + ext, ext))

        println(s"refAs: $refAs, refExt: $refExt, getOptExt: ${getOptExt()}")

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

      case OneToMany if isNested =>
        ???

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

      case _ => ???
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

    val Ref(ent, refAttr, ref, rel, _, _, optRevRef) = r
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

    val Ref(ent, refAttr, ref, relationship, _, _, optRevRefAttr) = r
    val revRefAttr = optRevRefAttr.get

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