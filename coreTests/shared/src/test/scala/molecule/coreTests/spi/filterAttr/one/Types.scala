package molecule.coreTests.spi.filterAttr.one

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait Types extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "equal (apply)" - types { implicit conn =>
      for {
        _ <- Ns.string.Ref.string.insert(string1, string1).transact
        _ <- Ns.int.Ref.int.insert(int1, int1).transact
        _ <- Ns.long.Ref.long.insert(long1, long1).transact
        _ <- Ns.float.Ref.float.insert(float1, float1).transact
        _ <- Ns.double.Ref.double.insert(double1, double1).transact
        _ <- Ns.boolean.Ref.boolean.insert(boolean1, boolean1).transact
        _ <- Ns.bigInt.Ref.bigInt.insert(bigInt1, bigInt1).transact
        _ <- Ns.bigDecimal.Ref.bigDecimal.insert(bigDecimal1, bigDecimal1).transact
        _ <- Ns.date.Ref.date.insert(date1, date1).transact
        _ <- Ns.duration.Ref.duration.insert(duration1, duration1).transact
        _ <- Ns.instant.Ref.instant.insert(instant1, instant1).transact
        _ <- Ns.localDate.Ref.localDate.insert(localDate1, localDate1).transact
        _ <- Ns.localTime.Ref.localTime.insert(localTime1, localTime1).transact
        _ <- Ns.localDateTime.Ref.localDateTime.insert(localDateTime1, localDateTime1).transact
        _ <- Ns.offsetTime.Ref.offsetTime.insert(offsetTime1, offsetTime1).transact
        _ <- Ns.offsetDateTime.Ref.offsetDateTime.insert(offsetDateTime1, offsetDateTime1).transact
        _ <- Ns.zonedDateTime.Ref.zonedDateTime.insert(zonedDateTime1, zonedDateTime1).transact
        _ <- Ns.uuid.Ref.uuid.insert(uuid1, uuid1).transact
        _ <- Ns.uri.Ref.uri.insert(uri1, uri1).transact
        _ <- Ns.byte.Ref.byte.insert(byte1, byte1).transact
        _ <- Ns.short.Ref.short.insert(short1, short1).transact
        _ <- Ns.char.Ref.char.insert(char1, char1).transact

        _ <- Ns.string(Ref.string_).Ref.string.query.get.map(_ ==> List((string1, string1)))
        _ <- Ns.int(Ref.int_).Ref.int.query.get.map(_ ==> List((int1, int1)))
        _ <- Ns.long(Ref.long_).Ref.long.query.get.map(_ ==> List((long1, long1)))
        _ <- Ns.float(Ref.float_).Ref.float.query.get.map(_ ==> List((float1, float1)))
        _ <- Ns.double(Ref.double_).Ref.double.query.get.map(_ ==> List((double1, double1)))
        _ <- Ns.boolean(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((boolean1, boolean1)))
        _ <- Ns.bigInt(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((bigInt1, bigInt1)))
        _ <- Ns.bigDecimal(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((bigDecimal1, bigDecimal1)))
        _ <- Ns.date(Ref.date_).Ref.date.query.get.map(_ ==> List((date1, date1)))
        _ <- Ns.duration(Ref.duration_).Ref.duration.query.get.map(_ ==> List((duration1, duration1)))
        _ <- Ns.instant(Ref.instant_).Ref.instant.query.get.map(_ ==> List((instant1, instant1)))
        _ <- Ns.localDate(Ref.localDate_).Ref.localDate.query.get.map(_ ==> List((localDate1, localDate1)))
        _ <- Ns.localTime(Ref.localTime_).Ref.localTime.query.get.map(_ ==> List((localTime1, localTime1)))
        _ <- Ns.localDateTime(Ref.localDateTime_).Ref.localDateTime.query.get.map(_ ==> List((localDateTime1, localDateTime1)))
        _ <- Ns.offsetTime(Ref.offsetTime_).Ref.offsetTime.query.get.map(_ ==> List((offsetTime1, offsetTime1)))
        _ <- Ns.offsetDateTime(Ref.offsetDateTime_).Ref.offsetDateTime.query.get.map(_ ==> List((offsetDateTime1, offsetDateTime1)))
        _ <- Ns.zonedDateTime(Ref.zonedDateTime_).Ref.zonedDateTime.query.get.map(_ ==> List((zonedDateTime1, zonedDateTime1)))
        _ <- Ns.uuid(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((uuid1, uuid1)))
        _ <- Ns.uri(Ref.uri_).Ref.uri.query.get.map(_ ==> List((uri1, uri1)))
        _ <- Ns.byte(Ref.byte_).Ref.byte.query.get.map(_ ==> List((byte1, byte1)))
        _ <- Ns.short(Ref.short_).Ref.short.query.get.map(_ ==> List((short1, short1)))
        _ <- Ns.char(Ref.char_).Ref.char.query.get.map(_ ==> List((char1, char1)))
      } yield ()
    }


    "not equal" - types { implicit conn =>
      for {
        _ <- Ns.string.Ref.string.insert(string1, string2).transact
        _ <- Ns.int.Ref.int.insert(int1, int2).transact
        _ <- Ns.long.Ref.long.insert(long1, long2).transact
        _ <- Ns.float.Ref.float.insert(float1, float2).transact
        _ <- Ns.double.Ref.double.insert(double1, double2).transact
        _ <- Ns.boolean.Ref.boolean.insert(boolean1, boolean2).transact
        _ <- Ns.bigInt.Ref.bigInt.insert(bigInt1, bigInt2).transact
        _ <- Ns.bigDecimal.Ref.bigDecimal.insert(bigDecimal1, bigDecimal2).transact
        _ <- Ns.date.Ref.date.insert(date1, date2).transact
        _ <- Ns.duration.Ref.duration.insert(duration1, duration2).transact
        _ <- Ns.instant.Ref.instant.insert(instant1, instant2).transact
        _ <- Ns.localDate.Ref.localDate.insert(localDate1, localDate2).transact
        _ <- Ns.localTime.Ref.localTime.insert(localTime1, localTime2).transact
        _ <- Ns.localDateTime.Ref.localDateTime.insert(localDateTime1, localDateTime2).transact
        _ <- Ns.offsetTime.Ref.offsetTime.insert(offsetTime1, offsetTime2).transact
        _ <- Ns.offsetDateTime.Ref.offsetDateTime.insert(offsetDateTime1, offsetDateTime2).transact
        _ <- Ns.zonedDateTime.Ref.zonedDateTime.insert(zonedDateTime1, zonedDateTime2).transact
        _ <- Ns.uuid.Ref.uuid.insert(uuid1, uuid2).transact
        _ <- Ns.uri.Ref.uri.insert(uri1, uri2).transact
        _ <- Ns.byte.Ref.byte.insert(byte1, byte2).transact
        _ <- Ns.short.Ref.short.insert(short1, short2).transact
        _ <- Ns.char.Ref.char.insert(char1, char2).transact

        _ <- Ns.string.not(Ref.string_).Ref.string.query.get.map(_ ==> List((string1, string2)))
        _ <- Ns.int.not(Ref.int_).Ref.int.query.get.map(_ ==> List((int1, int2)))
        _ <- Ns.long.not(Ref.long_).Ref.long.query.get.map(_ ==> List((long1, long2)))
        _ <- Ns.float.not(Ref.float_).Ref.float.query.get.map(_ ==> List((float1, float2)))
        _ <- Ns.double.not(Ref.double_).Ref.double.query.get.map(_ ==> List((double1, double2)))
        _ <- Ns.boolean.not(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((boolean1, boolean2)))
        _ <- Ns.bigInt.not(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((bigInt1, bigInt2)))
        _ <- Ns.bigDecimal.not(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((bigDecimal1, bigDecimal2)))
        _ <- Ns.date.not(Ref.date_).Ref.date.query.get.map(_ ==> List((date1, date2)))
        _ <- Ns.duration.not(Ref.duration_).Ref.duration.query.get.map(_ ==> List((duration1, duration2)))
        _ <- Ns.instant.not(Ref.instant_).Ref.instant.query.get.map(_ ==> List((instant1, instant2)))
        _ <- Ns.localDate.not(Ref.localDate_).Ref.localDate.query.get.map(_ ==> List((localDate1, localDate2)))
        _ <- Ns.localTime.not(Ref.localTime_).Ref.localTime.query.get.map(_ ==> List((localTime1, localTime2)))
        _ <- Ns.localDateTime.not(Ref.localDateTime_).Ref.localDateTime.query.get.map(_ ==> List((localDateTime1, localDateTime2)))
        _ <- Ns.offsetTime.not(Ref.offsetTime_).Ref.offsetTime.query.get.map(_ ==> List((offsetTime1, offsetTime2)))
        _ <- Ns.offsetDateTime.not(Ref.offsetDateTime_).Ref.offsetDateTime.query.get.map(_ ==> List((offsetDateTime1, offsetDateTime2)))
        _ <- Ns.zonedDateTime.not(Ref.zonedDateTime_).Ref.zonedDateTime.query.get.map(_ ==> List((zonedDateTime1, zonedDateTime2)))
        _ <- Ns.uuid.not(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((uuid1, uuid2)))
        _ <- Ns.uri.not(Ref.uri_).Ref.uri.query.get.map(_ ==> List((uri1, uri2)))
        _ <- Ns.byte.not(Ref.byte_).Ref.byte.query.get.map(_ ==> List((byte1, byte2)))
        _ <- Ns.short.not(Ref.short_).Ref.short.query.get.map(_ ==> List((short1, short2)))
        _ <- Ns.char.not(Ref.char_).Ref.char.query.get.map(_ ==> List((char1, char2)))
      } yield ()
    }


    "<" - types { implicit conn =>
      for {
        _ <- Ns.string.Ref.string.insert(string1, string2).transact
        _ <- Ns.int.Ref.int.insert(int1, int2).transact
        _ <- Ns.long.Ref.long.insert(long1, long2).transact
        _ <- Ns.float.Ref.float.insert(float1, float2).transact
        _ <- Ns.double.Ref.double.insert(double1, double2).transact
        _ <- Ns.boolean.Ref.boolean.insert(boolean1, boolean2).transact
        _ <- Ns.bigInt.Ref.bigInt.insert(bigInt1, bigInt2).transact
        _ <- Ns.bigDecimal.Ref.bigDecimal.insert(bigDecimal1, bigDecimal2).transact
        _ <- Ns.date.Ref.date.insert(date1, date2).transact
        _ <- Ns.duration.Ref.duration.insert(duration1, duration2).transact
        _ <- Ns.instant.Ref.instant.insert(instant1, instant2).transact
        _ <- Ns.localDate.Ref.localDate.insert(localDate1, localDate2).transact
        _ <- Ns.localTime.Ref.localTime.insert(localTime1, localTime2).transact
        _ <- Ns.localDateTime.Ref.localDateTime.insert(localDateTime1, localDateTime2).transact
        _ <- Ns.offsetTime.Ref.offsetTime.insert(offsetTime1, offsetTime2).transact
        _ <- Ns.offsetDateTime.Ref.offsetDateTime.insert(offsetDateTime1, offsetDateTime2).transact
        _ <- Ns.zonedDateTime.Ref.zonedDateTime.insert(zonedDateTime1, zonedDateTime2).transact
        _ <- Ns.uuid.Ref.uuid.insert(uuid1, uuid2).transact
        _ <- Ns.uri.Ref.uri.insert(uri1, uri2).transact
        _ <- Ns.byte.Ref.byte.insert(byte1, byte2).transact
        _ <- Ns.short.Ref.short.insert(short1, short2).transact
        _ <- Ns.char.Ref.char.insert(char1, char2).transact

        _ <- Ns.string.<(Ref.string_).Ref.string.query.get.map(_ ==> List((string1, string2)))
        _ <- Ns.int.<(Ref.int_).Ref.int.query.get.map(_ ==> List((int1, int2)))
        _ <- Ns.long.<(Ref.long_).Ref.long.query.get.map(_ ==> List((long1, long2)))
        _ <- Ns.float.<(Ref.float_).Ref.float.query.get.map(_ ==> List((float1, float2)))
        _ <- Ns.double.<(Ref.double_).Ref.double.query.get.map(_ ==> List((double1, double2)))
        _ <- Ns.boolean.<(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((boolean1, boolean2)))
        _ <- Ns.bigInt.<(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((bigInt1, bigInt2)))
        _ <- Ns.bigDecimal.<(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((bigDecimal1, bigDecimal2)))
        _ <- Ns.date.<(Ref.date_).Ref.date.query.get.map(_ ==> List((date1, date2)))
        _ <- Ns.duration.<(Ref.duration_).Ref.duration.query.get.map(_ ==> List((duration1, duration2)))
        _ <- Ns.instant.<(Ref.instant_).Ref.instant.query.get.map(_ ==> List((instant1, instant2)))
        _ <- Ns.localDate.<(Ref.localDate_).Ref.localDate.query.get.map(_ ==> List((localDate1, localDate2)))
        _ <- Ns.localTime.<(Ref.localTime_).Ref.localTime.query.get.map(_ ==> List((localTime1, localTime2)))
        _ <- Ns.localDateTime.<(Ref.localDateTime_).Ref.localDateTime.query.get.map(_ ==> List((localDateTime1, localDateTime2)))
        _ <- Ns.offsetTime.<(Ref.offsetTime_).Ref.offsetTime.query.get.map(_ ==> List((offsetTime1, offsetTime2)))
        _ <- Ns.offsetDateTime.<(Ref.offsetDateTime_).Ref.offsetDateTime.query.get.map(_ ==> List((offsetDateTime1, offsetDateTime2)))
        _ <- Ns.zonedDateTime.<(Ref.zonedDateTime_).Ref.zonedDateTime.query.get.map(_ ==> List((zonedDateTime1, zonedDateTime2)))
        _ <- Ns.uuid.<(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((uuid1, uuid2)))
        _ <- Ns.uri.<(Ref.uri_).Ref.uri.query.get.map(_ ==> List((uri1, uri2)))
        _ <- Ns.byte.<(Ref.byte_).Ref.byte.query.get.map(_ ==> List((byte1, byte2)))
        _ <- Ns.short.<(Ref.short_).Ref.short.query.get.map(_ ==> List((short1, short2)))
        _ <- Ns.char.<(Ref.char_).Ref.char.query.get.map(_ ==> List((char1, char2)))
      } yield ()
    }


    "<=" - types { implicit conn =>
      for {
        _ <- Ns.string.Ref.string.insert(string1, string2).transact
        _ <- Ns.int.Ref.int.insert(int1, int2).transact
        _ <- Ns.long.Ref.long.insert(long1, long2).transact
        _ <- Ns.float.Ref.float.insert(float1, float2).transact
        _ <- Ns.double.Ref.double.insert(double1, double2).transact
        _ <- Ns.boolean.Ref.boolean.insert(boolean1, boolean2).transact
        _ <- Ns.bigInt.Ref.bigInt.insert(bigInt1, bigInt2).transact
        _ <- Ns.bigDecimal.Ref.bigDecimal.insert(bigDecimal1, bigDecimal2).transact
        _ <- Ns.date.Ref.date.insert(date1, date2).transact
        _ <- Ns.duration.Ref.duration.insert(duration1, duration2).transact
        _ <- Ns.instant.Ref.instant.insert(instant1, instant2).transact
        _ <- Ns.localDate.Ref.localDate.insert(localDate1, localDate2).transact
        _ <- Ns.localTime.Ref.localTime.insert(localTime1, localTime2).transact
        _ <- Ns.localDateTime.Ref.localDateTime.insert(localDateTime1, localDateTime2).transact
        _ <- Ns.offsetTime.Ref.offsetTime.insert(offsetTime1, offsetTime2).transact
        _ <- Ns.offsetDateTime.Ref.offsetDateTime.insert(offsetDateTime1, offsetDateTime2).transact
        _ <- Ns.zonedDateTime.Ref.zonedDateTime.insert(zonedDateTime1, zonedDateTime2).transact
        _ <- Ns.uuid.Ref.uuid.insert(uuid1, uuid2).transact
        _ <- Ns.uri.Ref.uri.insert(uri1, uri2).transact
        _ <- Ns.byte.Ref.byte.insert(byte1, byte2).transact
        _ <- Ns.short.Ref.short.insert(short1, short2).transact
        _ <- Ns.char.Ref.char.insert(char1, char2).transact

        _ <- Ns.string.<=(Ref.string_).Ref.string.query.get.map(_ ==> List((string1, string2)))
        _ <- Ns.int.<=(Ref.int_).Ref.int.query.get.map(_ ==> List((int1, int2)))
        _ <- Ns.long.<=(Ref.long_).Ref.long.query.get.map(_ ==> List((long1, long2)))
        _ <- Ns.float.<=(Ref.float_).Ref.float.query.get.map(_ ==> List((float1, float2)))
        _ <- Ns.double.<=(Ref.double_).Ref.double.query.get.map(_ ==> List((double1, double2)))
        _ <- Ns.boolean.<=(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((boolean1, boolean2)))
        _ <- Ns.bigInt.<=(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((bigInt1, bigInt2)))
        _ <- Ns.bigDecimal.<=(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((bigDecimal1, bigDecimal2)))
        _ <- Ns.date.<=(Ref.date_).Ref.date.query.get.map(_ ==> List((date1, date2)))
        _ <- Ns.duration.<=(Ref.duration_).Ref.duration.query.get.map(_ ==> List((duration1, duration2)))
        _ <- Ns.instant.<=(Ref.instant_).Ref.instant.query.get.map(_ ==> List((instant1, instant2)))
        _ <- Ns.localDate.<=(Ref.localDate_).Ref.localDate.query.get.map(_ ==> List((localDate1, localDate2)))
        _ <- Ns.localTime.<=(Ref.localTime_).Ref.localTime.query.get.map(_ ==> List((localTime1, localTime2)))
        _ <- Ns.localDateTime.<=(Ref.localDateTime_).Ref.localDateTime.query.get.map(_ ==> List((localDateTime1, localDateTime2)))
        _ <- Ns.offsetTime.<=(Ref.offsetTime_).Ref.offsetTime.query.get.map(_ ==> List((offsetTime1, offsetTime2)))
        _ <- Ns.offsetDateTime.<=(Ref.offsetDateTime_).Ref.offsetDateTime.query.get.map(_ ==> List((offsetDateTime1, offsetDateTime2)))
        _ <- Ns.zonedDateTime.<=(Ref.zonedDateTime_).Ref.zonedDateTime.query.get.map(_ ==> List((zonedDateTime1, zonedDateTime2)))
        _ <- Ns.uuid.<=(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((uuid1, uuid2)))
        _ <- Ns.uri.<=(Ref.uri_).Ref.uri.query.get.map(_ ==> List((uri1, uri2)))
        _ <- Ns.byte.<=(Ref.byte_).Ref.byte.query.get.map(_ ==> List((byte1, byte2)))
        _ <- Ns.short.<=(Ref.short_).Ref.short.query.get.map(_ ==> List((short1, short2)))
        _ <- Ns.char.<=(Ref.char_).Ref.char.query.get.map(_ ==> List((char1, char2)))
      } yield ()
    }


    ">" - types { implicit conn =>
      for {
        _ <- Ns.string.Ref.string.insert(string2, string1).transact
        _ <- Ns.int.Ref.int.insert(int2, int1).transact
        _ <- Ns.long.Ref.long.insert(long2, long1).transact
        _ <- Ns.float.Ref.float.insert(float2, float1).transact
        _ <- Ns.double.Ref.double.insert(double2, double1).transact
        _ <- Ns.boolean.Ref.boolean.insert(boolean2, boolean1).transact
        _ <- Ns.bigInt.Ref.bigInt.insert(bigInt2, bigInt1).transact
        _ <- Ns.bigDecimal.Ref.bigDecimal.insert(bigDecimal2, bigDecimal1).transact
        _ <- Ns.date.Ref.date.insert(date2, date1).transact
        _ <- Ns.duration.Ref.duration.insert(duration2, duration1).transact
        _ <- Ns.instant.Ref.instant.insert(instant2, instant1).transact
        _ <- Ns.localDate.Ref.localDate.insert(localDate2, localDate1).transact
        _ <- Ns.localTime.Ref.localTime.insert(localTime2, localTime1).transact
        _ <- Ns.localDateTime.Ref.localDateTime.insert(localDateTime2, localDateTime1).transact
        _ <- Ns.offsetTime.Ref.offsetTime.insert(offsetTime2, offsetTime1).transact
        _ <- Ns.offsetDateTime.Ref.offsetDateTime.insert(offsetDateTime2, offsetDateTime1).transact
        _ <- Ns.zonedDateTime.Ref.zonedDateTime.insert(zonedDateTime2, zonedDateTime1).transact
        _ <- Ns.uuid.Ref.uuid.insert(uuid2, uuid1).transact
        _ <- Ns.uri.Ref.uri.insert(uri2, uri1).transact
        _ <- Ns.byte.Ref.byte.insert(byte2, byte1).transact
        _ <- Ns.short.Ref.short.insert(short2, short1).transact
        _ <- Ns.char.Ref.char.insert(char2, char1).transact

        _ <- Ns.string.>(Ref.string_).Ref.string.query.get.map(_ ==> List((string2, string1)))
        _ <- Ns.int.>(Ref.int_).Ref.int.query.get.map(_ ==> List((int2, int1)))
        _ <- Ns.long.>(Ref.long_).Ref.long.query.get.map(_ ==> List((long2, long1)))
        _ <- Ns.float.>(Ref.float_).Ref.float.query.get.map(_ ==> List((float2, float1)))
        _ <- Ns.double.>(Ref.double_).Ref.double.query.get.map(_ ==> List((double2, double1)))
        _ <- Ns.boolean.>(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((boolean2, boolean1)))
        _ <- Ns.bigInt.>(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((bigInt2, bigInt1)))
        _ <- Ns.bigDecimal.>(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((bigDecimal2, bigDecimal1)))
        _ <- Ns.date.>(Ref.date_).Ref.date.query.get.map(_ ==> List((date2, date1)))
        _ <- Ns.duration.>(Ref.duration_).Ref.duration.query.get.map(_ ==> List((duration2, duration1)))
        _ <- Ns.instant.>(Ref.instant_).Ref.instant.query.get.map(_ ==> List((instant2, instant1)))
        _ <- Ns.localDate.>(Ref.localDate_).Ref.localDate.query.get.map(_ ==> List((localDate2, localDate1)))
        _ <- Ns.localTime.>(Ref.localTime_).Ref.localTime.query.get.map(_ ==> List((localTime2, localTime1)))
        _ <- Ns.localDateTime.>(Ref.localDateTime_).Ref.localDateTime.query.get.map(_ ==> List((localDateTime2, localDateTime1)))
        _ <- Ns.offsetTime.>(Ref.offsetTime_).Ref.offsetTime.query.get.map(_ ==> List((offsetTime2, offsetTime1)))
        _ <- Ns.offsetDateTime.>(Ref.offsetDateTime_).Ref.offsetDateTime.query.get.map(_ ==> List((offsetDateTime2, offsetDateTime1)))
        _ <- Ns.zonedDateTime.>(Ref.zonedDateTime_).Ref.zonedDateTime.query.get.map(_ ==> List((zonedDateTime2, zonedDateTime1)))
        _ <- Ns.uuid.>(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((uuid2, uuid1)))
        _ <- Ns.uri.>(Ref.uri_).Ref.uri.query.get.map(_ ==> List((uri2, uri1)))
        _ <- Ns.byte.>(Ref.byte_).Ref.byte.query.get.map(_ ==> List((byte2, byte1)))
        _ <- Ns.short.>(Ref.short_).Ref.short.query.get.map(_ ==> List((short2, short1)))
        _ <- Ns.char.>(Ref.char_).Ref.char.query.get.map(_ ==> List((char2, char1)))
      } yield ()
    }


    ">=" - types { implicit conn =>
      for {
        _ <- Ns.string.Ref.string.insert(string2, string1).transact
        _ <- Ns.int.Ref.int.insert(int2, int1).transact
        _ <- Ns.long.Ref.long.insert(long2, long1).transact
        _ <- Ns.float.Ref.float.insert(float2, float1).transact
        _ <- Ns.double.Ref.double.insert(double2, double1).transact
        _ <- Ns.boolean.Ref.boolean.insert(boolean2, boolean1).transact
        _ <- Ns.bigInt.Ref.bigInt.insert(bigInt2, bigInt1).transact
        _ <- Ns.bigDecimal.Ref.bigDecimal.insert(bigDecimal2, bigDecimal1).transact
        _ <- Ns.date.Ref.date.insert(date2, date1).transact
        _ <- Ns.duration.Ref.duration.insert(duration2, duration1).transact
        _ <- Ns.instant.Ref.instant.insert(instant2, instant1).transact
        _ <- Ns.localDate.Ref.localDate.insert(localDate2, localDate1).transact
        _ <- Ns.localTime.Ref.localTime.insert(localTime2, localTime1).transact
        _ <- Ns.localDateTime.Ref.localDateTime.insert(localDateTime2, localDateTime1).transact
        _ <- Ns.offsetTime.Ref.offsetTime.insert(offsetTime2, offsetTime1).transact
        _ <- Ns.offsetDateTime.Ref.offsetDateTime.insert(offsetDateTime2, offsetDateTime1).transact
        _ <- Ns.zonedDateTime.Ref.zonedDateTime.insert(zonedDateTime2, zonedDateTime1).transact
        _ <- Ns.uuid.Ref.uuid.insert(uuid2, uuid1).transact
        _ <- Ns.uri.Ref.uri.insert(uri2, uri1).transact
        _ <- Ns.byte.Ref.byte.insert(byte2, byte1).transact
        _ <- Ns.short.Ref.short.insert(short2, short1).transact
        _ <- Ns.char.Ref.char.insert(char2, char1).transact

        _ <- Ns.string.>=(Ref.string_).Ref.string.query.get.map(_ ==> List((string2, string1)))
        _ <- Ns.int.>=(Ref.int_).Ref.int.query.get.map(_ ==> List((int2, int1)))
        _ <- Ns.long.>=(Ref.long_).Ref.long.query.get.map(_ ==> List((long2, long1)))
        _ <- Ns.float.>=(Ref.float_).Ref.float.query.get.map(_ ==> List((float2, float1)))
        _ <- Ns.double.>=(Ref.double_).Ref.double.query.get.map(_ ==> List((double2, double1)))
        _ <- Ns.boolean.>=(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((boolean2, boolean1)))
        _ <- Ns.bigInt.>=(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((bigInt2, bigInt1)))
        _ <- Ns.bigDecimal.>=(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((bigDecimal2, bigDecimal1)))
        _ <- Ns.date.>=(Ref.date_).Ref.date.query.get.map(_ ==> List((date2, date1)))
        _ <- Ns.duration.>=(Ref.duration_).Ref.duration.query.get.map(_ ==> List((duration2, duration1)))
        _ <- Ns.instant.>=(Ref.instant_).Ref.instant.query.get.map(_ ==> List((instant2, instant1)))
        _ <- Ns.localDate.>=(Ref.localDate_).Ref.localDate.query.get.map(_ ==> List((localDate2, localDate1)))
        _ <- Ns.localTime.>=(Ref.localTime_).Ref.localTime.query.get.map(_ ==> List((localTime2, localTime1)))
        _ <- Ns.localDateTime.>=(Ref.localDateTime_).Ref.localDateTime.query.get.map(_ ==> List((localDateTime2, localDateTime1)))
        _ <- Ns.offsetTime.>=(Ref.offsetTime_).Ref.offsetTime.query.get.map(_ ==> List((offsetTime2, offsetTime1)))
        _ <- Ns.offsetDateTime.>=(Ref.offsetDateTime_).Ref.offsetDateTime.query.get.map(_ ==> List((offsetDateTime2, offsetDateTime1)))
        _ <- Ns.zonedDateTime.>=(Ref.zonedDateTime_).Ref.zonedDateTime.query.get.map(_ ==> List((zonedDateTime2, zonedDateTime1)))
        _ <- Ns.uuid.>=(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((uuid2, uuid1)))
        _ <- Ns.uri.>=(Ref.uri_).Ref.uri.query.get.map(_ ==> List((uri2, uri1)))
        _ <- Ns.byte.>=(Ref.byte_).Ref.byte.query.get.map(_ ==> List((byte2, byte1)))
        _ <- Ns.short.>=(Ref.short_).Ref.short.query.get.map(_ ==> List((short2, short1)))
        _ <- Ns.char.>=(Ref.char_).Ref.char.query.get.map(_ ==> List((char2, char1)))
      } yield ()
    }
  }
}
