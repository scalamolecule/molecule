package molecule.db.h2.compliance.subquery

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.h2.async.*
import molecule.db.h2.setup.DbProviders_h2

class SelectVsJoin extends MUnit with DbProviders_h2 with TestUtils {

  // This test suite demonstrates the semantic differences between .select() and .join()
  // subqueries, helping users understand when to use each approach.


  "basic count" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(1, 2)),
        ("b", List(3, 4, 5)),
        ("c", List()), // Entity with no refs
      ).transact

      // .select() - Scalar subquery (like LEFT JOIN)
      // Returns ALL entities, even those with no matching refs
      _ <- Entity.s.a1.select(Ref.id(count).entity_(Entity.id_)).query.get.map(_ ==> List(
        ("a", 2),
        ("b", 3),
        ("c", 0), // ✓ Included with count 0
      ))

      // SQL for .select(): Correlated subquery in SELECT clause
      _ <- Entity.s.select(Ref.id(count).entity_(Entity.id_)).query.inspect.map(_.contains(
        """SELECT DISTINCT
          |  Entity.s,
          |  (
          |    SELECT
          |      COUNT(Ref.id)
          |    FROM Ref
          |    WHERE
          |      Ref.entity = Entity.id
          |  )
          |FROM Entity
          |WHERE
          |  Entity.s IS NOT NULL;""".stripMargin
      ) ==> true)

      // .join() - FROM clause subquery (INNER JOIN)
      // Returns ONLY entities that have matching refs
      _ <- Entity.s.a1.join(Ref.id(count).entity_(Entity.id_)).query.get.map(_ ==> List(
        ("a", 2),
        ("b", 3),
        // ✗ "c" excluded - no matching refs (INNER JOIN semantics)
      ))

      // SQL for .join(): Subquery in FROM with INNER JOIN
      _ <- Entity.s.join(Ref.id(count).entity_(Entity.id_)).query.inspect.map(_.contains(
        """SELECT DISTINCT
          |  Entity.s,
          |  subquery1.Ref_id_count
          |FROM Entity INNER JOIN (
          |    SELECT
          |      COUNT(Ref.id) AS Ref_id_count,
          |      Ref.entity
          |    FROM Ref
          |    GROUP BY Ref.entity
          |  ) subquery1 ON Entity.id = subquery1.entity
          |WHERE
          |  Entity.s IS NOT NULL;""".stripMargin
      ) ==> true)
    } yield ()
  }


  "multiple aggregates" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(1, 2, 3)),
        ("b", List(10, 20)),
        ("c", List()),
      ).transact

      // .select() - multiple aggregates in single subquery
      _ <- Entity.s.a1.select(Ref.id(count).i(sum).entity_(Entity.id_)).query.get.map(_ ==> List(
          ("a", (3, 6)),   // count=3, sum=6
          ("b", (2, 30)),  // count=2, sum=30
          ("c", (0, 0)),   // ✓ All entities included
        ))
      // or use multiple independent subqueries (separate SELECT clauses)
      _ <- Entity.s.a1
        .select(Ref.id(count).entity_(Entity.id_))
        .select(Ref.i(sum).entity_(Entity.id_))
        .query.get.map(_ ==> List(
          ("a", 3, 6),   // count=3, sum=6
          ("b", 2, 30),  // count=2, sum=30
          ("c", 0, 0),   // ✓ All entities included
        ))

      // .join() - Pre-aggregated data (single FROM subquery with GROUP BY)
      _ <- Entity.s.a1.join(Ref.id(count).i(sum).entity_(Entity.id_)).query.get.map(_ ==> List(
          ("a", (3, 6)),
          ("b", (2, 30)),
          // ✗ "c" excluded (no refs to aggregate)
        ))
    } yield ()
  }


  "filtering with aggregates" - types {
    for {
      _ <- Entity.s.i.Refs.*(Ref.i).insert(
        ("a", 1, List(10, 20, 30)),
        ("b", 2, List(40, 50)),
        ("c", 3, List()),
      ).transact

      // .select() with outer filter - still includes all filtered entities
      _ <- Entity.s.i.<(4).select(Ref.id(count).entity_(Entity.id_)).query.get.map(_ ==> List(
          ("a", 1, 3),
          ("b", 2, 2),
          ("c", 3, 0), // ✓ Included (passed outer filter i < 4)
        ))

      // .join() with outer filter - excludes entities without refs
      _ <- Entity.s.i.<(4).join(Ref.id(count).entity_(Entity.id_)).query.get.map(_ ==> List(
          ("a", 1, 3),
          ("b", 2, 2),
          // ✗ "c" excluded (no refs, despite passing outer filter)
        ))

      // .select() with subquery filter - counts only matching refs
      _ <- Entity.s.a1.select(Ref.i_.>(20).id(count).entity_(Entity.id_)).query.get.map(_ ==> List(
          ("a", 1), // only i=30 matches filter
          ("b", 2), // i=40,50 match filter
          ("c", 0), // ✓ Included (no matching refs → count 0)
        ))

      // .join() with subquery filter - excludes entities without matching refs
      _ <- Entity.s.a1.join(Ref.i_.>(20).id(count).entity_(Entity.id_)).query.get.map(_ ==> List(
          ("a", 1),
          ("b", 2),
          // ✗ "c" excluded (no refs match filter)
        ))
    } yield ()
  }


  "different aggregate functions" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(5, 15, 25)),
        ("b", List(10, 20, 30, 40)),
        ("c", List()),
      ).transact

      // .select() - min/max/avg for all entities
      _ <- Entity.s.a1.select(Ref.i(min).i(max).i(avg).entity_(Entity.id_)).query.get.map(_ ==> List(
          ("a", (5, 25, 15)),
          ("b", (10, 40, 25)),
          ("c", (0, 0, 0)), // ✓ Default values for empty relationship
        ))

      // .join() - min/max/avg only for entities with refs
      _ <- Entity.s.a1
        .join(Ref.i(min).i(max).i(avg).entity_(Entity.id_))
        .query.get.map(_ ==> List(
          ("a", (5, 25, 15)),
          ("b", (10, 40, 25)),
          // ✗ "c" excluded (no refs to aggregate)
        ))
    } yield ()
  }


  "sorting by aggregate" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(1)),
        ("b", List(1, 2, 3)),
        ("c", List(1, 2)),
        ("d", List()),
      ).transact

      // .select() - sort by count (descending), includes 0
      _ <- Entity.s.select(Ref.id(count).d1.entity_(Entity.id_)).query.get.map(_ ==> List(
          ("b", 3),
          ("c", 2),
          ("a", 1),
          ("d", 0), // ✓ Included at end
        ))

      // .join() - sort by count (descending), excludes 0
      _ <- Entity.s.join(Ref.id(count).d1.entity_(Entity.id_)).query.get.map(_ ==> List(
          ("b", 3),
          ("c", 2),
          ("a", 1),
          // ✗ "d" excluded
        ))
    } yield ()
  }


  "when to use .select() vs .join()" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("active", List(1, 2, 3)),
        ("inactive", List()),
      ).transact

      // Use .select() when you need:
      // 1. All outer rows, including those without matches
      // 2. Default/zero values for empty relationships
      // 3. Multiple independent aggregations
      // 4. LEFT JOIN-like semantics

      _ <- Entity.s.a1.select(Ref.id(count).entity_(Entity.id_)).query.get.map { result =>
          result.size ==> 2 // Both entities included
          result ==> List(
            ("active", 3),
            ("inactive", 0), // Important for reports/dashboards
          )
        }

      // Use .join() when you need:
      // 1. Only rows that have matches
      // 2. INNER JOIN filtering semantics
      // 3. Pre-aggregated datasets for further processing
      // 4. Performance optimization (smaller result set)

      _ <- Entity.s.a1.join(Ref.id(count).entity_(Entity.id_)).query.get.map { result =>
          result.size ==> 1 // Only entities with refs
          result ==> List(
            ("active", 3),
            // "inactive" excluded - cleaner for some use cases
          )
        }
    } yield ()
  }


  "analytics dashboard" - types {
    for {
      _ <- Entity.s.i.Refs.*(Ref.i).insert(
        ("product-a", 100, List(5, 4, 5, 5)), // 4 reviews
        ("product-b", 200, List(3, 4)),       // 2 reviews
        ("product-c", 150, List()),           // 0 reviews (new product)
      ).transact

      // Dashboard: Show ALL products with review stats
      // Use .select() to include products with no reviews
      _ <- Entity.s.a1.i.select(Ref.id(count).i(avg).entity_(Entity.id_)).query.get.map(_ ==> List(
          ("product-a", 100, (4, 4.75)), // 4 reviews, avg 4.75
          ("product-b", 200, (2, 3.5)),  // 2 reviews, avg 3.5
          ("product-c", 150, (0, 0.0)),  // ✓ Show new product with 0 reviews
        ))

      // Report: Only products with reviews
      // Use .join() to filter out products without reviews
      _ <- Entity.s.a1.i.join(Ref.id(count).i(avg).entity_(Entity.id_)).query.get.map(_ ==> List(
          ("product-a", 100, (4, 4.75)),
          ("product-b", 200, (2, 3.5)),
          // ✗ product-c excluded - focus on reviewed products only
        ))
    } yield ()
  }
}
