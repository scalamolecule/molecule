package molecule.db.compliance.test.aggregation.any

import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class Aggr_Boolean(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types { implicit conn =>
    for {
      _ <- Entity.i.boolean.insert(List(
        (1, true),
        (2, false),
        (2, false),
        (2, true)
      )).transact


      _ <- Entity.i.a1.boolean.a2.query.get.map(_ ==> List(
        (1, true),
        (2, false), // 2 rows coalesced
        (2, true),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.boolean(distinct).query.get.map(_ ==> List(
        (1, Set(true)),
        (2, Set(false, true)),
      ))

      _ <- Entity.boolean(distinct).query.get.map(_.head ==> Set(false, true))
    } yield ()
  }


  "min" - types { implicit conn =>
    for {
      _ <- Entity.boolean.insert(List(true, false, true)).transact
      _ <- Entity.boolean(min).query.get.map(_.head ==> false)
      _ <- Entity.boolean(min(1)).query.get.map(_.head ==> Set(false))
      _ <- Entity.boolean(min(2)).query.get.map(_.head ==> Set(false, true))
    } yield ()
  }


  "max" - types { implicit futConn =>
    for {
      _ <- Entity.boolean.insert(List(true, false, true)).transact
      _ <- Entity.boolean(max).query.get.map(_.head ==> true)
      _ <- Entity.boolean(max(1)).query.get.map(_.head ==> Set(true))
      _ <- Entity.boolean(max(2)).query.get.map(_.head ==> Set(true, false))
    } yield ()
  }


  "sample" - types { implicit futConn =>
    for {
      _ <- Entity.boolean.insert(List(true, false, true)).transact
      all = Set(true, false)
      _ <- Entity.boolean(sample).query.get.map(res => all.contains(res.head) ==> true)
      _ <- Entity.boolean(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.boolean(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count, countDistinct" - types { implicit conn =>
    for {
      _ <- Entity.i.boolean.insert(List(
        (1, true),
        (2, false),
        (2, false),
        (2, true),
      )).transact

      _ <- Entity.i(count).query.get.map(_ ==> List(4))
      _ <- Entity.i(countDistinct).query.get.map(_ ==> List(2))

      _ <- Entity.boolean(count).query.get.map(_ ==> List(4))
      _ <- Entity.boolean(countDistinct).query.get.map(_ ==> List(2))

      _ <- Entity.i.a1.boolean(count).query.get.map(_ ==> List(
        (1, 1),
        (2, 3)
      ))
      _ <- Entity.i.a1.boolean(countDistinct).query.get.map(_ ==> List(
        (1, 1),
        (2, 2)
      ))
    } yield ()
  }
}