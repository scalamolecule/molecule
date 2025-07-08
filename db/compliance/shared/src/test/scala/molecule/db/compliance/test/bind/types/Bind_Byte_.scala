// GENERATED CODE ********************************
package molecule.db.compliance.test.bind.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class Bind_Byte_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Mandatory" - types { implicit conn =>
    for {
      _ <- Entity.byte.insert(byte1, byte2, byte3).transact

      eq = Entity.byte(?).a1.query
      _ <- eq(byte1).get.map(_ ==> List(byte1))
      _ <- eq(byte2).get.map(_ ==> List(byte2))
      _ <- eq(byte3).get.map(_ ==> List(byte3))

      ne = Entity.byte.not(?).a1.query
      _ <- ne(byte1).get.map(_ ==> List(byte2, byte3))
      _ <- ne(byte2).get.map(_ ==> List(byte1, byte3))
      _ <- ne(byte3).get.map(_ ==> List(byte1, byte2))

      lt = Entity.byte.<(?).a1.query
      _ <- lt(byte1).get.map(_ ==> List())
      _ <- lt(byte2).get.map(_ ==> List(byte1))
      _ <- lt(byte3).get.map(_ ==> List(byte1, byte2))

      le = Entity.byte.<=(?).a1.query
      _ <- le(byte1).get.map(_ ==> List(byte1))
      _ <- le(byte2).get.map(_ ==> List(byte1, byte2))
      _ <- le(byte3).get.map(_ ==> List(byte1, byte2, byte3))

      gt = Entity.byte.>(?).a1.query
      _ <- gt(byte1).get.map(_ ==> List(byte2, byte3))
      _ <- gt(byte2).get.map(_ ==> List(byte3))
      _ <- gt(byte3).get.map(_ ==> List())

      ge = Entity.byte.>=(?).a1.query
      _ <- ge(byte1).get.map(_ ==> List(byte1, byte2, byte3))
      _ <- ge(byte2).get.map(_ ==> List(byte2, byte3))
      _ <- ge(byte3).get.map(_ ==> List(byte3))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    for {
      _ <- Entity.i.byte.insert((1, byte1), (2, byte2), (3, byte3)).transact

      eq = Entity.i.a1.byte_(?).query
      _ <- eq(byte1).get.map(_ ==> List(1))
      _ <- eq(byte2).get.map(_ ==> List(2))
      _ <- eq(byte3).get.map(_ ==> List(3))

      ne = Entity.i.a1.byte_.not(?).query
      _ <- ne(byte1).get.map(_ ==> List(2, 3))
      _ <- ne(byte2).get.map(_ ==> List(1, 3))
      _ <- ne(byte3).get.map(_ ==> List(1, 2))

      lt = Entity.i.a1.byte_.<(?).query
      _ <- lt(byte1).get.map(_ ==> List())
      _ <- lt(byte2).get.map(_ ==> List(1))
      _ <- lt(byte3).get.map(_ ==> List(1, 2))

      le = Entity.i.a1.byte_.<=(?).query
      _ <- le(byte1).get.map(_ ==> List(1))
      _ <- le(byte2).get.map(_ ==> List(1, 2))
      _ <- le(byte3).get.map(_ ==> List(1, 2, 3))

      gt = Entity.i.a1.byte_.>(?).query
      _ <- gt(byte1).get.map(_ ==> List(2, 3))
      _ <- gt(byte2).get.map(_ ==> List(3))
      _ <- gt(byte3).get.map(_ ==> List())

      ge = Entity.i.a1.byte_.>=(?).query
      _ <- ge(byte1).get.map(_ ==> List(1, 2, 3))
      _ <- ge(byte2).get.map(_ ==> List(2, 3))
      _ <- ge(byte3).get.map(_ ==> List(3))
    } yield ()
  }
}
