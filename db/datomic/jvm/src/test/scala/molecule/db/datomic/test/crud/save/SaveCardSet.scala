package molecule.db.datomic.test.crud.save

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object SaveCardSet extends DatomicTestSuite {


  lazy val tests = Tests {

    "mandatory" - types { implicit conn =>
      // Can't save multiple Sets of values (use insert for that)
      intercept[MoleculeException] {
        Ns.ints(Set(1), Set(2)).save.transact
      }.message ==> "Can only save one Set of values for Set attribute `:Ns/ints`. Found: ArraySeq(Set(1), Set(2))"

      intercept[MoleculeException] {
        Ns.ints(Seq(Set(1), Set(2))).save.transact
      }.message ==> "Can only save one Set of values for Set attribute `:Ns/ints`. Found: List(Set(1), Set(2))"

      // Saving empty list of Sets is ignored
      Ns.ints(Seq.empty[Set[Int]]).save.transact
      Ns.ints.query.get ==> List()


      Ns.i(1).strings(Set(string1, string2)).save.transact
      Ns.i(2).strings(Seq(Set(string1, string3))).save.transact

      Ns.i(1).ints(Set(int1, int2)).save.transact
      Ns.i(2).ints(Seq(Set(int1, int3))).save.transact

      Ns.i(1).longs(Set(long1, long2)).save.transact
      Ns.i(2).longs(Seq(Set(long1, long3))).save.transact

      Ns.i(1).floats(Set(float1, float2)).save.transact
      Ns.i(2).floats(Seq(Set(float1, float3))).save.transact

      Ns.i(1).doubles(Set(double1, double2)).save.transact
      Ns.i(2).doubles(Seq(Set(double1, double3))).save.transact

      Ns.i(1).booleans(Set(boolean0)).save.transact
      Ns.i(2).booleans(Seq(Set(boolean0, boolean1))).save.transact

      Ns.i(1).bigInts(Set(bigInt1, bigInt2)).save.transact
      Ns.i(2).bigInts(Seq(Set(bigInt1, bigInt3))).save.transact

      Ns.i(1).bigDecimals(Set(bigDecimal1, bigDecimal2)).save.transact
      Ns.i(2).bigDecimals(Seq(Set(bigDecimal1, bigDecimal3))).save.transact

      Ns.i(1).dates(Set(date1, date2)).save.transact
      Ns.i(2).dates(Seq(Set(date1, date3))).save.transact

      Ns.i(1).uuids(Set(uuid1, uuid2)).save.transact
      Ns.i(2).uuids(Seq(Set(uuid1, uuid3))).save.transact

      Ns.i(1).uris(Set(uri1, uri2)).save.transact
      Ns.i(2).uris(Seq(Set(uri1, uri3))).save.transact

      Ns.i(1).bytes(Set(byte1, byte2)).save.transact
      Ns.i(2).bytes(Seq(Set(byte1, byte3))).save.transact

      Ns.i(1).shorts(Set(short1, short2)).save.transact
      Ns.i(2).shorts(Seq(Set(short1, short3))).save.transact

      Ns.i(1).chars(Set(char1, char2)).save.transact
      Ns.i(2).chars(Seq(Set(char1, char3))).save.transact


      Ns.i.strings.query.get ==> List((1, Set(string1, string2)), (2, Set(string1, string3)))
      Ns.i.ints.query.get ==> List((1, Set(int1, int2)), (2, Set(int1, int3)))
      Ns.i.longs.query.get ==> List((1, Set(long1, long2)), (2, Set(long1, long3)))
      Ns.i.floats.query.get ==> List((1, Set(float1, float2)), (2, Set(float1, float3)))
      Ns.i.doubles.query.get ==> List((1, Set(double1, double2)), (2, Set(double1, double3)))
      Ns.i.booleans.query.get ==> List((1, Set(boolean0)), (2, Set(boolean0, boolean1)))
      Ns.i.bigInts.query.get ==> List((1, Set(bigInt1, bigInt2)), (2, Set(bigInt1, bigInt3)))
      Ns.i.bigDecimals.query.get ==> List((1, Set(bigDecimal1, bigDecimal2)), (2, Set(bigDecimal1, bigDecimal3)))
      Ns.i.dates.query.get ==> List((1, Set(date1, date2)), (2, Set(date1, date3)))
      Ns.i.uuids.query.get ==> List((1, Set(uuid1, uuid2)), (2, Set(uuid1, uuid3)))
      Ns.i.uris.query.get ==> List((1, Set(uri1, uri2)), (2, Set(uri1, uri3)))
      Ns.i.bytes.query.get ==> List((1, Set(byte1, byte2)), (2, Set(byte1, byte3)))
      Ns.i.shorts.query.get ==> List((1, Set(short1, short2)), (2, Set(short1, short3)))
      Ns.i.chars.query.get ==> List((1, Set(char1, char2)), (2, Set(char1, char3)))
    }


    "optional" - types { implicit conn =>
      // Can't save multiple Sets of values (use insert for that)
      intercept[MoleculeException] {
        Ns.ints_?(Some(Seq(Set(1), Set(2)))).save.transact
      }.message ==> "Can only save one Set of values for optional Set attribute `:Ns/ints`. Found: List(Set(1), Set(2))"

      // Empty option of Set of values saves nothing
      Ns.ints_?(Option.empty[Seq[Set[Int]]]).save.transact
      Ns.ints.query.get ==> List()


      Ns.int(1).i(0).strings_?(Some(Set.empty[String])).save.transact
      Ns.int(1).i(1).strings_?(Some(Set(string1))).save.transact
      Ns.int(1).i(2).strings_?(Some(Seq(Set(string2, string3)))).save.transact
      Ns.int(1).i(3).strings_?(Option.empty[Set[String]]).save.transact

      Ns.int(2).i(0).ints_?(Some(Set.empty[Int])).save.transact
      Ns.int(2).i(1).ints_?(Some(Set(int1))).save.transact
      Ns.int(2).i(2).ints_?(Some(Seq(Set(int2, int3)))).save.transact
      Ns.int(2).i(3).ints_?(Option.empty[Set[Int]]).save.transact

      Ns.int(3).i(0).longs_?(Some(Set.empty[Long])).save.transact
      Ns.int(3).i(1).longs_?(Some(Set(long1))).save.transact
      Ns.int(3).i(2).longs_?(Some(Seq(Set(long2, long3)))).save.transact
      Ns.int(3).i(3).longs_?(Option.empty[Set[Long]]).save.transact

      Ns.int(4).i(0).floats_?(Some(Set.empty[Float])).save.transact
      Ns.int(4).i(1).floats_?(Some(Set(float1))).save.transact
      Ns.int(4).i(2).floats_?(Some(Seq(Set(float2, float3)))).save.transact
      Ns.int(4).i(3).floats_?(Option.empty[Set[Float]]).save.transact

      Ns.int(5).i(0).doubles_?(Some(Set.empty[Double])).save.transact
      Ns.int(5).i(1).doubles_?(Some(Set(double1))).save.transact
      Ns.int(5).i(2).doubles_?(Some(Seq(Set(double2, double3)))).save.transact
      Ns.int(5).i(3).doubles_?(Option.empty[Set[Double]]).save.transact

      Ns.int(6).i(0).booleans_?(Some(Set.empty[Boolean])).save.transact
      Ns.int(6).i(1).booleans_?(Some(Set(boolean0))).save.transact
      Ns.int(6).i(2).booleans_?(Some(Seq(Set(boolean0, boolean1)))).save.transact
      Ns.int(6).i(3).booleans_?(Option.empty[Set[Boolean]]).save.transact

      Ns.int(7).i(0).bigInts_?(Some(Set.empty[BigInt])).save.transact
      Ns.int(7).i(1).bigInts_?(Some(Set(bigInt1))).save.transact
      Ns.int(7).i(2).bigInts_?(Some(Seq(Set(bigInt2, bigInt3)))).save.transact
      Ns.int(7).i(3).bigInts_?(Option.empty[Set[BigInt]]).save.transact

      Ns.int(8).i(0).bigDecimals_?(Some(Set.empty[BigDecimal])).save.transact
      Ns.int(8).i(1).bigDecimals_?(Some(Set(bigDecimal1))).save.transact
      Ns.int(8).i(2).bigDecimals_?(Some(Seq(Set(bigDecimal2, bigDecimal3)))).save.transact
      Ns.int(8).i(3).bigDecimals_?(Option.empty[Set[BigDecimal]]).save.transact

      Ns.int(9).i(0).dates_?(Some(Set.empty[Date])).save.transact
      Ns.int(9).i(1).dates_?(Some(Set(date1))).save.transact
      Ns.int(9).i(2).dates_?(Some(Seq(Set(date2, date3)))).save.transact
      Ns.int(9).i(3).dates_?(Option.empty[Set[Date]]).save.transact

      Ns.int(10).i(0).uuids_?(Some(Set.empty[UUID])).save.transact
      Ns.int(10).i(1).uuids_?(Some(Set(uuid1))).save.transact
      Ns.int(10).i(2).uuids_?(Some(Seq(Set(uuid2, uuid3)))).save.transact
      Ns.int(10).i(3).uuids_?(Option.empty[Set[UUID]]).save.transact

      Ns.int(11).i(0).uris_?(Some(Set.empty[URI])).save.transact
      Ns.int(11).i(1).uris_?(Some(Set(uri1))).save.transact
      Ns.int(11).i(2).uris_?(Some(Seq(Set(uri2, uri3)))).save.transact
      Ns.int(11).i(3).uris_?(Option.empty[Set[URI]]).save.transact

      Ns.int(12).i(0).bytes_?(Some(Set.empty[Byte])).save.transact
      Ns.int(12).i(1).bytes_?(Some(Set(byte1))).save.transact
      Ns.int(12).i(2).bytes_?(Some(Seq(Set(byte2, byte3)))).save.transact
      Ns.int(12).i(3).bytes_?(Option.empty[Set[Byte]]).save.transact

      Ns.int(13).i(0).shorts_?(Some(Set.empty[Short])).save.transact
      Ns.int(13).i(1).shorts_?(Some(Set(short1))).save.transact
      Ns.int(13).i(2).shorts_?(Some(Seq(Set(short2, short3)))).save.transact
      Ns.int(13).i(3).shorts_?(Option.empty[Set[Short]]).save.transact

      Ns.int(14).i(0).chars_?(Some(Set.empty[Char])).save.transact
      Ns.int(14).i(1).chars_?(Some(Set(char1))).save.transact
      Ns.int(14).i(2).chars_?(Some(Seq(Set(char2, char3)))).save.transact
      Ns.int(14).i(3).chars_?(Option.empty[Set[Char]]).save.transact


      Ns.int_(1).i.a1.strings_?.query.get ==> List(
        (0, None),
        (1, Some(Set(string1))),
        (2, Some(Set(string2, string3))),
        (3, None)
      )
      Ns.int_(2).i.a1.ints_?.query.get ==> List(
        (0, None),
        (1, Some(Set(int1))),
        (2, Some(Set(int2, int3))),
        (3, None)
      )
      Ns.int_(3).i.a1.longs_?.query.get ==> List(
        (0, None),
        (1, Some(Set(long1))),
        (2, Some(Set(long2, long3))),
        (3, None)
      )
      Ns.int_(4).i.a1.floats_?.query.get ==> List(
        (0, None),
        (1, Some(Set(float1))),
        (2, Some(Set(float2, float3))),
        (3, None)
      )
      Ns.int_(5).i.a1.doubles_?.query.get ==> List(
        (0, None),
        (1, Some(Set(double1))),
        (2, Some(Set(double2, double3))),
        (3, None)
      )

      // todo: Bug in Datomic: Set values of `false` not returned
      Ns.int_(6).i.a1.booleans_?.query.get ==> List(
        (0, None),
        (1, None),
        (2, Some(Set(boolean1))),
        (3, None)
      )
      // Should be:
      //      Ns.int_(6).i.a1.booleans_?.query.get ==> List(
      //        (0, None),
      //        (1, Some(Set(boolean0))),
      //        (2, Some(Set(boolean0, boolean1))),
      //        (3, None)
      //      )

      Ns.int_(7).i.a1.bigInts_?.query.get ==> List(
        (0, None),
        (1, Some(Set(bigInt1))),
        (2, Some(Set(bigInt2, bigInt3))),
        (3, None)
      )
      Ns.int_(8).i.a1.bigDecimals_?.query.get ==> List(
        (0, None),
        (1, Some(Set(bigDecimal1))),
        (2, Some(Set(bigDecimal2, bigDecimal3))),
        (3, None)
      )
      Ns.int_(9).i.a1.dates_?.query.get ==> List(
        (0, None),
        (1, Some(Set(date1))),
        (2, Some(Set(date2, date3))),
        (3, None)
      )
      Ns.int_(10).i.a1.uuids_?.query.get ==> List(
        (0, None),
        (1, Some(Set(uuid1))),
        (2, Some(Set(uuid2, uuid3))),
        (3, None)
      )
      Ns.int_(11).i.a1.uris_?.query.get ==> List(
        (0, None),
        (1, Some(Set(uri1))),
        (2, Some(Set(uri2, uri3))),
        (3, None)
      )
      Ns.int_(12).i.a1.bytes_?.query.get ==> List(
        (0, None),
        (1, Some(Set(byte1))),
        (2, Some(Set(byte2, byte3))),
        (3, None)
      )
      Ns.int_(13).i.a1.shorts_?.query.get ==> List(
        (0, None),
        (1, Some(Set(short1))),
        (2, Some(Set(short2, short3))),
        (3, None)
      )
      Ns.int_(14).i.a1.chars_?.query.get ==> List(
        (0, None),
        (1, Some(Set(char1))),
        (2, Some(Set(char2, char3))),
        (3, None)
      )
    }
  }
}
