package molecule.graphql.test.api.dsl.StarWars.leaf

import molecule.base.metaModel.*

object Enums {

  enum Episode:
    case NEWHOPE, EMPIRE, JEDI


  MetaSegment(
    "enums",
    List(
      MetaEntity("Episode", Nil, mandatoryAttrs = List("NEWHOPE", "EMPIRE", "JEDI"))
    )
  )
}
