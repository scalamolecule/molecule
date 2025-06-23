package molecule.db.core.api.expression

import molecule.core.dataModel.*


trait ExprBArOptOps[Tpl <: Tuple, T, This[_ <: Tuple, _], Next[_ <: Tuple, _]] {
  protected def _exprBArOpt(op: Op, optByteArray: Option[Array[T]]): This[Tpl, T] = ???
}

trait ExprBArOpt[Tpl <: Tuple, T, This[_ <: Tuple, _], Next[_ <: Tuple, _]]
  extends ExprBArOptOps[Tpl, T, This, Next]{
  def apply(byteArray: Option[Array[T]]): This[Tpl, T] = _exprBArOpt(Eq, byteArray)
}

