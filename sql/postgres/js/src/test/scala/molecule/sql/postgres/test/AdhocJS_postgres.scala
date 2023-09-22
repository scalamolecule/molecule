package molecule.sql.postgres.test

import boopickle.Default._
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.sql.postgres.async._
import molecule.sql.postgres.setup.TestSuite_postgres
import utest._


object AdhocJS_postgres extends TestSuite_postgres {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      for {
        _ <- Ns.int.insert(1).transact
        _ <- Ns.int.query.get.map(_ ==> List(1))
      } yield ()
    }


    //    "refs" - refs { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Refs._
    //      for {
    //
    //        _ <- Ns.i.Rs1.*(R1.i).insert(0, List(1)).transact
    //
    //      } yield ()
    //    }

    //
    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //      for {
    //
    //        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)
    //
    //        id <- MandatoryRefsB.i(1).refsB(Set(r1, r2)).save.transact.map(_.ids)
    //
    //        // We can remove an entity from a Set of refs as long as it's not the last value
    //        _ <- MandatoryRefsB(id).refsB.remove(r2).update.transact
    //
    //        // Can't remove the last value of a mandatory attribute Set of refs
    //        _ <- MandatoryRefsB(id).refsB.remove(r1).update.transact
    //          .map(_ ==> "Unexpected success").recover {
    //            case ModelError(error) =>
    //              error ==>
    //                """Can't delete mandatory attributes (or remove last values of card-many attributes):
    //                  |  MandatoryRefsB.refsB
    //                  |""".stripMargin
    //          }
    //
    //      } yield ()
    //    }
  }
}
