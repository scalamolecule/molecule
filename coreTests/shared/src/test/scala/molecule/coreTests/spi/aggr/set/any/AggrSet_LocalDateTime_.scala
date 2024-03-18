// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.set.any

import java.time.LocalDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSet_LocalDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.localDateTimeSet.insert(List(
          (1, Set(localDateTime1, localDateTime2)),
          (2, Set(localDateTime2)),
          (2, Set(localDateTime3, localDateTime4)),
          (2, Set(localDateTime3, localDateTime4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.localDateTimeSet(count).query.get.map(_ ==> List(7))
        _ <- Ns.localDateTimeSet(countDistinct).query.get.map(_ ==> List(4))

        // Sort by counts

        _ <- Ns.i.localDateTimeSet(count).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 5),
        ))
        _ <- Ns.i.localDateTimeSet(count).d1.query.get.map(_ ==> List(
          (2, 5),
          (1, 2),
        ))

        _ <- Ns.i.localDateTimeSet(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- Ns.i.localDateTimeSet(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.localDateTimeSet.insert(List(
          (1, Set(localDateTime1, localDateTime2)),
          (2, Set(localDateTime2)),
          (2, Set(localDateTime3, localDateTime4)),
          (2, Set(localDateTime3, localDateTime4)),
        )).transact

        // Minimum value

        _ <- Ns.localDateTimeSet(min).query.get.map(_ ==> List(localDateTime1))

        // Sort by minimum value
        _ <- Ns.i.localDateTimeSet(min).a1.query.get.map(_ ==> List(
          (1, localDateTime1),
          (2, localDateTime2),
        ))
        _ <- Ns.i.localDateTimeSet(min).d1.query.get.map(_ ==> List(
          (2, localDateTime2),
          (1, localDateTime1),
        ))

        // Set of minimum values

        _ <- Ns.localDateTimeSet(min(1)).query.get.map(_ ==> List(Set(localDateTime1)))
        _ <- Ns.localDateTimeSet(min(2)).query.get.map(_ ==> List(Set(localDateTime1, localDateTime2)))
        _ <- Ns.localDateTimeSet(min(3)).query.get.map(_ ==> List(Set(localDateTime1, localDateTime2, localDateTime3)))

        _ <- Ns.i.a1.localDateTimeSet(min(1)).query.get.map(_ ==> List(
          (1, Set(localDateTime1)),
          (2, Set(localDateTime2)),
        ))
        _ <- Ns.i.a1.localDateTimeSet(min(2)).query.get.map(_ ==> List(
          (1, Set(localDateTime1, localDateTime2)),
          (2, Set(localDateTime2, localDateTime3)),
        ))
        _ <- Ns.i.a1.localDateTimeSet(min(3)).query.get.map(_ ==> List(
          (1, Set(localDateTime1, localDateTime2)),
          (2, Set(localDateTime2, localDateTime3, localDateTime4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.localDateTimeSet.insert(List(
          (1, Set(localDateTime1, localDateTime2)),
          (2, Set(localDateTime2)),
          (2, Set(localDateTime3, localDateTime4)),
          (2, Set(localDateTime3, localDateTime4)),
        )).transact

        // Maximum value

        _ <- Ns.localDateTimeSet(max).query.get.map(_ ==> List(localDateTime4))

        // Sort by maximum value
        _ <- Ns.i.localDateTimeSet(max).a1.query.get.map(_ ==> List(
          (1, localDateTime2),
          (2, localDateTime4),
        ))
        _ <- Ns.i.localDateTimeSet(max).d1.query.get.map(_ ==> List(
          (2, localDateTime4),
          (1, localDateTime2),
        ))

        // Set of maximum values

        _ <- Ns.localDateTimeSet(max(1)).query.get.map(_ ==> List(Set(localDateTime4)))
        _ <- Ns.localDateTimeSet(max(2)).query.get.map(_ ==> List(Set(localDateTime3, localDateTime4)))
        _ <- Ns.localDateTimeSet(max(3)).query.get.map(_ ==> List(Set(localDateTime2, localDateTime3, localDateTime4)))

        _ <- Ns.i.a1.localDateTimeSet(max(1)).query.get.map(_ ==> List(
          (1, Set(localDateTime2)),
          (2, Set(localDateTime4)),
        ))
        _ <- Ns.i.a1.localDateTimeSet(max(2)).query.get.map(_ ==> List(
          (1, Set(localDateTime1, localDateTime2)),
          (2, Set(localDateTime3, localDateTime4)),
        ))
        _ <- Ns.i.a1.localDateTimeSet(max(3)).query.get.map(_ ==> List(
          (1, Set(localDateTime1, localDateTime2)),
          (2, Set(localDateTime2, localDateTime3, localDateTime4)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.localDateTimeSet.insert(List(
          (1, Set(localDateTime1, localDateTime2)),
          (2, Set(localDateTime2)),
          (2, Set(localDateTime3, localDateTime4)),
          (2, Set(localDateTime3, localDateTime4)),
        )).transact

        all = Set(localDateTime1, localDateTime2, localDateTime3, localDateTime4)

        _ <- Ns.localDateTimeSet(sample).query.get.map { rows =>
          val singleSampleValue: LocalDateTime = rows.head
          all.contains(singleSampleValue) ==> true
        }

        // Sort by sample value (not checked here)
        _ <- Ns.i.localDateTimeSet(sample).a1.query.get
        _ <- Ns.i.localDateTimeSet(sample).d1.query.get

        // Multiple samples
        _ <- Ns.localDateTimeSet(sample(1)).query.get.map { rows =>
          val sampleSetWithOneValue: Set[LocalDateTime] = rows.head
          all.contains(sampleSetWithOneValue.head) ==> true
        }
        _ <- Ns.localDateTimeSet(sample(2)).query.get.map { rows =>
          val sampleSetWithTwoValues: Set[LocalDateTime] = rows.head
          all.intersect(sampleSetWithTwoValues).nonEmpty ==> true
        }
      } yield ()
    }


    "distinct sets of values" - types { implicit conn =>
      for {
        _ <- Ns.i.localDateTimeSet.insert(List(
          (1, Set(localDateTime1, localDateTime2)),
          (2, Set(localDateTime2)),
          (2, Set(localDateTime3, localDateTime4)),
          (2, Set(localDateTime3, localDateTime4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.localDateTimeSet.query.get.map(_ ==> List(
          (1, Set(localDateTime1, localDateTime2)),
          (2, Set(localDateTime2, localDateTime3, localDateTime4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.localDateTimeSet(distinct).query.get.map(_ ==> List(
          (1, Set(Set(localDateTime1, localDateTime2))),
          (2, Set(
            Set(localDateTime2),
            Set(localDateTime3, localDateTime4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.localDateTimeSet(distinct).query.get.map(_ ==> List(
          Set(
            Set(localDateTime1, localDateTime2),
            Set(localDateTime2),
            Set(localDateTime3, localDateTime4),
          )
        ))
      } yield ()
    }
  }
}