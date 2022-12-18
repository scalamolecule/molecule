package molecule.db.datomic.test.sort

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object SortAggr extends DatomicTestSuite {

  // Use whole decimal numbers since only sorting is checked here
  override lazy val (float1, float2, float3, float4)                     = (1.0f, 2.0f, 3.0f, 4.0f)
  override lazy val (double1, double2, double3, double4)                 = (1.0, 2.0, 3.0, 4.0)
  override lazy val (bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4) =
    (BigDecimal(1.0), BigDecimal(2.0), BigDecimal(3.0), BigDecimal(4.0))


  lazy val tests = Tests {

    "count" - types { implicit conn =>
      Ns.i.string.insert(
        (1, string1),
        (1, string2),
        (1, string2),
        (2, string2)).transact
      Ns.i.int.insert((1, int1), (1, int2), (1, int2), (2, int2)).transact
      Ns.i.long.insert((1, long1), (1, long2), (1, long2), (2, long2)).transact
      Ns.i.float.insert((1, float1), (1, float2), (1, float2), (2, float2)).transact
      Ns.i.double.insert((1, double1), (1, double2), (1, double2), (2, double2)).transact
      Ns.i.boolean.insert((1, boolean1), (1, boolean2), (1, boolean2), (2, boolean2)).transact
      Ns.i.bigInt.insert((1, bigInt1), (1, bigInt2), (1, bigInt2), (2, bigInt2)).transact
      Ns.i.bigDecimal.insert((1, bigDecimal1), (1, bigDecimal2), (1, bigDecimal2), (2, bigDecimal2)).transact
      Ns.i.date.insert((1, date1), (1, date2), (1, date2), (2, date2)).transact
      Ns.i.uuid.insert((1, uuid1), (1, uuid2), (1, uuid2), (2, uuid2)).transact
      Ns.i.uri.insert((1, uri1), (1, uri2), (1, uri2), (2, uri2)).transact
      Ns.i.byte.insert((1, byte1), (1, byte2), (1, byte2), (2, byte2)).transact
      Ns.i.short.insert((1, short1), (1, short2), (1, short2), (2, short2)).transact
      Ns.i.char.insert((1, char1), (1, char2), (1, char2), (2, char2)).transact
      Ns.i.ref.insert((1, ref1), (1, ref2), (1, ref2), (2, ref2)).transact

      Ns.i.string(count).a1.query.get ==> List(
        (2, 1),
        (1, 3))
      Ns.i.int(count).a1.query.get ==> List((2, 1), (1, 3))
      Ns.i.long(count).a1.query.get ==> List((2, 1), (1, 3))
      Ns.i.float(count).a1.query.get ==> List((2, 1), (1, 3))
      Ns.i.double(count).a1.query.get ==> List((2, 1), (1, 3))
      Ns.i.boolean(count).a1.query.get ==> List((2, 1), (1, 3))
      Ns.i.bigInt(count).a1.query.get ==> List((2, 1), (1, 3))
      Ns.i.bigDecimal(count).a1.query.get ==> List((2, 1), (1, 3))
      Ns.i.date(count).a1.query.get ==> List((2, 1), (1, 3))
      Ns.i.uuid(count).a1.query.get ==> List((2, 1), (1, 3))
      Ns.i.uri(count).a1.query.get ==> List((2, 1), (1, 3))
      Ns.i.byte(count).a1.query.get ==> List((2, 1), (1, 3))
      Ns.i.short(count).a1.query.get ==> List((2, 1), (1, 3))
      Ns.i.char(count).a1.query.get ==> List((2, 1), (1, 3))
      Ns.i.ref(count).a1.query.get ==> List((2, 1), (1, 3))

      Ns.i.string(count).d1.query.get ==> List(
        (1, 3),
        (2, 1))
      Ns.i.int(count).d1.query.get ==> List((1, 3), (2, 1))
      Ns.i.long(count).d1.query.get ==> List((1, 3), (2, 1))
      Ns.i.float(count).d1.query.get ==> List((1, 3), (2, 1))
      Ns.i.double(count).d1.query.get ==> List((1, 3), (2, 1))
      Ns.i.boolean(count).d1.query.get ==> List((1, 3), (2, 1))
      Ns.i.bigInt(count).d1.query.get ==> List((1, 3), (2, 1))
      Ns.i.bigDecimal(count).d1.query.get ==> List((1, 3), (2, 1))
      Ns.i.date(count).d1.query.get ==> List((1, 3), (2, 1))
      Ns.i.uuid(count).d1.query.get ==> List((1, 3), (2, 1))
      Ns.i.uri(count).d1.query.get ==> List((1, 3), (2, 1))
      Ns.i.byte(count).d1.query.get ==> List((1, 3), (2, 1))
      Ns.i.short(count).d1.query.get ==> List((1, 3), (2, 1))
      Ns.i.char(count).d1.query.get ==> List((1, 3), (2, 1))
      Ns.i.ref(count).d1.query.get ==> List((1, 3), (2, 1))
    }


    "countDistinct" - types { implicit conn =>
      Ns.i.string.insert(
        (1, string1),
        (1, string2),
        (1, string2),
        (2, string2)).transact
      Ns.i.int.insert((1, int1), (1, int2), (1, int2), (2, int2)).transact
      Ns.i.long.insert((1, long1), (1, long2), (1, long2), (2, long2)).transact
      Ns.i.float.insert((1, float1), (1, float2), (1, float2), (2, float2)).transact
      Ns.i.double.insert((1, double1), (1, double2), (1, double2), (2, double2)).transact
      Ns.i.boolean.insert((1, boolean1), (1, boolean2), (1, boolean2), (2, boolean2)).transact
      Ns.i.bigInt.insert((1, bigInt1), (1, bigInt2), (1, bigInt2), (2, bigInt2)).transact
      Ns.i.bigDecimal.insert((1, bigDecimal1), (1, bigDecimal2), (1, bigDecimal2), (2, bigDecimal2)).transact
      Ns.i.date.insert((1, date1), (1, date2), (1, date2), (2, date2)).transact
      Ns.i.uuid.insert((1, uuid1), (1, uuid2), (1, uuid2), (2, uuid2)).transact
      Ns.i.uri.insert((1, uri1), (1, uri2), (1, uri2), (2, uri2)).transact
      Ns.i.byte.insert((1, byte1), (1, byte2), (1, byte2), (2, byte2)).transact
      Ns.i.short.insert((1, short1), (1, short2), (1, short2), (2, short2)).transact
      Ns.i.char.insert((1, char1), (1, char2), (1, char2), (2, char2)).transact
      Ns.i.ref.insert((1, ref1), (1, ref2), (1, ref2), (2, ref2)).transact

      Ns.i.string(countDistinct).a1.query.get ==> List(
        (2, 1),
        (1, 2))
      Ns.i.int(countDistinct).a1.query.get ==> List((2, 1), (1, 2))
      Ns.i.long(countDistinct).a1.query.get ==> List((2, 1), (1, 2))
      Ns.i.float(countDistinct).a1.query.get ==> List((2, 1), (1, 2))
      Ns.i.double(countDistinct).a1.query.get ==> List((2, 1), (1, 2))
      Ns.i.boolean(countDistinct).a1.query.get ==> List((2, 1), (1, 2))
      Ns.i.bigInt(countDistinct).a1.query.get ==> List((2, 1), (1, 2))
      Ns.i.bigDecimal(countDistinct).a1.query.get ==> List((2, 1), (1, 2))
      Ns.i.date(countDistinct).a1.query.get ==> List((2, 1), (1, 2))
      Ns.i.uuid(countDistinct).a1.query.get ==> List((2, 1), (1, 2))
      Ns.i.uri(countDistinct).a1.query.get ==> List((2, 1), (1, 2))
      Ns.i.byte(countDistinct).a1.query.get ==> List((2, 1), (1, 2))
      Ns.i.short(countDistinct).a1.query.get ==> List((2, 1), (1, 2))
      Ns.i.char(countDistinct).a1.query.get ==> List((2, 1), (1, 2))
      Ns.i.ref(countDistinct).a1.query.get ==> List((2, 1), (1, 2))

      Ns.i.string(countDistinct).d1.query.get ==> List(
        (1, 2),
        (2, 1))
      Ns.i.int(countDistinct).d1.query.get ==> List((1, 2), (2, 1))
      Ns.i.long(countDistinct).d1.query.get ==> List((1, 2), (2, 1))
      Ns.i.float(countDistinct).d1.query.get ==> List((1, 2), (2, 1))
      Ns.i.double(countDistinct).d1.query.get ==> List((1, 2), (2, 1))
      Ns.i.boolean(countDistinct).d1.query.get ==> List((1, 2), (2, 1))
      Ns.i.bigInt(countDistinct).d1.query.get ==> List((1, 2), (2, 1))
      Ns.i.bigDecimal(countDistinct).d1.query.get ==> List((1, 2), (2, 1))
      Ns.i.date(countDistinct).d1.query.get ==> List((1, 2), (2, 1))
      Ns.i.uuid(countDistinct).d1.query.get ==> List((1, 2), (2, 1))
      Ns.i.uri(countDistinct).d1.query.get ==> List((1, 2), (2, 1))
      Ns.i.byte(countDistinct).d1.query.get ==> List((1, 2), (2, 1))
      Ns.i.short(countDistinct).d1.query.get ==> List((1, 2), (2, 1))
      Ns.i.char(countDistinct).d1.query.get ==> List((1, 2), (2, 1))
      Ns.i.ref(countDistinct).d1.query.get ==> List((1, 2), (2, 1))
    }


    "sum" - types { implicit conn =>
      Ns.i.int.insert(
        (1, int1),
        (1, int2),
        (2, int2)).transact
      Ns.i.long.insert((1, long1), (1, long2), (2, long2)).transact
      Ns.i.float.insert((1, float1), (1, float2), (2, float2)).transact
      Ns.i.double.insert((1, double1), (1, double2), (2, double2)).transact
      Ns.i.bigInt.insert((1, bigInt1), (1, bigInt2), (2, bigInt2)).transact
      Ns.i.bigDecimal.insert((1, bigDecimal1), (1, bigDecimal2), (2, bigDecimal2)).transact
      Ns.i.byte.insert((1, byte1), (1, byte2), (2, byte2)).transact
      Ns.i.short.insert((1, short1), (1, short2), (2, short2)).transact
      Ns.i.ref.insert((1, ref1), (1, ref2), (2, ref2)).transact

      Ns.i.int(sum).a1.query.get ==> List(
        (2, 2),
        (1, 3))
      Ns.i.long(sum).a1.query.get ==> List((2, 2), (1, 3))
      Ns.i.float(sum).a1.query.get ==> List((2, 2), (1, 3))
      Ns.i.double(sum).a1.query.get ==> List((2, 2), (1, 3))
      Ns.i.bigInt(sum).a1.query.get ==> List((2, 2), (1, 3))
      Ns.i.bigDecimal(sum).a1.query.get ==> List((2, 2), (1, 3))
      Ns.i.byte(sum).a1.query.get ==> List((2, 2), (1, 3))
      Ns.i.short(sum).a1.query.get ==> List((2, 2), (1, 3))
      Ns.i.ref(sum).a1.query.get ==> List((2, 2), (1, 3))

      Ns.i.int(sum).d1.query.get ==> List(
        (1, 3),
        (2, 2))
      Ns.i.long(sum).d1.query.get ==> List((1, 3), (2, 2))
      Ns.i.float(sum).d1.query.get ==> List((1, 3), (2, 2))
      Ns.i.double(sum).d1.query.get ==> List((1, 3), (2, 2))
      Ns.i.bigInt(sum).d1.query.get ==> List((1, 3), (2, 2))
      Ns.i.bigDecimal(sum).d1.query.get ==> List((1, 3), (2, 2))
      Ns.i.byte(sum).d1.query.get ==> List((1, 3), (2, 2))
      Ns.i.short(sum).d1.query.get ==> List((1, 3), (2, 2))
      Ns.i.ref(sum).d1.query.get ==> List((1, 3), (2, 2))
    }


    "median" - types { implicit conn =>
      Ns.i.int.insert(
        (1, int1),
        (1, int3),
        (2, int4),
      ).transact
      Ns.i.long.insert((1, long1), (1, long3), (2, long4)).transact
      Ns.i.float.insert((1, float1), (1, float3), (2, float4)).transact
      Ns.i.double.insert((1, double1), (1, double3), (2, double4)).transact
      Ns.i.bigInt.insert((1, bigInt1), (1, bigInt3), (2, bigInt4)).transact
      Ns.i.bigDecimal.insert((1, bigDecimal1), (1, bigDecimal3), (2, bigDecimal4)).transact
      Ns.i.byte.insert((1, byte1), (1, byte3), (2, byte4)).transact
      Ns.i.short.insert((1, short1), (1, short3), (2, short4)).transact
      Ns.i.ref.insert((1, ref1), (1, ref3), (2, ref4)).transact

      Ns.i.int(median).a1.query.get ==> List(
        (1, 2),
        (2, 4))
      Ns.i.long(median).a1.query.get ==> List((1, 2), (2, 4))
      Ns.i.float(median).a1.query.get ==> List((1, 2), (2, 4))
      Ns.i.double(median).a1.query.get ==> List((1, 2), (2, 4))
      Ns.i.bigInt(median).a1.query.get ==> List((1, 2), (2, 4))
      Ns.i.bigDecimal(median).a1.query.get ==> List((1, 2), (2, 4))
      Ns.i.byte(median).a1.query.get ==> List((1, 2), (2, 4))
      Ns.i.short(median).a1.query.get ==> List((1, 2), (2, 4))
      Ns.i.ref(median).a1.query.get ==> List((1, 2), (2, 4))

      Ns.i.int(median).d1.query.get ==> List(
        (2, 4),
        (1, 2))
      Ns.i.long(median).d1.query.get ==> List((2, 4), (1, 2))
      Ns.i.float(median).d1.query.get ==> List((2, 4), (1, 2))
      Ns.i.double(median).d1.query.get ==> List((2, 4), (1, 2))
      Ns.i.bigInt(median).d1.query.get ==> List((2, 4), (1, 2))
      Ns.i.bigDecimal(median).d1.query.get ==> List((2, 4), (1, 2))
      Ns.i.byte(median).d1.query.get ==> List((2, 4), (1, 2))
      Ns.i.short(median).d1.query.get ==> List((2, 4), (1, 2))
      Ns.i.ref(median).d1.query.get ==> List((2, 4), (1, 2))
    }


    "avg" - types { implicit conn =>
      Ns.i.int.insert(
        (1, int1),
        (1, int3),
        (2, int4),
      ).transact
      Ns.i.long.insert((1, long1), (1, long3), (2, long4)).transact
      Ns.i.float.insert((1, float1), (1, float3), (2, float4)).transact
      Ns.i.double.insert((1, double1), (1, double3), (2, double4)).transact
      Ns.i.bigInt.insert((1, bigInt1), (1, bigInt3), (2, bigInt4)).transact
      Ns.i.bigDecimal.insert((1, bigDecimal1), (1, bigDecimal3), (2, bigDecimal4)).transact
      Ns.i.byte.insert((1, byte1), (1, byte3), (2, byte4)).transact
      Ns.i.short.insert((1, short1), (1, short3), (2, short4)).transact
      Ns.i.ref.insert((1, ref1), (1, ref3), (2, ref4)).transact

      Ns.i.int(avg).a1.query.get ==> List(
        (1, 2),
        (2, 4))
      Ns.i.long(avg).a1.query.get ==> List((1, 2), (2, 4))
      Ns.i.float(avg).a1.query.get ==> List((1, 2), (2, 4))
      Ns.i.double(avg).a1.query.get ==> List((1, 2), (2, 4))
      Ns.i.bigInt(avg).a1.query.get ==> List((1, 2), (2, 4))
      Ns.i.bigDecimal(avg).a1.query.get ==> List((1, 2), (2, 4))
      Ns.i.byte(avg).a1.query.get ==> List((1, 2), (2, 4))
      Ns.i.short(avg).a1.query.get ==> List((1, 2), (2, 4))
      Ns.i.ref(avg).a1.query.get ==> List((1, 2), (2, 4))

      Ns.i.int(avg).d1.query.get ==> List(
        (2, 4),
        (1, 2))
      Ns.i.long(avg).d1.query.get ==> List((2, 4), (1, 2))
      Ns.i.float(avg).d1.query.get ==> List((2, 4), (1, 2))
      Ns.i.double(avg).d1.query.get ==> List((2, 4), (1, 2))
      Ns.i.bigInt(avg).d1.query.get ==> List((2, 4), (1, 2))
      Ns.i.bigDecimal(avg).d1.query.get ==> List((2, 4), (1, 2))
      Ns.i.byte(avg).d1.query.get ==> List((2, 4), (1, 2))
      Ns.i.short(avg).d1.query.get ==> List((2, 4), (1, 2))
      Ns.i.ref(avg).d1.query.get ==> List((2, 4), (1, 2))
    }


    "variance" - types { implicit conn =>
      Ns.i.int.insert(
        (1, int1),
        (1, int3),
        (2, int4),
      ).transact
      Ns.i.long.insert((1, long1), (1, long3), (2, long4)).transact
      Ns.i.float.insert((1, float1), (1, float3), (2, float4)).transact
      Ns.i.double.insert((1, double1), (1, double3), (2, double4)).transact
      Ns.i.bigInt.insert((1, bigInt1), (1, bigInt3), (2, bigInt4)).transact
      Ns.i.bigDecimal.insert((1, bigDecimal1), (1, bigDecimal3), (2, bigDecimal4)).transact
      Ns.i.byte.insert((1, byte1), (1, byte3), (2, byte4)).transact
      Ns.i.short.insert((1, short1), (1, short3), (2, short4)).transact
      Ns.i.ref.insert((1, ref1), (1, ref3), (2, ref4)).transact

      Ns.i.int(variance).a1.query.get ==> List(
        (2, 0.0),
        (1, 1.0))
      Ns.i.long(variance).a1.query.get ==> List((2, 0.0), (1, 1.0))
      Ns.i.float(variance).a1.query.get ==> List((2, 0.0), (1, 1.0))
      Ns.i.double(variance).a1.query.get ==> List((2, 0.0), (1, 1.0))
      Ns.i.bigInt(variance).a1.query.get ==> List((2, 0.0), (1, 1.0))
      Ns.i.bigDecimal(variance).a1.query.get ==> List((2, 0.0), (1, 1.0))
      Ns.i.byte(variance).a1.query.get ==> List((2, 0.0), (1, 1.0))
      Ns.i.short(variance).a1.query.get ==> List((2, 0.0), (1, 1.0))
      Ns.i.ref(variance).a1.query.get ==> List((2, 0.0), (1, 1.0))

      Ns.i.int(variance).d1.query.get ==> List(
        (1, 1.0),
        (2, 0.0))
      Ns.i.long(variance).d1.query.get ==> List((1, 1.0), (2, 0.0))
      Ns.i.float(variance).d1.query.get ==> List((1, 1.0), (2, 0.0))
      Ns.i.double(variance).d1.query.get ==> List((1, 1.0), (2, 0.0))
      Ns.i.bigInt(variance).d1.query.get ==> List((1, 1.0), (2, 0.0))
      Ns.i.bigDecimal(variance).d1.query.get ==> List((1, 1.0), (2, 0.0))
      Ns.i.byte(variance).d1.query.get ==> List((1, 1.0), (2, 0.0))
      Ns.i.short(variance).d1.query.get ==> List((1, 1.0), (2, 0.0))
      Ns.i.ref(variance).d1.query.get ==> List((1, 1.0), (2, 0.0))
    }


    "stddev" - types { implicit conn =>
      Ns.i.int.insert(
        (1, int1),
        (1, int3),
        (2, int4),
      ).transact
      Ns.i.long.insert((1, long1), (1, long3), (2, long4)).transact
      Ns.i.float.insert((1, float1), (1, float3), (2, float4)).transact
      Ns.i.double.insert((1, double1), (1, double3), (2, double4)).transact
      Ns.i.bigInt.insert((1, bigInt1), (1, bigInt3), (2, bigInt4)).transact
      Ns.i.bigDecimal.insert((1, bigDecimal1), (1, bigDecimal3), (2, bigDecimal4)).transact
      Ns.i.byte.insert((1, byte1), (1, byte3), (2, byte4)).transact
      Ns.i.short.insert((1, short1), (1, short3), (2, short4)).transact
      Ns.i.ref.insert((1, ref1), (1, ref3), (2, ref4)).transact

      Ns.i.int(stddev).a1.query.get ==> List(
        (2, 0.0),
        (1, 1.0))
      Ns.i.long(stddev).a1.query.get ==> List((2, 0.0), (1, 1.0))
      Ns.i.float(stddev).a1.query.get ==> List((2, 0.0), (1, 1.0))
      Ns.i.double(stddev).a1.query.get ==> List((2, 0.0), (1, 1.0))
      Ns.i.bigInt(stddev).a1.query.get ==> List((2, 0.0), (1, 1.0))
      Ns.i.bigDecimal(stddev).a1.query.get ==> List((2, 0.0), (1, 1.0))
      Ns.i.byte(stddev).a1.query.get ==> List((2, 0.0), (1, 1.0))
      Ns.i.short(stddev).a1.query.get ==> List((2, 0.0), (1, 1.0))
      Ns.i.ref(stddev).a1.query.get ==> List((2, 0.0), (1, 1.0))

      Ns.i.int(stddev).d1.query.get ==> List(
        (1, 1.0),
        (2, 0.0))
      Ns.i.long(stddev).d1.query.get ==> List((1, 1.0), (2, 0.0))
      Ns.i.float(stddev).d1.query.get ==> List((1, 1.0), (2, 0.0))
      Ns.i.double(stddev).d1.query.get ==> List((1, 1.0), (2, 0.0))
      Ns.i.bigInt(stddev).d1.query.get ==> List((1, 1.0), (2, 0.0))
      Ns.i.bigDecimal(stddev).d1.query.get ==> List((1, 1.0), (2, 0.0))
      Ns.i.byte(stddev).d1.query.get ==> List((1, 1.0), (2, 0.0))
      Ns.i.short(stddev).d1.query.get ==> List((1, 1.0), (2, 0.0))
      Ns.i.ref(stddev).d1.query.get ==> List((1, 1.0), (2, 0.0))
    }
  }
}
