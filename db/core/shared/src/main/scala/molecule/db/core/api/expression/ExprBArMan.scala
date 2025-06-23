package molecule.db.core.api.expression

import molecule.core.dataModel.*


trait ExprBArMan[Tpl <: Tuple, T, This[_ <: Tuple, _], Next[_ <: Tuple, _]]
  extends ExprBArTacOps[Tpl, T, This, Next] {
  def apply(                   ): This[Tpl, T] = _exprBAr(NoValue, Array.empty[Byte].asInstanceOf[Array[T]])
  def apply(byteArray: Array[T]): This[Tpl, T] = _exprBAr(Eq     , byteArray                               )
  def not  (byteArray: Array[T]): This[Tpl, T] = _exprBAr(Neq    , byteArray                               )
}

