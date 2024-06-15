package molecule.sql.h2

import java.time.Instant
import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.coreTests.util.Array2List
import molecule.sql.h2.async._
import molecule.sql.h2.setup.{TestSuiteArray_h2, TestSuite_h2}
import utest._
import scala.collection.mutable
import scala.language.implicitConversions


//object AdhocJVM_h2 extends TestSuiteArray_h2 {
object AdhocJVM_h2 extends TestSuite_h2 {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {

        _ <- Ns.i_?.int.insert(
          (None, 0), // entity with missing i
          (Some(1), 1),
          (Some(2), 2),
        ).transact

        // Update all entities where `i` is not asserted (null)
        _ <- Ns.i_().int(3).update.transact

        // 1 entity updated
        _ <- Ns.i_?.a1.int.query.get.map(_ ==> List(
          (None, 3), // updated
          (Some(1), 1),
          (Some(2), 2),
        ))

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


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      val pint20 = "b" -> 20
      val pint30 = "c" -> 30
      for {


        // Will not be updated since entity has no A -> B relationship
        _ <- B.s("x").iSeq(Seq(1, 2, 3)).save.transact

        _ <- A.i.Bb.*?(B.s_?.iSeq_?).insert(
          (0, List()),
          (1, List(
            (None, None), // no B entity created
            (None, Some(Seq(0, 1, 0))),
            (Some("a"), None),
            (Some("b"), Some(Seq(1, 2, 1))),
            (Some("c"), Some(Seq(2, 3, 2))),
            (Some("d"), Some(Seq(3, 4, 3))),
          ))
        ).transact

        // Filter by B attribute, update B values
        _ <- A.Bb.s_.iSeq.remove(3, 4).update.transact

        _ <- A.i.a1.Bb.*?(B.s_?.a1.iSeq_?).query.get.map(_ ==> List(
          (0, List()), //                       no B entities to update
          (1, List(
            // (None, None),                    no B entity created
            (None, Some(Seq(0, 1, 0))), //      no change without filter match
            (Some("a"), None), //               no values to remove
            (Some("b"), Some(Seq(1, 2, 1))), // no matching values removed
            (Some("c"), Some(Seq(2, 2))), //    1 value removed
            (Some("d"), None), //               all 3 values removed
          ))
        ))

        _ <- B.s_?.a1.iSeq.query.get.map(_ ==> List(
          (None, Seq(0, 1, 0)),
          (Some("b"), Seq(1, 2, 1)),
          (Some("c"), Seq(2, 2)),
          (Some("x"), Seq(1, 2, 3)), // not update without relationship from A
        ))


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
    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //      for {
    //        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)
    //
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
