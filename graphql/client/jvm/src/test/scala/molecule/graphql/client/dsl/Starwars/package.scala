package molecule.graphql.client.dsl


package object Starwars {
  def hero(episode: String) = Character.appearsIn(Set(episode))
  def character(id: Long) = Character(id)
  def human(id: Long) = Human(id)
  def droid(id: Long) = Droid(id)
}
