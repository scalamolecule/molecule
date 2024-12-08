package molecule.coreTests.spi.filter.one.number

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterOneInteger_Int extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "odd/even" - types { implicit conn =>
      for {
        _ <- Ns.i.int.insert(
          (-2, -int2),
          (-1, -int1),
          (0, int0),
          (1, int1),
          (2, int2),
        ).transact

        _ <- Ns.int.even.a1.query.get.map(_ ==> List(-int2, int0, int2))
        _ <- Ns.i.a1.int_.even.query.get.map(_ ==> List(-2, 0, 2))

        _ <- Ns.int.odd.a1.query.get.map(_ ==> List(-int1, int1))
        _ <- Ns.i.a1.int_.odd.query.get.map(_ ==> List(-1, 1))
      } yield ()
    }


    "modulo" - types { implicit conn =>
      for {
        _ <- Ns.i.int.insert(
          (1, int1),
          (2, int2),
          (3, int3),
          (4, int4),
          (5, int5),
          (6, int6),
          (7, int7),
          (8, int8),
          (9, int9),
        ).transact

        // Mandatory

        _ <- Ns.int.%(int2, int0).query.get.map(_ ==> List(int2, int4, int6, int8))
        _ <- Ns.int.%(int2, int1).query.get.map(_ ==> List(int1, int3, int5, int7, int9))

        _ <- Ns.int.%(int3, int0).query.get.map(_ ==> List(int3, int6, int9))
        _ <- Ns.int.%(int3, int1).query.get.map(_ ==> List(int1, int4, int7))
        _ <- Ns.int.%(int3, int2).query.get.map(_ ==> List(int2, int5, int8))

        // Tacit

        _ <- Ns.i.int_.%(int2, int0).query.get.map(_ ==> List(2, 4, 6, 8))
        _ <- Ns.i.int_.%(int2, int1).query.get.map(_ ==> List(1, 3, 5, 7, 9))

        _ <- Ns.i.int_.%(int3, int0).query.get.map(_ ==> List(3, 6, 9))
        _ <- Ns.i.int_.%(int3, int1).query.get.map(_ ==> List(1, 4, 7))
        _ <- Ns.i.int_.%(int3, int2).query.get.map(_ ==> List(2, 5, 8))
      } yield ()
    }
  }
}
