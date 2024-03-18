// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.seq.any

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeq_Double_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.doubleSeq.insert(List(
          (1, List(double1, double2, double2)),
          (2, List(double2)),
          (2, List(double3, double4, double4)),
          (2, List(double3, double4, double4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.doubleSeq(count).query.get.map(_ ==> List(10))
        _ <- Ns.doubleSeq(countDistinct).query.get.map(_ ==> List(4))

        // We can sort by counts

        _ <- Ns.i.doubleSeq(count).a1.query.get.map(_ ==> List(
          (1, 3),
          (2, 7),
        ))
        _ <- Ns.i.doubleSeq(count).d1.query.get.map(_ ==> List(
          (2, 7),
          (1, 3),
        ))

        _ <- Ns.i.doubleSeq(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- Ns.i.doubleSeq(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.doubleSeq.insert(List(
          (1, List(double1, double2, double2)),
          (2, List(double2)),
          (2, List(double3, double4, double4)),
          (2, List(double3, double4, double4)),
        )).transact

        // Minimum value

        _ <- Ns.doubleSeq(min).query.get.map(_ ==> List(double1))

        // We can sort by minimum value
        _ <- Ns.i.doubleSeq(min).a1.query.get.map(_ ==> List(
          (1, double1),
          (2, double2),
        ))
        _ <- Ns.i.doubleSeq(min).d1.query.get.map(_ ==> List(
          (2, double2),
          (1, double1),
        ))

        // Set of minimum values

        _ <- Ns.doubleSeq.apply(min(1)).query.get.map(_ ==> List(Set(double1)))
        _ <- Ns.doubleSeq(min(2)).query.get.map(_ ==> List(Set(double1, double2)))
        _ <- Ns.doubleSeq(min(3)).query.get.map(_ ==> List(Set(double1, double2, double3)))

        _ <- Ns.i.a1.doubleSeq(min(1)).query.get.map(_ ==> List(
          (1, Set(double1)),
          (2, Set(double2)),
        ))
        _ <- Ns.i.a1.doubleSeq(min(2)).query.get.map(_ ==> List(
          (1, Set(double1, double2)),
          (2, Set(double2, double3)),
        ))
        _ <- Ns.i.a1.doubleSeq(min(3)).query.get.map(_ ==> List(
          (1, Set(double1, double2)),
          (2, Set(double2, double3, double4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.doubleSeq.insert(List(
          (1, List(double1, double2, double2)),
          (2, List(double2)),
          (2, List(double3, double4, double4)),
          (2, List(double3, double4, double4)),
        )).transact

        // Maximum value

        _ <- Ns.doubleSeq(max).query.get.map(_ ==> List(double4))

        // We can sort by maximum value
        _ <- Ns.i.doubleSeq(max).a1.query.get.map(_ ==> List(
          (1, double2),
          (2, double4),
        ))
        _ <- Ns.i.doubleSeq(max).d1.query.get.map(_ ==> List(
          (2, double4),
          (1, double2),
        ))

        // Set of maximum values

        _ <- Ns.doubleSeq(max(1)).query.get.map(_ ==> List(Set(double4)))
        _ <- Ns.doubleSeq(max(2)).query.get.map(_ ==> List(Set(double4, double3)))
        _ <- Ns.doubleSeq(max(3)).query.get.map(_ ==> List(Set(double4, double3, double2)))

        // Same as
        _ <- Ns.i.a1.doubleSeq(max(1)).query.get.map(_ ==> List(
          (1, Set(double2)),
          (2, Set(double4)),
        ))
        _ <- Ns.i.a1.doubleSeq(max(2)).query.get.map(_ ==> List(
          (1, Set(double2, double1)),
          (2, Set(double4, double3)),
        ))
        _ <- Ns.i.a1.doubleSeq(max(3)).query.get.map(_ ==> List(
          (1, Set(double2, double1)),
          (2, Set(double4, double3, double2)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.doubleSeq.insert(List(
          (1, List(double1, double2, double2)),
          (2, List(double2)),
          (2, List(double3, double4, double4)),
          (2, List(double3, double4, double4)),
        )).transact

        all = Set(double1, double2, double3, double4)

        _ <- Ns.doubleSeq(sample).query.get.map { rows =>
          val singleSampleValue = rows.head
          all.contains(singleSampleValue) ==> true
        }

        // We can sort by sample value (not checked here)
        _ <- Ns.i.doubleSeq(sample).a1.query.get
        _ <- Ns.i.doubleSeq(sample).d1.query.get

        // Multiple samples
        _ <- Ns.doubleSeq(sample(1)).query.get.map { rows =>
          val sampleSetWithOneValue: Set[Double] = rows.head
          all.contains(sampleSetWithOneValue.head) ==> true
        }
        _ <- Ns.doubleSeq(sample(2)).query.get.map { rows =>
          val sampleSetWithTwoValues: Set[Double] = rows.head
          all.intersect(sampleSetWithTwoValues).nonEmpty ==> true
        }
      } yield ()
    }


    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.doubleSeq.insert(List(
          (1, List(double1, double2, double2)),
          (2, List(double2)),
          (2, List(double3, double4, double4)),
          (2, List(double3, double4, double4)),
        )).transact

        // Non-aggregated card-many Seq of attribute values coalesce
        _ <- Ns.i.doubleSeq.query.get.map(_.sortBy(t => (t._1, t._2.head)) ==> List(
          (1, List(double1, double2, double2)),
          (2, List(double2)),
          (2, List(double3, double4, double4)), // 2 rows with List(double3, double4) coalesced
        ))

        // Use `distinct` keyword to retrieve unique Seqs of Seqs
        _ <- Ns.i.a1.doubleSeq(distinct).query.get.map(_ ==> List(
          (1, Set(List(double1, double2, double2))),
          (2, Set(
            List(double2),
            List(double3, double4, double4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.doubleSeq(distinct).query.get.map(_ ==> List(
          Set(
            List(double1, double2, double2),
            List(double2),
            List(double3, double4, double4),
          )
        ))
      } yield ()
    }
  }
}