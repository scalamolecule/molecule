package molecule.datalog.datomic.test.time

import java.util.Date
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions


object GetSince extends DatomicTestSuite {

  override lazy val tests = Tests {

    "save" - types { implicit conn =>
      for {
        tx1 <- Ns.int(1).save.transact
        tx2 <- Ns.int(2).save.transact
        tx3 <- Ns.int(3).save.transact

        // Current state same as of tx3
        _ <- Ns.int.query.get.map(_ ==> List(1, 2, 3))

        _ <- Ns.int.query.since(tx1).get.map(_ ==> List(2, 3))
        _ <- Ns.int.query.since(tx2).get.map(_ ==> List(3))
        _ <- Ns.int.query.since(tx3).get.map(_ ==> List())
      } yield ()
    }


    "update" - types { implicit conn =>
      for {
        tx1 <- Ns.int(1).save.transact
        e = tx1.eid
        _ <- Ns.int.query.since(tx1).get.map(_ ==> List())

        tx2 <- Ns(e).int(2).update.transact
        _ <- Ns.int.query.since(tx1).get.map(_ ==> List(2))
        _ <- Ns.int.query.since(tx2).get.map(_ ==> List())

        tx3 <- Ns(e).int(3).update.transact
        _ <- Ns.int.query.since(tx1).get.map(_ ==> List(3))
        _ <- Ns.int.query.since(tx2).get.map(_ ==> List(3))
        _ <- Ns.int.query.since(tx3).get.map(_ ==> List())

        // Current state same as of tx3
        _ <- Ns.int.query.get.map(_ ==> List(3))
      } yield ()
    }


    "delete" - types { implicit conn =>
      for {
        tx1 <- Ns.int(1).save.transact
        _ <- Ns.int.query.since(tx1).get.map(_ ==> List())

        tx2 <- Ns.int(2).save.transact
        _ <- Ns.int.query.since(tx1).get.map(_ ==> List(2))
        _ <- Ns.int.query.since(tx2).get.map(_ ==> List())

        tx3 <- Ns(tx2.eid).delete.transact
        _ <- Ns.int.query.since(tx1).get.map(_ ==> List())
        _ <- Ns.int.query.since(tx2).get.map(_ ==> List())
        _ <- Ns.int.query.since(tx3).get.map(_ ==> List())

        // Current state same as of tx3
        _ <- Ns.int.query.get.map(_ ==> List(1))
      } yield ()
    }


    "Using date" - types { implicit conn =>
      for {
        _ <- Ns.int(1).save.transact
        d1 = new Date()
        _ <- delay(2)(()) // Ensure dates are not within the same ms
        _ <- Ns.int(2).save.transact
        d2 = new Date()
        _ <- delay(2)(())
        _ <- Ns.int(3).save.transact
        d3 = new Date()

        _ <- Ns.int.query.since(d1).get.map(_ ==> List(2, 3))
        _ <- Ns.int.query.since(d2).get.map(_ ==> List(3))
        _ <- Ns.int.query.since(d3).get.map(_ ==> List())
      } yield ()
    }

    "Using transaction entity id" - types { implicit conn =>
      for {
        tx1 <- Ns.int(1).save.transact
        tx2 <- Ns.int(2).save.transact
        tx3 <- Ns.int(3).save.transact

        _ <- Ns.int.query.since(tx1.tx).get.map(_ ==> List(2, 3))
        _ <- Ns.int.query.since(tx2.tx).get.map(_ ==> List(3))
        _ <- Ns.int.query.since(tx3.tx).get.map(_ ==> List())
      } yield ()
    }
  }
}
