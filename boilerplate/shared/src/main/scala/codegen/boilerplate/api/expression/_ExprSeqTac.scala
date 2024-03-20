package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase


object _ExprSeqTac extends BoilerplateGenBase("ExprSeqTac", "/api/expression") {
  val content = {
    val traits = (0 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.api.expression
       |
       |import molecule.base.ast._
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
         |  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[${`A..V, `}t] = _attrTac(Eq   , a)
         |  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSeq)(implicit x: X): Ns1[${`A..V, `}t] = _attrTac(Neq  , a)
         |  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[${`A..V, `}t] = _attrTac(Has  , a)
         |  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[${`A..V, `}t] = _attrTac(HasNo, a)
         |
         |  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[${`A..V, `}Seq[t], t] = _attrMan(Eq   , a)
         |  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Seq[t], t, ns1, ns2] with CardSeq): Ns2[${`A..V, `}Seq[t], t] = _attrMan(Neq  , a)
         |  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[${`A..V, `}X     , t] = _attrMan(Has  , a)
         |  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[${`A..V, `}X     , t] = _attrMan(HasNo, a)""".stripMargin
    }

    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V, `}t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends ExprAttr_$arity[${`A..V, `}t, Ns1, Ns2] {
         |  protected def _exprSeq(op: Op, seqs: Seq[Seq[t]]): Ns1[${`A..V, `}t] = ???
         |}
         |
         |trait $fileName_$arity[${`A..V, `}t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V, `}t, Ns1, Ns2] {
         |  def apply (                           ): Ns1[${`A..V, `}t] = _exprSeq(NoValue, Nil                       )
         |  def apply (seq : Seq[t], seqs: Seq[t]*): Ns1[${`A..V, `}t] = _exprSeq(Eq     , seq +: seqs               )
         |  def apply (seqs: Seq[Seq[t]]          ): Ns1[${`A..V, `}t] = _exprSeq(Eq     , seqs                      )
         |  def not   (seq : Seq[t], seqs: Seq[t]*): Ns1[${`A..V, `}t] = _exprSeq(Neq    , seq +: seqs               )
         |  def not   (seqs: Seq[Seq[t]]          ): Ns1[${`A..V, `}t] = _exprSeq(Neq    , seqs                      )
         |  def has   (v   : t, vs: t*            ): Ns1[${`A..V, `}t] = _exprSeq(Has    , (v +: vs).map(v => Seq(v)))
         |  def has   (seq : Seq[t]               ): Ns1[${`A..V, `}t] = _exprSeq(Has    , Seq(seq)                  )
         |  def hasNo (v   : t, vs: t*            ): Ns1[${`A..V, `}t] = _exprSeq(HasNo  , (v +: vs).map(v => Seq(v)))
         |  def hasNo (seq : Seq[t]               ): Ns1[${`A..V, `}t] = _exprSeq(HasNo  , Seq(seq)                  )
         |  $attrExprs
         |}""".stripMargin
  }
}
