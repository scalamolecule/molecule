package molecule.datalog.datomic.test

import molecule.datalog.datomic.setup.DatomicTestSuite
import molecule.datalog.datomic.sync._
import utest._
import scala.language.implicitConversions


object AdhocDatomicJVMsync extends DatomicTestSuite {


  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._

      Ns.i.insert(1, 2, 3).transact
      Ns.i(min(2)).query.get ==> List(Set(1, 2))


    }


    //    "refs" - refs { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Refs._
    //      A.s.Bb.*(B.i.a1).query.inspect
    //    }
  }
}
