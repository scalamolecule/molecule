package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase


object _ExprSetTac extends BoilerplateGenBase("ExprSetTac", "/api/expression") {
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
         |  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[${`A..V, `}t] = _attrTac(Eq   , a)
         |  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardSet)(implicit x: X): Ns1[${`A..V, `}t] = _attrTac(Neq  , a)
         |  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[${`A..V, `}t] = _attrTac(Has  , a)
         |  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[${`A..V, `}t] = _attrTac(HasNo, a)
         |
         |  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[${`A..V, `}Set[t], t] = _attrMan(Eq   , a)
         |  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Set[t], t, ns1, ns2] with CardSet): Ns2[${`A..V, `}Set[t], t] = _attrMan(Neq  , a)
         |  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[${`A..V, `}X     , t] = _attrMan(Has  , a)
         |  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X     , t, ns1, ns2] with Card   ): Ns2[${`A..V, `}X     , t] = _attrMan(HasNo, a)""".stripMargin
    }
    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V, `}t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends ExprAttr_$arity[${`A..V, `}t, Ns1, Ns2] {
         |  protected def _exprSet(op: Op, sets: Seq[Set[t]]): Ns1[${`A..V, `}t] = ???
         |}
         |
         |trait $fileName_$arity[${`A..V, `}t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V, `}t, Ns1, Ns2] {
         |  def apply (                            ): Ns1[${`A..V, `}t] = _exprSet(NoValue, Nil                       )
         |  def apply (set  : Set[t], sets: Set[t]*): Ns1[${`A..V, `}t] = _exprSet(Eq     , set +: sets               )
         |  def apply (sets : Seq[Set[t]]          ): Ns1[${`A..V, `}t] = _exprSet(Eq     , sets                      )
         |  def not   (set  : Set[t], sets: Set[t]*): Ns1[${`A..V, `}t] = _exprSet(Neq    , set +: sets               )
         |  def not   (sets : Seq[Set[t]]          ): Ns1[${`A..V, `}t] = _exprSet(Neq    , sets                      )
         |  def has   (v    : t, vs: t*            ): Ns1[${`A..V, `}t] = _exprSet(Has    , (v +: vs).map(v => Set(v)))
         |  def has   (vs   : Seq[t]               ): Ns1[${`A..V, `}t] = _exprSet(Has    , vs.map(v => Set(v))       )
         |  def hasNo (v    : t, vs: t*            ): Ns1[${`A..V, `}t] = _exprSet(HasNo  , (v +: vs).map(v => Set(v)))
         |  def hasNo (vs   : Seq[t]               ): Ns1[${`A..V, `}t] = _exprSet(HasNo  , vs.map(v => Set(v))       )
         |  def add   (v    : t, vs: t*            ): Ns1[${`A..V, `}t] = _exprSet(Add    , Seq((v +: vs).toSet)      )
         |  def add   (vs   : Seq[t]               ): Ns1[${`A..V, `}t] = _exprSet(Add    , Seq(vs.toSet)             )
         |  def remove(v    : t, vs: t*            ): Ns1[${`A..V, `}t] = _exprSet(Remove , Seq((v +: vs).toSet)      )
         |  def remove(vs   : Seq[t]               ): Ns1[${`A..V, `}t] = _exprSet(Remove , Seq(vs.toSet)             )
         |  $attrExprs
         |}""".stripMargin
  }
}
