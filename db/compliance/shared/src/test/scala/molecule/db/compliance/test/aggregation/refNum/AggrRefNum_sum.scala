package molecule.db.compliance.test.aggregation.refNum

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import org.scalactic.Equality

case class AggrRefNum_sum(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  // Sum of distinct values (Set semantics)

  import api.*
  import suite.*

  "ref" - refs {
    implicit val tolerant = tolerantIntEquality(toleranceInt)
    for {
      _ <- A.i.B.i.insert(List(
        (1, 1),
        (1, 2),
        (2, 2),
        (2, 3),
        (2, 4),
      )).transact

      // Sum of all (non-coalesced) values
      _ <- A.B.i(sum).query.get.map(_.head ==> 1 + 2 + 2 + 3 + 4)
      _ <- A.i.a1.B.i(sum).query.get.map(_ ==> List(
        (1, 1 + 2),
        (2, 2 + 3 + 4),
      ))
    } yield ()
  }


  "2nd ref" - refs {
    for {
      _ <- A.i.B.i.C.i.insert(List(
        (1, 1, 1),
        (2, 2, 2),
        (2, 2, 2),
        (2, 2, 3),
      )).transact

      _ <- A.B.C.i(sum).query.get.map(_ ==> List(1 + 2 + 2 + 3))
      _ <- A.i.a1.B.i.C.i(sum).query.get.map(_ ==> List(
        (1, 1, 1),
        (2, 2, 2 + 2 + 3)
      ))
    } yield ()
  }


  "multiple refs" - refs {
    for {
      _ <- A.i.B.i.C.i.insert(List(
        (1, 1, 1),
        (2, 2, 2),
        (2, 2, 2),
        (2, 2, 3),
      )).transact

      _ <- A.i.a1.B.i(sum).C.i(sum).query.get.map(_ ==> List(
        (1, 1, 1),
        (2, 2 + 2 + 2, 2 + 2 + 3),
      ))
    } yield ()
  }


  "backref" - refs {
    for {
      _ <- A.i.B.i._A.C.i.insert(List(
        (1, 1, 1),
        (2, 2, 2),
        (2, 2, 2),
        (2, 2, 3),
      )).transact

      _ <- A.i.a1.B.i(sum)._A.C.i(sum).query.get.map(_ ==> List(
        (1, 1, 1),
        (2, 2 + 2 + 2, 2 + 2 + 3),
      ))
    } yield ()
  }
}