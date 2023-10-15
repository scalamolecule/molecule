package molecule.coreTests.compliance.crud.insert

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.concurrent.Future

trait InsertCardSet extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      for {
        // Inserting empty list/set of values is ignored
        _ <- Ns.ii.query.get.map(_ ==> List())
        _ <- Ns.ii.insert(Seq.empty[Set[Int]]).transact
        _ <- Ns.ii.insert(Seq(Set.empty[Int])).transact
        _ <- Ns.ii.query.get.map(_ ==> List())

        _ <- Ns.i.strings.insert(1, Set(string1, string2)).transact
        _ <- Ns.i.ints.insert(1, Set(int1, int2)).transact
        _ <- Ns.i.longs.insert(1, Set(long1, long2)).transact
        _ <- Ns.i.floats.insert(1, Set(float1, float2)).transact
        _ <- Ns.i.doubles.insert(1, Set(double1, double2)).transact
        _ <- Ns.i.booleans.insert(1, Set(boolean0)).transact
        _ <- Ns.i.bigInts.insert(1, Set(bigInt1, bigInt2)).transact
        _ <- Ns.i.bigDecimals.insert(1, Set(bigDecimal1, bigDecimal2)).transact
        _ <- Ns.i.dates.insert(1, Set(date1, date2)).transact
        _ <- Ns.i.durations.insert(1, Set(duration1, duration2)).transact
        _ <- Ns.i.instants.insert(1, Set(instant1, instant2)).transact
        _ <- Ns.i.localDates.insert(1, Set(localDate1, localDate2)).transact
        _ <- Ns.i.localTimes.insert(1, Set(localTime1, localTime2)).transact
        _ <- Ns.i.localDateTimes.insert(1, Set(localDateTime1, localDateTime2)).transact
        _ <- Ns.i.offsetTimes.insert(1, Set(offsetTime1, offsetTime2)).transact
        _ <- Ns.i.offsetDateTimes.insert(1, Set(offsetDateTime1, offsetDateTime2)).transact
        _ <- Ns.i.zonedDateTimes.insert(1, Set(zonedDateTime1, zonedDateTime2)).transact
        _ <- Ns.i.uuids.insert(1, Set(uuid1, uuid2)).transact
        _ <- Ns.i.uris.insert(1, Set(uri1, uri2)).transact
        _ <- Ns.i.bytes.insert(1, Set(byte1, byte2)).transact
        _ <- Ns.i.shorts.insert(1, Set(short1, short2)).transact
        _ <- Ns.i.chars.insert(1, Set(char1, char2)).transact

        _ <- Ns.i.strings.query.get.map(_ ==> List((1, Set(string1, string2))))
        _ <- Ns.i.ints.query.get.map(_ ==> List((1, Set(int1, int2))))
        _ <- Ns.i.longs.query.get.map(_ ==> List((1, Set(long1, long2))))
        _ <- Ns.i.floats.query.get.map(_ ==> List((1, Set(float1, float2))))
        _ <- Ns.i.doubles.query.get.map(_ ==> List((1, Set(double1, double2))))
        _ <- Ns.i.booleans.query.get.map(_ ==> List((1, Set(boolean0))))
        _ <- Ns.i.bigInts.query.get.map(_ ==> List((1, Set(bigInt1, bigInt2))))
        _ <- Ns.i.bigDecimals.query.get.map(_ ==> List((1, Set(bigDecimal1, bigDecimal2))))
        _ <- Ns.i.dates.query.get.map(_ ==> List((1, Set(date1, date2))))
        _ <- Ns.i.durations.query.get.map(_ ==> List((1, Set(duration1, duration2))))
        _ <- Ns.i.instants.query.get.map(_ ==> List((1, Set(instant1, instant2))))
        _ <- Ns.i.localDates.query.get.map(_ ==> List((1, Set(localDate1, localDate2))))
        _ <- Ns.i.localTimes.query.get.map(_ ==> List((1, Set(localTime1, localTime2))))
        _ <- Ns.i.localDateTimes.query.get.map(_ ==> List((1, Set(localDateTime1, localDateTime2))))
        _ <- Ns.i.offsetTimes.query.get.map(_ ==> List((1, Set(offsetTime1, offsetTime2))))
        _ <- Ns.i.offsetDateTimes.query.get.map(_ ==> List((1, Set(offsetDateTime1, offsetDateTime2))))
        _ <- Ns.i.zonedDateTimes.query.get.map(_ ==> List((1, Set(zonedDateTime1, zonedDateTime2))))
        _ <- Ns.i.uuids.query.get.map(_ ==> List((1, Set(uuid1, uuid2))))
        _ <- Ns.i.uris.query.get.map(_ ==> List((1, Set(uri1, uri2))))
        _ <- Ns.i.bytes.query.get.map(_ ==> List((1, Set(byte1, byte2))))
        _ <- Ns.i.shorts.query.get.map(_ ==> List((1, Set(short1, short2))))
        _ <- Ns.i.chars.query.get.map(_ ==> List((1, Set(char1, char2))))

