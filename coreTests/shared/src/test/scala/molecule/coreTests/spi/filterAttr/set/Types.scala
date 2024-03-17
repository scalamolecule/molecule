package molecule.coreTests.spi.filterAttr.set

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
        _ <- Ns.stringSet.Ref.stringSet.insert(Set(string1), Set(string1)).transact
        _ <- Ns.intSet.Ref.intSet.insert(Set(int1), Set(int1)).transact
        _ <- Ns.longSet.Ref.longSet.insert(Set(long1), Set(long1)).transact
        _ <- Ns.floatSet.Ref.floatSet.insert(Set(float1), Set(float1)).transact
        _ <- Ns.doubleSet.Ref.doubleSet.insert(Set(double1), Set(double1)).transact
        _ <- Ns.booleanSet.Ref.booleanSet.insert(Set(boolean1), Set(boolean1)).transact
        _ <- Ns.bigIntSet.Ref.bigIntSet.insert(Set(bigInt1), Set(bigInt1)).transact
        _ <- Ns.bigDecimalSet.Ref.bigDecimalSet.insert(Set(bigDecimal1), Set(bigDecimal1)).transact
        _ <- Ns.dateSet.Ref.dateSet.insert(Set(date1), Set(date1)).transact
        _ <- Ns.durationSet.Ref.durationSet.insert(Set(duration1), Set(duration1)).transact
        _ <- Ns.instantSet.Ref.instantSet.insert(Set(instant1), Set(instant1)).transact
        _ <- Ns.localDateSet.Ref.localDateSet.insert(Set(localDate1), Set(localDate1)).transact
        _ <- Ns.localTimeSet.Ref.localTimeSet.insert(Set(localTime1), Set(localTime1)).transact
        _ <- Ns.localDateTimeSet.Ref.localDateTimeSet.insert(Set(localDateTime1), Set(localDateTime1)).transact
        _ <- Ns.offsetTimeSet.Ref.offsetTimeSet.insert(Set(offsetTime1), Set(offsetTime1)).transact
        _ <- Ns.offsetDateTimeSet.Ref.offsetDateTimeSet.insert(Set(offsetDateTime1), Set(offsetDateTime1)).transact
        _ <- Ns.zonedDateTimeSet.Ref.zonedDateTimeSet.insert(Set(zonedDateTime1), Set(zonedDateTime1)).transact
        _ <- Ns.uuidSet.Ref.uuidSet.insert(Set(uuid1), Set(uuid1)).transact
        _ <- Ns.uriSet.Ref.uriSet.insert(Set(uri1), Set(uri1)).transact
        _ <- Ns.byteSet.Ref.byteSet.insert(Set(byte1), Set(byte1)).transact
        _ <- Ns.shortSet.Ref.shortSet.insert(Set(short1), Set(short1)).transact
        _ <- Ns.charSet.Ref.charSet.insert(Set(char1), Set(char1)).transact

        _ <- Ns.stringSet(Ref.stringSet_).Ref.stringSet.query.get.map(_ ==> List((Set(string1), Set(string1))))
        _ <- Ns.intSet(Ref.intSet_).Ref.intSet.query.get.map(_ ==> List((Set(int1), Set(int1))))
        _ <- Ns.longSet(Ref.longSet_).Ref.longSet.query.get.map(_ ==> List((Set(long1), Set(long1))))
        _ <- Ns.floatSet(Ref.floatSet_).Ref.floatSet.query.get.map(_ ==> List((Set(float1), Set(float1))))
        _ <- Ns.doubleSet(Ref.doubleSet_).Ref.doubleSet.query.get.map(_ ==> List((Set(double1), Set(double1))))
        _ <- Ns.booleanSet(Ref.booleanSet_).Ref.booleanSet.query.get.map(_ ==> List((Set(boolean1), Set(boolean1))))
        _ <- Ns.bigIntSet(Ref.bigIntSet_).Ref.bigIntSet.query.get.map(_ ==> List((Set(bigInt1), Set(bigInt1))))
        _ <- Ns.bigDecimalSet(Ref.bigDecimalSet_).Ref.bigDecimalSet.query.get.map(_ ==> List((Set(bigDecimal1), Set(bigDecimal1))))
        _ <- Ns.dateSet(Ref.dateSet_).Ref.dateSet.query.get.map(_ ==> List((Set(date1), Set(date1))))
        _ <- Ns.durationSet(Ref.durationSet_).Ref.durationSet.query.get.map(_ ==> List((Set(duration1), Set(duration1))))
        _ <- Ns.instantSet(Ref.instantSet_).Ref.instantSet.query.get.map(_ ==> List((Set(instant1), Set(instant1))))
        _ <- Ns.localDateSet(Ref.localDateSet_).Ref.localDateSet.query.get.map(_ ==> List((Set(localDate1), Set(localDate1))))
        _ <- Ns.localTimeSet(Ref.localTimeSet_).Ref.localTimeSet.query.get.map(_ ==> List((Set(localTime1), Set(localTime1))))
        _ <- Ns.localDateTimeSet(Ref.localDateTimeSet_).Ref.localDateTimeSet.query.get.map(_ ==> List((Set(localDateTime1), Set(localDateTime1))))
        _ <- Ns.offsetTimeSet(Ref.offsetTimeSet_).Ref.offsetTimeSet.query.get.map(_ ==> List((Set(offsetTime1), Set(offsetTime1))))
        _ <- Ns.offsetDateTimeSet(Ref.offsetDateTimeSet_).Ref.offsetDateTimeSet.query.get.map(_ ==> List((Set(offsetDateTime1), Set(offsetDateTime1))))
        _ <- Ns.zonedDateTimeSet(Ref.zonedDateTimeSet_).Ref.zonedDateTimeSet.query.get.map(_ ==> List((Set(zonedDateTime1), Set(zonedDateTime1))))
        _ <- Ns.uuidSet(Ref.uuidSet_).Ref.uuidSet.query.get.map(_ ==> List((Set(uuid1), Set(uuid1))))
        _ <- Ns.uriSet(Ref.uriSet_).Ref.uriSet.query.get.map(_ ==> List((Set(uri1), Set(uri1))))
        _ <- Ns.byteSet(Ref.byteSet_).Ref.byteSet.query.get.map(_ ==> List((Set(byte1), Set(byte1))))
        _ <- Ns.shortSet(Ref.shortSet_).Ref.shortSet.query.get.map(_ ==> List((Set(short1), Set(short1))))
        _ <- Ns.charSet(Ref.charSet_).Ref.charSet.query.get.map(_ ==> List((Set(char1), Set(char1))))
      } yield ()
    }


    "not equal" - types { implicit conn =>
      for {
        _ <- Ns.stringSet.Ref.stringSet.insert(Set(string1), Set(string2)).transact
        _ <- Ns.intSet.Ref.intSet.insert(Set(int1), Set(int2)).transact
        _ <- Ns.longSet.Ref.longSet.insert(Set(long1), Set(long2)).transact
        _ <- Ns.floatSet.Ref.floatSet.insert(Set(float1), Set(float2)).transact
        _ <- Ns.doubleSet.Ref.doubleSet.insert(Set(double1), Set(double2)).transact
        _ <- Ns.booleanSet.Ref.booleanSet.insert(Set(boolean1), Set(boolean2)).transact
        _ <- Ns.bigIntSet.Ref.bigIntSet.insert(Set(bigInt1), Set(bigInt2)).transact
        _ <- Ns.bigDecimalSet.Ref.bigDecimalSet.insert(Set(bigDecimal1), Set(bigDecimal2)).transact
        _ <- Ns.dateSet.Ref.dateSet.insert(Set(date1), Set(date2)).transact
        _ <- Ns.durationSet.Ref.durationSet.insert(Set(duration1), Set(duration2)).transact
        _ <- Ns.instantSet.Ref.instantSet.insert(Set(instant1), Set(instant2)).transact
        _ <- Ns.localDateSet.Ref.localDateSet.insert(Set(localDate1), Set(localDate2)).transact
        _ <- Ns.localTimeSet.Ref.localTimeSet.insert(Set(localTime1), Set(localTime2)).transact
        _ <- Ns.localDateTimeSet.Ref.localDateTimeSet.insert(Set(localDateTime1), Set(localDateTime2)).transact
        _ <- Ns.offsetTimeSet.Ref.offsetTimeSet.insert(Set(offsetTime1), Set(offsetTime2)).transact
        _ <- Ns.offsetDateTimeSet.Ref.offsetDateTimeSet.insert(Set(offsetDateTime1), Set(offsetDateTime2)).transact
        _ <- Ns.zonedDateTimeSet.Ref.zonedDateTimeSet.insert(Set(zonedDateTime1), Set(zonedDateTime2)).transact
        _ <- Ns.uuidSet.Ref.uuidSet.insert(Set(uuid1), Set(uuid2)).transact
        _ <- Ns.uriSet.Ref.uriSet.insert(Set(uri1), Set(uri2)).transact
        _ <- Ns.byteSet.Ref.byteSet.insert(Set(byte1), Set(byte2)).transact
        _ <- Ns.shortSet.Ref.shortSet.insert(Set(short1), Set(short2)).transact
        _ <- Ns.charSet.Ref.charSet.insert(Set(char1), Set(char2)).transact

        _ <- Ns.stringSet.not(Ref.stringSet_).Ref.stringSet.query.get.map(_ ==> List((Set(string1), Set(string2))))
        _ <- Ns.intSet.not(Ref.intSet_).Ref.intSet.query.get.map(_ ==> List((Set(int1), Set(int2))))
        _ <- Ns.longSet.not(Ref.longSet_).Ref.longSet.query.get.map(_ ==> List((Set(long1), Set(long2))))
        _ <- Ns.floatSet.not(Ref.floatSet_).Ref.floatSet.query.get.map(_ ==> List((Set(float1), Set(float2))))
        _ <- Ns.doubleSet.not(Ref.doubleSet_).Ref.doubleSet.query.get.map(_ ==> List((Set(double1), Set(double2))))
        _ <- Ns.booleanSet.not(Ref.booleanSet_).Ref.booleanSet.query.get.map(_ ==> List((Set(boolean1), Set(boolean2))))
        _ <- Ns.bigIntSet.not(Ref.bigIntSet_).Ref.bigIntSet.query.get.map(_ ==> List((Set(bigInt1), Set(bigInt2))))
        _ <- Ns.bigDecimalSet.not(Ref.bigDecimalSet_).Ref.bigDecimalSet.query.get.map(_ ==> List((Set(bigDecimal1), Set(bigDecimal2))))
        _ <- Ns.dateSet.not(Ref.dateSet_).Ref.dateSet.query.get.map(_ ==> List((Set(date1), Set(date2))))
        _ <- Ns.durationSet.not(Ref.durationSet_).Ref.durationSet.query.get.map(_ ==> List((Set(duration1), Set(duration2))))
        _ <- Ns.instantSet.not(Ref.instantSet_).Ref.instantSet.query.get.map(_ ==> List((Set(instant1), Set(instant2))))
        _ <- Ns.localDateSet.not(Ref.localDateSet_).Ref.localDateSet.query.get.map(_ ==> List((Set(localDate1), Set(localDate2))))
        _ <- Ns.localTimeSet.not(Ref.localTimeSet_).Ref.localTimeSet.query.get.map(_ ==> List((Set(localTime1), Set(localTime2))))
        _ <- Ns.localDateTimeSet.not(Ref.localDateTimeSet_).Ref.localDateTimeSet.query.get.map(_ ==> List((Set(localDateTime1), Set(localDateTime2))))
        _ <- Ns.offsetTimeSet.not(Ref.offsetTimeSet_).Ref.offsetTimeSet.query.get.map(_ ==> List((Set(offsetTime1), Set(offsetTime2))))
        _ <- Ns.offsetDateTimeSet.not(Ref.offsetDateTimeSet_).Ref.offsetDateTimeSet.query.get.map(_ ==> List((Set(offsetDateTime1), Set(offsetDateTime2))))
        _ <- Ns.zonedDateTimeSet.not(Ref.zonedDateTimeSet_).Ref.zonedDateTimeSet.query.get.map(_ ==> List((Set(zonedDateTime1), Set(zonedDateTime2))))
        _ <- Ns.uuidSet.not(Ref.uuidSet_).Ref.uuidSet.query.get.map(_ ==> List((Set(uuid1), Set(uuid2))))
        _ <- Ns.uriSet.not(Ref.uriSet_).Ref.uriSet.query.get.map(_ ==> List((Set(uri1), Set(uri2))))
        _ <- Ns.byteSet.not(Ref.byteSet_).Ref.byteSet.query.get.map(_ ==> List((Set(byte1), Set(byte2))))
        _ <- Ns.shortSet.not(Ref.shortSet_).Ref.shortSet.query.get.map(_ ==> List((Set(short1), Set(short2))))
        _ <- Ns.charSet.not(Ref.charSet_).Ref.charSet.query.get.map(_ ==> List((Set(char1), Set(char2))))
      } yield ()
    }

    "has set" - types { implicit conn =>
      for {
        _ <- Ns.stringSet.Ref.stringSet.insert(Set(string1), Set(string1)).transact
        _ <- Ns.intSet.Ref.intSet.insert(Set(int1), Set(int1)).transact
        _ <- Ns.longSet.Ref.longSet.insert(Set(long1), Set(long1)).transact
        _ <- Ns.floatSet.Ref.floatSet.insert(Set(float1), Set(float1)).transact
        _ <- Ns.doubleSet.Ref.doubleSet.insert(Set(double1), Set(double1)).transact
        _ <- Ns.booleanSet.Ref.booleanSet.insert(Set(boolean1), Set(boolean1)).transact
        _ <- Ns.bigIntSet.Ref.bigIntSet.insert(Set(bigInt1), Set(bigInt1)).transact
        _ <- Ns.bigDecimalSet.Ref.bigDecimalSet.insert(Set(bigDecimal1), Set(bigDecimal1)).transact
        _ <- Ns.dateSet.Ref.dateSet.insert(Set(date1), Set(date1)).transact
        _ <- Ns.durationSet.Ref.durationSet.insert(Set(duration1), Set(duration1)).transact
        _ <- Ns.instantSet.Ref.instantSet.insert(Set(instant1), Set(instant1)).transact
        _ <- Ns.localDateSet.Ref.localDateSet.insert(Set(localDate1), Set(localDate1)).transact
        _ <- Ns.localTimeSet.Ref.localTimeSet.insert(Set(localTime1), Set(localTime1)).transact
        _ <- Ns.localDateTimeSet.Ref.localDateTimeSet.insert(Set(localDateTime1), Set(localDateTime1)).transact
        _ <- Ns.offsetTimeSet.Ref.offsetTimeSet.insert(Set(offsetTime1), Set(offsetTime1)).transact
        _ <- Ns.offsetDateTimeSet.Ref.offsetDateTimeSet.insert(Set(offsetDateTime1), Set(offsetDateTime1)).transact
        _ <- Ns.zonedDateTimeSet.Ref.zonedDateTimeSet.insert(Set(zonedDateTime1), Set(zonedDateTime1)).transact
        _ <- Ns.uuidSet.Ref.uuidSet.insert(Set(uuid1), Set(uuid1)).transact
        _ <- Ns.uriSet.Ref.uriSet.insert(Set(uri1), Set(uri1)).transact
        _ <- Ns.byteSet.Ref.byteSet.insert(Set(byte1), Set(byte1)).transact
        _ <- Ns.shortSet.Ref.shortSet.insert(Set(short1), Set(short1)).transact
        _ <- Ns.charSet.Ref.charSet.insert(Set(char1), Set(char1)).transact

        _ <- Ns.stringSet.has(Ref.stringSet_).Ref.stringSet.query.get.map(_ ==> List((Set(string1), Set(string1))))
        _ <- Ns.intSet.has(Ref.intSet_).Ref.intSet.query.get.map(_ ==> List((Set(int1), Set(int1))))
        _ <- Ns.longSet.has(Ref.longSet_).Ref.longSet.query.get.map(_ ==> List((Set(long1), Set(long1))))
        _ <- Ns.floatSet.has(Ref.floatSet_).Ref.floatSet.query.get.map(_ ==> List((Set(float1), Set(float1))))
        _ <- Ns.doubleSet.has(Ref.doubleSet_).Ref.doubleSet.query.get.map(_ ==> List((Set(double1), Set(double1))))
        _ <- Ns.booleanSet.has(Ref.booleanSet_).Ref.booleanSet.query.get.map(_ ==> List((Set(boolean1), Set(boolean1))))
        _ <- Ns.bigIntSet.has(Ref.bigIntSet_).Ref.bigIntSet.query.get.map(_ ==> List((Set(bigInt1), Set(bigInt1))))
        _ <- Ns.bigDecimalSet.has(Ref.bigDecimalSet_).Ref.bigDecimalSet.query.get.map(_ ==> List((Set(bigDecimal1), Set(bigDecimal1))))
        _ <- Ns.dateSet.has(Ref.dateSet_).Ref.dateSet.query.get.map(_ ==> List((Set(date1), Set(date1))))
        _ <- Ns.durationSet.has(Ref.durationSet_).Ref.durationSet.query.get.map(_ ==> List((Set(duration1), Set(duration1))))
        _ <- Ns.instantSet.has(Ref.instantSet_).Ref.instantSet.query.get.map(_ ==> List((Set(instant1), Set(instant1))))
        _ <- Ns.localDateSet.has(Ref.localDateSet_).Ref.localDateSet.query.get.map(_ ==> List((Set(localDate1), Set(localDate1))))
        _ <- Ns.localTimeSet.has(Ref.localTimeSet_).Ref.localTimeSet.query.get.map(_ ==> List((Set(localTime1), Set(localTime1))))
        _ <- Ns.localDateTimeSet.has(Ref.localDateTimeSet_).Ref.localDateTimeSet.query.get.map(_ ==> List((Set(localDateTime1), Set(localDateTime1))))
        _ <- Ns.offsetTimeSet.has(Ref.offsetTimeSet_).Ref.offsetTimeSet.query.get.map(_ ==> List((Set(offsetTime1), Set(offsetTime1))))
        _ <- Ns.offsetDateTimeSet.has(Ref.offsetDateTimeSet_).Ref.offsetDateTimeSet.query.get.map(_ ==> List((Set(offsetDateTime1), Set(offsetDateTime1))))
        _ <- Ns.zonedDateTimeSet.has(Ref.zonedDateTimeSet_).Ref.zonedDateTimeSet.query.get.map(_ ==> List((Set(zonedDateTime1), Set(zonedDateTime1))))
        _ <- Ns.uuidSet.has(Ref.uuidSet_).Ref.uuidSet.query.get.map(_ ==> List((Set(uuid1), Set(uuid1))))
        _ <- Ns.uriSet.has(Ref.uriSet_).Ref.uriSet.query.get.map(_ ==> List((Set(uri1), Set(uri1))))
        _ <- Ns.byteSet.has(Ref.byteSet_).Ref.byteSet.query.get.map(_ ==> List((Set(byte1), Set(byte1))))
        _ <- Ns.shortSet.has(Ref.shortSet_).Ref.shortSet.query.get.map(_ ==> List((Set(short1), Set(short1))))
        _ <- Ns.charSet.has(Ref.charSet_).Ref.charSet.query.get.map(_ ==> List((Set(char1), Set(char1))))
      } yield ()
    }

    "has one" - types { implicit conn =>
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


    "hasNo set" - types { implicit conn =>
      for {
        _ <- Ns.stringSet.Ref.stringSet.insert(Set(string1), Set(string2)).transact
        _ <- Ns.intSet.Ref.intSet.insert(Set(int1), Set(int2)).transact
        _ <- Ns.longSet.Ref.longSet.insert(Set(long1), Set(long2)).transact
        _ <- Ns.floatSet.Ref.floatSet.insert(Set(float1), Set(float2)).transact
        _ <- Ns.doubleSet.Ref.doubleSet.insert(Set(double1), Set(double2)).transact
        _ <- Ns.booleanSet.Ref.booleanSet.insert(Set(boolean1), Set(boolean2)).transact
        _ <- Ns.bigIntSet.Ref.bigIntSet.insert(Set(bigInt1), Set(bigInt2)).transact
        _ <- Ns.bigDecimalSet.Ref.bigDecimalSet.insert(Set(bigDecimal1), Set(bigDecimal2)).transact
        _ <- Ns.dateSet.Ref.dateSet.insert(Set(date1), Set(date2)).transact
        _ <- Ns.durationSet.Ref.durationSet.insert(Set(duration1), Set(duration2)).transact
        _ <- Ns.instantSet.Ref.instantSet.insert(Set(instant1), Set(instant2)).transact
        _ <- Ns.localDateSet.Ref.localDateSet.insert(Set(localDate1), Set(localDate2)).transact
        _ <- Ns.localTimeSet.Ref.localTimeSet.insert(Set(localTime1), Set(localTime2)).transact
        _ <- Ns.localDateTimeSet.Ref.localDateTimeSet.insert(Set(localDateTime1), Set(localDateTime2)).transact
        _ <- Ns.offsetTimeSet.Ref.offsetTimeSet.insert(Set(offsetTime1), Set(offsetTime2)).transact
        _ <- Ns.offsetDateTimeSet.Ref.offsetDateTimeSet.insert(Set(offsetDateTime1), Set(offsetDateTime2)).transact
        _ <- Ns.zonedDateTimeSet.Ref.zonedDateTimeSet.insert(Set(zonedDateTime1), Set(zonedDateTime2)).transact
        _ <- Ns.uuidSet.Ref.uuidSet.insert(Set(uuid1), Set(uuid2)).transact
        _ <- Ns.uriSet.Ref.uriSet.insert(Set(uri1), Set(uri2)).transact
        _ <- Ns.byteSet.Ref.byteSet.insert(Set(byte1), Set(byte2)).transact
        _ <- Ns.shortSet.Ref.shortSet.insert(Set(short1), Set(short2)).transact
        _ <- Ns.charSet.Ref.charSet.insert(Set(char1), Set(char2)).transact

        _ <- Ns.stringSet.hasNo(Ref.stringSet_).Ref.stringSet.query.get.map(_ ==> List((Set(string1), Set(string2))))
        _ <- Ns.intSet.hasNo(Ref.intSet_).Ref.intSet.query.get.map(_ ==> List((Set(int1), Set(int2))))
        _ <- Ns.longSet.hasNo(Ref.longSet_).Ref.longSet.query.get.map(_ ==> List((Set(long1), Set(long2))))
        _ <- Ns.floatSet.hasNo(Ref.floatSet_).Ref.floatSet.query.get.map(_ ==> List((Set(float1), Set(float2))))
        _ <- Ns.doubleSet.hasNo(Ref.doubleSet_).Ref.doubleSet.query.get.map(_ ==> List((Set(double1), Set(double2))))
        _ <- Ns.booleanSet.hasNo(Ref.booleanSet_).Ref.booleanSet.query.get.map(_ ==> List((Set(boolean1), Set(boolean2))))
        _ <- Ns.bigIntSet.hasNo(Ref.bigIntSet_).Ref.bigIntSet.query.get.map(_ ==> List((Set(bigInt1), Set(bigInt2))))
        _ <- Ns.bigDecimalSet.hasNo(Ref.bigDecimalSet_).Ref.bigDecimalSet.query.get.map(_ ==> List((Set(bigDecimal1), Set(bigDecimal2))))
        _ <- Ns.dateSet.hasNo(Ref.dateSet_).Ref.dateSet.query.get.map(_ ==> List((Set(date1), Set(date2))))
        _ <- Ns.durationSet.hasNo(Ref.durationSet_).Ref.durationSet.query.get.map(_ ==> List((Set(duration1), Set(duration2))))
        _ <- Ns.instantSet.hasNo(Ref.instantSet_).Ref.instantSet.query.get.map(_ ==> List((Set(instant1), Set(instant2))))
        _ <- Ns.localDateSet.hasNo(Ref.localDateSet_).Ref.localDateSet.query.get.map(_ ==> List((Set(localDate1), Set(localDate2))))
        _ <- Ns.localTimeSet.hasNo(Ref.localTimeSet_).Ref.localTimeSet.query.get.map(_ ==> List((Set(localTime1), Set(localTime2))))
        _ <- Ns.localDateTimeSet.hasNo(Ref.localDateTimeSet_).Ref.localDateTimeSet.query.get.map(_ ==> List((Set(localDateTime1), Set(localDateTime2))))
        _ <- Ns.offsetTimeSet.hasNo(Ref.offsetTimeSet_).Ref.offsetTimeSet.query.get.map(_ ==> List((Set(offsetTime1), Set(offsetTime2))))
        _ <- Ns.offsetDateTimeSet.hasNo(Ref.offsetDateTimeSet_).Ref.offsetDateTimeSet.query.get.map(_ ==> List((Set(offsetDateTime1), Set(offsetDateTime2))))
        _ <- Ns.zonedDateTimeSet.hasNo(Ref.zonedDateTimeSet_).Ref.zonedDateTimeSet.query.get.map(_ ==> List((Set(zonedDateTime1), Set(zonedDateTime2))))
        _ <- Ns.uuidSet.hasNo(Ref.uuidSet_).Ref.uuidSet.query.get.map(_ ==> List((Set(uuid1), Set(uuid2))))
        _ <- Ns.uriSet.hasNo(Ref.uriSet_).Ref.uriSet.query.get.map(_ ==> List((Set(uri1), Set(uri2))))
        _ <- Ns.byteSet.hasNo(Ref.byteSet_).Ref.byteSet.query.get.map(_ ==> List((Set(byte1), Set(byte2))))
        _ <- Ns.shortSet.hasNo(Ref.shortSet_).Ref.shortSet.query.get.map(_ ==> List((Set(short1), Set(short2))))
        _ <- Ns.charSet.hasNo(Ref.charSet_).Ref.charSet.query.get.map(_ ==> List((Set(char1), Set(char2))))
      } yield ()
    }

    "hasNo one" - types { implicit conn =>
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
