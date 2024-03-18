// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.set.any

import java.util.UUID
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSet_UUID_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.uuidSet.insert(List(
          (1, Set(uuid1, uuid2)),
          (2, Set(uuid2)),
          (2, Set(uuid3, uuid4)),
          (2, Set(uuid3, uuid4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.uuidSet(count).query.get.map(_ ==> List(7))
        _ <- Ns.uuidSet(countDistinct).query.get.map(_ ==> List(4))

        // Sort by counts

        _ <- Ns.i.uuidSet(count).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 5),
        ))
        _ <- Ns.i.uuidSet(count).d1.query.get.map(_ ==> List(
          (2, 5),
          (1, 2),
        ))

        _ <- Ns.i.uuidSet(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- Ns.i.uuidSet(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.uuidSet.insert(List(
          (1, Set(uuid1, uuid2)),
          (2, Set(uuid2)),
          (2, Set(uuid3, uuid4)),
          (2, Set(uuid3, uuid4)),
        )).transact

        // Minimum value

        _ <- Ns.uuidSet(min).query.get.map(_ ==> List(uuid1))

        // Sort by minimum value
        _ <- Ns.i.uuidSet(min).a1.query.get.map(_ ==> List(
          (1, uuid1),
          (2, uuid2),
        ))
        _ <- Ns.i.uuidSet(min).d1.query.get.map(_ ==> List(
          (2, uuid2),
          (1, uuid1),
        ))

        // Set of minimum values

        _ <- Ns.uuidSet(min(1)).query.get.map(_ ==> List(Set(uuid1)))
        _ <- Ns.uuidSet(min(2)).query.get.map(_ ==> List(Set(uuid1, uuid2)))
        _ <- Ns.uuidSet(min(3)).query.get.map(_ ==> List(Set(uuid1, uuid2, uuid3)))

        _ <- Ns.i.a1.uuidSet(min(1)).query.get.map(_ ==> List(
          (1, Set(uuid1)),
          (2, Set(uuid2)),
        ))
        _ <- Ns.i.a1.uuidSet(min(2)).query.get.map(_ ==> List(
          (1, Set(uuid1, uuid2)),
          (2, Set(uuid2, uuid3)),
        ))
        _ <- Ns.i.a1.uuidSet(min(3)).query.get.map(_ ==> List(
          (1, Set(uuid1, uuid2)),
          (2, Set(uuid2, uuid3, uuid4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.uuidSet.insert(List(
          (1, Set(uuid1, uuid2)),
          (2, Set(uuid2)),
          (2, Set(uuid3, uuid4)),
          (2, Set(uuid3, uuid4)),
        )).transact

        // Maximum value

        _ <- Ns.uuidSet(max).query.get.map(_ ==> List(uuid4))

        // Sort by maximum value
        _ <- Ns.i.uuidSet(max).a1.query.get.map(_ ==> List(
          (1, uuid2),
          (2, uuid4),
        ))
        _ <- Ns.i.uuidSet(max).d1.query.get.map(_ ==> List(
          (2, uuid4),
          (1, uuid2),
        ))

        // Set of maximum values

        _ <- Ns.uuidSet(max(1)).query.get.map(_ ==> List(Set(uuid4)))
        _ <- Ns.uuidSet(max(2)).query.get.map(_ ==> List(Set(uuid3, uuid4)))
        _ <- Ns.uuidSet(max(3)).query.get.map(_ ==> List(Set(uuid2, uuid3, uuid4)))

        _ <- Ns.i.a1.uuidSet(max(1)).query.get.map(_ ==> List(
          (1, Set(uuid2)),
          (2, Set(uuid4)),
        ))
        _ <- Ns.i.a1.uuidSet(max(2)).query.get.map(_ ==> List(
          (1, Set(uuid1, uuid2)),
          (2, Set(uuid3, uuid4)),
        ))
        _ <- Ns.i.a1.uuidSet(max(3)).query.get.map(_ ==> List(
          (1, Set(uuid1, uuid2)),
          (2, Set(uuid2, uuid3, uuid4)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.uuidSet.insert(List(
          (1, Set(uuid1, uuid2)),
          (2, Set(uuid2)),
          (2, Set(uuid3, uuid4)),
          (2, Set(uuid3, uuid4)),
        )).transact

        all = Set(uuid1, uuid2, uuid3, uuid4)

        _ <- Ns.uuidSet(sample).query.get.map { rows =>
          val singleSampleValue: UUID = rows.head
          all.contains(singleSampleValue) ==> true
        }

        // Sort by sample value (not checked here)
        _ <- Ns.i.uuidSet(sample).a1.query.get
        _ <- Ns.i.uuidSet(sample).d1.query.get

        // Multiple samples
        _ <- Ns.uuidSet(sample(1)).query.get.map { rows =>
          val sampleSetWithOneValue: Set[UUID] = rows.head
          all.contains(sampleSetWithOneValue.head) ==> true
        }
        _ <- Ns.uuidSet(sample(2)).query.get.map { rows =>
          val sampleSetWithTwoValues: Set[UUID] = rows.head
          all.intersect(sampleSetWithTwoValues).nonEmpty ==> true
        }
      } yield ()
    }


    "distinct sets of values" - types { implicit conn =>
      for {
        _ <- Ns.i.uuidSet.insert(List(
          (1, Set(uuid1, uuid2)),
          (2, Set(uuid2)),
          (2, Set(uuid3, uuid4)),
          (2, Set(uuid3, uuid4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.uuidSet.query.get.map(_ ==> List(
          (1, Set(uuid1, uuid2)),
          (2, Set(uuid2, uuid3, uuid4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.uuidSet(distinct).query.get.map(_ ==> List(
          (1, Set(Set(uuid1, uuid2))),
          (2, Set(
            Set(uuid2),
            Set(uuid3, uuid4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.uuidSet(distinct).query.get.map(_ ==> List(
          Set(
            Set(uuid1, uuid2),
            Set(uuid2),
            Set(uuid3, uuid4),
          )
        ))
      } yield ()
    }
  }
}