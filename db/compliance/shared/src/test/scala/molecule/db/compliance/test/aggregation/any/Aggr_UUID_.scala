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

case class Aggr_UUID_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types {
    for {
      _ <- Entity.i.uuid.insert(List(
        (1, uuid1),
        (2, uuid2),
        (2, uuid2),
        (2, uuid3),
      )).transact

      _ <- Entity.i.uuid.a1.query.get.map(_ ==> List(
        (1, uuid1),
        (2, uuid2), // 2 rows coalesced
        (2, uuid3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.uuid(distinct).query.get.map(_ ==> List(
        (1, Set(uuid1)),
        (2, Set(uuid2, uuid3)),
      ))

      _ <- Entity.uuid(distinct).query.get.map(_.head ==> Set(
        uuid1, uuid2, uuid3
      ))
    } yield ()
  }


  "min/max" - types {
    for {
      _ <- Entity.i.uuid.insert(
        (1, uuid1),
        (1, uuid2),
        (1, uuid3),
        (2, uuid4),
        (2, uuid5),
        (2, uuid6),
      ).transact

      _ <- Entity.uuid(min).query.get.map(_ ==> List(uuid1))
      _ <- Entity.uuid(max).query.get.map(_ ==> List(uuid6))
      _ <- Entity.uuid(min).uuid(max).query.get.map(_ ==> List((uuid1, uuid6)))

      _ <- Entity.i.a1.uuid(min).query.get.map(_ ==> List(
        (1, uuid1),
        (2, uuid4)
      ))

      _ <- Entity.i.a1.uuid(max).query.get.map(_ ==> List(
        (1, uuid3),
        (2, uuid6)
      ))

      _ <- Entity.i.a1.uuid(min).uuid(max).query.get.map(_ ==> List(
        (1, uuid1, uuid3),
        (2, uuid4, uuid6)
      ))
    } yield ()
  }

  "min/max n" - types {
    for {
      _ <- Entity.i.uuid.insert(
        (1, uuid1),
        (1, uuid2),
        (1, uuid3),
        (2, uuid4),
        (2, uuid5),
        (2, uuid6),
        (2, uuid6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.uuid(min(1)).query.get.map(_ ==> List(Set(uuid1)))
      _ <- Entity.uuid(min(2)).query.get.map(_ ==> List(Set(uuid1, uuid2)))

      _ <- Entity.uuid(max(1)).query.get.map(_ ==> List(Set(uuid6)))
      _ <- Entity.uuid(max(2)).query.get.map(_ ==> List(Set(uuid5, uuid6)))

      _ <- Entity.i.a1.uuid(min(2)).query.get.map(_ ==> List(
        (1, Set(uuid1, uuid2)),
        (2, Set(uuid4, uuid5))
      ))

      _ <- Entity.i.a1.uuid(max(2)).query.get.map(_ ==> List(
        (1, Set(uuid2, uuid3)),
        (2, Set(uuid5, uuid6))
      ))

      _ <- Entity.i.a1.uuid(min(2)).uuid(max(2)).query.get.map(_ ==> List(
        (1, Set(uuid1, uuid2), Set(uuid2, uuid3)),
        (2, Set(uuid4, uuid5), Set(uuid5, uuid6))
      ))
    } yield ()
  }


  "sample" - types {
    val all = Set(uuid1, uuid2, uuid3, uuid4)
    for {
      _ <- Entity.uuid.insert(List(uuid1, uuid2, uuid3)).transact
      _ <- Entity.uuid(sample).query.get.map(res => all.contains(res.head) ==> true)
      _ <- Entity.uuid(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.uuid(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types {
    for {
      _ <- Entity.i.uuid.insert(List(
        (1, uuid1),
        (2, uuid2),
        (2, uuid2),
        (2, uuid3),
      )).transact

      _ <- Entity.uuid(count).query.get.map(_ ==> List(4))
      _ <- Entity.i.a1.uuid(count).query.get.map(_ ==> List(
        (1, 1),
        (2, 3)
      ))

      _ <- Entity.uuid(countDistinct).query.get.map(_ ==> List(3))
      _ <- Entity.i.a1.uuid(countDistinct).query.get.map(_ ==> List(
        (1, 1),
        (2, 2)
      ))
    } yield ()
  }
}