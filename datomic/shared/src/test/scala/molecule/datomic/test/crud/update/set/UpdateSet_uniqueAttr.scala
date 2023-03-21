package molecule.datomic.test.crud.update.set

import molecule.base.util.exceptions.ExecutionError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Unique._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import utest._


object UpdateSet_uniqueAttr extends DatomicTestSuite {


  lazy val tests = Tests {

    "Semantics" - unique { implicit conn =>
      for {
        _ <- Unique.ints_(1).s("b").update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err, _) =>
          err ==> "Can only lookup entity with card-one attribute value. Found:\n" +
            """AttrSetTacInt("Unique", "ints", Appl, Seq(Set(1)), None, None, None)"""
        }
      } yield ()
    }
  }
}
