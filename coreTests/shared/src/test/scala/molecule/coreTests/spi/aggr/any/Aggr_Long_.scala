// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.any

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait Aggr_Long_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.long.insert(List(
          (1, long1),
          (2, long2),
          (2, long2),
          (2, long3),
        )).transact

        _ <- Ns.i.long.a1.query.get.map(_ ==> List(
          (1, long1),
          (2, long2), // 2 rows coalesced
          (2, long3),
        ))

        // Distinct values are returned in a Set
        _ <- Ns.i.a1.long(distinct).query.get.map(_ ==> List(
          (1, Set(long1)),
          (2, Set(long2, long3)),
        ))

        _ <- Ns.long(distinct).query.get.map(_.head ==> Set(
          long1, long2, long3
        ))
      } yield ()
    }


    "min/max" - types { implicit conn =>
      for {
        _ <- Ns.i.long.insert(
          (1, long1),
          (1, long2),
          (1, long3),
          (2, long4),
          (2, long5),
          (2, long6),
        ).transact

        _ <- Ns.long(min).query.get.map(_ ==> List(long1))
        _ <- Ns.long(max).query.get.map(_ ==> List(long6))
        _ <- Ns.long(min).long(max).query.get.map(_ ==> List((long1, long6)))

        _ <- Ns.i.a1.long(min).query.get.map(_ ==> List(
          (1, long1),
          (2, long4)
        ))

        _ <- Ns.i.a1.long(max).query.get.map(_ ==> List(
          (1, long3),
          (2, long6)
        ))

        _ <- Ns.i.a1.long(min).long(max).query.get.map(_ ==> List(
          (1, long1, long3),
          (2, long4, long6)
        ))
      } yield ()
    }

    "min/max n" - types { implicit conn =>
      for {
        _ <- Ns.i.long.insert(
          (1, long1),
          (1, long2),
          (1, long3),
          (2, long4),
          (2, long5),
          (2, long6),
          (2, long6), // (make sure grouped values coalesce)
        ).transact

        _ <- Ns.long(min(1)).query.get.map(_ ==> List(Set(long1)))
        _ <- Ns.long(min(2)).query.get.map(_ ==> List(Set(long1, long2)))

        _ <- Ns.long(max(1)).query.get.map(_ ==> List(Set(long6)))
        _ <- Ns.long(max(2)).query.get.map(_ ==> List(Set(long5, long6)))

        _ <- Ns.i.a1.long(min(2)).query.get.map(_ ==> List(
          (1, Set(long1, long2)),
          (2, Set(long4, long5))
        ))

        _ <- Ns.i.a1.long(max(2)).query.get.map(_ ==> List(
          (1, Set(long2, long3)),
          (2, Set(long5, long6))
        ))

        _ <- Ns.i.a1.long(min(2)).long(max(2)).query.get.map(_ ==> List(
          (1, Set(long1, long2), Set(long2, long3)),
          (2, Set(long4, long5), Set(long5, long6))
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      val all = Set(long1, long2, long3, long4)
      for {
        _ <- Ns.long.insert(List(long1, long2, long3)).transact
        _ <- Ns.long(sample).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.long(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.long(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count" - types { implicit conn =>
      for {
        _ <- Ns.i.long.insert(List(
          (1, long1),
          (2, long2),
          (2, long2),
          (2, long3),
        )).transact

        _ <- Ns.long(count).query.get.map(_ ==> List(4))
        _ <- Ns.i.a1.long(count).query.get.map(_ ==> List(
          (1, 1),
          (2, 3)
        ))

        _ <- Ns.long(countDistinct).query.get.map(_ ==> List(3))
        _ <- Ns.i.a1.long(countDistinct).query.get.map(_ ==> List(
          (1, 1),
          (2, 2)
        ))
      } yield ()
    }
  }
}