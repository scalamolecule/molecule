// GENERATED CODE ********************************
package molecule.db.compliance.test.bind.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class Bind_String_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Mandatory" - types {
    for {
      _ <- Entity.string.insert(string1, string2, string3).transact

      eq = Entity.string(?).a1.query
      _ <- eq(string1).get.map(_ ==> List(string1))
      _ <- eq(string2).get.map(_ ==> List(string2))
      _ <- eq(string3).get.map(_ ==> List(string3))

      ne = Entity.string.not(?).a1.query
      _ <- ne(string1).get.map(_ ==> List(string2, string3))
      _ <- ne(string2).get.map(_ ==> List(string1, string3))
      _ <- ne(string3).get.map(_ ==> List(string1, string2))

      lt = Entity.string.<(?).a1.query
      _ <- lt(string1).get.map(_ ==> List())
      _ <- lt(string2).get.map(_ ==> List(string1))
      _ <- lt(string3).get.map(_ ==> List(string1, string2))

      le = Entity.string.<=(?).a1.query
      _ <- le(string1).get.map(_ ==> List(string1))
      _ <- le(string2).get.map(_ ==> List(string1, string2))
      _ <- le(string3).get.map(_ ==> List(string1, string2, string3))

      gt = Entity.string.>(?).a1.query
      _ <- gt(string1).get.map(_ ==> List(string2, string3))
      _ <- gt(string2).get.map(_ ==> List(string3))
      _ <- gt(string3).get.map(_ ==> List())

      ge = Entity.string.>=(?).a1.query
      _ <- ge(string1).get.map(_ ==> List(string1, string2, string3))
      _ <- ge(string2).get.map(_ ==> List(string2, string3))
      _ <- ge(string3).get.map(_ ==> List(string3))
    } yield ()
  }


  "Tacit" - types {
    for {
      _ <- Entity.i.string.insert((1, string1), (2, string2), (3, string3)).transact

      eq = Entity.i.a1.string_(?).query
      _ <- eq(string1).get.map(_ ==> List(1))
      _ <- eq(string2).get.map(_ ==> List(2))
      _ <- eq(string3).get.map(_ ==> List(3))

      ne = Entity.i.a1.string_.not(?).query
      _ <- ne(string1).get.map(_ ==> List(2, 3))
      _ <- ne(string2).get.map(_ ==> List(1, 3))
      _ <- ne(string3).get.map(_ ==> List(1, 2))

      lt = Entity.i.a1.string_.<(?).query
      _ <- lt(string1).get.map(_ ==> List())
      _ <- lt(string2).get.map(_ ==> List(1))
      _ <- lt(string3).get.map(_ ==> List(1, 2))

      le = Entity.i.a1.string_.<=(?).query
      _ <- le(string1).get.map(_ ==> List(1))
      _ <- le(string2).get.map(_ ==> List(1, 2))
      _ <- le(string3).get.map(_ ==> List(1, 2, 3))

      gt = Entity.i.a1.string_.>(?).query
      _ <- gt(string1).get.map(_ ==> List(2, 3))
      _ <- gt(string2).get.map(_ ==> List(3))
      _ <- gt(string3).get.map(_ ==> List())

      ge = Entity.i.a1.string_.>=(?).query
      _ <- ge(string1).get.map(_ ==> List(1, 2, 3))
      _ <- ge(string2).get.map(_ ==> List(2, 3))
      _ <- ge(string3).get.map(_ ==> List(3))
    } yield ()
  }
}
