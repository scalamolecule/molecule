package molecule.coreTests.test.aggr.set.any

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSet_Boolean extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.booleans.insert(List(
          (1, Set(true)),
          (2, Set(false)),
          (2, Set(true, false))
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.booleans.query.get.map(_ ==> List(
          (1, Set(true)),
          (2, Set(true, false)), // 2 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of values
        _ <- Ns.i.a1.booleans(distinct).query.get.map(_ ==> List(
          (1, Set(Set(true))),
          (2, Set(
            Set(false),
            Set(true, false)
          ))
        ))

        _ <- Ns.booleans(distinct).query.get.map(_ ==> List(
          Set(
            Set(true),
            Set(false),
            Set(true, false),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.booleans.insert(List(
          (1, Set(true)),
          (2, Set(false)),
          (2, Set(true, false))
        )).transact

        _ <- Ns.booleans(min).query.get.map(_ ==> List(Set(false)))
        _ <- Ns.booleans(min(1)).query.get.map(_ ==> List(Set(false)))
        _ <- Ns.booleans(min(2)).query.get.map(_ ==> List(Set(true, false)))

        _ <- Ns.i.booleans(min).query.get.map(_ ==> List(
          (1, Set(true)),
          (2, Set(false)),
        ))
        _ <- Ns.i.booleans(min(1)).query.get.map(_ ==> List(
          (1, Set(true)),
          (2, Set(false)),
        ))
        _ <- Ns.i.booleans(min(2)).query.get.map(_ ==> List(
          (1, Set(true)),
          (2, Set(false, true)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.booleans.insert(List(
          (1, Set(true)),
          (2, Set(false)),
          (2, Set(true, false))
        )).transact

        _ <- Ns.booleans(max).query.get.map(_ ==> List(Set(true)))
        _ <- Ns.booleans(max(1)).query.get.map(_ ==> List(Set(true)))
        _ <- Ns.booleans(max(2)).query.get.map(_ ==> List(Set(true, false)))

        _ <- Ns.i.booleans(max).query.get.map(_ ==> List(
          (1, Set(true)),
          (2, Set(true)),
        ))
        _ <- Ns.i.booleans(max(1)).query.get.map(_ ==> List(
          (1, Set(true)),
          (2, Set(true)),
        ))
        _ <- Ns.i.booleans(max(2)).query.get.map(_ ==> List(
          (1, Set(true)),
          (2, Set(true, false)),
        ))
      } yield ()
    }


    "rand" - types { implicit conn =>
      for {
        _ <- Ns.i.booleans.insert(List(
          (1, Set(true)),
          (2, Set(false)),
          (2, Set(true, false))
        )).transact
        all = Set(true, false)
        _ <- Ns.booleans(rand).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.booleans(rand(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.booleans(rand(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.booleans.insert(List(
          (1, Set(true)),
          (2, Set(false)),
          (2, Set(true, false))
        )).transact
        all = Set(true, false)
        _ <- Ns.booleans(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.booleans(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.booleans(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count, countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.booleans.insert(List(
          (1, Set(true)),
          (2, Set(false)),
          (2, Set(true, false))
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(3))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.booleans(count).query.get.map(_ ==> List(4))
        _ <- Ns.booleans(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.i.a1.booleans(count).query.get.map(_ ==> List(
          (1, 1),
          (2, 3)
        ))
        _ <- Ns.i.a1.booleans(countDistinct).query.get.map(_ ==> List(
          (1, 1),
          (2, 2)
        ))
      } yield ()
    }
  }
}