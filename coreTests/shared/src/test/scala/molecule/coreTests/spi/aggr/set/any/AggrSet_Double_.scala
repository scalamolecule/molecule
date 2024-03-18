// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.set.any

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSet_Double_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.doubleSet.insert(List(
          (1, Set(double1, double2)),
          (2, Set(double2)),
          (2, Set(double3, double4)),
          (2, Set(double3, double4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.doubleSet(count).query.get.map(_ ==> List(7))
        _ <- Ns.doubleSet(countDistinct).query.get.map(_ ==> List(4))

        // Sort by counts

        _ <- Ns.i.doubleSet(count).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 5),
        ))
        _ <- Ns.i.doubleSet(count).d1.query.get.map(_ ==> List(
          (2, 5),
          (1, 2),
        ))

        _ <- Ns.i.doubleSet(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- Ns.i.doubleSet(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.doubleSet.insert(List(
          (1, Set(double1, double2)),
          (2, Set(double2)),
          (2, Set(double3, double4)),
          (2, Set(double3, double4)),
        )).transact

        // Minimum value

        _ <- Ns.doubleSet(min).query.get.map(_ ==> List(double1))

        // Sort by minimum value
        _ <- Ns.i.doubleSet(min).a1.query.get.map(_ ==> List(
          (1, double1),
          (2, double2),
        ))
        _ <- Ns.i.doubleSet(min).d1.query.get.map(_ ==> List(
          (2, double2),
          (1, double1),
        ))

        // Set of minimum values

        _ <- Ns.doubleSet(min(1)).query.get.map(_ ==> List(Set(double1)))
        _ <- Ns.doubleSet(min(2)).query.get.map(_ ==> List(Set(double1, double2)))
        _ <- Ns.doubleSet(min(3)).query.get.map(_ ==> List(Set(double1, double2, double3)))

        _ <- Ns.i.a1.doubleSet(min(1)).query.get.map(_ ==> List(
          (1, Set(double1)),
          (2, Set(double2)),
        ))
        _ <- Ns.i.a1.doubleSet(min(2)).query.get.map(_ ==> List(
          (1, Set(double1, double2)),
          (2, Set(double2, double3)),
        ))
        _ <- Ns.i.a1.doubleSet(min(3)).query.get.map(_ ==> List(
          (1, Set(double1, double2)),
          (2, Set(double2, double3, double4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.doubleSet.insert(List(
          (1, Set(double1, double2)),
          (2, Set(double2)),
          (2, Set(double3, double4)),
          (2, Set(double3, double4)),
        )).transact

        // Maximum value

        _ <- Ns.doubleSet(max).query.get.map(_ ==> List(double4))

        // Sort by maximum value
        _ <- Ns.i.doubleSet(max).a1.query.get.map(_ ==> List(
          (1, double2),
          (2, double4),
        ))
        _ <- Ns.i.doubleSet(max).d1.query.get.map(_ ==> List(
          (2, double4),
          (1, double2),
        ))

        // Set of maximum values

        _ <- Ns.doubleSet(max(1)).query.get.map(_ ==> List(Set(double4)))
        _ <- Ns.doubleSet(max(2)).query.get.map(_ ==> List(Set(double3, double4)))
        _ <- Ns.doubleSet(max(3)).query.get.map(_ ==> List(Set(double2, double3, double4)))

        _ <- Ns.i.a1.doubleSet(max(1)).query.get.map(_ ==> List(
          (1, Set(double2)),
          (2, Set(double4)),
        ))
        _ <- Ns.i.a1.doubleSet(max(2)).query.get.map(_ ==> List(
          (1, Set(double1, double2)),
          (2, Set(double3, double4)),
        ))
        _ <- Ns.i.a1.doubleSet(max(3)).query.get.map(_ ==> List(
          (1, Set(double1, double2)),
          (2, Set(double2, double3, double4)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.doubleSet.insert(List(
          (1, Set(double1, double2)),
          (2, Set(double2)),
          (2, Set(double3, double4)),
          (2, Set(double3, double4)),
        )).transact

        all = Set(double1, double2, double3, double4)

        _ <- Ns.doubleSet(sample).query.get.map { rows =>
          val singleSampleValue: Double = rows.head
          all.contains(singleSampleValue) ==> true
        }

        // Sort by sample value (not checked here)
        _ <- Ns.i.doubleSet(sample).a1.query.get
        _ <- Ns.i.doubleSet(sample).d1.query.get

        // Multiple samples
        _ <- Ns.doubleSet(sample(1)).query.get.map { rows =>
          val sampleSetWithOneValue: Set[Double] = rows.head
          all.contains(sampleSetWithOneValue.head) ==> true
        }
        _ <- Ns.doubleSet(sample(2)).query.get.map { rows =>
          val sampleSetWithTwoValues: Set[Double] = rows.head
          all.intersect(sampleSetWithTwoValues).nonEmpty ==> true
        }
      } yield ()
    }


    "distinct sets of values" - types { implicit conn =>
      for {
        _ <- Ns.i.doubleSet.insert(List(
          (1, Set(double1, double2)),
          (2, Set(double2)),
          (2, Set(double3, double4)),
          (2, Set(double3, double4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.doubleSet.query.get.map(_ ==> List(
          (1, Set(double1, double2)),
          (2, Set(double2, double3, double4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.doubleSet(distinct).query.get.map(_ ==> List(
          (1, Set(Set(double1, double2))),
          (2, Set(
            Set(double2),
            Set(double3, double4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.doubleSet(distinct).query.get.map(_ ==> List(
          Set(
            Set(double1, double2),
            Set(double2),
            Set(double3, double4),
          )
        ))
      } yield ()
    }
  }
}