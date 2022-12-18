package molecule.db.datomic.test.sort

import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object SortIndexes extends DatomicTestSuite {


  lazy val tests = Tests {

    "Types" - types { implicit conn =>
      Ns.string.insert(string3, string1, string2).transact
      Ns.int.insert(int3, int1, int2).transact
      Ns.long.insert(long3, long1, long2).transact
      Ns.float.insert(float3, float1, float2).transact
      Ns.double.insert(double3, double1, double2).transact
      Ns.boolean.insert(true, false).transact
      Ns.bigInt.insert(bigInt3, bigInt1, bigInt2).transact
      Ns.bigDecimal.insert(bigDecimal3, bigDecimal1, bigDecimal2).transact
      Ns.date.insert(date3, date1, date2).transact
      Ns.uuid.insert(uuid3, uuid1, uuid2).transact
      Ns.uri.insert(uri3, uri1, uri2).transact
      Ns.byte.insert(byte3, byte1, byte2).transact
      Ns.short.insert(short3, short1, short2).transact
      Ns.char.insert(char3, char1, char2).transact
      Ns.ref.insert(ref3, ref1, ref2).transact

      // Ascending
      Ns.string.a1.query.get ==> List(string1, string2, string3)
      Ns.int.a1.query.get ==> List(int1, int2, int3)
      Ns.long.a1.query.get ==> List(long1, long2, long3)
      Ns.float.a1.query.get ==> List(float1, float2, float3)
      Ns.double.a1.query.get ==> List(double1, double2, double3)
      Ns.boolean.a1.query.get ==> List(false, true) // false before true
      Ns.bigInt.a1.query.get ==> List(bigInt1, bigInt2, bigInt3)
      Ns.bigDecimal.a1.query.get ==> List(bigDecimal1, bigDecimal2, bigDecimal3)
      Ns.date.a1.query.get ==> List(date1, date2, date3)
      Ns.uuid.a1.query.get ==> List(uuid1, uuid2, uuid3)
      Ns.uri.a1.query.get ==> List(uri1, uri2, uri3)
      Ns.byte.a1.query.get ==> List(byte1, byte2, byte3)
      Ns.short.a1.query.get ==> List(short1, short2, short3)
      Ns.char.a1.query.get ==> List(char1, char2, char3)
      Ns.ref.a1.query.get ==> List(ref1, ref2, ref3)

      // Descending
      Ns.string.d1.query.get ==> List(string3, string2, string1)
      Ns.int.d1.query.get ==> List(int3, int2, int1)
      Ns.long.d1.query.get ==> List(long3, long2, long1)
      Ns.double.d1.query.get ==> List(double3, double2, double1)
      Ns.double.d1.query.get ==> List(double3, double2, double1)
      Ns.boolean.d1.query.get ==> List(true, false)
      Ns.bigInt.d1.query.get ==> List(bigInt3, bigInt2, bigInt1)
      Ns.bigDecimal.d1.query.get ==> List(bigDecimal3, bigDecimal2, bigDecimal1)
      Ns.date.d1.query.get ==> List(date3, date2, date1)
      Ns.uuid.d1.query.get ==> List(uuid3, uuid2, uuid1)
      Ns.uri.d1.query.get ==> List(uri3, uri2, uri1)
      Ns.byte.d1.query.get ==> List(byte3, byte2, byte1)
      Ns.short.d1.query.get ==> List(short3, short2, short1)
      Ns.char.d1.query.get ==> List(char3, char2, char1)
      Ns.ref.d1.query.get ==> List(ref3, ref2, ref1)
    }


    "Optional" - types { implicit conn =>
      Ns.i.string_?.insert((1, Some(string2)), (1, None), (1, Some(string1))).transact
      Ns.i.int_?.insert((2, Some(int2)), (2, None), (2, Some(int1))).transact
      Ns.i.long_?.insert((3, Some(long2)), (3, None), (3, Some(long1))).transact
      Ns.i.float_?.insert((4, Some(float2)), (4, None), (4, Some(float1))).transact
      Ns.i.double_?.insert((5, Some(double2)), (5, None), (5, Some(double1))).transact
      Ns.i.boolean_?.insert((6, Some(true)), (6, None), (6, Some(false))).transact
      Ns.i.bigInt_?.insert((7, Some(bigInt2)), (7, None), (7, Some(bigInt1))).transact
      Ns.i.bigDecimal_?.insert((8, Some(bigDecimal2)), (8, None), (8, Some(bigDecimal1))).transact
      Ns.i.date_?.insert((9, Some(date2)), (9, None), (9, Some(date1))).transact
      Ns.i.uuid_?.insert((10, Some(uuid2)), (10, None), (10, Some(uuid1))).transact
      Ns.i.uri_?.insert((11, Some(uri2)), (11, None), (11, Some(uri1))).transact
      Ns.i.byte_?.insert((12, Some(byte2)), (12, None), (12, Some(byte1))).transact
      Ns.i.short_?.insert((13, Some(short2)), (13, None), (13, Some(short1))).transact
      Ns.i.char_?.insert((14, Some(char2)), (14, None), (14, Some(char1))).transact
      Ns.i.ref_?.insert((15, Some(ref2)), (15, None), (15, Some(ref1))).transact

      // Ascending
      Ns.i(1).string_?.a1.query.get ==> List((1, None), (1, Some(string1)), (1, Some(string2)))
      Ns.i(2).int_?.a1.query.get ==> List((2, None), (2, Some(int1)), (2, Some(int2)))
      Ns.i(3).long_?.a1.query.get ==> List((3, None), (3, Some(long1)), (3, Some(long2)))
      Ns.i(4).float_?.a1.query.get ==> List((4, None), (4, Some(float1)), (4, Some(float2)))
      Ns.i(5).double_?.a1.query.get ==> List((5, None), (5, Some(double1)), (5, Some(double2)))
      Ns.i(6).boolean_?.a1.query.get ==> List((6, None), (6, Some(false)), (6, Some(true)))
      Ns.i(7).bigInt_?.a1.query.get ==> List((7, None), (7, Some(bigInt1)), (7, Some(bigInt2)))
      Ns.i(8).bigDecimal_?.a1.query.get ==> List((8, None), (8, Some(bigDecimal1)), (8, Some(bigDecimal2)))
      Ns.i(9).date_?.a1.query.get ==> List((9, None), (9, Some(date1)), (9, Some(date2)))
      Ns.i(10).uuid_?.a1.query.get ==> List((10, None), (10, Some(uuid1)), (10, Some(uuid2)))
      Ns.i(11).uri_?.a1.query.get ==> List((11, None), (11, Some(uri1)), (11, Some(uri2)))
      Ns.i(12).byte_?.a1.query.get ==> List((12, None), (12, Some(byte1)), (12, Some(byte2)))
      Ns.i(13).short_?.a1.query.get ==> List((13, None), (13, Some(short1)), (13, Some(short2)))
      Ns.i(14).char_?.a1.query.get ==> List((14, None), (14, Some(char1)), (14, Some(char2)))
      Ns.i(15).ref_?.a1.query.get ==> List((15, None), (15, Some(ref1)), (15, Some(ref2)))

      // Descending
      Ns.i(1).string_?.d1.query.get ==> List((1, Some(string2)), (1, Some(string1)), (1, None))
      Ns.i(2).int_?.d1.query.get ==> List((2, Some(int2)), (2, Some(int1)), (2, None))
      Ns.i(3).long_?.d1.query.get ==> List((3, Some(long2)), (3, Some(long1)), (3, None))
      Ns.i(4).float_?.d1.query.get ==> List((4, Some(float2)), (4, Some(float1)), (4, None))
      Ns.i(5).double_?.d1.query.get ==> List((5, Some(double2)), (5, Some(double1)), (5, None))
      Ns.i(6).boolean_?.d1.query.get ==> List((6, Some(true)), (6, Some(false)), (6, None))
      Ns.i(7).bigInt_?.d1.query.get ==> List((7, Some(bigInt2)), (7, Some(bigInt1)), (7, None))
      Ns.i(8).bigDecimal_?.d1.query.get ==> List((8, Some(bigDecimal2)), (8, Some(bigDecimal1)), (8, None))
      Ns.i(9).date_?.d1.query.get ==> List((9, Some(date2)), (9, Some(date1)), (9, None))
      Ns.i(10).uuid_?.d1.query.get ==> List((10, Some(uuid2)), (10, Some(uuid1)), (10, None))
      Ns.i(11).uri_?.d1.query.get ==> List((11, Some(uri2)), (11, Some(uri1)), (11, None))
      Ns.i(12).byte_?.d1.query.get ==> List((12, Some(byte2)), (12, Some(byte1)), (12, None))
      Ns.i(13).short_?.d1.query.get ==> List((13, Some(short2)), (13, Some(short1)), (13, None))
      Ns.i(14).char_?.d1.query.get ==> List((14, Some(char2)), (14, Some(char1)), (14, None))
      Ns.i(15).ref_?.d1.query.get ==> List((15, Some(ref2)), (15, Some(ref1)), (15, None))
    }


    "2 sort markers" - types { implicit conn =>
      Ns.i.int.insert(
        (2, 3),
        (1, 4),
        (1, 3),
      ).transact

      Ns.i.a1.int.a2.query.get ==> List(
        (1, 3),
        (1, 4),
        (2, 3),
      )
      Ns.i.a1.int.d2.query.get ==> List(
        (1, 4),
        (1, 3),
        (2, 3),
      )
      Ns.i.d1.int.a2.query.get ==> List(
        (2, 3),
        (1, 3),
        (1, 4),
      )
      Ns.i.d1.int.d2.query.get ==> List(
        (2, 3),
        (1, 4),
        (1, 3),
      )

      Ns.i.a2.int.a1.query.get ==> List(
        (1, 3),
        (2, 3),
        (1, 4),
      )
      Ns.i.a2.int.d1.query.get ==> List(
        (1, 4),
        (1, 3),
        (2, 3),
      )
      Ns.i.d2.int.a1.query.get ==> List(
        (2, 3),
        (1, 3),
        (1, 4),
      )
      Ns.i.d2.int.d1.query.get ==> List(
        (1, 4),
        (2, 3),
        (1, 3),
      )
    }


    "All 5 sort markers" - types { implicit conn =>
      Ns.boolean.string.uri.i.int.insert(
        (false, "b", uri4, 8, 16),
        (true, "a", uri2, 3, 5),
        (true, "b", uri4, 7, 13),
        (false, "a", uri1, 2, 3),
        (true, "a", uri1, 1, 2),
        (true, "a", uri2, 4, 7),
        (false, "b", uri4, 8, 15),
        (true, "a", uri2, 4, 8),
        (false, "b", uri4, 7, 13),
        (false, "b", uri3, 5, 9),
        (true, "b", uri3, 6, 12),
        (false, "a", uri1, 2, 4),
        (true, "a", uri1, 2, 4),
        (true, "b", uri4, 8, 15),
        (true, "a", uri1, 1, 1),
        (false, "b", uri3, 6, 11),
        (false, "b", uri4, 7, 14),
        (false, "a", uri1, 1, 1),
        (false, "a", uri2, 3, 6),
        (true, "a", uri2, 3, 6),
        (true, "b", uri4, 8, 16),
        (false, "b", uri3, 6, 12),
        (false, "a", uri2, 4, 7),
        (false, "a", uri1, 1, 2),
        (true, "b", uri3, 5, 9),
        (false, "a", uri2, 4, 8),
        (true, "b", uri3, 6, 11),
        (true, "b", uri4, 7, 14),
        (true, "b", uri3, 5, 10),
        (false, "b", uri3, 5, 10),
        (false, "a", uri2, 3, 5),
        (true, "a", uri1, 2, 3),
      ).transact

      // Ascending
      Ns.boolean.a1.string.a2.uri.a3.i.a4.int.a5.query.get ==> List(
        (false, "a", uri1, 1, 1),
        (false, "a", uri1, 1, 2),
        (false, "a", uri1, 2, 3),
        (false, "a", uri1, 2, 4),
        (false, "a", uri2, 3, 5),
        (false, "a", uri2, 3, 6),
        (false, "a", uri2, 4, 7),
        (false, "a", uri2, 4, 8),
        (false, "b", uri3, 5, 9),
        (false, "b", uri3, 5, 10),
        (false, "b", uri3, 6, 11),
        (false, "b", uri3, 6, 12),
        (false, "b", uri4, 7, 13),
        (false, "b", uri4, 7, 14),
        (false, "b", uri4, 8, 15),
        (false, "b", uri4, 8, 16),
        (true, "a", uri1, 1, 1),
        (true, "a", uri1, 1, 2),
        (true, "a", uri1, 2, 3),
        (true, "a", uri1, 2, 4),
        (true, "a", uri2, 3, 5),
        (true, "a", uri2, 3, 6),
        (true, "a", uri2, 4, 7),
        (true, "a", uri2, 4, 8),
        (true, "b", uri3, 5, 9),
        (true, "b", uri3, 5, 10),
        (true, "b", uri3, 6, 11),
        (true, "b", uri3, 6, 12),
        (true, "b", uri4, 7, 13),
        (true, "b", uri4, 7, 14),
        (true, "b", uri4, 8, 15),
        (true, "b", uri4, 8, 16),
      )

      // Descending
      Ns.boolean.d1.string.d2.uri.d3.i.d4.int.d5.query.get ==> List(
        (true, "b", uri4, 8, 16),
        (true, "b", uri4, 8, 15),
        (true, "b", uri4, 7, 14),
        (true, "b", uri4, 7, 13),
        (true, "b", uri3, 6, 12),
        (true, "b", uri3, 6, 11),
        (true, "b", uri3, 5, 10),
        (true, "b", uri3, 5, 9),
        (true, "a", uri2, 4, 8),
        (true, "a", uri2, 4, 7),
        (true, "a", uri2, 3, 6),
        (true, "a", uri2, 3, 5),
        (true, "a", uri1, 2, 4),
        (true, "a", uri1, 2, 3),
        (true, "a", uri1, 1, 2),
        (true, "a", uri1, 1, 1),
        (false, "b", uri4, 8, 16),
        (false, "b", uri4, 8, 15),
        (false, "b", uri4, 7, 14),
        (false, "b", uri4, 7, 13),
        (false, "b", uri3, 6, 12),
        (false, "b", uri3, 6, 11),
        (false, "b", uri3, 5, 10),
        (false, "b", uri3, 5, 9),
        (false, "a", uri2, 4, 8),
        (false, "a", uri2, 4, 7),
        (false, "a", uri2, 3, 6),
        (false, "a", uri2, 3, 5),
        (false, "a", uri1, 2, 4),
        (false, "a", uri1, 2, 3),
        (false, "a", uri1, 1, 2),
        (false, "a", uri1, 1, 1),
      )

      // Mixed order
      Ns.boolean.a3.string.d1.uri.d4.i.a2.int.d5.query.get ==> List(
        (false, "b", uri3, 5, 10),
        (false, "b", uri3, 5, 9),
        (true, "b", uri3, 5, 10),
        (true, "b", uri3, 5, 9),

        (false, "b", uri3, 6, 12),
        (false, "b", uri3, 6, 11),
        (true, "b", uri3, 6, 12),
        (true, "b", uri3, 6, 11),

        (false, "b", uri4, 7, 14),
        (false, "b", uri4, 7, 13),
        (true, "b", uri4, 7, 14),
        (true, "b", uri4, 7, 13),

        (false, "b", uri4, 8, 16),
        (false, "b", uri4, 8, 15),
        (true, "b", uri4, 8, 16),
        (true, "b", uri4, 8, 15),


        (false, "a", uri1, 1, 2),
        (false, "a", uri1, 1, 1),
        (true, "a", uri1, 1, 2),
        (true, "a", uri1, 1, 1),

        (false, "a", uri1, 2, 4),
        (false, "a", uri1, 2, 3),
        (true, "a", uri1, 2, 4),
        (true, "a", uri1, 2, 3),

        (false, "a", uri2, 3, 6),
        (false, "a", uri2, 3, 5),
        (true, "a", uri2, 3, 6),
        (true, "a", uri2, 3, 5),

        (false, "a", uri2, 4, 8),
        (false, "a", uri2, 4, 7),
        (true, "a", uri2, 4, 8),
        (true, "a", uri2, 4, 7),
      )
    }


    "Correct use of sort markers" - types { implicit conn =>
      intercept[MoleculeException](
        Ns.string.a1.int.a1.query.get
      ).message ==> "Sort index 1 should be present and additional indexes continuously " +
        "increase (in any order). Found sort index(es): 1, 1"

      intercept[MoleculeException](
        Ns.string.d1.int.d2.long.d2.query.get
      ).message ==> "Sort index 1 should be present and additional indexes continuously " +
        "increase (in any order). Found sort index(es): 1, 2, 2"

      intercept[MoleculeException](
        Ns.string.a1.int.a3.query.get
      ).message ==> "Sort index 1 should be present and additional indexes continuously " +
        "increase (in any order). Found sort index(es): 1, 3"

      intercept[MoleculeException](
        Ns.string.d3.int.d1.query.get
      ).message ==> "Sort index 1 should be present and additional indexes continuously " +
        "increase (in any order). Found sort index(es): 1, 3"

      intercept[MoleculeException](
        Ns.string.d2.int.a3.query.get
      ).message ==> "Sort index 1 should be present and additional indexes continuously " +
        "increase (in any order). Found sort index(es): 2, 3"
    }
  }
}
