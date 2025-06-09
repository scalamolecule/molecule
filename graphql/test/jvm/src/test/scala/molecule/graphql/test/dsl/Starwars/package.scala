package molecule.graphql.test.dsl

import molecule.db.core.api.Keywords.qm
import molecule.db.core.ast.*
import molecule.graphql
import molecule.graphql.test.dsl.Starwars.Enums.Episode
import molecule.graphql.test.dsl.Starwars.input.*
import molecule.graphql.test.dsl.Starwars.output.*

package object Starwars {

//  enum Episode:
//    case NEWHOPE, EMPIRE, JEDI

  object query {
    def apply(queries: Any*) = ???

    def hero(id: String) = new Character_0[Nothing](DataModel(List(
      AttrOneTacString("Character", "id", Eq, Seq(id))
    )))

    def hero: Character_0[Nothing] = new Character_0[Nothing](DataModel(Nil))

    def character(id: String) = new Character_0[Nothing](DataModel(List(
      AttrOneTacString("Character", "id", Eq, Seq(id))
    )))

    def human(id: String) = new Human_0[Nothing](DataModel(List(
      AttrOneTacString("Human", "id", Eq, Seq(id))
    )))

    def human(id: qm) = new Human_0[Nothing](DataModel(List(
      AttrOneTacString("Human", "id", Eq)
    )))

    def droid(id: String) = new Droid_0[Nothing](DataModel(List(
      AttrOneTacString("Droid", "id", Eq)
    )))
  }

  case class Review(text: String)

  object mutation {
//    case class InputReview(stars: Int, commentary: Option[String] = None)

    def createReview(episode: Episode, review: InputReview): Review = ???


    def updateHumanName(id: String, name: String) = new Human_0[Nothing](DataModel(Nil, inputElements = List(
      AttrOneTacString("Human", "id", Eq, Seq(id)),
      AttrOneTacString("Human", "name", Eq, Seq(name))
    )))

    def deleteHuman(id: String) = new Human_0[Nothing](DataModel(Nil, inputElements = List(
      AttrOneTacString("Human", "id", Eq, Seq(id))
    )))
  }


  object subscription {
    def reviewCreated(): () => Review = ??? // ?
  }
}
