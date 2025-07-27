package molecule.db.compliance.test.aggregation.any

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import org.scalactic.Equality
import org.scalactic.Equality

case class Aggr_Int(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types {
    for {
      _ <- Entity.i.int.insert(List(
        (1, int1),
        (2, int2),
        (2, int2),
        (2, int3),
      )).transact

      _ <- Entity.i.int.a1.query.get.map(_ ==> List(
        (1, int1),
        (2, int2), // 2 rows coalesced
        (2, int3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.int(distinct).query.get.map(_ ==> List(
        (1, Set(int1)),
        (2, Set(int2, int3)),
      ))

      _ <- Entity.int(distinct).query.get.map(_.head ==> Set(
        int1, int2, int3
      ))
    } yield ()
  }


  "min/max" - types {
    for {
      _ <- Entity.i.int.insert(
        (1, int1),
        (1, int2),
        (1, int3),
        (2, int4),
        (2, int5),
        (2, int6),
      ).transact

      _ <- Entity.int(min).query.get.map(_ ==> List(int1))
      _ <- Entity.int(max).query.get.map(_ ==> List(int6))
      _ <- Entity.int(min).int(max).query.get.map(_ ==> List((int1, int6)))

      _ <- Entity.i.a1.int(min).query.get.map(_ ==> List(
        (1, int1),
        (2, int4)
      ))

      _ <- Entity.i.a1.int(max).query.get.map(_ ==> List(
        (1, int3),
        (2, int6)
      ))

      _ <- Entity.i.a1.int(min).int(max).query.get.map(_ ==> List(
        (1, int1, int3),
        (2, int4, int6)
      ))
    } yield ()
  }

  "min/max n" - types {
    for {
      _ <- Entity.i.int.insert(
        (1, int1),
        (1, int2),
        (1, int3),
        (2, int4),
        (2, int5),
        (2, int6),
        (2, int6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.int(min(1)).query.get.map(_ ==> List(Set(int1)))
      _ <- Entity.int(min(2)).query.get.map(_ ==> List(Set(int1, int2)))

      _ <- Entity.int(max(1)).query.get.map(_ ==> List(Set(int6)))
      _ <- Entity.int(max(2)).query.get.map(_ ==> List(Set(int5, int6)))

      _ <- Entity.i.a1.int(min(2)).query.get.map(_ ==> List(
        (1, Set(int1, int2)),
        (2, Set(int4, int5))
      ))

      _ <- Entity.i.a1.int(max(2)).query.get.map(_ ==> List(
        (1, Set(int2, int3)),
        (2, Set(int5, int6))
      ))

      _ <- Entity.i.a1.int(min(2)).int(max(2)).query.get.map(_ ==> List(
        (1, Set(int1, int2), Set(int2, int3)),
        (2, Set(int4, int5), Set(int5, int6))
      ))
    } yield ()
  }


  "sample" - types {
    val all = Set(int1, int2, int3, int4)
    for {
      _ <- Entity.int.insert(List(int1, int2, int3)).transact
      _ <- Entity.int(sample).query.get.map(res => all.contains(res.head) ==> true)
      _ <- Entity.int(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.int(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types {
    for {
      _ <- Entity.i.int.insert(List(
        (1, int1),
        (2, int2),
        (2, int2),
        (2, int3),
      )).transact

      _ <- Entity.int(count).query.get.map(_ ==> List(4))
      _ <- Entity.i.a1.int(count).query.get.map(_ ==> List(
        (1, 1),
        (2, 3)
      ))

      _ <- Entity.int(countDistinct).query.get.map(_ ==> List(3))
      _ <- Entity.i.a1.int(countDistinct).query.get.map(_ ==> List(
        (1, 1),
        (2, 2)
      ))
    } yield ()
  }
}