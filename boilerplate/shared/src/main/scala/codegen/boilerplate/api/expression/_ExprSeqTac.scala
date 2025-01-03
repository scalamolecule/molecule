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
       |import molecule.boilerplate.ast.DataModel._
       |import scala.language.higherKinds
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val attrExprs = if (arity == 22) {
      s"""
         |  def has  [ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[${`A..V`}, t] = _attrTac(Has  , a)
         |  def hasNo[ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[${`A..V`}, t] = _attrTac(HasNo, a)""".stripMargin
    } else {
      s"""
         |  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne)(implicit x: X): Ns1[${`A..V, `}t] = _attrTac(Has  , a)
         |  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne)(implicit x: X): Ns1[${`A..V, `}t] = _attrTac(HasNo, a)
         |
         |  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] with CardOne): Ns2[${`A..V, `}X, t] = _attrMan(Has  , a)
         |  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] with CardOne): Ns2[${`A..V, `}X, t] = _attrMan(HasNo, a)""".stripMargin
    }

    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V, `}t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends ExprAttr_$arity[${`A..V, `}t, Ns1, Ns2] {
         |  protected def _exprSeq(op: Op, seq: Seq[t]): Ns1[${`A..V, `}t] = ???
         |}
         |
         |trait $fileName_$arity[${`A..V, `}t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V, `}t, Ns1, Ns2] {
         |  def apply(                ): Ns1[${`A..V, `}t] = _exprSeq(NoValue, Seq.empty[t])
         |  def apply(seq: Seq[t]     ): Ns1[${`A..V, `}t] = _exprSeq(Eq     , seq         )
         |  def has  (v  : t, vs: t*  ): Ns1[${`A..V, `}t] = _exprSeq(Has    , Seq(v) ++ vs)
         |  def has  (vs : Iterable[t]): Ns1[${`A..V, `}t] = _exprSeq(Has    , Seq()  ++ vs)
         |  def hasNo(v  : t, vs: t*  ): Ns1[${`A..V, `}t] = _exprSeq(HasNo  , Seq(v) ++ vs)
         |  def hasNo(vs : Iterable[t]): Ns1[${`A..V, `}t] = _exprSeq(HasNo  , Seq()  ++ vs)
         |  $attrExprs
         |}""".stripMargin
  }
}
