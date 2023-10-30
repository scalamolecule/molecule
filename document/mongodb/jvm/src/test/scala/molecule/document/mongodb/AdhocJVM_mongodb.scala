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
      //      val id0 = "123456789012345678901234"
      val id0 = "1234567890"
      for {
        List(id1, id2, id3) <- Ns.i.insert(1, 2, 3).transact.map(_.ids)
        a = (1, id1)
        b = (2, id2)
        c = (3, id3)

        // Find all ids
        _ <- Ns.i.a1.id.query.get.map(_ ==> List(a, b, c))

        // Find value(s) matching
        _ <- Ns.i.a1.id(id0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.id(id1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.id(Seq(id0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.id(Seq(id1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args
        _ <- Ns.i.a1.id(id1, id2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.id(id1, id0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.id(Seq(id1, id2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.id(Seq(id1, id0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no ids
        _ <- Ns.i.a1.id(Seq.empty[String]).query.get.map(_ ==> List())

      } yield ()
    }

    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- A.i(1).B.i(2).save.transact
//        _ <- A.i.B.i.insert(1, 2).transact

//        _ <- rawQuery(
//          """{
//            |  "i": 1
//            |}""".stripMargin
//        )

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
