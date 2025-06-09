package molecule.graphql.test

import caliban.*
import caliban.Value.StringValue
import molecule.graphql.test.api.dsl.StarWars.hero
import molecule.graphql.client.Ziox.*
//import molecule.graphql.client.dataModel.Starwars
//import molecule.graphql.client.Ziox.*
//import molecule.graphql.client.dsl.Starwars.*
import zio.Scope
import zio.test.*
import zio.test.TestAspect.sequential

// Molecule translation of starwars query tests from javascript reference implementation
// https://github.com/graphql/graphql-js/blob/16.x.x/src/__tests__/starWarsQuery-test.ts

object StarWarsQuery extends StarWarsTest_zio {

  override def spec: Spec[TestEnvironment & Scope, CalibanError.ValidationError] =
    suite("Starwars Query Testsuite")(

      suite("Basic Queries")(

        test("Correctly identifies R2-D2 as the hero of the Star Wars Saga") {
          graphql(
            """{
              |  hero {
              |    name
              |  }
              |}""".stripMargin,
            """{
              |  "hero": {
              |    "name": "R2-D2"
              |  }
              |}""".stripMargin
          )

          //          hero.name.query.inspect.map(_ ==>
          //            """{
          //              |  hero {
          //              |    name
          //              |  }
          //              |}""".stripMargin
          //          )
//          hero.name.query.get.map(result => assertTrue(result.head == "R2-D2"))

          //          hero.name.query.get.map(_.head ==> "R2-D2")
          //          // or
          //          Starwars.hero.name.query.get.map(_.head ==> "R2-D2")
        },

        //        test("Attr") {
        //
        //          hero.name.query.get.map(_.head ==> "R2-D2")
        ////          hero.name.query.get.map(result => assertTrue(result.head == "R2-D2"))
        //
        ////          hero.name.query.get.map(_.head ==> "R2-D2")
        ////          // or
        ////          Starwars.hero.name.query.get.map(_.head ==> "R2-D2")
        //        },

        test("Allows us to query for the ID and friends of R2-D2") {
          graphql(
            """{
              |  hero {
              |    id
              |    name
              |    friends {
              |      name
              |    }
              |  }
              |}""".stripMargin,
            """{
              |  "hero": {
              |    "id": "2001",
              |    "name": "R2-D2",
              |    "friends": [
              |      {
              |        "name": "Luke Skywalker"
              |      },
              |      {
              |        "name": "Han Solo"
              |      },
              |      {
              |        "name": "Leia Organa"
              |      }
              |    ]
              |  }
              |}""".stripMargin
          )
          //        hero.id.name.Friends.*?(Ch.name).query.get.map(_.head ==>
          //          ("2001", "R2-D2", List("Luke Skywalker", "Han Solo", "Leia Organa"))
          //        )
        },
      ),


      suite("Nested Queries")(

        test("Allows us to query for the friends of friends of R2-D2") {
          graphql(
            """{
              |  hero {
              |    name
              |    friends {
              |      name
              |      appearsIn
              |      friends {
              |        name
              |      }
              |    }
              |  }
              |}""".stripMargin,
            """{
              |  "hero": {
              |    "name": "R2-D2",
              |    "friends": [
              |      {
              |        "name": "Luke Skywalker",
              |        "appearsIn": [
              |          "NEWHOPE",
              |          "EMPIRE",
              |          "JEDI"
              |        ],
              |        "friends": [
              |          {
              |            "name": "Han Solo"
              |          },
              |          {
              |            "name": "Leia Organa"
              |          },
              |          {
              |            "name": "C-3PO"
              |          },
              |          {
              |            "name": "R2-D2"
              |          }
              |        ]
              |      },
              |      {
              |        "name": "Han Solo",
              |        "appearsIn": [
              |          "NEWHOPE",
              |          "EMPIRE",
              |          "JEDI"
              |        ],
              |        "friends": [
              |          {
              |            "name": "Luke Skywalker"
              |          },
              |          {
              |            "name": "Leia Organa"
              |          },
              |          {
              |            "name": "R2-D2"
              |          }
              |        ]
              |      },
              |      {
              |        "name": "Leia Organa",
              |        "appearsIn": [
              |          "NEWHOPE",
              |          "EMPIRE",
              |          "JEDI"
              |        ],
              |        "friends": [
              |          {
              |            "name": "Luke Skywalker"
              |          },
              |          {
              |            "name": "Han Solo"
              |          },
              |          {
              |            "name": "C-3PO"
              |          },
              |          {
              |            "name": "R2-D2"
              |          }
              |        ]
              |      }
              |    ]
              |  }
              |}""".stripMargin
          )
          //        hero.name.appearsIn.Friends.*?(Ch.name.Friends.*?(Ch.name)).query.get.map(_.head ==>
          //          ("R2-D2", List(NEWHOPE, EMPIRE, JEDI), List(
          //            ("Luke Skywalker", List("Han Solo", "Leia Organa", "C-3PO", "R2-D2")),
          //            ("Han Solo", List("Luke Skywalker", "Leia Organa", "R2-D2")),
          //            ("Leia Organa", List("Luke Skywalker", "Han Solo", "C-3PO", "R2-D2")),
          //          ))
          //        )
        },
      ),


      suite("Using IDs and query parameters to refetch objects")(

        test("Allows us to query characters directly, using their IDs") {
          graphql(
            """{
              |  human(id: "1000") {
              |    name
              |  }
              |  droid(id: "2000") {
              |    name
              |  }
              |}""".stripMargin,
            """{
              |  "human": {
              |    "name": "Luke Skywalker"
              |  },
              |  "droid": {
              |    "name": "C-3PO"
              |  }
              |}""".stripMargin
          )
          //        Starwars(human("1000").name, droid("2000").name).query.get.map(_.head ==>
          //          ("Luke Skywalker", "C-3PO")
          //        )

          //          // Or separately
          //          human("1000").name.query.get.map(_.head ==> "Luke Skywalker")
          //          droid("1000").name.query.get.map(_.head ==> "C-3PO")
        },

        test("Allows us to create a generic query, then use it to fetch humans using their ID") {
          graphql(
            """query test($someId: String!) {
              |  human(id: $someId) {
              |    name
              |  }
              |}
              |""".stripMargin,
            """{
              |  "human": {
              |    "name": "Luke Skywalker"
              |  }
              |}""".stripMargin,
            Map("someId" -> StringValue("1000")) // Variable
          )

          //          // Input molecule
          //          val humanWithId = human(?).name.query
          //
          //          // Bind variables
          //          humanWithId("1000").get.map(_ ==> List("Luke Skywalker"))
          //          humanWithId("1002").get.map(_ ==> List("Darth Vader"))
          //          humanWithId("xxxx").get.map(_ ==> List())
        },
      ),


      suite("Using aliases to change the key in the response")(

        test("Allows us to query for Luke, changing his key with an alias") {
          graphql(
            """query FetchLukeAliased {
              |  luke: human(id: "1000") {
              |    name
              |  }
              |}""".stripMargin,
            """{
              |  "luke": {
              |    "name": "Luke Skywalker"
              |  }
              |}""".stripMargin
          )

          //          // Molecule returns data only without keys,
          //          // so adding an alias is not needed with molecules
          //          human("1000").name.query.get.map(_.head ==> "Luke Skywalker")
        },

        test("Allows us to query for both Luke and Leia, using two root fields and an alias") {
          graphql(
            """{
              |  luke: human(id: "1000") {
              |    name
              |  }
              |  leia: human(id: "1003") {
              |    name
              |  }
              |}""".stripMargin,
            """{
              |  "luke": {
              |    "name": "Luke Skywalker"
              |  },
              |  "leia": {
              |    "name": "Leia Organa"
              |  }
              |}""".stripMargin
          )

          //        // OR logic can transparently be translated to multiple graphql aliases by Molecule.
          //        // And we can simply ask for multiple variable values:
          //        human("1000", "1003").name.query.get.map(_ ==> List("Luke Skywalker", "Leia Organa"))
        },
      ),


      suite("Uses fragments to express more complex queries")(

        test("Allows us to query using duplicated content") {
          graphql(
            """{
              |  luke: human(id: "1000") {
              |    name
              |    homePlanet
              |  }
              |  leia: human(id: "1003") {
              |    name
              |    homePlanet
              |  }
              |}""".stripMargin,
            """{
              |  "luke": {
              |    "name": "Luke Skywalker",
              |    "homePlanet": "Tatooine"
              |  },
              |  "leia": {
              |    "name": "Leia Organa",
              |    "homePlanet": "Alderaan"
              |  }
              |}""".stripMargin
          )

          //          // Molecule OR logic in combination with multiple fields needs no fragment mechanism
          //          human("1000", "1003").name.homePlanet.query.get.map(_ ==> List(
          //            ("Luke Skywalker", "Tatooine"),
          //            ("Leia Organa", "Alderaan"),
          //          ))
        },
      )

      // Introspection not implemented with Molecule since the whole graphql schema
      // is already generated as Scala types that can be eaisly inferred by the IDE.

    ) @@ sequential


}
