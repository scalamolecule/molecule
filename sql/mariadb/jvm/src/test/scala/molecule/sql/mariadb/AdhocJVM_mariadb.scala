package molecule.sql.mariadb

import java.net.URI
import java.time.{Duration, Instant, LocalDate, LocalDateTime, LocalTime, OffsetDateTime, OffsetTime, ZonedDateTime}
import java.util.{Date, UUID}
import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.sql.mariadb.async._
import molecule.sql.mariadb.compliance.fallback.RawQuery.{bigDecimal1, bigInt1, boolean1, byte1, char1, date1, double1, duration1, float1, instant1, int1, localDate1, localDateTime1, localTime1, long1, offsetDateTime1, offsetTime1, ref1, ref2, short1, string1, string2, uri1, uuid1, zonedDateTime1}
import molecule.sql.mariadb.setup.TestSuite_mariadb
import utest._
import scala.language.implicitConversions

object AdhocJVM_mariadb extends TestSuite_mariadb {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

      def q(attr: String): String =
        s"""SELECT DISTINCT
           |  JSON_ARRAYAGG(t_1.vs)
           |FROM Ns,
           |  JSON_TABLE(Ns.$attr, '$$[*]' COLUMNS (vs LONGTEXT PATH '$$')) t_1
           |WHERE
           |  Ns.$attr IS NOT NULL
           |HAVING COUNT(*) > 0;
           |""".stripMargin

