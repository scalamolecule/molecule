package molecule.db.h2.compliance.subquery

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.h2.async.*
import molecule.db.h2.setup.DbProviders_h2

class ComparisonOperators extends MUnit with DbProviders_h2 with TestUtils {

  // Tests for comparing attributes with subquery aggregate results
  // Uses implicit/global subqueries (CROSS JOIN) that return a single value


  "All comparison operators - mandatory 1 (Molecule_1)" - types {
    for {
      _ <- Entity.i.Ref.i.insert(
        (2, 1),
        (3, 1),
        (4, 1),
      ).transact

      // Test all operators with count aggregate
      _ <- Entity.i(Ref.i(count)).query.get.map(_ ==> List(
        (3, 3),
      ))
      _ <- Entity.i.not(Ref.i(count)).query.get.map(_ ==> List(
        (2, 3),
        (4, 3),
      ))
      _ <- Entity.i.<(Ref.i(count)).query.get.map(_ ==> List(
        (2, 3),
      ))
      _ <- Entity.i.<=(Ref.i(count)).query.get.map(_ ==> List(
        (2, 3),
        (3, 3),
      ))
      _ <- Entity.i.>(Ref.i(count)).query.get.map(_ ==> List(
        (4, 3),
      ))
      _ <- Entity.i.>=(Ref.i(count)).query.get.map(_ ==> List(
        (3, 3),
        (4, 3),
      ))
    } yield ()
  }


  "All comparison operators - mandatory n (Molecule_n)" - types {
    for {
      _ <- Entity.s.i.Ref.i.insert(
        ("a", 2, 1),
        ("b", 3, 1),
        ("c", 4, 1),
      ).transact

      // Test all operators with count aggregate
      _ <- Entity.s.i(Ref.i(count)).query.get.map(_ ==> List(
        ("b", 3, 3),
      ))
      _ <- Entity.s.i.not(Ref.i(count)).query.get.map(_ ==> List(
        ("a", 2, 3),
        ("c", 4, 3),
      ))
      _ <- Entity.s.i.<(Ref.i(count)).query.get.map(_ ==> List(
        ("a", 2, 3),
      ))
      _ <- Entity.s.i.<=(Ref.i(count)).query.get.map(_ ==> List(
        ("a", 2, 3),
        ("b", 3, 3),
      ))
      _ <- Entity.s.i.>(Ref.i(count)).query.get.map(_ ==> List(
        ("c", 4, 3),
      ))
      _ <- Entity.s.i.>=(Ref.i(count)).query.get.map(_ ==> List(
        ("b", 3, 3),
        ("c", 4, 3),
      ))
    } yield ()
  }


  "All comparison operators - tacit" - types {
    for {
      _ <- Entity.s.i.Ref.i.insert(
        ("a", 2, 1),
        ("b", 3, 1),
        ("c", 4, 1),
      ).transact

      // Test all operators with tacit attribute
      _ <- Entity.s.i_(Ref.i(count)).query.get.map(_ ==> List(
        ("b", 3),
      ))
      _ <- Entity.s.i_.not(Ref.i(count)).query.get.map(_ ==> List(
        ("a", 3),
        ("c", 3),
      ))
      _ <- Entity.s.i_.<(Ref.i(count)).query.get.map(_ ==> List(
        ("a", 3),
      ))
      _ <- Entity.s.i_.<=(Ref.i(count)).query.get.map(_ ==> List(
        ("a", 3),
        ("b", 3),
      ))
      _ <- Entity.s.i_.>(Ref.i(count)).query.get.map(_ ==> List(
        ("c", 3),
      ))
      _ <- Entity.s.i_.>=(Ref.i(count)).query.get.map(_ ==> List(
        ("b", 3),
        ("c", 3),
      ))
    } yield ()
  }


  "ALL - greater than all values (max)" - types {
    for {
      _ <- Entity.i.insert(1, 2, 3).transact
      _ <- Ref.i.insert(1, 2).transact

      // Find entities whose i is greater than ALL ref values
      // SQL: Entity.i > MAX(Ref.i)
      _ <- Entity.i.>(Ref.i(max)).query.get.map(_ ==> List(
        (3, 2), // 3 > max(2)
      ))

      // SQL inspection: compare to MAX subquery
      _ <- Entity.i.>(Ref.i(max)).query.inspect.map(_.contains(
        """SELECT DISTINCT
          |  Entity.i,
          |  subquery1.col
          |FROM Entity
          |  CROSS JOIN (
          |    SELECT DISTINCT
          |      MAX(Ref.i) AS col
          |    FROM Ref
          |  ) subquery1
          |WHERE
          |  Entity.i > subquery1.col;""".stripMargin
      ) ==> true)
    } yield ()
  }


