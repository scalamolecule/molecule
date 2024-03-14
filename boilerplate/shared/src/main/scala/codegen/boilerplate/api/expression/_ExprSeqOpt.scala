package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase


object _ExprSeqOpt extends BoilerplateGenBase( "ExprSeqOpt", "/api/expression") {
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
         |  protected def _exprSeqOpt(op: Op, optSeqs: Option[Seq[Seq[t]]]): Ns1[${`A..V`}, t] = ???
         |}
         |
         |trait $fileName_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t, Ns1, Ns2]{
         |  def apply(seq : Option[Seq[t]]     )               : Ns1[${`A..V`}, t] = _exprSeqOpt(Eq   , seq.map(seq => Seq(seq))  )
         |  def apply(seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[${`A..V`}, t] = _exprSeqOpt(Eq   , seqs                      )
         |  def not  (seq : Option[Seq[t]]     )               : Ns1[${`A..V`}, t] = _exprSeqOpt(Neq  , seq.map(seq => Seq(seq))  )
         |  def not  (seqs: Option[Seq[Seq[t]]])(implicit x: X): Ns1[${`A..V`}, t] = _exprSeqOpt(Neq  , seqs                      )
         |  def has  (v   : Option[t]          )               : Ns1[${`A..V`}, t] = _exprSeqOpt(Has  , v.map(v => Seq(Seq(v)))   )
         |  def has  (vs  : Option[Seq[t]]     )(implicit x: X): Ns1[${`A..V`}, t] = _exprSeqOpt(Has  , vs.map(_.map(v => Seq(v))))
         |  def hasNo(v   : Option[t]          )               : Ns1[${`A..V`}, t] = _exprSeqOpt(HasNo, v.map(v => Seq(Seq(v)))   )
         |  def hasNo(vs  : Option[Seq[t]]     )(implicit x: X): Ns1[${`A..V`}, t] = _exprSeqOpt(HasNo, vs.map(seq => Seq(seq))   )
         |}""".stripMargin
  }
}
