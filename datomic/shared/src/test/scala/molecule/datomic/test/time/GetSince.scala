package molecule.datomic.test.time

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.test.time.GetAsOf.types
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
  }
}
