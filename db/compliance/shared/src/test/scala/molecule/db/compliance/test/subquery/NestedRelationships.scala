package molecule.db.compliance.test.subquery

import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders

case class NestedRelationships(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Aggregating attribute values across relationships" - refs {
    for {
      _ <- A.s.Bb.*(B.i.C.i).insert(
        ("a", List((1, 10), (2, 20))),
        ("b", List((3, 30), (4, 40), (5, 50))),
        ("c", List()),
      ).transact

      // Subquery can aggregate attribute values from nested relationships
      _ <- A.s.a1.join(B.id(count).a_(A.id_).C.i(sum)).query.get.map(_ ==> List(
        ("a", (2, 30)), // 2 B's, sum of C.i = 10+20
        ("b", (3, 120)), // 3 B's, sum of C.i = 30+40+50
        // "c" excluded - no B's
      ))
    } yield ()
  }


  "Aggregate IDs on related entities (regression test of specially handled edge case)" - refs {
    for {
      _ <- A.s.Bb.*(B.i.C.i).insert(
        ("a", List((1, 10), (2, 20))),
        ("b", List((3, 30), (4, 40), (5, 50))),
        ("c", List()),
      ).transact

      // Aggregate IDs across relationships
      _ <- A.s.a1.join(B.id(count).a_(A.id_).C.id(count)).query.get.map(_ ==> List(
        ("a", (2, 2)),
        ("b", (3, 3)),
        // "c" excluded - no refs to B
      ))
    } yield ()
  }
}
