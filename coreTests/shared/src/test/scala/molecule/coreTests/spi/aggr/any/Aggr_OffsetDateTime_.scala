// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.any

import java.time.OffsetDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.collection.immutable.Set

trait Aggr_OffsetDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.offsetDateTime.insert(List(
          (1, offsetDateTime1),
          (2, offsetDateTime2),
          (2, offsetDateTime2),
          (2, offsetDateTime3),
        )).transact

        _ <- Ns.i.offsetDateTime.a1.query.get.map(_ ==> List(
          (1, offsetDateTime1),
          (2, offsetDateTime2), // 2 rows coalesced
          (2, offsetDateTime3),
        ))

        // Distinct values are returned in a Set
        _ <- Ns.i.a1.offsetDateTime(distinct).query.get.map(_ ==> List(
          (1, Set(offsetDateTime1)),
          (2, Set(offsetDateTime2, offsetDateTime3)),
        ))

        _ <- Ns.offsetDateTime(distinct).query.get.map(_.head ==> Set(
          offsetDateTime1, offsetDateTime2, offsetDateTime3
        ))
      } yield ()
    }


    "min/max" - types { implicit conn =>
      for {
        _ <- Ns.i.offsetDateTime.insert(
          (1, offsetDateTime1),
          (1, offsetDateTime2),
          (1, offsetDateTime3),
          (2, offsetDateTime4),
          (2, offsetDateTime5),
          (2, offsetDateTime6),
        ).transact

        _ <- Ns.offsetDateTime(min).query.get.map(_ ==> List(offsetDateTime1))
        _ <- Ns.offsetDateTime(max).query.get.map(_ ==> List(offsetDateTime6))
        _ <- Ns.offsetDateTime(min).offsetDateTime(max).query.get.map(_ ==> List((offsetDateTime1, offsetDateTime6)))

        _ <- Ns.i.a1.offsetDateTime(min).query.get.map(_ ==> List(
          (1, offsetDateTime1),
          (2, offsetDateTime4)
        ))

        _ <- Ns.i.a1.offsetDateTime(max).query.get.map(_ ==> List(
          (1, offsetDateTime3),
          (2, offsetDateTime6)
        ))

        _ <- Ns.i.a1.offsetDateTime(min).offsetDateTime(max).query.get.map(_ ==> List(
          (1, offsetDateTime1, offsetDateTime3),
          (2, offsetDateTime4, offsetDateTime6)
        ))
      } yield ()
    }

    "min/max n" - types { implicit conn =>
      for {
        _ <- Ns.i.offsetDateTime.insert(
          (1, offsetDateTime1),
          (1, offsetDateTime2),
          (1, offsetDateTime3),
          (2, offsetDateTime4),
          (2, offsetDateTime5),
          (2, offsetDateTime6),
          (2, offsetDateTime6), // (make sure grouped values coalesce)
        ).transact

        _ <- Ns.offsetDateTime(min(1)).query.get.map(_ ==> List(Set(offsetDateTime1)))
        _ <- Ns.offsetDateTime(min(2)).query.get.map(_ ==> List(Set(offsetDateTime1, offsetDateTime2)))

        _ <- Ns.offsetDateTime(max(1)).query.get.map(_ ==> List(Set(offsetDateTime6)))
        _ <- Ns.offsetDateTime(max(2)).query.get.map(_ ==> List(Set(offsetDateTime5, offsetDateTime6)))

        _ <- Ns.i.a1.offsetDateTime(min(2)).query.get.map(_ ==> List(
          (1, Set(offsetDateTime1, offsetDateTime2)),
          (2, Set(offsetDateTime4, offsetDateTime5))
        ))

        _ <- Ns.i.a1.offsetDateTime(max(2)).query.get.map(_ ==> List(
          (1, Set(offsetDateTime2, offsetDateTime3)),
          (2, Set(offsetDateTime5, offsetDateTime6))
        ))

        _ <- Ns.i.a1.offsetDateTime(min(2)).offsetDateTime(max(2)).query.get.map(_ ==> List(
          (1, Set(offsetDateTime1, offsetDateTime2), Set(offsetDateTime2, offsetDateTime3)),
          (2, Set(offsetDateTime4, offsetDateTime5), Set(offsetDateTime5, offsetDateTime6))
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      val all = Set(offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4)
      for {
        _ <- Ns.offsetDateTime.insert(List(offsetDateTime1, offsetDateTime2, offsetDateTime3)).transact
        _ <- Ns.offsetDateTime(sample).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.offsetDateTime(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.offsetDateTime(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count" - types { implicit conn =>
      for {
        _ <- Ns.i.offsetDateTime.insert(List(
          (1, offsetDateTime1),
          (2, offsetDateTime2),
          (2, offsetDateTime2),
          (2, offsetDateTime3),
        )).transact

        _ <- Ns.offsetDateTime(count).query.get.map(_ ==> List(4))
        _ <- Ns.i.a1.offsetDateTime(count).query.get.map(_ ==> List(
          (1, 1),
          (2, 3)
        ))

        _ <- Ns.offsetDateTime(countDistinct).query.get.map(_ ==> List(3))
        _ <- Ns.i.a1.offsetDateTime(countDistinct).query.get.map(_ ==> List(
          (1, 1),
          (2, 2)
        ))
      } yield ()
    }
  }
}