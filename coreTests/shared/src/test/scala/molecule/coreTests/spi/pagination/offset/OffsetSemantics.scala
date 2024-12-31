package molecule.coreTests.spi.pagination.offset

import molecule.base.error.ModelError
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

// Offset with sql dbs is ineffective and should be avoided in favour of cursor pagination.
// https://medium.com/swlh/sql-pagination-you-are-probably-doing-it-wrong-d0f2719cc166

case class OffsetSemantics(
  suite: MUnitSuite,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "Different limit/offset sign" - types { implicit conn =>
    for {
      _ <- Entity.int.a1.query.limit(20).offset(-10).get
        .map(_ ==> "Unexpected success").recover { case ModelError(msg) =>
          msg ==> "Limit and offset should both be positive or negative."
        }
    } yield ()
  }


  // General problems with offset pagination (for any db system):

  "Re-seen data" - types { implicit conn =>
    for {
      _ <- Entity.int.insert(1, 3, 5).transact

      _ <- Entity.int.a1.query.limit(2).get.map(_ ==> List(1, 3))

      // Data added before next page is fetched
      _ <- Entity.int(2).save.transact

      // 3 is shown again!
      _ <- Entity.int.a1.query.limit(2).offset(2).get.map(_._1 ==> List(3, 5))
    } yield ()
  }

  "Skipped data" - types { implicit conn =>
    for {
      ids <- Entity.int.insert(1, 2, 3, 4).transact.map(_.ids)

      _ <- Entity.int.a1.query.limit(2).get.map(_ ==> List(1, 2))

      // First row (1) retracted before next page is fetched
      _ <- Entity(ids.head).delete.transact

      // 3 is never shown!
      _ <- Entity.int.a1.query.limit(2).offset(2).get.map(_._1 ==> List(4))
    } yield ()
  }
}
