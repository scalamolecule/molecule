package molecule.graphql.test.api.dsl.StarWars.input

import molecule.base.metaModel.{CardOne, MetaAttribute, MetaEntity, MetaSegment}

/** hej
 *
 * @param stars      bla bla
 * @param commentary bla bla
 */
case class InputReview(stars: Int, commentary: Option[String] = None) {

  MetaSegment(
    "input",
    List(
      MetaEntity(
        "InputReview",
        List(
          MetaAttribute("stars", CardOne, "Int", options = List("mandatory")),
          MetaAttribute("commentary", CardOne, "String")
        )
      )
    )
  )
}


