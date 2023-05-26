package molecule.coreTests.test.pagination.offset

import molecule.base.error.ModelError
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.async._
import molecule.coreTests.setup.CoreTestSuite
import molecule.core.spi.SpiAsync
import utest._

// Offset with sql dbs is ineffective and should be avoided in favour of cursor pagination.
// https://medium.com/swlh/sql-pagination-you-are-probably-doing-it-wrong-d0f2719cc166

trait OffsetSemantics extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>

  override lazy val tests = Tests {

    "Different limit/offset sign" - types { implicit conn =>
      for {
        //        _ <- Ns.int.a1.query.limit(0).get
        //          .map(_ ==> "Unexpected success")
        //          .recover { case ExecutionError(msg, _) =>
        //            msg ==> "Limit cannot be 0. Please use a positive number to limit next rows, " +
        //              "or a negative number to limit previous rows."
        //          }

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
        eids <- Ns.int.insert(1, 2, 3, 4).transact.map(_.eids)

        _ <- Ns.int.a1.query.limit(2).get.map(_ ==> List(1, 2))

        // First row (1) retracted before next page is fetched
        _ <- Ns(eids.head).delete.transact

        // 3 is never shown!
        _ <- Ns.int.a1.query.limit(2).offset(2).get.map(_._1 ==> List(4))
      } yield ()
    }
  }
}
