// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.one.number

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterOneInteger_Long_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "odd/even" - types { implicit conn =>
      for {
        _ <- Ns.i.long.insert(
          (-2, -long2),
          (-1, -long1),
          (0, long0),
          (1, long1),
          (2, long2),
        ).transact

        _ <- Ns.long.even.a1.query.get.map(_ ==> List(-long2, long0, long2))
        _ <- Ns.i.a1.long_.even.query.get.map(_ ==> List(-2, 0, 2))

        _ <- Ns.long.odd.a1.query.get.map(_ ==> List(-long1, long1))
        _ <- Ns.i.a1.long_.odd.query.get.map(_ ==> List(-1, 1))
      } yield ()
    }


    "modulo" - types { implicit conn =>
      for {
        _ <- Ns.i.long.insert(
          (1, long1),
          (2, long2),
          (3, long3),
          (4, long4),
          (5, long5),
          (6, long6),
          (7, long7),
          (8, long8),
          (9, long9),
        ).transact

        // Mandatory

        _ <- Ns.long.%(long2, long0).query.get.map(_ ==> List(long2, long4, long6, long8))
        _ <- Ns.long.%(long2, long1).query.get.map(_ ==> List(long1, long3, long5, long7, long9))

        _ <- Ns.long.%(long3, long0).query.get.map(_ ==> List(long3, long6, long9))
        _ <- Ns.long.%(long3, long1).query.get.map(_ ==> List(long1, long4, long7))
        _ <- Ns.long.%(long3, long2).query.get.map(_ ==> List(long2, long5, long8))

        // Tacit

        _ <- Ns.i.long_.%(long2, long0).query.get.map(_ ==> List(2, 4, 6, 8))
        _ <- Ns.i.long_.%(long2, long1).query.get.map(_ ==> List(1, 3, 5, 7, 9))

        _ <- Ns.i.long_.%(long3, long0).query.get.map(_ ==> List(3, 6, 9))
        _ <- Ns.i.long_.%(long3, long1).query.get.map(_ ==> List(1, 4, 7))
        _ <- Ns.i.long_.%(long3, long2).query.get.map(_ ==> List(2, 5, 8))
      } yield ()
    }
  }
}
