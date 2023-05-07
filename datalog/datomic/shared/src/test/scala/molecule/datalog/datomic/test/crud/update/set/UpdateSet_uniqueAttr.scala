package molecule.datalog.datomic.test.crud.update.set

import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Unique._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._


object UpdateSet_uniqueAttr extends DatomicTestSuite {


  override lazy val tests = Tests {

    "Semantics" - unique { implicit conn =>
      for {
        _ <- Unique.ints_(1).s("b").update.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can only lookup entity with card-one attribute value. Found:\n" +
            """AttrSetTacInt("Unique", "ints", Eq, Seq(Set(1)), None, None, Nil, Nil, None, None)"""
        }
      } yield ()
    }
  }
}