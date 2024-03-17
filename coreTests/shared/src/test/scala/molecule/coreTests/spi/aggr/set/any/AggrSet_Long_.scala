// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.set.any

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSet_Long_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.longSet.insert(List(
          (1, Set(long1, long2)),
          (2, Set(long2)),
          (2, Set(long3, long4)),
          (2, Set(long3, long4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.longSet.query.get.map(_ ==> List(
          (1, Set(long1, long2)),
          (2, Set(long2, long3, long4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.longSet(distinct).query.get.map(_ ==> List(
          (1, Set(Set(long1, long2))),
          (2, Set(
            Set(long2),
            Set(long3, long4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.longSet(distinct).query.get.map(_ ==> List(
          Set(
            Set(long1, long2),
            Set(long2),
            Set(long3, long4),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.longSet.insert(List(
          (1, Set(long1, long2)),
          (2, Set(long2)),
          (2, Set(long3, long4)),
          (2, Set(long3, long4)),
        )).transact

        // Matching values coalesced longo one Set

        _ <- Ns.longSet(min).query.get.map(_ ==> List(Set(long1)))
        _ <- Ns.longSet(min(1)).query.get.map(_ ==> List(Set(long1)))
        _ <- Ns.longSet(min(2)).query.get.map(_ ==> List(Set(long1, long2)))
        _ <- Ns.longSet(min(3)).query.get.map(_ ==> List(Set(long1, long2, long3)))

        _ <- Ns.i.a1.longSet(min).query.get.map(_ ==> List(
          (1, Set(long1)),
          (2, Set(long2)),
        ))
        // Same as
        _ <- Ns.i.a1.longSet(min(1)).query.get.map(_ ==> List(
          (1, Set(long1)),
          (2, Set(long2)),
        ))

        _ <- Ns.i.a1.longSet(min(2)).query.get.map(_ ==> List(
          (1, Set(long1, long2)),
          (2, Set(long2, long3)),
        ))

        _ <- Ns.i.a1.longSet(min(3)).query.get.map(_ ==> List(
          (1, Set(long1, long2)),
          (2, Set(long2, long3, long4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.longSet.insert(List(
          (1, Set(long1, long2)),
          (2, Set(long2)),
          (2, Set(long3, long4)),
          (2, Set(long3, long4)),
        )).transact

        // Matching values coalesced longo one Set

        _ <- Ns.longSet(max).query.get.map(_ ==> List(Set(long4)))
        _ <- Ns.longSet(max(1)).query.get.map(_ ==> List(Set(long4)))
        _ <- Ns.longSet(max(2)).query.get.map(_ ==> List(Set(long3, long4)))
        _ <- Ns.longSet(max(3)).query.get.map(_ ==> List(Set(long2, long3, long4)))

        _ <- Ns.i.a1.longSet(max).query.get.map(_ ==> List(
          (1, Set(long2)),
          (2, Set(long4)),
        ))
        // Same as
        _ <- Ns.i.a1.longSet(max(1)).query.get.map(_ ==> List(
          (1, Set(long2)),
          (2, Set(long4)),
        ))

        _ <- Ns.i.a1.longSet(max(2)).query.get.map(_ ==> List(
          (1, Set(long1, long2)),
          (2, Set(long3, long4)),
        ))

        _ <- Ns.i.a1.longSet(max(3)).query.get.map(_ ==> List(
          (1, Set(long1, long2)),
          (2, Set(long2, long3, long4)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.longSet.insert(List(
          (1, Set(long1, long2)),
          (2, Set(long2)),
          (2, Set(long3, long4)),
          (2, Set(long3, long4)),
        )).transact
        all = Set(long1, long2, long3, long4)
        _ <- Ns.longSet(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.longSet(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.longSet(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.longSet.insert(List(
          (1, Set(long1, long2)),
          (2, Set(long2)),
          (2, Set(long3, long4)),
          (2, Set(long3, long4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.longSet(count).query.get.map(_ ==> List(7))
        _ <- Ns.longSet(countDistinct).query.get.map(_ ==> List(4))

        _ <- Ns.i.a1.longSet(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 5)
        ))
        _ <- Ns.i.a1.longSet(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}