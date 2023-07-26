package molecule.coreTests.test.filter

import molecule.base.error.ModelError
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.collection.immutable.Seq

trait FilterOne_tx extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val tx0 = 42L
      for {
        tx1 <- Ns.i.insert(1).transact.map(_.tx)
        tx2 <- Ns.i.insert(2).transact.map(_.tx)
        tx3 <- Ns.i.insert(3).transact.map(_.tx)
        a = (1, tx1)
        b = (2, tx2)
        c = (3, tx3)

        // Find all values and transaction ids
        _ <- Ns.i.tx.query.get.map(_ ==> List(a, b, c))

        // Find value(s) matching
        _ <- Ns.i.a1.tx(tx0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.tx(tx1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.tx(Seq(tx0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.tx(Seq(tx1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args
        _ <- Ns.i.a1.tx(tx1, tx2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.tx(tx1, tx0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.tx(Seq(tx1, tx2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.tx(Seq(tx1, tx0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no txs
        _ <- Ns.i.a1.tx(Seq.empty[Long]).query.get.map(_ ==> List())

        // Find txs not matching
        _ <- Ns.i.a1.tx.not(tx0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.tx.not(tx1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.tx.not(tx2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.tx.not(tx3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.tx.not(Seq(tx0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.tx.not(Seq(tx1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.tx.not(Seq(tx2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.tx.not(Seq(tx3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.tx.not(tx0, tx1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.tx.not(tx1, tx2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.tx.not(tx2, tx3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.tx.not(Seq(tx0, tx1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.tx.not(Seq(tx1, tx2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.tx.not(Seq(tx2, tx3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all txs
        _ <- Ns.i.a1.tx.not(Seq.empty[Long]).query.get.map(_ ==> List(a, b, c))

        // Find txs in range
        _ <- Ns.i.a1.tx.<(tx2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.tx.>(tx2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.tx.<=(tx2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.tx.>=(tx2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b, c) = (1, 2, 3)
      val tx0       = 42L
      for {
        tx1 <- Ns.i.insert(1).transact.map(_.tx)
        tx2 <- Ns.i.insert(2).transact.map(_.tx)
        tx3 <- Ns.i.insert(3).transact.map(_.tx)

        // Since all transaction entities have an tx,
        // asking for a tacit tx makes no difference
        _ <- Ns.i.a1.tx_.query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.query.get.map(_ ==> List(a, b, c))

        _ <- Ns.i.a1.tx_().query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Since all transaction entities have an id, " +
              "asking for those without is not implemented."
          }

        // Match txs without returning them
        _ <- Ns.i.a1.tx_(tx0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.tx_(tx1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.tx_(Seq(tx0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.tx_(Seq(tx1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args
        _ <- Ns.i.a1.tx_(tx1, tx2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.tx_(tx1, tx0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.tx_(Seq(tx1, tx2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.tx_(Seq(tx1, tx0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no txs
        _ <- Ns.i.a1.tx_(Seq.empty[Long]).query.get.map(_ ==> List())

        // Match non-matching txs without returning them
        _ <- Ns.i.a1.tx_.not(tx0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.tx_.not(tx1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.tx_.not(tx2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.tx_.not(tx3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.tx_.not(Seq(tx0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.tx_.not(Seq(tx1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.tx_.not(Seq(tx2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.tx_.not(Seq(tx3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.tx_.not(tx0, tx1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.tx_.not(tx1, tx2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.tx_.not(tx2, tx3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.tx_.not(Seq(tx0, tx1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.tx_.not(Seq(tx1, tx2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.tx_.not(Seq(tx2, tx3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all txs (non-null)
        _ <- Ns.i.a1.tx_.not(Seq.empty[Long]).query.get.map(_ ==> List(a, b, c))

        // Match value ranges without returning them
        _ <- Ns.i.a1.tx_.<(tx2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.tx_.>(tx2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.tx_.<=(tx2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.tx_.>=(tx2).query.get.map(_ ==> List(b, c))
      } yield ()
    }
  }
}
