package molecule.graphql.client

import caliban.*
import caliban.schema.Schema.auto.*
import molecule.graphql.server.StarwarsServer
import zio.ZIO
import zio.test.*

trait StarwarsTest extends ZIOSpecDefault with StarwarsServer {

  implicit class ArrowAssert(lhs: Any) {
    def ==>[V](rhs: V): TestResult = assertTrue(lhs == rhs)
  }

  def graphql(query: String, expected: String): ZIO[Any, CalibanError.ValidationError, TestResult] = {
    graphQL(starwarsResolver).interpreter.flatMap(_.execute(query)).map { response =>
      val result = ujson.write(ujson.read(response.data.toString), indent = 2)
      //      println(result)
      assertTrue(result == expected)
    }
  }
}
