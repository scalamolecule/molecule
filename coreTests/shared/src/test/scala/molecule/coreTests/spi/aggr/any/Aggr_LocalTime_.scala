// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.any

import java.time.LocalTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.collection.immutable.Set

trait Aggr_LocalTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.localTime.insert(List(
          (1, localTime1),
          (2, localTime2),
          (2, localTime2),
          (2, localTime3),
        )).transact

        _ <- Ns.i.localTime.a1.query.get.map(_ ==> List(
          (1, localTime1),
          (2, localTime2), // 2 rows coalesced
          (2, localTime3),
        ))

        // Distinct values are returned in a Set
        _ <- Ns.i.a1.localTime(distinct).query.get.map(_ ==> List(
          (1, Set(localTime1)),
          (2, Set(localTime2, localTime3)),
        ))

        _ <- Ns.localTime(distinct).query.get.map(_.head ==> Set(
          localTime1, localTime2, localTime3
        ))
      } yield ()
    }


    "min/max" - types { implicit conn =>
      for {
        _ <- Ns.i.localTime.insert(
          (1, localTime1),
          (1, localTime2),
          (1, localTime3),
          (2, localTime4),
          (2, localTime5),
          (2, localTime6),
        ).transact

        _ <- Ns.localTime(min).query.get.map(_ ==> List(localTime1))
        _ <- Ns.localTime(max).query.get.map(_ ==> List(localTime6))
        _ <- Ns.localTime(min).localTime(max).query.get.map(_ ==> List((localTime1, localTime6)))

        _ <- Ns.i.a1.localTime(min).query.get.map(_ ==> List(
          (1, localTime1),
          (2, localTime4)
        ))

        _ <- Ns.i.a1.localTime(max).query.get.map(_ ==> List(
          (1, localTime3),
          (2, localTime6)
        ))

        _ <- Ns.i.a1.localTime(min).localTime(max).query.get.map(_ ==> List(
          (1, localTime1, localTime3),
          (2, localTime4, localTime6)
        ))
      } yield ()
    }

    "min/max n" - types { implicit conn =>
      for {
        _ <- Ns.i.localTime.insert(
          (1, localTime1),
          (1, localTime2),
          (1, localTime3),
          (2, localTime4),
          (2, localTime5),
          (2, localTime6),
          (2, localTime6), // (make sure grouped values coalesce)
        ).transact

        _ <- Ns.localTime(min(1)).query.get.map(_ ==> List(Set(localTime1)))
        _ <- Ns.localTime(min(2)).query.get.map(_ ==> List(Set(localTime1, localTime2)))

        _ <- Ns.localTime(max(1)).query.get.map(_ ==> List(Set(localTime6)))
        _ <- Ns.localTime(max(2)).query.get.map(_ ==> List(Set(localTime5, localTime6)))

        _ <- Ns.i.a1.localTime(min(2)).query.get.map(_ ==> List(
          (1, Set(localTime1, localTime2)),
          (2, Set(localTime4, localTime5))
        ))

        _ <- Ns.i.a1.localTime(max(2)).query.get.map(_ ==> List(
          (1, Set(localTime2, localTime3)),
          (2, Set(localTime5, localTime6))
        ))

        _ <- Ns.i.a1.localTime(min(2)).localTime(max(2)).query.get.map(_ ==> List(
          (1, Set(localTime1, localTime2), Set(localTime2, localTime3)),
          (2, Set(localTime4, localTime5), Set(localTime5, localTime6))
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      val all = Set(localTime1, localTime2, localTime3, localTime4)
      for {
        _ <- Ns.localTime.insert(List(localTime1, localTime2, localTime3)).transact
        _ <- Ns.localTime(sample).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.localTime(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.localTime(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count" - types { implicit conn =>
      for {
        _ <- Ns.i.localTime.insert(List(
          (1, localTime1),
          (2, localTime2),
          (2, localTime2),
          (2, localTime3),
        )).transact

        _ <- Ns.localTime(count).query.get.map(_ ==> List(4))
        _ <- Ns.i.a1.localTime(count).query.get.map(_ ==> List(
          (1, 1),
          (2, 3)
        ))

        _ <- Ns.localTime(countDistinct).query.get.map(_ ==> List(3))
        _ <- Ns.i.a1.localTime(countDistinct).query.get.map(_ ==> List(
          (1, 1),
          (2, 2)
        ))
      } yield ()
    }
  }
}