package molecule.db.sql.mariadb

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.core.util.Executor.*
import molecule.db.sql.mariadb.async.*
import molecule.db.sql.mariadb.setup.DbProviders_mariadb


class Adhoc_mariadb_jvm_async extends MUnit with DbProviders_mariadb with TestUtils {


  "types" - types { implicit conn =>
    import molecule.db.compliance.domains.dsl.Types.*
    for {


      List(a, b) <- Entity.int.insert(1, 2).transact.map(_.ids)


      //      _ <- Entity.int(3).save.transact
      //      _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2, 3))
      //      _ <- Entity(a).int(10).update.transact
      //      _ <- Entity(b).delete.transact
      //      _ <- Entity.int.a1.query.get.map(_ ==> List(3, 10))
    } yield ()
  }


  //  "refs" - refs { implicit conn =>
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


  //    "unique" - unique { implicit conn =>
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
  //    "validation" - validation { implicit conn =>
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