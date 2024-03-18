// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.seq.any

import java.time.LocalDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeq_LocalDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.localDateTimeSeq.insert(List(
          (1, List(localDateTime1, localDateTime2, localDateTime2)),
          (2, List(localDateTime2)),
          (2, List(localDateTime3, localDateTime4, localDateTime4)),
          (2, List(localDateTime3, localDateTime4, localDateTime4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.localDateTimeSeq(count).query.get.map(_ ==> List(10))
        _ <- Ns.localDateTimeSeq(countDistinct).query.get.map(_ ==> List(4))

        // We can sort by counts

        _ <- Ns.i.localDateTimeSeq(count).a1.query.get.map(_ ==> List(
          (1, 3),
          (2, 7),
        ))
        _ <- Ns.i.localDateTimeSeq(count).d1.query.get.map(_ ==> List(
          (2, 7),
          (1, 3),
        ))

        _ <- Ns.i.localDateTimeSeq(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- Ns.i.localDateTimeSeq(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.localDateTimeSeq.insert(List(
          (1, List(localDateTime1, localDateTime2, localDateTime2)),
          (2, List(localDateTime2)),
          (2, List(localDateTime3, localDateTime4, localDateTime4)),
          (2, List(localDateTime3, localDateTime4, localDateTime4)),
        )).transact

        // Minimum value

        _ <- Ns.localDateTimeSeq(min).query.get.map(_ ==> List(localDateTime1))

        // We can sort by minimum value
        _ <- Ns.i.localDateTimeSeq(min).a1.query.get.map(_ ==> List(
          (1, localDateTime1),
          (2, localDateTime2),
        ))
        _ <- Ns.i.localDateTimeSeq(min).d1.query.get.map(_ ==> List(
          (2, localDateTime2),
          (1, localDateTime1),
        ))

        // Set of minimum values

        _ <- Ns.localDateTimeSeq.apply(min(1)).query.get.map(_ ==> List(Set(localDateTime1)))
        _ <- Ns.localDateTimeSeq(min(2)).query.get.map(_ ==> List(Set(localDateTime1, localDateTime2)))
        _ <- Ns.localDateTimeSeq(min(3)).query.get.map(_ ==> List(Set(localDateTime1, localDateTime2, localDateTime3)))

        _ <- Ns.i.a1.localDateTimeSeq(min(1)).query.get.map(_ ==> List(
          (1, Set(localDateTime1)),
          (2, Set(localDateTime2)),
        ))
        _ <- Ns.i.a1.localDateTimeSeq(min(2)).query.get.map(_ ==> List(
          (1, Set(localDateTime1, localDateTime2)),
          (2, Set(localDateTime2, localDateTime3)),
        ))
        _ <- Ns.i.a1.localDateTimeSeq(min(3)).query.get.map(_ ==> List(
          (1, Set(localDateTime1, localDateTime2)),
          (2, Set(localDateTime2, localDateTime3, localDateTime4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.localDateTimeSeq.insert(List(
          (1, List(localDateTime1, localDateTime2, localDateTime2)),
          (2, List(localDateTime2)),
          (2, List(localDateTime3, localDateTime4, localDateTime4)),
          (2, List(localDateTime3, localDateTime4, localDateTime4)),
        )).transact

        // Maximum value

        _ <- Ns.localDateTimeSeq(max).query.get.map(_ ==> List(localDateTime4))

        // We can sort by maximum value
        _ <- Ns.i.localDateTimeSeq(max).a1.query.get.map(_ ==> List(
          (1, localDateTime2),
          (2, localDateTime4),
        ))
        _ <- Ns.i.localDateTimeSeq(max).d1.query.get.map(_ ==> List(
          (2, localDateTime4),
          (1, localDateTime2),
        ))

        // Set of maximum values

        _ <- Ns.localDateTimeSeq(max(1)).query.get.map(_ ==> List(Set(localDateTime4)))
        _ <- Ns.localDateTimeSeq(max(2)).query.get.map(_ ==> List(Set(localDateTime4, localDateTime3)))
        _ <- Ns.localDateTimeSeq(max(3)).query.get.map(_ ==> List(Set(localDateTime4, localDateTime3, localDateTime2)))

        // Same as
        _ <- Ns.i.a1.localDateTimeSeq(max(1)).query.get.map(_ ==> List(
          (1, Set(localDateTime2)),
          (2, Set(localDateTime4)),
        ))
        _ <- Ns.i.a1.localDateTimeSeq(max(2)).query.get.map(_ ==> List(
          (1, Set(localDateTime2, localDateTime1)),
          (2, Set(localDateTime4, localDateTime3)),
        ))
        _ <- Ns.i.a1.localDateTimeSeq(max(3)).query.get.map(_ ==> List(
          (1, Set(localDateTime2, localDateTime1)),
          (2, Set(localDateTime4, localDateTime3, localDateTime2)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.localDateTimeSeq.insert(List(
          (1, List(localDateTime1, localDateTime2, localDateTime2)),
          (2, List(localDateTime2)),
          (2, List(localDateTime3, localDateTime4, localDateTime4)),
          (2, List(localDateTime3, localDateTime4, localDateTime4)),
        )).transact

        all = Set(localDateTime1, localDateTime2, localDateTime3, localDateTime4)

        _ <- Ns.localDateTimeSeq(sample).query.get.map { rows =>
          val singleSampleValue = rows.head
          all.contains(singleSampleValue) ==> true
        }

        // We can sort by sample value (not checked here)
        _ <- Ns.i.localDateTimeSeq(sample).a1.query.get
        _ <- Ns.i.localDateTimeSeq(sample).d1.query.get

        // Multiple samples
        _ <- Ns.localDateTimeSeq(sample(1)).query.get.map { rows =>
          val sampleSetWithOneValue: Set[LocalDateTime] = rows.head
          all.contains(sampleSetWithOneValue.head) ==> true
        }
        _ <- Ns.localDateTimeSeq(sample(2)).query.get.map { rows =>
          val sampleSetWithTwoValues: Set[LocalDateTime] = rows.head
          all.intersect(sampleSetWithTwoValues).nonEmpty ==> true
        }
      } yield ()
    }


    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.localDateTimeSeq.insert(List(
          (1, List(localDateTime1, localDateTime2, localDateTime2)),
          (2, List(localDateTime2)),
          (2, List(localDateTime3, localDateTime4, localDateTime4)),
          (2, List(localDateTime3, localDateTime4, localDateTime4)),
        )).transact

        // Non-aggregated card-many Seq of attribute values coalesce
        _ <- Ns.i.localDateTimeSeq.query.get.map(_.sortBy(t => (t._1, t._2.head)) ==> List(
          (1, List(localDateTime1, localDateTime2, localDateTime2)),
          (2, List(localDateTime2)),
          (2, List(localDateTime3, localDateTime4, localDateTime4)), // 2 rows with List(localDateTime3, localDateTime4) coalesced
        ))

        // Use `distinct` keyword to retrieve unique Seqs of Seqs
        _ <- Ns.i.a1.localDateTimeSeq(distinct).query.get.map(_ ==> List(
          (1, Set(List(localDateTime1, localDateTime2, localDateTime2))),
          (2, Set(
            List(localDateTime2),
            List(localDateTime3, localDateTime4, localDateTime4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.localDateTimeSeq(distinct).query.get.map(_ ==> List(
          Set(
            List(localDateTime1, localDateTime2, localDateTime2),
            List(localDateTime2),
            List(localDateTime3, localDateTime4, localDateTime4),
          )
        ))
      } yield ()
    }
  }
}