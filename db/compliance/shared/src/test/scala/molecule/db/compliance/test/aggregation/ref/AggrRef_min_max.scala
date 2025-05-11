package molecule.db.compliance.test.aggregation.ref

import molecule.db.compliance.setup.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.domains.dsl.Refs.*

case class AggrRef_min_max(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "ref" - refs { implicit conn =>
    for {
      _ <- A.i.B.i.insert(
        (1, 1),
        (1, 2),
        (1, 3),
        (2, 4),
        (2, 5),
        (2, 6),
      ).transact

      _ <- A.B.i(min).query.get.map(_ ==> List(1))
      _ <- A.B.i(max).query.get.map(_ ==> List(6))
      _ <- A.B.i(min).i(max).query.get.map(_ ==> List((1, 6)))

      _ <- A.i.a1.B.i(min).query.get.map(_ ==> List(
        (1, 1),
        (2, 4)
      ))

      _ <- A.i.a1.B.i(max).query.get.map(_ ==> List(
        (1, 3),
        (2, 6)
      ))

      _ <- A.i.a1.B.i(min).i(max).query.get.map(_ ==> List(
        (1, 1, 3),
        (2, 4, 6)
      ))
    } yield ()
  }


  "2nd ref" - refs { implicit conn =>
    for {
      _ <- A.i.B.i.C.i.insert(
        (1, 1, 1),
        (1, 1, 2),
        (1, 1, 3),
        (2, 2, 4),
        (2, 2, 5),
        (2, 2, 6),
      ).transact

      _ <- A.i.a1.B.i.C.i(min).query.get.map(_ ==> List(
        (1, 1, 1),
        (2, 2, 4),
      ))
      _ <- A.i.a1.B.i.C.i(max).query.get.map(_ ==> List(
        (1, 1, 3),
        (2, 2, 6),
      ))

      _ <- A.B.C.i(min).query.get.map(_ ==> List(1))
      _ <- A.B.C.i(max).query.get.map(_ ==> List(6))
      _ <- A.B.C.i(min).i(max).query.get.map(_ ==> List((1, 6)))

      _ <- A.i.a1.B.i.C.i(min).query.get.map(_ ==> List(
        (1, 1, 1),
        (2, 2, 4)
      ))

      _ <- A.i.a1.B.i.C.i(max).query.get.map(_ ==> List(
        (1, 1, 3),
        (2, 2, 6)
      ))

      _ <- A.i.a1.B.i.C.i(min).i(max).query.get.map(_ ==> List(
        (1, 1, 1, 3),
        (2, 2, 4, 6)
      ))
    } yield ()
  }


  "multiple refs" - refs { implicit conn =>
    for {
      _ <- A.i.B.i.C.i.insert(
        (1, 1, 1),
        (1, 1, 2),
        (1, 2, 3),
        (1, 2, 4),
        (2, 3, 5),
        (2, 3, 6),
        (2, 4, 7),
        (2, 4, 8),
      ).transact

      _ <- A.i.a1.B.i(min).C.i(min).query.get.map(_ ==> List(
        (1, 1, 1),
        (2, 3, 5),
      ))
      _ <- A.i.a1.B.i(min).C.i(max).query.get.map(_ ==> List(
        (1, 1, 4),
        (2, 3, 8),
      ))
      _ <- A.i.a1.B.i(max).C.i(min).query.get.map(_ ==> List(
        (1, 2, 1),
        (2, 4, 5),
      ))
      _ <- A.i.a1.B.i(max).C.i(max).query.get.map(_ ==> List(
        (1, 2, 4),
        (2, 4, 8),
      ))
    } yield ()
  }


  "backref" - refs { implicit conn =>
    for {
      _ <- A.i.B.i._A.C.i.insert(
        (1, 1, 1),
        (1, 1, 2),
        (1, 2, 3),
        (1, 2, 4),
        (2, 3, 5),
        (2, 3, 6),
        (2, 4, 7),
        (2, 4, 8),
      ).transact

      _ <- A.i.a1.B.i(min)._A.C.i(min).query.get.map(_ ==> List(
        (1, 1, 1),
        (2, 3, 5),
      ))
      _ <- A.i.a1.B.i(min)._A.C.i(max).query.get.map(_ ==> List(
        (1, 1, 4),
        (2, 3, 8),
      ))
      _ <- A.i.a1.B.i(max)._A.C.i(min).query.get.map(_ ==> List(
        (1, 2, 1),
        (2, 4, 5),
      ))
      _ <- A.i.a1.B.i(max)._A.C.i(max).query.get.map(_ ==> List(
        (1, 2, 4),
        (2, 4, 8),
      ))
    } yield ()
  }
}