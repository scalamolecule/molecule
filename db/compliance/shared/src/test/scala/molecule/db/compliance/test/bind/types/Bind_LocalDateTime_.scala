// GENERATED CODE ********************************
package molecule.db.compliance.test.bind.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class Bind_LocalDateTime_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Mandatory" - types {
    for {
      _ <- Entity.localDateTime.insert(localDateTime1, localDateTime2, localDateTime3).transact

      eq = Entity.localDateTime(?).a1.query
      _ <- eq(localDateTime1).get.map(_ ==> List(localDateTime1))
      _ <- eq(localDateTime2).get.map(_ ==> List(localDateTime2))
      _ <- eq(localDateTime3).get.map(_ ==> List(localDateTime3))

      ne = Entity.localDateTime.not(?).a1.query
      _ <- ne(localDateTime1).get.map(_ ==> List(localDateTime2, localDateTime3))
      _ <- ne(localDateTime2).get.map(_ ==> List(localDateTime1, localDateTime3))
      _ <- ne(localDateTime3).get.map(_ ==> List(localDateTime1, localDateTime2))

      lt = Entity.localDateTime.<(?).a1.query
      _ <- lt(localDateTime1).get.map(_ ==> List())
      _ <- lt(localDateTime2).get.map(_ ==> List(localDateTime1))
      _ <- lt(localDateTime3).get.map(_ ==> List(localDateTime1, localDateTime2))

      le = Entity.localDateTime.<=(?).a1.query
      _ <- le(localDateTime1).get.map(_ ==> List(localDateTime1))
      _ <- le(localDateTime2).get.map(_ ==> List(localDateTime1, localDateTime2))
      _ <- le(localDateTime3).get.map(_ ==> List(localDateTime1, localDateTime2, localDateTime3))

      gt = Entity.localDateTime.>(?).a1.query
      _ <- gt(localDateTime1).get.map(_ ==> List(localDateTime2, localDateTime3))
      _ <- gt(localDateTime2).get.map(_ ==> List(localDateTime3))
      _ <- gt(localDateTime3).get.map(_ ==> List())

      ge = Entity.localDateTime.>=(?).a1.query
      _ <- ge(localDateTime1).get.map(_ ==> List(localDateTime1, localDateTime2, localDateTime3))
      _ <- ge(localDateTime2).get.map(_ ==> List(localDateTime2, localDateTime3))
      _ <- ge(localDateTime3).get.map(_ ==> List(localDateTime3))
    } yield ()
  }


  "Tacit" - types {
    for {
      _ <- Entity.i.localDateTime.insert((1, localDateTime1), (2, localDateTime2), (3, localDateTime3)).transact

      eq = Entity.i.a1.localDateTime_(?).query
      _ <- eq(localDateTime1).get.map(_ ==> List(1))
      _ <- eq(localDateTime2).get.map(_ ==> List(2))
      _ <- eq(localDateTime3).get.map(_ ==> List(3))

      ne = Entity.i.a1.localDateTime_.not(?).query
      _ <- ne(localDateTime1).get.map(_ ==> List(2, 3))
      _ <- ne(localDateTime2).get.map(_ ==> List(1, 3))
      _ <- ne(localDateTime3).get.map(_ ==> List(1, 2))

      lt = Entity.i.a1.localDateTime_.<(?).query
      _ <- lt(localDateTime1).get.map(_ ==> List())
      _ <- lt(localDateTime2).get.map(_ ==> List(1))
      _ <- lt(localDateTime3).get.map(_ ==> List(1, 2))

      le = Entity.i.a1.localDateTime_.<=(?).query
      _ <- le(localDateTime1).get.map(_ ==> List(1))
      _ <- le(localDateTime2).get.map(_ ==> List(1, 2))
      _ <- le(localDateTime3).get.map(_ ==> List(1, 2, 3))

      gt = Entity.i.a1.localDateTime_.>(?).query
      _ <- gt(localDateTime1).get.map(_ ==> List(2, 3))
      _ <- gt(localDateTime2).get.map(_ ==> List(3))
      _ <- gt(localDateTime3).get.map(_ ==> List())

      ge = Entity.i.a1.localDateTime_.>=(?).query
      _ <- ge(localDateTime1).get.map(_ ==> List(1, 2, 3))
      _ <- ge(localDateTime2).get.map(_ ==> List(2, 3))
      _ <- ge(localDateTime3).get.map(_ ==> List(3))
    } yield ()
  }
}
