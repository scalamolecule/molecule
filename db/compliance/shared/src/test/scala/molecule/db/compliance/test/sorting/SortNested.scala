package molecule.db.compliance.test.sorting

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import scala.concurrent.Future


case class SortNested(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Basic types, ascending" - types { implicit conn =>

    for {
      _ <- Ref.i.Entities.*(Entity.string).insert((1, List(string1, string2))).transact
      _ <- Ref.i.Entities.*(Entity.int).insert((2, List(int1, int2))).transact
      _ <- Ref.i.Entities.*(Entity.long).insert((3, List(long1, long2))).transact
      _ <- Ref.i.Entities.*(Entity.float).insert((4, List(float1, float2))).transact
      _ <- Ref.i.Entities.*(Entity.double).insert((5, List(double1, double2))).transact
      _ <- Ref.i.Entities.*(Entity.boolean).insert((6, List(boolean1, boolean2))).transact
      _ <- Ref.i.Entities.*(Entity.bigInt).insert((7, List(bigInt1, bigInt2))).transact
      _ <- Ref.i.Entities.*(Entity.bigDecimal).insert((8, List(bigDecimal1, bigDecimal2))).transact
      _ <- Ref.i.Entities.*(Entity.date).insert((9, List(date1, date2))).transact
      _ <- Ref.i.Entities.*(Entity.duration).insert((10, List(duration1, duration2))).transact
      _ <- Ref.i.Entities.*(Entity.instant).insert((11, List(instant1, instant2))).transact
      _ <- Ref.i.Entities.*(Entity.localDate).insert((12, List(localDate1, localDate2))).transact
      _ <- Ref.i.Entities.*(Entity.localTime).insert((13, List(localTime1, localTime2))).transact
      _ <- Ref.i.Entities.*(Entity.localDateTime).insert((14, List(localDateTime1, localDateTime2))).transact
      _ <- Ref.i.Entities.*(Entity.offsetTime).insert((15, List(offsetTime1, offsetTime2))).transact
      _ <- Ref.i.Entities.*(Entity.offsetDateTime).insert((16, List(offsetDateTime1, offsetDateTime2))).transact
      _ <- Ref.i.Entities.*(Entity.zonedDateTime).insert((17, List(zonedDateTime1, zonedDateTime2))).transact
      _ <- Ref.i.Entities.*(Entity.uuid).insert((18, List(uuid1, uuid2))).transact
      _ <- Ref.i.Entities.*(Entity.uri).insert((19, List(uri1, uri2))).transact
      _ <- Ref.i.Entities.*(Entity.byte).insert((20, List(byte1, byte2))).transact
      _ <- Ref.i.Entities.*(Entity.short).insert((21, List(short1, short2))).transact
      _ <- Ref.i.Entities.*(Entity.char).insert((22, List(char1, char2))).transact
      _ <- Ref.i.Entities.*(Entity.ref).insert((23, List(ref1, ref2))).transact

      // Ascending

      _ <- Ref.i_(1).Entities.*(Entity.string.a1).query.get.map(_ ==> List(List(string1, string2)))
      _ <- Ref.i_(2).Entities.*(Entity.int.a1).query.get.map(_ ==> List(List(int1, int2)))
      _ <- Ref.i_(3).Entities.*(Entity.long.a1).query.get.map(_ ==> List(List(long1, long2)))
      _ <- Ref.i_(4).Entities.*(Entity.float.a1).query.get.map(_ ==> List(List(float1, float2)))
      _ <- Ref.i_(5).Entities.*(Entity.double.a1).query.get.map(_ ==> List(List(double1, double2)))
      _ <- Ref.i_(6).Entities.*(Entity.boolean.a1).query.get.map(_ ==> List(List(boolean1, boolean2)))
      _ <- Ref.i_(7).Entities.*(Entity.bigInt.a1).query.get.map(_ ==> List(List(bigInt1, bigInt2)))
      _ <- Ref.i_(8).Entities.*(Entity.bigDecimal.a1).query.get.map(_ ==> List(List(bigDecimal1, bigDecimal2)))
      _ <- Ref.i_(9).Entities.*(Entity.date.a1).query.get.map(_ ==> List(List(date1, date2)))
      _ <- Ref.i_(10).Entities.*(Entity.duration.a1).query.get.map(_ ==> List(List(duration1, duration2)))
      _ <- Ref.i_(11).Entities.*(Entity.instant.a1).query.get.map(_ ==> List(List(instant1, instant2)))
      _ <- Ref.i_(12).Entities.*(Entity.localDate.a1).query.get.map(_ ==> List(List(localDate1, localDate2)))
      _ <- Ref.i_(13).Entities.*(Entity.localTime.a1).query.get.map(_ ==> List(List(localTime1, localTime2)))
      _ <- Ref.i_(14).Entities.*(Entity.localDateTime.a1).query.get.map(_ ==> List(List(localDateTime1, localDateTime2)))
      _ <- Ref.i_(15).Entities.*(Entity.offsetTime.a1).query.get.map(_ ==> List(List(offsetTime1, offsetTime2)))
      _ <- Ref.i_(16).Entities.*(Entity.offsetDateTime.a1).query.get.map(_ ==> List(List(offsetDateTime1, offsetDateTime2)))
      _ <- Ref.i_(17).Entities.*(Entity.zonedDateTime.a1).query.get.map(_ ==> List(List(zonedDateTime1, zonedDateTime2)))
      _ <- Ref.i_(18).Entities.*(Entity.uuid.a1).query.get.map(_ ==> List(List(uuid1, uuid2)))
      _ <- Ref.i_(19).Entities.*(Entity.uri.a1).query.get.map(_ ==> List(List(uri1, uri2)))
      _ <- Ref.i_(20).Entities.*(Entity.byte.a1).query.get.map(_ ==> List(List(byte1, byte2)))
      _ <- Ref.i_(21).Entities.*(Entity.short.a1).query.get.map(_ ==> List(List(short1, short2)))
      _ <- Ref.i_(22).Entities.*(Entity.char.a1).query.get.map(_ ==> List(List(char1, char2)))
      _ <- Ref.i_(23).Entities.*(Entity.ref.a1).query.get.map(_ ==> List(List(ref1, ref2)))

      _ <- Ref.i_(1).Entities.*?(Entity.string.a1).query.get.map(_ ==> List(List(string1, string2)))
      _ <- Ref.i_(2).Entities.*?(Entity.int.a1).query.get.map(_ ==> List(List(int1, int2)))
      _ <- Ref.i_(3).Entities.*?(Entity.long.a1).query.get.map(_ ==> List(List(long1, long2)))
      _ <- Ref.i_(4).Entities.*?(Entity.float.a1).query.get.map(_ ==> List(List(float1, float2)))
      _ <- Ref.i_(5).Entities.*?(Entity.double.a1).query.get.map(_ ==> List(List(double1, double2)))
      _ <- Ref.i_(6).Entities.*?(Entity.boolean.a1).query.get.map(_ ==> List(List(boolean1, boolean2)))
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
      _ <- Ref.i_(23).Entities.*?(Entity.ref.a1).query.get.map(_ ==> List(List(ref1, ref2)))
    } yield ()
  }


  "Basic types, descending" - types { implicit conn =>

    for {
      _ <- Ref.i.Entities.*(Entity.string).insert((1, List(string1, string2))).transact
      _ <- Ref.i.Entities.*(Entity.int).insert((2, List(int1, int2))).transact
      _ <- Ref.i.Entities.*(Entity.long).insert((3, List(long1, long2))).transact
      _ <- Ref.i.Entities.*(Entity.float).insert((4, List(float1, float2))).transact
      _ <- Ref.i.Entities.*(Entity.double).insert((5, List(double1, double2))).transact
      _ <- Ref.i.Entities.*(Entity.boolean).insert((6, List(boolean1, boolean2))).transact
      _ <- Ref.i.Entities.*(Entity.bigInt).insert((7, List(bigInt1, bigInt2))).transact
      _ <- Ref.i.Entities.*(Entity.bigDecimal).insert((8, List(bigDecimal1, bigDecimal2))).transact
      _ <- Ref.i.Entities.*(Entity.date).insert((9, List(date1, date2))).transact
      _ <- Ref.i.Entities.*(Entity.duration).insert((10, List(duration1, duration2))).transact
      _ <- Ref.i.Entities.*(Entity.instant).insert((11, List(instant1, instant2))).transact
      _ <- Ref.i.Entities.*(Entity.localDate).insert((12, List(localDate1, localDate2))).transact
      _ <- Ref.i.Entities.*(Entity.localTime).insert((13, List(localTime1, localTime2))).transact
      _ <- Ref.i.Entities.*(Entity.localDateTime).insert((14, List(localDateTime1, localDateTime2))).transact
      _ <- Ref.i.Entities.*(Entity.offsetTime).insert((15, List(offsetTime1, offsetTime2))).transact
      _ <- Ref.i.Entities.*(Entity.offsetDateTime).insert((16, List(offsetDateTime1, offsetDateTime2))).transact
      _ <- Ref.i.Entities.*(Entity.zonedDateTime).insert((17, List(zonedDateTime1, zonedDateTime2))).transact
      _ <- Ref.i.Entities.*(Entity.uuid).insert((18, List(uuid1, uuid2))).transact
      _ <- Ref.i.Entities.*(Entity.uri).insert((19, List(uri1, uri2))).transact
      _ <- Ref.i.Entities.*(Entity.byte).insert((20, List(byte1, byte2))).transact
      _ <- Ref.i.Entities.*(Entity.short).insert((21, List(short1, short2))).transact
      _ <- Ref.i.Entities.*(Entity.char).insert((22, List(char1, char2))).transact
      _ <- Ref.i.Entities.*(Entity.ref).insert((23, List(ref1, ref2))).transact

      _ <- Ref.i_(1).Entities.*(Entity.string.d1).query.get.map(_ ==> List(List(string2, string1)))
      _ <- Ref.i_(2).Entities.*(Entity.int.d1).query.get.map(_ ==> List(List(int2, int1)))
      _ <- Ref.i_(3).Entities.*(Entity.long.d1).query.get.map(_ ==> List(List(long2, long1)))
      _ <- Ref.i_(4).Entities.*(Entity.float.d1).query.get.map(_ ==> List(List(float2, float1)))
      _ <- Ref.i_(5).Entities.*(Entity.double.d1).query.get.map(_ ==> List(List(double2, double1)))
      _ <- Ref.i_(6).Entities.*(Entity.boolean.d1).query.get.map(_ ==> List(List(boolean2, boolean1)))
      _ <- Ref.i_(7).Entities.*(Entity.bigInt.d1).query.get.map(_ ==> List(List(bigInt2, bigInt1)))
      _ <- Ref.i_(8).Entities.*(Entity.bigDecimal.d1).query.get.map(_ ==> List(List(bigDecimal2, bigDecimal1)))
      _ <- Ref.i_(9).Entities.*(Entity.date.d1).query.get.map(_ ==> List(List(date2, date1)))
      _ <- Ref.i_(10).Entities.*(Entity.duration.d1).query.get.map(_ ==> List(List(duration2, duration1)))
      _ <- Ref.i_(11).Entities.*(Entity.instant.d1).query.get.map(_ ==> List(List(instant2, instant1)))
      _ <- Ref.i_(12).Entities.*(Entity.localDate.d1).query.get.map(_ ==> List(List(localDate2, localDate1)))
      _ <- Ref.i_(13).Entities.*(Entity.localTime.d1).query.get.map(_ ==> List(List(localTime2, localTime1)))
      _ <- Ref.i_(14).Entities.*(Entity.localDateTime.d1).query.get.map(_ ==> List(List(localDateTime2, localDateTime1)))
      _ <- Ref.i_(15).Entities.*(Entity.offsetTime.d1).query.get.map(_ ==> List(List(offsetTime2, offsetTime1)))
      _ <- Ref.i_(16).Entities.*(Entity.offsetDateTime.d1).query.get.map(_ ==> List(List(offsetDateTime2, offsetDateTime1)))
      _ <- Ref.i_(17).Entities.*(Entity.zonedDateTime.d1).query.get.map(_ ==> List(List(zonedDateTime2, zonedDateTime1)))
      _ <- Ref.i_(18).Entities.*(Entity.uuid.d1).query.get.map(_ ==> List(List(uuid2, uuid1)))
      _ <- Ref.i_(19).Entities.*(Entity.uri.d1).query.get.map(_ ==> List(List(uri2, uri1)))
      _ <- Ref.i_(20).Entities.*(Entity.byte.d1).query.get.map(_ ==> List(List(byte2, byte1)))
      _ <- Ref.i_(21).Entities.*(Entity.short.d1).query.get.map(_ ==> List(List(short2, short1)))
      _ <- Ref.i_(22).Entities.*(Entity.char.d1).query.get.map(_ ==> List(List(char2, char1)))
      _ <- Ref.i_(23).Entities.*(Entity.ref.d1).query.get.map(_ ==> List(List(ref2, ref1)))

      _ <- Ref.i_(1).Entities.*?(Entity.string.d1).query.get.map(_ ==> List(List(string2, string1)))
      _ <- Ref.i_(2).Entities.*?(Entity.int.d1).query.get.map(_ ==> List(List(int2, int1)))
      _ <- Ref.i_(3).Entities.*?(Entity.long.d1).query.get.map(_ ==> List(List(long2, long1)))
      _ <- Ref.i_(4).Entities.*?(Entity.float.d1).query.get.map(_ ==> List(List(float2, float1)))
      _ <- Ref.i_(5).Entities.*?(Entity.double.d1).query.get.map(_ ==> List(List(double2, double1)))
      _ <- Ref.i_(6).Entities.*?(Entity.boolean.d1).query.get.map(_ ==> List(List(boolean2, boolean1)))
      _ <- Ref.i_(7).Entities.*?(Entity.bigInt.d1).query.get.map(_ ==> List(List(bigInt2, bigInt1)))
      _ <- Ref.i_(8).Entities.*?(Entity.bigDecimal.d1).query.get.map(_ ==> List(List(bigDecimal2, bigDecimal1)))
      _ <- Ref.i_(9).Entities.*?(Entity.date.d1).query.get.map(_ ==> List(List(date2, date1)))
      _ <- Ref.i_(10).Entities.*?(Entity.duration.d1).query.get.map(_ ==> List(List(duration2, duration1)))
      _ <- Ref.i_(11).Entities.*?(Entity.instant.d1).query.get.map(_ ==> List(List(instant2, instant1)))
      _ <- Ref.i_(12).Entities.*?(Entity.localDate.d1).query.get.map(_ ==> List(List(localDate2, localDate1)))
      _ <- Ref.i_(13).Entities.*?(Entity.localTime.d1).query.get.map(_ ==> List(List(localTime2, localTime1)))
      _ <- Ref.i_(14).Entities.*?(Entity.localDateTime.d1).query.get.map(_ ==> List(List(localDateTime2, localDateTime1)))
      _ <- Ref.i_(15).Entities.*?(Entity.offsetTime.d1).query.get.map(_ ==> List(List(offsetTime2, offsetTime1)))
      _ <- Ref.i_(16).Entities.*?(Entity.offsetDateTime.d1).query.get.map(_ ==> List(List(offsetDateTime2, offsetDateTime1)))
      _ <- Ref.i_(17).Entities.*?(Entity.zonedDateTime.d1).query.get.map(_ ==> List(List(zonedDateTime2, zonedDateTime1)))
      _ <- Ref.i_(18).Entities.*?(Entity.uuid.d1).query.get.map(_ ==> List(List(uuid2, uuid1)))
      _ <- Ref.i_(19).Entities.*?(Entity.uri.d1).query.get.map(_ ==> List(List(uri2, uri1)))
      _ <- Ref.i_(20).Entities.*?(Entity.byte.d1).query.get.map(_ ==> List(List(byte2, byte1)))
      _ <- Ref.i_(21).Entities.*?(Entity.short.d1).query.get.map(_ ==> List(List(short2, short1)))
      _ <- Ref.i_(22).Entities.*?(Entity.char.d1).query.get.map(_ ==> List(List(char2, char1)))
      _ <- Ref.i_(23).Entities.*?(Entity.ref.d1).query.get.map(_ ==> List(List(ref2, ref1)))
    } yield ()
  }


  "OptNested type, optional" - types { implicit conn =>

    for {
      _ <- Ref.i.Entities.*(Entity.i.string_?).insert((1, List(
        (1, Some(string1)),
        (2, Some(string2)),
        (3, None)))).transact
      _ <- Ref.i_(1).Entities.*?(Entity.i.a2.string_?.a1).query.get.map(_ ==> List(List(
        (3, None),
        (1, Some(string1)),
        (2, Some(string2)))))
      _ <- Ref.i_(1).Entities.*?(Entity.i.d2.string_?.d1).query.get.map(_ ==> List(List(
        (2, Some(string2)),
        (1, Some(string1)),
        (3, None))))

      _ <- Ref.i.Entities.*(Entity.i.int_?).insert((2, List(
        (1, Some(int1)),
        (2, Some(int2)),
        (3, None)))).transact
      _ <- Ref.i_(2).Entities.*?(Entity.i.a2.int_?.a1).query.get.map(_ ==> List(List(
        (3, None),
        (1, Some(int1)),
        (2, Some(int2)))))
      _ <- Ref.i_(2).Entities.*?(Entity.i.d2.int_?.d1).query.get.map(_ ==> List(List(
        (2, Some(int2)),
        (1, Some(int1)),
        (3, None))))

      _ <- Ref.i.Entities.*(Entity.i.long_?).insert((3, List(
        (1, Some(long1)),
        (2, Some(long2)),
        (3, None)))).transact
      _ <- Ref.i_(3).Entities.*?(Entity.i.a2.long_?.a1).query.get.map(_ ==> List(List(
        (3, None),
        (1, Some(long1)),
        (2, Some(long2)))))
      _ <- Ref.i_(3).Entities.*?(Entity.i.d2.long_?.d1).query.get.map(_ ==> List(List(
        (2, Some(long2)),
        (1, Some(long1)),
        (3, None))))

      _ <- Ref.i.Entities.*(Entity.i.float_?).insert((4, List(
        (1, Some(float1)),
        (2, Some(float2)),
        (3, None)))).transact
      _ <- Ref.i_(4).Entities.*?(Entity.i.a2.float_?.a1).query.get.map(_ ==> List(List(
        (3, None),
        (1, Some(float1)),
        (2, Some(float2)))))
      _ <- Ref.i_(4).Entities.*?(Entity.i.d2.float_?.d1).query.get.map(_ ==> List(List(
        (2, Some(float2)),
        (1, Some(float1)),
        (3, None))))

      _ <- Ref.i.Entities.*(Entity.i.double_?).insert((5, List(
        (1, Some(double1)),
        (2, Some(double2)),
        (3, None)))).transact
      _ <- Ref.i_(5).Entities.*?(Entity.i.a2.double_?.a1).query.get.map(_ ==> List(List(
        (3, None),
        (1, Some(double1)),
        (2, Some(double2)))))
      _ <- Ref.i_(5).Entities.*?(Entity.i.d2.double_?.d1).query.get.map(_ ==> List(List(
        (2, Some(double2)),
        (1, Some(double1)),
        (3, None))))

      _ <- Ref.i.Entities.*(Entity.i.boolean_?).insert((6, List(
        (2, Some(true)),
        (3, None)))).transact
      _ <- Ref.i_(6).Entities.*?(Entity.i.a2.boolean_?.a1).query.get.map(_ ==> List(List(
        (3, None),
        (2, Some(true)))))
      _ <- Ref.i_(6).Entities.*?(Entity.i.d2.boolean_?.d1).query.get.map(_ ==> List(List(
        (2, Some(true)),
        (3, None))))

      _ <- Ref.i.Entities.*(Entity.i.bigInt_?).insert((7, List(
        (1, Some(bigInt1)),
        (2, Some(bigInt2)),
        (3, None)))).transact
      _ <- Ref.i_(7).Entities.*?(Entity.i.a2.bigInt_?.a1).query.get.map(_ ==> List(List(
        (3, None),
        (1, Some(bigInt1)),
        (2, Some(bigInt2)))))
      _ <- Ref.i_(7).Entities.*?(Entity.i.d2.bigInt_?.d1).query.get.map(_ ==> List(List(
        (2, Some(bigInt2)),
        (1, Some(bigInt1)),
        (3, None))))

      _ <- Ref.i.Entities.*(Entity.i.bigDecimal_?).insert((8, List(
        (1, Some(bigDecimal1)),
        (2, Some(bigDecimal2)),
        (3, None)))).transact
      _ <- Ref.i_(8).Entities.*?(Entity.i.a2.bigDecimal_?.a1).query.get.map(_ ==> List(List(
        (3, None),
        (1, Some(bigDecimal1)),
        (2, Some(bigDecimal2)))))
      _ <- Ref.i_(8).Entities.*?(Entity.i.d2.bigDecimal_?.d1).query.get.map(_ ==> List(List(
        (2, Some(bigDecimal2)),
        (1, Some(bigDecimal1)),
        (3, None))))

      _ <- Ref.i.Entities.*(Entity.i.date_?).insert((9, List(
        (1, Some(date1)),
        (2, Some(date2)),
        (3, None)))).transact
      _ <- Ref.i_(9).Entities.*?(Entity.i.a2.date_?.a1).query.get.map(_ ==> List(List(
        (3, None),
        (1, Some(date1)),
        (2, Some(date2)))))
      _ <- Ref.i_(9).Entities.*?(Entity.i.d2.date_?.d1).query.get.map(_ ==> List(List(
        (2, Some(date2)),
        (1, Some(date1)),
        (3, None))))

      _ <- Ref.i.Entities.*(Entity.i.duration_?).insert((10, List(
        (1, Some(duration1)),
        (2, Some(duration2)),
        (3, None)))).transact
      _ <- Ref.i_(10).Entities.*?(Entity.i.a2.duration_?.a1).query.get.map(_ ==> List(List(
        (3, None),
        (1, Some(duration1)),
        (2, Some(duration2)))))
      _ <- Ref.i_(10).Entities.*?(Entity.i.d2.duration_?.d1).query.get.map(_ ==> List(List(
        (2, Some(duration2)),
        (1, Some(duration1)),
        (3, None))))

      _ <- Ref.i.Entities.*(Entity.i.instant_?).insert((11, List(
        (1, Some(instant1)),
        (2, Some(instant2)),
        (3, None)))).transact
      _ <- Ref.i_(11).Entities.*?(Entity.i.a2.instant_?.a1).query.get.map(_ ==> List(List(
        (3, None),
        (1, Some(instant1)),
        (2, Some(instant2)))))
      _ <- Ref.i_(11).Entities.*?(Entity.i.d2.instant_?.d1).query.get.map(_ ==> List(List(
        (2, Some(instant2)),
        (1, Some(instant1)),
        (3, None))))

      _ <- Ref.i.Entities.*(Entity.i.localDate_?).insert((12, List(
        (1, Some(localDate1)),
        (2, Some(localDate2)),
        (3, None)))).transact
      _ <- Ref.i_(12).Entities.*?(Entity.i.a2.localDate_?.a1).query.get.map(_ ==> List(List(
        (3, None),
        (1, Some(localDate1)),
        (2, Some(localDate2)))))
      _ <- Ref.i_(12).Entities.*?(Entity.i.d2.localDate_?.d1).query.get.map(_ ==> List(List(
        (2, Some(localDate2)),
        (1, Some(localDate1)),
        (3, None))))

      _ <- Ref.i.Entities.*(Entity.i.localTime_?).insert((13, List(
        (1, Some(localTime1)),
        (2, Some(localTime2)),
        (3, None)))).transact
      _ <- Ref.i_(13).Entities.*?(Entity.i.a2.localTime_?.a1).query.get.map(_ ==> List(List(
        (3, None),
        (1, Some(localTime1)),
        (2, Some(localTime2)))))
      _ <- Ref.i_(13).Entities.*?(Entity.i.d2.localTime_?.d1).query.get.map(_ ==> List(List(
        (2, Some(localTime2)),
        (1, Some(localTime1)),
        (3, None))))

      _ <- Ref.i.Entities.*(Entity.i.localDateTime_?).insert((14, List(
        (1, Some(localDateTime1)),
        (2, Some(localDateTime2)),
        (3, None)))).transact
      _ <- Ref.i_(14).Entities.*?(Entity.i.a2.localDateTime_?.a1).query.get.map(_ ==> List(List(
        (3, None),
        (1, Some(localDateTime1)),
        (2, Some(localDateTime2)))))
      _ <- Ref.i_(14).Entities.*?(Entity.i.d2.localDateTime_?.d1).query.get.map(_ ==> List(List(
        (2, Some(localDateTime2)),
        (1, Some(localDateTime1)),
        (3, None))))

      _ <- Ref.i.Entities.*(Entity.i.offsetTime_?).insert((15, List(
        (1, Some(offsetTime1)),
        (2, Some(offsetTime2)),
        (3, None)))).transact
      _ <- Ref.i_(15).Entities.*?(Entity.i.a2.offsetTime_?.a1).query.get.map(_ ==> List(List(
        (3, None),
        (1, Some(offsetTime1)),
        (2, Some(offsetTime2)))))
      _ <- Ref.i_(15).Entities.*?(Entity.i.d2.offsetTime_?.d1).query.get.map(_ ==> List(List(
        (2, Some(offsetTime2)),
        (1, Some(offsetTime1)),
        (3, None))))

      _ <- Ref.i.Entities.*(Entity.i.offsetDateTime_?).insert((16, List(
        (1, Some(offsetDateTime1)),
        (2, Some(offsetDateTime2)),
        (3, None)))).transact
      _ <- Ref.i_(16).Entities.*?(Entity.i.a2.offsetDateTime_?.a1).query.get.map(_ ==> List(List(
        (3, None),
        (1, Some(offsetDateTime1)),
        (2, Some(offsetDateTime2)))))
      _ <- Ref.i_(16).Entities.*?(Entity.i.d2.offsetDateTime_?.d1).query.get.map(_ ==> List(List(
        (2, Some(offsetDateTime2)),
        (1, Some(offsetDateTime1)),
        (3, None))))

      _ <- Ref.i.Entities.*(Entity.i.zonedDateTime_?).insert((17, List(
        (1, Some(zonedDateTime1)),
        (2, Some(zonedDateTime2)),
        (3, None)))).transact
      _ <- Ref.i_(17).Entities.*?(Entity.i.a2.zonedDateTime_?.a1).query.get.map(_ ==> List(List(
        (3, None),
        (1, Some(zonedDateTime1)),
        (2, Some(zonedDateTime2)))))
      _ <- Ref.i_(17).Entities.*?(Entity.i.d2.zonedDateTime_?.d1).query.get.map(_ ==> List(List(
        (2, Some(zonedDateTime2)),
        (1, Some(zonedDateTime1)),
        (3, None))))

      _ <- Ref.i.Entities.*(Entity.i.uuid_?).insert((18, List(
        (1, Some(uuid1)),
        (2, Some(uuid2)),
        (3, None)))).transact
      _ <- Ref.i_(18).Entities.*?(Entity.i.a2.uuid_?.a1).query.get.map(_ ==> List(List(
        (3, None),
        (1, Some(uuid1)),
        (2, Some(uuid2)))))
      _ <- Ref.i_(18).Entities.*?(Entity.i.d2.uuid_?.d1).query.get.map(_ ==> List(List(
        (2, Some(uuid2)),
        (1, Some(uuid1)),
        (3, None))))

      _ <- Ref.i.Entities.*(Entity.i.uri_?).insert((19, List(
        (1, Some(uri1)),
        (2, Some(uri2)),
        (3, None)))).transact
      _ <- Ref.i_(19).Entities.*?(Entity.i.a2.uri_?.a1).query.get.map(_ ==> List(List(
        (3, None),
        (1, Some(uri1)),
        (2, Some(uri2)))))
      _ <- Ref.i_(19).Entities.*?(Entity.i.d2.uri_?.d1).query.get.map(_ ==> List(List(
        (2, Some(uri2)),
        (1, Some(uri1)),
        (3, None))))

      _ <- Ref.i.Entities.*(Entity.i.byte_?).insert((20, List(
        (1, Some(byte1)),
        (2, Some(byte2)),
        (3, None)))).transact
      _ <- Ref.i_(20).Entities.*?(Entity.i.a2.byte_?.a1).query.get.map(_ ==> List(List(
        (3, None),
        (1, Some(byte1)),
        (2, Some(byte2)))))
      _ <- Ref.i_(20).Entities.*?(Entity.i.d2.byte_?.d1).query.get.map(_ ==> List(List(
        (2, Some(byte2)),
        (1, Some(byte1)),
        (3, None))))

      _ <- Ref.i.Entities.*(Entity.i.short_?).insert((21, List(
        (1, Some(short1)),
        (2, Some(short2)),
        (3, None)))).transact
      _ <- Ref.i_(21).Entities.*?(Entity.i.a2.short_?.a1).query.get.map(_ ==> List(List(
        (3, None),
        (1, Some(short1)),
        (2, Some(short2)))))
      _ <- Ref.i_(21).Entities.*?(Entity.i.d2.short_?.d1).query.get.map(_ ==> List(List(
        (2, Some(short2)),
        (1, Some(short1)),
        (3, None))))

      _ <- Ref.i.Entities.*(Entity.i.char_?).insert((22, List(
        (1, Some(char1)),
        (2, Some(char2)),
        (3, None)))).transact
      _ <- Ref.i_(22).Entities.*?(Entity.i.a2.char_?.a1).query.get.map(_ ==> List(List(
        (3, None),
        (1, Some(char1)),
        (2, Some(char2)))))
      _ <- Ref.i_(22).Entities.*?(Entity.i.d2.char_?.d1).query.get.map(_ ==> List(List(
        (2, Some(char2)),
        (1, Some(char1)),
        (3, None))))

      _ <- Ref.i.Entities.*(Entity.i.ref_?).insert((23, List(
        (1, Some(ref1)),
        (2, Some(ref2)),
        (3, None)))).transact
      _ <- Ref.i_(23).Entities.*?(Entity.i.a2.ref_?.a1).query.get.map(_ ==> List(List(
        (3, None),
        (1, Some(ref1)),
        (2, Some(ref2)))))
      _ <- Ref.i_(23).Entities.*?(Entity.i.d2.ref_?.d1).query.get.map(_ ==> List(List(
        (2, Some(ref2)),
        (1, Some(ref1)),
        (3, None))))
    } yield ()
  }


  "Sort top level" - refs { implicit conn =>
    for {
      _ <- A.s.Bb.*(B.i).insert(
        ("A", List(1, 2)),
        ("B", List(1, 2)),
      ).transact

      _ <- A.s.a1.Bb.*(B.i.a1).query.get.map(_ ==> List(
        ("A", List(1, 2)),
        ("B", List(1, 2)),
      ))
      _ <- A.s.a1.Bb.*(B.i.d1).query.get.map(_ ==> List(
        ("A", List(2, 1)),
        ("B", List(2, 1)),
      ))
      _ <- A.s.d1.Bb.*(B.i.a1).query.get.map(_ ==> List(
        ("B", List(1, 2)),
        ("A", List(1, 2)),
      ))
      _ <- A.s.d1.Bb.*(B.i.d1).query.get.map(_ ==> List(
        ("B", List(2, 1)),
        ("A", List(2, 1)),
      ))
    } yield ()
  }


  "Options" - refs { implicit conn =>
    for {
      _ <- A.i.s_?.Bb.*(B.i.s_?).insert(
        (1, Some("A"), List(
          (1, Some("a")),
          (1, Some("b")),
          (2, Some("a")),
          (2, Some("b")),
          (2, None),
        )),
        (2, Some("B"), List(
          (1, Some("a")),
          (1, Some("b")),
          (2, Some("a")),
          (2, Some("b")),
        )),
        (3, Some("C"), List()),
        (4, None, List()),
      ).transact

      // a1 *? a1/a2
      _ <- A.i.s_?.a1.Bb.*?(B.i.a1.s_?.a2).query.get.map(_ ==> List(
        (4, None, List()),
        (1, Some("A"), List(
          (1, Some("a")),
          (1, Some("b")),
          (2, None),
          (2, Some("a")),
          (2, Some("b")),
        )),
        (2, Some("B"), List(
          (1, Some("a")),
          (1, Some("b")),
          (2, Some("a")),
          (2, Some("b")),
        )),
        (3, Some("C"), List()),
      ))

      // a1 *? a1/d2
      _ <- A.i.s_?.a1.Bb.*?(B.i.a1.s_?.d2).query.get.map(_ ==> List(
        (4, None, List()),
        (1, Some("A"), List(
          (1, Some("b")),
          (1, Some("a")),
          (2, Some("b")),
          (2, Some("a")),
          (2, None),
        )),
        (2, Some("B"), List(
          (1, Some("b")),
          (1, Some("a")),
          (2, Some("b")),
          (2, Some("a")),
        )),
        (3, Some("C"), List()),
      ))

      // a1 *? d1/a2
      _ <- A.i.s_?.a1.Bb.*?(B.i.d1.s_?.a2).query.get.map(_ ==> List(
        (4, None, List()),
        (1, Some("A"), List(
          (2, None),
          (2, Some("a")),
          (2, Some("b")),
          (1, Some("a")),
          (1, Some("b")),
        )),
        (2, Some("B"), List(
          (2, Some("a")),
          (2, Some("b")),
          (1, Some("a")),
          (1, Some("b")),
        )),
        (3, Some("C"), List()),
      ))

      // a1 *? d1/d2
      _ <- A.i.s_?.a1.Bb.*?(B.i.d1.s_?.d2).query.get.map(_ ==> List(
        (4, None, List()),
        (1, Some("A"), List(
          (2, Some("b")),
          (2, Some("a")),
          (2, None),
          (1, Some("b")),
          (1, Some("a")),
        )),
        (2, Some("B"), List(
          (2, Some("b")),
          (2, Some("a")),
          (1, Some("b")),
          (1, Some("a")),
        )),
        (3, Some("C"), List()),
      ))

      // d1 *? d1/d2
      _ <- A.i.s_?.d1.Bb.*?(B.i.d1.s_?.d2).query.get.map(_ ==> List(
        (3, Some("C"), List()),
        (2, Some("B"), List(
          (2, Some("b")),
          (2, Some("a")),
          (1, Some("b")),
          (1, Some("a")),
        )),
        (1, Some("A"), List(
          (2, Some("b")),
          (2, Some("a")),
          (2, None),
          (1, Some("b")),
          (1, Some("a")),
        )),
        (4, None, List()),
      ))

      // d1 *? d2/d1
      _ <- A.i.s_?.d1.Bb.*?(B.i.d2.s_?.d1).query.get.map(_ ==> List(
        (3, Some("C"), List()),
        (2, Some("B"), List(
          (2, Some("b")),
          (1, Some("b")),
          (2, Some("a")),
          (1, Some("a")),
        )),
        (1, Some("A"), List(
          (2, Some("b")),
          (1, Some("b")),
          (2, Some("a")),
          (1, Some("a")),
          (2, None),
        )),
        (4, None, List()),
      ))

      // d1(expr) *? d2/d1
      _ <- A.i.s.>("A").d1.Bb.*?(B.i.d2.s_?.d1).query.get.map(_ ==> List(
        (3, "C", List()),
        (2, "B", List(
          (2, Some("b")),
          (1, Some("b")),
          (2, Some("a")),
          (1, Some("a")),
        ))
      ))

      // expr+d1 * a2/d1(expr)
      _ <- A.i.<=(2).s.d1.Bb.*(B.i.a2.s.>=("a").d1).query.get.map(_ ==> List(
        (2, "B", List(
          (1, "b"),
          (2, "b"),
          (1, "a"),
          (2, "a"),
        )),
        (1, "A", List(
          (1, "b"),
          (2, "b"),
          (1, "a"),
          (2, "a"),
        )),
      ))
    } yield ()
  }


  "2 sub levels" - refs { implicit conn =>
    for {
      _ <- A.i.s_?.Bb.*(B.i.s_?.Cc.*(C.s)).insert(
        (1, Some("A"), List(
          (1, Some("a"), List("x", "y")),
          (1, Some("b"), List("y", "x")),
          (2, Some("a"), List("x", "y")),
          (2, Some("b"), Nil),
          (2, None, List("x", "y")),
        )),
        (2, Some("B"), List(
          (1, Some("a"), List("x", "y")),
          (1, Some("b"), List("y", "x")),
          (2, Some("a"), List("x", "y")),
          (2, Some("b"), List("y", "x")),
        )),
        (3, Some("C"), Nil),
        (4, None, Nil),
      ).transact

      _ <- A.i.s_?.a1.Bb.*?(B.i.d2.s_?.a1.Cc.*?(C.s.d1)).query.get.map(_ ==> List(
        (4, None, Nil),
        (1, Some("A"), List(
          (2, None, List("y", "x")),
          (2, Some("a"), List("y", "x")),
          (1, Some("a"), List("y", "x")),
          (2, Some("b"), Nil),
          (1, Some("b"), List("y", "x")),
        )),
        (2, Some("B"), List(
          (2, Some("a"), List("y", "x")),
          (1, Some("a"), List("y", "x")),
          (2, Some("b"), List("y", "x")),
          (1, Some("b"), List("y", "x")),
        )),
        (3, Some("C"), Nil),
      ))

      _ <- A.i.a1.s_?.Bb.*(B.i.d2.s_?.a1.Cc.*(C.s.d1)).query.get.map(_ ==> List(
        (1, Some("A"), List(
          (2, None, List("y", "x")),
          (2, Some("a"), List("y", "x")),
          (1, Some("a"), List("y", "x")),
          (1, Some("b"), List("y", "x")),
        )),
        (2, Some("B"), List(
          (2, Some("a"), List("y", "x")),
          (1, Some("a"), List("y", "x")),
          (2, Some("b"), List("y", "x")),
          (1, Some("b"), List("y", "x")),
        )),
      ))

      _ <- A.i.s_?.a1.Bb.*(B.i.d2.s_?.a1.Cc.*(C.s.d1)).query.get.map(_ ==> List(
        (1, Some("A"), List(
          (2, None, List("y", "x")),
          (2, Some("a"), List("y", "x")),
          (1, Some("a"), List("y", "x")),
          (1, Some("b"), List("y", "x")),
        )),
        (2, Some("B"), List(
          (2, Some("a"), List("y", "x")),
          (1, Some("a"), List("y", "x")),
          (2, Some("b"), List("y", "x")),
          (1, Some("b"), List("y", "x")),
        )),
      ))
    } yield ()
  }
}
