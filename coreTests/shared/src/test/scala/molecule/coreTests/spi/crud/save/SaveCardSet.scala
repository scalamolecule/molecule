package molecule.coreTests.spi.crud.save

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.base.error._
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait SaveCardSet extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "mandatory" - types { implicit conn =>
      for {
        // Can't save multiple Sets of values (use insert for that)
        _ <- Ns.ints(Seq(Set(1), Set(2))).save.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can only save one Set of values for Set attribute `Ns.ints`. Found: Set(1), Set(2)"
          }

        // Same as
        _ <- Ns.ints(Set(1), Set(2)).save.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can only save one Set of values for Set attribute `Ns.ints`. Found: Set(1), Set(2)"
          }

        // Same as
        _ <- Ns.ints(1, 2).save.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can only save one Set of values for Set attribute `Ns.ints`. Found: Set(1), Set(2)"
          }

        // Empty values are ignored
        _ <- Ns.ii.query.get.map(_ ==> List())
        _ <- Ns.ii(Seq.empty[Set[Int]]).save.transact
        _ <- Ns.ii(Seq(Set.empty[Int])).save.transact
        _ <- Ns.ii.query.get.map(_ ==> List())

        _ <- Ns.i(1).strings(Set(string1, string2)).save.transact
        _ <- Ns.i(1).ints(Set(int1, int2)).save.transact
        _ <- Ns.i(1).longs(Set(long1, long2)).save.transact
        _ <- Ns.i(1).floats(Set(float1, float2)).save.transact
        _ <- Ns.i(1).doubles(Set(double1, double2)).save.transact
        _ <- Ns.i(1).booleans(Set(boolean0)).save.transact
        _ <- Ns.i(1).bigInts(Set(bigInt1, bigInt2)).save.transact
        _ <- Ns.i(1).bigDecimals(Set(bigDecimal1, bigDecimal2)).save.transact
        _ <- Ns.i(1).dates(Set(date1, date2)).save.transact
        _ <- Ns.i(1).durations(Set(duration1, duration2)).save.transact
        _ <- Ns.i(1).instants(Set(instant1, instant2)).save.transact
        _ <- Ns.i(1).localDates(Set(localDate1, localDate2)).save.transact
        _ <- Ns.i(1).localTimes(Set(localTime1, localTime2)).save.transact
        _ <- Ns.i(1).localDateTimes(Set(localDateTime1, localDateTime2)).save.transact
        _ <- Ns.i(1).offsetTimes(Set(offsetTime1, offsetTime2)).save.transact
        _ <- Ns.i(1).offsetDateTimes(Set(offsetDateTime1, offsetDateTime2)).save.transact
        _ <- Ns.i(1).zonedDateTimes(Set(zonedDateTime1, zonedDateTime2)).save.transact
        _ <- Ns.i(1).uuids(Set(uuid1, uuid2)).save.transact
        _ <- Ns.i(1).uris(Set(uri1, uri2)).save.transact
        _ <- Ns.i(1).bytes(Set(byte1, byte2)).save.transact
        _ <- Ns.i(1).shorts(Set(short1, short2)).save.transact
        _ <- Ns.i(1).chars(Set(char1, char2)).save.transact

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
        _ <- Ns.i(1).refs(Set(r1, r2)).save.transact
        _ <- Ns.i.refs.query.get.map(_ ==> List((1, Set(r1, r2))))
      } yield ()
    }


    "optional" - types { implicit conn =>
      for {
        // Can't save multiple Sets of values (use insert for that)
        _ <- Ns.ints_?(Some(Seq(Set(1), Set(2)))).save.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can only save one Set of values for optional Set attribute `Ns.ints`. Found: Set(1), Set(2)"
          }

        // Empty option of Set of values saves nothing
        _ <- Ns.ints_?(Option.empty[Seq[Set[Int]]]).save.transact
        _ <- Ns.ints.query.get.map(_ ==> List())

        _ <- Ns.int(1).i(1).strings_?(Option.empty[Set[String]]).save.transact
        _ <- Ns.int(2).i(1).ints_?(Option.empty[Set[Int]]).save.transact
        _ <- Ns.int(3).i(1).longs_?(Option.empty[Set[Long]]).save.transact
        _ <- Ns.int(4).i(1).floats_?(Option.empty[Set[Float]]).save.transact
        _ <- Ns.int(5).i(1).doubles_?(Option.empty[Set[Double]]).save.transact
        _ <- Ns.int(6).i(1).booleans_?(Option.empty[Set[Boolean]]).save.transact
        _ <- Ns.int(7).i(1).bigInts_?(Option.empty[Set[BigInt]]).save.transact
        _ <- Ns.int(8).i(1).bigDecimals_?(Option.empty[Set[BigDecimal]]).save.transact
        _ <- Ns.int(9).i(1).dates_?(Option.empty[Set[Date]]).save.transact
        _ <- Ns.int(10).i(1).durations_?(Option.empty[Set[Duration]]).save.transact
        _ <- Ns.int(11).i(1).instants_?(Option.empty[Set[Instant]]).save.transact
        _ <- Ns.int(12).i(1).localDates_?(Option.empty[Set[LocalDate]]).save.transact
        _ <- Ns.int(13).i(1).localTimes_?(Option.empty[Set[LocalTime]]).save.transact
        _ <- Ns.int(14).i(1).localDateTimes_?(Option.empty[Set[LocalDateTime]]).save.transact
        _ <- Ns.int(15).i(1).offsetTimes_?(Option.empty[Set[OffsetTime]]).save.transact
        _ <- Ns.int(16).i(1).offsetDateTimes_?(Option.empty[Set[OffsetDateTime]]).save.transact
        _ <- Ns.int(17).i(1).zonedDateTimes_?(Option.empty[Set[ZonedDateTime]]).save.transact
        _ <- Ns.int(18).i(1).uuids_?(Option.empty[Set[UUID]]).save.transact
        _ <- Ns.int(19).i(1).uris_?(Option.empty[Set[URI]]).save.transact
        _ <- Ns.int(20).i(1).bytes_?(Option.empty[Set[Byte]]).save.transact
        _ <- Ns.int(21).i(1).shorts_?(Option.empty[Set[Short]]).save.transact
        _ <- Ns.int(22).i(1).chars_?(Option.empty[Set[Char]]).save.transact

        _ <- Ns.int(1).i(2).strings_?(Some(Set.empty[String])).save.transact
        _ <- Ns.int(2).i(2).ints_?(Some(Set.empty[Int])).save.transact
        _ <- Ns.int(3).i(2).longs_?(Some(Set.empty[Long])).save.transact
        _ <- Ns.int(4).i(2).floats_?(Some(Set.empty[Float])).save.transact
        _ <- Ns.int(5).i(2).doubles_?(Some(Set.empty[Double])).save.transact
        _ <- Ns.int(6).i(2).booleans_?(Some(Set.empty[Boolean])).save.transact
        _ <- Ns.int(7).i(2).bigInts_?(Some(Set.empty[BigInt])).save.transact
        _ <- Ns.int(8).i(2).bigDecimals_?(Some(Set.empty[BigDecimal])).save.transact
        _ <- Ns.int(9).i(2).dates_?(Some(Set.empty[Date])).save.transact
        _ <- Ns.int(10).i(2).durations_?(Some(Set.empty[Duration])).save.transact
        _ <- Ns.int(11).i(2).instants_?(Some(Set.empty[Instant])).save.transact
        _ <- Ns.int(12).i(2).localDates_?(Some(Set.empty[LocalDate])).save.transact
        _ <- Ns.int(13).i(2).localTimes_?(Some(Set.empty[LocalTime])).save.transact
        _ <- Ns.int(14).i(2).localDateTimes_?(Some(Set.empty[LocalDateTime])).save.transact
        _ <- Ns.int(15).i(2).offsetTimes_?(Some(Set.empty[OffsetTime])).save.transact
        _ <- Ns.int(16).i(2).offsetDateTimes_?(Some(Set.empty[OffsetDateTime])).save.transact
        _ <- Ns.int(17).i(2).zonedDateTimes_?(Some(Set.empty[ZonedDateTime])).save.transact
        _ <- Ns.int(18).i(2).uuids_?(Some(Set.empty[UUID])).save.transact
        _ <- Ns.int(19).i(2).uris_?(Some(Set.empty[URI])).save.transact
        _ <- Ns.int(20).i(2).bytes_?(Some(Set.empty[Byte])).save.transact
        _ <- Ns.int(21).i(2).shorts_?(Some(Set.empty[Short])).save.transact
        _ <- Ns.int(22).i(2).chars_?(Some(Set.empty[Char])).save.transact

        _ <- Ns.int(1).i(3).strings_?(Some(Set(string1, string2))).save.transact
        _ <- Ns.int(2).i(3).ints_?(Some(Set(int1, int2))).save.transact
        _ <- Ns.int(3).i(3).longs_?(Some(Set(long1, long2))).save.transact
        _ <- Ns.int(4).i(3).floats_?(Some(Set(float1, float2))).save.transact
        _ <- Ns.int(5).i(3).doubles_?(Some(Set(double1, double2))).save.transact
        _ <- Ns.int(6).i(3).booleans_?(Some(Set(boolean1, boolean2))).save.transact
        _ <- Ns.int(7).i(3).bigInts_?(Some(Set(bigInt1, bigInt2))).save.transact
        _ <- Ns.int(8).i(3).bigDecimals_?(Some(Set(bigDecimal1, bigDecimal2))).save.transact
        _ <- Ns.int(9).i(3).dates_?(Some(Set(date1, date2))).save.transact
        _ <- Ns.int(10).i(3).durations_?(Some(Set(duration1, duration2))).save.transact
        _ <- Ns.int(11).i(3).instants_?(Some(Set(instant1, instant2))).save.transact
        _ <- Ns.int(12).i(3).localDates_?(Some(Set(localDate1, localDate2))).save.transact
        _ <- Ns.int(13).i(3).localTimes_?(Some(Set(localTime1, localTime2))).save.transact
        _ <- Ns.int(14).i(3).localDateTimes_?(Some(Set(localDateTime1, localDateTime2))).save.transact
        _ <- Ns.int(15).i(3).offsetTimes_?(Some(Set(offsetTime1, offsetTime2))).save.transact
        _ <- Ns.int(16).i(3).offsetDateTimes_?(Some(Set(offsetDateTime1, offsetDateTime2))).save.transact
        _ <- Ns.int(17).i(3).zonedDateTimes_?(Some(Set(zonedDateTime1, zonedDateTime2))).save.transact
        _ <- Ns.int(18).i(3).uuids_?(Some(Set(uuid1, uuid2))).save.transact
        _ <- Ns.int(19).i(3).uris_?(Some(Set(uri1, uri2))).save.transact
        _ <- Ns.int(20).i(3).bytes_?(Some(Set(byte1, byte2))).save.transact
        _ <- Ns.int(21).i(3).shorts_?(Some(Set(short1, short2))).save.transact
        _ <- Ns.int(22).i(3).chars_?(Some(Set(char1, char2))).save.transact

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
        _ <- Ns.int(23).i(1).refs_?(Option.empty[Set[String]]).save.transact
        _ <- Ns.int(23).i(2).refs_?(Some(Set.empty[String])).save.transact
        _ <- Ns.int(23).i(3).refs_?(Some(Set(r1, r2))).save.transact
        _ <- Ns.int_(23).i.a1.refs_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(r1, r2)))))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      for {
        _ <- Ns.i(1).strings_(Set(string1, string2)).save.transact
        _ <- Ns.i(1).ints_(Set(int1, int2)).save.transact
        _ <- Ns.i(1).longs_(Set(long1, long2)).save.transact
        _ <- Ns.i(1).floats_(Set(float1, float2)).save.transact
        _ <- Ns.i(1).doubles_(Set(double1, double2)).save.transact
        _ <- Ns.i(1).booleans_(Set(boolean1, boolean2)).save.transact
        _ <- Ns.i(1).bigInts_(Set(bigInt1, bigInt2)).save.transact
        _ <- Ns.i(1).bigDecimals_(Set(bigDecimal1, bigDecimal2)).save.transact
        _ <- Ns.i(1).dates_(Set(date1, date2)).save.transact
        _ <- Ns.i(1).durations_(Set(duration1, duration2)).save.transact
        _ <- Ns.i(1).instants_(Set(instant1, instant2)).save.transact
        _ <- Ns.i(1).localDates_(Set(localDate1, localDate2)).save.transact
        _ <- Ns.i(1).localTimes_(Set(localTime1, localTime2)).save.transact
        _ <- Ns.i(1).localDateTimes_(Set(localDateTime1, localDateTime2)).save.transact
        _ <- Ns.i(1).offsetTimes_(Set(offsetTime1, offsetTime2)).save.transact
        _ <- Ns.i(1).offsetDateTimes_(Set(offsetDateTime1, offsetDateTime2)).save.transact
        _ <- Ns.i(1).zonedDateTimes_(Set(zonedDateTime1, zonedDateTime2)).save.transact
        _ <- Ns.i(1).uuids_(Set(uuid1, uuid2)).save.transact
        _ <- Ns.i(1).uris_(Set(uri1, uri2)).save.transact
        _ <- Ns.i(1).bytes_(Set(byte1, byte2)).save.transact
        _ <- Ns.i(1).shorts_(Set(short1, short2)).save.transact
        _ <- Ns.i(1).chars_(Set(char1, char2)).save.transact

        _ <- Ns.i.strings.query.get.map(_.head ==> (1, Set(string1, string2)))
        _ <- Ns.i.ints.query.get.map(_.head ==> (1, Set(int1, int2)))
        _ <- Ns.i.longs.query.get.map(_.head ==> (1, Set(long1, long2)))
        _ <- Ns.i.floats.query.get.map(_.head ==> (1, Set(float1, float2)))
        _ <- Ns.i.doubles.query.get.map(_.head ==> (1, Set(double1, double2)))
        _ <- Ns.i.booleans.query.get.map(_.head ==> (1, Set(boolean1, boolean2)))
        _ <- Ns.i.bigInts.query.get.map(_.head ==> (1, Set(bigInt1, bigInt2)))
        _ <- Ns.i.bigDecimals.query.get.map(_.head ==> (1, Set(bigDecimal1, bigDecimal2)))
        _ <- Ns.i.dates.query.get.map(_.head ==> (1, Set(date1, date2)))
        _ <- Ns.i.durations.query.get.map(_.head ==> (1, Set(duration1, duration2)))
        _ <- Ns.i.instants.query.get.map(_.head ==> (1, Set(instant1, instant2)))
        _ <- Ns.i.localDates.query.get.map(_.head ==> (1, Set(localDate1, localDate2)))
        _ <- Ns.i.localTimes.query.get.map(_.head ==> (1, Set(localTime1, localTime2)))
        _ <- Ns.i.localDateTimes.query.get.map(_.head ==> (1, Set(localDateTime1, localDateTime2)))
        _ <- Ns.i.offsetTimes.query.get.map(_.head ==> (1, Set(offsetTime1, offsetTime2)))
        _ <- Ns.i.offsetDateTimes.query.get.map(_.head ==> (1, Set(offsetDateTime1, offsetDateTime2)))
        _ <- Ns.i.zonedDateTimes.query.get.map(_.head ==> (1, Set(zonedDateTime1, zonedDateTime2)))
        _ <- Ns.i.uuids.query.get.map(_.head ==> (1, Set(uuid1, uuid2)))
        _ <- Ns.i.uris.query.get.map(_.head ==> (1, Set(uri1, uri2)))
        _ <- Ns.i.bytes.query.get.map(_.head ==> (1, Set(byte1, byte2)))
        _ <- Ns.i.shorts.query.get.map(_.head ==> (1, Set(short1, short2)))
        _ <- Ns.i.chars.query.get.map(_.head ==> (1, Set(char1, char2)))

        List(r1, r2) <- Ref.i.insert(1, 2).transact.map(_.ids)
        _ <- Ns.i(1).refs_(Set(r1, r2)).save.transact
        _ <- Ns.i.refs.query.get.map(_.head ==> (1, Set(r1, r2)))
      } yield ()
    }
  }
}
