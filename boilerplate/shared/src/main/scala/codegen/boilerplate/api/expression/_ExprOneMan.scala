package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase


object _ExprOneMan extends BoilerplateGenBase( "ExprOneMan", "/api/expression") {
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
         |  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = ???
         |}
         |
         |trait ${fileName}_$arity[${`A..V`}, t, Ns[${`_, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t, Ns]
         |    with Aggregates_$arity[${`A..V`}, t, Ns]
         |    with SortAttrs_$arity[${`A..V`}, t, Ns] {
         |  def apply(                ): Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprOneMan(Appl, Nil       )
         |  def apply(v    : t, vs: t*): Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprOneMan(Appl, v +: vs   )
         |  def apply(vs   : Seq[t]   ): Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprOneMan(Appl, vs        )
         |  def not  (v    : t, vs: t*): Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprOneMan(Not , v +: vs   )
         |  def not  (vs   : Seq[t]   ): Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprOneMan(Not , vs        )
         |  def <    (upper: t        ): Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprOneMan(Lt  , Seq(upper))
         |  def <=   (upper: t        ): Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprOneMan(Le  , Seq(upper))
         |  def >    (lower: t        ): Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprOneMan(Gt  , Seq(lower))
         |  def >=   (lower: t        ): Ns[${`A..V`}, t] with SortAttrs_$arity[${`A..V`}, t, Ns] = _exprOneMan(Ge  , Seq(lower))
         |}""".stripMargin
  }
}
