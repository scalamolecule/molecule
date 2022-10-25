package codegen.boilerplate.api

import codegen.BoilerplateGenBase

object _ModelOps extends BoilerplateGenBase("ModelOps", "/api") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.api
       |
       |import molecule.boilerplate.api.expression._
       |import molecule.boilerplate.ops.ModelTransformations
       |
       |
       |trait ModelOps_0[t, Ns[_]]
       |  extends ExprOneTacOps_0[t, Ns]
       |    with ModelTransformations
       |$traits""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |trait ${fileName}_$arity[${`A..V`}, t, Ns[${`_, _`}]]
         |  extends AggregatesOps_$arity[${`A..V`}, t, Ns]
         |    with ExprOneManOps_$arity[${`A..V`}, t, Ns]
         |    with ExprOneOptOps_$arity[${`A..V`}, t, Ns]
         |    with ExprOneTacOps_$arity[${`A..V`}, t, Ns]
         |    with SortAttrsOps_$arity[${`A..V`}, t, Ns]
         |    with ModelTransformations""".stripMargin
  }
}
