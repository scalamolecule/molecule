// GENERATED CODE ********************************
package molecule.db.compliance.test.bind.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class Bind_ZonedDateTime_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Mandatory" - types {
    for {
      _ <- Entity.zonedDateTime.insert(zonedDateTime1, zonedDateTime2, zonedDateTime3).transact

      eq = Entity.zonedDateTime(?).a1.query
      _ <- eq(zonedDateTime1).get.map(_ ==> List(zonedDateTime1))
      _ <- eq(zonedDateTime2).get.map(_ ==> List(zonedDateTime2))
      _ <- eq(zonedDateTime3).get.map(_ ==> List(zonedDateTime3))

      ne = Entity.zonedDateTime.not(?).a1.query
      _ <- ne(zonedDateTime1).get.map(_ ==> List(zonedDateTime2, zonedDateTime3))
      _ <- ne(zonedDateTime2).get.map(_ ==> List(zonedDateTime1, zonedDateTime3))
      _ <- ne(zonedDateTime3).get.map(_ ==> List(zonedDateTime1, zonedDateTime2))

      lt = Entity.zonedDateTime.<(?).a1.query
      _ <- lt(zonedDateTime1).get.map(_ ==> List())
      _ <- lt(zonedDateTime2).get.map(_ ==> List(zonedDateTime1))
      _ <- lt(zonedDateTime3).get.map(_ ==> List(zonedDateTime1, zonedDateTime2))

      le = Entity.zonedDateTime.<=(?).a1.query
      _ <- le(zonedDateTime1).get.map(_ ==> List(zonedDateTime1))
      _ <- le(zonedDateTime2).get.map(_ ==> List(zonedDateTime1, zonedDateTime2))
      _ <- le(zonedDateTime3).get.map(_ ==> List(zonedDateTime1, zonedDateTime2, zonedDateTime3))

      gt = Entity.zonedDateTime.>(?).a1.query
      _ <- gt(zonedDateTime1).get.map(_ ==> List(zonedDateTime2, zonedDateTime3))
      _ <- gt(zonedDateTime2).get.map(_ ==> List(zonedDateTime3))
      _ <- gt(zonedDateTime3).get.map(_ ==> List())

      ge = Entity.zonedDateTime.>=(?).a1.query
      _ <- ge(zonedDateTime1).get.map(_ ==> List(zonedDateTime1, zonedDateTime2, zonedDateTime3))
      _ <- ge(zonedDateTime2).get.map(_ ==> List(zonedDateTime2, zonedDateTime3))
      _ <- ge(zonedDateTime3).get.map(_ ==> List(zonedDateTime3))
    } yield ()
  }


  "Tacit" - types {
    for {
      _ <- Entity.i.zonedDateTime.insert((1, zonedDateTime1), (2, zonedDateTime2), (3, zonedDateTime3)).transact

      eq = Entity.i.a1.zonedDateTime_(?).query
      _ <- eq(zonedDateTime1).get.map(_ ==> List(1))
      _ <- eq(zonedDateTime2).get.map(_ ==> List(2))
      _ <- eq(zonedDateTime3).get.map(_ ==> List(3))

      ne = Entity.i.a1.zonedDateTime_.not(?).query
      _ <- ne(zonedDateTime1).get.map(_ ==> List(2, 3))
      _ <- ne(zonedDateTime2).get.map(_ ==> List(1, 3))
      _ <- ne(zonedDateTime3).get.map(_ ==> List(1, 2))

      lt = Entity.i.a1.zonedDateTime_.<(?).query
      _ <- lt(zonedDateTime1).get.map(_ ==> List())
      _ <- lt(zonedDateTime2).get.map(_ ==> List(1))
      _ <- lt(zonedDateTime3).get.map(_ ==> List(1, 2))

      le = Entity.i.a1.zonedDateTime_.<=(?).query
      _ <- le(zonedDateTime1).get.map(_ ==> List(1))
      _ <- le(zonedDateTime2).get.map(_ ==> List(1, 2))
      _ <- le(zonedDateTime3).get.map(_ ==> List(1, 2, 3))

      gt = Entity.i.a1.zonedDateTime_.>(?).query
      _ <- gt(zonedDateTime1).get.map(_ ==> List(2, 3))
      _ <- gt(zonedDateTime2).get.map(_ ==> List(3))
      _ <- gt(zonedDateTime3).get.map(_ ==> List())

      ge = Entity.i.a1.zonedDateTime_.>=(?).query
      _ <- ge(zonedDateTime1).get.map(_ ==> List(1, 2, 3))
      _ <- ge(zonedDateTime2).get.map(_ ==> List(2, 3))
      _ <- ge(zonedDateTime3).get.map(_ ==> List(3))
    } yield ()
  }
}
