package molecule.db.core.api.expression

import molecule.core.dataModel.*


trait ExprSetOptOps[Tpl <: Tuple, T, This[_ <: Tuple, _], Next[_ <: Tuple, _]]
  extends FilterAttr[Tpl, T, This, Next] {
  protected def _exprSetOpt(op: Op, optSet: Option[Set[T]]): This[Tpl, T] = ???
}

trait ExprSetOpt[Tpl <: Tuple, T, This[_ <: Tuple, _], Next[_ <: Tuple, _]]
  extends ExprSetOptOps[Tpl, T, This, Next] {
  def apply(optSet: Option[Set[T]]): This[Tpl, T] = _exprSetOpt(Eq, optSet)
}

trait ExprSetOpt_Enum[Tpl <: Tuple, T, This[_ <: Tuple, _], Next[_ <: Tuple, _]]
  extends ExprSetOptOps[Tpl, T, This, Next] {
  def apply(optSet: Option[Set[T]]) = _exprSetOpt(Eq, optSet.map(_.map(_.toString.asInstanceOf[T])))
}