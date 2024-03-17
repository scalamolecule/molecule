// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.set.any

import java.util.Date
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
        _ <- Ns.i.dateSet.insert(List(
          (1, Set(date1, date2)),
          (2, Set(date2)),
          (2, Set(date3, date4)),
          (2, Set(date3, date4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.dateSet.query.get.map(_ ==> List(
          (1, Set(date1, date2)),
          (2, Set(date2, date3, date4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.dateSet(distinct).query.get.map(_ ==> List(
          (1, Set(Set(date1, date2))),
          (2, Set(
            Set(date2),
            Set(date3, date4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.dateSet(distinct).query.get.map(_ ==> List(
          Set(
            Set(date1, date2),
            Set(date2),
            Set(date3, date4),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.dateSet.insert(List(
          (1, Set(date1, date2)),
          (2, Set(date2)),
          (2, Set(date3, date4)),
          (2, Set(date3, date4)),
        )).transact

        // Matching values coalesced dateo one Set

        _ <- Ns.dateSet(min).query.get.map(_ ==> List(Set(date1)))
        _ <- Ns.dateSet(min(1)).query.get.map(_ ==> List(Set(date1)))
        _ <- Ns.dateSet(min(2)).query.get.map(_ ==> List(Set(date1, date2)))
        _ <- Ns.dateSet(min(3)).query.get.map(_ ==> List(Set(date1, date2, date3)))

        _ <- Ns.i.a1.dateSet(min).query.get.map(_ ==> List(
          (1, Set(date1)),
          (2, Set(date2)),
        ))
        // Same as
        _ <- Ns.i.a1.dateSet(min(1)).query.get.map(_ ==> List(
          (1, Set(date1)),
          (2, Set(date2)),
        ))

        _ <- Ns.i.a1.dateSet(min(2)).query.get.map(_ ==> List(
          (1, Set(date1, date2)),
          (2, Set(date2, date3)),
        ))

        _ <- Ns.i.a1.dateSet(min(3)).query.get.map(_ ==> List(
          (1, Set(date1, date2)),
          (2, Set(date2, date3, date4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.dateSet.insert(List(
          (1, Set(date1, date2)),
          (2, Set(date2)),
          (2, Set(date3, date4)),
          (2, Set(date3, date4)),
        )).transact

        // Matching values coalesced dateo one Set

        _ <- Ns.dateSet(max).query.get.map(_ ==> List(Set(date4)))
        _ <- Ns.dateSet(max(1)).query.get.map(_ ==> List(Set(date4)))
        _ <- Ns.dateSet(max(2)).query.get.map(_ ==> List(Set(date3, date4)))
        _ <- Ns.dateSet(max(3)).query.get.map(_ ==> List(Set(date2, date3, date4)))

        _ <- Ns.i.a1.dateSet(max).query.get.map(_ ==> List(
          (1, Set(date2)),
          (2, Set(date4)),
        ))
        // Same as
        _ <- Ns.i.a1.dateSet(max(1)).query.get.map(_ ==> List(
          (1, Set(date2)),
          (2, Set(date4)),
        ))

        _ <- Ns.i.a1.dateSet(max(2)).query.get.map(_ ==> List(
          (1, Set(date1, date2)),
          (2, Set(date3, date4)),
        ))

        _ <- Ns.i.a1.dateSet(max(3)).query.get.map(_ ==> List(
          (1, Set(date1, date2)),
          (2, Set(date2, date3, date4)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.dateSet.insert(List(
          (1, Set(date1, date2)),
          (2, Set(date2)),
          (2, Set(date3, date4)),
          (2, Set(date3, date4)),
        )).transact
        all = Set(date1, date2, date3, date4)
        _ <- Ns.dateSet(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.dateSet(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.dateSet(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.dateSet.insert(List(
          (1, Set(date1, date2)),
          (2, Set(date2)),
          (2, Set(date3, date4)),
          (2, Set(date3, date4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.dateSet(count).query.get.map(_ ==> List(7))
        _ <- Ns.dateSet(countDistinct).query.get.map(_ ==> List(4))

        _ <- Ns.i.a1.dateSet(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 5)
        ))
        _ <- Ns.i.a1.dateSet(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}