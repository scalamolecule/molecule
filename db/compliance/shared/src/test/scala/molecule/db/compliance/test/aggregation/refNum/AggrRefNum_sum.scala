package molecule.db.compliance.test.aggregation.refNum

import molecule.db.compliance.setup.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.domains.dsl.Refs.*

case class AggrRefNum_sum(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  // Sum of distinct values (Set semantics)

  import api.*
  import suite.*

  "ref" - refs { implicit conn =>
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


  "2nd ref" - refs { implicit conn =>
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


  "multiple refs" - refs { implicit conn =>
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


  "backref" - refs { implicit conn =>
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