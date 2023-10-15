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
        _ <- Ns.i.uuids.insert(List(
          (1, Set(uuid1, uuid2)),
          (2, Set(uuid2, uuid3)),
          (2, Set(uuid3, uuid4)),
          (2, Set(uuid3, uuid4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.uuids.query.get.map(_ ==> List(
          (1, Set(uuid1, uuid2)),
          (2, Set(uuid2, uuid3, uuid4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.uuids(distinct).query.get.map(_ ==> List(
          (1, Set(Set(uuid1, uuid2))),
          (2, Set(
            Set(uuid2, uuid3),
            Set(uuid3, uuid4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.uuids(distinct).query.get.map(_ ==> List(
          Set(
            Set(uuid1, uuid2),
            Set(uuid2, uuid3),
            Set(uuid3, uuid4),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.uuids.insert(List(
          (1, Set(uuid1, uuid2)),
          (2, Set(uuid2, uuid3)),
          (2, Set(uuid3, uuid4)),
          (2, Set(uuid3, uuid4)),
        )).transact

        _ <- Ns.uuids(min).query.get.map(_ ==> List(Set(uuid1)))
        _ <- Ns.uuids(min(1)).query.get.map(_ ==> List(Set(uuid1)))
        _ <- Ns.uuids(min(2)).query.get.map(_ ==> List(Set(uuid1, uuid2)))

        _ <- Ns.i.a1.uuids(min).query.get.map(_ ==> List(
          (1, Set(uuid1)),
          (2, Set(uuid2)),
        ))
        // Same as
        _ <- Ns.i.a1.uuids(min(1)).query.get.map(_ ==> List(
          (1, Set(uuid1)),
          (2, Set(uuid2)),
        ))

        _ <- Ns.i.a1.uuids(min(2)).query.get.map(_ ==> List(
          (1, Set(uuid1, uuid2)),
          (2, Set(uuid2, uuid3)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.uuids.insert(List(
          (1, Set(uuid1, uuid2)),
          (2, Set(uuid2, uuid3)),
          (2, Set(uuid3, uuid4)),
          (2, Set(uuid3, uuid4)),
        )).transact

        _ <- Ns.uuids(max).query.get.map(_ ==> List(Set(uuid4)))
        _ <- Ns.uuids(max(1)).query.get.map(_ ==> List(Set(uuid4)))
        _ <- Ns.uuids(max(2)).query.get.map(_ ==> List(Set(uuid3, uuid4)))

        _ <- Ns.i.a1.uuids(max).query.get.map(_ ==> List(
          (1, Set(uuid2)),
          (2, Set(uuid4)),
        ))
        // Same as
        _ <- Ns.i.a1.uuids(max(1)).query.get.map(_ ==> List(
          (1, Set(uuid2)),
          (2, Set(uuid4)),
        ))

        _ <- Ns.i.a1.uuids(max(2)).query.get.map(_ ==> List(
          (1, Set(uuid1, uuid2)),
          (2, Set(uuid3, uuid4)),
        ))
      } yield ()
    }


    "rand" - types { implicit conn =>
      for {
        _ <- Ns.i.uuids.insert(List(
          (1, Set(uuid1, uuid2)),
          (2, Set(uuid2, uuid3)),
          (2, Set(uuid3, uuid4)),
          (2, Set(uuid3, uuid4)),
        )).transact
        all = Set(uuid1, uuid2, uuid3, uuid4)
        _ <- Ns.uuids(rand).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.uuids(rand(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.uuids(rand(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.uuids.insert(List(
          (1, Set(uuid1, uuid2)),
          (2, Set(uuid2, uuid3)),
          (2, Set(uuid3, uuid4)),
          (2, Set(uuid3, uuid4)),
        )).transact
        all = Set(uuid1, uuid2, uuid3, uuid4)
        _ <- Ns.uuids(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.uuids(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.uuids(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.uuids.insert(List(
          (1, Set(uuid1, uuid2)),
          (2, Set(uuid2, uuid3)),
          (2, Set(uuid3, uuid4)),
          (2, Set(uuid3, uuid4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.uuids(count).query.get.map(_ ==> List(8))
        _ <- Ns.uuids(countDistinct).query.get.map(_ ==> List(4))

        _ <- Ns.i.a1.uuids(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 6)
        ))
        _ <- Ns.i.a1.uuids(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}