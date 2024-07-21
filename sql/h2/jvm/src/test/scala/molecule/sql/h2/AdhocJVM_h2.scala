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

        List(r1, r2) <- Ref.i.insert(1, 2).i.transact.map(_.ids)
        _ <- Ns.refs(Set(r1, r2)).save.i.transact
        ////        _ <- Ns.refs(Set(1, 2)).save.i.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(r1, r2))

        //        List(r1, r2) <- Ref.i.insert(1, 2).transact.map(_.ids)
        //        _ <- Ns.int.i.refs_?.insert(23, 1, Option.empty[Set[Long]]).transact
        //        _ <- Ns.int.i.refs_?.insert(23, 2, Some(Set.empty[Long])).transact
        //        _ <- Ns.int.i.refs_?.insert(23, 3, Some(Set(r1, r2))).transact
        //        _ <- Ns.int_(23).i.a1.refs_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(r1, r2)))))


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

        //        _ <- A.i.Bb.*(B.i).insert(
        //          (1, List(1, 2)),
        //          (2, List(3, 4)),
        //        ).i.transact.map(_.ids)
        //
        //        _ <- A.i.a1.Bb.*?(B.i.a1).query.get.map(_ ==> List(
        //          (1, List(1, 2)),
        //          (2, List(3, 4)),
        //        ))
        //
        //
        //        _ <- A.i.Bb.*(B.i.s).insert(
        //          (1, List((1, "a"), (2, "b"))),
        //          (2, List((3, "c"), (4, "d"))),
        //        ).i.transact.map(_.ids)
        //
        //        _ <- A.i.a1.Bb.*?(B.i.a1.s).query.get.map(_ ==> List(
        //          (1, List((1, "a"), (2, "b"))),
        //          (2, List((3, "c"), (4, "d"))),
        //        ))


        //        _ <- A.i.Bb.*(B.i.C.s).insert(
        //          (1, List((1, "a"), (2, "b"))),
        //          (2, List((3, "c"), (4, "d"))),
        //        ).i.transact.map(_.ids)
        //
        //        _ <- A.i.a1.Bb.*?(B.i.a1.C.s).query.get.map(_ ==> List(
        //          (1, List((1, "a"), (2, "b"))),
        //          (2, List((3, "c"), (4, "d"))),
        //        ))


        //        _ <- A.i.Cc.*(C.i.D.i).insert(
        //          (1, List((1, 2), (3, 4))),
        //        ).i.transact

//        _ <- A.i.Cc.*(C.i).insert(
//          (1, List(1, 2)),
//        ).i.transact
//
//
//        _ <- A.i.B.i.Cc.*(C.i).insert(
//          (1, 10, List(1, 2)),
//          //          (1, 10, List((1, 2), (3, 4))),
//          //          (2, 20, Nil),
//        ).i.transact.map(_.ids)

        //        _ <- A.i.B.i.Cc.*(C.i.D.i).insert(
        //          (1, 10, List((1, 2))),
        //          //          (1, 10, List((1, 2), (3, 4))),
        //          //          (2, 20, Nil),
        //        ).i.transact.map(_.ids)


//                List(a1, a2) <- A.i.B.i.Cc.*(C.i.D.i).insert(
//                  (1, 10, List((1, 2), (3, 4))),
//                  (2, 20, Nil),
//                ).i.transact.map(_.ids)
//
//                _ <- A.id(a1, a2).i.a1.B.i.Cc.*?(C.i.a1.D.i).query.get.map(_ ==> List(
//                  (a1, 1, 10, List((1, 2), (3, 4))),
//                  (a2, 2, 20, Nil),
//                ))

                e1 <- A.i.Bb.*(B.i).insert(
                  (1, Seq(10, 11)),
                  (2, Seq(20, 21))
                ).transact.map(_.id)

                // 2 entities, each with 2 owned sub-entities
                _ <- A.i.a1.Bb.*(B.i.a1).query.get.map(_ ==> List(
                  (1, Seq(10, 11)),
                  (2, Seq(20, 21))
                ))


//        _ <- A.i.B.i.C.i.insert(1, 2, 3).i.transact
//        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((1, 2, 3)))


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
