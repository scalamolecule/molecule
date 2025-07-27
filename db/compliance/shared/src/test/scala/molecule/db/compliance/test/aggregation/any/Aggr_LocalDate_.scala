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

case class Aggr_LocalDate_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types {
    for {
      _ <- Entity.i.localDate.insert(List(
        (1, localDate1),
        (2, localDate2),
        (2, localDate2),
        (2, localDate3),
      )).transact

      _ <- Entity.i.localDate.a1.query.get.map(_ ==> List(
        (1, localDate1),
        (2, localDate2), // 2 rows coalesced
        (2, localDate3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.localDate(distinct).query.get.map(_ ==> List(
        (1, Set(localDate1)),
        (2, Set(localDate2, localDate3)),
      ))

      _ <- Entity.localDate(distinct).query.get.map(_.head ==> Set(
        localDate1, localDate2, localDate3
      ))
    } yield ()
  }


  "min/max" - types {
    for {
      _ <- Entity.i.localDate.insert(
        (1, localDate1),
        (1, localDate2),
        (1, localDate3),
        (2, localDate4),
        (2, localDate5),
        (2, localDate6),
      ).transact

      _ <- Entity.localDate(min).query.get.map(_ ==> List(localDate1))
      _ <- Entity.localDate(max).query.get.map(_ ==> List(localDate6))
      _ <- Entity.localDate(min).localDate(max).query.get.map(_ ==> List((localDate1, localDate6)))

      _ <- Entity.i.a1.localDate(min).query.get.map(_ ==> List(
        (1, localDate1),
        (2, localDate4)
      ))

      _ <- Entity.i.a1.localDate(max).query.get.map(_ ==> List(
        (1, localDate3),
        (2, localDate6)
      ))

      _ <- Entity.i.a1.localDate(min).localDate(max).query.get.map(_ ==> List(
        (1, localDate1, localDate3),
        (2, localDate4, localDate6)
      ))
    } yield ()
  }

  "min/max n" - types {
    for {
      _ <- Entity.i.localDate.insert(
        (1, localDate1),
        (1, localDate2),
        (1, localDate3),
        (2, localDate4),
        (2, localDate5),
        (2, localDate6),
        (2, localDate6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.localDate(min(1)).query.get.map(_ ==> List(Set(localDate1)))
      _ <- Entity.localDate(min(2)).query.get.map(_ ==> List(Set(localDate1, localDate2)))

      _ <- Entity.localDate(max(1)).query.get.map(_ ==> List(Set(localDate6)))
      _ <- Entity.localDate(max(2)).query.get.map(_ ==> List(Set(localDate5, localDate6)))

      _ <- Entity.i.a1.localDate(min(2)).query.get.map(_ ==> List(
        (1, Set(localDate1, localDate2)),
        (2, Set(localDate4, localDate5))
      ))

      _ <- Entity.i.a1.localDate(max(2)).query.get.map(_ ==> List(
        (1, Set(localDate2, localDate3)),
        (2, Set(localDate5, localDate6))
      ))

      _ <- Entity.i.a1.localDate(min(2)).localDate(max(2)).query.get.map(_ ==> List(
        (1, Set(localDate1, localDate2), Set(localDate2, localDate3)),
        (2, Set(localDate4, localDate5), Set(localDate5, localDate6))
      ))
    } yield ()
  }


  "sample" - types {
    val all = Set(localDate1, localDate2, localDate3, localDate4)
    for {
      _ <- Entity.localDate.insert(List(localDate1, localDate2, localDate3)).transact
      _ <- Entity.localDate(sample).query.get.map(res => all.contains(res.head) ==> true)
      _ <- Entity.localDate(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.localDate(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types {
    for {
      _ <- Entity.i.localDate.insert(List(
        (1, localDate1),
        (2, localDate2),
        (2, localDate2),
        (2, localDate3),
      )).transact

      _ <- Entity.localDate(count).query.get.map(_ ==> List(4))
      _ <- Entity.i.a1.localDate(count).query.get.map(_ ==> List(
        (1, 1),
        (2, 3)
      ))

      _ <- Entity.localDate(countDistinct).query.get.map(_ ==> List(3))
      _ <- Entity.i.a1.localDate(countDistinct).query.get.map(_ ==> List(
        (1, 1),
        (2, 2)
      ))
    } yield ()
  }
}