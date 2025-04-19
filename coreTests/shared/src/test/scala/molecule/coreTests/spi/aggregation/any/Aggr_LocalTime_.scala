// GENERATED CODE ********************************
package molecule.coreTests.spi.aggregation.any

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor.*
import molecule.coreTests.domains.dsl.Types.*
import molecule.coreTests.setup.*

case class Aggr_LocalTime_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types { implicit conn =>
    for {
      _ <- Entity.i.localTime.insert(List(
        (1, localTime1),
        (2, localTime2),
        (2, localTime2),
        (2, localTime3),
      )).transact

      _ <- Entity.i.localTime.a1.query.get.map(_ ==> List(
        (1, localTime1),
        (2, localTime2), // 2 rows coalesced
        (2, localTime3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.localTime(distinct).query.get.map(_ ==> List(
        (1, Set(localTime1)),
        (2, Set(localTime2, localTime3)),
      ))

      _ <- Entity.localTime(distinct).query.get.map(_.head ==> Set(
        localTime1, localTime2, localTime3
      ))
    } yield ()
  }


  "min/max" - types { implicit conn =>
    for {
      _ <- Entity.i.localTime.insert(
        (1, localTime1),
        (1, localTime2),
        (1, localTime3),
        (2, localTime4),
        (2, localTime5),
        (2, localTime6),
      ).transact

      _ <- Entity.localTime(min).query.get.map(_ ==> List(localTime1))
      _ <- Entity.localTime(max).query.get.map(_ ==> List(localTime6))
      _ <- Entity.localTime(min).localTime(max).query.get.map(_ ==> List((localTime1, localTime6)))

      _ <- Entity.i.a1.localTime(min).query.get.map(_ ==> List(
        (1, localTime1),
        (2, localTime4)
      ))

      _ <- Entity.i.a1.localTime(max).query.get.map(_ ==> List(
        (1, localTime3),
        (2, localTime6)
      ))

      _ <- Entity.i.a1.localTime(min).localTime(max).query.get.map(_ ==> List(
        (1, localTime1, localTime3),
        (2, localTime4, localTime6)
      ))
    } yield ()
  }

  "min/max n" - types { implicit conn =>
    for {
      _ <- Entity.i.localTime.insert(
        (1, localTime1),
        (1, localTime2),
        (1, localTime3),
        (2, localTime4),
        (2, localTime5),
        (2, localTime6),
        (2, localTime6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.localTime(min(1)).query.get.map(_ ==> List(Set(localTime1)))
      _ <- Entity.localTime(min(2)).query.get.map(_ ==> List(Set(localTime1, localTime2)))

      _ <- Entity.localTime(max(1)).query.get.map(_ ==> List(Set(localTime6)))
      _ <- Entity.localTime(max(2)).query.get.map(_ ==> List(Set(localTime5, localTime6)))

      _ <- Entity.i.a1.localTime(min(2)).query.get.map(_ ==> List(
        (1, Set(localTime1, localTime2)),
        (2, Set(localTime4, localTime5))
      ))

      _ <- Entity.i.a1.localTime(max(2)).query.get.map(_ ==> List(
        (1, Set(localTime2, localTime3)),
        (2, Set(localTime5, localTime6))
      ))

      _ <- Entity.i.a1.localTime(min(2)).localTime(max(2)).query.get.map(_ ==> List(
        (1, Set(localTime1, localTime2), Set(localTime2, localTime3)),
        (2, Set(localTime4, localTime5), Set(localTime5, localTime6))
      ))
    } yield ()
  }


  "sample" - types { implicit futConn =>
    val all = Set(localTime1, localTime2, localTime3, localTime4)
    for {
      _ <- Entity.localTime.insert(List(localTime1, localTime2, localTime3)).transact
      _ <- Entity.localTime(sample).query.get.map(res => all.contains(res.head) ==> true)
      _ <- Entity.localTime(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.localTime(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types { implicit conn =>
    for {
      _ <- Entity.i.localTime.insert(List(
        (1, localTime1),
        (2, localTime2),
        (2, localTime2),
        (2, localTime3),
      )).transact

      _ <- Entity.localTime(count).query.get.map(_ ==> List(4))
      _ <- Entity.i.a1.localTime(count).query.get.map(_ ==> List(
        (1, 1),
        (2, 3)
      ))

      _ <- Entity.localTime(countDistinct).query.get.map(_ ==> List(3))
      _ <- Entity.i.a1.localTime(countDistinct).query.get.map(_ ==> List(
        (1, 1),
        (2, 2)
      ))
    } yield ()
  }
}