// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.set.any

import java.time.OffsetDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSet_OffsetDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.offsetDateTimes.insert(List(
          (1, Set(offsetDateTime1, offsetDateTime2)),
          (2, Set(offsetDateTime2, offsetDateTime3)),
          (2, Set(offsetDateTime3, offsetDateTime4)),
          (2, Set(offsetDateTime3, offsetDateTime4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.offsetDateTimes.query.get.map(_ ==> List(
          (1, Set(offsetDateTime1, offsetDateTime2)),
          (2, Set(offsetDateTime2, offsetDateTime3, offsetDateTime4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.offsetDateTimes(distinct).query.get.map(_ ==> List(
          (1, Set(Set(offsetDateTime1, offsetDateTime2))),
          (2, Set(
            Set(offsetDateTime2, offsetDateTime3),
            Set(offsetDateTime3, offsetDateTime4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.offsetDateTimes(distinct).query.get.map(_ ==> List(
          Set(
            Set(offsetDateTime1, offsetDateTime2),
            Set(offsetDateTime2, offsetDateTime3),
            Set(offsetDateTime3, offsetDateTime4),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.offsetDateTimes.insert(List(
          (1, Set(offsetDateTime1, offsetDateTime2)),
          (2, Set(offsetDateTime2, offsetDateTime3)),
          (2, Set(offsetDateTime3, offsetDateTime4)),
          (2, Set(offsetDateTime3, offsetDateTime4)),
        )).transact

        _ <- Ns.offsetDateTimes(min).query.get.map(_ ==> List(Set(offsetDateTime1)))
        _ <- Ns.offsetDateTimes(min(1)).query.get.map(_ ==> List(Set(offsetDateTime1)))
        _ <- Ns.offsetDateTimes(min(2)).query.get.map(_ ==> List(Set(offsetDateTime1, offsetDateTime2)))

        _ <- Ns.i.a1.offsetDateTimes(min).query.get.map(_ ==> List(
          (1, Set(offsetDateTime1)),
          (2, Set(offsetDateTime2)),
        ))
        // Same as
        _ <- Ns.i.a1.offsetDateTimes(min(1)).query.get.map(_ ==> List(
          (1, Set(offsetDateTime1)),
          (2, Set(offsetDateTime2)),
        ))

        _ <- Ns.i.a1.offsetDateTimes(min(2)).query.get.map(_ ==> List(
          (1, Set(offsetDateTime1, offsetDateTime2)),
          (2, Set(offsetDateTime2, offsetDateTime3)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.offsetDateTimes.insert(List(
          (1, Set(offsetDateTime1, offsetDateTime2)),
          (2, Set(offsetDateTime2, offsetDateTime3)),
          (2, Set(offsetDateTime3, offsetDateTime4)),
          (2, Set(offsetDateTime3, offsetDateTime4)),
        )).transact

        _ <- Ns.offsetDateTimes(max).query.get.map(_ ==> List(Set(offsetDateTime4)))
        _ <- Ns.offsetDateTimes(max(1)).query.get.map(_ ==> List(Set(offsetDateTime4)))
        _ <- Ns.offsetDateTimes(max(2)).query.get.map(_ ==> List(Set(offsetDateTime3, offsetDateTime4)))

        _ <- Ns.i.a1.offsetDateTimes(max).query.get.map(_ ==> List(
          (1, Set(offsetDateTime2)),
          (2, Set(offsetDateTime4)),
        ))
        // Same as
        _ <- Ns.i.a1.offsetDateTimes(max(1)).query.get.map(_ ==> List(
          (1, Set(offsetDateTime2)),
          (2, Set(offsetDateTime4)),
        ))

        _ <- Ns.i.a1.offsetDateTimes(max(2)).query.get.map(_ ==> List(
          (1, Set(offsetDateTime1, offsetDateTime2)),
          (2, Set(offsetDateTime3, offsetDateTime4)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.offsetDateTimes.insert(List(
          (1, Set(offsetDateTime1, offsetDateTime2)),
          (2, Set(offsetDateTime2, offsetDateTime3)),
          (2, Set(offsetDateTime3, offsetDateTime4)),
          (2, Set(offsetDateTime3, offsetDateTime4)),
        )).transact
        all = Set(offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4)
        _ <- Ns.offsetDateTimes(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.offsetDateTimes(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.offsetDateTimes(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.offsetDateTimes.insert(List(
          (1, Set(offsetDateTime1, offsetDateTime2)),
          (2, Set(offsetDateTime2, offsetDateTime3)),
          (2, Set(offsetDateTime3, offsetDateTime4)),
          (2, Set(offsetDateTime3, offsetDateTime4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.offsetDateTimes(count).query.get.map(_ ==> List(8))
        _ <- Ns.offsetDateTimes(countDistinct).query.get.map(_ ==> List(4))

        _ <- Ns.i.a1.offsetDateTimes(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 6)
        ))
        _ <- Ns.i.a1.offsetDateTimes(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}