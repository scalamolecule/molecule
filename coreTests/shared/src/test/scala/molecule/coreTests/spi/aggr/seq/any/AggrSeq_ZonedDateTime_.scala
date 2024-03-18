// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.seq.any

import java.time.ZonedDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeq_ZonedDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.zonedDateTimeSeq.insert(List(
          (1, List(zonedDateTime1, zonedDateTime2, zonedDateTime2)),
          (2, List(zonedDateTime2)),
          (2, List(zonedDateTime3, zonedDateTime4, zonedDateTime4)),
          (2, List(zonedDateTime3, zonedDateTime4, zonedDateTime4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.zonedDateTimeSeq(count).query.get.map(_ ==> List(10))
        _ <- Ns.zonedDateTimeSeq(countDistinct).query.get.map(_ ==> List(4))

        // We can sort by counts

        _ <- Ns.i.zonedDateTimeSeq(count).a1.query.get.map(_ ==> List(
          (1, 3),
          (2, 7),
        ))
        _ <- Ns.i.zonedDateTimeSeq(count).d1.query.get.map(_ ==> List(
          (2, 7),
          (1, 3),
        ))

        _ <- Ns.i.zonedDateTimeSeq(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- Ns.i.zonedDateTimeSeq(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.zonedDateTimeSeq.insert(List(
          (1, List(zonedDateTime1, zonedDateTime2, zonedDateTime2)),
          (2, List(zonedDateTime2)),
          (2, List(zonedDateTime3, zonedDateTime4, zonedDateTime4)),
          (2, List(zonedDateTime3, zonedDateTime4, zonedDateTime4)),
        )).transact

        // Minimum value

        _ <- Ns.zonedDateTimeSeq(min).query.get.map(_ ==> List(zonedDateTime1))

        // We can sort by minimum value
        _ <- Ns.i.zonedDateTimeSeq(min).a1.query.get.map(_ ==> List(
          (1, zonedDateTime1),
          (2, zonedDateTime2),
        ))
        _ <- Ns.i.zonedDateTimeSeq(min).d1.query.get.map(_ ==> List(
          (2, zonedDateTime2),
          (1, zonedDateTime1),
        ))

        // Set of minimum values

        _ <- Ns.zonedDateTimeSeq.apply(min(1)).query.get.map(_ ==> List(Set(zonedDateTime1)))
        _ <- Ns.zonedDateTimeSeq(min(2)).query.get.map(_ ==> List(Set(zonedDateTime1, zonedDateTime2)))
        _ <- Ns.zonedDateTimeSeq(min(3)).query.get.map(_ ==> List(Set(zonedDateTime1, zonedDateTime2, zonedDateTime3)))

        _ <- Ns.i.a1.zonedDateTimeSeq(min(1)).query.get.map(_ ==> List(
          (1, Set(zonedDateTime1)),
          (2, Set(zonedDateTime2)),
        ))
        _ <- Ns.i.a1.zonedDateTimeSeq(min(2)).query.get.map(_ ==> List(
          (1, Set(zonedDateTime1, zonedDateTime2)),
          (2, Set(zonedDateTime2, zonedDateTime3)),
        ))
        _ <- Ns.i.a1.zonedDateTimeSeq(min(3)).query.get.map(_ ==> List(
          (1, Set(zonedDateTime1, zonedDateTime2)),
          (2, Set(zonedDateTime2, zonedDateTime3, zonedDateTime4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.zonedDateTimeSeq.insert(List(
          (1, List(zonedDateTime1, zonedDateTime2, zonedDateTime2)),
          (2, List(zonedDateTime2)),
          (2, List(zonedDateTime3, zonedDateTime4, zonedDateTime4)),
          (2, List(zonedDateTime3, zonedDateTime4, zonedDateTime4)),
        )).transact

        // Maximum value

        _ <- Ns.zonedDateTimeSeq(max).query.get.map(_ ==> List(zonedDateTime4))

        // We can sort by maximum value
        _ <- Ns.i.zonedDateTimeSeq(max).a1.query.get.map(_ ==> List(
          (1, zonedDateTime2),
          (2, zonedDateTime4),
        ))
        _ <- Ns.i.zonedDateTimeSeq(max).d1.query.get.map(_ ==> List(
          (2, zonedDateTime4),
          (1, zonedDateTime2),
        ))

        // Set of maximum values

        _ <- Ns.zonedDateTimeSeq(max(1)).query.get.map(_ ==> List(Set(zonedDateTime4)))
        _ <- Ns.zonedDateTimeSeq(max(2)).query.get.map(_ ==> List(Set(zonedDateTime4, zonedDateTime3)))
        _ <- Ns.zonedDateTimeSeq(max(3)).query.get.map(_ ==> List(Set(zonedDateTime4, zonedDateTime3, zonedDateTime2)))

        // Same as
        _ <- Ns.i.a1.zonedDateTimeSeq(max(1)).query.get.map(_ ==> List(
          (1, Set(zonedDateTime2)),
          (2, Set(zonedDateTime4)),
        ))
        _ <- Ns.i.a1.zonedDateTimeSeq(max(2)).query.get.map(_ ==> List(
          (1, Set(zonedDateTime2, zonedDateTime1)),
          (2, Set(zonedDateTime4, zonedDateTime3)),
        ))
        _ <- Ns.i.a1.zonedDateTimeSeq(max(3)).query.get.map(_ ==> List(
          (1, Set(zonedDateTime2, zonedDateTime1)),
          (2, Set(zonedDateTime4, zonedDateTime3, zonedDateTime2)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.zonedDateTimeSeq.insert(List(
          (1, List(zonedDateTime1, zonedDateTime2, zonedDateTime2)),
          (2, List(zonedDateTime2)),
          (2, List(zonedDateTime3, zonedDateTime4, zonedDateTime4)),
          (2, List(zonedDateTime3, zonedDateTime4, zonedDateTime4)),
        )).transact

        all = Set(zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4)

        _ <- Ns.zonedDateTimeSeq(sample).query.get.map { rows =>
          val singleSampleValue = rows.head
          all.contains(singleSampleValue) ==> true
        }

        // We can sort by sample value (not checked here)
        _ <- Ns.i.zonedDateTimeSeq(sample).a1.query.get
        _ <- Ns.i.zonedDateTimeSeq(sample).d1.query.get

        // Multiple samples
        _ <- Ns.zonedDateTimeSeq(sample(1)).query.get.map { rows =>
          val sampleSetWithOneValue: Set[ZonedDateTime] = rows.head
          all.contains(sampleSetWithOneValue.head) ==> true
        }
        _ <- Ns.zonedDateTimeSeq(sample(2)).query.get.map { rows =>
          val sampleSetWithTwoValues: Set[ZonedDateTime] = rows.head
          all.intersect(sampleSetWithTwoValues).nonEmpty ==> true
        }
      } yield ()
    }


    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.zonedDateTimeSeq.insert(List(
          (1, List(zonedDateTime1, zonedDateTime2, zonedDateTime2)),
          (2, List(zonedDateTime2)),
          (2, List(zonedDateTime3, zonedDateTime4, zonedDateTime4)),
          (2, List(zonedDateTime3, zonedDateTime4, zonedDateTime4)),
        )).transact

        // Non-aggregated card-many Seq of attribute values coalesce
        _ <- Ns.i.zonedDateTimeSeq.query.get.map(_.sortBy(t => (t._1, t._2.head)) ==> List(
          (1, List(zonedDateTime1, zonedDateTime2, zonedDateTime2)),
          (2, List(zonedDateTime2)),
          (2, List(zonedDateTime3, zonedDateTime4, zonedDateTime4)), // 2 rows with List(zonedDateTime3, zonedDateTime4) coalesced
        ))

        // Use `distinct` keyword to retrieve unique Seqs of Seqs
        _ <- Ns.i.a1.zonedDateTimeSeq(distinct).query.get.map(_ ==> List(
          (1, Set(List(zonedDateTime1, zonedDateTime2, zonedDateTime2))),
          (2, Set(
            List(zonedDateTime2),
            List(zonedDateTime3, zonedDateTime4, zonedDateTime4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.zonedDateTimeSeq(distinct).query.get.map(_ ==> List(
          Set(
            List(zonedDateTime1, zonedDateTime2, zonedDateTime2),
            List(zonedDateTime2),
            List(zonedDateTime3, zonedDateTime4, zonedDateTime4),
          )
        ))
      } yield ()
    }
  }
}