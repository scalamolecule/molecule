// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.any

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class Aggr_Char_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types {
    for {
      _ <- Entity.i.char.insert(List(
        (1, char1),
        (2, char2),
        (2, char2),
        (2, char3),
      )).transact

      _ <- Entity.i.char.a1.query.get.map(_ ==> List(
        (1, char1),
        (2, char2), // 2 rows coalesced
        (2, char3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.char(distinct).query.get.map(_ ==> List(
        (1, Set(char1)),
        (2, Set(char2, char3)),
      ))

      _ <- Entity.char(distinct).query.get.map(_.head ==> Set(
        char1, char2, char3
      ))
    } yield ()
  }


  "min/max" - types {
    for {
      _ <- Entity.i.char.insert(
        (1, char1),
        (1, char2),
        (1, char3),
        (2, char4),
        (2, char5),
        (2, char6),
      ).transact

      _ <- Entity.char(min).query.get.map(_ ==> List(char1))
      _ <- Entity.char(max).query.get.map(_ ==> List(char6))
      _ <- Entity.char(min).char(max).query.get.map(_ ==> List((char1, char6)))

      _ <- Entity.i.a1.char(min).query.get.map(_ ==> List(
        (1, char1),
        (2, char4)
      ))

      _ <- Entity.i.a1.char(max).query.get.map(_ ==> List(
        (1, char3),
        (2, char6)
      ))

      _ <- Entity.i.a1.char(min).char(max).query.get.map(_ ==> List(
        (1, char1, char3),
        (2, char4, char6)
      ))
    } yield ()
  }

  "min/max n" - types {
    for {
      _ <- Entity.i.char.insert(
        (1, char1),
        (1, char2),
        (1, char3),
        (2, char4),
        (2, char5),
        (2, char6),
        (2, char6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.char(min(1)).query.get.map(_ ==> List(Set(char1)))
      _ <- Entity.char(min(2)).query.get.map(_ ==> List(Set(char1, char2)))

      _ <- Entity.char(max(1)).query.get.map(_ ==> List(Set(char6)))
      _ <- Entity.char(max(2)).query.get.map(_ ==> List(Set(char5, char6)))

      _ <- Entity.i.a1.char(min(2)).query.get.map(_ ==> List(
        (1, Set(char1, char2)),
        (2, Set(char4, char5))
      ))

      _ <- Entity.i.a1.char(max(2)).query.get.map(_ ==> List(
        (1, Set(char2, char3)),
        (2, Set(char5, char6))
      ))

      _ <- Entity.i.a1.char(min(2)).char(max(2)).query.get.map(_ ==> List(
        (1, Set(char1, char2), Set(char2, char3)),
        (2, Set(char4, char5), Set(char5, char6))
      ))
    } yield ()
  }


  "sample" - types {
    val all = Set(char1, char2, char3, char4)
    for {
      _ <- Entity.char.insert(List(char1, char2, char3)).transact
      _ <- Entity.char(sample).query.get.map(res => all.contains(res.head) ==> true)
      _ <- Entity.char(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.char(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types {
    for {
      _ <- Entity.i.char.insert(List(
        (1, char1),
        (2, char2),
        (2, char2),
        (2, char3),
      )).transact

      _ <- Entity.char(count).query.get.map(_ ==> List(4))
      _ <- Entity.i.a1.char(count).query.get.map(_ ==> List(
        (1, 1),
        (2, 3)
      ))

      _ <- Entity.char(countDistinct).query.get.map(_ ==> List(3))
      _ <- Entity.i.a1.char(countDistinct).query.get.map(_ ==> List(
        (1, 1),
        (2, 2)
      ))
    } yield ()
  }
}