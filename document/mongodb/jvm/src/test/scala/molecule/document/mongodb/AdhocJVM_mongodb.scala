package molecule.document.mongodb

import java.time.LocalTime
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.document.mongodb.async._
import molecule.document.mongodb.setup.TestSuite_mongodb
import utest._
import scala.collection.immutable.{List, Seq, Set}
import scala.language.implicitConversions


object AdhocJVM_mongodb extends TestSuite_mongodb {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        _ <- Ns.i.s.insert((1, "a"), (2, "b")).i.transact

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

        _ <- Ns.i(1).s("a").query.get.map(_ ==> List((1, "a")))


      } yield ()
    }

    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- A.i(1).B.i(2).save.transact
//        _ <- A.i.B.i.insert(1, 2).transact

        _ <- rawQuery(
          """{
            |  "$match": {
            |    "$and": [
            |      {
            |        "i": {
            |          "$ne": null
            |        }
            |      },
            |      {
            |        "i": {
            |          "$ne": null
            |        }
            |      }
            |    ]
            |  },
            |  "$project": {
            |    "i": 1,
            |    "_id": 0
            |  }
            |}""".stripMargin
        )

//        _ <- A.i.query.get.map(_ ==> List(1))
//        _ <- B.i.query.get.map(_ ==> List(2))
        _ <- A.i.B.i.query.get.map(_ ==> List((1, 2)))

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
