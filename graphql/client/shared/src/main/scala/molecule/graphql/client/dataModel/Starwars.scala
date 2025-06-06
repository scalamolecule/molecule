package molecule.graphql.client.dataModel

import molecule.DomainStructure

object Starwars extends DomainStructure(6) {

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