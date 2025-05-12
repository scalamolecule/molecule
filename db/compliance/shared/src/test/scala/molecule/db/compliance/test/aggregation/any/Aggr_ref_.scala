// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.any

import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class Aggr_ref_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types { implicit conn =>
    for {
      _ <- Entity.i.ref.insert(List(
        (1, ref1),
        (2, ref2),
        (2, ref2),
        (2, ref3),
      )).transact

      _ <- Entity.i.ref.a1.query.get.map(_ ==> List(
        (1, ref1),
        (2, ref2), // 2 rows coalesced
        (2, ref3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.ref(distinct).query.get.map(_ ==> List(
        (1, Set(ref1)),
        (2, Set(ref2, ref3)),
      ))

      _ <- Entity.ref(distinct).query.get.map(_.head ==> Set(
        ref1, ref2, ref3
      ))
    } yield ()
  }


  "min/max" - types { implicit conn =>
    for {
      _ <- Entity.i.ref.insert(
        (1, ref1),
        (1, ref2),
        (1, ref3),
        (2, ref4),
        (2, ref5),
        (2, ref6),
      ).transact

      _ <- Entity.ref(min).query.get.map(_ ==> List(ref1))
      _ <- Entity.ref(max).query.get.map(_ ==> List(ref6))
      _ <- Entity.ref(min).ref(max).query.get.map(_ ==> List((ref1, ref6)))

      _ <- Entity.i.a1.ref(min).query.get.map(_ ==> List(
        (1, ref1),
        (2, ref4)
      ))

      _ <- Entity.i.a1.ref(max).query.get.map(_ ==> List(
        (1, ref3),
        (2, ref6)
      ))

      _ <- Entity.i.a1.ref(min).ref(max).query.get.map(_ ==> List(
        (1, ref1, ref3),
        (2, ref4, ref6)
      ))
    } yield ()
  }

  "min/max n" - types { implicit conn =>
    for {
      _ <- Entity.i.ref.insert(
        (1, ref1),
        (1, ref2),
        (1, ref3),
        (2, ref4),
        (2, ref5),
        (2, ref6),
        (2, ref6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.ref(min(1)).query.get.map(_ ==> List(Set(ref1)))
      _ <- Entity.ref(min(2)).query.get.map(_ ==> List(Set(ref1, ref2)))

      _ <- Entity.ref(max(1)).query.get.map(_ ==> List(Set(ref6)))
      _ <- Entity.ref(max(2)).query.get.map(_ ==> List(Set(ref5, ref6)))

      _ <- Entity.i.a1.ref(min(2)).query.get.map(_ ==> List(
        (1, Set(ref1, ref2)),
        (2, Set(ref4, ref5))
      ))

      _ <- Entity.i.a1.ref(max(2)).query.get.map(_ ==> List(
        (1, Set(ref2, ref3)),
        (2, Set(ref5, ref6))
      ))

      _ <- Entity.i.a1.ref(min(2)).ref(max(2)).query.get.map(_ ==> List(
        (1, Set(ref1, ref2), Set(ref2, ref3)),
        (2, Set(ref4, ref5), Set(ref5, ref6))
      ))
    } yield ()
  }


  "sample" - types { implicit futConn =>
    val all = Set(ref1, ref2, ref3, ref4)
    for {
      _ <- Entity.ref.insert(List(ref1, ref2, ref3)).transact
      _ <- Entity.ref(sample).query.get.map(res => all.contains(res.head) ==> true)
      _ <- Entity.ref(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.ref(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types { implicit conn =>
    for {
      _ <- Entity.i.ref.insert(List(
        (1, ref1),
        (2, ref2),
        (2, ref2),
        (2, ref3),
      )).transact

      _ <- Entity.ref(count).query.get.map(_ ==> List(4))
      _ <- Entity.i.a1.ref(count).query.get.map(_ ==> List(
        (1, 1),
        (2, 3)
      ))

      _ <- Entity.ref(countDistinct).query.get.map(_ ==> List(3))
      _ <- Entity.i.a1.ref(countDistinct).query.get.map(_ ==> List(
        (1, 1),
        (2, 2)
      ))
    } yield ()
  }
}