package molecule.boilerplate.generators

object GenModelOps extends _Template("modelOps") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""package molecule.boilerplate.api
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
         |trait ${Name}_$arity[${`A..N`}, t, Ns[${`_, _`}]]
         |  extends AggregatesOps_$arity[${`A..N`}, t, Ns]
         |    with ExprOneManOps_$arity[${`A..N`}, t, Ns]
         |    with ExprOneOptOps_$arity[${`A..N`}, t, Ns]
         |    with ExprOneTacOps_$arity[${`A..N`}, t, Ns]
         |    with SortAttrsOps_$arity[${`A..N`}, t, Ns]
         |    with ModelTransformations""".stripMargin
  }
}
