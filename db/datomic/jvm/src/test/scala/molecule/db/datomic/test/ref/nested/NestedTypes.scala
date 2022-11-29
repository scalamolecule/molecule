package molecule.db.datomic.test.ref.nested

import molecule.coreTests.dataModels.core.dsl.Types._

import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object NestedTypes extends DatomicTestSuite {

  lazy val tests = Tests {

    "Card one, mandatory" - types { implicit conn =>
      Ref.i.Nss.*(Ns.string).insert(1, List(string1)).transact
      Ref.i.Nss.*(Ns.int).insert(2, List(int1)).transact
      Ref.i.Nss.*(Ns.long).insert(3, List(long1)).transact
      Ref.i.Nss.*(Ns.float).insert(4, List(float1)).transact
      Ref.i.Nss.*(Ns.double).insert(5, List(double1)).transact
      Ref.i.Nss.*(Ns.boolean).insert(6, List(boolean1)).transact
      Ref.i.Nss.*(Ns.bigInt).insert(7, List(bigInt1)).transact
      Ref.i.Nss.*(Ns.bigDecimal).insert(8, List(bigDecimal1)).transact
      Ref.i.Nss.*(Ns.date).insert(9, List(date1)).transact
      Ref.i.Nss.*(Ns.uuid).insert(10, List(uuid1)).transact
      Ref.i.Nss.*(Ns.uri).insert(11, List(uri1)).transact
      Ref.i.Nss.*(Ns.char).insert(12, List(char1)).transact
      Ref.i.Nss.*(Ns.byte).insert(13, List(byte1)).transact
      Ref.i.Nss.*(Ns.short).insert(14, List(short1)).transact

      Ref.i_.Nss.*(Ns.string).query.get ==> List(List(string1))
      Ref.i_.Nss.*(Ns.int).query.get ==> List(List(int1))
      Ref.i_.Nss.*(Ns.long).query.get ==> List(List(long1))
      Ref.i_.Nss.*(Ns.float).query.get ==> List(List(float1))
      Ref.i_.Nss.*(Ns.double).query.get ==> List(List(double1))
      Ref.i_.Nss.*(Ns.boolean).query.get ==> List(List(boolean1))
      Ref.i_.Nss.*(Ns.bigInt).query.get ==> List(List(bigInt1))
      Ref.i_.Nss.*(Ns.bigDecimal).query.get ==> List(List(bigDecimal1))
      Ref.i_.Nss.*(Ns.date).query.get ==> List(List(date1))
      Ref.i_.Nss.*(Ns.uuid).query.get ==> List(List(uuid1))
      Ref.i_.Nss.*(Ns.uri).query.get ==> List(List(uri1))
      Ref.i_.Nss.*(Ns.char).query.get ==> List(List(char1))
      Ref.i_.Nss.*(Ns.byte).query.get ==> List(List(byte1))
      Ref.i_.Nss.*(Ns.short).query.get ==> List(List(short1))

      Ref.i_(1).Nss.*?(Ns.string).query.get ==> List(List(string1))
      Ref.i_(2).Nss.*?(Ns.int).query.get ==> List(List(int1))
      Ref.i_(3).Nss.*?(Ns.long).query.get ==> List(List(long1))
      Ref.i_(4).Nss.*?(Ns.float).query.get ==> List(List(float1))
      Ref.i_(5).Nss.*?(Ns.double).query.get ==> List(List(double1))
      Ref.i_(6).Nss.*?(Ns.boolean).query.get ==> List(List(boolean1))
      Ref.i_(7).Nss.*?(Ns.bigInt).query.get ==> List(List(bigInt1))
      Ref.i_(8).Nss.*?(Ns.bigDecimal).query.get ==> List(List(bigDecimal1))
      Ref.i_(9).Nss.*?(Ns.date).query.get ==> List(List(date1))
      Ref.i_(10).Nss.*?(Ns.uuid).query.get ==> List(List(uuid1))
      Ref.i_(11).Nss.*?(Ns.uri).query.get ==> List(List(uri1))
      Ref.i_(12).Nss.*?(Ns.char).query.get ==> List(List(char1))
      Ref.i_(13).Nss.*?(Ns.byte).query.get ==> List(List(byte1))
      Ref.i_(14).Nss.*?(Ns.short).query.get ==> List(List(short1))
    }


    "Card set" - types { implicit conn =>
      Ref.i.Nss.*(Ns.strings).insert(1, List(Set(string1, string2))).transact
      Ref.i.Nss.*(Ns.ints).insert(2, List(Set(int1, int2))).transact
      Ref.i.Nss.*(Ns.longs).insert(3, List(Set(long1, long2))).transact
      Ref.i.Nss.*(Ns.floats).insert(4, List(Set(float1, float2))).transact
      Ref.i.Nss.*(Ns.doubles).insert(5, List(Set(double1, double2))).transact
      Ref.i.Nss.*(Ns.booleans).insert(6, List(Set(boolean1, boolean2))).transact
      Ref.i.Nss.*(Ns.bigInts).insert(7, List(Set(bigInt1, bigInt2))).transact
      Ref.i.Nss.*(Ns.bigDecimals).insert(8, List(Set(bigDecimal1, bigDecimal2))).transact
      Ref.i.Nss.*(Ns.dates).insert(9, List(Set(date1, date2))).transact
      Ref.i.Nss.*(Ns.uuids).insert(10, List(Set(uuid1, uuid2))).transact
      Ref.i.Nss.*(Ns.uris).insert(11, List(Set(uri1, uri2))).transact
      Ref.i.Nss.*(Ns.chars).insert(12, List(Set(char1, char2))).transact
      Ref.i.Nss.*(Ns.bytes).insert(13, List(Set(byte1, byte2))).transact
      Ref.i.Nss.*(Ns.shorts).insert(14, List(Set(short1, short2))).transact


      Ref.i_.Nss.*(Ns.strings).query.get ==> List(List(Set(string1, string2)))
      Ref.i_.Nss.*(Ns.ints).query.get ==> List(List(Set(int1, int2)))
      Ref.i_.Nss.*(Ns.longs).query.get ==> List(List(Set(long1, long2)))
      Ref.i_.Nss.*(Ns.floats).query.get ==> List(List(Set(float1, float2)))
      Ref.i_.Nss.*(Ns.doubles).query.get ==> List(List(Set(double1, double2)))
      Ref.i_.Nss.*(Ns.booleans).query.get ==> List(List(Set(boolean1, boolean2)))
      Ref.i_.Nss.*(Ns.bigInts).query.get ==> List(List(Set(bigInt1, bigInt2)))
      Ref.i_.Nss.*(Ns.bigDecimals).query.get ==> List(List(Set(bigDecimal1, bigDecimal2)))
      Ref.i_.Nss.*(Ns.dates).query.get ==> List(List(Set(date1, date2)))
      Ref.i_.Nss.*(Ns.uuids).query.get ==> List(List(Set(uuid1, uuid2)))
      Ref.i_.Nss.*(Ns.uris).query.get ==> List(List(Set(uri1, uri2)))
      Ref.i_.Nss.*(Ns.chars).query.get ==> List(List(Set(char1, char2)))
      Ref.i_.Nss.*(Ns.bytes).query.get ==> List(List(Set(byte1, byte2)))
      Ref.i_.Nss.*(Ns.shorts).query.get ==> List(List(Set(short1, short2)))


      Ref.i_(1).Nss.*?(Ns.strings).query.get ==> List(List(Set(string1, string2)))
      Ref.i_(2).Nss.*?(Ns.ints).query.get ==> List(List(Set(int1, int2)))
      Ref.i_(3).Nss.*?(Ns.longs).query.get ==> List(List(Set(long1, long2)))
      Ref.i_(4).Nss.*?(Ns.floats).query.get ==> List(List(Set(float1, float2)))
      Ref.i_(5).Nss.*?(Ns.doubles).query.get ==> List(List(Set(double1, double2)))
      if (useFree)
        Ref.i_(6).Nss.*?(Ns.booleans).query.get ==> List(List(Set(boolean1))) // * Bug in Datomic Free
      else
        Ref.i_(6).Nss.*?(Ns.booleans).query.get ==> List(List(Set(boolean2, boolean1)))
      Ref.i_(7).Nss.*?(Ns.bigInts).query.get ==> List(List(Set(bigInt1, bigInt2)))
      Ref.i_(8).Nss.*?(Ns.bigDecimals).query.get ==> List(List(Set(bigDecimal1, bigDecimal2)))
      Ref.i_(9).Nss.*?(Ns.dates).query.get ==> List(List(Set(date1, date2)))
      Ref.i_(10).Nss.*?(Ns.uuids).query.get ==> List(List(Set(uuid1, uuid2)))
      Ref.i_(11).Nss.*?(Ns.uris).query.get ==> List(List(Set(uri1, uri2)))
      Ref.i_(12).Nss.*?(Ns.chars).query.get ==> List(List(Set(char1, char2)))
      Ref.i_(13).Nss.*?(Ns.bytes).query.get ==> List(List(Set(byte1, byte2)))
      Ref.i_(14).Nss.*?(Ns.shorts).query.get ==> List(List(Set(short1, short2)))
    }


    "Card one, optional" - types { implicit conn =>
      Ref.i.Nss.*(Ns.i.string_?).insert(1, List((1, Some(string1)), (2, None))).transact
      Ref.i.Nss.*(Ns.i.int_?).insert(2, List((1, Some(int1)), (2, None))).transact
      Ref.i.Nss.*(Ns.i.long_?).insert(3, List((1, Some(long1)), (2, None))).transact
      Ref.i.Nss.*(Ns.i.float_?).insert(4, List((1, Some(float1)), (2, None))).transact
      Ref.i.Nss.*(Ns.i.double_?).insert(5, List((1, Some(double1)), (2, None))).transact
      Ref.i.Nss.*(Ns.i.boolean_?).insert(6, List((1, Some(boolean1)), (2, None))).transact
      Ref.i.Nss.*(Ns.i.bigInt_?).insert(7, List((1, Some(bigInt1)), (2, None))).transact
      Ref.i.Nss.*(Ns.i.bigDecimal_?).insert(8, List((1, Some(bigDecimal1)), (2, None))).transact
      Ref.i.Nss.*(Ns.i.date_?).insert(9, List((1, Some(date1)), (2, None))).transact
      Ref.i.Nss.*(Ns.i.uuid_?).insert(10, List((1, Some(uuid1)), (2, None))).transact
      Ref.i.Nss.*(Ns.i.uri_?).insert(11, List((1, Some(uri1)), (2, None))).transact
      Ref.i.Nss.*(Ns.i.char_?).insert(12, List((1, Some(char1)), (2, None))).transact
      Ref.i.Nss.*(Ns.i.byte_?).insert(13, List((1, Some(byte1)), (2, None))).transact
      Ref.i.Nss.*(Ns.i.short_?).insert(14, List((1, Some(short1)), (2, None))).transact

      Ref.i(1).Nss.*(Ns.i.a1.string_?).query.get ==> List((1, List((1, Some(string1)), (2, None))))
      Ref.i(2).Nss.*(Ns.i.a1.int_?).query.get ==> List((2, List((1, Some(int1)), (2, None))))
      Ref.i(3).Nss.*(Ns.i.a1.long_?).query.get ==> List((3, List((1, Some(long1)), (2, None))))
      Ref.i(4).Nss.*(Ns.i.a1.float_?).query.get ==> List((4, List((1, Some(float1)), (2, None))))
      Ref.i(5).Nss.*(Ns.i.a1.double_?).query.get ==> List((5, List((1, Some(double1)), (2, None))))
      Ref.i(6).Nss.*(Ns.i.a1.boolean_?).query.get ==> List((6, List((1, Some(boolean1)), (2, None))))
      Ref.i(7).Nss.*(Ns.i.a1.bigInt_?).query.get ==> List((7, List((1, Some(bigInt1)), (2, None))))
      Ref.i(8).Nss.*(Ns.i.a1.bigDecimal_?).query.get ==> List((8, List((1, Some(bigDecimal1)), (2, None))))
      Ref.i(9).Nss.*(Ns.i.a1.date_?).query.get ==> List((9, List((1, Some(date1)), (2, None))))
      Ref.i(10).Nss.*(Ns.i.a1.uuid_?).query.get ==> List((10, List((1, Some(uuid1)), (2, None))))
      Ref.i(11).Nss.*(Ns.i.a1.uri_?).query.get ==> List((11, List((1, Some(uri1)), (2, None))))
      Ref.i(12).Nss.*(Ns.i.a1.char_?).query.get ==> List((12, List((1, Some(char1)), (2, None))))
      Ref.i(13).Nss.*(Ns.i.a1.byte_?).query.get ==> List((13, List((1, Some(byte1)), (2, None))))
      Ref.i(14).Nss.*(Ns.i.a1.short_?).query.get ==> List((14, List((1, Some(short1)), (2, None))))

      Ref.i(1).Nss.*?(Ns.i.a1.string_?).query.get ==> List((1, List((1, Some(string1)), (2, None))))
      Ref.i(2).Nss.*?(Ns.i.a1.int_?).query.get ==> List((2, List((1, Some(int1)), (2, None))))
      Ref.i(3).Nss.*?(Ns.i.a1.long_?).query.get ==> List((3, List((1, Some(long1)), (2, None))))
      Ref.i(4).Nss.*?(Ns.i.a1.float_?).query.get ==> List((4, List((1, Some(float1)), (2, None))))
      Ref.i(5).Nss.*?(Ns.i.a1.double_?).query.get ==> List((5, List((1, Some(double1)), (2, None))))
      Ref.i(6).Nss.*?(Ns.i.a1.boolean_?).query.get ==> List((6, List((1, Some(boolean1)), (2, None))))
      Ref.i(7).Nss.*?(Ns.i.a1.bigInt_?).query.get ==> List((7, List((1, Some(bigInt1)), (2, None))))
      Ref.i(8).Nss.*?(Ns.i.a1.bigDecimal_?).query.get ==> List((8, List((1, Some(bigDecimal1)), (2, None))))
      Ref.i(9).Nss.*?(Ns.i.a1.date_?).query.get ==> List((9, List((1, Some(date1)), (2, None))))
      Ref.i(10).Nss.*?(Ns.i.a1.uuid_?).query.get ==> List((10, List((1, Some(uuid1)), (2, None))))
      Ref.i(11).Nss.*?(Ns.i.a1.uri_?).query.get ==> List((11, List((1, Some(uri1)), (2, None))))
      Ref.i(12).Nss.*?(Ns.i.a1.char_?).query.get ==> List((12, List((1, Some(char1)), (2, None))))
      Ref.i(13).Nss.*?(Ns.i.a1.byte_?).query.get ==> List((13, List((1, Some(byte1)), (2, None))))
      Ref.i(14).Nss.*?(Ns.i.a1.short_?).query.get ==> List((14, List((1, Some(short1)), (2, None))))
    }


    "Card set, optional" - types { implicit conn =>
      Ref.i.Nss.*(Ns.i.strings_?).insert(1, List((1, Some(Set(string1, string2))), (2, None))).transact
      Ref.i.Nss.*(Ns.i.ints_?).insert(2, List((1, Some(Set(int1, int2))), (2, None))).transact
      Ref.i.Nss.*(Ns.i.longs_?).insert(3, List((1, Some(Set(long1, long2))), (2, None))).transact
      Ref.i.Nss.*(Ns.i.floats_?).insert(4, List((1, Some(Set(float1, float2))), (2, None))).transact
      Ref.i.Nss.*(Ns.i.doubles_?).insert(5, List((1, Some(Set(double1, double2))), (2, None))).transact
      Ref.i.Nss.*(Ns.i.booleans_?).insert(6, List((1, Some(Set(boolean1, boolean2))), (2, None))).transact
      Ref.i.Nss.*(Ns.i.bigInts_?).insert(7, List((1, Some(Set(bigInt1, bigInt2))), (2, None))).transact
      Ref.i.Nss.*(Ns.i.bigDecimals_?).insert(8, List((1, Some(Set(bigDecimal1, bigDecimal2))), (2, None))).transact
      Ref.i.Nss.*(Ns.i.dates_?).insert(9, List((1, Some(Set(date1, date2))), (2, None))).transact
      Ref.i.Nss.*(Ns.i.uuids_?).insert(10, List((1, Some(Set(uuid1, uuid2))), (2, None))).transact
      Ref.i.Nss.*(Ns.i.uris_?).insert(11, List((1, Some(Set(uri1, uri2))), (2, None))).transact
      Ref.i.Nss.*(Ns.i.chars_?).insert(12, List((1, Some(Set(char1, char2))), (2, None))).transact
      Ref.i.Nss.*(Ns.i.bytes_?).insert(13, List((1, Some(Set(byte1, byte2))), (2, None))).transact
      Ref.i.Nss.*(Ns.i.shorts_?).insert(14, List((1, Some(Set(short1, short2))), (2, None))).transact


      Ref.i(1).Nss.*(Ns.i.a1.strings_?).query.get ==> List((1, List((1, Some(Set(string1, string2))), (2, None))))
      Ref.i(2).Nss.*(Ns.i.a1.ints_?).query.get ==> List((2, List((1, Some(Set(int1, int2))), (2, None))))
      Ref.i(3).Nss.*(Ns.i.a1.longs_?).query.get ==> List((3, List((1, Some(Set(long1, long2))), (2, None))))
      Ref.i(4).Nss.*(Ns.i.a1.floats_?).query.get ==> List((4, List((1, Some(Set(float1, float2))), (2, None))))
      Ref.i(5).Nss.*(Ns.i.a1.doubles_?).query.get ==> List((5, List((1, Some(Set(double1, double2))), (2, None))))
      if (useFree)
        Ref.i(6).Nss.*(Ns.i.a1.booleans_?).query.get ==> List((6, List((1, Some(Set(boolean1))), (2, None)))) // * Bug in Datomic Free
      else
        Ref.i(6).Nss.*(Ns.i.a1.booleans_?).query.get ==> List((6, List((1, Some(Set(boolean2, boolean1))), (2, None))))
      Ref.i(7).Nss.*(Ns.i.a1.bigInts_?).query.get ==> List((7, List((1, Some(Set(bigInt1, bigInt2))), (2, None))))
      Ref.i(8).Nss.*(Ns.i.a1.bigDecimals_?).query.get ==> List((8, List((1, Some(Set(bigDecimal1, bigDecimal2))), (2, None))))
      Ref.i(9).Nss.*(Ns.i.a1.dates_?).query.get ==> List((9, List((1, Some(Set(date1, date2))), (2, None))))
      Ref.i(10).Nss.*(Ns.i.a1.uuids_?).query.get ==> List((10, List((1, Some(Set(uuid1, uuid2))), (2, None))))
      Ref.i(11).Nss.*(Ns.i.a1.uris_?).query.get ==> List((11, List((1, Some(Set(uri1, uri2))), (2, None))))
      Ref.i(12).Nss.*(Ns.i.a1.chars_?).query.get ==> List((12, List((1, Some(Set(char1, char2))), (2, None))))
      Ref.i(13).Nss.*(Ns.i.a1.bytes_?).query.get ==> List((13, List((1, Some(Set(byte1, byte2))), (2, None))))
      Ref.i(14).Nss.*(Ns.i.a1.shorts_?).query.get ==> List((14, List((1, Some(Set(short1, short2))), (2, None))))


      Ref.i(1).Nss.*?(Ns.i.a1.strings_?).query.get ==> List((1, List((1, Some(Set(string1, string2))), (2, None))))
      Ref.i(2).Nss.*?(Ns.i.a1.ints_?).query.get ==> List((2, List((1, Some(Set(int1, int2))), (2, None))))
      Ref.i(3).Nss.*?(Ns.i.a1.longs_?).query.get ==> List((3, List((1, Some(Set(long1, long2))), (2, None))))
      Ref.i(4).Nss.*?(Ns.i.a1.floats_?).query.get ==> List((4, List((1, Some(Set(float1, float2))), (2, None))))
      Ref.i(5).Nss.*?(Ns.i.a1.doubles_?).query.get ==> List((5, List((1, Some(Set(double1, double2))), (2, None))))
      if (useFree)
        Ref.i(6).Nss.*?(Ns.i.a1.booleans_?).query.get ==> List((6, List((1, Some(Set(boolean1))), (2, None)))) // * Bug in Datomic Free
      else
        Ref.i(6).Nss.*?(Ns.i.a1.booleans_?).query.get ==> List((6, List((1, Some(Set(boolean2, boolean1))), (2, None))))
      Ref.i(7).Nss.*?(Ns.i.a1.bigInts_?).query.get ==> List((7, List((1, Some(Set(bigInt1, bigInt2))), (2, None))))
      Ref.i(8).Nss.*?(Ns.i.a1.bigDecimals_?).query.get ==> List((8, List((1, Some(Set(bigDecimal1, bigDecimal2))), (2, None))))
      Ref.i(9).Nss.*?(Ns.i.a1.dates_?).query.get ==> List((9, List((1, Some(Set(date1, date2))), (2, None))))
      Ref.i(10).Nss.*?(Ns.i.a1.uuids_?).query.get ==> List((10, List((1, Some(Set(uuid1, uuid2))), (2, None))))
      Ref.i(11).Nss.*?(Ns.i.a1.uris_?).query.get ==> List((11, List((1, Some(Set(uri1, uri2))), (2, None))))
      Ref.i(12).Nss.*?(Ns.i.a1.chars_?).query.get ==> List((12, List((1, Some(Set(char1, char2))), (2, None))))
      Ref.i(13).Nss.*?(Ns.i.a1.bytes_?).query.get ==> List((13, List((1, Some(Set(byte1, byte2))), (2, None))))
      Ref.i(14).Nss.*?(Ns.i.a1.shorts_?).query.get ==> List((14, List((1, Some(Set(short1, short2))), (2, None))))
    }

    // * Bug in Datomic Free where card-many set of Boolean values only preserves true. Datomic Pro works correctly
  }
}
