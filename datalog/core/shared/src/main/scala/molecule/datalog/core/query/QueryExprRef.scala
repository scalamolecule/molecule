package molecule.datalog.core.query

import java.lang.{Long => jLong}
import molecule.base.ast.CardOne
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.query.QueryExpr
import molecule.datalog.core.query.casting.NestOpt_


trait QueryExprRef[Tpl] extends QueryExpr { self: Model2DatomicQuery[Tpl] with NestOpt_[Tpl] =>

  override protected def queryRef(ref: Ref, tail: List[Element]): Unit = {
    val (e, refAttr, refId) = (es.last, s":${ref.ns}/${ref.refAttr}", vv)
    refConfirmed = false
    val card = if (ref.card.isInstanceOf[CardOne]) "one" else "set"
    varPath = varPath ++ List(card, refAttr, refId)
    path = path ++ List(ref.refAttr, ref.refNs)
    where += s"[$e $refAttr $refId]" -> wClause
    es = es :+ refId
  }


  override protected def queryBackRef(backRef: BackRef, tail: List[Element]): Unit = {
    varPath = varPath.dropRight(3)
    path = path.dropRight(2)
    es = es.init
  }


  override protected def queryOptRef(ref: Ref, optionalElements: List[Element]): Unit = {

  }


  override protected def queryNested(ref: Ref, nestedElements: List[Element]): Unit = {
    isNested = true
    if (isOptNested)
      noMixedNestedModes
    validateRefNs(ref, nestedElements)

    aritiesNested()

    val (e, refAttr, refId) = (es.last, s":${ref.ns}/${ref.refAttr}", vv)
    varPath = varPath ++ List(refAttr, refId)
    path = path ++ List(ref.refAttr, ref.refNs)
    firstId = refId
    val nestedId = "?id" + nestedIds.size
    nestedIds += nestedId
    where += s"[(identity $e) $nestedId]" -> wGround
    where += s"[$e $refAttr $refId]" -> wClause

    // Start new level of casts
    castss = castss :+ Nil

    val nestedIndex          = nestedIds.length - 1
    val levelIdSorter        = (_: Int) => (a: Row, b: Row) =>
      a.get(nestedIndex).asInstanceOf[jLong].compareTo(b.get(nestedIndex).asInstanceOf[jLong])
    val dummyIndexOfNestedId = 6
    sortss = sortss.init :+ (sortss.last :+ (dummyIndexOfNestedId -> levelIdSorter))
    sortss = sortss :+ Nil

    es = es :+ refId

    resolve(nestedElements)
  }


  override protected def queryOptNested(nestedRef: Ref, nestedElements: List[Element]): Unit = {
    isOptNested = true
    if (isNested) {
      noMixedNestedModes
    }
    if (expectedFilterAttrs.nonEmpty) {
      throw ModelError("Filter attributes not allowed in optional nested queries.")
    }
    validateRefNs(nestedRef, nestedElements)

    // On top level, move past nested pull date to tx meta data (if any)
    attrIndex += 1

    aritiesNested()

    val e = es.last
    val nestedId = "?id" + nestedIds.size
    if (where.isEmpty) {
      val Ref(ns, refAttrClean, refNs, _, _, _) = nestedRef
      val (refAttr, refId)                      = (s":$ns/$refAttrClean", vv)
      varPath = varPath ++ List(refAttr, refId)
      path = path ++ List(refAttrClean, refNs)
      where += s"[$e $refAttr $refId]" -> wClause
    }
    where += s"[(identity $e) $nestedId]" -> wGround

    if (where.length == 2 && where.head._1.startsWith("[(identity")) {
      /*
      When only one identity function for single optional attribute and one for identity of pull entity id

      ([(identity ?a) ?a-?b],1)
      ([(identity ?a) ?id0],1)
       */
      throw ModelError("Single optional attribute before optional nested data structure is not allowed.")
    }

    // Add nested caster
    castss = (castss.head :+ pullNestedData) +: castss.tail

    // Start new level of casts
    castss = castss :+ Nil

    resolveOptNestedElements(e, nestedRef, nestedElements)
  }
}