package molecule.coreTests.spi.crud.update.map

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.concurrent.Future


trait UpdateMap_uniqueAttr extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "Semantics" - unique { implicit conn =>
      for {
        // Can't use map attributes to lookup entities
        _ <- Future(compileError("Uniques.intMap_(Map(pint1)).i(42).update.transact"))
      } yield ()
    }
  }
}
