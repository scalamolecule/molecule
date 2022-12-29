package molecule.boilerplate.ast

import molecule.base.ast.SchemaAST._
import molecule.boilerplate.api.Keywords.Kw
import scala.annotation.tailrec


object ModelBase extends ModelBase
trait ModelBase extends Validations {

  sealed trait Element {
    def render(i: Int = 0): String = "  " * i + this.toString
    def renders(es: Seq[Element], i: Int): String = es.map(_.render(i)).mkString(",\n")
  }

  trait Attr extends Element {
    val ns  : String
    val attr: String
    val op  : Op
    val sort: Option[String]
    def unapply(a: Attr): (String, String, Op) = (a.ns, a.attr, a.op)
    def name = ns + "." + attr
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

  case class Composite(elements: Seq[Element]) extends Element with Mandatory {
    override def render(i: Int): String = {
      s"""|${"  " * i}Composite(List(
          |${renders(elements, i + 1)}))""".stripMargin
    }
    override def toString: String = render(0)
  }

  case class Nested(ref: Ref, elements: Seq[Element]) extends Element with Mandatory {
    override def render(i: Int): String = {
      val indent = "  " * i
      s"""|${indent}Nested(
          |${indent}  $ref,
          |${indent}  List(
          |${renders(elements, i + 2)}))""".stripMargin
    }
    override def toString: String = render(0)
  }

  case class NestedOpt(ref: Ref, elements: Seq[Element]) extends Element with Mandatory {
    override def render(i: Int): String = {
      val indent = "  " * i
      s"""|${indent}NestedOpt(
          |${indent}  $ref,
          |${indent}  List(
          |${renders(elements, i + 2)}))""".stripMargin
    }
    override def toString: String = render(0)
  }

  case class TxMetaData(elements: Seq[Element]) extends Element with Mandatory {
    override def render(i: Int): String = {
      s"""|${"  " * i}TxMetaData(List(
          |${renders(elements, i + 1)}))""".stripMargin
    }
    override def toString: String = render(0)
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
  case object Swap extends Op
  case object Remove extends Op
}
