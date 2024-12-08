// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.one.number

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterOneInteger_Short_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "odd/even" - types { implicit conn =>
      for {
        _ <- Ns.i.short.insert(
          (-2, -2.toShort),
          (-1, -1.toShort),
          (0, short0),
          (1, short1),
          (2, short2),
        ).transact

        _ <- Ns.short.even.query.get.map(_ ==> List(-2.toShort, short0, short2))
        _ <- Ns.i.short_.even.query.get.map(_ ==> List(-2, 0, 2))

        _ <- Ns.short.odd.query.get.map(_ ==> List(-1.toShort, short1))
        _ <- Ns.i.short_.odd.query.get.map(_ ==> List(-1, 1))
      } yield ()
    }


    "modulo" - types { implicit conn =>
      for {
        _ <- Ns.i.short.insert(
          (1, short1),
          (2, short2),
          (3, short3),
          (4, short4),
          (5, short5),
          (6, short6),
          (7, short7),
          (8, short8),
          (9, short9),
        ).transact

        // Mandatory

        _ <- Ns.short.%(short2, short0).query.get.map(_ ==> List(short2, short4, short6, short8))
        _ <- Ns.short.%(short2, short1).query.get.map(_ ==> List(short1, short3, short5, short7, short9))

        _ <- Ns.short.%(short3, short0).query.get.map(_ ==> List(short3, short6, short9))
        _ <- Ns.short.%(short3, short1).query.get.map(_ ==> List(short1, short4, short7))
        _ <- Ns.short.%(short3, short2).query.get.map(_ ==> List(short2, short5, short8))

        // Tacit

        _ <- Ns.i.short_.%(short2, short0).query.get.map(_ ==> List(2, 4, 6, 8))
        _ <- Ns.i.short_.%(short2, short1).query.get.map(_ ==> List(1, 3, 5, 7, 9))

        _ <- Ns.i.short_.%(short3, short0).query.get.map(_ ==> List(3, 6, 9))
        _ <- Ns.i.short_.%(short3, short1).query.get.map(_ ==> List(1, 4, 7))
        _ <- Ns.i.short_.%(short3, short2).query.get.map(_ ==> List(2, 5, 8))
      } yield ()
    }
  }
}
