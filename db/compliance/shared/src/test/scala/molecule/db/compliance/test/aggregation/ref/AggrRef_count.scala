package molecule.db.compliance.test.aggregation.ref

import molecule.db.compliance.setup.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.domains.dsl.Refs.*

case class AggrRef_count(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "ref" - refs { implicit conn =>
    for {
      _ <- A.i.B.i.insert(List(
        (1, 1),
        (2, 2),
        (2, 2),
        (2, 3),
      )).transact

      _ <- A.B.i(count).query.get.map(_ ==> List(4))
      _ <- A.i.a1.B.i(count).query.get.map(_ ==> List(
        (1, 1),
        (2, 3)
      ))

      _ <- A.B.i(countDistinct).query.get.map(_ ==> List(3))
      _ <- A.i.a1.B.i(countDistinct).query.get.map(_ ==> List(
        (1, 1),
        (2, 2)
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

      _ <- A.B.C.i(count).query.get.map(_ ==> List(4))
      _ <- A.i.a1.B.i.C.i(count).query.get.map(_ ==> List(
        (1, 1, 1),
        (2, 2, 3)
      ))

      _ <- A.B.C.i(countDistinct).query.get.map(_ ==> List(3))
      _ <- A.i.a1.B.i.C.i(countDistinct).query.get.map(_ ==> List(
        (1, 1, 1),
        (2, 2, 2)
      ))
    } yield ()
  }


  "multiple refs" - refs { implicit conn =>
    for {
      _ <- A.i.B.i.C.i.insert(List(
        (1, 1, 1),
        (2, 2, 2),
        (2, 2, 2),
        (2, 3, 3),
      )).transact

      _ <- A.i.a1.B.i(count).C.i(count).query.get.map(_ ==> List(
        (1, 1, 1),
        (2, 3, 3)
      ))

      _ <- A.i.a1.B.i(countDistinct).C.i(countDistinct).query.get.map(_ ==> List(
        (1, 1, 1),
        (2, 2, 2)
      ))
    } yield ()
  }


  "backref" - refs { implicit conn =>
    for {
      _ <- A.i.B.i._A.C.i.insert(List(
        (1, 1, 1),
        (2, 2, 2),
        (2, 2, 2),
        (2, 3, 3),
      )).transact

      _ <- A.i.a1.B.i(count)._A.C.i(count).query.get.map(_ ==> List(
        (1, 1, 1),
        (2, 3, 3)
      ))

      _ <- A.i.a1.B.i(countDistinct)._A.C.i(countDistinct).query.get.map(_ ==> List(
        (1, 1, 1),
        (2, 2, 2)
      ))
    } yield ()
  }
}