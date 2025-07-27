// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.any

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import org.scalactic.Equality
import org.scalactic.Equality

case class Aggr_BigDecimal_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types {
    for {
      _ <- Entity.i.bigDecimal.insert(List(
        (1, bigDecimal1),
        (2, bigDecimal2),
        (2, bigDecimal2),
        (2, bigDecimal3),
      )).transact

      _ <- Entity.i.bigDecimal.a1.query.get.map(_ ==> List(
        (1, bigDecimal1),
        (2, bigDecimal2), // 2 rows coalesced
        (2, bigDecimal3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.bigDecimal(distinct).query.get.map(_ ==> List(
        (1, Set(bigDecimal1)),
        (2, Set(bigDecimal2, bigDecimal3)),
      ))

      _ <- Entity.bigDecimal(distinct).query.get.map(_.head ==> Set(
        bigDecimal1, bigDecimal2, bigDecimal3
      ))
    } yield ()
  }


  "min/max" - types {
    for {
      _ <- Entity.i.bigDecimal.insert(
        (1, bigDecimal1),
        (1, bigDecimal2),
        (1, bigDecimal3),
        (2, bigDecimal4),
        (2, bigDecimal5),
        (2, bigDecimal6),
      ).transact

      _ <- Entity.bigDecimal(min).query.get.map(_ ==> List(bigDecimal1))
      _ <- Entity.bigDecimal(max).query.get.map(_ ==> List(bigDecimal6))
      _ <- Entity.bigDecimal(min).bigDecimal(max).query.get.map(_ ==> List((bigDecimal1, bigDecimal6)))

      _ <- Entity.i.a1.bigDecimal(min).query.get.map(_ ==> List(
        (1, bigDecimal1),
        (2, bigDecimal4)
      ))

      _ <- Entity.i.a1.bigDecimal(max).query.get.map(_ ==> List(
        (1, bigDecimal3),
        (2, bigDecimal6)
      ))

      _ <- Entity.i.a1.bigDecimal(min).bigDecimal(max).query.get.map(_ ==> List(
        (1, bigDecimal1, bigDecimal3),
        (2, bigDecimal4, bigDecimal6)
      ))
    } yield ()
  }

  "min/max n" - types {
    for {
      _ <- Entity.i.bigDecimal.insert(
        (1, bigDecimal1),
        (1, bigDecimal2),
        (1, bigDecimal3),
        (2, bigDecimal4),
        (2, bigDecimal5),
        (2, bigDecimal6),
        (2, bigDecimal6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.bigDecimal(min(1)).query.get.map(_ ==> List(Set(bigDecimal1)))
      _ <- Entity.bigDecimal(min(2)).query.get.map(_ ==> List(Set(bigDecimal1, bigDecimal2)))

      _ <- Entity.bigDecimal(max(1)).query.get.map(_ ==> List(Set(bigDecimal6)))
      _ <- Entity.bigDecimal(max(2)).query.get.map(_ ==> List(Set(bigDecimal5, bigDecimal6)))

      _ <- Entity.i.a1.bigDecimal(min(2)).query.get.map(_ ==> List(
        (1, Set(bigDecimal1, bigDecimal2)),
        (2, Set(bigDecimal4, bigDecimal5))
      ))

      _ <- Entity.i.a1.bigDecimal(max(2)).query.get.map(_ ==> List(
        (1, Set(bigDecimal2, bigDecimal3)),
        (2, Set(bigDecimal5, bigDecimal6))
      ))

      _ <- Entity.i.a1.bigDecimal(min(2)).bigDecimal(max(2)).query.get.map(_ ==> List(
        (1, Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3)),
        (2, Set(bigDecimal4, bigDecimal5), Set(bigDecimal5, bigDecimal6))
      ))
    } yield ()
  }


  "sample" - types {
    val all = Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4)
    for {
      _ <- Entity.bigDecimal.insert(List(bigDecimal1, bigDecimal2, bigDecimal3)).transact
      _ <- Entity.bigDecimal(sample).query.get.map(res => all.contains(res.head) ==> true)
      _ <- Entity.bigDecimal(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.bigDecimal(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types {
    for {
      _ <- Entity.i.bigDecimal.insert(List(
        (1, bigDecimal1),
        (2, bigDecimal2),
        (2, bigDecimal2),
        (2, bigDecimal3),
      )).transact

      _ <- Entity.bigDecimal(count).query.get.map(_ ==> List(4))
      _ <- Entity.i.a1.bigDecimal(count).query.get.map(_ ==> List(
        (1, 1),
        (2, 3)
      ))

      _ <- Entity.bigDecimal(countDistinct).query.get.map(_ ==> List(3))
      _ <- Entity.i.a1.bigDecimal(countDistinct).query.get.map(_ ==> List(
        (1, 1),
        (2, 2)
      ))
    } yield ()
  }
}