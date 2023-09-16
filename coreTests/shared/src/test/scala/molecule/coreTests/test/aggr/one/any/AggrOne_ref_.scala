// GENERATED CODE ********************************
package molecule.coreTests.test.aggr.one.any

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrOne_ref_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.ref.insert(List(
          (1, ref1),
          (2, ref2),
          (2, ref2),
          (2, ref3),
        )).transact

        _ <- Ns.i.ref.a1.query.get.map(_ ==> List(
          (1, ref1),
          (2, ref2), // 2 rows coalesced
          (2, ref3),
        ))

        // Distinct values are returned in a Set
        _ <- Ns.i.a1.ref(distinct).query.get.map(_ ==> List(
          (1, Set(ref1)),
          (2, Set(ref2, ref3)),
        ))

        _ <- Ns.ref(distinct).query.get.map(_.head ==> Set(
          ref1, ref2, ref3
        ))
      } yield ()
    }


    "min/max" - types { implicit conn =>
      for {
        _ <- Ns.i.ref.insert(
          (1, ref1),
          (1, ref2),
          (1, ref3),
          (2, ref4),
          (2, ref5),
          (2, ref6),
        ).transact

        _ <- Ns.ref(min).query.get.map(_ ==> List(ref1))
        _ <- Ns.ref(min(1)).query.get.map(_ ==> List(Set(ref1)))
        _ <- Ns.ref(min(2)).query.get.map(_ ==> List(Set(ref1, ref2)))

        _ <- Ns.ref(max).query.get.map(_ ==> List(ref6))
        _ <- Ns.ref(max(1)).query.get.map(_ ==> List(Set(ref6)))
        _ <- Ns.ref(max(2)).query.get.map(_ ==> List(Set(ref5, ref6)))

        _ <- Ns.i.ref(min(2)).query.get.map(_ ==> List(
          (1, Set(ref1, ref2)),
          (2, Set(ref4, ref5))
        ))

        _ <- Ns.i.ref(max(2)).query.get.map(_ ==> List(
          (1, Set(ref2, ref3)),
          (2, Set(ref5, ref6))
        ))

        _ <- Ns.i.ref(min(2)).ref(max(2)).query.get.map(_ ==> List(
          (1, Set(ref1, ref2), Set(ref2, ref3)),
          (2, Set(ref4, ref5), Set(ref5, ref6))
        ))
      } yield ()
    }


    "rand" - types { implicit conn =>
      for {
        _ <- Ns.ref.insert(List(ref1, ref2, ref3)).transact
        all = Set(ref1, ref2, ref3, ref4)
        _ <- Ns.ref(rand).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.ref(rand(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.ref(rand(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.ref.insert(List(ref1, ref2, ref3)).transact
        all = Set(ref1, ref2, ref3, ref4)
        _ <- Ns.ref(sample).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.ref(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.ref(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count, countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.ref.insert(List(
          (1, ref1),
          (2, ref2),
          (2, ref2),
          (2, ref3),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.ref(count).query.get.map(_ ==> List(4))
        _ <- Ns.ref(countDistinct).query.get.map(_ ==> List(3))

        _ <- Ns.i.a1.ref(count).query.get.map(_ ==> List(
          (1, 1),
          (2, 3)
        ))
        _ <- Ns.i.a1.ref(countDistinct).query.get.map(_ ==> List(
          (1, 1),
          (2, 2)
        ))
      } yield ()
    }
  }
}