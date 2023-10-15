// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.one.any

import java.time.ZonedDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrOne_ZonedDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.zonedDateTime.insert(List(
          (1, zonedDateTime1),
          (2, zonedDateTime2),
          (2, zonedDateTime2),
          (2, zonedDateTime3),
        )).transact

        _ <- Ns.i.zonedDateTime.a1.query.get.map(_ ==> List(
          (1, zonedDateTime1),
          (2, zonedDateTime2), // 2 rows coalesced
          (2, zonedDateTime3),
        ))

        // Distinct values are returned in a Set
        _ <- Ns.i.a1.zonedDateTime(distinct).query.get.map(_ ==> List(
          (1, Set(zonedDateTime1)),
          (2, Set(zonedDateTime2, zonedDateTime3)),
        ))

        _ <- Ns.zonedDateTime(distinct).query.get.map(_.head ==> Set(
          zonedDateTime1, zonedDateTime2, zonedDateTime3
        ))
      } yield ()
    }


    "min/max" - types { implicit conn =>
      for {
        _ <- Ns.i.zonedDateTime.insert(
          (1, zonedDateTime1),
          (1, zonedDateTime2),
          (1, zonedDateTime3),
          (2, zonedDateTime4),
          (2, zonedDateTime5),
          (2, zonedDateTime6),
        ).transact

        _ <- Ns.zonedDateTime(min).query.get.map(_ ==> List(zonedDateTime1))
        _ <- Ns.zonedDateTime(min(1)).query.get.map(_ ==> List(Set(zonedDateTime1)))
        _ <- Ns.zonedDateTime(min(2)).query.get.map(_ ==> List(Set(zonedDateTime1, zonedDateTime2)))

        _ <- Ns.zonedDateTime(max).query.get.map(_ ==> List(zonedDateTime6))
        _ <- Ns.zonedDateTime(max(1)).query.get.map(_ ==> List(Set(zonedDateTime6)))
        _ <- Ns.zonedDateTime(max(2)).query.get.map(_ ==> List(Set(zonedDateTime5, zonedDateTime6)))

        _ <- Ns.i.a1.zonedDateTime(min(2)).query.get.map(_ ==> List(
          (1, Set(zonedDateTime1, zonedDateTime2)),
          (2, Set(zonedDateTime4, zonedDateTime5))
        ))

        _ <- Ns.i.a1.zonedDateTime(max(2)).query.get.map(_ ==> List(
          (1, Set(zonedDateTime2, zonedDateTime3)),
          (2, Set(zonedDateTime5, zonedDateTime6))
        ))

        _ <- Ns.i.a1.zonedDateTime(min(2)).zonedDateTime(max(2)).query.get.map(_ ==> List(
          (1, Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime2, zonedDateTime3)),
          (2, Set(zonedDateTime4, zonedDateTime5), Set(zonedDateTime5, zonedDateTime6))
        ))
      } yield ()
    }


    "rand" - types { implicit conn =>
      for {
        _ <- Ns.zonedDateTime.insert(List(zonedDateTime1, zonedDateTime2, zonedDateTime3)).transact
        all = Set(zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4)
        _ <- Ns.zonedDateTime(rand).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.zonedDateTime(rand(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.zonedDateTime(rand(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.zonedDateTime.insert(List(zonedDateTime1, zonedDateTime2, zonedDateTime3)).transact
        all = Set(zonedDateTime1, zonedDateTime2, zonedDateTime3, zonedDateTime4)
        _ <- Ns.zonedDateTime(sample).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.zonedDateTime(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.zonedDateTime(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count, countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.zonedDateTime.insert(List(
          (1, zonedDateTime1),
          (2, zonedDateTime2),
          (2, zonedDateTime2),
          (2, zonedDateTime3),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.zonedDateTime(count).query.get.map(_ ==> List(4))
        _ <- Ns.zonedDateTime(countDistinct).query.get.map(_ ==> List(3))

        _ <- Ns.i.a1.zonedDateTime(count).query.get.map(_ ==> List(
          (1, 1),
          (2, 3)
        ))
        _ <- Ns.i.a1.zonedDateTime(countDistinct).query.get.map(_ ==> List(
          (1, 1),
          (2, 2)
        ))
      } yield ()
    }
  }
}