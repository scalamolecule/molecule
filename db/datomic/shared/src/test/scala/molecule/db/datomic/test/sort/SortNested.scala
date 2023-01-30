package molecule.db.datomic.test.sort

import molecule.base.util.exceptions.MoleculeError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object SortNested extends DatomicTestSuite {


  lazy val tests = Tests {

    "Basic types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        _ <- Ref.i.Nss.*(Ns.string).insert((1, List(string1, string2))).transact
        _ <- Ref.i.Nss.*(Ns.int).insert((2, List(int1, int2))).transact
        _ <- Ref.i.Nss.*(Ns.long).insert((3, List(long1, long2))).transact
        _ <- Ref.i.Nss.*(Ns.float).insert((4, List(float1, float2))).transact
        _ <- Ref.i.Nss.*(Ns.double).insert((5, List(double1, double2))).transact
        _ <- Ref.i.Nss.*(Ns.boolean).insert((6, List(boolean1, boolean2))).transact
        _ <- Ref.i.Nss.*(Ns.bigInt).insert((7, List(bigInt1, bigInt2))).transact
        _ <- Ref.i.Nss.*(Ns.bigDecimal).insert((8, List(bigDecimal1, bigDecimal2))).transact
        _ <- Ref.i.Nss.*(Ns.date).insert((9, List(date1, date2))).transact
        _ <- Ref.i.Nss.*(Ns.uuid).insert((10, List(uuid1, uuid2))).transact
        _ <- Ref.i.Nss.*(Ns.uri).insert((11, List(uri1, uri2))).transact
        _ <- Ref.i.Nss.*(Ns.byte).insert((12, List(byte1, byte2))).transact
        _ <- Ref.i.Nss.*(Ns.short).insert((13, List(short1, short2))).transact
        _ <- Ref.i.Nss.*(Ns.char).insert((14, List(char1, char2))).transact
        _ <- Ref.i.Nss.*(Ns.ref).insert((15, List(ref1, ref2))).transact

        // Ascending

        _ <- Ref.i_(1).Nss.*(Ns.string.a1).query.get.map(_ ==> List(List(string1, string2)))
        _ <- Ref.i_(2).Nss.*(Ns.int.a1).query.get.map(_ ==> List(List(int1, int2)))
        _ <- Ref.i_(3).Nss.*(Ns.long.a1).query.get.map(_ ==> List(List(long1, long2)))
        _ <- Ref.i_(4).Nss.*(Ns.float.a1).query.get.map(_ ==> List(List(float1, float2)))
        _ <- Ref.i_(5).Nss.*(Ns.double.a1).query.get.map(_ ==> List(List(double1, double2)))
        _ <- Ref.i_(6).Nss.*(Ns.boolean.a1).query.get.map(_ ==> List(List(boolean1, boolean2)))
        _ <- Ref.i_(7).Nss.*(Ns.bigInt.a1).query.get.map(_ ==> List(List(bigInt1, bigInt2)))
        _ <- Ref.i_(8).Nss.*(Ns.bigDecimal.a1).query.get.map(_ ==> List(List(bigDecimal1, bigDecimal2)))
        _ <- Ref.i_(9).Nss.*(Ns.date.a1).query.get.map(_ ==> List(List(date1, date2)))
        _ <- Ref.i_(10).Nss.*(Ns.uuid.a1).query.get.map(_ ==> List(List(uuid1, uuid2)))
        _ <- Ref.i_(11).Nss.*(Ns.uri.a1).query.get.map(_ ==> List(List(uri1, uri2)))
        _ <- Ref.i_(12).Nss.*(Ns.byte.a1).query.get.map(_ ==> List(List(byte1, byte2)))
        _ <- Ref.i_(13).Nss.*(Ns.short.a1).query.get.map(_ ==> List(List(short1, short2)))
        _ <- Ref.i_(14).Nss.*(Ns.char.a1).query.get.map(_ ==> List(List(char1, char2)))
        _ <- Ref.i_(15).Nss.*(Ns.ref.a1).query.get.map(_ ==> List(List(ref1, ref2)))

        _ <- Ref.i_(1).Nss.*?(Ns.string.a1).query.get.map(_ ==> List(List(string1, string2)))
        _ <- Ref.i_(2).Nss.*?(Ns.int.a1).query.get.map(_ ==> List(List(int1, int2)))
        _ <- Ref.i_(3).Nss.*?(Ns.long.a1).query.get.map(_ ==> List(List(long1, long2)))
        _ <- Ref.i_(4).Nss.*?(Ns.float.a1).query.get.map(_ ==> List(List(float1, float2)))
        _ <- Ref.i_(5).Nss.*?(Ns.double.a1).query.get.map(_ ==> List(List(double1, double2)))
        _ <- Ref.i_(7).Nss.*?(Ns.bigInt.a1).query.get.map(_ ==> List(List(bigInt1, bigInt2)))
        _ <- Ref.i_(8).Nss.*?(Ns.bigDecimal.a1).query.get.map(_ ==> List(List(bigDecimal1, bigDecimal2)))
        _ <- Ref.i_(9).Nss.*?(Ns.date.a1).query.get.map(_ ==> List(List(date1, date2)))
        _ <- Ref.i_(10).Nss.*?(Ns.uuid.a1).query.get.map(_ ==> List(List(uuid1, uuid2)))
        _ <- Ref.i_(11).Nss.*?(Ns.uri.a1).query.get.map(_ ==> List(List(uri1, uri2)))
        _ <- Ref.i_(12).Nss.*?(Ns.byte.a1).query.get.map(_ ==> List(List(byte1, byte2)))
        _ <- Ref.i_(13).Nss.*?(Ns.short.a1).query.get.map(_ ==> List(List(short1, short2)))
        _ <- Ref.i_(14).Nss.*?(Ns.char.a1).query.get.map(_ ==> List(List(char1, char2)))
        _ <- Ref.i_(15).Nss.*?(Ns.ref.a1).query.get.map(_ ==> List(List(ref1, ref2)))

        // Descending

        _ <- Ref.i_(1).Nss.*(Ns.string.d1).query.get.map(_ ==> List(List(string2, string1)))
        _ <- Ref.i_(2).Nss.*(Ns.int.d1).query.get.map(_ ==> List(List(int2, int1)))
        _ <- Ref.i_(3).Nss.*(Ns.long.d1).query.get.map(_ ==> List(List(long2, long1)))
        _ <- Ref.i_(4).Nss.*(Ns.float.d1).query.get.map(_ ==> List(List(float2, float1)))
        _ <- Ref.i_(5).Nss.*(Ns.double.d1).query.get.map(_ ==> List(List(double2, double1)))
        _ <- Ref.i_(6).Nss.*(Ns.boolean.d1).query.get.map(_ ==> List(List(boolean2, boolean1)))
        _ <- Ref.i_(7).Nss.*(Ns.bigInt.d1).query.get.map(_ ==> List(List(bigInt2, bigInt1)))
        _ <- Ref.i_(8).Nss.*(Ns.bigDecimal.d1).query.get.map(_ ==> List(List(bigDecimal2, bigDecimal1)))
        _ <- Ref.i_(9).Nss.*(Ns.date.d1).query.get.map(_ ==> List(List(date2, date1)))
        _ <- Ref.i_(10).Nss.*(Ns.uuid.d1).query.get.map(_ ==> List(List(uuid2, uuid1)))
        _ <- Ref.i_(11).Nss.*(Ns.uri.d1).query.get.map(_ ==> List(List(uri2, uri1)))
        _ <- Ref.i_(12).Nss.*(Ns.byte.d1).query.get.map(_ ==> List(List(byte2, byte1)))
        _ <- Ref.i_(13).Nss.*(Ns.short.d1).query.get.map(_ ==> List(List(short2, short1)))
        _ <- Ref.i_(14).Nss.*(Ns.char.d1).query.get.map(_ ==> List(List(char2, char1)))
        _ <- Ref.i_(15).Nss.*(Ns.ref.d1).query.get.map(_ ==> List(List(ref2, ref1)))

        _ <- Ref.i_(1).Nss.*?(Ns.string.d1).query.get.map(_ ==> List(List(string2, string1)))
        _ <- Ref.i_(2).Nss.*?(Ns.int.d1).query.get.map(_ ==> List(List(int2, int1)))
        _ <- Ref.i_(3).Nss.*?(Ns.long.d1).query.get.map(_ ==> List(List(long2, long1)))
        _ <- Ref.i_(4).Nss.*?(Ns.float.d1).query.get.map(_ ==> List(List(float2, float1)))
        _ <- Ref.i_(5).Nss.*?(Ns.double.d1).query.get.map(_ ==> List(List(double2, double1)))
        _ <- Ref.i_(7).Nss.*?(Ns.bigInt.d1).query.get.map(_ ==> List(List(bigInt2, bigInt1)))
        _ <- Ref.i_(8).Nss.*?(Ns.bigDecimal.d1).query.get.map(_ ==> List(List(bigDecimal2, bigDecimal1)))
        _ <- Ref.i_(9).Nss.*?(Ns.date.d1).query.get.map(_ ==> List(List(date2, date1)))
        _ <- Ref.i_(10).Nss.*?(Ns.uuid.d1).query.get.map(_ ==> List(List(uuid2, uuid1)))
        _ <- Ref.i_(11).Nss.*?(Ns.uri.d1).query.get.map(_ ==> List(List(uri2, uri1)))
        _ <- Ref.i_(12).Nss.*?(Ns.byte.d1).query.get.map(_ ==> List(List(byte2, byte1)))
        _ <- Ref.i_(13).Nss.*?(Ns.short.d1).query.get.map(_ ==> List(List(short2, short1)))
        _ <- Ref.i_(14).Nss.*?(Ns.char.d1).query.get.map(_ ==> List(List(char2, char1)))
        _ <- Ref.i_(15).Nss.*?(Ns.ref.d1).query.get.map(_ ==> List(List(ref2, ref1)))

        _ <- if (useFree) {
          Ref.i_(6).Nss.*?(Ns.boolean.a1).query.get
            .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
            err ==> "Datomic Free (not Pro) has a bug that pulls boolean `false` values as nil."
          }
        } else {
          Ref.i_(6).Nss.*?(Ns.boolean).query.get.map(_ ==> List(List(boolean1, boolean2)))
        }
        _ <- if (useFree) {
          Ref.i_(6).Nss.*?(Ns.boolean.d1).query.get
            .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
            err ==> "Datomic Free (not Pro) has a bug that pulls boolean `false` values as nil."
          }
        } else {
          Ref.i_(6).Nss.*?(Ns.boolean.d1).query.get.map(_ ==> List(List(boolean2, boolean1)))
        }
      } yield ()
    }


    "OptNested type, optional" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        _ <- Ref.i.Nss.*(Ns.i.string_?).insert((1, List(
          (1, Some(string1)),
          (2, Some(string2)),
          (3, None)))).transact
        _ <- Ref.i_(1).Nss.*?(Ns.i.a2.string_?.a1).query.get.map(_ ==> List(List(
          (3, None),
          (1, Some(string1)),
          (2, Some(string2)))))
        _ <- Ref.i_(1).Nss.*?(Ns.i.d2.string_?.d1).query.get.map(_ ==> List(List(
          (2, Some(string2)),
          (1, Some(string1)),
          (3, None))))

        _ <- Ref.i.Nss.*(Ns.i.int_?).insert((2, List(
          (1, Some(int1)),
          (2, Some(int2)),
          (3, None)))).transact
        _ <- Ref.i_(2).Nss.*?(Ns.i.a2.int_?.a1).query.get.map(_ ==> List(List(
          (3, None),
          (1, Some(int1)),
          (2, Some(int2)))))
        _ <- Ref.i_(2).Nss.*?(Ns.i.d2.int_?.d1).query.get.map(_ ==> List(List(
          (2, Some(int2)),
          (1, Some(int1)),
          (3, None))))

        _ <- Ref.i.Nss.*(Ns.i.long_?).insert((3, List(
          (1, Some(long1)),
          (2, Some(long2)),
          (3, None)))).transact
        _ <- Ref.i_(3).Nss.*?(Ns.i.a2.long_?.a1).query.get.map(_ ==> List(List(
          (3, None),
          (1, Some(long1)),
          (2, Some(long2)))))
        _ <- Ref.i_(3).Nss.*?(Ns.i.d2.long_?.d1).query.get.map(_ ==> List(List(
          (2, Some(long2)),
          (1, Some(long1)),
          (3, None))))

        _ <- Ref.i.Nss.*(Ns.i.float_?).insert((4, List(
          (1, Some(float1)),
          (2, Some(float2)),
          (3, None)))).transact
        _ <- Ref.i_(4).Nss.*?(Ns.i.a2.float_?.a1).query.get.map(_ ==> List(List(
          (3, None),
          (1, Some(float1)),
          (2, Some(float2)))))
        _ <- Ref.i_(4).Nss.*?(Ns.i.d2.float_?.d1).query.get.map(_ ==> List(List(
          (2, Some(float2)),
          (1, Some(float1)),
          (3, None))))

        _ <- Ref.i.Nss.*(Ns.i.double_?).insert((5, List(
          (1, Some(double1)),
          (2, Some(double2)),
          (3, None)))).transact
        _ <- Ref.i_(5).Nss.*?(Ns.i.a2.double_?.a1).query.get.map(_ ==> List(List(
          (3, None),
          (1, Some(double1)),
          (2, Some(double2)))))
        _ <- Ref.i_(5).Nss.*?(Ns.i.d2.double_?.d1).query.get.map(_ ==> List(List(
          (2, Some(double2)),
          (1, Some(double1)),
          (3, None))))

        _ <- Ref.i.Nss.*(Ns.i.boolean_?).insert((6, List(
          (2, Some(true)),
          (3, None)))).transact

        _ <- if (useFree) {
          Ref.i_(6).Nss.*?(Ns.i.a2.boolean_?.a1).query.get
            .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
            err ==> "Datomic Free (not Pro) has a bug that pulls boolean `false` values as nil."
          }
        } else {
          Ref.i_(6).Nss.*?(Ns.i.a2.boolean_?.a1).query.get.map(_ ==> List(List(
            (3, None),
            (2, Some(true)))))
        }
        _ <- if (useFree) {
          Ref.i_(6).Nss.*?(Ns.i.d2.boolean_?.d1).query.get
            .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
            err ==> "Datomic Free (not Pro) has a bug that pulls boolean `false` values as nil."
          }
        } else {
          Ref.i_(6).Nss.*?(Ns.i.d2.boolean_?.d1).query.get.map(_ ==> List(List(
            (2, Some(true)),
            (3, None))))
        }

        _ <- Ref.i.Nss.*(Ns.i.bigInt_?).insert((7, List(
          (1, Some(bigInt1)),
          (2, Some(bigInt2)),
          (3, None)))).transact
        _ <- Ref.i_(7).Nss.*?(Ns.i.a2.bigInt_?.a1).query.get.map(_ ==> List(List(
          (3, None),
          (1, Some(bigInt1)),
          (2, Some(bigInt2)))))
        _ <- Ref.i_(7).Nss.*?(Ns.i.d2.bigInt_?.d1).query.get.map(_ ==> List(List(
          (2, Some(bigInt2)),
          (1, Some(bigInt1)),
          (3, None))))

        _ <- Ref.i.Nss.*(Ns.i.bigDecimal_?).insert((8, List(
          (1, Some(bigDecimal1)),
          (2, Some(bigDecimal2)),
          (3, None)))).transact
        _ <- Ref.i_(8).Nss.*?(Ns.i.a2.bigDecimal_?.a1).query.get.map(_ ==> List(List(
          (3, None),
          (1, Some(bigDecimal1)),
          (2, Some(bigDecimal2)))))
        _ <- Ref.i_(8).Nss.*?(Ns.i.d2.bigDecimal_?.d1).query.get.map(_ ==> List(List(
          (2, Some(bigDecimal2)),
          (1, Some(bigDecimal1)),
          (3, None))))

        _ <- Ref.i.Nss.*(Ns.i.date_?).insert((9, List(
          (1, Some(date1)),
          (2, Some(date2)),
          (3, None)))).transact
        _ <- Ref.i_(9).Nss.*?(Ns.i.a2.date_?.a1).query.get.map(_ ==> List(List(
          (3, None),
          (1, Some(date1)),
          (2, Some(date2)))))
        _ <- Ref.i_(9).Nss.*?(Ns.i.d2.date_?.d1).query.get.map(_ ==> List(List(
          (2, Some(date2)),
          (1, Some(date1)),
          (3, None))))

        _ <- Ref.i.Nss.*(Ns.i.uuid_?).insert((10, List(
          (1, Some(uuid1)),
          (2, Some(uuid2)),
          (3, None)))).transact
        _ <- Ref.i_(10).Nss.*?(Ns.i.a2.uuid_?.a1).query.get.map(_ ==> List(List(
          (3, None),
          (1, Some(uuid1)),
          (2, Some(uuid2)))))
        _ <- Ref.i_(10).Nss.*?(Ns.i.d2.uuid_?.d1).query.get.map(_ ==> List(List(
          (2, Some(uuid2)),
          (1, Some(uuid1)),
          (3, None))))

        _ <- Ref.i.Nss.*(Ns.i.uri_?).insert((11, List(
          (1, Some(uri1)),
          (2, Some(uri2)),
          (3, None)))).transact
        _ <- Ref.i_(11).Nss.*?(Ns.i.a2.uri_?.a1).query.get.map(_ ==> List(List(
          (3, None),
          (1, Some(uri1)),
          (2, Some(uri2)))))
        _ <- Ref.i_(11).Nss.*?(Ns.i.d2.uri_?.d1).query.get.map(_ ==> List(List(
          (2, Some(uri2)),
          (1, Some(uri1)),
          (3, None))))

        _ <- Ref.i.Nss.*(Ns.i.byte_?).insert((12, List(
          (1, Some(byte1)),
          (2, Some(byte2)),
          (3, None)))).transact
        _ <- Ref.i_(12).Nss.*?(Ns.i.a2.byte_?.a1).query.get.map(_ ==> List(List(
          (3, None),
          (1, Some(byte1)),
          (2, Some(byte2)))))
        _ <- Ref.i_(12).Nss.*?(Ns.i.d2.byte_?.d1).query.get.map(_ ==> List(List(
          (2, Some(byte2)),
          (1, Some(byte1)),
          (3, None))))

        _ <- Ref.i.Nss.*(Ns.i.short_?).insert((13, List(
          (1, Some(short1)),
          (2, Some(short2)),
          (3, None)))).transact
        _ <- Ref.i_(13).Nss.*?(Ns.i.a2.short_?.a1).query.get.map(_ ==> List(List(
          (3, None),
          (1, Some(short1)),
          (2, Some(short2)))))
        _ <- Ref.i_(13).Nss.*?(Ns.i.d2.short_?.d1).query.get.map(_ ==> List(List(
          (2, Some(short2)),
          (1, Some(short1)),
          (3, None))))

        _ <- Ref.i.Nss.*(Ns.i.char_?).insert((14, List(
          (1, Some(char1)),
          (2, Some(char2)),
          (3, None)))).transact
        _ <- Ref.i_(14).Nss.*?(Ns.i.a2.char_?.a1).query.get.map(_ ==> List(List(
          (3, None),
          (1, Some(char1)),
          (2, Some(char2)))))
        _ <- Ref.i_(14).Nss.*?(Ns.i.d2.char_?.d1).query.get.map(_ ==> List(List(
          (2, Some(char2)),
          (1, Some(char1)),
          (3, None))))

        _ <- Ref.i.Nss.*(Ns.i.ref_?).insert((15, List(
          (1, Some(ref1)),
          (2, Some(ref2)),
          (3, None)))).transact
        _ <- Ref.i_(15).Nss.*?(Ns.i.a2.ref_?.a1).query.get.map(_ ==> List(List(
          (3, None),
          (1, Some(ref1)),
          (2, Some(ref2)))))
        _ <- Ref.i_(15).Nss.*?(Ns.i.d2.ref_?.d1).query.get.map(_ ==> List(List(
          (2, Some(ref2)),
          (1, Some(ref1)),
          (3, None))))
      } yield ()
    }


    "Sort top level" - refs { implicit conn =>
      for {
        _ <- Ns.s.Rs1.*(R1.i).insert(
          ("A", List(1, 2)),
          ("B", List(1, 2)),
        ).transact

        _ <- Ns.s.a1.Rs1.*(R1.i.a1).query.get.map(_ ==> List(
          ("A", List(1, 2)),
          ("B", List(1, 2)),
        ))
        _ <- Ns.s.a1.Rs1.*(R1.i.d1).query.get.map(_ ==> List(
          ("A", List(2, 1)),
          ("B", List(2, 1)),
        ))
        _ <- Ns.s.d1.Rs1.*(R1.i.a1).query.get.map(_ ==> List(
          ("B", List(1, 2)),
          ("A", List(1, 2)),
        ))
        _ <- Ns.s.d1.Rs1.*(R1.i.d1).query.get.map(_ ==> List(
          ("B", List(2, 1)),
          ("A", List(2, 1)),
        ))
      } yield ()
    }


    "Options" - refs { implicit conn =>
      for {
        _ <- Ns.i.s_?.Rs1.*(R1.i.s_?).insert(
          (1, Some("A"), List(
            (1, Some("a")),
            (1, Some("b")),
            (2, Some("a")),
            (2, Some("b")),
            (2, None),
          )),
          (2, Some("B"), List(
            (1, Some("a")),
            (1, Some("b")),
            (2, Some("a")),
            (2, Some("b")),
          )),
          (3, Some("C"), List()),
          (4, None, List()),
        ).transact

        // a1 *? a1/a2
        _ <- Ns.i.s_?.a1.Rs1.*?(R1.i.a1.s_?.a2).query.get.map(_ ==> List(
          (4, None, List()),
          (1, Some("A"), List(
            (1, Some("a")),
            (1, Some("b")),
            (2, None),
            (2, Some("a")),
            (2, Some("b")),
          )),
          (2, Some("B"), List(
            (1, Some("a")),
            (1, Some("b")),
            (2, Some("a")),
            (2, Some("b")),
          )),
          (3, Some("C"), List()),
        ))

        // a1 *? a1/d2
        _ <- Ns.i.s_?.a1.Rs1.*?(R1.i.a1.s_?.d2).query.get.map(_ ==> List(
          (4, None, List()),
          (1, Some("A"), List(
            (1, Some("b")),
            (1, Some("a")),
            (2, Some("b")),
            (2, Some("a")),
            (2, None),
          )),
          (2, Some("B"), List(
            (1, Some("b")),
            (1, Some("a")),
            (2, Some("b")),
            (2, Some("a")),
          )),
          (3, Some("C"), List()),
        ))

        // a1 *? d1/a2
        _ <- Ns.i.s_?.a1.Rs1.*?(R1.i.d1.s_?.a2).query.get.map(_ ==> List(
          (4, None, List()),
          (1, Some("A"), List(
            (2, None),
            (2, Some("a")),
            (2, Some("b")),
            (1, Some("a")),
            (1, Some("b")),
          )),
          (2, Some("B"), List(
            (2, Some("a")),
            (2, Some("b")),
            (1, Some("a")),
            (1, Some("b")),
          )),
          (3, Some("C"), List()),
        ))

        // a1 *? d1/d2
        _ <- Ns.i.s_?.a1.Rs1.*?(R1.i.d1.s_?.d2).query.get.map(_ ==> List(
          (4, None, List()),
          (1, Some("A"), List(
            (2, Some("b")),
            (2, Some("a")),
            (2, None),
            (1, Some("b")),
            (1, Some("a")),
          )),
          (2, Some("B"), List(
            (2, Some("b")),
            (2, Some("a")),
            (1, Some("b")),
            (1, Some("a")),
          )),
          (3, Some("C"), List()),
        ))

        // d1 *? d1/d2
        _ <- Ns.i.s_?.d1.Rs1.*?(R1.i.d1.s_?.d2).query.get.map(_ ==> List(
          (3, Some("C"), List()),
          (2, Some("B"), List(
            (2, Some("b")),
            (2, Some("a")),
            (1, Some("b")),
            (1, Some("a")),
          )),
          (1, Some("A"), List(
            (2, Some("b")),
            (2, Some("a")),
            (2, None),
            (1, Some("b")),
            (1, Some("a")),
          )),
          (4, None, List()),
        ))

        // d1 *? d2/d1
        _ <- Ns.i.s_?.d1.Rs1.*?(R1.i.d2.s_?.d1).query.get.map(_ ==> List(
          (3, Some("C"), List()),
          (2, Some("B"), List(
            (2, Some("b")),
            (1, Some("b")),
            (2, Some("a")),
            (1, Some("a")),
          )),
          (1, Some("A"), List(
            (2, Some("b")),
            (1, Some("b")),
            (2, Some("a")),
            (1, Some("a")),
            (2, None),
          )),
          (4, None, List()),
        ))

        // d1(expr) *? d2/d1
        _ <- Ns.i.s.>("A").d1.Rs1.*?(R1.i.d2.s_?.d1).query.get.map(_ ==> List(
          (3, "C", List()),
          (2, "B", List(
            (2, Some("b")),
            (1, Some("b")),
            (2, Some("a")),
            (1, Some("a")),
          ))
        ))

        // expr+d1 * a2/d1(expr)
        _ <- Ns.i.<=(2).s.d1.Rs1.*(R1.i.a2.s.>=("a").d1).query.get.map(_ ==> List(
          (2, "B", List(
            (1, "b"),
            (2, "b"),
            (1, "a"),
            (2, "a"),
          )),
          (1, "A", List(
            (1, "b"),
            (2, "b"),
            (1, "a"),
            (2, "a"),
          )),
        ))
      } yield ()
    }


    "2 sub levels" - refs { implicit conn =>
      for {
        _ <- Ns.i.s_?.Rs1.*(R1.i.s_?.Rs2.*(R2.s)).insert(
          (1, Some("A"), List(
            (1, Some("a"), List("x", "y")),
            (1, Some("b"), List("y", "x")),
            (2, Some("a"), List("x", "y")),
            (2, Some("b"), Nil),
            (2, None, List("x", "y")),
          )),
          (2, Some("B"), List(
            (1, Some("a"), List("x", "y")),
            (1, Some("b"), List("y", "x")),
            (2, Some("a"), List("x", "y")),
            (2, Some("b"), List("y", "x")),
          )),
          (3, Some("C"), Nil),
          (4, None, Nil),
        ).transact

        _ <- Ns.i.s_?.a1.Rs1.*?(R1.i.d2.s_?.a1.Rs2.*?(R2.s.d1)).query.get.map(_ ==> List(
          (4, None, Nil),
          (1, Some("A"), List(
            (2, None, List("y", "x")),
            (2, Some("a"), List("y", "x")),
            (1, Some("a"), List("y", "x")),
            (2, Some("b"), Nil),
            (1, Some("b"), List("y", "x")),
          )),
          (2, Some("B"), List(
            (2, Some("a"), List("y", "x")),
            (1, Some("a"), List("y", "x")),
            (2, Some("b"), List("y", "x")),
            (1, Some("b"), List("y", "x")),
          )),
          (3, Some("C"), Nil),
        ))

        _ <- Ns.i.a1.s_?.Rs1.*(R1.i.d2.s_?.a1.Rs2.*(R2.s.d1)).query.get.map(_ ==> List(
          (1, Some("A"), List(
            (2, None, List("y", "x")),
            (2, Some("a"), List("y", "x")),
            (1, Some("a"), List("y", "x")),
            (1, Some("b"), List("y", "x")),
          )),
          (2, Some("B"), List(
            (2, Some("a"), List("y", "x")),
            (1, Some("a"), List("y", "x")),
            (2, Some("b"), List("y", "x")),
            (1, Some("b"), List("y", "x")),
          )),
        ))

        _ <- Ns.i.s_?.a1.Rs1.*(R1.i.d2.s_?.a1.Rs2.*(R2.s.d1)).query.get.map(_ ==> List(
          (1, Some("A"), List(
            (2, None, List("y", "x")),
            (2, Some("a"), List("y", "x")),
            (1, Some("a"), List("y", "x")),
            (1, Some("b"), List("y", "x")),
          )),
          (2, Some("B"), List(
            (2, Some("a"), List("y", "x")),
            (1, Some("a"), List("y", "x")),
            (2, Some("b"), List("y", "x")),
            (1, Some("b"), List("y", "x")),
          )),
        ))
      } yield ()
    }
  }
}
