package molecule.graphql.client

import caliban.*
import caliban.Value.StringValue
import zio.Scope
import zio.test.*
import zio.test.TestAspect.sequential
import molecule.graphql.client.dsl.Starwars.*
//import molecule.graphql.client.dsl.Starwars.Starwars.Episode.EMPIRE
//import molecule.graphql.client.dsl.Starwars.Starwars.hero

object StarwarsQuery extends StarwarsTest {

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
          // hero.name.query.get.map(_.head ==> "R2-D2")
        },

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
        },



        test("Allows us to create a generic query, then use it to fetch Luke Skywalker using his ID") {
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
            Map("someId" -> StringValue("1000x")) // Variable
          )

                  hero(EMPIRE).name.query.get.map(_.head ==> "Luke Skywalker")
        },


        /**
         * Aliases for fields in themselves not relevant for Molecule.
         * But for OR logic we can use them:
         */
        test("OR logic") {
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
          //        human("1000", "1003").name.query.get.map(_ ==> List("Luke Skywalker", "Leia Organa"))
        },

        test("__typename") {
          graphql(
            """{
              |  hero {
              |    __typename
              |    name
              |  }
              |}""".stripMargin,
            """{
              |  "hero": {
              |    "__typename": "Droid",
              |    "name": "R2-D2"
              |  }
              |}""".stripMargin
          )
          //        hero.__typename.name.query.get.map(_ ==> ("Droid", "R2-D2"))
        },









        //
        //      test("hero in JEDI (arg is Some(episode))") {
        //        graphql(
        //          """{
        //            |  hero(episode: JEDI) {
        //            |    name
        //            |  }
        //            |}""".stripMargin,
        //          """{
        //            |  "hero": {
        //            |    "name": "R2-D2"
        //            |  }
        //            |}""".stripMargin
        //        )
        //        //        hero(JEDI).name.query.get.map(_.head ==> "R2-D2")
        //      },
      )
    ) @@ sequential


}
