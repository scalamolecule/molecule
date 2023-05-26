// GENERATED CODE ********************************
package molecule.coreTests.test.aggr.one.any

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrOne_BigDecimal_ extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.bigDecimal.insert(List(
          (1, bigDecimal1),
          (2, bigDecimal2),
          (2, bigDecimal2),
          (2, bigDecimal3),
        )).transact

        _ <- Ns.i.a1.bigDecimal.query.get.map(_.sortBy(_._2) ==> List(
          (1, bigDecimal1),
          (2, bigDecimal2), // 2 rows coalesced
          (2, bigDecimal3),
        ))

        // Distinct values are returned in a List
        _ <- Ns.i.a1.bigDecimal(distinct).query.get.map(_ ==> List(
          (1, Set(bigDecimal1)),
          (2, Set(bigDecimal2, bigDecimal3)),
        ))

        _ <- Ns.bigDecimal(distinct).query.get.map(_.head ==> Set(
          bigDecimal1, bigDecimal2, bigDecimal3
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.bigDecimal.insert(List(bigDecimal1, bigDecimal2, bigDecimal3)).transact
        _ <- Ns.bigDecimal(min).query.get.map(_ ==> List(bigDecimal1))
        _ <- Ns.bigDecimal(min(1)).query.get.map(_ ==> List(Set(bigDecimal1)))
        _ <- Ns.bigDecimal(min(2)).query.get.map(_ ==> List(Set(bigDecimal1, bigDecimal2)))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.bigDecimal.insert(List(bigDecimal1, bigDecimal2, bigDecimal3)).transact
        _ <- Ns.bigDecimal(max).query.get.map(_ ==> List(bigDecimal3))
        _ <- Ns.bigDecimal(max(1)).query.get.map(_ ==> List(Set(bigDecimal3)))
        _ <- Ns.bigDecimal(max(2)).query.get.map(_ ==> List(Set(bigDecimal3, bigDecimal2)))
      } yield ()
    }


    "rand" - types { implicit conn =>
      for {
        _ <- Ns.bigDecimal.insert(List(bigDecimal1, bigDecimal2, bigDecimal3)).transact
        all = Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4)
        _ <- Ns.bigDecimal(rand).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.bigDecimal(rand(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.bigDecimal(rand(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.bigDecimal.insert(List(bigDecimal1, bigDecimal2, bigDecimal3)).transact
        all = Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4)
        _ <- Ns.bigDecimal(sample).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.bigDecimal(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.bigDecimal(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count, countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.bigDecimal.insert(List(
          (1, bigDecimal1),
          (2, bigDecimal2),
          (2, bigDecimal2),
          (2, bigDecimal3),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.bigDecimal(count).query.get.map(_ ==> List(4))
        _ <- Ns.bigDecimal(countDistinct).query.get.map(_ ==> List(3))

        _ <- Ns.i.a1.bigDecimal(count).query.get.map(_ ==> List(
          (1, 1),
          (2, 3)
        ))
        _ <- Ns.i.a1.bigDecimal(countDistinct).query.get.map(_ ==> List(
          (1, 1),
          (2, 2)
        ))
      } yield ()
    }
  }
}