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

        // Sort by counts

        _ <- Ns.i.offsetTimeSet(count).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 5),
        ))
        _ <- Ns.i.offsetTimeSet(count).d1.query.get.map(_ ==> List(
          (2, 5),
          (1, 2),
        ))

        _ <- Ns.i.offsetTimeSet(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- Ns.i.offsetTimeSet(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
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

        // Minimum value

        _ <- Ns.offsetTimeSet(min).query.get.map(_ ==> List(offsetTime1))

        // Sort by minimum value
        _ <- Ns.i.offsetTimeSet(min).a1.query.get.map(_ ==> List(
          (1, offsetTime1),
          (2, offsetTime2),
        ))
        _ <- Ns.i.offsetTimeSet(min).d1.query.get.map(_ ==> List(
          (2, offsetTime2),
          (1, offsetTime1),
        ))

        // Set of minimum values

        _ <- Ns.offsetTimeSet(min(1)).query.get.map(_ ==> List(Set(offsetTime1)))
        _ <- Ns.offsetTimeSet(min(2)).query.get.map(_ ==> List(Set(offsetTime1, offsetTime2)))
        _ <- Ns.offsetTimeSet(min(3)).query.get.map(_ ==> List(Set(offsetTime1, offsetTime2, offsetTime3)))

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

        // Maximum value

        _ <- Ns.offsetTimeSet(max).query.get.map(_ ==> List(offsetTime4))

        // Sort by maximum value
        _ <- Ns.i.offsetTimeSet(max).a1.query.get.map(_ ==> List(
          (1, offsetTime2),
          (2, offsetTime4),
        ))
        _ <- Ns.i.offsetTimeSet(max).d1.query.get.map(_ ==> List(
          (2, offsetTime4),
          (1, offsetTime2),
        ))

        // Set of maximum values

        _ <- Ns.offsetTimeSet(max(1)).query.get.map(_ ==> List(Set(offsetTime4)))
        _ <- Ns.offsetTimeSet(max(2)).query.get.map(_ ==> List(Set(offsetTime3, offsetTime4)))
        _ <- Ns.offsetTimeSet(max(3)).query.get.map(_ ==> List(Set(offsetTime2, offsetTime3, offsetTime4)))

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

        _ <- Ns.offsetTimeSet(sample).query.get.map { rows =>
          val singleSampleValue: OffsetTime = rows.head
          all.contains(singleSampleValue) ==> true
        }

        // Sort by sample value (not checked here)
        _ <- Ns.i.offsetTimeSet(sample).a1.query.get
        _ <- Ns.i.offsetTimeSet(sample).d1.query.get

        // Multiple samples
        _ <- Ns.offsetTimeSet(sample(1)).query.get.map { rows =>
          val sampleSetWithOneValue: Set[OffsetTime] = rows.head
          all.contains(sampleSetWithOneValue.head) ==> true
        }
        _ <- Ns.offsetTimeSet(sample(2)).query.get.map { rows =>
          val sampleSetWithTwoValues: Set[OffsetTime] = rows.head
          all.intersect(sampleSetWithTwoValues).nonEmpty ==> true
        }
      } yield ()
    }


    "distinct sets of values" - types { implicit conn =>
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
  }
}