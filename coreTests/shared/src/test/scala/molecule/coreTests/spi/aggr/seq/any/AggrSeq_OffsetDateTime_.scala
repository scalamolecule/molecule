// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.seq.any

import java.time.OffsetDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeq_OffsetDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.offsetDateTimeSeq.insert(List(
          (1, List(offsetDateTime1, offsetDateTime2, offsetDateTime2)),
          (2, List(offsetDateTime2)),
          (2, List(offsetDateTime3, offsetDateTime4, offsetDateTime4)),
          (2, List(offsetDateTime3, offsetDateTime4, offsetDateTime4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.offsetDateTimeSeq(count).query.get.map(_ ==> List(10))
        _ <- Ns.offsetDateTimeSeq(countDistinct).query.get.map(_ ==> List(4))

        // We can sort by counts

        _ <- Ns.i.offsetDateTimeSeq(count).a1.query.get.map(_ ==> List(
          (1, 3),
          (2, 7),
        ))
        _ <- Ns.i.offsetDateTimeSeq(count).d1.query.get.map(_ ==> List(
          (2, 7),
          (1, 3),
        ))

        _ <- Ns.i.offsetDateTimeSeq(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- Ns.i.offsetDateTimeSeq(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.offsetDateTimeSeq.insert(List(
          (1, List(offsetDateTime1, offsetDateTime2, offsetDateTime2)),
          (2, List(offsetDateTime2)),
          (2, List(offsetDateTime3, offsetDateTime4, offsetDateTime4)),
          (2, List(offsetDateTime3, offsetDateTime4, offsetDateTime4)),
        )).transact

        // Minimum value

        _ <- Ns.offsetDateTimeSeq(min).query.get.map(_ ==> List(offsetDateTime1))

        // We can sort by minimum value
        _ <- Ns.i.offsetDateTimeSeq(min).a1.query.get.map(_ ==> List(
          (1, offsetDateTime1),
          (2, offsetDateTime2),
        ))
        _ <- Ns.i.offsetDateTimeSeq(min).d1.query.get.map(_ ==> List(
          (2, offsetDateTime2),
          (1, offsetDateTime1),
        ))

        // Set of minimum values

        _ <- Ns.offsetDateTimeSeq.apply(min(1)).query.get.map(_ ==> List(Set(offsetDateTime1)))
        _ <- Ns.offsetDateTimeSeq(min(2)).query.get.map(_ ==> List(Set(offsetDateTime1, offsetDateTime2)))
        _ <- Ns.offsetDateTimeSeq(min(3)).query.get.map(_ ==> List(Set(offsetDateTime1, offsetDateTime2, offsetDateTime3)))

        _ <- Ns.i.a1.offsetDateTimeSeq(min(1)).query.get.map(_ ==> List(
          (1, Set(offsetDateTime1)),
          (2, Set(offsetDateTime2)),
        ))
        _ <- Ns.i.a1.offsetDateTimeSeq(min(2)).query.get.map(_ ==> List(
          (1, Set(offsetDateTime1, offsetDateTime2)),
          (2, Set(offsetDateTime2, offsetDateTime3)),
        ))
        _ <- Ns.i.a1.offsetDateTimeSeq(min(3)).query.get.map(_ ==> List(
          (1, Set(offsetDateTime1, offsetDateTime2)),
          (2, Set(offsetDateTime2, offsetDateTime3, offsetDateTime4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.offsetDateTimeSeq.insert(List(
          (1, List(offsetDateTime1, offsetDateTime2, offsetDateTime2)),
          (2, List(offsetDateTime2)),
          (2, List(offsetDateTime3, offsetDateTime4, offsetDateTime4)),
          (2, List(offsetDateTime3, offsetDateTime4, offsetDateTime4)),
        )).transact

        // Maximum value

        _ <- Ns.offsetDateTimeSeq(max).query.get.map(_ ==> List(offsetDateTime4))

        // We can sort by maximum value
        _ <- Ns.i.offsetDateTimeSeq(max).a1.query.get.map(_ ==> List(
          (1, offsetDateTime2),
          (2, offsetDateTime4),
        ))
        _ <- Ns.i.offsetDateTimeSeq(max).d1.query.get.map(_ ==> List(
          (2, offsetDateTime4),
          (1, offsetDateTime2),
        ))

        // Set of maximum values

        _ <- Ns.offsetDateTimeSeq(max(1)).query.get.map(_ ==> List(Set(offsetDateTime4)))
        _ <- Ns.offsetDateTimeSeq(max(2)).query.get.map(_ ==> List(Set(offsetDateTime4, offsetDateTime3)))
        _ <- Ns.offsetDateTimeSeq(max(3)).query.get.map(_ ==> List(Set(offsetDateTime4, offsetDateTime3, offsetDateTime2)))

        // Same as
        _ <- Ns.i.a1.offsetDateTimeSeq(max(1)).query.get.map(_ ==> List(
          (1, Set(offsetDateTime2)),
          (2, Set(offsetDateTime4)),
        ))
        _ <- Ns.i.a1.offsetDateTimeSeq(max(2)).query.get.map(_ ==> List(
          (1, Set(offsetDateTime2, offsetDateTime1)),
          (2, Set(offsetDateTime4, offsetDateTime3)),
        ))
        _ <- Ns.i.a1.offsetDateTimeSeq(max(3)).query.get.map(_ ==> List(
          (1, Set(offsetDateTime2, offsetDateTime1)),
          (2, Set(offsetDateTime4, offsetDateTime3, offsetDateTime2)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.offsetDateTimeSeq.insert(List(
          (1, List(offsetDateTime1, offsetDateTime2, offsetDateTime2)),
          (2, List(offsetDateTime2)),
          (2, List(offsetDateTime3, offsetDateTime4, offsetDateTime4)),
          (2, List(offsetDateTime3, offsetDateTime4, offsetDateTime4)),
        )).transact

        all = Set(offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4)

        _ <- Ns.offsetDateTimeSeq(sample).query.get.map { rows =>
          val singleSampleValue = rows.head
          all.contains(singleSampleValue) ==> true
        }

        // We can sort by sample value (not checked here)
        _ <- Ns.i.offsetDateTimeSeq(sample).a1.query.get
        _ <- Ns.i.offsetDateTimeSeq(sample).d1.query.get

        // Multiple samples
        _ <- Ns.offsetDateTimeSeq(sample(1)).query.get.map { rows =>
          val sampleSetWithOneValue: Set[OffsetDateTime] = rows.head
          all.contains(sampleSetWithOneValue.head) ==> true
        }
        _ <- Ns.offsetDateTimeSeq(sample(2)).query.get.map { rows =>
          val sampleSetWithTwoValues: Set[OffsetDateTime] = rows.head
          all.intersect(sampleSetWithTwoValues).nonEmpty ==> true
        }
      } yield ()
    }


    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.offsetDateTimeSeq.insert(List(
          (1, List(offsetDateTime1, offsetDateTime2, offsetDateTime2)),
          (2, List(offsetDateTime2)),
          (2, List(offsetDateTime3, offsetDateTime4, offsetDateTime4)),
          (2, List(offsetDateTime3, offsetDateTime4, offsetDateTime4)),
        )).transact

        // Non-aggregated card-many Seq of attribute values coalesce
        _ <- Ns.i.offsetDateTimeSeq.query.get.map(_.sortBy(t => (t._1, t._2.head)) ==> List(
          (1, List(offsetDateTime1, offsetDateTime2, offsetDateTime2)),
          (2, List(offsetDateTime2)),
          (2, List(offsetDateTime3, offsetDateTime4, offsetDateTime4)), // 2 rows with List(offsetDateTime3, offsetDateTime4) coalesced
        ))

        // Use `distinct` keyword to retrieve unique Seqs of Seqs
        _ <- Ns.i.a1.offsetDateTimeSeq(distinct).query.get.map(_ ==> List(
          (1, Set(List(offsetDateTime1, offsetDateTime2, offsetDateTime2))),
          (2, Set(
            List(offsetDateTime2),
            List(offsetDateTime3, offsetDateTime4, offsetDateTime4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.offsetDateTimeSeq(distinct).query.get.map(_ ==> List(
          Set(
            List(offsetDateTime1, offsetDateTime2, offsetDateTime2),
            List(offsetDateTime2),
            List(offsetDateTime3, offsetDateTime4, offsetDateTime4),
          )
        ))
      } yield ()
    }
  }
}