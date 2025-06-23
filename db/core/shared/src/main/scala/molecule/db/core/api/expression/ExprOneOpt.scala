package molecule.db.core.api.expression

import molecule.core.dataModel.*
import molecule.db.core.api.*


trait ExprOneOptOps[Tpl <: Tuple, T, This[_ <: Tuple, _], Next[_ <: Tuple, _]]
  extends FilterAttr[Tpl, T, This, Next] {
  protected def _exprOneOpt(op: Op, opt: Option[T]): This[Tpl, T] & SortAttrs[Tpl, T, This]
}

trait ExprOneOpt[Tpl <: Tuple, T, This[_ <: Tuple, _], Next[_ <: Tuple, _]]
  extends ExprOneOptOps[Tpl, T, This, Next]
    with SortAttrs[Tpl, T, This]
    {
  def apply(opt: Option[T]) = _exprOneOpt(Eq, opt)
}

trait ExprOneOpt_Enum[Tpl <: Tuple, T, This[_ <: Tuple, _], Next[_ <: Tuple, _]]
  extends ExprOneOptOps[Tpl, T, This, Next]
    with SortAttrs[Tpl, T, This] {
  def apply(opt: Option[T]) = _exprOneOpt(Eq, opt.map(_.toString.asInstanceOf[T]))
}

