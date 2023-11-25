// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.one.any

import java.time.OffsetTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.collection.immutable.Set

trait AggrOne_OffsetTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.offsetTime.insert(List(
          (1, offsetTime1),
          (2, offsetTime2),
          (2, offsetTime2),
          (2, offsetTime3),
        )).transact

        _ <- Ns.i.offsetTime.a1.query.get.map(_ ==> List(
          (1, offsetTime1),
          (2, offsetTime2), // 2 rows coalesced
          (2, offsetTime3),
        ))

        // Distinct values are returned in a Set
        _ <- Ns.i.a1.offsetTime(distinct).query.get.map(_ ==> List(
          (1, Set(offsetTime1)),
          (2, Set(offsetTime2, offsetTime3)),
        ))

        _ <- Ns.offsetTime(distinct).query.get.map(_.head ==> Set(
          offsetTime1, offsetTime2, offsetTime3
        ))
      } yield ()
    }


    "min/max" - types { implicit conn =>
      for {
        _ <- Ns.i.offsetTime.insert(
          (1, offsetTime1),
          (1, offsetTime2),
          (1, offsetTime3),
          (2, offsetTime4),
          (2, offsetTime5),
          (2, offsetTime6),
        ).transact

        _ <- Ns.offsetTime(min).query.get.map(_ ==> List(offsetTime1))
        _ <- Ns.offsetTime(max).query.get.map(_ ==> List(offsetTime6))
        _ <- Ns.offsetTime(min).offsetTime(max).query.get.map(_ ==> List((offsetTime1, offsetTime6)))

        _ <- Ns.i.a1.offsetTime(min).query.get.map(_ ==> List(
          (1, offsetTime1),
          (2, offsetTime4)
        ))

        _ <- Ns.i.a1.offsetTime(max).query.get.map(_ ==> List(
          (1, offsetTime3),
          (2, offsetTime6)
        ))

        _ <- Ns.i.a1.offsetTime(min).offsetTime(max).query.get.map(_ ==> List(
          (1, offsetTime1, offsetTime3),
          (2, offsetTime4, offsetTime6)
        ))
      } yield ()
    }

    "min/max n" - types { implicit conn =>
      for {
        _ <- Ns.i.offsetTime.insert(
          (1, offsetTime1),
          (1, offsetTime2),
          (1, offsetTime3),
          (2, offsetTime4),
          (2, offsetTime5),
          (2, offsetTime6),
          (2, offsetTime6), // (make sure grouped values coalesce)
        ).transact

        _ <- Ns.offsetTime(min(1)).query.get.map(_ ==> List(Set(offsetTime1)))
        _ <- Ns.offsetTime(min(2)).query.get.map(_ ==> List(Set(offsetTime1, offsetTime2)))

        _ <- Ns.offsetTime(max(1)).query.get.map(_ ==> List(Set(offsetTime6)))
        _ <- Ns.offsetTime(max(2)).query.get.map(_ ==> List(Set(offsetTime5, offsetTime6)))

        _ <- Ns.i.a1.offsetTime(min(2)).query.get.map(_ ==> List(
          (1, Set(offsetTime1, offsetTime2)),
          (2, Set(offsetTime4, offsetTime5))
        ))

        _ <- Ns.i.a1.offsetTime(max(2)).query.get.map(_ ==> List(
          (1, Set(offsetTime2, offsetTime3)),
          (2, Set(offsetTime5, offsetTime6))
        ))

        _ <- Ns.i.a1.offsetTime(min(2)).offsetTime(max(2)).query.get.map(_ ==> List(
          (1, Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3)),
          (2, Set(offsetTime4, offsetTime5), Set(offsetTime5, offsetTime6))
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      val all = Set(offsetTime1, offsetTime2, offsetTime3, offsetTime4)
      for {
        _ <- Ns.offsetTime.insert(List(offsetTime1, offsetTime2, offsetTime3)).transact
        _ <- Ns.offsetTime(sample).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.offsetTime(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.offsetTime(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count" - types { implicit conn =>
      for {
        _ <- Ns.i.offsetTime.insert(List(
          (1, offsetTime1),
          (2, offsetTime2),
          (2, offsetTime2),
          (2, offsetTime3),
        )).transact

        _ <- Ns.offsetTime(count).query.get.map(_ ==> List(4))
        _ <- Ns.i.a1.offsetTime(count).query.get.map(_ ==> List(
          (1, 1),
          (2, 3)
        ))

        _ <- Ns.offsetTime(countDistinct).query.get.map(_ ==> List(3))
        _ <- Ns.i.a1.offsetTime(countDistinct).query.get.map(_ ==> List(
          (1, 1),
          (2, 2)
        ))
      } yield ()
    }
  }
}