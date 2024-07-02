package molecule.datalog.datomic.compliance.time

import java.util.Date
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.TestSuite_datomic
import utest._
import scala.language.implicitConversions


object GetAsOf extends TestSuite_datomic {

  override lazy val tests = Tests {

    "save" - types { implicit conn =>
      for {
        tx1 <- Ns.int(1).save.transact
        tx2 <- Ns.int(2).save.transact
        tx3 <- Ns.int(3).save.transact

        _ <- Ns.int.query.asOf(tx1).get.map(_ ==> List(1))
        _ <- Ns.int.query.asOf(tx2).get.map(_ ==> List(1, 2))
        _ <- Ns.int.query.asOf(tx3).get.map(_ ==> List(1, 2, 3))

        // Current state same as of tx3
        _ <- Ns.int.query.get.map(_ ==> List(1, 2, 3))
      } yield ()
    }


    "update" - types { implicit conn =>
      for {
        tx1 <- Ns.int(1).save.transact
        e = tx1.id
        tx2 <- Ns(e).int(2).update.transact
        tx3 <- Ns(e).int(3).update.transact

        _ <- Ns.int.query.asOf(tx1).get.map(_ ==> List(1))
        _ <- Ns.int.query.asOf(tx2).get.map(_ ==> List(2))
        _ <- Ns.int.query.asOf(tx3).get.map(_ ==> List(3))

        // Current state same as of tx3
        _ <- Ns.int.query.get.map(_ ==> List(3))
      } yield ()
    }


    "delete" - types { implicit conn =>
      for {
        tx1 <- Ns.int(1).save.transact
        tx2 <- Ns.int(2).save.transact
        tx3 <- Ns(tx2.id).delete.transact

        _ <- Ns.int.query.asOf(tx1).get.map(_ ==> List(1))
        _ <- Ns.int.query.asOf(tx2).get.map(_ ==> List(1, 2))
        _ <- Ns.int.query.asOf(tx3).get.map(_ ==> List(1))

        // Current state same as of tx3
        _ <- Ns.int.query.get.map(_ ==> List(1))
      } yield ()
    }


    "Using date" - types { implicit conn =>
      for {
        _ <- Ns.int(1).save.transact
        d1 = new Date()
        _ <- delay(2)(()) // Ensure dateSet are not within the same ms
        _ <- Ns.int(2).save.transact
        d2 = new Date()
        _ <- delay(2)(())
        _ <- Ns.int(3).save.transact
        d3 = new Date()

        _ <- Ns.int.query.asOf(d1).get.map(_ ==> List(1))
        _ <- Ns.int.query.asOf(d2).get.map(_ ==> List(1, 2))
        _ <- Ns.int.query.asOf(d3).get.map(_ ==> List(1, 2, 3))
      } yield ()
    }

    "Using transaction entity id" - types { implicit conn =>
      for {
        tx1 <- Ns.int(1).save.transact
        tx2 <- Ns.int(2).save.transact
        tx3 <- Ns.int(3).save.transact

        _ <- Ns.int.query.asOf(tx1.tx).get.map(_ ==> List(1))
        _ <- Ns.int.query.asOf(tx2.tx).get.map(_ ==> List(1, 2))
        _ <- Ns.int.query.asOf(tx3.tx).get.map(_ ==> List(1, 2, 3))
      } yield ()
    }
  }
}
