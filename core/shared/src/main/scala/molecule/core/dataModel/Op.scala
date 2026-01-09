package molecule.core.dataModel

import molecule.core.util.BaseHelpers.*

sealed trait Op
case object NoValue extends Op
case object V extends Op
case object Eq extends Op
case object Neq extends Op
case object Lt extends Op
case object Le extends Op
case object Gt extends Op
case object Ge extends Op
case object StartsWith extends Op
case object EndsWith extends Op
case object Contains extends Op
case object Matches extends Op
case object Remainder extends Op
case object Even extends Op
case object Odd extends Op


// Cardinality Set
case object Has extends Op
case object HasNo extends Op
case object GetV extends Op
case object Add extends Op
case object Remove extends Op

case class AggrFn(
  baseType: String,
  fn: String,
  n: Option[Int] = None,
  op: Option[Op] = None,
  v: Option[Value] = None,
) extends Op {
  override def toString: String = s"""Fn("$baseType", "$fn", ${opt(n)}, ${opt(op)}, ${opt(v)})"""
}

sealed trait AttrOp extends Op
object AttrOp {
  // String ops
  case object Append extends AttrOp
  case object Prepend extends AttrOp
  case class SubString(start: Int, end: Int) extends AttrOp
  case object ReplaceAll extends AttrOp
  case object ToLower extends AttrOp
  case object ToUpper extends AttrOp
  // Numeric ops
  case object Plus extends AttrOp
  case object Minus extends AttrOp
  case object Times extends AttrOp
  case object Divide extends AttrOp
  case object Negate extends AttrOp
  case object Abs extends AttrOp
  case object AbsNeg extends AttrOp
  case object Ceil extends AttrOp
  case object Floor extends AttrOp
  // Boolean ops
  case object And extends AttrOp
  case object Or extends AttrOp
  case object Not extends AttrOp
}
