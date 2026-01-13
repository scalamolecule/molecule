package molecule.db.compliance.test.subquery

import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class _Overview(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "1a. Global aggregate - comparison" - types {
    for {
      _ <- Entity.i.insert(1, 2, 3).transact
      _ <- Ref.i.insert(1, 2).transact

      // Compare Entity.i to sum (aggregate) of ALL Ref.i values (3)
      _ <- Entity.i.<(Ref.i(sum)).query.get.map(_ ==> List(
        (1, 3),
        (2, 3),
      ))
    } yield ()
  }


  "1b. Global aggregate - select" - types {
    for {
      _ <- Entity.s.insert("a", "b", "c").transact
      _ <- Ref.i.insert(1, 2).transact

      // Add count of ALL Refs to each row
      _ <- Entity.s.a1.select(Ref.i(count)).query.get.map(_ ==> List(
        ("a", 2), // 2 total Refs
        ("b", 2), // 2 total Refs
        ("c", 2), // 2 total Refs - same for all
      ))
    } yield ()
  }

  "1c. Global aggregate - join" - types {
    for {
      _ <- Entity.s.insert("a", "b", "c").transact
      _ <- Ref.i.insert(1, 2).transact

      // Add count of ALL Refs to each row
      _ <- Entity.s.a1.join(Ref.i(count)).query.get.map(_ ==> List(
        ("a", 2), // Same as .select() for global aggregates
        ("b", 2),
        ("c", 2), // All rows included
      ))
    } yield ()
  }


  "2. Correlated aggregate - select / join" - types {
    // "Correlated" means the subquery depends on the current row being processed.
    // The .entity_(Entity.id_) creates the correlation: "count Refs WHERE Ref.entity = Entity.id"
    for {
      _ <- Entity.s.i.Refs.*(Ref.i).insert(
        ("a", 1, List(1, 2)), // two Ref rows have foreign keys pointing to "a" Entity row
        ("b", 3, List(3, 3, 4)),
        ("c", 5, List()), // No Ref rows pointing to "c" Entity row
      ).transact

      // Use .select() to count refs per Entity row
      // Returns ALL Entities, even those with no matching Refs
      _ <- Entity.s.a1.select(Ref.i(count).entity_(Entity.id_)).query.get.map(_ ==> List(
        ("a", 2),
        ("b", 3),
        ("c", 0), // ✓ Included with count 0
      ))
      // or we could correlate the Ref.i with Entity.i:
      _ <- Entity.s.a1.select(Ref.i(count).i_(Entity.i_)).query.get.map(_ ==> List(
        ("a", 1), // 1 matches once
        ("b", 2), // 3 matches twice
        ("c", 0), // ✓ Included with count 0
      ))

      // Use .join() to count _related_ refs per Entity row
      // Returns ONLY entities that have matching refs
      _ <- Entity.s.a1.join(Ref.id(count).entity_(Entity.id_)).query.get.map(_ ==> List(
        ("a", 2),
        ("b", 3),
        // ✗ "c" excluded - no matching refs (INNER JOIN semantics)
      ))
      _ <- Entity.s.a1.join(Ref.id(count).i_(Entity.i_)).query.get.map(_ ==> List(
        ("a", 1),
        ("b", 2),
        // ✗ "c" excluded - no matching refs (INNER JOIN semantics)
      ))
    } yield ()
  }


  "3. Sub table values with limit" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(1, 2, 3)),
        ("b", List(4, 5)),
        ("c", List()),
      ).transact

      // Global
      // Select highest of ALL Ref.i rows (5)
      _ <- Entity.s.a1.select(Ref.i.d1.query.limit(1)).query.get.map(_ ==> List(
        ("a", 5),
        ("b", 5),
        ("c", 5),
      ))

      // Correlate per row
      // Select highest Ref.i per entity
      _ <- Entity.s.a1.select(Ref.i.d1.entity_(Entity.id_).query.limit(1)).query.get.map(_ ==> List(
        ("a", 3), // Highest Ref.i for "a"
        ("b", 5), // Highest Ref.i for "b"
        // ("c", ), // No correlated Ref.i value to find
      ))
    } yield ()
  }


  /*
   * SUMMARY
   *
   * GLOBAL PATTERNS - same value from entire sub table for all rows
   *
   * Global aggregate comparison: Entity.i.>(Ref.i(sum))
   *   Compare each row to aggregate of entire sub table
   *
   * Global aggregate select/join: Entity.s.select(Ref.i(count))
   *   Add aggregate from entire sub table to each row
   *   Same result for .select() and .join()
   *
   * Global with limit: Entity.s.select(Ref.i.d1.query.limit(1))
   *   Add top sorted value from entire sub table to each row
   *
   *
   * CORRELATED PATTERNS - different values per row based on correlation
   *
   * Correlated aggregate select: Entity.s.select(Ref.i(count).entity_(Entity.id_))
   *   Per-row aggregate using correlation (e.g., .entity_() or .i_())
   *   Includes ALL rows (default values for no matches)
   *
   * Correlated aggregate join: Entity.s.join(Ref.i(count).entity_(Entity.id_))
   *   Per-row aggregate using correlation
   *   Excludes rows with no matches
   *
   * Correlated with limit: Entity.s.select(Ref.i.d1.entity_(Entity.id_).query.limit(1))
   *   Per-row top sorted value using correlation
   *   Excludes rows with no matches (no value to return)
   */
}
