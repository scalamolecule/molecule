package molecule.db.compliance.test.bind.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class Bind_Boolean(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Mandatory" - types {
    for {
      _ <- Entity.boolean.insert(true, false).transact

      eq = Entity.boolean(?).query
      _ <- eq(true).get.map(_ ==> List(true))
      _ <- eq(false).get.map(_ ==> List(false))

      ne = Entity.boolean.not(?).query
      _ <- ne(true).get.map(_ ==> List(false))
      _ <- ne(false).get.map(_ ==> List(true))
    } yield ()
  }


  "Tacit" - types {
    for {
      _ <- Entity.i.boolean.insert((1, true), (0, false)).transact

      eq = Entity.i.boolean_(?).query
      _ <- eq(true).get.map(_ ==> List(1))
      _ <- eq(false).get.map(_ ==> List(0))

      ne = Entity.i.boolean_.not(?).query
      _ <- ne(true).get.map(_ ==> List(0))
      _ <- ne(false).get.map(_ ==> List(1))
    } yield ()
  }
}
