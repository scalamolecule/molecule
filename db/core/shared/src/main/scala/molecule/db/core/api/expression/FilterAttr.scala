package molecule.db.core.api.expression

import molecule.base.metaModel.CardOne
import molecule.core.dataModel.*
import molecule.db.core.api.*
import molecule.db.core.ops.ModelTransformations_
import scala.Tuple.*


trait FilterAttr[Tpl <: Tuple, T, This[_ <: Tuple, _], Next[_ <: Tuple, _]] {
  protected def _filterAttrTacTac(op: Op, a: Molecule_0 & CardOne): This[Tpl, T]
  protected def _filterAttrManTac(op: Op, a: Molecule_0 & CardOne): This[Tpl, T] & SortAttrs[Tpl, T, This] = ???

  protected def _filterAttrTacMan[ns[_ <: Tuple, _]](op: Op, a: Molecule & SortAttrsOps[Tuple1[T], T, ns]): Next[T *: Tpl, T]
  protected def _filterAttrManMan[ns[_ <: Tuple, _]](op: Op, a: Molecule & SortAttrsOps[Tuple1[T], T, ns]): Next[T *: Tpl, T] & SortAttrs[T *: Tpl, T, Next] = ???
}