package molecule.graphql.test.dsl.Starwars.input

import molecule.graphql.client

/** hej
 *
 * @param stars      bla bla
 * @param commentary bla bla
 */
case class InputReview(stars: Int, commentary: Option[String] = None)