        List(r1, r2) <- Ref.i.insert(1, 2).transact.map(_.ids)
        _ <- Ns.i.refs.insert(1, Set(r1, r2)).transact
        _ <- Ns.i.refs.query.get.map(_ ==> List((1, Set(r1, r2))))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      for {
        _ <- Ns.int.i.strings_?.insert(1, 1, Option.empty[Set[String]]).transact
        _ <- Ns.int.i.ints_?.insert(2, 1, Option.empty[Set[Int]]).transact
        _ <- Ns.int.i.longs_?.insert(3, 1, Option.empty[Set[Long]]).transact
        _ <- Ns.int.i.floats_?.insert(4, 1, Option.empty[Set[Float]]).transact
        _ <- Ns.int.i.doubles_?.insert(5, 1, Option.empty[Set[Double]]).transact
        _ <- Ns.int.i.booleans_?.insert(6, 1, Option.empty[Set[Boolean]]).transact
        _ <- Ns.int.i.bigInts_?.insert(7, 1, Option.empty[Set[BigInt]]).transact
        _ <- Ns.int.i.bigDecimals_?.insert(8, 1, Option.empty[Set[BigDecimal]]).transact
        _ <- Ns.int.i.dates_?.insert(9, 1, Option.empty[Set[Date]]).transact
        _ <- Ns.int.i.durations_?.insert(10, 1, Option.empty[Set[Duration]]).transact
        _ <- Ns.int.i.instants_?.insert(11, 1, Option.empty[Set[Instant]]).transact
        _ <- Ns.int.i.localDates_?.insert(12, 1, Option.empty[Set[LocalDate]]).transact
        _ <- Ns.int.i.localTimes_?.insert(13, 1, Option.empty[Set[LocalTime]]).transact
        _ <- Ns.int.i.localDateTimes_?.insert(14, 1, Option.empty[Set[LocalDateTime]]).transact
        _ <- Ns.int.i.offsetTimes_?.insert(15, 1, Option.empty[Set[OffsetTime]]).transact
        _ <- Ns.int.i.offsetDateTimes_?.insert(16, 1, Option.empty[Set[OffsetDateTime]]).transact
        _ <- Ns.int.i.zonedDateTimes_?.insert(17, 1, Option.empty[Set[ZonedDateTime]]).transact
        _ <- Ns.int.i.uuids_?.insert(18, 1, Option.empty[Set[UUID]]).transact
        _ <- Ns.int.i.uris_?.insert(19, 1, Option.empty[Set[URI]]).transact
        _ <- Ns.int.i.bytes_?.insert(20, 1, Option.empty[Set[Byte]]).transact
        _ <- Ns.int.i.shorts_?.insert(21, 1, Option.empty[Set[Short]]).transact
        _ <- Ns.int.i.chars_?.insert(22, 1, Option.empty[Set[Char]]).transact

        _ <- Ns.int.i.strings_?.insert(1, 2, Some(Set.empty[String])).transact
        _ <- Ns.int.i.ints_?.insert(2, 2, Some(Set.empty[Int])).transact
        _ <- Ns.int.i.longs_?.insert(3, 2, Some(Set.empty[Long])).transact
        _ <- Ns.int.i.floats_?.insert(4, 2, Some(Set.empty[Float])).transact
        _ <- Ns.int.i.doubles_?.insert(5, 2, Some(Set.empty[Double])).transact
        _ <- Ns.int.i.booleans_?.insert(6, 2, Some(Set.empty[Boolean])).transact
        _ <- Ns.int.i.bigInts_?.insert(7, 2, Some(Set.empty[BigInt])).transact
        _ <- Ns.int.i.bigDecimals_?.insert(8, 2, Some(Set.empty[BigDecimal])).transact
        _ <- Ns.int.i.dates_?.insert(9, 2, Some(Set.empty[Date])).transact
        _ <- Ns.int.i.durations_?.insert(10, 2, Some(Set.empty[Duration])).transact
        _ <- Ns.int.i.instants_?.insert(11, 2, Some(Set.empty[Instant])).transact
        _ <- Ns.int.i.localDates_?.insert(12, 2, Some(Set.empty[LocalDate])).transact
        _ <- Ns.int.i.localTimes_?.insert(13, 2, Some(Set.empty[LocalTime])).transact
        _ <- Ns.int.i.localDateTimes_?.insert(14, 2, Some(Set.empty[LocalDateTime])).transact
        _ <- Ns.int.i.offsetTimes_?.insert(15, 2, Some(Set.empty[OffsetTime])).transact
        _ <- Ns.int.i.offsetDateTimes_?.insert(16, 2, Some(Set.empty[OffsetDateTime])).transact
        _ <- Ns.int.i.zonedDateTimes_?.insert(17, 2, Some(Set.empty[ZonedDateTime])).transact
        _ <- Ns.int.i.uuids_?.insert(18, 2, Some(Set.empty[UUID])).transact
        _ <- Ns.int.i.uris_?.insert(19, 2, Some(Set.empty[URI])).transact
        _ <- Ns.int.i.bytes_?.insert(20, 2, Some(Set.empty[Byte])).transact
        _ <- Ns.int.i.shorts_?.insert(21, 2, Some(Set.empty[Short])).transact
        _ <- Ns.int.i.chars_?.insert(22, 2, Some(Set.empty[Char])).transact

        _ <- Ns.int.i.strings_?.insert(1, 3, Some(Set(string1, string2))).transact
        _ <- Ns.int.i.ints_?.insert(2, 3, Some(Set(int1, int2))).transact
        _ <- Ns.int.i.longs_?.insert(3, 3, Some(Set(long1, long2))).transact
        _ <- Ns.int.i.floats_?.insert(4, 3, Some(Set(float1, float2))).transact
        _ <- Ns.int.i.doubles_?.insert(5, 3, Some(Set(double1, double2))).transact
        _ <- Ns.int.i.booleans_?.insert(6, 3, Some(Set(boolean1, boolean2))).transact
        _ <- Ns.int.i.bigInts_?.insert(7, 3, Some(Set(bigInt1, bigInt2))).transact
        _ <- Ns.int.i.bigDecimals_?.insert(8, 3, Some(Set(bigDecimal1, bigDecimal2))).transact
        _ <- Ns.int.i.dates_?.insert(9, 3, Some(Set(date1, date2))).transact
        _ <- Ns.int.i.durations_?.insert(10, 3, Some(Set(duration1, duration2))).transact
        _ <- Ns.int.i.instants_?.insert(11, 3, Some(Set(instant1, instant2))).transact
        _ <- Ns.int.i.localDates_?.insert(12, 3, Some(Set(localDate1, localDate2))).transact
        _ <- Ns.int.i.localTimes_?.insert(13, 3, Some(Set(localTime1, localTime2))).transact
        _ <- Ns.int.i.localDateTimes_?.insert(14, 3, Some(Set(localDateTime1, localDateTime2))).transact
        _ <- Ns.int.i.offsetTimes_?.insert(15, 3, Some(Set(offsetTime1, offsetTime2))).transact
        _ <- Ns.int.i.offsetDateTimes_?.insert(16, 3, Some(Set(offsetDateTime1, offsetDateTime2))).transact
        _ <- Ns.int.i.zonedDateTimes_?.insert(17, 3, Some(Set(zonedDateTime1, zonedDateTime2))).transact
        _ <- Ns.int.i.uuids_?.insert(18, 3, Some(Set(uuid1, uuid2))).transact
        _ <- Ns.int.i.uris_?.insert(19, 3, Some(Set(uri1, uri2))).transact
        _ <- Ns.int.i.bytes_?.insert(20, 3, Some(Set(byte1, byte2))).transact
        _ <- Ns.int.i.shorts_?.insert(21, 3, Some(Set(short1, short2))).transact
        _ <- Ns.int.i.chars_?.insert(22, 3, Some(Set(char1, char2))).transact

        _ <- Ns.int_(1).i.a1.strings_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(string1, string2)))))
        _ <- Ns.int_(2).i.a1.ints_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(int1, int2)))))
        _ <- Ns.int_(3).i.a1.longs_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(long1, long2)))))
        _ <- Ns.int_(4).i.a1.floats_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(float1, float2)))))
        _ <- Ns.int_(5).i.a1.doubles_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(double1, double2)))))
        _ <- Ns.int_(6).i.a1.booleans_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(boolean1, boolean2)))))
        _ <- Ns.int_(7).i.a1.bigInts_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(bigInt1, bigInt2)))))
        _ <- Ns.int_(8).i.a1.bigDecimals_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(bigDecimal1, bigDecimal2)))))
        _ <- Ns.int_(9).i.a1.dates_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(date1, date2)))))
        _ <- Ns.int_(10).i.a1.durations_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(duration1, duration2)))))
        _ <- Ns.int_(11).i.a1.instants_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(instant1, instant2)))))
        _ <- Ns.int_(12).i.a1.localDates_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(localDate1, localDate2)))))
        _ <- Ns.int_(13).i.a1.localTimes_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(localTime1, localTime2)))))
        _ <- Ns.int_(14).i.a1.localDateTimes_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(localDateTime1, localDateTime2)))))
        _ <- Ns.int_(15).i.a1.offsetTimes_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(offsetTime1, offsetTime2)))))
        _ <- Ns.int_(16).i.a1.offsetDateTimes_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(offsetDateTime1, offsetDateTime2)))))
        _ <- Ns.int_(17).i.a1.zonedDateTimes_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(zonedDateTime1, zonedDateTime2)))))
        _ <- Ns.int_(18).i.a1.uuids_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(uuid1, uuid2)))))
        _ <- Ns.int_(19).i.a1.uris_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(uri1, uri2)))))
        _ <- Ns.int_(20).i.a1.bytes_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(byte1, byte2)))))
        _ <- Ns.int_(21).i.a1.shorts_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(short1, short2)))))
        _ <- Ns.int_(22).i.a1.chars_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(char1, char2)))))

        List(r1, r2) <- Ref.i.insert(1, 2).transact.map(_.ids)
        _ <- Ns.int.i.refs_?.insert(23, 1, Option.empty[Set[Long]]).transact
        _ <- Ns.int.i.refs_?.insert(23, 2, Some(Set.empty[Long])).transact
        _ <- Ns.int.i.refs_?.insert(23, 3, Some(Set(r1, r2))).transact
        _ <- Ns.int_(23).i.a1.refs_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(r1, r2)))))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      for {
        // Can't insert tacit attributes
        _ <- Future(compileError("Ns.i.string_.insert(1, string1)"))
      } yield ()
    }
  }
}
