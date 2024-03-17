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
        _ <- Ns.i.offsetTimeSet.insert(List(
          (1, Set(offsetTime1, offsetTime2)),
          (2, Set(offsetTime2)),
          (2, Set(offsetTime3, offsetTime4)),
          (2, Set(offsetTime3, offsetTime4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.offsetTimeSet.query.get.map(_ ==> List(
          (1, Set(offsetTime1, offsetTime2)),
          (2, Set(offsetTime2, offsetTime3, offsetTime4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.offsetTimeSet(distinct).query.get.map(_ ==> List(
          (1, Set(Set(offsetTime1, offsetTime2))),
          (2, Set(
            Set(offsetTime2),
            Set(offsetTime3, offsetTime4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.offsetTimeSet(distinct).query.get.map(_ ==> List(
          Set(
            Set(offsetTime1, offsetTime2),
            Set(offsetTime2),
            Set(offsetTime3, offsetTime4),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.offsetTimeSet.insert(List(
          (1, Set(offsetTime1, offsetTime2)),
          (2, Set(offsetTime2)),
          (2, Set(offsetTime3, offsetTime4)),
          (2, Set(offsetTime3, offsetTime4)),
        )).transact

        // Matching values coalesced offsetTimeo one Set

        _ <- Ns.offsetTimeSet(min).query.get.map(_ ==> List(Set(offsetTime1)))
        _ <- Ns.offsetTimeSet(min(1)).query.get.map(_ ==> List(Set(offsetTime1)))
        _ <- Ns.offsetTimeSet(min(2)).query.get.map(_ ==> List(Set(offsetTime1, offsetTime2)))
        _ <- Ns.offsetTimeSet(min(3)).query.get.map(_ ==> List(Set(offsetTime1, offsetTime2, offsetTime3)))

        _ <- Ns.i.a1.offsetTimeSet(min).query.get.map(_ ==> List(
          (1, Set(offsetTime1)),
          (2, Set(offsetTime2)),
        ))
        // Same as
        _ <- Ns.i.a1.offsetTimeSet(min(1)).query.get.map(_ ==> List(
          (1, Set(offsetTime1)),
          (2, Set(offsetTime2)),
        ))

        _ <- Ns.i.a1.offsetTimeSet(min(2)).query.get.map(_ ==> List(
          (1, Set(offsetTime1, offsetTime2)),
          (2, Set(offsetTime2, offsetTime3)),
        ))

        _ <- Ns.i.a1.offsetTimeSet(min(3)).query.get.map(_ ==> List(
          (1, Set(offsetTime1, offsetTime2)),
          (2, Set(offsetTime2, offsetTime3, offsetTime4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.offsetTimeSet.insert(List(
          (1, Set(offsetTime1, offsetTime2)),
          (2, Set(offsetTime2)),
          (2, Set(offsetTime3, offsetTime4)),
          (2, Set(offsetTime3, offsetTime4)),
        )).transact

        // Matching values coalesced offsetTimeo one Set

        _ <- Ns.offsetTimeSet(max).query.get.map(_ ==> List(Set(offsetTime4)))
        _ <- Ns.offsetTimeSet(max(1)).query.get.map(_ ==> List(Set(offsetTime4)))
        _ <- Ns.offsetTimeSet(max(2)).query.get.map(_ ==> List(Set(offsetTime3, offsetTime4)))
        _ <- Ns.offsetTimeSet(max(3)).query.get.map(_ ==> List(Set(offsetTime2, offsetTime3, offsetTime4)))

        _ <- Ns.i.a1.offsetTimeSet(max).query.get.map(_ ==> List(
          (1, Set(offsetTime2)),
          (2, Set(offsetTime4)),
        ))
        // Same as
        _ <- Ns.i.a1.offsetTimeSet(max(1)).query.get.map(_ ==> List(
          (1, Set(offsetTime2)),
          (2, Set(offsetTime4)),
        ))

        _ <- Ns.i.a1.offsetTimeSet(max(2)).query.get.map(_ ==> List(
          (1, Set(offsetTime1, offsetTime2)),
          (2, Set(offsetTime3, offsetTime4)),
        ))

        _ <- Ns.i.a1.offsetTimeSet(max(3)).query.get.map(_ ==> List(
          (1, Set(offsetTime1, offsetTime2)),
          (2, Set(offsetTime2, offsetTime3, offsetTime4)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.offsetTimeSet.insert(List(
          (1, Set(offsetTime1, offsetTime2)),
          (2, Set(offsetTime2)),
          (2, Set(offsetTime3, offsetTime4)),
          (2, Set(offsetTime3, offsetTime4)),
        )).transact
        all = Set(offsetTime1, offsetTime2, offsetTime3, offsetTime4)
        _ <- Ns.offsetTimeSet(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.offsetTimeSet(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.offsetTimeSet(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.offsetTimeSet.insert(List(
          (1, Set(offsetTime1, offsetTime2)),
          (2, Set(offsetTime2)),
          (2, Set(offsetTime3, offsetTime4)),
          (2, Set(offsetTime3, offsetTime4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.offsetTimeSet(count).query.get.map(_ ==> List(7))
        _ <- Ns.offsetTimeSet(countDistinct).query.get.map(_ ==> List(4))

        _ <- Ns.i.a1.offsetTimeSet(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 5)
        ))
        _ <- Ns.i.a1.offsetTimeSet(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}