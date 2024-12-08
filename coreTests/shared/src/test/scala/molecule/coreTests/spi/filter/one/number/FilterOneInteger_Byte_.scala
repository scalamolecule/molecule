// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.one.number

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterOneInteger_Byte_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "odd/even" - types { implicit conn =>
      for {
        _ <- Ns.i.byte.insert(
          (-2, -2.toByte),
          (-1, -1.toByte),
          (0, byte0),
          (1, byte1),
          (2, byte2),
        ).transact

        _ <- Ns.byte.even.a1.query.get.map(_ ==> List(-2.toByte, byte0, byte2))
        _ <- Ns.i.a1.byte_.even.query.get.map(_ ==> List(-2, 0, 2))

        _ <- Ns.byte.odd.a1.query.get.map(_ ==> List(-1.toByte, byte1))
        _ <- Ns.i.a1.byte_.odd.query.get.map(_ ==> List(-1, 1))
      } yield ()
    }


    "modulo" - types { implicit conn =>
      for {
        _ <- Ns.i.byte.insert(
          (1, byte1),
          (2, byte2),
          (3, byte3),
          (4, byte4),
          (5, byte5),
          (6, byte6),
          (7, byte7),
          (8, byte8),
          (9, byte9),
        ).transact

        // Mandatory

        _ <- Ns.byte.%(byte2, byte0).query.get.map(_ ==> List(byte2, byte4, byte6, byte8))
        _ <- Ns.byte.%(byte2, byte1).query.get.map(_ ==> List(byte1, byte3, byte5, byte7, byte9))

        _ <- Ns.byte.%(byte3, byte0).query.get.map(_ ==> List(byte3, byte6, byte9))
        _ <- Ns.byte.%(byte3, byte1).query.get.map(_ ==> List(byte1, byte4, byte7))
        _ <- Ns.byte.%(byte3, byte2).query.get.map(_ ==> List(byte2, byte5, byte8))

        // Tacit

        _ <- Ns.i.byte_.%(byte2, byte0).query.get.map(_ ==> List(2, 4, 6, 8))
        _ <- Ns.i.byte_.%(byte2, byte1).query.get.map(_ ==> List(1, 3, 5, 7, 9))

        _ <- Ns.i.byte_.%(byte3, byte0).query.get.map(_ ==> List(3, 6, 9))
        _ <- Ns.i.byte_.%(byte3, byte1).query.get.map(_ ==> List(1, 4, 7))
        _ <- Ns.i.byte_.%(byte3, byte2).query.get.map(_ ==> List(2, 5, 8))
      } yield ()
    }
  }
}
