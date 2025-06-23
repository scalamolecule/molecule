package molecule.db.core.api.expression

import molecule.core.dataModel.*
import molecule.db.core.api.Tail
//import scala.Tuple.Tail // requires NonEmptyTuple in scala 3.3.6, not in 3.7


trait ExprMapOptOps[Tpl <: Tuple, T, This[_ <: Tuple, _], Next[_ <: Tuple, _]]
  extends FilterAttr[Tpl, T, This, Next] {
  protected def _exprMapOpt(op: Op, map: Option[Map[String, T]]): This[Tpl                   , T] = ???
  protected def _exprMapOpK(op: Op, key: String                ): This[Option[T] *: Tail[Tpl], T] = ???
}

trait ExprMapOpt[Tpl <: Tuple, T, This[_ <: Tuple, _], Next[_ <: Tuple, _]]
  extends ExprMapOptOps[Tpl, T, This, Next]{
  def apply(map: Option[Map[String, T]]): This[Tpl                   , T] = _exprMapOpt(Eq , map)
  def apply(key: String                ): This[Option[T] *: Tail[Tpl], T] = _exprMapOpK(Has, key)
}