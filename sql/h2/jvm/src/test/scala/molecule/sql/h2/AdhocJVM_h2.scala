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


        _ <- A.B.Cc.i.insert(1).transact

//        _ <- A.s.Bb.*(B.iSeq).insert(List(("a", List(Seq(3))))).transact
//
//        _ <- rawQuery(
//          """SELECT DISTINCT
//            |  A.id,
//            |  A.s,
//            |  B.iSeq
//            |FROM A
//            |  INNER JOIN A_bb_B ON
//            |    A.id = A_bb_B.A_id
//            |  INNER JOIN B ON
//            |    A_bb_B.B_id = B.id
//            |WHERE
//            |  A.s    IS NOT NULL AND
//            |  B.iSeq IS NOT NULL;
//            |""".stripMargin, true)
//        _ <- A.s.Bb.*(B.iSeq).query.get.map(_ ==> List(("a", List(Seq(3)))))

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
