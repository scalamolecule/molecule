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


  "2a. Correlated per-row subquery - value" - types {
    // "Correlated per-row" means the subquery depends on the outer row being processed.
    for {
      _ <- Entity.s.i.Refs.*(Ref.i.s).insert(
        ("a", 1, List((10, "a"))),
        ("b", 3, List((30, "x"))),
        ("c", 5, List()), // No Ref rows pointing to "c" Entity row
      ).transact

      // Join correlated Ref.i value to each Entity row
      // The .entity_(Entity.id_) creates the correlation: "get Ref value WHERE Ref.entity = Entity.id"
      _ <- Entity.s.a1.join(Ref.i.entity_(Entity.id_)).query.get.map(_ ==> List(
        ("a", 10),
        ("b", 30),
        // ✗ "c" excluded - no matching refs
      ))

      // Or correlate using different attributes (Ref.s with Entity.s):
      _ <- Entity.s.a1.join(Ref.i.s_(Entity.s_)).query.get.map(_ ==> List(
        ("a", 10), // Entity.s=a matches Ref.s=a
        // ("b", 3), // no match
        // ✗ "c" excluded - no matching refs (Entity.i=5 has no Ref.i=5)
      ))
    } yield ()
  }


  "2b. Correlated per-row subquery - aggregated value" - types {
    // Aggregated correlated per-row subquery: count, sum, etc. per row
    for {
      _ <- Entity.s.i.Refs.*(Ref.i).insert(
        ("a", 1, List(1, 2)), // two Ref rows have foreign keys pointing to "a" Entity row
        ("b", 3, List(3, 3, 4)),
        ("c", 5, List()), // No Ref rows pointing to "c" Entity row
      ).transact

      // Join to count of correlated Ref.i value of each Entity row
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


  "1a. Global comparison of aggregated value" - types {
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


  "1b. Global join to aggregated value" - types {
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
}
