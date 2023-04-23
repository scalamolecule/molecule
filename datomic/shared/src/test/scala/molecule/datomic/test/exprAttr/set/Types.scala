package molecule.datomic.test.exprAttr.set

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import utest._

object Types extends DatomicTestSuite {


  override lazy val tests = Tests {

    "equal (apply)" - types { implicit conn =>
      for {
        _ <- Ns.strings.Ref.strings.insert(Set(string1), Set(string1)).transact
        _ <- Ns.ints.Ref.ints.insert(Set(int1), Set(int1)).transact
        _ <- Ns.longs.Ref.longs.insert(Set(long1), Set(long1)).transact
        _ <- Ns.floats.Ref.floats.insert(Set(float1), Set(float1)).transact
        _ <- Ns.doubles.Ref.doubles.insert(Set(double1), Set(double1)).transact
        _ <- Ns.booleans.Ref.booleans.insert(Set(boolean1), Set(boolean1)).transact
        _ <- Ns.bigInts.Ref.bigInts.insert(Set(bigInt1), Set(bigInt1)).transact
        _ <- Ns.bigDecimals.Ref.bigDecimals.insert(Set(bigDecimal1), Set(bigDecimal1)).transact
        _ <- Ns.dates.Ref.dates.insert(Set(date1), Set(date1)).transact
        _ <- Ns.uuids.Ref.uuids.insert(Set(uuid1), Set(uuid1)).transact
        _ <- Ns.uris.Ref.uris.insert(Set(uri1), Set(uri1)).transact
        _ <- Ns.bytes.Ref.bytes.insert(Set(byte1), Set(byte1)).transact
        _ <- Ns.shorts.Ref.shorts.insert(Set(short1), Set(short1)).transact
        _ <- Ns.chars.Ref.chars.insert(Set(char1), Set(char1)).transact

        _ <- Ns.strings(Ref.strings_).Ref.strings.query.get.map(_ ==> List((Set(string1), Set(string1))))
        _ <- Ns.ints(Ref.ints_).Ref.ints.query.get.map(_ ==> List((Set(int1), Set(int1))))
        _ <- Ns.longs(Ref.longs_).Ref.longs.query.get.map(_ ==> List((Set(long1), Set(long1))))
        _ <- Ns.floats(Ref.floats_).Ref.floats.query.get.map(_ ==> List((Set(float1), Set(float1))))
        _ <- Ns.doubles(Ref.doubles_).Ref.doubles.query.get.map(_ ==> List((Set(double1), Set(double1))))
        _ <- Ns.booleans(Ref.booleans_).Ref.booleans.query.get.map(_ ==> List((Set(boolean1), Set(boolean1))))
        _ <- Ns.bigInts(Ref.bigInts_).Ref.bigInts.query.get.map(_ ==> List((Set(bigInt1), Set(bigInt1))))
        _ <- Ns.bigDecimals(Ref.bigDecimals_).Ref.bigDecimals.query.get.map(_ ==> List((Set(bigDecimal1), Set(bigDecimal1))))
        _ <- Ns.dates(Ref.dates_).Ref.dates.query.get.map(_ ==> List((Set(date1), Set(date1))))
        _ <- Ns.uuids(Ref.uuids_).Ref.uuids.query.get.map(_ ==> List((Set(uuid1), Set(uuid1))))
        _ <- Ns.uris(Ref.uris_).Ref.uris.query.get.map(_ ==> List((Set(uri1), Set(uri1))))
        _ <- Ns.bytes(Ref.bytes_).Ref.bytes.query.get.map(_ ==> List((Set(byte1), Set(byte1))))
        _ <- Ns.shorts(Ref.shorts_).Ref.shorts.query.get.map(_ ==> List((Set(short1), Set(short1))))
        _ <- Ns.chars(Ref.chars_).Ref.chars.query.get.map(_ ==> List((Set(char1), Set(char1))))
      } yield ()
    }


    "not equal" - types { implicit conn =>
      for {
        _ <- Ns.strings.Ref.strings.insert(Set(string1), Set(string2)).transact
        _ <- Ns.ints.Ref.ints.insert(Set(int1), Set(int2)).transact
        _ <- Ns.longs.Ref.longs.insert(Set(long1), Set(long2)).transact
        _ <- Ns.floats.Ref.floats.insert(Set(float1), Set(float2)).transact
        _ <- Ns.doubles.Ref.doubles.insert(Set(double1), Set(double2)).transact
        _ <- Ns.booleans.Ref.booleans.insert(Set(boolean1), Set(boolean2)).transact
        _ <- Ns.bigInts.Ref.bigInts.insert(Set(bigInt1), Set(bigInt2)).transact
        _ <- Ns.bigDecimals.Ref.bigDecimals.insert(Set(bigDecimal1), Set(bigDecimal2)).transact
        _ <- Ns.dates.Ref.dates.insert(Set(date1), Set(date2)).transact
        _ <- Ns.uuids.Ref.uuids.insert(Set(uuid1), Set(uuid2)).transact
        _ <- Ns.uris.Ref.uris.insert(Set(uri1), Set(uri2)).transact
        _ <- Ns.bytes.Ref.bytes.insert(Set(byte1), Set(byte2)).transact
        _ <- Ns.shorts.Ref.shorts.insert(Set(short1), Set(short2)).transact
        _ <- Ns.chars.Ref.chars.insert(Set(char1), Set(char2)).transact

        _ <- Ns.strings.not(Ref.strings_).Ref.strings.query.get.map(_ ==> List((Set(string1), Set(string2))))
        _ <- Ns.ints.not(Ref.ints_).Ref.ints.query.get.map(_ ==> List((Set(int1), Set(int2))))
        _ <- Ns.longs.not(Ref.longs_).Ref.longs.query.get.map(_ ==> List((Set(long1), Set(long2))))
        _ <- Ns.floats.not(Ref.floats_).Ref.floats.query.get.map(_ ==> List((Set(float1), Set(float2))))
        _ <- Ns.doubles.not(Ref.doubles_).Ref.doubles.query.get.map(_ ==> List((Set(double1), Set(double2))))
        _ <- Ns.booleans.not(Ref.booleans_).Ref.booleans.query.get.map(_ ==> List((Set(boolean1), Set(boolean2))))
        _ <- Ns.bigInts.not(Ref.bigInts_).Ref.bigInts.query.get.map(_ ==> List((Set(bigInt1), Set(bigInt2))))
        _ <- Ns.bigDecimals.not(Ref.bigDecimals_).Ref.bigDecimals.query.get.map(_ ==> List((Set(bigDecimal1), Set(bigDecimal2))))
        _ <- Ns.dates.not(Ref.dates_).Ref.dates.query.get.map(_ ==> List((Set(date1), Set(date2))))
        _ <- Ns.uuids.not(Ref.uuids_).Ref.uuids.query.get.map(_ ==> List((Set(uuid1), Set(uuid2))))
        _ <- Ns.uris.not(Ref.uris_).Ref.uris.query.get.map(_ ==> List((Set(uri1), Set(uri2))))
        _ <- Ns.bytes.not(Ref.bytes_).Ref.bytes.query.get.map(_ ==> List((Set(byte1), Set(byte2))))
        _ <- Ns.shorts.not(Ref.shorts_).Ref.shorts.query.get.map(_ ==> List((Set(short1), Set(short2))))
        _ <- Ns.chars.not(Ref.chars_).Ref.chars.query.get.map(_ ==> List((Set(char1), Set(char2))))
      } yield ()
    }

    "has set" - types { implicit conn =>
      for {
        _ <- Ns.strings.Ref.strings.insert(Set(string1), Set(string1)).transact
        _ <- Ns.ints.Ref.ints.insert(Set(int1), Set(int1)).transact
        _ <- Ns.longs.Ref.longs.insert(Set(long1), Set(long1)).transact
        _ <- Ns.floats.Ref.floats.insert(Set(float1), Set(float1)).transact
        _ <- Ns.doubles.Ref.doubles.insert(Set(double1), Set(double1)).transact
        _ <- Ns.booleans.Ref.booleans.insert(Set(boolean1), Set(boolean1)).transact
        _ <- Ns.bigInts.Ref.bigInts.insert(Set(bigInt1), Set(bigInt1)).transact
        _ <- Ns.bigDecimals.Ref.bigDecimals.insert(Set(bigDecimal1), Set(bigDecimal1)).transact
        _ <- Ns.dates.Ref.dates.insert(Set(date1), Set(date1)).transact
        _ <- Ns.uuids.Ref.uuids.insert(Set(uuid1), Set(uuid1)).transact
        _ <- Ns.uris.Ref.uris.insert(Set(uri1), Set(uri1)).transact
        _ <- Ns.bytes.Ref.bytes.insert(Set(byte1), Set(byte1)).transact
        _ <- Ns.shorts.Ref.shorts.insert(Set(short1), Set(short1)).transact
        _ <- Ns.chars.Ref.chars.insert(Set(char1), Set(char1)).transact

        _ <- Ns.strings.has(Ref.strings_).Ref.strings.query.get.map(_ ==> List((Set(string1), Set(string1))))
        _ <- Ns.ints.has(Ref.ints_).Ref.ints.query.get.map(_ ==> List((Set(int1), Set(int1))))
        _ <- Ns.longs.has(Ref.longs_).Ref.longs.query.get.map(_ ==> List((Set(long1), Set(long1))))
        _ <- Ns.floats.has(Ref.floats_).Ref.floats.query.get.map(_ ==> List((Set(float1), Set(float1))))
        _ <- Ns.doubles.has(Ref.doubles_).Ref.doubles.query.get.map(_ ==> List((Set(double1), Set(double1))))
        _ <- Ns.booleans.has(Ref.booleans_).Ref.booleans.query.get.map(_ ==> List((Set(boolean1), Set(boolean1))))
        _ <- Ns.bigInts.has(Ref.bigInts_).Ref.bigInts.query.get.map(_ ==> List((Set(bigInt1), Set(bigInt1))))
        _ <- Ns.bigDecimals.has(Ref.bigDecimals_).Ref.bigDecimals.query.get.map(_ ==> List((Set(bigDecimal1), Set(bigDecimal1))))
        _ <- Ns.dates.has(Ref.dates_).Ref.dates.query.get.map(_ ==> List((Set(date1), Set(date1))))
        _ <- Ns.uuids.has(Ref.uuids_).Ref.uuids.query.get.map(_ ==> List((Set(uuid1), Set(uuid1))))
        _ <- Ns.uris.has(Ref.uris_).Ref.uris.query.get.map(_ ==> List((Set(uri1), Set(uri1))))
        _ <- Ns.bytes.has(Ref.bytes_).Ref.bytes.query.get.map(_ ==> List((Set(byte1), Set(byte1))))
        _ <- Ns.shorts.has(Ref.shorts_).Ref.shorts.query.get.map(_ ==> List((Set(short1), Set(short1))))
        _ <- Ns.chars.has(Ref.chars_).Ref.chars.query.get.map(_ ==> List((Set(char1), Set(char1))))
      } yield ()
    }

    "has one" - types { implicit conn =>
      for {
        _ <- Ns.strings.Ref.string.insert(Set(string1), string1).transact
        _ <- Ns.ints.Ref.int.insert(Set(int1), int1).transact
        _ <- Ns.longs.Ref.long.insert(Set(long1), long1).transact
        _ <- Ns.floats.Ref.float.insert(Set(float1), float1).transact
        _ <- Ns.doubles.Ref.double.insert(Set(double1), double1).transact
        _ <- Ns.booleans.Ref.boolean.insert(Set(boolean1), boolean1).transact
        _ <- Ns.bigInts.Ref.bigInt.insert(Set(bigInt1), bigInt1).transact
        _ <- Ns.bigDecimals.Ref.bigDecimal.insert(Set(bigDecimal1), bigDecimal1).transact
        _ <- Ns.dates.Ref.date.insert(Set(date1), date1).transact
        _ <- Ns.uuids.Ref.uuid.insert(Set(uuid1), uuid1).transact
        _ <- Ns.uris.Ref.uri.insert(Set(uri1), uri1).transact
        _ <- Ns.bytes.Ref.byte.insert(Set(byte1), byte1).transact
        _ <- Ns.shorts.Ref.short.insert(Set(short1), short1).transact
        _ <- Ns.chars.Ref.char.insert(Set(char1), char1).transact

        _ <- Ns.strings.has(Ref.string_).Ref.string.query.get.map(_ ==> List((Set(string1), string1)))
        _ <- Ns.ints.has(Ref.int_).Ref.int.query.get.map(_ ==> List((Set(int1), int1)))
        _ <- Ns.longs.has(Ref.long_).Ref.long.query.get.map(_ ==> List((Set(long1), long1)))
        _ <- Ns.floats.has(Ref.float_).Ref.float.query.get.map(_ ==> List((Set(float1), float1)))
        _ <- Ns.doubles.has(Ref.double_).Ref.double.query.get.map(_ ==> List((Set(double1), double1)))
        _ <- Ns.booleans.has(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((Set(boolean1), boolean1)))
        _ <- Ns.bigInts.has(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((Set(bigInt1), bigInt1)))
        _ <- Ns.bigDecimals.has(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((Set(bigDecimal1), bigDecimal1)))
        _ <- Ns.dates.has(Ref.date_).Ref.date.query.get.map(_ ==> List((Set(date1), date1)))
        _ <- Ns.uuids.has(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((Set(uuid1), uuid1)))
        _ <- Ns.uris.has(Ref.uri_).Ref.uri.query.get.map(_ ==> List((Set(uri1), uri1)))
        _ <- Ns.bytes.has(Ref.byte_).Ref.byte.query.get.map(_ ==> List((Set(byte1), byte1)))
        _ <- Ns.shorts.has(Ref.short_).Ref.short.query.get.map(_ ==> List((Set(short1), short1)))
        _ <- Ns.chars.has(Ref.char_).Ref.char.query.get.map(_ ==> List((Set(char1), char1)))
      } yield ()
    }


    "hasNo set" - types { implicit conn =>
      for {
        _ <- Ns.strings.Ref.strings.insert(Set(string1), Set(string2)).transact
        _ <- Ns.ints.Ref.ints.insert(Set(int1), Set(int2)).transact
        _ <- Ns.longs.Ref.longs.insert(Set(long1), Set(long2)).transact
        _ <- Ns.floats.Ref.floats.insert(Set(float1), Set(float2)).transact
        _ <- Ns.doubles.Ref.doubles.insert(Set(double1), Set(double2)).transact
        _ <- Ns.booleans.Ref.booleans.insert(Set(boolean1), Set(boolean2)).transact
        _ <- Ns.bigInts.Ref.bigInts.insert(Set(bigInt1), Set(bigInt2)).transact
        _ <- Ns.bigDecimals.Ref.bigDecimals.insert(Set(bigDecimal1), Set(bigDecimal2)).transact
        _ <- Ns.dates.Ref.dates.insert(Set(date1), Set(date2)).transact
        _ <- Ns.uuids.Ref.uuids.insert(Set(uuid1), Set(uuid2)).transact
        _ <- Ns.uris.Ref.uris.insert(Set(uri1), Set(uri2)).transact
        _ <- Ns.bytes.Ref.bytes.insert(Set(byte1), Set(byte2)).transact
        _ <- Ns.shorts.Ref.shorts.insert(Set(short1), Set(short2)).transact
        _ <- Ns.chars.Ref.chars.insert(Set(char1), Set(char2)).transact

        _ <- Ns.strings.hasNo(Ref.strings_).Ref.strings.query.get.map(_ ==> List((Set(string1), Set(string2))))
        _ <- Ns.ints.hasNo(Ref.ints_).Ref.ints.query.get.map(_ ==> List((Set(int1), Set(int2))))
        _ <- Ns.longs.hasNo(Ref.longs_).Ref.longs.query.get.map(_ ==> List((Set(long1), Set(long2))))
        _ <- Ns.floats.hasNo(Ref.floats_).Ref.floats.query.get.map(_ ==> List((Set(float1), Set(float2))))
        _ <- Ns.doubles.hasNo(Ref.doubles_).Ref.doubles.query.get.map(_ ==> List((Set(double1), Set(double2))))
        _ <- Ns.booleans.hasNo(Ref.booleans_).Ref.booleans.query.get.map(_ ==> List((Set(boolean1), Set(boolean2))))
        _ <- Ns.bigInts.hasNo(Ref.bigInts_).Ref.bigInts.query.get.map(_ ==> List((Set(bigInt1), Set(bigInt2))))
        _ <- Ns.bigDecimals.hasNo(Ref.bigDecimals_).Ref.bigDecimals.query.get.map(_ ==> List((Set(bigDecimal1), Set(bigDecimal2))))
        _ <- Ns.dates.hasNo(Ref.dates_).Ref.dates.query.get.map(_ ==> List((Set(date1), Set(date2))))
        _ <- Ns.uuids.hasNo(Ref.uuids_).Ref.uuids.query.get.map(_ ==> List((Set(uuid1), Set(uuid2))))
        _ <- Ns.uris.hasNo(Ref.uris_).Ref.uris.query.get.map(_ ==> List((Set(uri1), Set(uri2))))
        _ <- Ns.bytes.hasNo(Ref.bytes_).Ref.bytes.query.get.map(_ ==> List((Set(byte1), Set(byte2))))
        _ <- Ns.shorts.hasNo(Ref.shorts_).Ref.shorts.query.get.map(_ ==> List((Set(short1), Set(short2))))
        _ <- Ns.chars.hasNo(Ref.chars_).Ref.chars.query.get.map(_ ==> List((Set(char1), Set(char2))))
      } yield ()
    }

    "hasNo one" - types { implicit conn =>
      for {
        _ <- Ns.strings.Ref.string.insert(Set(string1), string2).transact
        _ <- Ns.ints.Ref.int.insert(Set(int1), int2).transact
        _ <- Ns.longs.Ref.long.insert(Set(long1), long2).transact
        _ <- Ns.floats.Ref.float.insert(Set(float1), float2).transact
        _ <- Ns.doubles.Ref.double.insert(Set(double1), double2).transact
        _ <- Ns.booleans.Ref.boolean.insert(Set(boolean1), boolean2).transact
        _ <- Ns.bigInts.Ref.bigInt.insert(Set(bigInt1), bigInt2).transact
        _ <- Ns.bigDecimals.Ref.bigDecimal.insert(Set(bigDecimal1), bigDecimal2).transact
        _ <- Ns.dates.Ref.date.insert(Set(date1), date2).transact
        _ <- Ns.uuids.Ref.uuid.insert(Set(uuid1), uuid2).transact
        _ <- Ns.uris.Ref.uri.insert(Set(uri1), uri2).transact
        _ <- Ns.bytes.Ref.byte.insert(Set(byte1), byte2).transact
        _ <- Ns.shorts.Ref.short.insert(Set(short1), short2).transact
        _ <- Ns.chars.Ref.char.insert(Set(char1), char2).transact

        _ <- Ns.strings.hasNo(Ref.string_).Ref.string.query.get.map(_ ==> List((Set(string1), string2)))
        _ <- Ns.ints.hasNo(Ref.int_).Ref.int.query.get.map(_ ==> List((Set(int1), int2)))
        _ <- Ns.longs.hasNo(Ref.long_).Ref.long.query.get.map(_ ==> List((Set(long1), long2)))
        _ <- Ns.floats.hasNo(Ref.float_).Ref.float.query.get.map(_ ==> List((Set(float1), float2)))
        _ <- Ns.doubles.hasNo(Ref.double_).Ref.double.query.get.map(_ ==> List((Set(double1), double2)))
        _ <- Ns.booleans.hasNo(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((Set(boolean1), boolean2)))
        _ <- Ns.bigInts.hasNo(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((Set(bigInt1), bigInt2)))
        _ <- Ns.bigDecimals.hasNo(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((Set(bigDecimal1), bigDecimal2)))
        _ <- Ns.dates.hasNo(Ref.date_).Ref.date.query.get.map(_ ==> List((Set(date1), date2)))
        _ <- Ns.uuids.hasNo(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((Set(uuid1), uuid2)))
        _ <- Ns.uris.hasNo(Ref.uri_).Ref.uri.query.get.map(_ ==> List((Set(uri1), uri2)))
        _ <- Ns.bytes.hasNo(Ref.byte_).Ref.byte.query.get.map(_ ==> List((Set(byte1), byte2)))
        _ <- Ns.shorts.hasNo(Ref.short_).Ref.short.query.get.map(_ ==> List((Set(short1), short2)))
        _ <- Ns.chars.hasNo(Ref.char_).Ref.char.query.get.map(_ ==> List((Set(char1), char2)))
      } yield ()
    }


    "hasLt" - types { implicit conn =>
      for {
        _ <- Ns.strings.Ref.string.insert(Set(string1), string2).transact
        _ <- Ns.ints.Ref.int.insert(Set(int1), int2).transact
        _ <- Ns.longs.Ref.long.insert(Set(long1), long2).transact
        _ <- Ns.floats.Ref.float.insert(Set(float1), float2).transact
        _ <- Ns.doubles.Ref.double.insert(Set(double1), double2).transact
        _ <- Ns.booleans.Ref.boolean.insert(Set(boolean1), boolean2).transact
        _ <- Ns.bigInts.Ref.bigInt.insert(Set(bigInt1), bigInt2).transact
        _ <- Ns.bigDecimals.Ref.bigDecimal.insert(Set(bigDecimal1), bigDecimal2).transact
        _ <- Ns.dates.Ref.date.insert(Set(date1), date2).transact
        _ <- Ns.uuids.Ref.uuid.insert(Set(uuid1), uuid2).transact
        _ <- Ns.uris.Ref.uri.insert(Set(uri1), uri2).transact
        _ <- Ns.bytes.Ref.byte.insert(Set(byte1), byte2).transact
        _ <- Ns.shorts.Ref.short.insert(Set(short1), short2).transact
        _ <- Ns.chars.Ref.char.insert(Set(char1), char2).transact

        _ <- Ns.strings.hasLt(Ref.string_).Ref.string.query.get.map(_ ==> List((Set(string1), string2)))
        _ <- Ns.ints.hasLt(Ref.int_).Ref.int.query.get.map(_ ==> List((Set(int1), int2)))
        _ <- Ns.longs.hasLt(Ref.long_).Ref.long.query.get.map(_ ==> List((Set(long1), long2)))
        _ <- Ns.floats.hasLt(Ref.float_).Ref.float.query.get.map(_ ==> List((Set(float1), float2)))
        _ <- Ns.doubles.hasLt(Ref.double_).Ref.double.query.get.map(_ ==> List((Set(double1), double2)))
        _ <- Ns.booleans.hasLt(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((Set(boolean1), boolean2)))
        _ <- Ns.bigInts.hasLt(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((Set(bigInt1), bigInt2)))
        _ <- Ns.bigDecimals.hasLt(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((Set(bigDecimal1), bigDecimal2)))
        _ <- Ns.dates.hasLt(Ref.date_).Ref.date.query.get.map(_ ==> List((Set(date1), date2)))
        _ <- Ns.uuids.hasLt(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((Set(uuid1), uuid2)))
        _ <- Ns.uris.hasLt(Ref.uri_).Ref.uri.query.get.map(_ ==> List((Set(uri1), uri2)))
        _ <- Ns.bytes.hasLt(Ref.byte_).Ref.byte.query.get.map(_ ==> List((Set(byte1), byte2)))
        _ <- Ns.shorts.hasLt(Ref.short_).Ref.short.query.get.map(_ ==> List((Set(short1), short2)))
        _ <- Ns.chars.hasLt(Ref.char_).Ref.char.query.get.map(_ ==> List((Set(char1), char2)))
      } yield ()
    }


    "hasLe" - types { implicit conn =>
      for {
        _ <- Ns.strings.Ref.string.insert(Set(string1), string2).transact
        _ <- Ns.ints.Ref.int.insert(Set(int1), int2).transact
        _ <- Ns.longs.Ref.long.insert(Set(long1), long2).transact
        _ <- Ns.floats.Ref.float.insert(Set(float1), float2).transact
        _ <- Ns.doubles.Ref.double.insert(Set(double1), double2).transact
        _ <- Ns.booleans.Ref.boolean.insert(Set(boolean1), boolean2).transact
        _ <- Ns.bigInts.Ref.bigInt.insert(Set(bigInt1), bigInt2).transact
        _ <- Ns.bigDecimals.Ref.bigDecimal.insert(Set(bigDecimal1), bigDecimal2).transact
        _ <- Ns.dates.Ref.date.insert(Set(date1), date2).transact
        _ <- Ns.uuids.Ref.uuid.insert(Set(uuid1), uuid2).transact
        _ <- Ns.uris.Ref.uri.insert(Set(uri1), uri2).transact
        _ <- Ns.bytes.Ref.byte.insert(Set(byte1), byte2).transact
        _ <- Ns.shorts.Ref.short.insert(Set(short1), short2).transact
        _ <- Ns.chars.Ref.char.insert(Set(char1), char2).transact

        _ <- Ns.strings.hasLe(Ref.string_).Ref.string.query.get.map(_ ==> List((Set(string1), string2)))
        _ <- Ns.ints.hasLe(Ref.int_).Ref.int.query.get.map(_ ==> List((Set(int1), int2)))
        _ <- Ns.longs.hasLe(Ref.long_).Ref.long.query.get.map(_ ==> List((Set(long1), long2)))
        _ <- Ns.floats.hasLe(Ref.float_).Ref.float.query.get.map(_ ==> List((Set(float1), float2)))
        _ <- Ns.doubles.hasLe(Ref.double_).Ref.double.query.get.map(_ ==> List((Set(double1), double2)))
        _ <- Ns.booleans.hasLe(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((Set(boolean1), boolean2)))
        _ <- Ns.bigInts.hasLe(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((Set(bigInt1), bigInt2)))
        _ <- Ns.bigDecimals.hasLe(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((Set(bigDecimal1), bigDecimal2)))
        _ <- Ns.dates.hasLe(Ref.date_).Ref.date.query.get.map(_ ==> List((Set(date1), date2)))
        _ <- Ns.uuids.hasLe(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((Set(uuid1), uuid2)))
        _ <- Ns.uris.hasLe(Ref.uri_).Ref.uri.query.get.map(_ ==> List((Set(uri1), uri2)))
        _ <- Ns.bytes.hasLe(Ref.byte_).Ref.byte.query.get.map(_ ==> List((Set(byte1), byte2)))
        _ <- Ns.shorts.hasLe(Ref.short_).Ref.short.query.get.map(_ ==> List((Set(short1), short2)))
        _ <- Ns.chars.hasLe(Ref.char_).Ref.char.query.get.map(_ ==> List((Set(char1), char2)))
      } yield ()
    }


    "hasGt" - types { implicit conn =>
      for {
        _ <- Ns.strings.Ref.string.insert(Set(string2), string1).transact
        _ <- Ns.ints.Ref.int.insert(Set(int2), int1).transact
        _ <- Ns.longs.Ref.long.insert(Set(long2), long1).transact
        _ <- Ns.floats.Ref.float.insert(Set(float2), float1).transact
        _ <- Ns.doubles.Ref.double.insert(Set(double2), double1).transact
        _ <- Ns.booleans.Ref.boolean.insert(Set(boolean2), boolean1).transact
        _ <- Ns.bigInts.Ref.bigInt.insert(Set(bigInt2), bigInt1).transact
        _ <- Ns.bigDecimals.Ref.bigDecimal.insert(Set(bigDecimal2), bigDecimal1).transact
        _ <- Ns.dates.Ref.date.insert(Set(date2), date1).transact
        _ <- Ns.uuids.Ref.uuid.insert(Set(uuid2), uuid1).transact
        _ <- Ns.uris.Ref.uri.insert(Set(uri2), uri1).transact
        _ <- Ns.bytes.Ref.byte.insert(Set(byte2), byte1).transact
        _ <- Ns.shorts.Ref.short.insert(Set(short2), short1).transact
        _ <- Ns.chars.Ref.char.insert(Set(char2), char1).transact

        _ <- Ns.strings.hasGt(Ref.string_).Ref.string.query.get.map(_ ==> List((Set(string2), string1)))
        _ <- Ns.ints.hasGt(Ref.int_).Ref.int.query.get.map(_ ==> List((Set(int2), int1)))
        _ <- Ns.longs.hasGt(Ref.long_).Ref.long.query.get.map(_ ==> List((Set(long2), long1)))
        _ <- Ns.floats.hasGt(Ref.float_).Ref.float.query.get.map(_ ==> List((Set(float2), float1)))
        _ <- Ns.doubles.hasGt(Ref.double_).Ref.double.query.get.map(_ ==> List((Set(double2), double1)))
        _ <- Ns.booleans.hasGt(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((Set(boolean2), boolean1)))
        _ <- Ns.bigInts.hasGt(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((Set(bigInt2), bigInt1)))
        _ <- Ns.bigDecimals.hasGt(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((Set(bigDecimal2), bigDecimal1)))
        _ <- Ns.dates.hasGt(Ref.date_).Ref.date.query.get.map(_ ==> List((Set(date2), date1)))
        _ <- Ns.uuids.hasGt(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((Set(uuid2), uuid1)))
        _ <- Ns.uris.hasGt(Ref.uri_).Ref.uri.query.get.map(_ ==> List((Set(uri2), uri1)))
        _ <- Ns.bytes.hasGt(Ref.byte_).Ref.byte.query.get.map(_ ==> List((Set(byte2), byte1)))
        _ <- Ns.shorts.hasGt(Ref.short_).Ref.short.query.get.map(_ ==> List((Set(short2), short1)))
        _ <- Ns.chars.hasGt(Ref.char_).Ref.char.query.get.map(_ ==> List((Set(char2), char1)))
      } yield ()
    }


    "hasGe" - types { implicit conn =>
      for {
        _ <- Ns.strings.Ref.string.insert(Set(string2), string1).transact
        _ <- Ns.ints.Ref.int.insert(Set(int2), int1).transact
        _ <- Ns.longs.Ref.long.insert(Set(long2), long1).transact
        _ <- Ns.floats.Ref.float.insert(Set(float2), float1).transact
        _ <- Ns.doubles.Ref.double.insert(Set(double2), double1).transact
        _ <- Ns.booleans.Ref.boolean.insert(Set(boolean2), boolean1).transact
        _ <- Ns.bigInts.Ref.bigInt.insert(Set(bigInt2), bigInt1).transact
        _ <- Ns.bigDecimals.Ref.bigDecimal.insert(Set(bigDecimal2), bigDecimal1).transact
        _ <- Ns.dates.Ref.date.insert(Set(date2), date1).transact
        _ <- Ns.uuids.Ref.uuid.insert(Set(uuid2), uuid1).transact
        _ <- Ns.uris.Ref.uri.insert(Set(uri2), uri1).transact
        _ <- Ns.bytes.Ref.byte.insert(Set(byte2), byte1).transact
        _ <- Ns.shorts.Ref.short.insert(Set(short2), short1).transact
        _ <- Ns.chars.Ref.char.insert(Set(char2), char1).transact

        _ <- Ns.strings.hasGe(Ref.string_).Ref.string.query.get.map(_ ==> List((Set(string2), string1)))
        _ <- Ns.ints.hasGe(Ref.int_).Ref.int.query.get.map(_ ==> List((Set(int2), int1)))
        _ <- Ns.longs.hasGe(Ref.long_).Ref.long.query.get.map(_ ==> List((Set(long2), long1)))
        _ <- Ns.floats.hasGe(Ref.float_).Ref.float.query.get.map(_ ==> List((Set(float2), float1)))
        _ <- Ns.doubles.hasGe(Ref.double_).Ref.double.query.get.map(_ ==> List((Set(double2), double1)))
        _ <- Ns.booleans.hasGe(Ref.boolean_).Ref.boolean.query.get.map(_ ==> List((Set(boolean2), boolean1)))
        _ <- Ns.bigInts.hasGe(Ref.bigInt_).Ref.bigInt.query.get.map(_ ==> List((Set(bigInt2), bigInt1)))
        _ <- Ns.bigDecimals.hasGe(Ref.bigDecimal_).Ref.bigDecimal.query.get.map(_ ==> List((Set(bigDecimal2), bigDecimal1)))
        _ <- Ns.dates.hasGe(Ref.date_).Ref.date.query.get.map(_ ==> List((Set(date2), date1)))
        _ <- Ns.uuids.hasGe(Ref.uuid_).Ref.uuid.query.get.map(_ ==> List((Set(uuid2), uuid1)))
        _ <- Ns.uris.hasGe(Ref.uri_).Ref.uri.query.get.map(_ ==> List((Set(uri2), uri1)))
        _ <- Ns.bytes.hasGe(Ref.byte_).Ref.byte.query.get.map(_ ==> List((Set(byte2), byte1)))
        _ <- Ns.shorts.hasGe(Ref.short_).Ref.short.query.get.map(_ ==> List((Set(short2), short1)))
        _ <- Ns.chars.hasGe(Ref.char_).Ref.char.query.get.map(_ ==> List((Set(char2), char1)))
      } yield ()
    }
  }
}
