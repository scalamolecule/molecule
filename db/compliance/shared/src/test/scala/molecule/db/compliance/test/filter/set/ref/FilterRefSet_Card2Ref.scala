package molecule.db.compliance.test.filter.set.ref

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class FilterRefSet_Card2Ref(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "mandatory" - refs {
    for {
      _ <- A.i.Bb.iSet.insert(
        (1, Set(1, 2)),
        (2, Set(2)),
        (2, Set(7)),
        (3, Set(3)),
        (4, Set())
      ).transact

      // has

      _ <- A.i.a1.Bb.iSet.has(1).query.get.map(_ ==> List(
        (1, Set(1, 2)),
      ))
      _ <- A.i.a1.Bb.iSet.has(2).query.get.map(_ ==> List(
        (1, Set(1, 2)),
        (2, Set(2)),
      ))

      _ <- A.i.a1.Bb.iSet.has(2, 1).query.get.map(_ ==> List(
        (1, Set(1, 2)),
        (2, Set(2)),
      ))
      _ <- A.i.a1.Bb.iSet.has(2, 7).query.get.map(_ ==> List(
        (1, Set(1, 2)),
        (2, Set(2, 7)),
      ))
      _ <- A.i.a1.Bb.iSet.has(2, 3).query.get.map(_ ==> List(
        (1, Set(1, 2)),
        (2, Set(2)),
        (3, Set(3)),
      ))


      // hasNo

      _ <- A.i.a1.Bb.iSet.hasNo(1).query.get.map(_ ==> List(
        (2, Set(2, 7)),
        (3, Set(3)),
      ))
      _ <- A.i.a1.Bb.iSet.hasNo(2).query.get.map(_ ==> List(
        (2, Set(7)),
        (3, Set(3)),
      ))
      _ <- A.i.a1.Bb.iSet.hasNo(3).query.get.map(_ ==> List(
        (1, Set(1, 2)),
        (2, Set(2, 7))
      ))
    } yield ()
  }


  "tacit" - refs {
    for {
      _ <- A.i.Bb.iSet.insert(
        (1, Set(1, 2)),
        (2, Set(2)),
        (2, Set(7)),
        (3, Set(3)),
        (4, Set())
      ).transact

      // has
      _ <- A.i.a1.Bb.iSet_.has(1).query.get.map(_ ==> List(1))
      _ <- A.i.a1.Bb.iSet_.has(2).query.get.map(_ ==> List(1, 2))
      _ <- A.i.a1.Bb.iSet_.has(2, 7).query.get.map(_ ==> List(1, 2))
      _ <- A.i.a1.Bb.iSet_.has(2, 3).query.get.map(_ ==> List(1, 2, 3))

      // hasNo
      _ <- A.i.a1.Bb.iSet_.hasNo(1).query.get.map(_ ==> List(2, 3))
      _ <- A.i.a1.Bb.iSet_.hasNo(2).query.get.map(_ ==> List(2, 3))
      _ <- A.i.a1.Bb.iSet_.hasNo(3).query.get.map(_ ==> List(1, 2))

      // no value - match non-asserted attribute (null)
      _ <- A.i.a1.Bb.iSet_().query.get.map(_ ==> List(4))
    } yield ()
  }

  // No filtering on optional Set attributes
}
