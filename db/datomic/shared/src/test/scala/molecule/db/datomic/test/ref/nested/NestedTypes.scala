package molecule.db.datomic.test.ref.nested

import molecule.coreTests.dataModels.core.dsl.Types._

import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object NestedTypes extends DatomicTestSuite {

  lazy val tests = Tests {

    "Card one, mandatory" - types { implicit conn =>
      Ref.n.Nss.*(Ns.string).insert(1, List(string1)).transact
      Ref.n.Nss.*(Ns.int).insert(2, List(int1)).transact
      Ref.n.Nss.*(Ns.long).insert(3, List(long1)).transact
      Ref.n.Nss.*(Ns.float).insert(4, List(float1)).transact
      Ref.n.Nss.*(Ns.double).insert(5, List(double1)).transact
      Ref.n.Nss.*(Ns.boolean).insert(6, List(boolean1)).transact
      Ref.n.Nss.*(Ns.bigInt).insert(7, List(bigInt1)).transact
      Ref.n.Nss.*(Ns.bigDecimal).insert(8, List(bigDecimal1)).transact
      Ref.n.Nss.*(Ns.date).insert(9, List(date1)).transact
      Ref.n.Nss.*(Ns.uuid).insert(10, List(uuid1)).transact
      Ref.n.Nss.*(Ns.uri).insert(11, List(uri1)).transact
      Ref.n.Nss.*(Ns.char).insert(12, List(char1)).transact
      Ref.n.Nss.*(Ns.byte).insert(13, List(byte1)).transact
      Ref.n.Nss.*(Ns.short).insert(14, List(short1)).transact

      Ref.n_.Nss.*(Ns.string).query.get ==> List(List(string1))
      Ref.n_.Nss.*(Ns.int).query.get ==> List(List(int1))
      Ref.n_.Nss.*(Ns.long).query.get ==> List(List(long1))
      Ref.n_.Nss.*(Ns.float).query.get ==> List(List(float1))
      Ref.n_.Nss.*(Ns.double).query.get ==> List(List(double1))
      Ref.n_.Nss.*(Ns.boolean).query.get ==> List(List(boolean1))
      Ref.n_.Nss.*(Ns.bigInt).query.get ==> List(List(bigInt1))
      Ref.n_.Nss.*(Ns.bigDecimal).query.get ==> List(List(bigDecimal1))
      Ref.n_.Nss.*(Ns.date).query.get ==> List(List(date1))
      Ref.n_.Nss.*(Ns.uuid).query.get ==> List(List(uuid1))
      Ref.n_.Nss.*(Ns.uri).query.get ==> List(List(uri1))
      Ref.n_.Nss.*(Ns.char).query.get ==> List(List(char1))
      Ref.n_.Nss.*(Ns.byte).query.get ==> List(List(byte1))
      Ref.n_.Nss.*(Ns.short).query.get ==> List(List(short1))

      Ref.n_(1).Nss.*?(Ns.string).query.get ==> List(List(string1))
      Ref.n_(2).Nss.*?(Ns.int).query.get ==> List(List(int1))
      Ref.n_(3).Nss.*?(Ns.long).query.get ==> List(List(long1))
      Ref.n_(4).Nss.*?(Ns.float).query.get ==> List(List(float1))
      Ref.n_(5).Nss.*?(Ns.double).query.get ==> List(List(double1))
      Ref.n_(6).Nss.*?(Ns.boolean).query.get ==> List(List(boolean1))
      Ref.n_(7).Nss.*?(Ns.bigInt).query.get ==> List(List(bigInt1))
      Ref.n_(8).Nss.*?(Ns.bigDecimal).query.get ==> List(List(bigDecimal1))
      Ref.n_(9).Nss.*?(Ns.date).query.get ==> List(List(date1))
      Ref.n_(10).Nss.*?(Ns.uuid).query.get ==> List(List(uuid1))
      Ref.n_(11).Nss.*?(Ns.uri).query.get ==> List(List(uri1))
      Ref.n_(12).Nss.*?(Ns.char).query.get ==> List(List(char1))
      Ref.n_(13).Nss.*?(Ns.byte).query.get ==> List(List(byte1))
      Ref.n_(14).Nss.*?(Ns.short).query.get ==> List(List(short1))
    }


    "Card set" - types { implicit conn =>
      Ref.n.Nss.*(Ns.strings).insert(1, List(Set(string1, string2))).transact
      Ref.n.Nss.*(Ns.ints).insert(2, List(Set(int1, int2))).transact
      Ref.n.Nss.*(Ns.longs).insert(3, List(Set(long1, long2))).transact
      Ref.n.Nss.*(Ns.floats).insert(4, List(Set(float1, float2))).transact
      Ref.n.Nss.*(Ns.doubles).insert(5, List(Set(double1, double2))).transact
      Ref.n.Nss.*(Ns.booleans).insert(6, List(Set(boolean1, boolean2))).transact
      Ref.n.Nss.*(Ns.bigInts).insert(7, List(Set(bigInt1, bigInt2))).transact
      Ref.n.Nss.*(Ns.bigDecimals).insert(8, List(Set(bigDecimal1, bigDecimal2))).transact
      Ref.n.Nss.*(Ns.dates).insert(9, List(Set(date1, date2))).transact
      Ref.n.Nss.*(Ns.uuids).insert(10, List(Set(uuid1, uuid2))).transact
      Ref.n.Nss.*(Ns.uris).insert(11, List(Set(uri1, uri2))).transact
      Ref.n.Nss.*(Ns.chars).insert(12, List(Set(char1, char2))).transact
      Ref.n.Nss.*(Ns.bytes).insert(13, List(Set(byte1, byte2))).transact
      Ref.n.Nss.*(Ns.shorts).insert(14, List(Set(short1, short2))).transact


      Ref.n_.Nss.*(Ns.strings).query.get ==> List(List(Set(string1, string2)))
      Ref.n_.Nss.*(Ns.ints).query.get ==> List(List(Set(int1, int2)))
      Ref.n_.Nss.*(Ns.longs).query.get ==> List(List(Set(long1, long2)))
      Ref.n_.Nss.*(Ns.floats).query.get ==> List(List(Set(float1, float2)))
      Ref.n_.Nss.*(Ns.doubles).query.get ==> List(List(Set(double1, double2)))
      Ref.n_.Nss.*(Ns.booleans).query.get ==> List(List(Set(boolean1, boolean2)))
      Ref.n_.Nss.*(Ns.bigInts).query.get ==> List(List(Set(bigInt1, bigInt2)))
      Ref.n_.Nss.*(Ns.bigDecimals).query.get ==> List(List(Set(bigDecimal1, bigDecimal2)))
      Ref.n_.Nss.*(Ns.dates).query.get ==> List(List(Set(date1, date2)))
      Ref.n_.Nss.*(Ns.uuids).query.get ==> List(List(Set(uuid1, uuid2)))
      Ref.n_.Nss.*(Ns.uris).query.get ==> List(List(Set(uri1, uri2)))
      Ref.n_.Nss.*(Ns.chars).query.get ==> List(List(Set(char1, char2)))
      Ref.n_.Nss.*(Ns.bytes).query.get ==> List(List(Set(byte1, byte2)))
      Ref.n_.Nss.*(Ns.shorts).query.get ==> List(List(Set(short1, short2)))


      Ref.n_(1).Nss.*?(Ns.strings).query.get ==> List(List(Set(string1, string2)))
      Ref.n_(2).Nss.*?(Ns.ints).query.get ==> List(List(Set(int1, int2)))
      Ref.n_(3).Nss.*?(Ns.longs).query.get ==> List(List(Set(long1, long2)))
      Ref.n_(4).Nss.*?(Ns.floats).query.get ==> List(List(Set(float1, float2)))
      Ref.n_(5).Nss.*?(Ns.doubles).query.get ==> List(List(Set(double1, double2)))
      if (useFree)
        Ref.n_(6).Nss.*?(Ns.booleans).query.get ==> List(List(Set(boolean1))) // * Bug in Datomic Free
      else
        Ref.n_(6).Nss.*?(Ns.booleans).query.get ==> List(List(Set(boolean2, boolean1)))
      Ref.n_(7).Nss.*?(Ns.bigInts).query.get ==> List(List(Set(bigInt1, bigInt2)))
      Ref.n_(8).Nss.*?(Ns.bigDecimals).query.get ==> List(List(Set(bigDecimal1, bigDecimal2)))
      Ref.n_(9).Nss.*?(Ns.dates).query.get ==> List(List(Set(date1, date2)))
      Ref.n_(10).Nss.*?(Ns.uuids).query.get ==> List(List(Set(uuid1, uuid2)))
      Ref.n_(11).Nss.*?(Ns.uris).query.get ==> List(List(Set(uri1, uri2)))
      Ref.n_(12).Nss.*?(Ns.chars).query.get ==> List(List(Set(char1, char2)))
      Ref.n_(13).Nss.*?(Ns.bytes).query.get ==> List(List(Set(byte1, byte2)))
      Ref.n_(14).Nss.*?(Ns.shorts).query.get ==> List(List(Set(short1, short2)))
    }


    "Card one, optional" - types { implicit conn =>
      Ref.n.Nss.*(Ns.n.string_?).insert(1, List((1, Some(string1)), (2, None))).transact
      Ref.n.Nss.*(Ns.n.int_?).insert(2, List((1, Some(int1)), (2, None))).transact
      Ref.n.Nss.*(Ns.n.long_?).insert(3, List((1, Some(long1)), (2, None))).transact
      Ref.n.Nss.*(Ns.n.float_?).insert(4, List((1, Some(float1)), (2, None))).transact
      Ref.n.Nss.*(Ns.n.double_?).insert(5, List((1, Some(double1)), (2, None))).transact
      Ref.n.Nss.*(Ns.n.boolean_?).insert(6, List((1, Some(boolean1)), (2, None))).transact
      Ref.n.Nss.*(Ns.n.bigInt_?).insert(7, List((1, Some(bigInt1)), (2, None))).transact
      Ref.n.Nss.*(Ns.n.bigDecimal_?).insert(8, List((1, Some(bigDecimal1)), (2, None))).transact
      Ref.n.Nss.*(Ns.n.date_?).insert(9, List((1, Some(date1)), (2, None))).transact
      Ref.n.Nss.*(Ns.n.uuid_?).insert(10, List((1, Some(uuid1)), (2, None))).transact
      Ref.n.Nss.*(Ns.n.uri_?).insert(11, List((1, Some(uri1)), (2, None))).transact
      Ref.n.Nss.*(Ns.n.char_?).insert(12, List((1, Some(char1)), (2, None))).transact
      Ref.n.Nss.*(Ns.n.byte_?).insert(13, List((1, Some(byte1)), (2, None))).transact
      Ref.n.Nss.*(Ns.n.short_?).insert(14, List((1, Some(short1)), (2, None))).transact

      Ref.n(1).Nss.*(Ns.n.a1.string_?).query.get ==> List((1, List((1, Some(string1)), (2, None))))
      Ref.n(2).Nss.*(Ns.n.a1.int_?).query.get ==> List((2, List((1, Some(int1)), (2, None))))
      Ref.n(3).Nss.*(Ns.n.a1.long_?).query.get ==> List((3, List((1, Some(long1)), (2, None))))
      Ref.n(4).Nss.*(Ns.n.a1.float_?).query.get ==> List((4, List((1, Some(float1)), (2, None))))
      Ref.n(5).Nss.*(Ns.n.a1.double_?).query.get ==> List((5, List((1, Some(double1)), (2, None))))
      Ref.n(6).Nss.*(Ns.n.a1.boolean_?).query.get ==> List((6, List((1, Some(boolean1)), (2, None))))
      Ref.n(7).Nss.*(Ns.n.a1.bigInt_?).query.get ==> List((7, List((1, Some(bigInt1)), (2, None))))
      Ref.n(8).Nss.*(Ns.n.a1.bigDecimal_?).query.get ==> List((8, List((1, Some(bigDecimal1)), (2, None))))
      Ref.n(9).Nss.*(Ns.n.a1.date_?).query.get ==> List((9, List((1, Some(date1)), (2, None))))
      Ref.n(10).Nss.*(Ns.n.a1.uuid_?).query.get ==> List((10, List((1, Some(uuid1)), (2, None))))
      Ref.n(11).Nss.*(Ns.n.a1.uri_?).query.get ==> List((11, List((1, Some(uri1)), (2, None))))
      Ref.n(12).Nss.*(Ns.n.a1.char_?).query.get ==> List((12, List((1, Some(char1)), (2, None))))
      Ref.n(13).Nss.*(Ns.n.a1.byte_?).query.get ==> List((13, List((1, Some(byte1)), (2, None))))
      Ref.n(14).Nss.*(Ns.n.a1.short_?).query.get ==> List((14, List((1, Some(short1)), (2, None))))

      Ref.n(1).Nss.*?(Ns.n.a1.string_?).query.get ==> List((1, List((1, Some(string1)), (2, None))))
      Ref.n(2).Nss.*?(Ns.n.a1.int_?).query.get ==> List((2, List((1, Some(int1)), (2, None))))
      Ref.n(3).Nss.*?(Ns.n.a1.long_?).query.get ==> List((3, List((1, Some(long1)), (2, None))))
      Ref.n(4).Nss.*?(Ns.n.a1.float_?).query.get ==> List((4, List((1, Some(float1)), (2, None))))
      Ref.n(5).Nss.*?(Ns.n.a1.double_?).query.get ==> List((5, List((1, Some(double1)), (2, None))))
      Ref.n(6).Nss.*?(Ns.n.a1.boolean_?).query.get ==> List((6, List((1, Some(boolean1)), (2, None))))
      Ref.n(7).Nss.*?(Ns.n.a1.bigInt_?).query.get ==> List((7, List((1, Some(bigInt1)), (2, None))))
      Ref.n(8).Nss.*?(Ns.n.a1.bigDecimal_?).query.get ==> List((8, List((1, Some(bigDecimal1)), (2, None))))
      Ref.n(9).Nss.*?(Ns.n.a1.date_?).query.get ==> List((9, List((1, Some(date1)), (2, None))))
      Ref.n(10).Nss.*?(Ns.n.a1.uuid_?).query.get ==> List((10, List((1, Some(uuid1)), (2, None))))
      Ref.n(11).Nss.*?(Ns.n.a1.uri_?).query.get ==> List((11, List((1, Some(uri1)), (2, None))))
      Ref.n(12).Nss.*?(Ns.n.a1.char_?).query.get ==> List((12, List((1, Some(char1)), (2, None))))
      Ref.n(13).Nss.*?(Ns.n.a1.byte_?).query.get ==> List((13, List((1, Some(byte1)), (2, None))))
      Ref.n(14).Nss.*?(Ns.n.a1.short_?).query.get ==> List((14, List((1, Some(short1)), (2, None))))
    }


    "Card set, optional" - types { implicit conn =>
      Ref.n.Nss.*(Ns.n.strings_?).insert(1, List((1, Some(Set(string1, string2))), (2, None))).transact
      Ref.n.Nss.*(Ns.n.ints_?).insert(2, List((1, Some(Set(int1, int2))), (2, None))).transact
      Ref.n.Nss.*(Ns.n.longs_?).insert(3, List((1, Some(Set(long1, long2))), (2, None))).transact
      Ref.n.Nss.*(Ns.n.floats_?).insert(4, List((1, Some(Set(float1, float2))), (2, None))).transact
      Ref.n.Nss.*(Ns.n.doubles_?).insert(5, List((1, Some(Set(double1, double2))), (2, None))).transact
      Ref.n.Nss.*(Ns.n.booleans_?).insert(6, List((1, Some(Set(boolean1, boolean2))), (2, None))).transact
      Ref.n.Nss.*(Ns.n.bigInts_?).insert(7, List((1, Some(Set(bigInt1, bigInt2))), (2, None))).transact
      Ref.n.Nss.*(Ns.n.bigDecimals_?).insert(8, List((1, Some(Set(bigDecimal1, bigDecimal2))), (2, None))).transact
      Ref.n.Nss.*(Ns.n.dates_?).insert(9, List((1, Some(Set(date1, date2))), (2, None))).transact
      Ref.n.Nss.*(Ns.n.uuids_?).insert(10, List((1, Some(Set(uuid1, uuid2))), (2, None))).transact
      Ref.n.Nss.*(Ns.n.uris_?).insert(11, List((1, Some(Set(uri1, uri2))), (2, None))).transact
      Ref.n.Nss.*(Ns.n.chars_?).insert(12, List((1, Some(Set(char1, char2))), (2, None))).transact
      Ref.n.Nss.*(Ns.n.bytes_?).insert(13, List((1, Some(Set(byte1, byte2))), (2, None))).transact
      Ref.n.Nss.*(Ns.n.shorts_?).insert(14, List((1, Some(Set(short1, short2))), (2, None))).transact


      Ref.n(1).Nss.*(Ns.n.a1.strings_?).query.get ==> List((1, List((1, Some(Set(string1, string2))), (2, None))))
      Ref.n(2).Nss.*(Ns.n.a1.ints_?).query.get ==> List((2, List((1, Some(Set(int1, int2))), (2, None))))
      Ref.n(3).Nss.*(Ns.n.a1.longs_?).query.get ==> List((3, List((1, Some(Set(long1, long2))), (2, None))))
      Ref.n(4).Nss.*(Ns.n.a1.floats_?).query.get ==> List((4, List((1, Some(Set(float1, float2))), (2, None))))
      Ref.n(5).Nss.*(Ns.n.a1.doubles_?).query.get ==> List((5, List((1, Some(Set(double1, double2))), (2, None))))
      if (useFree)
        Ref.n(6).Nss.*(Ns.n.a1.booleans_?).query.get ==> List((6, List((1, Some(Set(boolean1))), (2, None)))) // * Bug in Datomic Free
      else
        Ref.n(6).Nss.*(Ns.n.a1.booleans_?).query.get ==> List((6, List((1, Some(Set(boolean2, boolean1))), (2, None))))
      Ref.n(7).Nss.*(Ns.n.a1.bigInts_?).query.get ==> List((7, List((1, Some(Set(bigInt1, bigInt2))), (2, None))))
      Ref.n(8).Nss.*(Ns.n.a1.bigDecimals_?).query.get ==> List((8, List((1, Some(Set(bigDecimal1, bigDecimal2))), (2, None))))
      Ref.n(9).Nss.*(Ns.n.a1.dates_?).query.get ==> List((9, List((1, Some(Set(date1, date2))), (2, None))))
      Ref.n(10).Nss.*(Ns.n.a1.uuids_?).query.get ==> List((10, List((1, Some(Set(uuid1, uuid2))), (2, None))))
      Ref.n(11).Nss.*(Ns.n.a1.uris_?).query.get ==> List((11, List((1, Some(Set(uri1, uri2))), (2, None))))
      Ref.n(12).Nss.*(Ns.n.a1.chars_?).query.get ==> List((12, List((1, Some(Set(char1, char2))), (2, None))))
      Ref.n(13).Nss.*(Ns.n.a1.bytes_?).query.get ==> List((13, List((1, Some(Set(byte1, byte2))), (2, None))))
      Ref.n(14).Nss.*(Ns.n.a1.shorts_?).query.get ==> List((14, List((1, Some(Set(short1, short2))), (2, None))))


      Ref.n(1).Nss.*?(Ns.n.a1.strings_?).query.get ==> List((1, List((1, Some(Set(string1, string2))), (2, None))))
      Ref.n(2).Nss.*?(Ns.n.a1.ints_?).query.get ==> List((2, List((1, Some(Set(int1, int2))), (2, None))))
      Ref.n(3).Nss.*?(Ns.n.a1.longs_?).query.get ==> List((3, List((1, Some(Set(long1, long2))), (2, None))))
      Ref.n(4).Nss.*?(Ns.n.a1.floats_?).query.get ==> List((4, List((1, Some(Set(float1, float2))), (2, None))))
      Ref.n(5).Nss.*?(Ns.n.a1.doubles_?).query.get ==> List((5, List((1, Some(Set(double1, double2))), (2, None))))
      if (useFree)
        Ref.n(6).Nss.*?(Ns.n.a1.booleans_?).query.get ==> List((6, List((1, Some(Set(boolean1))), (2, None)))) // * Bug in Datomic Free
      else
        Ref.n(6).Nss.*?(Ns.n.a1.booleans_?).query.get ==> List((6, List((1, Some(Set(boolean2, boolean1))), (2, None))))
      Ref.n(7).Nss.*?(Ns.n.a1.bigInts_?).query.get ==> List((7, List((1, Some(Set(bigInt1, bigInt2))), (2, None))))
      Ref.n(8).Nss.*?(Ns.n.a1.bigDecimals_?).query.get ==> List((8, List((1, Some(Set(bigDecimal1, bigDecimal2))), (2, None))))
      Ref.n(9).Nss.*?(Ns.n.a1.dates_?).query.get ==> List((9, List((1, Some(Set(date1, date2))), (2, None))))
      Ref.n(10).Nss.*?(Ns.n.a1.uuids_?).query.get ==> List((10, List((1, Some(Set(uuid1, uuid2))), (2, None))))
      Ref.n(11).Nss.*?(Ns.n.a1.uris_?).query.get ==> List((11, List((1, Some(Set(uri1, uri2))), (2, None))))
      Ref.n(12).Nss.*?(Ns.n.a1.chars_?).query.get ==> List((12, List((1, Some(Set(char1, char2))), (2, None))))
      Ref.n(13).Nss.*?(Ns.n.a1.bytes_?).query.get ==> List((13, List((1, Some(Set(byte1, byte2))), (2, None))))
      Ref.n(14).Nss.*?(Ns.n.a1.shorts_?).query.get ==> List((14, List((1, Some(Set(short1, short2))), (2, None))))
    }

    // * Bug in Datomic Free where card-many set of Boolean values only preserves true. Datomic Pro works correctly
  }
}
