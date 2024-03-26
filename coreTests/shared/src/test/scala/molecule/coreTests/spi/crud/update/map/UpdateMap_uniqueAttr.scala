package molecule.coreTests.spi.crud.update.map

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Uniques._
import molecule.coreTests.setup.CoreTestSuite
import utest._


trait UpdateMap_uniqueAttr extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "Semantics" - unique { implicit conn =>
      for {
        _ <- Uniques.intMap_(Map(pint1)).s("b").update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can only lookup entity with card-one attribute value. Found:\n" +
              s"""AttrMapTacInt("Uniques", "intMap", Eq, Map($pint1), None, None, Nil, Nil, None, None, Seq(0, 27))"""
          }
      } yield ()
    }
  }
}
