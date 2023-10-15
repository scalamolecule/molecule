package molecule.coreTests.spi.aggr.one.any

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrOne_Boolean extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.boolean.insert(List(
          (1, true),
          (2, false),
          (2, false),
          (2, true)
        )).transact


        _ <- Ns.i.a1.boolean.a2.query.get.map(_ ==> List(
          (1, true),
          (2, false), // 2 rows coalesced
          (2, true),
        ))

        // Distinct values are returned in a Set
        _ <- Ns.i.a1.boolean(distinct).query.get.map(_ ==> List(
          (1, Set(true)),
          (2, Set(false, true)),
        ))

        _ <- Ns.boolean(distinct).query.get.map(_.head ==> Set(false, true))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.boolean.insert(List(true, false, true)).transact
        _ <- Ns.boolean(min).query.get.map(_.head ==> false)
        _ <- Ns.boolean(min(1)).query.get.map(_.head ==> Set(false))
        _ <- Ns.boolean(min(2)).query.get.map(_.head ==> Set(false, true))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.boolean.insert(List(true, false, true)).transact
        _ <- Ns.boolean(max).query.get.map(_.head ==> true)
        _ <- Ns.boolean(max(1)).query.get.map(_.head ==> Set(true))
        _ <- Ns.boolean(max(2)).query.get.map(_.head ==> Set(true, false))
      } yield ()
    }


    "rand" - types { implicit conn =>
      for {
        _ <- Ns.boolean.insert(List(true, false, true)).transact
        all = Set(true, false)
        _ <- Ns.boolean(rand).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.boolean(rand(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.boolean(rand(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.boolean.insert(List(true, false, true)).transact
        all = Set(true, false)
        _ <- Ns.boolean(sample).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.boolean(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.boolean(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count, countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.boolean.insert(List(
          (1, true),
          (2, false),
          (2, false),
          (2, true),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.boolean(count).query.get.map(_ ==> List(4))
        _ <- Ns.boolean(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.i.a1.boolean(count).query.get.map(_ ==> List(
          (1, 1),
          (2, 3)
        ))
        _ <- Ns.i.a1.boolean(countDistinct).query.get.map(_ ==> List(
          (1, 1),
          (2, 2)
        ))
      } yield ()
    }
  }
}