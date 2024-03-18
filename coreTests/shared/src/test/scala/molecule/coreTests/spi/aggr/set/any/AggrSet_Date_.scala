// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.set.any

import java.util.Date
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSet_Date_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.dateSet.insert(List(
          (1, Set(date1, date2)),
          (2, Set(date2)),
          (2, Set(date3, date4)),
          (2, Set(date3, date4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.dateSet(count).query.get.map(_ ==> List(7))
        _ <- Ns.dateSet(countDistinct).query.get.map(_ ==> List(4))

        // Sort by counts

        _ <- Ns.i.dateSet(count).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 5),
        ))
        _ <- Ns.i.dateSet(count).d1.query.get.map(_ ==> List(
          (2, 5),
          (1, 2),
        ))

        _ <- Ns.i.dateSet(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- Ns.i.dateSet(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.dateSet.insert(List(
          (1, Set(date1, date2)),
          (2, Set(date2)),
          (2, Set(date3, date4)),
          (2, Set(date3, date4)),
        )).transact

        // Minimum value

        _ <- Ns.dateSet(min).query.get.map(_ ==> List(date1))

        // Sort by minimum value
        _ <- Ns.i.dateSet(min).a1.query.get.map(_ ==> List(
          (1, date1),
          (2, date2),
        ))
        _ <- Ns.i.dateSet(min).d1.query.get.map(_ ==> List(
          (2, date2),
          (1, date1),
        ))

        // Set of minimum values

        _ <- Ns.dateSet(min(1)).query.get.map(_ ==> List(Set(date1)))
        _ <- Ns.dateSet(min(2)).query.get.map(_ ==> List(Set(date1, date2)))
        _ <- Ns.dateSet(min(3)).query.get.map(_ ==> List(Set(date1, date2, date3)))

        _ <- Ns.i.a1.dateSet(min(1)).query.get.map(_ ==> List(
          (1, Set(date1)),
          (2, Set(date2)),
        ))
        _ <- Ns.i.a1.dateSet(min(2)).query.get.map(_ ==> List(
          (1, Set(date1, date2)),
          (2, Set(date2, date3)),
        ))
        _ <- Ns.i.a1.dateSet(min(3)).query.get.map(_ ==> List(
          (1, Set(date1, date2)),
          (2, Set(date2, date3, date4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.dateSet.insert(List(
          (1, Set(date1, date2)),
          (2, Set(date2)),
          (2, Set(date3, date4)),
          (2, Set(date3, date4)),
        )).transact

        // Maximum value

        _ <- Ns.dateSet(max).query.get.map(_ ==> List(date4))

        // Sort by maximum value
        _ <- Ns.i.dateSet(max).a1.query.get.map(_ ==> List(
          (1, date2),
          (2, date4),
        ))
        _ <- Ns.i.dateSet(max).d1.query.get.map(_ ==> List(
          (2, date4),
          (1, date2),
        ))

        // Set of maximum values

        _ <- Ns.dateSet(max(1)).query.get.map(_ ==> List(Set(date4)))
        _ <- Ns.dateSet(max(2)).query.get.map(_ ==> List(Set(date3, date4)))
        _ <- Ns.dateSet(max(3)).query.get.map(_ ==> List(Set(date2, date3, date4)))

        _ <- Ns.i.a1.dateSet(max(1)).query.get.map(_ ==> List(
          (1, Set(date2)),
          (2, Set(date4)),
        ))
        _ <- Ns.i.a1.dateSet(max(2)).query.get.map(_ ==> List(
          (1, Set(date1, date2)),
          (2, Set(date3, date4)),
        ))
        _ <- Ns.i.a1.dateSet(max(3)).query.get.map(_ ==> List(
          (1, Set(date1, date2)),
          (2, Set(date2, date3, date4)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.dateSet.insert(List(
          (1, Set(date1, date2)),
          (2, Set(date2)),
          (2, Set(date3, date4)),
          (2, Set(date3, date4)),
        )).transact

        all = Set(date1, date2, date3, date4)

        _ <- Ns.dateSet(sample).query.get.map { rows =>
          val singleSampleValue: Date = rows.head
          all.contains(singleSampleValue) ==> true
        }

        // Sort by sample value (not checked here)
        _ <- Ns.i.dateSet(sample).a1.query.get
        _ <- Ns.i.dateSet(sample).d1.query.get

        // Multiple samples
        _ <- Ns.dateSet(sample(1)).query.get.map { rows =>
          val sampleSetWithOneValue: Set[Date] = rows.head
          all.contains(sampleSetWithOneValue.head) ==> true
        }
        _ <- Ns.dateSet(sample(2)).query.get.map { rows =>
          val sampleSetWithTwoValues: Set[Date] = rows.head
          all.intersect(sampleSetWithTwoValues).nonEmpty ==> true
        }
      } yield ()
    }


    "distinct sets of values" - types { implicit conn =>
      for {
        _ <- Ns.i.dateSet.insert(List(
          (1, Set(date1, date2)),
          (2, Set(date2)),
          (2, Set(date3, date4)),
          (2, Set(date3, date4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.dateSet.query.get.map(_ ==> List(
          (1, Set(date1, date2)),
          (2, Set(date2, date3, date4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.dateSet(distinct).query.get.map(_ ==> List(
          (1, Set(Set(date1, date2))),
          (2, Set(
            Set(date2),
            Set(date3, date4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.dateSet(distinct).query.get.map(_ ==> List(
          Set(
            Set(date1, date2),
            Set(date2),
            Set(date3, date4),
          )
        ))
      } yield ()
    }
  }
}