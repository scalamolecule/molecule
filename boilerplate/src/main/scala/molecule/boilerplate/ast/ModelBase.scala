package molecule.boilerplate.ast

import molecule.base.ast.SchemaAST._
import molecule.boilerplate.api.Keywords.Kw


trait ModelBase extends Validations {

  sealed trait Element

  trait Attr extends Element {
    val ns  : String
    val attr: String
    val op  : Op
    val sort: Option[String]
    def unapply(a: Attr): (String, String, Op) = (a.ns, a.attr, a.op)
  }
  trait AttrOne extends Attr
  trait AttrSet extends Attr
  trait AttrArray extends Attr
  trait AttrMap extends Attr

  case class Ref(
    ns: String,
    refAttr: String,
    refNs: String = "",
    card: Cardinality = CardOne
  ) extends Element

  case class BackRef(backRef: String) extends Element

  case class Nested(bond: Ref, elements: Seq[Element]) extends Element
  case class OptNested(bond: Ref, elements: Seq[Element]) extends Element

  case class TxMetaData(elements: Seq[Element]) extends Element
  case class Composite(elements: Seq[Element]) extends Element
  case object Self extends Element

  sealed trait Op
  case object V extends Op
  case object Appl extends Op
  case object Not extends Op
  case object Eq extends Op
  case object Neq extends Op
  case object Lt extends Op
  case object Le extends Op
  case object Gt extends Op
  case object Ge extends Op
  case object NoValue extends Op
  case class Fn(fn: Kw, n: Option[Int] = None) extends Op
  case object Unify extends Op
  case object Add extends Op
  case object Replace extends Op
  case object Delete extends Op
}
