package molecule.db.datomic.test.crud.save

import molecule.coreTests.dataModels.core.types.dsl.TypesSet._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object CardSetTypes extends DatomicTestSuite {



  lazy val tests = Tests {

//    "mandatory" - typesSet { implicit conn =>
//      NsSet.strings(string1).save.transact
//      NsSet.ints(int1).save.transact
//      NsSet.longs.apply(long1).save.transact
//      NsSet.floats(float1).save.transact
//      NsSet.doubles(double1).save.transact
//      NsSet.booleans(boolean1).save.transact
//      NsSet.bigInts(bigInt1).save.transact
//      NsSet.bigDecimals(bigDecimal1).save.transact
//      NsSet.dates(date1).save.transact
//      NsSet.uuids(uuid1).save.transact
//      NsSet.uris(uri1).save.transact
//      NsSet.chars(char1).save.transact
//      NsSet.bytes(byte1).save.transact
//      NsSet.shorts(short1).save.transact
//
//      NsSet.strings.query.get ==> List(Set(string1))
//      NsSet.ints.query.get ==> List(Set(int1))
//      NsSet.longs.query.get ==> List(Set(long1))
//      NsSet.floats.query.get ==> List(Set(float1))
//      NsSet.doubles.query.get ==> List(Set(double1))
//      NsSet.booleans.query.get ==> List(Set(boolean1))
//      NsSet.bigInts.query.get ==> List(Set(bigInt1))
//      NsSet.bigDecimals.query.get ==> List(Set(bigDecimal1))
//      NsSet.dates.query.get ==> List(Set(date1))
//      NsSet.uuids.query.get ==> List(Set(uuid1))
//      NsSet.uris.query.get ==> List(Set(uri1))
//      NsSet.chars.query.get ==> List(Set(char1))
//      NsSet.bytes.query.get ==> List(Set(byte1))
//      NsSet.shorts.query.get ==> List(Set(short1))
//    }


    "tacit" - typesSet { implicit conn =>
//      NsSet.n.strings.insert((11, Set(string1, string2))).transact
      NsSet.n.strings_?.insert((11, Some(Set(string1, string2)))).transact
//      NsSet.n.ints.insert((12, Set(int1))).transact
//      NsSet.n.longs.insert((13, Set(long1))).transact
//      NsSet.n.floats.insert((14, Set(float1))).transact
//      NsSet.n.doubles.insert((15, Set(double1))).transact
//      NsSet.n.booleans.insert((16, Set(boolean1))).transact
//      NsSet.n.bigInts.insert((17, Set(bigInt1))).transact
//      NsSet.n.bigDecimals.insert((18, Set(bigDecimal1))).transact
//      NsSet.n.dates.insert((19, Set(date1))).transact
//      NsSet.n.uuids.insert((20, Set(uuid1))).transact
//      NsSet.n.uris.insert((21, Set(uri1))).transact
//      NsSet.n.chars.insert((22, Set(char1))).transact
//      NsSet.n.bytes.insert((23, Set(byte1))).transact
//      NsSet.n.shorts.insert((24, Set(short1))).transact

//      NsSet.n.strings_.query.get ==> List(11)
//      NsSet.n.ints_.query.get ==> List(12)
//      NsSet.n.longs_.query.get ==> List(13)
//      NsSet.n.floats_.query.get ==> List(14)
//      NsSet.n.doubles_.query.get ==> List(15)
//      NsSet.n.booleans_.query.get ==> List(16)
//      NsSet.n.bigInts_.query.get ==> List(17)
//      NsSet.n.bigDecimals_.query.get ==> List(18)
//      NsSet.n.dates_.query.get ==> List(19)
//      NsSet.n.uuids_.query.get ==> List(20)
//      NsSet.n.uris_.query.get ==> List(21)
//      NsSet.n.chars_.query.get ==> List(22)
//      NsSet.n.bytes_.query.get ==> List(23)
//      NsSet.n.shorts_.query.get ==> List(24)


      NsSet.strings.query.get ==> List(Set(string1, string2))
//      NsSet.ints.query.get ==> List(Set(int1))
//      NsSet.longs.query.get ==> List(Set(long1))
//      NsSet.floats.query.get ==> List(Set(float1))
//      NsSet.doubles.query.get ==> List(Set(double1))
//      NsSet.booleans.query.get ==> List(Set(boolean1))
//      NsSet.bigInts.query.get ==> List(Set(bigInt1))
//      NsSet.bigDecimals.query.get ==> List(Set(bigDecimal1))
//      NsSet.dates.query.get ==> List(Set(date1))
//      NsSet.uuids.query.get ==> List(Set(uuid1))
//      NsSet.uris.query.get ==> List(Set(uri1))
//      NsSet.chars.query.get ==> List(Set(char1))
//      NsSet.bytes.query.get ==> List(Set(byte1))
//      NsSet.shorts.query.get ==> List(Set(short1))
    }
//
//
//    "optional" - typesSet { implicit conn =>
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
//      NsSet.n.strings_?.insert(dataString).transact
//      NsSet.n.ints_?.insert(dataInt).transact
//      NsSet.n.longs_?.insert(dataLong).transact
//      NsSet.n.floats_?.insert(dataFloat).transact
//      NsSet.n.doubles_?.insert(dataDouble).transact
//      NsSet.n.booleans_?.insert(dataBoolean).transact
//      NsSet.n.bigInts_?.insert(dataBigInt).transact
//      NsSet.n.bigDecimals_?.insert(dataBigDecimal).transact
//      NsSet.n.dates_?.insert(dataDate).transact
//      NsSet.n.uuids_?.insert(dataUUID).transact
//      NsSet.n.uris_?.insert(dataURI).transact
//      NsSet.n.chars_?.insert(dataChar).transact
//      NsSet.n.bytes_?.insert(dataByte).transact
//      NsSet.n.shorts_?.insert(dataShort).transact
//
//      NsSet.n(11).strings_?.query.get ==> dataString
//      NsSet.n(12).ints_?.query.get ==> dataInt
//      NsSet.n(13).longs_?.query.get ==> dataLong
//      NsSet.n(14).floats_?.query.get ==> dataFloat
//      NsSet.n(15).doubles_?.query.get ==> dataDouble
//      NsSet.n(16).booleans_?.query.get ==> dataBoolean
//      NsSet.n(17).bigInts_?.query.get ==> dataBigInt
//      NsSet.n(18).bigDecimals_?.query.get ==> dataBigDecimal
//      NsSet.n(19).dates_?.query.get ==> dataDate
//      NsSet.n(20).uuids_?.query.get ==> dataUUID
//      NsSet.n(21).uris_?.query.get ==> dataURI
//      NsSet.n(22).chars_?.query.get ==> dataChar
//      NsSet.n(23).bytes_?.query.get ==> dataByte
//      NsSet.n(24).shorts_?.query.get ==> dataShort
//    }
  }
}
