package molecule.document.mongodb

import java.time.LocalTime
import molecule.base.error.{ExecutionError, ModelError}
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs.A
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.document.mongodb.async._
import molecule.document.mongodb.setup.TestSuite_mongodb
import org.bson.BsonInvalidOperationException
import utest._
import scala.language.implicitConversions


object AdhocJVM_mongodb extends TestSuite_mongodb {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        _ <- Ns.i.s.insert(
          (1, "a"),
          (2, "b"),
          (3, "c"),
        ).transact

        //        _ <- rawTransact(
        //          """{
        //            |  "collection": "Ns",
        //            |  "data": [
        //            |    {
        //            |      "i": 1,
        //            |      "s": "a"
        //            |    },
        //            |    {
        //            |      "i": 2,
        //            |      "s": "b"
        //            |    },
        //            |  ]
        //            |}
        //            |""".stripMargin)

        //        _ <- rawQuery(
        //          """{
        //            |  "collection": "Ns",
        //            |  "$match": {
        //            |    "$and": [
        //            |      {
        //            |        "i": 1
        //            |      },
        //            |      {
        //            |        "s": "a"
        //            |      }
        //            |    ]
        //            |  },
        //            |  "$project": {
        //            |    "i": 1,
        //            |    "s": 1,
        //            |    "_id": 0
        //            |  }
        //            |}
        //            |""".stripMargin).map(_.foreach(println))

        //        _ <- rawQuery("""{"collection": "Ns"}""", true)

        _ <- Ns.i(1).s("a").query.get.map(_ ==> List((1, "a")))
      } yield ()
    }

    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {

        //        _ <- A.i.B.i.insert(1, 2).i.transact

        //        _ <- A.s.Bb.*(B.i).insert(
        //          ("a", List(1, 2)),
        //          ("b", List(3)),
        //          //          ("b", Nil),
        //        ).i.transact

        _ <- A.s.Bb.*(B.i).insert(("a", List(1, 2))).i.transact
        //        _ <- A.s.Bb.*(B.i.C.s).insert(("a", List((1, "x")))).i.transact
        //        _ <- A.s.Bb.*(B.i.s).insert(("a", List((1, "x"), (2, "y")))).i.transact
        //        _ <- A.s.Bb.*(B.i.C.s).insert(("a", List((1, "x"), (2, "y")))).i.transact
        //        _ <- A.s.Bb.*(B.s.C.i).insert(("A", List(("x", 1), ("y", 2)))).i.transact

        //                _ <- rawTransact(
        //                  """{
        //                    |  "insert": "A",
        //                    |  "data": [
        //                    |    {
        //                    |      "s": "a",
        //                    |      "bb": [
        //                    |        {
        //                    |          "i": 1,
        //                    |          "c": {
        //                    |            "s": "x"
        //                    |          }
        //                    |        },
        //                    |        {
        //                    |          "i": 2,
        //                    |          "c": {
        //                    |            "s": "y"
        //                    |          }
        //                    |        }
        //                    |      ]
        //                    |    },
        //                    |    {
        //                    |      "s": "b",
        //                    |      "bb": [
        //                    |        {
        //                    |          "i": 3,
        //                    |          "c": {
        //                    |            "s": "z"
        //                    |          }
        //                    |        }
        //                    |      ]
        //                    |    }
        //                    |  ]
        //                    |}
        //                    |""".stripMargin)
        //
        //        _ <- rawQuery(
        //          """{
        //            |  "collection": "A",
        //            |  "$match": {
        //            |    "$and": [
        //            |      {
        //            |        "s": {
        //            |          "$ne": null
        //            |        }
        //            |      }
        //            |    ]
        //            |  },
        //            |  "$project": {
        //            |    "_id": 0,
        //            |    "s": 1,
        //            |    "bb.i": 1,
        //            |  }
        //            |}
        //            |""".stripMargin, true)

        _ <- rawQuery(
          """{
            |  "collection": "A",
            |  "$match": {
            |    "$and": [
            |      {
            |        "s": {
            |          "$ne": null
            |        }
            |      }
            |    ]
            |  }
            |}
            |""".stripMargin, true)


        //        _ <- rawTransact(
        //          """{
        //            |  "insert": "A",
        //            |  "data": [
        //            |    {
        //            |      "s": "a",
        //            |      "bb": [
        //            |        {
        //            |          "i": 1
        //            |        },
        //            |        {
        //            |          "i": 2
        //            |        }
        //            |      ]
        //            |    }
        //            |  ]
        //            |}
        //            |""".stripMargin)


        //        // Mandatory nested data
        //        _ <- A.s.query.i.get.map(_ ==> List("a", "b"))
        _ <- A.s.Bb.*(B.i).query.i.get.map(_ ==> List(("a", List(1, 2))))
        //
        //        // Optional nested data
        //        _ <- A.s.a1.Bb.*?(B.i.a1).query.get.map(_ ==> List(
        //          ("a", List(1, 2)),
        //          ("b", Nil),
        //        ))
      } yield ()
    }


    //    "unique" - unique { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Uniques._
    //      for {
    //        _ <- Uniques.i(1).save.transact
    //
    //      } yield ()
    //    }
    //
    //
    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //
    //      for {
    //        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)
    //
    //      } yield ()
    //    }
  }
}
