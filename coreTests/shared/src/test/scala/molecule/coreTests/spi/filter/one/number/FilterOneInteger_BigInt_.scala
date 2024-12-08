// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.one.number

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterOneInteger_BigInt_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "odd/even" - types { implicit conn =>
      for {
        _ <- Ns.i.bigInt.insert(
          (-2, -bigInt2),
          (-1, -bigInt1),
          (0, bigInt0),
          (1, bigInt1),
          (2, bigInt2),
        ).transact

        _ <- Ns.bigInt.even.query.get.map(_ ==> List(-bigInt2, bigInt0, bigInt2))
        _ <- Ns.i.bigInt_.even.query.get.map(_ ==> List(-2, 0, 2))

        _ <- Ns.bigInt.odd.query.get.map(_ ==> List(-bigInt1, bigInt1))
        _ <- Ns.i.bigInt_.odd.query.get.map(_ ==> List(-1, 1))
      } yield ()
    }


    "modulo" - types { implicit conn =>
      for {
        _ <- Ns.i.bigInt.insert(
          (1, bigInt1),
          (2, bigInt2),
          (3, bigInt3),
          (4, bigInt4),
          (5, bigInt5),
          (6, bigInt6),
          (7, bigInt7),
          (8, bigInt8),
          (9, bigInt9),
        ).transact

        // Mandatory

        _ <- Ns.bigInt.%(bigInt2, bigInt0).query.get.map(_ ==> List(bigInt2, bigInt4, bigInt6, bigInt8))
        _ <- Ns.bigInt.%(bigInt2, bigInt1).query.get.map(_ ==> List(bigInt1, bigInt3, bigInt5, bigInt7, bigInt9))

        _ <- Ns.bigInt.%(bigInt3, bigInt0).query.get.map(_ ==> List(bigInt3, bigInt6, bigInt9))
        _ <- Ns.bigInt.%(bigInt3, bigInt1).query.get.map(_ ==> List(bigInt1, bigInt4, bigInt7))
        _ <- Ns.bigInt.%(bigInt3, bigInt2).query.get.map(_ ==> List(bigInt2, bigInt5, bigInt8))

        // Tacit

        _ <- Ns.i.bigInt_.%(bigInt2, bigInt0).query.get.map(_ ==> List(2, 4, 6, 8))
        _ <- Ns.i.bigInt_.%(bigInt2, bigInt1).query.get.map(_ ==> List(1, 3, 5, 7, 9))

        _ <- Ns.i.bigInt_.%(bigInt3, bigInt0).query.get.map(_ ==> List(3, 6, 9))
        _ <- Ns.i.bigInt_.%(bigInt3, bigInt1).query.get.map(_ ==> List(1, 4, 7))
        _ <- Ns.i.bigInt_.%(bigInt3, bigInt2).query.get.map(_ ==> List(2, 5, 8))
      } yield ()
    }
  }
}
