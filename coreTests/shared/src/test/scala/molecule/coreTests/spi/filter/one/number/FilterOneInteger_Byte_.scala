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


    "odd/even" - types { implicit conn =>
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

        _ <- Ns.byte.even.query.get.map(_ ==> List(byte2, byte4, byte6, byte8))
        _ <- Ns.byte.odd.query.get.map(_ ==> List(byte1, byte3, byte5, byte7, byte9))

        _ <- Ns.i.byte_.even.query.get.map(_ ==> List(2, 4, 6, 8))
        _ <- Ns.i.byte_.odd.query.get.map(_ ==> List(1, 3, 5, 7, 9))
      } yield ()
    }


    "comparison" - types { implicit conn =>
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

        _ <- Ns.i.a1.byte_.>(byte2).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8, 9))
        _ <- Ns.i.a1.byte_.>(byte2).byte_.<=(byte8).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8))
        _ <- Ns.i.a1.byte_.>(byte2).byte_.<=(byte8).byte_.not(byte4, byte5).query.get.map(_ ==> List(3, 6, 7, 8))
        _ <- Ns.i.a1.byte_.>(byte2).byte_.<=(byte8).byte_.not(byte4, byte5).byte_.odd.query.get.map(_ ==> List(3, 7))
      } yield ()
    }
  }
}
