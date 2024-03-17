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
        _ <- Ns.i.localDateSet.insert(List(
          (1, Set(localDate1, localDate2)),
          (2, Set(localDate2)),
          (2, Set(localDate3, localDate4)),
          (2, Set(localDate3, localDate4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.localDateSet.query.get.map(_ ==> List(
          (1, Set(localDate1, localDate2)),
          (2, Set(localDate2, localDate3, localDate4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.localDateSet(distinct).query.get.map(_ ==> List(
          (1, Set(Set(localDate1, localDate2))),
          (2, Set(
            Set(localDate2),
            Set(localDate3, localDate4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.localDateSet(distinct).query.get.map(_ ==> List(
          Set(
            Set(localDate1, localDate2),
            Set(localDate2),
            Set(localDate3, localDate4),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.localDateSet.insert(List(
          (1, Set(localDate1, localDate2)),
          (2, Set(localDate2)),
          (2, Set(localDate3, localDate4)),
          (2, Set(localDate3, localDate4)),
        )).transact

        // Matching values coalesced localDateo one Set

        _ <- Ns.localDateSet(min).query.get.map(_ ==> List(Set(localDate1)))
        _ <- Ns.localDateSet(min(1)).query.get.map(_ ==> List(Set(localDate1)))
        _ <- Ns.localDateSet(min(2)).query.get.map(_ ==> List(Set(localDate1, localDate2)))
        _ <- Ns.localDateSet(min(3)).query.get.map(_ ==> List(Set(localDate1, localDate2, localDate3)))

        _ <- Ns.i.a1.localDateSet(min).query.get.map(_ ==> List(
          (1, Set(localDate1)),
          (2, Set(localDate2)),
        ))
        // Same as
        _ <- Ns.i.a1.localDateSet(min(1)).query.get.map(_ ==> List(
          (1, Set(localDate1)),
          (2, Set(localDate2)),
        ))

        _ <- Ns.i.a1.localDateSet(min(2)).query.get.map(_ ==> List(
          (1, Set(localDate1, localDate2)),
          (2, Set(localDate2, localDate3)),
        ))

        _ <- Ns.i.a1.localDateSet(min(3)).query.get.map(_ ==> List(
          (1, Set(localDate1, localDate2)),
          (2, Set(localDate2, localDate3, localDate4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.localDateSet.insert(List(
          (1, Set(localDate1, localDate2)),
          (2, Set(localDate2)),
          (2, Set(localDate3, localDate4)),
          (2, Set(localDate3, localDate4)),
        )).transact

        // Matching values coalesced localDateo one Set

        _ <- Ns.localDateSet(max).query.get.map(_ ==> List(Set(localDate4)))
        _ <- Ns.localDateSet(max(1)).query.get.map(_ ==> List(Set(localDate4)))
        _ <- Ns.localDateSet(max(2)).query.get.map(_ ==> List(Set(localDate3, localDate4)))
        _ <- Ns.localDateSet(max(3)).query.get.map(_ ==> List(Set(localDate2, localDate3, localDate4)))

        _ <- Ns.i.a1.localDateSet(max).query.get.map(_ ==> List(
          (1, Set(localDate2)),
          (2, Set(localDate4)),
        ))
        // Same as
        _ <- Ns.i.a1.localDateSet(max(1)).query.get.map(_ ==> List(
          (1, Set(localDate2)),
          (2, Set(localDate4)),
        ))

        _ <- Ns.i.a1.localDateSet(max(2)).query.get.map(_ ==> List(
          (1, Set(localDate1, localDate2)),
          (2, Set(localDate3, localDate4)),
        ))

        _ <- Ns.i.a1.localDateSet(max(3)).query.get.map(_ ==> List(
          (1, Set(localDate1, localDate2)),
          (2, Set(localDate2, localDate3, localDate4)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.localDateSet.insert(List(
          (1, Set(localDate1, localDate2)),
          (2, Set(localDate2)),
          (2, Set(localDate3, localDate4)),
          (2, Set(localDate3, localDate4)),
        )).transact
        all = Set(localDate1, localDate2, localDate3, localDate4)
        _ <- Ns.localDateSet(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.localDateSet(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.localDateSet(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.localDateSet.insert(List(
          (1, Set(localDate1, localDate2)),
          (2, Set(localDate2)),
          (2, Set(localDate3, localDate4)),
          (2, Set(localDate3, localDate4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.localDateSet(count).query.get.map(_ ==> List(7))
        _ <- Ns.localDateSet(countDistinct).query.get.map(_ ==> List(4))

        _ <- Ns.i.a1.localDateSet(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 5)
        ))
        _ <- Ns.i.a1.localDateSet(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}