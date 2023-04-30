package molecule.datomic.test.time

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
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
        _ <- Ns.int.query.widh(
          Ns.int(2).save,
          Ns.int(3).save
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
          Ns.int.insert(2, 3),
          Ns.int.insert(4),
        ).get.map(_ ==> List(1, 2, 3, 4))

        // Current data is unchanged
        _ <- Ns.int.query.get.map(_ ==> List(1))
      } yield ()
    }


    "update" - types { implicit conn =>
      for {
        e <- Ns.int(1).save.transact.map(_.eid)

        // Current data
        _ <- Ns.int.query.get.map(_ ==> List(1))

        // Test temporary actions
        _ <- Ns.int.query.widh(
          Ns(e).int(2).update,
        ).get.map(_ ==> List(2))

        // Current data is unchanged
        _ <- Ns.int.query.get.map(_ ==> List(1))
      } yield ()
    }


    "delete" - types { implicit conn =>
      for {
        e1 <- Ns.int.insert(1, 2).transact.map(_.eid)

        // Current data
        _ <- Ns.int.query.get.map(_ ==> List(1, 2))

        // Test temporary actions
        _ <- Ns.int.query.widh(
          Ns(e1).delete,
        ).get.map(_ ==> List(2))

        // Current data is unchanged
        _ <- Ns.int.query.get.map(_ ==> List(1, 2))
      } yield ()
    }


    "Mixing" - types { implicit conn =>
      for {
        List(e1, e2) <- Ns.int.insert(1, 2).transact.map(_.eids)

        // Current data
        _ <- Ns.int.query.get.map(_ ==> List(1, 2))

        // Test temporary actions
        _ <- Ns.int.a1.query.widh(
          Ns.int(3).save,
          Ns(e1).int(0).update,
          Ns(e2).delete,
        ).get.map(_ ==> List(0, 3))

        // Current data is unchanged
        _ <- Ns.int.query.get.map(_ ==> List(1, 2))
      } yield ()
    }
  }
}
