package molecule.base.codegen.render

import molecule.base.ast.SchemaAST.{CardOne, CardSet, MetaAttr, MetaNs, MetaSchema}
import molecule.base.util.BaseHelpers


class DslFormatting(schema: MetaSchema, namespace: MetaNs, arity: Int = 0) extends BaseHelpers {
  val pkg          = schema.pkg + ".dsl"
  val domain       = schema.domain
  val maxArity     = schema.maxArity
  val ns           = namespace.ns
  val attrs        = namespace.attrs ++ Seq(
    MetaAttr("e", CardOne, "Long", None, Nil, Some("Entity id"), None, Nil),
    MetaAttr("a", CardOne, "String", None, Nil, Some("Attribute name"), None, Nil),
    MetaAttr("v", CardOne, "String", None, Nil, Some("String representation of any type of value"), None, Nil),
    MetaAttr("tx", CardOne, "Long", None, Nil, Some("Transaction entity id"), None, Nil),
    MetaAttr("txDate", CardOne, "Date", None, Nil, Some("Transaction time as java.util.Date"), None, Nil),
    MetaAttr("txOp", CardOne, "Boolean", None, Nil, Some("Transaction operation (add: True or retract: False"), None, Nil)
  )
  val genericAttrs = Seq("e", "a", "v", "tx", "txDate", "txOp")
  val refs         = namespace.attrs.filter(_.refNs.nonEmpty)
  val backRefs     = namespace.backRefNss

  def camel(s: String) = s"${s.head.toUpper}${s.tail}"

  def getTpe(s: String) = s match {
    case "ref" => "Long"
    case t     => t
  }

  lazy val maxAttr    = attrs.map(_.attr.length).max
  lazy val maxTpe     = attrs.map(a => getTpe(a.tpe).length).max
  lazy val maxRefAttr = attrs.filter(_.refNs.isDefined).map(_.attr.length).max
  lazy val maxRefNs   = attrs.flatMap(_.refNs.map(_.length)).max

  lazy val padAttr    = (s: String) => padS(maxAttr, s)
  lazy val padType    = (s: String) => padS(maxTpe, s)
  lazy val padRefAttr = (s: String) => padS(maxRefAttr, s)
  lazy val padRefNs   = (s: String) => padS(maxRefNs, s)

  def nn(i: Int) = if (i < 10) s"0$i" else s"$i"

  lazy val V        = ('A' + arity - 1).toChar
  lazy val tpes     = (0 until arity) map (n => (n + 'A').toChar)
  lazy val _0       = "_" + arity
  lazy val _1       = "_" + (arity + 1)
  lazy val ns_0     = ns + _0
  lazy val ns_1     = ns + _1
  lazy val `, A`    = if (arity == 0) "" else ", " + tpes.mkString(", ")
  lazy val `A..U`   = if (arity <= 1) "" else tpes.init.mkString("", ", ", ", ")
  lazy val `A..V`   = if (arity == 0) "" else tpes.mkString(", ")
  lazy val `A..V, ` = if (arity == 0) "" else tpes.mkString("", ", ", ", ")
  lazy val `[A..V]` = if (arity == 0) "" else tpes.mkString("[", ", ", "]")
}
