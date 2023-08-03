package molecule.coreTests.test.aggr.one.any

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrOne_Int extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.int.insert(List(
          (1, int1),
          (2, int2),
          (2, int2),
          (2, int3),
        )).transact

        _ <- Ns.i.int.a1.query.get.map(_ ==> List(
          (1, int1),
          (2, int2), // 2 rows coalesced
          (2, int3),
        ))

        // Distinct values are returned in a Set
        _ <- Ns.i.a1.int(distinct).query.get.map(_ ==> List(
          (1, Set(int1)),
          (2, Set(int2, int3)),
        ))

        _ <- Ns.int(distinct).query.get.map(_.head ==> Set(
          int1, int2, int3
        ))
      } yield ()
    }


    "min/max" - types { implicit conn =>
      for {
        _ <- Ns.i.int.insert(
          (1, int1),
          (1, int2),
          (1, int3),
          (2, int4),
          (2, int5),
          (2, int6),
        ).transact

        _ <- Ns.int(min).query.get.map(_ ==> List(int1))
        _ <- Ns.int(min(1)).query.get.map(_ ==> List(Set(int1)))
        _ <- Ns.int(min(2)).query.get.map(_ ==> List(Set(int1, int2)))

        _ <- Ns.int(max).query.get.map(_ ==> List(int6))
        _ <- Ns.int(max(1)).query.get.map(_ ==> List(Set(int6)))
        _ <- Ns.int(max(2)).query.get.map(_ ==> List(Set(int5, int6)))

        _ <- Ns.i.int(min(2)).query.get.map(_ ==> List(
          (1, Set(int1, int2)),
          (2, Set(int4, int5))
        ))

        _ <- Ns.i.int(max(2)).query.get.map(_ ==> List(
          (1, Set(int2, int3)),
          (2, Set(int5, int6))
        ))

        _ <- Ns.i.int(min(2)).int(max(2)).query.get.map(_ ==> List(
          (1, Set(int1, int2), Set(int2, int3)),
          (2, Set(int4, int5), Set(int5, int6))
        ))
      } yield ()
    }


    "rand" - types { implicit conn =>
      for {
        _ <- Ns.int.insert(List(int1, int2, int3)).transact
        all = Set(int1, int2, int3, int4)
        _ <- Ns.int(rand).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.int(rand(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.int(rand(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.int.insert(List(int1, int2, int3)).transact
        all = Set(int1, int2, int3, int4)
        _ <- Ns.int(sample).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.int(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.int(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count, countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.int.insert(List(
          (1, int1),
          (2, int2),
          (2, int2),
          (2, int3),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.int(count).query.get.map(_ ==> List(4))
        _ <- Ns.int(countDistinct).query.get.map(_ ==> List(3))

        _ <- Ns.i.a1.int(count).query.get.map(_ ==> List(
          (1, 1),
          (2, 3)
        ))
        _ <- Ns.i.a1.int(countDistinct).query.get.map(_ ==> List(
          (1, 1),
          (2, 2)
        ))
      } yield ()
    }
  }
}