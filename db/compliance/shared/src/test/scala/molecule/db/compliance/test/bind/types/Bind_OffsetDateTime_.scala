// GENERATED CODE ********************************
package molecule.db.compliance.test.bind.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class Bind_OffsetDateTime_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Mandatory" - types {
    for {
      _ <- Entity.offsetDateTime.insert(offsetDateTime1, offsetDateTime2, offsetDateTime3).transact

      eq = Entity.offsetDateTime(?).a1.query
      _ <- eq(offsetDateTime1).get.map(_ ==> List(offsetDateTime1))
      _ <- eq(offsetDateTime2).get.map(_ ==> List(offsetDateTime2))
      _ <- eq(offsetDateTime3).get.map(_ ==> List(offsetDateTime3))

      ne = Entity.offsetDateTime.not(?).a1.query
      _ <- ne(offsetDateTime1).get.map(_ ==> List(offsetDateTime2, offsetDateTime3))
      _ <- ne(offsetDateTime2).get.map(_ ==> List(offsetDateTime1, offsetDateTime3))
      _ <- ne(offsetDateTime3).get.map(_ ==> List(offsetDateTime1, offsetDateTime2))

      lt = Entity.offsetDateTime.<(?).a1.query
      _ <- lt(offsetDateTime1).get.map(_ ==> List())
      _ <- lt(offsetDateTime2).get.map(_ ==> List(offsetDateTime1))
      _ <- lt(offsetDateTime3).get.map(_ ==> List(offsetDateTime1, offsetDateTime2))

      le = Entity.offsetDateTime.<=(?).a1.query
      _ <- le(offsetDateTime1).get.map(_ ==> List(offsetDateTime1))
      _ <- le(offsetDateTime2).get.map(_ ==> List(offsetDateTime1, offsetDateTime2))
      _ <- le(offsetDateTime3).get.map(_ ==> List(offsetDateTime1, offsetDateTime2, offsetDateTime3))

      gt = Entity.offsetDateTime.>(?).a1.query
      _ <- gt(offsetDateTime1).get.map(_ ==> List(offsetDateTime2, offsetDateTime3))
      _ <- gt(offsetDateTime2).get.map(_ ==> List(offsetDateTime3))
      _ <- gt(offsetDateTime3).get.map(_ ==> List())

      ge = Entity.offsetDateTime.>=(?).a1.query
      _ <- ge(offsetDateTime1).get.map(_ ==> List(offsetDateTime1, offsetDateTime2, offsetDateTime3))
      _ <- ge(offsetDateTime2).get.map(_ ==> List(offsetDateTime2, offsetDateTime3))
      _ <- ge(offsetDateTime3).get.map(_ ==> List(offsetDateTime3))
    } yield ()
  }


  "Tacit" - types {
    for {
      _ <- Entity.i.offsetDateTime.insert((1, offsetDateTime1), (2, offsetDateTime2), (3, offsetDateTime3)).transact

      eq = Entity.i.a1.offsetDateTime_(?).query
      _ <- eq(offsetDateTime1).get.map(_ ==> List(1))
      _ <- eq(offsetDateTime2).get.map(_ ==> List(2))
      _ <- eq(offsetDateTime3).get.map(_ ==> List(3))

      ne = Entity.i.a1.offsetDateTime_.not(?).query
      _ <- ne(offsetDateTime1).get.map(_ ==> List(2, 3))
      _ <- ne(offsetDateTime2).get.map(_ ==> List(1, 3))
      _ <- ne(offsetDateTime3).get.map(_ ==> List(1, 2))

      lt = Entity.i.a1.offsetDateTime_.<(?).query
      _ <- lt(offsetDateTime1).get.map(_ ==> List())
      _ <- lt(offsetDateTime2).get.map(_ ==> List(1))
      _ <- lt(offsetDateTime3).get.map(_ ==> List(1, 2))

      le = Entity.i.a1.offsetDateTime_.<=(?).query
      _ <- le(offsetDateTime1).get.map(_ ==> List(1))
      _ <- le(offsetDateTime2).get.map(_ ==> List(1, 2))
      _ <- le(offsetDateTime3).get.map(_ ==> List(1, 2, 3))

      gt = Entity.i.a1.offsetDateTime_.>(?).query
      _ <- gt(offsetDateTime1).get.map(_ ==> List(2, 3))
      _ <- gt(offsetDateTime2).get.map(_ ==> List(3))
      _ <- gt(offsetDateTime3).get.map(_ ==> List())

      ge = Entity.i.a1.offsetDateTime_.>=(?).query
      _ <- ge(offsetDateTime1).get.map(_ ==> List(1, 2, 3))
      _ <- ge(offsetDateTime2).get.map(_ ==> List(2, 3))
      _ <- ge(offsetDateTime3).get.map(_ ==> List(3))
    } yield ()
  }
}
