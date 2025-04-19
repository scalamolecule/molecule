// GENERATED CODE ********************************
package molecule.coreTests.spi.aggregation.any

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor.*
import molecule.coreTests.domains.dsl.Types.*
import molecule.coreTests.setup.*

case class Aggr_BigInt_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types { implicit conn =>
    for {
      _ <- Entity.i.bigInt.insert(List(
        (1, bigInt1),
        (2, bigInt2),
        (2, bigInt2),
        (2, bigInt3),
      )).transact

      _ <- Entity.i.bigInt.a1.query.get.map(_ ==> List(
        (1, bigInt1),
        (2, bigInt2), // 2 rows coalesced
        (2, bigInt3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.bigInt(distinct).query.get.map(_ ==> List(
        (1, Set(bigInt1)),
        (2, Set(bigInt2, bigInt3)),
      ))

      _ <- Entity.bigInt(distinct).query.get.map(_.head ==> Set(
        bigInt1, bigInt2, bigInt3
      ))
    } yield ()
  }


  "min/max" - types { implicit conn =>
    for {
      _ <- Entity.i.bigInt.insert(
        (1, bigInt1),
        (1, bigInt2),
        (1, bigInt3),
        (2, bigInt4),
        (2, bigInt5),
        (2, bigInt6),
      ).transact

      _ <- Entity.bigInt(min).query.get.map(_ ==> List(bigInt1))
      _ <- Entity.bigInt(max).query.get.map(_ ==> List(bigInt6))
      _ <- Entity.bigInt(min).bigInt(max).query.get.map(_ ==> List((bigInt1, bigInt6)))

      _ <- Entity.i.a1.bigInt(min).query.get.map(_ ==> List(
        (1, bigInt1),
        (2, bigInt4)
      ))

      _ <- Entity.i.a1.bigInt(max).query.get.map(_ ==> List(
        (1, bigInt3),
        (2, bigInt6)
      ))

      _ <- Entity.i.a1.bigInt(min).bigInt(max).query.get.map(_ ==> List(
        (1, bigInt1, bigInt3),
        (2, bigInt4, bigInt6)
      ))
    } yield ()
  }

  "min/max n" - types { implicit conn =>
    for {
      _ <- Entity.i.bigInt.insert(
        (1, bigInt1),
        (1, bigInt2),
        (1, bigInt3),
        (2, bigInt4),
        (2, bigInt5),
        (2, bigInt6),
        (2, bigInt6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.bigInt(min(1)).query.get.map(_ ==> List(Set(bigInt1)))
      _ <- Entity.bigInt(min(2)).query.get.map(_ ==> List(Set(bigInt1, bigInt2)))

      _ <- Entity.bigInt(max(1)).query.get.map(_ ==> List(Set(bigInt6)))
      _ <- Entity.bigInt(max(2)).query.get.map(_ ==> List(Set(bigInt5, bigInt6)))

      _ <- Entity.i.a1.bigInt(min(2)).query.get.map(_ ==> List(
        (1, Set(bigInt1, bigInt2)),
        (2, Set(bigInt4, bigInt5))
      ))

      _ <- Entity.i.a1.bigInt(max(2)).query.get.map(_ ==> List(
        (1, Set(bigInt2, bigInt3)),
        (2, Set(bigInt5, bigInt6))
      ))

      _ <- Entity.i.a1.bigInt(min(2)).bigInt(max(2)).query.get.map(_ ==> List(
        (1, Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)),
        (2, Set(bigInt4, bigInt5), Set(bigInt5, bigInt6))
      ))
    } yield ()
  }


  "sample" - types { implicit futConn =>
    val all = Set(bigInt1, bigInt2, bigInt3, bigInt4)
    for {
      _ <- Entity.bigInt.insert(List(bigInt1, bigInt2, bigInt3)).transact
      _ <- Entity.bigInt(sample).query.get.map(res => all.contains(res.head) ==> true)
      _ <- Entity.bigInt(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.bigInt(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types { implicit conn =>
    for {
      _ <- Entity.i.bigInt.insert(List(
        (1, bigInt1),
        (2, bigInt2),
        (2, bigInt2),
        (2, bigInt3),
      )).transact

      _ <- Entity.bigInt(count).query.get.map(_ ==> List(4))
      _ <- Entity.i.a1.bigInt(count).query.get.map(_ ==> List(
        (1, 1),
        (2, 3)
      ))

      _ <- Entity.bigInt(countDistinct).query.get.map(_ ==> List(3))
      _ <- Entity.i.a1.bigInt(countDistinct).query.get.map(_ ==> List(
        (1, 1),
        (2, 2)
      ))
    } yield ()
  }
}