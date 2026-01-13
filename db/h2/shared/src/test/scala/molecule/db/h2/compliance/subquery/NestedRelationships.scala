package molecule.db.h2.compliance.subquery

import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.h2.async.*
import molecule.db.h2.setup.DbProviders_h2

class NestedRelationships extends MUnit with DbProviders_h2 with TestUtils {

  "Aggregating attribute values across relationships" - refs {
    for {
      _ <- A.s.Bb.*(B.i.C.i).insert(
        ("a", List((1, 10), (2, 20))),
        ("b", List((3, 30), (4, 40), (5, 50))),
        ("c", List()),
      ).transact

      // Subquery can aggregate attribute values from nested relationships (.select)
      _ <- A.s.a1.select(B.id(count).a_(A.id_).C.i(sum)).query.get.map(_ ==> List(
          ("a", (2, 30)),   // 2 B's, sum of C.i = 10+20
          ("b", (3, 120)),  // 3 B's, sum of C.i = 30+40+50
          ("c", (0, 0)),    // No B's
        ))

      // SQL inspection (.select): verify nested relationship in subquery
      _ <- A.s.select(B.id(count).a_(A.id_).C.i(sum)).query.inspect.map(_.contains(
        """SELECT DISTINCT
          |  A.s,
          |  (
          |    SELECT
          |      COUNT(B.id),
          |      SUM(C.i)
          |    FROM B
          |      INNER JOIN C ON B.c = C.id
          |    WHERE
          |      B.a = A.id
          |  )""".stripMargin
      ) ==> true)

      // Same with .join()
      _ <- A.s.a1.join(B.id(count).a_(A.id_).C.i(sum)).query.get.map(_ ==> List(
          ("a", (2, 30)),
          ("b", (3, 120)),
          // "c" excluded
        ))
    } yield ()
  }


  "Boundary: Cannot aggregate IDs on related entities" - refs {
    for {
      _ <- A.s.Bb.*(B.i.C.i).insert(
        ("a", List((1, 10), (2, 20))),
        ("b", List((3, 30), (4, 40), (5, 50))),
        ("c", List()),
      ).transact

      // Cannot aggregate IDs across relationships (COUNT on both B.id and C.id)
      _ <- A.s.a1.select(B.id(count).a_(A.id_).C.id(count))
        .query.get.map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Cannot aggregate IDs on related entities in subqueries. Found ID aggregates on: B, C"
        }

      // Same with .join()
      _ <- A.s.a1.join(B.id(count).a_(A.id_).C.id(count))
        .query.get.map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Cannot aggregate IDs on related entities in subqueries. Found ID aggregates on: B, C"
        }
    } yield ()
  }
}
