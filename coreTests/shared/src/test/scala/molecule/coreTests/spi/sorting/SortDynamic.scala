package molecule.coreTests.spi.sorting

import molecule.base.error.ModelError
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait SortDynamic extends CoreTestSuite with Api_async { spi: Spi_async =>


  override lazy val tests = Tests {

    "Dynamic sort markers" - types { implicit conn =>
      for {
        _ <- Ns.boolean.string.uri.i.int.insert(
          (false, "b", uri4, 8, 16),
          (true, "a", uri2, 3, 5),
          (true, "b", uri4, 7, 13),
          (false, "a", uri1, 2, 3),
          (true, "a", uri1, 1, 2),
          (true, "a", uri2, 4, 7),
          (false, "b", uri4, 8, 15),
          (true, "a", uri2, 4, 8),
          (false, "b", uri4, 7, 13),
          (false, "b", uri3, 5, 9),
          (true, "b", uri3, 6, 12),
          (false, "a", uri1, 2, 4),
          (true, "a", uri1, 2, 4),
          (true, "b", uri4, 8, 15),
          (true, "a", uri1, 1, 1),
          (false, "b", uri3, 6, 11),
          (false, "b", uri4, 7, 14),
          (false, "a", uri1, 1, 1),
          (false, "a", uri2, 3, 6),
          (true, "a", uri2, 3, 6),
          (true, "b", uri4, 8, 16),
          (false, "b", uri3, 6, 12),
          (false, "a", uri2, 4, 7),
          (false, "a", uri1, 1, 2),
          (true, "b", uri3, 5, 9),
          (false, "a", uri2, 4, 8),
          (true, "b", uri3, 6, 11),
          (true, "b", uri4, 7, 14),
          (true, "b", uri3, 5, 10),
          (false, "b", uri3, 5, 10),
          (false, "a", uri2, 3, 5),
          (true, "a", uri1, 2, 3),
        ).transact

        // Ascending
        _ <- Ns.boolean.sort(1).string.sort(2).uri.sort(3).i.sort(4).int.sort(5).query.get.map(_ ==> List(
          (false, "a", uri1, 1, 1),
          (false, "a", uri1, 1, 2),
          (false, "a", uri1, 2, 3),
          (false, "a", uri1, 2, 4),
          (false, "a", uri2, 3, 5),
          (false, "a", uri2, 3, 6),
          (false, "a", uri2, 4, 7),
          (false, "a", uri2, 4, 8),
          (false, "b", uri3, 5, 9),
          (false, "b", uri3, 5, 10),
          (false, "b", uri3, 6, 11),
          (false, "b", uri3, 6, 12),
          (false, "b", uri4, 7, 13),
          (false, "b", uri4, 7, 14),
          (false, "b", uri4, 8, 15),
          (false, "b", uri4, 8, 16),
          (true, "a", uri1, 1, 1),
          (true, "a", uri1, 1, 2),
          (true, "a", uri1, 2, 3),
          (true, "a", uri1, 2, 4),
          (true, "a", uri2, 3, 5),
          (true, "a", uri2, 3, 6),
          (true, "a", uri2, 4, 7),
          (true, "a", uri2, 4, 8),
          (true, "b", uri3, 5, 9),
          (true, "b", uri3, 5, 10),
          (true, "b", uri3, 6, 11),
          (true, "b", uri3, 6, 12),
          (true, "b", uri4, 7, 13),
          (true, "b", uri4, 7, 14),
          (true, "b", uri4, 8, 15),
          (true, "b", uri4, 8, 16),
        ))

        // Descending
        _ <- Ns.boolean.sort(-1).string.sort(-2).uri.sort(-3).i.sort(-4).int.sort(-5).query.get.map(_ ==> List(
          (true, "b", uri4, 8, 16),
          (true, "b", uri4, 8, 15),
          (true, "b", uri4, 7, 14),
          (true, "b", uri4, 7, 13),
          (true, "b", uri3, 6, 12),
          (true, "b", uri3, 6, 11),
          (true, "b", uri3, 5, 10),
          (true, "b", uri3, 5, 9),
          (true, "a", uri2, 4, 8),
          (true, "a", uri2, 4, 7),
          (true, "a", uri2, 3, 6),
          (true, "a", uri2, 3, 5),
          (true, "a", uri1, 2, 4),
          (true, "a", uri1, 2, 3),
          (true, "a", uri1, 1, 2),
          (true, "a", uri1, 1, 1),
          (false, "b", uri4, 8, 16),
          (false, "b", uri4, 8, 15),
          (false, "b", uri4, 7, 14),
          (false, "b", uri4, 7, 13),
          (false, "b", uri3, 6, 12),
          (false, "b", uri3, 6, 11),
          (false, "b", uri3, 5, 10),
          (false, "b", uri3, 5, 9),
          (false, "a", uri2, 4, 8),
          (false, "a", uri2, 4, 7),
          (false, "a", uri2, 3, 6),
          (false, "a", uri2, 3, 5),
          (false, "a", uri1, 2, 4),
          (false, "a", uri1, 2, 3),
          (false, "a", uri1, 1, 2),
          (false, "a", uri1, 1, 1),
        ))

        // Mixed order
        _ <- Ns.boolean.sort(3).string.sort(-1).uri.sort(-4).i.sort(2).int.sort(-5).query.get.map(_ ==> List(
          (false, "b", uri3, 5, 10),
          (false, "b", uri3, 5, 9),
          (true, "b", uri3, 5, 10),
          (true, "b", uri3, 5, 9),

          (false, "b", uri3, 6, 12),
          (false, "b", uri3, 6, 11),
          (true, "b", uri3, 6, 12),
          (true, "b", uri3, 6, 11),

          (false, "b", uri4, 7, 14),
          (false, "b", uri4, 7, 13),
          (true, "b", uri4, 7, 14),
          (true, "b", uri4, 7, 13),

          (false, "b", uri4, 8, 16),
          (false, "b", uri4, 8, 15),
          (true, "b", uri4, 8, 16),
          (true, "b", uri4, 8, 15),


          (false, "a", uri1, 1, 2),
          (false, "a", uri1, 1, 1),
          (true, "a", uri1, 1, 2),
          (true, "a", uri1, 1, 1),

          (false, "a", uri1, 2, 4),
          (false, "a", uri1, 2, 3),
          (true, "a", uri1, 2, 4),
          (true, "a", uri1, 2, 3),

          (false, "a", uri2, 3, 6),
          (false, "a", uri2, 3, 5),
          (true, "a", uri2, 3, 6),
          (true, "a", uri2, 3, 5),

          (false, "a", uri2, 4, 8),
          (false, "a", uri2, 4, 7),
          (true, "a", uri2, 4, 8),
          (true, "a", uri2, 4, 7),
        ))
      } yield ()
    }


    "Correct use of sort markers" - types { implicit conn =>
      for {
        _ <- Ns.string.sort(1).int.sort(1).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Sort index 1 should be present and additional indexes continuously " +
              "increase (in any order). Found non-unique sort index(es): 1, 1"
          }

        _ <- Ns.string.sort(-1).int.sort(-2).long.sort(-2).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Sort index 1 should be present and additional indexes continuously " +
              "increase (in any order). Found non-unique sort index(es): 1, 2, 2"
          }

        _ <- Ns.string.sort(1).int.sort(3).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Sort index 1 should be present and additional indexes continuously " +
              "increase (in any order). Found non-unique sort index(es): 1, 3"
          }

        _ <- Ns.string.sort(-3).int.sort(-1).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Sort index 1 should be present and additional indexes continuously " +
              "increase (in any order). Found non-unique sort index(es): 1, 3"
          }

        _ <- Ns.string.sort(-2).int.sort(3).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Sort index 1 should be present and additional indexes continuously " +
              "increase (in any order). Found non-unique sort index(es): 2, 3"
          }
      } yield ()
    }
  }
}
