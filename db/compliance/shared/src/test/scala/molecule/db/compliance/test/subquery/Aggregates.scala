package molecule.db.compliance.test.subquery

import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class Aggregates(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "count / countDistinct" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(1, 2)),
        ("b", List(3, 4, 4)),
        ("c", List()), // Entity with no refs
      ).transact

      // .select() - includes all entities
      _ <- Entity.s.a1.select(Ref.i(count).entity_(Entity.id_)).query.get.map(_ ==> List(
        ("a", 2),
        ("b", 3),
        ("c", 0), // Included with .select
      ))
      _ <- Entity.s.a1.select(Ref.i(countDistinct).entity_(Entity.id_)).query.get.map(_ ==> List(
        ("a", 2),
        ("b", 2), // only 2 distinct values (3, 4)
        ("c", 0), // Included with .select
      ))

      // .join() - only entities with refs
      _ <- Entity.s.a1.join(Ref.i(count).entity_(Entity.id_)).query.get.map(_ ==> List(
        ("a", 2),
        ("b", 3),
        // "c" excluded - no refs
      ))
      _ <- Entity.s.a1.join(Ref.i(countDistinct).entity_(Entity.id_)).query.get.map(_ ==> List(
        ("a", 2),
        ("b", 2), // only 2 distinct values (3, 4)
        // "c" excluded - no refs
      ))

      // SQL inspection for .select(): correlated subquery in SELECT clause
      _ <- Entity.s.select(Ref.i(count).entity_(Entity.id_)).query.inspect.map(_.contains(
        """SELECT DISTINCT
          |  Entity.s,
          |  (
          |    SELECT
          |      COUNT(Ref.i)
          |    FROM Ref
          |    WHERE
          |      Ref.entity = Entity.id
          |  )""".stripMargin
      ) ==> true)

      // Distinct added
      _ <- Entity.s.select(Ref.i(countDistinct).entity_(Entity.id_)).query.inspect.map(_.contains(
        """SELECT DISTINCT
          |  Entity.s,
          |  (
          |    SELECT
          |      COUNT(DISTINCT Ref.i)
          |    FROM Ref
          |    WHERE
          |      Ref.entity = Entity.id
          |  )""".stripMargin
      ) ==> true)

      // SQL inspection for .join(): INNER JOIN with GROUP BY
      _ <- Entity.s.join(Ref.i(count).entity_(Entity.id_)).query.inspect.map(_.contains(
        """INNER JOIN (
          |    SELECT
          |      COUNT(Ref.i) AS Ref_i_count,
          |      Ref.entity
          |    FROM Ref
          |    GROUP BY Ref.entity
          |  ) subquery1 ON Entity.id = subquery1.entity""".stripMargin
      ) ==> true)

      // Distinct added
      _ <- Entity.s.join(Ref.i(countDistinct).entity_(Entity.id_)).query.inspect.map(_.contains(
        """INNER JOIN (
          |    SELECT
          |      COUNT(DISTINCT Ref.i) AS Ref_i_countDistinct,
          |      Ref.entity
          |    FROM Ref
          |    GROUP BY Ref.entity
          |  ) subquery1 ON Entity.id = subquery1.entity""".stripMargin
      ) ==> true)
    } yield ()
  }


  "min / max" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(10, 20, 30)),
        ("b", List(5, 15, 25, 35)),
        ("c", List()),
      ).transact

      // .select() - separate subqueries for min and max
      _ <- Entity.s.a1.select(Ref.i(min).i(max).entity_(Entity.id_)).query.i.get.map(_ ==> List(
        ("a", (10, 30)),
        ("b", (5, 35)),
        ("c", (0, 0)), // Default values
      ))

      // We could also use multiple .select() calls (inefficient here though for the same table)
      _ <- Entity.s.a1
        .select(Ref.i(min).entity_(Entity.id_))
        .select(Ref.i(max).entity_(Entity.id_))
        .query.get.map(_ ==> List(
          ("a", 10, 30),
          ("b", 5, 35),
          ("c", 0, 0), // Default values
        ))

      // .join() - single subquery returning tuple
      _ <- Entity.s.a1.join(Ref.i(min).i(max).entity_(Entity.id_)).query.get.map(_ ==> List(
        ("a", (10, 30)),
        ("b", (5, 35)),
        // "c" excluded
      ))
    } yield ()
  }


  "sum / avg" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(1, 2, 3)),
        ("b", List(10, 20, 30, 40)),
        ("c", List()),
      ).transact

      // .select()
      _ <- Entity.s.a1.select(Ref.i(sum).i(avg).entity_(Entity.id_)).query.get.map(_ ==> List(
        ("a", (6, 2)),
        ("b", (100, 25)),
        ("c", (0, 0)), // Default values
      ))

      // .join()
      _ <- Entity.s.a1.join(Ref.i(sum).i(avg).entity_(Entity.id_)).query.get.map(_ ==> List(
        ("a", (6, 2)),
        ("b", (100, 25)),
        // "c" excluded
      ))
    } yield ()
  }


  "median" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(1, 2, 3, 4, 5)),
        ("b", List(10, 20)),
        ("c", List()),
      ).transact

      // .select()
      _ <- if (database == "sqlite")
        Entity.s.a1.select(Ref.i(median).entity_(Entity.id_)).query.i.get
          .map(_ ==> "Should fail").recover { case ModelError(err) =>
            err ==> "Median, variance and stddev in .select() subqueries not supported for SQLite."
          }
      else
        Entity.s.a1.select(Ref.i(median).entity_(Entity.id_)).query.get.map(_ ==> List(
          ("a", 3),
          ("b", 15),
          ("c", 0), // Default
        ))

      // .join()
      _ <- Entity.s.a1.join(Ref.i(median).entity_(Entity.id_)).query.get.map(_ ==> List(
        ("a", 3),
        ("b", 15),
        // "c" excluded
      ))
    } yield ()
  }


  "variance / stddev" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(10, 20, 30)),
        ("b", List(5, 5, 5)), // No variance
        ("c", List()),
      ).transact

      // .select()
      _ <- if (database == "sqlite")
        Entity.s.a1.select(Ref.i(variance).i(stddev).entity_(Entity.id_)).query.i.get
          .map(_ ==> "Should fail").recover { case ModelError(err) =>
            err ==> "Median, variance and stddev in .select() subqueries not supported for SQLite."
          }
      else
        Entity.s.a1.select(Ref.i(variance).i(stddev).entity_(Entity.id_)).query.get.map { result =>
          result.size ==> 3
          val (s1, (var1, std1)) = result(0)
          val (s2, (var2, std2)) = result(1)
          val (s3, (var3, std3)) = result(2)
          s1 ==> "a"
          assert(var1 > 0 && std1 > 0)
          s2 ==> "b"
          var2 ==> 0.0
          std2 ==> 0.0
          s3 ==> "c"
          var3 ==> 0.0
          std3 ==> 0.0
        }

      // .join()
      _ <- Entity.s.a1.join(Ref.i(variance).i(stddev).entity_(Entity.id_)).query.get.map { result =>
        result.size ==> 2 // "c" excluded
        val (s1, (var1, std1)) = result(0)
        val (s2, (var2, std2)) = result(1)
        s1 ==> "a"
        assert(var1 > 0 && std1 > 0)
        s2 ==> "b"
        var2 ==> 0.0
        std2 ==> 0.0
      }
    } yield ()
  }


  "separate vs combined" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(1, 2, 3)),
        ("b", List(10, 20)),
        ("c", List()),
      ).transact

      // Separate .select() calls create independent subqueries
      _ <- Entity.s.a1
        .select(Ref.id(count).entity_(Entity.id_))
        .select(Ref.i(sum).entity_(Entity.id_))
        .select(Ref.i(avg).entity_(Entity.id_))
        .query.get.map(_ ==> List(
          ("a", 3, 6, 2),
          ("b", 2, 30, 15),
          ("c", 0, 0, 0), // All default
        ))

      // Single .select() with multiple aggregates - one subquery, returns tuple
      _ <- Entity.s.a1.select(Ref.id(count).i(sum).i(avg).entity_(Entity.id_)).query.get.map(_ ==> List(
        ("a", (3, 6, 2)),
        ("b", (2, 30, 15)),
        ("c", (0, 0, 0)), // Tuple of defaults
      ))

      // .join() always returns tuple for multiple aggregates
      _ <- Entity.s.a1.join(Ref.id(count).i(sum).i(avg).entity_(Entity.id_)).query.get.map(_ ==> List(
        ("a", (3, 6, 2)),
        ("b", (2, 30, 15)),
        // "c" excluded
      ))
    } yield ()
  }


  "with optional attributes" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i.s_?).insert(
        ("a", List((1, Some("x")), (2, None), (3, Some("y")))),
        ("b", List((4, Some("z")), (5, Some("w")))),
        ("c", List()),
      ).transact

      // Count all refs (.select)
      _ <- Entity.s.a1.select(Ref.id(count).entity_(Entity.id_)).query.get.map(_ ==> List(
        ("a", 3),
        ("b", 2),
        ("c", 0),
      ))

      // Count only refs with optional value present (.select)
      _ <- Entity.s.a1.select(Ref.s_.id(count).entity_(Entity.id_)).query.get.map(_ ==> List(
        ("a", 2), // Only 2 refs have s
        ("b", 2),
        ("c", 0),
      ))

      // Count all refs (.join)
      _ <- Entity.s.a1.join(Ref.id(count).entity_(Entity.id_)).query.get.map(_ ==> List(
        ("a", 3),
        ("b", 2),
        // "c" excluded
      ))

      // Count only refs with optional value present (.join)
      _ <- Entity.s.a1.join(Ref.s_.id(count).entity_(Entity.id_)).query.get.map(_ ==> List(
        ("a", 2),
        ("b", 2),
        // "c" excluded
      ))
    } yield ()
  }


  "compute in Scala" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(1, 2, 3)),
        ("b", List(10, 20)),
        ("c", List()),
      ).transact

      // Get sum and count, compute average in Scala (.select)
      _ <- Entity.s.a1.select(Ref.i(sum).i(count).entity_(Entity.id_)).query.get.map { results =>
        results.map { case (s, (sum, count)) =>
          val avg = if (count > 0) sum / count else 0
          (s, sum, count, avg)
        } ==> List(
          ("a", 6, 3, 2),
          ("b", 30, 2, 15),
          ("c", 0, 0, 0),
        )
      }

      // Same with .join() - no entities with zero refs
      _ <- Entity.s.a1.join(Ref.i(sum).i(count).entity_(Entity.id_)).query.get.map { results =>
        results.map { case (s, (sum, count)) =>
          val avg = if (count > 0) sum / count else 0
          (s, sum, count, avg)
        } ==> List(
          ("a", 6, 3, 2),
          ("b", 30, 2, 15),
          // "c" excluded
        )
      }
    } yield ()
  }


  "tacit outer attribute" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(1, 2)),
        ("b", List(3, 4, 5)),
        ("c", List()),
      ).transact

      // .select() - includes 0 from entity "c"
      _ <- Entity.s_.select(Ref.id(count).entity_(Entity.id_)).query.get.map(_.sorted ==> List(0, 2, 3))

      // .join() - excludes entities without refs
      _ <- Entity.s_.join(Ref.id(count).entity_(Entity.id_)).query.get.map(_.sorted ==> List(2, 3))
    } yield ()
  }


  "product reviews" - types {
    for {
      // Product name, price, review scores
      _ <- Entity.s.i.Refs.*(Ref.i).insert(
        ("Widget-A", 100, List(5, 4, 5, 5)), // 4 reviews
        ("Widget-B", 200, List(3, 4)), // 2 reviews
        ("Widget-C", 150, List()), // New product, no reviews
      ).transact

      // Dashboard: Show ALL products with review statistics (.select)
      _ <- Entity.s.a1.i.select(Ref
        .id(count) // review count
        .i(avg) // average rating
        .entity_(Entity.id_)).query.get.map(_ ==> List(
        ("Widget-A", 100, (4, 4.75)),
        ("Widget-B", 200, (2, 3.5)),
        ("Widget-C", 150, (0, 0.0)), // Show new product with 0 reviews
      ))

      // Report: Only products with reviews (.join)
      _ <- Entity.s.a1.i.join(Ref.id(count).i(avg).entity_(Entity.id_)).query.get.map(_ ==> List(
        ("Widget-A", 100, (4, 4.75)),
        ("Widget-B", 200, (2, 3.5)),
        // Widget-C excluded - focus on reviewed products only
      ))
    } yield ()
  }

  "Empty subquery results" - types {
    for {
      _ <- Entity.i.insert(1, 2, 3).transact
      // No Ref entities at all!

      _ <- Entity.i.a1.select(Ref.i(count)).query.get.map(_ ==> List(
        (1, 0),
        (2, 0),
        (3, 0),
      ))
      _ <- Entity.i.a1.select(Ref.i(avg)).query.get.map(_ ==> List(
        (1, 0),
        (2, 0),
        (3, 0),
      ))

      // .join() with global aggregate on empty table
      _ <- Entity.i.a1.join(Ref.i(count)).query.get.map(_ ==> List(
        (1, 0),
        (2, 0),
        (3, 0),
      ))

      _ <- Entity.i.a1.join(Ref.i(avg)).query.get.map(_ ==> List(
        (1, 0),
        (2, 0),
        (3, 0),
      ))

      // Comparison with aggregate from empty table
      _ <- Entity.i(Ref.i(count)).query.get.map(_ ==> List())
    } yield ()
  }

  "Optional values" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i_?).insert(
        ("a", List(Some(1), None, Some(3))),
        ("b", List(None, None)),
        ("c", List()),
      ).transact

      // COUNT should ignore NULLs
      _ <- Entity.s.a1.select(Ref.i(count).entity_(Entity.id_)).query.get.map(_ ==> List(
        ("a", 2), // Only counts non-NULL values
        ("b", 0), // All NULL = count 0
        ("c", 0), // No refs = count 0
      ))

      // SUM should ignore NULLs
      _ <- Entity.s.a1.select(Ref.i(sum).entity_(Entity.id_)).query.get.map(_ ==> List(
        ("a", 4), // 1 + 3, NULLs ignored
        ("b", 0), // All NULL = 0?
        ("c", 0),
      ))
    } yield ()
  }

  "empty set aggregates" - types {
    for {
      _ <- Entity.s.insert("a", "b", "c").transact
      // No Refs at all

      // count/sum returning 0 is semantically correct
      _ <- Entity.s.a1.select(Ref.i(count).entity_(Entity.id_)).query.get.map(_ ==> List(
        ("a", 0), // Counted 0 items
        ("b", 0),
        ("c", 0),
      ))
      _ <- Entity.s.a1.select(Ref.i(sum).entity_(Entity.id_)).query.get.map(_ ==> List(
        ("a", 0), // Sum of nothing is 0
        ("b", 0),
        ("c", 0),
      ))

      // min/max/avg returning 0 is a default (not semantically "correct" but pragmatic)
      _ <- Entity.s.a1.select(Ref.i(min).entity_(Entity.id_)).query.get.map(_ ==> List(
        ("a", 0), // Default: no minimum exists
        ("b", 0),
        ("c", 0),
      ))
      _ <- Entity.s.a1.select(Ref.i(max).entity_(Entity.id_)).query.get.map(_ ==> List(
        ("a", 0), // Default: no maximum exists
        ("b", 0),
        ("c", 0),
      ))
      // etc. for other aggregate functions (countDistinct, median, stddev, variance)

      // Note: .join() would exclude these rows entirely
      _ <- Entity.s.a1.join(Ref.i(count).entity_(Entity.id_)).query.get.map(_ ==> List())
    } yield ()
  }
}
