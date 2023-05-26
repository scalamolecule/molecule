// GENERATED CODE ********************************
package molecule.coreTests.test.aggr.set.any

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSet_Short_ extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.shorts.insert(List(
          (1, Set(short1, short2)),
          (2, Set(short2, short3)),
          (2, Set(short3, short4)),
          (2, Set(short3, short4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.shorts.query.get.map(_ ==> List(
          (1, Set(short1, short2)),
          (2, Set(short2, short3, short4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of values
        _ <- Ns.i.a1.shorts(distinct).query.get.map(_ ==> List(
          (1, Set(Set(short1, short2))),
          (2, Set(
            Set(short2, short3),
            Set(short3, short4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.shorts(distinct).query.get.map(_ ==> List(
          Set(
            Set(short1, short2),
            Set(short2, short3),
            Set(short3, short4),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.shorts.insert(List(
          (1, Set(short1, short2)),
          (2, Set(short2, short3)),
          (2, Set(short3, short4)),
          (2, Set(short3, short4)),
        )).transact

        _ <- Ns.shorts(min).query.get.map(_ ==> List(Set(short1)))
        _ <- Ns.shorts(min(1)).query.get.map(_ ==> List(Set(short1)))
        _ <- Ns.shorts(min(2)).query.get.map(_ ==> List(Set(short1, short2)))

        _ <- Ns.i.shorts(min).query.get.map(_ ==> List(
          (1, Set(short1)),
          (2, Set(short2)),
        ))
        // Same as
        _ <- Ns.i.shorts(min(1)).query.get.map(_ ==> List(
          (1, Set(short1)),
          (2, Set(short2)),
        ))

        _ <- Ns.i.shorts(min(2)).query.get.map(_ ==> List(
          (1, Set(short1, short2)),
          (2, Set(short2, short3)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.shorts.insert(List(
          (1, Set(short1, short2)),
          (2, Set(short2, short3)),
          (2, Set(short3, short4)),
          (2, Set(short3, short4)),
        )).transact

        _ <- Ns.shorts(max).query.get.map(_ ==> List(Set(short4)))
        _ <- Ns.shorts(max(1)).query.get.map(_ ==> List(Set(short4)))
        _ <- Ns.shorts(max(2)).query.get.map(_ ==> List(Set(short3, short4)))

        _ <- Ns.i.shorts(max).query.get.map(_ ==> List(
          (1, Set(short2)),
          (2, Set(short4)),
        ))
        // Same as
        _ <- Ns.i.shorts(max(1)).query.get.map(_ ==> List(
          (1, Set(short2)),
          (2, Set(short4)),
        ))

        _ <- Ns.i.shorts(max(2)).query.get.map(_ ==> List(
          (1, Set(short1, short2)),
          (2, Set(short3, short4)),
        ))
      } yield ()
    }


    "rand" - types { implicit conn =>
      for {
        _ <- Ns.i.shorts.insert(List(
          (1, Set(short1, short2)),
          (2, Set(short2, short3)),
          (2, Set(short3, short4)),
          (2, Set(short3, short4)),
        )).transact
        all = Set(short1, short2, short3, short4)
        _ <- Ns.shorts(rand).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.shorts(rand(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.shorts(rand(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.shorts.insert(List(
          (1, Set(short1, short2)),
          (2, Set(short2, short3)),
          (2, Set(short3, short4)),
          (2, Set(short3, short4)),
        )).transact
        all = Set(short1, short2, short3, short4)
        _ <- Ns.shorts(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.shorts(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.shorts(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.shorts.insert(List(
          (1, Set(short1, short2)),
          (2, Set(short2, short3)),
          (2, Set(short3, short4)),
          (2, Set(short3, short4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.shorts(count).query.get.map(_ ==> List(8))
        _ <- Ns.shorts(countDistinct).query.get.map(_ ==> List(4))

        _ <- Ns.i.a1.shorts(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 6)
        ))
        _ <- Ns.i.a1.shorts(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}