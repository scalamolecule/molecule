package molecule.coreTests.spi.crud.update.one

import molecule.base.error._
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateOne_filter extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "Update multiple values" - types { implicit conn =>
      for {
        List(a, b, c) <- Ns.i.int_?.insert(
          (1, None),
          (1, Some(1)),
          (2, Some(2)),
        ).transact.map(_.ids)

        // Upsert all entities where non-unique attribute i is 1
        _ <- Ns.i_(1).int(3).update.transact

        // 2 matching entities updated
        _ <- Ns.id.a1.i.int_?.query.get.map(_ ==> List(
          (a, 1, Some(3)), // 3 inserted
          (b, 1, Some(3)), // 1 updated to 3
          (c, 2, Some(2)),
        ))
      } yield ()
    }


    "Update filter value itself" - types { implicit conn =>
      for {
        _ <- Ns.i.insert(1).transact
        _ <- Ns.i.query.get.map(_ ==> List(1))

        // Filter by tacit i_(1), update to new mandatory i(2)
        _ <- Ns.i_(1).i(2).update.transact
        _ <- Ns.i.query.get.map(_ ==> List(2))
      } yield ()
    }


    "Expression" - types { implicit conn =>
      for {
        _ <- Ns.i.int.insert(
          (1, 1),
          (2, 2),
          (3, 3),
        ).transact.map(_.ids)

        // Update all entities where non-unique attribute i <= 2
        _ <- Ns.i_.<=(2).int(4).update.transact

        _ <- Ns.i.a1.int.query.get.map(_ ==> List(
          (1, 4), // updated
          (2, 4), // updated
          (3, 3),
        ))
      } yield ()
    }


    "Multiple values" - types { implicit conn =>
      for {
        _ <- Ns.i_(1).int(1, 2).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can only update one value for attribute `Ns.int`. Found: 1, 2"
          }
      } yield ()
    }
  }
}
