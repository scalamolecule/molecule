// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.set.any

import java.time.LocalTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSet_LocalTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.localTimeSet.insert(List(
          (1, Set(localTime1, localTime2)),
          (2, Set(localTime2)),
          (2, Set(localTime3, localTime4)),
          (2, Set(localTime3, localTime4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.localTimeSet(count).query.get.map(_ ==> List(7))
        _ <- Ns.localTimeSet(countDistinct).query.get.map(_ ==> List(4))

        // Sort by counts

        _ <- Ns.i.localTimeSet(count).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 5),
        ))
        _ <- Ns.i.localTimeSet(count).d1.query.get.map(_ ==> List(
          (2, 5),
          (1, 2),
        ))

        _ <- Ns.i.localTimeSet(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- Ns.i.localTimeSet(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.localTimeSet.insert(List(
          (1, Set(localTime1, localTime2)),
          (2, Set(localTime2)),
          (2, Set(localTime3, localTime4)),
          (2, Set(localTime3, localTime4)),
        )).transact

        // Minimum value

        _ <- Ns.localTimeSet(min).query.get.map(_ ==> List(localTime1))

        // Sort by minimum value
        _ <- Ns.i.localTimeSet(min).a1.query.get.map(_ ==> List(
          (1, localTime1),
          (2, localTime2),
        ))
        _ <- Ns.i.localTimeSet(min).d1.query.get.map(_ ==> List(
          (2, localTime2),
          (1, localTime1),
        ))

        // Set of minimum values

        _ <- Ns.localTimeSet(min(1)).query.get.map(_ ==> List(Set(localTime1)))
        _ <- Ns.localTimeSet(min(2)).query.get.map(_ ==> List(Set(localTime1, localTime2)))
        _ <- Ns.localTimeSet(min(3)).query.get.map(_ ==> List(Set(localTime1, localTime2, localTime3)))

        _ <- Ns.i.a1.localTimeSet(min(1)).query.get.map(_ ==> List(
          (1, Set(localTime1)),
          (2, Set(localTime2)),
        ))
        _ <- Ns.i.a1.localTimeSet(min(2)).query.get.map(_ ==> List(
          (1, Set(localTime1, localTime2)),
          (2, Set(localTime2, localTime3)),
        ))
        _ <- Ns.i.a1.localTimeSet(min(3)).query.get.map(_ ==> List(
          (1, Set(localTime1, localTime2)),
          (2, Set(localTime2, localTime3, localTime4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.localTimeSet.insert(List(
          (1, Set(localTime1, localTime2)),
          (2, Set(localTime2)),
          (2, Set(localTime3, localTime4)),
          (2, Set(localTime3, localTime4)),
        )).transact

        // Maximum value

        _ <- Ns.localTimeSet(max).query.get.map(_ ==> List(localTime4))

        // Sort by maximum value
        _ <- Ns.i.localTimeSet(max).a1.query.get.map(_ ==> List(
          (1, localTime2),
          (2, localTime4),
        ))
        _ <- Ns.i.localTimeSet(max).d1.query.get.map(_ ==> List(
          (2, localTime4),
          (1, localTime2),
        ))

        // Set of maximum values

        _ <- Ns.localTimeSet(max(1)).query.get.map(_ ==> List(Set(localTime4)))
        _ <- Ns.localTimeSet(max(2)).query.get.map(_ ==> List(Set(localTime3, localTime4)))
        _ <- Ns.localTimeSet(max(3)).query.get.map(_ ==> List(Set(localTime2, localTime3, localTime4)))

        _ <- Ns.i.a1.localTimeSet(max(1)).query.get.map(_ ==> List(
          (1, Set(localTime2)),
          (2, Set(localTime4)),
        ))
        _ <- Ns.i.a1.localTimeSet(max(2)).query.get.map(_ ==> List(
          (1, Set(localTime1, localTime2)),
          (2, Set(localTime3, localTime4)),
        ))
        _ <- Ns.i.a1.localTimeSet(max(3)).query.get.map(_ ==> List(
          (1, Set(localTime1, localTime2)),
          (2, Set(localTime2, localTime3, localTime4)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.localTimeSet.insert(List(
          (1, Set(localTime1, localTime2)),
          (2, Set(localTime2)),
          (2, Set(localTime3, localTime4)),
          (2, Set(localTime3, localTime4)),
        )).transact

        all = Set(localTime1, localTime2, localTime3, localTime4)

        _ <- Ns.localTimeSet(sample).query.get.map { rows =>
          val singleSampleValue: LocalTime = rows.head
          all.contains(singleSampleValue) ==> true
        }

        // Sort by sample value (not checked here)
        _ <- Ns.i.localTimeSet(sample).a1.query.get
        _ <- Ns.i.localTimeSet(sample).d1.query.get

        // Multiple samples
        _ <- Ns.localTimeSet(sample(1)).query.get.map { rows =>
          val sampleSetWithOneValue: Set[LocalTime] = rows.head
          all.contains(sampleSetWithOneValue.head) ==> true
        }
        _ <- Ns.localTimeSet(sample(2)).query.get.map { rows =>
          val sampleSetWithTwoValues: Set[LocalTime] = rows.head
          all.intersect(sampleSetWithTwoValues).nonEmpty ==> true
        }
      } yield ()
    }


    "distinct sets of values" - types { implicit conn =>
      for {
        _ <- Ns.i.localTimeSet.insert(List(
          (1, Set(localTime1, localTime2)),
          (2, Set(localTime2)),
          (2, Set(localTime3, localTime4)),
          (2, Set(localTime3, localTime4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.localTimeSet.query.get.map(_ ==> List(
          (1, Set(localTime1, localTime2)),
          (2, Set(localTime2, localTime3, localTime4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.localTimeSet(distinct).query.get.map(_ ==> List(
          (1, Set(Set(localTime1, localTime2))),
          (2, Set(
            Set(localTime2),
            Set(localTime3, localTime4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.localTimeSet(distinct).query.get.map(_ ==> List(
          Set(
            Set(localTime1, localTime2),
            Set(localTime2),
            Set(localTime3, localTime4),
          )
        ))
      } yield ()
    }
  }
}