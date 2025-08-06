package molecule.db.compliance.test.filterAttr.set

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders

case class CrossEntity(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "has" - refs {
    for {
      _ <- A.i.iSet.B.iSet.i.insert(
        (1, Set(1, 2), Set(1, 2, 3), 3),
        (2, Set(2, 3), Set(2, 3), 3),
        (2, Set(4), Set(4), 4),
      ).transact

      _ <- A.i.iSet_.has(B.i_).B.iSet.i.a1.query.get.map(_ ==> List(
        (2, Set(2, 3), 3),
        (2, Set(4), 4),
      ))
      _ <- A.i.iSet.has(B.i_).B.iSet_.i.a1.query.get.map(_ ==> List(
        (2, Set(2, 3), 3),
        (2, Set(4), 4),
      ))

      _ <- A.i.a1.iSet_.B.iSet.has(A.i_).query.get.map(_ ==> List(
        (1, Set(1, 2, 3)),
        (2, Set(2, 3)),
      ))
      _ <- A.i.a1.iSet.B.iSet_.has(A.i_).query.get.map(_ ==> List(
        (1, Set(1, 2)),
        (2, Set(2, 3)),
      ))
    } yield ()
  }


  "hasNo" - refs {
    for {
      _ <- A.i.iSet.B.iSet.i.insert(
        (1, Set(1, 2), Set(1, 2, 3), 3),
        (2, Set(2, 3), Set(2, 3), 3),
        (2, Set(4), Set(4), 4),
        (2, Set(4), Set(3), 4),
      ).transact

      _ <- A.i.iSet_.hasNo(B.i_).B.iSet.i.a1.query.get.map(_ ==> List(
        (1, Set(1, 2, 3), 3),
      ))
      _ <- A.i.iSet.hasNo(B.i_).B.iSet_.i.a1.query.get.map(_ ==> List(
        (1, Set(1, 2), 3),
      ))

      _ <- A.i.a1.iSet_.B.iSet.hasNo(A.i_).query.get.map(_ ==> List(
        (2, Set(3, 4)),
      ))
      _ <- A.i.a1.iSet.B.iSet_.hasNo(A.i_).query.get.map(_ ==> List(
        (2, Set(4)),
      ))
    } yield ()
  }
}
