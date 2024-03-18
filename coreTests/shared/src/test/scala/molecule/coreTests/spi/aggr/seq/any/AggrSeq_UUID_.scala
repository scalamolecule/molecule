// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.seq.any

import java.util.UUID
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeq_UUID_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.uuidSeq.insert(List(
          (1, List(uuid1, uuid2, uuid2)),
          (2, List(uuid2)),
          (2, List(uuid3, uuid4, uuid4)),
          (2, List(uuid3, uuid4, uuid4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.uuidSeq(count).query.get.map(_ ==> List(10))
        _ <- Ns.uuidSeq(countDistinct).query.get.map(_ ==> List(4))

        // We can sort by counts

        _ <- Ns.i.uuidSeq(count).a1.query.get.map(_ ==> List(
          (1, 3),
          (2, 7),
        ))
        _ <- Ns.i.uuidSeq(count).d1.query.get.map(_ ==> List(
          (2, 7),
          (1, 3),
        ))

        _ <- Ns.i.uuidSeq(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- Ns.i.uuidSeq(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.uuidSeq.insert(List(
          (1, List(uuid1, uuid2, uuid2)),
          (2, List(uuid2)),
          (2, List(uuid3, uuid4, uuid4)),
          (2, List(uuid3, uuid4, uuid4)),
        )).transact

        // Minimum value

        _ <- Ns.uuidSeq(min).query.get.map(_ ==> List(uuid1))

        // We can sort by minimum value
        _ <- Ns.i.uuidSeq(min).a1.query.get.map(_ ==> List(
          (1, uuid1),
          (2, uuid2),
        ))
        _ <- Ns.i.uuidSeq(min).d1.query.get.map(_ ==> List(
          (2, uuid2),
          (1, uuid1),
        ))

        // Set of minimum values

        _ <- Ns.uuidSeq.apply(min(1)).query.get.map(_ ==> List(Set(uuid1)))
        _ <- Ns.uuidSeq(min(2)).query.get.map(_ ==> List(Set(uuid1, uuid2)))
        _ <- Ns.uuidSeq(min(3)).query.get.map(_ ==> List(Set(uuid1, uuid2, uuid3)))

        _ <- Ns.i.a1.uuidSeq(min(1)).query.get.map(_ ==> List(
          (1, Set(uuid1)),
          (2, Set(uuid2)),
        ))
        _ <- Ns.i.a1.uuidSeq(min(2)).query.get.map(_ ==> List(
          (1, Set(uuid1, uuid2)),
          (2, Set(uuid2, uuid3)),
        ))
        _ <- Ns.i.a1.uuidSeq(min(3)).query.get.map(_ ==> List(
          (1, Set(uuid1, uuid2)),
          (2, Set(uuid2, uuid3, uuid4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.uuidSeq.insert(List(
          (1, List(uuid1, uuid2, uuid2)),
          (2, List(uuid2)),
          (2, List(uuid3, uuid4, uuid4)),
          (2, List(uuid3, uuid4, uuid4)),
        )).transact

        // Maximum value

        _ <- Ns.uuidSeq(max).query.get.map(_ ==> List(uuid4))

        // We can sort by maximum value
        _ <- Ns.i.uuidSeq(max).a1.query.get.map(_ ==> List(
          (1, uuid2),
          (2, uuid4),
        ))
        _ <- Ns.i.uuidSeq(max).d1.query.get.map(_ ==> List(
          (2, uuid4),
          (1, uuid2),
        ))

        // Set of maximum values

        _ <- Ns.uuidSeq(max(1)).query.get.map(_ ==> List(Set(uuid4)))
        _ <- Ns.uuidSeq(max(2)).query.get.map(_ ==> List(Set(uuid4, uuid3)))
        _ <- Ns.uuidSeq(max(3)).query.get.map(_ ==> List(Set(uuid4, uuid3, uuid2)))

        // Same as
        _ <- Ns.i.a1.uuidSeq(max(1)).query.get.map(_ ==> List(
          (1, Set(uuid2)),
          (2, Set(uuid4)),
        ))
        _ <- Ns.i.a1.uuidSeq(max(2)).query.get.map(_ ==> List(
          (1, Set(uuid2, uuid1)),
          (2, Set(uuid4, uuid3)),
        ))
        _ <- Ns.i.a1.uuidSeq(max(3)).query.get.map(_ ==> List(
          (1, Set(uuid2, uuid1)),
          (2, Set(uuid4, uuid3, uuid2)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.uuidSeq.insert(List(
          (1, List(uuid1, uuid2, uuid2)),
          (2, List(uuid2)),
          (2, List(uuid3, uuid4, uuid4)),
          (2, List(uuid3, uuid4, uuid4)),
        )).transact

        all = Set(uuid1, uuid2, uuid3, uuid4)

        _ <- Ns.uuidSeq(sample).query.get.map { rows =>
          val singleSampleValue = rows.head
          all.contains(singleSampleValue) ==> true
        }

        // We can sort by sample value (not checked here)
        _ <- Ns.i.uuidSeq(sample).a1.query.get
        _ <- Ns.i.uuidSeq(sample).d1.query.get

        // Multiple samples
        _ <- Ns.uuidSeq(sample(1)).query.get.map { rows =>
          val sampleSetWithOneValue: Set[UUID] = rows.head
          all.contains(sampleSetWithOneValue.head) ==> true
        }
        _ <- Ns.uuidSeq(sample(2)).query.get.map { rows =>
          val sampleSetWithTwoValues: Set[UUID] = rows.head
          all.intersect(sampleSetWithTwoValues).nonEmpty ==> true
        }
      } yield ()
    }


    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.uuidSeq.insert(List(
          (1, List(uuid1, uuid2, uuid2)),
          (2, List(uuid2)),
          (2, List(uuid3, uuid4, uuid4)),
          (2, List(uuid3, uuid4, uuid4)),
        )).transact

        // Non-aggregated card-many Seq of attribute values coalesce
        _ <- Ns.i.uuidSeq.query.get.map(_.sortBy(t => (t._1, t._2.head)) ==> List(
          (1, List(uuid1, uuid2, uuid2)),
          (2, List(uuid2)),
          (2, List(uuid3, uuid4, uuid4)), // 2 rows with List(uuid3, uuid4) coalesced
        ))

        // Use `distinct` keyword to retrieve unique Seqs of Seqs
        _ <- Ns.i.a1.uuidSeq(distinct).query.get.map(_ ==> List(
          (1, Set(List(uuid1, uuid2, uuid2))),
          (2, Set(
            List(uuid2),
            List(uuid3, uuid4, uuid4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.uuidSeq(distinct).query.get.map(_ ==> List(
          Set(
            List(uuid1, uuid2, uuid2),
            List(uuid2),
            List(uuid3, uuid4, uuid4),
          )
        ))
      } yield ()
    }
  }
}