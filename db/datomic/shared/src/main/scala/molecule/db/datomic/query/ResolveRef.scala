package molecule.db.datomic.query

import molecule.boilerplate.ast.MoleculeModel._


trait ResolveRef[Tpl] { self: Sort_[Tpl] with Base[Tpl] =>

  protected def resolveRef(es: List[Var], ref: Ref): List[Var] = {
    val (e, refAttr, refId) = (es.last, s":${ref.ns}/${ref.refAttr}", vv)
    where += s"[$e $refAttr $refId]" -> wClause
    es :+ refId
  }

  protected def resolveBackRef(es: List[Var]): List[Var] = {
    es.init
  }

  protected def resolveNestedRef(es: List[Var], ref: Ref): List[Var] = {
    val (e, refAttr, refId) = (es.last, s":${ref.ns}/${ref.refAttr}", vv)
    nestedIds += e + "-nested"
    where += s"[(identity $e) $e-nested]" -> wGround
    where += s"[$e $refAttr $refId]" -> wClause

    castss = castss :+ casts.toList
    casts.clear()

    sortNestedLevel()
    isNested = true
    es :+ refId
  }

  protected def resolveNestedOptRef(es: List[Var], nestedOpt: NestedOpt): List[Var] = {
    nestedOptIds += es.last //+ "-nestedOpt"
    pull = Some((es.last, nestedOpt))
//    if (isNestedOpt)
//      pullCastss = pullCastss :+ pullCasts.toList
//    pullCasts.clear()
    isNestedOpt = true
    es
  }
}