package molecule.coreTests.spi.relation.nested

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._
import scala.concurrent.Future

case class NestedTypes(
  suite: MUnitSuiteWithArrays,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api._
  import suite._

  "Card one mandatory" - types { implicit conn =>
    for {
      _ <- Ref.i.Entities.*(Entity.string).insert(1, List(string1, string2)).transact
      _ <- Ref.i.Entities.*(Entity.int).insert(2, List(int1, int2)).transact
      _ <- Ref.i.Entities.*(Entity.long).insert(3, List(long1, long2)).transact
      _ <- Ref.i.Entities.*(Entity.float).insert(4, List(float1, float2)).transact
      _ <- Ref.i.Entities.*(Entity.double).insert(5, List(double1, double2)).transact
      _ <- Ref.i.Entities.*(Entity.boolean).insert(6, List(boolean1, boolean2)).transact
      _ <- Ref.i.Entities.*(Entity.bigInt).insert(7, List(bigInt1, bigInt2)).transact
      _ <- Ref.i.Entities.*(Entity.bigDecimal).insert(8, List(bigDecimal1, bigDecimal2)).transact
      _ <- Ref.i.Entities.*(Entity.date).insert(9, List(date1, date2)).transact
      _ <- Ref.i.Entities.*(Entity.duration).insert(10, List(duration1, duration2)).transact
      _ <- Ref.i.Entities.*(Entity.instant).insert(11, List(instant1, instant2)).transact
      _ <- Ref.i.Entities.*(Entity.localDate).insert(12, List(localDate1, localDate2)).transact
      _ <- Ref.i.Entities.*(Entity.localTime).insert(13, List(localTime1, localTime2)).transact
      _ <- Ref.i.Entities.*(Entity.localDateTime).insert(14, List(localDateTime1, localDateTime2)).transact
      _ <- Ref.i.Entities.*(Entity.offsetTime).insert(15, List(offsetTime1, offsetTime2)).transact
      _ <- Ref.i.Entities.*(Entity.offsetDateTime).insert(16, List(offsetDateTime1, offsetDateTime2)).transact
      _ <- Ref.i.Entities.*(Entity.zonedDateTime).insert(17, List(zonedDateTime1, zonedDateTime2)).transact
      _ <- Ref.i.Entities.*(Entity.uuid).insert(18, List(uuid1, uuid2)).transact
      _ <- Ref.i.Entities.*(Entity.uri).insert(19, List(uri1, uri2)).transact
      _ <- Ref.i.Entities.*(Entity.byte).insert(20, List(byte1, byte2)).transact
      _ <- Ref.i.Entities.*(Entity.short).insert(21, List(short1, short2)).transact
      _ <- Ref.i.Entities.*(Entity.char).insert(22, List(char1, char2)).transact

      _ <- Ref.i_.Entities.*(Entity.string.a1).query.get.map(_ ==> List(List(string1, string2)))
      _ <- Ref.i_.Entities.*(Entity.int.a1).query.get.map(_ ==> List(List(int1, int2)))
      _ <- Ref.i_.Entities.*(Entity.long.a1).query.get.map(_ ==> List(List(long1, long2)))
      _ <- Ref.i_.Entities.*(Entity.float.a1).query.get.map(_ ==> List(List(float1, float2)))
      _ <- Ref.i_.Entities.*(Entity.double.a1).query.get.map(_ ==> List(List(double1, double2)))
      _ <- Ref.i_.Entities.*(Entity.boolean.a1).query.get.map(_ ==> List(List(boolean1, boolean2)))
      _ <- Ref.i_.Entities.*(Entity.bigInt.a1).query.get.map(_ ==> List(List(bigInt1, bigInt2)))
      _ <- Ref.i_.Entities.*(Entity.bigDecimal.a1).query.get.map(_ ==> List(List(bigDecimal1, bigDecimal2)))
      _ <- Ref.i_.Entities.*(Entity.date.a1).query.get.map(_ ==> List(List(date1, date2)))
      _ <- Ref.i_.Entities.*(Entity.duration.a1).query.get.map(_ ==> List(List(duration1, duration2)))
      _ <- Ref.i_.Entities.*(Entity.instant.a1).query.get.map(_ ==> List(List(instant1, instant2)))
      _ <- Ref.i_.Entities.*(Entity.localDate.a1).query.get.map(_ ==> List(List(localDate1, localDate2)))
      _ <- Ref.i_.Entities.*(Entity.localTime.a1).query.get.map(_ ==> List(List(localTime1, localTime2)))
      _ <- Ref.i_.Entities.*(Entity.localDateTime.a1).query.get.map(_ ==> List(List(localDateTime1, localDateTime2)))
      _ <- Ref.i_.Entities.*(Entity.offsetTime.a1).query.get.map(_ ==> List(List(offsetTime1, offsetTime2)))
      _ <- Ref.i_.Entities.*(Entity.offsetDateTime.a1).query.get.map(_ ==> List(List(offsetDateTime1, offsetDateTime2)))
      _ <- Ref.i_.Entities.*(Entity.zonedDateTime.a1).query.get.map(_ ==> List(List(zonedDateTime1, zonedDateTime2)))
      _ <- Ref.i_.Entities.*(Entity.uuid.a1).query.get.map(_ ==> List(List(uuid1, uuid2)))
      _ <- Ref.i_.Entities.*(Entity.uri.a1).query.get.map(_ ==> List(List(uri1, uri2)))
      _ <- Ref.i_.Entities.*(Entity.byte.a1).query.get.map(_ ==> List(List(byte1, byte2)))
      _ <- Ref.i_.Entities.*(Entity.short.a1).query.get.map(_ ==> List(List(short1, short2)))
      _ <- Ref.i_.Entities.*(Entity.char.a1).query.get.map(_ ==> List(List(char1, char2)))

      _ <- Ref.i_(1).Entities.*?(Entity.string.a1).query.get.map(_ ==> List(List(string1, string2)))
      _ <- Ref.i_(2).Entities.*?(Entity.int.a1).query.get.map(_ ==> List(List(int1, int2)))
      _ <- Ref.i_(3).Entities.*?(Entity.long.a1).query.get.map(_ ==> List(List(long1, long2)))
      _ <- Ref.i_(4).Entities.*?(Entity.float.a1).query.get.map(_ ==> List(List(float1, float2)))
      _ <- Ref.i_(5).Entities.*?(Entity.double.a1).query.get.map(_ ==> List(List(double1, double2)))
      _ <- if (database == "datomic") {
        // `false` wrongly becomes null in Datomic. Seems like a bug
        Future.unit
      } else {
        Ref.i_(6).Entities.*?(Entity.boolean.a1).query.get.map(_ ==> List(List(boolean1, boolean2)))
      }
      _ <- Ref.i_(7).Entities.*?(Entity.bigInt.a1).query.get.map(_ ==> List(List(bigInt1, bigInt2)))
      _ <- Ref.i_(8).Entities.*?(Entity.bigDecimal.a1).query.get.map(_ ==> List(List(bigDecimal1, bigDecimal2)))
      _ <- Ref.i_(9).Entities.*?(Entity.date.a1).query.get.map(_ ==> List(List(date1, date2)))
      _ <- Ref.i_(10).Entities.*?(Entity.duration.a1).query.get.map(_ ==> List(List(duration1, duration2)))
      _ <- Ref.i_(11).Entities.*?(Entity.instant.a1).query.get.map(_ ==> List(List(instant1, instant2)))
      _ <- Ref.i_(12).Entities.*?(Entity.localDate.a1).query.get.map(_ ==> List(List(localDate1, localDate2)))
      _ <- Ref.i_(13).Entities.*?(Entity.localTime.a1).query.get.map(_ ==> List(List(localTime1, localTime2)))
      _ <- Ref.i_(14).Entities.*?(Entity.localDateTime.a1).query.get.map(_ ==> List(List(localDateTime1, localDateTime2)))
      _ <- Ref.i_(15).Entities.*?(Entity.offsetTime.a1).query.get.map(_ ==> List(List(offsetTime1, offsetTime2)))
      _ <- Ref.i_(16).Entities.*?(Entity.offsetDateTime.a1).query.get.map(_ ==> List(List(offsetDateTime1, offsetDateTime2)))
      _ <- Ref.i_(17).Entities.*?(Entity.zonedDateTime.a1).query.get.map(_ ==> List(List(zonedDateTime1, zonedDateTime2)))
      _ <- Ref.i_(18).Entities.*?(Entity.uuid.a1).query.get.map(_ ==> List(List(uuid1, uuid2)))
      _ <- Ref.i_(19).Entities.*?(Entity.uri.a1).query.get.map(_ ==> List(List(uri1, uri2)))
      _ <- Ref.i_(20).Entities.*?(Entity.byte.a1).query.get.map(_ ==> List(List(byte1, byte2)))
      _ <- Ref.i_(21).Entities.*?(Entity.short.a1).query.get.map(_ ==> List(List(short1, short2)))
      _ <- Ref.i_(22).Entities.*?(Entity.char.a1).query.get.map(_ ==> List(List(char1, char2)))
    } yield ()
  }


  "Card one optional" - types { implicit conn =>
    for {
      _ <- Ref.i.Entities.*(Entity.i.string_?).insert(1, List((1, Some(string1)), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.int_?).insert(2, List((1, Some(int1)), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.long_?).insert(3, List((1, Some(long1)), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.float_?).insert(4, List((1, Some(float1)), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.double_?).insert(5, List((1, Some(double1)), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.boolean_?).insert(6, List((1, Some(boolean1)), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.bigInt_?).insert(7, List((1, Some(bigInt1)), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.bigDecimal_?).insert(8, List((1, Some(bigDecimal1)), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.date_?).insert(9, List((1, Some(date1)), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.duration_?).insert(10, List((1, Some(duration1)), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.instant_?).insert(11, List((1, Some(instant1)), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.localDate_?).insert(12, List((1, Some(localDate1)), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.localTime_?).insert(13, List((1, Some(localTime1)), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.localDateTime_?).insert(14, List((1, Some(localDateTime1)), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.offsetTime_?).insert(15, List((1, Some(offsetTime1)), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.offsetDateTime_?).insert(16, List((1, Some(offsetDateTime1)), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.zonedDateTime_?).insert(17, List((1, Some(zonedDateTime1)), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.uuid_?).insert(18, List((1, Some(uuid1)), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.uri_?).insert(19, List((1, Some(uri1)), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.byte_?).insert(20, List((1, Some(byte1)), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.short_?).insert(21, List((1, Some(short1)), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.char_?).insert(22, List((1, Some(char1)), (2, None))).transact

      _ <- Ref.i(1).Entities.*(Entity.i.a1.string_?).query.get.map(_ ==> List((1, List((1, Some(string1)), (2, None)))))
      _ <- Ref.i(2).Entities.*(Entity.i.a1.int_?).query.get.map(_ ==> List((2, List((1, Some(int1)), (2, None)))))
      _ <- Ref.i(3).Entities.*(Entity.i.a1.long_?).query.get.map(_ ==> List((3, List((1, Some(long1)), (2, None)))))
      _ <- Ref.i(4).Entities.*(Entity.i.a1.float_?).query.get.map(_ ==> List((4, List((1, Some(float1)), (2, None)))))
      _ <- Ref.i(5).Entities.*(Entity.i.a1.double_?).query.get.map(_ ==> List((5, List((1, Some(double1)), (2, None)))))
      _ <- Ref.i(6).Entities.*(Entity.i.a1.boolean_?).query.get.map(_ ==> List((6, List((1, Some(boolean1)), (2, None)))))
      _ <- Ref.i(7).Entities.*(Entity.i.a1.bigInt_?).query.get.map(_ ==> List((7, List((1, Some(bigInt1)), (2, None)))))
      _ <- Ref.i(8).Entities.*(Entity.i.a1.bigDecimal_?).query.get.map(_ ==> List((8, List((1, Some(bigDecimal1)), (2, None)))))
      _ <- Ref.i(9).Entities.*(Entity.i.a1.date_?).query.get.map(_ ==> List((9, List((1, Some(date1)), (2, None)))))
      _ <- Ref.i(10).Entities.*(Entity.i.a1.duration_?).query.get.map(_ ==> List((10, List((1, Some(duration1)), (2, None)))))
      _ <- Ref.i(11).Entities.*(Entity.i.a1.instant_?).query.get.map(_ ==> List((11, List((1, Some(instant1)), (2, None)))))
      _ <- Ref.i(12).Entities.*(Entity.i.a1.localDate_?).query.get.map(_ ==> List((12, List((1, Some(localDate1)), (2, None)))))
      _ <- Ref.i(13).Entities.*(Entity.i.a1.localTime_?).query.get.map(_ ==> List((13, List((1, Some(localTime1)), (2, None)))))
      _ <- Ref.i(14).Entities.*(Entity.i.a1.localDateTime_?).query.get.map(_ ==> List((14, List((1, Some(localDateTime1)), (2, None)))))
      _ <- Ref.i(15).Entities.*(Entity.i.a1.offsetTime_?).query.get.map(_ ==> List((15, List((1, Some(offsetTime1)), (2, None)))))
      _ <- Ref.i(16).Entities.*(Entity.i.a1.offsetDateTime_?).query.get.map(_ ==> List((16, List((1, Some(offsetDateTime1)), (2, None)))))
      _ <- Ref.i(17).Entities.*(Entity.i.a1.zonedDateTime_?).query.get.map(_ ==> List((17, List((1, Some(zonedDateTime1)), (2, None)))))
      _ <- Ref.i(18).Entities.*(Entity.i.a1.uuid_?).query.get.map(_ ==> List((18, List((1, Some(uuid1)), (2, None)))))
      _ <- Ref.i(19).Entities.*(Entity.i.a1.uri_?).query.get.map(_ ==> List((19, List((1, Some(uri1)), (2, None)))))
      _ <- Ref.i(20).Entities.*(Entity.i.a1.byte_?).query.get.map(_ ==> List((20, List((1, Some(byte1)), (2, None)))))
      _ <- Ref.i(21).Entities.*(Entity.i.a1.short_?).query.get.map(_ ==> List((21, List((1, Some(short1)), (2, None)))))
      _ <- Ref.i(22).Entities.*(Entity.i.a1.char_?).query.get.map(_ ==> List((22, List((1, Some(char1)), (2, None)))))

      _ <- Ref.i(1).Entities.*?(Entity.i.a1.string_?).query.get.map(_ ==> List((1, List((1, Some(string1)), (2, None)))))
      _ <- Ref.i(2).Entities.*?(Entity.i.a1.int_?).query.get.map(_ ==> List((2, List((1, Some(int1)), (2, None)))))
      _ <- Ref.i(3).Entities.*?(Entity.i.a1.long_?).query.get.map(_ ==> List((3, List((1, Some(long1)), (2, None)))))
      _ <- Ref.i(4).Entities.*?(Entity.i.a1.float_?).query.get.map(_ ==> List((4, List((1, Some(float1)), (2, None)))))
      _ <- Ref.i(5).Entities.*?(Entity.i.a1.double_?).query.get.map(_ ==> List((5, List((1, Some(double1)), (2, None)))))
      _ <- if (database == "datomic") {
        // `false` wrongly becomes null in Datomic. Seems like a bug
        Future.unit
      } else {
        Ref.i(6).Entities.*?(Entity.i.a1.boolean_?).query.get.map(_ ==> List((6, List((1, Some(boolean1)), (2, None)))))
      }
      _ <- Ref.i(7).Entities.*?(Entity.i.a1.bigInt_?).query.get.map(_ ==> List((7, List((1, Some(bigInt1)), (2, None)))))
      _ <- Ref.i(8).Entities.*?(Entity.i.a1.bigDecimal_?).query.get.map(_ ==> List((8, List((1, Some(bigDecimal1)), (2, None)))))
      _ <- Ref.i(9).Entities.*?(Entity.i.a1.date_?).query.get.map(_ ==> List((9, List((1, Some(date1)), (2, None)))))
      _ <- Ref.i(10).Entities.*?(Entity.i.a1.duration_?).query.get.map(_ ==> List((10, List((1, Some(duration1)), (2, None)))))
      _ <- Ref.i(11).Entities.*?(Entity.i.a1.instant_?).query.get.map(_ ==> List((11, List((1, Some(instant1)), (2, None)))))
      _ <- Ref.i(12).Entities.*?(Entity.i.a1.localDate_?).query.get.map(_ ==> List((12, List((1, Some(localDate1)), (2, None)))))
      _ <- Ref.i(13).Entities.*?(Entity.i.a1.localTime_?).query.get.map(_ ==> List((13, List((1, Some(localTime1)), (2, None)))))
      _ <- Ref.i(14).Entities.*?(Entity.i.a1.localDateTime_?).query.get.map(_ ==> List((14, List((1, Some(localDateTime1)), (2, None)))))
      _ <- Ref.i(15).Entities.*?(Entity.i.a1.offsetTime_?).query.get.map(_ ==> List((15, List((1, Some(offsetTime1)), (2, None)))))
      _ <- Ref.i(16).Entities.*?(Entity.i.a1.offsetDateTime_?).query.get.map(_ ==> List((16, List((1, Some(offsetDateTime1)), (2, None)))))
      _ <- Ref.i(17).Entities.*?(Entity.i.a1.zonedDateTime_?).query.get.map(_ ==> List((17, List((1, Some(zonedDateTime1)), (2, None)))))
      _ <- Ref.i(18).Entities.*?(Entity.i.a1.uuid_?).query.get.map(_ ==> List((18, List((1, Some(uuid1)), (2, None)))))
      _ <- Ref.i(19).Entities.*?(Entity.i.a1.uri_?).query.get.map(_ ==> List((19, List((1, Some(uri1)), (2, None)))))
      _ <- Ref.i(20).Entities.*?(Entity.i.a1.byte_?).query.get.map(_ ==> List((20, List((1, Some(byte1)), (2, None)))))
      _ <- Ref.i(21).Entities.*?(Entity.i.a1.short_?).query.get.map(_ ==> List((21, List((1, Some(short1)), (2, None)))))
      _ <- Ref.i(22).Entities.*?(Entity.i.a1.char_?).query.get.map(_ ==> List((22, List((1, Some(char1)), (2, None)))))
    } yield ()
  }


  "Card set mandatory" - types { implicit conn =>
    for {
      _ <- Ref.i.Entities.*(Entity.stringSet).insert(1, List(Set(string1, string2))).transact
      _ <- Ref.i.Entities.*(Entity.intSet).insert(2, List(Set(int1, int2))).transact
      _ <- Ref.i.Entities.*(Entity.longSet).insert(3, List(Set(long1, long2))).transact
      _ <- Ref.i.Entities.*(Entity.floatSet).insert(4, List(Set(float1, float2))).transact
      _ <- Ref.i.Entities.*(Entity.doubleSet).insert(5, List(Set(double1, double2))).transact
      _ <- Ref.i.Entities.*(Entity.booleanSet).insert(6, List(Set(boolean1, boolean2))).transact
      _ <- Ref.i.Entities.*(Entity.bigIntSet).insert(7, List(Set(bigInt1, bigInt2))).transact
      _ <- Ref.i.Entities.*(Entity.bigDecimalSet).insert(8, List(Set(bigDecimal1, bigDecimal2))).transact
      _ <- Ref.i.Entities.*(Entity.dateSet).insert(9, List(Set(date1, date2))).transact
      _ <- Ref.i.Entities.*(Entity.durationSet).insert(10, List(Set(duration1, duration2))).transact
      _ <- Ref.i.Entities.*(Entity.instantSet).insert(11, List(Set(instant1, instant2))).transact
      _ <- Ref.i.Entities.*(Entity.localDateSet).insert(12, List(Set(localDate1, localDate2))).transact
      _ <- Ref.i.Entities.*(Entity.localTimeSet).insert(13, List(Set(localTime1, localTime2))).transact
      _ <- Ref.i.Entities.*(Entity.localDateTimeSet).insert(14, List(Set(localDateTime1, localDateTime2))).transact
      _ <- Ref.i.Entities.*(Entity.offsetTimeSet).insert(15, List(Set(offsetTime1, offsetTime2))).transact
      _ <- Ref.i.Entities.*(Entity.offsetDateTimeSet).insert(16, List(Set(offsetDateTime1, offsetDateTime2))).transact
      _ <- Ref.i.Entities.*(Entity.zonedDateTimeSet).insert(17, List(Set(zonedDateTime1, zonedDateTime2))).transact
      _ <- Ref.i.Entities.*(Entity.uuidSet).insert(18, List(Set(uuid1, uuid2))).transact
      _ <- Ref.i.Entities.*(Entity.uriSet).insert(19, List(Set(uri1, uri2))).transact
      _ <- Ref.i.Entities.*(Entity.byteSet).insert(20, List(Set(byte1, byte2))).transact
      _ <- Ref.i.Entities.*(Entity.shortSet).insert(21, List(Set(short1, short2))).transact
      _ <- Ref.i.Entities.*(Entity.charSet).insert(22, List(Set(char1, char2))).transact

      _ <- Ref.i_.Entities.*(Entity.stringSet).query.get.map(_ ==> List(List(Set(string1, string2))))
      _ <- Ref.i_.Entities.*(Entity.intSet).query.get.map(_ ==> List(List(Set(int1, int2))))
      _ <- Ref.i_.Entities.*(Entity.longSet).query.get.map(_ ==> List(List(Set(long1, long2))))
      _ <- Ref.i_.Entities.*(Entity.floatSet).query.get.map(_ ==> List(List(Set(float1, float2))))
      _ <- Ref.i_.Entities.*(Entity.doubleSet).query.get.map(_ ==> List(List(Set(double1, double2))))
      _ <- Ref.i_.Entities.*(Entity.booleanSet).query.get.map(_ ==> List(List(Set(boolean1, boolean2))))
      _ <- Ref.i_.Entities.*(Entity.bigIntSet).query.get.map(_ ==> List(List(Set(bigInt1, bigInt2))))
      _ <- Ref.i_.Entities.*(Entity.bigDecimalSet).query.get.map(_ ==> List(List(Set(bigDecimal1, bigDecimal2))))
      _ <- Ref.i_.Entities.*(Entity.dateSet).query.get.map(_ ==> List(List(Set(date1, date2))))
      _ <- Ref.i_.Entities.*(Entity.durationSet).query.get.map(_ ==> List(List(Set(duration1, duration2))))
      _ <- Ref.i_.Entities.*(Entity.instantSet).query.get.map(_ ==> List(List(Set(instant1, instant2))))
      _ <- Ref.i_.Entities.*(Entity.localDateSet).query.get.map(_ ==> List(List(Set(localDate1, localDate2))))
      _ <- Ref.i_.Entities.*(Entity.localTimeSet).query.get.map(_ ==> List(List(Set(localTime1, localTime2))))
      _ <- Ref.i_.Entities.*(Entity.localDateTimeSet).query.get.map(_ ==> List(List(Set(localDateTime1, localDateTime2))))
      _ <- Ref.i_.Entities.*(Entity.offsetTimeSet).query.get.map(_ ==> List(List(Set(offsetTime1, offsetTime2))))
      _ <- Ref.i_.Entities.*(Entity.offsetDateTimeSet).query.get.map(_ ==> List(List(Set(offsetDateTime1, offsetDateTime2))))
      _ <- Ref.i_.Entities.*(Entity.zonedDateTimeSet).query.get.map(_ ==> List(List(Set(zonedDateTime1, zonedDateTime2))))
      _ <- Ref.i_.Entities.*(Entity.uuidSet).query.get.map(_ ==> List(List(Set(uuid1, uuid2))))
      _ <- Ref.i_.Entities.*(Entity.uriSet).query.get.map(_ ==> List(List(Set(uri1, uri2))))
      _ <- Ref.i_.Entities.*(Entity.byteSet).query.get.map(_ ==> List(List(Set(byte1, byte2))))
      _ <- Ref.i_.Entities.*(Entity.shortSet).query.get.map(_ ==> List(List(Set(short1, short2))))
      _ <- Ref.i_.Entities.*(Entity.charSet).query.get.map(_ ==> List(List(Set(char1, char2))))

      _ <- Ref.i_(1).Entities.*?(Entity.stringSet).query.get.map(_ ==> List(List(Set(string1, string2))))
      _ <- Ref.i_(2).Entities.*?(Entity.intSet).query.get.map(_ ==> List(List(Set(int1, int2))))
      _ <- Ref.i_(3).Entities.*?(Entity.longSet).query.get.map(_ ==> List(List(Set(long1, long2))))
      _ <- Ref.i_(4).Entities.*?(Entity.floatSet).query.get.map(_ ==> List(List(Set(float1, float2))))
      _ <- Ref.i_(5).Entities.*?(Entity.doubleSet).query.get.map(_ ==> List(List(Set(double1, double2))))
      _ <- Ref.i_(6).Entities.*?(Entity.booleanSet).query.get.map(_ ==> List(List(Set(boolean1, boolean2))))
      _ <- Ref.i_(7).Entities.*?(Entity.bigIntSet).query.get.map(_ ==> List(List(Set(bigInt1, bigInt2))))
      _ <- Ref.i_(8).Entities.*?(Entity.bigDecimalSet).query.get.map(_ ==> List(List(Set(bigDecimal1, bigDecimal2))))
      _ <- Ref.i_(9).Entities.*?(Entity.dateSet).query.get.map(_ ==> List(List(Set(date1, date2))))
      _ <- Ref.i_(10).Entities.*?(Entity.durationSet).query.get.map(_ ==> List(List(Set(duration1, duration2))))
      _ <- Ref.i_(11).Entities.*?(Entity.instantSet).query.get.map(_ ==> List(List(Set(instant1, instant2))))
      _ <- Ref.i_(12).Entities.*?(Entity.localDateSet).query.get.map(_ ==> List(List(Set(localDate1, localDate2))))
      _ <- Ref.i_(13).Entities.*?(Entity.localTimeSet).query.get.map(_ ==> List(List(Set(localTime1, localTime2))))
      _ <- Ref.i_(14).Entities.*?(Entity.localDateTimeSet).query.get.map(_ ==> List(List(Set(localDateTime1, localDateTime2))))
      _ <- Ref.i_(15).Entities.*?(Entity.offsetTimeSet).query.get.map(_ ==> List(List(Set(offsetTime1, offsetTime2))))
      _ <- Ref.i_(16).Entities.*?(Entity.offsetDateTimeSet).query.get.map(_ ==> List(List(Set(offsetDateTime1, offsetDateTime2))))
      _ <- Ref.i_(17).Entities.*?(Entity.zonedDateTimeSet).query.get.map(_ ==> List(List(Set(zonedDateTime1, zonedDateTime2))))
      _ <- Ref.i_(18).Entities.*?(Entity.uuidSet).query.get.map(_ ==> List(List(Set(uuid1, uuid2))))
      _ <- Ref.i_(19).Entities.*?(Entity.uriSet).query.get.map(_ ==> List(List(Set(uri1, uri2))))
      _ <- Ref.i_(20).Entities.*?(Entity.byteSet).query.get.map(_ ==> List(List(Set(byte1, byte2))))
      _ <- Ref.i_(21).Entities.*?(Entity.shortSet).query.get.map(_ ==> List(List(Set(short1, short2))))
      _ <- Ref.i_(22).Entities.*?(Entity.charSet).query.get.map(_ ==> List(List(Set(char1, char2))))
    } yield ()
  }


  "Card set optional" - types { implicit conn =>
    for {
      _ <- Ref.i.Entities.*(Entity.i.stringSet_?).insert(1, List((1, Some(Set(string1, string2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.intSet_?).insert(2, List((1, Some(Set(int1, int2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.longSet_?).insert(3, List((1, Some(Set(long1, long2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.floatSet_?).insert(4, List((1, Some(Set(float1, float2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.doubleSet_?).insert(5, List((1, Some(Set(double1, double2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.booleanSet_?).insert(6, List((1, Some(Set(boolean1, boolean2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.bigIntSet_?).insert(7, List((1, Some(Set(bigInt1, bigInt2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.bigDecimalSet_?).insert(8, List((1, Some(Set(bigDecimal1, bigDecimal2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.dateSet_?).insert(9, List((1, Some(Set(date1, date2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.durationSet_?).insert(10, List((1, Some(Set(duration1, duration2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.instantSet_?).insert(11, List((1, Some(Set(instant1, instant2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.localDateSet_?).insert(12, List((1, Some(Set(localDate1, localDate2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.localTimeSet_?).insert(13, List((1, Some(Set(localTime1, localTime2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.localDateTimeSet_?).insert(14, List((1, Some(Set(localDateTime1, localDateTime2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.offsetTimeSet_?).insert(15, List((1, Some(Set(offsetTime1, offsetTime2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.offsetDateTimeSet_?).insert(16, List((1, Some(Set(offsetDateTime1, offsetDateTime2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.zonedDateTimeSet_?).insert(17, List((1, Some(Set(zonedDateTime1, zonedDateTime2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.uuidSet_?).insert(18, List((1, Some(Set(uuid1, uuid2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.uriSet_?).insert(19, List((1, Some(Set(uri1, uri2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.byteSet_?).insert(20, List((1, Some(Set(byte1, byte2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.shortSet_?).insert(21, List((1, Some(Set(short1, short2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.charSet_?).insert(22, List((1, Some(Set(char1, char2))), (2, None))).transact

      _ <- Ref.i(1).Entities.*(Entity.i.a1.stringSet_?).query.get.map(_ ==> List((1, List((1, Some(Set(string1, string2))), (2, None)))))
      _ <- Ref.i(2).Entities.*(Entity.i.a1.intSet_?).query.get.map(_ ==> List((2, List((1, Some(Set(int1, int2))), (2, None)))))
      _ <- Ref.i(3).Entities.*(Entity.i.a1.longSet_?).query.get.map(_ ==> List((3, List((1, Some(Set(long1, long2))), (2, None)))))
      _ <- Ref.i(4).Entities.*(Entity.i.a1.floatSet_?).query.get.map(_ ==> List((4, List((1, Some(Set(float1, float2))), (2, None)))))
      _ <- Ref.i(5).Entities.*(Entity.i.a1.doubleSet_?).query.get.map(_ ==> List((5, List((1, Some(Set(double1, double2))), (2, None)))))
      _ <- Ref.i(6).Entities.*(Entity.i.a1.booleanSet_?).query.get.map(_ ==> List((6, List((1, Some(Set(boolean1, boolean2))), (2, None)))))
      _ <- Ref.i(7).Entities.*(Entity.i.a1.bigIntSet_?).query.get.map(_ ==> List((7, List((1, Some(Set(bigInt1, bigInt2))), (2, None)))))
      _ <- Ref.i(8).Entities.*(Entity.i.a1.bigDecimalSet_?).query.get.map(_ ==> List((8, List((1, Some(Set(bigDecimal1, bigDecimal2))), (2, None)))))
      _ <- Ref.i(9).Entities.*(Entity.i.a1.dateSet_?).query.get.map(_ ==> List((9, List((1, Some(Set(date1, date2))), (2, None)))))
      _ <- Ref.i(10).Entities.*(Entity.i.a1.durationSet_?).query.get.map(_ ==> List((10, List((1, Some(Set(duration1, duration2))), (2, None)))))
      _ <- Ref.i(11).Entities.*(Entity.i.a1.instantSet_?).query.get.map(_ ==> List((11, List((1, Some(Set(instant1, instant2))), (2, None)))))
      _ <- Ref.i(12).Entities.*(Entity.i.a1.localDateSet_?).query.get.map(_ ==> List((12, List((1, Some(Set(localDate1, localDate2))), (2, None)))))
      _ <- Ref.i(13).Entities.*(Entity.i.a1.localTimeSet_?).query.get.map(_ ==> List((13, List((1, Some(Set(localTime1, localTime2))), (2, None)))))
      _ <- Ref.i(14).Entities.*(Entity.i.a1.localDateTimeSet_?).query.get.map(_ ==> List((14, List((1, Some(Set(localDateTime1, localDateTime2))), (2, None)))))
      _ <- Ref.i(15).Entities.*(Entity.i.a1.offsetTimeSet_?).query.get.map(_ ==> List((15, List((1, Some(Set(offsetTime1, offsetTime2))), (2, None)))))
      _ <- Ref.i(16).Entities.*(Entity.i.a1.offsetDateTimeSet_?).query.get.map(_ ==> List((16, List((1, Some(Set(offsetDateTime1, offsetDateTime2))), (2, None)))))
      _ <- Ref.i(17).Entities.*(Entity.i.a1.zonedDateTimeSet_?).query.get.map(_ ==> List((17, List((1, Some(Set(zonedDateTime1, zonedDateTime2))), (2, None)))))
      _ <- Ref.i(18).Entities.*(Entity.i.a1.uuidSet_?).query.get.map(_ ==> List((18, List((1, Some(Set(uuid1, uuid2))), (2, None)))))
      _ <- Ref.i(19).Entities.*(Entity.i.a1.uriSet_?).query.get.map(_ ==> List((19, List((1, Some(Set(uri1, uri2))), (2, None)))))
      _ <- Ref.i(20).Entities.*(Entity.i.a1.byteSet_?).query.get.map(_ ==> List((20, List((1, Some(Set(byte1, byte2))), (2, None)))))
      _ <- Ref.i(21).Entities.*(Entity.i.a1.shortSet_?).query.get.map(_ ==> List((21, List((1, Some(Set(short1, short2))), (2, None)))))
      _ <- Ref.i(22).Entities.*(Entity.i.a1.charSet_?).query.get.map(_ ==> List((22, List((1, Some(Set(char1, char2))), (2, None)))))

      _ <- Ref.i(1).Entities.*?(Entity.i.a1.stringSet_?).query.get.map(_ ==> List((1, List((1, Some(Set(string1, string2))), (2, None)))))
      _ <- Ref.i(2).Entities.*?(Entity.i.a1.intSet_?).query.get.map(_ ==> List((2, List((1, Some(Set(int1, int2))), (2, None)))))
      _ <- Ref.i(3).Entities.*?(Entity.i.a1.longSet_?).query.get.map(_ ==> List((3, List((1, Some(Set(long1, long2))), (2, None)))))
      _ <- Ref.i(4).Entities.*?(Entity.i.a1.floatSet_?).query.get.map(_ ==> List((4, List((1, Some(Set(float1, float2))), (2, None)))))
      _ <- Ref.i(5).Entities.*?(Entity.i.a1.doubleSet_?).query.get.map(_ ==> List((5, List((1, Some(Set(double1, double2))), (2, None)))))
      _ <- Ref.i(6).Entities.*?(Entity.i.a1.booleanSet_?).query.get.map(_ ==> List((6, List((1, Some(Set(boolean1, boolean2))), (2, None)))))
      _ <- Ref.i(7).Entities.*?(Entity.i.a1.bigIntSet_?).query.get.map(_ ==> List((7, List((1, Some(Set(bigInt1, bigInt2))), (2, None)))))
      _ <- Ref.i(8).Entities.*?(Entity.i.a1.bigDecimalSet_?).query.get.map(_ ==> List((8, List((1, Some(Set(bigDecimal1, bigDecimal2))), (2, None)))))
      _ <- Ref.i(9).Entities.*?(Entity.i.a1.dateSet_?).query.get.map(_ ==> List((9, List((1, Some(Set(date1, date2))), (2, None)))))
      _ <- Ref.i(10).Entities.*?(Entity.i.a1.durationSet_?).query.get.map(_ ==> List((10, List((1, Some(Set(duration1, duration2))), (2, None)))))
      _ <- Ref.i(11).Entities.*?(Entity.i.a1.instantSet_?).query.get.map(_ ==> List((11, List((1, Some(Set(instant1, instant2))), (2, None)))))
      _ <- Ref.i(12).Entities.*?(Entity.i.a1.localDateSet_?).query.get.map(_ ==> List((12, List((1, Some(Set(localDate1, localDate2))), (2, None)))))
      _ <- Ref.i(13).Entities.*?(Entity.i.a1.localTimeSet_?).query.get.map(_ ==> List((13, List((1, Some(Set(localTime1, localTime2))), (2, None)))))
      _ <- Ref.i(14).Entities.*?(Entity.i.a1.localDateTimeSet_?).query.get.map(_ ==> List((14, List((1, Some(Set(localDateTime1, localDateTime2))), (2, None)))))
      _ <- Ref.i(15).Entities.*?(Entity.i.a1.offsetTimeSet_?).query.get.map(_ ==> List((15, List((1, Some(Set(offsetTime1, offsetTime2))), (2, None)))))
      _ <- Ref.i(16).Entities.*?(Entity.i.a1.offsetDateTimeSet_?).query.get.map(_ ==> List((16, List((1, Some(Set(offsetDateTime1, offsetDateTime2))), (2, None)))))
      _ <- Ref.i(17).Entities.*?(Entity.i.a1.zonedDateTimeSet_?).query.get.map(_ ==> List((17, List((1, Some(Set(zonedDateTime1, zonedDateTime2))), (2, None)))))
      _ <- Ref.i(18).Entities.*?(Entity.i.a1.uuidSet_?).query.get.map(_ ==> List((18, List((1, Some(Set(uuid1, uuid2))), (2, None)))))
      _ <- Ref.i(19).Entities.*?(Entity.i.a1.uriSet_?).query.get.map(_ ==> List((19, List((1, Some(Set(uri1, uri2))), (2, None)))))
      _ <- Ref.i(20).Entities.*?(Entity.i.a1.byteSet_?).query.get.map(_ ==> List((20, List((1, Some(Set(byte1, byte2))), (2, None)))))
      _ <- Ref.i(21).Entities.*?(Entity.i.a1.shortSet_?).query.get.map(_ ==> List((21, List((1, Some(Set(short1, short2))), (2, None)))))
      _ <- Ref.i(22).Entities.*?(Entity.i.a1.charSet_?).query.get.map(_ ==> List((22, List((1, Some(Set(char1, char2))), (2, None)))))
    } yield ()
  }


  "Card seq mandatory" - types { implicit conn =>
    for {
      _ <- Ref.i.Entities.*(Entity.stringSeq).insert(1, List(List(string1, string2))).transact
      _ <- Ref.i.Entities.*(Entity.intSeq).insert(2, List(List(int1, int2))).transact
      _ <- Ref.i.Entities.*(Entity.longSeq).insert(3, List(List(long1, long2))).transact
      _ <- Ref.i.Entities.*(Entity.floatSeq).insert(4, List(List(float1, float2))).transact
      _ <- Ref.i.Entities.*(Entity.doubleSeq).insert(5, List(List(double1, double2))).transact
      _ <- Ref.i.Entities.*(Entity.booleanSeq).insert(6, List(List(boolean1, boolean2))).transact
      _ <- Ref.i.Entities.*(Entity.bigIntSeq).insert(7, List(List(bigInt1, bigInt2))).transact
      _ <- Ref.i.Entities.*(Entity.bigDecimalSeq).insert(8, List(List(bigDecimal1, bigDecimal2))).transact
      _ <- Ref.i.Entities.*(Entity.dateSeq).insert(9, List(List(date1, date2))).transact
      _ <- Ref.i.Entities.*(Entity.durationSeq).insert(10, List(List(duration1, duration2))).transact
      _ <- Ref.i.Entities.*(Entity.instantSeq).insert(11, List(List(instant1, instant2))).transact
      _ <- Ref.i.Entities.*(Entity.localDateSeq).insert(12, List(List(localDate1, localDate2))).transact
      _ <- Ref.i.Entities.*(Entity.localTimeSeq).insert(13, List(List(localTime1, localTime2))).transact
      _ <- Ref.i.Entities.*(Entity.localDateTimeSeq).insert(14, List(List(localDateTime1, localDateTime2))).transact
      _ <- Ref.i.Entities.*(Entity.offsetTimeSeq).insert(15, List(List(offsetTime1, offsetTime2))).transact
      _ <- Ref.i.Entities.*(Entity.offsetDateTimeSeq).insert(16, List(List(offsetDateTime1, offsetDateTime2))).transact
      _ <- Ref.i.Entities.*(Entity.zonedDateTimeSeq).insert(17, List(List(zonedDateTime1, zonedDateTime2))).transact
      _ <- Ref.i.Entities.*(Entity.uuidSeq).insert(18, List(List(uuid1, uuid2))).transact
      _ <- Ref.i.Entities.*(Entity.uriSeq).insert(19, List(List(uri1, uri2))).transact
      _ <- Ref.i.Entities.*(Entity.byteArray).insert(20, List(Array(byte1, byte2))).transact
      _ <- Ref.i.Entities.*(Entity.shortSeq).insert(21, List(List(short1, short2))).transact
      _ <- Ref.i.Entities.*(Entity.charSeq).insert(22, List(List(char1, char2))).transact

      _ <- Ref.i_.Entities.*(Entity.stringSeq).query.get.map(_ ==> List(List(List(string1, string2))))
      _ <- Ref.i_.Entities.*(Entity.intSeq).query.get.map(_ ==> List(List(List(int1, int2))))
      _ <- Ref.i_.Entities.*(Entity.longSeq).query.get.map(_ ==> List(List(List(long1, long2))))
      _ <- Ref.i_.Entities.*(Entity.floatSeq).query.get.map(_ ==> List(List(List(float1, float2))))
      _ <- Ref.i_.Entities.*(Entity.doubleSeq).query.get.map(_ ==> List(List(List(double1, double2))))
      _ <- Ref.i_.Entities.*(Entity.booleanSeq).query.get.map(_ ==> List(List(List(boolean1, boolean2))))
      _ <- Ref.i_.Entities.*(Entity.bigIntSeq).query.get.map(_ ==> List(List(List(bigInt1, bigInt2))))
      _ <- Ref.i_.Entities.*(Entity.bigDecimalSeq).query.get.map(_ ==> List(List(List(bigDecimal1, bigDecimal2))))
      _ <- Ref.i_.Entities.*(Entity.dateSeq).query.get.map(_ ==> List(List(List(date1, date2))))
      _ <- Ref.i_.Entities.*(Entity.durationSeq).query.get.map(_ ==> List(List(List(duration1, duration2))))
      _ <- Ref.i_.Entities.*(Entity.instantSeq).query.get.map(_ ==> List(List(List(instant1, instant2))))
      _ <- Ref.i_.Entities.*(Entity.localDateSeq).query.get.map(_ ==> List(List(List(localDate1, localDate2))))
      _ <- Ref.i_.Entities.*(Entity.localTimeSeq).query.get.map(_ ==> List(List(List(localTime1, localTime2))))
      _ <- Ref.i_.Entities.*(Entity.localDateTimeSeq).query.get.map(_ ==> List(List(List(localDateTime1, localDateTime2))))
      _ <- Ref.i_.Entities.*(Entity.offsetTimeSeq).query.get.map(_ ==> List(List(List(offsetTime1, offsetTime2))))
      _ <- Ref.i_.Entities.*(Entity.offsetDateTimeSeq).query.get.map(_ ==> List(List(List(offsetDateTime1, offsetDateTime2))))
      _ <- Ref.i_.Entities.*(Entity.zonedDateTimeSeq).query.get.map(_ ==> List(List(List(zonedDateTime1, zonedDateTime2))))
      _ <- Ref.i_.Entities.*(Entity.uuidSeq).query.get.map(_ ==> List(List(List(uuid1, uuid2))))
      _ <- Ref.i_.Entities.*(Entity.uriSeq).query.get.map(_ ==> List(List(List(uri1, uri2))))
      _ <- Ref.i_.Entities.*(Entity.byteArray).query.get.map(_ ==> List(List(Array(byte1, byte2))))
      _ <- Ref.i_.Entities.*(Entity.shortSeq).query.get.map(_ ==> List(List(List(short1, short2))))
      _ <- Ref.i_.Entities.*(Entity.charSeq).query.get.map(_ ==> List(List(List(char1, char2))))

      _ <- Ref.i_(1).Entities.*?(Entity.stringSeq).query.get.map(_ ==> List(List(List(string1, string2))))
      _ <- Ref.i_(2).Entities.*?(Entity.intSeq).query.get.map(_ ==> List(List(List(int1, int2))))
      _ <- Ref.i_(3).Entities.*?(Entity.longSeq).query.get.map(_ ==> List(List(List(long1, long2))))
      _ <- Ref.i_(4).Entities.*?(Entity.floatSeq).query.get.map(_ ==> List(List(List(float1, float2))))
      _ <- Ref.i_(5).Entities.*?(Entity.doubleSeq).query.get.map(_ ==> List(List(List(double1, double2))))
      _ <- Ref.i_(6).Entities.*?(Entity.booleanSeq).query.get.map(_ ==> List(List(List(boolean1, boolean2))))
      _ <- Ref.i_(7).Entities.*?(Entity.bigIntSeq).query.get.map(_ ==> List(List(List(bigInt1, bigInt2))))
      _ <- Ref.i_(8).Entities.*?(Entity.bigDecimalSeq).query.get.map(_ ==> List(List(List(bigDecimal1, bigDecimal2))))
      _ <- Ref.i_(9).Entities.*?(Entity.dateSeq).query.get.map(_ ==> List(List(List(date1, date2))))
      _ <- Ref.i_(10).Entities.*?(Entity.durationSeq).query.get.map(_ ==> List(List(List(duration1, duration2))))
      _ <- Ref.i_(11).Entities.*?(Entity.instantSeq).query.get.map(_ ==> List(List(List(instant1, instant2))))
      _ <- Ref.i_(12).Entities.*?(Entity.localDateSeq).query.get.map(_ ==> List(List(List(localDate1, localDate2))))
      _ <- Ref.i_(13).Entities.*?(Entity.localTimeSeq).query.get.map(_ ==> List(List(List(localTime1, localTime2))))
      _ <- Ref.i_(14).Entities.*?(Entity.localDateTimeSeq).query.get.map(_ ==> List(List(List(localDateTime1, localDateTime2))))
      _ <- Ref.i_(15).Entities.*?(Entity.offsetTimeSeq).query.get.map(_ ==> List(List(List(offsetTime1, offsetTime2))))
      _ <- Ref.i_(16).Entities.*?(Entity.offsetDateTimeSeq).query.get.map(_ ==> List(List(List(offsetDateTime1, offsetDateTime2))))
      _ <- Ref.i_(17).Entities.*?(Entity.zonedDateTimeSeq).query.get.map(_ ==> List(List(List(zonedDateTime1, zonedDateTime2))))
      _ <- Ref.i_(18).Entities.*?(Entity.uuidSeq).query.get.map(_ ==> List(List(List(uuid1, uuid2))))
      _ <- Ref.i_(19).Entities.*?(Entity.uriSeq).query.get.map(_ ==> List(List(List(uri1, uri2))))
      _ <- Ref.i_(20).Entities.*?(Entity.byteArray).query.get.map(_ ==> List(List(Array(byte1, byte2))))
      _ <- Ref.i_(21).Entities.*?(Entity.shortSeq).query.get.map(_ ==> List(List(List(short1, short2))))
      _ <- Ref.i_(22).Entities.*?(Entity.charSeq).query.get.map(_ ==> List(List(List(char1, char2))))
    } yield ()
  }


  "Card seq optional" - types { implicit conn =>
    for {
      _ <- Ref.i.Entities.*(Entity.i.stringSeq_?).insert(1, List((1, Some(List(string1, string2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.intSeq_?).insert(2, List((1, Some(List(int1, int2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.longSeq_?).insert(3, List((1, Some(List(long1, long2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.floatSeq_?).insert(4, List((1, Some(List(float1, float2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.doubleSeq_?).insert(5, List((1, Some(List(double1, double2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.booleanSeq_?).insert(6, List((1, Some(List(boolean1, boolean2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.bigIntSeq_?).insert(7, List((1, Some(List(bigInt1, bigInt2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.bigDecimalSeq_?).insert(8, List((1, Some(List(bigDecimal1, bigDecimal2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.dateSeq_?).insert(9, List((1, Some(List(date1, date2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.durationSeq_?).insert(10, List((1, Some(List(duration1, duration2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.instantSeq_?).insert(11, List((1, Some(List(instant1, instant2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.localDateSeq_?).insert(12, List((1, Some(List(localDate1, localDate2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.localTimeSeq_?).insert(13, List((1, Some(List(localTime1, localTime2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.localDateTimeSeq_?).insert(14, List((1, Some(List(localDateTime1, localDateTime2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.offsetTimeSeq_?).insert(15, List((1, Some(List(offsetTime1, offsetTime2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.offsetDateTimeSeq_?).insert(16, List((1, Some(List(offsetDateTime1, offsetDateTime2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.zonedDateTimeSeq_?).insert(17, List((1, Some(List(zonedDateTime1, zonedDateTime2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.uuidSeq_?).insert(18, List((1, Some(List(uuid1, uuid2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.uriSeq_?).insert(19, List((1, Some(List(uri1, uri2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.byteArray_?).insert(20, List((1, Some(Array(byte1, byte2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.shortSeq_?).insert(21, List((1, Some(List(short1, short2))), (2, None))).transact
      _ <- Ref.i.Entities.*(Entity.i.charSeq_?).insert(22, List((1, Some(List(char1, char2))), (2, None))).transact

      _ <- Ref.i(1).Entities.*(Entity.i.a1.stringSeq_?).query.get.map(_ ==> List((1, List((1, Some(List(string1, string2))), (2, None)))))
      _ <- Ref.i(2).Entities.*(Entity.i.a1.intSeq_?).query.get.map(_ ==> List((2, List((1, Some(List(int1, int2))), (2, None)))))
      _ <- Ref.i(3).Entities.*(Entity.i.a1.longSeq_?).query.get.map(_ ==> List((3, List((1, Some(List(long1, long2))), (2, None)))))
      _ <- Ref.i(4).Entities.*(Entity.i.a1.floatSeq_?).query.get.map(_ ==> List((4, List((1, Some(List(float1, float2))), (2, None)))))
      _ <- Ref.i(5).Entities.*(Entity.i.a1.doubleSeq_?).query.get.map(_ ==> List((5, List((1, Some(List(double1, double2))), (2, None)))))
      _ <- Ref.i(6).Entities.*(Entity.i.a1.booleanSeq_?).query.get.map(_ ==> List((6, List((1, Some(List(boolean1, boolean2))), (2, None)))))
      _ <- Ref.i(7).Entities.*(Entity.i.a1.bigIntSeq_?).query.get.map(_ ==> List((7, List((1, Some(List(bigInt1, bigInt2))), (2, None)))))
      _ <- Ref.i(8).Entities.*(Entity.i.a1.bigDecimalSeq_?).query.get.map(_ ==> List((8, List((1, Some(List(bigDecimal1, bigDecimal2))), (2, None)))))
      _ <- Ref.i(9).Entities.*(Entity.i.a1.dateSeq_?).query.get.map(_ ==> List((9, List((1, Some(List(date1, date2))), (2, None)))))
      _ <- Ref.i(10).Entities.*(Entity.i.a1.durationSeq_?).query.get.map(_ ==> List((10, List((1, Some(List(duration1, duration2))), (2, None)))))
      _ <- Ref.i(11).Entities.*(Entity.i.a1.instantSeq_?).query.get.map(_ ==> List((11, List((1, Some(List(instant1, instant2))), (2, None)))))
      _ <- Ref.i(12).Entities.*(Entity.i.a1.localDateSeq_?).query.get.map(_ ==> List((12, List((1, Some(List(localDate1, localDate2))), (2, None)))))
      _ <- Ref.i(13).Entities.*(Entity.i.a1.localTimeSeq_?).query.get.map(_ ==> List((13, List((1, Some(List(localTime1, localTime2))), (2, None)))))
      _ <- Ref.i(14).Entities.*(Entity.i.a1.localDateTimeSeq_?).query.get.map(_ ==> List((14, List((1, Some(List(localDateTime1, localDateTime2))), (2, None)))))
      _ <- Ref.i(15).Entities.*(Entity.i.a1.offsetTimeSeq_?).query.get.map(_ ==> List((15, List((1, Some(List(offsetTime1, offsetTime2))), (2, None)))))
      _ <- Ref.i(16).Entities.*(Entity.i.a1.offsetDateTimeSeq_?).query.get.map(_ ==> List((16, List((1, Some(List(offsetDateTime1, offsetDateTime2))), (2, None)))))
      _ <- Ref.i(17).Entities.*(Entity.i.a1.zonedDateTimeSeq_?).query.get.map(_ ==> List((17, List((1, Some(List(zonedDateTime1, zonedDateTime2))), (2, None)))))
      _ <- Ref.i(18).Entities.*(Entity.i.a1.uuidSeq_?).query.get.map(_ ==> List((18, List((1, Some(List(uuid1, uuid2))), (2, None)))))
      _ <- Ref.i(19).Entities.*(Entity.i.a1.uriSeq_?).query.get.map(_ ==> List((19, List((1, Some(List(uri1, uri2))), (2, None)))))
      _ <- Ref.i(20).Entities.*(Entity.i.a1.byteArray_?).query.get.map(_ ==> List((20, List((1, Some(Array(byte1, byte2))), (2, None)))))
      _ <- Ref.i(21).Entities.*(Entity.i.a1.shortSeq_?).query.get.map(_ ==> List((21, List((1, Some(List(short1, short2))), (2, None)))))
      _ <- Ref.i(22).Entities.*(Entity.i.a1.charSeq_?).query.get.map(_ ==> List((22, List((1, Some(List(char1, char2))), (2, None)))))

      _ <- Ref.i(1).Entities.*?(Entity.i.a1.stringSeq_?).query.get.map(_ ==> List((1, List((1, Some(List(string1, string2))), (2, None)))))
      _ <- Ref.i(2).Entities.*?(Entity.i.a1.intSeq_?).query.get.map(_ ==> List((2, List((1, Some(List(int1, int2))), (2, None)))))
      _ <- Ref.i(3).Entities.*?(Entity.i.a1.longSeq_?).query.get.map(_ ==> List((3, List((1, Some(List(long1, long2))), (2, None)))))
      _ <- Ref.i(4).Entities.*?(Entity.i.a1.floatSeq_?).query.get.map(_ ==> List((4, List((1, Some(List(float1, float2))), (2, None)))))
      _ <- Ref.i(5).Entities.*?(Entity.i.a1.doubleSeq_?).query.get.map(_ ==> List((5, List((1, Some(List(double1, double2))), (2, None)))))
      _ <- Ref.i(6).Entities.*?(Entity.i.a1.booleanSeq_?).query.get.map(_ ==> List((6, List((1, Some(List(boolean1, boolean2))), (2, None)))))
      _ <- Ref.i(7).Entities.*?(Entity.i.a1.bigIntSeq_?).query.get.map(_ ==> List((7, List((1, Some(List(bigInt1, bigInt2))), (2, None)))))
      _ <- Ref.i(8).Entities.*?(Entity.i.a1.bigDecimalSeq_?).query.get.map(_ ==> List((8, List((1, Some(List(bigDecimal1, bigDecimal2))), (2, None)))))
      _ <- Ref.i(9).Entities.*?(Entity.i.a1.dateSeq_?).query.get.map(_ ==> List((9, List((1, Some(List(date1, date2))), (2, None)))))
      _ <- Ref.i(10).Entities.*?(Entity.i.a1.durationSeq_?).query.get.map(_ ==> List((10, List((1, Some(List(duration1, duration2))), (2, None)))))
      _ <- Ref.i(11).Entities.*?(Entity.i.a1.instantSeq_?).query.get.map(_ ==> List((11, List((1, Some(List(instant1, instant2))), (2, None)))))
      _ <- Ref.i(12).Entities.*?(Entity.i.a1.localDateSeq_?).query.get.map(_ ==> List((12, List((1, Some(List(localDate1, localDate2))), (2, None)))))
      _ <- Ref.i(13).Entities.*?(Entity.i.a1.localTimeSeq_?).query.get.map(_ ==> List((13, List((1, Some(List(localTime1, localTime2))), (2, None)))))
      _ <- Ref.i(14).Entities.*?(Entity.i.a1.localDateTimeSeq_?).query.get.map(_ ==> List((14, List((1, Some(List(localDateTime1, localDateTime2))), (2, None)))))
      _ <- Ref.i(15).Entities.*?(Entity.i.a1.offsetTimeSeq_?).query.get.map(_ ==> List((15, List((1, Some(List(offsetTime1, offsetTime2))), (2, None)))))
      _ <- Ref.i(16).Entities.*?(Entity.i.a1.offsetDateTimeSeq_?).query.get.map(_ ==> List((16, List((1, Some(List(offsetDateTime1, offsetDateTime2))), (2, None)))))
      _ <- Ref.i(17).Entities.*?(Entity.i.a1.zonedDateTimeSeq_?).query.get.map(_ ==> List((17, List((1, Some(List(zonedDateTime1, zonedDateTime2))), (2, None)))))
      _ <- Ref.i(18).Entities.*?(Entity.i.a1.uuidSeq_?).query.get.map(_ ==> List((18, List((1, Some(List(uuid1, uuid2))), (2, None)))))
      _ <- Ref.i(19).Entities.*?(Entity.i.a1.uriSeq_?).query.get.map(_ ==> List((19, List((1, Some(List(uri1, uri2))), (2, None)))))
      _ <- Ref.i(20).Entities.*?(Entity.i.a1.byteArray_?).query.get.map(_ ==> List((20, List((1, Some(Array(byte1, byte2))), (2, None)))))
      _ <- Ref.i(21).Entities.*?(Entity.i.a1.shortSeq_?).query.get.map(_ ==> List((21, List((1, Some(List(short1, short2))), (2, None)))))
      _ <- Ref.i(22).Entities.*?(Entity.i.a1.charSeq_?).query.get.map(_ ==> List((22, List((1, Some(List(char1, char2))), (2, None)))))
    } yield ()
  }
}
