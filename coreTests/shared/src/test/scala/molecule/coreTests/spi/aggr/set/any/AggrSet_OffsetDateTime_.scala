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
        _ <- Ns.i.offsetDateTimeSet.insert(List(
          (1, Set(offsetDateTime1, offsetDateTime2)),
          (2, Set(offsetDateTime2)),
          (2, Set(offsetDateTime3, offsetDateTime4)),
          (2, Set(offsetDateTime3, offsetDateTime4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.offsetDateTimeSet.query.get.map(_ ==> List(
          (1, Set(offsetDateTime1, offsetDateTime2)),
          (2, Set(offsetDateTime2, offsetDateTime3, offsetDateTime4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.offsetDateTimeSet(distinct).query.get.map(_ ==> List(
          (1, Set(Set(offsetDateTime1, offsetDateTime2))),
          (2, Set(
            Set(offsetDateTime2),
            Set(offsetDateTime3, offsetDateTime4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.offsetDateTimeSet(distinct).query.get.map(_ ==> List(
          Set(
            Set(offsetDateTime1, offsetDateTime2),
            Set(offsetDateTime2),
            Set(offsetDateTime3, offsetDateTime4),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.offsetDateTimeSet.insert(List(
          (1, Set(offsetDateTime1, offsetDateTime2)),
          (2, Set(offsetDateTime2)),
          (2, Set(offsetDateTime3, offsetDateTime4)),
          (2, Set(offsetDateTime3, offsetDateTime4)),
        )).transact

        // Matching values coalesced offsetDateTimeo one Set

        _ <- Ns.offsetDateTimeSet(min).query.get.map(_ ==> List(Set(offsetDateTime1)))
        _ <- Ns.offsetDateTimeSet(min(1)).query.get.map(_ ==> List(Set(offsetDateTime1)))
        _ <- Ns.offsetDateTimeSet(min(2)).query.get.map(_ ==> List(Set(offsetDateTime1, offsetDateTime2)))
        _ <- Ns.offsetDateTimeSet(min(3)).query.get.map(_ ==> List(Set(offsetDateTime1, offsetDateTime2, offsetDateTime3)))

        _ <- Ns.i.a1.offsetDateTimeSet(min).query.get.map(_ ==> List(
          (1, Set(offsetDateTime1)),
          (2, Set(offsetDateTime2)),
        ))
        // Same as
        _ <- Ns.i.a1.offsetDateTimeSet(min(1)).query.get.map(_ ==> List(
          (1, Set(offsetDateTime1)),
          (2, Set(offsetDateTime2)),
        ))

        _ <- Ns.i.a1.offsetDateTimeSet(min(2)).query.get.map(_ ==> List(
          (1, Set(offsetDateTime1, offsetDateTime2)),
          (2, Set(offsetDateTime2, offsetDateTime3)),
        ))

        _ <- Ns.i.a1.offsetDateTimeSet(min(3)).query.get.map(_ ==> List(
          (1, Set(offsetDateTime1, offsetDateTime2)),
          (2, Set(offsetDateTime2, offsetDateTime3, offsetDateTime4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.offsetDateTimeSet.insert(List(
          (1, Set(offsetDateTime1, offsetDateTime2)),
          (2, Set(offsetDateTime2)),
          (2, Set(offsetDateTime3, offsetDateTime4)),
          (2, Set(offsetDateTime3, offsetDateTime4)),
        )).transact

        // Matching values coalesced offsetDateTimeo one Set

        _ <- Ns.offsetDateTimeSet(max).query.get.map(_ ==> List(Set(offsetDateTime4)))
        _ <- Ns.offsetDateTimeSet(max(1)).query.get.map(_ ==> List(Set(offsetDateTime4)))
        _ <- Ns.offsetDateTimeSet(max(2)).query.get.map(_ ==> List(Set(offsetDateTime3, offsetDateTime4)))
        _ <- Ns.offsetDateTimeSet(max(3)).query.get.map(_ ==> List(Set(offsetDateTime2, offsetDateTime3, offsetDateTime4)))

        _ <- Ns.i.a1.offsetDateTimeSet(max).query.get.map(_ ==> List(
          (1, Set(offsetDateTime2)),
          (2, Set(offsetDateTime4)),
        ))
        // Same as
        _ <- Ns.i.a1.offsetDateTimeSet(max(1)).query.get.map(_ ==> List(
          (1, Set(offsetDateTime2)),
          (2, Set(offsetDateTime4)),
        ))

        _ <- Ns.i.a1.offsetDateTimeSet(max(2)).query.get.map(_ ==> List(
          (1, Set(offsetDateTime1, offsetDateTime2)),
          (2, Set(offsetDateTime3, offsetDateTime4)),
        ))

        _ <- Ns.i.a1.offsetDateTimeSet(max(3)).query.get.map(_ ==> List(
          (1, Set(offsetDateTime1, offsetDateTime2)),
          (2, Set(offsetDateTime2, offsetDateTime3, offsetDateTime4)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.offsetDateTimeSet.insert(List(
          (1, Set(offsetDateTime1, offsetDateTime2)),
          (2, Set(offsetDateTime2)),
          (2, Set(offsetDateTime3, offsetDateTime4)),
          (2, Set(offsetDateTime3, offsetDateTime4)),
        )).transact
        all = Set(offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4)
        _ <- Ns.offsetDateTimeSet(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.offsetDateTimeSet(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.offsetDateTimeSet(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.offsetDateTimeSet.insert(List(
          (1, Set(offsetDateTime1, offsetDateTime2)),
          (2, Set(offsetDateTime2)),
          (2, Set(offsetDateTime3, offsetDateTime4)),
          (2, Set(offsetDateTime3, offsetDateTime4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.offsetDateTimeSet(count).query.get.map(_ ==> List(7))
        _ <- Ns.offsetDateTimeSet(countDistinct).query.get.map(_ ==> List(4))

        _ <- Ns.i.a1.offsetDateTimeSet(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 5)
        ))
        _ <- Ns.i.a1.offsetDateTimeSet(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}