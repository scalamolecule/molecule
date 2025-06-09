package boilerplate.db.core.api.expression

import boilerplate.db.core.DbCoreBase

object _ExprAttr extends DbCoreBase("ExprAttr", "/api/expression") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.core.api.expression
       |
       |import molecule.base.ast.CardOne
       |import molecule.core.ast.*
       |import molecule.db.core.api.*
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
         |  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] & CardOne): Entity1[${`A..V`},    t] & SortAttrs${a0}[${`A..V`},    t, Entity1] = ???
         |  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]          ): Entity1[${`A..V`},    t] = ???
         |}""".stripMargin
    } else {
      s"""
         |trait $fileName_$arity[${`A..V`}, t, Entity1[${`_, _`}], Entity2[${`_, _, _`}]] extends ExprBase {
         |  protected def _attrSortTac[   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2] & CardOne): Entity1[${`A..V`},    t] & SortAttrs${a0}[${`A..V`},    t, Entity1] = ???
         |  protected def _attrSortMan[   ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[$V, t, ns1, ns2]          ): Entity2[${`A..V`}, $V, t] & SortAttrs${a1}[${`A..V`}, $V, t, Entity2] = ???
         |  protected def _attrTac    [   ns1[_]   , ns2[_, _]   ](op: Op, a: ModelOps_0[   t, ns1, ns2]          ): Entity1[${`A..V`},    t] = ???
         |  protected def _attrMan    [X, ns1[_, _], ns2[_, _, _]](op: Op, a: ModelOps_1[X, t, ns1, ns2]          ): Entity2[${`A..V`}, X, t] = ???
         |}""".stripMargin
    }
  }
}
