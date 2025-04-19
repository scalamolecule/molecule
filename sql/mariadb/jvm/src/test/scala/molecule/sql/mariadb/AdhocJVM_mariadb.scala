package molecule.sql.mariadb

import molecule.core.util.Executor.*
import molecule.coreTests.setup.{Test, TestUtils}
import molecule.sql.mariadb.async.*
import molecule.sql.mariadb.setup.DbProviders_mariadb
import scala.language.implicitConversions

class AdhocJVM_mariadb extends Test with DbProviders_mariadb with TestUtils {

  val a = (1, Map("a" -> localDate1, "b" -> localDate2))
  val b = (2, Map("a" -> localDate2, "b" -> localDate3, "c" -> localDate4))

  "types" - types { implicit conn =>
    import molecule.coreTests.domains.dsl.Types.*
    for {

      //        id <- Entity.i(42).save.transact.map(_.id)
      //
      //        // Map attribute not yet asserted
      //        _ <- Entity.intMap.query.get.map(_ ==> Nil)
      //
      //        // Removing pair by key from non-asserted Map has no effect
      //        _ <- Entity(id).intMap.remove(string1).update.transact
      //        _ <- Entity.intMap.query.get.map(_ ==> Nil)
      //
      //        // Start with some pairs
      //        _ <- Entity(id).intMap.add(pint1, pint2, pint3, pint4, pint5, pint6, pint7).upsert.transact
      //
      //        // Remove pair by String key
      //        _ <- Entity(id).intMap.remove(string7).update.i.transact
      //        _ <- Entity.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4, pint5, pint6))
      //
      //


      //        _ <- rawTransact(
      //          """UPDATE Entity
      //            |    SET
      //            |      string = concat("x", "b")
      //            |    WHERE
      //            |      Entity.id IN(1) AND
      //            |      Entity.string IS NOT NULL
      //            |""".stripMargin)

//      id <- Entity.string("a").save.transact.map(_.id)
//      _ <- Entity(id).string.prepend("b").update.transact
//      _ <- Entity.string.query.get.map(_.head ==> "ba")


      _ <- Entity.i.localDateMap.insert(a, b).transact

      // Get Map value by key

      // Like calling `apply` on a Scala Map.
//      _ <- Entity.i.localDateMap("_").query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap("a").query.inspect

      _ <- Entity.i.a1.localDateMap("a").query.get.map(_ ==> List((1, localDate1), (2, localDate2)))
//      _ <- Entity.i.a1.localDateMap("b").query.get.map(_ ==> List((1, localDate2), (2, localDate3)))
//      _ <- Entity.i.a1.localDateMap("c").query.get.map(_ ==> List((2, localDate4)))

    } yield ()
  }


//  "refs" - refs { implicit conn =>
//    import molecule.coreTests.domains.dsl.Refs.*
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
  //      import molecule.coreTests.domains.dsl.Uniques._
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
  //      import molecule.coreTests.domains.dsl.Validation._
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