      for {
        _ <- Ns.strings(Set(string1)).save.transact
        _ <- Ns.ints(Set(int1)).save.transact
        _ <- Ns.longs(Set(long1)).save.transact
        _ <- Ns.floats(Set(float1)).save.transact
        _ <- Ns.doubles(Set(double1)).save.transact
        _ <- Ns.booleans(Set(boolean1)).save.transact
        _ <- Ns.bigInts(Set(bigInt1)).save.transact
        _ <- Ns.bigDecimals(Set(bigDecimal1)).save.transact
        _ <- Ns.dates(Set(date1)).save.transact
        _ <- Ns.durations(Set(duration1)).save.transact
        _ <- Ns.instants(Set(instant1)).save.transact
        _ <- Ns.localDates(Set(localDate1)).save.transact
        _ <- Ns.localTimes(Set(localTime1)).save.transact
        _ <- Ns.localDateTimes(Set(localDateTime1)).save.transact
        _ <- Ns.offsetTimes(Set(offsetTime1)).save.transact
        _ <- Ns.offsetDateTimes(Set(offsetDateTime1)).save.transact
        _ <- Ns.zonedDateTimes(Set(zonedDateTime1)).save.transact
        _ <- Ns.uuids(Set(uuid1)).save.transact
        _ <- Ns.uris(Set(uri1)).save.transact
        _ <- Ns.bytes(Set(byte1)).save.transact
        _ <- Ns.shorts(Set(short1)).save.transact
        _ <- Ns.chars(Set(char1)).save.transact

        _ <- rawQuery(q("strings")).map(_.head ==> List(Set(string1)))
        _ <- rawQuery(q("ints")).map(_.head ==> List(Set(int1.toString)))
        _ <- rawQuery(q("longs")).map(_.head ==> List(Set(long1.toString)))
        _ <- rawQuery(q("floats")).map(_.head ==> List(Set(float1.toString)))
        _ <- rawQuery(q("doubles")).map(_.head ==> List(Set(double1.toString)))
        _ <- rawQuery(q("booleans")).map(_.head ==> List(Set("0")))
        _ <- rawQuery(q("bigInts")).map(_.head ==> List(Set("1")))
        _ <- rawQuery(q("bigDecimals")).map(_.head ==> List(Set("1.1")))
        _ <- rawQuery(q("dates")).map(_.head ==> List(Set(date1.getTime.toString)))
        _ <- rawQuery(q("durations")).map(_.head ==> List(Set(duration1.toString)))
        _ <- rawQuery(q("instants")).map(_.head ==> List(Set(instant1.toString)))
        _ <- rawQuery(q("localDates")).map(_.head ==> List(Set(localDate1.toString)))
        _ <- rawQuery(q("localTimes")).map(_.head ==> List(Set(localTime1.toString)))
        _ <- rawQuery(q("localDateTimes")).map(_.head ==> List(Set(localDateTime1.toString)))
        _ <- rawQuery(q("offsetTimes")).map(_.head ==> List(Set(offsetTime1.toString)))
        _ <- rawQuery(q("offsetDateTimes")).map(_.head ==> List(Set(offsetDateTime1.toString)))
        _ <- rawQuery(q("zonedDateTimes")).map(_.head ==> List(Set(zonedDateTime1.toString)))
        _ <- rawQuery(q("uuids")).map(_.head ==> List(Set(uuid1.toString)))
        _ <- rawQuery(q("uris")).map(_.head ==> List(Set(uri1.toString)))
        _ <- rawQuery(q("bytes")).map(_.head ==> List(Set(byte1.toString)))
        _ <- rawQuery(q("shorts")).map(_.head ==> List(Set(short1.toString)))
        _ <- rawQuery(q("chars")).map(_.head ==> List(Set(char1.toString)))

      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._

      for {

        List(a, b, c, d) <- A.i.ii_?.insert(
          (1, None),
          (1, Some(Set(2))),
          (1, Some(Set(3))),
          (2, Some(Set(4, 5))),
        ).transact.map(_.ids)

        _ <- rawQuery(
          """SELECT DISTINCT
            |  A.i,
            |  JSON_ARRAYAGG(DISTINCT t_1.vs)
            |FROM A
            |  LEFT OUTER JOIN JSON_TABLE(A.ii, '$[*]' COLUMNS (vs INT PATH '$')) t_1 ON true
            |WHERE
            |  A.i IS NOT NULL
            |GROUP BY A.i
            |ORDER BY A.i;
            |""".stripMargin, true)

        _ <- A.i.a1.ii_?.query.i.get.map(_ ==> List( // (since we can't sort by Sets)
          // (1, None), // coalesced with Set(2) and Set(3)
          (1, Some(Set(2, 3))), // coalesced Set(2) and Set(3)
          (2, Some(Set(4, 5))),
        ))



        //        _ <- A.i.Bb.*(B.i.C.ii).insert(
        //          (0, Nil),
        //          (1, List(
        //            (1, Set.empty[Int])
        //          )),
        //          (2, List(
        //            (1, Set.empty[Int]),
        //            (2, Set(0)),
        //            (3, Set(1, 2)),
        //          )),
        //        ).transact
        //
        //        _ <- rawQuery(
        //          """SELECT DISTINCT
        //            |  A.id,
        //            |  A.i,
        //            |  JSON_ARRAYAGG(t_1.vs)
        //            |FROM A
        //            |  LEFT JOIN A_bb_B ON A.id        = A_bb_B.A_id
        //            |  LEFT JOIN B      ON A_bb_B.B_id = B.id
        //            |  LEFT JOIN C      ON B.c         = C.id,
        //            |  JSON_TABLE(
        //            |    IF(C.ii IS NULL, '[null]', C.ii),
        //            |    '$[*]' COLUMNS (vs INT PATH '$')
        //            |  ) t_1
        //            |WHERE
        //            |  A.i IS NOT NULL
        //            |GROUP BY A.id, A.i
        //            |HAVING COUNT(*) > 0
        //            |ORDER BY A.i;
        //            |""".stripMargin, true)
        //
        //        _ <- A.i.a1.Bb.*?(B.C.ii).query.i.get.map(_ ==> List(
        //          (0, Nil),
        //          (1, Nil),
        //          (2, List(
        //            Set(0, 1, 2), // Set(0) and Set(1, 2) coalesced to one Set
        //          )),
        //        ))

      } yield ()
    }


    //    "unique" - unique { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Uniques._
    //      for {
    //        _ <- Uniques.i(1).save.transact
    //
    //      } yield ()
    //    }
    //
    //
    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //      for {
    //        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)
    //
    //      } yield ()
    //    }
  }
}