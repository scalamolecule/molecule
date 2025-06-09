package molecule.db.compliance.test.filterAttr.seq

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

  "Byte arrays not allowed to filter" - types { implicit conn =>
    def check(errors: String): Boolean = errors.contains("type mismatch")

    check(compileErrors("Entity.byteArray.apply(Ref.byteArray_).Ref.byteArray.query.get"))
    check(compileErrors("Entity.byteArray.not(Ref.byteArray_).Ref.byteArray.query.get"))
    check(compileErrors("Entity.byteArray.has(Ref.byteArray_).Ref.byteArray.query.get"))
    check(compileErrors("Entity.byteArray.hasNo(Ref.byteArray_).Ref.byteArray.query.get"))

    check(compileErrors("Entity.byteArray_.apply(Ref.byteArray_).Ref.byteArray.query.get"))
    check(compileErrors("Entity.byteArray_.not(Ref.byteArray_).Ref.byteArray.query.get"))
    check(compileErrors("Entity.byteArray_.has(Ref.byteArray_).Ref.byteArray.query.get"))
    check(compileErrors("Entity.byteArray_.hasNo(Ref.byteArray_).Ref.byteArray.query.get"))
  }


  "has" - types { implicit conn =>
    for {
      _ <- Entity.stringSeq.Ref.string.insert(List(string1), string1).transact
      _ <- Entity.intSeq.Ref.int.insert(List(int1), int1).transact
      _ <- Entity.longSeq.Ref.long.insert(List(long1), long1).transact
      _ <- Entity.floatSeq.Ref.float.insert(List(float1), float1).transact
      _ <- Entity.doubleSeq.Ref.double.insert(List(double1), double1).transact
      _ <- Entity.booleanSeq.Ref.boolean.insert(List(boolean1), boolean1).transact
      _ <- Entity.bigIntSeq.Ref.bigInt.insert(List(bigInt1), bigInt1).transact
      _ <- Entity.bigDecimalSeq.Ref.bigDecimal.insert(List(bigDecimal1), bigDecimal1).transact
      _ <- Entity.dateSeq.Ref.date.insert(List(date1), date1).transact
      _ <- Entity.durationSeq.Ref.duration.insert(List(duration1), duration1).transact
      _ <- Entity.instantSeq.Ref.instant.insert(List(instant1), instant1).transact
      _ <- Entity.localDateSeq.Ref.localDate.insert(List(localDate1), localDate1).transact
      _ <- Entity.localTimeSeq.Ref.localTime.insert(List(localTime1), localTime1).transact
      _ <- Entity.localDateTimeSeq.Ref.localDateTime.insert(List(localDateTime1), localDateTime1).transact
      _ <- Entity.offsetTimeSeq.Ref.offsetTime.insert(List(offsetTime1), offsetTime1).transact
      _ <- Entity.offsetDateTimeSeq.Ref.offsetDateTime.insert(List(offsetDateTime1), offsetDateTime1).transact
      _ <- Entity.zonedDateTimeSeq.Ref.zonedDateTime.insert(List(zonedDateTime1), zonedDateTime1).transact
      _ <- Entity.uuidSeq.Ref.uuid.insert(List(uuid1), uuid1).transact
      _ <- Entity.uriSeq.Ref.uri.insert(List(uri1), uri1).transact
      _ <- Entity.shortSeq.Ref.short.insert(List(short1), short1).transact
      _ <- Entity.charSeq.Ref.char.insert(List(char1), char1).transact

      _ <- Entity.stringSeq.has(Ref.string_).Ref.string.query.get.map(_ ==> List((List(string1), string1)))
      _ <- Entity.intSeq.has(Ref.int_).Ref.int.query.get.map(_ ==> List((List(int1), int1)))
      _ <- Entity.longSeq.has(Ref.long_).Ref.long.query.get.map(_ ==> List((List(long1), long1)))
      _ <- Entity.floatSeq.has(Ref.float_).Ref.float.query.get.map(_ ==> List((List(float1), float1)))
      _ <- Entity.doubleSeq.has(Ref.double_).Ref.double.query.get.map(_ ==> List((List(double1), double1)))
      _ <- Entity.booleanSeq.has(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((List(boolean1), boolean1)))
      _ <- Entity.bigIntSeq.has(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((List(bigInt1), bigInt1)))
      _ <- Entity.bigDecimalSeq.has(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((List(bigDecimal1), bigDecimal1)))
      _ <- Entity.dateSeq.has(Ref.date_).Ref.date.query.get.map(_ ==> List((List(date1), date1)))
      _ <- Entity.durationSeq.has(Ref.duration_).Ref.duration.query.get.map(_ ==> List((List(duration1), duration1)))
      _ <- Entity.instantSeq.has(Ref.instant_).Ref.instant.query.get.map(_ ==> List((List(instant1), instant1)))
      _ <- Entity.localDateSeq.has(Ref.localDate_).Ref.localDate.query.get.map(_ ==> List((List(localDate1), localDate1)))
      _ <- Entity.localTimeSeq.has(Ref.localTime_).Ref.localTime.query.get.map(_ ==> List((List(localTime1), localTime1)))
      _ <- Entity.localDateTimeSeq.has(Ref.localDateTime_).Ref.localDateTime.query.get.map(_ ==> List((List(localDateTime1), localDateTime1)))
      _ <- Entity.offsetTimeSeq.has(Ref.offsetTime_).Ref.offsetTime.query.get.map(_ ==> List((List(offsetTime1), offsetTime1)))
      _ <- Entity.offsetDateTimeSeq.has(Ref.offsetDateTime_).Ref.offsetDateTime.query.get.map(_ ==> List((List(offsetDateTime1), offsetDateTime1)))
      _ <- Entity.zonedDateTimeSeq.has(Ref.zonedDateTime_).Ref.zonedDateTime.query.get.map(_ ==> List((List(zonedDateTime1), zonedDateTime1)))
      _ <- Entity.uuidSeq.has(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((List(uuid1), uuid1)))
      _ <- Entity.uriSeq.has(Ref.uri_).Ref.uri.query.get.map(_ ==> List((List(uri1), uri1)))
      _ <- Entity.shortSeq.has(Ref.short_).Ref.short.query.get.map(_ ==> List((List(short1), short1)))
      _ <- Entity.charSeq.has(Ref.char_).Ref.char.query.get.map(_ ==> List((List(char1), char1)))
    } yield ()
  }


  "hasNo" - types { implicit conn =>
    for {
      _ <- Entity.stringSeq.Ref.string.insert(List(string1), string2).transact
      _ <- Entity.intSeq.Ref.int.insert(List(int1), int2).transact
      _ <- Entity.longSeq.Ref.long.insert(List(long1), long2).transact
      _ <- Entity.floatSeq.Ref.float.insert(List(float1), float2).transact
      _ <- Entity.doubleSeq.Ref.double.insert(List(double1), double2).transact
      _ <- Entity.booleanSeq.Ref.boolean.insert(List(boolean1), boolean2).transact
      _ <- Entity.bigIntSeq.Ref.bigInt.insert(List(bigInt1), bigInt2).transact
      _ <- Entity.bigDecimalSeq.Ref.bigDecimal.insert(List(bigDecimal1), bigDecimal2).transact
      _ <- Entity.dateSeq.Ref.date.insert(List(date1), date2).transact
      _ <- Entity.durationSeq.Ref.duration.insert(List(duration1), duration2).transact
      _ <- Entity.instantSeq.Ref.instant.insert(List(instant1), instant2).transact
      _ <- Entity.localDateSeq.Ref.localDate.insert(List(localDate1), localDate2).transact
      _ <- Entity.localTimeSeq.Ref.localTime.insert(List(localTime1), localTime2).transact
      _ <- Entity.localDateTimeSeq.Ref.localDateTime.insert(List(localDateTime1), localDateTime2).transact
      _ <- Entity.offsetTimeSeq.Ref.offsetTime.insert(List(offsetTime1), offsetTime2).transact
      _ <- Entity.offsetDateTimeSeq.Ref.offsetDateTime.insert(List(offsetDateTime1), offsetDateTime2).transact
      _ <- Entity.zonedDateTimeSeq.Ref.zonedDateTime.insert(List(zonedDateTime1), zonedDateTime2).transact
      _ <- Entity.uuidSeq.Ref.uuid.insert(List(uuid1), uuid2).transact
      _ <- Entity.uriSeq.Ref.uri.insert(List(uri1), uri2).transact
      _ <- Entity.shortSeq.Ref.short.insert(List(short1), short2).transact
      _ <- Entity.charSeq.Ref.char.insert(List(char1), char2).transact

      _ <- Entity.stringSeq.hasNo(Ref.string_).Ref.string.query.get.map(_ ==> List((List(string1), string2)))
      _ <- Entity.intSeq.hasNo(Ref.int_).Ref.int.query.get.map(_ ==> List((List(int1), int2)))
      _ <- Entity.longSeq.hasNo(Ref.long_).Ref.long.query.get.map(_ ==> List((List(long1), long2)))
      _ <- Entity.floatSeq.hasNo(Ref.float_).Ref.float.query.get.map(_ ==> List((List(float1), float2)))
      _ <- Entity.doubleSeq.hasNo(Ref.double_).Ref.double.query.get.map(_ ==> List((List(double1), double2)))
      _ <- Entity.booleanSeq.hasNo(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((List(boolean1), boolean2)))
      _ <- Entity.bigIntSeq.hasNo(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((List(bigInt1), bigInt2)))
      _ <- Entity.bigDecimalSeq.hasNo(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((List(bigDecimal1), bigDecimal2)))
      _ <- Entity.dateSeq.hasNo(Ref.date_).Ref.date.query.get.map(_ ==> List((List(date1), date2)))
      _ <- Entity.durationSeq.hasNo(Ref.duration_).Ref.duration.query.get.map(_ ==> List((List(duration1), duration2)))
      _ <- Entity.instantSeq.hasNo(Ref.instant_).Ref.instant.query.get.map(_ ==> List((List(instant1), instant2)))
      _ <- Entity.localDateSeq.hasNo(Ref.localDate_).Ref.localDate.query.get.map(_ ==> List((List(localDate1), localDate2)))
      _ <- Entity.localTimeSeq.hasNo(Ref.localTime_).Ref.localTime.query.get.map(_ ==> List((List(localTime1), localTime2)))
      _ <- Entity.localDateTimeSeq.hasNo(Ref.localDateTime_).Ref.localDateTime.query.get.map(_ ==> List((List(localDateTime1), localDateTime2)))
      _ <- Entity.offsetTimeSeq.hasNo(Ref.offsetTime_).Ref.offsetTime.query.get.map(_ ==> List((List(offsetTime1), offsetTime2)))
      _ <- Entity.offsetDateTimeSeq.hasNo(Ref.offsetDateTime_).Ref.offsetDateTime.query.get.map(_ ==> List((List(offsetDateTime1), offsetDateTime2)))
      _ <- Entity.zonedDateTimeSeq.hasNo(Ref.zonedDateTime_).Ref.zonedDateTime.query.get.map(_ ==> List((List(zonedDateTime1), zonedDateTime2)))
      _ <- Entity.uuidSeq.hasNo(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((List(uuid1), uuid2)))
      _ <- Entity.uriSeq.hasNo(Ref.uri_).Ref.uri.query.get.map(_ ==> List((List(uri1), uri2)))
      _ <- Entity.shortSeq.hasNo(Ref.short_).Ref.short.query.get.map(_ ==> List((List(short1), short2)))
      _ <- Entity.charSeq.hasNo(Ref.char_).Ref.char.query.get.map(_ ==> List((List(char1), char2)))
    } yield ()
  }
}
