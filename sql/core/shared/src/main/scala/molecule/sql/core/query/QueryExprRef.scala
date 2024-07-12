package molecule.sql.core.query

import molecule.base.ast._
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.query.{Model2Query, QueryExpr}


trait QueryExprRef extends QueryExpr { self: Model2Query with SqlQueryBase =>

  override protected def queryRef(ref: Ref, tail: List[Element]): Unit = {
    val Ref(ns, refAttr, refNs, card, _, _) = ref
    if (isOptNested && card == CardSet) {
      throw ModelError(
        s"Only cardinality-one refs allowed in optional nested queries ($ns.$refAttr)."
      )
    }
    checkOnlyOptRef()
    handleRef(refAttr, refNs)

    val singleOptSet = tail.length == 1 && tail.head.isInstanceOf[AttrSetOpt]

    val nsExt = if (ns == refNs)
      "" // self-joins
    else
      getOptExt(path.dropRight(2)).getOrElse("")

    if (ref.card == CardOne) {
      val (refAs, refExt) = getOptExt().fold(("", ""))(ext => (refNs + ext, ext))
      val joinType        = if (isOptNested) "LEFT" else "INNER"
      joins += ((s"$joinType JOIN", refNs, refAs, List(s"$ns$nsExt.$refAttr = $refNs$refExt.id")))
    } else {
      val joinType = if (singleOptSet) "LEFT" else "INNER"
      addJoins(ns, nsExt, refAttr, refNs, joinType)
    }
  }


  override protected def queryBackRef(backRef: BackRef, tail: List[Element]): Unit = {
    checkOnlyOptRef()
    if (isManNested || isOptNested) {
      val BackRef(bRef, _, _) = backRef
      tail.head match {
        case a: Attr => throw ModelError(
          s"Expected ref after backref _$bRef. " +
            s"Please add attribute ${a.ns}.${a.attr} to initial namespace ${a.ns} " +
            s"instead of after backref _$bRef."
        )
        case _       => ()
      }
    }
    handleBackRef()
  }


  override protected def queryOptRef(ref: Ref, nestedElements: List[Element]): Unit = {

    println(ref)
    println(s"========================= $hasOptRef  A  ")
    if (hasOptRef) {
      println("-------- B  ")
      // transfer previous predicates from where
      addPredicatesToLastLeftJoin()
    }
    hasOptRef = true

    // Know where we should steal predicates from subsequent `where` additions
    whereSplit = where.length

    val Ref(ns, refAttr, refNs, _, _, _) = ref
    handleRef(refAttr, refNs)

    val nsExt           = getOptExt(path.dropRight(2)).getOrElse("")
    val (refAs, refExt) = getOptExt().fold(("", ""))(ext => (refNs + ext, ext))
    joins += ((s"LEFT JOIN", refNs, refAs, List(s"$ns$nsExt.$refAttr = $refNs$refExt.id")))

//    println("===================================")
//    println(self.elements0)
//    joins.foreach(println)
//    println("----------")
//    where.foreach(println)


//    aritiesAddNested()
//    castss = castss :+ Nil



    resolve(nestedElements)
  }


  override protected def queryNested(ref: Ref, nestedElements: List[Element]): Unit = {
    isManNested = true
    if (isOptNested) {
      noMixedNestedModes
    }
    resolveNested(ref, nestedElements, "INNER")
  }


  override protected def queryOptNested(ref: Ref, nestedElements: List[Element]): Unit = {
    isOptNested = true
    if (isManNested) {
      noMixedNestedModes
    }
    if (expectedFilterAttrs.nonEmpty) {
      throw ModelError("Filter attributes not allowed in optional nested queries.")
    }
    resolveNested(ref, nestedElements, "LEFT")
  }


  private def resolveNested(ref: Ref, nestedElements: List[Element], joinType: String): Unit = {
    val Ref(ns, refAttr, refNs, _, _, _) = ref
    level += 1
    checkOnlyOptRef()
    validateRefNs(ref, nestedElements)

    handleRef(refAttr, refNs)
    val nsExt = getOptExt(path.dropRight(2)).getOrElse("")
    addJoins(ns, nsExt, refAttr, refNs, joinType)

    val id = s"$ns.id"
    nestedIds += id
    groupByCols += id // if we later need to group by non-aggregated columns

    casts = casts.nest
//    aritiesAddNested()
//    castss = castss :+ Nil

    resolve(nestedElements)
  }

  private def addJoins(ns: String, nsExt: String, refAttr: String, refNs: String, joinType: String): Unit = {
    val (refAs, refExt) = getOptExt().fold(("", ""))(ext => (refNs + ext, ext))
    val joinTable       = ss(ns, refAttr, refNs)
    val (id1, id2)      = if (ns == refNs) ("1_id", "2_id") else ("id", "id")
    val ns_id1          = ss(ns, id1)
    val refNs_id2       = ss(refNs, id2)
    joins += ((s"$joinType JOIN", joinTable, "", List(s"$ns$nsExt.id = $joinTable.$ns_id1")))
    joins += ((s"$joinType JOIN", refNs, refAs, List(s"$joinTable.$refNs_id2 = $refNs$refExt.id")))
  }
}