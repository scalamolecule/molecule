// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.set.any

import java.time.LocalDate
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSet_LocalDate_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.localDates.insert(List(
          (1, Set(localDate1, localDate2)),
          (2, Set(localDate2, localDate3)),
          (2, Set(localDate3, localDate4)),
          (2, Set(localDate3, localDate4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.localDates.query.get.map(_ ==> List(
          (1, Set(localDate1, localDate2)),
          (2, Set(localDate2, localDate3, localDate4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.localDates(distinct).query.get.map(_ ==> List(
          (1, Set(Set(localDate1, localDate2))),
          (2, Set(
            Set(localDate2, localDate3),
            Set(localDate3, localDate4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.localDates(distinct).query.get.map(_ ==> List(
          Set(
            Set(localDate1, localDate2),
            Set(localDate2, localDate3),
            Set(localDate3, localDate4),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.localDates.insert(List(
          (1, Set(localDate1, localDate2)),
          (2, Set(localDate2, localDate3)),
          (2, Set(localDate3, localDate4)),
          (2, Set(localDate3, localDate4)),
        )).transact

        _ <- Ns.localDates(min).query.get.map(_ ==> List(Set(localDate1)))
        _ <- Ns.localDates(min(1)).query.get.map(_ ==> List(Set(localDate1)))
        _ <- Ns.localDates(min(2)).query.get.map(_ ==> List(Set(localDate1, localDate2)))

        _ <- Ns.i.a1.localDates(min).query.get.map(_ ==> List(
          (1, Set(localDate1)),
          (2, Set(localDate2)),
        ))
        // Same as
        _ <- Ns.i.a1.localDates(min(1)).query.get.map(_ ==> List(
          (1, Set(localDate1)),
          (2, Set(localDate2)),
        ))

        _ <- Ns.i.a1.localDates(min(2)).query.get.map(_ ==> List(
          (1, Set(localDate1, localDate2)),
          (2, Set(localDate2, localDate3)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.localDates.insert(List(
          (1, Set(localDate1, localDate2)),
          (2, Set(localDate2, localDate3)),
          (2, Set(localDate3, localDate4)),
          (2, Set(localDate3, localDate4)),
        )).transact

        _ <- Ns.localDates(max).query.get.map(_ ==> List(Set(localDate4)))
        _ <- Ns.localDates(max(1)).query.get.map(_ ==> List(Set(localDate4)))
        _ <- Ns.localDates(max(2)).query.get.map(_ ==> List(Set(localDate3, localDate4)))

        _ <- Ns.i.a1.localDates(max).query.get.map(_ ==> List(
          (1, Set(localDate2)),
          (2, Set(localDate4)),
        ))
        // Same as
        _ <- Ns.i.a1.localDates(max(1)).query.get.map(_ ==> List(
          (1, Set(localDate2)),
          (2, Set(localDate4)),
        ))

        _ <- Ns.i.a1.localDates(max(2)).query.get.map(_ ==> List(
          (1, Set(localDate1, localDate2)),
          (2, Set(localDate3, localDate4)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.localDates.insert(List(
          (1, Set(localDate1, localDate2)),
          (2, Set(localDate2, localDate3)),
          (2, Set(localDate3, localDate4)),
          (2, Set(localDate3, localDate4)),
        )).transact
        all = Set(localDate1, localDate2, localDate3, localDate4)
        _ <- Ns.localDates(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.localDates(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.localDates(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.localDates.insert(List(
          (1, Set(localDate1, localDate2)),
          (2, Set(localDate2, localDate3)),
          (2, Set(localDate3, localDate4)),
          (2, Set(localDate3, localDate4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.localDates(count).query.get.map(_ ==> List(8))
        _ <- Ns.localDates(countDistinct).query.get.map(_ ==> List(4))

        _ <- Ns.i.a1.localDates(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 6)
        ))
        _ <- Ns.i.a1.localDates(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}