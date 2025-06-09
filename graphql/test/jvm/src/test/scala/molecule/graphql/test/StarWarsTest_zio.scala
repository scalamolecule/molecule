package molecule.graphql.test

import caliban.*
import caliban.schema.Schema.auto.*
import molecule.graphql.server.StarWarsServer
import zio.ZIO
import zio.test.*

trait StarWarsTest_zio extends ZIOSpecDefault with StarWarsServer {

  implicit class ArrowAssert(lhs: Any) {
    def ==>[V](rhs: V): TestResult = assertTrue(lhs == rhs)
  }

  def graphql(
    query: String,
    expected: String,
    variables: Map[String, InputValue] = Map()
  ): ZIO[Any, CalibanError.ValidationError, TestResult] = {
    graphQL(starwarsResolver).interpreter
      .flatMap(_.execute(query, None, variables))
      .map { response =>
        val result = ujson.write(ujson.read(response.data.toString), indent = 2)
        //      println(result)
        assertTrue(result == expected)
      }
  }
}
