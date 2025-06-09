package molecule.graphql.server

import caliban.*
import caliban.schema.Annotations.GQLInterface
import caliban.schema.Schema.auto.genAll
import caliban.schema.{ArgBuilder, Schema}

trait StarWarsServer {

  // Schema -----------------------------------------

  enum Episode:
    case NEWHOPE, EMPIRE, JEDI

  @GQLInterface
  sealed trait Character {
    def id: String
    def name: String
    def friends: List[Character]
    def appearsIn: List[Episode]
  }

  case class Human(
    id: String,
    name: String,
    friends: List[Character],
    appearsIn: List[Episode],
    homePlanet: Option[String])
    extends Character

  case class Droid(
    id: String,
    name: String,
    friends: List[Character],
    appearsIn: List[Episode],
    primaryFunction: Option[String])
    extends Character

  case class Query(
    hero: HeroArg => Character,
    character: CharacterArg => Option[Character],
    human: HumanArg => Human,
    droid: DroidArg => Droid,
  )

  // Data -----------------------------------------

  import Episode.*

  // Step 1: Create stub characters with empty friends
  val luke    = Human("1000", "Luke Skywalker", Nil, List(NEWHOPE, EMPIRE, JEDI), Some("Tatooine"))
  val darth   = Human("1001", "Darth Vader", Nil, List(NEWHOPE, EMPIRE, JEDI), Some("Tatooine"))
  val han     = Human("1002", "Han Solo", Nil, List(NEWHOPE, EMPIRE, JEDI), None)
  val leia    = Human("1003", "Leia Organa", Nil, List(NEWHOPE, EMPIRE, JEDI), Some("Alderaan"))
  val wilhuff = Human("1004", "Wilhuff Tarkin", Nil, List(NEWHOPE, EMPIRE, JEDI), None)
  val c3po    = Droid("2000", "C-3PO", Nil, List(NEWHOPE, EMPIRE, JEDI), Some("Protocol"))
  val r2d2    = Droid("2001", "R2-D2", Nil, List(NEWHOPE, EMPIRE, JEDI), Some("Astromech"))

  // Step 2: Wire up friendships
  val luke2    = luke.copy(friends = List(han, leia, c3po, r2d2))
  val darth2   = darth.copy(friends = List(wilhuff))
  val han2     = han.copy(friends = List(luke2, leia, r2d2))
  val leia2    = leia.copy(friends = List(luke2, han2, c3po, r2d2))
  val wilhuff2 = wilhuff.copy(friends = List(darth2))
  val c3po2    = c3po.copy(friends = List(luke2, han2, leia2, r2d2))
  val r2d2_2   = r2d2.copy(friends = List(luke2, han2, leia2))

  val humans                      = List(luke2, darth2, han2, leia2, wilhuff2)
  val droids                      = List(c3po2, r2d2_2)
  val characters: List[Character] = humans ++ droids

  // Resolve -----------------------------------------

  case class HeroArg(episode: Option[Episode])derives Schema.SemiAuto, ArgBuilder
  case class CharacterArg(id: String)derives Schema.SemiAuto, ArgBuilder
  case class HumanArg(id: String)derives Schema.SemiAuto, ArgBuilder
  case class DroidArg(id: String)derives Schema.SemiAuto, ArgBuilder

  given ArgBuilder[Episode] = ArgBuilder.gen
  given Schema[Any, Episode] = Schema.gen
  given Schema[Any, Human] = genAll
  given Schema[Any, Droid] = genAll
  given Schema[Any, Character] = genAll

  val starwarsResolver = RootResolver(
    Query(
      args => if (args.episode.contains(EMPIRE)) humans.head else droids.last,
      args => characters.find(c => args.id.contains(c.id)),
      args => humans.find(_.id == args.id).get,
      args => droids.find(c => args.id.contains(c.id)).get,
    )
  )
}
