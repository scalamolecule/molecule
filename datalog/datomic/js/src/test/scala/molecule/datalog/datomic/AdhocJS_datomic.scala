package molecule.datalog.datomic

import boopickle.Default._
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.TestSuite_datomic
import utest._


object AdhocJS_datomic extends TestSuite_datomic {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      for {
        List(a, b) <- Ns.int.insert(1, 2).transact.map(_.ids)
        _ <- Ns.int(3).save.transact
        _ <- Ns.int.a1.query.get.map(_ ==> List(1, 2, 3))
        _ <- Ns(a).int(10).update.transact
        _ <- Ns(b).delete.transact
        _ <- Ns.int.a1.query.get.map(_ ==> List(3, 10))
      } yield ()
    }


    //    "refs" - refs { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Refs._
    //      for {
    //
    //        _ <- A.i.insert(2).transact
    //        _ <- A.i.query.get.map(_ ==> List(2))
    //
    //      } yield ()
    //    }
  }
}
