package molecule.db.compliance.test.aggregation

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*


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


  "ref own" - refs {
    for {
      _ <- A.i.B.i.OwnC.s.i.insert(List(
        (1, 1, "a", 1),
        (1, 2, "a", 2),
        (1, 2, "b", 2),
        (2, 2, "b", 3),
      )).transact

      _ <- A.B.OwnC.i(count).query.get.map(_ ==> List(
        4
      ))
      _ <- A.B.OwnC.i(countDistinct).query.get.map(_ ==> List(
        3
      ))

      _ <- A.B.OwnC.s.a1.i(count).query.get.map(_ ==> List(
        ("a", 2),
        ("b", 2)
      ))
      _ <- A.B.OwnC.s.a1.i(countDistinct).query.get.map(_ ==> List(
        ("a", 2),
        ("b", 2)
      ))

      _ <- A.B.i.a1.OwnC.i(count).query.get.map(_ ==> List(
        (1, 1),
        (2, 3)
      ))
      _ <- A.B.i.a1.OwnC.i(countDistinct).query.get.map(_ ==> List(
        (1, 1),
        (2, 2)
      ))

      _ <- A.i.a1.B.OwnC.i(count).query.get.map(_ ==> List(
        (1, 3),
        (2, 1)
      ))
      _ <- A.i.a1.B.OwnC.i(countDistinct).query.get.map(_ ==> List(
        (1, 2),
        (2, 1)
      ))

      _ <- A.i.a1.B.i.a2.OwnC.i(count).query.get.map(_ ==> List(
        (1, 1, 1),
        (1, 2, 2),
        (2, 2, 1),
      ))
      _ <- A.i.a1.B.i.a2.OwnC.i(countDistinct).query.get.map(_ ==> List(
        (1, 1, 1),
        (1, 2, 1),
        (2, 2, 1),
      ))

      _ <- A.i.a1.B.i.a2.OwnC.s.a3.i(count).query.get.map(_ ==> List(
        (1, 1, "a", 1),
        (1, 2, "a", 1),
        (1, 2, "b", 1),
        (2, 2, "b", 1),
      ))
      _ <- A.i.a1.B.i.a2.OwnC.s.a3.i(countDistinct).query.get.map(_ ==> List(
        (1, 1, "a", 1),
        (1, 2, "a", 1),
        (1, 2, "b", 1),
        (2, 2, "b", 1),
      ))


      _ <- A.i.B.i(count).OwnC.i.a1.query.get.map(_ ==> List(
        (1, 1, 1),
        (1, 2, 2),
        (2, 1, 3),
      ))
      _ <- A.i.B.i(countDistinct).OwnC.i.a1.query.get.map(_ ==> List(
        (1, 1, 1),
        (1, 1, 2),
        (2, 1, 3),
      ))

      _ <- A.i.a1.B.i(count).OwnC.i_.query.get.map(_ ==> List(
        (1, 3),
        (2, 1),
      ))
      _ <- A.i.a1.B.i(countDistinct).OwnC.i_.query.get.map(_ ==> List(
        (1, 2),
        (2, 1),
      ))

      _ <- A.i_.B.i(count).OwnC.i.a1.query.get.map(_ ==> List(
        (1, 1),
        (2, 2),
        (1, 3),
      ))
      _ <- A.i_.B.i(countDistinct).OwnC.i.a1.query.get.map(_ ==> List(
        (1, 1),
        (1, 2),
        (1, 3),
      ))

      _ <- A.i_.B.i(count).OwnC.i_.query.get.map(_ ==> List(
        4
      ))
      _ <- A.i_.B.i(countDistinct).OwnC.i_.query.get.map(_ ==> List(
        2
      ))


      _ <- A.i(count).B.i.OwnC.i.a1.query.get.map(_ ==> List(
        (1, 1, 1),
        (2, 2, 2),
        (1, 2, 3),
      ))
      _ <- A.i(countDistinct).B.i.OwnC.i.a1.query.get.map(_ ==> List(
        (1, 1, 1),
        (1, 2, 2),
        (1, 2, 3),
      ))

      _ <- A.i(count).B.i.a1.OwnC.i_.query.get.map(_ ==> List(
        (1, 1),
        (3, 2),
      ))
      _ <- A.i(countDistinct).B.i.a1.OwnC.i_.query.get.map(_ ==> List(
        (1, 1),
        (2, 2),
      ))

      _ <- A.i(count).B.i_.OwnC.i.a1.query.get.map(_ ==> List(
        (1, 1),
        (2, 2),
        (1, 3),
      ))
      _ <- A.i(countDistinct).B.i_.OwnC.i.a1.query.get.map(_ ==> List(
        (1, 1),
        (1, 2),
        (1, 3),
      ))

      _ <- A.i(count).B.i_.OwnC.i_.query.get.map(_ ==> List(
        4
      ))
      _ <- A.i(countDistinct).B.i_.OwnC.i_.query.get.map(_ ==> List(
        2
      ))
    } yield ()
  }


  "own ref" - refs {
    for {
      _ <- A.i.OwnB.i.C.s.i.insert(List(
        (1, 1, "a", 1),
        (1, 2, "a", 2),
        (1, 2, "b", 2),
        (2, 2, "b", 3),
      )).transact

      _ <- A.OwnB.C.i(count).query.get.map(_ ==> List(
        4
      ))
      _ <- A.OwnB.C.i(countDistinct).query.get.map(_ ==> List(
        3
      ))

      _ <- A.OwnB.C.s.a1.i(count).query.get.map(_ ==> List(
        ("a", 2),
        ("b", 2)
      ))
      _ <- A.OwnB.C.s.a1.i(countDistinct).query.get.map(_ ==> List(
        ("a", 2),
        ("b", 2)
      ))

      _ <- A.OwnB.i.a1.C.i(count).query.get.map(_ ==> List(
        (1, 1),
        (2, 3)
      ))
      _ <- A.OwnB.i.a1.C.i(countDistinct).query.get.map(_ ==> List(
        (1, 1),
        (2, 2)
      ))

      _ <- A.i.a1.OwnB.C.i(count).query.get.map(_ ==> List(
        (1, 3),
        (2, 1)
      ))
      _ <- A.i.a1.OwnB.C.i(countDistinct).query.get.map(_ ==> List(
        (1, 2),
        (2, 1)
      ))

      _ <- A.i.a1.OwnB.i.a2.C.i(count).query.get.map(_ ==> List(
        (1, 1, 1),
        (1, 2, 2),
        (2, 2, 1),
      ))
      _ <- A.i.a1.OwnB.i.a2.C.i(countDistinct).query.get.map(_ ==> List(
        (1, 1, 1),
        (1, 2, 1),
        (2, 2, 1),
      ))

      _ <- A.i.a1.OwnB.i.a2.C.s.a3.i(count).query.get.map(_ ==> List(
        (1, 1, "a", 1),
        (1, 2, "a", 1),
        (1, 2, "b", 1),
        (2, 2, "b", 1),
      ))
      _ <- A.i.a1.OwnB.i.a2.C.s.a3.i(countDistinct).query.get.map(_ ==> List(
        (1, 1, "a", 1),
        (1, 2, "a", 1),
        (1, 2, "b", 1),
        (2, 2, "b", 1),
      ))


      _ <- A.i.OwnB.i(count).C.i.a1.query.get.map(_ ==> List(
        (1, 1, 1),
        (1, 2, 2),
        (2, 1, 3),
      ))
      _ <- A.i.OwnB.i(countDistinct).C.i.a1.query.get.map(_ ==> List(
        (1, 1, 1),
        (1, 1, 2),
        (2, 1, 3),
      ))

      _ <- A.i.a1.OwnB.i(count).C.i_.query.get.map(_ ==> List(
        (1, 3),
        (2, 1),
      ))
      _ <- A.i.a1.OwnB.i(countDistinct).C.i_.query.get.map(_ ==> List(
        (1, 2),
        (2, 1),
      ))

      _ <- A.i_.OwnB.i(count).C.i.a1.query.get.map(_ ==> List(
        (1, 1),
        (2, 2),
        (1, 3),
      ))
      _ <- A.i_.OwnB.i(countDistinct).C.i.a1.query.get.map(_ ==> List(
        (1, 1),
        (1, 2),
        (1, 3),
      ))

      _ <- A.i_.OwnB.i(count).C.i_.query.get.map(_ ==> List(
        4
      ))
      _ <- A.i_.OwnB.i(countDistinct).C.i_.query.get.map(_ ==> List(
        2
      ))


      _ <- A.i(count).OwnB.i.C.i.a1.query.get.map(_ ==> List(
        (1, 1, 1),
        (2, 2, 2),
        (1, 2, 3),
      ))
      _ <- A.i(countDistinct).OwnB.i.C.i.a1.query.get.map(_ ==> List(
        (1, 1, 1),
        (1, 2, 2),
        (1, 2, 3),
      ))

      _ <- A.i(count).OwnB.i.a1.C.i_.query.get.map(_ ==> List(
        (1, 1),
        (3, 2),
      ))
      _ <- A.i(countDistinct).OwnB.i.a1.C.i_.query.get.map(_ ==> List(
        (1, 1),
        (2, 2),
      ))

      _ <- A.i(count).OwnB.i_.C.i.a1.query.get.map(_ ==> List(
        (1, 1),
        (2, 2),
        (1, 3),
      ))
      _ <- A.i(countDistinct).OwnB.i_.C.i.a1.query.get.map(_ ==> List(
        (1, 1),
        (1, 2),
        (1, 3),
      ))

      _ <- A.i(count).OwnB.i_.C.i_.query.get.map(_ ==> List(
        4
      ))
      _ <- A.i(countDistinct).OwnB.i_.C.i_.query.get.map(_ ==> List(
        2
      ))
    } yield ()
  }


  "own own" - refs {
    for {
      _ <- A.i.OwnB.i.OwnC.s.i.insert(List(
        (1, 1, "a", 1),
        (1, 2, "a", 2),
        (1, 2, "b", 2),
        (2, 2, "b", 3),
      )).transact

      _ <- A.OwnB.OwnC.i(count).query.get.map(_ ==> List(
        4
      ))
      _ <- A.OwnB.OwnC.i(countDistinct).query.get.map(_ ==> List(
        3
      ))

      _ <- A.OwnB.OwnC.s.a1.i(count).query.get.map(_ ==> List(
        ("a", 2),
        ("b", 2)
      ))
      _ <- A.OwnB.OwnC.s.a1.i(countDistinct).query.get.map(_ ==> List(
        ("a", 2),
        ("b", 2)
      ))

      _ <- A.OwnB.i.a1.OwnC.i(count).query.get.map(_ ==> List(
        (1, 1),
        (2, 3)
      ))
      _ <- A.OwnB.i.a1.OwnC.i(countDistinct).query.get.map(_ ==> List(
        (1, 1),
        (2, 2)
      ))

      _ <- A.i.a1.OwnB.OwnC.i(count).query.get.map(_ ==> List(
        (1, 3),
        (2, 1)
      ))
      _ <- A.i.a1.OwnB.OwnC.i(countDistinct).query.get.map(_ ==> List(
        (1, 2),
        (2, 1)
      ))

      _ <- A.i.a1.OwnB.i.a2.OwnC.i(count).query.get.map(_ ==> List(
        (1, 1, 1),
        (1, 2, 2),
        (2, 2, 1),
      ))
      _ <- A.i.a1.OwnB.i.a2.OwnC.i(countDistinct).query.get.map(_ ==> List(
        (1, 1, 1),
        (1, 2, 1),
        (2, 2, 1),
      ))

      _ <- A.i.a1.OwnB.i.a2.OwnC.s.a3.i(count).query.get.map(_ ==> List(
        (1, 1, "a", 1),
        (1, 2, "a", 1),
        (1, 2, "b", 1),
        (2, 2, "b", 1),
      ))
      _ <- A.i.a1.OwnB.i.a2.OwnC.s.a3.i(countDistinct).query.get.map(_ ==> List(
        (1, 1, "a", 1),
        (1, 2, "a", 1),
        (1, 2, "b", 1),
        (2, 2, "b", 1),
      ))


      _ <- A.i.OwnB.i(count).OwnC.i.a1.query.get.map(_ ==> List(
        (1, 1, 1),
        (1, 2, 2),
        (2, 1, 3),
      ))
      _ <- A.i.OwnB.i(countDistinct).OwnC.i.a1.query.get.map(_ ==> List(
        (1, 1, 1),
        (1, 1, 2),
        (2, 1, 3),
      ))

      _ <- A.i.a1.OwnB.i(count).OwnC.i_.query.get.map(_ ==> List(
        (1, 3),
        (2, 1),
      ))
      _ <- A.i.a1.OwnB.i(countDistinct).OwnC.i_.query.get.map(_ ==> List(
        (1, 2),
        (2, 1),
      ))

      _ <- A.i_.OwnB.i(count).OwnC.i.a1.query.get.map(_ ==> List(
        (1, 1),
        (2, 2),
        (1, 3),
      ))
      _ <- A.i_.OwnB.i(countDistinct).OwnC.i.a1.query.get.map(_ ==> List(
        (1, 1),
        (1, 2),
        (1, 3),
      ))

      _ <- A.i_.OwnB.i(count).OwnC.i_.query.get.map(_ ==> List(
        4
      ))
      _ <- A.i_.OwnB.i(countDistinct).OwnC.i_.query.get.map(_ ==> List(
        2
      ))


      _ <- A.i(count).OwnB.i.OwnC.i.a1.query.get.map(_ ==> List(
        (1, 1, 1),
        (2, 2, 2),
        (1, 2, 3),
      ))
      _ <- A.i(countDistinct).OwnB.i.OwnC.i.a1.query.get.map(_ ==> List(
        (1, 1, 1),
        (1, 2, 2),
        (1, 2, 3),
      ))

      _ <- A.i(count).OwnB.i.a1.OwnC.i_.query.get.map(_ ==> List(
        (1, 1),
        (3, 2),
      ))
      _ <- A.i(countDistinct).OwnB.i.a1.OwnC.i_.query.get.map(_ ==> List(
        (1, 1),
        (2, 2),
      ))

      _ <- A.i(count).OwnB.i_.OwnC.i.a1.query.get.map(_ ==> List(
        (1, 1),
        (2, 2),
        (1, 3),
      ))
      _ <- A.i(countDistinct).OwnB.i_.OwnC.i.a1.query.get.map(_ ==> List(
        (1, 1),
        (1, 2),
        (1, 3),
      ))

      _ <- A.i(count).OwnB.i_.OwnC.i_.query.get.map(_ ==> List(
        4
      ))
      _ <- A.i(countDistinct).OwnB.i_.OwnC.i_.query.get.map(_ ==> List(
        2
      ))
    } yield ()
  }
}
