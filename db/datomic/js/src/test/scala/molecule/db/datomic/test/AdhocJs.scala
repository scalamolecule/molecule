package molecule.db.datomic.test

import boopickle.Default._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._
//import molecule.db.datomic.RpcTester._
import molecule.core.util.Executor._
import molecule.db.datomic._


object AdhocJs extends DatomicTestSuite {

  lazy val tests = Tests {

    "types" - types { implicit conn =>
      val proxy = conn.proxy
      for {

        _ <- Ns.i(1).s("a").save.transact
        _ <- Ns.i(2).s("b").save.transact
        _ <- Ns.i.s.query.get.map(_ ==> List((1, "a"), (2, "b")))
//        _ <- conn.rpc.query2(conn.proxy, es)
      } yield ()
    }


    //    "refs" - refs { implicit conn =>
    //      //      Ns.i.v.Self.i(v).s
    //      //      Ns.i.>(1).as(v1).R1.i.<(v1)
    //
    //    }
  }
}
