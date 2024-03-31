// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.any

import java.time.LocalDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait Aggr_LocalDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.localDateTime.insert(List(
          (1, localDateTime1),
          (2, localDateTime2),
          (2, localDateTime2),
          (2, localDateTime3),
        )).transact

        _ <- Ns.i.localDateTime.a1.query.get.map(_ ==> List(
          (1, localDateTime1),
          (2, localDateTime2), // 2 rows coalesced
          (2, localDateTime3),
        ))

        // Distinct values are returned in a Set
        _ <- Ns.i.a1.localDateTime(distinct).query.get.map(_ ==> List(
          (1, Set(localDateTime1)),
          (2, Set(localDateTime2, localDateTime3)),
        ))

        _ <- Ns.localDateTime(distinct).query.get.map(_.head ==> Set(
          localDateTime1, localDateTime2, localDateTime3
        ))
      } yield ()
    }


    "min/max" - types { implicit conn =>
      for {
        _ <- Ns.i.localDateTime.insert(
          (1, localDateTime1),
          (1, localDateTime2),
          (1, localDateTime3),
          (2, localDateTime4),
          (2, localDateTime5),
          (2, localDateTime6),
        ).transact

        _ <- Ns.localDateTime(min).query.get.map(_ ==> List(localDateTime1))
        _ <- Ns.localDateTime(max).query.get.map(_ ==> List(localDateTime6))
        _ <- Ns.localDateTime(min).localDateTime(max).query.get.map(_ ==> List((localDateTime1, localDateTime6)))

        _ <- Ns.i.a1.localDateTime(min).query.get.map(_ ==> List(
          (1, localDateTime1),
          (2, localDateTime4)
        ))

        _ <- Ns.i.a1.localDateTime(max).query.get.map(_ ==> List(
          (1, localDateTime3),
          (2, localDateTime6)
        ))

        _ <- Ns.i.a1.localDateTime(min).localDateTime(max).query.get.map(_ ==> List(
          (1, localDateTime1, localDateTime3),
          (2, localDateTime4, localDateTime6)
        ))
      } yield ()
    }

    "min/max n" - types { implicit conn =>
      for {
        _ <- Ns.i.localDateTime.insert(
          (1, localDateTime1),
          (1, localDateTime2),
          (1, localDateTime3),
          (2, localDateTime4),
          (2, localDateTime5),
          (2, localDateTime6),
          (2, localDateTime6), // (make sure grouped values coalesce)
        ).transact

        _ <- Ns.localDateTime(min(1)).query.get.map(_ ==> List(Set(localDateTime1)))
        _ <- Ns.localDateTime(min(2)).query.get.map(_ ==> List(Set(localDateTime1, localDateTime2)))

        _ <- Ns.localDateTime(max(1)).query.get.map(_ ==> List(Set(localDateTime6)))
        _ <- Ns.localDateTime(max(2)).query.get.map(_ ==> List(Set(localDateTime5, localDateTime6)))

        _ <- Ns.i.a1.localDateTime(min(2)).query.get.map(_ ==> List(
          (1, Set(localDateTime1, localDateTime2)),
          (2, Set(localDateTime4, localDateTime5))
        ))

        _ <- Ns.i.a1.localDateTime(max(2)).query.get.map(_ ==> List(
          (1, Set(localDateTime2, localDateTime3)),
          (2, Set(localDateTime5, localDateTime6))
        ))

        _ <- Ns.i.a1.localDateTime(min(2)).localDateTime(max(2)).query.get.map(_ ==> List(
          (1, Set(localDateTime1, localDateTime2), Set(localDateTime2, localDateTime3)),
          (2, Set(localDateTime4, localDateTime5), Set(localDateTime5, localDateTime6))
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      val all = Set(localDateTime1, localDateTime2, localDateTime3, localDateTime4)
      for {
        _ <- Ns.localDateTime.insert(List(localDateTime1, localDateTime2, localDateTime3)).transact
        _ <- Ns.localDateTime(sample).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.localDateTime(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.localDateTime(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count" - types { implicit conn =>
      for {
        _ <- Ns.i.localDateTime.insert(List(
          (1, localDateTime1),
          (2, localDateTime2),
          (2, localDateTime2),
          (2, localDateTime3),
        )).transact

        _ <- Ns.localDateTime(count).query.get.map(_ ==> List(4))
        _ <- Ns.i.a1.localDateTime(count).query.get.map(_ ==> List(
          (1, 1),
          (2, 3)
        ))

        _ <- Ns.localDateTime(countDistinct).query.get.map(_ ==> List(3))
        _ <- Ns.i.a1.localDateTime(countDistinct).query.get.map(_ ==> List(
          (1, 1),
          (2, 2)
        ))
      } yield ()
    }
  }
}