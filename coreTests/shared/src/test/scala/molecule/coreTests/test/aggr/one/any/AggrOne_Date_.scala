// GENERATED CODE ********************************
package molecule.coreTests.test.aggr.one.any

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrOne_Date_ extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.date.insert(List(
          (1, date1),
          (2, date2),
          (2, date2),
          (2, date3),
        )).transact

        _ <- Ns.i.date.a1.query.get.map(_ ==> List(
          (1, date1),
          (2, date2), // 2 rows coalesced
          (2, date3),
        ))

        // Distinct values are returned in a Set
        _ <- Ns.i.a1.date(distinct).query.get.map(_ ==> List(
          (1, Set(date1)),
          (2, Set(date2, date3)),
        ))

        _ <- Ns.date(distinct).query.get.map(_.head ==> Set(
          date1, date2, date3
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.date.insert(List(date1, date2, date3)).transact
        _ <- Ns.date(min).query.get.map(_ ==> List(date1))
        _ <- Ns.date(min(1)).query.get.map(_ ==> List(Set(date1)))
        _ <- Ns.date(min(2)).query.get.map(_ ==> List(Set(date1, date2)))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.date.insert(List(date1, date2, date3)).transact
        _ <- Ns.date(max).query.get.map(_ ==> List(date3))
        _ <- Ns.date(max(1)).query.get.map(_ ==> List(Set(date3)))
        _ <- Ns.date(max(2)).query.get.map(_ ==> List(Set(date3, date2)))
      } yield ()
    }


    "rand" - types { implicit conn =>
      for {
        _ <- Ns.date.insert(List(date1, date2, date3)).transact
        all = Set(date1, date2, date3, date4)
        _ <- Ns.date(rand).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.date(rand(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.date(rand(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.date.insert(List(date1, date2, date3)).transact
        all = Set(date1, date2, date3, date4)
        _ <- Ns.date(sample).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.date(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.date(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count, countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.date.insert(List(
          (1, date1),
          (2, date2),
          (2, date2),
          (2, date3),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.date(count).query.get.map(_ ==> List(4))
        _ <- Ns.date(countDistinct).query.get.map(_ ==> List(3))

        _ <- Ns.i.a1.date(count).query.get.map(_ ==> List(
          (1, 1),
          (2, 3)
        ))
        _ <- Ns.i.a1.date(countDistinct).query.get.map(_ ==> List(
          (1, 1),
          (2, 2)
        ))
      } yield ()
    }
  }
}