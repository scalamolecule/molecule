// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.set.any

import java.time.OffsetTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSet_OffsetTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.offsetTimes.insert(List(
          (1, Set(offsetTime1, offsetTime2)),
          (2, Set(offsetTime2, offsetTime3)),
          (2, Set(offsetTime3, offsetTime4)),
          (2, Set(offsetTime3, offsetTime4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.offsetTimes.query.get.map(_ ==> List(
          (1, Set(offsetTime1, offsetTime2)),
          (2, Set(offsetTime2, offsetTime3, offsetTime4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.offsetTimes(distinct).query.get.map(_ ==> List(
          (1, Set(Set(offsetTime1, offsetTime2))),
          (2, Set(
            Set(offsetTime2, offsetTime3),
            Set(offsetTime3, offsetTime4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.offsetTimes(distinct).query.get.map(_ ==> List(
          Set(
            Set(offsetTime1, offsetTime2),
            Set(offsetTime2, offsetTime3),
            Set(offsetTime3, offsetTime4),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.offsetTimes.insert(List(
          (1, Set(offsetTime1, offsetTime2)),
          (2, Set(offsetTime2, offsetTime3)),
          (2, Set(offsetTime3, offsetTime4)),
          (2, Set(offsetTime3, offsetTime4)),
        )).transact

        // Matching values coalesced offsetTimeo one Set

        _ <- Ns.offsetTimes(min).query.get.map(_ ==> List(Set(offsetTime1)))
        _ <- Ns.offsetTimes(min(1)).query.get.map(_ ==> List(Set(offsetTime1)))
        _ <- Ns.offsetTimes(min(2)).query.get.map(_ ==> List(Set(offsetTime1, offsetTime2)))
        _ <- Ns.offsetTimes(min(3)).query.get.map(_ ==> List(Set(offsetTime1, offsetTime2, offsetTime3)))

        _ <- Ns.i.a1.offsetTimes(min).query.get.map(_ ==> List(
          (1, Set(offsetTime1)),
          (2, Set(offsetTime2)),
        ))
        // Same as
        _ <- Ns.i.a1.offsetTimes(min(1)).query.get.map(_ ==> List(
          (1, Set(offsetTime1)),
          (2, Set(offsetTime2)),
        ))

        _ <- Ns.i.a1.offsetTimes(min(2)).query.get.map(_ ==> List(
          (1, Set(offsetTime1, offsetTime2)),
          (2, Set(offsetTime2, offsetTime3)),
        ))

        _ <- Ns.i.a1.offsetTimes(min(3)).query.get.map(_ ==> List(
          (1, Set(offsetTime1, offsetTime2)),
          (2, Set(offsetTime2, offsetTime3, offsetTime4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.offsetTimes.insert(List(
          (1, Set(offsetTime1, offsetTime2)),
          (2, Set(offsetTime2, offsetTime3)),
          (2, Set(offsetTime3, offsetTime4)),
          (2, Set(offsetTime3, offsetTime4)),
        )).transact

        // Matching values coalesced offsetTimeo one Set

        _ <- Ns.offsetTimes(max).query.get.map(_ ==> List(Set(offsetTime4)))
        _ <- Ns.offsetTimes(max(1)).query.get.map(_ ==> List(Set(offsetTime4)))
        _ <- Ns.offsetTimes(max(2)).query.get.map(_ ==> List(Set(offsetTime3, offsetTime4)))
        _ <- Ns.offsetTimes(max(3)).query.get.map(_ ==> List(Set(offsetTime2, offsetTime3, offsetTime4)))

        _ <- Ns.i.a1.offsetTimes(max).query.get.map(_ ==> List(
          (1, Set(offsetTime2)),
          (2, Set(offsetTime4)),
        ))
        // Same as
        _ <- Ns.i.a1.offsetTimes(max(1)).query.get.map(_ ==> List(
          (1, Set(offsetTime2)),
          (2, Set(offsetTime4)),
        ))

        _ <- Ns.i.a1.offsetTimes(max(2)).query.get.map(_ ==> List(
          (1, Set(offsetTime1, offsetTime2)),
          (2, Set(offsetTime3, offsetTime4)),
        ))

        _ <- Ns.i.a1.offsetTimes(max(3)).query.get.map(_ ==> List(
          (1, Set(offsetTime1, offsetTime2)),
          (2, Set(offsetTime2, offsetTime3, offsetTime4)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.offsetTimes.insert(List(
          (1, Set(offsetTime1, offsetTime2)),
          (2, Set(offsetTime2, offsetTime3)),
          (2, Set(offsetTime3, offsetTime4)),
          (2, Set(offsetTime3, offsetTime4)),
        )).transact
        all = Set(offsetTime1, offsetTime2, offsetTime3, offsetTime4)
        _ <- Ns.offsetTimes(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.offsetTimes(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.offsetTimes(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.offsetTimes.insert(List(
          (1, Set(offsetTime1, offsetTime2)),
          (2, Set(offsetTime2, offsetTime3)),
          (2, Set(offsetTime3, offsetTime4)),
          (2, Set(offsetTime3, offsetTime4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.offsetTimes(count).query.get.map(_ ==> List(8))
        _ <- Ns.offsetTimes(countDistinct).query.get.map(_ ==> List(4))

        _ <- Ns.i.a1.offsetTimes(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 6)
        ))
        _ <- Ns.i.a1.offsetTimes(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}