// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.any

import molecule.db.compliance.setup.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.domains.dsl.Refs.*

case class Aggr_Byte_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types { implicit conn =>
    for {
      _ <- Entity.i.byte.insert(List(
        (1, byte1),
        (2, byte2),
        (2, byte2),
        (2, byte3),
      )).transact

      _ <- Entity.i.byte.a1.query.get.map(_ ==> List(
        (1, byte1),
        (2, byte2), // 2 rows coalesced
        (2, byte3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.byte(distinct).query.get.map(_ ==> List(
        (1, Set(byte1)),
        (2, Set(byte2, byte3)),
      ))

      _ <- Entity.byte(distinct).query.get.map(_.head ==> Set(
        byte1, byte2, byte3
      ))
    } yield ()
  }


  "min/max" - types { implicit conn =>
    for {
      _ <- Entity.i.byte.insert(
        (1, byte1),
        (1, byte2),
        (1, byte3),
        (2, byte4),
        (2, byte5),
        (2, byte6),
      ).transact

      _ <- Entity.byte(min).query.get.map(_ ==> List(byte1))
      _ <- Entity.byte(max).query.get.map(_ ==> List(byte6))
      _ <- Entity.byte(min).byte(max).query.get.map(_ ==> List((byte1, byte6)))

      _ <- Entity.i.a1.byte(min).query.get.map(_ ==> List(
        (1, byte1),
        (2, byte4)
      ))

      _ <- Entity.i.a1.byte(max).query.get.map(_ ==> List(
        (1, byte3),
        (2, byte6)
      ))

      _ <- Entity.i.a1.byte(min).byte(max).query.get.map(_ ==> List(
        (1, byte1, byte3),
        (2, byte4, byte6)
      ))
    } yield ()
  }

  "min/max n" - types { implicit conn =>
    for {
      _ <- Entity.i.byte.insert(
        (1, byte1),
        (1, byte2),
        (1, byte3),
        (2, byte4),
        (2, byte5),
        (2, byte6),
        (2, byte6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.byte(min(1)).query.get.map(_ ==> List(Set(byte1)))
      _ <- Entity.byte(min(2)).query.get.map(_ ==> List(Set(byte1, byte2)))

      _ <- Entity.byte(max(1)).query.get.map(_ ==> List(Set(byte6)))
      _ <- Entity.byte(max(2)).query.get.map(_ ==> List(Set(byte5, byte6)))

      _ <- Entity.i.a1.byte(min(2)).query.get.map(_ ==> List(
        (1, Set(byte1, byte2)),
        (2, Set(byte4, byte5))
      ))

      _ <- Entity.i.a1.byte(max(2)).query.get.map(_ ==> List(
        (1, Set(byte2, byte3)),
        (2, Set(byte5, byte6))
      ))

      _ <- Entity.i.a1.byte(min(2)).byte(max(2)).query.get.map(_ ==> List(
        (1, Set(byte1, byte2), Set(byte2, byte3)),
        (2, Set(byte4, byte5), Set(byte5, byte6))
      ))
    } yield ()
  }


  "sample" - types { implicit futConn =>
    val all = Set(byte1, byte2, byte3, byte4)
    for {
      _ <- Entity.byte.insert(List(byte1, byte2, byte3)).transact
      _ <- Entity.byte(sample).query.get.map(res => all.contains(res.head) ==> true)
      _ <- Entity.byte(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.byte(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types { implicit conn =>
    for {
      _ <- Entity.i.byte.insert(List(
        (1, byte1),
        (2, byte2),
        (2, byte2),
        (2, byte3),
      )).transact

      _ <- Entity.byte(count).query.get.map(_ ==> List(4))
      _ <- Entity.i.a1.byte(count).query.get.map(_ ==> List(
        (1, 1),
        (2, 3)
      ))

      _ <- Entity.byte(countDistinct).query.get.map(_ ==> List(3))
      _ <- Entity.i.a1.byte(countDistinct).query.get.map(_ ==> List(
        (1, 1),
        (2, 2)
      ))
    } yield ()
  }
}