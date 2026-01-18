package molecule.db.compliance.test.subquery

import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import org.scalactic.Equality

case class Sorting(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "by aggregate - ascending" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("c", List(1, 2, 3)),
        ("a", List(1)),
        ("b", List(1, 2)),
      ).transact

      // Sort by count ascending
      _ <- Entity.s.join(Ref.id(count).a1.entity_(Entity.id_)).query.get.map(_ ==> List(
        ("a", 1),
        ("b", 2),
        ("c", 3),
      ))
    } yield ()
  }


  "by aggregate - descending" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(1)),
        ("b", List(1, 2)),
        ("c", List(1, 2, 3, 4, 5)),
        ("d", List()),
      ).transact

      // Sort by count descending
      _ <- Entity.s.join(Ref.id(count).d1.entity_(Entity.id_)).query.get.map(_ ==> List(
        ("c", 5),
        ("b", 2),
        ("a", 1),
        // "d" excluded - no refs
      ))
    } yield ()
  }


  "by outer attribute" - types {
    for {
      _ <- Entity.s.i.Refs.*(Ref.i).insert(
        ("c", 3, List(1)),
        ("a", 1, List(1, 2, 3)),
        ("b", 2, List(1, 2)),
        ("d", 4, List()),
      ).transact

      // Sort by outer attribute i ascending
      _ <- Entity.s.i.a1.join(Ref.id(count).entity_(Entity.id_)).query.get.map(_ ==> List(
        ("a", 1, 3),
        ("b", 2, 2),
        ("c", 3, 1),
        // "d" excluded
      ))
    } yield ()
  }


  "by multiple attributes" - types {
    for {
      _ <- Entity.s.i.Refs.*(Ref.i).insert(
        ("a", 1, List(10, 20)),
        ("b", 2, List(5)),
        ("c", 1, List(30, 40, 50)),
        ("d", 2, List(15, 25)),
        ("e", 3, List()),
      ).transact

      // Sort by outer i ascending and inner count descending
      // As with nested queries, sort markers start at index 1 on each level.
      _ <- Entity.s.i.a1.join(Ref.id(count).d1.entity_(Entity.id_)).query.get.map(_ ==> List(
        ("c", 1, 3), // i=1, count=3
        ("a", 1, 2), // i=1, count=2
        ("d", 2, 2), // i=2, count=2
        ("b", 2, 1), // i=2, count=1
        // "e" excluded
      ))

      // Sort by outer i descending, then by count ascending
      _ <- Entity.s.i.d1.join(Ref.id(count).a1.entity_(Entity.id_)).query.get.map(_ ==> List(
        ("b", 2, 1),
        ("d", 2, 2),
        ("a", 1, 2),
        ("c", 1, 3),
        // "e" excluded
      ))
    } yield ()
  }


  "by different aggregates" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(1, 2, 3)), // sum=6
        ("b", List(10)), // sum=10
        ("c", List(2, 3, 4, 5)), // sum=14
        ("d", List()),
      ).transact

      // Sort by sum descending
      _ <- Entity.s.join(Ref.i(sum).d1.entity_(Entity.id_)).query.get.map(_ ==> List(
        ("c", 14),
        ("b", 10),
        ("a", 6),
        // "d" excluded
      ))

      // Sort by min ascending
      _ <- Entity.s.join(Ref.i(min).a1.entity_(Entity.id_)).query.get.map(_ ==> List(
        ("a", 1),
        ("c", 2),
        ("b", 10),
        // "d" excluded
      ))

      // Sort by max descending
      _ <- Entity.s.join(Ref.i(max).d1.entity_(Entity.id_)).query.get.map(_ ==> List(
        ("b", 10),
        ("c", 5),
        ("a", 3),
        // "d" excluded
      ))
    } yield ()
  }


  "by aggregate with filters" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(1, 5, 10, 15)),
        ("b", List(2, 3, 4)),
        ("c", List(8, 12, 20)),
        ("d", List()),
      ).transact

      // Count refs where i > 5, sort by count descending
      _ <- Entity.s.join(Ref.i_.>(5).id(count).d1.entity_(Entity.id_)).query.get.map(_ ==> List(
        ("c", 3), // i=8,12,20
        ("a", 2), // i=10,15
        // "b", "d" excluded
      ))
    } yield ()
  }


  "multiple aggregates" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(5, 5, 5)), // count=3, avg=5
        ("b", List(10)), // count=1, avg=10
        ("c", List(1, 2, 3, 4)), // count=4, avg=2.5
        ("d", List()),
      ).transact

      _ <- Entity.s.join(Ref.id(count).a1.i(avg).entity_(Entity.id_)).query.get.map(_ ==> List(
        ("b", (1, 10.0)),
        ("a", (3, 5.0)),
        ("c", (4, 2.5)),
        // "d" excluded
      ))
      _ <- Entity.s.join(Ref.id(count).d1.i(avg).entity_(Entity.id_)).query.get.map(_ ==> List(
        ("c", (4, 2.5)),
        ("a", (3, 5.0)),
        ("b", (1, 10.0)),
      ))
      _ <- Entity.s.join(Ref.id(count).i(avg).a1.entity_(Entity.id_)).query.get.map(_ ==> List(
        ("c", (4, 2.5)),
        ("a", (3, 5.0)),
        ("b", (1, 10.0)),
        // "d" excluded
      ))
      _ <- Entity.s.join(Ref.id(count).i(avg).d1.entity_(Entity.id_)).query.get.map(_ ==> List(
        ("b", (1, 10.0)),
        ("a", (3, 5.0)),
        ("c", (4, 2.5)),
      ))
    } yield ()
  }


  "by string aggregate (min/max)" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.s).insert(
        ("a", List("z", "y", "x")),
        ("b", List("m", "n")),
        ("c", List("a", "b", "c")),
        ("d", List()),
      ).transact

      // Sort by min string ascending
      _ <- Entity.s.join(Ref.s(min).a1.entity_(Entity.id_)).query.get.map(_ ==> List(
        ("c", "a"),
        ("b", "m"),
        ("a", "x"),
        // "d" excluded
      ))

      // Sort by max string ascending
      _ <- Entity.s.join(Ref.s(max).a1.entity_(Entity.id_)).query.get.map(_ ==> List(
        ("c", "c"),
        ("b", "n"),
        ("a", "z"),
        // "d" excluded
      ))

      // Sort by min string descending
      _ <- Entity.s.join(Ref.s(min).d1.entity_(Entity.id_)).query.get.map(_ ==> List(
          ("a", "x"),
          ("b", "m"),
          ("c", "a"),
          // "d" excluded
        ))

      // Sort by max string descending
      _ <- Entity.s.join(Ref.s(max).d1.entity_(Entity.id_)).query.get.map(_ ==> List(
          ("a", "z"),
          ("b", "n"),
          ("c", "c"),
          // "d" excluded
        ))
    } yield ()
  }


  "by countDistinct" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(1, 1, 2)), // 2 distinct
        ("b", List(5, 5, 5, 5)), // 1 distinct
        ("c", List(1, 2, 3, 4)), // 4 distinct
        ("d", List()),
      ).transact

      // Sort by countDistinct ascending
      _ <- Entity.s.join(Ref.i(countDistinct).a1.entity_(Entity.id_)).query.get.map(_ ==> List(
        ("b", 1),
        ("a", 2),
        ("c", 4),
        // "d" excluded
      ))

      // Sort by countDistinct descending
      _ <- Entity.s.join(Ref.i(countDistinct).d1.entity_(Entity.id_)).query.get.map(_ ==> List(
        ("c", 4),
        ("a", 2),
        ("b", 1),
        // "d" excluded
      ))
    } yield ()
  }


  "by avg" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(2, 4, 6)), // avg = 4.0
        ("b", List(10, 20)), // avg = 15.0
        ("c", List(1, 1, 1)), // avg = 1.0
        ("d", List()),
      ).transact

      // Sort by avg ascending
      _ <- Entity.s.join(Ref.i(avg).a1.entity_(Entity.id_)).query.get.map(_ ==> List(
        ("c", 1.0),
        ("a", 4.0),
        ("b", 15.0),
        // "d" excluded
      ))

      // Sort by avg descending
      _ <- Entity.s.join(Ref.i(avg).d1.entity_(Entity.id_)).query.get.map(_ ==> List(
        ("b", 15.0),
        ("a", 4.0),
        ("c", 1.0),
        // "d" excluded
      ))
    } yield ()
  }


  "by median" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(1, 2, 3)), // median = 2.0
        ("b", List(10, 20, 30, 40)), // median = 25.0
        ("c", List(5)), // median = 5.0
        ("d", List()),
      ).transact

      // Sort by median ascending
      _ <- if (List("sqlite", "mariadb", "mysql").contains(database))
        Entity.s.join(Ref.i(median).a1.entity_(Entity.id_)).query.get
          .map(_ ==> "Should fail").recover { case ModelError(err) =>
            err ==> "Sorting by median not implemented for this database."
          }
      else
        Entity.s.join(Ref.i(median).a1.entity_(Entity.id_)).query.get.map(_ ==> List(
          ("a", 2.0),
          ("c", 5.0),
          ("b", 25.0),
          // "d" excluded
        ))

      // Sort by median descending
      _ <- if (List("sqlite", "mariadb", "mysql").contains(database))
        Entity.s.join(Ref.i(median).d1.entity_(Entity.id_)).query.get
          .map(_ ==> "Should fail").recover { case ModelError(err) =>
            err ==> "Sorting by median not implemented for this database."
          }
      else
        Entity.s.join(Ref.i(median).d1.entity_(Entity.id_)).query.get.map(_ ==> List(
          ("b", 25.0),
          ("c", 5.0),
          ("a", 2.0),
          // "d" excluded
        ))
    } yield ()
  }


  "by variance" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(2, 4, 6)), // variance = 2.6666666667 (mean=4, deviations: 4,0,4)
        ("b", List(1, 5, 9)), // variance = 10.6666666667 (mean=5, deviations: 16,0,16)
        ("c", List(10, 12)), // variance = 1.0 (mean=11, deviations: 1,1)
        ("d", List()),
      ).transact

      // Sort by variance ascending
      _ <- if (List("sqlite", "mariadb", "mysql").contains(database))
        Entity.s.join(Ref.i(variance).a1.entity_(Entity.id_)).query.get
          .map(_ ==> "Should fail").recover { case ModelError(err) =>
            err ==> "Sorting by variance not implemented for this database."
          }
      else
        Entity.s.join(Ref.i(variance).a1.entity_(Entity.id_)).query.get.map(_ ==~ List(
          ("c", 1.0),
          ("a", 2.6666666667),
          ("b", 10.6666666667),
          // "d" excluded
        ))

      // Sort by variance descending
      _ <- if (List("sqlite", "mariadb", "mysql").contains(database))
        Entity.s.join(Ref.i(variance).d1.entity_(Entity.id_)).query.get
          .map(_ ==> "Should fail").recover { case ModelError(err) =>
            err ==> "Sorting by variance not implemented for this database."
          }
      else
        Entity.s.join(Ref.i(variance).d1.entity_(Entity.id_)).query.get.map(_ ==~ List(
          ("b", 10.6666666667),
          ("a", 2.6666666667),
          ("c", 1.0),
          // "d" excluded
        ))
    } yield ()
  }


  "by stddev" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(2, 4, 6)), // stddev ≈ 1.633 (variance = 2.667)
        ("b", List(10, 20, 30)), // stddev ≈ 8.165 (variance = 66.667)
        ("c", List(10, 12)), // stddev = 1.0 (variance = 1.0)
        ("d", List()),
      ).transact

      // Sort by stddev ascending
      _ <- if (List("sqlite", "mariadb", "mysql").contains(database))
        Entity.s.join(Ref.i(stddev).a1.entity_(Entity.id_)).query.get
          .map(_ ==> "Should fail").recover { case ModelError(err) =>
            err ==> "Sorting by standard deviation not implemented for this database."
          }
      else
        Entity.s.join(Ref.i(stddev).a1.entity_(Entity.id_)).query.get.map(_ ==~ List(
          ("c", 1.0),
          ("a", 1.6329931619),
          ("b", 8.1649658093),
          // "d" excluded
        ))

      // Sort by stddev descending
      _ <- if (List("sqlite", "mariadb", "mysql").contains(database))
        Entity.s.join(Ref.i(stddev).d1.entity_(Entity.id_)).query.get
          .map(_ ==> "Should fail").recover { case ModelError(err) =>
            err ==> "Sorting by standard deviation not implemented for this database."
          }
      else
        Entity.s.join(Ref.i(stddev).d1.entity_(Entity.id_)).query.get.map(_ ==~ List(
          ("b", 8.1649658093),
          ("a", 1.6329931619),
          ("c", 1.0),
          // "d" excluded
        ))
    } yield ()
  }


  "leaderboard" - types {
    for {
      // Player name, level, (match_id, score)
      _ <- Entity.s.i.Refs.*(Ref.i).insert(
        ("alice", 10, List(85, 92, 88)),
        ("bob", 8, List(95, 90)),
        ("charlie", 12, List(70, 75, 80, 85)),
        ("diana", 5, List()), // No matches yet
      ).transact

      // Leaderboard: sort by total score descending
      _ <- Entity.s.i
        .join(Ref.i(count).entity_(Entity.id_))
        .join(Ref.i(sum).d1.entity_(Entity.id_)) // Sort by total score
        .query.get.map(_ ==> List(
          ("charlie", 12, 4, 310), // 70+75+80+85
          ("alice", 10, 3, 265), // 85+92+88
          ("bob", 8, 2, 185), // 95+90
          // "diana" excluded - no matches yet
        ))

      // Or use single join with tuple result
      _ <- Entity.s.i.join(Ref.i(count).i(sum).d1.entity_(Entity.id_)).query.get.map(_ ==> List(
          ("charlie", 12, (4, 310)),
          ("alice", 10, (3, 265)),
          ("bob", 8, (2, 185)),
          // "diana" excluded - no matches yet
        ))
    } yield ()
  }


  "tacit outer" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(1, 2)),
        ("b", List(1, 2, 3)),
        ("c", List(1)),
        ("d", List()),
      ).transact

      // Return only counts, sorted descending
      _ <- Entity.s_.join(Ref.id(count).d1.entity_(Entity.id_)).query.get.map(_ ==> List(3, 2, 1))
    } yield ()
  }
}
