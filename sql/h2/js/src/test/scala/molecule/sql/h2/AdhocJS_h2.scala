package molecule.sql.h2

import boopickle.Default._
import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.sql.h2.async._
import molecule.sql.h2.setup.TestSuite_h2
import utest._


object AdhocJS_h2 extends TestSuite_h2 {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      for {
        List(a, b) <- Ns.int.insert(1, 2).transact.map(_.ids)
        _ <- Ns.int(3).save.transact
        _ <- Ns.int.a1.query.get.map(_ ==> List(1, 2, 3))
        _ <- Ns(a).int(10).update.transact
        _ <- Ns(b).delete.transact
        _ <- Ns.int.a1.query.get.map(_ ==> List(3, 10))

      } yield ()
    }


    //    "refs" - refs { implicit conn =>
    //      import molecule.coreTests.dataModels.dsl.Refs._
    //      for {
    //
    //        _ <- A.i(1).save.transact
    //        _ <- A.i(2).B.s("b").save.transact
    //        _ <- A.i(3).B.s("c").i(3).save.transact
    //
    //        // Current entity with A value and ref to B value
    //        _ <- A.i.a1.B.i.query.get.map(_ ==> List(
    //          (3, 3)
    //        ))
    //
    //        // Filter by A value, update existing B values
    //        _ <- A.i_.B.i(4).update.transact
    //
    //        _ <- A.i.a1.B.i.query.get.map(_ ==> List(
    //          (3, 4) // B value updated since there was a previous value
    //        ))
    //
    //        // Filter by A ids, upsert B values (insert if not already present)
    //        _ <- A.i_.B.i(5).upsert.i.transact
    //
    //        // Now three A entities with referenced B value
    //        _ <- A.i.a1.B.i.query.get.map(_ ==> List(
    //          (1, 5), // relationship to B created and B value inserted
    //          (2, 5), // B value inserted
    //          (3, 5), // B value updated
    //        ))
    //
    //      } yield ()
    //    }
    //
    //
    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.dsl.Validation._
    //      for {
    //
    //        id <- MandatoryAttr.name("Bob").age(42)
    //          .hobbies(Set("golf")).save.transact.map(_.id)
    //        //          .hobbies(Set("golf", "stamps")).save.transact.map(_.id)
    //
    //        //        // We can remove a value from a Set as long as it's not the last value
    //        //        _ <- MandatoryAttr(id).hobbies.remove("stamps").update.transact
    //
    //        // Can't remove the last value of a mandatory attribute Set of values
    //        _ <- MandatoryAttr(id).hobbies.remove("golf").update.transact
    //          .map(_ ==> "Unexpected success").recover {
    //            case ModelError(error) =>
    //              error ==>
    //                """Can't delete mandatory attributes (or remove last values of card-many attributes):
    //                  |  MandatoryAttr.hobbies
    //                  |""".stripMargin
    //          }
    //
    //      } yield ()
    //    }
  }
}
