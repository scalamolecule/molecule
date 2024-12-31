package molecule.graphql.client.dataModel

import molecule.Domain

object Starwars extends Domain(6) {

  trait Character {
    val name      = oneString
    val friends   = many[Character]
    val appearsIn = setString.enums("NEWHOPE", "EMPIRE", "JEDI")
  }

  trait Droid {
    val name            = oneString
    val friends         = many[Character]
    val appearsIn       = setString.enums("NEWHOPE", "EMPIRE", "JEDI")
    val primaryFunction = oneString
  }

  trait Human {
    val name       = oneString
    val friends    = many[Character]
    val appearsIn  = setString.enums("NEWHOPE", "EMPIRE", "JEDI")
    val homePlanet = oneString
  }
}