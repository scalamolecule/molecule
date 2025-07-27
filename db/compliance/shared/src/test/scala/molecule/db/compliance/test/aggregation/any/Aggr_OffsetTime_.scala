// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.any

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class Aggr_OffsetTime_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types {
    for {
      _ <- Entity.i.offsetTime.insert(List(
        (1, offsetTime1),
        (2, offsetTime2),
        (2, offsetTime2),
        (2, offsetTime3),
      )).transact

      _ <- Entity.i.offsetTime.a1.query.get.map(_ ==> List(
        (1, offsetTime1),
        (2, offsetTime2), // 2 rows coalesced
        (2, offsetTime3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.offsetTime(distinct).query.get.map(_ ==> List(
        (1, Set(offsetTime1)),
        (2, Set(offsetTime2, offsetTime3)),
      ))

      _ <- Entity.offsetTime(distinct).query.get.map(_.head ==> Set(
        offsetTime1, offsetTime2, offsetTime3
      ))
    } yield ()
  }


  "min/max" - types {
    for {
      _ <- Entity.i.offsetTime.insert(
        (1, offsetTime1),
        (1, offsetTime2),
        (1, offsetTime3),
        (2, offsetTime4),
        (2, offsetTime5),
        (2, offsetTime6),
      ).transact

      _ <- Entity.offsetTime(min).query.get.map(_ ==> List(offsetTime1))
      _ <- Entity.offsetTime(max).query.get.map(_ ==> List(offsetTime6))
      _ <- Entity.offsetTime(min).offsetTime(max).query.get.map(_ ==> List((offsetTime1, offsetTime6)))

      _ <- Entity.i.a1.offsetTime(min).query.get.map(_ ==> List(
        (1, offsetTime1),
        (2, offsetTime4)
      ))

      _ <- Entity.i.a1.offsetTime(max).query.get.map(_ ==> List(
        (1, offsetTime3),
        (2, offsetTime6)
      ))

      _ <- Entity.i.a1.offsetTime(min).offsetTime(max).query.get.map(_ ==> List(
        (1, offsetTime1, offsetTime3),
        (2, offsetTime4, offsetTime6)
      ))
    } yield ()
  }

  "min/max n" - types {
    for {
      _ <- Entity.i.offsetTime.insert(
        (1, offsetTime1),
        (1, offsetTime2),
        (1, offsetTime3),
        (2, offsetTime4),
        (2, offsetTime5),
        (2, offsetTime6),
        (2, offsetTime6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.offsetTime(min(1)).query.get.map(_ ==> List(Set(offsetTime1)))
      _ <- Entity.offsetTime(min(2)).query.get.map(_ ==> List(Set(offsetTime1, offsetTime2)))

      _ <- Entity.offsetTime(max(1)).query.get.map(_ ==> List(Set(offsetTime6)))
      _ <- Entity.offsetTime(max(2)).query.get.map(_ ==> List(Set(offsetTime5, offsetTime6)))

      _ <- Entity.i.a1.offsetTime(min(2)).query.get.map(_ ==> List(
        (1, Set(offsetTime1, offsetTime2)),
        (2, Set(offsetTime4, offsetTime5))
      ))

      _ <- Entity.i.a1.offsetTime(max(2)).query.get.map(_ ==> List(
        (1, Set(offsetTime2, offsetTime3)),
        (2, Set(offsetTime5, offsetTime6))
      ))

      _ <- Entity.i.a1.offsetTime(min(2)).offsetTime(max(2)).query.get.map(_ ==> List(
        (1, Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3)),
        (2, Set(offsetTime4, offsetTime5), Set(offsetTime5, offsetTime6))
      ))
    } yield ()
  }


  "sample" - types {
    val all = Set(offsetTime1, offsetTime2, offsetTime3, offsetTime4)
    for {
      _ <- Entity.offsetTime.insert(List(offsetTime1, offsetTime2, offsetTime3)).transact
      _ <- Entity.offsetTime(sample).query.get.map(res => all.contains(res.head) ==> true)
      _ <- Entity.offsetTime(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.offsetTime(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types {
    for {
      _ <- Entity.i.offsetTime.insert(List(
        (1, offsetTime1),
        (2, offsetTime2),
        (2, offsetTime2),
        (2, offsetTime3),
      )).transact

      _ <- Entity.offsetTime(count).query.get.map(_ ==> List(4))
      _ <- Entity.i.a1.offsetTime(count).query.get.map(_ ==> List(
        (1, 1),
        (2, 3)
      ))

      _ <- Entity.offsetTime(countDistinct).query.get.map(_ ==> List(3))
      _ <- Entity.i.a1.offsetTime(countDistinct).query.get.map(_ ==> List(
        (1, 1),
        (2, 2)
      ))
    } yield ()
  }
}