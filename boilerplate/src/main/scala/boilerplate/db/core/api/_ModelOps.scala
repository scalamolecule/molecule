package boilerplate.db.core.api

import boilerplate.db.core.CoreGenBase

object _ModelOps extends CoreGenBase("ModelOps", "/api") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.core.api
       |
       |import molecule.db.core.api.expression.*
       |import molecule.db.core.ops.ModelTransformations_
       |
       |
       |trait ${fileName}_0[t, Entity1[_], Entity2[_, _]]
       |  extends Molecule_00
       |    with ModelTransformations_
       |    with ExprBArTacOps_0[t, Entity1, Entity2]
       |    with ExprOneTacOps_0[t, Entity1, Entity2]
       |    with ExprSetTacOps_0[t, Entity1, Entity2]
       |    with ExprSeqTacOps_0[t, Entity1, Entity2]
       |    with ExprMapTacOps_0[t, Entity1, Entity2]
       |$traits""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body   =
      s"""
         |trait $fileName_$arity[${`A..V`}, t, Entity1[${`_, _`}], Entity2[${`_, _, _`}]]
         |  extends Molecule_$n0[${`A..V`}]
         |    with ModelTransformations_
         |    with AggregatesOps_$arity[${`A..V`}, t, Entity1]
         |    with ExprBArTacOps_$arity[${`A..V`}, t, Entity1, Entity2]
         |    with ExprBArOptOps_$arity[${`A..V`}, t, Entity1, Entity2]
         |    with ExprOneManOps_$arity[${`A..V`}, t, Entity1, Entity2]
         |    with ExprOneTacOps_$arity[${`A..V`}, t, Entity1, Entity2]
         |    with ExprOneOptOps_$arity[${`A..V`}, t, Entity1, Entity2]
         |    with ExprSetTacOps_$arity[${`A..V`}, t, Entity1, Entity2]
         |    with ExprSetOptOps_$arity[${`A..V`}, t, Entity1, Entity2]
         |    with ExprSeqTacOps_$arity[${`A..V`}, t, Entity1, Entity2]
         |    with ExprSeqOptOps_$arity[${`A..V`}, t, Entity1, Entity2]
         |    with ExprMapTacOps_$arity[${`A..V`}, t, Entity1, Entity2]
         |    with ExprMapOptOps_$arity[${`A..V`}, t, Entity1, Entity2]
         |    with SortAttrsOps_$arity[${`A..V`}, t, Entity1]""".stripMargin
  }
}
