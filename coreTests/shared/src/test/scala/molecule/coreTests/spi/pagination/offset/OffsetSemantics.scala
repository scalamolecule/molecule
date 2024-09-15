package molecule.coreTests.spi.pagination.offset

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

// Offset with sql dbs is ineffective and should be avoided in favour of cursor pagination.
// https://medium.com/swlh/sql-pagination-you-are-probably-doing-it-wrong-d0f2719cc166

trait OffsetSemantics extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Different limit/offset sign" - types { implicit conn =>
      for {
        _ <- Ns.int.a1.query.limit(20).offset(-10).get
          .map(_ ==> "Unexpected success").recover { case ModelError(msg) =>
            msg ==> "Limit and offset should both be positive or negative."
          }
      } yield ()
    }


    // General problems with offset pagination (for any db system):

    "Re-seen data" - types { implicit conn =>
      for {
        _ <- Ns.int.insert(1, 3, 5).transact

        _ <- Ns.int.a1.query.limit(2).get.map(_ ==> List(1, 3))

        // Data added before next page is fetched
        _ <- Ns.int(2).save.transact

        // 3 is shown again!
        _ <- Ns.int.a1.query.limit(2).offset(2).get.map(_._1 ==> List(3, 5))
      } yield ()
    }

    "Skipped data" - types { implicit conn =>
      for {
        ids <- Ns.int.insert(1, 2, 3, 4).transact.map(_.ids)

        _ <- Ns.int.a1.query.limit(2).get.map(_ ==> List(1, 2))

        // First row (1) retracted before next page is fetched
        _ <- Ns(ids.head).delete.transact

        // 3 is never shown!
        _ <- Ns.int.a1.query.limit(2).offset(2).get.map(_._1 ==> List(4))
      } yield ()
    }
  }
}
