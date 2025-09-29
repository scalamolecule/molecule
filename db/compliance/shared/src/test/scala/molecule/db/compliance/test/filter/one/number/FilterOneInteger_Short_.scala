// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.one.number

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class FilterOneInteger_Short_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "odd/even" - types {
    for {
      _ <- Entity.i.short.insert(
        (-2, -2.toShort),
        (-1, -1.toShort),
        (0, short0),
        (1, short1),
        (2, short2),
      ).transact

      _ <- Entity.short.even.a1.query.get.map(_ ==> List(-2.toShort, short0, short2))
      _ <- Entity.i.a1.short_.even.query.get.map(_ ==> List(-2, 0, 2))

      _ <- Entity.short.odd.a1.query.get.map(_ ==> List(-1.toShort, short1))
      _ <- Entity.i.a1.short_.odd.query.get.map(_ ==> List(-1, 1))
    } yield ()
  }


  "modulo" - types {
    for {
      _ <- Entity.i.short.insert(
        (-4, -4.toShort),
        (-3, -3.toShort),
        (-2, -2.toShort),
        (-1, -1.toShort),
        (0, short0),
        (1, short1),
        (2, short2),
        (3, short3),
        (4, short4),
      ).transact

      // Mandatory

      _ <- Entity.short.%(short2, short0).query.get.map(_ ==> List(-4.toShort, -2.toShort, short0, short2, short4))
      _ <- Entity.short.%(short2, short1).query.get.map(_ ==> List(-3.toShort, -1.toShort, short1, short3))

      _ <- Entity.short.%(short3, short0).query.get.map(_ ==> List(-3.toShort, short0, short3))
      _ <- Entity.short.%(short3, short1).query.get.map(_ ==> List(-4.toShort, -1.toShort, short1, short4))
      _ <- Entity.short.%(short3, short2).query.get.map(_ ==> List(-2.toShort, short2))

      // Tacit

      _ <- Entity.i.short_.%(short2, short0).query.get.map(_ ==> List(-4, -2, 0, 2, 4))
      _ <- Entity.i.short_.%(short2, short1).query.get.map(_ ==> List(-3, -1, 1, 3))

      _ <- Entity.i.short_.%(short3, short0).query.get.map(_ ==> List(-3, 0, 3))
      _ <- Entity.i.short_.%(short3, short1).query.get.map(_ ==> List(-4, -1, 1, 4))
      _ <- Entity.i.short_.%(short3, short2).query.get.map(_ ==> List(-2, 2))
    } yield ()
  }
}
