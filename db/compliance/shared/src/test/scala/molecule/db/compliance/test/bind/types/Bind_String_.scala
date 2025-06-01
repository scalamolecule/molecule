// GENERATED CODE ********************************
package molecule.db.compliance.test.bind.types

import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class Bind_String_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Mandatory" - types { implicit conn =>
    for {
      _ <- Entity.string.insert(string1, string2, string3).transact

      eq = Entity.string(?).query
      _ <- eq(string1).get.map(_ ==> List(string1))
      _ <- eq(string2).get.map(_ ==> List(string2))
      _ <- eq(string3).get.map(_ ==> List(string3))

      ne = Entity.string.not(?).query
      _ <- ne(string1).get.map(_ ==> List(string2, string3))
      _ <- ne(string2).get.map(_ ==> List(string1, string3))
      _ <- ne(string3).get.map(_ ==> List(string1, string2))

      lt = Entity.string.<(?).query
      _ <- lt(string1).get.map(_ ==> List())
      _ <- lt(string2).get.map(_ ==> List(string1))
      _ <- lt(string3).get.map(_ ==> List(string1, string2))

      le = Entity.string.<=(?).query
      _ <- le(string1).get.map(_ ==> List(string1))
      _ <- le(string2).get.map(_ ==> List(string1, string2))
      _ <- le(string3).get.map(_ ==> List(string1, string2, string3))

      gt = Entity.string.>(?).query
      _ <- gt(string1).get.map(_ ==> List(string2, string3))
      _ <- gt(string2).get.map(_ ==> List(string3))
      _ <- gt(string3).get.map(_ ==> List())

      ge = Entity.string.>=(?).query
      _ <- ge(string1).get.map(_ ==> List(string1, string2, string3))
      _ <- ge(string2).get.map(_ ==> List(string2, string3))
      _ <- ge(string3).get.map(_ ==> List(string3))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    for {
      _ <- Entity.i.string.insert((1, string1), (2, string2), (3, string3)).transact

      eq = Entity.i.string_(?).query
      _ <- eq(string1).get.map(_ ==> List(1))
      _ <- eq(string2).get.map(_ ==> List(2))
      _ <- eq(string3).get.map(_ ==> List(3))

      ne = Entity.i.string_.not(?).query
      _ <- ne(string1).get.map(_ ==> List(2, 3))
      _ <- ne(string2).get.map(_ ==> List(1, 3))
      _ <- ne(string3).get.map(_ ==> List(1, 2))

      lt = Entity.i.string_.<(?).query
      _ <- lt(string1).get.map(_ ==> List())
      _ <- lt(string2).get.map(_ ==> List(1))
      _ <- lt(string3).get.map(_ ==> List(1, 2))

      le = Entity.i.string_.<=(?).query
      _ <- le(string1).get.map(_ ==> List(1))
      _ <- le(string2).get.map(_ ==> List(1, 2))
      _ <- le(string3).get.map(_ ==> List(1, 2, 3))

      gt = Entity.i.string_.>(?).query
      _ <- gt(string1).get.map(_ ==> List(2, 3))
      _ <- gt(string2).get.map(_ ==> List(3))
      _ <- gt(string3).get.map(_ ==> List())

      ge = Entity.i.string_.>=(?).query
      _ <- ge(string1).get.map(_ ==> List(1, 2, 3))
      _ <- ge(string2).get.map(_ ==> List(2, 3))
      _ <- ge(string3).get.map(_ ==> List(3))
    } yield ()
  }
}
