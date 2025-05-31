package boilerplate.db.core.api.expression

import boilerplate.db.core.CoreGenBase


object _ExprSeqMan extends CoreGenBase("ExprSeqMan", "/api/expression") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.core.api.expression
       |
       |import molecule.db.base.ast.*
       |import molecule.db.core.api.*
       |import molecule.db.core.api.Keywords.qm
       |import molecule.db.core.ast._
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val attrExprs = if (arity == 22) {
      s"""
         |  def has  [ns1[_]](a: ModelOps_0[t, ns1, X2]): Entity1[${`A..V`}, t] = _attrTac(Has  , a)
         |  def hasNo[ns1[_]](a: ModelOps_0[t, ns1, X2]): Entity1[${`A..V`}, t] = _attrTac(HasNo, a)""".stripMargin
    } else {
      s"""
         |  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[${`A..V`}, t] = _attrTac(Has  , a)
         |  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] & CardOne)(implicit x: X): Entity1[${`A..V`}, t] = _attrTac(HasNo, a)
         |
         |  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[${`A..V`}, X, t] = _attrMan(Has  , a)
         |  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] & CardOne): Entity2[${`A..V`}, X, t] = _attrMan(HasNo, a)""".stripMargin
    }

    val body =
      s"""
         |
         |trait $fileName_$arity[${`A..V`}, t, Entity1[${`_, _`}], Entity2[${`_, _, _`}]]
         |  extends ExprSeqTacOps_$arity[${`A..V`}, t, Entity1, Entity2] {
         |
         |  def apply (                 ): Entity1[${`A..V`}, t] = _exprSeq(NoValue, Seq.empty[t])
         |  def apply (seq : Seq[t]     ): Entity1[${`A..V`}, t] = _exprSeq(Eq     , seq         )
         |  def has   (v   : t, vs: t*  ): Entity1[${`A..V`}, t] = _exprSeq(Has    , Seq(v) ++ vs)
         |  def has   (vs  : Iterable[t]): Entity1[${`A..V`}, t] = _exprSeq(Has    , Seq()  ++ vs)
         |  def hasNo (v   : t, vs: t*  ): Entity1[${`A..V`}, t] = _exprSeq(HasNo  , Seq(v) ++ vs)
         |  def hasNo (vs  : Iterable[t]): Entity1[${`A..V`}, t] = _exprSeq(HasNo  , Seq()  ++ vs)
         |  def add   (v   : t, vs: t*  ): Entity1[${`A..V`}, t] = _exprSeq(Add    , Seq(v) ++ vs)
         |  def add   (vs  : Iterable[t]): Entity1[${`A..V`}, t] = _exprSeq(Add    , Seq()  ++ vs)
         |  def remove(v   : t, vs: t*  ): Entity1[${`A..V`}, t] = _exprSeq(Remove , Seq(v) ++ vs)
         |  def remove(vs  : Iterable[t]): Entity1[${`A..V`}, t] = _exprSeq(Remove , Seq()  ++ vs)
         |
         |  def has   (v: qm): Entity1[${`A..V`}, t] = _exprSeq(Has  , Nil, true)
         |  def hasNo (v: qm): Entity1[${`A..V`}, t] = _exprSeq(HasNo, Nil, true)
         |  $attrExprs
         |}""".stripMargin
  }
}
