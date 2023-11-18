package molecule.document.mongodb

import molecule.core.util.Executor._
import molecule.document.mongodb.async._
import molecule.document.mongodb.setup.TestSuite_mongodb
import utest._
import scala.language.implicitConversions


object AdhocJVM_mongodb extends TestSuite_mongodb {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        //        _ <- Ns.i.s.insert(
        //          (1, "a"),
        //          (2, "b"),
        //          (3, "c"),
        //        ).transact

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


        //        _ <- rawQuery("""{"collection": "Ns"}""", true)

        _ <- Ns.i.string.insert(
          (1, "hello"),
          (2, "friends"),
        ).transact

        _ <- Ns.string.take(1).a1.query.i.get.map(_ ==> List("f", "h"))

        //
        //        _ <- Ns.i.int.insert(List(
        //          (1, int1),
        //          (2, int2),
        //          (2, int2),
        //          (2, int3),
        //        )).transact

        //        _ <- Ns.int(count).query.i.get.map(_ ==> List(4))
        //        _ <- Ns.i.a1.int(count).query.i.get.map(_ ==> List(
        //          (1, 1),
        //          (2, 3)
        //        ))


        //        _ <- Ns.i.Ref.int.insert(List(
        //          (1, int1),
        //          (2, int2),
        //          (2, int2),
        //          (2, int3),
        //        )).transact
        //
        //        _ <- rawQuery(
        //          """{
        //            |  "collection": "Ns",
        //            |  "pipeline": [
        //            |    {
        //            |      "$match": {
        //            |        "$and": [
        //            |          {
        //            |            "i": {
        //            |              "$ne": null
        //            |            }
        //            |          }
        //            |        ]
        //            |      }
        //            |    },
        //            |    {
        //            |      "$group": {
        //            |        "_id": {
        //            |          "1_i": "$i"
        //            |        },
        //            |        "ref_2_int": {
        //            |          "$sum": 1
        //            |        }
        //            |      }
        //            |    },
        //            |    {
        //            |      "$addFields": {
        //            |        "1_i": "$_id.1_i"
        //            |      }
        //            |    },
        //            |    {
        //            |      "$project": {
        //            |        "_id": 0,
        //            |        "1_i": 1,
        //            |        "ref_2_int": 1
        //            |      }
        //            |    },
        //            |    {
        //            |      "$sort": {
        //            |        "1_i": 1
        //            |      }
        //            |    }
        //            |  ]
        //            |}
        //            |""".stripMargin, true).map(_.foreach(println))

        //        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        //        _ <- Ns.int(count).query.i.get.map(_ ==> List(4))
        //        _ <- Ns.i(count).int(count).query.i.get.map(_ ==> List((4, 4)))
        //        _ <- Ns.i.a1.int(count).query.get.map(_ ==> List(
        //          (1, 1),
        //          (2, 3)
        //        ))

        //        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))
        //        _ <- Ns.int(countDistinct).query.i.get.map(_ ==> List(3))
        //        _ <- Ns.i(count).int(countDistinct).query.i.get.map(_ ==> List((2, 3)))
        //        _ <- Ns.i(countDistinct).int(countDistinct).query.i.get.map(_ ==> List((2, 3)))
        //        _ <- Ns.i.a1.int(countDistinct).query.get.map(_ ==> List(
        //          (1, 1),
        //          (2, 2)
        //        ))

        //        _ <- Ns.i.a1.Ref.int.query.i.get.map(_ ==> List())
        //
        //        _ <- Ns.i.a1.Ref.int(count).query.i.get.map(_ ==> List(
        //          (1, 1),
        //          (2, 3)
        //        ))

      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- A.i.B.i.insert(1, 2).transact
        _ <- rawQuery(
          """{
            |  "collection": "A",
            |  "pipeline": [
            |    {
            |      "$match": {
            |        "$and": [
            |          {
            |            "i": {
            |              "$ne": null
            |            }
            |          },
            |          {
            |            "b.i": {
            |              "$ne": null
            |            }
            |          }
            |        ]
            |      }
            |    },
            |    {
            |      "$addFields": {
            |        "1_i": "$i",
            |        "2_i": "$b.i"
            |      }
            |    },
            |    {
            |      "$project": {
            |        "_id": 0,
            |        "1_i": 1,
            |        "2_i": 1
            |      }
            |    }
            |  ]
            |}
            |""".stripMargin, true).map(println)
        _ <- A.i.B.i.query.i.get.map(_ ==> List((1, 2)))
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
