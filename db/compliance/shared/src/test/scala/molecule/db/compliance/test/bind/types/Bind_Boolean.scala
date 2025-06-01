package molecule.db.compliance.test.bind.types

import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class Bind_Boolean(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Mandatory" - types { implicit conn =>
    for {
      _ <- Entity.boolean.insert(true, false).transact

      eq = Entity.boolean(?).query
      _ <- eq(true).get.map(_ ==> List(true))
      _ <- eq(false).get.map(_ ==> List(false))

      ne = Entity.boolean.not(?).query
      _ <- ne(true).get.map(_ ==> List(false))
      _ <- ne(false).get.map(_ ==> List(true))

      lt = Entity.boolean.<(?).query
      _ <- lt(true).get.map(_ ==> List(false))
      _ <- lt(false).get.map(_ ==> List())

      le = Entity.boolean.<=(?).query
      _ <- le(true).get.map(_ ==> List(false, true))
      _ <- le(false).get.map(_ ==> List(false))

      gt = Entity.boolean.>(?).query
      _ <- gt(true).get.map(_ ==> List())
      _ <- gt(false).get.map(_ ==> List(true))

      ge = Entity.boolean.>=(?).query
      _ <- ge(true).get.map(_ ==> List(true))
      _ <- ge(false).get.map(_ ==> List(false, true))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    for {
      _ <- Entity.i.boolean.insert((1, true), (0, false)).transact

      eq = Entity.i.boolean_(?).query
      _ <- eq(true).get.map(_ ==> List(1))
      _ <- eq(false).get.map(_ ==> List(0))

      ne = Entity.i.boolean_.not(?).query
      _ <- ne(true).get.map(_ ==> List(0))
      _ <- ne(false).get.map(_ ==> List(1))

      lt = Entity.i.boolean_.<(?).query
      _ <- lt(true).get.map(_ ==> List(0))
      _ <- lt(false).get.map(_ ==> List())

      le = Entity.i.boolean_.<=(?).query
      _ <- le(true).get.map(_ ==> List(0, 1))
      _ <- le(false).get.map(_ ==> List(0))

      gt = Entity.i.boolean_.>(?).query
      _ <- gt(true).get.map(_ ==> List())
      _ <- gt(false).get.map(_ ==> List(1))

      ge = Entity.i.boolean_.>=(?).query
      _ <- ge(true).get.map(_ ==> List(1))
      _ <- ge(false).get.map(_ ==> List(0, 1))
    } yield ()
  }
}
