package molecule.db.datomic.test.attrTypes

import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object CardOneTypes extends DatomicTestSuite {


  lazy val tests = Tests {

    "mandatory" - cardOneTypes { implicit conn =>
      One.string(string1).save.transact
      One.int(int1).save.transact
      One.long.apply(long1).save.transact
      One.float(float1).save.transact
      One.double(double1).save.transact
      One.boolean(boolean1).save.transact
      One.bigInt(bigInt1).save.transact
      One.bigDecimal(bigDecimal1).save.transact
      One.date(date1).save.transact
      One.uuid(uuid1).save.transact
      One.uri(uri1).save.transact
      One.char(char1).save.transact
      One.byte(byte1).save.transact
      One.short(short1).save.transact

      One.string.query.get ==> List(string1)
      One.int.query.get ==> List(int1)
      One.long.query.get ==> List(long1)
      One.float.query.get ==> List(float1)
      One.double.query.get ==> List(double1)
      One.boolean.query.get ==> List(boolean1)
      One.bigInt.query.get ==> List(bigInt1)
      One.bigDecimal.query.get ==> List(bigDecimal1)
      One.date.query.get ==> List(date1)
      One.uuid.query.get ==> List(uuid1)
      One.uri.query.get ==> List(uri1)
      One.char.query.get ==> List(char1)
      One.byte.query.get ==> List(byte1)
      One.short.query.get ==> List(short1)
    }


//    "optional" - cardOneTypes { implicit conn =>
//      val dataString = List((0, None), (1, Some(string1)))
//      val dataInt = List((0, None), (1, Some(int1)))
//      val dataLong = List((0, None), (1, Some(long1)))
//      val dataFloat = List((0, None), (1, Some(float1)))
//      val dataDouble = List((0, None), (1, Some(double1)))
//      val dataBoolean = List((0, None), (1, Some(boolean1)))
//      val dataBigInt = List((0, None), (1, Some(bigInt1)))
//      val dataBigDecimal = List((0, None), (1, Some(bigDecimal1)))
//      val dataDate = List((0, None), (1, Some(date1)))
//      val dataUUID = List((0, None), (1, Some(uuid1)))
//      val dataURI = List((0, None), (1, Some(uri1)))
//      val dataChar = List((0, None), (1, Some(char1)))
//      val dataByte = List((0, None), (1, Some(byte1)))
//      val dataShort = List((0, None), (1, Some(short1)))
//
//      One.n.string_? insert dataString
//      One.n.int_? insert dataInt
//      One.n.long_? insert dataLong
//      One.n.float_? insert dataFloat
//      One.n.double_? insert dataDouble
//      One.n.boolean_? insert dataBoolean
//      One.n.bigInt_? insert dataBigInt
//      One.n.bigDecimal_? insert dataBigDecimal
//      One.n.date_? insert dataDate
//      One.n.uuid_? insert dataUUID
//      One.n.uri_? insert dataURI
//      One.n.char_? insert dataChar
//      One.n.byte_? insert dataByte
//      One.n.short_? insert dataShort
//
//      One.n.string_?.query.get ==> dataString
//      One.n.int_?.query.get ==> dataInt
//      One.n.long_?.query.get ==> dataLong
//      One.n.float_?.query.get ==> dataFloat
//      One.n.double_?.query.get ==> dataDouble
//      One.n.boolean_?.query.get ==> dataBoolean
//      One.n.bigInt_?.query.get ==> dataBigInt
//      One.n.bigDecimal_?.query.get ==> dataBigDecimal
//      One.n.date_?.query.get ==> dataDate
//      One.n.uuid_?.query.get ==> dataUUID
//      One.n.uri_?.query.get ==> dataURI
//      One.n.char_?.query.get ==> dataChar
//      One.n.byte_?.query.get ==> dataByte
//      One.n.short_?.query.get ==> dataShort
//    }


    "tacit" - cardOneTypes { implicit conn =>
      One.n.string.insert((1, string1)).transact
      One.n.int.insert((1, int1)).transact
      One.n.long.insert((1, long1)).transact
      One.n.float.insert((1, float1)).transact
      One.n.double.insert((1, double1)).transact
      One.n.boolean.insert((1, boolean1)).transact
      One.n.bigInt.insert((1, bigInt1)).transact
      One.n.bigDecimal.insert((1, bigDecimal1)).transact
      One.n.date.insert((1, date1)).transact
      One.n.uuid.insert((1, uuid1)).transact
      One.n.uri.insert((1, uri1)).transact
      One.n.char.insert((1, char1)).transact
      One.n.byte.insert((1, byte1)).transact
      One.n.short.insert((1, short1)).transact

      One.n.string_.query.get ==> List(1)
      One.n.int_.query.get ==> List(1)
      One.n.long_.query.get ==> List(1)
      One.n.float_.query.get ==> List(1)
      One.n.double_.query.get ==> List(1)
      One.n.boolean_.query.get ==> List(1)
      One.n.bigInt_.query.get ==> List(1)
      One.n.bigDecimal_.query.get ==> List(1)
      One.n.date_.query.get ==> List(1)
      One.n.uuid_.query.get ==> List(1)
      One.n.uri_.query.get ==> List(1)
      One.n.char_.query.get ==> List(1)
      One.n.byte_.query.get ==> List(1)
      One.n.short_.query.get ==> List(1)
    }
  }
}