  "ALL - less than all values (min)" - types {
    for {
      _ <- Entity.i.insert(1, 2, 3).transact
      _ <- Ref.i.insert(2, 3).transact

      // Find entities whose i is less than ALL ref values
      // SQL: Entity.i < MIN(Ref.i)
      _ <- Entity.i.<(Ref.i(min)).query.get.map(_ ==> List(
        (1, 2), // 1 < min(2)
      ))
    } yield ()
  }


  "ANY - equals any value (IN semantics)" - types {
    for {
      _ <- Entity.s.i.insert(
        ("a", 1),
        ("b", 2),
        ("c", 3),
      ).transact
      _ <- Ref.i.insert(1, 3).transact

      // Find entities whose i equals ANY ref value
      // SQL: Entity.i IN (SELECT Ref.i FROM Ref)
      _ <- Entity.s.i(Ref.i).query.get.map(_ ==> List(
        ("a", 1, 1),
        ("c", 3, 3),
      ))

      // Tacit form
      _ <- Entity.s.i_(Ref.i).query.get.map(_ ==> List(
        ("a", 1),
        ("c", 3),
      ))
    } yield ()
  }


  "not-equals - cross join with inequality" - types {
    for {
      _ <- Entity.s.i.insert(
        ("a", 1),
        ("b", 2),
        ("c", 3),
      ).transact
      _ <- Ref.i.insert(1, 2).transact

      // Cross join semantics: each Entity.i paired with each Ref.i where they're not equal
      // Returns all combinations where Entity.i ≠ Ref.i
      _ <- Entity.s.i.not(Ref.i).query.get.map(_ ==> List(
        ("a", 1, 2), // 1 ≠ 2
        ("b", 2, 1), // 2 ≠ 1
        ("c", 3, 1), // 3 ≠ 1
        ("c", 3, 2), // 3 ≠ 2
      ))

      // SQL inspection: CROSS JOIN with <> comparison
      _ <- Entity.s.i.not(Ref.i).query.inspect.map(_.contains(
        """SELECT DISTINCT
          |  Entity.s,
          |  Entity.i,
          |  subquery1.col
          |FROM Entity
          |  CROSS JOIN (
          |    SELECT DISTINCT
          |      Ref.i AS col
          |    FROM Ref
          |    WHERE
          |      Ref.i IS NOT NULL
          |  ) subquery1
          |WHERE
          |  Entity.s IS NOT NULL AND
          |  Entity.i <> subquery1.col;""".stripMargin
      ) ==> true)
    } yield ()
  }


  "count vs countDistinct" - types {
    for {
      _ <- Entity.i.Ref.i.insert(
        (1, 1),
        (2, 1),
        (3, 1),
      ).transact

      _ <- Entity.i(Ref.i(count)).query.get.map(_ ==> List(
        (3, 3), // 3 = count(3)
      ))

      _ <- Ref.i(countDistinct).query.get.map(_ ==> List(1))
      _ <- Entity.i(Ref.i(countDistinct)).query.get.map(_ ==> List(
        (1, 1), // 1 = countDistinct(1)
      ))
    } yield ()
  }


  "min" - types {
    for {
      _ <- Entity.i.Ref.i.insert(
        (1, 1),
        (2, 2),
        (3, 3),
      ).transact

      _ <- Entity.i(Ref.i(min)).query.get.map(_ ==> List(
        (1, 1), // 1 = min(1)
      ))
    } yield ()
  }


  "max" - types {
    for {
      _ <- Entity.i.Ref.i.insert(
        (1, 1),
        (2, 2),
        (3, 3),
      ).transact

      _ <- Entity.i(Ref.i(max)).query.get.map(_ ==> List(
        (3, 3), // 3 = max(3)
      ))
    } yield ()
  }


