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


  "1b. Global aggregate - join" - types {
    for {
      _ <- Entity.s.insert("a", "b", "c").transact
      _ <- Ref.i.insert(1, 2).transact

      // Add count of ALL Refs to each row
      _ <- Entity.s.a1.join(Ref.i(count)).query.get.map(_ ==> List(
        ("a", 2), // 2 total Refs
        ("b", 2), // 2 total Refs
        ("c", 2), // 2 total Refs - same for all (global aggregate)
      ))
    } yield ()
  }


  "2. Correlated aggregate - join" - types {
    // "Correlated" means the subquery depends on the current row being processed.
    // The .entity_(Entity.id_) creates the correlation: "count Refs WHERE Ref.entity = Entity.id"
    for {
      _ <- Entity.s.i.Refs.*(Ref.i).insert(
        ("a", 1, List(1, 2)), // two Ref rows have foreign keys pointing to "a" Entity row
        ("b", 3, List(3, 3, 4)),
        ("c", 5, List()), // No Ref rows pointing to "c" Entity row
      ).transact

      // Count refs per Entity row - returns ONLY entities that have matching refs
      _ <- Entity.s.a1.join(Ref.id(count).entity_(Entity.id_)).query.get.map(_ ==> List(
        ("a", 2),
        ("b", 3),
        // ✗ "c" excluded - no matching refs
      ))

      // Or correlate using different attributes (Ref.i with Entity.i):
      _ <- Entity.s.a1.join(Ref.id(count).i_(Entity.i_)).query.get.map(_ ==> List(
        ("a", 1), // 1 matches once
        ("b", 2), // 3 matches twice
        // ✗ "c" excluded - no matching refs
      ))
    } yield ()
  }


  "3. Sub table values with limit/offset" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(1, 2, 3)),
        ("b", List(4, 5)),
        ("c", List()),
      ).transact


      // Correlated - highest Ref.i per entity (just 1-1 correlation)
      _ <- Entity.s.a1.join(Ref.i.d1.entity_(Entity.id_)).query.i.get.map(_ ==> List(
        ("a", 3),
        ("a", 2),
        ("a", 1),
        ("b", 5),
        ("b", 4),
      ))

      // limit(3) - all in this case

      _ <- Entity.s.a1.join(Ref.i.d1.entity_(Entity.id_).query.limit(3)).query.i.get.map(_ ==> List(
        ("a", 3),
        ("a", 2),
        ("a", 1),
        ("b", 5),
        ("b", 4),
      ))
      _ <- Entity.s.a1.join(Ref.i.d1.entity_(Entity.id_).query.offset(1).limit(3)).query.i.get.map(_ ==> List(
        // ("a", 3),
        ("a", 2),
        ("a", 1),
        // ("b", 5),
        ("b", 4),
      ))
      _ <- Entity.s.a1.join(Ref.i.d1.entity_(Entity.id_).query.offset(2).limit(3)).query.i.get.map(_ ==> List(
        // ("a", 3),
        // ("a", 2),
        ("a", 1),
        // ("b", 5),
        // ("b", 4),
      ))


      // limit(2)

      _ <- Entity.s.a1.join(Ref.i.d1.entity_(Entity.id_).query.limit(2)).query.i.get.map(_ ==> List(
        ("a", 3),
        ("a", 2),
        // ("a", 1),
        ("b", 5),
        ("b", 4),
      ))
      _ <- Entity.s.a1.join(Ref.i.d1.entity_(Entity.id_).query.offset(1).limit(2)).query.i.get.map(_ ==> List(
        // ("a", 3),
        ("a", 2),
        ("a", 1),
        // ("b", 5),
        ("b", 4),
      ))
      _ <- Entity.s.a1.join(Ref.i.d1.entity_(Entity.id_).query.offset(2).limit(2)).query.i.get.map(_ ==> List(
        // ("a", 3),
        // ("a", 2),
        ("a", 1),
        // ("b", 5),
        // ("b", 4),
      ))


      // limit(1)

      _ <- Entity.s.a1.join(Ref.i.d1.entity_(Entity.id_).query.limit(1)).query.i.get.map(_ ==> List(
        ("a", 3),
        // ("a", 2),
        // ("a", 1),
        ("b", 5),
        // ("b", 4),
      ))
      _ <- Entity.s.a1.join(Ref.i.d1.entity_(Entity.id_).query.offset(1).limit(1)).query.i.get.map(_ ==> List(
        // ("a", 3),
        ("a", 2),
        // ("a", 1),
        // ("b", 5),
        ("b", 4),
      ))
      _ <- Entity.s.a1.join(Ref.i.d1.entity_(Entity.id_).query.offset(2).limit(1)).query.i.get.map(_ ==> List(
        // ("a", 3),
        // ("a", 2),
        ("a", 1),
        // ("b", 5),
        // ("b", 4),
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
   * Global aggregate join: Entity.s.join(Ref.i(count))
   *   Add aggregate from entire sub table to each row
   *
   * Global with limit: Entity.s.join(Ref.i.d1.query.limit(1))
   *   Add top sorted value from entire sub table to each row
   *
   *
   * CORRELATED PATTERNS - different values per row based on correlation
   *
   * Correlated aggregate: Entity.s.join(Ref.i(count).entity_(Entity.id_))
   *   Per-row aggregate using correlation (e.g., .entity_() or .i_())
   *   Returns ONLY rows with matches
   *
   * Correlated with limit: Entity.s.join(Ref.i.d1.entity_(Entity.id_).query.limit(1))
   *   Per-row top sorted value using correlation
   *   Returns ONLY rows with matches
   */
}
