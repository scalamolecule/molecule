package molecule.coreTests.spi.sorting

import molecule.base.error.ModelError
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class SortAggr(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  // Use whole decimal numbers since only sorting is checked here
  override lazy val (float1, float2, float3, float4)                     = (1.0f, 2.0f, 3.0f, 4.0f)
  override lazy val (double1, double2, double3, double4)                 = (1.0, 2.0, 3.0, 4.0)
  override lazy val (bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4) =
    (BigDecimal(1.0), BigDecimal(2.0), BigDecimal(3.0), BigDecimal(4.0))


  import api._
  import suite._

  "count" - types { implicit conn =>
    for {
      List(ref1, ref2) <- Ref.i.insert(1, 2).transact.map(_.ids)

      _ <- Entity.i.string.insert(
        (1, string1),
        (1, string2),
        (1, string2),
        (2, string2)).transact
      _ <- Entity.i.int.insert((1, int1), (1, int2), (1, int2), (2, int2)).transact
      _ <- Entity.i.long.insert((1, long1), (1, long2), (1, long2), (2, long2)).transact
      _ <- Entity.i.float.insert((1, float1), (1, float2), (1, float2), (2, float2)).transact
      _ <- Entity.i.double.insert((1, double1), (1, double2), (1, double2), (2, double2)).transact
      _ <- Entity.i.boolean.insert((1, boolean1), (1, boolean2), (1, boolean2), (2, boolean2)).transact
      _ <- Entity.i.bigInt.insert((1, bigInt1), (1, bigInt2), (1, bigInt2), (2, bigInt2)).transact
      _ <- Entity.i.bigDecimal.insert((1, bigDecimal1), (1, bigDecimal2), (1, bigDecimal2), (2, bigDecimal2)).transact
      _ <- Entity.i.date.insert((1, date1), (1, date2), (1, date2), (2, date2)).transact
      _ <- Entity.i.duration.insert((1, duration1), (1, duration2), (1, duration2), (2, duration2)).transact
      _ <- Entity.i.instant.insert((1, instant1), (1, instant2), (1, instant2), (2, instant2)).transact
      _ <- Entity.i.localDate.insert((1, localDate1), (1, localDate2), (1, localDate2), (2, localDate2)).transact
      _ <- Entity.i.localTime.insert((1, localTime1), (1, localTime2), (1, localTime2), (2, localTime2)).transact
      _ <- Entity.i.localDateTime.insert((1, localDateTime1), (1, localDateTime2), (1, localDateTime2), (2, localDateTime2)).transact
      _ <- Entity.i.offsetTime.insert((1, offsetTime1), (1, offsetTime2), (1, offsetTime2), (2, offsetTime2)).transact
      _ <- Entity.i.offsetDateTime.insert((1, offsetDateTime1), (1, offsetDateTime2), (1, offsetDateTime2), (2, offsetDateTime2)).transact
      _ <- Entity.i.zonedDateTime.insert((1, zonedDateTime1), (1, zonedDateTime2), (1, zonedDateTime2), (2, zonedDateTime2)).transact
      _ <- Entity.i.uuid.insert((1, uuid1), (1, uuid2), (1, uuid2), (2, uuid2)).transact
      _ <- Entity.i.uri.insert((1, uri1), (1, uri2), (1, uri2), (2, uri2)).transact
      _ <- Entity.i.byte.insert((1, byte1), (1, byte2), (1, byte2), (2, byte2)).transact
      _ <- Entity.i.short.insert((1, short1), (1, short2), (1, short2), (2, short2)).transact
      _ <- Entity.i.char.insert((1, char1), (1, char2), (1, char2), (2, char2)).transact
      _ <- Entity.i.ref.insert((1, ref1), (1, ref2), (1, ref2), (2, ref2)).transact

      _ <- Entity.i.string(count).a1.query.get.map(_ ==> List((2, 1), (1, 3)))
      _ <- Entity.i.int(count).a1.query.get.map(_ ==> List((2, 1), (1, 3)))
      _ <- Entity.i.long(count).a1.query.get.map(_ ==> List((2, 1), (1, 3)))
      _ <- Entity.i.float(count).a1.query.get.map(_ ==> List((2, 1), (1, 3)))
      _ <- Entity.i.double(count).a1.query.get.map(_ ==> List((2, 1), (1, 3)))
      _ <- Entity.i.boolean(count).a1.query.get.map(_ ==> List((2, 1), (1, 3)))
      _ <- Entity.i.bigInt(count).a1.query.get.map(_ ==> List((2, 1), (1, 3)))
      _ <- Entity.i.bigDecimal(count).a1.query.get.map(_ ==> List((2, 1), (1, 3)))
      _ <- Entity.i.date(count).a1.query.get.map(_ ==> List((2, 1), (1, 3)))
      _ <- Entity.i.duration(count).a1.query.get.map(_ ==> List((2, 1), (1, 3)))
      _ <- Entity.i.instant(count).a1.query.get.map(_ ==> List((2, 1), (1, 3)))
      _ <- Entity.i.localDate(count).a1.query.get.map(_ ==> List((2, 1), (1, 3)))
      _ <- Entity.i.localTime(count).a1.query.get.map(_ ==> List((2, 1), (1, 3)))
      _ <- Entity.i.localDateTime(count).a1.query.get.map(_ ==> List((2, 1), (1, 3)))
      _ <- Entity.i.offsetTime(count).a1.query.get.map(_ ==> List((2, 1), (1, 3)))
      _ <- Entity.i.offsetDateTime(count).a1.query.get.map(_ ==> List((2, 1), (1, 3)))
      _ <- Entity.i.zonedDateTime(count).a1.query.get.map(_ ==> List((2, 1), (1, 3)))
      _ <- Entity.i.uuid(count).a1.query.get.map(_ ==> List((2, 1), (1, 3)))
      _ <- Entity.i.uri(count).a1.query.get.map(_ ==> List((2, 1), (1, 3)))
      _ <- Entity.i.byte(count).a1.query.get.map(_ ==> List((2, 1), (1, 3)))
      _ <- Entity.i.short(count).a1.query.get.map(_ ==> List((2, 1), (1, 3)))
      _ <- Entity.i.char(count).a1.query.get.map(_ ==> List((2, 1), (1, 3)))
      _ <- Entity.i.ref(count).a1.query.get.map(_ ==> List((2, 1), (1, 3)))

      _ <- Entity.i.string(count).d1.query.get.map(_ ==> List((1, 3), (2, 1)))
      _ <- Entity.i.int(count).d1.query.get.map(_ ==> List((1, 3), (2, 1)))
      _ <- Entity.i.long(count).d1.query.get.map(_ ==> List((1, 3), (2, 1)))
      _ <- Entity.i.float(count).d1.query.get.map(_ ==> List((1, 3), (2, 1)))
      _ <- Entity.i.double(count).d1.query.get.map(_ ==> List((1, 3), (2, 1)))
      _ <- Entity.i.boolean(count).d1.query.get.map(_ ==> List((1, 3), (2, 1)))
      _ <- Entity.i.bigInt(count).d1.query.get.map(_ ==> List((1, 3), (2, 1)))
      _ <- Entity.i.bigDecimal(count).d1.query.get.map(_ ==> List((1, 3), (2, 1)))
      _ <- Entity.i.date(count).d1.query.get.map(_ ==> List((1, 3), (2, 1)))
      _ <- Entity.i.duration(count).d1.query.get.map(_ ==> List((1, 3), (2, 1)))
      _ <- Entity.i.instant(count).d1.query.get.map(_ ==> List((1, 3), (2, 1)))
      _ <- Entity.i.localDate(count).d1.query.get.map(_ ==> List((1, 3), (2, 1)))
      _ <- Entity.i.localTime(count).d1.query.get.map(_ ==> List((1, 3), (2, 1)))
      _ <- Entity.i.localDateTime(count).d1.query.get.map(_ ==> List((1, 3), (2, 1)))
      _ <- Entity.i.offsetTime(count).d1.query.get.map(_ ==> List((1, 3), (2, 1)))
      _ <- Entity.i.offsetDateTime(count).d1.query.get.map(_ ==> List((1, 3), (2, 1)))
      _ <- Entity.i.zonedDateTime(count).d1.query.get.map(_ ==> List((1, 3), (2, 1)))
      _ <- Entity.i.uuid(count).d1.query.get.map(_ ==> List((1, 3), (2, 1)))
      _ <- Entity.i.uri(count).d1.query.get.map(_ ==> List((1, 3), (2, 1)))
      _ <- Entity.i.byte(count).d1.query.get.map(_ ==> List((1, 3), (2, 1)))
      _ <- Entity.i.short(count).d1.query.get.map(_ ==> List((1, 3), (2, 1)))
      _ <- Entity.i.char(count).d1.query.get.map(_ ==> List((1, 3), (2, 1)))
      _ <- Entity.i.ref(count).d1.query.get.map(_ ==> List((1, 3), (2, 1)))
    } yield ()
  }


  "countDistinct" - types { implicit conn =>
    for {
      List(ref1, ref2) <- Ref.i.insert(1, 2).transact.map(_.ids)

      _ <- Entity.i.string.insert(
        (1, string1),
        (1, string2),
        (1, string2),
        (2, string2)).transact
      _ <- Entity.i.int.insert((1, int1), (1, int2), (1, int2), (2, int2)).transact
      _ <- Entity.i.long.insert((1, long1), (1, long2), (1, long2), (2, long2)).transact
      _ <- Entity.i.float.insert((1, float1), (1, float2), (1, float2), (2, float2)).transact
      _ <- Entity.i.double.insert((1, double1), (1, double2), (1, double2), (2, double2)).transact
      _ <- Entity.i.boolean.insert((1, boolean1), (1, boolean2), (1, boolean2), (2, boolean2)).transact
      _ <- Entity.i.bigInt.insert((1, bigInt1), (1, bigInt2), (1, bigInt2), (2, bigInt2)).transact
      _ <- Entity.i.bigDecimal.insert((1, bigDecimal1), (1, bigDecimal2), (1, bigDecimal2), (2, bigDecimal2)).transact
      _ <- Entity.i.date.insert((1, date1), (1, date2), (1, date2), (2, date2)).transact
      _ <- Entity.i.duration.insert((1, duration1), (1, duration2), (1, duration2), (2, duration2)).transact
      _ <- Entity.i.instant.insert((1, instant1), (1, instant2), (1, instant2), (2, instant2)).transact
      _ <- Entity.i.localDate.insert((1, localDate1), (1, localDate2), (1, localDate2), (2, localDate2)).transact
      _ <- Entity.i.localTime.insert((1, localTime1), (1, localTime2), (1, localTime2), (2, localTime2)).transact
      _ <- Entity.i.localDateTime.insert((1, localDateTime1), (1, localDateTime2), (1, localDateTime2), (2, localDateTime2)).transact
      _ <- Entity.i.offsetTime.insert((1, offsetTime1), (1, offsetTime2), (1, offsetTime2), (2, offsetTime2)).transact
      _ <- Entity.i.offsetDateTime.insert((1, offsetDateTime1), (1, offsetDateTime2), (1, offsetDateTime2), (2, offsetDateTime2)).transact
      _ <- Entity.i.zonedDateTime.insert((1, zonedDateTime1), (1, zonedDateTime2), (1, zonedDateTime2), (2, zonedDateTime2)).transact
      _ <- Entity.i.uuid.insert((1, uuid1), (1, uuid2), (1, uuid2), (2, uuid2)).transact
      _ <- Entity.i.uri.insert((1, uri1), (1, uri2), (1, uri2), (2, uri2)).transact
      _ <- Entity.i.byte.insert((1, byte1), (1, byte2), (1, byte2), (2, byte2)).transact
      _ <- Entity.i.short.insert((1, short1), (1, short2), (1, short2), (2, short2)).transact
      _ <- Entity.i.char.insert((1, char1), (1, char2), (1, char2), (2, char2)).transact
      _ <- Entity.i.ref.insert((1, ref1), (1, ref2), (1, ref2), (2, ref2)).transact

      _ <- Entity.i.string(countDistinct).a1.query.get.map(_ ==> List((2, 1), (1, 2)))
      _ <- Entity.i.int(countDistinct).a1.query.get.map(_ ==> List((2, 1), (1, 2)))
      _ <- Entity.i.long(countDistinct).a1.query.get.map(_ ==> List((2, 1), (1, 2)))
      _ <- Entity.i.float(countDistinct).a1.query.get.map(_ ==> List((2, 1), (1, 2)))
      _ <- Entity.i.double(countDistinct).a1.query.get.map(_ ==> List((2, 1), (1, 2)))
      _ <- Entity.i.boolean(countDistinct).a1.query.get.map(_ ==> List((2, 1), (1, 2)))
      _ <- Entity.i.bigInt(countDistinct).a1.query.get.map(_ ==> List((2, 1), (1, 2)))
      _ <- Entity.i.bigDecimal(countDistinct).a1.query.get.map(_ ==> List((2, 1), (1, 2)))
      _ <- Entity.i.date(countDistinct).a1.query.get.map(_ ==> List((2, 1), (1, 2)))
      _ <- Entity.i.duration(countDistinct).a1.query.get.map(_ ==> List((2, 1), (1, 2)))
      _ <- Entity.i.instant(countDistinct).a1.query.get.map(_ ==> List((2, 1), (1, 2)))
      _ <- Entity.i.localDate(countDistinct).a1.query.get.map(_ ==> List((2, 1), (1, 2)))
      _ <- Entity.i.localTime(countDistinct).a1.query.get.map(_ ==> List((2, 1), (1, 2)))
      _ <- Entity.i.localDateTime(countDistinct).a1.query.get.map(_ ==> List((2, 1), (1, 2)))
      _ <- Entity.i.offsetTime(countDistinct).a1.query.get.map(_ ==> List((2, 1), (1, 2)))
      _ <- Entity.i.offsetDateTime(countDistinct).a1.query.get.map(_ ==> List((2, 1), (1, 2)))
      _ <- Entity.i.zonedDateTime(countDistinct).a1.query.get.map(_ ==> List((2, 1), (1, 2)))
      _ <- Entity.i.uuid(countDistinct).a1.query.get.map(_ ==> List((2, 1), (1, 2)))
      _ <- Entity.i.uri(countDistinct).a1.query.get.map(_ ==> List((2, 1), (1, 2)))
      _ <- Entity.i.byte(countDistinct).a1.query.get.map(_ ==> List((2, 1), (1, 2)))
      _ <- Entity.i.short(countDistinct).a1.query.get.map(_ ==> List((2, 1), (1, 2)))
      _ <- Entity.i.char(countDistinct).a1.query.get.map(_ ==> List((2, 1), (1, 2)))
      _ <- Entity.i.ref(countDistinct).a1.query.get.map(_ ==> List((2, 1), (1, 2)))

      _ <- Entity.i.string(countDistinct).d1.query.get.map(_ ==> List((1, 2), (2, 1)))
      _ <- Entity.i.int(countDistinct).d1.query.get.map(_ ==> List((1, 2), (2, 1)))
      _ <- Entity.i.long(countDistinct).d1.query.get.map(_ ==> List((1, 2), (2, 1)))
      _ <- Entity.i.float(countDistinct).d1.query.get.map(_ ==> List((1, 2), (2, 1)))
      _ <- Entity.i.double(countDistinct).d1.query.get.map(_ ==> List((1, 2), (2, 1)))
      _ <- Entity.i.boolean(countDistinct).d1.query.get.map(_ ==> List((1, 2), (2, 1)))
      _ <- Entity.i.bigInt(countDistinct).d1.query.get.map(_ ==> List((1, 2), (2, 1)))
      _ <- Entity.i.bigDecimal(countDistinct).d1.query.get.map(_ ==> List((1, 2), (2, 1)))
      _ <- Entity.i.date(countDistinct).d1.query.get.map(_ ==> List((1, 2), (2, 1)))
      _ <- Entity.i.duration(countDistinct).d1.query.get.map(_ ==> List((1, 2), (2, 1)))
      _ <- Entity.i.instant(countDistinct).d1.query.get.map(_ ==> List((1, 2), (2, 1)))
      _ <- Entity.i.localDate(countDistinct).d1.query.get.map(_ ==> List((1, 2), (2, 1)))
      _ <- Entity.i.localTime(countDistinct).d1.query.get.map(_ ==> List((1, 2), (2, 1)))
      _ <- Entity.i.localDateTime(countDistinct).d1.query.get.map(_ ==> List((1, 2), (2, 1)))
      _ <- Entity.i.offsetTime(countDistinct).d1.query.get.map(_ ==> List((1, 2), (2, 1)))
      _ <- Entity.i.offsetDateTime(countDistinct).d1.query.get.map(_ ==> List((1, 2), (2, 1)))
      _ <- Entity.i.zonedDateTime(countDistinct).d1.query.get.map(_ ==> List((1, 2), (2, 1)))
      _ <- Entity.i.uuid(countDistinct).d1.query.get.map(_ ==> List((1, 2), (2, 1)))
      _ <- Entity.i.uri(countDistinct).d1.query.get.map(_ ==> List((1, 2), (2, 1)))
      _ <- Entity.i.byte(countDistinct).d1.query.get.map(_ ==> List((1, 2), (2, 1)))
      _ <- Entity.i.short(countDistinct).d1.query.get.map(_ ==> List((1, 2), (2, 1)))
      _ <- Entity.i.char(countDistinct).d1.query.get.map(_ ==> List((1, 2), (2, 1)))
      _ <- Entity.i.ref(countDistinct).d1.query.get.map(_ ==> List((1, 2), (2, 1)))
    } yield ()
  }


  "sum" - types { implicit conn =>
    for {
      _ <- Entity.i.int.insert(
        (1, int1),
        (1, int2),
        (2, int2)).transact
      _ <- Entity.i.long.insert((1, long1), (1, long2), (2, long2)).transact
      _ <- Entity.i.float.insert((1, float1), (1, float2), (2, float2)).transact
      _ <- Entity.i.double.insert((1, double1), (1, double2), (2, double2)).transact
      _ <- Entity.i.bigInt.insert((1, bigInt1), (1, bigInt2), (2, bigInt2)).transact
      _ <- Entity.i.bigDecimal.insert((1, bigDecimal1), (1, bigDecimal2), (2, bigDecimal2)).transact
      _ <- Entity.i.byte.insert((1, byte1), (1, byte2), (2, byte2)).transact
      _ <- Entity.i.short.insert((1, short1), (1, short2), (2, short2)).transact

      _ <- Entity.i.int(sum).a1.query.get.map(_ ==> List((2, 2), (1, 3)))
      _ <- Entity.i.long(sum).a1.query.get.map(_ ==> List((2, 2), (1, 3)))
      _ <- Entity.i.float(sum).a1.query.get.map(_ ==> List((2, 2), (1, 3)))
      _ <- Entity.i.double(sum).a1.query.get.map(_ ==> List((2, 2), (1, 3)))
      _ <- Entity.i.bigInt(sum).a1.query.get.map(_ ==> List((2, 2), (1, 3)))
      _ <- Entity.i.bigDecimal(sum).a1.query.get.map(_ ==> List((2, 2), (1, 3)))
      _ <- Entity.i.byte(sum).a1.query.get.map(_ ==> List((2, 2), (1, 3)))
      _ <- Entity.i.short(sum).a1.query.get.map(_ ==> List((2, 2), (1, 3)))

      _ <- Entity.i.int(sum).d1.query.get.map(_ ==> List((1, 3), (2, 2)))
      _ <- Entity.i.long(sum).d1.query.get.map(_ ==> List((1, 3), (2, 2)))
      _ <- Entity.i.float(sum).d1.query.get.map(_ ==> List((1, 3), (2, 2)))
      _ <- Entity.i.double(sum).d1.query.get.map(_ ==> List((1, 3), (2, 2)))
      _ <- Entity.i.bigInt(sum).d1.query.get.map(_ ==> List((1, 3), (2, 2)))
      _ <- Entity.i.bigDecimal(sum).d1.query.get.map(_ ==> List((1, 3), (2, 2)))
      _ <- Entity.i.byte(sum).d1.query.get.map(_ ==> List((1, 3), (2, 2)))
      _ <- Entity.i.short(sum).d1.query.get.map(_ ==> List((1, 3), (2, 2)))
    } yield ()
  }


  "median" - types { implicit conn =>
    if (Seq("mariadb", "mysql", "sqlite").contains(database)) {
      Entity.i.int(median).a1.query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Sorting by median not implemented for this database."
        }
    } else {
      for {
        _ <- Entity.i.int.insert(
          (1, int1),
          (1, int2),
          (1, int3),
          (2, int4),
        ).transact
        _ <- Entity.i.long.insert((1, long1), (1, long2), (1, long3), (2, long4)).transact
        _ <- Entity.i.float.insert((1, float1), (1, float2), (1, float3), (2, float4)).transact
        _ <- Entity.i.double.insert((1, double1), (1, double2), (1, double3), (2, double4)).transact
        _ <- Entity.i.bigInt.insert((1, bigInt1), (1, bigInt2), (1, bigInt3), (2, bigInt4)).transact
        _ <- Entity.i.bigDecimal.insert((1, bigDecimal1), (1, bigDecimal2), (1, bigDecimal3), (2, bigDecimal4)).transact
        _ <- Entity.i.byte.insert((1, byte1), (1, byte2), (1, byte3), (2, byte4)).transact
        _ <- Entity.i.short.insert((1, short1), (1, short2), (1, short3), (2, short4)).transact

        _ <- Entity.i.int(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
        _ <- Entity.i.long(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
        _ <- Entity.i.float(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
        _ <- Entity.i.double(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
        _ <- Entity.i.bigInt(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
        _ <- Entity.i.bigDecimal(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
        _ <- Entity.i.byte(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
        _ <- Entity.i.short(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))

        _ <- Entity.i.int(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
        _ <- Entity.i.long(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
        _ <- Entity.i.float(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
        _ <- Entity.i.double(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
        _ <- Entity.i.bigInt(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
        _ <- Entity.i.bigDecimal(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
        _ <- Entity.i.byte(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
        _ <- Entity.i.short(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
      } yield ()
    }
  }


  "avg" - types { implicit conn =>
    for {
      _ <- Entity.i.int.insert(
        (1, int1),
        (1, int3),
        (2, int4),
      ).transact
      _ <- Entity.i.long.insert((1, long1), (1, long3), (2, long4)).transact
      _ <- Entity.i.float.insert((1, float1), (1, float3), (2, float4)).transact
      _ <- Entity.i.double.insert((1, double1), (1, double3), (2, double4)).transact
      _ <- Entity.i.bigInt.insert((1, bigInt1), (1, bigInt3), (2, bigInt4)).transact
      _ <- Entity.i.bigDecimal.insert((1, bigDecimal1), (1, bigDecimal3), (2, bigDecimal4)).transact
      _ <- Entity.i.byte.insert((1, byte1), (1, byte3), (2, byte4)).transact
      _ <- Entity.i.short.insert((1, short1), (1, short3), (2, short4)).transact

      _ <- Entity.i.int(avg).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
      _ <- Entity.i.long(avg).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
      _ <- Entity.i.float(avg).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
      _ <- Entity.i.double(avg).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
      _ <- Entity.i.bigInt(avg).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
      _ <- Entity.i.bigDecimal(avg).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
      _ <- Entity.i.byte(avg).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
      _ <- Entity.i.short(avg).a1.query.get.map(_ ==> List((1, 2), (2, 4)))

      _ <- Entity.i.int(avg).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
      _ <- Entity.i.long(avg).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
      _ <- Entity.i.float(avg).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
      _ <- Entity.i.double(avg).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
      _ <- Entity.i.bigInt(avg).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
      _ <- Entity.i.bigDecimal(avg).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
      _ <- Entity.i.byte(avg).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
      _ <- Entity.i.short(avg).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
    } yield ()
  }


  "variance" - types { implicit conn =>
    if (Seq("mariadb", "mysql", "sqlite").contains(database)) {
      Entity.i.int(variance).a1.query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Sorting by variance not implemented for this database."
        }
    } else {
      for {
        _ <- Entity.i.int.insert(
          (1, int1),
          (1, int3),
          (2, int4),
        ).transact
        _ <- Entity.i.long.insert((1, long1), (1, long3), (2, long4)).transact
        _ <- Entity.i.float.insert((1, float1), (1, float3), (2, float4)).transact
        _ <- Entity.i.double.insert((1, double1), (1, double3), (2, double4)).transact
        _ <- Entity.i.bigInt.insert((1, bigInt1), (1, bigInt3), (2, bigInt4)).transact
        _ <- Entity.i.bigDecimal.insert((1, bigDecimal1), (1, bigDecimal3), (2, bigDecimal4)).transact
        _ <- Entity.i.byte.insert((1, byte1), (1, byte3), (2, byte4)).transact
        _ <- Entity.i.short.insert((1, short1), (1, short3), (2, short4)).transact

        _ <- Entity.i.int(variance).a1.query.get.map(_ ==> List((2, 0.0), (1, 1.0)))
        _ <- Entity.i.long(variance).a1.query.get.map(_ ==> List((2, 0.0), (1, 1.0)))
        _ <- Entity.i.float(variance).a1.query.get.map(_ ==> List((2, 0.0), (1, 1.0)))
        _ <- Entity.i.double(variance).a1.query.get.map(_ ==> List((2, 0.0), (1, 1.0)))
        _ <- Entity.i.bigInt(variance).a1.query.get.map(_ ==> List((2, 0.0), (1, 1.0)))
        _ <- Entity.i.bigDecimal(variance).a1.query.get.map(_ ==> List((2, 0.0), (1, 1.0)))
        _ <- Entity.i.byte(variance).a1.query.get.map(_ ==> List((2, 0.0), (1, 1.0)))
        _ <- Entity.i.short(variance).a1.query.get.map(_ ==> List((2, 0.0), (1, 1.0)))

        _ <- Entity.i.int(variance).d1.query.get.map(_ ==> List((1, 1.0), (2, 0.0)))
        _ <- Entity.i.long(variance).d1.query.get.map(_ ==> List((1, 1.0), (2, 0.0)))
        _ <- Entity.i.float(variance).d1.query.get.map(_ ==> List((1, 1.0), (2, 0.0)))
        _ <- Entity.i.double(variance).d1.query.get.map(_ ==> List((1, 1.0), (2, 0.0)))
        _ <- Entity.i.bigInt(variance).d1.query.get.map(_ ==> List((1, 1.0), (2, 0.0)))
        _ <- Entity.i.bigDecimal(variance).d1.query.get.map(_ ==> List((1, 1.0), (2, 0.0)))
        _ <- Entity.i.byte(variance).d1.query.get.map(_ ==> List((1, 1.0), (2, 0.0)))
        _ <- Entity.i.short(variance).d1.query.get.map(_ ==> List((1, 1.0), (2, 0.0)))
      } yield ()
    }
  }


  "stddev" - types { implicit conn =>
    if (Seq("mariadb", "mysql", "sqlite").contains(database)) {
      Entity.i.int(stddev).a1.query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Sorting by standard deviation not implemented for this database."
        }
    } else {
      for {
        _ <- Entity.i.int.insert(
          (1, int1),
          (1, int3),
          (2, int4),
        ).transact
        _ <- Entity.i.long.insert((1, long1), (1, long3), (2, long4)).transact
        _ <- Entity.i.float.insert((1, float1), (1, float3), (2, float4)).transact
        _ <- Entity.i.double.insert((1, double1), (1, double3), (2, double4)).transact
        _ <- Entity.i.bigInt.insert((1, bigInt1), (1, bigInt3), (2, bigInt4)).transact
        _ <- Entity.i.bigDecimal.insert((1, bigDecimal1), (1, bigDecimal3), (2, bigDecimal4)).transact
        _ <- Entity.i.byte.insert((1, byte1), (1, byte3), (2, byte4)).transact
        _ <- Entity.i.short.insert((1, short1), (1, short3), (2, short4)).transact

        _ <- Entity.i.int(stddev).a1.query.get.map(_ ==> List((2, 0.0), (1, 1.0)))
        _ <- Entity.i.long(stddev).a1.query.get.map(_ ==> List((2, 0.0), (1, 1.0)))
        _ <- Entity.i.float(stddev).a1.query.get.map(_ ==> List((2, 0.0), (1, 1.0)))
        _ <- Entity.i.double(stddev).a1.query.get.map(_ ==> List((2, 0.0), (1, 1.0)))
        _ <- Entity.i.bigInt(stddev).a1.query.get.map(_ ==> List((2, 0.0), (1, 1.0)))
        _ <- Entity.i.bigDecimal(stddev).a1.query.get.map(_ ==> List((2, 0.0), (1, 1.0)))
        _ <- Entity.i.byte(stddev).a1.query.get.map(_ ==> List((2, 0.0), (1, 1.0)))
        _ <- Entity.i.short(stddev).a1.query.get.map(_ ==> List((2, 0.0), (1, 1.0)))

        _ <- Entity.i.int(stddev).d1.query.get.map(_ ==> List((1, 1.0), (2, 0.0)))
        _ <- Entity.i.long(stddev).d1.query.get.map(_ ==> List((1, 1.0), (2, 0.0)))
        _ <- Entity.i.float(stddev).d1.query.get.map(_ ==> List((1, 1.0), (2, 0.0)))
        _ <- Entity.i.double(stddev).d1.query.get.map(_ ==> List((1, 1.0), (2, 0.0)))
        _ <- Entity.i.bigInt(stddev).d1.query.get.map(_ ==> List((1, 1.0), (2, 0.0)))
        _ <- Entity.i.bigDecimal(stddev).d1.query.get.map(_ ==> List((1, 1.0), (2, 0.0)))
        _ <- Entity.i.byte(stddev).d1.query.get.map(_ ==> List((1, 1.0), (2, 0.0)))
        _ <- Entity.i.short(stddev).d1.query.get.map(_ ==> List((1, 1.0), (2, 0.0)))
      } yield ()
    }
  }
}
