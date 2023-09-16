package molecule.coreTests.test.relation.nested

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait NestedTypes extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

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
        _ <- Ref.i.Nss.*(Ns.uuid).insert(10, List(uuid1, uuid2)).transact
        _ <- Ref.i.Nss.*(Ns.uri).insert(11, List(uri1, uri2)).transact
        _ <- Ref.i.Nss.*(Ns.char).insert(12, List(char1, char2)).transact
        _ <- Ref.i.Nss.*(Ns.byte).insert(13, List(byte1, byte2)).transact
        _ <- Ref.i.Nss.*(Ns.short).insert(14, List(short1, short2)).transact

        _ <- Ref.i_.Nss.*(Ns.string.a1).query.get.map(_ ==> List(List(string1, string2)))
        _ <- Ref.i_.Nss.*(Ns.int.a1).query.get.map(_ ==> List(List(int1, int2)))
        _ <- Ref.i_.Nss.*(Ns.long.a1).query.get.map(_ ==> List(List(long1, long2)))
        _ <- Ref.i_.Nss.*(Ns.float.a1).query.get.map(_ ==> List(List(float1, float2)))
        _ <- Ref.i_.Nss.*(Ns.double.a1).query.get.map(_ ==> List(List(double1, double2)))
        _ <- Ref.i_.Nss.*(Ns.boolean.a1).query.get.map(_ ==> List(List(boolean1, boolean2)))
        _ <- Ref.i_.Nss.*(Ns.bigInt.a1).query.get.map(_ ==> List(List(bigInt1, bigInt2)))
        _ <- Ref.i_.Nss.*(Ns.bigDecimal.a1).query.get.map(_ ==> List(List(bigDecimal1, bigDecimal2)))
        _ <- Ref.i_.Nss.*(Ns.date.a1).query.get.map(_ ==> List(List(date1, date2)))
        _ <- Ref.i_.Nss.*(Ns.uuid.a1).query.get.map(_ ==> List(List(uuid1, uuid2)))
        _ <- Ref.i_.Nss.*(Ns.uri.a1).query.get.map(_ ==> List(List(uri1, uri2)))
        _ <- Ref.i_.Nss.*(Ns.char.a1).query.get.map(_ ==> List(List(char1, char2)))
        _ <- Ref.i_.Nss.*(Ns.byte.a1).query.get.map(_ ==> List(List(byte1, byte2)))
        _ <- Ref.i_.Nss.*(Ns.short.a1).query.get.map(_ ==> List(List(short1, short2)))

        _ <- Ref.i_(1).Nss.*?(Ns.string.a1).query.get.map(_ ==> List(List(string1, string2)))
        _ <- Ref.i_(2).Nss.*?(Ns.int.a1).query.get.map(_ ==> List(List(int1, int2)))
        _ <- Ref.i_(3).Nss.*?(Ns.long.a1).query.get.map(_ ==> List(List(long1, long2)))
        _ <- Ref.i_(4).Nss.*?(Ns.float.a1).query.get.map(_ ==> List(List(float1, float2)))
        _ <- Ref.i_(5).Nss.*?(Ns.double.a1).query.get.map(_ ==> List(List(double1, double2)))
        _ <- Ref.i_(6).Nss.*?(Ns.boolean.a1).query.get.map(_ ==> List(List(boolean1, boolean2)))
        _ <- Ref.i_(7).Nss.*?(Ns.bigInt.a1).query.get.map(_ ==> List(List(bigInt1, bigInt2)))
        _ <- Ref.i_(8).Nss.*?(Ns.bigDecimal.a1).query.get.map(_ ==> List(List(bigDecimal1, bigDecimal2)))
        _ <- Ref.i_(9).Nss.*?(Ns.date.a1).query.get.map(_ ==> List(List(date1, date2)))
        _ <- Ref.i_(10).Nss.*?(Ns.uuid.a1).query.get.map(_ ==> List(List(uuid1, uuid2)))
        _ <- Ref.i_(11).Nss.*?(Ns.uri.a1).query.get.map(_ ==> List(List(uri1, uri2)))
        _ <- Ref.i_(12).Nss.*?(Ns.char.a1).query.get.map(_ ==> List(List(char1, char2)))
        _ <- Ref.i_(13).Nss.*?(Ns.byte.a1).query.get.map(_ ==> List(List(byte1, byte2)))
        _ <- Ref.i_(14).Nss.*?(Ns.short.a1).query.get.map(_ ==> List(List(short1, short2)))
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
        _ <- Ref.i.Nss.*(Ns.i.uuid_?).insert(10, List((1, Some(uuid1)), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.uri_?).insert(11, List((1, Some(uri1)), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.char_?).insert(12, List((1, Some(char1)), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.byte_?).insert(13, List((1, Some(byte1)), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.short_?).insert(14, List((1, Some(short1)), (2, None))).transact

        _ <- Ref.i(1).Nss.*(Ns.i.a1.string_?).query.get.map(_ ==> List((1, List((1, Some(string1)), (2, None)))))
        _ <- Ref.i(2).Nss.*(Ns.i.a1.int_?).query.get.map(_ ==> List((2, List((1, Some(int1)), (2, None)))))
        _ <- Ref.i(3).Nss.*(Ns.i.a1.long_?).query.get.map(_ ==> List((3, List((1, Some(long1)), (2, None)))))
        _ <- Ref.i(4).Nss.*(Ns.i.a1.float_?).query.get.map(_ ==> List((4, List((1, Some(float1)), (2, None)))))
        _ <- Ref.i(5).Nss.*(Ns.i.a1.double_?).query.get.map(_ ==> List((5, List((1, Some(double1)), (2, None)))))
        _ <- Ref.i(6).Nss.*(Ns.i.a1.boolean_?).query.get.map(_ ==> List((6, List((1, Some(boolean1)), (2, None)))))
        _ <- Ref.i(7).Nss.*(Ns.i.a1.bigInt_?).query.get.map(_ ==> List((7, List((1, Some(bigInt1)), (2, None)))))
        _ <- Ref.i(8).Nss.*(Ns.i.a1.bigDecimal_?).query.get.map(_ ==> List((8, List((1, Some(bigDecimal1)), (2, None)))))
        _ <- Ref.i(9).Nss.*(Ns.i.a1.date_?).query.get.map(_ ==> List((9, List((1, Some(date1)), (2, None)))))
        _ <- Ref.i(10).Nss.*(Ns.i.a1.uuid_?).query.get.map(_ ==> List((10, List((1, Some(uuid1)), (2, None)))))
        _ <- Ref.i(11).Nss.*(Ns.i.a1.uri_?).query.get.map(_ ==> List((11, List((1, Some(uri1)), (2, None)))))
        _ <- Ref.i(12).Nss.*(Ns.i.a1.char_?).query.get.map(_ ==> List((12, List((1, Some(char1)), (2, None)))))
        _ <- Ref.i(13).Nss.*(Ns.i.a1.byte_?).query.get.map(_ ==> List((13, List((1, Some(byte1)), (2, None)))))
        _ <- Ref.i(14).Nss.*(Ns.i.a1.short_?).query.get.map(_ ==> List((14, List((1, Some(short1)), (2, None)))))

        _ <- Ref.i(1).Nss.*?(Ns.i.a1.string_?).query.get.map(_ ==> List((1, List((1, Some(string1)), (2, None)))))
        _ <- Ref.i(2).Nss.*?(Ns.i.a1.int_?).query.get.map(_ ==> List((2, List((1, Some(int1)), (2, None)))))
        _ <- Ref.i(3).Nss.*?(Ns.i.a1.long_?).query.get.map(_ ==> List((3, List((1, Some(long1)), (2, None)))))
        _ <- Ref.i(4).Nss.*?(Ns.i.a1.float_?).query.get.map(_ ==> List((4, List((1, Some(float1)), (2, None)))))
        _ <- Ref.i(5).Nss.*?(Ns.i.a1.double_?).query.get.map(_ ==> List((5, List((1, Some(double1)), (2, None)))))
        _ <- Ref.i(6).Nss.*?(Ns.i.a1.boolean_?).query.get.map(_ ==> List((6, List((1, Some(boolean1)), (2, None)))))
        _ <- Ref.i(7).Nss.*?(Ns.i.a1.bigInt_?).query.get.map(_ ==> List((7, List((1, Some(bigInt1)), (2, None)))))
        _ <- Ref.i(8).Nss.*?(Ns.i.a1.bigDecimal_?).query.get.map(_ ==> List((8, List((1, Some(bigDecimal1)), (2, None)))))
        _ <- Ref.i(9).Nss.*?(Ns.i.a1.date_?).query.get.map(_ ==> List((9, List((1, Some(date1)), (2, None)))))
        _ <- Ref.i(10).Nss.*?(Ns.i.a1.uuid_?).query.get.map(_ ==> List((10, List((1, Some(uuid1)), (2, None)))))
        _ <- Ref.i(11).Nss.*?(Ns.i.a1.uri_?).query.get.map(_ ==> List((11, List((1, Some(uri1)), (2, None)))))
        _ <- Ref.i(12).Nss.*?(Ns.i.a1.char_?).query.get.map(_ ==> List((12, List((1, Some(char1)), (2, None)))))
        _ <- Ref.i(13).Nss.*?(Ns.i.a1.byte_?).query.get.map(_ ==> List((13, List((1, Some(byte1)), (2, None)))))
        _ <- Ref.i(14).Nss.*?(Ns.i.a1.short_?).query.get.map(_ ==> List((14, List((1, Some(short1)), (2, None)))))
      } yield ()
    }


    "Card set mandatory" - types { implicit conn =>
      for {
        _ <- Ref.i.Nss.*(Ns.strings).insert(1, List(Set(string1, string2))).transact
        _ <- Ref.i.Nss.*(Ns.ints).insert(2, List(Set(int1, int2))).transact
        _ <- Ref.i.Nss.*(Ns.longs).insert(3, List(Set(long1, long2))).transact
        _ <- Ref.i.Nss.*(Ns.floats).insert(4, List(Set(float1, float2))).transact
        _ <- Ref.i.Nss.*(Ns.doubles).insert(5, List(Set(double1, double2))).transact
        _ <- Ref.i.Nss.*(Ns.booleans).insert(6, List(Set(boolean1, boolean2))).transact
        _ <- Ref.i.Nss.*(Ns.bigInts).insert(7, List(Set(bigInt1, bigInt2))).transact
        _ <- Ref.i.Nss.*(Ns.bigDecimals).insert(8, List(Set(bigDecimal1, bigDecimal2))).transact
        _ <- Ref.i.Nss.*(Ns.dates).insert(9, List(Set(date1, date2))).transact
        _ <- Ref.i.Nss.*(Ns.uuids).insert(10, List(Set(uuid1, uuid2))).transact
        _ <- Ref.i.Nss.*(Ns.uris).insert(11, List(Set(uri1, uri2))).transact
        _ <- Ref.i.Nss.*(Ns.chars).insert(12, List(Set(char1, char2))).transact
        _ <- Ref.i.Nss.*(Ns.bytes).insert(13, List(Set(byte1, byte2))).transact
        _ <- Ref.i.Nss.*(Ns.shorts).insert(14, List(Set(short1, short2))).transact

        _ <- Ref.i_.Nss.*(Ns.strings).query.get.map(_ ==> List(List(Set(string1, string2))))
        _ <- Ref.i_.Nss.*(Ns.ints).query.get.map(_ ==> List(List(Set(int1, int2))))
        _ <- Ref.i_.Nss.*(Ns.longs).query.get.map(_ ==> List(List(Set(long1, long2))))
        _ <- Ref.i_.Nss.*(Ns.floats).query.get.map(_ ==> List(List(Set(float1, float2))))
        _ <- Ref.i_.Nss.*(Ns.doubles).query.get.map(_ ==> List(List(Set(double1, double2))))
        _ <- Ref.i_.Nss.*(Ns.booleans).query.get.map(_ ==> List(List(Set(boolean1, boolean2))))
        _ <- Ref.i_.Nss.*(Ns.bigInts).query.get.map(_ ==> List(List(Set(bigInt1, bigInt2))))
        _ <- Ref.i_.Nss.*(Ns.bigDecimals).query.get.map(_ ==> List(List(Set(bigDecimal1, bigDecimal2))))
        _ <- Ref.i_.Nss.*(Ns.dates).query.get.map(_ ==> List(List(Set(date1, date2))))
        _ <- Ref.i_.Nss.*(Ns.uuids).query.get.map(_ ==> List(List(Set(uuid1, uuid2))))
        _ <- Ref.i_.Nss.*(Ns.uris).query.get.map(_ ==> List(List(Set(uri1, uri2))))
        _ <- Ref.i_.Nss.*(Ns.chars).query.get.map(_ ==> List(List(Set(char1, char2))))
        _ <- Ref.i_.Nss.*(Ns.bytes).query.get.map(_ ==> List(List(Set(byte1, byte2))))
        _ <- Ref.i_.Nss.*(Ns.shorts).query.get.map(_ ==> List(List(Set(short1, short2))))

        _ <- Ref.i_(1).Nss.*?(Ns.strings).query.get.map(_ ==> List(List(Set(string1, string2))))
        _ <- Ref.i_(2).Nss.*?(Ns.ints).query.get.map(_ ==> List(List(Set(int1, int2))))
        _ <- Ref.i_(3).Nss.*?(Ns.longs).query.get.map(_ ==> List(List(Set(long1, long2))))
        _ <- Ref.i_(4).Nss.*?(Ns.floats).query.get.map(_ ==> List(List(Set(float1, float2))))
        _ <- Ref.i_(5).Nss.*?(Ns.doubles).query.get.map(_ ==> List(List(Set(double1, double2))))
        _ <- Ref.i_(6).Nss.*?(Ns.booleans).query.get.map(_ ==> List(List(Set(boolean1, boolean2))))
        _ <- Ref.i_(7).Nss.*?(Ns.bigInts).query.get.map(_ ==> List(List(Set(bigInt1, bigInt2))))
        _ <- Ref.i_(8).Nss.*?(Ns.bigDecimals).query.get.map(_ ==> List(List(Set(bigDecimal1, bigDecimal2))))
        _ <- Ref.i_(9).Nss.*?(Ns.dates).query.get.map(_ ==> List(List(Set(date1, date2))))
        _ <- Ref.i_(10).Nss.*?(Ns.uuids).query.get.map(_ ==> List(List(Set(uuid1, uuid2))))
        _ <- Ref.i_(11).Nss.*?(Ns.uris).query.get.map(_ ==> List(List(Set(uri1, uri2))))
        _ <- Ref.i_(12).Nss.*?(Ns.chars).query.get.map(_ ==> List(List(Set(char1, char2))))
        _ <- Ref.i_(13).Nss.*?(Ns.bytes).query.get.map(_ ==> List(List(Set(byte1, byte2))))
        _ <- Ref.i_(14).Nss.*?(Ns.shorts).query.get.map(_ ==> List(List(Set(short1, short2))))
      } yield ()
    }


    "Card set optional" - types { implicit conn =>
      for {
        _ <- Ref.i.Nss.*(Ns.i.strings_?).insert(1, List((1, Some(Set(string1, string2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.ints_?).insert(2, List((1, Some(Set(int1, int2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.longs_?).insert(3, List((1, Some(Set(long1, long2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.floats_?).insert(4, List((1, Some(Set(float1, float2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.doubles_?).insert(5, List((1, Some(Set(double1, double2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.booleans_?).insert(6, List((1, Some(Set(boolean1, boolean2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.bigInts_?).insert(7, List((1, Some(Set(bigInt1, bigInt2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.bigDecimals_?).insert(8, List((1, Some(Set(bigDecimal1, bigDecimal2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.dates_?).insert(9, List((1, Some(Set(date1, date2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.uuids_?).insert(10, List((1, Some(Set(uuid1, uuid2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.uris_?).insert(11, List((1, Some(Set(uri1, uri2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.chars_?).insert(12, List((1, Some(Set(char1, char2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.bytes_?).insert(13, List((1, Some(Set(byte1, byte2))), (2, None))).transact
        _ <- Ref.i.Nss.*(Ns.i.shorts_?).insert(14, List((1, Some(Set(short1, short2))), (2, None))).transact

        _ <- Ref.i(1).Nss.*(Ns.i.a1.strings_?).query.get.map(_ ==> List((1, List((1, Some(Set(string1, string2))), (2, None)))))
        _ <- Ref.i(2).Nss.*(Ns.i.a1.ints_?).query.get.map(_ ==> List((2, List((1, Some(Set(int1, int2))), (2, None)))))
        _ <- Ref.i(3).Nss.*(Ns.i.a1.longs_?).query.get.map(_ ==> List((3, List((1, Some(Set(long1, long2))), (2, None)))))
        _ <- Ref.i(4).Nss.*(Ns.i.a1.floats_?).query.get.map(_ ==> List((4, List((1, Some(Set(float1, float2))), (2, None)))))
        _ <- Ref.i(5).Nss.*(Ns.i.a1.doubles_?).query.get.map(_ ==> List((5, List((1, Some(Set(double1, double2))), (2, None)))))
        _ <- Ref.i(6).Nss.*(Ns.i.a1.booleans_?).query.get.map(_ ==> List((6, List((1, Some(Set(boolean1, boolean2))), (2, None)))))
        _ <- Ref.i(7).Nss.*(Ns.i.a1.bigInts_?).query.get.map(_ ==> List((7, List((1, Some(Set(bigInt1, bigInt2))), (2, None)))))
        _ <- Ref.i(8).Nss.*(Ns.i.a1.bigDecimals_?).query.get.map(_ ==> List((8, List((1, Some(Set(bigDecimal1, bigDecimal2))), (2, None)))))
        _ <- Ref.i(9).Nss.*(Ns.i.a1.dates_?).query.get.map(_ ==> List((9, List((1, Some(Set(date1, date2))), (2, None)))))
        _ <- Ref.i(10).Nss.*(Ns.i.a1.uuids_?).query.get.map(_ ==> List((10, List((1, Some(Set(uuid1, uuid2))), (2, None)))))
        _ <- Ref.i(11).Nss.*(Ns.i.a1.uris_?).query.get.map(_ ==> List((11, List((1, Some(Set(uri1, uri2))), (2, None)))))
        _ <- Ref.i(12).Nss.*(Ns.i.a1.chars_?).query.get.map(_ ==> List((12, List((1, Some(Set(char1, char2))), (2, None)))))
        _ <- Ref.i(13).Nss.*(Ns.i.a1.bytes_?).query.get.map(_ ==> List((13, List((1, Some(Set(byte1, byte2))), (2, None)))))
        _ <- Ref.i(14).Nss.*(Ns.i.a1.shorts_?).query.get.map(_ ==> List((14, List((1, Some(Set(short1, short2))), (2, None)))))

        _ <- Ref.i(1).Nss.*?(Ns.i.a1.strings_?).query.get.map(_ ==> List((1, List((1, Some(Set(string1, string2))), (2, None)))))
        _ <- Ref.i(2).Nss.*?(Ns.i.a1.ints_?).query.get.map(_ ==> List((2, List((1, Some(Set(int1, int2))), (2, None)))))
        _ <- Ref.i(3).Nss.*?(Ns.i.a1.longs_?).query.get.map(_ ==> List((3, List((1, Some(Set(long1, long2))), (2, None)))))
        _ <- Ref.i(4).Nss.*?(Ns.i.a1.floats_?).query.get.map(_ ==> List((4, List((1, Some(Set(float1, float2))), (2, None)))))
        _ <- Ref.i(5).Nss.*?(Ns.i.a1.doubles_?).query.get.map(_ ==> List((5, List((1, Some(Set(double1, double2))), (2, None)))))
        _ <- Ref.i(6).Nss.*?(Ns.i.a1.booleans_?).query.get.map(_ ==> List((6, List((1, Some(Set(boolean1, boolean2))), (2, None)))))
        _ <- Ref.i(7).Nss.*?(Ns.i.a1.bigInts_?).query.get.map(_ ==> List((7, List((1, Some(Set(bigInt1, bigInt2))), (2, None)))))
        _ <- Ref.i(8).Nss.*?(Ns.i.a1.bigDecimals_?).query.get.map(_ ==> List((8, List((1, Some(Set(bigDecimal1, bigDecimal2))), (2, None)))))
        _ <- Ref.i(9).Nss.*?(Ns.i.a1.dates_?).query.get.map(_ ==> List((9, List((1, Some(Set(date1, date2))), (2, None)))))
        _ <- Ref.i(10).Nss.*?(Ns.i.a1.uuids_?).query.get.map(_ ==> List((10, List((1, Some(Set(uuid1, uuid2))), (2, None)))))
        _ <- Ref.i(11).Nss.*?(Ns.i.a1.uris_?).query.get.map(_ ==> List((11, List((1, Some(Set(uri1, uri2))), (2, None)))))
        _ <- Ref.i(12).Nss.*?(Ns.i.a1.chars_?).query.get.map(_ ==> List((12, List((1, Some(Set(char1, char2))), (2, None)))))
        _ <- Ref.i(13).Nss.*?(Ns.i.a1.bytes_?).query.get.map(_ ==> List((13, List((1, Some(Set(byte1, byte2))), (2, None)))))
        _ <- Ref.i(14).Nss.*?(Ns.i.a1.shorts_?).query.get.map(_ ==> List((14, List((1, Some(Set(short1, short2))), (2, None)))))
      } yield ()
    }
  }
}
