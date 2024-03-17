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
        _ <- Ns.i.zonedDateTimeSet.insert(List(
          (1, Set(zonedDateTime1, zonedDateTime2)),
          (2, Set(zonedDateTime2)),
          (2, Set(zonedDateTime3, zonedDateTime4)),
          (2, Set(zonedDateTime3, zonedDateTime4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.zonedDateTimeSet.query.get.map(_ ==> List(
          (1, Set(zonedDateTime1, zonedDateTime2)),
          (2, Set(zonedDateTime2, zonedDateTime3, zonedDateTime4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.zonedDateTimeSet(distinct).query.get.map(_ ==> List(
          (1, Set(Set(zonedDateTime1, zonedDateTime2))),
          (2, Set(
            Set(zonedDateTime2),
            Set(zonedDateTime3, zonedDateTime4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.zonedDateTimeSet(distinct).query.get.map(_ ==> List(
          Set(
            Set(zonedDateTime1, zonedDateTime2),
            Set(zonedDateTime2),
            Set(zonedDateTime3, zonedDateTime4),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.zonedDateTimeSet.insert(List(
          (1, Set(zonedDateTime1, zonedDateTime2)),
          (2, Set(zonedDateTime2)),
          (2, Set(zonedDateTime3, zonedDateTime4)),
          (2, Set(zonedDateTime3, zonedDateTime4)),
        )).transact

        // Matching values coalesced zonedDateTimeo one Set

        _ <- Ns.zonedDateTimeSet(min).query.get.map(_ ==> List(Set(zonedDateTime1)))
        _ <- Ns.zonedDateTimeSet(min(1)).query.get.map(_ ==> List(Set(zonedDateTime1)))
        _ <- Ns.zonedDateTimeSet(min(2)).query.get.map(_ ==> List(Set(zonedDateTime1, zonedDateTime2)))
        _ <- Ns.zonedDateTimeSet(min(3)).query.get.map(_ ==> List(Set(zonedDateTime1, zonedDateTime2, zonedDateTime3)))

        _ <- Ns.i.a1.zonedDateTimeSet(min).query.get.map(_ ==> List(
          (1, Set(zonedDateTime1)),
          (2, Set(zonedDateTime2)),
        ))
        // Same as
        _ <- Ns.i.a1.zonedDateTimeSet(min(1)).query.get.map(_ ==> List(
          (1, Set(zonedDateTime1)),
          (2, Set(zonedDateTime2)),
        ))

        _ <- Ns.i.a1.zonedDateTimeSet(min(2)).query.get.map(_ ==> List(
          (1, Set(zonedDateTime1, zonedDateTime2)),
          (2, Set(zonedDateTime2, zonedDateTime3)),
        ))

        _ <- Ns.i.a1.zonedDateTimeSet(min(3)).query.get.map(_ ==> List(
          (1, Set(zonedDateTime1, zonedDateTime2)),
          (2, Set(zonedDateTime2, zonedDateTime3, zonedDateTime4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.zonedDateTimeSet.insert(List(
          (1, Set(zonedDateTime1, zonedDateTime2)),
          (2, Set(zonedDateTime2)),
          (2, Set(zonedDateTime3, zonedDateTime4)),
          (2, Set(zonedDateTime3, zonedDateTime4)),
        )).transact

        // Matching values coalesced zonedDateTimeo one Set

        _ <- Ns.zonedDateTimeSet(max).query.get.map(_ ==> List(Set(zonedDateTime4)))
        _ <- Ns.zonedDateTimeSet(max(1)).query.get.map(_ ==> List(Set(zonedDateTime4)))
        _ <- Ns.zonedDateTimeSet(max(2)).query.get.map(_ ==> List(Set(zonedDateTime3, zonedDateTime4)))
        _ <- Ns.zonedDateTimeSet(max(3)).query.get.map(_ ==> List(Set(zonedDateTime2, zonedDateTime3, zonedDateTime4)))

        _ <- Ns.i.a1.zonedDateTimeSet(max).query.get.map(_ ==> List(
          (1, Set(zonedDateTime2)),
          (2, Set(zonedDateTime4)),
        ))
        // Same as
        _ <- Ns.i.a1.zonedDateTimeSet(max(1)).query.get.map(_ ==> List(
          (1, Set(zonedDateTime2)),
          (2, Set(zonedDateTime4)),
        ))

        _ <- Ns.i.a1.zonedDateTimeSet(max(2)).query.get.map(_ ==> List(
          (1, Set(zonedDateTime1, zonedDateTime2)),
          (2, Set(zonedDateTime3, zonedDateTime4)),
        ))

        _ <- Ns.i.a1.zonedDateTimeSet(max(3)).query.get.map(_ ==> List(
          (1, Set(zonedDateTime1, zonedDateTime2)),
          (2, Set(zonedDateTime2, zonedDateTime3, zonedDateTime4)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.zonedDateTimeSet.insert(List(
          (1, Set(zonedDateTime1, zonedDateTime2)),
          (2, Set(zonedDateTime2)),
          (2, Set(zonedDateTime3, zonedDateTime4)),
          (2, Set(zonedDateTime3, zonedDateTime4)),
        )).transact
        all = Set(zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4)
        _ <- Ns.zonedDateTimeSet(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.zonedDateTimeSet(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.zonedDateTimeSet(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.zonedDateTimeSet.insert(List(
          (1, Set(zonedDateTime1, zonedDateTime2)),
          (2, Set(zonedDateTime2)),
          (2, Set(zonedDateTime3, zonedDateTime4)),
          (2, Set(zonedDateTime3, zonedDateTime4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.zonedDateTimeSet(count).query.get.map(_ ==> List(7))
        _ <- Ns.zonedDateTimeSet(countDistinct).query.get.map(_ ==> List(4))

        _ <- Ns.i.a1.zonedDateTimeSet(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 5)
        ))
        _ <- Ns.i.a1.zonedDateTimeSet(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}