// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.set.any

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSet_Short_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.shortSet.insert(List(
          (1, Set(short1, short2)),
          (2, Set(short2)),
          (2, Set(short3, short4)),
          (2, Set(short3, short4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.shortSet.query.get.map(_ ==> List(
          (1, Set(short1, short2)),
          (2, Set(short2, short3, short4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.shortSet(distinct).query.get.map(_ ==> List(
          (1, Set(Set(short1, short2))),
          (2, Set(
            Set(short2),
            Set(short3, short4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.shortSet(distinct).query.get.map(_ ==> List(
          Set(
            Set(short1, short2),
            Set(short2),
            Set(short3, short4),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.shortSet.insert(List(
          (1, Set(short1, short2)),
          (2, Set(short2)),
          (2, Set(short3, short4)),
          (2, Set(short3, short4)),
        )).transact

        // Matching values coalesced shorto one Set

        _ <- Ns.shortSet(min).query.get.map(_ ==> List(Set(short1)))
        _ <- Ns.shortSet(min(1)).query.get.map(_ ==> List(Set(short1)))
        _ <- Ns.shortSet(min(2)).query.get.map(_ ==> List(Set(short1, short2)))
        _ <- Ns.shortSet(min(3)).query.get.map(_ ==> List(Set(short1, short2, short3)))

        _ <- Ns.i.a1.shortSet(min).query.get.map(_ ==> List(
          (1, Set(short1)),
          (2, Set(short2)),
        ))
        // Same as
        _ <- Ns.i.a1.shortSet(min(1)).query.get.map(_ ==> List(
          (1, Set(short1)),
          (2, Set(short2)),
        ))

        _ <- Ns.i.a1.shortSet(min(2)).query.get.map(_ ==> List(
          (1, Set(short1, short2)),
          (2, Set(short2, short3)),
        ))

        _ <- Ns.i.a1.shortSet(min(3)).query.get.map(_ ==> List(
          (1, Set(short1, short2)),
          (2, Set(short2, short3, short4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.shortSet.insert(List(
          (1, Set(short1, short2)),
          (2, Set(short2)),
          (2, Set(short3, short4)),
          (2, Set(short3, short4)),
        )).transact

        // Matching values coalesced shorto one Set

        _ <- Ns.shortSet(max).query.get.map(_ ==> List(Set(short4)))
        _ <- Ns.shortSet(max(1)).query.get.map(_ ==> List(Set(short4)))
        _ <- Ns.shortSet(max(2)).query.get.map(_ ==> List(Set(short3, short4)))
        _ <- Ns.shortSet(max(3)).query.get.map(_ ==> List(Set(short2, short3, short4)))

        _ <- Ns.i.a1.shortSet(max).query.get.map(_ ==> List(
          (1, Set(short2)),
          (2, Set(short4)),
        ))
        // Same as
        _ <- Ns.i.a1.shortSet(max(1)).query.get.map(_ ==> List(
          (1, Set(short2)),
          (2, Set(short4)),
        ))

        _ <- Ns.i.a1.shortSet(max(2)).query.get.map(_ ==> List(
          (1, Set(short1, short2)),
          (2, Set(short3, short4)),
        ))

        _ <- Ns.i.a1.shortSet(max(3)).query.get.map(_ ==> List(
          (1, Set(short1, short2)),
          (2, Set(short2, short3, short4)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.shortSet.insert(List(
          (1, Set(short1, short2)),
          (2, Set(short2)),
          (2, Set(short3, short4)),
          (2, Set(short3, short4)),
        )).transact
        all = Set(short1, short2, short3, short4)
        _ <- Ns.shortSet(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.shortSet(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.shortSet(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.shortSet.insert(List(
          (1, Set(short1, short2)),
          (2, Set(short2)),
          (2, Set(short3, short4)),
          (2, Set(short3, short4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.shortSet(count).query.get.map(_ ==> List(7))
        _ <- Ns.shortSet(countDistinct).query.get.map(_ ==> List(4))

        _ <- Ns.i.a1.shortSet(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 5)
        ))
        _ <- Ns.i.a1.shortSet(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}