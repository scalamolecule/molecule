package molecule.coreTests.spi.crud.update.set

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Uniques._
import molecule.coreTests.setup.CoreTestSuite
import utest._


trait UpdateSet_uniqueAttr extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "Semantics" - unique { implicit conn =>
      for {
        _ <- Uniques.ints_(1).s("b").update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can only lookup entity with card-one attribute value. Found:\n" +
              """AttrSetTacInt("Uniques", "ints", Eq, Seq(Set(1)), None, None, Nil, Nil, None, None, Seq(0, 25))"""
          }
      } yield ()
    }
  }
}
