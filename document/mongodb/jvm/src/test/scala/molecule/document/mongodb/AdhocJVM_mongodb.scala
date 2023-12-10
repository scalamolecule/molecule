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
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        //                _ <- A.i(1).OwnB.i(2).save.transact.map(_.ids)
        //        _ <- A.i.OwnB.i.query.i.get.map(_.head ==> (1, 2))


        //        ids1 <- A.i(1).B.i(2).Cc.i(3).query.i.get

//        _ <- A.i(1).B.i(2).C.i(3).D.i(4).save.transact
//        _ <- A.i.B.i.C.i.D.i.query.i.get.map(_ ==> List((1, 2, 3, 4)))
//
        _ <- A.i(1).OwnB.i(2)._A.OwnC.i(3).save.i.transact
        _ <- A.i.OwnB.i._A.OwnC.i.query.i.get.map(_ ==> List((1, 2, 3)))

//        _ <- A.i(1).B.i(2)._A.C.i(3).save.transact
//        _ <- A.i.B.i._A.C.i.query.i.get.map(_ ==> List((1, 2, 3)))


        //        _ <- A.i.B.i.C.i.query.i.get.map(_ ==> List((1, 2, 3)))


        //        //        _ <- A.i.OwnB.i.query.get.map(_.head ==> (1, 2))
        //
        //

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
