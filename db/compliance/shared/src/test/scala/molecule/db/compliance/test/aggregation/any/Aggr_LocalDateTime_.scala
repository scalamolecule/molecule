// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.any

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class Aggr_LocalDateTime_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types { implicit conn =>
    for {
      _ <- Entity.i.localDateTime.insert(List(
        (1, localDateTime1),
        (2, localDateTime2),
        (2, localDateTime2),
        (2, localDateTime3),
      )).transact

      _ <- Entity.i.localDateTime.a1.query.get.map(_ ==> List(
        (1, localDateTime1),
        (2, localDateTime2), // 2 rows coalesced
        (2, localDateTime3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.localDateTime(distinct).query.get.map(_ ==> List(
        (1, Set(localDateTime1)),
        (2, Set(localDateTime2, localDateTime3)),
      ))

      _ <- Entity.localDateTime(distinct).query.get.map(_.head ==> Set(
        localDateTime1, localDateTime2, localDateTime3
      ))
    } yield ()
  }


  "min/max" - types { implicit conn =>
    for {
      _ <- Entity.i.localDateTime.insert(
        (1, localDateTime1),
        (1, localDateTime2),
        (1, localDateTime3),
        (2, localDateTime4),
        (2, localDateTime5),
        (2, localDateTime6),
      ).transact

      _ <- Entity.localDateTime(min).query.get.map(_ ==> List(localDateTime1))
      _ <- Entity.localDateTime(max).query.get.map(_ ==> List(localDateTime6))
      _ <- Entity.localDateTime(min).localDateTime(max).query.get.map(_ ==> List((localDateTime1, localDateTime6)))

      _ <- Entity.i.a1.localDateTime(min).query.get.map(_ ==> List(
        (1, localDateTime1),
        (2, localDateTime4)
      ))

      _ <- Entity.i.a1.localDateTime(max).query.get.map(_ ==> List(
        (1, localDateTime3),
        (2, localDateTime6)
      ))

      _ <- Entity.i.a1.localDateTime(min).localDateTime(max).query.get.map(_ ==> List(
        (1, localDateTime1, localDateTime3),
        (2, localDateTime4, localDateTime6)
      ))
    } yield ()
  }

  "min/max n" - types { implicit conn =>
    for {
      _ <- Entity.i.localDateTime.insert(
        (1, localDateTime1),
        (1, localDateTime2),
        (1, localDateTime3),
        (2, localDateTime4),
        (2, localDateTime5),
        (2, localDateTime6),
        (2, localDateTime6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.localDateTime(min(1)).query.get.map(_ ==> List(Set(localDateTime1)))
      _ <- Entity.localDateTime(min(2)).query.get.map(_ ==> List(Set(localDateTime1, localDateTime2)))

      _ <- Entity.localDateTime(max(1)).query.get.map(_ ==> List(Set(localDateTime6)))
      _ <- Entity.localDateTime(max(2)).query.get.map(_ ==> List(Set(localDateTime5, localDateTime6)))

      _ <- Entity.i.a1.localDateTime(min(2)).query.get.map(_ ==> List(
        (1, Set(localDateTime1, localDateTime2)),
        (2, Set(localDateTime4, localDateTime5))
      ))

      _ <- Entity.i.a1.localDateTime(max(2)).query.get.map(_ ==> List(
        (1, Set(localDateTime2, localDateTime3)),
        (2, Set(localDateTime5, localDateTime6))
      ))

      _ <- Entity.i.a1.localDateTime(min(2)).localDateTime(max(2)).query.get.map(_ ==> List(
        (1, Set(localDateTime1, localDateTime2), Set(localDateTime2, localDateTime3)),
        (2, Set(localDateTime4, localDateTime5), Set(localDateTime5, localDateTime6))
      ))
    } yield ()
  }


  "sample" - types { implicit futConn =>
    val all = Set(localDateTime1, localDateTime2, localDateTime3, localDateTime4)
    for {
      _ <- Entity.localDateTime.insert(List(localDateTime1, localDateTime2, localDateTime3)).transact
      _ <- Entity.localDateTime(sample).query.get.map(res => all.contains(res.head) ==> true)
      _ <- Entity.localDateTime(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.localDateTime(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types { implicit conn =>
    for {
      _ <- Entity.i.localDateTime.insert(List(
        (1, localDateTime1),
        (2, localDateTime2),
        (2, localDateTime2),
        (2, localDateTime3),
      )).transact

      _ <- Entity.localDateTime(count).query.get.map(_ ==> List(4))
      _ <- Entity.i.a1.localDateTime(count).query.get.map(_ ==> List(
        (1, 1),
        (2, 3)
      ))

      _ <- Entity.localDateTime(countDistinct).query.get.map(_ ==> List(3))
      _ <- Entity.i.a1.localDateTime(countDistinct).query.get.map(_ ==> List(
        (1, 1),
        (2, 2)
      ))
    } yield ()
  }
}