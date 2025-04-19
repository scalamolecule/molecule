package molecule.sql.mariadb

import boopickle.Default.*
import molecule.core.util.Executor.*
import molecule.coreTests.domains.dsl.Types.*
import molecule.coreTests.setup.{Test, TestUtils}
import molecule.sql.mariadb.async.*
import molecule.sql.mariadb.setup.DbProviders_mariadb


class AdhocJS_mariadb extends Test with DbProviders_mariadb with TestUtils {
//  val a = (1, Map("a" -> localDate1))
  val a = (1, Map("a" -> localDate1, "b" -> localDate2))
  val b = (2, Map("a" -> localDate2, "b" -> localDate3, "c" -> localDate4))


  "types" - types { implicit conn =>
    for {
//      _ <- Entity.int.insert(1).transact
//      _ <- Entity.int.query.get.map(_ ==> List(1))



      _ <- Entity.i.localDateMap.insert(a, b).transact
//      _ <- Entity.i.localDateMap.insert(a).transact

      // Get Map value by key

      // Like calling `apply` on a Scala Map.
      _ <- Entity.i.a1.localDateMap("_").query.get.map(_ ==> List())
//      _ <- Entity.i.a1.localDateMap("a").query.inspect

      _ <- Entity.i.a1.localDateMap("a").query.get.map(_ ==> List((1, localDate1), (2, localDate2)))
      _ <- Entity.i.a1.localDateMap("a").query.i.get.map(_ ==> List((1, localDate1), (2, localDate2)))
      _ <- Entity.i.a1.localDateMap("b").query.get.map(_ ==> List((1, localDate2), (2, localDate3)))
      _ <- Entity.i.a1.localDateMap("c").query.get.map(_ ==> List((2, localDate4)))




    } yield ()

//    1 ==> 2
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
