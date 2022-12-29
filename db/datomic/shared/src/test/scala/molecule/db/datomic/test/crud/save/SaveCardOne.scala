package molecule.db.datomic.test.crud.save

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.util.exceptions.MoleculeException
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object SaveCardOne extends DatomicTestSuite {


  lazy val tests = Tests {

    "mandatory" - types { implicit conn =>
      for {
        // Can't save multiple values (use insert for that)
        _ <- Ns.i(1, 2).save.transact
          .map(_ ==> "Unexpected success").recover { case MoleculeException(err, _) =>
          err ==> "Can only save one value for attribute `Ns.i`. Found: ArraySeq(1, 2)"
        }

        _ <- Ns.i(Seq(1, 2)).save.transact
          .map(_ ==> "Unexpected success").recover { case MoleculeException(err, _) =>
          err ==> "Can only save one value for attribute `Ns.i`. Found: List(1, 2)"
        }

        // Saving empty list of values is ignored
        _ <- Ns.i(Seq.empty[Int]).save.transact
        _ <- Ns.i.query.get.map(_ ==> List())


        _ <- Ns.string(string1).save.transact
        _ <- Ns.string(Seq(string2)).save.transact

        _ <- Ns.int(int1).save.transact
        _ <- Ns.int(Seq(int2)).save.transact

        _ <- Ns.long(long1).save.transact
        _ <- Ns.long(Seq(long2)).save.transact

        _ <- Ns.float(float1).save.transact
        _ <- Ns.float(Seq(float2)).save.transact

        _ <- Ns.double(double1).save.transact
        _ <- Ns.double(Seq(double2)).save.transact

        _ <- Ns.boolean(boolean1).save.transact
        _ <- Ns.boolean(Seq(boolean2)).save.transact

        _ <- Ns.bigInt(bigInt1).save.transact
        _ <- Ns.bigInt(Seq(bigInt2)).save.transact

        _ <- Ns.bigDecimal(bigDecimal1).save.transact
        _ <- Ns.bigDecimal(Seq(bigDecimal2)).save.transact

        _ <- Ns.date(date1).save.transact
        _ <- Ns.date(Seq(date2)).save.transact

        _ <- Ns.uuid(uuid1).save.transact
        _ <- Ns.uuid(Seq(uuid2)).save.transact

        _ <- Ns.uri(uri1).save.transact
        _ <- Ns.uri(Seq(uri2)).save.transact

        _ <- Ns.byte(byte1).save.transact
        _ <- Ns.byte(Seq(byte2)).save.transact

        _ <- Ns.short(short1).save.transact
        _ <- Ns.short(Seq(short2)).save.transact

        _ <- Ns.char(char1).save.transact
        _ <- Ns.char(Seq(char2)).save.transact


        _ <- Ns.string.a1.query.get.map(_ ==> List(string1, string2))
        _ <- Ns.int.a1.query.get.map(_ ==> List(int1, int2))
        _ <- Ns.long.a1.query.get.map(_ ==> List(long1, long2))
        _ <- Ns.float.a1.query.get.map(_ ==> List(float1, float2))
        _ <- Ns.double.a1.query.get.map(_ ==> List(double1, double2))
        _ <- Ns.boolean.a1.query.get.map(_ ==> List(boolean1, boolean2))
        _ <- Ns.bigInt.a1.query.get.map(_ ==> List(bigInt1, bigInt2))
        _ <- Ns.bigDecimal.a1.query.get.map(_ ==> List(bigDecimal1, bigDecimal2))
        _ <- Ns.date.a1.query.get.map(_ ==> List(date1, date2))
        _ <- Ns.uuid.a1.query.get.map(_ ==> List(uuid1, uuid2))
        _ <- Ns.uri.a1.query.get.map(_ ==> List(uri1, uri2))
        _ <- Ns.byte.a1.query.get.map(_ ==> List(byte1, byte2))
        _ <- Ns.short.a1.query.get.map(_ ==> List(short1, short2))
        _ <- Ns.char.a1.query.get.map(_ ==> List(char1, char2))
      } yield ()
    }


    "optional" - types { implicit conn =>
      for {
        // Can't save multiple values (use insert for that)
        _ <- Ns.i_?(Some(Seq(1, 2))).save.transact
          .map(_ ==> "Unexpected success").recover { case MoleculeException(err, _) =>
          err ==> "Can only save one value for optional attribute `Ns.i`. Found: List(1, 2)"
        }

        // Empty option of values saves nothing
        _ <- Ns.i_?(Option.empty[Seq[Int]]).save.transact
        _ <- Ns.i.query.get.map(_ ==> List())


        _ <- Ns.i(1).string_?(Some(string1)).save.transact
        _ <- Ns.i(1).string_?(Some(Seq(string2))).save.transact
        _ <- Ns.i(1).string_?(Option.empty[String]).save.transact

        _ <- Ns.i(2).int_?(Some(int1)).save.transact
        _ <- Ns.i(2).int_?(Some(Seq(int2))).save.transact
        _ <- Ns.i(2).int_?(Option.empty[Int]).save.transact

        _ <- Ns.i(3).long_?(Some(long1)).save.transact
        _ <- Ns.i(3).long_?(Some(Seq(long2))).save.transact
        _ <- Ns.i(3).long_?(Option.empty[Long]).save.transact

        _ <- Ns.i(4).float_?(Some(float1)).save.transact
        _ <- Ns.i(4).float_?(Some(Seq(float2))).save.transact
        _ <- Ns.i(4).float_?(Option.empty[Float]).save.transact

        _ <- Ns.i(5).double_?(Some(double1)).save.transact
        _ <- Ns.i(5).double_?(Some(Seq(double2))).save.transact
        _ <- Ns.i(5).double_?(Option.empty[Double]).save.transact

        _ <- Ns.i(6).boolean_?(Some(boolean1)).save.transact
        _ <- Ns.i(6).boolean_?(Some(Seq(boolean2))).save.transact
        _ <- Ns.i(6).boolean_?(Option.empty[Boolean]).save.transact

        _ <- Ns.i(7).bigInt_?(Some(bigInt1)).save.transact
        _ <- Ns.i(7).bigInt_?(Some(Seq(bigInt2))).save.transact
        _ <- Ns.i(7).bigInt_?(Option.empty[BigInt]).save.transact

        _ <- Ns.i(8).bigDecimal_?(Some(bigDecimal1)).save.transact
        _ <- Ns.i(8).bigDecimal_?(Some(Seq(bigDecimal2))).save.transact
        _ <- Ns.i(8).bigDecimal_?(Option.empty[BigDecimal]).save.transact

        _ <- Ns.i(9).date_?(Some(date1)).save.transact
        _ <- Ns.i(9).date_?(Some(Seq(date2))).save.transact
        _ <- Ns.i(9).date_?(Option.empty[Date]).save.transact

        _ <- Ns.i(10).uuid_?(Some(uuid1)).save.transact
        _ <- Ns.i(10).uuid_?(Some(Seq(uuid2))).save.transact
        _ <- Ns.i(10).uuid_?(Option.empty[UUID]).save.transact

        _ <- Ns.i(11).uri_?(Some(uri1)).save.transact
        _ <- Ns.i(11).uri_?(Some(Seq(uri2))).save.transact
        _ <- Ns.i(11).uri_?(Option.empty[URI]).save.transact

        _ <- Ns.i(12).byte_?(Some(byte1)).save.transact
        _ <- Ns.i(12).byte_?(Some(Seq(byte2))).save.transact
        _ <- Ns.i(12).byte_?(Option.empty[Byte]).save.transact

        _ <- Ns.i(13).short_?(Some(short1)).save.transact
        _ <- Ns.i(13).short_?(Some(Seq(short2))).save.transact
        _ <- Ns.i(13).short_?(Option.empty[Short]).save.transact

        _ <- Ns.i(14).char_?(Some(char1)).save.transact
        _ <- Ns.i(14).char_?(Some(Seq(char2))).save.transact
        _ <- Ns.i(14).char_?(Option.empty[Char]).save.transact


        _ <- Ns.i_(1).string_?.a1.query.get.map(_ ==> List(None, Some(string1), Some(string2)))
        _ <- Ns.i_(2).int_?.a1.query.get.map(_ ==> List(None, Some(int1), Some(int2)))
        _ <- Ns.i_(3).long_?.a1.query.get.map(_ ==> List(None, Some(long1), Some(long2)))
        _ <- Ns.i_(4).float_?.a1.query.get.map(_ ==> List(None, Some(float1), Some(float2)))
        _ <- Ns.i_(5).double_?.a1.query.get.map(_ ==> List(None, Some(double1), Some(double2)))
        _ <- Ns.i_(6).boolean_?.a1.query.get.map(_ ==> List(None, Some(boolean1), Some(boolean2)))
        _ <- Ns.i_(7).bigInt_?.a1.query.get.map(_ ==> List(None, Some(bigInt1), Some(bigInt2)))
        _ <- Ns.i_(8).bigDecimal_?.a1.query.get.map(_ ==> List(None, Some(bigDecimal1), Some(bigDecimal2)))
        _ <- Ns.i_(9).date_?.a1.query.get.map(_ ==> List(None, Some(date1), Some(date2)))
        _ <- Ns.i_(10).uuid_?.a1.query.get.map(_ ==> List(None, Some(uuid1), Some(uuid2)))
        _ <- Ns.i_(11).uri_?.a1.query.get.map(_ ==> List(None, Some(uri1), Some(uri2)))
        _ <- Ns.i_(12).byte_?.a1.query.get.map(_ ==> List(None, Some(byte1), Some(byte2)))
        _ <- Ns.i_(13).short_?.a1.query.get.map(_ ==> List(None, Some(short1), Some(short2)))
        _ <- Ns.i_(14).char_?.a1.query.get.map(_ ==> List(None, Some(char1), Some(char2)))
      } yield ()
    }
  }
}
