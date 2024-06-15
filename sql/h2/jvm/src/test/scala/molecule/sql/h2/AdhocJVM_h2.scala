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

        //        id <- Ns.i(42).save.transact.map(_.id)

        //        // Attribute not yet asserted
        //        _ <- Ns.int.query.get.map(_ ==> Nil)
        //
        //        // When attribute is not already asserted, an update has no effect
        //        _ <- Ns(id).int(int1).update.transact
        //        _ <- Ns.int.query.get.map(_ ==> Nil)
        //
        //        // To insert the attribute value if not already asserted, use `upsert`
        //        _ <- Ns(id).int(int1).upsert.transact
        //        // Now the attribute value was inserted
        //        _ <- Ns.int.query.get.map(_.head ==> int1)
        //
        //        // Update value to current value doesn't change anything
        //        _ <- Ns(id).int(int1).update.transact
        //        _ <- Ns.int.query.get.map(_.head ==> int1)
        //
        //        // Update existing value
        //        _ <- Ns(id).int(int2).update.transact
        //        _ <- Ns.int.query.get.map(_.head ==> int2)
        //
        //        // Upsert existing value - same effect as update
        //        _ <- Ns(id).int(int3).upsert.transact
        //        _ <- Ns.int.query.get.map(_.head ==> int3)

        id <- Ns.i(42).s("x").int(3).save.transact.map(_.id)
        // OBS: all attributes have to be asserted for any value to be updated!
        _ <- Ns(id).i(7).int(int4).update.transact
        _ <- Ns(id).s("foo").int(int4).update.transact
        // Nothing is updated
        _ <- Ns.s_?.int.query.get.map(_.head ==> (None, int3))

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
      for {


        //        _ <- rawTransact(
        //          """UPDATE B
        //            |SET
        //            |  iMap = json_merge_patch(iMap, json_object("b", 20, "c", 3, "d", 4))
        //            |WHERE
        //            |  B.iMap is not null and
        //            |  B.id IN(1, 2, 3)
        //            |""".stripMargin)

        _ <- A.i.a1.Bb.*?(B.s_?.iSeq_?).insert(
          //          (1, List()),
          //          (2, List((Some("a"), None))),
          //          (3, List((Some("b"), None), (Some("c"), None))),
          //                    (4, List((Some("d"), Some(Seq(1, 2))))),
          //          (5, List((Some("e"), Some(Seq(2, 3))), (Some("f"), Some(Seq(3, 4))))),
          (6, List((Some("g"), Some(Seq(4, 5))), (Some("h"), None))),
        ).transact.map(_.ids)

        // Filter by A ids, update B values
        //        _ <- A.i_.Bb.iSeq(Seq(4, 5)).update.transact

        //        _ <- A.i.a1.Bb.*?(B.s_?.iSet).query.i.get

//
//        _ <- rawQuery(
//          """SELECT DISTINCT
//            |  A.id,
//            |  A.i,
//            |  B.s,
//            |  B.iSeq
//            |FROM A
//            |  LEFT JOIN A_bb_B ON A.id        = A_bb_B.A_id
//            |  LEFT JOIN B      ON A_bb_B.B_id = B.id
//            |WHERE
//            |  A.i IS NOT NULL
//            |GROUP BY A.id, A.i, B.s, B.iSeq
//            |ORDER BY A.i;
//            |""".stripMargin, true)

        _ <- A.i.a1.Bb.*?(B.s_?.iSeq).query.i.get.map(_ ==> List(
          //          (1, List()), //                                               no B.i value
          //          (2, List()), //                                               no B.i value
          //          (3, List()), //                                               no B.i value
          //          (4, List((Some("d"), Seq(4, 5)))), //                         update in 1 ref entity
          //          (5, List((Some("e"), Seq(4, 5)), (Some("f"), Seq(4, 5)))), // update in 2 ref entities
          (6, List((Some("g"), Seq(4, 5)))), //                         already had same value
        ))

        //        // Filter by A ids, upsert B values
        //        _ <- A.i_.Bb.iSeq(Seq(5, 6)).upsert.transact
        //
        //        _ <- A.i.a1.Bb.*?(B.s_?.iSeq).query.get.map(_ ==> List(
        //          (1, List((None, Seq(5, 6)))), //                              ref + addition
        //          (2, List((Some("a"), Seq(5, 6)))), //                         addition in 1 ref entity
        //          (3, List((Some("b"), Seq(5, 6)), (Some("c"), Seq(5, 6)))), // addition in 2 ref entities
        //          (4, List((Some("d"), Seq(5, 6)))), //                         update in 1 ref entity
        //          (5, List((Some("e"), Seq(5, 6)), (Some("f"), Seq(5, 6)))), // update in 2 ref entities
        //          (6, List((Some("g"), Seq(5, 6)), (Some("h"), Seq(5, 6)))), // update in one ref entity and addition in another
        //        ))

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
