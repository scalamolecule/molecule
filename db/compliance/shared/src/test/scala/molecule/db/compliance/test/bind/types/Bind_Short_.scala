// GENERATED CODE ********************************
package molecule.db.compliance.test.bind.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class Bind_Short_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Mandatory" - types { implicit conn =>
    for {
      _ <- Entity.short.insert(short1, short2, short3).transact

      eq = Entity.short(?).a1.query
      _ <- eq(short1).get.map(_ ==> List(short1))
      _ <- eq(short2).get.map(_ ==> List(short2))
      _ <- eq(short3).get.map(_ ==> List(short3))

      ne = Entity.short.not(?).a1.query
      _ <- ne(short1).get.map(_ ==> List(short2, short3))
      _ <- ne(short2).get.map(_ ==> List(short1, short3))
      _ <- ne(short3).get.map(_ ==> List(short1, short2))

      lt = Entity.short.<(?).a1.query
      _ <- lt(short1).get.map(_ ==> List())
      _ <- lt(short2).get.map(_ ==> List(short1))
      _ <- lt(short3).get.map(_ ==> List(short1, short2))

      le = Entity.short.<=(?).a1.query
      _ <- le(short1).get.map(_ ==> List(short1))
      _ <- le(short2).get.map(_ ==> List(short1, short2))
      _ <- le(short3).get.map(_ ==> List(short1, short2, short3))

      gt = Entity.short.>(?).a1.query
      _ <- gt(short1).get.map(_ ==> List(short2, short3))
      _ <- gt(short2).get.map(_ ==> List(short3))
      _ <- gt(short3).get.map(_ ==> List())

      ge = Entity.short.>=(?).a1.query
      _ <- ge(short1).get.map(_ ==> List(short1, short2, short3))
      _ <- ge(short2).get.map(_ ==> List(short2, short3))
      _ <- ge(short3).get.map(_ ==> List(short3))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    for {
      _ <- Entity.i.short.insert((1, short1), (2, short2), (3, short3)).transact

      eq = Entity.i.a1.short_(?).query
      _ <- eq(short1).get.map(_ ==> List(1))
      _ <- eq(short2).get.map(_ ==> List(2))
      _ <- eq(short3).get.map(_ ==> List(3))

      ne = Entity.i.a1.short_.not(?).query
      _ <- ne(short1).get.map(_ ==> List(2, 3))
      _ <- ne(short2).get.map(_ ==> List(1, 3))
      _ <- ne(short3).get.map(_ ==> List(1, 2))

      lt = Entity.i.a1.short_.<(?).query
      _ <- lt(short1).get.map(_ ==> List())
      _ <- lt(short2).get.map(_ ==> List(1))
      _ <- lt(short3).get.map(_ ==> List(1, 2))

      le = Entity.i.a1.short_.<=(?).query
      _ <- le(short1).get.map(_ ==> List(1))
      _ <- le(short2).get.map(_ ==> List(1, 2))
      _ <- le(short3).get.map(_ ==> List(1, 2, 3))

      gt = Entity.i.a1.short_.>(?).query
      _ <- gt(short1).get.map(_ ==> List(2, 3))
      _ <- gt(short2).get.map(_ ==> List(3))
      _ <- gt(short3).get.map(_ ==> List())

      ge = Entity.i.a1.short_.>=(?).query
      _ <- ge(short1).get.map(_ ==> List(1, 2, 3))
      _ <- ge(short2).get.map(_ ==> List(2, 3))
      _ <- ge(short3).get.map(_ ==> List(3))
    } yield ()
  }
}
