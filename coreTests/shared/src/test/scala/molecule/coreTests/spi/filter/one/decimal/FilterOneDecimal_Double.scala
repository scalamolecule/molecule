package molecule.coreTests.spi.filter.one.decimal

import molecule.core.api.Api_async
import molecule.core.spi.{Spi_async, TxReport}
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.concurrent.Future

trait FilterOneDecimal_Double extends CoreTestSuite with Api_async { spi: Spi_async =>


  override lazy val tests = Tests {

    "modulo" - types { implicit conn =>
      def load: Future[TxReport] = Ns.i.double.insert(
        (1, double1),
        (2, double2),
        (3, double3),
        (4, double4),
        (5, double5),
        (6, double6),
        (7, double7),
        (8, double8),
        (9, double9),
      ).transact

      if (database == "SQlite") {
        // Remainder in SQlite is a whole number
        for {
          _ <- load
          _ <- Ns.double.%(double2, 0).query.get.map(_ ==> List(double2, double4, double6, double8))
          _ <- Ns.double.%(double2, 1).query.i.get.map(_ ==> List(double1, double3, double5, double7, double9))

          _ <- Ns.double.%(double3, 0).query.get.map(_ ==> List(double3, double6, double9))
          _ <- Ns.double.%(double3, 1).query.get.map(_ ==> List(double1, double4, double7))
          _ <- Ns.double.%(double3, 2).query.get.map(_ ==> List(double2, double5, double8))

          _ <- Ns.i.double_.%(double2, 0).query.get.map(_ ==> List(2, 4, 6, 8))
          _ <- Ns.i.double_.%(double2, 1).query.get.map(_ ==> List(1, 3, 5, 7, 9))

          _ <- Ns.i.double_.%(double3, 0).query.get.map(_ ==> List(3, 6, 9))
          _ <- Ns.i.double_.%(double3, 1).query.get.map(_ ==> List(1, 4, 7))
          _ <- Ns.i.double_.%(double3, 2).query.get.map(_ ==> List(2, 5, 8))
        } yield ()

      } else if (database == "MariaDB") {

        // Unreliable modulo rounding differences with MariaDB

      } else {
        for {
          _ <- load
          _ <- Ns.double.%(double2, double0).query.get.map(_ ==> List(double2, double4, double6, double8))
          _ <- Ns.double.%(double2, double1).query.i.get.map(_ ==> List(double1, double3, double5, double7, double9))

          _ <- Ns.double.%(double3, double0).query.get.map(_ ==> List(double3, double6, double9))
          _ <- Ns.double.%(double3, double1).query.get.map(_ ==> List(double1, double4, double7))
          _ <- Ns.double.%(double3, double2).query.get.map(_ ==> List(double2, double5, double8))

          _ <- Ns.i.double_.%(double2, double0).query.get.map(_ ==> List(2, 4, 6, 8))
          _ <- Ns.i.double_.%(double2, double1).query.get.map(_ ==> List(1, 3, 5, 7, 9))

          _ <- Ns.i.double_.%(double3, double0).query.get.map(_ ==> List(3, 6, 9))
          _ <- Ns.i.double_.%(double3, double1).query.get.map(_ ==> List(1, 4, 7))
          _ <- Ns.i.double_.%(double3, double2).query.get.map(_ ==> List(2, 5, 8))
        } yield ()
      }
    }


    "comparison" - types { implicit conn =>
      for {
        _ <- Ns.i.double.insert(
          (1, double1),
          (2, double2),
          (3, double3),
          (4, double4),
          (5, double5),
          (6, double6),
          (7, double7),
          (8, double8),
          (9, double9),
        ).transact

        _ <- Ns.i.a1.double_.>(double2).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8, 9))
        _ <- Ns.i.a1.double_.>(double2).double_.<=(double8).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8))
        _ <- Ns.i.a1.double_.>(double2).double_.<=(double8).double_.not(double4, double5).query.get.map(_ ==> List(3, 6, 7, 8))
      } yield ()
    }
  }
}