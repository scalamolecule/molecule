package molecule.boilerplate.generators.expression

import molecule.boilerplate.generators._Template

object GenExprMapOpt extends _Template( "exprMapO", "expression") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""package molecule.boilerplate.api.expression
       |
       |import molecule.boilerplate.api.sortAttrs._
       |
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |trait ${Name}_$arity[Attr, t, ${`A..N`}, $nsIn]
         |  extends SortAttrs_$arity[Attr, t, ${`A..N`}, Ns] {
         |  def apply(some: Option[Map[String, t]]): $nsOut = ???
         |}""".stripMargin
  }
}
