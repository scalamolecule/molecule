package molecule.db.postgresql

import boopickle.Default.*
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.postgresql.async.*
import molecule.db.postgresql.setup.DbProviders_postgresql

class Adhoc_postgresql_js_async extends MUnit with DbProviders_postgresql with TestUtils {


  "types" - types {
    for {
      _ <- Entity.int.insert(1).transact
      _ <- Entity.int.query.get.map(_ ==> List(1))

    } yield ()
  }


  "refs" - refs {
    import molecule.db.compliance.domains.dsl.Refs.*
    for {

      _ <- A.i.insert(1).transact
      _ <- A.i.query.get.map(_ ==> List(1))

    } yield ()
  }

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
