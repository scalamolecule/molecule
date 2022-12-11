package molecule.db.datomic.test


import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object Adhoc extends DatomicTestSuite {


  lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._


    }

    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._


//      Ns.i.v.Self.i(v).s

//      Ns.i.>(1).as(v1).R1.i.<(v1)

    }
  }

}
