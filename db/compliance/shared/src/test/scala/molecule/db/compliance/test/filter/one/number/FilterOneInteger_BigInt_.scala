// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.one.number

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class FilterOneInteger_BigInt_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "odd/even" - types { implicit conn =>
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


  "modulo" - types { implicit conn =>
    for {
      _ <- Entity.i.bigInt.insert(
        (1, bigInt1),
        (2, bigInt2),
        (3, bigInt3),
        (4, bigInt4),
        (5, bigInt5),
        (6, bigInt6),
        (7, bigInt7),
        (8, bigInt8),
        (9, bigInt9),
      ).transact

      // Mandatory

      _ <- Entity.bigInt.%(bigInt2, bigInt0).query.get.map(_ ==> List(bigInt2, bigInt4, bigInt6, bigInt8))
      _ <- Entity.bigInt.%(bigInt2, bigInt1).query.get.map(_ ==> List(bigInt1, bigInt3, bigInt5, bigInt7, bigInt9))

      _ <- Entity.bigInt.%(bigInt3, bigInt0).query.get.map(_ ==> List(bigInt3, bigInt6, bigInt9))
      _ <- Entity.bigInt.%(bigInt3, bigInt1).query.get.map(_ ==> List(bigInt1, bigInt4, bigInt7))
      _ <- Entity.bigInt.%(bigInt3, bigInt2).query.get.map(_ ==> List(bigInt2, bigInt5, bigInt8))

      // Tacit

      _ <- Entity.i.bigInt_.%(bigInt2, bigInt0).query.get.map(_ ==> List(2, 4, 6, 8))
      _ <- Entity.i.bigInt_.%(bigInt2, bigInt1).query.get.map(_ ==> List(1, 3, 5, 7, 9))

      _ <- Entity.i.bigInt_.%(bigInt3, bigInt0).query.get.map(_ ==> List(3, 6, 9))
      _ <- Entity.i.bigInt_.%(bigInt3, bigInt1).query.get.map(_ ==> List(1, 4, 7))
      _ <- Entity.i.bigInt_.%(bigInt3, bigInt2).query.get.map(_ ==> List(2, 5, 8))
    } yield ()
  }
}
