package molecule.db.core.api.expression

import molecule.core.dataModel.*


trait ExprSeqOptOps[Tpl <: Tuple, T, This[_ <: Tuple, _], Next[_ <: Tuple, _]]
  extends FilterAttr[Tpl, T, This, Next] {
  protected def _exprSeqOpt(op: Op, optSeq: Option[Seq[T]]): This[Tpl, T] = ???
}

trait ExprSeqOpt[Tpl <: Tuple, T, This[_ <: Tuple, _], Next[_ <: Tuple, _]]
  extends ExprSeqOptOps[Tpl, T, This, Next]{
  def apply(optSeq: Option[Seq[T]]): This[Tpl, T] = _exprSeqOpt(Eq, optSeq)
}

trait ExprSeqOpt_Enum[Tpl <: Tuple, T, This[_ <: Tuple, _], Next[_ <: Tuple, _]]
  extends ExprSeqOptOps[Tpl, T, This, Next]{
  def apply(optSeq: Option[Seq[T]]) = _exprSeqOpt(Eq, optSeq.map(_.map(_.toString.asInstanceOf[T])))
}
