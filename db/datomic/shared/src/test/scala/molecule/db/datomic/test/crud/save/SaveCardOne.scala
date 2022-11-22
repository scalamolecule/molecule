package molecule.db.datomic.test.crud.save

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object SaveCardOne extends DatomicTestSuite {


  lazy val tests = Tests {

    "mandatory" - types { implicit conn =>
      // Can't save multiple values (use insert for that)
      intercept[MoleculeException] {
        Ns.n(1, 2).save.transact
      }.message ==> "Can only save one value for attribute `:Ns/n`. Found: ArraySeq(1, 2)"

      intercept[MoleculeException] {
        Ns.n(Seq(1, 2)).save.transact
      }.message ==> "Can only save one value for attribute `:Ns/n`. Found: List(1, 2)"

      // Saving empty list of values is ignored
      Ns.int(Seq.empty[Int]).save.transact
      Ns.int.query.get ==> List()


      Ns.string(string1).save.transact
      Ns.string(Seq(string2)).save.transact

      Ns.int(int1).save.transact
      Ns.int(Seq(int2)).save.transact

      Ns.long(long1).save.transact
      Ns.long(Seq(long2)).save.transact

      Ns.float(float1).save.transact
      Ns.float(Seq(float2)).save.transact

      Ns.double(double1).save.transact
      Ns.double(Seq(double2)).save.transact

      Ns.boolean(boolean1).save.transact
      Ns.boolean(Seq(boolean2)).save.transact

      Ns.bigInt(bigInt1).save.transact
      Ns.bigInt(Seq(bigInt2)).save.transact

      Ns.bigDecimal(bigDecimal1).save.transact
      Ns.bigDecimal(Seq(bigDecimal2)).save.transact

      Ns.date(date1).save.transact
      Ns.date(Seq(date2)).save.transact

      Ns.uuid(uuid1).save.transact
      Ns.uuid(Seq(uuid2)).save.transact

      Ns.uri(uri1).save.transact
      Ns.uri(Seq(uri2)).save.transact

      Ns.char(char1).save.transact
      Ns.char(Seq(char2)).save.transact

      Ns.byte(byte1).save.transact
      Ns.byte(Seq(byte2)).save.transact

      Ns.short(short1).save.transact
      Ns.short(Seq(short2)).save.transact


      Ns.string.a1.query.get ==> List(string1, string2)
      Ns.int.a1.query.get ==> List(int1, int2)
      Ns.long.a1.query.get ==> List(long1, long2)
      Ns.float.a1.query.get ==> List(float1, float2)
      Ns.double.a1.query.get ==> List(double1, double2)
      Ns.boolean.a1.query.get ==> List(boolean2, boolean1)
      Ns.bigInt.a1.query.get ==> List(bigInt1, bigInt2)
      Ns.bigDecimal.a1.query.get ==> List(bigDecimal1, bigDecimal2)
      Ns.date.a1.query.get ==> List(date1, date2)
      Ns.uuid.a1.query.get ==> List(uuid1, uuid2)
      Ns.uri.a1.query.get ==> List(uri1, uri2)
      Ns.char.a1.query.get ==> List(char1, char2)
      Ns.byte.a1.query.get ==> List(byte1, byte2)
      Ns.short.a1.query.get ==> List(short1, short2)
    }


    "optional" - types { implicit conn =>
      // Can't save multiple values (use insert for that)
      intercept[MoleculeException] {
        Ns.n_?(Some(Seq(2, 3))).save.transact
      }.message ==> "Can only save one value for optional attribute `:Ns/n`. Found: List(2, 3)"

      // Empty option of values saves nothing
      Ns.n_?(Option.empty[Seq[Int]]).save.transact
      Ns.n.query.get ==> List()


      Ns.n(1).string_?(Some(string1)).save.transact
      Ns.n(1).string_?(Some(Seq(string2))).save.transact
      Ns.n(1).string_?(Option.empty[String]).save.transact

      Ns.n(2).int_?(Some(int1)).save.transact
      Ns.n(2).int_?(Some(Seq(int2))).save.transact
      Ns.n(2).int_?(Option.empty[Int]).save.transact

      Ns.n(3).long_?(Some(long1)).save.transact
      Ns.n(3).long_?(Some(Seq(long2))).save.transact
      Ns.n(3).long_?(Option.empty[Long]).save.transact

      Ns.n(4).float_?(Some(float1)).save.transact
      Ns.n(4).float_?(Some(Seq(float2))).save.transact
      Ns.n(4).float_?(Option.empty[Float]).save.transact

      Ns.n(5).double_?(Some(double1)).save.transact
      Ns.n(5).double_?(Some(Seq(double2))).save.transact
      Ns.n(5).double_?(Option.empty[Double]).save.transact

      Ns.n(6).boolean_?(Some(boolean1)).save.transact
      Ns.n(6).boolean_?(Some(Seq(boolean2))).save.transact
      Ns.n(6).boolean_?(Option.empty[Boolean]).save.transact

      Ns.n(7).bigInt_?(Some(bigInt1)).save.transact
      Ns.n(7).bigInt_?(Some(Seq(bigInt2))).save.transact
      Ns.n(7).bigInt_?(Option.empty[BigInt]).save.transact

      Ns.n(8).bigDecimal_?(Some(bigDecimal1)).save.transact
      Ns.n(8).bigDecimal_?(Some(Seq(bigDecimal2))).save.transact
      Ns.n(8).bigDecimal_?(Option.empty[BigDecimal]).save.transact

      Ns.n(9).date_?(Some(date1)).save.transact
      Ns.n(9).date_?(Some(Seq(date2))).save.transact
      Ns.n(9).date_?(Option.empty[Date]).save.transact

      Ns.n(10).uuid_?(Some(uuid1)).save.transact
      Ns.n(10).uuid_?(Some(Seq(uuid2))).save.transact
      Ns.n(10).uuid_?(Option.empty[UUID]).save.transact

      Ns.n(11).uri_?(Some(uri1)).save.transact
      Ns.n(11).uri_?(Some(Seq(uri2))).save.transact
      Ns.n(11).uri_?(Option.empty[URI]).save.transact

      Ns.n(12).byte_?(Some(byte1)).save.transact
      Ns.n(12).byte_?(Some(Seq(byte2))).save.transact
      Ns.n(12).byte_?(Option.empty[Byte]).save.transact

      Ns.n(13).short_?(Some(short1)).save.transact
      Ns.n(13).short_?(Some(Seq(short2))).save.transact
      Ns.n(13).short_?(Option.empty[Short]).save.transact

      Ns.n(14).char_?(Some(char1)).save.transact
      Ns.n(14).char_?(Some(Seq(char2))).save.transact
      Ns.n(14).char_?(Option.empty[Char]).save.transact


      Ns.n_(1).string_?.a1.query.get ==> List(None, Some(string1), Some(string2))
      Ns.n_(2).int_?.a1.query.get ==> List(None, Some(int1), Some(int2))
      Ns.n_(3).long_?.a1.query.get ==> List(None, Some(long1), Some(long2))
      Ns.n_(4).float_?.a1.query.get ==> List(None, Some(float1), Some(float2))
      Ns.n_(5).double_?.a1.query.get ==> List(None, Some(double1), Some(double2))
      Ns.n_(6).boolean_?.a1.query.get ==> List(None, Some(boolean2), Some(boolean1))
      Ns.n_(7).bigInt_?.a1.query.get ==> List(None, Some(bigInt1), Some(bigInt2))
      Ns.n_(8).bigDecimal_?.a1.query.get ==> List(None, Some(bigDecimal1), Some(bigDecimal2))
      Ns.n_(9).date_?.a1.query.get ==> List(None, Some(date1), Some(date2))
      Ns.n_(10).uuid_?.a1.query.get ==> List(None, Some(uuid1), Some(uuid2))
      Ns.n_(11).uri_?.a1.query.get ==> List(None, Some(uri1), Some(uri2))
      Ns.n_(12).byte_?.a1.query.get ==> List(None, Some(byte1), Some(byte2))
      Ns.n_(13).short_?.a1.query.get ==> List(None, Some(short1), Some(short2))
      Ns.n_(14).char_?.a1.query.get ==> List(None, Some(char1), Some(char2))
    }
  }
}
