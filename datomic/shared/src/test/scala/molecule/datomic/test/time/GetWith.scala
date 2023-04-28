package molecule.datomic.test.time

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.action.DatomicSave
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions


object GetWith extends DatomicTestSuite {

  override lazy val tests = Tests {

    "save" - types { implicit conn =>
      for {
        _ <- Ns.int(1).save.transact

        // Current data
        _ <- Ns.int.query.get.map(_ ==> List(1))

        // Test temporary actions
//        _ <- Ns.int.query.widh(
//          Ns.int(2).save,
//          Ns.int(3).save
//        ).get.map(_ ==> List(1, 2, 3))
//
//        // Current data is unchanged
//        _ <- Ns.int.query.get.map(_ ==> List(1))
//
        // Test temporary actions
        _ <- Ns.int.query.widh(
          Ns.int.insert.apply(2, 3)
        ).get.map(_ ==> List(1, 2, 3))

        // Current data is unchanged
        _ <- Ns.int.query.get.map(_ ==> List(1))
      } yield ()
    }


    "insert" - types { implicit conn =>
      for {
        _ <- Ns.int(1).save.transact

        // Current data
        _ <- Ns.int.query.get.map(_ ==> List(1))

        // Test temporary actions
        _ <- Ns.int.query.widh(
          Ns.int(2).save,
          Ns.int(3).save
        ).get.map(_ ==> List(1, 2, 3))

        // Current data is unchanged
        _ <- Ns.int.query.get.map(_ ==> List(1))

        // Test temporary actions
        _ <- Ns.int.query.widh(
          Ns.int(2).save,
          Ns.int(3).save
        ).get.map(_ ==> List(1, 2, 3))

        // Current data is unchanged
        _ <- Ns.int.query.get.map(_ ==> List(1))
      } yield ()
    }


    "update" - types { implicit conn =>
      for {
        tx1 <- Ns.int(1).save.transact
        e = tx1.eid
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
        tx3 <- Ns(tx2.eid).delete.transact

        _ <- Ns.int.query.asOf(tx1).get.map(_ ==> List(1))
        _ <- Ns.int.query.asOf(tx2).get.map(_ ==> List(1, 2))
        _ <- Ns.int.query.asOf(tx3).get.map(_ ==> List(1))

        // Current state same as of tx3
        _ <- Ns.int.query.get.map(_ ==> List(1))
      } yield ()
    }
  }
}
