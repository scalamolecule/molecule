// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.any

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class Aggr_String_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types {
    for {
      _ <- Entity.i.string.insert(List(
        (1, string1),
        (2, string2),
        (2, string2),
        (2, string3),
      )).transact

      _ <- Entity.i.string.a1.query.get.map(_ ==> List(
        (1, string1),
        (2, string2), // 2 rows coalesced
        (2, string3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.string(distinct).query.get.map(_ ==> List(
        (1, Set(string1)),
        (2, Set(string2, string3)),
      ))

      _ <- Entity.string(distinct).query.get.map(_.head ==> Set(
        string1, string2, string3
      ))
    } yield ()
  }


  "min/max" - types {
    for {
      _ <- Entity.i.string.insert(
        (1, string1),
        (1, string2),
        (1, string3),
        (2, string4),
        (2, string5),
        (2, string6),
      ).transact

      _ <- Entity.string(min).query.get.map(_ ==> List(string1))
      _ <- Entity.string(max).query.get.map(_ ==> List(string6))
      _ <- Entity.string(min).string(max).query.get.map(_ ==> List((string1, string6)))

      _ <- Entity.i.a1.string(min).query.get.map(_ ==> List(
        (1, string1),
        (2, string4)
      ))

      _ <- Entity.i.a1.string(max).query.get.map(_ ==> List(
        (1, string3),
        (2, string6)
      ))

      _ <- Entity.i.a1.string(min).string(max).query.get.map(_ ==> List(
        (1, string1, string3),
        (2, string4, string6)
      ))
    } yield ()
  }

  "min/max n" - types {
    for {
      _ <- Entity.i.string.insert(
        (1, string1),
        (1, string2),
        (1, string3),
        (2, string4),
        (2, string5),
        (2, string6),
        (2, string6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.string(min(1)).query.get.map(_ ==> List(Set(string1)))
      _ <- Entity.string(min(2)).query.get.map(_ ==> List(Set(string1, string2)))

      _ <- Entity.string(max(1)).query.get.map(_ ==> List(Set(string6)))
      _ <- Entity.string(max(2)).query.get.map(_ ==> List(Set(string5, string6)))

      _ <- Entity.i.a1.string(min(2)).query.get.map(_ ==> List(
        (1, Set(string1, string2)),
        (2, Set(string4, string5))
      ))

      _ <- Entity.i.a1.string(max(2)).query.get.map(_ ==> List(
        (1, Set(string2, string3)),
        (2, Set(string5, string6))
      ))

      _ <- Entity.i.a1.string(min(2)).string(max(2)).query.get.map(_ ==> List(
        (1, Set(string1, string2), Set(string2, string3)),
        (2, Set(string4, string5), Set(string5, string6))
      ))
    } yield ()
  }


  "sample" - types {
    val all = Set(string1, string2, string3, string4)
    for {
      _ <- Entity.string.insert(List(string1, string2, string3)).transact
      _ <- Entity.string(sample).query.get.map(res => all.contains(res.head) ==> true)
      _ <- Entity.string(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.string(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types {
    for {
      _ <- Entity.i.string.insert(List(
        (1, string1),
        (2, string2),
        (2, string2),
        (2, string3),
      )).transact

      _ <- Entity.string(count).query.get.map(_ ==> List(4))
      _ <- Entity.i.a1.string(count).query.get.map(_ ==> List(
        (1, 1),
        (2, 3)
      ))

      _ <- Entity.string(countDistinct).query.get.map(_ ==> List(3))
      _ <- Entity.i.a1.string(countDistinct).query.get.map(_ ==> List(
        (1, 1),
        (2, 2)
      ))
    } yield ()
  }
}