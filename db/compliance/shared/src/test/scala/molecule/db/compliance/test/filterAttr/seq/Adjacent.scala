package molecule.db.compliance.test.filterAttr.seq

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class Adjacent(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "has" - types {
    for {
      _ <- Entity.s.iSeq.i.insert(
        ("a", List(1, 2), 1),
        ("b", List(3), 2),
      ).transact

      _ <- Entity.s.iSeq.has(Entity.i_).query.get.map(_ ==> List(("a", List(1, 2))))
      _ <- Entity.s.iSeq_.has(Entity.i_).query.get.map(_ ==> List("a"))
    } yield ()
  }


  "hasNo" - types {
    for {
      _ <- Entity.s.iSeq.i.insert(
        ("a", List(1, 2), 1),
        ("b", List(3), 2),
      ).transact

      _ <- Entity.s.iSeq.hasNo(Entity.i_).query.get.map(_ ==> List(("b", List(3))))
      _ <- Entity.s.iSeq_.hasNo(Entity.i_).query.get.map(_ ==> List("b"))
    } yield ()
  }
}
