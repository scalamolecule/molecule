package molecule.coreTests.spi.relation.nested

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuiteBase
import molecule.coreTests.util.Array2List
import utest._
import scala.concurrent.Future

trait NestedTypes extends CoreTestSuiteBase with Array2List with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Card one mandatory" - types { implicit conn =>
      for {
        _ <- Ref.i.Nss.*(Ns.string).insert(1, List(string1, string2)).transact
        _ <- Ref.i.Nss.*(Ns.int).insert(2, List(int1, int2)).transact
        _ <- Ref.i.Nss.*(Ns.long).insert(3, List(long1, long2)).transact
        _ <- Ref.i.Nss.*(Ns.float).insert(4, List(float1, float2)).transact
        _ <- Ref.i.Nss.*(Ns.double).insert(5, List(double1, double2)).transact
        _ <- Ref.i.Nss.*(Ns.boolean).insert(6, List(boolean1, boolean2)).transact
        _ <- Ref.i.Nss.*(Ns.bigInt).insert(7, List(bigInt1, bigInt2)).transact
        _ <- Ref.i.Nss.*(Ns.bigDecimal).insert(8, List(bigDecimal1, bigDecimal2)).transact
        _ <- Ref.i.Nss.*(Ns.date).insert(9, List(date1, date2)).transact
        _ <- Ref.i.Nss.*(Ns.duration).insert(10, List(duration1, duration2)).transact
        _ <- Ref.i.Nss.*(Ns.instant).insert(11, List(instant1, instant2)).transact
        _ <- Ref.i.Nss.*(Ns.localDate).insert(12, List(localDate1, localDate2)).transact
        _ <- Ref.i.Nss.*(Ns.localTime).insert(13, List(localTime1, localTime2)).transact
        _ <- Ref.i.Nss.*(Ns.localDateTime).insert(14, List(localDateTime1, localDateTime2)).transact
        _ <- Ref.i.Nss.*(Ns.offsetTime).insert(15, List(offsetTime1, offsetTime2)).transact
        _ <- Ref.i.Nss.*(Ns.offsetDateTime).insert(16, List(offsetDateTime1, offsetDateTime2)).transact
        _ <- Ref.i.Nss.*(Ns.zonedDateTime).insert(17, List(zonedDateTime1, zonedDateTime2)).transact
        _ <- Ref.i.Nss.*(Ns.uuid).insert(18, List(uuid1, uuid2)).transact
        _ <- Ref.i.Nss.*(Ns.uri).insert(19, List(uri1, uri2)).transact
        _ <- Ref.i.Nss.*(Ns.byte).insert(20, List(byte1, byte2)).transact
        _ <- Ref.i.Nss.*(Ns.short).insert(21, List(short1, short2)).transact
        _ <- Ref.i.Nss.*(Ns.char).insert(22, List(char1, char2)).transact

        _ <- Ref.i_.Nss.*(Ns.string.a1).query.get.map(_ ==> List(List(string1, string2)))
        _ <- Ref.i_.Nss.*(Ns.int.a1).query.get.map(_ ==> List(List(int1, int2)))
        _ <- Ref.i_.Nss.*(Ns.long.a1).query.get.map(_ ==> List(List(long1, long2)))
        _ <- Ref.i_.Nss.*(Ns.float.a1).query.get.map(_ ==> List(List(float1, float2)))
        _ <- Ref.i_.Nss.*(Ns.double.a1).query.get.map(_ ==> List(List(double1, double2)))
        _ <- Ref.i_.Nss.*(Ns.boolean.a1).query.get.map(_ ==> List(List(boolean1, boolean2)))
        _ <- Ref.i_.Nss.*(Ns.bigInt.a1).query.get.map(_ ==> List(List(bigInt1, bigInt2)))
        _ <- Ref.i_.Nss.*(Ns.bigDecimal.a1).query.get.map(_ ==> List(List(bigDecimal1, bigDecimal2)))
        _ <- Ref.i_.Nss.*(Ns.date.a1).query.get.map(_ ==> List(List(date1, date2)))
        _ <- Ref.i_.Nss.*(Ns.duration.a1).query.get.map(_ ==> List(List(duration1, duration2)))
        _ <- Ref.i_.Nss.*(Ns.instant.a1).query.get.map(_ ==> List(List(instant1, instant2)))
        _ <- Ref.i_.Nss.*(Ns.localDate.a1).query.get.map(_ ==> List(List(localDate1, localDate2)))
        _ <- Ref.i_.Nss.*(Ns.localTime.a1).query.get.map(_ ==> List(List(localTime1, localTime2)))
        _ <- Ref.i_.Nss.*(Ns.localDateTime.a1).query.get.map(_ ==> List(List(localDateTime1, localDateTime2)))
        _ <- Ref.i_.Nss.*(Ns.offsetTime.a1).query.get.map(_ ==> List(List(offsetTime1, offsetTime2)))
        _ <- Ref.i_.Nss.*(Ns.offsetDateTime.a1).query.get.map(_ ==> List(List(offsetDateTime1, offsetDateTime2)))
        _ <- Ref.i_.Nss.*(Ns.zonedDateTime.a1).query.get.map(_ ==> List(List(zonedDateTime1, zonedDateTime2)))
        _ <- Ref.i_.Nss.*(Ns.uuid.a1).query.get.map(_ ==> List(List(uuid1, uuid2)))
        _ <- Ref.i_.Nss.*(Ns.uri.a1).query.get.map(_ ==> List(List(uri1, uri2)))
        _ <- Ref.i_.Nss.*(Ns.byte.a1).query.get.map(_ ==> List(List(byte1, byte2)))
        _ <- Ref.i_.Nss.*(Ns.short.a1).query.get.map(_ ==> List(List(short1, short2)))
        _ <- Ref.i_.Nss.*(Ns.char.a1).query.get.map(_ ==> List(List(char1, char2)))

        _ <- Ref.i_(1).Nss.*?(Ns.string.a1).query.get.map(_ ==> List(List(string1, string2)))
        _ <- Ref.i_(2).Nss.*?(Ns.int.a1).query.get.map(_ ==> List(List(int1, int2)))
        _ <- Ref.i_(3).Nss.*?(Ns.long.a1).query.get.map(_ ==> List(List(long1, long2)))
        _ <- Ref.i_(4).Nss.*?(Ns.float.a1).query.get.map(_ ==> List(List(float1, float2)))
        _ <- Ref.i_(5).Nss.*?(Ns.double.a1).query.get.map(_ ==> List(List(double1, double2)))
        _ <- if (database == "Datomic") {
          // `false` wrongly becomes null in Datomic. Seems like a bug
          Future.unit
        } else {
          Ref.i_(6).Nss.*?(Ns.boolean.a1).query.get.map(_ ==> List(List(boolean1, boolean2)))
        }
        _ <- Ref.i_(7).Nss.*?(Ns.bigInt.a1).query.get.map(_ ==> List(List(bigInt1, bigInt2)))
        _ <- Ref.i_(8).Nss.*?(Ns.bigDecimal.a1).query.get.map(_ ==> List(List(bigDecimal1, bigDecimal2)))
        _ <- Ref.i_(9).Nss.*?(Ns.date.a1).query.get.map(_ ==> List(List(date1, date2)))
        _ <- Ref.i_(10).Nss.*?(Ns.duration.a1).query.get.map(_ ==> List(List(duration1, duration2)))
        _ <- Ref.i_(11).Nss.*?(Ns.instant.a1).query.get.map(_ ==> List(List(instant1, instant2)))
        _ <- Ref.i_(12).Nss.*?(Ns.localDate.a1).query.get.map(_ ==> List(List(localDate1, localDate2)))
        _ <- Ref.i_(13).Nss.*?(Ns.localTime.a1).query.get.map(_ ==> List(List(localTime1, localTime2)))
        _ <- Ref.i_(14).Nss.*?(Ns.localDateTime.a1).query.get.map(_ ==> List(List(localDateTime1, localDateTime2)))
        _ <- Ref.i_(15).Nss.*?(Ns.offsetTime.a1).query.get.map(_ ==> List(List(offsetTime1, offsetTime2)))
        _ <- Ref.i_(16).Nss.*?(Ns.offsetDateTime.a1).query.get.map(_ ==> List(List(offsetDateTime1, offsetDateTime2)))
        _ <- Ref.i_(17).Nss.*?(Ns.zonedDateTime.a1).query.get.map(_ ==> List(List(zonedDateTime1, zonedDateTime2)))
        _ <- Ref.i_(18).Nss.*?(Ns.uuid.a1).query.get.map(_ ==> List(List(uuid1, uuid2)))
        _ <- Ref.i_(19).Nss.*?(Ns.uri.a1).query.get.map(_ ==> List(List(uri1, uri2)))
        _ <- Ref.i_(20).Nss.*?(Ns.byte.a1).query.get.map(_ ==> List(List(byte1, byte2)))
        _ <- Ref.i_(21).Nss.*?(Ns.short.a1).query.get.map(_ ==> List(List(short1, short2)))
        _ <- Ref.i_(22).Nss.*?(Ns.char.a1).query.get.map(_ ==> List(List(char1, char2)))
      } yield ()
    }


    "Card one optional" - types { implicit conn =>
      for {
        _ <- Ref.i.Nss.*(Ns.i.string_?).insert(1, List((1, Some(string1)), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.int_?).insert(2, List((1, Some(int1)), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.long_?).insert(3, List((1, Some(long1)), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.float_?).insert(4, List((1, Some(float1)), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.double_?).insert(5, List((1, Some(double1)), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.boolean_?).insert(6, List((1, Some(boolean1)), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.bigInt_?).insert(7, List((1, Some(bigInt1)), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.bigDecimal_?).insert(8, List((1, Some(bigDecimal1)), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.date_?).insert(9, List((1, Some(date1)), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.duration_?).insert(10, List((1, Some(duration1)), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.instant_?).insert(11, List((1, Some(instant1)), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.localDate_?).insert(12, List((1, Some(localDate1)), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.localTime_?).insert(13, List((1, Some(localTime1)), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.localDateTime_?).insert(14, List((1, Some(localDateTime1)), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.offsetTime_?).insert(15, List((1, Some(offsetTime1)), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.offsetDateTime_?).insert(16, List((1, Some(offsetDateTime1)), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.zonedDateTime_?).insert(17, List((1, Some(zonedDateTime1)), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.uuid_?).insert(18, List((1, Some(uuid1)), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.uri_?).insert(19, List((1, Some(uri1)), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.byte_?).insert(20, List((1, Some(byte1)), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.short_?).insert(21, List((1, Some(short1)), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.char_?).insert(22, List((1, Some(char1)), (2, None))).transact

        _ <- Ref.i(1).Nss.*(Ns.i.a1.string_?).query.get.map(_ ==> List((1, List((1, Some(string1)), (2, None)))))
        _ <- Ref.i(2).Nss.*(Ns.i.a1.int_?).query.get.map(_ ==> List((2, List((1, Some(int1)), (2, None)))))
        _ <- Ref.i(3).Nss.*(Ns.i.a1.long_?).query.get.map(_ ==> List((3, List((1, Some(long1)), (2, None)))))
        _ <- Ref.i(4).Nss.*(Ns.i.a1.float_?).query.get.map(_ ==> List((4, List((1, Some(float1)), (2, None)))))
        _ <- Ref.i(5).Nss.*(Ns.i.a1.double_?).query.get.map(_ ==> List((5, List((1, Some(double1)), (2, None)))))
        _ <- Ref.i(6).Nss.*(Ns.i.a1.boolean_?).query.get.map(_ ==> List((6, List((1, Some(boolean1)), (2, None)))))
        _ <- Ref.i(7).Nss.*(Ns.i.a1.bigInt_?).query.get.map(_ ==> List((7, List((1, Some(bigInt1)), (2, None)))))
        _ <- Ref.i(8).Nss.*(Ns.i.a1.bigDecimal_?).query.get.map(_ ==> List((8, List((1, Some(bigDecimal1)), (2, None)))))
        _ <- Ref.i(9).Nss.*(Ns.i.a1.date_?).query.get.map(_ ==> List((9, List((1, Some(date1)), (2, None)))))
        _ <- Ref.i(10).Nss.*(Ns.i.a1.duration_?).query.get.map(_ ==> List((10, List((1, Some(duration1)), (2, None)))))
        _ <- Ref.i(11).Nss.*(Ns.i.a1.instant_?).query.get.map(_ ==> List((11, List((1, Some(instant1)), (2, None)))))
        _ <- Ref.i(12).Nss.*(Ns.i.a1.localDate_?).query.get.map(_ ==> List((12, List((1, Some(localDate1)), (2, None)))))
        _ <- Ref.i(13).Nss.*(Ns.i.a1.localTime_?).query.get.map(_ ==> List((13, List((1, Some(localTime1)), (2, None)))))
        _ <- Ref.i(14).Nss.*(Ns.i.a1.localDateTime_?).query.get.map(_ ==> List((14, List((1, Some(localDateTime1)), (2, None)))))
        _ <- Ref.i(15).Nss.*(Ns.i.a1.offsetTime_?).query.get.map(_ ==> List((15, List((1, Some(offsetTime1)), (2, None)))))
        _ <- Ref.i(16).Nss.*(Ns.i.a1.offsetDateTime_?).query.get.map(_ ==> List((16, List((1, Some(offsetDateTime1)), (2, None)))))
        _ <- Ref.i(17).Nss.*(Ns.i.a1.zonedDateTime_?).query.get.map(_ ==> List((17, List((1, Some(zonedDateTime1)), (2, None)))))
        _ <- Ref.i(18).Nss.*(Ns.i.a1.uuid_?).query.get.map(_ ==> List((18, List((1, Some(uuid1)), (2, None)))))
        _ <- Ref.i(19).Nss.*(Ns.i.a1.uri_?).query.get.map(_ ==> List((19, List((1, Some(uri1)), (2, None)))))
        _ <- Ref.i(20).Nss.*(Ns.i.a1.byte_?).query.get.map(_ ==> List((20, List((1, Some(byte1)), (2, None)))))
        _ <- Ref.i(21).Nss.*(Ns.i.a1.short_?).query.get.map(_ ==> List((21, List((1, Some(short1)), (2, None)))))
        _ <- Ref.i(22).Nss.*(Ns.i.a1.char_?).query.get.map(_ ==> List((22, List((1, Some(char1)), (2, None)))))

        _ <- Ref.i(1).Nss.*?(Ns.i.a1.string_?).query.get.map(_ ==> List((1, List((1, Some(string1)), (2, None)))))
        _ <- Ref.i(2).Nss.*?(Ns.i.a1.int_?).query.get.map(_ ==> List((2, List((1, Some(int1)), (2, None)))))
        _ <- Ref.i(3).Nss.*?(Ns.i.a1.long_?).query.get.map(_ ==> List((3, List((1, Some(long1)), (2, None)))))
        _ <- Ref.i(4).Nss.*?(Ns.i.a1.float_?).query.get.map(_ ==> List((4, List((1, Some(float1)), (2, None)))))
        _ <- Ref.i(5).Nss.*?(Ns.i.a1.double_?).query.get.map(_ ==> List((5, List((1, Some(double1)), (2, None)))))
        _ <- if (database == "Datomic") {
          // `false` wrongly becomes null in Datomic. Seems like a bug
          Future.unit
        } else {
          Ref.i(6).Nss.*?(Ns.i.a1.boolean_?).query.get.map(_ ==> List((6, List((1, Some(boolean1)), (2, None)))))
        }
        _ <- Ref.i(7).Nss.*?(Ns.i.a1.bigInt_?).query.get.map(_ ==> List((7, List((1, Some(bigInt1)), (2, None)))))
        _ <- Ref.i(8).Nss.*?(Ns.i.a1.bigDecimal_?).query.get.map(_ ==> List((8, List((1, Some(bigDecimal1)), (2, None)))))
        _ <- Ref.i(9).Nss.*?(Ns.i.a1.date_?).query.get.map(_ ==> List((9, List((1, Some(date1)), (2, None)))))
        _ <- Ref.i(10).Nss.*?(Ns.i.a1.duration_?).query.get.map(_ ==> List((10, List((1, Some(duration1)), (2, None)))))
        _ <- Ref.i(11).Nss.*?(Ns.i.a1.instant_?).query.get.map(_ ==> List((11, List((1, Some(instant1)), (2, None)))))
        _ <- Ref.i(12).Nss.*?(Ns.i.a1.localDate_?).query.get.map(_ ==> List((12, List((1, Some(localDate1)), (2, None)))))
        _ <- Ref.i(13).Nss.*?(Ns.i.a1.localTime_?).query.get.map(_ ==> List((13, List((1, Some(localTime1)), (2, None)))))
        _ <- Ref.i(14).Nss.*?(Ns.i.a1.localDateTime_?).query.get.map(_ ==> List((14, List((1, Some(localDateTime1)), (2, None)))))
        _ <- Ref.i(15).Nss.*?(Ns.i.a1.offsetTime_?).query.get.map(_ ==> List((15, List((1, Some(offsetTime1)), (2, None)))))
        _ <- Ref.i(16).Nss.*?(Ns.i.a1.offsetDateTime_?).query.get.map(_ ==> List((16, List((1, Some(offsetDateTime1)), (2, None)))))
        _ <- Ref.i(17).Nss.*?(Ns.i.a1.zonedDateTime_?).query.get.map(_ ==> List((17, List((1, Some(zonedDateTime1)), (2, None)))))
        _ <- Ref.i(18).Nss.*?(Ns.i.a1.uuid_?).query.get.map(_ ==> List((18, List((1, Some(uuid1)), (2, None)))))
        _ <- Ref.i(19).Nss.*?(Ns.i.a1.uri_?).query.get.map(_ ==> List((19, List((1, Some(uri1)), (2, None)))))
        _ <- Ref.i(20).Nss.*?(Ns.i.a1.byte_?).query.get.map(_ ==> List((20, List((1, Some(byte1)), (2, None)))))
        _ <- Ref.i(21).Nss.*?(Ns.i.a1.short_?).query.get.map(_ ==> List((21, List((1, Some(short1)), (2, None)))))
        _ <- Ref.i(22).Nss.*?(Ns.i.a1.char_?).query.get.map(_ ==> List((22, List((1, Some(char1)), (2, None)))))
      } yield ()
    }


    "Card set mandatory" - types { implicit conn =>
      for {
        _ <- Ref.i.Nss.*(Ns.stringSet).insert(1, List(Set(string1, string2))).transact
        _ <- Ref.i.Nss.*(Ns.intSet).insert(2, List(Set(int1, int2))).transact
        _ <- Ref.i.Nss.*(Ns.longSet).insert(3, List(Set(long1, long2))).transact
        _ <- Ref.i.Nss.*(Ns.floatSet).insert(4, List(Set(float1, float2))).transact
        _ <- Ref.i.Nss.*(Ns.doubleSet).insert(5, List(Set(double1, double2))).transact
        _ <- Ref.i.Nss.*(Ns.booleanSet).insert(6, List(Set(boolean1, boolean2))).transact
        _ <- Ref.i.Nss.*(Ns.bigIntSet).insert(7, List(Set(bigInt1, bigInt2))).transact
        _ <- Ref.i.Nss.*(Ns.bigDecimalSet).insert(8, List(Set(bigDecimal1, bigDecimal2))).transact
        _ <- Ref.i.Nss.*(Ns.dateSet).insert(9, List(Set(date1, date2))).transact
        _ <- Ref.i.Nss.*(Ns.durationSet).insert(10, List(Set(duration1, duration2))).transact
        _ <- Ref.i.Nss.*(Ns.instantSet).insert(11, List(Set(instant1, instant2))).transact
        _ <- Ref.i.Nss.*(Ns.localDateSet).insert(12, List(Set(localDate1, localDate2))).transact
        _ <- Ref.i.Nss.*(Ns.localTimeSet).insert(13, List(Set(localTime1, localTime2))).transact
        _ <- Ref.i.Nss.*(Ns.localDateTimeSet).insert(14, List(Set(localDateTime1, localDateTime2))).transact
        _ <- Ref.i.Nss.*(Ns.offsetTimeSet).insert(15, List(Set(offsetTime1, offsetTime2))).transact
        _ <- Ref.i.Nss.*(Ns.offsetDateTimeSet).insert(16, List(Set(offsetDateTime1, offsetDateTime2))).transact
        _ <- Ref.i.Nss.*(Ns.zonedDateTimeSet).insert(17, List(Set(zonedDateTime1, zonedDateTime2))).transact
        _ <- Ref.i.Nss.*(Ns.uuidSet).insert(18, List(Set(uuid1, uuid2))).transact
        _ <- Ref.i.Nss.*(Ns.uriSet).insert(19, List(Set(uri1, uri2))).transact
        _ <- Ref.i.Nss.*(Ns.byteSet).insert(20, List(Set(byte1, byte2))).transact
        _ <- Ref.i.Nss.*(Ns.shortSet).insert(21, List(Set(short1, short2))).transact
        _ <- Ref.i.Nss.*(Ns.charSet).insert(22, List(Set(char1, char2))).transact

        _ <- Ref.i_.Nss.*(Ns.stringSet).query.get.map(_ ==> List(List(Set(string1, string2))))
        _ <- Ref.i_.Nss.*(Ns.intSet).query.get.map(_ ==> List(List(Set(int1, int2))))
        _ <- Ref.i_.Nss.*(Ns.longSet).query.get.map(_ ==> List(List(Set(long1, long2))))
        _ <- Ref.i_.Nss.*(Ns.floatSet).query.get.map(_ ==> List(List(Set(float1, float2))))
        _ <- Ref.i_.Nss.*(Ns.doubleSet).query.get.map(_ ==> List(List(Set(double1, double2))))
        _ <- Ref.i_.Nss.*(Ns.booleanSet).query.get.map(_ ==> List(List(Set(boolean1, boolean2))))
        _ <- Ref.i_.Nss.*(Ns.bigIntSet).query.get.map(_ ==> List(List(Set(bigInt1, bigInt2))))
        _ <- Ref.i_.Nss.*(Ns.bigDecimalSet).query.get.map(_ ==> List(List(Set(bigDecimal1, bigDecimal2))))
        _ <- Ref.i_.Nss.*(Ns.dateSet).query.get.map(_ ==> List(List(Set(date1, date2))))
        _ <- Ref.i_.Nss.*(Ns.durationSet).query.get.map(_ ==> List(List(Set(duration1, duration2))))
        _ <- Ref.i_.Nss.*(Ns.instantSet).query.get.map(_ ==> List(List(Set(instant1, instant2))))
        _ <- Ref.i_.Nss.*(Ns.localDateSet).query.get.map(_ ==> List(List(Set(localDate1, localDate2))))
        _ <- Ref.i_.Nss.*(Ns.localTimeSet).query.get.map(_ ==> List(List(Set(localTime1, localTime2))))
        _ <- Ref.i_.Nss.*(Ns.localDateTimeSet).query.get.map(_ ==> List(List(Set(localDateTime1, localDateTime2))))
        _ <- Ref.i_.Nss.*(Ns.offsetTimeSet).query.get.map(_ ==> List(List(Set(offsetTime1, offsetTime2))))
        _ <- Ref.i_.Nss.*(Ns.offsetDateTimeSet).query.get.map(_ ==> List(List(Set(offsetDateTime1, offsetDateTime2))))
        _ <- Ref.i_.Nss.*(Ns.zonedDateTimeSet).query.get.map(_ ==> List(List(Set(zonedDateTime1, zonedDateTime2))))
        _ <- Ref.i_.Nss.*(Ns.uuidSet).query.get.map(_ ==> List(List(Set(uuid1, uuid2))))
        _ <- Ref.i_.Nss.*(Ns.uriSet).query.get.map(_ ==> List(List(Set(uri1, uri2))))
        _ <- Ref.i_.Nss.*(Ns.byteSet).query.get.map(_ ==> List(List(Set(byte1, byte2))))
        _ <- Ref.i_.Nss.*(Ns.shortSet).query.get.map(_ ==> List(List(Set(short1, short2))))
        _ <- Ref.i_.Nss.*(Ns.charSet).query.get.map(_ ==> List(List(Set(char1, char2))))

        _ <- Ref.i_(1).Nss.*?(Ns.stringSet).query.get.map(_ ==> List(List(Set(string1, string2))))
        _ <- Ref.i_(2).Nss.*?(Ns.intSet).query.get.map(_ ==> List(List(Set(int1, int2))))
        _ <- Ref.i_(3).Nss.*?(Ns.longSet).query.get.map(_ ==> List(List(Set(long1, long2))))
        _ <- Ref.i_(4).Nss.*?(Ns.floatSet).query.get.map(_ ==> List(List(Set(float1, float2))))
        _ <- Ref.i_(5).Nss.*?(Ns.doubleSet).query.get.map(_ ==> List(List(Set(double1, double2))))
        _ <- Ref.i_(6).Nss.*?(Ns.booleanSet).query.get.map(_ ==> List(List(Set(boolean1, boolean2))))
        _ <- Ref.i_(7).Nss.*?(Ns.bigIntSet).query.get.map(_ ==> List(List(Set(bigInt1, bigInt2))))
        _ <- Ref.i_(8).Nss.*?(Ns.bigDecimalSet).query.get.map(_ ==> List(List(Set(bigDecimal1, bigDecimal2))))
        _ <- Ref.i_(9).Nss.*?(Ns.dateSet).query.get.map(_ ==> List(List(Set(date1, date2))))
        _ <- Ref.i_(10).Nss.*?(Ns.durationSet).query.get.map(_ ==> List(List(Set(duration1, duration2))))
        _ <- Ref.i_(11).Nss.*?(Ns.instantSet).query.get.map(_ ==> List(List(Set(instant1, instant2))))
        _ <- Ref.i_(12).Nss.*?(Ns.localDateSet).query.get.map(_ ==> List(List(Set(localDate1, localDate2))))
        _ <- Ref.i_(13).Nss.*?(Ns.localTimeSet).query.get.map(_ ==> List(List(Set(localTime1, localTime2))))
        _ <- Ref.i_(14).Nss.*?(Ns.localDateTimeSet).query.get.map(_ ==> List(List(Set(localDateTime1, localDateTime2))))
        _ <- Ref.i_(15).Nss.*?(Ns.offsetTimeSet).query.get.map(_ ==> List(List(Set(offsetTime1, offsetTime2))))
        _ <- Ref.i_(16).Nss.*?(Ns.offsetDateTimeSet).query.get.map(_ ==> List(List(Set(offsetDateTime1, offsetDateTime2))))
        _ <- Ref.i_(17).Nss.*?(Ns.zonedDateTimeSet).query.get.map(_ ==> List(List(Set(zonedDateTime1, zonedDateTime2))))
        _ <- Ref.i_(18).Nss.*?(Ns.uuidSet).query.get.map(_ ==> List(List(Set(uuid1, uuid2))))
        _ <- Ref.i_(19).Nss.*?(Ns.uriSet).query.get.map(_ ==> List(List(Set(uri1, uri2))))
        _ <- Ref.i_(20).Nss.*?(Ns.byteSet).query.get.map(_ ==> List(List(Set(byte1, byte2))))
        _ <- Ref.i_(21).Nss.*?(Ns.shortSet).query.get.map(_ ==> List(List(Set(short1, short2))))
        _ <- Ref.i_(22).Nss.*?(Ns.charSet).query.get.map(_ ==> List(List(Set(char1, char2))))
      } yield ()
    }


    "Card set optional" - types { implicit conn =>
      for {
        _ <- Ref.i.Nss.*(Ns.i.stringSet_?).insert(1, List((1, Some(Set(string1, string2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.intSet_?).insert(2, List((1, Some(Set(int1, int2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.longSet_?).insert(3, List((1, Some(Set(long1, long2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.floatSet_?).insert(4, List((1, Some(Set(float1, float2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.doubleSet_?).insert(5, List((1, Some(Set(double1, double2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.booleanSet_?).insert(6, List((1, Some(Set(boolean1, boolean2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.bigIntSet_?).insert(7, List((1, Some(Set(bigInt1, bigInt2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.bigDecimalSet_?).insert(8, List((1, Some(Set(bigDecimal1, bigDecimal2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.dateSet_?).insert(9, List((1, Some(Set(date1, date2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.durationSet_?).insert(10, List((1, Some(Set(duration1, duration2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.instantSet_?).insert(11, List((1, Some(Set(instant1, instant2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.localDateSet_?).insert(12, List((1, Some(Set(localDate1, localDate2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.localTimeSet_?).insert(13, List((1, Some(Set(localTime1, localTime2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.localDateTimeSet_?).insert(14, List((1, Some(Set(localDateTime1, localDateTime2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.offsetTimeSet_?).insert(15, List((1, Some(Set(offsetTime1, offsetTime2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.offsetDateTimeSet_?).insert(16, List((1, Some(Set(offsetDateTime1, offsetDateTime2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.zonedDateTimeSet_?).insert(17, List((1, Some(Set(zonedDateTime1, zonedDateTime2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.uuidSet_?).insert(18, List((1, Some(Set(uuid1, uuid2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.uriSet_?).insert(19, List((1, Some(Set(uri1, uri2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.byteSet_?).insert(20, List((1, Some(Set(byte1, byte2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.shortSet_?).insert(21, List((1, Some(Set(short1, short2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.charSet_?).insert(22, List((1, Some(Set(char1, char2))), (2, None))).transact

        _ <- Ref.i(1).Nss.*(Ns.i.a1.stringSet_?).query.get.map(_ ==> List((1, List((1, Some(Set(string1, string2))), (2, None)))))
        _ <- Ref.i(2).Nss.*(Ns.i.a1.intSet_?).query.get.map(_ ==> List((2, List((1, Some(Set(int1, int2))), (2, None)))))
        _ <- Ref.i(3).Nss.*(Ns.i.a1.longSet_?).query.get.map(_ ==> List((3, List((1, Some(Set(long1, long2))), (2, None)))))
        _ <- Ref.i(4).Nss.*(Ns.i.a1.floatSet_?).query.get.map(_ ==> List((4, List((1, Some(Set(float1, float2))), (2, None)))))
        _ <- Ref.i(5).Nss.*(Ns.i.a1.doubleSet_?).query.get.map(_ ==> List((5, List((1, Some(Set(double1, double2))), (2, None)))))
        _ <- Ref.i(6).Nss.*(Ns.i.a1.booleanSet_?).query.get.map(_ ==> List((6, List((1, Some(Set(boolean1, boolean2))), (2, None)))))
        _ <- Ref.i(7).Nss.*(Ns.i.a1.bigIntSet_?).query.get.map(_ ==> List((7, List((1, Some(Set(bigInt1, bigInt2))), (2, None)))))
        _ <- Ref.i(8).Nss.*(Ns.i.a1.bigDecimalSet_?).query.get.map(_ ==> List((8, List((1, Some(Set(bigDecimal1, bigDecimal2))), (2, None)))))
        _ <- Ref.i(9).Nss.*(Ns.i.a1.dateSet_?).query.get.map(_ ==> List((9, List((1, Some(Set(date1, date2))), (2, None)))))
        _ <- Ref.i(10).Nss.*(Ns.i.a1.durationSet_?).query.get.map(_ ==> List((10, List((1, Some(Set(duration1, duration2))), (2, None)))))
        _ <- Ref.i(11).Nss.*(Ns.i.a1.instantSet_?).query.get.map(_ ==> List((11, List((1, Some(Set(instant1, instant2))), (2, None)))))
        _ <- Ref.i(12).Nss.*(Ns.i.a1.localDateSet_?).query.get.map(_ ==> List((12, List((1, Some(Set(localDate1, localDate2))), (2, None)))))
        _ <- Ref.i(13).Nss.*(Ns.i.a1.localTimeSet_?).query.get.map(_ ==> List((13, List((1, Some(Set(localTime1, localTime2))), (2, None)))))
        _ <- Ref.i(14).Nss.*(Ns.i.a1.localDateTimeSet_?).query.get.map(_ ==> List((14, List((1, Some(Set(localDateTime1, localDateTime2))), (2, None)))))
        _ <- Ref.i(15).Nss.*(Ns.i.a1.offsetTimeSet_?).query.get.map(_ ==> List((15, List((1, Some(Set(offsetTime1, offsetTime2))), (2, None)))))
        _ <- Ref.i(16).Nss.*(Ns.i.a1.offsetDateTimeSet_?).query.get.map(_ ==> List((16, List((1, Some(Set(offsetDateTime1, offsetDateTime2))), (2, None)))))
        _ <- Ref.i(17).Nss.*(Ns.i.a1.zonedDateTimeSet_?).query.get.map(_ ==> List((17, List((1, Some(Set(zonedDateTime1, zonedDateTime2))), (2, None)))))
        _ <- Ref.i(18).Nss.*(Ns.i.a1.uuidSet_?).query.get.map(_ ==> List((18, List((1, Some(Set(uuid1, uuid2))), (2, None)))))
        _ <- Ref.i(19).Nss.*(Ns.i.a1.uriSet_?).query.get.map(_ ==> List((19, List((1, Some(Set(uri1, uri2))), (2, None)))))
        _ <- Ref.i(20).Nss.*(Ns.i.a1.byteSet_?).query.get.map(_ ==> List((20, List((1, Some(Set(byte1, byte2))), (2, None)))))
        _ <- Ref.i(21).Nss.*(Ns.i.a1.shortSet_?).query.get.map(_ ==> List((21, List((1, Some(Set(short1, short2))), (2, None)))))
        _ <- Ref.i(22).Nss.*(Ns.i.a1.charSet_?).query.get.map(_ ==> List((22, List((1, Some(Set(char1, char2))), (2, None)))))

        _ <- Ref.i(1).Nss.*?(Ns.i.a1.stringSet_?).query.get.map(_ ==> List((1, List((1, Some(Set(string1, string2))), (2, None)))))
        _ <- Ref.i(2).Nss.*?(Ns.i.a1.intSet_?).query.get.map(_ ==> List((2, List((1, Some(Set(int1, int2))), (2, None)))))
        _ <- Ref.i(3).Nss.*?(Ns.i.a1.longSet_?).query.get.map(_ ==> List((3, List((1, Some(Set(long1, long2))), (2, None)))))
        _ <- Ref.i(4).Nss.*?(Ns.i.a1.floatSet_?).query.get.map(_ ==> List((4, List((1, Some(Set(float1, float2))), (2, None)))))
        _ <- Ref.i(5).Nss.*?(Ns.i.a1.doubleSet_?).query.get.map(_ ==> List((5, List((1, Some(Set(double1, double2))), (2, None)))))
        _ <- Ref.i(6).Nss.*?(Ns.i.a1.booleanSet_?).query.get.map(_ ==> List((6, List((1, Some(Set(boolean1, boolean2))), (2, None)))))
        _ <- Ref.i(7).Nss.*?(Ns.i.a1.bigIntSet_?).query.get.map(_ ==> List((7, List((1, Some(Set(bigInt1, bigInt2))), (2, None)))))
        _ <- Ref.i(8).Nss.*?(Ns.i.a1.bigDecimalSet_?).query.get.map(_ ==> List((8, List((1, Some(Set(bigDecimal1, bigDecimal2))), (2, None)))))
        _ <- Ref.i(9).Nss.*?(Ns.i.a1.dateSet_?).query.get.map(_ ==> List((9, List((1, Some(Set(date1, date2))), (2, None)))))
        _ <- Ref.i(10).Nss.*?(Ns.i.a1.durationSet_?).query.get.map(_ ==> List((10, List((1, Some(Set(duration1, duration2))), (2, None)))))
        _ <- Ref.i(11).Nss.*?(Ns.i.a1.instantSet_?).query.get.map(_ ==> List((11, List((1, Some(Set(instant1, instant2))), (2, None)))))
        _ <- Ref.i(12).Nss.*?(Ns.i.a1.localDateSet_?).query.get.map(_ ==> List((12, List((1, Some(Set(localDate1, localDate2))), (2, None)))))
        _ <- Ref.i(13).Nss.*?(Ns.i.a1.localTimeSet_?).query.get.map(_ ==> List((13, List((1, Some(Set(localTime1, localTime2))), (2, None)))))
        _ <- Ref.i(14).Nss.*?(Ns.i.a1.localDateTimeSet_?).query.get.map(_ ==> List((14, List((1, Some(Set(localDateTime1, localDateTime2))), (2, None)))))
        _ <- Ref.i(15).Nss.*?(Ns.i.a1.offsetTimeSet_?).query.get.map(_ ==> List((15, List((1, Some(Set(offsetTime1, offsetTime2))), (2, None)))))
        _ <- Ref.i(16).Nss.*?(Ns.i.a1.offsetDateTimeSet_?).query.get.map(_ ==> List((16, List((1, Some(Set(offsetDateTime1, offsetDateTime2))), (2, None)))))
        _ <- Ref.i(17).Nss.*?(Ns.i.a1.zonedDateTimeSet_?).query.get.map(_ ==> List((17, List((1, Some(Set(zonedDateTime1, zonedDateTime2))), (2, None)))))
        _ <- Ref.i(18).Nss.*?(Ns.i.a1.uuidSet_?).query.get.map(_ ==> List((18, List((1, Some(Set(uuid1, uuid2))), (2, None)))))
        _ <- Ref.i(19).Nss.*?(Ns.i.a1.uriSet_?).query.get.map(_ ==> List((19, List((1, Some(Set(uri1, uri2))), (2, None)))))
        _ <- Ref.i(20).Nss.*?(Ns.i.a1.byteSet_?).query.get.map(_ ==> List((20, List((1, Some(Set(byte1, byte2))), (2, None)))))
        _ <- Ref.i(21).Nss.*?(Ns.i.a1.shortSet_?).query.get.map(_ ==> List((21, List((1, Some(Set(short1, short2))), (2, None)))))
        _ <- Ref.i(22).Nss.*?(Ns.i.a1.charSet_?).query.get.map(_ ==> List((22, List((1, Some(Set(char1, char2))), (2, None)))))
      } yield ()
    }


    "Card seq mandatory" - types { implicit conn =>
      for {
        _ <- Ref.i.Nss.*(Ns.stringSeq).insert(1, List(List(string1, string2))).transact
        _ <- Ref.i.Nss.*(Ns.intSeq).insert(2, List(List(int1, int2))).transact
        _ <- Ref.i.Nss.*(Ns.longSeq).insert(3, List(List(long1, long2))).transact
        _ <- Ref.i.Nss.*(Ns.floatSeq).insert(4, List(List(float1, float2))).transact
        _ <- Ref.i.Nss.*(Ns.doubleSeq).insert(5, List(List(double1, double2))).transact
        _ <- Ref.i.Nss.*(Ns.booleanSeq).insert(6, List(List(boolean1, boolean2))).transact
        _ <- Ref.i.Nss.*(Ns.bigIntSeq).insert(7, List(List(bigInt1, bigInt2))).transact
        _ <- Ref.i.Nss.*(Ns.bigDecimalSeq).insert(8, List(List(bigDecimal1, bigDecimal2))).transact
        _ <- Ref.i.Nss.*(Ns.dateSeq).insert(9, List(List(date1, date2))).transact
        _ <- Ref.i.Nss.*(Ns.durationSeq).insert(10, List(List(duration1, duration2))).transact
        _ <- Ref.i.Nss.*(Ns.instantSeq).insert(11, List(List(instant1, instant2))).transact
        _ <- Ref.i.Nss.*(Ns.localDateSeq).insert(12, List(List(localDate1, localDate2))).transact
        _ <- Ref.i.Nss.*(Ns.localTimeSeq).insert(13, List(List(localTime1, localTime2))).transact
        _ <- Ref.i.Nss.*(Ns.localDateTimeSeq).insert(14, List(List(localDateTime1, localDateTime2))).transact
        _ <- Ref.i.Nss.*(Ns.offsetTimeSeq).insert(15, List(List(offsetTime1, offsetTime2))).transact
        _ <- Ref.i.Nss.*(Ns.offsetDateTimeSeq).insert(16, List(List(offsetDateTime1, offsetDateTime2))).transact
        _ <- Ref.i.Nss.*(Ns.zonedDateTimeSeq).insert(17, List(List(zonedDateTime1, zonedDateTime2))).transact
        _ <- Ref.i.Nss.*(Ns.uuidSeq).insert(18, List(List(uuid1, uuid2))).transact
        _ <- Ref.i.Nss.*(Ns.uriSeq).insert(19, List(List(uri1, uri2))).transact
        _ <- Ref.i.Nss.*(Ns.byteArray).insert(20, List(Array(byte1, byte2))).transact
        _ <- Ref.i.Nss.*(Ns.shortSeq).insert(21, List(List(short1, short2))).transact
        _ <- Ref.i.Nss.*(Ns.charSeq).insert(22, List(List(char1, char2))).transact

        _ <- Ref.i_.Nss.*(Ns.stringSeq).query.get.map(_ ==> List(List(List(string1, string2))))
        _ <- Ref.i_.Nss.*(Ns.intSeq).query.get.map(_ ==> List(List(List(int1, int2))))
        _ <- Ref.i_.Nss.*(Ns.longSeq).query.get.map(_ ==> List(List(List(long1, long2))))
        _ <- Ref.i_.Nss.*(Ns.floatSeq).query.get.map(_ ==> List(List(List(float1, float2))))
        _ <- Ref.i_.Nss.*(Ns.doubleSeq).query.get.map(_ ==> List(List(List(double1, double2))))
        _ <- Ref.i_.Nss.*(Ns.booleanSeq).query.get.map(_ ==> List(List(List(boolean1, boolean2))))
        _ <- Ref.i_.Nss.*(Ns.bigIntSeq).query.get.map(_ ==> List(List(List(bigInt1, bigInt2))))
        _ <- Ref.i_.Nss.*(Ns.bigDecimalSeq).query.get.map(_ ==> List(List(List(bigDecimal1, bigDecimal2))))
        _ <- Ref.i_.Nss.*(Ns.dateSeq).query.get.map(_ ==> List(List(List(date1, date2))))
        _ <- Ref.i_.Nss.*(Ns.durationSeq).query.get.map(_ ==> List(List(List(duration1, duration2))))
        _ <- Ref.i_.Nss.*(Ns.instantSeq).query.get.map(_ ==> List(List(List(instant1, instant2))))
        _ <- Ref.i_.Nss.*(Ns.localDateSeq).query.get.map(_ ==> List(List(List(localDate1, localDate2))))
        _ <- Ref.i_.Nss.*(Ns.localTimeSeq).query.get.map(_ ==> List(List(List(localTime1, localTime2))))
        _ <- Ref.i_.Nss.*(Ns.localDateTimeSeq).query.get.map(_ ==> List(List(List(localDateTime1, localDateTime2))))
        _ <- Ref.i_.Nss.*(Ns.offsetTimeSeq).query.get.map(_ ==> List(List(List(offsetTime1, offsetTime2))))
        _ <- Ref.i_.Nss.*(Ns.offsetDateTimeSeq).query.get.map(_ ==> List(List(List(offsetDateTime1, offsetDateTime2))))
        _ <- Ref.i_.Nss.*(Ns.zonedDateTimeSeq).query.get.map(_ ==> List(List(List(zonedDateTime1, zonedDateTime2))))
        _ <- Ref.i_.Nss.*(Ns.uuidSeq).query.get.map(_ ==> List(List(List(uuid1, uuid2))))
        _ <- Ref.i_.Nss.*(Ns.uriSeq).query.get.map(_ ==> List(List(List(uri1, uri2))))
        _ <- Ref.i_.Nss.*(Ns.byteArray).query.get.map(_ ==> List(List(Array(byte1, byte2))))
        _ <- Ref.i_.Nss.*(Ns.shortSeq).query.get.map(_ ==> List(List(List(short1, short2))))
        _ <- Ref.i_.Nss.*(Ns.charSeq).query.get.map(_ ==> List(List(List(char1, char2))))

        _ <- Ref.i_(1).Nss.*?(Ns.stringSeq).query.get.map(_ ==> List(List(List(string1, string2))))
        _ <- Ref.i_(2).Nss.*?(Ns.intSeq).query.get.map(_ ==> List(List(List(int1, int2))))
        _ <- Ref.i_(3).Nss.*?(Ns.longSeq).query.get.map(_ ==> List(List(List(long1, long2))))
        _ <- Ref.i_(4).Nss.*?(Ns.floatSeq).query.get.map(_ ==> List(List(List(float1, float2))))
        _ <- Ref.i_(5).Nss.*?(Ns.doubleSeq).query.get.map(_ ==> List(List(List(double1, double2))))
        _ <- Ref.i_(6).Nss.*?(Ns.booleanSeq).query.get.map(_ ==> List(List(List(boolean1, boolean2))))
        _ <- Ref.i_(7).Nss.*?(Ns.bigIntSeq).query.get.map(_ ==> List(List(List(bigInt1, bigInt2))))
        _ <- Ref.i_(8).Nss.*?(Ns.bigDecimalSeq).query.get.map(_ ==> List(List(List(bigDecimal1, bigDecimal2))))
        _ <- Ref.i_(9).Nss.*?(Ns.dateSeq).query.get.map(_ ==> List(List(List(date1, date2))))
        _ <- Ref.i_(10).Nss.*?(Ns.durationSeq).query.get.map(_ ==> List(List(List(duration1, duration2))))
        _ <- Ref.i_(11).Nss.*?(Ns.instantSeq).query.get.map(_ ==> List(List(List(instant1, instant2))))
        _ <- Ref.i_(12).Nss.*?(Ns.localDateSeq).query.get.map(_ ==> List(List(List(localDate1, localDate2))))
        _ <- Ref.i_(13).Nss.*?(Ns.localTimeSeq).query.get.map(_ ==> List(List(List(localTime1, localTime2))))
        _ <- Ref.i_(14).Nss.*?(Ns.localDateTimeSeq).query.get.map(_ ==> List(List(List(localDateTime1, localDateTime2))))
        _ <- Ref.i_(15).Nss.*?(Ns.offsetTimeSeq).query.get.map(_ ==> List(List(List(offsetTime1, offsetTime2))))
        _ <- Ref.i_(16).Nss.*?(Ns.offsetDateTimeSeq).query.get.map(_ ==> List(List(List(offsetDateTime1, offsetDateTime2))))
        _ <- Ref.i_(17).Nss.*?(Ns.zonedDateTimeSeq).query.get.map(_ ==> List(List(List(zonedDateTime1, zonedDateTime2))))
        _ <- Ref.i_(18).Nss.*?(Ns.uuidSeq).query.get.map(_ ==> List(List(List(uuid1, uuid2))))
        _ <- Ref.i_(19).Nss.*?(Ns.uriSeq).query.get.map(_ ==> List(List(List(uri1, uri2))))
        _ <- Ref.i_(20).Nss.*?(Ns.byteArray).query.get.map(_ ==> List(List(Array(byte1, byte2))))
        _ <- Ref.i_(21).Nss.*?(Ns.shortSeq).query.get.map(_ ==> List(List(List(short1, short2))))
        _ <- Ref.i_(22).Nss.*?(Ns.charSeq).query.get.map(_ ==> List(List(List(char1, char2))))
      } yield ()
    }


    "Card seq optional" - types { implicit conn =>
      for {
        _ <- Ref.i.Nss.*(Ns.i.stringSeq_?).insert(1, List((1, Some(List(string1, string2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.intSeq_?).insert(2, List((1, Some(List(int1, int2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.longSeq_?).insert(3, List((1, Some(List(long1, long2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.floatSeq_?).insert(4, List((1, Some(List(float1, float2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.doubleSeq_?).insert(5, List((1, Some(List(double1, double2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.booleanSeq_?).insert(6, List((1, Some(List(boolean1, boolean2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.bigIntSeq_?).insert(7, List((1, Some(List(bigInt1, bigInt2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.bigDecimalSeq_?).insert(8, List((1, Some(List(bigDecimal1, bigDecimal2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.dateSeq_?).insert(9, List((1, Some(List(date1, date2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.durationSeq_?).insert(10, List((1, Some(List(duration1, duration2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.instantSeq_?).insert(11, List((1, Some(List(instant1, instant2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.localDateSeq_?).insert(12, List((1, Some(List(localDate1, localDate2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.localTimeSeq_?).insert(13, List((1, Some(List(localTime1, localTime2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.localDateTimeSeq_?).insert(14, List((1, Some(List(localDateTime1, localDateTime2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.offsetTimeSeq_?).insert(15, List((1, Some(List(offsetTime1, offsetTime2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.offsetDateTimeSeq_?).insert(16, List((1, Some(List(offsetDateTime1, offsetDateTime2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.zonedDateTimeSeq_?).insert(17, List((1, Some(List(zonedDateTime1, zonedDateTime2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.uuidSeq_?).insert(18, List((1, Some(List(uuid1, uuid2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.uriSeq_?).insert(19, List((1, Some(List(uri1, uri2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.byteArray_?).insert(20, List((1, Some(Array(byte1, byte2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.shortSeq_?).insert(21, List((1, Some(List(short1, short2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.charSeq_?).insert(22, List((1, Some(List(char1, char2))), (2, None))).transact

        _ <- Ref.i(1).Nss.*(Ns.i.a1.stringSeq_?).query.get.map(_ ==> List((1, List((1, Some(List(string1, string2))), (2, None)))))
        _ <- Ref.i(2).Nss.*(Ns.i.a1.intSeq_?).query.get.map(_ ==> List((2, List((1, Some(List(int1, int2))), (2, None)))))
        _ <- Ref.i(3).Nss.*(Ns.i.a1.longSeq_?).query.get.map(_ ==> List((3, List((1, Some(List(long1, long2))), (2, None)))))
        _ <- Ref.i(4).Nss.*(Ns.i.a1.floatSeq_?).query.get.map(_ ==> List((4, List((1, Some(List(float1, float2))), (2, None)))))
        _ <- Ref.i(5).Nss.*(Ns.i.a1.doubleSeq_?).query.get.map(_ ==> List((5, List((1, Some(List(double1, double2))), (2, None)))))
        _ <- Ref.i(6).Nss.*(Ns.i.a1.booleanSeq_?).query.get.map(_ ==> List((6, List((1, Some(List(boolean1, boolean2))), (2, None)))))
        _ <- Ref.i(7).Nss.*(Ns.i.a1.bigIntSeq_?).query.get.map(_ ==> List((7, List((1, Some(List(bigInt1, bigInt2))), (2, None)))))
        _ <- Ref.i(8).Nss.*(Ns.i.a1.bigDecimalSeq_?).query.get.map(_ ==> List((8, List((1, Some(List(bigDecimal1, bigDecimal2))), (2, None)))))
        _ <- Ref.i(9).Nss.*(Ns.i.a1.dateSeq_?).query.get.map(_ ==> List((9, List((1, Some(List(date1, date2))), (2, None)))))
        _ <- Ref.i(10).Nss.*(Ns.i.a1.durationSeq_?).query.get.map(_ ==> List((10, List((1, Some(List(duration1, duration2))), (2, None)))))
        _ <- Ref.i(11).Nss.*(Ns.i.a1.instantSeq_?).query.get.map(_ ==> List((11, List((1, Some(List(instant1, instant2))), (2, None)))))
        _ <- Ref.i(12).Nss.*(Ns.i.a1.localDateSeq_?).query.get.map(_ ==> List((12, List((1, Some(List(localDate1, localDate2))), (2, None)))))
        _ <- Ref.i(13).Nss.*(Ns.i.a1.localTimeSeq_?).query.get.map(_ ==> List((13, List((1, Some(List(localTime1, localTime2))), (2, None)))))
        _ <- Ref.i(14).Nss.*(Ns.i.a1.localDateTimeSeq_?).query.get.map(_ ==> List((14, List((1, Some(List(localDateTime1, localDateTime2))), (2, None)))))
        _ <- Ref.i(15).Nss.*(Ns.i.a1.offsetTimeSeq_?).query.get.map(_ ==> List((15, List((1, Some(List(offsetTime1, offsetTime2))), (2, None)))))
        _ <- Ref.i(16).Nss.*(Ns.i.a1.offsetDateTimeSeq_?).query.get.map(_ ==> List((16, List((1, Some(List(offsetDateTime1, offsetDateTime2))), (2, None)))))
        _ <- Ref.i(17).Nss.*(Ns.i.a1.zonedDateTimeSeq_?).query.get.map(_ ==> List((17, List((1, Some(List(zonedDateTime1, zonedDateTime2))), (2, None)))))
        _ <- Ref.i(18).Nss.*(Ns.i.a1.uuidSeq_?).query.get.map(_ ==> List((18, List((1, Some(List(uuid1, uuid2))), (2, None)))))
        _ <- Ref.i(19).Nss.*(Ns.i.a1.uriSeq_?).query.get.map(_ ==> List((19, List((1, Some(List(uri1, uri2))), (2, None)))))
        _ <- Ref.i(20).Nss.*(Ns.i.a1.byteArray_?).query.get.map(_ ==> List((20, List((1, Some(Array(byte1, byte2))), (2, None)))))
        _ <- Ref.i(21).Nss.*(Ns.i.a1.shortSeq_?).query.get.map(_ ==> List((21, List((1, Some(List(short1, short2))), (2, None)))))
        _ <- Ref.i(22).Nss.*(Ns.i.a1.charSeq_?).query.get.map(_ ==> List((22, List((1, Some(List(char1, char2))), (2, None)))))

        _ <- Ref.i(1).Nss.*?(Ns.i.a1.stringSeq_?).query.get.map(_ ==> List((1, List((1, Some(List(string1, string2))), (2, None)))))
        _ <- Ref.i(2).Nss.*?(Ns.i.a1.intSeq_?).query.get.map(_ ==> List((2, List((1, Some(List(int1, int2))), (2, None)))))
        _ <- Ref.i(3).Nss.*?(Ns.i.a1.longSeq_?).query.get.map(_ ==> List((3, List((1, Some(List(long1, long2))), (2, None)))))
        _ <- Ref.i(4).Nss.*?(Ns.i.a1.floatSeq_?).query.get.map(_ ==> List((4, List((1, Some(List(float1, float2))), (2, None)))))
        _ <- Ref.i(5).Nss.*?(Ns.i.a1.doubleSeq_?).query.get.map(_ ==> List((5, List((1, Some(List(double1, double2))), (2, None)))))
        _ <- Ref.i(6).Nss.*?(Ns.i.a1.booleanSeq_?).query.get.map(_ ==> List((6, List((1, Some(List(boolean1, boolean2))), (2, None)))))
        _ <- Ref.i(7).Nss.*?(Ns.i.a1.bigIntSeq_?).query.get.map(_ ==> List((7, List((1, Some(List(bigInt1, bigInt2))), (2, None)))))
        _ <- Ref.i(8).Nss.*?(Ns.i.a1.bigDecimalSeq_?).query.get.map(_ ==> List((8, List((1, Some(List(bigDecimal1, bigDecimal2))), (2, None)))))
        _ <- Ref.i(9).Nss.*?(Ns.i.a1.dateSeq_?).query.get.map(_ ==> List((9, List((1, Some(List(date1, date2))), (2, None)))))
        _ <- Ref.i(10).Nss.*?(Ns.i.a1.durationSeq_?).query.get.map(_ ==> List((10, List((1, Some(List(duration1, duration2))), (2, None)))))
        _ <- Ref.i(11).Nss.*?(Ns.i.a1.instantSeq_?).query.get.map(_ ==> List((11, List((1, Some(List(instant1, instant2))), (2, None)))))
        _ <- Ref.i(12).Nss.*?(Ns.i.a1.localDateSeq_?).query.get.map(_ ==> List((12, List((1, Some(List(localDate1, localDate2))), (2, None)))))
        _ <- Ref.i(13).Nss.*?(Ns.i.a1.localTimeSeq_?).query.get.map(_ ==> List((13, List((1, Some(List(localTime1, localTime2))), (2, None)))))
        _ <- Ref.i(14).Nss.*?(Ns.i.a1.localDateTimeSeq_?).query.get.map(_ ==> List((14, List((1, Some(List(localDateTime1, localDateTime2))), (2, None)))))
        _ <- Ref.i(15).Nss.*?(Ns.i.a1.offsetTimeSeq_?).query.get.map(_ ==> List((15, List((1, Some(List(offsetTime1, offsetTime2))), (2, None)))))
        _ <- Ref.i(16).Nss.*?(Ns.i.a1.offsetDateTimeSeq_?).query.get.map(_ ==> List((16, List((1, Some(List(offsetDateTime1, offsetDateTime2))), (2, None)))))
        _ <- Ref.i(17).Nss.*?(Ns.i.a1.zonedDateTimeSeq_?).query.get.map(_ ==> List((17, List((1, Some(List(zonedDateTime1, zonedDateTime2))), (2, None)))))
        _ <- Ref.i(18).Nss.*?(Ns.i.a1.uuidSeq_?).query.get.map(_ ==> List((18, List((1, Some(List(uuid1, uuid2))), (2, None)))))
        _ <- Ref.i(19).Nss.*?(Ns.i.a1.uriSeq_?).query.get.map(_ ==> List((19, List((1, Some(List(uri1, uri2))), (2, None)))))
        _ <- Ref.i(20).Nss.*?(Ns.i.a1.byteArray_?).query.get.map(_ ==> List((20, List((1, Some(Array(byte1, byte2))), (2, None)))))
        _ <- Ref.i(21).Nss.*?(Ns.i.a1.shortSeq_?).query.get.map(_ ==> List((21, List((1, Some(List(short1, short2))), (2, None)))))
        _ <- Ref.i(22).Nss.*?(Ns.i.a1.charSeq_?).query.get.map(_ ==> List((22, List((1, Some(List(char1, char2))), (2, None)))))
      } yield ()
    }
  }
}
