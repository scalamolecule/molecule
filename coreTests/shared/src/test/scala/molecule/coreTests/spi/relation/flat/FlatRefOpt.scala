package molecule.coreTests.spi.relation.flat

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._


trait FlatRefOpt extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "Basic optional ref" - refs { implicit conn =>
      for {
        _ <- A.i(1).save.transact
        _ <- A.i(2).B.i(20).save.transact

        _ <- A.i.B.?(B.i).query.get.map(_ ==> List(
          (1, None),
          (2, Some(20)),
        ))
      } yield ()
    }

  }
}
