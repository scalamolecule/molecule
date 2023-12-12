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
        _ <- A.i(0).s("a").B.i(1).s("b").Cc.i(22)
          ._B.C.i(2).s("c")
          ._B._A.Bb.i(11)
          .save.transact

        _ <- A.i.B.i.query.get.map(_ ==> List((0, 1)))
        _ <- A.i.B.i._A.s.query.get.map(_ ==> List((0, 1, "a")))
        _ <- A.i.B.i._A.Bb.i.query.get.map(_ ==> List((0, 1, 11)))
        _ <- A.i.B.i.C.i._B.s.query.get.map(_ ==> List((0, 1, 2, "b")))
        _ <- A.i.B.C.i.query.get.map(_ ==> List((0, 2)))
        _ <- A.i.B.C.i._B.i.query.get.map(_ ==> List((0, 2, 1)))
        _ <- A.i.B.i.C.i._B.Cc.i.query.get.map(_ ==> List((0, 1, 2, 22)))
        _ <- A.i.B.C.i._B.Cc.i.query.get.map(_ ==> List((0, 2, 22)))
        _ <- A.i.B.i.C.i._B.s._A.s.query.get.map(_ ==> List((0, 1, 2, "b", "a")))
        _ <- A.i.B.i.C.i._B._A.s.query.get.map(_ ==> List((0, 1, 2, "a")))
        _ <- A.i.B.C.i._B._A.s.query.get.map(_ ==> List((0, 2, "a")))
        _ <- A.i.B.i.C.i._B.s._A.Bb.i.query.get.map(_ ==> List((0, 1, 2, "b", 11)))
        _ <- A.i.B.i.C.i._B._A.Bb.i.query.get.map(_ ==> List((0, 1, 2, 11)))
        _ <- A.i.B.C.i._B._A.Bb.i.query.get.map(_ ==> List((0, 2, 11)))
        _ <- A.B.C.s._B._A.Bb.i.query.get.map(_ ==> List(("c", 11)))
      } yield ()
    }

    "r-r" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- A.i(1).B.i(2).C.i(3).save.transact
        _ <- A.i.B.i.C.i.query.i.get.map(_ ==> List((1, 2, 3)))
      } yield ()
    }


    "o-r" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- A.i(1).OwnB.i(2).C.i(3).save.transact
        _ <- A.i.OwnB.i.C.i.query.i.get.map(_ ==> List((1, 2, 3)))

        _ <- A.i(1).OwnB.i(2).OwnC.i(3).D.i(4).save.transact
        _ <- A.i.OwnB.i.OwnC.i.D.i.query.i.get.map(_ ==> List((1, 2, 3, 4)))
      } yield ()
    }


    "r-o" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- A.i(1).B.i(2).OwnC.i(3).save.transact
        _ <- A.i.B.i.OwnC.i.query.i.get.map(_ ==> List((1, 2, 3)))
      } yield ()
    }
    "r-o 4" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- A.i(1).B.i(2).C.i(3).OwnD.i(4).save.transact
        _ <- A.i.B.i.C.i.OwnD.i.query.i.get.map(_ ==> List((1, 2, 3, 4)))
      } yield ()
    }

    "o-o" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- A.i(1).OwnB.i(2)._A.OwnC.i(3).save.i.transact
        _ <- A.i.OwnB.i._A.OwnC.i.query.i.get.map(_ ==> List((1, 2, 3)))
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
