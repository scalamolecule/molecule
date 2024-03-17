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

    "distinct" - types { implicit conn =>
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


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.uuidSet.insert(List(
          (1, Set(uuid1, uuid2)),
          (2, Set(uuid2)),
          (2, Set(uuid3, uuid4)),
          (2, Set(uuid3, uuid4)),
        )).transact

        // Matching values coalesced uuido one Set

        _ <- Ns.uuidSet(min).query.get.map(_ ==> List(Set(uuid1)))
        _ <- Ns.uuidSet(min(1)).query.get.map(_ ==> List(Set(uuid1)))
        _ <- Ns.uuidSet(min(2)).query.get.map(_ ==> List(Set(uuid1, uuid2)))
        _ <- Ns.uuidSet(min(3)).query.get.map(_ ==> List(Set(uuid1, uuid2, uuid3)))

        _ <- Ns.i.a1.uuidSet(min).query.get.map(_ ==> List(
          (1, Set(uuid1)),
          (2, Set(uuid2)),
        ))
        // Same as
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

        // Matching values coalesced uuido one Set

        _ <- Ns.uuidSet(max).query.get.map(_ ==> List(Set(uuid4)))
        _ <- Ns.uuidSet(max(1)).query.get.map(_ ==> List(Set(uuid4)))
        _ <- Ns.uuidSet(max(2)).query.get.map(_ ==> List(Set(uuid3, uuid4)))
        _ <- Ns.uuidSet(max(3)).query.get.map(_ ==> List(Set(uuid2, uuid3, uuid4)))

        _ <- Ns.i.a1.uuidSet(max).query.get.map(_ ==> List(
          (1, Set(uuid2)),
          (2, Set(uuid4)),
        ))
        // Same as
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
        _ <- Ns.uuidSet(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.uuidSet(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.uuidSet(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


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

        _ <- Ns.i.a1.uuidSet(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 5)
        ))
        _ <- Ns.i.a1.uuidSet(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}