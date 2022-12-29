package molecule.db.datomic.test


import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object Adhoc extends DatomicTestSuite {

  lazy val tests = Tests {

    "types1" - types { implicit conn =>
      //      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        _ <- Ns.int(1).save.transact
        _ <- Ns.int.query.get.map(_ ==> List(1))
      } yield ()
    }

    //    "refs" - refs { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Refs._
    //
    //
    //      //      Ns.i.v.Self.i(v).s
    //
    //      //      Ns.i.>(1).as(v1).R1.i.<(v1)
    //
    //    }
  }

}
