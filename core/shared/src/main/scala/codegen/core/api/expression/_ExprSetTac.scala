package codegen.core.api.expression

import codegen.CoreGenBase


object _ExprSetTac extends CoreGenBase("ExprSetTac", "/api/expression") {
  val content = {
    val traits = (0 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.core.api.expression
       |
       |import molecule.base.ast._
       |import molecule.core.api._
       |import molecule.core.ast.DataModel._
       |import scala.language.higherKinds
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
         |  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne)(implicit x: X): Entity1[${`A..V, `}t] = _attrTac(Has  , a)
         |  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne)(implicit x: X): Entity1[${`A..V, `}t] = _attrTac(HasNo, a)
         |
         |  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] with CardOne): Entity2[${`A..V, `}X, t] = _attrMan(Has  , a)
         |  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X, t, ns1, ns2] with CardOne): Entity2[${`A..V, `}X, t] = _attrMan(HasNo, a)""".stripMargin
    }

    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V, `}t, Entity1[${`_, _`}], Entity2[${`_, _, _`}]] extends ExprAttr_$arity[${`A..V, `}t, Entity1, Entity2] {
         |  protected def _exprSet(op: Op, set: Set[t]): Entity1[${`A..V, `}t] = ???
         |}
         |
         |trait $fileName_$arity[${`A..V, `}t, Entity1[${`_, _`}], Entity2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V, `}t, Entity1, Entity2] {
         |  def apply(                ): Entity1[${`A..V, `}t] = _exprSet(NoValue, Set.empty[t]   )
         |  def apply(set: Set[t]     ): Entity1[${`A..V, `}t] = _exprSet(Eq     , set            )
         |  def has  (v  : t, vs: t*  ): Entity1[${`A..V, `}t] = _exprSet(Has    , Set(v) ++ vs   )
         |  def has  (vs : Iterable[t]): Entity1[${`A..V, `}t] = _exprSet(Has    , vs.toSet       )
         |  def hasNo(v  : t, vs: t*  ): Entity1[${`A..V, `}t] = _exprSet(HasNo  , Set(v) ++ vs   )
         |  def hasNo(vs : Iterable[t]): Entity1[${`A..V, `}t] = _exprSet(HasNo  , vs.toSet       )
         |  $attrExprs
         |}""".stripMargin
  }
}
