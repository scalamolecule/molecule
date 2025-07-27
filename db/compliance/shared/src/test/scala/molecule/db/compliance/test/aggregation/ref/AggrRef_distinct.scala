package molecule.db.compliance.test.aggregation.ref

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import org.scalactic.Equality

case class AggrRef_distinct(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "ref" - refs {
    for {
      _ <- A.i.B.i.insert(List(
        (1, 1),
        (2, 2),
        (2, 2),
        (2, 3),
      )).transact

      _ <- A.i.B.i.a1.query.get.map(_ ==> List(
        (1, 1),
        (2, 2), // 2 rows coalesced
        (2, 3),
      ))

      // Distinct values are returned in a Set
      _ <- A.i.a1.B.i(distinct).query.get.map(_ ==> List(
        (1, Set(1)),
        (2, Set(2, 3)),
      ))

      _ <- A.B.i(distinct).query.get.map(_.head ==> Set(1, 2, 3))
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

      _ <- A.i.B.i.C.i.a1.query.get.map(_ ==> List(
        (1, 1, 1),
        (2, 2, 2), // 2 rows coalesced
        (2, 2, 3),
      ))

      // Distinct values are returned in a Set
      _ <- A.i.a1.B.i.C.i(distinct).query.get.map(_ ==> List(
        (1, 1, Set(1)),
        (2, 2, Set(2, 3)),
      ))

      _ <- A.B.C.i(distinct).query.get.map(_.head ==> Set(1, 2, 3))
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

      _ <- A.i.B.i.C.i.a1.query.get.map(_ ==> List(
        (1, 1, 1),
        (2, 2, 2), // 2 rows coalesced
        (2, 2, 3),
      ))

      // Distinct values are returned in a Set
      _ <- A.i.a1.B.i(distinct).C.i(distinct).query.get.map(_ ==> List(
        (1, Set(1), Set(1)),
        (2, Set(2), Set(2, 3)),
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

      _ <- A.i.B.i._A.C.i.a1.query.get.map(_ ==> List(
        (1, 1, 1),
        (2, 2, 2), // 2 rows coalesced
        (2, 2, 3),
      ))

      // Distinct values are returned in a Set
      _ <- A.i.a1.B.i(distinct)._A.C.i(distinct).query.get.map(_ ==> List(
        (1, Set(1), Set(1)),
        (2, Set(2), Set(2, 3)),
      ))
    } yield ()
  }
}