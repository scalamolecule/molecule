// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.set.any

import java.time.Duration
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSet_Duration_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.durationSet.insert(List(
          (1, Set(duration1, duration2)),
          (2, Set(duration2)),
          (2, Set(duration3, duration4)),
          (2, Set(duration3, duration4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.durationSet(count).query.get.map(_ ==> List(7))
        _ <- Ns.durationSet(countDistinct).query.get.map(_ ==> List(4))

        // Sort by counts

        _ <- Ns.i.durationSet(count).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 5),
        ))
        _ <- Ns.i.durationSet(count).d1.query.get.map(_ ==> List(
          (2, 5),
          (1, 2),
        ))

        _ <- Ns.i.durationSet(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- Ns.i.durationSet(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.durationSet.insert(List(
          (1, Set(duration1, duration2)),
          (2, Set(duration2)),
          (2, Set(duration3, duration4)),
          (2, Set(duration3, duration4)),
        )).transact

        // Minimum value

        _ <- Ns.durationSet(min).query.get.map(_ ==> List(duration1))

        // Sort by minimum value
        _ <- Ns.i.durationSet(min).a1.query.get.map(_ ==> List(
          (1, duration1),
          (2, duration2),
        ))
        _ <- Ns.i.durationSet(min).d1.query.get.map(_ ==> List(
          (2, duration2),
          (1, duration1),
        ))

        // Set of minimum values

        _ <- Ns.durationSet(min(1)).query.get.map(_ ==> List(Set(duration1)))
        _ <- Ns.durationSet(min(2)).query.get.map(_ ==> List(Set(duration1, duration2)))
        _ <- Ns.durationSet(min(3)).query.get.map(_ ==> List(Set(duration1, duration2, duration3)))

        _ <- Ns.i.a1.durationSet(min(1)).query.get.map(_ ==> List(
          (1, Set(duration1)),
          (2, Set(duration2)),
        ))
        _ <- Ns.i.a1.durationSet(min(2)).query.get.map(_ ==> List(
          (1, Set(duration1, duration2)),
          (2, Set(duration2, duration3)),
        ))
        _ <- Ns.i.a1.durationSet(min(3)).query.get.map(_ ==> List(
          (1, Set(duration1, duration2)),
          (2, Set(duration2, duration3, duration4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.durationSet.insert(List(
          (1, Set(duration1, duration2)),
          (2, Set(duration2)),
          (2, Set(duration3, duration4)),
          (2, Set(duration3, duration4)),
        )).transact

        // Maximum value

        _ <- Ns.durationSet(max).query.get.map(_ ==> List(duration4))

        // Sort by maximum value
        _ <- Ns.i.durationSet(max).a1.query.get.map(_ ==> List(
          (1, duration2),
          (2, duration4),
        ))
        _ <- Ns.i.durationSet(max).d1.query.get.map(_ ==> List(
          (2, duration4),
          (1, duration2),
        ))

        // Set of maximum values

        _ <- Ns.durationSet(max(1)).query.get.map(_ ==> List(Set(duration4)))
        _ <- Ns.durationSet(max(2)).query.get.map(_ ==> List(Set(duration3, duration4)))
        _ <- Ns.durationSet(max(3)).query.get.map(_ ==> List(Set(duration2, duration3, duration4)))

        _ <- Ns.i.a1.durationSet(max(1)).query.get.map(_ ==> List(
          (1, Set(duration2)),
          (2, Set(duration4)),
        ))
        _ <- Ns.i.a1.durationSet(max(2)).query.get.map(_ ==> List(
          (1, Set(duration1, duration2)),
          (2, Set(duration3, duration4)),
        ))
        _ <- Ns.i.a1.durationSet(max(3)).query.get.map(_ ==> List(
          (1, Set(duration1, duration2)),
          (2, Set(duration2, duration3, duration4)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.durationSet.insert(List(
          (1, Set(duration1, duration2)),
          (2, Set(duration2)),
          (2, Set(duration3, duration4)),
          (2, Set(duration3, duration4)),
        )).transact

        all = Set(duration1, duration2, duration3, duration4)

        _ <- Ns.durationSet(sample).query.get.map { rows =>
          val singleSampleValue: Duration = rows.head
          all.contains(singleSampleValue) ==> true
        }

        // Sort by sample value (not checked here)
        _ <- Ns.i.durationSet(sample).a1.query.get
        _ <- Ns.i.durationSet(sample).d1.query.get

        // Multiple samples
        _ <- Ns.durationSet(sample(1)).query.get.map { rows =>
          val sampleSetWithOneValue: Set[Duration] = rows.head
          all.contains(sampleSetWithOneValue.head) ==> true
        }
        _ <- Ns.durationSet(sample(2)).query.get.map { rows =>
          val sampleSetWithTwoValues: Set[Duration] = rows.head
          all.intersect(sampleSetWithTwoValues).nonEmpty ==> true
        }
      } yield ()
    }


    "distinct sets of values" - types { implicit conn =>
      for {
        _ <- Ns.i.durationSet.insert(List(
          (1, Set(duration1, duration2)),
          (2, Set(duration2)),
          (2, Set(duration3, duration4)),
          (2, Set(duration3, duration4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.durationSet.query.get.map(_ ==> List(
          (1, Set(duration1, duration2)),
          (2, Set(duration2, duration3, duration4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.durationSet(distinct).query.get.map(_ ==> List(
          (1, Set(Set(duration1, duration2))),
          (2, Set(
            Set(duration2),
            Set(duration3, duration4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.durationSet(distinct).query.get.map(_ ==> List(
          Set(
            Set(duration1, duration2),
            Set(duration2),
            Set(duration3, duration4),
          )
        ))
      } yield ()
    }
  }
}