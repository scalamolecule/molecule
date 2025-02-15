package codegen.core.api.expression

import codegen.CoreGenBase

object _ExprAttr extends CoreGenBase("ExprAttr", "/api/expression") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.core.api.expression
       |
       |import molecule.base.ast._
       |import molecule.core.api._
       |import molecule.core.ast.DataModel._
       |import scala.language.higherKinds
       |
       |
       |trait ExprAttr_0[t, Entity1[_], Entity2[_, _]] extends ExprBase {
       |  protected def _attrTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]): Entity1[   t] = ???
       |  protected def _attrMan[X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]): Entity2[X, t] = ???
       |}
       |$traits""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body   = if (arity == 22) {
      s"""
         |trait $fileName_$arity[${`A..V`}, t, Entity1[${`_, _`}], Entity2[${`_, _, _`}]] extends ExprBase {
         |  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne): Entity1[${`A..V`},    t] with SortAttrs${_0}[${`A..V`},    t, Entity1] = ???
         |  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ): Entity1[${`A..V`},    t] = ???
         |}""".stripMargin
    } else {
      s"""
         |trait $fileName_$arity[${`A..V`}, t, Entity1[${`_, _`}], Entity2[${`_, _, _`}]] extends ExprBase {
         |  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] with CardOne): Entity1[${`A..V`},    t] with SortAttrs${_0}[${`A..V`},    t, Entity1] = ???
         |  protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[$V, t, ns1, ns2]             ): Entity2[${`A..V`}, $V, t] with SortAttrs${_1}[${`A..V`}, $V, t, Entity2] = ???
         |  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]             ): Entity1[${`A..V`},    t] = ???
         |  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]             ): Entity2[${`A..V`}, X, t] = ???
         |}""".stripMargin
    }
  }
}
