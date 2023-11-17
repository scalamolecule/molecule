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


        _ <- Ns.i.int.insert(List(
          (1, int1),
          (2, int2),
          (2, int2),
          (2, int3),
        )).transact

        _ <- rawQuery(
          """{
            |  "collection": "Ns",
            |  "pipeline": [
            |    {
            |      "$match": {
            |        "$and": [
            |          {
            |            "i": {
            |              "$ne": null
            |            }
            |          }
            |        ]
            |      }
            |    },
            |    {
            |      "$group": {
            |        "_id": {
            |          "1_i": "$i"
            |        },
            |        "2_int": {
            |          $addToSet: "$int"
            |        }
            |      }
            |    },
            |    {
            |      "$addFields": {
            |        "1_i": "$_id.1_i"
            |      }
            |    },
            |    {
            |      "$project": {
            |        "_id": 0,
            |        "1_i": 1,
            |        "2_int": 1
            |      }
            |    },
            |    {
            |      "$sort": {
            |        "1_i": 1
            |      }
            |    }
            |  ]
            |}
            |""".stripMargin, true).map(_.foreach(println))


        //        _ <- Ns.i.int.a1.query.get.map(_ ==> List(
        //          (1, int1),
        //          (2, int2), // 2 rows coalesced
        //          (2, int3),
        //        ))

        // Distinct values are returned in a Set
        //        _ <- Ns.i.a1.int(min).query.i.get.map(_ ==> List(
        _ <- Ns.i.a1.int(distinct).query.i.get.map(_ ==> List(
          (1, Set(int1)),
          (2, Set(int2, int3)),
        ))

        //        _ <- Ns.int(distinct).query.get.map(_.head ==> Set(
        //          int1, int2, int3
        //        ))

      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
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
        //            |  }
        //            |}
        //            |""".stripMargin, true)
        _ <- A.i(1).save.transact
        _ <- A.i.query.i.get.map(_ ==> List(1))
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
