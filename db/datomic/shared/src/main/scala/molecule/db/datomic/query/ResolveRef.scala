package molecule.db.datomic.query

import java.lang.{Long => jLong}
import molecule.boilerplate.ast.MoleculeModel._


trait ResolveRef[Tpl] { self: Sort_[Tpl] with Base[Tpl] =>

  protected def resolveRef(es: List[Var], ref: Ref): List[Var] = {
    val (e, refAttr, refId) = (es.last, s":${ref.ns}/${ref.refAttr}", vv)
    where += s"[$e $refAttr $refId]" -> wClause
    es :+ refId
  }

  protected def resolveNestedRef(es: List[Var], ref: Ref): List[Var] = {
    val (e, refAttr, refId) = (es.last, s":${ref.ns}/${ref.refAttr}", vv)
    nestedIds += e + "-nested"
    where += s"[(identity $e) $e-nested]" -> wGround
    where += s"[$e $refAttr $refId]" -> wClause

    // Start new level of casts
    castss = castss :+ casts.toList
    casts.clear()

    sortNestedLevel()
    isNested = true
    es :+ refId
  }

  private def sortNestedLevel(): Unit = {
    val nestedIndex   = nestedIds.length - 1
    val levelIdSorter = (_: Int) => (a: Row, b: Row) =>
      a.get(nestedIndex).asInstanceOf[jLong].compareTo(b.get(nestedIndex).asInstanceOf[jLong])

    validateSortIndexes()
    sortsAcc ++= sorts.sortBy(_._1).map(_._2)

    // Group by entity id of this level
    sortsAcc += levelIdSorter

    // Start independent sorting on next nested level
    sorts.clear()
  }

  protected def resolveNestedOptRef(es: List[Var], nestedOpt: NestedOpt): List[Var] = {
    nestedOptIds += es.last
    pull = Some((es.last, nestedOpt))
    isNestedOpt = true
    es
  }
}