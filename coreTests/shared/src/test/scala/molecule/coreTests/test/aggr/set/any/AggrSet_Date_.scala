// GENERATED CODE ********************************
package molecule.coreTests.test.aggr.set.any

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSet_Date_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.dates.insert(List(
          (1, Set(date1, date2)),
          (2, Set(date2, date3)),
          (2, Set(date3, date4)),
          (2, Set(date3, date4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.dates.query.get.map(_ ==> List(
          (1, Set(date1, date2)),
          (2, Set(date2, date3, date4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of values
        _ <- Ns.i.a1.dates(distinct).query.get.map(_ ==> List(
          (1, Set(Set(date1, date2))),
          (2, Set(
            Set(date2, date3),
            Set(date3, date4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.dates(distinct).query.get.map(_ ==> List(
          Set(
            Set(date1, date2),
            Set(date2, date3),
            Set(date3, date4),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.dates.insert(List(
          (1, Set(date1, date2)),
          (2, Set(date2, date3)),
          (2, Set(date3, date4)),
          (2, Set(date3, date4)),
        )).transact

        _ <- Ns.dates(min).query.get.map(_ ==> List(Set(date1)))
        _ <- Ns.dates(min(1)).query.get.map(_ ==> List(Set(date1)))
        _ <- Ns.dates(min(2)).query.get.map(_ ==> List(Set(date1, date2)))

        _ <- Ns.i.dates(min).query.get.map(_ ==> List(
          (1, Set(date1)),
          (2, Set(date2)),
        ))
        // Same as
        _ <- Ns.i.dates(min(1)).query.get.map(_ ==> List(
          (1, Set(date1)),
          (2, Set(date2)),
        ))

        _ <- Ns.i.dates(min(2)).query.get.map(_ ==> List(
          (1, Set(date1, date2)),
          (2, Set(date2, date3)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.dates.insert(List(
          (1, Set(date1, date2)),
          (2, Set(date2, date3)),
          (2, Set(date3, date4)),
          (2, Set(date3, date4)),
        )).transact

        _ <- Ns.dates(max).query.get.map(_ ==> List(Set(date4)))
        _ <- Ns.dates(max(1)).query.get.map(_ ==> List(Set(date4)))
        _ <- Ns.dates(max(2)).query.get.map(_ ==> List(Set(date3, date4)))

        _ <- Ns.i.dates(max).query.get.map(_ ==> List(
          (1, Set(date2)),
          (2, Set(date4)),
        ))
        // Same as
        _ <- Ns.i.dates(max(1)).query.get.map(_ ==> List(
          (1, Set(date2)),
          (2, Set(date4)),
        ))

        _ <- Ns.i.dates(max(2)).query.get.map(_ ==> List(
          (1, Set(date1, date2)),
          (2, Set(date3, date4)),
        ))
      } yield ()
    }


    "rand" - types { implicit conn =>
      for {
        _ <- Ns.i.dates.insert(List(
          (1, Set(date1, date2)),
          (2, Set(date2, date3)),
          (2, Set(date3, date4)),
          (2, Set(date3, date4)),
        )).transact
        all = Set(date1, date2, date3, date4)
        _ <- Ns.dates(rand).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.dates(rand(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.dates(rand(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.dates.insert(List(
          (1, Set(date1, date2)),
          (2, Set(date2, date3)),
          (2, Set(date3, date4)),
          (2, Set(date3, date4)),
        )).transact
        all = Set(date1, date2, date3, date4)
        _ <- Ns.dates(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.dates(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.dates(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.dates.insert(List(
          (1, Set(date1, date2)),
          (2, Set(date2, date3)),
          (2, Set(date3, date4)),
          (2, Set(date3, date4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.dates(count).query.get.map(_ ==> List(8))
        _ <- Ns.dates(countDistinct).query.get.map(_ ==> List(4))

        _ <- Ns.i.a1.dates(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 6)
        ))
        _ <- Ns.i.a1.dates(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}