package molecule.sql.h2

import molecule.base.error.{ModelError, ValidationErrors}
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup.{Test, TestUtils}
import molecule.sql.h2.async._
import molecule.sql.h2.setup.DbProviders_h2
import scala.concurrent.Future
import scala.language.implicitConversions


class AdhocJVM_h2 extends Test with DbProviders_h2 with TestUtils {


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
  //
  //
  //  "mixed" - types { implicit conn =>
  //    for {
  //      _ <- transact(
  //        Entity.int(1).save, //         List(1)
  //        Entity.int.insert(2, 3), //    List(1, 2, 3)
  //        Entity(1).delete, //           List(2, 3)
  //        Entity(3).int.*(10).update, // List(2, 30)
  //      )
  //      _ <- Entity.int.query.get.map(_ ==> List(2, 30))
  //    } yield ()
  //  }


  "refs" - refs { implicit conn =>
    import molecule.coreTests.domains.dsl.Refs._
    for {
//      _ <- A.?(A.i.s).B.s.insert(
//        (None, "-"),
//        (Some((10, "x")), "a"),
//        (Some((20, "y")), "b"),
//      ).transact
//      //
//      //      //      _ <- rawQuery(
//      //      //        """SELECT DISTINCT
//      //      //          |  B.id,
//      //      //          |  B.s
//      //      //          |FROM B where c is null
//      //      //          |""".stripMargin, true)
//      //      //
//      //      _ <- rawQuery(
//      //        """SELECT DISTINCT
//      //          |  B.s
//      //          |FROM A
//      //          |  RIGHT JOIN B ON
//      //          |    A.b = B.id // and A.b IS NULL
//      //          |where A.b IS NULL
//      //          |ORDER BY B.s;
//      //          |""".stripMargin, true)
//      //
//      ////      x <- A.?(A.i.s).B.s.a1.query.get
//      //      _ <- A.?(A.i.s).B.s.a1.query.get.map(_ ==> List(
//      //        (None, "-"),
//      //        (Some((10, "x")), "a"),
//      //        (Some((20, "y")), "b"),
//      //      ))
//
//      _ <- A.?(A.b_()).B.s.a1.query.i.get.map(_ ==> List(
//        (None, "-"),
//      ))

      _ <- A.?(A.i).B.i.insert(List(
        (None, 1),
        (Some(20), 2),
      )).transact

      _ <- A.?(A.i).B.i.a1.query.get.map(_ ==> List(
        (None, 1),
        (Some(20), 2),
      ))

      _ <- A.?(A.b_()).B.i.a1.query.get.map(_ ==> List(
        (None, 1),
      ))
    } yield ()
  }



  //    "unique" - unique { implicit conn =>
  //      import molecule.coreTests.domains.dsl.Uniques._
  //      val triples             = getTriples.map(t => (t._3, t._1, t._2))
  //      val List(a, b, c, d, e) = triples.sortBy(p => (p._2, p._3, p._1))
  //      val query               = (c: String, l: Int) => Uniques.int.a3.s.a1.i.a2.query.from(c).limit(l)
  //      for {
  //        _ <- Uniques.int.s.i.insert(triples).transact
  //
  //      } yield ()
  //    }
  //

  //  "validation" - validation { implicit conn =>
  //    import molecule.coreTests.domains.dsl.Validation._
  //
  //    for {
  //
  //      //        _ <- Type.int(1).save.transact
  //      //          .recover {
  //      //            case ValidationErrors(errorMap) =>
  //      //              errorMap.head._2.head ==>
  //      //                s"""Type.int with value `1` doesn't satisfy validation:
  //      //                   |_ > 2
  //      //                   |""".stripMargin
  //      //          }
  //
  //      //        _ <- Type.int(3).save.transact
  //      //        _ <- transact(Type.int(3).save, Type.int(5).save)
  //      //                _ <- transact(Type.int(1).save, Type.int.insert(5, 6)).recover {
  //      _ <- transact(
  //        Type.int.insert(5, 6),
  //        Type.int(1).save
  //      ).recover {
  //        case ValidationErrors(errorMap) =>
  //          errorMap.head._2.head ==>
  //            s"""Type.int with value `1` doesn't satisfy validation:
  //               |_ > 2
  //               |""".stripMargin
  //      }
  //
  //      _ <- unitOfWork {
  //        for {
  //          last <- Type.int.insert(5, 6).transact
  //          last <- Type.int(1).save.transact
  //        } yield last
  //      }.recover {
  //        case ValidationErrors(errorMap) =>
  //          errorMap.head._2.head ==>
  //            s"""Type.int with value `1` doesn't satisfy validation:
  //               |_ > 2
  //               |""".stripMargin
  //      }
  //
  //      // No data has been transacted
  //      _ <- Type.int.query.get.map(_ ==> List())
  //      //        _ <- Type.int.query.get.map(_ ==> List(3, 5))
  //
  //    } yield ()
  //  }
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
