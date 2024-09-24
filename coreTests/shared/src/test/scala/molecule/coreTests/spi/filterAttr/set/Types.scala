package molecule.coreTests.spi.filterAttr.set

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait Types extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "has" - types { implicit conn =>
      for {
        _ <- Ns.stringSet.Ref.string.insert(Set(string1), string1).transact
        _ <- Ns.intSet.Ref.int.insert(Set(int1), int1).transact
        _ <- Ns.longSet.Ref.long.insert(Set(long1), long1).transact
        _ <- Ns.floatSet.Ref.float.insert(Set(float1), float1).transact
        _ <- Ns.doubleSet.Ref.double.insert(Set(double1), double1).transact
        _ <- Ns.booleanSet.Ref.boolean.insert(Set(boolean1), boolean1).transact
        _ <- Ns.bigIntSet.Ref.bigInt.insert(Set(bigInt1), bigInt1).transact
        _ <- Ns.bigDecimalSet.Ref.bigDecimal.insert(Set(bigDecimal1), bigDecimal1).transact
        _ <- Ns.dateSet.Ref.date.insert(Set(date1), date1).transact
        _ <- Ns.durationSet.Ref.duration.insert(Set(duration1), duration1).transact
        _ <- Ns.instantSet.Ref.instant.insert(Set(instant1), instant1).transact
        _ <- Ns.localDateSet.Ref.localDate.insert(Set(localDate1), localDate1).transact
        _ <- Ns.localTimeSet.Ref.localTime.insert(Set(localTime1), localTime1).transact
        _ <- Ns.localDateTimeSet.Ref.localDateTime.insert(Set(localDateTime1), localDateTime1).transact
        _ <- Ns.offsetTimeSet.Ref.offsetTime.insert(Set(offsetTime1), offsetTime1).transact
        _ <- Ns.offsetDateTimeSet.Ref.offsetDateTime.insert(Set(offsetDateTime1), offsetDateTime1).transact
        _ <- Ns.zonedDateTimeSet.Ref.zonedDateTime.insert(Set(zonedDateTime1), zonedDateTime1).transact
        _ <- Ns.uuidSet.Ref.uuid.insert(Set(uuid1), uuid1).transact
        _ <- Ns.uriSet.Ref.uri.insert(Set(uri1), uri1).transact
        _ <- Ns.byteSet.Ref.byte.insert(Set(byte1), byte1).transact
        _ <- Ns.shortSet.Ref.short.insert(Set(short1), short1).transact
        _ <- Ns.charSet.Ref.char.insert(Set(char1), char1).transact

        _ <- Ns.stringSet.has(Ref.string_).Ref.string.query.get.map(_ ==> List((Set(string1), string1)))
        _ <- Ns.intSet.has(Ref.int_).Ref.int.query.get.map(_ ==> List((Set(int1), int1)))
        _ <- Ns.longSet.has(Ref.long_).Ref.long.query.get.map(_ ==> List((Set(long1), long1)))
        _ <- Ns.floatSet.has(Ref.float_).Ref.float.query.get.map(_ ==> List((Set(float1), float1)))
        _ <- Ns.doubleSet.has(Ref.double_).Ref.double.query.get.map(_ ==> List((Set(double1), double1)))
        _ <- Ns.booleanSet.has(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((Set(boolean1), boolean1)))
        _ <- Ns.bigIntSet.has(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((Set(bigInt1), bigInt1)))
        _ <- Ns.bigDecimalSet.has(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((Set(bigDecimal1), bigDecimal1)))
        _ <- Ns.dateSet.has(Ref.date_).Ref.date.query.get.map(_ ==> List((Set(date1), date1)))
        _ <- Ns.durationSet.has(Ref.duration_).Ref.duration.query.get.map(_ ==> List((Set(duration1), duration1)))
        _ <- Ns.instantSet.has(Ref.instant_).Ref.instant.query.get.map(_ ==> List((Set(instant1), instant1)))
        _ <- Ns.localDateSet.has(Ref.localDate_).Ref.localDate.query.get.map(_ ==> List((Set(localDate1), localDate1)))
        _ <- Ns.localTimeSet.has(Ref.localTime_).Ref.localTime.query.get.map(_ ==> List((Set(localTime1), localTime1)))
        _ <- Ns.localDateTimeSet.has(Ref.localDateTime_).Ref.localDateTime.query.get.map(_ ==> List((Set(localDateTime1), localDateTime1)))
        _ <- Ns.offsetTimeSet.has(Ref.offsetTime_).Ref.offsetTime.query.get.map(_ ==> List((Set(offsetTime1), offsetTime1)))
        _ <- Ns.offsetDateTimeSet.has(Ref.offsetDateTime_).Ref.offsetDateTime.query.get.map(_ ==> List((Set(offsetDateTime1), offsetDateTime1)))
        _ <- Ns.zonedDateTimeSet.has(Ref.zonedDateTime_).Ref.zonedDateTime.query.get.map(_ ==> List((Set(zonedDateTime1), zonedDateTime1)))
        _ <- Ns.uuidSet.has(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((Set(uuid1), uuid1)))
        _ <- Ns.uriSet.has(Ref.uri_).Ref.uri.query.get.map(_ ==> List((Set(uri1), uri1)))
        _ <- Ns.byteSet.has(Ref.byte_).Ref.byte.query.get.map(_ ==> List((Set(byte1), byte1)))
        _ <- Ns.shortSet.has(Ref.short_).Ref.short.query.get.map(_ ==> List((Set(short1), short1)))
        _ <- Ns.charSet.has(Ref.char_).Ref.char.query.get.map(_ ==> List((Set(char1), char1)))
      } yield ()
    }


    "hasNo" - types { implicit conn =>
      for {
        _ <- Ns.stringSet.Ref.string.insert(Set(string1), string2).transact
        _ <- Ns.intSet.Ref.int.insert(Set(int1), int2).transact
        _ <- Ns.longSet.Ref.long.insert(Set(long1), long2).transact
        _ <- Ns.floatSet.Ref.float.insert(Set(float1), float2).transact
        _ <- Ns.doubleSet.Ref.double.insert(Set(double1), double2).transact
        _ <- Ns.booleanSet.Ref.boolean.insert(Set(boolean1), boolean2).transact
        _ <- Ns.bigIntSet.Ref.bigInt.insert(Set(bigInt1), bigInt2).transact
        _ <- Ns.bigDecimalSet.Ref.bigDecimal.insert(Set(bigDecimal1), bigDecimal2).transact
        _ <- Ns.dateSet.Ref.date.insert(Set(date1), date2).transact
        _ <- Ns.durationSet.Ref.duration.insert(Set(duration1), duration2).transact
        _ <- Ns.instantSet.Ref.instant.insert(Set(instant1), instant2).transact
        _ <- Ns.localDateSet.Ref.localDate.insert(Set(localDate1), localDate2).transact
        _ <- Ns.localTimeSet.Ref.localTime.insert(Set(localTime1), localTime2).transact
        _ <- Ns.localDateTimeSet.Ref.localDateTime.insert(Set(localDateTime1), localDateTime2).transact
        _ <- Ns.offsetTimeSet.Ref.offsetTime.insert(Set(offsetTime1), offsetTime2).transact
        _ <- Ns.offsetDateTimeSet.Ref.offsetDateTime.insert(Set(offsetDateTime1), offsetDateTime2).transact
        _ <- Ns.zonedDateTimeSet.Ref.zonedDateTime.insert(Set(zonedDateTime1), zonedDateTime2).transact
        _ <- Ns.uuidSet.Ref.uuid.insert(Set(uuid1), uuid2).transact
        _ <- Ns.uriSet.Ref.uri.insert(Set(uri1), uri2).transact
        _ <- Ns.byteSet.Ref.byte.insert(Set(byte1), byte2).transact
        _ <- Ns.shortSet.Ref.short.insert(Set(short1), short2).transact
        _ <- Ns.charSet.Ref.char.insert(Set(char1), char2).transact

        _ <- Ns.stringSet.hasNo(Ref.string_).Ref.string.query.get.map(_ ==> List((Set(string1), string2)))
        _ <- Ns.intSet.hasNo(Ref.int_).Ref.int.query.get.map(_ ==> List((Set(int1), int2)))
        _ <- Ns.longSet.hasNo(Ref.long_).Ref.long.query.get.map(_ ==> List((Set(long1), long2)))
        _ <- Ns.floatSet.hasNo(Ref.float_).Ref.float.query.get.map(_ ==> List((Set(float1), float2)))
        _ <- Ns.doubleSet.hasNo(Ref.double_).Ref.double.query.get.map(_ ==> List((Set(double1), double2)))
        _ <- Ns.booleanSet.hasNo(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((Set(boolean1), boolean2)))
        _ <- Ns.bigIntSet.hasNo(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((Set(bigInt1), bigInt2)))
        _ <- Ns.bigDecimalSet.hasNo(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((Set(bigDecimal1), bigDecimal2)))
        _ <- Ns.dateSet.hasNo(Ref.date_).Ref.date.query.get.map(_ ==> List((Set(date1), date2)))
        _ <- Ns.durationSet.hasNo(Ref.duration_).Ref.duration.query.get.map(_ ==> List((Set(duration1), duration2)))
        _ <- Ns.instantSet.hasNo(Ref.instant_).Ref.instant.query.get.map(_ ==> List((Set(instant1), instant2)))
        _ <- Ns.localDateSet.hasNo(Ref.localDate_).Ref.localDate.query.get.map(_ ==> List((Set(localDate1), localDate2)))
        _ <- Ns.localTimeSet.hasNo(Ref.localTime_).Ref.localTime.query.get.map(_ ==> List((Set(localTime1), localTime2)))
        _ <- Ns.localDateTimeSet.hasNo(Ref.localDateTime_).Ref.localDateTime.query.get.map(_ ==> List((Set(localDateTime1), localDateTime2)))
        _ <- Ns.offsetTimeSet.hasNo(Ref.offsetTime_).Ref.offsetTime.query.get.map(_ ==> List((Set(offsetTime1), offsetTime2)))
        _ <- Ns.offsetDateTimeSet.hasNo(Ref.offsetDateTime_).Ref.offsetDateTime.query.get.map(_ ==> List((Set(offsetDateTime1), offsetDateTime2)))
        _ <- Ns.zonedDateTimeSet.hasNo(Ref.zonedDateTime_).Ref.zonedDateTime.query.get.map(_ ==> List((Set(zonedDateTime1), zonedDateTime2)))
        _ <- Ns.uuidSet.hasNo(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((Set(uuid1), uuid2)))
        _ <- Ns.uriSet.hasNo(Ref.uri_).Ref.uri.query.get.map(_ ==> List((Set(uri1), uri2)))
        _ <- Ns.byteSet.hasNo(Ref.byte_).Ref.byte.query.get.map(_ ==> List((Set(byte1), byte2)))
        _ <- Ns.shortSet.hasNo(Ref.short_).Ref.short.query.get.map(_ ==> List((Set(short1), short2)))
        _ <- Ns.charSet.hasNo(Ref.char_).Ref.char.query.get.map(_ ==> List((Set(char1), char2)))
      } yield ()
    }
  }
}
