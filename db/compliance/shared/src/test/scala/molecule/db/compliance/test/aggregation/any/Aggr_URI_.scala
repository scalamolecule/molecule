// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.any

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class Aggr_URI_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types { implicit conn =>
    for {
      _ <- Entity.i.uri.insert(List(
        (1, uri1),
        (2, uri2),
        (2, uri2),
        (2, uri3),
      )).transact

      _ <- Entity.i.uri.a1.query.get.map(_ ==> List(
        (1, uri1),
        (2, uri2), // 2 rows coalesced
        (2, uri3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.uri(distinct).query.get.map(_ ==> List(
        (1, Set(uri1)),
        (2, Set(uri2, uri3)),
      ))

      _ <- Entity.uri(distinct).query.get.map(_.head ==> Set(
        uri1, uri2, uri3
      ))
    } yield ()
  }


  "min/max" - types { implicit conn =>
    for {
      _ <- Entity.i.uri.insert(
        (1, uri1),
        (1, uri2),
        (1, uri3),
        (2, uri4),
        (2, uri5),
        (2, uri6),
      ).transact

      _ <- Entity.uri(min).query.get.map(_ ==> List(uri1))
      _ <- Entity.uri(max).query.get.map(_ ==> List(uri6))
      _ <- Entity.uri(min).uri(max).query.get.map(_ ==> List((uri1, uri6)))

      _ <- Entity.i.a1.uri(min).query.get.map(_ ==> List(
        (1, uri1),
        (2, uri4)
      ))

      _ <- Entity.i.a1.uri(max).query.get.map(_ ==> List(
        (1, uri3),
        (2, uri6)
      ))

      _ <- Entity.i.a1.uri(min).uri(max).query.get.map(_ ==> List(
        (1, uri1, uri3),
        (2, uri4, uri6)
      ))
    } yield ()
  }

  "min/max n" - types { implicit conn =>
    for {
      _ <- Entity.i.uri.insert(
        (1, uri1),
        (1, uri2),
        (1, uri3),
        (2, uri4),
        (2, uri5),
        (2, uri6),
        (2, uri6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.uri(min(1)).query.get.map(_ ==> List(Set(uri1)))
      _ <- Entity.uri(min(2)).query.get.map(_ ==> List(Set(uri1, uri2)))

      _ <- Entity.uri(max(1)).query.get.map(_ ==> List(Set(uri6)))
      _ <- Entity.uri(max(2)).query.get.map(_ ==> List(Set(uri5, uri6)))

      _ <- Entity.i.a1.uri(min(2)).query.get.map(_ ==> List(
        (1, Set(uri1, uri2)),
        (2, Set(uri4, uri5))
      ))

      _ <- Entity.i.a1.uri(max(2)).query.get.map(_ ==> List(
        (1, Set(uri2, uri3)),
        (2, Set(uri5, uri6))
      ))

      _ <- Entity.i.a1.uri(min(2)).uri(max(2)).query.get.map(_ ==> List(
        (1, Set(uri1, uri2), Set(uri2, uri3)),
        (2, Set(uri4, uri5), Set(uri5, uri6))
      ))
    } yield ()
  }


  "sample" - types { implicit futConn =>
    val all = Set(uri1, uri2, uri3, uri4)
    for {
      _ <- Entity.uri.insert(List(uri1, uri2, uri3)).transact
      _ <- Entity.uri(sample).query.get.map(res => all.contains(res.head) ==> true)
      _ <- Entity.uri(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.uri(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types { implicit conn =>
    for {
      _ <- Entity.i.uri.insert(List(
        (1, uri1),
        (2, uri2),
        (2, uri2),
        (2, uri3),
      )).transact

      _ <- Entity.uri(count).query.get.map(_ ==> List(4))
      _ <- Entity.i.a1.uri(count).query.get.map(_ ==> List(
        (1, 1),
        (2, 3)
      ))

      _ <- Entity.uri(countDistinct).query.get.map(_ ==> List(3))
      _ <- Entity.i.a1.uri(countDistinct).query.get.map(_ ==> List(
        (1, 1),
        (2, 2)
      ))
    } yield ()
  }
}