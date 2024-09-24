// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.any

import java.util.Date
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait Aggr_Date_ extends CoreTestSuite with Api_async { spi: Spi_async =>

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


    "min/max" - types { implicit conn =>
      for {
        _ <- Ns.i.date.insert(
          (1, date1),
          (1, date2),
          (1, date3),
          (2, date4),
          (2, date5),
          (2, date6),
        ).transact

        _ <- Ns.date(min).query.get.map(_ ==> List(date1))
        _ <- Ns.date(max).query.get.map(_ ==> List(date6))
        _ <- Ns.date(min).date(max).query.get.map(_ ==> List((date1, date6)))

        _ <- Ns.i.a1.date(min).query.get.map(_ ==> List(
          (1, date1),
          (2, date4)
        ))

        _ <- Ns.i.a1.date(max).query.get.map(_ ==> List(
          (1, date3),
          (2, date6)
        ))

        _ <- Ns.i.a1.date(min).date(max).query.get.map(_ ==> List(
          (1, date1, date3),
          (2, date4, date6)
        ))
      } yield ()
    }

    "min/max n" - types { implicit conn =>
      for {
        _ <- Ns.i.date.insert(
          (1, date1),
          (1, date2),
          (1, date3),
          (2, date4),
          (2, date5),
          (2, date6),
          (2, date6), // (make sure grouped values coalesce)
        ).transact

        _ <- Ns.date(min(1)).query.get.map(_ ==> List(Set(date1)))
        _ <- Ns.date(min(2)).query.get.map(_ ==> List(Set(date1, date2)))

        _ <- Ns.date(max(1)).query.get.map(_ ==> List(Set(date6)))
        _ <- Ns.date(max(2)).query.get.map(_ ==> List(Set(date5, date6)))

        _ <- Ns.i.a1.date(min(2)).query.get.map(_ ==> List(
          (1, Set(date1, date2)),
          (2, Set(date4, date5))
        ))

        _ <- Ns.i.a1.date(max(2)).query.get.map(_ ==> List(
          (1, Set(date2, date3)),
          (2, Set(date5, date6))
        ))

        _ <- Ns.i.a1.date(min(2)).date(max(2)).query.get.map(_ ==> List(
          (1, Set(date1, date2), Set(date2, date3)),
          (2, Set(date4, date5), Set(date5, date6))
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      val all = Set(date1, date2, date3, date4)
      for {
        _ <- Ns.date.insert(List(date1, date2, date3)).transact
        _ <- Ns.date(sample).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.date(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.date(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count" - types { implicit conn =>
      for {
        _ <- Ns.i.date.insert(List(
          (1, date1),
          (2, date2),
          (2, date2),
          (2, date3),
        )).transact

        _ <- Ns.date(count).query.get.map(_ ==> List(4))
        _ <- Ns.i.a1.date(count).query.get.map(_ ==> List(
          (1, 1),
          (2, 3)
        ))

        _ <- Ns.date(countDistinct).query.get.map(_ ==> List(3))
        _ <- Ns.i.a1.date(countDistinct).query.get.map(_ ==> List(
          (1, 1),
          (2, 2)
        ))
      } yield ()
    }
  }
}