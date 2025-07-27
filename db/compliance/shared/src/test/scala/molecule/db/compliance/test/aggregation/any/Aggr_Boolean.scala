package molecule.db.compliance.test.aggregation.any

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import org.scalactic.Equality
import org.scalactic.Equality

case class Aggr_Boolean(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types {
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


  "min" - types {
    for {
      _ <- Entity.boolean.insert(List(true, false, true)).transact
    } yield ()
  }


  "max" - types {
    for {
      _ <- Entity.boolean.insert(List(true, false, true)).transact
    } yield ()
  }


  "sample" - types {
    for {
      _ <- Entity.boolean.insert(List(true, false, true)).transact
      all = Set(true, false)
    } yield ()
  }


  "count, countDistinct" - types {
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