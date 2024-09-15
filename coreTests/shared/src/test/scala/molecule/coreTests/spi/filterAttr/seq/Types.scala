package molecule.coreTests.spi.filterAttr.seq

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait Types extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "Byte arrays not allowed to filter" - types { implicit conn =>
      compileError("Ns.byteArray.apply(Ref.byteArray_).Ref.byteArray.query.get")
      compileError("Ns.byteArray.not(Ref.byteArray_).Ref.byteArray.query.get")
      compileError("Ns.byteArray.has(Ref.byteArray_).Ref.byteArray.query.get")
      compileError("Ns.byteArray.hasNo(Ref.byteArray_).Ref.byteArray.query.get")

      compileError("Ns.byteArray_.apply(Ref.byteArray_).Ref.byteArray.query.get")
      compileError("Ns.byteArray_.not(Ref.byteArray_).Ref.byteArray.query.get")
      compileError("Ns.byteArray_.has(Ref.byteArray_).Ref.byteArray.query.get")
      compileError("Ns.byteArray_.hasNo(Ref.byteArray_).Ref.byteArray.query.get")
    }


    "has" - types { implicit conn =>
      for {
        _ <- Ns.stringSeq.Ref.string.insert(List(string1), string1).transact
        _ <- Ns.intSeq.Ref.int.insert(List(int1), int1).transact
        _ <- Ns.longSeq.Ref.long.insert(List(long1), long1).transact
        _ <- Ns.floatSeq.Ref.float.insert(List(float1), float1).transact
        _ <- Ns.doubleSeq.Ref.double.insert(List(double1), double1).transact
        _ <- Ns.booleanSeq.Ref.boolean.insert(List(boolean1), boolean1).transact
        _ <- Ns.bigIntSeq.Ref.bigInt.insert(List(bigInt1), bigInt1).transact
        _ <- Ns.bigDecimalSeq.Ref.bigDecimal.insert(List(bigDecimal1), bigDecimal1).transact
        _ <- Ns.dateSeq.Ref.date.insert(List(date1), date1).transact
        _ <- Ns.durationSeq.Ref.duration.insert(List(duration1), duration1).transact
        _ <- Ns.instantSeq.Ref.instant.insert(List(instant1), instant1).transact
        _ <- Ns.localDateSeq.Ref.localDate.insert(List(localDate1), localDate1).transact
        _ <- Ns.localTimeSeq.Ref.localTime.insert(List(localTime1), localTime1).transact
        _ <- Ns.localDateTimeSeq.Ref.localDateTime.insert(List(localDateTime1), localDateTime1).transact
        _ <- Ns.offsetTimeSeq.Ref.offsetTime.insert(List(offsetTime1), offsetTime1).transact
        _ <- Ns.offsetDateTimeSeq.Ref.offsetDateTime.insert(List(offsetDateTime1), offsetDateTime1).transact
        _ <- Ns.zonedDateTimeSeq.Ref.zonedDateTime.insert(List(zonedDateTime1), zonedDateTime1).transact
        _ <- Ns.uuidSeq.Ref.uuid.insert(List(uuid1), uuid1).transact
        _ <- Ns.uriSeq.Ref.uri.insert(List(uri1), uri1).transact
        _ <- Ns.shortSeq.Ref.short.insert(List(short1), short1).transact
        _ <- Ns.charSeq.Ref.char.insert(List(char1), char1).transact

        _ <- Ns.stringSeq.has(Ref.string_).Ref.string.query.get.map(_ ==> List((List(string1), string1)))
        _ <- Ns.intSeq.has(Ref.int_).Ref.int.query.get.map(_ ==> List((List(int1), int1)))
        _ <- Ns.longSeq.has(Ref.long_).Ref.long.query.get.map(_ ==> List((List(long1), long1)))
        _ <- Ns.floatSeq.has(Ref.float_).Ref.float.query.get.map(_ ==> List((List(float1), float1)))
        _ <- Ns.doubleSeq.has(Ref.double_).Ref.double.query.get.map(_ ==> List((List(double1), double1)))
        _ <- Ns.booleanSeq.has(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((List(boolean1), boolean1)))
        _ <- Ns.bigIntSeq.has(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((List(bigInt1), bigInt1)))
        _ <- Ns.bigDecimalSeq.has(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((List(bigDecimal1), bigDecimal1)))
        _ <- Ns.dateSeq.has(Ref.date_).Ref.date.query.get.map(_ ==> List((List(date1), date1)))
        _ <- Ns.durationSeq.has(Ref.duration_).Ref.duration.query.get.map(_ ==> List((List(duration1), duration1)))
        _ <- Ns.instantSeq.has(Ref.instant_).Ref.instant.query.get.map(_ ==> List((List(instant1), instant1)))
        _ <- Ns.localDateSeq.has(Ref.localDate_).Ref.localDate.query.get.map(_ ==> List((List(localDate1), localDate1)))
        _ <- Ns.localTimeSeq.has(Ref.localTime_).Ref.localTime.query.get.map(_ ==> List((List(localTime1), localTime1)))
        _ <- Ns.localDateTimeSeq.has(Ref.localDateTime_).Ref.localDateTime.query.get.map(_ ==> List((List(localDateTime1), localDateTime1)))
        _ <- Ns.offsetTimeSeq.has(Ref.offsetTime_).Ref.offsetTime.query.get.map(_ ==> List((List(offsetTime1), offsetTime1)))
        _ <- Ns.offsetDateTimeSeq.has(Ref.offsetDateTime_).Ref.offsetDateTime.query.get.map(_ ==> List((List(offsetDateTime1), offsetDateTime1)))
        _ <- Ns.zonedDateTimeSeq.has(Ref.zonedDateTime_).Ref.zonedDateTime.query.get.map(_ ==> List((List(zonedDateTime1), zonedDateTime1)))
        _ <- Ns.uuidSeq.has(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((List(uuid1), uuid1)))
        _ <- Ns.uriSeq.has(Ref.uri_).Ref.uri.query.get.map(_ ==> List((List(uri1), uri1)))
        _ <- Ns.shortSeq.has(Ref.short_).Ref.short.query.get.map(_ ==> List((List(short1), short1)))
        _ <- Ns.charSeq.has(Ref.char_).Ref.char.query.get.map(_ ==> List((List(char1), char1)))
      } yield ()
    }


    "hasNo" - types { implicit conn =>
      for {
        _ <- Ns.stringSeq.Ref.string.insert(List(string1), string2).transact
        _ <- Ns.intSeq.Ref.int.insert(List(int1), int2).transact
        _ <- Ns.longSeq.Ref.long.insert(List(long1), long2).transact
        _ <- Ns.floatSeq.Ref.float.insert(List(float1), float2).transact
        _ <- Ns.doubleSeq.Ref.double.insert(List(double1), double2).transact
        _ <- Ns.booleanSeq.Ref.boolean.insert(List(boolean1), boolean2).transact
        _ <- Ns.bigIntSeq.Ref.bigInt.insert(List(bigInt1), bigInt2).transact
        _ <- Ns.bigDecimalSeq.Ref.bigDecimal.insert(List(bigDecimal1), bigDecimal2).transact
        _ <- Ns.dateSeq.Ref.date.insert(List(date1), date2).transact
        _ <- Ns.durationSeq.Ref.duration.insert(List(duration1), duration2).transact
        _ <- Ns.instantSeq.Ref.instant.insert(List(instant1), instant2).transact
        _ <- Ns.localDateSeq.Ref.localDate.insert(List(localDate1), localDate2).transact
        _ <- Ns.localTimeSeq.Ref.localTime.insert(List(localTime1), localTime2).transact
        _ <- Ns.localDateTimeSeq.Ref.localDateTime.insert(List(localDateTime1), localDateTime2).transact
        _ <- Ns.offsetTimeSeq.Ref.offsetTime.insert(List(offsetTime1), offsetTime2).transact
        _ <- Ns.offsetDateTimeSeq.Ref.offsetDateTime.insert(List(offsetDateTime1), offsetDateTime2).transact
        _ <- Ns.zonedDateTimeSeq.Ref.zonedDateTime.insert(List(zonedDateTime1), zonedDateTime2).transact
        _ <- Ns.uuidSeq.Ref.uuid.insert(List(uuid1), uuid2).transact
        _ <- Ns.uriSeq.Ref.uri.insert(List(uri1), uri2).transact
        _ <- Ns.shortSeq.Ref.short.insert(List(short1), short2).transact
        _ <- Ns.charSeq.Ref.char.insert(List(char1), char2).transact

        _ <- Ns.stringSeq.hasNo(Ref.string_).Ref.string.query.get.map(_ ==> List((List(string1), string2)))
        _ <- Ns.intSeq.hasNo(Ref.int_).Ref.int.query.get.map(_ ==> List((List(int1), int2)))
        _ <- Ns.longSeq.hasNo(Ref.long_).Ref.long.query.get.map(_ ==> List((List(long1), long2)))
        _ <- Ns.floatSeq.hasNo(Ref.float_).Ref.float.query.get.map(_ ==> List((List(float1), float2)))
        _ <- Ns.doubleSeq.hasNo(Ref.double_).Ref.double.query.get.map(_ ==> List((List(double1), double2)))
        _ <- Ns.booleanSeq.hasNo(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((List(boolean1), boolean2)))
        _ <- Ns.bigIntSeq.hasNo(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((List(bigInt1), bigInt2)))
        _ <- Ns.bigDecimalSeq.hasNo(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((List(bigDecimal1), bigDecimal2)))
        _ <- Ns.dateSeq.hasNo(Ref.date_).Ref.date.query.get.map(_ ==> List((List(date1), date2)))
        _ <- Ns.durationSeq.hasNo(Ref.duration_).Ref.duration.query.get.map(_ ==> List((List(duration1), duration2)))
        _ <- Ns.instantSeq.hasNo(Ref.instant_).Ref.instant.query.get.map(_ ==> List((List(instant1), instant2)))
        _ <- Ns.localDateSeq.hasNo(Ref.localDate_).Ref.localDate.query.get.map(_ ==> List((List(localDate1), localDate2)))
        _ <- Ns.localTimeSeq.hasNo(Ref.localTime_).Ref.localTime.query.get.map(_ ==> List((List(localTime1), localTime2)))
        _ <- Ns.localDateTimeSeq.hasNo(Ref.localDateTime_).Ref.localDateTime.query.get.map(_ ==> List((List(localDateTime1), localDateTime2)))
        _ <- Ns.offsetTimeSeq.hasNo(Ref.offsetTime_).Ref.offsetTime.query.get.map(_ ==> List((List(offsetTime1), offsetTime2)))
        _ <- Ns.offsetDateTimeSeq.hasNo(Ref.offsetDateTime_).Ref.offsetDateTime.query.get.map(_ ==> List((List(offsetDateTime1), offsetDateTime2)))
        _ <- Ns.zonedDateTimeSeq.hasNo(Ref.zonedDateTime_).Ref.zonedDateTime.query.get.map(_ ==> List((List(zonedDateTime1), zonedDateTime2)))
        _ <- Ns.uuidSeq.hasNo(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((List(uuid1), uuid2)))
        _ <- Ns.uriSeq.hasNo(Ref.uri_).Ref.uri.query.get.map(_ ==> List((List(uri1), uri2)))
        _ <- Ns.shortSeq.hasNo(Ref.short_).Ref.short.query.get.map(_ ==> List((List(short1), short2)))
        _ <- Ns.charSeq.hasNo(Ref.char_).Ref.char.query.get.map(_ ==> List((List(char1), char2)))
      } yield ()
    }
  }
}
