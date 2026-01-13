package molecule.db.h2.compliance.subquery

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.h2.async.*
import molecule.db.h2.setup.DbProviders_h2

class Filtering extends MUnit with DbProviders_h2 with TestUtils {

  "inside subquery" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(1, 2, 3)),
        ("b", List(2, 3, 4, 5)),
        ("c", List(5, 6)),
        ("d", List(1)), // Only low values
      ).transact

      // Count only refs where i > 2 (.select)
      _ <- Entity.s.a1.select(Ref.i_.>(2).id(count).entity_(Entity.id_)).query.get.map(_ ==> List(
          ("a", 1), // only i=3
          ("b", 3), // i=3,4,5
          ("c", 2), // i=5,6
          ("d", 0), // no refs match filter
        ))

      // Count only refs where i > 2 (.join)
      _ <- Entity.s.a1.join(Ref.i_.>(2).id(count).entity_(Entity.id_)).query.get.map(_ ==> List(
          ("a", 1),
          ("b", 3),
          ("c", 2),
          // "d" excluded - no refs match filter
        ))

      // SQL inspection (.select): filter in WHERE clause of subquery
      _ <- Entity.s.select(Ref.i_.>(2).id(count).entity_(Entity.id_)).query.inspect.map(_.contains(
        """SELECT
          |      COUNT(Ref.id)
          |    FROM Ref
          |    WHERE
          |      Ref.i      > 2 AND
          |      Ref.entity = Entity.id""".stripMargin
      ) ==> true)

      // SQL inspection (.join): filter in WHERE clause before GROUP BY
      _ <- Entity.s.join(Ref.i_.>(2).id(count).entity_(Entity.id_)).query.inspect.map(_.contains(
        """SELECT DISTINCT
          |  Entity.s,
          |  subquery1.Ref_id_count
          |FROM Entity INNER JOIN (
          |    SELECT
          |      COUNT(Ref.id) AS Ref_id_count,
          |      Ref.entity
          |    FROM Ref
          |    WHERE
          |      Ref.i > 2
          |    GROUP BY Ref.entity
          |  ) subquery1 ON Entity.id = subquery1.entity
          |WHERE
          |  Entity.s IS NOT NULL;""".stripMargin
      ) ==> true)
    } yield ()
  }


  "outer query" - types {
    for {
      _ <- Entity.s.i.Refs.*(Ref.i).insert(
        ("a", 1, List(10, 20)),
        ("b", 2, List(30, 40, 50)),
        ("c", 3, List()),
        ("d", 4, List(60)),
      ).transact

      // Filter entities where i < 4 (.select) - includes "c" with 0 refs
      _ <- Entity.s.i.<(4).a1.select(Ref.id(count).entity_(Entity.id_)).query.get.map(_ ==> List(
          ("a", 1, 2),
          ("b", 2, 3),
          ("c", 3, 0), // Included (passes filter, has 0 refs)
          // "d" excluded by outer filter
        ))

      // Filter entities where i < 4 (.join) - excludes "c" (no refs to join)
      _ <- Entity.s.i.<(4).a1.join(Ref.id(count).entity_(Entity.id_)).query.get.map(_ ==> List(
          ("a", 1, 2),
          ("b", 2, 3),
          // "c" excluded - no refs
          // "d" excluded by outer filter
        ))
    } yield ()
  }


  "outer and subquery" - types {
    for {
      _ <- Entity.s.i.Refs.*(Ref.i).insert(
        ("a", 1, List(5, 10, 15)),
        ("b", 2, List(20, 25)),
        ("c", 3, List(5, 6)), // Only low values
        ("d", 4, List(30)),
      ).transact

      // Outer: i < 4, Subquery: Ref.i > 10 (.select)
      _ <- Entity.s.i.<(4).a1.select(Ref.i_.>(10).id(count).entity_(Entity.id_)).query.get.map(_ ==> List(
          ("a", 1, 1), // only i=15
          ("b", 2, 2), // i=20,25
          ("c", 3, 0), // no refs match subquery filter
          // "d" excluded by outer filter
        ))

      // Outer: i < 4, Subquery: Ref.i > 10 (.join)
      _ <- Entity.s.i.<(4).a1.join(Ref.i_.>(10).id(count).entity_(Entity.id_)).query.get.map(_ ==> List(
          ("a", 1, 1),
          ("b", 2, 2),
          // "c" excluded - no refs match subquery filter
          // "d" excluded by outer filter
        ))
    } yield ()
  }


  "multiple in subquery" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i.s).insert(
        ("a", List((1, "x"), (5, "y"), (10, "z"))),
        ("b", List((2, "m"), (8, "n"))),
        ("c", List()),
      ).transact

      // Count refs where i > 3 AND s != "n" (.select)
      _ <- Entity.s.a1.select(Ref.i_.>(3).s_.not("n").id(count).entity_(Entity.id_)).query.get.map(_ ==> List(
          ("a", 2), // i=5,10 (both have s != "n")
          ("b", 0), // i=8 but s="n" is excluded
          ("c", 0),
        ))

      // Count refs where i > 3 AND s != "n" (.join)
      _ <- Entity.s.a1.join(Ref.i_.>(3).s_.not("n").id(count).entity_(Entity.id_)).query.get.map(_ ==> List(
          ("a", 2),
          // "b" excluded - no refs match filters
          // "c" excluded - no refs
        ))
    } yield ()
  }


  "on aggregate result (having-like)" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(1, 2, 3)),
        ("b", List(4, 5)),
        ("c", List(6)),
        ("d", List()),
      ).transact

      // Filter where count > 1 (.select)
      // Note: Subquery still returns result for all entities
      _ <- Entity.s.a1.select(Ref.id(count).>(1).entity_(Entity.id_)).query.get.map(_ ==> List(
          ("a", 3), // matches count > 1
          ("b", 2), // matches count > 1
          ("c", 0), // doesn't match, but row is included (subquery in SELECT)
          ("d", 0), // doesn't match, but row is included
        ))

      // Filter where count > 1 (.join)
      // Note: Entities without matching refs are excluded
      _ <- Entity.s.a1.join(Ref.id(count).>(1).entity_(Entity.id_)).query.get.map(_ ==> List(
          ("a", 3),
          ("b", 2),
          // "c" excluded - count=1 not > 1
          // "d" excluded - no refs
        ))

      // Filter where sum > 5 (.select)
      _ <- Entity.s.a1.select(Ref.i(sum).>(5).entity_(Entity.id_)).query.get.map(_ ==> List(
          ("a", 6),  // 1+2+3 > 5
          ("b", 9),  // 4+5 > 5
          ("c", 6),  // 6 > 5
          ("d", 0),  // Included but doesn't match
        ))

      // Filter where sum > 5 (.join)
      _ <- Entity.s.a1.join(Ref.i(sum).>(5).entity_(Entity.id_)).query.get.map(_ ==> List(
          ("a", 6),
          ("b", 9),
          ("c", 6),
          // "d" excluded
        ))
    } yield ()
  }


  "range in subquery" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(1, 5, 10, 15, 20)),
        ("b", List(2, 3, 4)),
        ("c", List(18, 19, 20)),
        ("d", List()),
      ).transact

      // Count refs where 5 <= i <= 15 (.select)
      _ <- Entity.s.a1.select(Ref.i_.>=(5).i_.<=(15).id(count).entity_(Entity.id_)).query.get.map(_ ==> List(
          ("a", 3), // i=5,10,15
          ("b", 0), // all values < 5
          ("c", 0), // all values > 15
          ("d", 0),
        ))

      // Count refs where 5 <= i <= 15 (.join)
      _ <- Entity.s.a1.join(Ref.i_.>=(5).i_.<=(15).id(count).entity_(Entity.id_)).query.get.map(_ ==> List(
          ("a", 3),
          // "b", "c", "d" all excluded
        ))
    } yield ()
  }


  "outer + subquery + having" - types {
    for {
      _ <- Entity.s.i.Refs.*(Ref.i).insert(
        ("a", 1, List(10, 20, 30)),
        ("b", 2, List(5, 15)),
        ("c", 3, List(2, 4, 6, 8)),
        ("d", 4, List()),
      ).transact

      // Outer: i < 4
      // Subquery: Ref.i > 5
      // Having: sum > 20
      // (.select) - all passing outer filter are included
      _ <- Entity.s.i.<(4).a1.select(Ref.i_.>(5).i(sum).>(20).entity_(Entity.id_)).query.get.map(_ ==> List(
          ("a", 1, 60), // 10+20+30 > 20
          ("b", 2, 0), // only i=15 > 5, but 15 not > 20 (still included)
          ("c", 3, 0), // i=6,8 > 5, sum=14 not > 20 (still included)
          // "d" excluded by outer filter
        ))

      // Same filters with .join() - only matching entities
      _ <- Entity.s.i.<(4).a1.join(Ref.i_.>(5).i(sum).>(20).entity_(Entity.id_)).query.get.map(_ ==> List(
          ("a", 1, 60),
          // "b" excluded - sum not > 20
          // "c" excluded - sum not > 20
          // "d" excluded by outer filter
        ))
    } yield ()
  }


  "negation" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i.s).insert(
        ("a", List((1, "x"), (2, "y"), (3, "x"))),
        ("b", List((4, "y"), (5, "z"))),
        ("c", List()),
      ).transact

      // Count refs where s != "x" (.select)
      _ <- Entity.s.a1.select(Ref.s_.not("x").id(count).entity_(Entity.id_)).query.get.map(_ ==> List(
          ("a", 1), // only (2, "y")
          ("b", 2), // both refs
          ("c", 0),
        ))

      // Count refs where s != "x" (.join)
      _ <- Entity.s.a1.join(Ref.s_.not("x").id(count).entity_(Entity.id_)).query.get.map(_ ==> List(
          ("a", 1),
          ("b", 2),
          // "c" excluded
        ))
    } yield ()
  }


  "product filtering" - types {
    for {
      // Product, price, (review_score, review_date)
      _ <- Entity.s.i.Refs.*(Ref.i.s).insert(
        ("premium", 1000, List((5, "2024-01"), (4, "2024-02"), (5, "2024-03"))),
        ("standard", 500, List((3, "2024-01"), (3, "2024-02"))),
        ("budget", 100, List((2, "2023-12"))), // Old review
        ("new-product", 250, List()), // No reviews yet
      ).transact

      // Find products with price < 600 that have recent good reviews (.select)
      // Recent = "2024-*", Good = score >= 4
      _ <- Entity.s.i.<(600).a1
        .select(Ref.i_.>=(4).s_.startsWith("2024").id(count).entity_(Entity.id_))
        .query.get.map(_ ==> List(
          ("budget", 100, 0),       // No recent good reviews
          ("new-product", 250, 0),  // No reviews
          ("standard", 500, 0),     // Recent but not good (3,3)
          // "premium" excluded by price filter
        ))

      // Same with .join() - only products matching all criteria
      _ <- Entity.s.i.<(600).a1
        .join(Ref.i_.>=(4).s_.startsWith("2024").id(count).entity_(Entity.id_))
        .query.get.map(_ ==> List(
          // All excluded - none have recent good reviews in price range
        ))
    } yield ()
  }
}
