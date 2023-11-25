// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.one.any

import java.time.Duration
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.collection.immutable.Set

trait AggrOne_Duration_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.duration.insert(List(
          (1, duration1),
          (2, duration2),
          (2, duration2),
          (2, duration3),
        )).transact

        _ <- Ns.i.duration.a1.query.get.map(_ ==> List(
          (1, duration1),
          (2, duration2), // 2 rows coalesced
          (2, duration3),
        ))

        // Distinct values are returned in a Set
        _ <- Ns.i.a1.duration(distinct).query.get.map(_ ==> List(
          (1, Set(duration1)),
          (2, Set(duration2, duration3)),
        ))

        _ <- Ns.duration(distinct).query.get.map(_.head ==> Set(
          duration1, duration2, duration3
        ))
      } yield ()
    }


    "min/max" - types { implicit conn =>
      for {
        _ <- Ns.i.duration.insert(
          (1, duration1),
          (1, duration2),
          (1, duration3),
          (2, duration4),
          (2, duration5),
          (2, duration6),
        ).transact

        _ <- Ns.duration(min).query.get.map(_ ==> List(duration1))
        _ <- Ns.duration(max).query.get.map(_ ==> List(duration6))
        _ <- Ns.duration(min).duration(max).query.get.map(_ ==> List((duration1, duration6)))

        _ <- Ns.i.a1.duration(min).query.get.map(_ ==> List(
          (1, duration1),
          (2, duration4)
        ))

        _ <- Ns.i.a1.duration(max).query.get.map(_ ==> List(
          (1, duration3),
          (2, duration6)
        ))

        _ <- Ns.i.a1.duration(min).duration(max).query.get.map(_ ==> List(
          (1, duration1, duration3),
          (2, duration4, duration6)
        ))
      } yield ()
    }

    "min/max n" - types { implicit conn =>
      for {
        _ <- Ns.i.duration.insert(
          (1, duration1),
          (1, duration2),
          (1, duration3),
          (2, duration4),
          (2, duration5),
          (2, duration6),
          (2, duration6), // (make sure grouped values coalesce)
        ).transact

        _ <- Ns.duration(min(1)).query.get.map(_ ==> List(Set(duration1)))
        _ <- Ns.duration(min(2)).query.get.map(_ ==> List(Set(duration1, duration2)))

        _ <- Ns.duration(max(1)).query.get.map(_ ==> List(Set(duration6)))
        _ <- Ns.duration(max(2)).query.get.map(_ ==> List(Set(duration5, duration6)))

        _ <- Ns.i.a1.duration(min(2)).query.get.map(_ ==> List(
          (1, Set(duration1, duration2)),
          (2, Set(duration4, duration5))
        ))

        _ <- Ns.i.a1.duration(max(2)).query.get.map(_ ==> List(
          (1, Set(duration2, duration3)),
          (2, Set(duration5, duration6))
        ))

        _ <- Ns.i.a1.duration(min(2)).duration(max(2)).query.get.map(_ ==> List(
          (1, Set(duration1, duration2), Set(duration2, duration3)),
          (2, Set(duration4, duration5), Set(duration5, duration6))
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      val all = Set(duration1, duration2, duration3, duration4)
      for {
        _ <- Ns.duration.insert(List(duration1, duration2, duration3)).transact
        _ <- Ns.duration(sample).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.duration(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.duration(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count" - types { implicit conn =>
      for {
        _ <- Ns.i.duration.insert(List(
          (1, duration1),
          (2, duration2),
          (2, duration2),
          (2, duration3),
        )).transact

        _ <- Ns.duration(count).query.get.map(_ ==> List(4))
        _ <- Ns.i.a1.duration(count).query.get.map(_ ==> List(
          (1, 1),
          (2, 3)
        ))

        _ <- Ns.duration(countDistinct).query.get.map(_ ==> List(3))
        _ <- Ns.i.a1.duration(countDistinct).query.get.map(_ ==> List(
          (1, 1),
          (2, 2)
        ))
      } yield ()
    }
  }
}