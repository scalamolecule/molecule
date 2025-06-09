package molecule.graphql.test.api.dsl.StarWars.input

/** hej
 *
 * @param stars      bla bla
 * @param commentary bla bla
 */
case class InputReview(stars: Int, commentary: Option[String] = None)
