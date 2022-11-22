package molecule.db.datomic.test.crud.insert

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object CardOneTypes extends DatomicTestSuite {


  lazy val tests = Tests {

    "mandatory" - types { implicit conn =>
      Ns.string(string1).save.transact
      Ns.int(int1).save.transact
      Ns.long.apply(long1).save.transact
      Ns.float(float1).save.transact
      Ns.double(double1).save.transact
      Ns.boolean(boolean1).save.transact
      Ns.bigInt(bigInt1).save.transact
      Ns.bigDecimal(bigDecimal1).save.transact
      Ns.date(date1).save.transact
      Ns.uuid(uuid1).save.transact
      Ns.uri(uri1).save.transact
      Ns.char(char1).save.transact
      Ns.byte(byte1).save.transact
      Ns.short(short1).save.transact

      Ns.string.query.get ==> List(string1)
      Ns.int.query.get ==> List(int1)
      Ns.long.query.get ==> List(long1)
      Ns.float.query.get ==> List(float1)
      Ns.double.query.get ==> List(double1)
      Ns.boolean.query.get ==> List(boolean1)
      Ns.bigInt.query.get ==> List(bigInt1)
      Ns.bigDecimal.query.get ==> List(bigDecimal1)
      Ns.date.query.get ==> List(date1)
      Ns.uuid.query.get ==> List(uuid1)
      Ns.uri.query.get ==> List(uri1)
      Ns.char.query.get ==> List(char1)
      Ns.byte.query.get ==> List(byte1)
      Ns.short.query.get ==> List(short1)
    }


    "tacit" - types { implicit conn =>
      Ns.n.string.insert(11, string1).transact
      Ns.n.int.insert((12, int1)).transact
      Ns.n.long.insert((13, long1)).transact
      Ns.n.float.insert((14, float1)).transact
      Ns.n.double.insert((15, double1)).transact
      Ns.n.boolean.insert((16, boolean1)).transact
      Ns.n.bigInt.insert((17, bigInt1)).transact
      Ns.n.bigDecimal.insert((18, bigDecimal1)).transact
      Ns.n.date.insert((19, date1)).transact
      Ns.n.uuid.insert((20, uuid1)).transact
      Ns.n.uri.insert((21, uri1)).transact
      Ns.n.char.insert((22, char1)).transact
      Ns.n.byte.insert((23, byte1)).transact
      Ns.n.short.insert((24, short1)).transact

      Ns.n.string_.query.get ==> List(11)
      Ns.n.int_.query.get ==> List(12)
      Ns.n.long_.query.get ==> List(13)
      Ns.n.float_.query.get ==> List(14)
      Ns.n.double_.query.get ==> List(15)
      Ns.n.boolean_.query.get ==> List(16)
      Ns.n.bigInt_.query.get ==> List(17)
      Ns.n.bigDecimal_.query.get ==> List(18)
      Ns.n.date_.query.get ==> List(19)
      Ns.n.uuid_.query.get ==> List(20)
      Ns.n.uri_.query.get ==> List(21)
      Ns.n.char_.query.get ==> List(22)
      Ns.n.byte_.query.get ==> List(23)
      Ns.n.short_.query.get ==> List(24)
    }


    "optional" - types { implicit conn =>
      val dataString     = List((11, None), (11, Some(string1)))
      val dataInt        = List((12, None), (12, Some(int1)))
      val dataLong       = List((13, None), (13, Some(long1)))
      val dataFloat      = List((14, None), (14, Some(float1)))
      val dataDouble     = List((15, None), (15, Some(double1)))
      val dataBoolean    = List((16, None), (16, Some(boolean1)))
      val dataBigInt     = List((17, None), (17, Some(bigInt1)))
      val dataBigDecimal = List((18, None), (18, Some(bigDecimal1)))
      val dataDate       = List((19, None), (19, Some(date1)))
      val dataUUID       = List((20, None), (20, Some(uuid1)))
      val dataURI        = List((21, None), (21, Some(uri1)))
      val dataChar       = List((22, None), (22, Some(char1)))
      val dataByte       = List((23, None), (23, Some(byte1)))
      val dataShort      = List((24, None), (24, Some(short1)))

      Ns.n.string_?.insert(dataString).transact
      Ns.n.int_?.insert(dataInt).transact
      Ns.n.long_?.insert(dataLong).transact
      Ns.n.float_?.insert(dataFloat).transact
      Ns.n.double_?.insert(dataDouble).transact
      Ns.n.boolean_?.insert(dataBoolean).transact
      Ns.n.bigInt_?.insert(dataBigInt).transact
      Ns.n.bigDecimal_?.insert(dataBigDecimal).transact
      Ns.n.date_?.insert(dataDate).transact
      Ns.n.uuid_?.insert(dataUUID).transact
      Ns.n.uri_?.insert(dataURI).transact
      Ns.n.char_?.insert(dataChar).transact
      Ns.n.byte_?.insert(dataByte).transact
      Ns.n.short_?.insert(dataShort).transact

      Ns.n(11).string_?.query.get ==> dataString
      Ns.n(12).int_?.query.get ==> dataInt
      Ns.n(13).long_?.query.get ==> dataLong
      Ns.n(14).float_?.query.get ==> dataFloat
      Ns.n(15).double_?.query.get ==> dataDouble
      Ns.n(16).boolean_?.query.get ==> dataBoolean
      Ns.n(17).bigInt_?.query.get ==> dataBigInt
      Ns.n(18).bigDecimal_?.query.get ==> dataBigDecimal
      Ns.n(19).date_?.query.get ==> dataDate
      Ns.n(20).uuid_?.query.get ==> dataUUID
      Ns.n(21).uri_?.query.get ==> dataURI
      Ns.n(22).char_?.query.get ==> dataChar
      Ns.n(23).byte_?.query.get ==> dataByte
      Ns.n(24).short_?.query.get ==> dataShort
    }
  }
}
