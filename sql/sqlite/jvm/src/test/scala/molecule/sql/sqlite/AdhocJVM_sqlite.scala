package molecule.sql.sqlite

import java.net.URI
import java.time.{Duration, Instant, LocalDate, LocalDateTime, LocalTime, OffsetDateTime, OffsetTime, ZonedDateTime}
import java.util.{Date, UUID}
import molecule.base.error.{ExecutionError, ModelError}
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs.A
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.coreTests.util.Array2List
import molecule.sql.sqlite.async._
import molecule.sql.sqlite.setup.{TestSuiteArray_sqlite, TestSuite_sqlite}
import utest._
import scala.collection.mutable
import scala.language.implicitConversions


//object AdhocJVM_sqlite extends TestSuiteArray_sqlite {
object AdhocJVM_sqlite extends TestSuite_sqlite {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

      for {

        _ <- Ns.i(1).string_?(Some(string1)).save.transact

        _ <- Ns.i(1).string_?(Option.empty[String]).save.transact


//        _ <- rawQuery(
//          """SELECT DISTINCT
//            |  Ns.i,
//            |  Ns.string
//            |FROM Ns
//            |WHERE
//            |  Ns.i IS NOT NULL
//            |ORDER BY Ns.string;
//            |""".stripMargin, true)

        _ <- Ns.i_.string_?.a1.query.i.get.map(_ ==> List(None, Some(string1)))


      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {


        _ <- A.i.B.i.insert(
          (1, 1),
          (1, 2),
          (1, 3),
          (2, 4),
          (2, 5),
          (2, 6),
          (2, 6), // (make sure grouped values coalesce)
        ).transact

        _ <- A.B.i(min(2)).query.get.map(_ ==> List(Set(1, 2)))
        _ <- A.B.i(max(2)).query.get.map(_ ==> List(Set(5, 6)))
        _ <- A.B.i(min(2)).i(max(2)).query.get.map(_ ==> List((Set(1, 2), Set(5, 6))))

        _ <- rawQuery(
          """SELECT DISTINCT
            |  A.i,
            |  (
            |    SELECT JSON_GROUP_ARRAY(i)
            |    FROM (
            |      SELECT distinct _B.i
            |      FROM A as _A
            |      INNER JOIN B as _B ON _A.b = _B.id
            |      WHERE _A.i = A.i
            |      ORDER BY _B.i ASC
            |      LIMIT 2
            |    )
            |  ) as i_min
            |FROM A
            |  INNER JOIN B ON A.b = B.id
            |WHERE
            |  A.i IS NOT NULL AND
            |  B.i IS NOT NULL
            |GROUP BY A.i
            |ORDER BY A.i;
            |""".stripMargin, true)

        _ <- A.i.a1.B.i(min(2)).query.i.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(4, 5))
        ))

        _ <- A.i.a1.B.i(max(2)).query.get.map(_ ==> List(
          (1, Set(2, 3)),
          (2, Set(5, 6))
        ))

        _ <- A.i.a1.B.i(min(2)).i(max(2)).query.get.map(_ ==> List(
          (1, Set(1, 2), Set(2, 3)),
          (2, Set(4, 5), Set(5, 6))
        ))


        //        _ <- A.i.a1.B.i(min(2)).query.i.get.map(_ ==> List(
        //          (1, Set(1, 2)),
        //          (2, Set(4, 5))
        //        ))
        //
        ////        _ <- A.i.a1.B.i(max(2)).query.get.map(_ ==> List(
        ////          (1, Set(2, 3)),
        ////          (2, Set(5, 6))
        ////        ))
        ////
        ////        _ <- A.i.a1.B.i(min(2)).i(max(2)).query.get.map(_ ==> List(
        ////          (1, Set(1, 2), Set(2, 3)),
        ////          (2, Set(4, 5), Set(5, 6))
        ////        ))
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
