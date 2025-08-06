package molecule.db.compliance.test.filterAttr.set

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class Adjacent(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "has" - types {
    for {
      _ <- Entity.s.iSet.i.insert(
        ("a", Set(1, 2), 1),
        ("b", Set(3), 2),
      ).transact

      _ <- Entity.s.iSet.has(Entity.i_).query.get.map(_ ==> List(("a", Set(1, 2))))
      _ <- Entity.s.iSet_.has(Entity.i_).query.get.map(_ ==> List("a"))
    } yield ()
  }


  "hasNo" - types {
    for {
      _ <- Entity.s.iSet.i.insert(
        ("a", Set(1, 2), 1),
        ("b", Set(3), 2),
      ).transact

      _ <- Entity.s.iSet.hasNo(Entity.i_).query.get.map(_ ==> List(("b", Set(3))))
      _ <- Entity.s.iSet_.hasNo(Entity.i_).query.get.map(_ ==> List("b"))
    } yield ()
  }
}
