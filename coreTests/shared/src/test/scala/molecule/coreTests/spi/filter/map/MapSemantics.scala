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
            err ==> "Matching collections (Ns.intMap) not supported in queries."
          }

        _ <- Ns.intMap_?(Some(Map(pint1))).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Matching collections (Ns.intMap) not supported in queries."
          }

        _ <- Future(compileError("Ns.intMap_(Map(pint1)).query.get"))
      } yield ()
    }


    "equal nothing" - types { implicit conn =>
      for {
        _ <- Ns.i.intMap_?.insert(List(
          (0, None),
          (1, Some(Map("a" -> int1))),
        )).transact

        // Match non-asserted attribute (null) with tacit attribute
        _ <- Ns.i.intMap_().query.get.map(_ ==> List(0))

        // Can't query for empty attribute
        _ <- Ns.i.intMap().query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Applying nothing to mandatory attribute (Ns.intMap) is reserved for updates to retract."
          }
      } yield ()
    }
  }
}