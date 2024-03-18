// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.seq.any

import java.time.OffsetTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeq_OffsetTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.offsetTimeSeq.insert(List(
          (1, List(offsetTime1, offsetTime2, offsetTime2)),
          (2, List(offsetTime2)),
          (2, List(offsetTime3, offsetTime4, offsetTime4)),
          (2, List(offsetTime3, offsetTime4, offsetTime4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.offsetTimeSeq(count).query.get.map(_ ==> List(10))
        _ <- Ns.offsetTimeSeq(countDistinct).query.get.map(_ ==> List(4))

        // We can sort by counts

        _ <- Ns.i.offsetTimeSeq(count).a1.query.get.map(_ ==> List(
          (1, 3),
          (2, 7),
        ))
        _ <- Ns.i.offsetTimeSeq(count).d1.query.get.map(_ ==> List(
          (2, 7),
          (1, 3),
        ))

        _ <- Ns.i.offsetTimeSeq(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- Ns.i.offsetTimeSeq(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.offsetTimeSeq.insert(List(
          (1, List(offsetTime1, offsetTime2, offsetTime2)),
          (2, List(offsetTime2)),
          (2, List(offsetTime3, offsetTime4, offsetTime4)),
          (2, List(offsetTime3, offsetTime4, offsetTime4)),
        )).transact

        // Minimum value

        _ <- Ns.offsetTimeSeq(min).query.get.map(_ ==> List(offsetTime1))

        // We can sort by minimum value
        _ <- Ns.i.offsetTimeSeq(min).a1.query.get.map(_ ==> List(
          (1, offsetTime1),
          (2, offsetTime2),
        ))
        _ <- Ns.i.offsetTimeSeq(min).d1.query.get.map(_ ==> List(
          (2, offsetTime2),
          (1, offsetTime1),
        ))

        // Set of minimum values

        _ <- Ns.offsetTimeSeq.apply(min(1)).query.get.map(_ ==> List(Set(offsetTime1)))
        _ <- Ns.offsetTimeSeq(min(2)).query.get.map(_ ==> List(Set(offsetTime1, offsetTime2)))
        _ <- Ns.offsetTimeSeq(min(3)).query.get.map(_ ==> List(Set(offsetTime1, offsetTime2, offsetTime3)))

        _ <- Ns.i.a1.offsetTimeSeq(min(1)).query.get.map(_ ==> List(
          (1, Set(offsetTime1)),
          (2, Set(offsetTime2)),
        ))
        _ <- Ns.i.a1.offsetTimeSeq(min(2)).query.get.map(_ ==> List(
          (1, Set(offsetTime1, offsetTime2)),
          (2, Set(offsetTime2, offsetTime3)),
        ))
        _ <- Ns.i.a1.offsetTimeSeq(min(3)).query.get.map(_ ==> List(
          (1, Set(offsetTime1, offsetTime2)),
          (2, Set(offsetTime2, offsetTime3, offsetTime4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.offsetTimeSeq.insert(List(
          (1, List(offsetTime1, offsetTime2, offsetTime2)),
          (2, List(offsetTime2)),
          (2, List(offsetTime3, offsetTime4, offsetTime4)),
          (2, List(offsetTime3, offsetTime4, offsetTime4)),
        )).transact

        // Maximum value

        _ <- Ns.offsetTimeSeq(max).query.get.map(_ ==> List(offsetTime4))

        // We can sort by maximum value
        _ <- Ns.i.offsetTimeSeq(max).a1.query.get.map(_ ==> List(
          (1, offsetTime2),
          (2, offsetTime4),
        ))
        _ <- Ns.i.offsetTimeSeq(max).d1.query.get.map(_ ==> List(
          (2, offsetTime4),
          (1, offsetTime2),
        ))

        // Set of maximum values

        _ <- Ns.offsetTimeSeq(max(1)).query.get.map(_ ==> List(Set(offsetTime4)))
        _ <- Ns.offsetTimeSeq(max(2)).query.get.map(_ ==> List(Set(offsetTime4, offsetTime3)))
        _ <- Ns.offsetTimeSeq(max(3)).query.get.map(_ ==> List(Set(offsetTime4, offsetTime3, offsetTime2)))

        // Same as
        _ <- Ns.i.a1.offsetTimeSeq(max(1)).query.get.map(_ ==> List(
          (1, Set(offsetTime2)),
          (2, Set(offsetTime4)),
        ))
        _ <- Ns.i.a1.offsetTimeSeq(max(2)).query.get.map(_ ==> List(
          (1, Set(offsetTime2, offsetTime1)),
          (2, Set(offsetTime4, offsetTime3)),
        ))
        _ <- Ns.i.a1.offsetTimeSeq(max(3)).query.get.map(_ ==> List(
          (1, Set(offsetTime2, offsetTime1)),
          (2, Set(offsetTime4, offsetTime3, offsetTime2)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.offsetTimeSeq.insert(List(
          (1, List(offsetTime1, offsetTime2, offsetTime2)),
          (2, List(offsetTime2)),
          (2, List(offsetTime3, offsetTime4, offsetTime4)),
          (2, List(offsetTime3, offsetTime4, offsetTime4)),
        )).transact

        all = Set(offsetTime1, offsetTime2, offsetTime3, offsetTime4)

        _ <- Ns.offsetTimeSeq(sample).query.get.map { rows =>
          val singleSampleValue = rows.head
          all.contains(singleSampleValue) ==> true
        }

        // We can sort by sample value (not checked here)
        _ <- Ns.i.offsetTimeSeq(sample).a1.query.get
        _ <- Ns.i.offsetTimeSeq(sample).d1.query.get

        // Multiple samples
        _ <- Ns.offsetTimeSeq(sample(1)).query.get.map { rows =>
          val sampleSetWithOneValue: Set[OffsetTime] = rows.head
          all.contains(sampleSetWithOneValue.head) ==> true
        }
        _ <- Ns.offsetTimeSeq(sample(2)).query.get.map { rows =>
          val sampleSetWithTwoValues: Set[OffsetTime] = rows.head
          all.intersect(sampleSetWithTwoValues).nonEmpty ==> true
        }
      } yield ()
    }


    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.offsetTimeSeq.insert(List(
          (1, List(offsetTime1, offsetTime2, offsetTime2)),
          (2, List(offsetTime2)),
          (2, List(offsetTime3, offsetTime4, offsetTime4)),
          (2, List(offsetTime3, offsetTime4, offsetTime4)),
        )).transact

        // Non-aggregated card-many Seq of attribute values coalesce
        _ <- Ns.i.offsetTimeSeq.query.get.map(_.sortBy(t => (t._1, t._2.head)) ==> List(
          (1, List(offsetTime1, offsetTime2, offsetTime2)),
          (2, List(offsetTime2)),
          (2, List(offsetTime3, offsetTime4, offsetTime4)), // 2 rows with List(offsetTime3, offsetTime4) coalesced
        ))

        // Use `distinct` keyword to retrieve unique Seqs of Seqs
        _ <- Ns.i.a1.offsetTimeSeq(distinct).query.get.map(_ ==> List(
          (1, Set(List(offsetTime1, offsetTime2, offsetTime2))),
          (2, Set(
            List(offsetTime2),
            List(offsetTime3, offsetTime4, offsetTime4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.offsetTimeSeq(distinct).query.get.map(_ ==> List(
          Set(
            List(offsetTime1, offsetTime2, offsetTime2),
            List(offsetTime2),
            List(offsetTime3, offsetTime4, offsetTime4),
          )
        ))
      } yield ()
    }
  }
}