package molecule.db.datomic.test.ref.nested

import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object NestedBasics extends DatomicTestSuite {


  lazy val tests = Tests {


    "Nested data not allowed in save" - refs { implicit conn =>
      try {
        Ns.int(0).Rs1.*(R1.int1(1)).save.transact
        throw MoleculeException("Unexpected success")
      } catch {
        case MoleculeException(err, _) =>
          err ==> "Nested data structure not allowed in save molecule."
      }

      try {
        Ns.int(0).Rs1.*?(R1.int1(1)).save.transact
        throw MoleculeException("Unexpected success")
      } catch {
        case MoleculeException(err, _) =>
          err ==> "Optional nested data structure not allowed in save molecule."
      }
    }


    "Correct nested ref" - refs { implicit conn =>
      try {
        Ns.n.Rs1.*?(R4.n4).query.get
        throw MoleculeException("Unexpected success")
      } catch {
        case MoleculeException(err, _) =>
          err ==> "Optional nested data structure not allowed in save molecule."
      }
    }


    "insert" - refs { implicit conn =>


      //      m(Ns.str.Rs1 * R1.int1.str1_?).insert(List(
      //        ("A", List((1, Some("a")), (2, None))),
      //        ("B", List())
      //      )).transact
      //
      //      m(Ns.str.Rs1 *? R1.int1.str1_?).query.get ==> List(
      //        ("A", List((1, Some("a")), (2, None))),
      //        ("B", List())
      //      )
      //
      ////      m(Ns.str.Rs1 * R1.int1.str1_?).query.get ==> List(
      ////        ("A", List((1, Some("a")), (2, None)))
      ////      )


      Ns.str.Rs1.*(R1.int1.Rs2.*(R2.int2)).insert(List(
        ("a", List(
          (1, List(11, 12)),
          (2, List())
        )),
        ("b", List())
      )).transact


      Ns.str.Rs1.*(R1.int1.Rs2.*(R2.int2)).query.get ==> List(
        ("a", List(
          (1, List(11, 12))
        ))
      )


      Ns.str.a1.Rs1.*?(R1.int1.Rs2.*?(R2.int2)).query.get ==> List(
        ("a", List(
          (1, List(11, 12)),
          (2, List())
        )),
        ("b", List())
      )

    }
  }
}
