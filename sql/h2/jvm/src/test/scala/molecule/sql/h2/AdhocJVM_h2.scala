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

        List(a, b) <- Ns.int.insert(1, 2).transact.map(_.ids)
        _ <- Ns.int(3).save.transact
        _ <- Ns.int.a1.query.get.map(_ ==> List(1, 2, 3))
        _ <- Ns(a).int(10).update.transact
        _ <- Ns(b).delete.transact
        _ <- Ns.int.a1.query.get.map(_ ==> List(3, 10))

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

        a <- A.i(1).save.transact.map(_.id)
        b <- A.i(2).B.s("b").save.transact.map(_.id)
        c <- A.i(3).B.s("c").i(3).save.transact.map(_.id)

        //        // Current entity with A value and ref to B value
        //        _ <- A.i.a1.B.i.query.get.map(_ ==> List(
        //          (3, 3)
        //        ))
        //
        //        // Filter by A ids, update existing B values
        //        _ <- A(a, b, c).B.i(4).update.transact
        //
        //        _ <- A.i.a1.B.i.query.get.map(_ ==> List(
        //          (3, 4) // B value updated since there was a previous value
        //        ))

        // Filter by A ids, upsert B values (insert if not already present)
        _ <- A(a, b, c).B.i(5).upsert.i.transact

        // Now three A entities with referenced B value
        _ <- A.i.a1.B.i.query.get.map(_ ==> List(
          (1, 5), // relationship to B created + B value inserted
          (2, 5), // B value inserted
          (3, 5), // B value updated
        ))


        //        _ <- rawQuery(
        //          """select count(*) from Ns
        //            |    INNER JOIN Ns_refs_Ref ON Ns.id = Ns_refs_Ref.Ns_id
        //            |""".stripMargin, true)
        //
        //        _ <- rawTransact(
        //          """UPDATE B
        //            |SET
        //            |  i = ?
        //            |WHERE
        //            |  i IS NOT NULL AND
        //            |  B.id IN(42)
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
