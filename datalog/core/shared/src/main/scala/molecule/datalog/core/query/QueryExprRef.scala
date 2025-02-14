package molecule.datalog.core.query

import java.lang.{Long => jLong}
import molecule.base.ast.CardOne
import molecule.base.error.ModelError
import molecule.core.ast.DataModel._
import molecule.core.query.QueryExpr


trait QueryExprRef[Tpl] extends QueryExpr { self: Model2DatomicQuery[Tpl] =>

  override protected def queryRef(ref: Ref, tail: List[Element]): Unit = {
    checkOnlyOptRef()
    val (e, refAttr, refId) = (es.last, s":${ref.ent}/${ref.refAttr}", vv)
    refConfirmed = false
    val card = if (ref.card.isInstanceOf[CardOne]) "one" else "set"
    varPath = varPath ++ List(card, refAttr, refId)
    path = path ++ List(ref.refAttr, ref.ref)
    where += s"[$e $refAttr $refId]" -> wClause
    es = es :+ refId
  }


  override protected def queryBackRef(backRef: BackRef, tail: List[Element]): Unit = {
    checkOnlyOptRef()
    varPath = varPath.dropRight(3)
    path = path.dropRight(2)
    es = es.init
  }


  private var firstOptRef = true

  override protected def queryOptRef(ref: Ref, optElements: List[Element]): Unit = {
    nestedOptRef = true
    hasOptRef = true
    firstOptRef = false

    // Add opt ref caster
    castss = castss.init :+ (castss.last :+ pullOptRefData(ref, optElements))

    firstOptRef = true
  }

  override protected def queryOptEntity(optElements: List[Element]): Unit = {
    throw ModelError(
      "Optional entity not implement for Datomic."
    )
  }


  override protected def queryOptNested(nestedRef: Ref, nestedElements: List[Element]): Unit = {
    isOptNested = true
    if (isNested) {
      noMixedNestedModes
    }
    if (expectedFilterAttrs.nonEmpty) {
      throw ModelError("Filter attributes not allowed in optional nested queries.")
    }
    validateRefEntity(nestedRef, nestedElements)

    // On top level, move past nested pull date to tx metadata (if any)
    attrIndex += 1

    val e        = es.last
    val nestedId = "?id" + nestedIds.size
    if (where.isEmpty) {
      val Ref(ent, refAttrClean, ref, _, _, _) = nestedRef
      val (refAttr, refId)                     = (s":$ent/$refAttrClean", vv)
      varPath = varPath ++ List(refAttr, refId)
      path = path ++ List(refAttrClean, ref)
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
    castss = castss.init :+ (castss.last :+ pullOptNestedData)

    // Start new level of casts
    castss = castss :+ Nil

    resolveOptNestedElements(nestedRef, nestedElements)
  }


  override protected def queryNested(ref: Ref, nestedElements: List[Element]): Unit = {
    isNested = true
    checkOnlyOptRef()
    noCardManyInsideOptRef()
    if (isOptNested) {
      noMixedNestedModes
    }
    validateRefEntity(ref, nestedElements)

    val (e, refAttr, refId) = (es.last, s":${ref.ent}/${ref.refAttr}", vv)
    varPath = varPath ++ List(refAttr, refId)
    path = path ++ List(ref.refAttr, ref.ref)
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
}