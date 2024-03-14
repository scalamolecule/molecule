package molecule.graphql.client.dsl


package object Starwars {
  def hero(episode: String) = Character.appearsIn(Set(episode))

  def character(id: String) = Character(id)
  def character(id: Int) = Character(id)
  def character(id: Long) = Character(id)

  def human(id: String) = Human(id)
  def human(id: Int) = Human(id)
  def human(id: Long) = Human(id)

  def droid(id: String) = Droid(id)
  def droid(id: Int) = Droid(id)
  def droid(id: Long) = Droid(id)
}
