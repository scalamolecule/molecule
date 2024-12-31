package molecule.sql.postgres

import boopickle.Default._
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup.{MUnitSuite, TestUtils}
import molecule.sql.postgres.async._
import molecule.sql.postgres.setup.DbProviders_postgres


class AdhocJS_postgres extends MUnitSuite with DbProviders_postgres with TestUtils {


  "types" - types { implicit conn =>
    for {
      _ <- Entity.int.insert(1).transact
      _ <- Entity.int.query.get.map(_ ==> List(1))

    } yield ()
  }


  "refs" - refs { implicit conn =>
    import molecule.coreTests.domains.dsl.Refs._
    for {

      _ <- A.i.insert(1).transact
      _ <- A.i.query.get.map(_ ==> List(1))

    } yield ()
  }

  //
  //    "validation" - validation { implicit conn =>
  //      import molecule.coreTests.domains.dsl.Validation._
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
