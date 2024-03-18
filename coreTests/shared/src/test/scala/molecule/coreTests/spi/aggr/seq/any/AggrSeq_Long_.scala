// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.seq.any

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeq_Long_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.longSeq.insert(List(
          (1, List(long1, long2, long2)),
          (2, List(long2)),
          (2, List(long3, long4, long4)),
          (2, List(long3, long4, long4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.longSeq(count).query.get.map(_ ==> List(10))
        _ <- Ns.longSeq(countDistinct).query.get.map(_ ==> List(4))

        // We can sort by counts

        _ <- Ns.i.longSeq(count).a1.query.get.map(_ ==> List(
          (1, 3),
          (2, 7),
        ))
        _ <- Ns.i.longSeq(count).d1.query.get.map(_ ==> List(
          (2, 7),
          (1, 3),
        ))

        _ <- Ns.i.longSeq(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- Ns.i.longSeq(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.longSeq.insert(List(
          (1, List(long1, long2, long2)),
          (2, List(long2)),
          (2, List(long3, long4, long4)),
          (2, List(long3, long4, long4)),
        )).transact

        // Minimum value

        _ <- Ns.longSeq(min).query.get.map(_ ==> List(long1))

        // We can sort by minimum value
        _ <- Ns.i.longSeq(min).a1.query.get.map(_ ==> List(
          (1, long1),
          (2, long2),
        ))
        _ <- Ns.i.longSeq(min).d1.query.get.map(_ ==> List(
          (2, long2),
          (1, long1),
        ))

        // Set of minimum values

        _ <- Ns.longSeq.apply(min(1)).query.get.map(_ ==> List(Set(long1)))
        _ <- Ns.longSeq(min(2)).query.get.map(_ ==> List(Set(long1, long2)))
        _ <- Ns.longSeq(min(3)).query.get.map(_ ==> List(Set(long1, long2, long3)))

        _ <- Ns.i.a1.longSeq(min(1)).query.get.map(_ ==> List(
          (1, Set(long1)),
          (2, Set(long2)),
        ))
        _ <- Ns.i.a1.longSeq(min(2)).query.get.map(_ ==> List(
          (1, Set(long1, long2)),
          (2, Set(long2, long3)),
        ))
        _ <- Ns.i.a1.longSeq(min(3)).query.get.map(_ ==> List(
          (1, Set(long1, long2)),
          (2, Set(long2, long3, long4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.longSeq.insert(List(
          (1, List(long1, long2, long2)),
          (2, List(long2)),
          (2, List(long3, long4, long4)),
          (2, List(long3, long4, long4)),
        )).transact

        // Maximum value

        _ <- Ns.longSeq(max).query.get.map(_ ==> List(long4))

        // We can sort by maximum value
        _ <- Ns.i.longSeq(max).a1.query.get.map(_ ==> List(
          (1, long2),
          (2, long4),
        ))
        _ <- Ns.i.longSeq(max).d1.query.get.map(_ ==> List(
          (2, long4),
          (1, long2),
        ))

        // Set of maximum values

        _ <- Ns.longSeq(max(1)).query.get.map(_ ==> List(Set(long4)))
        _ <- Ns.longSeq(max(2)).query.get.map(_ ==> List(Set(long4, long3)))
        _ <- Ns.longSeq(max(3)).query.get.map(_ ==> List(Set(long4, long3, long2)))

        // Same as
        _ <- Ns.i.a1.longSeq(max(1)).query.get.map(_ ==> List(
          (1, Set(long2)),
          (2, Set(long4)),
        ))
        _ <- Ns.i.a1.longSeq(max(2)).query.get.map(_ ==> List(
          (1, Set(long2, long1)),
          (2, Set(long4, long3)),
        ))
        _ <- Ns.i.a1.longSeq(max(3)).query.get.map(_ ==> List(
          (1, Set(long2, long1)),
          (2, Set(long4, long3, long2)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.longSeq.insert(List(
          (1, List(long1, long2, long2)),
          (2, List(long2)),
          (2, List(long3, long4, long4)),
          (2, List(long3, long4, long4)),
        )).transact

        all = Set(long1, long2, long3, long4)

        _ <- Ns.longSeq(sample).query.get.map { rows =>
          val singleSampleValue = rows.head
          all.contains(singleSampleValue) ==> true
        }

        // We can sort by sample value (not checked here)
        _ <- Ns.i.longSeq(sample).a1.query.get
        _ <- Ns.i.longSeq(sample).d1.query.get

        // Multiple samples
        _ <- Ns.longSeq(sample(1)).query.get.map { rows =>
          val sampleSetWithOneValue: Set[Long] = rows.head
          all.contains(sampleSetWithOneValue.head) ==> true
        }
        _ <- Ns.longSeq(sample(2)).query.get.map { rows =>
          val sampleSetWithTwoValues: Set[Long] = rows.head
          all.intersect(sampleSetWithTwoValues).nonEmpty ==> true
        }
      } yield ()
    }


    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.longSeq.insert(List(
          (1, List(long1, long2, long2)),
          (2, List(long2)),
          (2, List(long3, long4, long4)),
          (2, List(long3, long4, long4)),
        )).transact

        // Non-aggregated card-many Seq of attribute values coalesce
        _ <- Ns.i.longSeq.query.get.map(_.sortBy(t => (t._1, t._2.head)) ==> List(
          (1, List(long1, long2, long2)),
          (2, List(long2)),
          (2, List(long3, long4, long4)), // 2 rows with List(long3, long4) coalesced
        ))

        // Use `distinct` keyword to retrieve unique Seqs of Seqs
        _ <- Ns.i.a1.longSeq(distinct).query.get.map(_ ==> List(
          (1, Set(List(long1, long2, long2))),
          (2, Set(
            List(long2),
            List(long3, long4, long4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.longSeq(distinct).query.get.map(_ ==> List(
          Set(
            List(long1, long2, long2),
            List(long2),
            List(long3, long4, long4),
          )
        ))
      } yield ()
    }
  }
}