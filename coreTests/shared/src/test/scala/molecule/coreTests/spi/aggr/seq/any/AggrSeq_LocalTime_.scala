// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.seq.any

import java.time.LocalTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeq_LocalTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.localTimeSeq.insert(List(
          (1, List(localTime1, localTime2, localTime2)),
          (2, List(localTime2)),
          (2, List(localTime3, localTime4, localTime4)),
          (2, List(localTime3, localTime4, localTime4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.localTimeSeq(count).query.get.map(_ ==> List(10))
        _ <- Ns.localTimeSeq(countDistinct).query.get.map(_ ==> List(4))

        // We can sort by counts

        _ <- Ns.i.localTimeSeq(count).a1.query.get.map(_ ==> List(
          (1, 3),
          (2, 7),
        ))
        _ <- Ns.i.localTimeSeq(count).d1.query.get.map(_ ==> List(
          (2, 7),
          (1, 3),
        ))

        _ <- Ns.i.localTimeSeq(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- Ns.i.localTimeSeq(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.localTimeSeq.insert(List(
          (1, List(localTime1, localTime2, localTime2)),
          (2, List(localTime2)),
          (2, List(localTime3, localTime4, localTime4)),
          (2, List(localTime3, localTime4, localTime4)),
        )).transact

        // Minimum value

        _ <- Ns.localTimeSeq(min).query.get.map(_ ==> List(localTime1))

        // We can sort by minimum value
        _ <- Ns.i.localTimeSeq(min).a1.query.get.map(_ ==> List(
          (1, localTime1),
          (2, localTime2),
        ))
        _ <- Ns.i.localTimeSeq(min).d1.query.get.map(_ ==> List(
          (2, localTime2),
          (1, localTime1),
        ))

        // Set of minimum values

        _ <- Ns.localTimeSeq.apply(min(1)).query.get.map(_ ==> List(Set(localTime1)))
        _ <- Ns.localTimeSeq(min(2)).query.get.map(_ ==> List(Set(localTime1, localTime2)))
        _ <- Ns.localTimeSeq(min(3)).query.get.map(_ ==> List(Set(localTime1, localTime2, localTime3)))

        _ <- Ns.i.a1.localTimeSeq(min(1)).query.get.map(_ ==> List(
          (1, Set(localTime1)),
          (2, Set(localTime2)),
        ))
        _ <- Ns.i.a1.localTimeSeq(min(2)).query.get.map(_ ==> List(
          (1, Set(localTime1, localTime2)),
          (2, Set(localTime2, localTime3)),
        ))
        _ <- Ns.i.a1.localTimeSeq(min(3)).query.get.map(_ ==> List(
          (1, Set(localTime1, localTime2)),
          (2, Set(localTime2, localTime3, localTime4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.localTimeSeq.insert(List(
          (1, List(localTime1, localTime2, localTime2)),
          (2, List(localTime2)),
          (2, List(localTime3, localTime4, localTime4)),
          (2, List(localTime3, localTime4, localTime4)),
        )).transact

        // Maximum value

        _ <- Ns.localTimeSeq(max).query.get.map(_ ==> List(localTime4))

        // We can sort by maximum value
        _ <- Ns.i.localTimeSeq(max).a1.query.get.map(_ ==> List(
          (1, localTime2),
          (2, localTime4),
        ))
        _ <- Ns.i.localTimeSeq(max).d1.query.get.map(_ ==> List(
          (2, localTime4),
          (1, localTime2),
        ))

        // Set of maximum values

        _ <- Ns.localTimeSeq(max(1)).query.get.map(_ ==> List(Set(localTime4)))
        _ <- Ns.localTimeSeq(max(2)).query.get.map(_ ==> List(Set(localTime4, localTime3)))
        _ <- Ns.localTimeSeq(max(3)).query.get.map(_ ==> List(Set(localTime4, localTime3, localTime2)))

        // Same as
        _ <- Ns.i.a1.localTimeSeq(max(1)).query.get.map(_ ==> List(
          (1, Set(localTime2)),
          (2, Set(localTime4)),
        ))
        _ <- Ns.i.a1.localTimeSeq(max(2)).query.get.map(_ ==> List(
          (1, Set(localTime2, localTime1)),
          (2, Set(localTime4, localTime3)),
        ))
        _ <- Ns.i.a1.localTimeSeq(max(3)).query.get.map(_ ==> List(
          (1, Set(localTime2, localTime1)),
          (2, Set(localTime4, localTime3, localTime2)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.localTimeSeq.insert(List(
          (1, List(localTime1, localTime2, localTime2)),
          (2, List(localTime2)),
          (2, List(localTime3, localTime4, localTime4)),
          (2, List(localTime3, localTime4, localTime4)),
        )).transact

        all = Set(localTime1, localTime2, localTime3, localTime4)

        _ <- Ns.localTimeSeq(sample).query.get.map { rows =>
          val singleSampleValue = rows.head
          all.contains(singleSampleValue) ==> true
        }

        // We can sort by sample value (not checked here)
        _ <- Ns.i.localTimeSeq(sample).a1.query.get
        _ <- Ns.i.localTimeSeq(sample).d1.query.get

        // Multiple samples
        _ <- Ns.localTimeSeq(sample(1)).query.get.map { rows =>
          val sampleSetWithOneValue: Set[LocalTime] = rows.head
          all.contains(sampleSetWithOneValue.head) ==> true
        }
        _ <- Ns.localTimeSeq(sample(2)).query.get.map { rows =>
          val sampleSetWithTwoValues: Set[LocalTime] = rows.head
          all.intersect(sampleSetWithTwoValues).nonEmpty ==> true
        }
      } yield ()
    }


    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.localTimeSeq.insert(List(
          (1, List(localTime1, localTime2, localTime2)),
          (2, List(localTime2)),
          (2, List(localTime3, localTime4, localTime4)),
          (2, List(localTime3, localTime4, localTime4)),
        )).transact

        // Non-aggregated card-many Seq of attribute values coalesce
        _ <- Ns.i.localTimeSeq.query.get.map(_.sortBy(t => (t._1, t._2.head)) ==> List(
          (1, List(localTime1, localTime2, localTime2)),
          (2, List(localTime2)),
          (2, List(localTime3, localTime4, localTime4)), // 2 rows with List(localTime3, localTime4) coalesced
        ))

        // Use `distinct` keyword to retrieve unique Seqs of Seqs
        _ <- Ns.i.a1.localTimeSeq(distinct).query.get.map(_ ==> List(
          (1, Set(List(localTime1, localTime2, localTime2))),
          (2, Set(
            List(localTime2),
            List(localTime3, localTime4, localTime4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.localTimeSeq(distinct).query.get.map(_ ==> List(
          Set(
            List(localTime1, localTime2, localTime2),
            List(localTime2),
            List(localTime3, localTime4, localTime4),
          )
        ))
      } yield ()
    }
  }
}