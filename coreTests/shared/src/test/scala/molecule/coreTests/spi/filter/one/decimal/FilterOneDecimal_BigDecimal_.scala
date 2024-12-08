// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.one.decimal

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterOneDecimal_BigDecimal_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "ceil/floor" - types { implicit conn =>
      for {
        _ <- Ns.bigDecimal.insert(
          -bigDecimal2, // -2.2
          -bigDecimal1, // -1.1
          bigDecimal0, //   0.0
          bigDecimal1, //   1.1
          bigDecimal2 //    2.2
        ).transact

        // To avoid changing type, Molecule returns whole decimal numbers

        _ <- Ns.bigDecimal.ceil.query.get.map(_.sorted ==> List(
          -bigDecimal20, // -2.0
          -bigDecimal10, // -1.0
          bigDecimal0, //    0.0
          bigDecimal20, //   2.0
          bigDecimal30, //   3.0
        ))

        _ <- Ns.bigDecimal.floor.query.get.map(_.sorted ==> List(
          -bigDecimal30, // -3.0
          -bigDecimal20, // -2.0
          bigDecimal0, //    0.0
          bigDecimal10, //   1.0
          bigDecimal20, //   2.0
        ))
      } yield ()
    }
  }
}