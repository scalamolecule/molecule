package molecule.db.compliance.test.pagination.offset

import molecule.base.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

// Offset with sql dbs is ineffective and should be avoided in favour of cursor pagination.
// https://medium.com/swlh/sql-pagination-you-are-probably-doing-it-wrong-d0f2719cc166

case class OffsetSemantics(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Different limit/offset sign" - types {
    for {
      _ <- Entity.int.a1.query.limit(20).offset(-10).get
        .map(_ ==> "Unexpected success").recover { case ModelError(msg) =>
          msg ==> "Limit and offset should both be positive or negative."
        }
    } yield ()
  }


  // General problems with offset pagination (for any db system):

  "Re-seen data" - types {
    for {
      _ <- Entity.int.insert(1, 3, 5).transact

      _ <- Entity.int.a1.query.limit(2).get.map(_ ==> List(1, 3))

      // Data added before next page is fetched
      _ <- Entity.int(2).save.transact

      // 3 is shown again!
      _ <- Entity.int.a1.query.limit(2).offset(2).get.map(_._1 ==> List(3, 5))
    } yield ()
  }

  "Skipped data" - types {
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
