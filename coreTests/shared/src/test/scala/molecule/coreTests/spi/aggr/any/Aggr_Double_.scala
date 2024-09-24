// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.any

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait Aggr_Double_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.double.insert(List(
          (1, double1),
          (2, double2),
          (2, double2),
          (2, double3),
        )).transact

        _ <- Ns.i.double.a1.query.get.map(_ ==> List(
          (1, double1),
          (2, double2), // 2 rows coalesced
          (2, double3),
        ))

        // Distinct values are returned in a Set
        _ <- Ns.i.a1.double(distinct).query.get.map(_ ==> List(
          (1, Set(double1)),
          (2, Set(double2, double3)),
        ))

        _ <- Ns.double(distinct).query.get.map(_.head ==> Set(
          double1, double2, double3
        ))
      } yield ()
    }


    "min/max" - types { implicit conn =>
      for {
        _ <- Ns.i.double.insert(
          (1, double1),
          (1, double2),
          (1, double3),
          (2, double4),
          (2, double5),
          (2, double6),
        ).transact

        _ <- Ns.double(min).query.get.map(_ ==> List(double1))
        _ <- Ns.double(max).query.get.map(_ ==> List(double6))
        _ <- Ns.double(min).double(max).query.get.map(_ ==> List((double1, double6)))

        _ <- Ns.i.a1.double(min).query.get.map(_ ==> List(
          (1, double1),
          (2, double4)
        ))

        _ <- Ns.i.a1.double(max).query.get.map(_ ==> List(
          (1, double3),
          (2, double6)
        ))

        _ <- Ns.i.a1.double(min).double(max).query.get.map(_ ==> List(
          (1, double1, double3),
          (2, double4, double6)
        ))
      } yield ()
    }

    "min/max n" - types { implicit conn =>
      for {
        _ <- Ns.i.double.insert(
          (1, double1),
          (1, double2),
          (1, double3),
          (2, double4),
          (2, double5),
          (2, double6),
          (2, double6), // (make sure grouped values coalesce)
        ).transact

        _ <- Ns.double(min(1)).query.get.map(_ ==> List(Set(double1)))
        _ <- Ns.double(min(2)).query.get.map(_ ==> List(Set(double1, double2)))

        _ <- Ns.double(max(1)).query.get.map(_ ==> List(Set(double6)))
        _ <- Ns.double(max(2)).query.get.map(_ ==> List(Set(double5, double6)))

        _ <- Ns.i.a1.double(min(2)).query.get.map(_ ==> List(
          (1, Set(double1, double2)),
          (2, Set(double4, double5))
        ))

        _ <- Ns.i.a1.double(max(2)).query.get.map(_ ==> List(
          (1, Set(double2, double3)),
          (2, Set(double5, double6))
        ))

        _ <- Ns.i.a1.double(min(2)).double(max(2)).query.get.map(_ ==> List(
          (1, Set(double1, double2), Set(double2, double3)),
          (2, Set(double4, double5), Set(double5, double6))
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      val all = Set(double1, double2, double3, double4)
      for {
        _ <- Ns.double.insert(List(double1, double2, double3)).transact
        _ <- Ns.double(sample).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.double(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.double(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count" - types { implicit conn =>
      for {
        _ <- Ns.i.double.insert(List(
          (1, double1),
          (2, double2),
          (2, double2),
          (2, double3),
        )).transact

        _ <- Ns.double(count).query.get.map(_ ==> List(4))
        _ <- Ns.i.a1.double(count).query.get.map(_ ==> List(
          (1, 1),
          (2, 3)
        ))

        _ <- Ns.double(countDistinct).query.get.map(_ ==> List(3))
        _ <- Ns.i.a1.double(countDistinct).query.get.map(_ ==> List(
          (1, 1),
          (2, 2)
        ))
      } yield ()
    }
  }
}