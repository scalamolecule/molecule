// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.seq.any

import java.time.Instant
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeq_Instant_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.instantSeq.insert(List(
          (1, List(instant1, instant2, instant2)),
          (2, List(instant2)),
          (2, List(instant3, instant4, instant4)),
          (2, List(instant3, instant4, instant4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.instantSeq(count).query.get.map(_ ==> List(10))
        _ <- Ns.instantSeq(countDistinct).query.get.map(_ ==> List(4))

        // We can sort by counts

        _ <- Ns.i.instantSeq(count).a1.query.get.map(_ ==> List(
          (1, 3),
          (2, 7),
        ))
        _ <- Ns.i.instantSeq(count).d1.query.get.map(_ ==> List(
          (2, 7),
          (1, 3),
        ))

        _ <- Ns.i.instantSeq(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- Ns.i.instantSeq(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.instantSeq.insert(List(
          (1, List(instant1, instant2, instant2)),
          (2, List(instant2)),
          (2, List(instant3, instant4, instant4)),
          (2, List(instant3, instant4, instant4)),
        )).transact

        // Minimum value

        _ <- Ns.instantSeq(min).query.get.map(_ ==> List(instant1))

        // We can sort by minimum value
        _ <- Ns.i.instantSeq(min).a1.query.get.map(_ ==> List(
          (1, instant1),
          (2, instant2),
        ))
        _ <- Ns.i.instantSeq(min).d1.query.get.map(_ ==> List(
          (2, instant2),
          (1, instant1),
        ))

        // Set of minimum values

        _ <- Ns.instantSeq.apply(min(1)).query.get.map(_ ==> List(Set(instant1)))
        _ <- Ns.instantSeq(min(2)).query.get.map(_ ==> List(Set(instant1, instant2)))
        _ <- Ns.instantSeq(min(3)).query.get.map(_ ==> List(Set(instant1, instant2, instant3)))

        _ <- Ns.i.a1.instantSeq(min(1)).query.get.map(_ ==> List(
          (1, Set(instant1)),
          (2, Set(instant2)),
        ))
        _ <- Ns.i.a1.instantSeq(min(2)).query.get.map(_ ==> List(
          (1, Set(instant1, instant2)),
          (2, Set(instant2, instant3)),
        ))
        _ <- Ns.i.a1.instantSeq(min(3)).query.get.map(_ ==> List(
          (1, Set(instant1, instant2)),
          (2, Set(instant2, instant3, instant4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.instantSeq.insert(List(
          (1, List(instant1, instant2, instant2)),
          (2, List(instant2)),
          (2, List(instant3, instant4, instant4)),
          (2, List(instant3, instant4, instant4)),
        )).transact

        // Maximum value

        _ <- Ns.instantSeq(max).query.get.map(_ ==> List(instant4))

        // We can sort by maximum value
        _ <- Ns.i.instantSeq(max).a1.query.get.map(_ ==> List(
          (1, instant2),
          (2, instant4),
        ))
        _ <- Ns.i.instantSeq(max).d1.query.get.map(_ ==> List(
          (2, instant4),
          (1, instant2),
        ))

        // Set of maximum values

        _ <- Ns.instantSeq(max(1)).query.get.map(_ ==> List(Set(instant4)))
        _ <- Ns.instantSeq(max(2)).query.get.map(_ ==> List(Set(instant4, instant3)))
        _ <- Ns.instantSeq(max(3)).query.get.map(_ ==> List(Set(instant4, instant3, instant2)))

        // Same as
        _ <- Ns.i.a1.instantSeq(max(1)).query.get.map(_ ==> List(
          (1, Set(instant2)),
          (2, Set(instant4)),
        ))
        _ <- Ns.i.a1.instantSeq(max(2)).query.get.map(_ ==> List(
          (1, Set(instant2, instant1)),
          (2, Set(instant4, instant3)),
        ))
        _ <- Ns.i.a1.instantSeq(max(3)).query.get.map(_ ==> List(
          (1, Set(instant2, instant1)),
          (2, Set(instant4, instant3, instant2)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.instantSeq.insert(List(
          (1, List(instant1, instant2, instant2)),
          (2, List(instant2)),
          (2, List(instant3, instant4, instant4)),
          (2, List(instant3, instant4, instant4)),
        )).transact

        all = Set(instant1, instant2, instant3, instant4)

        _ <- Ns.instantSeq(sample).query.get.map { rows =>
          val singleSampleValue = rows.head
          all.contains(singleSampleValue) ==> true
        }

        // We can sort by sample value (not checked here)
        _ <- Ns.i.instantSeq(sample).a1.query.get
        _ <- Ns.i.instantSeq(sample).d1.query.get

        // Multiple samples
        _ <- Ns.instantSeq(sample(1)).query.get.map { rows =>
          val sampleSetWithOneValue: Set[Instant] = rows.head
          all.contains(sampleSetWithOneValue.head) ==> true
        }
        _ <- Ns.instantSeq(sample(2)).query.get.map { rows =>
          val sampleSetWithTwoValues: Set[Instant] = rows.head
          all.intersect(sampleSetWithTwoValues).nonEmpty ==> true
        }
      } yield ()
    }


    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.instantSeq.insert(List(
          (1, List(instant1, instant2, instant2)),
          (2, List(instant2)),
          (2, List(instant3, instant4, instant4)),
          (2, List(instant3, instant4, instant4)),
        )).transact

        // Non-aggregated card-many Seq of attribute values coalesce
        _ <- Ns.i.instantSeq.query.get.map(_.sortBy(t => (t._1, t._2.head)) ==> List(
          (1, List(instant1, instant2, instant2)),
          (2, List(instant2)),
          (2, List(instant3, instant4, instant4)), // 2 rows with List(instant3, instant4) coalesced
        ))

        // Use `distinct` keyword to retrieve unique Seqs of Seqs
        _ <- Ns.i.a1.instantSeq(distinct).query.get.map(_ ==> List(
          (1, Set(List(instant1, instant2, instant2))),
          (2, Set(
            List(instant2),
            List(instant3, instant4, instant4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.instantSeq(distinct).query.get.map(_ ==> List(
          Set(
            List(instant1, instant2, instant2),
            List(instant2),
            List(instant3, instant4, instant4),
          )
        ))
      } yield ()
    }
  }
}