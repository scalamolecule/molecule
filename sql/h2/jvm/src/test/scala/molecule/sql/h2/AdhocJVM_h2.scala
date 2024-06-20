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

        List(
        ref1,
        ref2,
        ref3,
        ) <- Ref.i.insert(1, 2, 3).transact.map(_.ids)

        _ <- Ns.i.ref.insert(List(
          (1, ref1),
          (2, ref2),
          (2, ref2),
          (2, ref3),
        )).transact

        _ <- Ns.i.ref.a1.query.get.map(_ ==> List(
          (1, ref1),
          (2, ref2), // 2 rows coalesced
          (2, ref3),
        ))

        // Distinct values are returned in a Set
        _ <- Ns.i.a1.ref(distinct).query.get.map(_ ==> List(
          (1, Set(ref1)),
          (2, Set(ref2, ref3)),
        ))

        _ <- Ns.ref(distinct).query.get.map(_.head ==> Set(
          ref1, ref2, ref3
        ))

        //        ref1 <- Ref.i(1).save.transact.map(_.id)
        //        _ <- Ns.i(1).ref(ref1).save.transact

        //        List(
        //        ref1,
        //        ref2,
        //        ref3,
        //        ) <- Ref.i.insert(1, 2, 3).transact.map(_.ids)
        //
        //        _ <- Ns.i.ref.insert(List(
        //          (1, ref1),
        //          (2, ref2),
        //          (2, ref2),
        //          (2, ref3),
        //        )).transact
        //
        //        _ <- Ns.i.ref.a1.query.get.map(_ ==> List(
        //          (1, ref1),
        //          (2, ref2), // 2 rows coalesced
        //          (2, ref3),
        //        ))
        //
        //        // Distinct values are returned in a Set
        //        _ <- Ns.i.a1.ref(distinct).query.get.map(_ ==> List(
        //          (1, Set(ref1)),
        //          (2, Set(ref2, ref3)),
        //        ))
        //
        //        _ <- Ns.ref(distinct).query.get.map(_.head ==> Set(
        //          ref1, ref2, ref3
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


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      val pint20 = "b" -> 20
      val pint30 = "c" -> 30
      for {


        e1 <- A.i.Bb.*(B.i).insert(
          (1, Seq(10, 11)),
          (2, Seq(20, 21))
        ).transact.map(_.id)

        //        // 2 entities, each with 2 owned sub-entities
        //        _ <- A.i.a1.Bb.*(B.i.a1).query.get.map(_ ==> List(
        //          (1, Seq(10, 11)),
        //          (2, Seq(20, 21))
        //        ))
        //
        //        // 4 referenced entities
        //        _ <- B.i.a1.query.get.map(_ ==> List(10, 11, 20, 21))
        //
        //        _ <- if (platform == "Jdbc jvm") {
        //          // 4 join rows from A to B
        //          rawQuery("SELECT * FROM A_bb_B").map(_ ==> List(
        //            List(1, 1),
        //            List(1, 2),
        //            List(2, 3),
        //            List(2, 4),
        //          ))
        //        } else Future.unit

        //
        //        _ <- rawTransact(
        //          """DELETE FROM A_bb_B WHERE A_id IN (1)
        //            |""".stripMargin)
        //
        //        _ <- rawTransact(
        //          """DELETE FROM A WHERE A.id IN (1)
        //            |""".stripMargin)

        // Delete first A entity and implicitly its joins to B
        _ <- A(e1).delete.transact

        // 1 entity with 2 referenced entities left
        _ <- A.i.Bb.*(B.i.a1).query.get.map(_ ==> List(
          (2, Seq(20, 21))
        ))
        //
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
