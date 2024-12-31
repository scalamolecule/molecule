package molecule.sql.sqlite

import molecule.core.util.Executor._
import molecule.coreTests.setup.{Test, TestUtils}
import molecule.sql.sqlite.async._
import molecule.sql.sqlite.setup.DbProviders_sqlite
import scala.language.implicitConversions


class AdhocJVM_sqlite extends Test with DbProviders_sqlite with TestUtils {


//  "types" - types { implicit conn =>
//    import molecule.coreTests.domains.dsl.Types._
//    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
//
//    for {
//      List(a, b) <- Entity.int.insert(1, 2).transact.map(_.ids)
//      _ <- Entity.int(3).save.transact
//      _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2, 3))
//      _ <- Entity(a).int(10).update.transact
//      _ <- Entity(b).delete.transact
//      _ <- Entity.int.a1.query.get.map(_ ==> List(3, 10))
//
//
//    } yield ()
//  }


  "refs" - refs { implicit conn =>
    import molecule.coreTests.domains.dsl.Refs._
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

    for {

      List(e1, e2, _) <- A.i.insert(1, 2, 3).transact.map(_.ids)
      _ <- A.i.a1.query.get.map(_ ==> List(1, 2, 3))
      _ <- A(e1).delete.transact
      // or
      _ <- A.id_(e2).delete.transact
      _ <- A.i.query.get.map(_ ==> List(3))

    } yield ()
  }


  //    "unique" - unique { implicit conn =>
  //      import molecule.coreTests.domains.dsl.Uniques._
  //      //          val triples             = getTriples.map(t => (t._3, t._1, t._2))
  //      //          val List(a, b, c, d, e) = triples.sortBy(p => (p._2, p._3, p._1))
  //      //          val query               = (c: String, l: Int) => Uniques.int.a3.s.a1.i.a2.query.from(c).limit(l)
  //      for {
  //
  //
  //        _ <- Uniques.i.float.insert((1, float1), (2, float2)).transact
  //        //        _ <- Uniques.i.float.insert((1, float1)).transact
  //        //        _ <- Uniques.i.double.insert((1, double1), (2, double2)).transact
  //        //            _ <- Uniques.i.float.insert((2, float2)).transact
  //
  //        _ <- Uniques.i.float.query.get.map(_ ==> List(
  //          (1, 1.1f),
  //          (2, 2.2f),
  //        ))
  //
  //      } yield ()
  //    }


  //    "validation" - validation { implicit conn =>
  //      import molecule.coreTests.domains.dsl.Validation._
  //      for {
  //        id <- MandatoryAttr.name("Bob").age(42).hobbies(Set("golf", "stamps")).save.transact.map(_.id)
  //
  //        // We can remove a value from a Set as long as it's not the last value
  //        _ <- MandatoryAttr(id).hobbies.remove("stamps").update.i.transact
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
  //
  //    "partitions" - partition { implicit conn =>
  //      import molecule.coreTests.domains.dsl.Groups._
  //      for {
  //
  //        _ <- lit_Book.title.Reviewers.name.Professions.*(gen_Profession.name)
  //          .insert("book", "Jan", List("Musician")).transact
  //
  //      } yield ()
  //    }
}
