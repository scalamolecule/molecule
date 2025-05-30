package molecule.graphql.client

import caliban.*
import zio.Scope
import zio.test.*
import zio.test.TestAspect.sequential

object StarwarsIntrospection extends StarwarsTest {

  override def spec: Spec[TestEnvironment & Scope, CalibanError.ValidationError] =
    suite("Starwars introspection")(

      test("hero (arg is None)") {
        graphql(
          """{
            |  __schema {
            |    types {
            |      name
            |    }
            |  }
            |}""".stripMargin,
          """{
            |  "__schema": {
            |    "types": [
            |      {
            |        "name": "Boolean"
            |      },
            |      {
            |        "name": "Character"
            |      },
            |      {
            |        "name": "Droid"
            |      },
            |      {
            |        "name": "Episode"
            |      },
            |      {
            |        "name": "Float"
            |      },
            |      {
            |        "name": "Human"
            |      },
            |      {
            |        "name": "Int"
            |      },
            |      {
            |        "name": "Query"
            |      },
            |      {
            |        "name": "String"
            |      },
            |      {
            |        "name": "__Directive"
            |      },
            |      {
            |        "name": "__DirectiveLocation"
            |      },
            |      {
            |        "name": "__EnumValue"
            |      },
            |      {
            |        "name": "__Field"
            |      },
            |      {
            |        "name": "__InputValue"
            |      },
            |      {
            |        "name": "__Schema"
            |      },
            |      {
            |        "name": "__Type"
            |      },
            |      {
            |        "name": "__TypeKind"
            |      }
            |    ]
            |  }
            |}""".stripMargin
        )
        //        __schema.types.query.get.map(_ ==> List(
        //          "Human",
        //            "Character",
        //            "String",
        //            "Episode",
        //            "Droid",
        //            "Query",
        //            "Boolean",
        //            "__Schema",
        //            "__Type",
        //            "__TypeKind",
        //            "__Field",
        //            "__InputValue",
        //            "__EnumValue",
        //            "__Directive",
        //            "__DirectiveLocation",
        //        ))
      },


    ) @@ sequential
}
