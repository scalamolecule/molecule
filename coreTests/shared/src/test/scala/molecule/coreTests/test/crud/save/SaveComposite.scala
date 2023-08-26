package molecule.coreTests.test.crud.save

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait SaveComposite extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>


  override lazy val tests = Tests {

    "Composite" - refs { implicit conn =>
      for {
        _ <- (A.i(1) + C.i(2)).save.transact
        _ <- (A.i + C.i).query.get.map(_ ==> List((1, 2)))

        _ <- (A.B.i(1) + C.i(2)).save.transact
        _ <- (A.B.i + C.i).query.get.map(_ ==> List((1, 2)))
      } yield ()
    }
  }
}
