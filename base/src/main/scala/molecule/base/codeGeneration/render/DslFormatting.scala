package molecule.base.codeGeneration.render

import molecule.base.ast.SchemaAST.{MetaNs, MetaSchema}
import molecule.base.util.BaseHelpers


class DslFormatting(
  schema: MetaSchema,
  namespace: MetaNs,
  arity: Int = 0
) extends BaseHelpers {

  lazy val pkg      = schema.pkg + ".dsl"
  lazy val domain   = schema.domain
  lazy val maxArity = schema.maxArity
  lazy val ns       = namespace.ns
  lazy val attrs    = namespace.attrs
  lazy val refs     = namespace.attrs.filter(_.refNs.nonEmpty)

  def camel(s: String) = s"${s.head.toUpper}${s.tail}"

  def getTpe(s: String) = s match {
    case "ref" => "Long"
    case t     => t
  }

  lazy val maxAttr = attrs.map(_.attr).map(_.length).max
  lazy val maxTpe  = attrs.map(a => getTpe(a.tpe)).map(_.length).max

  lazy val padAttr = (s: String) => padS(maxAttr, s)
  lazy val padType = (s: String) => padS(maxTpe, s)
  lazy val padTpe2 = (s: String) => padS(maxTpe + 2 + maxTpe, s)

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

  lazy val NS = s"Molecule_${nn(arity)}${`[A..V]`}"
}
