// GENERATED CODE ********************************
package molecule.coreTests.test.aggr.one.any

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrOne_UUID_ extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync =>

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


    "min" - types { implicit conn =>
      for {
        _ <- Ns.uuid.insert(List(uuid1, uuid2, uuid3)).transact
        _ <- Ns.uuid(min).query.get.map(_ ==> List(uuid1))
        _ <- Ns.uuid(min(1)).query.get.map(_ ==> List(Set(uuid1)))
        _ <- Ns.uuid(min(2)).query.get.map(_ ==> List(Set(uuid1, uuid2)))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.uuid.insert(List(uuid1, uuid2, uuid3)).transact
        _ <- Ns.uuid(max).query.get.map(_ ==> List(uuid3))
        _ <- Ns.uuid(max(1)).query.get.map(_ ==> List(Set(uuid3)))
        _ <- Ns.uuid(max(2)).query.get.map(_ ==> List(Set(uuid3, uuid2)))
      } yield ()
    }


    "rand" - types { implicit conn =>
      for {
        _ <- Ns.uuid.insert(List(uuid1, uuid2, uuid3)).transact
        all = Set(uuid1, uuid2, uuid3, uuid4)
        _ <- Ns.uuid(rand).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.uuid(rand(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.uuid(rand(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.uuid.insert(List(uuid1, uuid2, uuid3)).transact
        all = Set(uuid1, uuid2, uuid3, uuid4)
        _ <- Ns.uuid(sample).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.uuid(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.uuid(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count, countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.uuid.insert(List(
          (1, uuid1),
          (2, uuid2),
          (2, uuid2),
          (2, uuid3),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.uuid(count).query.get.map(_ ==> List(4))
        _ <- Ns.uuid(countDistinct).query.get.map(_ ==> List(3))

        _ <- Ns.i.a1.uuid(count).query.get.map(_ ==> List(
          (1, 1),
          (2, 3)
        ))
        _ <- Ns.i.a1.uuid(countDistinct).query.get.map(_ ==> List(
          (1, 1),
          (2, 2)
        ))
      } yield ()
    }
  }
}