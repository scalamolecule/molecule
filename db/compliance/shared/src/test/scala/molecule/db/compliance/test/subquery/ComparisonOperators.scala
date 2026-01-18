package molecule.db.compliance.test.subquery

import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import org.scalactic.Equality

case class ComparisonOperators(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

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
      _ <- Entity.i(Ref.i(count)).query.get.map(_ ==> List((3, 3)))
      _ <- Entity.i.not(Ref.i(count)).a1.query.get.map(_ ==> List((2, 3), (4, 3)))
      _ <- Entity.i.<(Ref.i(count)).query.get.map(_ ==> List((2, 3)))
      _ <- Entity.i.<=(Ref.i(count)).a1.query.get.map(_ ==> List((2, 3), (3, 3)))
      _ <- Entity.i.>(Ref.i(count)).query.get.map(_ ==> List((4, 3)))
      _ <- Entity.i.>=(Ref.i(count)).a1.query.get.map(_ ==> List((3, 3), (4, 3)))

      // tacit count aggregate
      _ <- Entity.i(Ref.i_(count)).query.get.map(_ ==> List(3))
      _ <- Entity.i.not(Ref.i_(count)).a1.query.get.map(_ ==> List(2, 4))
      _ <- Entity.i.<(Ref.i_(count)).query.get.map(_ ==> List(2))
      _ <- Entity.i.<=(Ref.i_(count)).a1.query.get.map(_ ==> List(2, 3))
      _ <- Entity.i.>(Ref.i_(count)).query.get.map(_ ==> List(4))
      _ <- Entity.i.>=(Ref.i_(count)).a1.query.get.map(_ ==> List(3, 4))
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
      _ <- Entity.s.a1.i(Ref.i(count)).query.get.map(_ ==> List(("b", 3, 3)))
      _ <- Entity.s.a1.i.not(Ref.i(count)).query.get.map(_ ==> List(("a", 2, 3), ("c", 4, 3)))
      _ <- Entity.s.a1.i.<(Ref.i(count)).query.get.map(_ ==> List(("a", 2, 3)))
      _ <- Entity.s.a1.i.<=(Ref.i(count)).query.get.map(_ ==> List(("a", 2, 3), ("b", 3, 3)))
      _ <- Entity.s.a1.i.>(Ref.i(count)).query.get.map(_ ==> List(("c", 4, 3)))
      _ <- Entity.s.a1.i.>=(Ref.i(count)).query.get.map(_ ==> List(("b", 3, 3), ("c", 4, 3)))

      // Tacit count aggregate
      _ <- Entity.s.a1.i(Ref.i_(count)).query.get.map(_ ==> List(("b", 3)))
      _ <- Entity.s.a1.i.not(Ref.i_(count)).query.get.map(_ ==> List(("a", 2), ("c", 4)))
      _ <- Entity.s.a1.i.<(Ref.i_(count)).query.get.map(_ ==> List(("a", 2)))
      _ <- Entity.s.a1.i.<=(Ref.i_(count)).query.get.map(_ ==> List(("a", 2), ("b", 3)))
      _ <- Entity.s.a1.i.>(Ref.i_(count)).query.get.map(_ ==> List(("c", 4)))
      _ <- Entity.s.a1.i.>=(Ref.i_(count)).query.get.map(_ ==> List(("b", 3), ("c", 4)))
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
      _ <- Entity.s.a1.i_(Ref.i(count)).query.get.map(_ ==> List(("b", 3)))
      _ <- Entity.s.a1.i_.not(Ref.i(count)).query.get.map(_ ==> List(("a", 3), ("c", 3)))
      _ <- Entity.s.a1.i_.<(Ref.i(count)).query.get.map(_ ==> List(("a", 3)))
      _ <- Entity.s.a1.i_.<=(Ref.i(count)).query.get.map(_ ==> List(("a", 3), ("b", 3)))
      _ <- Entity.s.a1.i_.>(Ref.i(count)).query.get.map(_ ==> List(("c", 3)))
      _ <- Entity.s.a1.i_.>=(Ref.i(count)).query.get.map(_ ==> List(("b", 3), ("c", 3)))

      // Tacit attribute with tacit count aggregate
      _ <- Entity.s.a1.i_(Ref.i_(count)).query.get.map(_ ==> List("b"))
      _ <- Entity.s.a1.i_.not(Ref.i_(count)).query.get.map(_ ==> List("a", "c"))
      _ <- Entity.s.a1.i_.<(Ref.i_(count)).query.get.map(_ ==> List("a"))
      _ <- Entity.s.a1.i_.<=(Ref.i_(count)).query.get.map(_ ==> List("a", "b"))
      _ <- Entity.s.a1.i_.>(Ref.i_(count)).query.get.map(_ ==> List("c"))
      _ <- Entity.s.a1.i_.>=(Ref.i_(count)).query.get.map(_ ==> List("b", "c"))
    } yield ()
  }


  "Tacit aggregate, countDistinct" - types {
    for {
      _ <- Entity.i.Ref.i.insert(
        (1, 1),
        (2, 2),
        (3, 2),
      ).transact

      _ <- Entity.i(Ref.i(countDistinct)).a1.query.get.map(_ ==> List((2, 2)))
      _ <- Entity.i.not(Ref.i(countDistinct)).a1.query.get.map(_ ==> List((1, 2), (3, 2)))
      _ <- Entity.i.<(Ref.i(countDistinct)).a1.query.get.map(_ ==> List((1, 2)))
      _ <- Entity.i.<=(Ref.i(countDistinct)).a1.query.get.map(_ ==> List((1, 2), (2, 2)))
      _ <- Entity.i.>(Ref.i(countDistinct)).a1.query.get.map(_ ==> List((3, 2)))
      _ <- Entity.i.>=(Ref.i(countDistinct)).a1.query.get.map(_ ==> List((2, 2), (3, 2)))

      _ <- Entity.i(Ref.i_(countDistinct)).a1.query.get.map(_ ==> List(2))
      _ <- Entity.i.not(Ref.i_(countDistinct)).a1.query.get.map(_ ==> List(1, 3))
      _ <- Entity.i.<(Ref.i_(countDistinct)).a1.query.get.map(_ ==> List(1))
      _ <- Entity.i.<=(Ref.i_(countDistinct)).a1.query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.>(Ref.i_(countDistinct)).a1.query.get.map(_ ==> List(3))
      _ <- Entity.i.>=(Ref.i_(countDistinct)).a1.query.get.map(_ ==> List(2, 3))
    } yield ()
  }

  "Tacit aggregate, min" - types {
    for {
      _ <- Entity.i.Ref.i.insert(
        (1, 2),
        (2, 3),
        (3, 2),
      ).transact

      _ <- Entity.i(Ref.i(min)).a1.query.get.map(_ ==> List((2, 2)))
      _ <- Entity.i.not(Ref.i(min)).a1.query.get.map(_ ==> List((1, 2), (3, 2)))
      _ <- Entity.i.<(Ref.i(min)).a1.query.get.map(_ ==> List((1, 2)))
      _ <- Entity.i.<=(Ref.i(min)).a1.query.get.map(_ ==> List((1, 2), (2, 2)))
      _ <- Entity.i.>(Ref.i(min)).a1.query.get.map(_ ==> List((3, 2)))
      _ <- Entity.i.>=(Ref.i(min)).a1.query.get.map(_ ==> List((2, 2), (3, 2)))

      _ <- Entity.i(Ref.i_(min)).a1.query.get.map(_ ==> List(2))
      _ <- Entity.i.not(Ref.i_(min)).a1.query.get.map(_ ==> List(1, 3))
      _ <- Entity.i.<(Ref.i_(min)).a1.query.get.map(_ ==> List(1))
      _ <- Entity.i.<=(Ref.i_(min)).a1.query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.>(Ref.i_(min)).a1.query.get.map(_ ==> List(3))
      _ <- Entity.i.>=(Ref.i_(min)).a1.query.get.map(_ ==> List(2, 3))
    } yield ()
  }

  "Tacit aggregate, max" - types {
    for {
      _ <- Entity.i.Ref.i.insert(
        (1, 1),
        (2, 2),
        (3, 1),
      ).transact

      _ <- Entity.i(Ref.i(max)).a1.query.get.map(_ ==> List((2, 2)))
      _ <- Entity.i.not(Ref.i(max)).a1.query.get.map(_ ==> List((1, 2), (3, 2)))
      _ <- Entity.i.<(Ref.i(max)).a1.query.get.map(_ ==> List((1, 2)))
      _ <- Entity.i.<=(Ref.i(max)).a1.query.get.map(_ ==> List((1, 2), (2, 2)))
      _ <- Entity.i.>(Ref.i(max)).a1.query.get.map(_ ==> List((3, 2)))
      _ <- Entity.i.>=(Ref.i(max)).a1.query.get.map(_ ==> List((2, 2), (3, 2)))

      _ <- Entity.i(Ref.i_(max)).a1.query.get.map(_ ==> List(2))
      _ <- Entity.i.not(Ref.i_(max)).a1.query.get.map(_ ==> List(1, 3))
      _ <- Entity.i.<(Ref.i_(max)).a1.query.get.map(_ ==> List(1))
      _ <- Entity.i.<=(Ref.i_(max)).a1.query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.>(Ref.i_(max)).a1.query.get.map(_ ==> List(3))
      _ <- Entity.i.>=(Ref.i_(max)).a1.query.get.map(_ ==> List(2, 3))
    } yield ()
  }

  "Tacit aggregate, sum" - types {
    for {
      _ <- Entity.i.Ref.i.insert(
        (1, -1),
        (2, 1),
        (3, 2),
      ).transact

      _ <- Entity.i(Ref.i(sum)).a1.query.get.map(_ ==> List((2, 2)))
      _ <- Entity.i.not(Ref.i(sum)).a1.query.get.map(_ ==> List((1, 2), (3, 2)))
      _ <- Entity.i.<(Ref.i(sum)).a1.query.get.map(_ ==> List((1, 2)))
      _ <- Entity.i.<=(Ref.i(sum)).a1.query.get.map(_ ==> List((1, 2), (2, 2)))
      _ <- Entity.i.>(Ref.i(sum)).a1.query.get.map(_ ==> List((3, 2)))
      _ <- Entity.i.>=(Ref.i(sum)).a1.query.get.map(_ ==> List((2, 2), (3, 2)))

      _ <- Entity.i(Ref.i_(sum)).a1.query.get.map(_ ==> List(2))
      _ <- Entity.i.not(Ref.i_(sum)).a1.query.get.map(_ ==> List(1, 3))
      _ <- Entity.i.<(Ref.i_(sum)).a1.query.get.map(_ ==> List(1))
      _ <- Entity.i.<=(Ref.i_(sum)).a1.query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.>(Ref.i_(sum)).a1.query.get.map(_ ==> List(3))
      _ <- Entity.i.>=(Ref.i_(sum)).a1.query.get.map(_ ==> List(2, 3))
    } yield ()
  }

  "Tacit aggregate, median" - types {
    if (Seq("mariadb", "mysql", "sqlite").contains(database)) {
      Entity.double(Ref.i(median)).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Median, variance and stddev in subqueries not supported for this database."
        }
    } else {
      for {
        _ <- Entity.double.Ref.i.insert(
          (1.0, 1),
          (2.0, 2),
          (3.0, 3),
        ).transact

        _ <- Ref.i(median).query.get.map(_ ==> List(2.0))

        _ <- Entity.double(Ref.i(median)).a1.query.get.map(_ ==> List((2, 2)))
        _ <- Entity.double.not(Ref.i(median)).a1.query.get.map(_ ==> List((1, 2), (3, 2)))
        _ <- Entity.double.<(Ref.i(median)).a1.query.get.map(_ ==> List((1, 2)))
        _ <- Entity.double.<=(Ref.i(median)).a1.query.get.map(_ ==> List((1, 2), (2, 2)))
        _ <- Entity.double.>(Ref.i(median)).a1.query.get.map(_ ==> List((3, 2)))
        _ <- Entity.double.>=(Ref.i(median)).a1.query.get.map(_ ==> List((2, 2), (3, 2)))

        _ <- Entity.double(Ref.i_(median)).a1.query.get.map(_ ==> List(2))
        _ <- Entity.double.not(Ref.i_(median)).a1.query.get.map(_ ==> List(1, 3))
        _ <- Entity.double.<(Ref.i_(median)).a1.query.get.map(_ ==> List(1))
        _ <- Entity.double.<=(Ref.i_(median)).a1.query.get.map(_ ==> List(1, 2))
        _ <- Entity.double.>(Ref.i_(median)).a1.query.get.map(_ ==> List(3))
        _ <- Entity.double.>=(Ref.i_(median)).a1.query.get.map(_ ==> List(2, 3))
      } yield ()
    }
  }

  "Tacit aggregate, variance" - types {
    if (Seq("mariadb", "mysql", "sqlite").contains(database)) {
      Entity.double(Ref.i(median)).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Median, variance and stddev in subqueries not supported for this database."
        }
    } else {
      given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
      val v = if (database == "postgresql") 0.6666666667 else 0.6666666666666666
      for {
        _ <- Entity.double.Ref.i.insert(
          (0, 1),
          (v, 2),
          (1, 3),
        ).transact

        _ <- Ref.i(variance).query.get.map(_.head ==~ v)

        _ <- Entity.double(Ref.i(variance)).a1.query.get.map(_ ==~ List((v, v)))
        _ <- Entity.double.not(Ref.i(variance)).a1.query.i.get.map(_ ==~ List((0, v), (1, v)))
        _ <- Entity.double.<(Ref.i(variance)).a1.query.get.map(_ ==~ List((0, v)))
        _ <- Entity.double.<=(Ref.i(variance)).a1.query.get.map(_ ==~ List((0, v), (v, v)))
        _ <- Entity.double.>(Ref.i(variance)).a1.query.get.map(_ ==~ List((1, v)))
        _ <- Entity.double.>=(Ref.i(variance)).a1.query.get.map(_ ==~ List((v, v), (1, v)))

        _ <- Entity.double(Ref.i_(variance)).a1.query.get.map(_ ==~ List(v))
        _ <- Entity.double.not(Ref.i_(variance)).a1.query.get.map(_ ==~ List(0, 1))
        _ <- Entity.double.<(Ref.i_(variance)).a1.query.get.map(_ ==~ List(0))
        _ <- Entity.double.<=(Ref.i_(variance)).a1.query.get.map(_ ==~ List(0, v))
        _ <- Entity.double.>(Ref.i_(variance)).a1.query.get.map(_ ==~ List(1))
        _ <- Entity.double.>=(Ref.i_(variance)).a1.query.get.map(_ ==~ List(v, 1))
      } yield ()
    }
  }

  "Tacit aggregate, stddev" - types {
    if (Seq("mariadb", "mysql", "sqlite").contains(database)) {
      Entity.double(Ref.i(median)).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Median, variance and stddev in subqueries not supported for this database."
        }
    } else {
      given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
      val v = 0.816496580927726
      for {
        _ <- Entity.double.Ref.i.insert(
          (0, 1),
          (v, 2),
          (1, 3),
        ).transact

        _ <- Ref.i(stddev).query.get.map(_.head ==~ v)

        _ <- Entity.double(Ref.i(stddev)).a1.query.get.map(_ ==~ List((v, v)))
        _ <- Entity.double.not(Ref.i(stddev)).a1.query.get.map(_ ==~ List((0, v), (1, v)))
        _ <- Entity.double.<(Ref.i(stddev)).a1.query.get.map(_ ==~ List((0, v)))
        _ <- Entity.double.<=(Ref.i(stddev)).a1.query.get.map(_ ==~ List((0, v), (v, v)))
        _ <- Entity.double.>(Ref.i(stddev)).a1.query.get.map(_ ==~ List((1, v)))
        _ <- Entity.double.>=(Ref.i(stddev)).a1.query.get.map(_ ==~ List((v, v), (1, v)))

        _ <- Entity.double(Ref.i_(stddev)).a1.query.get.map(_ ==~ List(v))
        _ <- Entity.double.not(Ref.i_(stddev)).a1.query.get.map(_ ==~ List(0, 1))
        _ <- Entity.double.<(Ref.i_(stddev)).a1.query.get.map(_ ==~ List(0))
        _ <- Entity.double.<=(Ref.i_(stddev)).a1.query.get.map(_ ==~ List(0, v))
        _ <- Entity.double.>(Ref.i_(stddev)).a1.query.get.map(_ ==~ List(1))
        _ <- Entity.double.>=(Ref.i_(stddev)).a1.query.get.map(_ ==~ List(v, 1))
      } yield ()
    }
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



  "Self-aggregation" - types {
    for {
      _ <- Entity.double.insert(1.0, 2.0, 3.0).transact

      // Entity.double allowed to point to outer attribute as a subquery when it aggregates
      _ <- Entity.double.<=(Entity.double(avg)).query.get.map(_ ==> List(
        (1.0, 2.0),
        (2.0, 2.0),
      ))

      // This on the other hand is interpretted as an attempt to use a filter attribute
      _ <- Entity.double.<=(Entity.double).query.get
        .map(_ ==> "Should fail").recover { case ModelError(err) =>
          err ==> "Filter attribute Entity.double should be tacit."
        }
    } yield ()
  }
}
