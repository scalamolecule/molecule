package molecule.coreTests.spi.aggr.set.any

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSet_Int extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.intSet.insert(List(
          (1, Set(int1, int2)),
          (2, Set(int2)),
          (2, Set(int3, int4)),
          (2, Set(int3, int4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.intSet.query.get.map(_ ==> List(
          (1, Set(int1, int2)),
          (2, Set(int2, int3, int4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.intSet(distinct).query.get.map(_ ==> List(
          (1, Set(Set(int1, int2))),
          (2, Set(
            Set(int2),
            Set(int3, int4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.intSet(distinct).query.get.map(_ ==> List(
          Set(
            Set(int1, int2),
            Set(int2),
            Set(int3, int4),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.intSet.insert(List(
          (1, Set(int1, int2)),
          (2, Set(int2)),
          (2, Set(int3, int4)),
          (2, Set(int3, int4)),
        )).transact

        // Matching values coalesced into one Set

        _ <- Ns.intSet(min).query.get.map(_ ==> List(Set(int1)))
        _ <- Ns.intSet(min(1)).query.get.map(_ ==> List(Set(int1)))
        _ <- Ns.intSet(min(2)).query.get.map(_ ==> List(Set(int1, int2)))
        _ <- Ns.intSet(min(3)).query.get.map(_ ==> List(Set(int1, int2, int3)))

        _ <- Ns.i.a1.intSet(min).query.get.map(_ ==> List(
          (1, Set(int1)),
          (2, Set(int2)),
        ))
        // Same as
        _ <- Ns.i.a1.intSet(min(1)).query.get.map(_ ==> List(
          (1, Set(int1)),
          (2, Set(int2)),
        ))

        _ <- Ns.i.a1.intSet(min(2)).query.get.map(_ ==> List(
          (1, Set(int1, int2)),
          (2, Set(int2, int3)),
        ))

        _ <- Ns.i.a1.intSet(min(3)).query.get.map(_ ==> List(
          (1, Set(int1, int2)),
          (2, Set(int2, int3, int4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.intSet.insert(List(
          (1, Set(int1, int2)),
          (2, Set(int2)),
          (2, Set(int3, int4)),
          (2, Set(int3, int4)),
        )).transact

        // Matching values coalesced into one Set

        _ <- Ns.intSet(max).query.get.map(_ ==> List(Set(int4)))
        _ <- Ns.intSet(max(1)).query.get.map(_ ==> List(Set(int4)))
        _ <- Ns.intSet(max(2)).query.get.map(_ ==> List(Set(int3, int4)))
        _ <- Ns.intSet(max(3)).query.get.map(_ ==> List(Set(int2, int3, int4)))

        _ <- Ns.i.a1.intSet(max).query.get.map(_ ==> List(
          (1, Set(int2)),
          (2, Set(int4)),
        ))
        // Same as
        _ <- Ns.i.a1.intSet(max(1)).query.get.map(_ ==> List(
          (1, Set(int2)),
          (2, Set(int4)),
        ))

        _ <- Ns.i.a1.intSet(max(2)).query.get.map(_ ==> List(
          (1, Set(int1, int2)),
          (2, Set(int3, int4)),
        ))

        _ <- Ns.i.a1.intSet(max(3)).query.get.map(_ ==> List(
          (1, Set(int1, int2)),
          (2, Set(int2, int3, int4)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.intSet.insert(List(
          (1, Set(int1, int2)),
          (2, Set(int2)),
          (2, Set(int3, int4)),
          (2, Set(int3, int4)),
        )).transact
        all = Set(int1, int2, int3, int4)
        _ <- Ns.intSet(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.intSet(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.intSet(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.intSet.insert(List(
          (1, Set(int1, int2)),
          (2, Set(int2)),
          (2, Set(int3, int4)),
          (2, Set(int3, int4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.intSet(count).query.get.map(_ ==> List(7))
        _ <- Ns.intSet(countDistinct).query.get.map(_ ==> List(4))

        _ <- Ns.i.a1.intSet(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 5)
        ))
        _ <- Ns.i.a1.intSet(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}