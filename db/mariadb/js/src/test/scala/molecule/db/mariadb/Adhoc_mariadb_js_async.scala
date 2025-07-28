package molecule.db.mariadb

import boopickle.Default.*
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import async.*
import molecule.db.mariadb.setup.DbProviders_mariadb

class Adhoc_mariadb_js_async extends MUnit with DbProviders_mariadb with TestUtils {

  "types" - types {
    for {
      List(a, b) <- Entity.int.insert(1, 2).transact.map(_.ids)
      _ <- Entity.int(3).save.transact
      _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2, 3))
      _ <- Entity(a).int(10).update.transact
      _ <- Entity(b).delete.transact
      _ <- Entity.int.a1.query.get.map(_ ==> List(3, 10))

    } yield ()
  }

  // testOnly molecule.db.sql.mariadb.Adhoc_mariadb_js_async

  //    "refs" - refs {
  //      import molecule.db.compliance.domains.dsl.Refs._
  //      for {
  //
  //        _ <- Entity.i.Rs1.*(R1.i).insert(0, List(1)).transact
  //
  //      } yield ()
  //    }

  //
  //    "validation" - validation {
  //      import molecule.db.compliance.domains.dsl.Validation._
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
