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
       |trait ${fileName}_0[t, Ns0[_], Ns1[_, _]]
       |  extends Molecule_00
       |    with ModelTransformations
       |    with ExprOneTacOps_0[t, Ns0]
       |    with ExprSetTacOps_0[t, Ns0]
       |    with Nested_0[Ns1]
       |$traits""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val ns1    = if (arity == 22) "" else s", Ns1[${`_, _, _`}]"
    val nested = if (arity == 22) "" else s"\n    with Nested_$arity[${`A..V`}, Ns1]"
    val body   =
      s"""
         |trait ${fileName}_$arity[${`A..V`}, t, Ns0[${`_, _`}]$ns1]
         |  extends Molecule_$n0[${`A..V`}]
         |    with ModelTransformations
         |    with AggregatesOps_$arity[${`A..V`}, t, Ns0]
         |    with ExprOneManOps_$arity[${`A..V`}, t, Ns0]
         |    with ExprOneOptOps_$arity[${`A..V`}, t, Ns0]
         |    with ExprOneTacOps_$arity[${`A..V`}, t, Ns0]
         |    with ExprSetManOps_$arity[${`A..V`}, t, Ns0]
         |    with ExprSetOptOps_$arity[${`A..V`}, t, Ns0]
         |    with ExprSetTacOps_$arity[${`A..V`}, t, Ns0]$nested
         |    with SortAttrsOps_$arity[${`A..V`}, t, Ns0]""".stripMargin
  }
}
