package molecule.db.datomic.test.ref.nested

import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object NestedBasic extends DatomicTestSuite {


  lazy val tests = Tests {

    "Mandatory/optional rows" - refs { implicit conn =>
      Ns.n.Rs1.*(R1.n1).insert(
        (1, List(10, 11)),
        (2, Nil),
      ).transact

      // Mandatory nested data
      Ns.n.Rs1.*(R1.n1).query.get ==> List(
        (1, List(10, 11)),
      )

      // Optional nested data
      Ns.n.Rs1.*?(R1.n1).query.get ==> List(
        (1, List(10, 11)),
        (2, Nil),
      )
    }
  }
}
