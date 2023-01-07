package molecule.db.datomic.test

import boopickle.Default._
import molecule.base.util.exceptions.MoleculeException
import molecule.core.util.Executor._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AdhocJs extends DatomicTestSuite {

  lazy val tests = Tests {

    //    "types" - types { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Types._
    //      for {
    //
    //        _ <- Ns.i(1).save.transact
    //
    //      } yield ()
    //    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- (R3.i + Ns.i.R1.i).insert(0, (1, 2)).transact

        _ <- R3.i.query.get.map(_ ==> List(0))
        _ <- (R3.i + Ns.R1.i_).query.get.map(_ ==> List(0))

      } yield ()
    }
  }
}
