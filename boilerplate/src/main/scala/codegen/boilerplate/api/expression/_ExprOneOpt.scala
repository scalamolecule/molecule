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
         |trait ${fileName}Ops_$arity[${`A..V`}, t, Ns[${`_, _`}]] extends ExprBase {
         |  protected def _exprOneOpt(op: Op, vs: Option[Seq[t]]): Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = ???
         |}
         |
         |trait ${fileName}_$arity[${`A..V`}, t, Ns[${`_, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t, Ns]
         |    with SortAttrs_$arity[${`A..V`}, t, Ns] {
         |  def apply(v    : Option[t]     )(implicit x: X): Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprOneOpt(Appl, v.map(Seq(_))    )
         |  def apply(vs   : Option[Seq[t]])               : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprOneOpt(Appl, vs               )
         |  def not  (v    : Option[t]     )(implicit x: X): Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprOneOpt(Not , v.map(Seq(_))    )
         |  def not  (vs   : Option[Seq[t]])               : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprOneOpt(Not , vs               )
         |  def <    (upper: Option[t]     )               : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprOneOpt(Lt  , upper.map(Seq(_)))
         |  def <=   (upper: Option[t]     )               : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprOneOpt(Le  , upper.map(Seq(_)))
         |  def >    (lower: Option[t]     )               : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprOneOpt(Gt  , lower.map(Seq(_)))
         |  def >=   (lower: Option[t]     )               : Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprOneOpt(Ge  , lower.map(Seq(_)))
         |}""".stripMargin
  }
}
