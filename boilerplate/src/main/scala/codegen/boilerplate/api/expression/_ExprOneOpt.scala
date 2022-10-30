package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase

object _ExprOneOpt extends BoilerplateGenBase( "ExprOneOpt", "/api/expression") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.api.expression
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
         |  protected def _exprOpt(op: Op, vs: Option[Seq[t]]): Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns]
         |}
         |
         |trait ${fileName}_$arity[${`A..V`}, t, Ns[${`_, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t, Ns]
         |    with SortAttrs_$arity[${`A..V`}, t, Ns] {
         |  def apply(v    : Option[t]     )                           : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprOpt(Eq, v.map(Seq(_)))
         |  def apply(vs   : Option[Seq[t]])(implicit d: DummyImplicit): Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprOpt(Eq, vs)
         |  def not  (v    : Option[t]     )                           : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprOpt(Neq, v.map(Seq(_)))
         |  def not  (vs   : Option[Seq[t]])(implicit d: DummyImplicit): Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprOpt(Neq, vs)
         |  def <    (upper: Option[t]     )                           : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprOpt(Lt, upper.map(Seq(_)))
         |  def <=   (upper: Option[t]     )                           : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprOpt(Le, upper.map(Seq(_)))
         |  def >    (lower: Option[t]     )                           : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprOpt(Gt, lower.map(Seq(_)))
         |  def >=   (lower: Option[t]     )                           : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprOpt(Ge, lower.map(Seq(_)))
         |}""".stripMargin
  }
}
