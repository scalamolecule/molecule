package molecule.sql.core.query

import molecule.base.ast._
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.query.QueryExpr


trait QueryExprRef extends QueryExpr { self: SqlQueryBase =>

  override protected def queryRef(ref: Ref, tail: List[Element]): Unit = {
    val Ref(ns, refAttr, refNs, card, _, _) = ref
    if (isOptNested && card == CardSet) {
      throw ModelError(
        s"Only cardinality-one refs allowed in optional nested queries ($ns.$refAttr)."
      )
    }
    checkOnlyOptRef()
    handleRef(refAttr, refNs)

    val singleOptSet    = tail.length == 1 && tail.head.isInstanceOf[AttrSetOpt]
    val (refAs, refExt) = getOptExt().fold(("", ""))(ext => (refNs + ext, ext))

    val nsExt = if (ns == refNs)
      "" // self-joins
    else
      getOptExt(path.dropRight(2)).getOrElse("")

    if (ref.card == CardOne) {
      val joinType = if (isOptNested) "LEFT" else "INNER"
      joins += ((s"$joinType JOIN", refNs, refAs, s"$ns$nsExt.$refAttr", s"= $refNs$refExt.id"))
    } else {
      val joinTable  = ss(ns, refAttr, refNs)
      val (id1, id2) = if (ns == refNs) ("1_id", "2_id") else ("id", "id")
      val ns_id1     = ss(ns, id1)
      val refNs_id2  = ss(refNs, id2)
      val joinType   = if (singleOptSet) "LEFT" else "INNER"
      joins += ((s"$joinType JOIN", joinTable, "", s"$ns$nsExt.id", s"= $joinTable.$ns_id1"))
      joins += ((s"$joinType JOIN", refNs, refAs, s"$joinTable.$refNs_id2", s"= $refNs$refExt.id"))
    }
  }


  override protected def queryBackRef(backRef: BackRef, tail: List[Element]): Unit = {
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
    //    level += 1

    hasOptRef = true
    //    if (expectedFilterAttrs.nonEmpty) {
    //      throw ModelError("Filter attributes not allowed in optional nested queries.")
    //    }
    val Ref(_, refAttr, refNs, _, _, _) = ref
    handleRef(refAttr, refNs)

//    aritiesNested()
//    resolveOptNestedRef(ref)
    resolve(nestedElements)
  }


  override protected def queryNested(
    ref: Ref, nestedElements: List[Element]
  ): Unit = {
    level += 1
    isManNested = true
    if (isOptNested) {
      noMixedNestedModes
    }
    validateRefNs(ref, nestedElements)

    val Ref(ns, refAttr, refNs, _, _, _) = ref
    handleRef(refAttr, refNs)

    aritiesNested()

    val (as, ext) = getOptExt().fold(("", ""))(ext => (refNs + ext, ext))
    val nsExt     = getOptExt(path.dropRight(2)).getOrElse("")

    val id = s"$ns.id"
    nestedIds += id
    groupByCols += id // if we later need to group by non-aggregated columns

    val joinTable  = ss(ns, refAttr, refNs)
    val (id1, id2) = if (ns == refNs) ("1_id", "2_id") else ("id", "id")
    val ns_id1     = ss(ns, id1)
    val refNs_id2  = ss(refNs, id2)
    joins += (("INNER JOIN", joinTable, "", s"$ns$nsExt.id", s"= $joinTable.$ns_id1"))
    joins += (("INNER JOIN", refNs, as, s"$joinTable.$refNs_id2", s"= $refNs$ext.id"))
    castss = castss :+ Nil

    resolve(nestedElements)
  }


  override protected def queryOptNested(ref: Ref, nestedElements: List[Element]): Unit = {
    level += 1
    isOptNested = true
    if (isManNested) {
      noMixedNestedModes
    }
    if (expectedFilterAttrs.nonEmpty) {
      throw ModelError("Filter attributes not allowed in optional nested queries.")
    }
    validateRefNs(ref, nestedElements)

    val Ref(ns, refAttr, refNs, _, _, _) = ref
    handleRef(refAttr, refNs)

    aritiesNested()

    val (as, ext) = getOptExt().fold(("", ""))(ext => (refNs + ext, ext))
    val nsExt     = getOptExt(path.dropRight(2)).getOrElse("")

    val id = s"$ns.id"
    nestedIds += id
    groupByCols += id // if we later need to group by non-aggregated columns

    val joinTable  = ss(ns, refAttr, refNs)
    val (id1, id2) = if (ns == refNs) ("1_id", "2_id") else ("id", "id")
    val ns_id1     = ss(ns, id1)
    val refNs_id2  = ss(refNs, id2)
    joins += (("LEFT JOIN", joinTable, "", s"$ns$nsExt.id", s"= $joinTable.$ns_id1"))
    joins += (("LEFT JOIN", refNs, as, s"$joinTable.$refNs_id2", s"= $refNs$ext.id"))
    castss = castss :+ Nil

    resolve(nestedElements)
  }
}