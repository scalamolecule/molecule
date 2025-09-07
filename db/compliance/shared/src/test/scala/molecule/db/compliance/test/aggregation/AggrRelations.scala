package molecule.db.compliance.test.aggregation

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders
import org.scalactic.Equality


case class AggrRelations(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "ref ref" - refs {
    for {
      _ <- A.i.B.i.C.s.i.insert(List(
        (1, 1, "a", 1),
        (1, 2, "a", 2),
        (1, 2, "b", 2),
        (2, 2, "b", 3),
      )).transact

      _ <- A.B.C.i(count).query.get.map(_ ==> List(
        4
      ))
      _ <- A.B.C.i(countDistinct).query.get.map(_ ==> List(
        3
      ))

      _ <- A.B.C.s.a1.i(count).query.get.map(_ ==> List(
        ("a", 2),
        ("b", 2)
      ))
      _ <- A.B.C.s.a1.i(countDistinct).query.get.map(_ ==> List(
        ("a", 2),
        ("b", 2)
      ))

      _ <- A.B.i.a1.C.i(count).query.get.map(_ ==> List(
        (1, 1),
        (2, 3)
      ))
      _ <- A.B.i.a1.C.i(countDistinct).query.get.map(_ ==> List(
        (1, 1),
        (2, 2)
      ))

      _ <- A.i.a1.B.C.i(count).query.get.map(_ ==> List(
        (1, 3),
        (2, 1)
      ))
      _ <- A.i.a1.B.C.i(countDistinct).query.get.map(_ ==> List(
        (1, 2),
        (2, 1)
      ))

      _ <- A.i.a1.B.i.a2.C.i(count).query.get.map(_ ==> List(
        (1, 1, 1),
        (1, 2, 2),
        (2, 2, 1),
      ))
      _ <- A.i.a1.B.i.a2.C.i(countDistinct).query.get.map(_ ==> List(
        (1, 1, 1),
        (1, 2, 1),
        (2, 2, 1),
      ))

      _ <- A.i.a1.B.i.a2.C.s.a3.i(count).query.get.map(_ ==> List(
        (1, 1, "a", 1),
        (1, 2, "a", 1),
        (1, 2, "b", 1),
        (2, 2, "b", 1),
      ))
      _ <- A.i.a1.B.i.a2.C.s.a3.i(countDistinct).query.get.map(_ ==> List(
        (1, 1, "a", 1),
        (1, 2, "a", 1),
        (1, 2, "b", 1),
        (2, 2, "b", 1),
      ))


      _ <- A.i.B.i(count).C.i.a1.query.get.map(_ ==> List(
        (1, 1, 1),
        (1, 2, 2),
        (2, 1, 3),
      ))
      _ <- A.i.B.i(countDistinct).C.i.a1.query.get.map(_ ==> List(
        (1, 1, 1),
        (1, 1, 2),
        (2, 1, 3),
      ))

      _ <- A.i.a1.B.i(count).C.i_.query.get.map(_ ==> List(
        (1, 3),
        (2, 1),
      ))
      _ <- A.i.a1.B.i(countDistinct).C.i_.query.get.map(_ ==> List(
        (1, 2),
        (2, 1),
      ))

      _ <- A.i_.B.i(count).C.i.a1.query.get.map(_ ==> List(
        (1, 1),
        (2, 2),
        (1, 3),
      ))
      _ <- A.i_.B.i(countDistinct).C.i.a1.query.get.map(_ ==> List(
        (1, 1),
        (1, 2),
        (1, 3),
      ))

      _ <- A.i_.B.i(count).C.i_.query.get.map(_ ==> List(
        4
      ))
      _ <- A.i_.B.i(countDistinct).C.i_.query.get.map(_ ==> List(
        2
      ))


      _ <- A.i(count).B.i.C.i.a1.query.get.map(_ ==> List(
        (1, 1, 1),
        (2, 2, 2),
        (1, 2, 3),
      ))
      _ <- A.i(countDistinct).B.i.C.i.a1.query.get.map(_ ==> List(
        (1, 1, 1),
        (1, 2, 2),
        (1, 2, 3),
      ))

      _ <- A.i(count).B.i.a1.C.i_.query.get.map(_ ==> List(
        (1, 1),
        (3, 2),
      ))
      _ <- A.i(countDistinct).B.i.a1.C.i_.query.get.map(_ ==> List(
        (1, 1),
        (2, 2),
      ))

      _ <- A.i(count).B.i_.C.i.a1.query.get.map(_ ==> List(
        (1, 1),
        (2, 2),
        (1, 3),
      ))
      _ <- A.i(countDistinct).B.i_.C.i.a1.query.get.map(_ ==> List(
        (1, 1),
        (1, 2),
        (1, 3),
      ))

      _ <- A.i(count).B.i_.C.i_.query.get.map(_ ==> List(
        4
      ))
      _ <- A.i(countDistinct).B.i_.C.i_.query.get.map(_ ==> List(
        2
      ))
    } yield ()
  }
}
