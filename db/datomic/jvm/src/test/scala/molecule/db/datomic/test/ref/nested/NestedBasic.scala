package molecule.db.datomic.test.ref.nested

import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object NestedBasic extends DatomicTestSuite {


  lazy val tests = Tests {

    "Mandatory/optional rows" - refs { implicit conn =>
      Ns.i.Rs1.*(R1.i).insert(
        (1, List(10, 11)),
        (2, Nil),
      ).transact

      // Mandatory nested data
      Ns.i.Rs1.*(R1.i.a1).query.get ==> List(
        (1, List(10, 11)),
      )

      // Optional nested data
      Ns.i.Rs1.*?(R1.i.a1).query.get ==> List(
        (1, List(10, 11)),
        (2, Nil),
      )
    }
  }
}
