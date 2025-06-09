// GENERATED CODE ********************************
package molecule.db.compliance.test.bind.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class Bind_Char_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Mandatory" - types { implicit conn =>
    for {
      _ <- Entity.char.insert(char1, char2, char3).transact

      eq = Entity.char(?).a1.query
      _ <- eq(char1).get.map(_ ==> List(char1))
      _ <- eq(char2).get.map(_ ==> List(char2))
      _ <- eq(char3).get.map(_ ==> List(char3))

      ne = Entity.char.not(?).a1.query
      _ <- ne(char1).get.map(_ ==> List(char2, char3))
      _ <- ne(char2).get.map(_ ==> List(char1, char3))
      _ <- ne(char3).get.map(_ ==> List(char1, char2))

      lt = Entity.char.<(?).a1.query
      _ <- lt(char1).get.map(_ ==> List())
      _ <- lt(char2).get.map(_ ==> List(char1))
      _ <- lt(char3).get.map(_ ==> List(char1, char2))

      le = Entity.char.<=(?).a1.query
      _ <- le(char1).get.map(_ ==> List(char1))
      _ <- le(char2).get.map(_ ==> List(char1, char2))
      _ <- le(char3).get.map(_ ==> List(char1, char2, char3))

      gt = Entity.char.>(?).a1.query
      _ <- gt(char1).get.map(_ ==> List(char2, char3))
      _ <- gt(char2).get.map(_ ==> List(char3))
      _ <- gt(char3).get.map(_ ==> List())

      ge = Entity.char.>=(?).a1.query
      _ <- ge(char1).get.map(_ ==> List(char1, char2, char3))
      _ <- ge(char2).get.map(_ ==> List(char2, char3))
      _ <- ge(char3).get.map(_ ==> List(char3))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    for {
      _ <- Entity.i.char.insert((1, char1), (2, char2), (3, char3)).transact

      eq = Entity.i.a1.char_(?).query
      _ <- eq(char1).get.map(_ ==> List(1))
      _ <- eq(char2).get.map(_ ==> List(2))
      _ <- eq(char3).get.map(_ ==> List(3))

      ne = Entity.i.a1.char_.not(?).query
      _ <- ne(char1).get.map(_ ==> List(2, 3))
      _ <- ne(char2).get.map(_ ==> List(1, 3))
      _ <- ne(char3).get.map(_ ==> List(1, 2))

      lt = Entity.i.a1.char_.<(?).query
      _ <- lt(char1).get.map(_ ==> List())
      _ <- lt(char2).get.map(_ ==> List(1))
      _ <- lt(char3).get.map(_ ==> List(1, 2))

      le = Entity.i.a1.char_.<=(?).query
      _ <- le(char1).get.map(_ ==> List(1))
      _ <- le(char2).get.map(_ ==> List(1, 2))
      _ <- le(char3).get.map(_ ==> List(1, 2, 3))

      gt = Entity.i.a1.char_.>(?).query
      _ <- gt(char1).get.map(_ ==> List(2, 3))
      _ <- gt(char2).get.map(_ ==> List(3))
      _ <- gt(char3).get.map(_ ==> List())

      ge = Entity.i.a1.char_.>=(?).query
      _ <- ge(char1).get.map(_ ==> List(1, 2, 3))
      _ <- ge(char2).get.map(_ ==> List(2, 3))
      _ <- ge(char3).get.map(_ ==> List(3))
    } yield ()
  }
}
