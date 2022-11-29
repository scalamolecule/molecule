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

  sealed trait Mode
  trait Mandatory extends Mode
  trait Optional extends Mode
  trait Tacit extends Mode

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

  case class Nested(ref: Ref, elements: Seq[Element]) extends Element with Mandatory {
    def render(elements: Seq[Element], i: Int): String = {
      val indent = "  " * i
      elements.map {
        case Nested(ref, elements1) =>
          s"""|Nested(
              |$indent  $ref,
              |$indent  List(
              |$indent    ${render(elements1, i + 2)}))""".stripMargin
        case other => other
      }.mkString(s",\n$indent")
    }
    override def toString: String = render(Seq(this), 0)
  }

  case class NestedOpt(ref: Ref, elements: Seq[Element]) extends Element with Mandatory {
    def render(elements: Seq[Element], i: Int): String = {
      val indent = "  " * i
      elements.map {
        case NestedOpt(ref, elements1) =>
          s"""|NestedOpt(
              |$indent  $ref,
              |$indent  List(
              |$indent    ${render(elements1, i + 2)}))""".stripMargin
        case other                  => other
      }.mkString(s",\n$indent")
    }
    override def toString: String = render(Seq(this), 0)
  }

  case class TxMetaData(elements: Seq[Element]) extends Element with Mandatory {
    override def toString: String = elements.mkString("TxMetaData(List(\n  ", ",\n  ", "\n))")
  }

  case class Composite(elements: Seq[Element]) extends Element with Mandatory {
    override def toString: String = elements.mkString("Composite(List(\n  ", ",\n  ", "\n))")
  }

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
