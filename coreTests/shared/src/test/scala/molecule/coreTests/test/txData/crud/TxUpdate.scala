package molecule.coreTests.test.txData.crud

import molecule.base.error._
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait TxUpdate extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>

  override lazy val tests = Tests {

    "Update base and tx meta data" - types { implicit conn =>
      for {
        eid <- Ns.int.Tx(Other.s_("meta")).insert(1).transact.map(_.eid)
        _ <- Ns.int.Tx(Other.s).query.get.map(_ ==> List((1, "meta")))

        _ <- Ns(eid).int(2).Tx(Other.s("meta2")).update.transact
        _ <- Ns.int.Tx(Other.s).query.get.map(_ ==> List((2, "meta2")))
      } yield ()
    }


    "Update base data only" - types { implicit conn =>
      for {
        // Initial entity with tx meta data
        txReport <- Ns.int(1).Tx(Other.s("meta")).save.transact
        eid = txReport.eid
        tx = txReport.tx
        _ <- Ns.int.Tx(Other.s).query.get.map(_ ==> List((1, "meta")))

        // Update base entity data only
        _ <- Ns(eid).int(2).update.transact
        _ <- Ns.int.query.get.map(_ ==> List(2))

        // Note that the initial tx meta data is still tied to the first transaction entity
        _ <- Other.eid(tx).s.query.get.map(_ ==> List((tx, "meta")))

        // OBS: The old tx meta data is no longer associated with the updated base entity.
        // transaction meta data is tied only to the transaction entity where it was created!
        _ <- Ns.int.Tx(Other.s).query.get.map(_ ==> Nil)

        // If we instead update both the base value(s) and tx meta data we'll get the expected result
        _ <- Ns(eid).int(3).Tx(Other.s("meta3")).update.transact
        _ <- Ns.int.Tx(Other.s).query.get.map(_ ==> List((3, "meta3")))
      } yield ()
    }


    "Update tx meta data only" - types { implicit conn =>
      for {
        // Initial entity with tx meta data
        txReport <- Ns.int(1).Tx(Other.s("meta")).save.transact
        eid = txReport.eid
        tx = txReport.tx
        _ <- Ns.int.Tx(Other.s).query.get.map(_ ==> List((1, "meta")))

        // Update tx meta data only by using the tx id
        _ <- Other(tx).s("meta2").update.transact
        _ <- Ns.int.Tx(Other.s).query.get.map(_.head ==> (1, "meta2"))

        // Can't apply base eid without base attribute values too
        _ <- Ns(eid).Tx(Other.s("meta3")).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Please apply the tx id to the namespace of tx meta data to be updated."
        }
      } yield ()
    }
  }
}