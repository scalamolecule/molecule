package molecule.db.datomic.test

import boopickle.Default._
import molecule.base.util.exceptions.MoleculeException
import molecule.core.util.Executor._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AdhocJs extends DatomicTestSuite {

  lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {

        _ <- Ns.i(1).save.transact

      } yield ()
    }


    //    "refs" - refs { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Refs._
    //      for {
    ////        _ <- Ns.i.Tx(R2.i_(7)).insert(1).transact
    ////        _ <- Ns.i.Tx(R2.i).query.get.map(_ ==> List((1, 7)))
    //
    ////        _ <- Ns.i(1).s("a").Tx(R2.i_(7)).save.transact
    ////        _ <- Ns.i.s.Tx(R2.i).query.get.map(_ ==> List((1, "a", 7)))
    //
    //
    //        _ <- Ns.i(1).Tx(R2.i_(7).s_("tx")).save.transact
    //        _ <- Ns.i.Tx(R2.i.s).query.get.map(_ ==> List(
    //          (1, 7, "tx")
    //        ))
    //
    ////        _ <- Ns.i.s.Tx(R2.i_(7)).insert((1, "a"), (2, "b")).transact
    ////        _ <- Ns.i.s.Tx(R2.i).query.get.map(_ ==> List((1, "a", 7), (2, "b", 7)))
    //      } yield ()
    //    }
  }
}
