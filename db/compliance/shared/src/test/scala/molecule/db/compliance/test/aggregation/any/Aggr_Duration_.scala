// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.any

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class Aggr_Duration_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types { implicit conn =>
    for {
      _ <- Entity.i.duration.insert(List(
        (1, duration1),
        (2, duration2),
        (2, duration2),
        (2, duration3),
      )).transact

      _ <- Entity.i.duration.a1.query.get.map(_ ==> List(
        (1, duration1),
        (2, duration2), // 2 rows coalesced
        (2, duration3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.duration(distinct).query.get.map(_ ==> List(
        (1, Set(duration1)),
        (2, Set(duration2, duration3)),
      ))

      _ <- Entity.duration(distinct).query.get.map(_.head ==> Set(
        duration1, duration2, duration3
      ))
    } yield ()
  }


  "min/max" - types { implicit conn =>
    for {
      _ <- Entity.i.duration.insert(
        (1, duration1),
        (1, duration2),
        (1, duration3),
        (2, duration4),
        (2, duration5),
        (2, duration6),
      ).transact

      _ <- Entity.duration(min).query.get.map(_ ==> List(duration1))
      _ <- Entity.duration(max).query.get.map(_ ==> List(duration6))
      _ <- Entity.duration(min).duration(max).query.get.map(_ ==> List((duration1, duration6)))

      _ <- Entity.i.a1.duration(min).query.get.map(_ ==> List(
        (1, duration1),
        (2, duration4)
      ))

      _ <- Entity.i.a1.duration(max).query.get.map(_ ==> List(
        (1, duration3),
        (2, duration6)
      ))

      _ <- Entity.i.a1.duration(min).duration(max).query.get.map(_ ==> List(
        (1, duration1, duration3),
        (2, duration4, duration6)
      ))
    } yield ()
  }

  "min/max n" - types { implicit conn =>
    for {
      _ <- Entity.i.duration.insert(
        (1, duration1),
        (1, duration2),
        (1, duration3),
        (2, duration4),
        (2, duration5),
        (2, duration6),
        (2, duration6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.duration(min(1)).query.get.map(_ ==> List(Set(duration1)))
      _ <- Entity.duration(min(2)).query.get.map(_ ==> List(Set(duration1, duration2)))

      _ <- Entity.duration(max(1)).query.get.map(_ ==> List(Set(duration6)))
      _ <- Entity.duration(max(2)).query.get.map(_ ==> List(Set(duration5, duration6)))

      _ <- Entity.i.a1.duration(min(2)).query.get.map(_ ==> List(
        (1, Set(duration1, duration2)),
        (2, Set(duration4, duration5))
      ))

      _ <- Entity.i.a1.duration(max(2)).query.get.map(_ ==> List(
        (1, Set(duration2, duration3)),
        (2, Set(duration5, duration6))
      ))

      _ <- Entity.i.a1.duration(min(2)).duration(max(2)).query.get.map(_ ==> List(
        (1, Set(duration1, duration2), Set(duration2, duration3)),
        (2, Set(duration4, duration5), Set(duration5, duration6))
      ))
    } yield ()
  }


  "sample" - types { implicit futConn =>
    val all = Set(duration1, duration2, duration3, duration4)
    for {
      _ <- Entity.duration.insert(List(duration1, duration2, duration3)).transact
      _ <- Entity.duration(sample).query.get.map(res => all.contains(res.head) ==> true)
      _ <- Entity.duration(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.duration(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types { implicit conn =>
    for {
      _ <- Entity.i.duration.insert(List(
        (1, duration1),
        (2, duration2),
        (2, duration2),
        (2, duration3),
      )).transact

      _ <- Entity.duration(count).query.get.map(_ ==> List(4))
      _ <- Entity.i.a1.duration(count).query.get.map(_ ==> List(
        (1, 1),
        (2, 3)
      ))

      _ <- Entity.duration(countDistinct).query.get.map(_ ==> List(3))
      _ <- Entity.i.a1.duration(countDistinct).query.get.map(_ ==> List(
        (1, 1),
        (2, 2)
      ))
    } yield ()
  }
}