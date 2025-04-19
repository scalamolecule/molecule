// GENERATED CODE ********************************
package molecule.coreTests.spi.aggregation.any

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor.*
import molecule.coreTests.domains.dsl.Types.*
import molecule.coreTests.setup.*

case class Aggr_Short_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types { implicit conn =>
    for {
      _ <- Entity.i.short.insert(List(
        (1, short1),
        (2, short2),
        (2, short2),
        (2, short3),
      )).transact

      _ <- Entity.i.short.a1.query.get.map(_ ==> List(
        (1, short1),
        (2, short2), // 2 rows coalesced
        (2, short3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.short(distinct).query.get.map(_ ==> List(
        (1, Set(short1)),
        (2, Set(short2, short3)),
      ))

      _ <- Entity.short(distinct).query.get.map(_.head ==> Set(
        short1, short2, short3
      ))
    } yield ()
  }


  "min/max" - types { implicit conn =>
    for {
      _ <- Entity.i.short.insert(
        (1, short1),
        (1, short2),
        (1, short3),
        (2, short4),
        (2, short5),
        (2, short6),
      ).transact

      _ <- Entity.short(min).query.get.map(_ ==> List(short1))
      _ <- Entity.short(max).query.get.map(_ ==> List(short6))
      _ <- Entity.short(min).short(max).query.get.map(_ ==> List((short1, short6)))

      _ <- Entity.i.a1.short(min).query.get.map(_ ==> List(
        (1, short1),
        (2, short4)
      ))

      _ <- Entity.i.a1.short(max).query.get.map(_ ==> List(
        (1, short3),
        (2, short6)
      ))

      _ <- Entity.i.a1.short(min).short(max).query.get.map(_ ==> List(
        (1, short1, short3),
        (2, short4, short6)
      ))
    } yield ()
  }

  "min/max n" - types { implicit conn =>
    for {
      _ <- Entity.i.short.insert(
        (1, short1),
        (1, short2),
        (1, short3),
        (2, short4),
        (2, short5),
        (2, short6),
        (2, short6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.short(min(1)).query.get.map(_ ==> List(Set(short1)))
      _ <- Entity.short(min(2)).query.get.map(_ ==> List(Set(short1, short2)))

      _ <- Entity.short(max(1)).query.get.map(_ ==> List(Set(short6)))
      _ <- Entity.short(max(2)).query.get.map(_ ==> List(Set(short5, short6)))

      _ <- Entity.i.a1.short(min(2)).query.get.map(_ ==> List(
        (1, Set(short1, short2)),
        (2, Set(short4, short5))
      ))

      _ <- Entity.i.a1.short(max(2)).query.get.map(_ ==> List(
        (1, Set(short2, short3)),
        (2, Set(short5, short6))
      ))

      _ <- Entity.i.a1.short(min(2)).short(max(2)).query.get.map(_ ==> List(
        (1, Set(short1, short2), Set(short2, short3)),
        (2, Set(short4, short5), Set(short5, short6))
      ))
    } yield ()
  }


  "sample" - types { implicit futConn =>
    val all = Set(short1, short2, short3, short4)
    for {
      _ <- Entity.short.insert(List(short1, short2, short3)).transact
      _ <- Entity.short(sample).query.get.map(res => all.contains(res.head) ==> true)
      _ <- Entity.short(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.short(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types { implicit conn =>
    for {
      _ <- Entity.i.short.insert(List(
        (1, short1),
        (2, short2),
        (2, short2),
        (2, short3),
      )).transact

      _ <- Entity.short(count).query.get.map(_ ==> List(4))
      _ <- Entity.i.a1.short(count).query.get.map(_ ==> List(
        (1, 1),
        (2, 3)
      ))

      _ <- Entity.short(countDistinct).query.get.map(_ ==> List(3))
      _ <- Entity.i.a1.short(countDistinct).query.get.map(_ ==> List(
        (1, 1),
        (2, 2)
      ))
    } yield ()
  }
}