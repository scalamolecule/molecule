package molecule.coreTests.spi.filter.map

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.concurrent.Future

trait MapSemantics extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Matching entire map not supported" - types { implicit conn =>
      for {
        _ <- Ns.intMap(Map(pint1)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Matching/applying a map for map attribute `Ns.intMap` is not supported in queries."
          }

        _ <- Ns.intMap_?(Some(Map(pint1))).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Matching/applying a map for map attribute `Ns.intMap` is not supported in queries."
          }

        _ <- Future(compileError("Ns.intMap_(Map(pint1)).query.get"))
      } yield ()
    }
  }
}