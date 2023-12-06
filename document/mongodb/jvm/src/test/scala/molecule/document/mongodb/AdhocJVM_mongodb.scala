package molecule.document.mongodb

import com.mongodb.client.model.Filters
import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.document.mongodb.AdhocJVM_mongodb.int2
import molecule.document.mongodb.async._
import molecule.document.mongodb.setup.TestSuite_mongodb
import utest._
import scala.collection.immutable.List
import scala.language.implicitConversions


object AdhocJVM_mongodb extends TestSuite_mongodb {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        //        _ <- Ns.i(1).save.transact
        //        _ <- Ns.i.query.get.map(_ ==> List(1))


        _ <- Ns.i.insert(1, 2).transact
        _ <- Ns.i_(1).delete.transact
        _ <- Ns.i.query.get.map(_ ==> List(2))

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


      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
//        _ <- A.i(1).OwnB.i(2).save.transact.map(_.ids)
//        _ <- A.i.OwnB.i.query.i.get.map(_.head ==> (1, 2))

        ids1 <- A.i(1).B.i(2).save.transact.map(_.ids)
        //        _ <- A.i.B.i.query.i.get.map(_.head ==> (1, 2))
        //        _ = println(ids1)

        //        _ <- rawTransact(
        //          """{
        //            |  "action": "insert",
        //            |  "A": [
        //            |    {
        //            |      "i": 1,
        //            |      "b": {
        //            |        "i": 2
        //            |      }
        //            |    }
        //            |  ]
        //            |}
        //            |""".stripMargin)

        //        _ <- rawTransact(
        //          """{
        //            |  "action": "insert",
        //            |  "A": [
        //            |    {
        //            |      "i": 1,
        //            |      "b": "42"
        //            |    }
        //            |  ],
        //            |  "B": [
        //            |    {
        //            |      "_id": "42"
        //            |      "i": 2
        //            |    }
        //            |  ]
        //            |}
        //            |""".stripMargin)
        //
        //        //        _ <- A.i.OwnB.i.query.get.map(_.head ==> (1, 2))
        //
        //
        _ <- rawQuery(
          """{
            |  "collection": "A",
            |  "pipeline": [
            |    {
            |      "$lookup": {
            |        "from": "B",
            |        "localField": "b",
            |        "foreignField": "_id",
            |        "pipeline": [
            |          {
            |            "$project": {
            |              "_id": 0,
            |              "i": 1
            |            }
            |          }
            |        ],
            |        "as": "b"
            |      }
            |    },
            |    {
            |      "$addFields": {
            |        "b": {
            |          $arrayElemAt: [
            |            "$b",
            |            0
            |          ]
            |        }
            |      }
            |    },
            |    {
            |      "$project": {
            |        "_id": 0,
            |        "i": 1,
            |        "b": 1
            |      }
            |    }
            |  ]
            |}
            |""".stripMargin, true).map(println)
        _ <- rawQuery(
          """{
            |  "collection": "A",
            |  "pipeline": [
            |    {
            |      "$lookup": {
            |        "from": "B",
            |        "localField": "b",
            |        "foreignField": "_id",
            |        "pipeline": [
            |          {
            |            "$project": {
            |              "_id": 0,
            |              "i": 1
            |            }
            |          }
            |        ],
            |        "as": "b"
            |      }
            |    },
            |    {
            |      "$project": {
            |        "_id": 0,
            |        "i": 1,
            |        "b": 1
            |      }
            |    }
            |  ]
            |}
            |""".stripMargin, true).map(println)


        //        ids2 <- A.i(1).B.i(2).save.i.transact.map(_.ids)
        //        //        _ = println(ids2)
        //        _ <- A.i.B.i.query.i.get.map(_.head ==> (1, 2))

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
