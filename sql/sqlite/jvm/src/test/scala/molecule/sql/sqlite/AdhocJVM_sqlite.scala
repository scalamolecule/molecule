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

        _ <- Ns.bigIntSet(Set(bigInt1, bigInt2)).save.transact
        _ <- Ns.bigIntSet.query.get.map(_.head ==> Set(bigInt1, bigInt2))

//        _ <- Ns.bigDecimalSet(Set(bigDecimal1, bigDecimal2)).save.transact
//        _ <- Ns.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2))

        _ <- Ns.i.bigIntSet.insert(1, Set(bigInt1, bigInt2)).transact
        _ <- Ns.i.bigIntSet.query.get.map(_ ==> List((1, Set(bigInt1, bigInt2))))


//        _ <- Ns.i.stringSet.insert(1, Set(string1, string2)).transact
//        _ <- Ns.i.intSet.insert(1, Set(int1, int2)).transact
//        _ <- Ns.i.longSet.insert(1, Set(long1, long2)).transact
//        _ <- Ns.i.floatSet.insert(1, Set(float1, float2)).transact
//        _ <- Ns.i.doubleSet.insert(1, Set(double1, double2)).transact
//        _ <- Ns.i.booleanSet.insert(1, Set(boolean0)).transact
//        _ <- Ns.i.bigDecimalSet.insert(1, Set(bigDecimal1, bigDecimal2)).transact
//        _ <- Ns.i.dateSet.insert(1, Set(date1, date2)).transact
//        _ <- Ns.i.durationSet.insert(1, Set(duration1, duration2)).transact
//        _ <- Ns.i.instantSet.insert(1, Set(instant1, instant2)).transact
//        _ <- Ns.i.localDateSet.insert(1, Set(localDate1, localDate2)).transact
//        _ <- Ns.i.localTimeSet.insert(1, Set(localTime1, localTime2)).transact
//        _ <- Ns.i.localDateTimeSet.insert(1, Set(localDateTime1, localDateTime2)).transact
//        _ <- Ns.i.offsetTimeSet.insert(1, Set(offsetTime1, offsetTime2)).transact
//        _ <- Ns.i.offsetDateTimeSet.insert(1, Set(offsetDateTime1, offsetDateTime2)).transact
//        _ <- Ns.i.zonedDateTimeSet.insert(1, Set(zonedDateTime1, zonedDateTime2)).transact
//        _ <- Ns.i.uuidSet.insert(1, Set(uuid1, uuid2)).transact
//        _ <- Ns.i.uriSet.insert(1, Set(uri1, uri2)).transact
//        _ <- Ns.i.byteSet.insert(1, Set(byte1, byte2)).transact
//        _ <- Ns.i.shortSet.insert(1, Set(short1, short2)).transact
//        _ <- Ns.i.charSet.insert(1, Set(char1, char2)).transact
//
//        _ <- Ns.i.stringSet.query.get.map(_ ==> List((1, Set(string1, string2))))
//        _ <- Ns.i.intSet.query.get.map(_ ==> List((1, Set(int1, int2))))
//        _ <- Ns.i.longSet.query.get.map(_ ==> List((1, Set(long1, long2))))
//        _ <- Ns.i.floatSet.query.get.map(_ ==> List((1, Set(float1, float2))))
//        _ <- Ns.i.doubleSet.query.get.map(_ ==> List((1, Set(double1, double2))))
//        _ <- Ns.i.booleanSet.query.get.map(_ ==> List((1, Set(boolean0))))
//        _ <- Ns.i.bigDecimalSet.query.get.map(_ ==> List((1, Set(bigDecimal1, bigDecimal2))))
//        _ <- Ns.i.dateSet.query.get.map(_ ==> List((1, Set(date1, date2))))
//        _ <- Ns.i.durationSet.query.get.map(_ ==> List((1, Set(duration1, duration2))))
//        _ <- Ns.i.instantSet.query.get.map(_ ==> List((1, Set(instant1, instant2))))
//        _ <- Ns.i.localDateSet.query.get.map(_ ==> List((1, Set(localDate1, localDate2))))
//        _ <- Ns.i.localTimeSet.query.get.map(_ ==> List((1, Set(localTime1, localTime2))))
//        _ <- Ns.i.localDateTimeSet.query.get.map(_ ==> List((1, Set(localDateTime1, localDateTime2))))
//        _ <- Ns.i.offsetTimeSet.query.get.map(_ ==> List((1, Set(offsetTime1, offsetTime2))))
//        _ <- Ns.i.offsetDateTimeSet.query.get.map(_ ==> List((1, Set(offsetDateTime1, offsetDateTime2))))
//        _ <- Ns.i.zonedDateTimeSet.query.get.map(_ ==> List((1, Set(zonedDateTime1, zonedDateTime2))))
//        _ <- Ns.i.uuidSet.query.get.map(_ ==> List((1, Set(uuid1, uuid2))))
//        _ <- Ns.i.uriSet.query.get.map(_ ==> List((1, Set(uri1, uri2))))
//        _ <- Ns.i.byteSet.query.get.map(_ ==> List((1, Set(byte1, byte2))))
//        _ <- Ns.i.shortSet.query.get.map(_ ==> List((1, Set(short1, short2))))
//        _ <- Ns.i.charSet.query.get.map(_ ==> List((1, Set(char1, char2))))
//
//        List(r1, r2) <- Ref.i.insert(1, 2).transact.map(_.ids)
//        _ <- Ns.i.refs.insert(1, Set(r1, r2)).transact
//        _ <- Ns.i.refs.query.get.map(_ ==> List((1, Set(r1, r2))))


        //        _ <- rawTransact(
        //          """insert into Ns default values
        //            |""".stripMargin)

//        _ <- rawQuery(
//          """select id from Ns
//            |""".stripMargin, true)

        //        _ <- rawQuery(
        //          """SELECT DISTINCT
        //            |  Ns.i,
        //            |  Ns.string
        //            |FROM Ns
        //            |WHERE
        //            |  Ns.i IS NOT NULL
        //            |ORDER BY Ns.string;
        //            |""".stripMargin, true)


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
