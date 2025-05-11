// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.any

import molecule.db.compliance.setup.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.domains.dsl.Refs.*

case class Aggr_Date_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types { implicit conn =>
    for {
      _ <- Entity.i.date.insert(List(
        (1, date1),
        (2, date2),
        (2, date2),
        (2, date3),
      )).transact

      _ <- Entity.i.date.a1.query.get.map(_ ==> List(
        (1, date1),
        (2, date2), // 2 rows coalesced
        (2, date3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.date(distinct).query.get.map(_ ==> List(
        (1, Set(date1)),
        (2, Set(date2, date3)),
      ))

      _ <- Entity.date(distinct).query.get.map(_.head ==> Set(
        date1, date2, date3
      ))
    } yield ()
  }


  "min/max" - types { implicit conn =>
    for {
      _ <- Entity.i.date.insert(
        (1, date1),
        (1, date2),
        (1, date3),
        (2, date4),
        (2, date5),
        (2, date6),
      ).transact

      _ <- Entity.date(min).query.get.map(_ ==> List(date1))
      _ <- Entity.date(max).query.get.map(_ ==> List(date6))
      _ <- Entity.date(min).date(max).query.get.map(_ ==> List((date1, date6)))

      _ <- Entity.i.a1.date(min).query.get.map(_ ==> List(
        (1, date1),
        (2, date4)
      ))

      _ <- Entity.i.a1.date(max).query.get.map(_ ==> List(
        (1, date3),
        (2, date6)
      ))

      _ <- Entity.i.a1.date(min).date(max).query.get.map(_ ==> List(
        (1, date1, date3),
        (2, date4, date6)
      ))
    } yield ()
  }

  "min/max n" - types { implicit conn =>
    for {
      _ <- Entity.i.date.insert(
        (1, date1),
        (1, date2),
        (1, date3),
        (2, date4),
        (2, date5),
        (2, date6),
        (2, date6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.date(min(1)).query.get.map(_ ==> List(Set(date1)))
      _ <- Entity.date(min(2)).query.get.map(_ ==> List(Set(date1, date2)))

      _ <- Entity.date(max(1)).query.get.map(_ ==> List(Set(date6)))
      _ <- Entity.date(max(2)).query.get.map(_ ==> List(Set(date5, date6)))

      _ <- Entity.i.a1.date(min(2)).query.get.map(_ ==> List(
        (1, Set(date1, date2)),
        (2, Set(date4, date5))
      ))

      _ <- Entity.i.a1.date(max(2)).query.get.map(_ ==> List(
        (1, Set(date2, date3)),
        (2, Set(date5, date6))
      ))

      _ <- Entity.i.a1.date(min(2)).date(max(2)).query.get.map(_ ==> List(
        (1, Set(date1, date2), Set(date2, date3)),
        (2, Set(date4, date5), Set(date5, date6))
      ))
    } yield ()
  }


  "sample" - types { implicit futConn =>
    val all = Set(date1, date2, date3, date4)
    for {
      _ <- Entity.date.insert(List(date1, date2, date3)).transact
      _ <- Entity.date(sample).query.get.map(res => all.contains(res.head) ==> true)
      _ <- Entity.date(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.date(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types { implicit conn =>
    for {
      _ <- Entity.i.date.insert(List(
        (1, date1),
        (2, date2),
        (2, date2),
        (2, date3),
      )).transact

      _ <- Entity.date(count).query.get.map(_ ==> List(4))
      _ <- Entity.i.a1.date(count).query.get.map(_ ==> List(
        (1, 1),
        (2, 3)
      ))

      _ <- Entity.date(countDistinct).query.get.map(_ ==> List(3))
      _ <- Entity.i.a1.date(countDistinct).query.get.map(_ ==> List(
        (1, 1),
        (2, 2)
      ))
    } yield ()
  }
}