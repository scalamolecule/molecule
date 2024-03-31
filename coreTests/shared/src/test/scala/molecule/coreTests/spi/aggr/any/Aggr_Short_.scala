// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.any

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait Aggr_Short_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.short.insert(List(
          (1, short1),
          (2, short2),
          (2, short2),
          (2, short3),
        )).transact

        _ <- Ns.i.short.a1.query.get.map(_ ==> List(
          (1, short1),
          (2, short2), // 2 rows coalesced
          (2, short3),
        ))

        // Distinct values are returned in a Set
        _ <- Ns.i.a1.short(distinct).query.get.map(_ ==> List(
          (1, Set(short1)),
          (2, Set(short2, short3)),
        ))

        _ <- Ns.short(distinct).query.get.map(_.head ==> Set(
          short1, short2, short3
        ))
      } yield ()
    }


    "min/max" - types { implicit conn =>
      for {
        _ <- Ns.i.short.insert(
          (1, short1),
          (1, short2),
          (1, short3),
          (2, short4),
          (2, short5),
          (2, short6),
        ).transact

        _ <- Ns.short(min).query.get.map(_ ==> List(short1))
        _ <- Ns.short(max).query.get.map(_ ==> List(short6))
        _ <- Ns.short(min).short(max).query.get.map(_ ==> List((short1, short6)))

        _ <- Ns.i.a1.short(min).query.get.map(_ ==> List(
          (1, short1),
          (2, short4)
        ))

        _ <- Ns.i.a1.short(max).query.get.map(_ ==> List(
          (1, short3),
          (2, short6)
        ))

        _ <- Ns.i.a1.short(min).short(max).query.get.map(_ ==> List(
          (1, short1, short3),
          (2, short4, short6)
        ))
      } yield ()
    }

    "min/max n" - types { implicit conn =>
      for {
        _ <- Ns.i.short.insert(
          (1, short1),
          (1, short2),
          (1, short3),
          (2, short4),
          (2, short5),
          (2, short6),
          (2, short6), // (make sure grouped values coalesce)
        ).transact

        _ <- Ns.short(min(1)).query.get.map(_ ==> List(Set(short1)))
        _ <- Ns.short(min(2)).query.get.map(_ ==> List(Set(short1, short2)))

        _ <- Ns.short(max(1)).query.get.map(_ ==> List(Set(short6)))
        _ <- Ns.short(max(2)).query.get.map(_ ==> List(Set(short5, short6)))

        _ <- Ns.i.a1.short(min(2)).query.get.map(_ ==> List(
          (1, Set(short1, short2)),
          (2, Set(short4, short5))
        ))

        _ <- Ns.i.a1.short(max(2)).query.get.map(_ ==> List(
          (1, Set(short2, short3)),
          (2, Set(short5, short6))
        ))

        _ <- Ns.i.a1.short(min(2)).short(max(2)).query.get.map(_ ==> List(
          (1, Set(short1, short2), Set(short2, short3)),
          (2, Set(short4, short5), Set(short5, short6))
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      val all = Set(short1, short2, short3, short4)
      for {
        _ <- Ns.short.insert(List(short1, short2, short3)).transact
        _ <- Ns.short(sample).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.short(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.short(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count" - types { implicit conn =>
      for {
        _ <- Ns.i.short.insert(List(
          (1, short1),
          (2, short2),
          (2, short2),
          (2, short3),
        )).transact

        _ <- Ns.short(count).query.get.map(_ ==> List(4))
        _ <- Ns.i.a1.short(count).query.get.map(_ ==> List(
          (1, 1),
          (2, 3)
        ))

        _ <- Ns.short(countDistinct).query.get.map(_ ==> List(3))
        _ <- Ns.i.a1.short(countDistinct).query.get.map(_ ==> List(
          (1, 1),
          (2, 2)
        ))
      } yield ()
    }
  }
}