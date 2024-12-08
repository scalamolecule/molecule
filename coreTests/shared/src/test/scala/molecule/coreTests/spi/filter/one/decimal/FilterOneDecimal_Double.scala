package molecule.coreTests.spi.filter.one.decimal

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterOneDecimal_Double extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "ceil/floor" - types { implicit conn =>
      for {
        _ <- Ns.double.insert(
          -double2, // -2.2
          -double1, // -1.1
          double0, //   0.0
          double1, //   1.1
          double2 //    2.2
        ).transact

        // To avoid changing type, Molecule returns whole decimal numbers

        _ <- Ns.double.ceil.query.get.map(_.sorted ==> List(
          -double20, // -2.0
          -double10, // -1.0
          double0, //    0.0
          double20, //   2.0
          double30, //   3.0
        ))

        _ <- Ns.double.floor.query.get.map(_.sorted ==> List(
          -double30, // -3.0
          -double20, // -2.0
          double0, //    0.0
          double10, //   1.0
          double20, //   2.0
        ))
      } yield ()
    }
  }
}