// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.one.decimal

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterOneDecimal_Float_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "ceil/floor" - types { implicit conn =>
      for {
        _ <- Ns.float.insert(
          -float2, // -2.2
          -float1, // -1.1
          float0, //   0.0
          float1, //   1.1
          float2 //    2.2
        ).transact

        // To avoid changing type, Molecule returns whole decimal numbers

        _ <- Ns.float.ceil.query.get.map(_.sorted ==> List(
          -float20, // -2.0
          -float10, // -1.0
          float0, //    0.0
          float20, //   2.0
          float30, //   3.0
        ))

        _ <- Ns.float.floor.query.get.map(_.sorted ==> List(
          -float30, // -3.0
          -float20, // -2.0
          float0, //    0.0
          float10, //   1.0
          float20, //   2.0
        ))
      } yield ()
    }
  }
}