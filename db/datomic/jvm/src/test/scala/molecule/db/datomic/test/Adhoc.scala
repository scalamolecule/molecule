package molecule.db.datomic.test


import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object Adhoc extends DatomicTestSuite {


  lazy val tests = Tests {

    "core" - types { implicit conn =>

//      Ns.int.d1.query.get ==> List(3, 2, 1)
//      Ns.int.a1.query.get ==> List(1, 2, 3)

      //      One.string.int.insert(("Bob", 42), ("Liz", 35)).transact
      //      One.string.int.query.get ==> List(("Liz", 35), ("Bob", 42))
    }
  }

}
