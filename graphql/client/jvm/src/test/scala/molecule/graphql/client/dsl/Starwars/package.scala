package molecule.graphql.client.dsl.Starwars

import scala.annotation.targetName

object Starwars {

  enum Episode:
    case NEWHOPE, EMPIRE, JEDI


  def apply(x: Any*) = ???

  def hero(episode: String): Character_1[Set[String], String] = Character.appearsIn(Set(episode))
  def hero: Character_0[Nothing] = ???

  def character(id: String): Character_0[String] = Character.apply(id)
  def human(id: String*): Human_0[String] = Human(id)
  def droid(id: String): Droid_0[String] = Droid(id)

  lazy val __schema = {
//    lazy val name
  }

  trait InputPlaceholder

  @targetName("InputPlaceholder")
  object ? extends InputPlaceholder


  trait _n
  object _n extends _n

  object _1 extends _n
  object _2

  def input(i: _1.type) = 1
  def input(i: _2.type) = 2

//  def input(i: ?.type) = 3

  input(_1)
  input(_2)


}
