package molecule.coreTests.test.filterAttr.one

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait Types extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>


  override lazy val tests = Tests {

    "equal (apply)" - types { implicit conn =>
      for {
        _ <- Ns.string.Ref.string.insert(string1, string1).transact
        _ <- Ns.int.Ref.int.insert(int1, int1).transact
        _ <- Ns.long.Ref.long.insert(long1, long1).transact
        _ <- Ns.float.Ref.float.insert(float1, float1).transact
        _ <- Ns.double.Ref.double.insert(double1, double1).transact
        _ <- Ns.boolean.Ref.boolean.insert(boolean1, boolean1).transact
        _ <- Ns.bigInt.Ref.bigInt.insert(bigInt1, bigInt1).transact
        _ <- Ns.bigDecimal.Ref.bigDecimal.insert(bigDecimal1, bigDecimal1).transact
        _ <- Ns.date.Ref.date.insert(date1, date1).transact
        _ <- Ns.uuid.Ref.uuid.insert(uuid1, uuid1).transact
        _ <- Ns.uri.Ref.uri.insert(uri1, uri1).transact
        _ <- Ns.byte.Ref.byte.insert(byte1, byte1).transact
        _ <- Ns.short.Ref.short.insert(short1, short1).transact
        _ <- Ns.char.Ref.char.insert(char1, char1).transact

        _ <- Ns.string(Ref.string_).Ref.string.query.get.map(_ ==> List((string1, string1)))
        _ <- Ns.int(Ref.int_).Ref.int.query.get.map(_ ==> List((int1, int1)))
        _ <- Ns.long(Ref.long_).Ref.long.query.get.map(_ ==> List((long1, long1)))
        _ <- Ns.float(Ref.float_).Ref.float.query.get.map(_ ==> List((float1, float1)))
        _ <- Ns.double(Ref.double_).Ref.double.query.get.map(_ ==> List((double1, double1)))
        _ <- Ns.boolean(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((boolean1, boolean1)))
        _ <- Ns.bigInt(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((bigInt1, bigInt1)))
        _ <- Ns.bigDecimal(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((bigDecimal1, bigDecimal1)))
        _ <- Ns.date(Ref.date_).Ref.date.query.get.map(_ ==> List((date1, date1)))
        _ <- Ns.uuid(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((uuid1, uuid1)))
        _ <- Ns.uri(Ref.uri_).Ref.uri.query.get.map(_ ==> List((uri1, uri1)))
        _ <- Ns.byte(Ref.byte_).Ref.byte.query.get.map(_ ==> List((byte1, byte1)))
        _ <- Ns.short(Ref.short_).Ref.short.query.get.map(_ ==> List((short1, short1)))
        _ <- Ns.char(Ref.char_).Ref.char.query.get.map(_ ==> List((char1, char1)))
      } yield ()
    }


    "not equal" - types { implicit conn =>
      for {
        _ <- Ns.string.Ref.string.insert(string1, string2).transact
        _ <- Ns.int.Ref.int.insert(int1, int2).transact
        _ <- Ns.long.Ref.long.insert(long1, long2).transact
        _ <- Ns.float.Ref.float.insert(float1, float2).transact
        _ <- Ns.double.Ref.double.insert(double1, double2).transact
        _ <- Ns.boolean.Ref.boolean.insert(boolean1, boolean2).transact
        _ <- Ns.bigInt.Ref.bigInt.insert(bigInt1, bigInt2).transact
        _ <- Ns.bigDecimal.Ref.bigDecimal.insert(bigDecimal1, bigDecimal2).transact
        _ <- Ns.date.Ref.date.insert(date1, date2).transact
        _ <- Ns.uuid.Ref.uuid.insert(uuid1, uuid2).transact
        _ <- Ns.uri.Ref.uri.insert(uri1, uri2).transact
        _ <- Ns.byte.Ref.byte.insert(byte1, byte2).transact
        _ <- Ns.short.Ref.short.insert(short1, short2).transact
        _ <- Ns.char.Ref.char.insert(char1, char2).transact

        _ <- Ns.string.not(Ref.string_).Ref.string.query.get.map(_ ==> List((string1, string2)))
        _ <- Ns.int.not(Ref.int_).Ref.int.query.get.map(_ ==> List((int1, int2)))
        _ <- Ns.long.not(Ref.long_).Ref.long.query.get.map(_ ==> List((long1, long2)))
        _ <- Ns.float.not(Ref.float_).Ref.float.query.get.map(_ ==> List((float1, float2)))
        _ <- Ns.double.not(Ref.double_).Ref.double.query.get.map(_ ==> List((double1, double2)))
        _ <- Ns.boolean.not(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((boolean1, boolean2)))
        _ <- Ns.bigInt.not(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((bigInt1, bigInt2)))
        _ <- Ns.bigDecimal.not(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((bigDecimal1, bigDecimal2)))
        _ <- Ns.date.not(Ref.date_).Ref.date.query.get.map(_ ==> List((date1, date2)))
        _ <- Ns.uuid.not(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((uuid1, uuid2)))
        _ <- Ns.uri.not(Ref.uri_).Ref.uri.query.get.map(_ ==> List((uri1, uri2)))
        _ <- Ns.byte.not(Ref.byte_).Ref.byte.query.get.map(_ ==> List((byte1, byte2)))
        _ <- Ns.short.not(Ref.short_).Ref.short.query.get.map(_ ==> List((short1, short2)))
        _ <- Ns.char.not(Ref.char_).Ref.char.query.get.map(_ ==> List((char1, char2)))
      } yield ()
    }


    "<" - types { implicit conn =>
      for {
        _ <- Ns.string.Ref.string.insert(string1, string2).transact
        _ <- Ns.int.Ref.int.insert(int1, int2).transact
        _ <- Ns.long.Ref.long.insert(long1, long2).transact
        _ <- Ns.float.Ref.float.insert(float1, float2).transact
        _ <- Ns.double.Ref.double.insert(double1, double2).transact
        _ <- Ns.boolean.Ref.boolean.insert(boolean1, boolean2).transact
        _ <- Ns.bigInt.Ref.bigInt.insert(bigInt1, bigInt2).transact
        _ <- Ns.bigDecimal.Ref.bigDecimal.insert(bigDecimal1, bigDecimal2).transact
        _ <- Ns.date.Ref.date.insert(date1, date2).transact
        _ <- Ns.uuid.Ref.uuid.insert(uuid1, uuid2).transact
        _ <- Ns.uri.Ref.uri.insert(uri1, uri2).transact
        _ <- Ns.byte.Ref.byte.insert(byte1, byte2).transact
        _ <- Ns.short.Ref.short.insert(short1, short2).transact
        _ <- Ns.char.Ref.char.insert(char1, char2).transact

        _ <- Ns.string.<(Ref.string_).Ref.string.query.get.map(_ ==> List((string1, string2)))
        _ <- Ns.int.<(Ref.int_).Ref.int.query.get.map(_ ==> List((int1, int2)))
        _ <- Ns.long.<(Ref.long_).Ref.long.query.get.map(_ ==> List((long1, long2)))
        _ <- Ns.float.<(Ref.float_).Ref.float.query.get.map(_ ==> List((float1, float2)))
        _ <- Ns.double.<(Ref.double_).Ref.double.query.get.map(_ ==> List((double1, double2)))
        _ <- Ns.boolean.<(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((boolean1, boolean2)))
        _ <- Ns.bigInt.<(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((bigInt1, bigInt2)))
        _ <- Ns.bigDecimal.<(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((bigDecimal1, bigDecimal2)))
        _ <- Ns.date.<(Ref.date_).Ref.date.query.get.map(_ ==> List((date1, date2)))
        _ <- Ns.uuid.<(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((uuid1, uuid2)))
        _ <- Ns.uri.<(Ref.uri_).Ref.uri.query.get.map(_ ==> List((uri1, uri2)))
        _ <- Ns.byte.<(Ref.byte_).Ref.byte.query.get.map(_ ==> List((byte1, byte2)))
        _ <- Ns.short.<(Ref.short_).Ref.short.query.get.map(_ ==> List((short1, short2)))
        _ <- Ns.char.<(Ref.char_).Ref.char.query.get.map(_ ==> List((char1, char2)))
      } yield ()
    }


    "<=" - types { implicit conn =>
      for {
        _ <- Ns.string.Ref.string.insert(string1, string2).transact
        _ <- Ns.int.Ref.int.insert(int1, int2).transact
        _ <- Ns.long.Ref.long.insert(long1, long2).transact
        _ <- Ns.float.Ref.float.insert(float1, float2).transact
        _ <- Ns.double.Ref.double.insert(double1, double2).transact
        _ <- Ns.boolean.Ref.boolean.insert(boolean1, boolean2).transact
        _ <- Ns.bigInt.Ref.bigInt.insert(bigInt1, bigInt2).transact
        _ <- Ns.bigDecimal.Ref.bigDecimal.insert(bigDecimal1, bigDecimal2).transact
        _ <- Ns.date.Ref.date.insert(date1, date2).transact
        _ <- Ns.uuid.Ref.uuid.insert(uuid1, uuid2).transact
        _ <- Ns.uri.Ref.uri.insert(uri1, uri2).transact
        _ <- Ns.byte.Ref.byte.insert(byte1, byte2).transact
        _ <- Ns.short.Ref.short.insert(short1, short2).transact
        _ <- Ns.char.Ref.char.insert(char1, char2).transact

        _ <- Ns.string.<=(Ref.string_).Ref.string.query.get.map(_ ==> List((string1, string2)))
        _ <- Ns.int.<=(Ref.int_).Ref.int.query.get.map(_ ==> List((int1, int2)))
        _ <- Ns.long.<=(Ref.long_).Ref.long.query.get.map(_ ==> List((long1, long2)))
        _ <- Ns.float.<=(Ref.float_).Ref.float.query.get.map(_ ==> List((float1, float2)))
        _ <- Ns.double.<=(Ref.double_).Ref.double.query.get.map(_ ==> List((double1, double2)))
        _ <- Ns.boolean.<=(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((boolean1, boolean2)))
        _ <- Ns.bigInt.<=(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((bigInt1, bigInt2)))
        _ <- Ns.bigDecimal.<=(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((bigDecimal1, bigDecimal2)))
        _ <- Ns.date.<=(Ref.date_).Ref.date.query.get.map(_ ==> List((date1, date2)))
        _ <- Ns.uuid.<=(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((uuid1, uuid2)))
        _ <- Ns.uri.<=(Ref.uri_).Ref.uri.query.get.map(_ ==> List((uri1, uri2)))
        _ <- Ns.byte.<=(Ref.byte_).Ref.byte.query.get.map(_ ==> List((byte1, byte2)))
        _ <- Ns.short.<=(Ref.short_).Ref.short.query.get.map(_ ==> List((short1, short2)))
        _ <- Ns.char.<=(Ref.char_).Ref.char.query.get.map(_ ==> List((char1, char2)))
      } yield ()
    }


    ">" - types { implicit conn =>
      for {
        _ <- Ns.string.Ref.string.insert(string2, string1).transact
        _ <- Ns.int.Ref.int.insert(int2, int1).transact
        _ <- Ns.long.Ref.long.insert(long2, long1).transact
        _ <- Ns.float.Ref.float.insert(float2, float1).transact
        _ <- Ns.double.Ref.double.insert(double2, double1).transact
        _ <- Ns.boolean.Ref.boolean.insert(boolean2, boolean1).transact
        _ <- Ns.bigInt.Ref.bigInt.insert(bigInt2, bigInt1).transact
        _ <- Ns.bigDecimal.Ref.bigDecimal.insert(bigDecimal2, bigDecimal1).transact
        _ <- Ns.date.Ref.date.insert(date2, date1).transact
        _ <- Ns.uuid.Ref.uuid.insert(uuid2, uuid1).transact
        _ <- Ns.uri.Ref.uri.insert(uri2, uri1).transact
        _ <- Ns.byte.Ref.byte.insert(byte2, byte1).transact
        _ <- Ns.short.Ref.short.insert(short2, short1).transact
        _ <- Ns.char.Ref.char.insert(char2, char1).transact

        _ <- Ns.string.>(Ref.string_).Ref.string.query.get.map(_ ==> List((string2, string1)))
        _ <- Ns.int.>(Ref.int_).Ref.int.query.get.map(_ ==> List((int2, int1)))
        _ <- Ns.long.>(Ref.long_).Ref.long.query.get.map(_ ==> List((long2, long1)))
        _ <- Ns.float.>(Ref.float_).Ref.float.query.get.map(_ ==> List((float2, float1)))
        _ <- Ns.double.>(Ref.double_).Ref.double.query.get.map(_ ==> List((double2, double1)))
        _ <- Ns.boolean.>(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((boolean2, boolean1)))
        _ <- Ns.bigInt.>(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((bigInt2, bigInt1)))
        _ <- Ns.bigDecimal.>(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((bigDecimal2, bigDecimal1)))
        _ <- Ns.date.>(Ref.date_).Ref.date.query.get.map(_ ==> List((date2, date1)))
        _ <- Ns.uuid.>(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((uuid2, uuid1)))
        _ <- Ns.uri.>(Ref.uri_).Ref.uri.query.get.map(_ ==> List((uri2, uri1)))
        _ <- Ns.byte.>(Ref.byte_).Ref.byte.query.get.map(_ ==> List((byte2, byte1)))
        _ <- Ns.short.>(Ref.short_).Ref.short.query.get.map(_ ==> List((short2, short1)))
        _ <- Ns.char.>(Ref.char_).Ref.char.query.get.map(_ ==> List((char2, char1)))
      } yield ()
    }


    ">=" - types { implicit conn =>
      for {
        _ <- Ns.string.Ref.string.insert(string2, string1).transact
        _ <- Ns.int.Ref.int.insert(int2, int1).transact
        _ <- Ns.long.Ref.long.insert(long2, long1).transact
        _ <- Ns.float.Ref.float.insert(float2, float1).transact
        _ <- Ns.double.Ref.double.insert(double2, double1).transact
        _ <- Ns.boolean.Ref.boolean.insert(boolean2, boolean1).transact
        _ <- Ns.bigInt.Ref.bigInt.insert(bigInt2, bigInt1).transact
        _ <- Ns.bigDecimal.Ref.bigDecimal.insert(bigDecimal2, bigDecimal1).transact
        _ <- Ns.date.Ref.date.insert(date2, date1).transact
        _ <- Ns.uuid.Ref.uuid.insert(uuid2, uuid1).transact
        _ <- Ns.uri.Ref.uri.insert(uri2, uri1).transact
        _ <- Ns.byte.Ref.byte.insert(byte2, byte1).transact
        _ <- Ns.short.Ref.short.insert(short2, short1).transact
        _ <- Ns.char.Ref.char.insert(char2, char1).transact

        _ <- Ns.string.>=(Ref.string_).Ref.string.query.get.map(_ ==> List((string2, string1)))
        _ <- Ns.int.>=(Ref.int_).Ref.int.query.get.map(_ ==> List((int2, int1)))
        _ <- Ns.long.>=(Ref.long_).Ref.long.query.get.map(_ ==> List((long2, long1)))
        _ <- Ns.float.>=(Ref.float_).Ref.float.query.get.map(_ ==> List((float2, float1)))
        _ <- Ns.double.>=(Ref.double_).Ref.double.query.get.map(_ ==> List((double2, double1)))
        _ <- Ns.boolean.>=(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((boolean2, boolean1)))
        _ <- Ns.bigInt.>=(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((bigInt2, bigInt1)))
        _ <- Ns.bigDecimal.>=(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((bigDecimal2, bigDecimal1)))
        _ <- Ns.date.>=(Ref.date_).Ref.date.query.get.map(_ ==> List((date2, date1)))
        _ <- Ns.uuid.>=(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((uuid2, uuid1)))
        _ <- Ns.uri.>=(Ref.uri_).Ref.uri.query.get.map(_ ==> List((uri2, uri1)))
        _ <- Ns.byte.>=(Ref.byte_).Ref.byte.query.get.map(_ ==> List((byte2, byte1)))
        _ <- Ns.short.>=(Ref.short_).Ref.short.query.get.map(_ ==> List((short2, short1)))
        _ <- Ns.char.>=(Ref.char_).Ref.char.query.get.map(_ ==> List((char2, char1)))
      } yield ()
    }
  }
}
