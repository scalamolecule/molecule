package codegen.boilerplate.api

import codegen.BoilerplateGenBase

object _ModelOps extends BoilerplateGenBase("ModelOps", "/api") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.api
       |
       |import molecule.boilerplate.api.expression._
       |import molecule.boilerplate.ops.ModelTransformations_
       |
       |
       |trait ${fileName}_0[t, Ns[_]]
       |  extends Molecule_00
       |    with ModelTransformations_
       |    with ExprOneTacOps_0[t, Ns]
       |    with ExprSetTacOps_0[t, Ns]
       |    with CompositeInit_0
       |    with NestedOp_0
       |    with Tx_0_
       |$traits""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val nested = if (arity == 22) "" else s"\n    with NestedOp_$arity[${`A..V`}]"
    val body   =
      s"""
         |trait $fileName_$arity[${`A..V`}, t, Ns[${`_, _`}]]
         |  extends Molecule_$n0[${`A..V`}]
         |    with ModelTransformations_
         |    with AggregatesOps_$arity[${`A..V`}, t, Ns]
         |    with ExprOneManOps_$arity[${`A..V`}, t, Ns]
         |    with ExprOneOptOps_$arity[${`A..V`}, t, Ns]
         |    with ExprOneTacOps_$arity[${`A..V`}, t, Ns]
         |    with ExprSetManOps_$arity[${`A..V`}, t, Ns]
         |    with ExprSetOptOps_$arity[${`A..V`}, t, Ns]
         |    with ExprSetTacOps_$arity[${`A..V`}, t, Ns]
         |    with SortAttrsOps_$arity[${`A..V`}, t, Ns]
         |    with CompositeInit_$arity[${`A..V`}]$nested
         |    with Tx_${arity}_[${`A..V`}]""".stripMargin
  }
}
