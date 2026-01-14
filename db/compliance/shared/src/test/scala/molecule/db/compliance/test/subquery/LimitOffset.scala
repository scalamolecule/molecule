package molecule.db.compliance.test.subquery

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class LimitOffset(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "limit" - types {
    for {
      _ <- Entity.i.Ref.i.insert(
        (1, 4),
        (2, 5),
        (3, 6),
      ).transact

      _ <- Entity.i.a1.select(Ref.i.a1.query.limit(1)).query.get.map(_ ==> List(
        (1, 4),
        (2, 4),
        (3, 4),
      ))

      _ <- Entity.i.a1.select(Ref.i.d1.query.limit(1)).query.get.map(_ ==> List(
        (1, 6),
        (2, 6),
        (3, 6),
      ))
    } yield ()
  }


  "limit + offset" - types {
    for {
      _ <- Entity.i.Ref.i.insert(
        (1, 4),
        (2, 5),
        (3, 6),
      ).transact

      // Ascending
      _ <- Entity.i.a1.select(Ref.i.a1.query.limit(1)).query.get.map(_ ==> List(
        (1, 4),
        (2, 4),
        (3, 4),
      ))
      _ <- Entity.i.a1.select(Ref.i.a1.query.limit(1).offset(0)).query.get.map(_ ==> List(
        (1, 4),
        (2, 4),
        (3, 4),
      ))
      _ <- Entity.i.a1.select(Ref.i.a1.query.limit(1).offset(1)).query.get.map(_ ==> List(
        (1, 5),
        (2, 5),
        (3, 5),
      ))
      _ <- Entity.i.a1.select(Ref.i.a1.query.limit(1).offset(2)).query.get.map(_ ==> List(
        (1, 6),
        (2, 6),
        (3, 6),
      ))

      // Descending
      _ <- Entity.i.a1.select(Ref.i.d1.query.limit(1)).query.get.map(_ ==> List(
        (1, 6),
        (2, 6),
        (3, 6),
      ))
      _ <- Entity.i.a1.select(Ref.i.d1.query.limit(1).offset(0)).query.get.map(_ ==> List(
        (1, 6),
        (2, 6),
        (3, 6),
      ))
      _ <- Entity.i.a1.select(Ref.i.d1.query.limit(1).offset(1)).query.get.map(_ ==> List(
        (1, 5),
        (2, 5),
        (3, 5),
      ))
      _ <- Entity.i.a1.select(Ref.i.d1.query.limit(1).offset(2)).query.get.map(_ ==> List(
        (1, 4),
        (2, 4),
        (3, 4),
      ))
    } yield ()
  }
}
