package molecule.datomic.test.crud.update.set

import molecule.base.error.ExecutionError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Unique._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import utest._


object UpdateSet_uniqueAttr extends DatomicTestSuite {


  override lazy val tests = Tests {

    "Semantics" - unique { implicit conn =>
      for {
        _ <- Unique.ints_(1).s("b").update.transact.expect { case ExecutionError(err, _) =>
          err ==> "Can only lookup entity with card-one attribute value. Found:\n" +
            """AttrSetTacInt("Unique", "ints", Appl, Seq(Set(1)), None, Nil, None, None)"""
        }
      } yield ()
    }
  }
}