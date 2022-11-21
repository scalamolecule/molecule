package molecule.db.datomic.test.ref.nested

import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object NestedBasic extends DatomicTestSuite {


  lazy val tests = Tests {

    "mandatory/optional" - refs { implicit conn =>
      Ns.str.Rs1.*(R1.int1).insert(List(
        ("a", List(1, 2)),
        ("b", Nil) // "b" has no nested data
      )).transact

      // Mandatory nested data
      Ns.str.Rs1.*(R1.int1).insert(List(
        ("a", List(1, 2))
      )).transact

      // Optional nested data
      Ns.str.Rs1.*?(R1.int1).insert(List(
        ("a", List(1, 2)),
        ("b", Nil)
      )).transact
    }
  }
}
