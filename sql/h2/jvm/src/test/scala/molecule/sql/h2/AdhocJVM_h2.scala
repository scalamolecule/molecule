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


        List(r1, r2) <- Ref.i.insert(1, 2).transact.map(_.ids)
        _ <- Ns.refs(Set(r1, r2)).save.i.transact

        _ <- rawQuery(
          """select id from Ns
            |""".stripMargin, true)

        _ <- Ns.refs.query.get.map(_.head ==> Set(r1, r2))

        //        _ <- rawQuery(
        //          """SELECT DISTINCT
        //            |  Ns.i,
        //            |  Ns.string
        //            |FROM Ns
        //            |WHERE
        //            |  Ns.i IS NOT NULL
        //            |ORDER BY Ns.string;
        //            |""".stripMargin, true)

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

        e1 <- A.i.OwnB.i.insert(
          (1, 10),
          (2, 20)
        ).transact.map(_.id)

        // 2 entities, each with an owned sub-entity
        _ <- A.i.a1.OwnB.i.query.get.map(_ ==> List(
          (1, 10),
          (2, 20)
        ))

        // 2 sub-entities
        _ <- B.i.a1.query.get.map(_ ==> List(10, 20))

        _ <- A(e1).delete.transact

        // 1 entity with 1 owned sub-entity left
        _ <- A.i.OwnB.i.query.get.map(_ ==> List((2, 20)))

        // 2 sub-entities
        _ <- B.i.query.get.map(_ ==> List(20))

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
