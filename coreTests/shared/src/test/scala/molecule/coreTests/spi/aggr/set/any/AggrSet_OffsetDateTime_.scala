// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.set.any

import java.time.OffsetDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSet_OffsetDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.offsetDateTimeSet.insert(List(
          (1, Set(offsetDateTime1, offsetDateTime2)),
          (2, Set(offsetDateTime2)),
          (2, Set(offsetDateTime3, offsetDateTime4)),
          (2, Set(offsetDateTime3, offsetDateTime4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.offsetDateTimeSet(count).query.get.map(_ ==> List(7))
        _ <- Ns.offsetDateTimeSet(countDistinct).query.get.map(_ ==> List(4))

        // Sort by counts

        _ <- Ns.i.offsetDateTimeSet(count).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 5),
        ))
        _ <- Ns.i.offsetDateTimeSet(count).d1.query.get.map(_ ==> List(
          (2, 5),
          (1, 2),
        ))

        _ <- Ns.i.offsetDateTimeSet(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- Ns.i.offsetDateTimeSet(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.offsetDateTimeSet.insert(List(
          (1, Set(offsetDateTime1, offsetDateTime2)),
          (2, Set(offsetDateTime2)),
          (2, Set(offsetDateTime3, offsetDateTime4)),
          (2, Set(offsetDateTime3, offsetDateTime4)),
        )).transact

        // Minimum value

        _ <- Ns.offsetDateTimeSet(min).query.get.map(_ ==> List(offsetDateTime1))

        // Sort by minimum value
        _ <- Ns.i.offsetDateTimeSet(min).a1.query.get.map(_ ==> List(
          (1, offsetDateTime1),
          (2, offsetDateTime2),
        ))
        _ <- Ns.i.offsetDateTimeSet(min).d1.query.get.map(_ ==> List(
          (2, offsetDateTime2),
          (1, offsetDateTime1),
        ))

        // Set of minimum values

        _ <- Ns.offsetDateTimeSet(min(1)).query.get.map(_ ==> List(Set(offsetDateTime1)))
        _ <- Ns.offsetDateTimeSet(min(2)).query.get.map(_ ==> List(Set(offsetDateTime1, offsetDateTime2)))
        _ <- Ns.offsetDateTimeSet(min(3)).query.get.map(_ ==> List(Set(offsetDateTime1, offsetDateTime2, offsetDateTime3)))

        _ <- Ns.i.a1.offsetDateTimeSet(min(1)).query.get.map(_ ==> List(
          (1, Set(offsetDateTime1)),
          (2, Set(offsetDateTime2)),
        ))
        _ <- Ns.i.a1.offsetDateTimeSet(min(2)).query.get.map(_ ==> List(
          (1, Set(offsetDateTime1, offsetDateTime2)),
          (2, Set(offsetDateTime2, offsetDateTime3)),
        ))
        _ <- Ns.i.a1.offsetDateTimeSet(min(3)).query.get.map(_ ==> List(
          (1, Set(offsetDateTime1, offsetDateTime2)),
          (2, Set(offsetDateTime2, offsetDateTime3, offsetDateTime4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.offsetDateTimeSet.insert(List(
          (1, Set(offsetDateTime1, offsetDateTime2)),
          (2, Set(offsetDateTime2)),
          (2, Set(offsetDateTime3, offsetDateTime4)),
          (2, Set(offsetDateTime3, offsetDateTime4)),
        )).transact

        // Maximum value

        _ <- Ns.offsetDateTimeSet(max).query.get.map(_ ==> List(offsetDateTime4))

        // Sort by maximum value
        _ <- Ns.i.offsetDateTimeSet(max).a1.query.get.map(_ ==> List(
          (1, offsetDateTime2),
          (2, offsetDateTime4),
        ))
        _ <- Ns.i.offsetDateTimeSet(max).d1.query.get.map(_ ==> List(
          (2, offsetDateTime4),
          (1, offsetDateTime2),
        ))

        // Set of maximum values

        _ <- Ns.offsetDateTimeSet(max(1)).query.get.map(_ ==> List(Set(offsetDateTime4)))
        _ <- Ns.offsetDateTimeSet(max(2)).query.get.map(_ ==> List(Set(offsetDateTime3, offsetDateTime4)))
        _ <- Ns.offsetDateTimeSet(max(3)).query.get.map(_ ==> List(Set(offsetDateTime2, offsetDateTime3, offsetDateTime4)))

        _ <- Ns.i.a1.offsetDateTimeSet(max(1)).query.get.map(_ ==> List(
          (1, Set(offsetDateTime2)),
          (2, Set(offsetDateTime4)),
        ))
        _ <- Ns.i.a1.offsetDateTimeSet(max(2)).query.get.map(_ ==> List(
          (1, Set(offsetDateTime1, offsetDateTime2)),
          (2, Set(offsetDateTime3, offsetDateTime4)),
        ))
        _ <- Ns.i.a1.offsetDateTimeSet(max(3)).query.get.map(_ ==> List(
          (1, Set(offsetDateTime1, offsetDateTime2)),
          (2, Set(offsetDateTime2, offsetDateTime3, offsetDateTime4)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.offsetDateTimeSet.insert(List(
          (1, Set(offsetDateTime1, offsetDateTime2)),
          (2, Set(offsetDateTime2)),
          (2, Set(offsetDateTime3, offsetDateTime4)),
          (2, Set(offsetDateTime3, offsetDateTime4)),
        )).transact

        all = Set(offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4)

        _ <- Ns.offsetDateTimeSet(sample).query.get.map { rows =>
          val singleSampleValue: OffsetDateTime = rows.head
          all.contains(singleSampleValue) ==> true
        }

        // Sort by sample value (not checked here)
        _ <- Ns.i.offsetDateTimeSet(sample).a1.query.get
        _ <- Ns.i.offsetDateTimeSet(sample).d1.query.get

        // Multiple samples
        _ <- Ns.offsetDateTimeSet(sample(1)).query.get.map { rows =>
          val sampleSetWithOneValue: Set[OffsetDateTime] = rows.head
          all.contains(sampleSetWithOneValue.head) ==> true
        }
        _ <- Ns.offsetDateTimeSet(sample(2)).query.get.map { rows =>
          val sampleSetWithTwoValues: Set[OffsetDateTime] = rows.head
          all.intersect(sampleSetWithTwoValues).nonEmpty ==> true
        }
      } yield ()
    }


    "distinct sets of values" - types { implicit conn =>
      for {
        _ <- Ns.i.offsetDateTimeSet.insert(List(
          (1, Set(offsetDateTime1, offsetDateTime2)),
          (2, Set(offsetDateTime2)),
          (2, Set(offsetDateTime3, offsetDateTime4)),
          (2, Set(offsetDateTime3, offsetDateTime4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.offsetDateTimeSet.query.get.map(_ ==> List(
          (1, Set(offsetDateTime1, offsetDateTime2)),
          (2, Set(offsetDateTime2, offsetDateTime3, offsetDateTime4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.offsetDateTimeSet(distinct).query.get.map(_ ==> List(
          (1, Set(Set(offsetDateTime1, offsetDateTime2))),
          (2, Set(
            Set(offsetDateTime2),
            Set(offsetDateTime3, offsetDateTime4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.offsetDateTimeSet(distinct).query.get.map(_ ==> List(
          Set(
            Set(offsetDateTime1, offsetDateTime2),
            Set(offsetDateTime2),
            Set(offsetDateTime3, offsetDateTime4),
          )
        ))
      } yield ()
    }
  }
}