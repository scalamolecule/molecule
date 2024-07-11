package molecule.sql.h2

import java.time.Instant
import molecule.base.error.{ExecutionError, ModelError}
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.coreTests.util.Array2List
import molecule.sql.h2.async._
import molecule.sql.h2.setup.{TestSuiteArray_h2, TestSuite_h2}
import utest._
import scala.collection.mutable
import scala.concurrent.Future
import scala.language.implicitConversions


//object AdhocJVM_h2 extends TestSuiteArray_h2 {
object AdhocJVM_h2 extends TestSuite_h2 {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

      for {
        _ <- Ns.int.insert(8).transact
        _ <- Ns.int.query.get.map(_ ==> List(8))
      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      //      val a0: Future[List[(Int, String, Seq[(Int, String)])]]                   = A.i.s.Bb.*?(B.i.s).query.get
      //      val a1: Future[List[(Int, String, Seq[(Int, String)])]]                   = A.i.s.Bb.*(B.i.s).query.get
      //      val a2: Future[List[(Int, String, Option[(Int, String)])]]                = A.i.s.B.?(B.i.s).query.get
      //      val a3: Future[List[(Int, String, Option[(Int, Option[String])])]]        = A.i.s.B.?(B.i.s_?).query.get
      //      val a4: Future[List[(Int, String, Int, String)]]                          = A.i.s.B.i.s.query.get
      //      val a6: Future[List[(Int, Option[(Int, String, Int, String)])]]           = A.i.B.?(B.i.s.C.i.s).query.get
      //      val a7: Future[List[(Int, Option[(Int, String)])]]                        = A.i.B.?(B.i.s).query.get
      //      val a5: Future[List[(Int, Option[(Int, String, Option[(Int, String)])])]] = A.i.B.?(B.i.s.C.?(C.i.s)).query.get
      //      val a8: Future[List[(Int, Option[(Int, String)], Option[(Int, String)])]] = A.i.B.?(B.i.s).C.?(C.i.s).query.get
      for {


        //        _ <- A.s.Bb.*(B.i).insert(
        //          ("a", List(2, 3)),
        //        ).transact
        //        _ <- A.s.Bb.*(B.i).query.get


        _ <- A.s.Bb.*(B.i.Cc.*(C.i)).insert(
          ("a", List(
            (2, List(20, 21)),
            (3, List(30, 31)),
          )),
//          ("a", List((2, List(20)))),
        ).transact

        _ <- rawQuery(
          """SELECT DISTINCT
            |  A.id,
            |  B.id,
            |  A.s,
            |  B.i,
            |  C.i
            |FROM A
            |  INNER JOIN A_bb_B ON
            |    A.id = A_bb_B.A_id
            |  INNER JOIN B ON
            |    A_bb_B.B_id = B.id
            |  INNER JOIN B_cc_C ON
            |    B.id = B_cc_C.B_id
            |  INNER JOIN C ON
            |    B_cc_C.C_id = C.id
            |WHERE
            |  A.s IS NOT NULL AND
            |  B.i IS NOT NULL AND
            |  C.i IS NOT NULL;
            |""".stripMargin, true)


        _ <- A.s.Bb.*(B.i.Cc.*(C.i)).query.get
        //        _ <- A.s.Bb.*(B.s).query.get
        //                  .map(_ ==> List(
        //                  ("a", List(2, 3)),
        //                ))


        //        //        _ <- A.i(1).save.transact
        //        //        _ <- A.i(2).B.i(20).save.transact
        //        //        _ <- A.i(3).B.i(30)._A.C.s("300").i(300).save.transact
        //        //        _ <- A.i(4).B.i(40).s("40").save.transact
        //        //        _ <- A.i(5).B.i(50).s("50")._A.C.s("500").save.transact
        //        _ <- A.i(6).B.i(60).s("60")._A.C.s("600").i(600).save.transact
        //
        //        //        _ <- rawQuery(
        //        //          """SELECT DISTINCT
        //        //            |  A.i,
        //        //            |  B.i,
        //        //            |  B.s,
        //        //            |  C.s,
        //        //            |  C.i
        //        //            |FROM A
        //        //            |  LEFT JOIN B ON
        //        //            |    A.b = B.id AND
        //        //            |    B.i IS NOT NULL AND
        //        //            |    B.s IS NOT NULL
        //        //            |  LEFT JOIN C ON
        //        //            |    B.c = C.id AND
        //        //            |    C.s IS NOT NULL AND
        //        //            |    C.i IS NOT NULL
        //        //            |WHERE
        //        //            |  A.i IS NOT NULL
        //        //            |""".stripMargin, true)
        //        //
        //
        //        //        _ <- rawQuery(
        //        //          """SELECT DISTINCT
        //        //            |  A.i,
        //        //            |  B.i, B.s,
        //        //            |  C.i, C.s
        //        //            |FROM A
        //        //            |  LEFT JOIN B ON
        //        //            |    A.b = B.id AND
        //        //            |    B.i IS NOT NULL AND
        //        //            |    B.s IS NOT NULL
        //        //            |  LEFT JOIN C ON
        //        //            |    A.c = C.id AND
        //        //            |    C.i IS NOT NULL AND
        //        //            |    C.s IS NOT NULL
        //        //            |WHERE
        //        //            |  A.i IS NOT NULL
        //        //            |""".stripMargin, true)
        //
        ////        _ <- A.i.B.?(B.i.s.C.?(C.s.i)).query.i.get
        //
        //        a = List(
        //          List(0, -1),
        //          List(0, 0, -1),
        //          List(0, 0),
        //        )
        //
        //        b = List(
        //          List(0, -1),
        //          List(2),
        //          List(2),
        //        )
        //
        //        c = List(
        //          List(0, 2, 2),
        //        )
        //
        //        _ <- A.i
        //          .B.?(B.i.s)
        //          .C.?(C.s.i).query.i.get.map(_ ==> List(
        //            //          (1, None),
        //            //          (2, None),
        //            //          (3, None),
        //            //          (4, Some((40, "40", None))),
        //            //          (5, Some((50, "50", None))),
        //            (6, Some((60, "60")), Some(("600", 600))),
        //          ))

        //        _ <- A.i
        //          .B.?(B.i.s)
        //          .C.?(C.s.i_?).query.i.get.map(_ ==> List(
        //            (1, None),
        //            (2, None),
        //            (3, None),
        //            (4, Some((40, "40", None))),
        //            (5, Some((50, "50", Some(("500", None))))),
        //            (6, Some((60, "60", Some(("600", Some(600)))))),
        //          ))
        //
        //        _ <- A.i
        //          .B.?(B.i.s_?)
        //          .C.?(C.s.i).query.i.get.map(_ ==> List(
        //            (1, None),
        //            (2, Some((20, None, None))),
        //            (3, Some((30, None, Some(("300", 300))))),
        //            (4, Some((40, Some("40"), None))),
        //            (5, Some((50, Some("50"), None))),
        //            (6, Some((60, Some("60"), Some(("600", 600))))),
        //          ))
        //
        //        _ <- A.i
        //          .B.?(B.i.s_?)
        //          .C.?(C.s.i_?).query.i.get.map(_ ==> List(
        //            (1, None),
        //            (2, Some((20, None, None))),
        //            (3, Some((30, None, Some(("300", Some(300)))))),
        //            (4, Some((40, Some("40"), None))),
        //            (5, Some((50, Some("50"), Some(("500", None))))),
        //            (6, Some((60, Some("60"), Some(("600", Some(600)))))),
        //          ))


        //        _ <- A.i.B.?(B.i.s).C.i.query.get
        //          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
        //            err ==> "Only further optional refs allowed after optional ref."
        //          }
        //        _ <- A.i.B.?(B.i.s).Cc.*(C.i).query.get
        //          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
        //            err ==> "Only further optional refs allowed after optional ref."
        //          }

        //
        //
        //        // todo: allow
        //
        //        _ <- A.i.B.?(B.i).insert(1, Some(2)).transact
        //          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
        //            err ==> "Optional ref not allowed in insert molecule. Please use mandatory ref instead."
        //          }
        //
        //        _ <- A.i.B.?(B.i).insert(List(
        //            (1, Some(2)),
        //            (2, None),
        //          )).transact
        //          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
        //            err ==> "Optional ref not allowed in insert molecule. Please use mandatory ref instead."
        //          }
        //
        //        _ <- A.i.B.?(B.i.s).insert(1, Some((2, "foo"))).transact
        //          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
        //            err ==> "Optional ref not allowed in insert molecule. Please use mandatory ref instead."
        //          }
        //
        //        _ <- A.i.B.?(B.i.s).insert(List(
        //            (1, Some((2, "foo"))),
        //            (2, None),
        //          )).transact
        //          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
        //            err ==> "Optional ref not allowed in insert molecule. Please use mandatory ref instead."
        //          }


        //        _ <- rawQuery(
        //          """select count(*) from Ns
        //            |    INNER JOIN Ns_refs_Ref ON Ns.id = Ns_refs_Ref.Ns_id
        //            |""".stripMargin, true)
        //
        //        _ <- rawTransact(
        //          """UPDATE Ns
        //            |SET
        //            |  i = 7
        //            |WHERE
        //            |  Ns.i IS NOT NULL AND
        //            |  exists (
        //            |    select * from Ns
        //            |      INNER JOIN Ns_refs_Ref ON Ns.id = Ns_refs_Ref.Ns_id
        //            |  )
        //            |""".stripMargin)
      } yield ()
    }



    //    "unique" - unique { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Uniques._
    //      val triples             = getTriples.map(t => (t._3, t._1, t._2))
    //      val List(a, b, c, d, e) = triples.sortBy(p => (p._2, p._3, p._1))
    //      val query               = (c: String, l: Int) => Uniques.int.a3.s.a1.i.a2.query.from(c).limit(l)
    //      for {
    //        _ <- Uniques.int.s.i.insert(triples).transact
    //
    //      } yield ()
    //    }
    //
    //
    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //      for {
    //        id <- MandatoryAttr.name("Bob").age(42)
    //          .hobbies(Set("golf")).save.transact.map(_.id)
    //        //          .hobbies(Set("golf", "stamps")).save.transact.map(_.id)
    //
    //        _ = println("+++++++++++++++++++++")
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
    //
    //    "partitions" - partition { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Partitions._
    //      for {
    //
    //        _ <- lit_Book.title.Reviewers.name.Professions.*(gen_Profession.name)
    //          .insert("book", "Jan", List("Musician")).transact
    //
    //      } yield ()
    //    }
  }
}
