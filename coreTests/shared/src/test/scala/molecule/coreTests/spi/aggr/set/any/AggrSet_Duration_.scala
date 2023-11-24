// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.set.any

import java.time.Duration
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSet_Duration_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.durations.insert(List(
          (1, Set(duration1, duration2)),
          (2, Set(duration2, duration3)),
          (2, Set(duration3, duration4)),
          (2, Set(duration3, duration4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.durations.query.get.map(_ ==> List(
          (1, Set(duration1, duration2)),
          (2, Set(duration2, duration3, duration4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.durations(distinct).query.get.map(_ ==> List(
          (1, Set(Set(duration1, duration2))),
          (2, Set(
            Set(duration2, duration3),
            Set(duration3, duration4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.durations(distinct).query.get.map(_ ==> List(
          Set(
            Set(duration1, duration2),
            Set(duration2, duration3),
            Set(duration3, duration4),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.durations.insert(List(
          (1, Set(duration1, duration2)),
          (2, Set(duration2, duration3)),
          (2, Set(duration3, duration4)),
          (2, Set(duration3, duration4)),
        )).transact

        _ <- Ns.durations(min).query.get.map(_ ==> List(Set(duration1)))
        _ <- Ns.durations(min(1)).query.get.map(_ ==> List(Set(duration1)))
        _ <- Ns.durations(min(2)).query.get.map(_ ==> List(Set(duration1, duration2)))

        _ <- Ns.i.a1.durations(min).query.get.map(_ ==> List(
          (1, Set(duration1)),
          (2, Set(duration2)),
        ))
        // Same as
        _ <- Ns.i.a1.durations(min(1)).query.get.map(_ ==> List(
          (1, Set(duration1)),
          (2, Set(duration2)),
        ))

        _ <- Ns.i.a1.durations(min(2)).query.get.map(_ ==> List(
          (1, Set(duration1, duration2)),
          (2, Set(duration2, duration3)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.durations.insert(List(
          (1, Set(duration1, duration2)),
          (2, Set(duration2, duration3)),
          (2, Set(duration3, duration4)),
          (2, Set(duration3, duration4)),
        )).transact

        _ <- Ns.durations(max).query.get.map(_ ==> List(Set(duration4)))
        _ <- Ns.durations(max(1)).query.get.map(_ ==> List(Set(duration4)))
        _ <- Ns.durations(max(2)).query.get.map(_ ==> List(Set(duration3, duration4)))

        _ <- Ns.i.a1.durations(max).query.get.map(_ ==> List(
          (1, Set(duration2)),
          (2, Set(duration4)),
        ))
        // Same as
        _ <- Ns.i.a1.durations(max(1)).query.get.map(_ ==> List(
          (1, Set(duration2)),
          (2, Set(duration4)),
        ))

        _ <- Ns.i.a1.durations(max(2)).query.get.map(_ ==> List(
          (1, Set(duration1, duration2)),
          (2, Set(duration3, duration4)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.durations.insert(List(
          (1, Set(duration1, duration2)),
          (2, Set(duration2, duration3)),
          (2, Set(duration3, duration4)),
          (2, Set(duration3, duration4)),
        )).transact
        all = Set(duration1, duration2, duration3, duration4)
        _ <- Ns.durations(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.durations(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.durations(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.durations.insert(List(
          (1, Set(duration1, duration2)),
          (2, Set(duration2, duration3)),
          (2, Set(duration3, duration4)),
          (2, Set(duration3, duration4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.durations(count).query.get.map(_ ==> List(8))
        _ <- Ns.durations(countDistinct).query.get.map(_ ==> List(4))

        _ <- Ns.i.a1.durations(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 6)
        ))
        _ <- Ns.i.a1.durations(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}