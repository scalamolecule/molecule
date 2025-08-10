package molecule.graphql.test.api.dsl.StarWars.input

import molecule.core.dataModel.*

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
          MetaAttribute("stars", OneValue, "Int", options = List("mandatory")),
          MetaAttribute("commentary", OneValue, "String")
        )
      )
    )
  )
}


