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


        _ <- A.i.Bb.*(B.i).insert(
          (1, Nil),
          (2, List(20))
        ).i.transact


        _ <- A.i.B.?(B.i).insert(
          (1, None),
          (2, Some(20))
        ).i.transact

        //        _ <- rawQuery(
        //          """SELECT DISTINCT
        //            |  A.i,
        //            |  B.i
        //            |FROM A
        //            |  LEFT JOIN B ON
        //            |    A.b = B.id
        //            |WHERE
        //            |  A.i IS NOT NULL;
        //            |""".stripMargin, true)
        //
        //        _ <- rawQuery(
        //          """SELECT count(*) from B
        //            |""".stripMargin, true)


        _ <- A.i.B.?(B.i).query.get.map(_ ==> List(
          (1, None),
          (2, Some(20)),
        ))





        //        _ <- A.i.B.?(B.i.C.i).insert(
        //          (0, Nil),
        //          (1, List((1, 2))),
        //          (2, List((3, 4), (5, 6))),
        //        ).transact
        //
        //        _ <- A.i.a1.B.?(B.i.a1.C.i).query.get.map(_ ==> List(
        //          (0, Nil),
        //          (1, List((1, 2))),
        //          (2, List((3, 4), (5, 6))),
        //        ))
        //        _ <- A.i.a1.B.?(B.i.a1.C.i).query.get.map(_ ==> List(
        //          (1, List((1, 2))),
        //          (2, List((3, 4), (5, 6))),
        //        ))
        //
        //        _ <- A.i.a1.B.?(B.C.i.a1).query.get.map(_ ==> List(
        //          (0, Nil),
        //          (1, List(2)),
        //          (2, List(4, 6)),
        //        ))
        //        _ <- A.i.a1.B.?(B.C.i.a1).query.get.map(_ ==> List(
        //          (1, List(2)),
        //          (2, List(4, 6)),
        //        ))
        //
        //        _ <- A.B.?(B.C.i.a1).query.get.map(_ ==> List(
        //          // List(), // empty list is not returned when no attributes are present before nesting
        //          List(2),
        //          List(4, 6),
        //        ))
        //        _ <- A.B.?(B.C.i.a1).query.get.map(_ ==> List(
        //          List(2),
        //          List(4, 6),
        //        ))

        //        _ <- A.s.Bb.*(B.iSet).insert(List(("a", List(Set(3))))).transact
        //        _ <- rawQuery(
        //          """SELECT DISTINCT
        //            |  A.id,
        //            |  A.s,
        //            |  ARRAY_AGG(B.iSet)
        //            |FROM A
        //            |  INNER JOIN A_bb_B ON
        //            |    A.id = A_bb_B.A_id
        //            |  INNER JOIN B ON
        //            |    A_bb_B.B_id = B.id
        //            |WHERE
        //            |  A.s    IS NOT NULL AND
        //            |  B.iSet IS NOT NULL
        //            |GROUP BY A.s, A.id
        //            |HAVING COUNT(*) > 0;
        //            |""".stripMargin, true)
        //        _ <- A.s.Bb.*(B.iSet).query.get.map(_ ==> List(("a", List(Set(3)))))

        //        _ <- A.i.Bb.*(B.iSet).insert(List((2, List(Set(3, 4))))).transact
        //        _ <- A.i.Bb.*(B.iSet).query.get.map(_ ==> List((2, List(Set(3, 4)))))

        //        // Referenced entities are not deleted
        //        _ <- B.i.a1.query.get.map(_ ==> List(10, 11, 20, 21))
        //
        //        _ <- if (platform == "Jdbc jvm") {
        //          // Join rows deleted
        //          rawQuery("SELECT * FROM A_bb_B").map(_ ==> List(
        //            // List(1, 1),
        //            // List(1, 2),
        //            List(2, 3),
        //            List(2, 4),
        //          ))
        //        } else Future.unit


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
