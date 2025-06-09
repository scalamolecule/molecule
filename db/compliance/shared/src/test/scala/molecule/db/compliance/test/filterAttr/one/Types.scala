package molecule.db.compliance.test.filterAttr.one

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class Types(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "equal (apply)" - types { implicit conn =>
    for {
      _ <- Entity.string.Ref.string.insert(string1, string1).transact
      _ <- Entity.int.Ref.int.insert(int1, int1).transact
      _ <- Entity.long.Ref.long.insert(long1, long1).transact
      _ <- Entity.float.Ref.float.insert(float1, float1).transact
      _ <- Entity.double.Ref.double.insert(double1, double1).transact
      _ <- Entity.boolean.Ref.boolean.insert(boolean1, boolean1).transact
      _ <- Entity.bigInt.Ref.bigInt.insert(bigInt1, bigInt1).transact
      _ <- Entity.bigDecimal.Ref.bigDecimal.insert(bigDecimal1, bigDecimal1).transact
      _ <- Entity.date.Ref.date.insert(date1, date1).transact
      _ <- Entity.duration.Ref.duration.insert(duration1, duration1).transact
      _ <- Entity.instant.Ref.instant.insert(instant1, instant1).transact
      _ <- Entity.localDate.Ref.localDate.insert(localDate1, localDate1).transact
      _ <- Entity.localTime.Ref.localTime.insert(localTime1, localTime1).transact
      _ <- Entity.localDateTime.Ref.localDateTime.insert(localDateTime1, localDateTime1).transact
      _ <- Entity.offsetTime.Ref.offsetTime.insert(offsetTime1, offsetTime1).transact
      _ <- Entity.offsetDateTime.Ref.offsetDateTime.insert(offsetDateTime1, offsetDateTime1).transact
      _ <- Entity.zonedDateTime.Ref.zonedDateTime.insert(zonedDateTime1, zonedDateTime1).transact
      _ <- Entity.uuid.Ref.uuid.insert(uuid1, uuid1).transact
      _ <- Entity.uri.Ref.uri.insert(uri1, uri1).transact
      _ <- Entity.byte.Ref.byte.insert(byte1, byte1).transact
      _ <- Entity.short.Ref.short.insert(short1, short1).transact
      _ <- Entity.char.Ref.char.insert(char1, char1).transact

      _ <- Entity.string(Ref.string_).Ref.string.query.get.map(_ ==> List((string1, string1)))
      _ <- Entity.int(Ref.int_).Ref.int.query.get.map(_ ==> List((int1, int1)))
      _ <- Entity.long(Ref.long_).Ref.long.query.get.map(_ ==> List((long1, long1)))
      _ <- Entity.float(Ref.float_).Ref.float.query.get.map(_ ==> List((float1, float1)))
      _ <- Entity.double(Ref.double_).Ref.double.query.get.map(_ ==> List((double1, double1)))
      _ <- Entity.boolean(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((boolean1, boolean1)))
      _ <- Entity.bigInt(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((bigInt1, bigInt1)))
      _ <- Entity.bigDecimal(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((bigDecimal1, bigDecimal1)))
      _ <- Entity.date(Ref.date_).Ref.date.query.get.map(_ ==> List((date1, date1)))
      _ <- Entity.duration(Ref.duration_).Ref.duration.query.get.map(_ ==> List((duration1, duration1)))
      _ <- Entity.instant(Ref.instant_).Ref.instant.query.get.map(_ ==> List((instant1, instant1)))
      _ <- Entity.localDate(Ref.localDate_).Ref.localDate.query.get.map(_ ==> List((localDate1, localDate1)))
      _ <- Entity.localTime(Ref.localTime_).Ref.localTime.query.get.map(_ ==> List((localTime1, localTime1)))
      _ <- Entity.localDateTime(Ref.localDateTime_).Ref.localDateTime.query.get.map(_ ==> List((localDateTime1, localDateTime1)))
      _ <- Entity.offsetTime(Ref.offsetTime_).Ref.offsetTime.query.get.map(_ ==> List((offsetTime1, offsetTime1)))
      _ <- Entity.offsetDateTime(Ref.offsetDateTime_).Ref.offsetDateTime.query.get.map(_ ==> List((offsetDateTime1, offsetDateTime1)))
      _ <- Entity.zonedDateTime(Ref.zonedDateTime_).Ref.zonedDateTime.query.get.map(_ ==> List((zonedDateTime1, zonedDateTime1)))
      _ <- Entity.uuid(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((uuid1, uuid1)))
      _ <- Entity.uri(Ref.uri_).Ref.uri.query.get.map(_ ==> List((uri1, uri1)))
      _ <- Entity.byte(Ref.byte_).Ref.byte.query.get.map(_ ==> List((byte1, byte1)))
      _ <- Entity.short(Ref.short_).Ref.short.query.get.map(_ ==> List((short1, short1)))
      _ <- Entity.char(Ref.char_).Ref.char.query.get.map(_ ==> List((char1, char1)))
    } yield ()
  }


  "not equal" - types { implicit conn =>
    for {
      _ <- Entity.string.Ref.string.insert(string1, string2).transact
      _ <- Entity.int.Ref.int.insert(int1, int2).transact
      _ <- Entity.long.Ref.long.insert(long1, long2).transact
      _ <- Entity.float.Ref.float.insert(float1, float2).transact
      _ <- Entity.double.Ref.double.insert(double1, double2).transact
      _ <- Entity.boolean.Ref.boolean.insert(boolean1, boolean2).transact
      _ <- Entity.bigInt.Ref.bigInt.insert(bigInt1, bigInt2).transact
      _ <- Entity.bigDecimal.Ref.bigDecimal.insert(bigDecimal1, bigDecimal2).transact
      _ <- Entity.date.Ref.date.insert(date1, date2).transact
      _ <- Entity.duration.Ref.duration.insert(duration1, duration2).transact
      _ <- Entity.instant.Ref.instant.insert(instant1, instant2).transact
      _ <- Entity.localDate.Ref.localDate.insert(localDate1, localDate2).transact
      _ <- Entity.localTime.Ref.localTime.insert(localTime1, localTime2).transact
      _ <- Entity.localDateTime.Ref.localDateTime.insert(localDateTime1, localDateTime2).transact
      _ <- Entity.offsetTime.Ref.offsetTime.insert(offsetTime1, offsetTime2).transact
      _ <- Entity.offsetDateTime.Ref.offsetDateTime.insert(offsetDateTime1, offsetDateTime2).transact
      _ <- Entity.zonedDateTime.Ref.zonedDateTime.insert(zonedDateTime1, zonedDateTime2).transact
      _ <- Entity.uuid.Ref.uuid.insert(uuid1, uuid2).transact
      _ <- Entity.uri.Ref.uri.insert(uri1, uri2).transact
      _ <- Entity.byte.Ref.byte.insert(byte1, byte2).transact
      _ <- Entity.short.Ref.short.insert(short1, short2).transact
      _ <- Entity.char.Ref.char.insert(char1, char2).transact

      _ <- Entity.string.not(Ref.string_).Ref.string.query.get.map(_ ==> List((string1, string2)))
      _ <- Entity.int.not(Ref.int_).Ref.int.query.get.map(_ ==> List((int1, int2)))
      _ <- Entity.long.not(Ref.long_).Ref.long.query.get.map(_ ==> List((long1, long2)))
      _ <- Entity.float.not(Ref.float_).Ref.float.query.get.map(_ ==> List((float1, float2)))
      _ <- Entity.double.not(Ref.double_).Ref.double.query.get.map(_ ==> List((double1, double2)))
      _ <- Entity.boolean.not(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((boolean1, boolean2)))
      _ <- Entity.bigInt.not(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((bigInt1, bigInt2)))
      _ <- Entity.bigDecimal.not(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((bigDecimal1, bigDecimal2)))
      _ <- Entity.date.not(Ref.date_).Ref.date.query.get.map(_ ==> List((date1, date2)))
      _ <- Entity.duration.not(Ref.duration_).Ref.duration.query.get.map(_ ==> List((duration1, duration2)))
      _ <- Entity.instant.not(Ref.instant_).Ref.instant.query.get.map(_ ==> List((instant1, instant2)))
      _ <- Entity.localDate.not(Ref.localDate_).Ref.localDate.query.get.map(_ ==> List((localDate1, localDate2)))
      _ <- Entity.localTime.not(Ref.localTime_).Ref.localTime.query.get.map(_ ==> List((localTime1, localTime2)))
      _ <- Entity.localDateTime.not(Ref.localDateTime_).Ref.localDateTime.query.get.map(_ ==> List((localDateTime1, localDateTime2)))
      _ <- Entity.offsetTime.not(Ref.offsetTime_).Ref.offsetTime.query.get.map(_ ==> List((offsetTime1, offsetTime2)))
      _ <- Entity.offsetDateTime.not(Ref.offsetDateTime_).Ref.offsetDateTime.query.get.map(_ ==> List((offsetDateTime1, offsetDateTime2)))
      _ <- Entity.zonedDateTime.not(Ref.zonedDateTime_).Ref.zonedDateTime.query.get.map(_ ==> List((zonedDateTime1, zonedDateTime2)))
      _ <- Entity.uuid.not(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((uuid1, uuid2)))
      _ <- Entity.uri.not(Ref.uri_).Ref.uri.query.get.map(_ ==> List((uri1, uri2)))
      _ <- Entity.byte.not(Ref.byte_).Ref.byte.query.get.map(_ ==> List((byte1, byte2)))
      _ <- Entity.short.not(Ref.short_).Ref.short.query.get.map(_ ==> List((short1, short2)))
      _ <- Entity.char.not(Ref.char_).Ref.char.query.get.map(_ ==> List((char1, char2)))
    } yield ()
  }


  "<" - types { implicit conn =>
    for {
      _ <- Entity.string.Ref.string.insert(string1, string2).transact
      _ <- Entity.int.Ref.int.insert(int1, int2).transact
      _ <- Entity.long.Ref.long.insert(long1, long2).transact
      _ <- Entity.float.Ref.float.insert(float1, float2).transact
      _ <- Entity.double.Ref.double.insert(double1, double2).transact
      _ <- Entity.boolean.Ref.boolean.insert(boolean1, boolean2).transact
      _ <- Entity.bigInt.Ref.bigInt.insert(bigInt1, bigInt2).transact
      _ <- Entity.bigDecimal.Ref.bigDecimal.insert(bigDecimal1, bigDecimal2).transact
      _ <- Entity.date.Ref.date.insert(date1, date2).transact
      _ <- Entity.duration.Ref.duration.insert(duration1, duration2).transact
      _ <- Entity.instant.Ref.instant.insert(instant1, instant2).transact
      _ <- Entity.localDate.Ref.localDate.insert(localDate1, localDate2).transact
      _ <- Entity.localTime.Ref.localTime.insert(localTime1, localTime2).transact
      _ <- Entity.localDateTime.Ref.localDateTime.insert(localDateTime1, localDateTime2).transact
      _ <- Entity.offsetTime.Ref.offsetTime.insert(offsetTime1, offsetTime2).transact
      _ <- Entity.offsetDateTime.Ref.offsetDateTime.insert(offsetDateTime1, offsetDateTime2).transact
      _ <- Entity.zonedDateTime.Ref.zonedDateTime.insert(zonedDateTime1, zonedDateTime2).transact
      _ <- Entity.uuid.Ref.uuid.insert(uuid1, uuid2).transact
      _ <- Entity.uri.Ref.uri.insert(uri1, uri2).transact
      _ <- Entity.byte.Ref.byte.insert(byte1, byte2).transact
      _ <- Entity.short.Ref.short.insert(short1, short2).transact
      _ <- Entity.char.Ref.char.insert(char1, char2).transact

      _ <- Entity.string.<(Ref.string_).Ref.string.query.get.map(_ ==> List((string1, string2)))
      _ <- Entity.int.<(Ref.int_).Ref.int.query.get.map(_ ==> List((int1, int2)))
      _ <- Entity.long.<(Ref.long_).Ref.long.query.get.map(_ ==> List((long1, long2)))
      _ <- Entity.float.<(Ref.float_).Ref.float.query.get.map(_ ==> List((float1, float2)))
      _ <- Entity.double.<(Ref.double_).Ref.double.query.get.map(_ ==> List((double1, double2)))
      _ <- Entity.boolean.<(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((boolean1, boolean2)))
      _ <- Entity.bigInt.<(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((bigInt1, bigInt2)))
      _ <- Entity.bigDecimal.<(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((bigDecimal1, bigDecimal2)))
      _ <- Entity.date.<(Ref.date_).Ref.date.query.get.map(_ ==> List((date1, date2)))
      _ <- Entity.duration.<(Ref.duration_).Ref.duration.query.get.map(_ ==> List((duration1, duration2)))
      _ <- Entity.instant.<(Ref.instant_).Ref.instant.query.get.map(_ ==> List((instant1, instant2)))
      _ <- Entity.localDate.<(Ref.localDate_).Ref.localDate.query.get.map(_ ==> List((localDate1, localDate2)))
      _ <- Entity.localTime.<(Ref.localTime_).Ref.localTime.query.get.map(_ ==> List((localTime1, localTime2)))
      _ <- Entity.localDateTime.<(Ref.localDateTime_).Ref.localDateTime.query.get.map(_ ==> List((localDateTime1, localDateTime2)))
      _ <- Entity.offsetTime.<(Ref.offsetTime_).Ref.offsetTime.query.get.map(_ ==> List((offsetTime1, offsetTime2)))
      _ <- Entity.offsetDateTime.<(Ref.offsetDateTime_).Ref.offsetDateTime.query.get.map(_ ==> List((offsetDateTime1, offsetDateTime2)))
      _ <- Entity.zonedDateTime.<(Ref.zonedDateTime_).Ref.zonedDateTime.query.get.map(_ ==> List((zonedDateTime1, zonedDateTime2)))
      _ <- Entity.uuid.<(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((uuid1, uuid2)))
      _ <- Entity.uri.<(Ref.uri_).Ref.uri.query.get.map(_ ==> List((uri1, uri2)))
      _ <- Entity.byte.<(Ref.byte_).Ref.byte.query.get.map(_ ==> List((byte1, byte2)))
      _ <- Entity.short.<(Ref.short_).Ref.short.query.get.map(_ ==> List((short1, short2)))
      _ <- Entity.char.<(Ref.char_).Ref.char.query.get.map(_ ==> List((char1, char2)))
    } yield ()
  }


  "<=" - types { implicit conn =>
    for {
      _ <- Entity.string.Ref.string.insert(string1, string2).transact
      _ <- Entity.int.Ref.int.insert(int1, int2).transact
      _ <- Entity.long.Ref.long.insert(long1, long2).transact
      _ <- Entity.float.Ref.float.insert(float1, float2).transact
      _ <- Entity.double.Ref.double.insert(double1, double2).transact
      _ <- Entity.boolean.Ref.boolean.insert(boolean1, boolean2).transact
      _ <- Entity.bigInt.Ref.bigInt.insert(bigInt1, bigInt2).transact
      _ <- Entity.bigDecimal.Ref.bigDecimal.insert(bigDecimal1, bigDecimal2).transact
      _ <- Entity.date.Ref.date.insert(date1, date2).transact
      _ <- Entity.duration.Ref.duration.insert(duration1, duration2).transact
      _ <- Entity.instant.Ref.instant.insert(instant1, instant2).transact
      _ <- Entity.localDate.Ref.localDate.insert(localDate1, localDate2).transact
      _ <- Entity.localTime.Ref.localTime.insert(localTime1, localTime2).transact
      _ <- Entity.localDateTime.Ref.localDateTime.insert(localDateTime1, localDateTime2).transact
      _ <- Entity.offsetTime.Ref.offsetTime.insert(offsetTime1, offsetTime2).transact
      _ <- Entity.offsetDateTime.Ref.offsetDateTime.insert(offsetDateTime1, offsetDateTime2).transact
      _ <- Entity.zonedDateTime.Ref.zonedDateTime.insert(zonedDateTime1, zonedDateTime2).transact
      _ <- Entity.uuid.Ref.uuid.insert(uuid1, uuid2).transact
      _ <- Entity.uri.Ref.uri.insert(uri1, uri2).transact
      _ <- Entity.byte.Ref.byte.insert(byte1, byte2).transact
      _ <- Entity.short.Ref.short.insert(short1, short2).transact
      _ <- Entity.char.Ref.char.insert(char1, char2).transact

      _ <- Entity.string.<=(Ref.string_).Ref.string.query.get.map(_ ==> List((string1, string2)))
      _ <- Entity.int.<=(Ref.int_).Ref.int.query.get.map(_ ==> List((int1, int2)))
      _ <- Entity.long.<=(Ref.long_).Ref.long.query.get.map(_ ==> List((long1, long2)))
      _ <- Entity.float.<=(Ref.float_).Ref.float.query.get.map(_ ==> List((float1, float2)))
      _ <- Entity.double.<=(Ref.double_).Ref.double.query.get.map(_ ==> List((double1, double2)))
      _ <- Entity.boolean.<=(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((boolean1, boolean2)))
      _ <- Entity.bigInt.<=(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((bigInt1, bigInt2)))
      _ <- Entity.bigDecimal.<=(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((bigDecimal1, bigDecimal2)))
      _ <- Entity.date.<=(Ref.date_).Ref.date.query.get.map(_ ==> List((date1, date2)))
      _ <- Entity.duration.<=(Ref.duration_).Ref.duration.query.get.map(_ ==> List((duration1, duration2)))
      _ <- Entity.instant.<=(Ref.instant_).Ref.instant.query.get.map(_ ==> List((instant1, instant2)))
      _ <- Entity.localDate.<=(Ref.localDate_).Ref.localDate.query.get.map(_ ==> List((localDate1, localDate2)))
      _ <- Entity.localTime.<=(Ref.localTime_).Ref.localTime.query.get.map(_ ==> List((localTime1, localTime2)))
      _ <- Entity.localDateTime.<=(Ref.localDateTime_).Ref.localDateTime.query.get.map(_ ==> List((localDateTime1, localDateTime2)))
      _ <- Entity.offsetTime.<=(Ref.offsetTime_).Ref.offsetTime.query.get.map(_ ==> List((offsetTime1, offsetTime2)))
      _ <- Entity.offsetDateTime.<=(Ref.offsetDateTime_).Ref.offsetDateTime.query.get.map(_ ==> List((offsetDateTime1, offsetDateTime2)))
      _ <- Entity.zonedDateTime.<=(Ref.zonedDateTime_).Ref.zonedDateTime.query.get.map(_ ==> List((zonedDateTime1, zonedDateTime2)))
      _ <- Entity.uuid.<=(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((uuid1, uuid2)))
      _ <- Entity.uri.<=(Ref.uri_).Ref.uri.query.get.map(_ ==> List((uri1, uri2)))
      _ <- Entity.byte.<=(Ref.byte_).Ref.byte.query.get.map(_ ==> List((byte1, byte2)))
      _ <- Entity.short.<=(Ref.short_).Ref.short.query.get.map(_ ==> List((short1, short2)))
      _ <- Entity.char.<=(Ref.char_).Ref.char.query.get.map(_ ==> List((char1, char2)))
    } yield ()
  }


  ">" - types { implicit conn =>
    for {
      _ <- Entity.string.Ref.string.insert(string2, string1).transact
      _ <- Entity.int.Ref.int.insert(int2, int1).transact
      _ <- Entity.long.Ref.long.insert(long2, long1).transact
      _ <- Entity.float.Ref.float.insert(float2, float1).transact
      _ <- Entity.double.Ref.double.insert(double2, double1).transact
      _ <- Entity.boolean.Ref.boolean.insert(boolean2, boolean1).transact
      _ <- Entity.bigInt.Ref.bigInt.insert(bigInt2, bigInt1).transact
      _ <- Entity.bigDecimal.Ref.bigDecimal.insert(bigDecimal2, bigDecimal1).transact
      _ <- Entity.date.Ref.date.insert(date2, date1).transact
      _ <- Entity.duration.Ref.duration.insert(duration2, duration1).transact
      _ <- Entity.instant.Ref.instant.insert(instant2, instant1).transact
      _ <- Entity.localDate.Ref.localDate.insert(localDate2, localDate1).transact
      _ <- Entity.localTime.Ref.localTime.insert(localTime2, localTime1).transact
      _ <- Entity.localDateTime.Ref.localDateTime.insert(localDateTime2, localDateTime1).transact
      _ <- Entity.offsetTime.Ref.offsetTime.insert(offsetTime2, offsetTime1).transact
      _ <- Entity.offsetDateTime.Ref.offsetDateTime.insert(offsetDateTime2, offsetDateTime1).transact
      _ <- Entity.zonedDateTime.Ref.zonedDateTime.insert(zonedDateTime2, zonedDateTime1).transact
      _ <- Entity.uuid.Ref.uuid.insert(uuid2, uuid1).transact
      _ <- Entity.uri.Ref.uri.insert(uri2, uri1).transact
      _ <- Entity.byte.Ref.byte.insert(byte2, byte1).transact
      _ <- Entity.short.Ref.short.insert(short2, short1).transact
      _ <- Entity.char.Ref.char.insert(char2, char1).transact

      _ <- Entity.string.>(Ref.string_).Ref.string.query.get.map(_ ==> List((string2, string1)))
      _ <- Entity.int.>(Ref.int_).Ref.int.query.get.map(_ ==> List((int2, int1)))
      _ <- Entity.long.>(Ref.long_).Ref.long.query.get.map(_ ==> List((long2, long1)))
      _ <- Entity.float.>(Ref.float_).Ref.float.query.get.map(_ ==> List((float2, float1)))
      _ <- Entity.double.>(Ref.double_).Ref.double.query.get.map(_ ==> List((double2, double1)))
      _ <- Entity.boolean.>(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((boolean2, boolean1)))
      _ <- Entity.bigInt.>(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((bigInt2, bigInt1)))
      _ <- Entity.bigDecimal.>(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((bigDecimal2, bigDecimal1)))
      _ <- Entity.date.>(Ref.date_).Ref.date.query.get.map(_ ==> List((date2, date1)))
      _ <- Entity.duration.>(Ref.duration_).Ref.duration.query.get.map(_ ==> List((duration2, duration1)))
      _ <- Entity.instant.>(Ref.instant_).Ref.instant.query.get.map(_ ==> List((instant2, instant1)))
      _ <- Entity.localDate.>(Ref.localDate_).Ref.localDate.query.get.map(_ ==> List((localDate2, localDate1)))
      _ <- Entity.localTime.>(Ref.localTime_).Ref.localTime.query.get.map(_ ==> List((localTime2, localTime1)))
      _ <- Entity.localDateTime.>(Ref.localDateTime_).Ref.localDateTime.query.get.map(_ ==> List((localDateTime2, localDateTime1)))
      _ <- Entity.offsetTime.>(Ref.offsetTime_).Ref.offsetTime.query.get.map(_ ==> List((offsetTime2, offsetTime1)))
      _ <- Entity.offsetDateTime.>(Ref.offsetDateTime_).Ref.offsetDateTime.query.get.map(_ ==> List((offsetDateTime2, offsetDateTime1)))
      _ <- Entity.zonedDateTime.>(Ref.zonedDateTime_).Ref.zonedDateTime.query.get.map(_ ==> List((zonedDateTime2, zonedDateTime1)))
      _ <- Entity.uuid.>(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((uuid2, uuid1)))
      _ <- Entity.uri.>(Ref.uri_).Ref.uri.query.get.map(_ ==> List((uri2, uri1)))
      _ <- Entity.byte.>(Ref.byte_).Ref.byte.query.get.map(_ ==> List((byte2, byte1)))
      _ <- Entity.short.>(Ref.short_).Ref.short.query.get.map(_ ==> List((short2, short1)))
      _ <- Entity.char.>(Ref.char_).Ref.char.query.get.map(_ ==> List((char2, char1)))
    } yield ()
  }


  ">=" - types { implicit conn =>
    for {
      _ <- Entity.string.Ref.string.insert(string2, string1).transact
      _ <- Entity.int.Ref.int.insert(int2, int1).transact
      _ <- Entity.long.Ref.long.insert(long2, long1).transact
      _ <- Entity.float.Ref.float.insert(float2, float1).transact
      _ <- Entity.double.Ref.double.insert(double2, double1).transact
      _ <- Entity.boolean.Ref.boolean.insert(boolean2, boolean1).transact
      _ <- Entity.bigInt.Ref.bigInt.insert(bigInt2, bigInt1).transact
      _ <- Entity.bigDecimal.Ref.bigDecimal.insert(bigDecimal2, bigDecimal1).transact
      _ <- Entity.date.Ref.date.insert(date2, date1).transact
      _ <- Entity.duration.Ref.duration.insert(duration2, duration1).transact
      _ <- Entity.instant.Ref.instant.insert(instant2, instant1).transact
      _ <- Entity.localDate.Ref.localDate.insert(localDate2, localDate1).transact
      _ <- Entity.localTime.Ref.localTime.insert(localTime2, localTime1).transact
      _ <- Entity.localDateTime.Ref.localDateTime.insert(localDateTime2, localDateTime1).transact
      _ <- Entity.offsetTime.Ref.offsetTime.insert(offsetTime2, offsetTime1).transact
      _ <- Entity.offsetDateTime.Ref.offsetDateTime.insert(offsetDateTime2, offsetDateTime1).transact
      _ <- Entity.zonedDateTime.Ref.zonedDateTime.insert(zonedDateTime2, zonedDateTime1).transact
      _ <- Entity.uuid.Ref.uuid.insert(uuid2, uuid1).transact
      _ <- Entity.uri.Ref.uri.insert(uri2, uri1).transact
      _ <- Entity.byte.Ref.byte.insert(byte2, byte1).transact
      _ <- Entity.short.Ref.short.insert(short2, short1).transact
      _ <- Entity.char.Ref.char.insert(char2, char1).transact

      _ <- Entity.string.>=(Ref.string_).Ref.string.query.get.map(_ ==> List((string2, string1)))
      _ <- Entity.int.>=(Ref.int_).Ref.int.query.get.map(_ ==> List((int2, int1)))
      _ <- Entity.long.>=(Ref.long_).Ref.long.query.get.map(_ ==> List((long2, long1)))
      _ <- Entity.float.>=(Ref.float_).Ref.float.query.get.map(_ ==> List((float2, float1)))
      _ <- Entity.double.>=(Ref.double_).Ref.double.query.get.map(_ ==> List((double2, double1)))
      _ <- Entity.boolean.>=(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((boolean2, boolean1)))
      _ <- Entity.bigInt.>=(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((bigInt2, bigInt1)))
      _ <- Entity.bigDecimal.>=(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((bigDecimal2, bigDecimal1)))
      _ <- Entity.date.>=(Ref.date_).Ref.date.query.get.map(_ ==> List((date2, date1)))
      _ <- Entity.duration.>=(Ref.duration_).Ref.duration.query.get.map(_ ==> List((duration2, duration1)))
      _ <- Entity.instant.>=(Ref.instant_).Ref.instant.query.get.map(_ ==> List((instant2, instant1)))
      _ <- Entity.localDate.>=(Ref.localDate_).Ref.localDate.query.get.map(_ ==> List((localDate2, localDate1)))
      _ <- Entity.localTime.>=(Ref.localTime_).Ref.localTime.query.get.map(_ ==> List((localTime2, localTime1)))
      _ <- Entity.localDateTime.>=(Ref.localDateTime_).Ref.localDateTime.query.get.map(_ ==> List((localDateTime2, localDateTime1)))
      _ <- Entity.offsetTime.>=(Ref.offsetTime_).Ref.offsetTime.query.get.map(_ ==> List((offsetTime2, offsetTime1)))
      _ <- Entity.offsetDateTime.>=(Ref.offsetDateTime_).Ref.offsetDateTime.query.get.map(_ ==> List((offsetDateTime2, offsetDateTime1)))
      _ <- Entity.zonedDateTime.>=(Ref.zonedDateTime_).Ref.zonedDateTime.query.get.map(_ ==> List((zonedDateTime2, zonedDateTime1)))
      _ <- Entity.uuid.>=(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((uuid2, uuid1)))
      _ <- Entity.uri.>=(Ref.uri_).Ref.uri.query.get.map(_ ==> List((uri2, uri1)))
      _ <- Entity.byte.>=(Ref.byte_).Ref.byte.query.get.map(_ ==> List((byte2, byte1)))
      _ <- Entity.short.>=(Ref.short_).Ref.short.query.get.map(_ ==> List((short2, short1)))
      _ <- Entity.char.>=(Ref.char_).Ref.char.query.get.map(_ ==> List((char2, char1)))
    } yield ()
  }
}
