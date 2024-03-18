// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.seq.any

import java.time.Duration
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeq_Duration_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.durationSeq.insert(List(
          (1, List(duration1, duration2, duration2)),
          (2, List(duration2)),
          (2, List(duration3, duration4, duration4)),
          (2, List(duration3, duration4, duration4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.durationSeq(count).query.get.map(_ ==> List(10))
        _ <- Ns.durationSeq(countDistinct).query.get.map(_ ==> List(4))

        // We can sort by counts

        _ <- Ns.i.durationSeq(count).a1.query.get.map(_ ==> List(
          (1, 3),
          (2, 7),
        ))
        _ <- Ns.i.durationSeq(count).d1.query.get.map(_ ==> List(
          (2, 7),
          (1, 3),
        ))

        _ <- Ns.i.durationSeq(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- Ns.i.durationSeq(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.durationSeq.insert(List(
          (1, List(duration1, duration2, duration2)),
          (2, List(duration2)),
          (2, List(duration3, duration4, duration4)),
          (2, List(duration3, duration4, duration4)),
        )).transact

        // Minimum value

        _ <- Ns.durationSeq(min).query.get.map(_ ==> List(duration1))

        // We can sort by minimum value
        _ <- Ns.i.durationSeq(min).a1.query.get.map(_ ==> List(
          (1, duration1),
          (2, duration2),
        ))
        _ <- Ns.i.durationSeq(min).d1.query.get.map(_ ==> List(
          (2, duration2),
          (1, duration1),
        ))

        // Set of minimum values

        _ <- Ns.durationSeq.apply(min(1)).query.get.map(_ ==> List(Set(duration1)))
        _ <- Ns.durationSeq(min(2)).query.get.map(_ ==> List(Set(duration1, duration2)))
        _ <- Ns.durationSeq(min(3)).query.get.map(_ ==> List(Set(duration1, duration2, duration3)))

        _ <- Ns.i.a1.durationSeq(min(1)).query.get.map(_ ==> List(
          (1, Set(duration1)),
          (2, Set(duration2)),
        ))
        _ <- Ns.i.a1.durationSeq(min(2)).query.get.map(_ ==> List(
          (1, Set(duration1, duration2)),
          (2, Set(duration2, duration3)),
        ))
        _ <- Ns.i.a1.durationSeq(min(3)).query.get.map(_ ==> List(
          (1, Set(duration1, duration2)),
          (2, Set(duration2, duration3, duration4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.durationSeq.insert(List(
          (1, List(duration1, duration2, duration2)),
          (2, List(duration2)),
          (2, List(duration3, duration4, duration4)),
          (2, List(duration3, duration4, duration4)),
        )).transact

        // Maximum value

        _ <- Ns.durationSeq(max).query.get.map(_ ==> List(duration4))

        // We can sort by maximum value
        _ <- Ns.i.durationSeq(max).a1.query.get.map(_ ==> List(
          (1, duration2),
          (2, duration4),
        ))
        _ <- Ns.i.durationSeq(max).d1.query.get.map(_ ==> List(
          (2, duration4),
          (1, duration2),
        ))

        // Set of maximum values

        _ <- Ns.durationSeq(max(1)).query.get.map(_ ==> List(Set(duration4)))
        _ <- Ns.durationSeq(max(2)).query.get.map(_ ==> List(Set(duration4, duration3)))
        _ <- Ns.durationSeq(max(3)).query.get.map(_ ==> List(Set(duration4, duration3, duration2)))

        // Same as
        _ <- Ns.i.a1.durationSeq(max(1)).query.get.map(_ ==> List(
          (1, Set(duration2)),
          (2, Set(duration4)),
        ))
        _ <- Ns.i.a1.durationSeq(max(2)).query.get.map(_ ==> List(
          (1, Set(duration2, duration1)),
          (2, Set(duration4, duration3)),
        ))
        _ <- Ns.i.a1.durationSeq(max(3)).query.get.map(_ ==> List(
          (1, Set(duration2, duration1)),
          (2, Set(duration4, duration3, duration2)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.durationSeq.insert(List(
          (1, List(duration1, duration2, duration2)),
          (2, List(duration2)),
          (2, List(duration3, duration4, duration4)),
          (2, List(duration3, duration4, duration4)),
        )).transact

        all = Set(duration1, duration2, duration3, duration4)

        _ <- Ns.durationSeq(sample).query.get.map { rows =>
          val singleSampleValue = rows.head
          all.contains(singleSampleValue) ==> true
        }

        // We can sort by sample value (not checked here)
        _ <- Ns.i.durationSeq(sample).a1.query.get
        _ <- Ns.i.durationSeq(sample).d1.query.get

        // Multiple samples
        _ <- Ns.durationSeq(sample(1)).query.get.map { rows =>
          val sampleSetWithOneValue: Set[Duration] = rows.head
          all.contains(sampleSetWithOneValue.head) ==> true
        }
        _ <- Ns.durationSeq(sample(2)).query.get.map { rows =>
          val sampleSetWithTwoValues: Set[Duration] = rows.head
          all.intersect(sampleSetWithTwoValues).nonEmpty ==> true
        }
      } yield ()
    }


    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.durationSeq.insert(List(
          (1, List(duration1, duration2, duration2)),
          (2, List(duration2)),
          (2, List(duration3, duration4, duration4)),
          (2, List(duration3, duration4, duration4)),
        )).transact

        // Non-aggregated card-many Seq of attribute values coalesce
        _ <- Ns.i.durationSeq.query.get.map(_.sortBy(t => (t._1, t._2.head)) ==> List(
          (1, List(duration1, duration2, duration2)),
          (2, List(duration2)),
          (2, List(duration3, duration4, duration4)), // 2 rows with List(duration3, duration4) coalesced
        ))

        // Use `distinct` keyword to retrieve unique Seqs of Seqs
        _ <- Ns.i.a1.durationSeq(distinct).query.get.map(_ ==> List(
          (1, Set(List(duration1, duration2, duration2))),
          (2, Set(
            List(duration2),
            List(duration3, duration4, duration4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.durationSeq(distinct).query.get.map(_ ==> List(
          Set(
            List(duration1, duration2, duration2),
            List(duration2),
            List(duration3, duration4, duration4),
          )
        ))
      } yield ()
    }
  }
}