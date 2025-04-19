// GENERATED CODE ********************************
package molecule.coreTests.spi.aggregation.any

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor.*
import molecule.coreTests.domains.dsl.Types.*
import molecule.coreTests.setup.*

case class Aggr_OffsetDateTime_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types { implicit conn =>
    for {
      _ <- Entity.i.offsetDateTime.insert(List(
        (1, offsetDateTime1),
        (2, offsetDateTime2),
        (2, offsetDateTime2),
        (2, offsetDateTime3),
      )).transact

      _ <- Entity.i.offsetDateTime.a1.query.get.map(_ ==> List(
        (1, offsetDateTime1),
        (2, offsetDateTime2), // 2 rows coalesced
        (2, offsetDateTime3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.offsetDateTime(distinct).query.get.map(_ ==> List(
        (1, Set(offsetDateTime1)),
        (2, Set(offsetDateTime2, offsetDateTime3)),
      ))

      _ <- Entity.offsetDateTime(distinct).query.get.map(_.head ==> Set(
        offsetDateTime1, offsetDateTime2, offsetDateTime3
      ))
    } yield ()
  }


  "min/max" - types { implicit conn =>
    for {
      _ <- Entity.i.offsetDateTime.insert(
        (1, offsetDateTime1),
        (1, offsetDateTime2),
        (1, offsetDateTime3),
        (2, offsetDateTime4),
        (2, offsetDateTime5),
        (2, offsetDateTime6),
      ).transact

      _ <- Entity.offsetDateTime(min).query.get.map(_ ==> List(offsetDateTime1))
      _ <- Entity.offsetDateTime(max).query.get.map(_ ==> List(offsetDateTime6))
      _ <- Entity.offsetDateTime(min).offsetDateTime(max).query.get.map(_ ==> List((offsetDateTime1, offsetDateTime6)))

      _ <- Entity.i.a1.offsetDateTime(min).query.get.map(_ ==> List(
        (1, offsetDateTime1),
        (2, offsetDateTime4)
      ))

      _ <- Entity.i.a1.offsetDateTime(max).query.get.map(_ ==> List(
        (1, offsetDateTime3),
        (2, offsetDateTime6)
      ))

      _ <- Entity.i.a1.offsetDateTime(min).offsetDateTime(max).query.get.map(_ ==> List(
        (1, offsetDateTime1, offsetDateTime3),
        (2, offsetDateTime4, offsetDateTime6)
      ))
    } yield ()
  }

  "min/max n" - types { implicit conn =>
    for {
      _ <- Entity.i.offsetDateTime.insert(
        (1, offsetDateTime1),
        (1, offsetDateTime2),
        (1, offsetDateTime3),
        (2, offsetDateTime4),
        (2, offsetDateTime5),
        (2, offsetDateTime6),
        (2, offsetDateTime6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.offsetDateTime(min(1)).query.get.map(_ ==> List(Set(offsetDateTime1)))
      _ <- Entity.offsetDateTime(min(2)).query.get.map(_ ==> List(Set(offsetDateTime1, offsetDateTime2)))

      _ <- Entity.offsetDateTime(max(1)).query.get.map(_ ==> List(Set(offsetDateTime6)))
      _ <- Entity.offsetDateTime(max(2)).query.get.map(_ ==> List(Set(offsetDateTime5, offsetDateTime6)))

      _ <- Entity.i.a1.offsetDateTime(min(2)).query.get.map(_ ==> List(
        (1, Set(offsetDateTime1, offsetDateTime2)),
        (2, Set(offsetDateTime4, offsetDateTime5))
      ))

      _ <- Entity.i.a1.offsetDateTime(max(2)).query.get.map(_ ==> List(
        (1, Set(offsetDateTime2, offsetDateTime3)),
        (2, Set(offsetDateTime5, offsetDateTime6))
      ))

      _ <- Entity.i.a1.offsetDateTime(min(2)).offsetDateTime(max(2)).query.get.map(_ ==> List(
        (1, Set(offsetDateTime1, offsetDateTime2), Set(offsetDateTime2, offsetDateTime3)),
        (2, Set(offsetDateTime4, offsetDateTime5), Set(offsetDateTime5, offsetDateTime6))
      ))
    } yield ()
  }


  "sample" - types { implicit futConn =>
    val all = Set(offsetDateTime1, offsetDateTime2, offsetDateTime3, offsetDateTime4)
    for {
      _ <- Entity.offsetDateTime.insert(List(offsetDateTime1, offsetDateTime2, offsetDateTime3)).transact
      _ <- Entity.offsetDateTime(sample).query.get.map(res => all.contains(res.head) ==> true)
      _ <- Entity.offsetDateTime(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.offsetDateTime(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types { implicit conn =>
    for {
      _ <- Entity.i.offsetDateTime.insert(List(
        (1, offsetDateTime1),
        (2, offsetDateTime2),
        (2, offsetDateTime2),
        (2, offsetDateTime3),
      )).transact

      _ <- Entity.offsetDateTime(count).query.get.map(_ ==> List(4))
      _ <- Entity.i.a1.offsetDateTime(count).query.get.map(_ ==> List(
        (1, 1),
        (2, 3)
      ))

      _ <- Entity.offsetDateTime(countDistinct).query.get.map(_ ==> List(3))
      _ <- Entity.i.a1.offsetDateTime(countDistinct).query.get.map(_ ==> List(
        (1, 1),
        (2, 2)
      ))
    } yield ()
  }
}