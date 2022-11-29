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
       |trait ${fileName}_0[t, Ns[_]]
       |  extends Molecule_00
       |    with ModelTransformations
       |    with ExprOneTacOps_0[t, Ns]
       |    with ExprSetTacOps_0[t, Ns]
       |    with NestedOp_0
       |    with CompositeInit_0
       |$traits""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val nested = if (arity == 22) "" else s"\n    with NestedOp_$arity[${`A..V`}]"
    val body   =
      s"""
         |trait ${fileName}_$arity[${`A..V`}, t, Ns[${`_, _`}]]
         |  extends Molecule_$n0[${`A..V`}]
         |    with ModelTransformations
         |    with AggregatesOps_$arity[${`A..V`}, t, Ns]
         |    with ExprOneManOps_$arity[${`A..V`}, t, Ns]
         |    with ExprOneOptOps_$arity[${`A..V`}, t, Ns]
         |    with ExprOneTacOps_$arity[${`A..V`}, t, Ns]
         |    with ExprSetManOps_$arity[${`A..V`}, t, Ns]
         |    with ExprSetOptOps_$arity[${`A..V`}, t, Ns]
         |    with ExprSetTacOps_$arity[${`A..V`}, t, Ns]$nested
         |    with CompositeInit_$arity[${`A..V`}]
         |    with SortAttrsOps_$arity[${`A..V`}, t, Ns]""".stripMargin
  }
}
