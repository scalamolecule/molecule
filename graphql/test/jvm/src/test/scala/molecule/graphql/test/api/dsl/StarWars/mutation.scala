package molecule.graphql.test.api.dsl.StarWars

import molecule.core.ast.{AttrOneTacString, DataModel, Eq}
import molecule.graphql.test.api.dsl.StarWars.input.InputReview
import molecule.graphql.test.api.dsl.StarWars.leaf.Enums.Episode
import molecule.graphql.test.api.dsl.StarWars.output.{Human_0, Review}

object mutation extends mutation

trait mutation {
  def createReview(episode: Episode, review: InputReview): Review = ???

  def updateHumanName(id: String, name: String) = new Human_0[Nothing](DataModel(Nil, inputElements = List(
    AttrOneTacString("Human", "id", Eq, Seq(id)),
    AttrOneTacString("Human", "name", Eq, Seq(name))
  )))

  def deleteHuman(id: String) = new Human_0[Nothing](DataModel(Nil, inputElements = List(
    AttrOneTacString("Human", "id", Eq, Seq(id))
  )))
}

