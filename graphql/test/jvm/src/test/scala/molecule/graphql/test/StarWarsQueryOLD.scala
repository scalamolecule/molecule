//package molecule.graphql.test
//
//import caliban.*
//import molecule.graphql.client
//import zio.Scope
//import zio.test.*
//import zio.test.TestAspect.sequential
//
//object StarWarsQueryOLD extends StarWarsTest_zio {
//
//  override def spec: Spec[TestEnvironment & Scope, CalibanError.ValidationError] =
//    suite("Starwars query")(
//      test("hero (arg is None)") {
//        graphql(
//          """{
//            |  hero {
//            |    name
//            |  }
//            |}""".stripMargin,
//          """{
//            |  "hero": {
//            |    "name": "R2-D2"
//            |  }
//            |}""".stripMargin
//        )
//        // hero.name.query.get.map(_.head ==> "R2-D2")
//      },
//
//
//      test("hero in EMPIRE (arg is Some(episode))") {
//        graphql(
//          """{
//            |  hero(episode: EMPIRE) {
//            |    name
//            |  }
//            |}""".stripMargin,
//          """{
//            |  "hero": {
//            |    "name": "Luke Skywalker"
//            |  }
//            |}""".stripMargin
//        )
//        //        hero(EMPIRE).name.query.get.map(_.head ==> "Luke Skywalker")
//      },
//
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
//
//
//      test("R2-D2 with friends") {
//        graphql(
//          """{
//            |  hero {
//            |    id
//            |    name
//            |    friends {
//            |      name
//            |    }
//            |  }
//            |}""".stripMargin,
//          """{
//            |  "hero": {
//            |    "id": "2001",
//            |    "name": "R2-D2",
//            |    "friends": [
//            |      {
//            |        "name": "Luke Skywalker"
//            |      },
//            |      {
//            |        "name": "Han Solo"
//            |      },
//            |      {
//            |        "name": "Leia Organa"
//            |      }
//            |    ]
//            |  }
//            |}""".stripMargin
//        )
//        //        hero.id.name.Friends.*?(Ch.name).query.get.map(_.head ==>
//        //          ("2001", "R2-D2", List("Luke Skywalker", "Han Solo", "Leia Organa"))
//        //        )
//      },
//
//
//      test("R2-D2 with friends of friends") {
//        graphql(
//          """{
//            |  hero {
//            |    name
//            |    friends {
//            |      name
//            |      appearsIn
//            |      friends {
//            |        name
//            |      }
//            |    }
//            |  }
//            |}""".stripMargin,
//          """{
//            |  "hero": {
//            |    "name": "R2-D2",
//            |    "friends": [
//            |      {
//            |        "name": "Luke Skywalker",
//            |        "appearsIn": [
//            |          "NEWHOPE",
//            |          "EMPIRE",
//            |          "JEDI"
//            |        ],
//            |        "friends": [
//            |          {
//            |            "name": "Han Solo"
//            |          },
//            |          {
//            |            "name": "Leia Organa"
//            |          },
//            |          {
//            |            "name": "C-3PO"
//            |          },
//            |          {
//            |            "name": "R2-D2"
//            |          }
//            |        ]
//            |      },
//            |      {
//            |        "name": "Han Solo",
//            |        "appearsIn": [
//            |          "NEWHOPE",
//            |          "EMPIRE",
//            |          "JEDI"
//            |        ],
//            |        "friends": [
//            |          {
//            |            "name": "Luke Skywalker"
//            |          },
//            |          {
//            |            "name": "Leia Organa"
//            |          },
//            |          {
//            |            "name": "R2-D2"
//            |          }
//            |        ]
//            |      },
//            |      {
//            |        "name": "Leia Organa",
//            |        "appearsIn": [
//            |          "NEWHOPE",
//            |          "EMPIRE",
//            |          "JEDI"
//            |        ],
//            |        "friends": [
//            |          {
//            |            "name": "Luke Skywalker"
//            |          },
//            |          {
//            |            "name": "Han Solo"
//            |          },
//            |          {
//            |            "name": "C-3PO"
//            |          },
//            |          {
//            |            "name": "R2-D2"
//            |          }
//            |        ]
//            |      }
//            |    ]
//            |  }
//            |}""".stripMargin
//        )
//        //        hero.name.appearsIn.Friends.*?(Ch.name.Friends.*?(Ch.name)).query.get.map(_.head ==>
//        //          ("R2-D2", List(NEWHOPE, EMPIRE, JEDI), List(
//        //            ("Luke Skywalker", List("Han Solo", "Leia Organa", "C-3PO", "R2-D2")),
//        //            ("Han Solo", List("Luke Skywalker", "Leia Organa", "R2-D2")),
//        //            ("Leia Organa", List("Luke Skywalker", "Han Solo", "C-3PO", "R2-D2")),
//        //          ))
//        //        )
//      },
//
//
//      test("human") {
//        graphql(
//          """{
//            |  human(id: "1000") {
//            |    name
//            |  }
//            |  droid(id: "2000") {
//            |    name
//            |  }
//            |}""".stripMargin,
//          """{
//            |  "human": {
//            |    "name": "Luke Skywalker"
//            |  },
//            |  "droid": {
//            |    "name": "C-3PO"
//            |  }
//            |}""".stripMargin
//        )
//        //        Starwars(human("1000").name, droid("2000").name).query.get.map(_.head ==>
//        //          ("Luke Skywalker", "C-3PO")
//        //        )
//      },
//
//      /**
//       * Generic graphql queries using variables
//       * Molecule not implementing using graphql variables since arguments are applied
//       * directly to attributes of the molecule.
//       */
//
//      /**
//       * Aliases for fields in themselves not relevant for Molecule.
//       * But for OR logic we can use them:
//       */
//      test("OR logic") {
//        graphql(
//          """{
//            |  luke: human(id: "1000") {
//            |    name
//            |  }
//            |  leia: human(id: "1003") {
//            |    name
//            |  }
//            |}""".stripMargin,
//          """{
//            |  "luke": {
//            |    "name": "Luke Skywalker"
//            |  },
//            |  "leia": {
//            |    "name": "Leia Organa"
//            |  }
//            |}""".stripMargin
//        )
//        //        human("1000", "1003").name.query.get.map(_ ==> List("Luke Skywalker", "Leia Organa"))
//      },
//
//      test("__typename") {
//        graphql(
//          """{
//            |  hero {
//            |    __typename
//            |    name
//            |  }
//            |}""".stripMargin,
//          """{
//            |  "hero": {
//            |    "__typename": "Droid",
//            |    "name": "R2-D2"
//            |  }
//            |}""".stripMargin
//        )
//        //        hero.__typename.name.query.get.map(_ ==> ("Droid", "R2-D2"))
//      },
//
//    ) @@ sequential
//}
