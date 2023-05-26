package molecule.coreTests.test.txData.crud

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._


trait TxDelete extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>

  override lazy val tests = Tests {

    "tx data basics" - types { implicit conn =>
      for {
        // Create entity with tx data.
        txReport <- Ns.int(1).Tx(Other.i_(7)).save.transact

        // A base entity id is created.
        List(eid) = txReport.eids
        _ <- Ns.eid(eid).int.query.get.map(_.head ==> (eid, 1))

        // The tx data is tied to the transaction id
        tx = txReport.tx
        _ <- Other.eid(tx).i.query.get.map(_.head ==> (tx, 7))

        // Since the base entity is tied to the transaction, we can query entity with tx data
        _ <- Ns.int.Tx(Other.i).query.get.map(_.head ==> (1, 7))

        // Query entity without its tx data
        _ <- Ns.int.query.get.map(_.head ==> 1)

        // Query the tx data itself
        _ <- Other.i.query.get.map(_.head ==> 7)

        // Now delete the base entity
        _ <- Ns(eid).delete.transact

        // The entity is gone
        _ <- Ns.int.query.get.map(_ ==> Nil)
        _ <- Ns.int.Tx(Other.i).query.get.map(_ ==> Nil)

        // The tx data itself is not deleted though since it's tied
        // to the initial transaction entity that still exists.
        _ <- Other.i.query.get.map(_.head ==> 7)
        _ <- Other.eid(tx).i.query.get.map(_.head ==> (tx, 7))

        // We cannot delete a transaction entity
        _ <- Other(tx).delete.transact
          .map(_ ==> "Unexpected success").recover { case err =>
          err.getMessage ==> "Can't delete transaction id."
        }

        // If we really want to delete the tx data, we'll need to delete the involved
        // attribute values by applying Nothing and then update
        _ <- Other(tx).i().update.transact

        // Now the tx data is gone too
        _ <- Other.i.query.get.map(_ ==> Nil)
      } yield ()
    }

  }
}