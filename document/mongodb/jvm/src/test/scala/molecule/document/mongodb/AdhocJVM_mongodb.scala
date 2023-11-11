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
