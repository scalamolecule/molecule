// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.one.decimal

import molecule.core.api.Api_async
import molecule.core.spi.{Spi_async, TxReport}
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.concurrent.Future

trait FilterOneDecimal_BigDecimal_ extends CoreTestSuite with Api_async { spi: Spi_async =>


  override lazy val tests = Tests {

    "modulo" - types { implicit conn =>
      def load: Future[TxReport] = Ns.i.bigDecimal.insert(
        (1, bigDecimal1),
        (2, bigDecimal2),
        (3, bigDecimal3),
        (4, bigDecimal4),
        (5, bigDecimal5),
        (6, bigDecimal6),
        (7, bigDecimal7),
        (8, bigDecimal8),
        (9, bigDecimal9),
      ).transact

      if (database == "SQlite") {
        // Remainder in SQlite is a whole number
        for {
          _ <- load
          _ <- Ns.bigDecimal.%(bigDecimal2, 0).query.get.map(_ ==> List(bigDecimal2, bigDecimal4, bigDecimal6, bigDecimal8))
          _ <- Ns.bigDecimal.%(bigDecimal2, 1).query.i.get.map(_ ==> List(bigDecimal1, bigDecimal3, bigDecimal5, bigDecimal7, bigDecimal9))

          _ <- Ns.bigDecimal.%(bigDecimal3, 0).query.get.map(_ ==> List(bigDecimal3, bigDecimal6, bigDecimal9))
          _ <- Ns.bigDecimal.%(bigDecimal3, 1).query.get.map(_ ==> List(bigDecimal1, bigDecimal4, bigDecimal7))
          _ <- Ns.bigDecimal.%(bigDecimal3, 2).query.get.map(_ ==> List(bigDecimal2, bigDecimal5, bigDecimal8))

          _ <- Ns.i.bigDecimal_.%(bigDecimal2, 0).query.get.map(_ ==> List(2, 4, 6, 8))
          _ <- Ns.i.bigDecimal_.%(bigDecimal2, 1).query.get.map(_ ==> List(1, 3, 5, 7, 9))

          _ <- Ns.i.bigDecimal_.%(bigDecimal3, 0).query.get.map(_ ==> List(3, 6, 9))
          _ <- Ns.i.bigDecimal_.%(bigDecimal3, 1).query.get.map(_ ==> List(1, 4, 7))
          _ <- Ns.i.bigDecimal_.%(bigDecimal3, 2).query.get.map(_ ==> List(2, 5, 8))
        } yield ()

      } else {
        for {
          _ <- load
          _ <- Ns.bigDecimal.%(bigDecimal2, bigDecimal0).query.get.map(_ ==> List(bigDecimal2, bigDecimal4, bigDecimal6, bigDecimal8))
          _ <- Ns.bigDecimal.%(bigDecimal2, bigDecimal1).query.i.get.map(_ ==> List(bigDecimal1, bigDecimal3, bigDecimal5, bigDecimal7, bigDecimal9))

          _ <- Ns.bigDecimal.%(bigDecimal3, bigDecimal0).query.get.map(_ ==> List(bigDecimal3, bigDecimal6, bigDecimal9))
          _ <- Ns.bigDecimal.%(bigDecimal3, bigDecimal1).query.get.map(_ ==> List(bigDecimal1, bigDecimal4, bigDecimal7))
          _ <- Ns.bigDecimal.%(bigDecimal3, bigDecimal2).query.get.map(_ ==> List(bigDecimal2, bigDecimal5, bigDecimal8))

          _ <- Ns.i.bigDecimal_.%(bigDecimal2, bigDecimal0).query.get.map(_ ==> List(2, 4, 6, 8))
          _ <- Ns.i.bigDecimal_.%(bigDecimal2, bigDecimal1).query.get.map(_ ==> List(1, 3, 5, 7, 9))

          _ <- Ns.i.bigDecimal_.%(bigDecimal3, bigDecimal0).query.get.map(_ ==> List(3, 6, 9))
          _ <- Ns.i.bigDecimal_.%(bigDecimal3, bigDecimal1).query.get.map(_ ==> List(1, 4, 7))
          _ <- Ns.i.bigDecimal_.%(bigDecimal3, bigDecimal2).query.get.map(_ ==> List(2, 5, 8))
        } yield ()
      }
    }


    "comparison" - types { implicit conn =>
      for {
        _ <- Ns.i.bigDecimal.insert(
          (1, bigDecimal1),
          (2, bigDecimal2),
          (3, bigDecimal3),
          (4, bigDecimal4),
          (5, bigDecimal5),
          (6, bigDecimal6),
          (7, bigDecimal7),
          (8, bigDecimal8),
          (9, bigDecimal9),
        ).transact

        _ <- Ns.i.a1.bigDecimal_.>(bigDecimal2).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8, 9))
        _ <- Ns.i.a1.bigDecimal_.>(bigDecimal2).bigDecimal_.<=(bigDecimal8).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8))
        _ <- Ns.i.a1.bigDecimal_.>(bigDecimal2).bigDecimal_.<=(bigDecimal8).bigDecimal_.not(bigDecimal4, bigDecimal5).query.get.map(_ ==> List(3, 6, 7, 8))
      } yield ()
    }
  }
}