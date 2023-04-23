package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase


object _ExprOneOpt extends BoilerplateGenBase( "ExprOneOpt", "/api/expression") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.api.expression
       |
       |import molecule.boilerplate.api._
       |import molecule.boilerplate.ast.Model._
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends ExprAttr_$arity[${`A..V, `}t, Ns1, Ns2] {
         |  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = ???
         |}
         |
         |trait $fileName_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t, Ns1, Ns2]
         |    with SortAttrs_$arity[${`A..V`}, t, Ns1] {
         |  def apply(v    : Option[t]     )(implicit x: X): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(Eq , v.map(Seq(_))    )
         |  def apply(vs   : Option[Seq[t]])               : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(Eq , vs               )
         |  def not  (v    : Option[t]     )(implicit x: X): Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(Neq, v.map(Seq(_))    )
         |  def not  (vs   : Option[Seq[t]])               : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(Neq, vs               )
         |  def <    (upper: Option[t]     )               : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(Lt , upper.map(Seq(_)))
         |  def <=   (upper: Option[t]     )               : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(Le , upper.map(Seq(_)))
         |  def >    (lower: Option[t]     )               : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(Gt , lower.map(Seq(_)))
         |  def >=   (lower: Option[t]     )               : Ns1[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns1] = _exprOneOpt(Ge , lower.map(Seq(_)))
         |}""".stripMargin
  }
}
