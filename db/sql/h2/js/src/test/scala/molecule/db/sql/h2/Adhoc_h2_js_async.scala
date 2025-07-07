package molecule.db.sql.h2

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.Entity
import molecule.db.core.util.Executor.*
import molecule.db.sql.h2.async.*
import molecule.db.sql.h2.setup.DbProviders_h2


class Adhoc_h2_js_async extends MUnit with DbProviders_h2 with TestUtils {


  "types" - types { implicit conn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {
      case List(a, b) <- Entity.int.insert(1, 2).transact.map(_.ids)
      _ <- Entity.int(3).save.transact
      _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2, 3))
      _ <- Entity(a).int(10).update.transact
      _ <- Entity(b).delete.transact
      _ <- Entity.int.a1.query.get.map(_ ==> List(3, 10))

    } yield ()
  }

  //  "refs" - refs { implicit conn =>
  //    import molecule.db.compliance.domains.dsl.Refs.*
  //    for {
  //
  //      _ <- A.i(1).B.i(2).save.transact
  //      _ <- A.i.B.i.query.get.map(_ ==> List((1, 2)))
  //
  //    } yield ()
  //  }
  //
  //
  //    "validation" - validation { implicit conn =>
  //      import molecule.db.compliance.domains.dsl.Validation._
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
