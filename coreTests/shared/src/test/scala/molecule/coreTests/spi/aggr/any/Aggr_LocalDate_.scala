// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.any

import java.time.LocalDate
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait Aggr_LocalDate_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.localDate.insert(List(
          (1, localDate1),
          (2, localDate2),
          (2, localDate2),
          (2, localDate3),
        )).transact

        _ <- Ns.i.localDate.a1.query.get.map(_ ==> List(
          (1, localDate1),
          (2, localDate2), // 2 rows coalesced
          (2, localDate3),
        ))

        // Distinct values are returned in a Set
        _ <- Ns.i.a1.localDate(distinct).query.get.map(_ ==> List(
          (1, Set(localDate1)),
          (2, Set(localDate2, localDate3)),
        ))

        _ <- Ns.localDate(distinct).query.get.map(_.head ==> Set(
          localDate1, localDate2, localDate3
        ))
      } yield ()
    }


    "min/max" - types { implicit conn =>
      for {
        _ <- Ns.i.localDate.insert(
          (1, localDate1),
          (1, localDate2),
          (1, localDate3),
          (2, localDate4),
          (2, localDate5),
          (2, localDate6),
        ).transact

        _ <- Ns.localDate(min).query.get.map(_ ==> List(localDate1))
        _ <- Ns.localDate(max).query.get.map(_ ==> List(localDate6))
        _ <- Ns.localDate(min).localDate(max).query.get.map(_ ==> List((localDate1, localDate6)))

        _ <- Ns.i.a1.localDate(min).query.get.map(_ ==> List(
          (1, localDate1),
          (2, localDate4)
        ))

        _ <- Ns.i.a1.localDate(max).query.get.map(_ ==> List(
          (1, localDate3),
          (2, localDate6)
        ))

        _ <- Ns.i.a1.localDate(min).localDate(max).query.get.map(_ ==> List(
          (1, localDate1, localDate3),
          (2, localDate4, localDate6)
        ))
      } yield ()
    }

    "min/max n" - types { implicit conn =>
      for {
        _ <- Ns.i.localDate.insert(
          (1, localDate1),
          (1, localDate2),
          (1, localDate3),
          (2, localDate4),
          (2, localDate5),
          (2, localDate6),
          (2, localDate6), // (make sure grouped values coalesce)
        ).transact

        _ <- Ns.localDate(min(1)).query.get.map(_ ==> List(Set(localDate1)))
        _ <- Ns.localDate(min(2)).query.get.map(_ ==> List(Set(localDate1, localDate2)))

        _ <- Ns.localDate(max(1)).query.get.map(_ ==> List(Set(localDate6)))
        _ <- Ns.localDate(max(2)).query.get.map(_ ==> List(Set(localDate5, localDate6)))

        _ <- Ns.i.a1.localDate(min(2)).query.get.map(_ ==> List(
          (1, Set(localDate1, localDate2)),
          (2, Set(localDate4, localDate5))
        ))

        _ <- Ns.i.a1.localDate(max(2)).query.get.map(_ ==> List(
          (1, Set(localDate2, localDate3)),
          (2, Set(localDate5, localDate6))
        ))

        _ <- Ns.i.a1.localDate(min(2)).localDate(max(2)).query.get.map(_ ==> List(
          (1, Set(localDate1, localDate2), Set(localDate2, localDate3)),
          (2, Set(localDate4, localDate5), Set(localDate5, localDate6))
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      val all = Set(localDate1, localDate2, localDate3, localDate4)
      for {
        _ <- Ns.localDate.insert(List(localDate1, localDate2, localDate3)).transact
        _ <- Ns.localDate(sample).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.localDate(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.localDate(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count" - types { implicit conn =>
      for {
        _ <- Ns.i.localDate.insert(List(
          (1, localDate1),
          (2, localDate2),
          (2, localDate2),
          (2, localDate3),
        )).transact

        _ <- Ns.localDate(count).query.get.map(_ ==> List(4))
        _ <- Ns.i.a1.localDate(count).query.get.map(_ ==> List(
          (1, 1),
          (2, 3)
        ))

        _ <- Ns.localDate(countDistinct).query.get.map(_ ==> List(3))
        _ <- Ns.i.a1.localDate(countDistinct).query.get.map(_ ==> List(
          (1, 1),
          (2, 2)
        ))
      } yield ()
    }
  }
}