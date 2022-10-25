package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase

object _ExprOneMan extends BoilerplateGenBase( "exprOneMan", "/api/expression") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |molecule.boilerplate.api.expression
       |
       |import molecule.boilerplate.api._
       |import molecule.boilerplate.ast.MoleculeModel._
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V`}, t, Ns[${`_, _`}]] {
         |  protected def _exprMan(op: Op, vs: Seq[t]): Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns]
         |}
         |
         |trait ${fileName}_$arity[${`A..V`}, t, Ns[${`_, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t, Ns]
         |    with Aggregates_$arity[${`A..V`}, t, Ns]
         |    with SortAttrs_$arity[${`A..V`}, t, Ns] { self: Ns[${`_, _`}] =>
         |  def apply(v    : t, vs: t*): Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprMan(Eq, v +: vs)
         |  def apply(vs   : Seq[t])   : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprMan(Eq, vs)
         |  def ==   (v    : t)        : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprMan(Eq, Seq(v))
         |  def ==   (v    : t, vs: t*): Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprMan(Eq, v +: vs)
         |  def ==   (vs   : Seq[t])   : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprMan(Eq, vs)
         |  def not  (v    : t, vs: t*): Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprMan(Neq, v +: vs)
         |  def not  (vs   : Seq[t])   : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprMan(Neq, vs)
         |  def !=   (v    : t)        : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprMan(Neq, Seq(v))
         |  def !=   (v    : t, vs: t*): Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprMan(Neq, v +: vs)
         |  def !=   (vs   : Seq[t])   : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprMan(Neq, vs)
         |  def <    (upper: t)        : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprMan(Lt, Seq(upper))
         |  def <=   (upper: t)        : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprMan(Le, Seq(upper))
         |  def >    (lower: t)        : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprMan(Gt, Seq(lower))
         |  def >=   (lower: t)        : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprMan(Ge, Seq(lower))
         |}""".stripMargin
  }
}
