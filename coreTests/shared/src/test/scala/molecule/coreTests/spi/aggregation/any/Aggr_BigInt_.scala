// GENERATED CODE ********************************
package molecule.coreTests.spi.aggregation.any

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait Aggr_BigInt_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.bigInt.insert(List(
          (1, bigInt1),
          (2, bigInt2),
          (2, bigInt2),
          (2, bigInt3),
        )).transact

        _ <- Ns.i.bigInt.a1.query.get.map(_ ==> List(
          (1, bigInt1),
          (2, bigInt2), // 2 rows coalesced
          (2, bigInt3),
        ))

        // Distinct values are returned in a Set
        _ <- Ns.i.a1.bigInt(distinct).query.get.map(_ ==> List(
          (1, Set(bigInt1)),
          (2, Set(bigInt2, bigInt3)),
        ))

        _ <- Ns.bigInt(distinct).query.get.map(_.head ==> Set(
          bigInt1, bigInt2, bigInt3
        ))
      } yield ()
    }


    "min/max" - types { implicit conn =>
      for {
        _ <- Ns.i.bigInt.insert(
          (1, bigInt1),
          (1, bigInt2),
          (1, bigInt3),
          (2, bigInt4),
          (2, bigInt5),
          (2, bigInt6),
        ).transact

        _ <- Ns.bigInt(min).query.get.map(_ ==> List(bigInt1))
        _ <- Ns.bigInt(max).query.get.map(_ ==> List(bigInt6))
        _ <- Ns.bigInt(min).bigInt(max).query.get.map(_ ==> List((bigInt1, bigInt6)))

        _ <- Ns.i.a1.bigInt(min).query.get.map(_ ==> List(
          (1, bigInt1),
          (2, bigInt4)
        ))

        _ <- Ns.i.a1.bigInt(max).query.get.map(_ ==> List(
          (1, bigInt3),
          (2, bigInt6)
        ))

        _ <- Ns.i.a1.bigInt(min).bigInt(max).query.get.map(_ ==> List(
          (1, bigInt1, bigInt3),
          (2, bigInt4, bigInt6)
        ))
      } yield ()
    }

    "min/max n" - types { implicit conn =>
      for {
        _ <- Ns.i.bigInt.insert(
          (1, bigInt1),
          (1, bigInt2),
          (1, bigInt3),
          (2, bigInt4),
          (2, bigInt5),
          (2, bigInt6),
          (2, bigInt6), // (make sure grouped values coalesce)
        ).transact

        _ <- Ns.bigInt(min(1)).query.get.map(_ ==> List(Set(bigInt1)))
        _ <- Ns.bigInt(min(2)).query.get.map(_ ==> List(Set(bigInt1, bigInt2)))

        _ <- Ns.bigInt(max(1)).query.get.map(_ ==> List(Set(bigInt6)))
        _ <- Ns.bigInt(max(2)).query.get.map(_ ==> List(Set(bigInt5, bigInt6)))

        _ <- Ns.i.a1.bigInt(min(2)).query.get.map(_ ==> List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt4, bigInt5))
        ))

        _ <- Ns.i.a1.bigInt(max(2)).query.get.map(_ ==> List(
          (1, Set(bigInt2, bigInt3)),
          (2, Set(bigInt5, bigInt6))
        ))

        _ <- Ns.i.a1.bigInt(min(2)).bigInt(max(2)).query.get.map(_ ==> List(
          (1, Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)),
          (2, Set(bigInt4, bigInt5), Set(bigInt5, bigInt6))
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      val all = Set(bigInt1, bigInt2, bigInt3, bigInt4)
      for {
        _ <- Ns.bigInt.insert(List(bigInt1, bigInt2, bigInt3)).transact
        _ <- Ns.bigInt(sample).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.bigInt(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.bigInt(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count" - types { implicit conn =>
      for {
        _ <- Ns.i.bigInt.insert(List(
          (1, bigInt1),
          (2, bigInt2),
          (2, bigInt2),
          (2, bigInt3),
        )).transact

        _ <- Ns.bigInt(count).query.get.map(_ ==> List(4))
        _ <- Ns.i.a1.bigInt(count).query.get.map(_ ==> List(
          (1, 1),
          (2, 3)
        ))

        _ <- Ns.bigInt(countDistinct).query.get.map(_ ==> List(3))
        _ <- Ns.i.a1.bigInt(countDistinct).query.get.map(_ ==> List(
          (1, 1),
          (2, 2)
        ))
      } yield ()
    }
  }
}