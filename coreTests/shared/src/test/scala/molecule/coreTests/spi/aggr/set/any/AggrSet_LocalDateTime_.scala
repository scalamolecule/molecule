// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.set.any

import java.time.LocalDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSet_LocalDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.localDateTimes.insert(List(
          (1, Set(localDateTime1, localDateTime2)),
          (2, Set(localDateTime2, localDateTime3)),
          (2, Set(localDateTime3, localDateTime4)),
          (2, Set(localDateTime3, localDateTime4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.localDateTimes.query.get.map(_ ==> List(
          (1, Set(localDateTime1, localDateTime2)),
          (2, Set(localDateTime2, localDateTime3, localDateTime4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.localDateTimes(distinct).query.get.map(_ ==> List(
          (1, Set(Set(localDateTime1, localDateTime2))),
          (2, Set(
            Set(localDateTime2, localDateTime3),
            Set(localDateTime3, localDateTime4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.localDateTimes(distinct).query.get.map(_ ==> List(
          Set(
            Set(localDateTime1, localDateTime2),
            Set(localDateTime2, localDateTime3),
            Set(localDateTime3, localDateTime4),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.localDateTimes.insert(List(
          (1, Set(localDateTime1, localDateTime2)),
          (2, Set(localDateTime2, localDateTime3)),
          (2, Set(localDateTime3, localDateTime4)),
          (2, Set(localDateTime3, localDateTime4)),
        )).transact

        _ <- Ns.localDateTimes(min).query.get.map(_ ==> List(Set(localDateTime1)))
        _ <- Ns.localDateTimes(min(1)).query.get.map(_ ==> List(Set(localDateTime1)))
        _ <- Ns.localDateTimes(min(2)).query.get.map(_ ==> List(Set(localDateTime1, localDateTime2)))

        _ <- Ns.i.a1.localDateTimes(min).query.get.map(_ ==> List(
          (1, Set(localDateTime1)),
          (2, Set(localDateTime2)),
        ))
        // Same as
        _ <- Ns.i.a1.localDateTimes(min(1)).query.get.map(_ ==> List(
          (1, Set(localDateTime1)),
          (2, Set(localDateTime2)),
        ))

        _ <- Ns.i.a1.localDateTimes(min(2)).query.get.map(_ ==> List(
          (1, Set(localDateTime1, localDateTime2)),
          (2, Set(localDateTime2, localDateTime3)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.localDateTimes.insert(List(
          (1, Set(localDateTime1, localDateTime2)),
          (2, Set(localDateTime2, localDateTime3)),
          (2, Set(localDateTime3, localDateTime4)),
          (2, Set(localDateTime3, localDateTime4)),
        )).transact

        _ <- Ns.localDateTimes(max).query.get.map(_ ==> List(Set(localDateTime4)))
        _ <- Ns.localDateTimes(max(1)).query.get.map(_ ==> List(Set(localDateTime4)))
        _ <- Ns.localDateTimes(max(2)).query.get.map(_ ==> List(Set(localDateTime3, localDateTime4)))

        _ <- Ns.i.a1.localDateTimes(max).query.get.map(_ ==> List(
          (1, Set(localDateTime2)),
          (2, Set(localDateTime4)),
        ))
        // Same as
        _ <- Ns.i.a1.localDateTimes(max(1)).query.get.map(_ ==> List(
          (1, Set(localDateTime2)),
          (2, Set(localDateTime4)),
        ))

        _ <- Ns.i.a1.localDateTimes(max(2)).query.get.map(_ ==> List(
          (1, Set(localDateTime1, localDateTime2)),
          (2, Set(localDateTime3, localDateTime4)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.localDateTimes.insert(List(
          (1, Set(localDateTime1, localDateTime2)),
          (2, Set(localDateTime2, localDateTime3)),
          (2, Set(localDateTime3, localDateTime4)),
          (2, Set(localDateTime3, localDateTime4)),
        )).transact
        all = Set(localDateTime1, localDateTime2, localDateTime3, localDateTime4)
        _ <- Ns.localDateTimes(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.localDateTimes(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.localDateTimes(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.localDateTimes.insert(List(
          (1, Set(localDateTime1, localDateTime2)),
          (2, Set(localDateTime2, localDateTime3)),
          (2, Set(localDateTime3, localDateTime4)),
          (2, Set(localDateTime3, localDateTime4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.localDateTimes(count).query.get.map(_ ==> List(8))
        _ <- Ns.localDateTimes(countDistinct).query.get.map(_ ==> List(4))

        _ <- Ns.i.a1.localDateTimes(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 6)
        ))
        _ <- Ns.i.a1.localDateTimes(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}