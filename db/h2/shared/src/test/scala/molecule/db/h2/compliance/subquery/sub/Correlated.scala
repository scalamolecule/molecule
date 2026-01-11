package molecule.db.h2.compliance.subquery.sub

import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.h2.async.*
import molecule.db.h2.setup.DbProviders_h2

class Correlated extends MUnit with DbProviders_h2 with TestUtils {

  // SELECT clause subqueries are used when we need correlated scalar aggregations
  // that preserve all outer rows, including those with empty relationships.
  // This is different from JOIN+GROUP BY which would either:
  // 1. Omit rows with no matches (INNER JOIN)
  // 2. Require complex NULL handling (LEFT JOIN + COALESCE)


  "Basic correlated count - preserves all rows" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(1, 2)),
        ("b", List(3, 4, 5)),
        ("c", List()), // Entity with no refs
      ).transact

      // Subquery returns count for each entity, including 0 for empty relationships
      _ <- Entity.s.sub(Ref.id(count).entity_(Entity.id_)).query.get.map(_ ==> List(
        ("a", 2),
        ("b", 3),
        ("c", 0), // Important: entity "c" is included with count 0
      ))

      // SQL inspection: verify SELECT subquery is generated
      _ <- Entity.s.sub(Ref.id(count).entity_(Entity.id_)).query.inspect.map(_.contains(
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

      // Compare with JOIN approach (would miss entity "c" without LEFT JOIN)
      _ <- Ref.id(count).Entity.s.query.i.get.map(_ ==> List(
        (2, "a"),
        (3, "b"),
        // "c" is missing! This is why we need SELECT subquery
      ))

      // Tacit outer attribute - return only subquery results
      _ <- Entity.s_.sub(Ref.id(count).entity_(Entity.id_))
        .query.get.map(_ ==> List(0, 2, 3))
    } yield ()
  }


  "Multiple independent correlated subqueries - avoids cartesian product" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(1, 2)),
        ("b", List(3, 4, 5)),
        ("c", List()),
      ).transact

      // Approach 1: Two separate .sub() calls - creates two independent subqueries
      _ <- Entity.s
        .sub(Ref.id(count).entity_(Entity.id_))
        .sub(Ref.i(sum).entity_(Entity.id_))
        .query.get.map(_ ==> List(
          ("a", 2, 3),
          ("b", 3, 12),
          ("c", 0, 0), // Both subqueries return 0 for empty relationship
        ))

      // SQL inspection: two separate SELECT subqueries
      _ <- Entity.s
        .sub(Ref.id(count).entity_(Entity.id_))
        .sub(Ref.i(sum).entity_(Entity.id_))
        .query.inspect.map(_.contains(
          """SELECT DISTINCT
            |  Entity.s,
            |  (
            |    SELECT
            |      COUNT(Ref.id)
            |    FROM Ref
            |    WHERE
            |      Ref.entity = Entity.id
            |  ),
            |  (
            |    SELECT DISTINCT
            |      SUM(Ref.i)
            |    FROM Ref
            |    WHERE
            |      Ref.entity = Entity.id
            |  )
            |FROM Entity
            |WHERE
            |  Entity.s IS NOT NULL;""".stripMargin
        ) ==> true)

      // Approach 2: Multiple aggregations in one .sub() call - single subquery
      // Returns tuple of aggregations instead of flat values
      _ <- Entity.s
        .sub(Ref.id(count).i(sum).entity_(Entity.id_))
        .query.get.map(_ ==> List(
          ("a", (2, 3)),      // Note: tuple for subquery results
          ("b", (3, 12)),
          ("c", (0, 0)),
        ))

      // SQL inspection: one SELECT subquery with multiple aggregations
      _ <- Entity.s
        .sub(Ref.id(count).i(sum).entity_(Entity.id_))
        .query.inspect.map(_.contains(
          """SELECT DISTINCT
            |  Entity.s,
            |  (
            |    SELECT
            |      COUNT(Ref.id),
            |      SUM(Ref.i)
            |    FROM Ref
            |    WHERE
            |      Ref.entity = Entity.id
            |  )
            |FROM Entity
            |WHERE
            |  Entity.s IS NOT NULL;""".stripMargin
        ) ==> true)
    } yield ()
  }


  "Filtered correlated aggregation - counts only matching refs" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(1, 2, 3)),
        ("b", List(2, 3, 4, 5)),
        ("c", List(5, 6)),
        ("d", List(1)), // Only low values
      ).transact

      // Count only refs where i > 2
      _ <- Entity.s.sub(
        Ref.i_.>(2).id(count).entity_(Entity.id_)
      ).query.get.map(_ ==> List(
        ("a", 1), // only i=3
        ("b", 3), // i=3,4,5
        ("c", 2), // i=5,6
        ("d", 0), // no refs match filter
      ))

      // SQL inspection: verify filtered subquery
      _ <- Entity.s.sub(
        Ref.i_.>(2).id(count).entity_(Entity.id_)
      ).query.inspect.map(_.contains(
        """SELECT DISTINCT
          |  Entity.s,
          |  (
          |    SELECT
          |      COUNT(Ref.id)
          |    FROM Ref
          |    WHERE
          |      Ref.i      > 2 AND
          |      Ref.entity = Entity.id
          |  )
          |FROM Entity
          |WHERE
          |  Entity.s IS NOT NULL;""".stripMargin
      ) ==> true)

      // Compare different filter threshold
      _ <- Entity.s.sub(
        Ref.i_.<(5).i(max).entity_(Entity.id_)
      ).query.get.map(_ ==> List(
        ("a", 3),
        ("b", 4),
        ("c", 0), // no refs match i < 5
        ("d", 1),
      ))
    } yield ()
  }


  "Outer query filter with correlated subquery" - types {
    for {
      _ <- Entity.s.i.Refs.*(Ref.i).insert(
        ("a", 1, List(10, 20)),
        ("b", 2, List(30, 40, 50)),
        ("c", 3, List()),
        ("d", 4, List(60)),
      ).transact

      // Filter entities where i < 4, still get correlated count for each
      _ <- Entity.s.i.<(4).sub(
        Ref.id(count).entity_(Entity.id_)
      ).query.get.map(_ ==> List(
        ("a", 1, 2),
        ("b", 2, 3),
        ("c", 3, 0),
        // "d" filtered out by i < 4
      ))

      // Multiple outer attributes with subquery
      _ <- Entity.s.i.sub(
        Ref.id(count).i(sum).entity_(Entity.id_)
      ).query.get.map(_ ==> List(
        ("a", 1, (2, 30)),
        ("b", 2, (3, 120)),
        ("c", 3, (0, 0)),
        ("d", 4, (1, 60)),
      ))
    } yield ()
  }


  "Different aggregation functions" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(10, 20, 30)),
        ("b", List(5, 15, 25, 35)),
        ("c", List()),
      ).transact

      // Approach 1: Separate .sub() calls for min, max, avg
      _ <- Entity.s
        .sub(Ref.i(min).entity_(Entity.id_))
        .sub(Ref.i(max).entity_(Entity.id_))
        .sub(Ref.i(avg).entity_(Entity.id_))
        .query.get.map(_ ==> List(
          ("a", 10, 30, 20),
          ("b", 5, 35, 20),
          ("c", 0, 0, 0), // Default values for empty relationship
        ))

      // Approach 2: Single .sub() call with multiple aggregations
      _ <- Entity.s
        .sub(Ref.i(min).i(max).i(avg).entity_(Entity.id_))
        .query.get.map(_ ==> List(
          ("a", (10, 30, 20)),      // Note: tuple for subquery results
          ("b", (5, 35, 20)),
          ("c", (0, 0, 0)),
        ))

      // variance and stddev (values vary by implementation, check they exist)
      _ <- Entity.s.a1
        .sub(Ref.i(variance).entity_(Entity.id_))
        .sub(Ref.i(stddev).entity_(Entity.id_))
        .query.get.map { result =>
          result.size ==> 3
          val (s1, var1, std1) = result.head
          val (s2, var2, std2) = result(1)
          val (s3, var3, std3) = result.last
          s1 ==> "a"
          assert(var1 > 0 && std1 > 0)
          s2 ==> "b"
          assert(var2 > 0 && std2 > 0)
          s3 ==> "c"
          var3 ==> 0.0
          std3 ==> 0.0
        }

      // median
      _ <- Entity.s.sub(
        Ref.i(median).entity_(Entity.id_)
      ).query.get.map(_ ==> List(
        ("a", 20),
        ("b", 20),
        ("c", 0),
      ))
    } yield ()
  }


  "Sorting by subquery result" - types {
    for {
      _ <- Entity.s.i.Refs.*(Ref.i).insert(
        ("c", 3, List(1)),
        ("a", 1, List(1, 2, 3)),
        ("b", 2, List(1, 2)),
      ).transact

      // Sort by subquery count (descending)
      _ <- Entity.s.i.sub(
        Ref.id(count).d1.entity_(Entity.id_)
      ).query.get.map(_ ==> List(
        ("a", 1, 3),
        ("b", 2, 2),
        ("c", 3, 1),
      ))

      // Sort by main attribute
      _ <- Entity.s.i.a1.sub(
        Ref.id(count).entity_(Entity.id_)
      ).query.get.map(_ ==> List(
        ("a", 1, 3),
        ("b", 2, 2),
        ("c", 3, 1),
      ))
    } yield ()
  }


  "Having-like filtering on subquery results" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(1, 2, 3)),
        ("b", List(4, 5)),
        ("c", List(6)),
        ("d", List()),
      ).transact

      // Filter where count > 1
      _ <- Entity.s.a1.sub(
        Ref.id(count).>(1).entity_(Entity.id_)
      ).query.get.map(_ ==> List(
        ("a", 3),
        ("b", 2),
        ("c", 0), // Included because subquery is in SELECT, not WHERE
        ("d", 0),
      ))

      // Filter where sum > 5
      _ <- Entity.s.a1.sub(
        Ref.i(sum).>(5).entity_(Entity.id_)
      ).query.get.map(_ ==> List(
        ("a", 6), // 1+2+3
        ("b", 9), // 4+5
        ("c", 6), // 6
        ("d", 0), // 0 is not > 5, but row is still included
      ))
    } yield ()
  }


  "Optional attributes in subquery" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i.s_?).insert(
        ("a", List((1, Some("x")), (2, None))),
        ("b", List((3, Some("y")), (4, Some("z")))),
        ("c", List()),
      ).transact

      // Count all refs
      _ <- Entity.s.sub(
        Ref.id(count).entity_(Entity.id_)
      ).query.get.map(_ ==> List(
        ("a", 2),
        ("b", 2),
        ("c", 0),
      ))

      // Count only refs with optional value present
      _ <- Entity.s.sub(
        Ref.s_.id(count).entity_(Entity.id_)
      ).query.get.map(_ ==> List(
        ("a", 1), // only first ref has s
        ("b", 2), // both refs have s
        ("c", 0),
      ))
    } yield ()
  }


  "Nested relationship in subquery" - refs {
    import molecule.db.compliance.domains.dsl.Refs.*
    for {
      _ <- A.s.Bb.*(B.i.C.i).insert(
        ("a", List((1, 10), (2, 20))),
        ("b", List((3, 30), (4, 40), (5, 50))),
        ("c", List()),
      ).transact

      // Subquery traversing nested relationship
      _ <- A.s.sub(
        B.id(count).a_(A.id_).C.i(sum)
      ).query.get.map(_ ==> List(
        ("a", (2, 30)), // 10+20
        ("b", (3, 120)), // 30+40+50
        ("c", (0, 0)),
      ))

      // SQL inspection: verify nested relationship in subquery
      _ <- A.s.sub(
        B.id(count).a_(A.id_).C.i(sum)
      ).query.inspect.map(_.contains(
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
          |  )
          |FROM A
          |WHERE
          |  A.s IS NOT NULL;""".stripMargin
      ) ==> true)
    } yield ()
  }


  "Expression-like computation on subquery results" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(1, 2, 3)),
        ("b", List(10, 20)),
        ("c", List()),
      ).transact

      // Get sum and count, compute average in Scala
      _ <- Entity.s.sub(
        Ref.i(sum).i(count).entity_(Entity.id_)
      ).query.get.map { results =>
        results.map { case (s, (sum, count)) =>
          val avg = if (count > 0) sum / count else 0
          (s, sum, count, avg)
        } ==> List(
          ("a", 6, 3, 2),
          ("b", 30, 2, 15),
          ("c", 0, 0, 0),
        )
      }
    } yield ()
  }
}
