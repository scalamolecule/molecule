package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase


object _ExprSetOpt extends BoilerplateGenBase( "ExprSetOpt", "/api/expression") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.api.expression
       |
       |import molecule.boilerplate.ast.Model._
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends ExprAttr_$arity[${`A..V, `}t, Ns1, Ns2] {
         |  protected def _exprSetOpt(op: Op, optSets: Option[Seq[Set[t]]]): Ns1[${`A..V`}, t] = ???
         |}
         |
         |trait $fileName_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t, Ns1, Ns2]{
         |  def apply(set : Option[Set[t]]     )               : Ns1[${`A..V`}, t] = _exprSetOpt(Eq   , set.map(set => Seq(set))   )
         |  def apply(sets: Option[Seq[Set[t]]])(implicit x: X): Ns1[${`A..V`}, t] = _exprSetOpt(Eq   , sets                       )
         |  def has  (v   : Option[t]          )               : Ns1[${`A..V`}, t] = _exprSetOpt(Has  , v.map(v => Seq(Set(v)))    )
         |  def has  (vs  : Option[Seq[t]]     )(implicit x: X): Ns1[${`A..V`}, t] = _exprSetOpt(Has  , vs.map(_.map(v => Set(v))) )
         |  def hasNo(v   : Option[t]          )               : Ns1[${`A..V`}, t] = _exprSetOpt(HasNo, v.map(v => Seq(Set(v)))    )
         |  def hasNo(vs  : Option[Seq[t]]     )(implicit x: X): Ns1[${`A..V`}, t] = _exprSetOpt(HasNo, vs.map(_.map(v => Set(v))) )
         |}""".stripMargin
  }
}
