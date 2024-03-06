package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase


object _ExprMapMan extends BoilerplateGenBase("ExprMapMan", "/api/expression") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
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
         |  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[${`A..V`}, t] = _attrTac(Eq   , a)
         |  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[${`A..V`}, t] = _attrTac(Neq  , a)
         |  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[${`A..V`}, t] = _attrTac(Has  , a)
         |  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[${`A..V`}, t] = _attrTac(HasNo, a)
         |
         |  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[${`A..V`}, Map[String, t], t] = _attrMan(Eq   , a)
         |  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[${`A..V`}, Map[String, t], t] = _attrMan(Neq  , a)
         |  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[${`A..V`}, X             , t] = _attrMan(Has  , a)
         |  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[${`A..V`}, X             , t] = _attrMan(HasNo, a)""".stripMargin
    }

    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends ExprAttr_$arity[${`A..V, `}t, Ns1, Ns2] {
         |  protected def _exprMapMan(op: Op, maps: Seq[Map[String, t]]): Ns1[${`A..V`}, t] = ???
         |  protected def _exprMapMaK(op: Op, keys: Seq[String        ]): Ns1[${`A..V`}, t] = ???
         |  protected def _exprMapMaV(op: Op, vs  : Seq[t             ]): Ns1[${`A..V`}, t] = ???
         |}
         |
         |trait $fileName_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V`}, t, Ns1, Ns2]
         |    with Aggregates_$arity[${`A..V`}, t, Ns1] {
         |  def apply (                                           ): Ns1[${`A..V`}, t] = _exprMapMaK(Eq    , Nil                       )
         |  def apply (key  : String, keys: String*               ): Ns1[${`A..V`}, t] = _exprMapMaK(Eq    , key +: keys               )
         |  def apply (keys : Seq[String]                         ): Ns1[${`A..V`}, t] = _exprMapMaK(Eq    , keys                      )
         |  def not   (key  : String, keys: String*               ): Ns1[${`A..V`}, t] = _exprMapMaK(Neq   , key +: keys               )
         |  def not   (keys : Seq[String]                         ): Ns1[${`A..V`}, t] = _exprMapMaK(Neq   , keys                      )
         |  def has   (key  : String, keys: String*               ): Ns1[${`A..V`}, t] = _exprMapMaK(Has   , key +: keys               )
         |  def has   (keys : Seq[String]                         ): Ns1[${`A..V`}, t] = _exprMapMaK(Has   , keys                      )
         |  def hasNo (key  : String, keys: String*               ): Ns1[${`A..V`}, t] = _exprMapMaK(HasNo , key +: keys               )
         |  def hasNo (keys : Seq[String]                         ): Ns1[${`A..V`}, t] = _exprMapMaK(HasNo , keys                      )
         |  def getV  (v    : t, vs: t*                           ): Ns1[${`A..V`}, t] = _exprMapMaV(GetV  , v +: vs                   )
         |  def getV  (vs   : Seq[t]                              ): Ns1[${`A..V`}, t] = _exprMapMaV(GetV  , vs                        )
         |  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[${`A..V`}, t] = _exprMapMan(Add   , Seq((pair +: pairs).toMap))
         |  def add   (pairs: Iterable[(String, t)]               ): Ns1[${`A..V`}, t] = _exprMapMan(Add   , Seq(pairs.toMap)          )
         |  def remove(key  : String, keys: String*               ): Ns1[${`A..V`}, t] = _exprMapMaK(Remove, key +: keys               )
         |  def remove(keys : Seq[String]                         ): Ns1[${`A..V`}, t] = _exprMapMaK(Remove, keys                      )
         |  $attrExprs
         |}""".stripMargin
  }
}
