package molecule.db.compliance.test.filter.one.number

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class FilterOneInteger_Int(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "odd/even" - types {
    for {
      _ <- Entity.i.int.insert(
        (-2, -int2),
        (-1, -int1),
        (0, int0),
        (1, int1),
        (2, int2),
      ).transact

      _ <- Entity.int.even.a1.query.get.map(_ ==> List(-int2, int0, int2))
      _ <- Entity.i.a1.int_.even.query.get.map(_ ==> List(-2, 0, 2))

      _ <- Entity.int.odd.a1.query.get.map(_ ==> List(-int1, int1))
      _ <- Entity.i.a1.int_.odd.query.get.map(_ ==> List(-1, 1))
    } yield ()
  }


  "modulo" - types {
    for {
      _ <- Entity.i.int.insert(
        (-4, -int4),
        (-3, -int3),
        (-2, -int2),
        (-1, -int1),
        (0, int0),
        (1, int1),
        (2, int2),
        (3, int3),
        (4, int4),
      ).transact

      // Mandatory

      _ <- Entity.int.%(int2, int0).query.get.map(_ ==> List(-int4, -int2, int0, int2, int4))
      _ <- Entity.int.%(int2, int1).query.get.map(_ ==> List(-int3, -int1, int1, int3))

      _ <- Entity.int.%(int3, int0).query.get.map(_ ==> List(-int3, int0, int3))
      _ <- Entity.int.%(int3, int1).query.get.map(_ ==> List(-int4, -int1, int1, int4))
      _ <- Entity.int.%(int3, int2).query.get.map(_ ==> List(-int2, int2))

      // Tacit

      _ <- Entity.i.int_.%(int2, int0).query.get.map(_ ==> List(-4, -2, 0, 2, 4))
      _ <- Entity.i.int_.%(int2, int1).query.get.map(_ ==> List(-3, -1, 1, 3))

      _ <- Entity.i.int_.%(int3, int0).query.get.map(_ ==> List(-3, 0, 3))
      _ <- Entity.i.int_.%(int3, int1).query.get.map(_ ==> List(-4, -1, 1, 4))
      _ <- Entity.i.int_.%(int3, int2).query.get.map(_ ==> List(-2, 2))
    } yield ()
  }
}
