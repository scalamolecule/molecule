package molecule.coreTests.spi.filter.one.number

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterOneInteger_Int extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

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


    "odd/even" - types { implicit conn =>
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

        _ <- Ns.int.even.query.get.map(_ ==> List(int2, int4, int6, int8))
        _ <- Ns.int.odd.query.get.map(_ ==> List(int1, int3, int5, int7, int9))

        _ <- Ns.i.int_.even.query.get.map(_ ==> List(2, 4, 6, 8))
        _ <- Ns.i.int_.odd.query.get.map(_ ==> List(1, 3, 5, 7, 9))
      } yield ()
    }


    "comparison" - types { implicit conn =>
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

        _ <- Ns.i.a1.int_.>(int2).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8, 9))
        _ <- Ns.i.a1.int_.>(int2).int_.<=(int8).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8))
        _ <- Ns.i.a1.int_.>(int2).int_.<=(int8).int_.not(int4, int5).query.get.map(_ ==> List(3, 6, 7, 8))
        _ <- Ns.i.a1.int_.>(int2).int_.<=(int8).int_.not(int4, int5).int_.odd.query.get.map(_ ==> List(3, 7))
      } yield ()
    }
  }
}
