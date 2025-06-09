package boilerplate.graphql.client.api

import boilerplate.graphql.GraphqlBase

object _Molecules extends GraphqlBase( "Molecules", "/api") {

  val content = {
    val molecules = (1 to 22).map(arity => MoleculeFactories(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.graphql.client.api
       |
       |import molecule.base.error.ModelError
       |import molecule.core.ast.DataModel
       |import molecule.graphql.client.action.{Mutate, Query}
       |
       |trait Molecule {
       |  val dataModel: DataModel
       |
       |  protected def noBinding: Unit = {
       |    if (dataModel.binds != 0)
       |      throw ModelError(s"Mutations don't support input arguments.")
       |  }
       |}
       |
       |trait MoleculeBase extends Molecule {
       |  def mutate: Mutate = {
       |    noBinding
       |    Mutate(dataModel)
       |  }
       |}
       |
       |$molecules
       |""".stripMargin
  }

  case class MoleculeFactories(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""trait Molecule_$n0[${`A..V`}] extends MoleculeBase {
         |  def query: Query[${`(A..V)`}] =
         |    Query[${`(A..V)`}](dataModel)
         |}
         |""".stripMargin
  }
}
