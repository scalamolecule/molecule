package molecule.sql.h2

import java.time.Instant
import molecule.base.error.{ExecutionError, ModelError}
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs.{A, B}
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
  List(1).zip(List(2))
  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {

        //        _ <- rawTransact(
        //          """UPDATE Ns
        //            |  SET
        //            |    intSet = ?INT
        //            |  WHERE
        //            |    Ns.id = 1 AND
        //            |    Ns.intSet IS NOT NULL
        //            |""".stripMargin)

        id <- Ns.i(42).save.transact.map(_.id)

        // Map attribute not yet asserted
        _ <- Ns.intMap.query.get.map(_ ==> Nil)

        // When attribute is not already asserted, an update has no effect
        _ <- Ns(id).intMap(Map(pint1, pint2)).update.i.transact
        _ <- Ns.intMap.query.get.map(_ ==> Nil)

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


        List(b1, b2) <- B.s.insert("b10", "b20").transact.map(_.ids)

        List(a1, a2) <- A.i.b.insert(
          (10, b1),
          (20, b2),
        ).transact.map(_.ids)

        _ <- A.i.B.s.query.get.map(_ ==> List(
          (10, "b10"),
          (20, "b20"),
        ))

        _ <- A(a1).i(11).B.s("b11").query.inspect

        _ <- A(a1).i(11).B.s("b11").update.i.transact
//        _ <- A.i.B.s.query.get.map(_ ==> List(
//          (11, "b11"),
//          (20, "b20"),
//        ))
//
//        _ <- A.i(21).B.id(b2).s("b21").update.i.transact
//        _ <- A.i.B.s.query.get.map(_ ==> List(
//          (11, "b11"),
//          (21, "b21"),
//        ))



//        a <- A.i(1).save.transact.map(_.id)
//        b <- A.i(2).B.s("b").save.transact.map(_.id)
//        c <- A.i(3).B.s("c").i(3).save.transact.map(_.id)
//
//        // Current entity with A value and ref to B value
//        _ <- A.i.a1.B.i.query.get.map(_ ==> List(
//          (3, 3)
//        ))
//
//        // Filter by A ids, update existing B values
//        _ <- A(a, b, c).B.i(4).update.i.transact
        /*
        ========================================
        UPDATE:
        AttrOneTacID("A", "id", Eq, Seq(1L, 2L, 3L), None, None, Nil, Nil, None, None, Seq(0, 0))
        Ref("A", "b", "B", CardOne, false, Seq(0, 8, 1))
        AttrOneManInt("B", "i", Eq, Seq(4), None, None, Nil, Nil, None, None, Seq(1, 24))

        REF IDS MODEL ----------------
        AttrOneTacID("A", "id", Eq, Seq(1L, 2L, 3L), None, None, Nil, Nil, None, None, Seq(0, 0))
        Ref("A", "b", "B", CardOne, false, Seq(0, 8, 1))
        AttrOneTacInt("B", "i", V, Seq(), None, None, Nil, Nil, None, None, Seq(1, 24))
        AttrOneManID("B", "id", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 0))

        SELECT DISTINCT
          B.id
        FROM A
          INNER JOIN B ON
            A.b = B.id
        WHERE
          A.id IN (1, 2, 3) AND
          B.i  IS NOT NULL AND
          B.id IS NOT NULL;

        UPDATES ----------------------
        AttrOneTacID("A", "id", Eq, Seq(1L, 2L, 3L), None, None, Nil, Nil, None, None, Seq(0, 0))
        ------------
        AttrOneTacID("B", "id", Eq, Seq(42L), None, None, Nil, Nil, None, None, Seq(0, 0))
        AttrOneManInt("B", "i", Eq, Seq(4), None, None, Nil, Nil, None, None, Seq(1, 24))
        UPDATE B
        SET
          i = ?
        WHERE
          i IS NOT NULL AND
          B.id IN(42)
        ----------------------------------------
         */
//        _ <- A.i.a1.B.i.query.get.map(_ ==> List(
//          (3, 4) // B value updated since there was a previous value
//        ))
//
//        // Filter by A ids, upsert B values (insert if not already present)
//        _ <- A(a, b, c).B.i(5).upsert.transact
//
//        // Now three A entities with referenced B value
//        _ <- A.i.a1.B.i.query.get.map(_ ==> List(
//          (1, 5), // relationship to B created + B value inserted
//          (2, 5), // B value inserted
//          (3, 5), // B value updated
//        ))

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
