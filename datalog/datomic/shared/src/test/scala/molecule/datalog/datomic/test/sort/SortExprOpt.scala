package molecule.datalog.datomic.test.sort

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._

object SortExprOpt extends DatomicTestSuite {

  override lazy val tests = Tests {

    "Equal (apply)" - types { implicit conn =>
      for {
        _ <- Ns.i.string_?.insert((0, None), (1, Some(string1)), (2, Some(string2)), (3, Some(string3))).transact
        _ <- Ns.i.int_?.insert((0, None), (1, Some(int1)), (2, Some(int2)), (3, Some(int3))).transact
        _ <- Ns.i.long_?.insert((0, None), (1, Some(long1)), (2, Some(long2)), (3, Some(long3))).transact
        _ <- Ns.i.float_?.insert((0, None), (1, Some(float1)), (2, Some(float2)), (3, Some(float3))).transact
        _ <- Ns.i.double_?.insert((0, None), (1, Some(double1)), (2, Some(double2)), (3, Some(double3))).transact
        _ <- Ns.i.boolean_?.insert((0, None), (1, Some(boolean1)), (2, Some(boolean2))).transact
        _ <- Ns.i.bigInt_?.insert((0, None), (1, Some(bigInt1)), (2, Some(bigInt2)), (3, Some(bigInt3))).transact
        _ <- Ns.i.bigDecimal_?.insert((0, None), (1, Some(bigDecimal1)), (2, Some(bigDecimal2)), (3, Some(bigDecimal3))).transact
        _ <- Ns.i.date_?.insert((0, None), (1, Some(date1)), (2, Some(date2)), (3, Some(date3))).transact
        _ <- Ns.i.uuid_?.insert((0, None), (1, Some(uuid1)), (2, Some(uuid2)), (3, Some(uuid3))).transact
        _ <- Ns.i.uri_?.insert((0, None), (1, Some(uri1)), (2, Some(uri2)), (3, Some(uri3))).transact
        _ <- Ns.i.byte_?.insert((0, None), (1, Some(byte1)), (2, Some(byte2)), (3, Some(byte3))).transact
        _ <- Ns.i.short_?.insert((0, None), (1, Some(short1)), (2, Some(short2)), (3, Some(short3))).transact
        _ <- Ns.i.char_?.insert((0, None), (1, Some(char1)), (2, Some(char2)), (3, Some(char3))).transact
        _ <- Ns.i.ref_?.insert((0, None), (1, Some(ref1)), (2, Some(ref2)), (3, Some(ref3))).transact

        // Ascending
        _ <- Ns.i.string_?(Some(Seq(string1, string2))).a1.query.get.map(_ ==> List((1, Some(string1)), (2, Some(string2))))
        _ <- Ns.i.int_?(Some(Seq(int1, int2))).a1.query.get.map(_ ==> List((1, Some(int1)), (2, Some(int2))))
        _ <- Ns.i.long_?(Some(Seq(long1, long2))).a1.query.get.map(_ ==> List((1, Some(long1)), (2, Some(long2))))
        _ <- Ns.i.float_?(Some(Seq(float1, float2))).a1.query.get.map(_ ==> List((1, Some(float1)), (2, Some(float2))))
        _ <- Ns.i.double_?(Some(Seq(double1, double2))).a1.query.get.map(_ ==> List((1, Some(double1)), (2, Some(double2))))
        _ <- Ns.i.boolean_?(Some(Seq(boolean1, boolean2))).a1.query.get.map(_ ==> List((1, Some(boolean1)), (2, Some(boolean2))))
        _ <- Ns.i.bigInt_?(Some(Seq(bigInt1, bigInt2))).a1.query.get.map(_ ==> List((1, Some(bigInt1)), (2, Some(bigInt2))))
        _ <- Ns.i.bigDecimal_?(Some(Seq(bigDecimal1, bigDecimal2))).a1.query.get.map(_ ==> List((1, Some(bigDecimal1)), (2, Some(bigDecimal2))))
        _ <- Ns.i.date_?(Some(Seq(date1, date2))).a1.query.get.map(_ ==> List((1, Some(date1)), (2, Some(date2))))
        _ <- Ns.i.uuid_?(Some(Seq(uuid1, uuid2))).a1.query.get.map(_ ==> List((1, Some(uuid1)), (2, Some(uuid2))))
        _ <- Ns.i.uri_?(Some(Seq(uri1, uri2))).a1.query.get.map(_ ==> List((1, Some(uri1)), (2, Some(uri2))))
        _ <- Ns.i.byte_?(Some(Seq(byte1, byte2))).a1.query.get.map(_ ==> List((1, Some(byte1)), (2, Some(byte2))))
        _ <- Ns.i.short_?(Some(Seq(short1, short2))).a1.query.get.map(_ ==> List((1, Some(short1)), (2, Some(short2))))
        _ <- Ns.i.char_?(Some(Seq(char1, char2))).a1.query.get.map(_ ==> List((1, Some(char1)), (2, Some(char2))))
        _ <- Ns.i.ref_?(Some(Seq(ref1, ref2))).a1.query.get.map(_ ==> List((1, Some(ref1)), (2, Some(ref2))))

        // Descending
        _ <- Ns.i.string_?(Some(Seq(string1, string2))).d1.query.get.map(_ ==> List((2, Some(string2)), (1, Some(string1))))
        _ <- Ns.i.int_?(Some(Seq(int1, int2))).d1.query.get.map(_ ==> List((2, Some(int2)), (1, Some(int1))))
        _ <- Ns.i.long_?(Some(Seq(long1, long2))).d1.query.get.map(_ ==> List((2, Some(long2)), (1, Some(long1))))
        _ <- Ns.i.float_?(Some(Seq(float1, float2))).d1.query.get.map(_ ==> List((2, Some(float2)), (1, Some(float1))))
        _ <- Ns.i.double_?(Some(Seq(double1, double2))).d1.query.get.map(_ ==> List((2, Some(double2)), (1, Some(double1))))
        _ <- Ns.i.boolean_?(Some(Seq(boolean1, boolean2))).d1.query.get.map(_ ==> List((2, Some(boolean2)), (1, Some(boolean1))))
        _ <- Ns.i.bigInt_?(Some(Seq(bigInt1, bigInt2))).d1.query.get.map(_ ==> List((2, Some(bigInt2)), (1, Some(bigInt1))))
        _ <- Ns.i.bigDecimal_?(Some(Seq(bigDecimal1, bigDecimal2))).d1.query.get.map(_ ==> List((2, Some(bigDecimal2)), (1, Some(bigDecimal1))))
        _ <- Ns.i.date_?(Some(Seq(date1, date2))).d1.query.get.map(_ ==> List((2, Some(date2)), (1, Some(date1))))
        _ <- Ns.i.uuid_?(Some(Seq(uuid1, uuid2))).d1.query.get.map(_ ==> List((2, Some(uuid2)), (1, Some(uuid1))))
        _ <- Ns.i.uri_?(Some(Seq(uri1, uri2))).d1.query.get.map(_ ==> List((2, Some(uri2)), (1, Some(uri1))))
        _ <- Ns.i.byte_?(Some(Seq(byte1, byte2))).d1.query.get.map(_ ==> List((2, Some(byte2)), (1, Some(byte1))))
        _ <- Ns.i.short_?(Some(Seq(short1, short2))).d1.query.get.map(_ ==> List((2, Some(short2)), (1, Some(short1))))
        _ <- Ns.i.char_?(Some(Seq(char1, char2))).d1.query.get.map(_ ==> List((2, Some(char2)), (1, Some(char1))))
        _ <- Ns.i.ref_?(Some(Seq(ref1, ref2))).d1.query.get.map(_ ==> List((2, Some(ref2)), (1, Some(ref1))))


        // Some empty list of values
        _ <- Ns.i.int_?(Some(Seq())).a1.query.get.map(_ ==> List())

        // Only one None value, so not relevant to sort anyway
        _ <- Ns.i.a2.int_?(Option.empty[Int]).a1.query.get.map(_ ==> List(
          (0, None),
          (1, None),
          (2, None),
          (3, None),
        ))
      } yield ()
    }


    "Not equal" - types { implicit conn =>
      for {
        _ <- Ns.i.string_?.insert((0, None), (1, Some(string1)), (2, Some(string2)), (3, Some(string3))).transact
        _ <- Ns.i.int_?.insert((0, None), (1, Some(int1)), (2, Some(int2)), (3, Some(int3))).transact
        _ <- Ns.i.long_?.insert((0, None), (1, Some(long1)), (2, Some(long2)), (3, Some(long3))).transact
        _ <- Ns.i.float_?.insert((0, None), (1, Some(float1)), (2, Some(float2)), (3, Some(float3))).transact
        _ <- Ns.i.double_?.insert((0, None), (1, Some(double1)), (2, Some(double2)), (3, Some(double3))).transact
        _ <- Ns.i.boolean_?.insert((0, None), (1, Some(boolean1)), (2, Some(boolean2))).transact
        _ <- Ns.i.bigInt_?.insert((0, None), (1, Some(bigInt1)), (2, Some(bigInt2)), (3, Some(bigInt3))).transact
        _ <- Ns.i.bigDecimal_?.insert((0, None), (1, Some(bigDecimal1)), (2, Some(bigDecimal2)), (3, Some(bigDecimal3))).transact
        _ <- Ns.i.date_?.insert((0, None), (1, Some(date1)), (2, Some(date2)), (3, Some(date3))).transact
        _ <- Ns.i.uuid_?.insert((0, None), (1, Some(uuid1)), (2, Some(uuid2)), (3, Some(uuid3))).transact
        _ <- Ns.i.uri_?.insert((0, None), (1, Some(uri1)), (2, Some(uri2)), (3, Some(uri3))).transact
        _ <- Ns.i.byte_?.insert((0, None), (1, Some(byte1)), (2, Some(byte2)), (3, Some(byte3))).transact
        _ <- Ns.i.short_?.insert((0, None), (1, Some(short1)), (2, Some(short2)), (3, Some(short3))).transact
        _ <- Ns.i.char_?.insert((0, None), (1, Some(char1)), (2, Some(char2)), (3, Some(char3))).transact
        _ <- Ns.i.ref_?.insert((0, None), (1, Some(ref1)), (2, Some(ref2)), (3, Some(ref3))).transact

        // Ascending
        _ <- Ns.i.string_?.not(Some(string1)).a1.query.get.map(_ ==> List((2, Some(string2)), (3, Some(string3))))
        _ <- Ns.i.int_?.not(Some(int1)).a1.query.get.map(_ ==> List((2, Some(int2)), (3, Some(int3))))
        _ <- Ns.i.long_?.not(Some(long1)).a1.query.get.map(_ ==> List((2, Some(long2)), (3, Some(long3))))
        _ <- Ns.i.float_?.not(Some(float1)).a1.query.get.map(_ ==> List((2, Some(float2)), (3, Some(float3))))
        _ <- Ns.i.double_?.not(Some(double1)).a1.query.get.map(_ ==> List((2, Some(double2)), (3, Some(double3))))
        _ <- Ns.i.boolean_?.not(Some(boolean1)).a1.query.get.map(_ ==> List((2, Some(boolean2))))
        _ <- Ns.i.bigInt_?.not(Some(bigInt1)).a1.query.get.map(_ ==> List((2, Some(bigInt2)), (3, Some(bigInt3))))
        _ <- Ns.i.bigDecimal_?.not(Some(bigDecimal1)).a1.query.get.map(_ ==> List((2, Some(bigDecimal2)), (3, Some(bigDecimal3))))
        _ <- Ns.i.date_?.not(Some(date1)).a1.query.get.map(_ ==> List((2, Some(date2)), (3, Some(date3))))
        _ <- Ns.i.uuid_?.not(Some(uuid1)).a1.query.get.map(_ ==> List((2, Some(uuid2)), (3, Some(uuid3))))
        _ <- Ns.i.uri_?.not(Some(uri1)).a1.query.get.map(_ ==> List((2, Some(uri2)), (3, Some(uri3))))
        _ <- Ns.i.byte_?.not(Some(byte1)).a1.query.get.map(_ ==> List((2, Some(byte2)), (3, Some(byte3))))
        _ <- Ns.i.short_?.not(Some(short1)).a1.query.get.map(_ ==> List((2, Some(short2)), (3, Some(short3))))
        _ <- Ns.i.char_?.not(Some(char1)).a1.query.get.map(_ ==> List((2, Some(char2)), (3, Some(char3))))
        _ <- Ns.i.ref_?.not(Some(ref1)).a1.query.get.map(_ ==> List((2, Some(ref2)), (3, Some(ref3))))

        // Descending
        _ <- Ns.i.string_?.not(Some(string1)).d1.query.get.map(_ ==> List((3, Some(string3)), (2, Some(string2))))
        _ <- Ns.i.int_?.not(Some(int1)).d1.query.get.map(_ ==> List((3, Some(int3)), (2, Some(int2))))
        _ <- Ns.i.long_?.not(Some(long1)).d1.query.get.map(_ ==> List((3, Some(long3)), (2, Some(long2))))
        _ <- Ns.i.float_?.not(Some(float1)).d1.query.get.map(_ ==> List((3, Some(float3)), (2, Some(float2))))
        _ <- Ns.i.double_?.not(Some(double1)).d1.query.get.map(_ ==> List((3, Some(double3)), (2, Some(double2))))
        _ <- Ns.i.boolean_?.not(Some(boolean1)).d1.query.get.map(_ ==> List((2, Some(boolean2))))
        _ <- Ns.i.bigInt_?.not(Some(bigInt1)).d1.query.get.map(_ ==> List((3, Some(bigInt3)), (2, Some(bigInt2))))
        _ <- Ns.i.bigDecimal_?.not(Some(bigDecimal1)).d1.query.get.map(_ ==> List((3, Some(bigDecimal3)), (2, Some(bigDecimal2))))
        _ <- Ns.i.date_?.not(Some(date1)).d1.query.get.map(_ ==> List((3, Some(date3)), (2, Some(date2))))
        _ <- Ns.i.uuid_?.not(Some(uuid1)).d1.query.get.map(_ ==> List((3, Some(uuid3)), (2, Some(uuid2))))
        _ <- Ns.i.uri_?.not(Some(uri1)).d1.query.get.map(_ ==> List((3, Some(uri3)), (2, Some(uri2))))
        _ <- Ns.i.byte_?.not(Some(byte1)).d1.query.get.map(_ ==> List((3, Some(byte3)), (2, Some(byte2))))
        _ <- Ns.i.short_?.not(Some(short1)).d1.query.get.map(_ ==> List((3, Some(short3)), (2, Some(short2))))
        _ <- Ns.i.char_?.not(Some(char1)).d1.query.get.map(_ ==> List((3, Some(char3)), (2, Some(char2))))
        _ <- Ns.i.ref_?.not(Some(ref1)).d1.query.get.map(_ ==> List((3, Some(ref3)), (2, Some(ref2))))


        _ <- Ns.i.int_?.not(Some(Seq())).a1.query.get.map(_ ==> List(
          (1, Some(int1)),
          (2, Some(int2)),
          (3, Some(int3))
        ))

        _ <- Ns.i.a2.int_?.not(Option.empty[Int]).a1.query.get.map(_ ==> List(
          (1, Some(int1)),
          (2, Some(int2)),
          (3, Some(int3))
        ))
      } yield ()
    }


    "Compare" - types { implicit conn =>
      for {
        _ <- Ns.i.string_?.insert((0, None), (1, Some(string1)), (2, Some(string2)), (3, Some(string3))).transact
        _ <- Ns.i.int_?.insert((0, None), (1, Some(int1)), (2, Some(int2)), (3, Some(int3))).transact
        _ <- Ns.i.long_?.insert((0, None), (1, Some(long1)), (2, Some(long2)), (3, Some(long3))).transact
        _ <- Ns.i.float_?.insert((0, None), (1, Some(float1)), (2, Some(float2)), (3, Some(float3))).transact
        _ <- Ns.i.double_?.insert((0, None), (1, Some(double1)), (2, Some(double2)), (3, Some(double3))).transact
        _ <- Ns.i.boolean_?.insert((0, None), (1, Some(boolean1)), (2, Some(boolean2))).transact
        _ <- Ns.i.bigInt_?.insert((0, None), (1, Some(bigInt1)), (2, Some(bigInt2)), (3, Some(bigInt3))).transact
        _ <- Ns.i.bigDecimal_?.insert((0, None), (1, Some(bigDecimal1)), (2, Some(bigDecimal2)), (3, Some(bigDecimal3))).transact
        _ <- Ns.i.date_?.insert((0, None), (1, Some(date1)), (2, Some(date2)), (3, Some(date3))).transact
        _ <- Ns.i.uuid_?.insert((0, None), (1, Some(uuid1)), (2, Some(uuid2)), (3, Some(uuid3))).transact
        _ <- Ns.i.uri_?.insert((0, None), (1, Some(uri1)), (2, Some(uri2)), (3, Some(uri3))).transact
        _ <- Ns.i.byte_?.insert((0, None), (1, Some(byte1)), (2, Some(byte2)), (3, Some(byte3))).transact
        _ <- Ns.i.short_?.insert((0, None), (1, Some(short1)), (2, Some(short2)), (3, Some(short3))).transact
        _ <- Ns.i.char_?.insert((0, None), (1, Some(char1)), (2, Some(char2)), (3, Some(char3))).transact
        _ <- Ns.i.ref_?.insert((0, None), (1, Some(ref1)), (2, Some(ref2)), (3, Some(ref3))).transact

        // Ascending
        _ <- Ns.i.string_?.<(Some(string3)).a1.query.get.map(_ ==> List((1, Some(string1)), (2, Some(string2))))
        _ <- Ns.i.int_?.<(Some(int3)).a1.query.get.map(_ ==> List((1, Some(int1)), (2, Some(int2))))
        _ <- Ns.i.long_?.<(Some(long3)).a1.query.get.map(_ ==> List((1, Some(long1)), (2, Some(long2))))
        _ <- Ns.i.float_?.<(Some(float3)).a1.query.get.map(_ ==> List((1, Some(float1)), (2, Some(float2))))
        _ <- Ns.i.double_?.<(Some(double3)).a1.query.get.map(_ ==> List((1, Some(double1)), (2, Some(double2))))
        _ <- Ns.i.boolean_?.<(Some(boolean3)).a1.query.get.map(_ ==> List())
        _ <- Ns.i.bigInt_?.<(Some(bigInt3)).a1.query.get.map(_ ==> List((1, Some(bigInt1)), (2, Some(bigInt2))))
        _ <- Ns.i.bigDecimal_?.<(Some(bigDecimal3)).a1.query.get.map(_ ==> List((1, Some(bigDecimal1)), (2, Some(bigDecimal2))))
        _ <- Ns.i.date_?.<(Some(date3)).a1.query.get.map(_ ==> List((1, Some(date1)), (2, Some(date2))))
        _ <- Ns.i.uuid_?.<(Some(uuid3)).a1.query.get.map(_ ==> List((1, Some(uuid1)), (2, Some(uuid2))))
        _ <- Ns.i.uri_?.<(Some(uri3)).a1.query.get.map(_ ==> List((1, Some(uri1)), (2, Some(uri2))))
        _ <- Ns.i.byte_?.<(Some(byte3)).a1.query.get.map(_ ==> List((1, Some(byte1)), (2, Some(byte2))))
        _ <- Ns.i.short_?.<(Some(short3)).a1.query.get.map(_ ==> List((1, Some(short1)), (2, Some(short2))))
        _ <- Ns.i.char_?.<(Some(char3)).a1.query.get.map(_ ==> List((1, Some(char1)), (2, Some(char2))))
        _ <- Ns.i.ref_?.<(Some(ref3)).a1.query.get.map(_ ==> List((1, Some(ref1)), (2, Some(ref2))))

        // Descending
        _ <- Ns.i.string_?.<(Some(string3)).d1.query.get.map(_ ==> List((2, Some(string2)), (1, Some(string1))))
        _ <- Ns.i.int_?.<(Some(int3)).d1.query.get.map(_ ==> List((2, Some(int2)), (1, Some(int1))))
        _ <- Ns.i.long_?.<(Some(long3)).d1.query.get.map(_ ==> List((2, Some(long2)), (1, Some(long1))))
        _ <- Ns.i.float_?.<(Some(float3)).d1.query.get.map(_ ==> List((2, Some(float2)), (1, Some(float1))))
        _ <- Ns.i.double_?.<(Some(double3)).d1.query.get.map(_ ==> List((2, Some(double2)), (1, Some(double1))))
        _ <- Ns.i.boolean_?.<(Some(boolean3)).d1.query.get.map(_ ==> List())
        _ <- Ns.i.bigInt_?.<(Some(bigInt3)).d1.query.get.map(_ ==> List((2, Some(bigInt2)), (1, Some(bigInt1))))
        _ <- Ns.i.bigDecimal_?.<(Some(bigDecimal3)).d1.query.get.map(_ ==> List((2, Some(bigDecimal2)), (1, Some(bigDecimal1))))
        _ <- Ns.i.date_?.<(Some(date3)).d1.query.get.map(_ ==> List((2, Some(date2)), (1, Some(date1))))
        _ <- Ns.i.uuid_?.<(Some(uuid3)).d1.query.get.map(_ ==> List((2, Some(uuid2)), (1, Some(uuid1))))
        _ <- Ns.i.uri_?.<(Some(uri3)).d1.query.get.map(_ ==> List((2, Some(uri2)), (1, Some(uri1))))
        _ <- Ns.i.byte_?.<(Some(byte3)).d1.query.get.map(_ ==> List((2, Some(byte2)), (1, Some(byte1))))
        _ <- Ns.i.short_?.<(Some(short3)).d1.query.get.map(_ ==> List((2, Some(short2)), (1, Some(short1))))
        _ <- Ns.i.char_?.<(Some(char3)).d1.query.get.map(_ ==> List((2, Some(char2)), (1, Some(char1))))
        _ <- Ns.i.ref_?.<(Some(ref3)).d1.query.get.map(_ ==> List((2, Some(ref2)), (1, Some(ref1))))

        // None can't be compared and returns empty result, so no idea sorting
        _ <- Ns.i.a2.int_?.<(Option.empty[Int]).a1.query.get.map(_ ==> List())

        // Other comparisons behave similarly
        a = (1, Some(1))
        b = (2, Some(2))
        c = (3, Some(3))
        _ <- Ns.i.int_?.<=(Some(2)).a1.query.get.map(_ ==> List(a, b))
        _ <- Ns.i.int_?.<=(Some(2)).d1.query.get.map(_ ==> List(b, a))

        _ <- Ns.i.int_?.>(Some(1)).a1.query.get.map(_ ==> List(b, c))
        _ <- Ns.i.int_?.>(Some(1)).d1.query.get.map(_ ==> List(c, b))

        _ <- Ns.i.int_?.>=(Some(2)).a1.query.get.map(_ ==> List(b, c))
        _ <- Ns.i.int_?.>=(Some(2)).d1.query.get.map(_ ==> List(c, b))
      } yield ()
    }
  }
}
