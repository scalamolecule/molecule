// GENERATED CODE ********************************
package molecule.coreTests.spi.aggregation.any

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class Aggr_Instant_(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "distinct" - types { implicit conn =>
    for {
      _ <- Entity.i.instant.insert(List(
        (1, instant1),
        (2, instant2),
        (2, instant2),
        (2, instant3),
      )).transact

      _ <- Entity.i.instant.a1.query.get.map(_ ==> List(
        (1, instant1),
        (2, instant2), // 2 rows coalesced
        (2, instant3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.instant(distinct).query.get.map(_ ==> List(
        (1, Set(instant1)),
        (2, Set(instant2, instant3)),
      ))

      _ <- Entity.instant(distinct).query.get.map(_.head ==> Set(
        instant1, instant2, instant3
      ))
    } yield ()
  }


  "min/max" - types { implicit conn =>
    for {
      _ <- Entity.i.instant.insert(
        (1, instant1),
        (1, instant2),
        (1, instant3),
        (2, instant4),
        (2, instant5),
        (2, instant6),
      ).transact

      _ <- Entity.instant(min).query.get.map(_ ==> List(instant1))
      _ <- Entity.instant(max).query.get.map(_ ==> List(instant6))
      _ <- Entity.instant(min).instant(max).query.get.map(_ ==> List((instant1, instant6)))

      _ <- Entity.i.a1.instant(min).query.get.map(_ ==> List(
        (1, instant1),
        (2, instant4)
      ))

      _ <- Entity.i.a1.instant(max).query.get.map(_ ==> List(
        (1, instant3),
        (2, instant6)
      ))

      _ <- Entity.i.a1.instant(min).instant(max).query.get.map(_ ==> List(
        (1, instant1, instant3),
        (2, instant4, instant6)
      ))
    } yield ()
  }

  "min/max n" - types { implicit conn =>
    for {
      _ <- Entity.i.instant.insert(
        (1, instant1),
        (1, instant2),
        (1, instant3),
        (2, instant4),
        (2, instant5),
        (2, instant6),
        (2, instant6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.instant(min(1)).query.get.map(_ ==> List(Set(instant1)))
      _ <- Entity.instant(min(2)).query.get.map(_ ==> List(Set(instant1, instant2)))

      _ <- Entity.instant(max(1)).query.get.map(_ ==> List(Set(instant6)))
      _ <- Entity.instant(max(2)).query.get.map(_ ==> List(Set(instant5, instant6)))

      _ <- Entity.i.a1.instant(min(2)).query.get.map(_ ==> List(
        (1, Set(instant1, instant2)),
        (2, Set(instant4, instant5))
      ))

      _ <- Entity.i.a1.instant(max(2)).query.get.map(_ ==> List(
        (1, Set(instant2, instant3)),
        (2, Set(instant5, instant6))
      ))

      _ <- Entity.i.a1.instant(min(2)).instant(max(2)).query.get.map(_ ==> List(
        (1, Set(instant1, instant2), Set(instant2, instant3)),
        (2, Set(instant4, instant5), Set(instant5, instant6))
      ))
    } yield ()
  }


  "sample" - types { implicit futConn =>
    val all = Set(instant1, instant2, instant3, instant4)
    for {
      _ <- Entity.instant.insert(List(instant1, instant2, instant3)).transact
      _ <- Entity.instant(sample).query.get.map(res => all.contains(res.head) ==> true)
      _ <- Entity.instant(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.instant(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types { implicit conn =>
    for {
      _ <- Entity.i.instant.insert(List(
        (1, instant1),
        (2, instant2),
        (2, instant2),
        (2, instant3),
      )).transact

      _ <- Entity.instant(count).query.get.map(_ ==> List(4))
      _ <- Entity.i.a1.instant(count).query.get.map(_ ==> List(
        (1, 1),
        (2, 3)
      ))

      _ <- Entity.instant(countDistinct).query.get.map(_ ==> List(3))
      _ <- Entity.i.a1.instant(countDistinct).query.get.map(_ ==> List(
        (1, 1),
        (2, 2)
      ))
    } yield ()
  }
}