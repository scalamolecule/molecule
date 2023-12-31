package molecule.document.mongodb

import molecule.core.util.Executor._
import molecule.document.mongodb.async._
import molecule.document.mongodb.setup.TestSuite_mongodb2
import utest._
import scala.language.implicitConversions


object AdhocJVM_mongodb2 extends TestSuite_mongodb2 {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.document.mongodb.dsl.Types2._
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
        //        _ <- rawQuery(
        //          """{
        //            |  "collection": "A",
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
        //            |  ]
        //            |}
        //            |""".stripMargin, false)//.map(println)

      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.document.mongodb.dsl.Refs2._
      for {

        _ <- A.i.B.i.insert(List(
          (1, 1),
          (2, 2),
          (2, 2),
          (2, 3),
        )).i.transact

        _ <- A.B.i(count).query.i.get.map(_ ==> List(4))
        //        _ <- A.i.a1.B.i(count).query.get.map(_ ==> List(
        //          (1, 1),
        //          (2, 3)
        //        ))
        //
        //        _ <- A.B.i(countDistinct).query.get.map(_ ==> List(3))
        //        _ <- A.i.a1.B.i(countDistinct).query.get.map(_ ==> List(
        //          (1, 1),
        //          (2, 2)
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
