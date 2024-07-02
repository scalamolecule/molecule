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
        _ <- Ns.int.insert(1).transact
        _ <- Ns.int.query.get.map(_ ==> List(1))
      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {

        _ <- A.i.insert(2).transact
        _ <- A.i.query.get.map(_ ==> List(2))

      } yield ()
    }
  }
}