  "sum" - types {
    for {
      _ <- Entity.i.Ref.i.insert(
        (2, 1),
        (4, 2),
        (6, 3),
      ).transact

      _ <- Entity.i(Ref.i(sum)).query.get.map(_ ==> List(
        (6, 6), // 6 = sum(1+2+3)
      ))
    } yield ()
  }


  "avg" - types {
    for {
      _ <- Entity.double.Ref.i.insert(
        (1.0, 1),
        (2.0, 2),
        (3.0, 3),
      ).transact

      _ <- Entity.double(Ref.i(avg)).query.get.map(_ ==> List(
        (2, 2), // 2.0 = avg(1,2,3)
      ))
    } yield ()
  }


  "median" - types {
    for {
      _ <- Entity.double.Ref.i.insert(
        (1.0, 1),
        (2.0, 2),
        (3.0, 3),
      ).transact

      _ <- Entity.double(Ref.i(median)).query.get.map(_ ==> List(
        (2, 2), // 2.0 = median(1,2,3)
      ))
    } yield ()
  }


  "variance" - types {
    for {
      _ <- Entity.double.Ref.i.insert(
        (0.5, 1),
        (0.7, 2),
        (0.9, 3),
      ).transact

      _ <- Ref.i(variance).query.get.map(_ ==> List(2.0/3))
      _ <- Entity.double.<(Ref.i(variance)).query.get.map(_ ==> List(
        (0.5, 2.0/3), // 0.5 < variance
      ))
    } yield ()
  }


  "stddev" - types {
    for {
      _ <- Entity.double.Ref.i.insert(
        (0.5, 1),
        (0.7, 2),
        (0.9, 3),
      ).transact

      _ <- Ref.i(stddev).query.get.map(_ ==> List(0.816496580927726))
      _ <- Entity.double.>(Ref.i(stddev)).query.get.map(_ ==> List(
        (0.9, 0.816496580927726), // 0.9 > stddev
      ))
    } yield ()
  }


  "Multiple aggregates" - types {
    for {
      _ <- Entity.s.i.Ref.i.insert(
        ("a", 1, 1),
        ("b", 2, 2),
        ("c", 3, 3),
      ).transact

      // Entity.i is between min and max of ALL Ref.i values globally
      // SQL: Entity.i > MIN(Ref.i) AND Entity.i < MAX(Ref.i)
      _ <- Entity.s.i.>(Ref.i(min)).i_.<(Ref.i(max)).query.get.map(_ ==> List(
        ("b", 2, 1, 3), // 2 is between min(1) and max(3)
      ))

      // SQL inspection: two separate global aggregate subqueries
      _ <- Entity.s.i.>(Ref.i(min)).i_.<(Ref.i(max)).query.inspect.map(_.contains(
          """SELECT DISTINCT
            |  Entity.s,
            |  Entity.i,
            |  subquery1.col,
            |  subquery2.col
            |FROM Entity
            |  CROSS JOIN (
            |    SELECT DISTINCT
            |      MIN(Ref.i) AS col
            |    FROM Ref
            |  ) subquery1
            |  CROSS JOIN (
            |    SELECT DISTINCT
            |      MAX(Ref.i) AS col
            |    FROM Ref
            |  ) subquery2
            |WHERE
            |  Entity.s IS NOT NULL AND
            |  Entity.i > subquery1.col AND
            |  Entity.i < subquery2.col;""".stripMargin
        ) ==> true)
    } yield ()
  }


  "threshold check" - types {
    for {
      // Sensor readings, with alert threshold
      _ <- Entity.s.double.Ref.double.insert(
        ("sensor-1", 75.0, 50.0), // Below threshold
        ("sensor-2", 120.0, 100.0), // Above threshold
        ("sensor-3", 85.0, 100.0), // Above threshold
      ).transact

      // Find sensors where reading is above average threshold
      _ <- Entity.s.double.>(Ref.double(avg)).query.get.map(_ ==> List(
        ("sensor-2", 120.0, 83.33333333333333), // 120 > avg(50,100,100)
        ("sensor-3", 85.0, 83.33333333333333), // Above threshold
      ))

      // Find sensors where reading is above max threshold (alert!)
      _ <- Entity.s.double.>(Ref.double(max)).query.get.map(_ ==> List(
        ("sensor-2", 120.0, 100.0), // 120 > max(100)
      ))
    } yield ()
  }
}
