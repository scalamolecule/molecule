package molecule.db.datomic.test.crud.insert

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object CardSetTypes extends DatomicTestSuite {



  lazy val tests = Tests {

//    "mandatory" - types { implicit conn =>
//      Ns.strings(string1).save.transact
//      Ns.ints(int1).save.transact
//      Ns.longs.apply(long1).save.transact
//      Ns.floats(float1).save.transact
//      Ns.doubles(double1).save.transact
//      Ns.booleans(boolean1).save.transact
//      Ns.bigInts(bigInt1).save.transact
//      Ns.bigDecimals(bigDecimal1).save.transact
//      Ns.dates(date1).save.transact
//      Ns.uuids(uuid1).save.transact
//      Ns.uris(uri1).save.transact
//      Ns.chars(char1).save.transact
//      Ns.bytes(byte1).save.transact
//      Ns.shorts(short1).save.transact
//
//      Ns.strings.query.get ==> List(Set(string1))
//      Ns.ints.query.get ==> List(Set(int1))
//      Ns.longs.query.get ==> List(Set(long1))
//      Ns.floats.query.get ==> List(Set(float1))
//      Ns.doubles.query.get ==> List(Set(double1))
//      Ns.booleans.query.get ==> List(Set(boolean1))
//      Ns.bigInts.query.get ==> List(Set(bigInt1))
//      Ns.bigDecimals.query.get ==> List(Set(bigDecimal1))
//      Ns.dates.query.get ==> List(Set(date1))
//      Ns.uuids.query.get ==> List(Set(uuid1))
//      Ns.uris.query.get ==> List(Set(uri1))
//      Ns.chars.query.get ==> List(Set(char1))
//      Ns.bytes.query.get ==> List(Set(byte1))
//      Ns.shorts.query.get ==> List(Set(short1))
//    }


    "tacit" - types { implicit conn =>
//      Ns.n.strings.insert((11, Set(string1, string2))).transact
      Ns.n.strings_?.insert((11, Some(Set(string1, string2)))).transact
//      Ns.n.ints.insert((12, Set(int1))).transact
//      Ns.n.longs.insert((13, Set(long1))).transact
//      Ns.n.floats.insert((14, Set(float1))).transact
//      Ns.n.doubles.insert((15, Set(double1))).transact
//      Ns.n.booleans.insert((16, Set(boolean1))).transact
//      Ns.n.bigInts.insert((17, Set(bigInt1))).transact
//      Ns.n.bigDecimals.insert((18, Set(bigDecimal1))).transact
//      Ns.n.dates.insert((19, Set(date1))).transact
//      Ns.n.uuids.insert((20, Set(uuid1))).transact
//      Ns.n.uris.insert((21, Set(uri1))).transact
//      Ns.n.chars.insert((22, Set(char1))).transact
//      Ns.n.bytes.insert((23, Set(byte1))).transact
//      Ns.n.shorts.insert((24, Set(short1))).transact

//      Ns.n.strings_.query.get ==> List(11)
//      Ns.n.ints_.query.get ==> List(12)
//      Ns.n.longs_.query.get ==> List(13)
//      Ns.n.floats_.query.get ==> List(14)
//      Ns.n.doubles_.query.get ==> List(15)
//      Ns.n.booleans_.query.get ==> List(16)
//      Ns.n.bigInts_.query.get ==> List(17)
//      Ns.n.bigDecimals_.query.get ==> List(18)
//      Ns.n.dates_.query.get ==> List(19)
//      Ns.n.uuids_.query.get ==> List(20)
//      Ns.n.uris_.query.get ==> List(21)
//      Ns.n.chars_.query.get ==> List(22)
//      Ns.n.bytes_.query.get ==> List(23)
//      Ns.n.shorts_.query.get ==> List(24)


      Ns.strings.query.get ==> List(Set(string1, string2))
//      Ns.ints.query.get ==> List(Set(int1))
//      Ns.longs.query.get ==> List(Set(long1))
//      Ns.floats.query.get ==> List(Set(float1))
//      Ns.doubles.query.get ==> List(Set(double1))
//      Ns.booleans.query.get ==> List(Set(boolean1))
//      Ns.bigInts.query.get ==> List(Set(bigInt1))
//      Ns.bigDecimals.query.get ==> List(Set(bigDecimal1))
//      Ns.dates.query.get ==> List(Set(date1))
//      Ns.uuids.query.get ==> List(Set(uuid1))
//      Ns.uris.query.get ==> List(Set(uri1))
//      Ns.chars.query.get ==> List(Set(char1))
//      Ns.bytes.query.get ==> List(Set(byte1))
//      Ns.shorts.query.get ==> List(Set(short1))
    }
//
//
//    "optional" - types { implicit conn =>
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
//      Ns.n.strings_?.insert(dataString).transact
//      Ns.n.ints_?.insert(dataInt).transact
//      Ns.n.longs_?.insert(dataLong).transact
//      Ns.n.floats_?.insert(dataFloat).transact
//      Ns.n.doubles_?.insert(dataDouble).transact
//      Ns.n.booleans_?.insert(dataBoolean).transact
//      Ns.n.bigInts_?.insert(dataBigInt).transact
//      Ns.n.bigDecimals_?.insert(dataBigDecimal).transact
//      Ns.n.dates_?.insert(dataDate).transact
//      Ns.n.uuids_?.insert(dataUUID).transact
//      Ns.n.uris_?.insert(dataURI).transact
//      Ns.n.chars_?.insert(dataChar).transact
//      Ns.n.bytes_?.insert(dataByte).transact
//      Ns.n.shorts_?.insert(dataShort).transact
//
//      Ns.n(11).strings_?.query.get ==> dataString
//      Ns.n(12).ints_?.query.get ==> dataInt
//      Ns.n(13).longs_?.query.get ==> dataLong
//      Ns.n(14).floats_?.query.get ==> dataFloat
//      Ns.n(15).doubles_?.query.get ==> dataDouble
//      Ns.n(16).booleans_?.query.get ==> dataBoolean
//      Ns.n(17).bigInts_?.query.get ==> dataBigInt
//      Ns.n(18).bigDecimals_?.query.get ==> dataBigDecimal
//      Ns.n(19).dates_?.query.get ==> dataDate
//      Ns.n(20).uuids_?.query.get ==> dataUUID
//      Ns.n(21).uris_?.query.get ==> dataURI
//      Ns.n(22).chars_?.query.get ==> dataChar
//      Ns.n(23).bytes_?.query.get ==> dataByte
//      Ns.n(24).shorts_?.query.get ==> dataShort
//    }
  }
}
