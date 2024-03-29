// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.any

import java.util.UUID
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.collection.immutable.Set

trait Aggr_UUID_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.uuid.insert(List(
          (1, uuid1),
          (2, uuid2),
          (2, uuid2),
          (2, uuid3),
        )).transact

        _ <- Ns.i.uuid.a1.query.get.map(_ ==> List(
          (1, uuid1),
          (2, uuid2), // 2 rows coalesced
          (2, uuid3),
        ))

        // Distinct values are returned in a Set
        _ <- Ns.i.a1.uuid(distinct).query.get.map(_ ==> List(
          (1, Set(uuid1)),
          (2, Set(uuid2, uuid3)),
        ))

        _ <- Ns.uuid(distinct).query.get.map(_.head ==> Set(
          uuid1, uuid2, uuid3
        ))
      } yield ()
    }


    "min/max" - types { implicit conn =>
      for {
        _ <- Ns.i.uuid.insert(
          (1, uuid1),
          (1, uuid2),
          (1, uuid3),
          (2, uuid4),
          (2, uuid5),
          (2, uuid6),
        ).transact

        _ <- Ns.uuid(min).query.get.map(_ ==> List(uuid1))
        _ <- Ns.uuid(max).query.get.map(_ ==> List(uuid6))
        _ <- Ns.uuid(min).uuid(max).query.get.map(_ ==> List((uuid1, uuid6)))

        _ <- Ns.i.a1.uuid(min).query.get.map(_ ==> List(
          (1, uuid1),
          (2, uuid4)
        ))

        _ <- Ns.i.a1.uuid(max).query.get.map(_ ==> List(
          (1, uuid3),
          (2, uuid6)
        ))

        _ <- Ns.i.a1.uuid(min).uuid(max).query.get.map(_ ==> List(
          (1, uuid1, uuid3),
          (2, uuid4, uuid6)
        ))
      } yield ()
    }

    "min/max n" - types { implicit conn =>
      for {
        _ <- Ns.i.uuid.insert(
          (1, uuid1),
          (1, uuid2),
          (1, uuid3),
          (2, uuid4),
          (2, uuid5),
          (2, uuid6),
          (2, uuid6), // (make sure grouped values coalesce)
        ).transact

        _ <- Ns.uuid(min(1)).query.get.map(_ ==> List(Set(uuid1)))
        _ <- Ns.uuid(min(2)).query.get.map(_ ==> List(Set(uuid1, uuid2)))

        _ <- Ns.uuid(max(1)).query.get.map(_ ==> List(Set(uuid6)))
        _ <- Ns.uuid(max(2)).query.get.map(_ ==> List(Set(uuid5, uuid6)))

        _ <- Ns.i.a1.uuid(min(2)).query.get.map(_ ==> List(
          (1, Set(uuid1, uuid2)),
          (2, Set(uuid4, uuid5))
        ))

        _ <- Ns.i.a1.uuid(max(2)).query.get.map(_ ==> List(
          (1, Set(uuid2, uuid3)),
          (2, Set(uuid5, uuid6))
        ))

        _ <- Ns.i.a1.uuid(min(2)).uuid(max(2)).query.get.map(_ ==> List(
          (1, Set(uuid1, uuid2), Set(uuid2, uuid3)),
          (2, Set(uuid4, uuid5), Set(uuid5, uuid6))
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      val all = Set(uuid1, uuid2, uuid3, uuid4)
      for {
        _ <- Ns.uuid.insert(List(uuid1, uuid2, uuid3)).transact
        _ <- Ns.uuid(sample).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.uuid(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.uuid(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count" - types { implicit conn =>
      for {
        _ <- Ns.i.uuid.insert(List(
          (1, uuid1),
          (2, uuid2),
          (2, uuid2),
          (2, uuid3),
        )).transact

        _ <- Ns.uuid(count).query.get.map(_ ==> List(4))
        _ <- Ns.i.a1.uuid(count).query.get.map(_ ==> List(
          (1, 1),
          (2, 3)
        ))

        _ <- Ns.uuid(countDistinct).query.get.map(_ ==> List(3))
        _ <- Ns.i.a1.uuid(countDistinct).query.get.map(_ ==> List(
          (1, 1),
          (2, 2)
        ))
      } yield ()
    }
  }
}