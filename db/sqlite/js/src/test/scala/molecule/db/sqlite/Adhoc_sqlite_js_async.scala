package molecule.db.sqlite

import boopickle.Default.*
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import async.*
import molecule.db.sqlite.setup.DbProviders_sqlite

class Adhoc_sqlite_js_async extends MUnit with DbProviders_sqlite with TestUtils {


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


  //    "refs" - refs {
  //      import molecule.db.compliance.domains.dsl.Refs._
  //      for {
  //
  //        _ <- A.i(1).save.transact
  //        _ <- A.i(2).B.s("b").save.transact
  //        _ <- A.i(3).B.s("c").i(3).save.transact
  //
  //        //        // Current entity with A value and ref to B value
  //        //        _ <- A.i.a1.B.i.query.get.map(_ ==> List(
  //        //          (3, 3)
  //        //        ))
  //        //
  //        //        // Filter by A value, update existing B values
  //        //        _ <- A.i_.B.i(4).update.transact
  //        //
  //        //        _ <- A.i.a1.B.i.query.get.map(_ ==> List(
  //        //          (3, 4) // B value updated since there was a previous value
  //        //        ))
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
