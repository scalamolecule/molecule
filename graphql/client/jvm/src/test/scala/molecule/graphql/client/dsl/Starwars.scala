package molecule.graphql.client.dsl

import molecule.db.core.api.Keywords.qm
import molecule.graphql.client.dsl.starwars.*

object Starwars {
  enum Episode:
    case NEWHOPE, EMPIRE, JEDI

  def apply(x: Any*) = ???

  def hero(id: String): Character_1[Set[String], String] = Character.appearsIn(Set(id))
  def hero: Character_0[Nothing] = ???

  def character(id: String): Character_0[String] = Character.apply(id)

  def human(id: String*): Human_0[String] = Human(id)
  def human(id: qm): Human_0[String] = ???

  def droid(id: String): Droid_0[String] = Droid(id)

}
