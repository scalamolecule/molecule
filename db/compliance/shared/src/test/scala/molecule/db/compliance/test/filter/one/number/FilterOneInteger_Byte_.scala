// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.one.number

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class FilterOneInteger_Byte_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "odd/even" - types {
    for {
      _ <- Entity.i.byte.insert(
        (-2, -2.toByte),
        (-1, -1.toByte),
        (0, byte0),
        (1, byte1),
        (2, byte2),
      ).transact

      _ <- Entity.byte.even.a1.query.get.map(_ ==> List(-2.toByte, byte0, byte2))
      _ <- Entity.i.a1.byte_.even.query.get.map(_ ==> List(-2, 0, 2))

      _ <- Entity.byte.odd.a1.query.get.map(_ ==> List(-1.toByte, byte1))
      _ <- Entity.i.a1.byte_.odd.query.get.map(_ ==> List(-1, 1))
    } yield ()
  }


  "modulo" - types {
    for {
      _ <- Entity.i.byte.insert(
        (-4, -4.toByte),
        (-3, -3.toByte),
        (-2, -2.toByte),
        (-1, -1.toByte),
        (0, byte0),
        (1, byte1),
        (2, byte2),
        (3, byte3),
        (4, byte4),
      ).transact

      // Mandatory

      _ <- Entity.byte.%(byte2, byte0).query.get.map(_ ==> List(-4.toByte, -2.toByte, byte0, byte2, byte4))
      _ <- Entity.byte.%(byte2, byte1).query.get.map(_ ==> List(-3.toByte, -1.toByte, byte1, byte3))

      _ <- Entity.byte.%(byte3, byte0).query.get.map(_ ==> List(-3.toByte, byte0, byte3))
      _ <- Entity.byte.%(byte3, byte1).query.get.map(_ ==> List(-4.toByte, -1.toByte, byte1, byte4))
      _ <- Entity.byte.%(byte3, byte2).query.get.map(_ ==> List(-2.toByte, byte2))

      // Tacit

      _ <- Entity.i.byte_.%(byte2, byte0).query.get.map(_ ==> List(-4, -2, 0, 2, 4))
      _ <- Entity.i.byte_.%(byte2, byte1).query.get.map(_ ==> List(-3, -1, 1, 3))

      _ <- Entity.i.byte_.%(byte3, byte0).query.get.map(_ ==> List(-3, 0, 3))
      _ <- Entity.i.byte_.%(byte3, byte1).query.get.map(_ ==> List(-4, -1, 1, 4))
      _ <- Entity.i.byte_.%(byte3, byte2).query.get.map(_ ==> List(-2, 2))
    } yield ()
  }
}
