// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.one.number

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class FilterOneInteger_BigInt_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "odd/even" - types {
    for {
      _ <- Entity.i.bigInt.insert(
        (-2, -bigInt2),
        (-1, -bigInt1),
        (0, bigInt0),
        (1, bigInt1),
        (2, bigInt2),
      ).transact

      _ <- Entity.bigInt.even.a1.query.get.map(_ ==> List(-bigInt2, bigInt0, bigInt2))
      _ <- Entity.i.a1.bigInt_.even.query.get.map(_ ==> List(-2, 0, 2))

      _ <- Entity.bigInt.odd.a1.query.get.map(_ ==> List(-bigInt1, bigInt1))
      _ <- Entity.i.a1.bigInt_.odd.query.get.map(_ ==> List(-1, 1))
    } yield ()
  }


  "modulo" - types {
    for {
      _ <- Entity.i.bigInt.insert(
        (-4, -bigInt4),
        (-3, -bigInt3),
        (-2, -bigInt2),
        (-1, -bigInt1),
        (0, bigInt0),
        (1, bigInt1),
        (2, bigInt2),
        (3, bigInt3),
        (4, bigInt4),
      ).transact

      // Mandatory

      _ <- Entity.bigInt.%(bigInt2, bigInt0).query.get.map(_ ==> List(-bigInt4, -bigInt2, bigInt0, bigInt2, bigInt4))
      _ <- Entity.bigInt.%(bigInt2, bigInt1).query.get.map(_ ==> List(-bigInt3, -bigInt1, bigInt1, bigInt3))

      _ <- Entity.bigInt.%(bigInt3, bigInt0).query.get.map(_ ==> List(-bigInt3, bigInt0, bigInt3))
      _ <- Entity.bigInt.%(bigInt3, bigInt1).query.get.map(_ ==> List(-bigInt4, -bigInt1, bigInt1, bigInt4))
      _ <- Entity.bigInt.%(bigInt3, bigInt2).query.get.map(_ ==> List(-bigInt2, bigInt2))

      // Tacit

      _ <- Entity.i.bigInt_.%(bigInt2, bigInt0).query.get.map(_ ==> List(-4, -2, 0, 2, 4))
      _ <- Entity.i.bigInt_.%(bigInt2, bigInt1).query.get.map(_ ==> List(-3, -1, 1, 3))

      _ <- Entity.i.bigInt_.%(bigInt3, bigInt0).query.get.map(_ ==> List(-3, 0, 3))
      _ <- Entity.i.bigInt_.%(bigInt3, bigInt1).query.get.map(_ ==> List(-4, -1, 1, 4))
      _ <- Entity.i.bigInt_.%(bigInt3, bigInt2).query.get.map(_ ==> List(-2, 2))
    } yield ()
  }
}
