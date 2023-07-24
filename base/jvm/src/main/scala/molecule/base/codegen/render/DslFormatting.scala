package molecule.base.codegen.render

import molecule.base.ast.SchemaAST._
import molecule.base.util.BaseHelpers


class DslFormatting(schema: MetaSchema, namespace: MetaNs, arity: Int = 0) extends BaseHelpers {
  val pkg      = schema.pkg + ".dsl"
  val domain   = schema.domain
  val maxArity = schema.maxArity
  val ns       = namespace.ns
  val attrs    = namespace.attrs
  val refs     = attrs.filter(_.refNs.nonEmpty)
  val backRefs = namespace.backRefNss
  val custom   = ns != "Tx"

  def camel(s: String) = s"${s.head.toUpper}${s.tail}"

  def getTpe(s: String) = s match {
    case "ref" => "Long"
    case t     => t
  }

  lazy val maxAttr    = attrs.map(_.attr.length).max
  lazy val maxTpe     = attrs.map(a => getTpe(a.baseTpe).length).max
  lazy val maxRefAttr = attrs.filter(_.refNs.isDefined).map(_.attr.length).max
  lazy val maxRefNs   = attrs.flatMap(_.refNs.map(_.length)).max

  lazy val padAttr    = (s: String) => padS(maxAttr, s)
  lazy val padType    = (s: String) => padS(maxTpe, s)
  lazy val padRefAttr = (s: String) => padS(maxRefAttr, s)
  lazy val padRefNs   = (s: String) => padS(maxRefNs, s)

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

  val tpl = arity match {
    case 0 => "Nothing"
    case 1 => "A"
    case _ => s"(${`A..V`})"
  }

  def padN(n: Int) = if (n < 10) s"0$n" else n
  val n0 = padN(arity)
  val n1 = padN(arity + 1)

  //  val txs_    = (0 to 22).map(i => s"Tx$i[" + Seq.fill(i + 1)("_").mkString(",") + "]").mkString(", ")
  //  val txs     = (0 to 22).map(i => s"Tx$i").mkString(", ")
  val dummies = if (maxArity == 22) "" else (maxArity + 2 to 23).map(i => s"X$i").mkString(", ", ", ", "")
  val tx_n    = (0 to maxArity).map(i => s"Tx_$i").mkString(", ") + dummies
}
