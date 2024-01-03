package molecule.document.mongodb

import molecule.core.util.AggrUtils
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types.{Ns, Ref}
import molecule.document.mongodb.async._
import molecule.document.mongodb.setup.TestSuite_mongodb
import utest._
import scala.collection.mutable.ListBuffer
import scala.concurrent.Future
import scala.language.implicitConversions


object AdhocJVM_mongodb extends TestSuite_mongodb with AggrUtils {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {

        _ <- Ns.i(1).save.i.transact
        _ <- Ns.i.query.get.map(_ ==> List(1))
      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- A.i.OwnB.i.insert((1, 10), (2, 20)).transact
        _ <- A.i.a1.query.get.map(_ ==> List(1, 2))
        _ <- if (database == "MongoDB") Future.unit else {
          B.i.a1.query.get.map(_ ==> List(10, 20))
        }
        _ <- A.i.a1.OwnB.i.query.get.map(_ ==> List((1, 10), (2, 20)))

        _ <- A.i_.OwnB.i_.>(15).delete.transact
        _ <- A.i.query.get.map(_ ==> List(1))
        // Note that the OwnB.int entity is a separate entity and is not deleted
        // Only the entity of the initial namespace is deleted
        _ <- if (database == "MongoDB") Future.unit else {
          B.i.a1.query.get.map(_ ==> List(10, 20))
        }
        _ <- A.i.OwnB.i.query.get.map(_ ==> List((1, 10)))

        //        _ <- rawTransact(
        //          """{
        //            |  "action": "delete",
        //            |  "A": {
        //            |    "i": 1
        //            |  }
        //            |}
        //            |""".stripMargin)
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
