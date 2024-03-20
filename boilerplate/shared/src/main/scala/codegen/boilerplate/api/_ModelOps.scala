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
       |trait ${fileName}_0[t, Ns1[_], Ns2[_, _]]
       |  extends Molecule_00
       |    with ModelTransformations_
       |    with ExprBArTacOps_0[t, Ns1, Ns2]
       |    with ExprOneTacOps_0[t, Ns1, Ns2]
       |    with ExprSetTacOps_0[t, Ns1, Ns2]
       |    with ExprSeqTacOps_0[t, Ns1, Ns2]
       |    with ExprMapTacOps_0[t, Ns1, Ns2]
       |$traits""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body   =
      s"""
         |trait $fileName_$arity[${`A..V`}, t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends Molecule_$n0[${`A..V`}]
         |    with ModelTransformations_
         |    with AggregatesOps_$arity[${`A..V`}, t, Ns1]
         |    with ExprBArTacOps_$arity[${`A..V`}, t, Ns1, Ns2]
         |    with ExprBArOptOps_$arity[${`A..V`}, t, Ns1, Ns2]
         |    with ExprOneManOps_$arity[${`A..V`}, t, Ns1, Ns2]
         |    with ExprOneTacOps_$arity[${`A..V`}, t, Ns1, Ns2]
         |    with ExprOneOptOps_$arity[${`A..V`}, t, Ns1, Ns2]
         |    with ExprSetTacOps_$arity[${`A..V`}, t, Ns1, Ns2]
         |    with ExprSetOptOps_$arity[${`A..V`}, t, Ns1, Ns2]
         |    with ExprSeqTacOps_$arity[${`A..V`}, t, Ns1, Ns2]
         |    with ExprSeqOptOps_$arity[${`A..V`}, t, Ns1, Ns2]
         |    with ExprMapTacOps_$arity[${`A..V`}, t, Ns1, Ns2]
         |    with ExprMapOptOps_$arity[${`A..V`}, t, Ns1, Ns2]
         |    with SortAttrsOps_$arity[${`A..V`}, t, Ns1]""".stripMargin
  }
}
