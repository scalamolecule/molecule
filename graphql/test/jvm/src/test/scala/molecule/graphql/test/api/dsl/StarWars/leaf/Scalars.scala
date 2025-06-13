package molecule.graphql.test.api.dsl.StarWars.leaf

import molecule.base.metaModel.{MetaEntity, MetaSegment}

object Scalars {

  type Date = java.util.Date

  type ReviewId = String

  MetaSegment(
    "scalars",
    List(
      MetaEntity("Date", Nil, mandatoryAttrs = List("java.util.Date")),
      MetaEntity("ReviewId", Nil, mandatoryAttrs = List("String"))
    )
  )
}
