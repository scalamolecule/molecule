package molecule.sql.mysql

import boopickle.Default.*
import molecule.core.util.Executor.*
import molecule.coreTests.domains.dsl.Types.*
import molecule.coreTests.setup.{Test, TestUtils}
import molecule.sql.mysql.async.*
import molecule.sql.mysql.setup.DbProviders_mysql


class AdhocJS_mysql extends Test with DbProviders_mysql with TestUtils {


  "types" - types { implicit conn =>
    for {
      _ <- Entity.int.insert(1).transact
      _ <- Entity.int.query.get.map(_ ==> List(1))
    } yield ()
  }


  //    "refs" - refs { implicit conn =>
  //      import molecule.coreTests.domains.dsl.Refs._
  //      for {
  //
  //        _ <- Entity.i.Rs1.*(R1.i).insert(0, List(1)).transact
  //
  //      } yield ()
  //    }

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
