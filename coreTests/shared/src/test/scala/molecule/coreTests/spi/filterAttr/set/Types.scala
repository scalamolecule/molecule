package molecule.coreTests.spi.filterAttr.set

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class Types(
  suite: MUnitSuite,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "has" - types { implicit conn =>
    for {
      _ <- Entity.stringSet.Ref.string.insert(Set(string1), string1).transact
      _ <- Entity.intSet.Ref.int.insert(Set(int1), int1).transact
      _ <- Entity.longSet.Ref.long.insert(Set(long1), long1).transact
      _ <- Entity.floatSet.Ref.float.insert(Set(float1), float1).transact
      _ <- Entity.doubleSet.Ref.double.insert(Set(double1), double1).transact
      _ <- Entity.booleanSet.Ref.boolean.insert(Set(boolean1), boolean1).transact
      _ <- Entity.bigIntSet.Ref.bigInt.insert(Set(bigInt1), bigInt1).transact
      _ <- Entity.bigDecimalSet.Ref.bigDecimal.insert(Set(bigDecimal1), bigDecimal1).transact
      _ <- Entity.dateSet.Ref.date.insert(Set(date1), date1).transact
      _ <- Entity.durationSet.Ref.duration.insert(Set(duration1), duration1).transact
      _ <- Entity.instantSet.Ref.instant.insert(Set(instant1), instant1).transact
      _ <- Entity.localDateSet.Ref.localDate.insert(Set(localDate1), localDate1).transact
      _ <- Entity.localTimeSet.Ref.localTime.insert(Set(localTime1), localTime1).transact
      _ <- Entity.localDateTimeSet.Ref.localDateTime.insert(Set(localDateTime1), localDateTime1).transact
      _ <- Entity.offsetTimeSet.Ref.offsetTime.insert(Set(offsetTime1), offsetTime1).transact
      _ <- Entity.offsetDateTimeSet.Ref.offsetDateTime.insert(Set(offsetDateTime1), offsetDateTime1).transact
      _ <- Entity.zonedDateTimeSet.Ref.zonedDateTime.insert(Set(zonedDateTime1), zonedDateTime1).transact
      _ <- Entity.uuidSet.Ref.uuid.insert(Set(uuid1), uuid1).transact
      _ <- Entity.uriSet.Ref.uri.insert(Set(uri1), uri1).transact
      _ <- Entity.byteSet.Ref.byte.insert(Set(byte1), byte1).transact
      _ <- Entity.shortSet.Ref.short.insert(Set(short1), short1).transact
      _ <- Entity.charSet.Ref.char.insert(Set(char1), char1).transact

      _ <- Entity.stringSet.has(Ref.string_).Ref.string.query.get.map(_ ==> List((Set(string1), string1)))
      _ <- Entity.intSet.has(Ref.int_).Ref.int.query.get.map(_ ==> List((Set(int1), int1)))
      _ <- Entity.longSet.has(Ref.long_).Ref.long.query.get.map(_ ==> List((Set(long1), long1)))
      _ <- Entity.floatSet.has(Ref.float_).Ref.float.query.get.map(_ ==> List((Set(float1), float1)))
      _ <- Entity.doubleSet.has(Ref.double_).Ref.double.query.get.map(_ ==> List((Set(double1), double1)))
      _ <- Entity.booleanSet.has(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((Set(boolean1), boolean1)))
      _ <- Entity.bigIntSet.has(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((Set(bigInt1), bigInt1)))
      _ <- Entity.bigDecimalSet.has(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((Set(bigDecimal1), bigDecimal1)))
      _ <- Entity.dateSet.has(Ref.date_).Ref.date.query.get.map(_ ==> List((Set(date1), date1)))
      _ <- Entity.durationSet.has(Ref.duration_).Ref.duration.query.get.map(_ ==> List((Set(duration1), duration1)))
      _ <- Entity.instantSet.has(Ref.instant_).Ref.instant.query.get.map(_ ==> List((Set(instant1), instant1)))
      _ <- Entity.localDateSet.has(Ref.localDate_).Ref.localDate.query.get.map(_ ==> List((Set(localDate1), localDate1)))
      _ <- Entity.localTimeSet.has(Ref.localTime_).Ref.localTime.query.get.map(_ ==> List((Set(localTime1), localTime1)))
      _ <- Entity.localDateTimeSet.has(Ref.localDateTime_).Ref.localDateTime.query.get.map(_ ==> List((Set(localDateTime1), localDateTime1)))
      _ <- Entity.offsetTimeSet.has(Ref.offsetTime_).Ref.offsetTime.query.get.map(_ ==> List((Set(offsetTime1), offsetTime1)))
      _ <- Entity.offsetDateTimeSet.has(Ref.offsetDateTime_).Ref.offsetDateTime.query.get.map(_ ==> List((Set(offsetDateTime1), offsetDateTime1)))
      _ <- Entity.zonedDateTimeSet.has(Ref.zonedDateTime_).Ref.zonedDateTime.query.get.map(_ ==> List((Set(zonedDateTime1), zonedDateTime1)))
      _ <- Entity.uuidSet.has(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((Set(uuid1), uuid1)))
      _ <- Entity.uriSet.has(Ref.uri_).Ref.uri.query.get.map(_ ==> List((Set(uri1), uri1)))
      _ <- Entity.byteSet.has(Ref.byte_).Ref.byte.query.get.map(_ ==> List((Set(byte1), byte1)))
      _ <- Entity.shortSet.has(Ref.short_).Ref.short.query.get.map(_ ==> List((Set(short1), short1)))
      _ <- Entity.charSet.has(Ref.char_).Ref.char.query.get.map(_ ==> List((Set(char1), char1)))
    } yield ()
  }


  "hasNo" - types { implicit conn =>
    for {
      _ <- Entity.stringSet.Ref.string.insert(Set(string1), string2).transact
      _ <- Entity.intSet.Ref.int.insert(Set(int1), int2).transact
      _ <- Entity.longSet.Ref.long.insert(Set(long1), long2).transact
      _ <- Entity.floatSet.Ref.float.insert(Set(float1), float2).transact
      _ <- Entity.doubleSet.Ref.double.insert(Set(double1), double2).transact
      _ <- Entity.booleanSet.Ref.boolean.insert(Set(boolean1), boolean2).transact
      _ <- Entity.bigIntSet.Ref.bigInt.insert(Set(bigInt1), bigInt2).transact
      _ <- Entity.bigDecimalSet.Ref.bigDecimal.insert(Set(bigDecimal1), bigDecimal2).transact
      _ <- Entity.dateSet.Ref.date.insert(Set(date1), date2).transact
      _ <- Entity.durationSet.Ref.duration.insert(Set(duration1), duration2).transact
      _ <- Entity.instantSet.Ref.instant.insert(Set(instant1), instant2).transact
      _ <- Entity.localDateSet.Ref.localDate.insert(Set(localDate1), localDate2).transact
      _ <- Entity.localTimeSet.Ref.localTime.insert(Set(localTime1), localTime2).transact
      _ <- Entity.localDateTimeSet.Ref.localDateTime.insert(Set(localDateTime1), localDateTime2).transact
      _ <- Entity.offsetTimeSet.Ref.offsetTime.insert(Set(offsetTime1), offsetTime2).transact
      _ <- Entity.offsetDateTimeSet.Ref.offsetDateTime.insert(Set(offsetDateTime1), offsetDateTime2).transact
      _ <- Entity.zonedDateTimeSet.Ref.zonedDateTime.insert(Set(zonedDateTime1), zonedDateTime2).transact
      _ <- Entity.uuidSet.Ref.uuid.insert(Set(uuid1), uuid2).transact
      _ <- Entity.uriSet.Ref.uri.insert(Set(uri1), uri2).transact
      _ <- Entity.byteSet.Ref.byte.insert(Set(byte1), byte2).transact
      _ <- Entity.shortSet.Ref.short.insert(Set(short1), short2).transact
      _ <- Entity.charSet.Ref.char.insert(Set(char1), char2).transact

      _ <- Entity.stringSet.hasNo(Ref.string_).Ref.string.query.get.map(_ ==> List((Set(string1), string2)))
      _ <- Entity.intSet.hasNo(Ref.int_).Ref.int.query.get.map(_ ==> List((Set(int1), int2)))
      _ <- Entity.longSet.hasNo(Ref.long_).Ref.long.query.get.map(_ ==> List((Set(long1), long2)))
      _ <- Entity.floatSet.hasNo(Ref.float_).Ref.float.query.get.map(_ ==> List((Set(float1), float2)))
      _ <- Entity.doubleSet.hasNo(Ref.double_).Ref.double.query.get.map(_ ==> List((Set(double1), double2)))
      _ <- Entity.booleanSet.hasNo(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((Set(boolean1), boolean2)))
      _ <- Entity.bigIntSet.hasNo(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((Set(bigInt1), bigInt2)))
      _ <- Entity.bigDecimalSet.hasNo(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((Set(bigDecimal1), bigDecimal2)))
      _ <- Entity.dateSet.hasNo(Ref.date_).Ref.date.query.get.map(_ ==> List((Set(date1), date2)))
      _ <- Entity.durationSet.hasNo(Ref.duration_).Ref.duration.query.get.map(_ ==> List((Set(duration1), duration2)))
      _ <- Entity.instantSet.hasNo(Ref.instant_).Ref.instant.query.get.map(_ ==> List((Set(instant1), instant2)))
      _ <- Entity.localDateSet.hasNo(Ref.localDate_).Ref.localDate.query.get.map(_ ==> List((Set(localDate1), localDate2)))
      _ <- Entity.localTimeSet.hasNo(Ref.localTime_).Ref.localTime.query.get.map(_ ==> List((Set(localTime1), localTime2)))
      _ <- Entity.localDateTimeSet.hasNo(Ref.localDateTime_).Ref.localDateTime.query.get.map(_ ==> List((Set(localDateTime1), localDateTime2)))
      _ <- Entity.offsetTimeSet.hasNo(Ref.offsetTime_).Ref.offsetTime.query.get.map(_ ==> List((Set(offsetTime1), offsetTime2)))
      _ <- Entity.offsetDateTimeSet.hasNo(Ref.offsetDateTime_).Ref.offsetDateTime.query.get.map(_ ==> List((Set(offsetDateTime1), offsetDateTime2)))
      _ <- Entity.zonedDateTimeSet.hasNo(Ref.zonedDateTime_).Ref.zonedDateTime.query.get.map(_ ==> List((Set(zonedDateTime1), zonedDateTime2)))
      _ <- Entity.uuidSet.hasNo(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((Set(uuid1), uuid2)))
      _ <- Entity.uriSet.hasNo(Ref.uri_).Ref.uri.query.get.map(_ ==> List((Set(uri1), uri2)))
      _ <- Entity.byteSet.hasNo(Ref.byte_).Ref.byte.query.get.map(_ ==> List((Set(byte1), byte2)))
      _ <- Entity.shortSet.hasNo(Ref.short_).Ref.short.query.get.map(_ ==> List((Set(short1), short2)))
      _ <- Entity.charSet.hasNo(Ref.char_).Ref.char.query.get.map(_ ==> List((Set(char1), char2)))
    } yield ()
  }
}
