// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.set.any

import java.time.ZonedDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSet_ZonedDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.zonedDateTimes.insert(List(
          (1, Set(zonedDateTime1, zonedDateTime2)),
          (2, Set(zonedDateTime2, zonedDateTime3)),
          (2, Set(zonedDateTime3, zonedDateTime4)),
          (2, Set(zonedDateTime3, zonedDateTime4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.zonedDateTimes.query.get.map(_ ==> List(
          (1, Set(zonedDateTime1, zonedDateTime2)),
          (2, Set(zonedDateTime2, zonedDateTime3, zonedDateTime4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.zonedDateTimes(distinct).query.get.map(_ ==> List(
          (1, Set(Set(zonedDateTime1, zonedDateTime2))),
          (2, Set(
            Set(zonedDateTime2, zonedDateTime3),
            Set(zonedDateTime3, zonedDateTime4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.zonedDateTimes(distinct).query.get.map(_ ==> List(
          Set(
            Set(zonedDateTime1, zonedDateTime2),
            Set(zonedDateTime2, zonedDateTime3),
            Set(zonedDateTime3, zonedDateTime4),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.zonedDateTimes.insert(List(
          (1, Set(zonedDateTime1, zonedDateTime2)),
          (2, Set(zonedDateTime2, zonedDateTime3)),
          (2, Set(zonedDateTime3, zonedDateTime4)),
          (2, Set(zonedDateTime3, zonedDateTime4)),
        )).transact

        _ <- Ns.zonedDateTimes(min).query.get.map(_ ==> List(Set(zonedDateTime1)))
        _ <- Ns.zonedDateTimes(min(1)).query.get.map(_ ==> List(Set(zonedDateTime1)))
        _ <- Ns.zonedDateTimes(min(2)).query.get.map(_ ==> List(Set(zonedDateTime1, zonedDateTime2)))

        _ <- Ns.i.a1.zonedDateTimes(min).query.get.map(_ ==> List(
          (1, Set(zonedDateTime1)),
          (2, Set(zonedDateTime2)),
        ))
        // Same as
        _ <- Ns.i.a1.zonedDateTimes(min(1)).query.get.map(_ ==> List(
          (1, Set(zonedDateTime1)),
          (2, Set(zonedDateTime2)),
        ))

        _ <- Ns.i.a1.zonedDateTimes(min(2)).query.get.map(_ ==> List(
          (1, Set(zonedDateTime1, zonedDateTime2)),
          (2, Set(zonedDateTime2, zonedDateTime3)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.zonedDateTimes.insert(List(
          (1, Set(zonedDateTime1, zonedDateTime2)),
          (2, Set(zonedDateTime2, zonedDateTime3)),
          (2, Set(zonedDateTime3, zonedDateTime4)),
          (2, Set(zonedDateTime3, zonedDateTime4)),
        )).transact

        _ <- Ns.zonedDateTimes(max).query.get.map(_ ==> List(Set(zonedDateTime4)))
        _ <- Ns.zonedDateTimes(max(1)).query.get.map(_ ==> List(Set(zonedDateTime4)))
        _ <- Ns.zonedDateTimes(max(2)).query.get.map(_ ==> List(Set(zonedDateTime3, zonedDateTime4)))

        _ <- Ns.i.a1.zonedDateTimes(max).query.get.map(_ ==> List(
          (1, Set(zonedDateTime2)),
          (2, Set(zonedDateTime4)),
        ))
        // Same as
        _ <- Ns.i.a1.zonedDateTimes(max(1)).query.get.map(_ ==> List(
          (1, Set(zonedDateTime2)),
          (2, Set(zonedDateTime4)),
        ))

        _ <- Ns.i.a1.zonedDateTimes(max(2)).query.get.map(_ ==> List(
          (1, Set(zonedDateTime1, zonedDateTime2)),
          (2, Set(zonedDateTime3, zonedDateTime4)),
        ))
      } yield ()
    }


    "rand" - types { implicit conn =>
      for {
        _ <- Ns.i.zonedDateTimes.insert(List(
          (1, Set(zonedDateTime1, zonedDateTime2)),
          (2, Set(zonedDateTime2, zonedDateTime3)),
          (2, Set(zonedDateTime3, zonedDateTime4)),
          (2, Set(zonedDateTime3, zonedDateTime4)),
        )).transact
        all = Set(zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4)
        _ <- Ns.zonedDateTimes(rand).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.zonedDateTimes(rand(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.zonedDateTimes(rand(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.zonedDateTimes.insert(List(
          (1, Set(zonedDateTime1, zonedDateTime2)),
          (2, Set(zonedDateTime2, zonedDateTime3)),
          (2, Set(zonedDateTime3, zonedDateTime4)),
          (2, Set(zonedDateTime3, zonedDateTime4)),
        )).transact
        all = Set(zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4)
        _ <- Ns.zonedDateTimes(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.zonedDateTimes(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.zonedDateTimes(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.zonedDateTimes.insert(List(
          (1, Set(zonedDateTime1, zonedDateTime2)),
          (2, Set(zonedDateTime2, zonedDateTime3)),
          (2, Set(zonedDateTime3, zonedDateTime4)),
          (2, Set(zonedDateTime3, zonedDateTime4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.zonedDateTimes(count).query.get.map(_ ==> List(8))
        _ <- Ns.zonedDateTimes(countDistinct).query.get.map(_ ==> List(4))

        _ <- Ns.i.a1.zonedDateTimes(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 6)
        ))
        _ <- Ns.i.a1.zonedDateTimes(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}