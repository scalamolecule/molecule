package molecule.coreTests.test.crud.update.set

import molecule.base.error.ModelError
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Unique._
import molecule.coreTests.async._
import molecule.coreTests.setup.CoreTestSuite
import molecule.core.spi.SpiAsync
import utest._


trait UpdateSet_uniqueAttr extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>


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
