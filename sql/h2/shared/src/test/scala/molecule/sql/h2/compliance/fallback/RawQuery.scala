package molecule.sql.h2.compliance.fallback

import java.math.{BigDecimal => jBigDecimal}
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.sql.h2.async._
import molecule.sql.h2.setup.TestSuite_h2
import utest._
import scala.language.implicitConversions

object RawQuery extends TestSuite_h2 {

  override lazy val tests = Tests {

    "Lists of Lists of Any" - types { implicit conn =>
      for {
        _ <- Ns.string("a").int(1).save.transact

        _ <- Ns.string.int.query.i.get

        // Each Row returned as a List of Any
        _ <- rawQuery(
          """SELECT DISTINCT
            |  Ns.string,
            |  Ns.int
            |FROM Ns
            |WHERE
            |  Ns.string IS NOT NULL AND
            |  Ns.int    IS NOT NULL;
            |""".stripMargin)
          .map(_ ==> List(
            List("a", 1) // First row
          ))

        // Values are still typed
        _ <- rawQuery(
          """SELECT DISTINCT
            |  Ns.string,
            |  Ns.int
            |FROM Ns
            |WHERE
            |  Ns.string IS NOT NULL AND
            |  Ns.int    IS NOT NULL;
            |""".stripMargin).map(_.head == List("a", "1") ==> false)
      } yield ()
    }


    "Card One types" - types { implicit conn =>
      def q(attr: String): String =
        s"""SELECT DISTINCT
           |  Ns.$attr
           |FROM Ns
           |WHERE
           |  Ns.$attr IS NOT NULL;
           |""".stripMargin

      for {
        _ <- Ns.string(string1).save.transact
        _ <- Ns.int(int1).save.transact
        _ <- Ns.long(long1).save.transact
        _ <- Ns.float(float1).save.transact
        _ <- Ns.double(double1).save.transact
        _ <- Ns.boolean(boolean1).save.transact
        _ <- Ns.bigInt(bigInt1).save.transact
        _ <- Ns.bigDecimal(bigDecimal1).save.transact
        _ <- Ns.date(date1).save.transact
        _ <- Ns.duration(duration1).save.transact
        _ <- Ns.instant(instant1).save.transact
        _ <- Ns.localDate(localDate1).save.transact
        _ <- Ns.localTime(localTime1).save.transact
        _ <- Ns.localDateTime(localDateTime1).save.transact
        _ <- Ns.offsetTime(offsetTime1).save.transact
        _ <- Ns.offsetDateTime(offsetDateTime1).save.transact
        _ <- Ns.zonedDateTime(zonedDateTime1).save.transact
        _ <- Ns.uuid(uuid1).save.transact
        _ <- Ns.uri(uri1).save.transact
        _ <- Ns.byte(byte1).save.transact
        _ <- Ns.short(short1).save.transact
        _ <- Ns.char(char1).save.transact

        _ <- rawQuery(q("string")).map(_.head ==> List(string1))
        _ <- rawQuery(q("int")).map(_.head ==> List(int1))
        _ <- rawQuery(q("long")).map(_.head ==> List(long1))
        _ <- rawQuery(q("float")).map(_.head ==> List(float1))
        _ <- rawQuery(q("double")).map(_.head ==> List(double1))
        _ <- rawQuery(q("boolean")).map(_.head ==> List(boolean1))
        _ <- rawQuery(q("bigInt")).map(_.head ==> List(bigInt1))
        _ <- rawQuery(q("bigDecimal")).map(_.head ==> List(bigDecimal1))
        _ <- rawQuery(q("date")).map(_.head ==> List(date1.getTime))
        _ <- rawQuery(q("duration")).map(_.head ==> List(duration1.toString))
        _ <- rawQuery(q("instant")).map(_.head ==> List(instant1.toString))
        _ <- rawQuery(q("localDate")).map(_.head ==> List(localDate1.toString))
        _ <- rawQuery(q("localTime_")).map(_.head ==> List(localTime1.toString))
        _ <- rawQuery(q("localDateTime")).map(_.head ==> List(localDateTime1.toString))
        _ <- rawQuery(q("offsetTime")).map(_.head ==> List(offsetTime1.toString))
        _ <- rawQuery(q("offsetDateTime")).map(_.head ==> List(offsetDateTime1.toString))
        _ <- rawQuery(q("zonedDateTime")).map(_.head ==> List(zonedDateTime1.toString))
        _ <- rawQuery(q("uuid")).map(_.head ==> List(uuid1.toString))
        _ <- rawQuery(q("uri")).map(_.head ==> List(uri1.toString))
        _ <- rawQuery(q("byte")).map(_.head ==> List(byte1))
        _ <- rawQuery(q("short")).map(_.head ==> List(short1))
        _ <- rawQuery(q("char")).map(_.head ==> List(char1.toString))
      } yield ()
    }


    "Card Set types" - types { implicit conn =>
      def q(attr: String): String =
        s"""SELECT DISTINCT
           |  ARRAY_AGG(Ns.$attr)
           |FROM Ns
           |WHERE
           |  Ns.$attr IS NOT NULL
           |HAVING COUNT(*) > 0;
           |""".stripMargin

      for {
        _ <- Ns.strings(Set(string1, string2)).save.transact
        _ <- Ns.ints(Set(int1, int2)).save.transact
        _ <- Ns.longs(Set(long1, long2)).save.transact
        _ <- Ns.floats(Set(float1, float2)).save.transact
        _ <- Ns.doubles(Set(double1, double2)).save.transact
        _ <- Ns.booleans(Set(boolean1, boolean2)).save.transact
        _ <- Ns.bigInts(Set(bigInt1, bigInt2)).save.transact
        _ <- Ns.bigDecimals(Set(bigDecimal1, bigDecimal2)).save.transact
        _ <- Ns.dates(Set(date1, date2)).save.transact
        _ <- Ns.durations(Set(duration1, duration2)).save.transact
        _ <- Ns.instants(Set(instant1, instant2)).save.transact
        _ <- Ns.localDates(Set(localDate1, localDate2)).save.transact
        _ <- Ns.localTimes(Set(localTime1, localTime2)).save.transact
        _ <- Ns.localDateTimes(Set(localDateTime1, localDateTime2)).save.transact
        _ <- Ns.offsetTimes(Set(offsetTime1, offsetTime2)).save.transact
        _ <- Ns.offsetDateTimes(Set(offsetDateTime1, offsetDateTime2)).save.transact
        _ <- Ns.zonedDateTimes(Set(zonedDateTime1, zonedDateTime2)).save.transact
        _ <- Ns.uuids(Set(uuid1, uuid2)).save.transact
        _ <- Ns.uris(Set(uri1, uri2)).save.transact
        _ <- Ns.bytes(Set(byte1, byte2)).save.transact
        _ <- Ns.shorts(Set(short1, short2)).save.transact
        _ <- Ns.chars(Set(char1, char2)).save.transact

        _ <- rawQuery(q("strings")).map(_.head ==> List(Set(string1, string2)))
        _ <- rawQuery(q("ints")).map(_.head ==> List(Set(int1, int2)))
        _ <- rawQuery(q("longs")).map(_.head ==> List(Set(long1, long2)))
        _ <- rawQuery(q("floats")).map(_.head ==> List(Set(float1, float2)))
        _ <- rawQuery(q("doubles")).map(_.head ==> List(Set(double1, double2)))
        _ <- rawQuery(q("booleans")).map(_.head ==> List(Set(boolean1, boolean2)))
        _ <- rawQuery(q("bigInts")).map(_.head ==> List(Set(new jBigDecimal(bigInt1.bigInteger), new jBigDecimal(bigInt2.bigInteger))))
        _ <- rawQuery(q("bigDecimals")).map(_.head ==> List(Set(bigDecimal1.bigDecimal.setScale(25), bigDecimal2.bigDecimal.setScale(25))))
        _ <- rawQuery(q("dates")).map(_.head ==> List(Set(date1.getTime, date2.getTime)))
        _ <- rawQuery(q("durations")).map(_.head ==> List(Set(duration1.toString, duration2.toString)))
        _ <- rawQuery(q("instants")).map(_.head ==> List(Set(instant1.toString, instant2.toString)))
        _ <- rawQuery(q("localDates")).map(_.head ==> List(Set(localDate1.toString, localDate2.toString)))
        _ <- rawQuery(q("localTimes")).map(_.head ==> List(Set(localTime1.toString, localTime2.toString)))
        _ <- rawQuery(q("localDateTimes")).map(_.head ==> List(Set(localDateTime1.toString, localDateTime2.toString)))
        _ <- rawQuery(q("offsetTimes")).map(_.head ==> List(Set(offsetTime1.toString, offsetTime2.toString)))
        _ <- rawQuery(q("offsetDateTimes")).map(_.head ==> List(Set(offsetDateTime1.toString, offsetDateTime2.toString)))
        _ <- rawQuery(q("zonedDateTimes")).map(_.head ==> List(Set(zonedDateTime1.toString, zonedDateTime2.toString)))
        _ <- rawQuery(q("uuids")).map(_.head ==> List(Set(uuid1, uuid2)))
        _ <- rawQuery(q("uris")).map(_.head ==> List(Set(uri1.toString, uri2.toString)))
        _ <- rawQuery(q("bytes")).map(_.head ==> List(Set(byte1, byte2)))
        _ <- rawQuery(q("shorts")).map(_.head ==> List(Set(short1, short2)))
        _ <- rawQuery(q("chars")).map(_.head ==> List(Set(char1.toString, char2.toString)))
      } yield ()
    }


    "Distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.int.insert(List((1, 2))).transact

        _ <- Ns.i.int(distinct).query.i.get.map(_.head ==> (1, Set(2)))

        _ <- rawQuery(
          """SELECT DISTINCT
            |  Ns.i,
            |  ARRAY_AGG(DISTINCT Ns.int)
            |FROM Ns
            |WHERE
            |  Ns.i   IS NOT NULL AND
            |  Ns.int IS NOT NULL
            |GROUP BY Ns.i
            |ORDER BY Ns.i NULLS FIRST;
            |""".stripMargin,
          true // debug
        ).map(_.head ==> List(1, Set(2)))
      } yield ()
    }


    "Optional Set" - types { implicit conn =>
      for {
        _ <- Ns.i.strings_?.insert(
          (1, Option.empty[Set[String]]),
          (2, Some(Set.empty[String])),
          (3, Some(Set(string1, string2))),
        ).transact

        _ <- Ns.i.a1.strings_?.query.i.get.map(_ ==> List(
          (1, None),
          (2, None),
          (3, Some(Set(string1, string2)))
        ))

        _ <- rawQuery(
          """SELECT DISTINCT
            |  Ns.i,
            |  Ns.strings
            |FROM Ns
            |WHERE
            |  Ns.i IS NOT NULL
            |ORDER BY Ns.i NULLS FIRST;
            |""".stripMargin,
          true // debug
        ).map(_ ==> List(
          List(1, null),
          List(2, Set()), // Notice empty Set, not null
          List(3, Set(string1, string2))
        ))
      } yield ()
    }


    "Optional Set of refs" - types { implicit conn =>
      for {
        _ <- Ns.i.refs_?.insert(
          (1, Option.empty[Set[String]]),
          (2, Some(Set.empty[String])),
          (3, Some(Set(ref1, ref2))),
        ).transact

        _ <- Ns.i.a1.refs_?.query.i.get.map(_ ==> List(
          (1, None),
          (2, None),
          (3, Some(Set(ref1, ref2)))
        ))

        _ <- rawQuery(
          """SELECT DISTINCT
            |  Ns.i,
            |  ARRAY_AGG(Ns_refs_Ref.Ref_id) Ns_refs
            |FROM Ns
            |  LEFT JOIN Ns_refs_Ref ON Ns.id = Ns_refs_Ref.Ns_id
            |WHERE
            |  Ns.i IS NOT NULL
            |GROUP BY Ns.id
            |ORDER BY Ns.i NULLS FIRST;
            |""".stripMargin,
          true // debug
        ).map(_ ==> List(
          List(1, Set(null)), // Notice Set
          List(2, Set(null)), // Notice Set
          List(3, Set(1, 2)) // Ids are numbers in SQL
        ))
      } yield ()
    }
  }
}