// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.any

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class Aggr_Double_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types {
    for {
      _ <- Entity.i.double.insert(List(
        (1, double1),
        (2, double2),
        (2, double2),
        (2, double3),
      )).transact

      _ <- Entity.i.double.a1.query.get.map(_ ==> List(
        (1, double1),
        (2, double2), // 2 rows coalesced
        (2, double3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.double(distinct).query.get.map(_ ==> List(
        (1, Set(double1)),
        (2, Set(double2, double3)),
      ))

      _ <- Entity.double(distinct).query.get.map(_.head ==> Set(
        double1, double2, double3
      ))
    } yield ()
  }


  "min/max" - types {
    for {
      _ <- Entity.i.double.insert(
        (1, double1),
        (1, double2),
        (1, double3),
        (2, double4),
        (2, double5),
        (2, double6),
      ).transact

      _ <- Entity.double(min).query.get.map(_ ==> List(double1))
      _ <- Entity.double(max).query.get.map(_ ==> List(double6))
      _ <- Entity.double(min).double(max).query.get.map(_ ==> List((double1, double6)))

      _ <- Entity.i.a1.double(min).query.get.map(_ ==> List(
        (1, double1),
        (2, double4)
      ))

      _ <- Entity.i.a1.double(max).query.get.map(_ ==> List(
        (1, double3),
        (2, double6)
      ))

      _ <- Entity.i.a1.double(min).double(max).query.get.map(_ ==> List(
        (1, double1, double3),
        (2, double4, double6)
      ))
    } yield ()
  }

  "min/max n" - types {
    for {
      _ <- Entity.i.double.insert(
        (1, double1),
        (1, double2),
        (1, double3),
        (2, double4),
        (2, double5),
        (2, double6),
        (2, double6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.double(min(1)).query.get.map(_ ==> List(Set(double1)))
      _ <- Entity.double(min(2)).query.get.map(_ ==> List(Set(double1, double2)))

      _ <- Entity.double(max(1)).query.get.map(_ ==> List(Set(double6)))
      _ <- Entity.double(max(2)).query.get.map(_ ==> List(Set(double5, double6)))

      _ <- Entity.i.a1.double(min(2)).query.get.map(_ ==> List(
        (1, Set(double1, double2)),
        (2, Set(double4, double5))
      ))

      _ <- Entity.i.a1.double(max(2)).query.get.map(_ ==> List(
        (1, Set(double2, double3)),
        (2, Set(double5, double6))
      ))

      _ <- Entity.i.a1.double(min(2)).double(max(2)).query.get.map(_ ==> List(
        (1, Set(double1, double2), Set(double2, double3)),
        (2, Set(double4, double5), Set(double5, double6))
      ))
    } yield ()
  }


  "sample" - types {
    val all = Set(double1, double2, double3, double4)
    for {
      _ <- Entity.double.insert(List(double1, double2, double3)).transact
      _ <- Entity.double(sample).query.get.map(res => all.contains(res.head) ==> true)
      _ <- Entity.double(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.double(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types {
    for {
      _ <- Entity.i.double.insert(List(
        (1, double1),
        (2, double2),
        (2, double2),
        (2, double3),
      )).transact

      _ <- Entity.double(count).query.get.map(_ ==> List(4))
      _ <- Entity.i.a1.double(count).query.get.map(_ ==> List(
        (1, 1),
        (2, 3)
      ))

      _ <- Entity.double(countDistinct).query.get.map(_ ==> List(3))
      _ <- Entity.i.a1.double(countDistinct).query.get.map(_ ==> List(
        (1, 1),
        (2, 2)
      ))
    } yield ()
  }
}