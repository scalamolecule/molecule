package molecule.graphql.client.dsl.Starwars

object Starwars {

  enum Episode:
    case NEWHOPE, EMPIRE, JEDI


  def apply(x: Any*) = ???

  def hero(episode: String): Character_1[Set[String], String] = Character.appearsIn(Set(episode))
  def hero: Character_0[Nothing] = ???

  def character(id: String): Character_0[String] = Character.apply(id)
  def human(id: String*): Human_0[String] = Human(id)
  def droid(id: String): Droid_0[String] = Droid(id)
}
