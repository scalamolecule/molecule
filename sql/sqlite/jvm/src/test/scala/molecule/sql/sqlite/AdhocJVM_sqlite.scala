package molecule.sql.sqlite

import java.net.URI
import java.time.{Duration, Instant, LocalDate, LocalDateTime, LocalTime, OffsetDateTime, OffsetTime, ZonedDateTime}
import java.util.{Date, UUID}
import molecule.base.error.{ExecutionError, ModelError}
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs.{A, B}
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.coreTests.util.Array2List
import molecule.sql.sqlite.async._
import molecule.sql.sqlite.setup.{TestSuiteArray_sqlite, TestSuite_sqlite}
import utest._
import scala.collection.mutable
import scala.concurrent.Future
import scala.language.implicitConversions


//object AdhocJVM_sqlite extends TestSuiteArray_sqlite {
object AdhocJVM_sqlite extends TestSuite_sqlite {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)


      val (a1, b2) = ("a" -> offsetDateTime1, "b" -> offsetDateTime2)
      val (b3, c4) = ("b" -> offsetDateTime3, "c" -> offsetDateTime4)
      for {
        _ <- Ns.i.offsetDateTimeMap.insert(List(
          (1, Map(a1, b2)),
          (2, Map(b3, c4)),
        )).transact


        _ <- rawQuery(
          """SELECT DISTINCT
            |  Ns.i,
            |  Ns.offsetDateTimeMap,
            |  Ns.offsetDateTimeMap REGEXP '2001-01-01T01:01:01.000000001\\+01:00',
            |  Ns.offsetDateTimeMap REGEXP '2001-01-01T01:01:01.000000001\+01:00'
            |FROM Ns
            |WHERE
            |  Ns.i IS NOT NULL
            |ORDER BY Ns.i;
            |""".stripMargin, true)

        _ <- rawQuery(
          """SELECT DISTINCT
            |  Ns.i
            |FROM Ns
            |WHERE
            |  Ns.offsetDateTimeMap REGEXP '2001-01-01T01:01:01.000000001\\+?01:00' = 1 AND
            |  Ns.i IS NOT NULL
            |ORDER BY Ns.i;
            |""".stripMargin, true)

        // "Map contains this OR that value"
        //        _ <- Ns.i.a1.offsetDateTimeMap_.has(offsetDateTime0).query.get.map(_ ==> Nil)
        _ <- Ns.i.a1.offsetDateTimeMap_.has(offsetDateTime1).query.i.get.map(_ ==> List(1))
//        _ <- Ns.i.a1.offsetDateTimeMap_.has(offsetDateTime2).query.get.map(_ ==> List(1))


      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {


        _ <- A.i.iSet.B.iSet.i.insert(
          (1, Set(1, 2), Set(1, 2, 3), 3),
          (2, Set(2, 3), Set(2, 3), 3),
          (2, Set(4), Set(4), 4),
          (2, Set(4), Set(3), 4),
        ).transact

        _ <- A.i.iSet_.hasNo(B.i_).B.iSet.i.a1.query.get.map(_ ==> List(
          (1, Set(1, 2, 3), 3),
        ))
        _ <- A.i.iSet.hasNo(B.i_).B.iSet_.i.a1.query.get.map(_ ==> List(
          (1, Set(1, 2), 3),
        ))


        //            |  B.iSet
        _ <- rawQuery(
          """SELECT DISTINCT
            |  A.i,
            |  JSON_GROUP_ARRAY(_B_iSet.value) as B_iSet
            |FROM A
            |  INNER JOIN B ON A.b = B.id
            |  inner join JSON_EACH(B.iSet) as _B_iSet
            |WHERE
            |  NOT EXISTS (
            |    SELECT *
            |    FROM JSON_EACH(B.iSet)
            |    WHERE JSON_EACH.VALUE = A.i
            |  ) AND
            |  A.i    IS NOT NULL AND
            |  A.iSet IS NOT NULL AND
            |  B.iSet IS NOT NULL
            |group by A.i
            |ORDER BY A.i
            |""".stripMargin, true)

        _ <- A.i.a1.iSet_.B.iSet.hasNo(A.i_).query.i.get.map(_ ==> List(
          (2, Set(3, 4)),
        ))
        _ <- A.i.a1.iSet.B.iSet_.hasNo(A.i_).query.get.map(_ ==> List(
          (2, Set(4)),
        ))







        //        _ <- rawQuery(
        //          """SELECT DISTINCT
        //            |  i,
        //            |  json_group_array(iSet)
        //            |FROM A
        //            |where iSet is not null
        //            |group by i
        //            |""".stripMargin, true)


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
    //      //          val triples             = getTriples.map(t => (t._3, t._1, t._2))
    //      //          val List(a, b, c, d, e) = triples.sortBy(p => (p._2, p._3, p._1))
    //      //          val query               = (c: String, l: Int) => Uniques.int.a3.s.a1.i.a2.query.from(c).limit(l)
    //      for {
    //
    //
    //        _ <- Uniques.i.float.insert((1, float1), (2, float2)).transact
    //        //        _ <- Uniques.i.float.insert((1, float1)).transact
    //        //        _ <- Uniques.i.double.insert((1, double1), (2, double2)).transact
    //        //            _ <- Uniques.i.float.insert((2, float2)).transact
    //
    //        _ <- Uniques.i.float.query.get.map(_ ==> List(
    //          (1, 1.1f),
    //          (2, 2.2f),
    //        ))
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
