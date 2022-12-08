package molecule.db.datomic.test

import molecule.db.datomic.setup.DatomicTestSuite
import utest._
import molecule.db.datomic._


object AdhocShared extends DatomicTestSuite {


  lazy val tests = Tests {

    "types" - types { implicit conn =>



//      Ns.i.Ns.s
    }

    "types" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._


      Ns.i(1).save
      Ns.i.insert(1)
    }
  }

}
