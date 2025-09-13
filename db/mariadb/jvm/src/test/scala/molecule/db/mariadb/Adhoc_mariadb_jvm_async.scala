package molecule.db.mariadb

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.Entity
import molecule.db.mariadb.async.*
import molecule.db.mariadb.setup.DbProviders_mariadb
import org.scalactic.Equality


class Adhoc_mariadb_jvm_async extends MUnit with DbProviders_mariadb with TestUtils {

  "types" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    for {
      List(a, b) <- Entity.int.insert(1, 2).transact.map(_.ids)
      //      _ <- Entity.int(3).save.transact
      //      _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2, 3))
      //      _ <- Entity(a).int(10).update.transact
      //      _ <- Entity(b).delete.transact
      //      _ <- Entity.int.a1.query.get.map(_ ==> List(3, 10))


    } yield ()
  }


  //  "refs" - refs {
  //    import molecule.db.compliance.domains.dsl.Refs.*
  //    for {
  //
  //      _ <- A.i.B.?(B.iMap).insert(
  //        (0, None),
  //        (1, Some(Map("a" -> 1, "b" -> 2))),
  //      ).transact
  //
  //      _ <- A.i.B.?(B.iMap).query.i.get.map(_ ==> List(
  //        (0, None),
  //        (1, Some(Map("a" -> 1, "b" -> 2))),
  //      ))
  //
  //    } yield ()
  //  }


  //    "unique" - unique {
  //      import molecule.db.compliance.domains.dsl.Uniques._
  //
  //
  //      for {
  //

  //
  //
  //
  //        _ <- Uniques.int.insert(1, 2).transact
  //
  //
  //      } yield ()
  //    }
  //
  //
  //    "validation" - validation {
  //      import molecule.db.compliance.domains.dsl.Validation._
  //      for {
  //        id <- MandatoryAttr.name("Bob").age(42).hobbies(Set("golf", "stamps")).save.transact.map(_.id)
  //
  //        // We can remove a value from a Set as long as it's not the last value
  //        _ <- MandatoryAttr(id).hobbies.remove("stamps").update.i.transact
  //
  //        //        // Can't remove the last value of a mandatory attribute Set of values
  //        //        _ <- MandatoryAttr(id).hobbies.remove("golf").update.transact
  //        //          .map(_ ==> "Unexpected success").recover {
  //        //            case ModelError(error) =>
  //        //              error ==>
  //        //                """Can't delete mandatory attributes (or remove last values of card-many attributes):
  //        //                  |  MandatoryAttr.hobbies
  //        //                  |""".stripMargin
  //        //          }
  //
  //      } yield ()
  //    }
}