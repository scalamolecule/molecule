package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase


object _ExprSeqMan extends BoilerplateGenBase("ExprSeqMan", "/api/expression") {
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
    val attrExprs = if (arity == 22) {
      s"""
         |  def apply[ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[${`A..V`}, t] = _attrTac(Eq   , a)
         |  def not  [ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[${`A..V`}, t] = _attrTac(Neq  , a)
         |  def has  [ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[${`A..V`}, t] = _attrTac(Has  , a)
         |  def hasNo[ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[${`A..V`}, t] = _attrTac(HasNo, a)""".stripMargin
    } else {
      s"""
         |  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[${`A..V`}, t] = _attrTac(Eq   , a)
         |  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[${`A..V`}, t] = _attrTac(Neq  , a)
         |  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[${`A..V`}, t] = _attrTac(Has  , a)
         |  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[${`A..V`}, t] = _attrTac(HasNo, a)
         |
         |  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[${`A..V`}, Seq[t], t] = _attrMan(Eq   , a)
         |  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[${`A..V`}, Seq[t], t] = _attrMan(Neq  , a)
         |  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[${`A..V`}, X     , t] = _attrMan(Has  , a)
         |  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[${`A..V`}, X     , t] = _attrMan(HasNo, a)""".stripMargin
    }

    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends ExprAttr_$arity[${`A..V, `}t, Ns1, Ns2] {
         |  protected def _exprSeqMan(op: Op, seqs: Seq[Seq[t]]): Ns1[${`A..V`}, t] = ???
         |}
         |
         |trait $fileName_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t, Ns1, Ns2]
         |    with Aggregates_$arity[${`A..V`}, t, Ns1] {
         |  def apply (                           ): Ns1[${`A..V`}, t] = _exprSeqMan(Eq    , Nil                       )
         |  def apply (v   : t, vs: t*            ): Ns1[${`A..V`}, t] = _exprSeqMan(Eq    , (v +: vs).map(v => Seq(v)))
         |  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[${`A..V`}, t] = _exprSeqMan(Eq    , seq +: seqs               )
         |  def apply (seqs: Seq[Seq[t]]          ): Ns1[${`A..V`}, t] = _exprSeqMan(Eq    , seqs                      )
         |  def not   (v   : t, vs: t*            ): Ns1[${`A..V`}, t] = _exprSeqMan(Neq   , (v +: vs).map(v => Seq(v)))
         |  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[${`A..V`}, t] = _exprSeqMan(Neq   , seq +: seqs               )
         |  def not   (seqs: Seq[Seq[t]]          ): Ns1[${`A..V`}, t] = _exprSeqMan(Neq   , seqs                      )
         |  def has   (v   : t, vs: t*            ): Ns1[${`A..V`}, t] = _exprSeqMan(Has   , (v +: vs).map(v => Seq(v)))
         |  def has   (seq : Seq[t], seqs: Seq[t]*): Ns1[${`A..V`}, t] = _exprSeqMan(Has   , seq +: seqs               )
         |  def has   (seqs: Seq[Seq[t]]          ): Ns1[${`A..V`}, t] = _exprSeqMan(Has   , seqs                      )
         |  def hasNo (v   : t, vs: t*            ): Ns1[${`A..V`}, t] = _exprSeqMan(HasNo , (v +: vs).map(v => Seq(v)))
         |  def hasNo (seq : Seq[t], seqs: Seq[t]*): Ns1[${`A..V`}, t] = _exprSeqMan(HasNo , seq +: seqs               )
         |  def hasNo (seqs: Seq[Seq[t]]          ): Ns1[${`A..V`}, t] = _exprSeqMan(HasNo , seqs                      )
         |  def add   (v   : t, vs: t*            ): Ns1[${`A..V`}, t] = _exprSeqMan(Add   , Seq(v +: vs)              )
         |  def add   (vs  : Iterable[t]          ): Ns1[${`A..V`}, t] = _exprSeqMan(Add   , Seq(vs.toSeq)             )
         |  def remove(v   : t, vs: t*            ): Ns1[${`A..V`}, t] = _exprSeqMan(Remove, Seq(v +: vs)              )
         |  def remove(vs  : Iterable[t]          ): Ns1[${`A..V`}, t] = _exprSeqMan(Remove, Seq(vs.toSeq)             )
         |  $attrExprs
         |}""".stripMargin
  }
}
