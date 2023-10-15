package molecule.coreTests.compliance.filterAttr.set

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait Types extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "equal (apply)" - types { implicit conn =>
      for {
        _ <- Ns.strings.Ref.strings.insert(Set(string1), Set(string1)).transact
        _ <- Ns.ints.Ref.ints.insert(Set(int1), Set(int1)).transact
        _ <- Ns.longs.Ref.longs.insert(Set(long1), Set(long1)).transact
        _ <- Ns.floats.Ref.floats.insert(Set(float1), Set(float1)).transact
        _ <- Ns.doubles.Ref.doubles.insert(Set(double1), Set(double1)).transact
        _ <- Ns.booleans.Ref.booleans.insert(Set(boolean1), Set(boolean1)).transact
        _ <- Ns.bigInts.Ref.bigInts.insert(Set(bigInt1), Set(bigInt1)).transact
        _ <- Ns.bigDecimals.Ref.bigDecimals.insert(Set(bigDecimal1), Set(bigDecimal1)).transact
        _ <- Ns.dates.Ref.dates.insert(Set(date1), Set(date1)).transact
        _ <- Ns.durations.Ref.durations.insert(Set(duration1), Set(duration1)).transact
        _ <- Ns.instants.Ref.instants.insert(Set(instant1), Set(instant1)).transact
        _ <- Ns.localDates.Ref.localDates.insert(Set(localDate1), Set(localDate1)).transact
        _ <- Ns.localTimes.Ref.localTimes.insert(Set(localTime1), Set(localTime1)).transact
        _ <- Ns.localDateTimes.Ref.localDateTimes.insert(Set(localDateTime1), Set(localDateTime1)).transact
        _ <- Ns.offsetTimes.Ref.offsetTimes.insert(Set(offsetTime1), Set(offsetTime1)).transact
        _ <- Ns.offsetDateTimes.Ref.offsetDateTimes.insert(Set(offsetDateTime1), Set(offsetDateTime1)).transact
        _ <- Ns.zonedDateTimes.Ref.zonedDateTimes.insert(Set(zonedDateTime1), Set(zonedDateTime1)).transact
        _ <- Ns.uuids.Ref.uuids.insert(Set(uuid1), Set(uuid1)).transact
        _ <- Ns.uris.Ref.uris.insert(Set(uri1), Set(uri1)).transact
        _ <- Ns.bytes.Ref.bytes.insert(Set(byte1), Set(byte1)).transact
        _ <- Ns.shorts.Ref.shorts.insert(Set(short1), Set(short1)).transact
        _ <- Ns.chars.Ref.chars.insert(Set(char1), Set(char1)).transact

        _ <- Ns.strings(Ref.strings_).Ref.strings.query.get.map(_ ==> List((Set(string1), Set(string1))))
        _ <- Ns.ints(Ref.ints_).Ref.ints.query.get.map(_ ==> List((Set(int1), Set(int1))))
        _ <- Ns.longs(Ref.longs_).Ref.longs.query.get.map(_ ==> List((Set(long1), Set(long1))))
        _ <- Ns.floats(Ref.floats_).Ref.floats.query.get.map(_ ==> List((Set(float1), Set(float1))))
        _ <- Ns.doubles(Ref.doubles_).Ref.doubles.query.get.map(_ ==> List((Set(double1), Set(double1))))
        _ <- Ns.booleans(Ref.booleans_).Ref.booleans.query.get.map(_ ==> List((Set(boolean1), Set(boolean1))))
        _ <- Ns.bigInts(Ref.bigInts_).Ref.bigInts.query.get.map(_ ==> List((Set(bigInt1), Set(bigInt1))))
        _ <- Ns.bigDecimals(Ref.bigDecimals_).Ref.bigDecimals.query.get.map(_ ==> List((Set(bigDecimal1), Set(bigDecimal1))))
        _ <- Ns.dates(Ref.dates_).Ref.dates.query.get.map(_ ==> List((Set(date1), Set(date1))))
        _ <- Ns.durations(Ref.durations_).Ref.durations.query.get.map(_ ==> List((Set(duration1), Set(duration1))))
        _ <- Ns.instants(Ref.instants_).Ref.instants.query.get.map(_ ==> List((Set(instant1), Set(instant1))))
        _ <- Ns.localDates(Ref.localDates_).Ref.localDates.query.get.map(_ ==> List((Set(localDate1), Set(localDate1))))
        _ <- Ns.localTimes(Ref.localTimes_).Ref.localTimes.query.get.map(_ ==> List((Set(localTime1), Set(localTime1))))
        _ <- Ns.localDateTimes(Ref.localDateTimes_).Ref.localDateTimes.query.get.map(_ ==> List((Set(localDateTime1), Set(localDateTime1))))
        _ <- Ns.offsetTimes(Ref.offsetTimes_).Ref.offsetTimes.query.get.map(_ ==> List((Set(offsetTime1), Set(offsetTime1))))
        _ <- Ns.offsetDateTimes(Ref.offsetDateTimes_).Ref.offsetDateTimes.query.get.map(_ ==> List((Set(offsetDateTime1), Set(offsetDateTime1))))
        _ <- Ns.zonedDateTimes(Ref.zonedDateTimes_).Ref.zonedDateTimes.query.get.map(_ ==> List((Set(zonedDateTime1), Set(zonedDateTime1))))
        _ <- Ns.uuids(Ref.uuids_).Ref.uuids.query.get.map(_ ==> List((Set(uuid1), Set(uuid1))))
        _ <- Ns.uris(Ref.uris_).Ref.uris.query.get.map(_ ==> List((Set(uri1), Set(uri1))))
        _ <- Ns.bytes(Ref.bytes_).Ref.bytes.query.get.map(_ ==> List((Set(byte1), Set(byte1))))
        _ <- Ns.shorts(Ref.shorts_).Ref.shorts.query.get.map(_ ==> List((Set(short1), Set(short1))))
        _ <- Ns.chars(Ref.chars_).Ref.chars.query.get.map(_ ==> List((Set(char1), Set(char1))))
      } yield ()
    }


    "not equal" - types { implicit conn =>
      for {
        _ <- Ns.strings.Ref.strings.insert(Set(string1), Set(string2)).transact
        _ <- Ns.ints.Ref.ints.insert(Set(int1), Set(int2)).transact
        _ <- Ns.longs.Ref.longs.insert(Set(long1), Set(long2)).transact
        _ <- Ns.floats.Ref.floats.insert(Set(float1), Set(float2)).transact
        _ <- Ns.doubles.Ref.doubles.insert(Set(double1), Set(double2)).transact
        _ <- Ns.booleans.Ref.booleans.insert(Set(boolean1), Set(boolean2)).transact
        _ <- Ns.bigInts.Ref.bigInts.insert(Set(bigInt1), Set(bigInt2)).transact
        _ <- Ns.bigDecimals.Ref.bigDecimals.insert(Set(bigDecimal1), Set(bigDecimal2)).transact
        _ <- Ns.dates.Ref.dates.insert(Set(date1), Set(date2)).transact
        _ <- Ns.durations.Ref.durations.insert(Set(duration1), Set(duration2)).transact
        _ <- Ns.instants.Ref.instants.insert(Set(instant1), Set(instant2)).transact
        _ <- Ns.localDates.Ref.localDates.insert(Set(localDate1), Set(localDate2)).transact
        _ <- Ns.localTimes.Ref.localTimes.insert(Set(localTime1), Set(localTime2)).transact
        _ <- Ns.localDateTimes.Ref.localDateTimes.insert(Set(localDateTime1), Set(localDateTime2)).transact
        _ <- Ns.offsetTimes.Ref.offsetTimes.insert(Set(offsetTime1), Set(offsetTime2)).transact
        _ <- Ns.offsetDateTimes.Ref.offsetDateTimes.insert(Set(offsetDateTime1), Set(offsetDateTime2)).transact
        _ <- Ns.zonedDateTimes.Ref.zonedDateTimes.insert(Set(zonedDateTime1), Set(zonedDateTime2)).transact
        _ <- Ns.uuids.Ref.uuids.insert(Set(uuid1), Set(uuid2)).transact
        _ <- Ns.uris.Ref.uris.insert(Set(uri1), Set(uri2)).transact
        _ <- Ns.bytes.Ref.bytes.insert(Set(byte1), Set(byte2)).transact
        _ <- Ns.shorts.Ref.shorts.insert(Set(short1), Set(short2)).transact
        _ <- Ns.chars.Ref.chars.insert(Set(char1), Set(char2)).transact

        _ <- Ns.strings.not(Ref.strings_).Ref.strings.query.get.map(_ ==> List((Set(string1), Set(string2))))
        _ <- Ns.ints.not(Ref.ints_).Ref.ints.query.get.map(_ ==> List((Set(int1), Set(int2))))
        _ <- Ns.longs.not(Ref.longs_).Ref.longs.query.get.map(_ ==> List((Set(long1), Set(long2))))
        _ <- Ns.floats.not(Ref.floats_).Ref.floats.query.get.map(_ ==> List((Set(float1), Set(float2))))
        _ <- Ns.doubles.not(Ref.doubles_).Ref.doubles.query.get.map(_ ==> List((Set(double1), Set(double2))))
        _ <- Ns.booleans.not(Ref.booleans_).Ref.booleans.query.get.map(_ ==> List((Set(boolean1), Set(boolean2))))
        _ <- Ns.bigInts.not(Ref.bigInts_).Ref.bigInts.query.get.map(_ ==> List((Set(bigInt1), Set(bigInt2))))
        _ <- Ns.bigDecimals.not(Ref.bigDecimals_).Ref.bigDecimals.query.get.map(_ ==> List((Set(bigDecimal1), Set(bigDecimal2))))
        _ <- Ns.dates.not(Ref.dates_).Ref.dates.query.get.map(_ ==> List((Set(date1), Set(date2))))
        _ <- Ns.durations.not(Ref.durations_).Ref.durations.query.get.map(_ ==> List((Set(duration1), Set(duration2))))
        _ <- Ns.instants.not(Ref.instants_).Ref.instants.query.get.map(_ ==> List((Set(instant1), Set(instant2))))
        _ <- Ns.localDates.not(Ref.localDates_).Ref.localDates.query.get.map(_ ==> List((Set(localDate1), Set(localDate2))))
        _ <- Ns.localTimes.not(Ref.localTimes_).Ref.localTimes.query.get.map(_ ==> List((Set(localTime1), Set(localTime2))))
        _ <- Ns.localDateTimes.not(Ref.localDateTimes_).Ref.localDateTimes.query.get.map(_ ==> List((Set(localDateTime1), Set(localDateTime2))))
        _ <- Ns.offsetTimes.not(Ref.offsetTimes_).Ref.offsetTimes.query.get.map(_ ==> List((Set(offsetTime1), Set(offsetTime2))))
        _ <- Ns.offsetDateTimes.not(Ref.offsetDateTimes_).Ref.offsetDateTimes.query.get.map(_ ==> List((Set(offsetDateTime1), Set(offsetDateTime2))))
        _ <- Ns.zonedDateTimes.not(Ref.zonedDateTimes_).Ref.zonedDateTimes.query.get.map(_ ==> List((Set(zonedDateTime1), Set(zonedDateTime2))))
        _ <- Ns.uuids.not(Ref.uuids_).Ref.uuids.query.get.map(_ ==> List((Set(uuid1), Set(uuid2))))
        _ <- Ns.uris.not(Ref.uris_).Ref.uris.query.get.map(_ ==> List((Set(uri1), Set(uri2))))
        _ <- Ns.bytes.not(Ref.bytes_).Ref.bytes.query.get.map(_ ==> List((Set(byte1), Set(byte2))))
        _ <- Ns.shorts.not(Ref.shorts_).Ref.shorts.query.get.map(_ ==> List((Set(short1), Set(short2))))
        _ <- Ns.chars.not(Ref.chars_).Ref.chars.query.get.map(_ ==> List((Set(char1), Set(char2))))
      } yield ()
    }

    "has set" - types { implicit conn =>
      for {
        _ <- Ns.strings.Ref.strings.insert(Set(string1), Set(string1)).transact
        _ <- Ns.ints.Ref.ints.insert(Set(int1), Set(int1)).transact
        _ <- Ns.longs.Ref.longs.insert(Set(long1), Set(long1)).transact
        _ <- Ns.floats.Ref.floats.insert(Set(float1), Set(float1)).transact
        _ <- Ns.doubles.Ref.doubles.insert(Set(double1), Set(double1)).transact
        _ <- Ns.booleans.Ref.booleans.insert(Set(boolean1), Set(boolean1)).transact
        _ <- Ns.bigInts.Ref.bigInts.insert(Set(bigInt1), Set(bigInt1)).transact
        _ <- Ns.bigDecimals.Ref.bigDecimals.insert(Set(bigDecimal1), Set(bigDecimal1)).transact
        _ <- Ns.dates.Ref.dates.insert(Set(date1), Set(date1)).transact
        _ <- Ns.durations.Ref.durations.insert(Set(duration1), Set(duration1)).transact
        _ <- Ns.instants.Ref.instants.insert(Set(instant1), Set(instant1)).transact
        _ <- Ns.localDates.Ref.localDates.insert(Set(localDate1), Set(localDate1)).transact
        _ <- Ns.localTimes.Ref.localTimes.insert(Set(localTime1), Set(localTime1)).transact
        _ <- Ns.localDateTimes.Ref.localDateTimes.insert(Set(localDateTime1), Set(localDateTime1)).transact
        _ <- Ns.offsetTimes.Ref.offsetTimes.insert(Set(offsetTime1), Set(offsetTime1)).transact
        _ <- Ns.offsetDateTimes.Ref.offsetDateTimes.insert(Set(offsetDateTime1), Set(offsetDateTime1)).transact
        _ <- Ns.zonedDateTimes.Ref.zonedDateTimes.insert(Set(zonedDateTime1), Set(zonedDateTime1)).transact
        _ <- Ns.uuids.Ref.uuids.insert(Set(uuid1), Set(uuid1)).transact
        _ <- Ns.uris.Ref.uris.insert(Set(uri1), Set(uri1)).transact
        _ <- Ns.bytes.Ref.bytes.insert(Set(byte1), Set(byte1)).transact
        _ <- Ns.shorts.Ref.shorts.insert(Set(short1), Set(short1)).transact
        _ <- Ns.chars.Ref.chars.insert(Set(char1), Set(char1)).transact

        _ <- Ns.strings.has(Ref.strings_).Ref.strings.query.get.map(_ ==> List((Set(string1), Set(string1))))
        _ <- Ns.ints.has(Ref.ints_).Ref.ints.query.get.map(_ ==> List((Set(int1), Set(int1))))
        _ <- Ns.longs.has(Ref.longs_).Ref.longs.query.get.map(_ ==> List((Set(long1), Set(long1))))
        _ <- Ns.floats.has(Ref.floats_).Ref.floats.query.get.map(_ ==> List((Set(float1), Set(float1))))
        _ <- Ns.doubles.has(Ref.doubles_).Ref.doubles.query.get.map(_ ==> List((Set(double1), Set(double1))))
        _ <- Ns.booleans.has(Ref.booleans_).Ref.booleans.query.get.map(_ ==> List((Set(boolean1), Set(boolean1))))
        _ <- Ns.bigInts.has(Ref.bigInts_).Ref.bigInts.query.get.map(_ ==> List((Set(bigInt1), Set(bigInt1))))
        _ <- Ns.bigDecimals.has(Ref.bigDecimals_).Ref.bigDecimals.query.get.map(_ ==> List((Set(bigDecimal1), Set(bigDecimal1))))
        _ <- Ns.dates.has(Ref.dates_).Ref.dates.query.get.map(_ ==> List((Set(date1), Set(date1))))
        _ <- Ns.durations.has(Ref.durations_).Ref.durations.query.get.map(_ ==> List((Set(duration1), Set(duration1))))
        _ <- Ns.instants.has(Ref.instants_).Ref.instants.query.get.map(_ ==> List((Set(instant1), Set(instant1))))
        _ <- Ns.localDates.has(Ref.localDates_).Ref.localDates.query.get.map(_ ==> List((Set(localDate1), Set(localDate1))))
        _ <- Ns.localTimes.has(Ref.localTimes_).Ref.localTimes.query.get.map(_ ==> List((Set(localTime1), Set(localTime1))))
        _ <- Ns.localDateTimes.has(Ref.localDateTimes_).Ref.localDateTimes.query.get.map(_ ==> List((Set(localDateTime1), Set(localDateTime1))))
        _ <- Ns.offsetTimes.has(Ref.offsetTimes_).Ref.offsetTimes.query.get.map(_ ==> List((Set(offsetTime1), Set(offsetTime1))))
        _ <- Ns.offsetDateTimes.has(Ref.offsetDateTimes_).Ref.offsetDateTimes.query.get.map(_ ==> List((Set(offsetDateTime1), Set(offsetDateTime1))))
        _ <- Ns.zonedDateTimes.has(Ref.zonedDateTimes_).Ref.zonedDateTimes.query.get.map(_ ==> List((Set(zonedDateTime1), Set(zonedDateTime1))))
        _ <- Ns.uuids.has(Ref.uuids_).Ref.uuids.query.get.map(_ ==> List((Set(uuid1), Set(uuid1))))
        _ <- Ns.uris.has(Ref.uris_).Ref.uris.query.get.map(_ ==> List((Set(uri1), Set(uri1))))
        _ <- Ns.bytes.has(Ref.bytes_).Ref.bytes.query.get.map(_ ==> List((Set(byte1), Set(byte1))))
        _ <- Ns.shorts.has(Ref.shorts_).Ref.shorts.query.get.map(_ ==> List((Set(short1), Set(short1))))
        _ <- Ns.chars.has(Ref.chars_).Ref.chars.query.get.map(_ ==> List((Set(char1), Set(char1))))
      } yield ()
    }

    "has one" - types { implicit conn =>
      for {
        _ <- Ns.strings.Ref.string.insert(Set(string1), string1).transact
        _ <- Ns.ints.Ref.int.insert(Set(int1), int1).transact
        _ <- Ns.longs.Ref.long.insert(Set(long1), long1).transact
        _ <- Ns.floats.Ref.float.insert(Set(float1), float1).transact
        _ <- Ns.doubles.Ref.double.insert(Set(double1), double1).transact
        _ <- Ns.booleans.Ref.boolean.insert(Set(boolean1), boolean1).transact
        _ <- Ns.bigInts.Ref.bigInt.insert(Set(bigInt1), bigInt1).transact
        _ <- Ns.bigDecimals.Ref.bigDecimal.insert(Set(bigDecimal1), bigDecimal1).transact
        _ <- Ns.dates.Ref.date.insert(Set(date1), date1).transact
        _ <- Ns.durations.Ref.duration.insert(Set(duration1), duration1).transact
        _ <- Ns.instants.Ref.instant.insert(Set(instant1), instant1).transact
        _ <- Ns.localDates.Ref.localDate.insert(Set(localDate1), localDate1).transact
        _ <- Ns.localTimes.Ref.localTime.insert(Set(localTime1), localTime1).transact
        _ <- Ns.localDateTimes.Ref.localDateTime.insert(Set(localDateTime1), localDateTime1).transact
        _ <- Ns.offsetTimes.Ref.offsetTime.insert(Set(offsetTime1), offsetTime1).transact
        _ <- Ns.offsetDateTimes.Ref.offsetDateTime.insert(Set(offsetDateTime1), offsetDateTime1).transact
        _ <- Ns.zonedDateTimes.Ref.zonedDateTime.insert(Set(zonedDateTime1), zonedDateTime1).transact
        _ <- Ns.uuids.Ref.uuid.insert(Set(uuid1), uuid1).transact
        _ <- Ns.uris.Ref.uri.insert(Set(uri1), uri1).transact
        _ <- Ns.bytes.Ref.byte.insert(Set(byte1), byte1).transact
        _ <- Ns.shorts.Ref.short.insert(Set(short1), short1).transact
        _ <- Ns.chars.Ref.char.insert(Set(char1), char1).transact

        _ <- Ns.strings.has(Ref.string_).Ref.string.query.get.map(_ ==> List((Set(string1), string1)))
        _ <- Ns.ints.has(Ref.int_).Ref.int.query.get.map(_ ==> List((Set(int1), int1)))
        _ <- Ns.longs.has(Ref.long_).Ref.long.query.get.map(_ ==> List((Set(long1), long1)))
        _ <- Ns.floats.has(Ref.float_).Ref.float.query.get.map(_ ==> List((Set(float1), float1)))
        _ <- Ns.doubles.has(Ref.double_).Ref.double.query.get.map(_ ==> List((Set(double1), double1)))
        _ <- Ns.booleans.has(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((Set(boolean1), boolean1)))
        _ <- Ns.bigInts.has(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((Set(bigInt1), bigInt1)))
        _ <- Ns.bigDecimals.has(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((Set(bigDecimal1), bigDecimal1)))
        _ <- Ns.dates.has(Ref.date_).Ref.date.query.get.map(_ ==> List((Set(date1), date1)))
        _ <- Ns.durations.has(Ref.duration_).Ref.duration.query.get.map(_ ==> List((Set(duration1), duration1)))
        _ <- Ns.instants.has(Ref.instant_).Ref.instant.query.get.map(_ ==> List((Set(instant1), instant1)))
        _ <- Ns.localDates.has(Ref.localDate_).Ref.localDate.query.get.map(_ ==> List((Set(localDate1), localDate1)))
        _ <- Ns.localTimes.has(Ref.localTime_).Ref.localTime.query.get.map(_ ==> List((Set(localTime1), localTime1)))
        _ <- Ns.localDateTimes.has(Ref.localDateTime_).Ref.localDateTime.query.get.map(_ ==> List((Set(localDateTime1), localDateTime1)))
        _ <- Ns.offsetTimes.has(Ref.offsetTime_).Ref.offsetTime.query.get.map(_ ==> List((Set(offsetTime1), offsetTime1)))
        _ <- Ns.offsetDateTimes.has(Ref.offsetDateTime_).Ref.offsetDateTime.query.get.map(_ ==> List((Set(offsetDateTime1), offsetDateTime1)))
        _ <- Ns.zonedDateTimes.has(Ref.zonedDateTime_).Ref.zonedDateTime.query.get.map(_ ==> List((Set(zonedDateTime1), zonedDateTime1)))
        _ <- Ns.uuids.has(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((Set(uuid1), uuid1)))
        _ <- Ns.uris.has(Ref.uri_).Ref.uri.query.get.map(_ ==> List((Set(uri1), uri1)))
        _ <- Ns.bytes.has(Ref.byte_).Ref.byte.query.get.map(_ ==> List((Set(byte1), byte1)))
        _ <- Ns.shorts.has(Ref.short_).Ref.short.query.get.map(_ ==> List((Set(short1), short1)))
        _ <- Ns.chars.has(Ref.char_).Ref.char.query.get.map(_ ==> List((Set(char1), char1)))
      } yield ()
    }


    "hasNo set" - types { implicit conn =>
      for {
        _ <- Ns.strings.Ref.strings.insert(Set(string1), Set(string2)).transact
        _ <- Ns.ints.Ref.ints.insert(Set(int1), Set(int2)).transact
        _ <- Ns.longs.Ref.longs.insert(Set(long1), Set(long2)).transact
        _ <- Ns.floats.Ref.floats.insert(Set(float1), Set(float2)).transact
        _ <- Ns.doubles.Ref.doubles.insert(Set(double1), Set(double2)).transact
        _ <- Ns.booleans.Ref.booleans.insert(Set(boolean1), Set(boolean2)).transact
        _ <- Ns.bigInts.Ref.bigInts.insert(Set(bigInt1), Set(bigInt2)).transact
        _ <- Ns.bigDecimals.Ref.bigDecimals.insert(Set(bigDecimal1), Set(bigDecimal2)).transact
        _ <- Ns.dates.Ref.dates.insert(Set(date1), Set(date2)).transact
        _ <- Ns.durations.Ref.durations.insert(Set(duration1), Set(duration2)).transact
        _ <- Ns.instants.Ref.instants.insert(Set(instant1), Set(instant2)).transact
        _ <- Ns.localDates.Ref.localDates.insert(Set(localDate1), Set(localDate2)).transact
        _ <- Ns.localTimes.Ref.localTimes.insert(Set(localTime1), Set(localTime2)).transact
        _ <- Ns.localDateTimes.Ref.localDateTimes.insert(Set(localDateTime1), Set(localDateTime2)).transact
        _ <- Ns.offsetTimes.Ref.offsetTimes.insert(Set(offsetTime1), Set(offsetTime2)).transact
        _ <- Ns.offsetDateTimes.Ref.offsetDateTimes.insert(Set(offsetDateTime1), Set(offsetDateTime2)).transact
        _ <- Ns.zonedDateTimes.Ref.zonedDateTimes.insert(Set(zonedDateTime1), Set(zonedDateTime2)).transact
        _ <- Ns.uuids.Ref.uuids.insert(Set(uuid1), Set(uuid2)).transact
        _ <- Ns.uris.Ref.uris.insert(Set(uri1), Set(uri2)).transact
        _ <- Ns.bytes.Ref.bytes.insert(Set(byte1), Set(byte2)).transact
        _ <- Ns.shorts.Ref.shorts.insert(Set(short1), Set(short2)).transact
        _ <- Ns.chars.Ref.chars.insert(Set(char1), Set(char2)).transact

        _ <- Ns.strings.hasNo(Ref.strings_).Ref.strings.query.get.map(_ ==> List((Set(string1), Set(string2))))
        _ <- Ns.ints.hasNo(Ref.ints_).Ref.ints.query.get.map(_ ==> List((Set(int1), Set(int2))))
        _ <- Ns.longs.hasNo(Ref.longs_).Ref.longs.query.get.map(_ ==> List((Set(long1), Set(long2))))
        _ <- Ns.floats.hasNo(Ref.floats_).Ref.floats.query.get.map(_ ==> List((Set(float1), Set(float2))))
        _ <- Ns.doubles.hasNo(Ref.doubles_).Ref.doubles.query.get.map(_ ==> List((Set(double1), Set(double2))))
        _ <- Ns.booleans.hasNo(Ref.booleans_).Ref.booleans.query.get.map(_ ==> List((Set(boolean1), Set(boolean2))))
        _ <- Ns.bigInts.hasNo(Ref.bigInts_).Ref.bigInts.query.get.map(_ ==> List((Set(bigInt1), Set(bigInt2))))
        _ <- Ns.bigDecimals.hasNo(Ref.bigDecimals_).Ref.bigDecimals.query.get.map(_ ==> List((Set(bigDecimal1), Set(bigDecimal2))))
        _ <- Ns.dates.hasNo(Ref.dates_).Ref.dates.query.get.map(_ ==> List((Set(date1), Set(date2))))
        _ <- Ns.durations.hasNo(Ref.durations_).Ref.durations.query.get.map(_ ==> List((Set(duration1), Set(duration2))))
        _ <- Ns.instants.hasNo(Ref.instants_).Ref.instants.query.get.map(_ ==> List((Set(instant1), Set(instant2))))
        _ <- Ns.localDates.hasNo(Ref.localDates_).Ref.localDates.query.get.map(_ ==> List((Set(localDate1), Set(localDate2))))
        _ <- Ns.localTimes.hasNo(Ref.localTimes_).Ref.localTimes.query.get.map(_ ==> List((Set(localTime1), Set(localTime2))))
        _ <- Ns.localDateTimes.hasNo(Ref.localDateTimes_).Ref.localDateTimes.query.get.map(_ ==> List((Set(localDateTime1), Set(localDateTime2))))
        _ <- Ns.offsetTimes.hasNo(Ref.offsetTimes_).Ref.offsetTimes.query.get.map(_ ==> List((Set(offsetTime1), Set(offsetTime2))))
        _ <- Ns.offsetDateTimes.hasNo(Ref.offsetDateTimes_).Ref.offsetDateTimes.query.get.map(_ ==> List((Set(offsetDateTime1), Set(offsetDateTime2))))
        _ <- Ns.zonedDateTimes.hasNo(Ref.zonedDateTimes_).Ref.zonedDateTimes.query.get.map(_ ==> List((Set(zonedDateTime1), Set(zonedDateTime2))))
        _ <- Ns.uuids.hasNo(Ref.uuids_).Ref.uuids.query.get.map(_ ==> List((Set(uuid1), Set(uuid2))))
        _ <- Ns.uris.hasNo(Ref.uris_).Ref.uris.query.get.map(_ ==> List((Set(uri1), Set(uri2))))
        _ <- Ns.bytes.hasNo(Ref.bytes_).Ref.bytes.query.get.map(_ ==> List((Set(byte1), Set(byte2))))
        _ <- Ns.shorts.hasNo(Ref.shorts_).Ref.shorts.query.get.map(_ ==> List((Set(short1), Set(short2))))
        _ <- Ns.chars.hasNo(Ref.chars_).Ref.chars.query.get.map(_ ==> List((Set(char1), Set(char2))))
      } yield ()
    }

    "hasNo one" - types { implicit conn =>
      for {
        _ <- Ns.strings.Ref.string.insert(Set(string1), string2).transact
        _ <- Ns.ints.Ref.int.insert(Set(int1), int2).transact
        _ <- Ns.longs.Ref.long.insert(Set(long1), long2).transact
        _ <- Ns.floats.Ref.float.insert(Set(float1), float2).transact
        _ <- Ns.doubles.Ref.double.insert(Set(double1), double2).transact
        _ <- Ns.booleans.Ref.boolean.insert(Set(boolean1), boolean2).transact
        _ <- Ns.bigInts.Ref.bigInt.insert(Set(bigInt1), bigInt2).transact
        _ <- Ns.bigDecimals.Ref.bigDecimal.insert(Set(bigDecimal1), bigDecimal2).transact
        _ <- Ns.dates.Ref.date.insert(Set(date1), date2).transact
        _ <- Ns.durations.Ref.duration.insert(Set(duration1), duration2).transact
        _ <- Ns.instants.Ref.instant.insert(Set(instant1), instant2).transact
        _ <- Ns.localDates.Ref.localDate.insert(Set(localDate1), localDate2).transact
        _ <- Ns.localTimes.Ref.localTime.insert(Set(localTime1), localTime2).transact
        _ <- Ns.localDateTimes.Ref.localDateTime.insert(Set(localDateTime1), localDateTime2).transact
        _ <- Ns.offsetTimes.Ref.offsetTime.insert(Set(offsetTime1), offsetTime2).transact
        _ <- Ns.offsetDateTimes.Ref.offsetDateTime.insert(Set(offsetDateTime1), offsetDateTime2).transact
        _ <- Ns.zonedDateTimes.Ref.zonedDateTime.insert(Set(zonedDateTime1), zonedDateTime2).transact
        _ <- Ns.uuids.Ref.uuid.insert(Set(uuid1), uuid2).transact
        _ <- Ns.uris.Ref.uri.insert(Set(uri1), uri2).transact
        _ <- Ns.bytes.Ref.byte.insert(Set(byte1), byte2).transact
        _ <- Ns.shorts.Ref.short.insert(Set(short1), short2).transact
        _ <- Ns.chars.Ref.char.insert(Set(char1), char2).transact

        _ <- Ns.strings.hasNo(Ref.string_).Ref.string.query.get.map(_ ==> List((Set(string1), string2)))
        _ <- Ns.ints.hasNo(Ref.int_).Ref.int.query.get.map(_ ==> List((Set(int1), int2)))
        _ <- Ns.longs.hasNo(Ref.long_).Ref.long.query.get.map(_ ==> List((Set(long1), long2)))
        _ <- Ns.floats.hasNo(Ref.float_).Ref.float.query.get.map(_ ==> List((Set(float1), float2)))
        _ <- Ns.doubles.hasNo(Ref.double_).Ref.double.query.get.map(_ ==> List((Set(double1), double2)))
        _ <- Ns.booleans.hasNo(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((Set(boolean1), boolean2)))
        _ <- Ns.bigInts.hasNo(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((Set(bigInt1), bigInt2)))
        _ <- Ns.bigDecimals.hasNo(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((Set(bigDecimal1), bigDecimal2)))
        _ <- Ns.dates.hasNo(Ref.date_).Ref.date.query.get.map(_ ==> List((Set(date1), date2)))
        _ <- Ns.durations.hasNo(Ref.duration_).Ref.duration.query.get.map(_ ==> List((Set(duration1), duration2)))
        _ <- Ns.instants.hasNo(Ref.instant_).Ref.instant.query.get.map(_ ==> List((Set(instant1), instant2)))
        _ <- Ns.localDates.hasNo(Ref.localDate_).Ref.localDate.query.get.map(_ ==> List((Set(localDate1), localDate2)))
        _ <- Ns.localTimes.hasNo(Ref.localTime_).Ref.localTime.query.get.map(_ ==> List((Set(localTime1), localTime2)))
        _ <- Ns.localDateTimes.hasNo(Ref.localDateTime_).Ref.localDateTime.query.get.map(_ ==> List((Set(localDateTime1), localDateTime2)))
        _ <- Ns.offsetTimes.hasNo(Ref.offsetTime_).Ref.offsetTime.query.get.map(_ ==> List((Set(offsetTime1), offsetTime2)))
        _ <- Ns.offsetDateTimes.hasNo(Ref.offsetDateTime_).Ref.offsetDateTime.query.get.map(_ ==> List((Set(offsetDateTime1), offsetDateTime2)))
        _ <- Ns.zonedDateTimes.hasNo(Ref.zonedDateTime_).Ref.zonedDateTime.query.get.map(_ ==> List((Set(zonedDateTime1), zonedDateTime2)))
        _ <- Ns.uuids.hasNo(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((Set(uuid1), uuid2)))
        _ <- Ns.uris.hasNo(Ref.uri_).Ref.uri.query.get.map(_ ==> List((Set(uri1), uri2)))
        _ <- Ns.bytes.hasNo(Ref.byte_).Ref.byte.query.get.map(_ ==> List((Set(byte1), byte2)))
        _ <- Ns.shorts.hasNo(Ref.short_).Ref.short.query.get.map(_ ==> List((Set(short1), short2)))
        _ <- Ns.chars.hasNo(Ref.char_).Ref.char.query.get.map(_ ==> List((Set(char1), char2)))
      } yield ()
    }
  }
}
