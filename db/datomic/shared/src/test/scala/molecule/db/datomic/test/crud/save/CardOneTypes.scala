package molecule.db.datomic.test.crud.save

import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object CardOneTypes extends DatomicTestSuite {


  lazy val tests = Tests {

    "mandatory" - cardOne { implicit conn =>
      One.string(string1).save.transact
//      One.int(int1).save.transact
//      One.long.apply(long1).save.transact
//      One.float(float1).save.transact
//      One.double(double1).save.transact
//      One.boolean(boolean1).save.transact
//      One.bigInt(bigInt1).save.transact
//      One.bigDecimal(bigDecimal1).save.transact
//      One.date(date1).save.transact
//      One.uuid(uuid1).save.transact
//      One.uri(uri1).save.transact
//      One.char(char1).save.transact
//      One.byte(byte1).save.transact
//      One.short(short1).save.transact

      One.string.query.get ==> List(string1)
//      One.int.query.get ==> List(int1)
//      One.long.query.get ==> List(long1)
//      One.float.query.get ==> List(float1)
//      One.double.query.get ==> List(double1)
//      One.boolean.query.get ==> List(boolean1)
//      One.bigInt.query.get ==> List(bigInt1)
//      One.bigDecimal.query.get ==> List(bigDecimal1)
//      One.date.query.get ==> List(date1)
//      One.uuid.query.get ==> List(uuid1)
//      One.uri.query.get ==> List(uri1)
//      One.char.query.get ==> List(char1)
//      One.byte.query.get ==> List(byte1)
//      One.short.query.get ==> List(short1)
    }


    "tacit" - cardOne { implicit conn =>
      One.n.string.insert((11, string1)).transact
//      One.n.int.insert((12, int1)).transact
//      One.n.long.insert((13, long1)).transact
//      One.n.float.insert((14, float1)).transact
//      One.n.double.insert((15, double1)).transact
//      One.n.boolean.insert((16, boolean1)).transact
//      One.n.bigInt.insert((17, bigInt1)).transact
//      One.n.bigDecimal.insert((18, bigDecimal1)).transact
//      One.n.date.insert((19, date1)).transact
//      One.n.uuid.insert((20, uuid1)).transact
//      One.n.uri.insert((21, uri1)).transact
//      One.n.char.insert((22, char1)).transact
//      One.n.byte.insert((23, byte1)).transact
//      One.n.short.insert((24, short1)).transact

      One.n.string_.query.get ==> List(11)
//      One.n.int_.query.get ==> List(12)
//      One.n.long_.query.get ==> List(13)
//      One.n.float_.query.get ==> List(14)
//      One.n.double_.query.get ==> List(15)
//      One.n.boolean_.query.get ==> List(16)
//      One.n.bigInt_.query.get ==> List(17)
//      One.n.bigDecimal_.query.get ==> List(18)
//      One.n.date_.query.get ==> List(19)
//      One.n.uuid_.query.get ==> List(20)
//      One.n.uri_.query.get ==> List(21)
//      One.n.char_.query.get ==> List(22)
//      One.n.byte_.query.get ==> List(23)
//      One.n.short_.query.get ==> List(24)
    }
//
//
//    "optional" - cardOne { implicit conn =>
//      val dataString     = List((11, None), (11, Some(string1)))
//      val dataInt        = List((12, None), (12, Some(int1)))
//      val dataLong       = List((13, None), (13, Some(long1)))
//      val dataFloat      = List((14, None), (14, Some(float1)))
//      val dataDouble     = List((15, None), (15, Some(double1)))
//      val dataBoolean    = List((16, None), (16, Some(boolean1)))
//      val dataBigInt     = List((17, None), (17, Some(bigInt1)))
//      val dataBigDecimal = List((18, None), (18, Some(bigDecimal1)))
//      val dataDate       = List((19, None), (19, Some(date1)))
//      val dataUUID       = List((20, None), (20, Some(uuid1)))
//      val dataURI        = List((21, None), (21, Some(uri1)))
//      val dataChar       = List((22, None), (22, Some(char1)))
//      val dataByte       = List((23, None), (23, Some(byte1)))
//      val dataShort      = List((24, None), (24, Some(short1)))
//
//      One.n.string_?.insert(dataString).transact
//      One.n.int_?.insert(dataInt).transact
//      One.n.long_?.insert(dataLong).transact
//      One.n.float_?.insert(dataFloat).transact
//      One.n.double_?.insert(dataDouble).transact
//      One.n.boolean_?.insert(dataBoolean).transact
//      One.n.bigInt_?.insert(dataBigInt).transact
//      One.n.bigDecimal_?.insert(dataBigDecimal).transact
//      One.n.date_?.insert(dataDate).transact
//      One.n.uuid_?.insert(dataUUID).transact
//      One.n.uri_?.insert(dataURI).transact
//      One.n.char_?.insert(dataChar).transact
//      One.n.byte_?.insert(dataByte).transact
//      One.n.short_?.insert(dataShort).transact
//
//      One.n(11).string_?.query.get ==> dataString
//      One.n(12).int_?.query.get ==> dataInt
//      One.n(13).long_?.query.get ==> dataLong
//      One.n(14).float_?.query.get ==> dataFloat
//      One.n(15).double_?.query.get ==> dataDouble
//      One.n(16).boolean_?.query.get ==> dataBoolean
//      One.n(17).bigInt_?.query.get ==> dataBigInt
//      One.n(18).bigDecimal_?.query.get ==> dataBigDecimal
//      One.n(19).date_?.query.get ==> dataDate
//      One.n(20).uuid_?.query.get ==> dataUUID
//      One.n(21).uri_?.query.get ==> dataURI
//      One.n(22).char_?.query.get ==> dataChar
//      One.n(23).byte_?.query.get ==> dataByte
//      One.n(24).short_?.query.get ==> dataShort
//    }
  }
}
