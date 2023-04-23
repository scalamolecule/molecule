package molecule.base.codegen.render

import molecule.base.ast.SchemaAST._
import molecule.base.util.BaseHelpers


class DslFormatting(schema: MetaSchema, namespace: MetaNs, arity: Int = 0) extends BaseHelpers {
  val pkg          = schema.pkg + ".dsl"
  val domain       = schema.domain
  val maxArity     = schema.maxArity
  val ns           = namespace.ns
  val attrsCustom  = namespace.attrs
  val attrsGeneric = Seq(
    MetaAttr("e", CardOne, "Long", None, Nil, Some("Entity id"), None, Nil),
    MetaAttr("a", CardOne, "String", None, Nil, Some("Attribute name"), None, Nil),
    MetaAttr("v", CardOne, "String", None, Nil, Some("String representation of any type of value"), None, Nil),
    MetaAttr("tx", CardOne, "Long", None, Nil, Some("Transaction entity id"), None, Nil),
    MetaAttr("txDate", CardOne, "Date", None, Nil, Some("Transaction time as java.util.Date"), None, Nil),
    MetaAttr("txOp", CardOne, "Boolean", None, Nil, Some("Transaction operation (add: True or retract: False"), None, Nil)
  )
  val attrsAll     = attrsCustom ++ attrsGeneric
  val genericAttrs = Seq("e", "a", "v", "tx", "txDate", "txOp")
  val refs         = attrsCustom.filter(_.refNs.nonEmpty)
  val backRefs     = namespace.backRefNss

  def camel(s: String) = s"${s.head.toUpper}${s.tail}"

  def getTpe(s: String) = s match {
    case "ref" => "Long"
    case t     => t
  }

  lazy val maxAttrCustom = attrsCustom.map(_.attr.length).max
  lazy val maxAttrAll    = attrsAll.map(_.attr.length).max
  lazy val maxTpeCustom  = attrsCustom.map(a => getTpe(a.baseTpe).length).max
  lazy val maxTpeAll     = attrsAll.map(a => getTpe(a.baseTpe).length).max
  lazy val maxRefAttr    = attrsAll.filter(_.refNs.isDefined).map(_.attr.length).max
  lazy val maxRefNs      = attrsAll.flatMap(_.refNs.map(_.length)).max

  lazy val padAttrCustom = (s: String) => padS(maxAttrCustom, s)
  lazy val padAttrAll    = (s: String) => padS(maxAttrAll, s)
  lazy val padTypeCustom = (s: String) => padS(maxTpeCustom, s)
  lazy val padTypeAll    = (s: String) => padS(maxTpeAll, s)
  lazy val padRefAttr    = (s: String) => padS(maxRefAttr, s)
  lazy val padRefNs      = (s: String) => padS(maxRefNs, s)

  lazy val V        = ('A' + arity - 1).toChar
  lazy val tpes     = (0 until arity) map (n => (n + 'A').toChar)
  lazy val _0       = "_" + arity
  lazy val _1       = "_" + (arity + 1)
  lazy val _2       = "_" + (arity + 2)
  lazy val ns_0     = ns + _0
  lazy val ns_1     = ns + _1
  lazy val ns_2     = ns + _2
  lazy val `, A`    = if (arity == 0) "" else ", " + tpes.mkString(", ")
  lazy val `A..U`   = if (arity <= 1) "" else tpes.init.mkString("", ", ", ", ")
  lazy val `A..V`   = if (arity == 0) "" else tpes.mkString(", ")
  lazy val `A..V, ` = if (arity == 0) "" else tpes.mkString("", ", ", ", ")
  lazy val `[A..V]` = if (arity == 0) "" else tpes.mkString("[", ", ", "]")
}
