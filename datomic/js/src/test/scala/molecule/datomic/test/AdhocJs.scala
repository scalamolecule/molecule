package molecule.datomic.test

import boopickle.Default._
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import utest._


object AdhocJs extends DatomicTestSuite {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      for {
        _ <- Ns.i(1).save.transact


      } yield ()
    }


    //    "refs" - refs { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Refs._
    //      for {
    //
    //        _ <- Ns.i.Rs1.*(R1.i).insert(0, List(1)).transact
    //
    //      } yield ()
    //    }
  }
}
