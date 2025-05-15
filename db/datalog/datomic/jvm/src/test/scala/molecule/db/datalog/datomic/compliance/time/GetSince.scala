package molecule.db.datalog.datomic.compliance.time

import java.util.Date
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.{Test, TestUtils}
import molecule.db.core.util.Executor.*
import molecule.db.datalog.datomic.async.*
import molecule.db.datalog.datomic.setup.DbProviders_datomic


class Test_GetSince extends Test with DbProviders_datomic with TestUtils {

  "save" - types { implicit conn =>
    for {
      tx1 <- Entity.int(1).save.transact
      tx2 <- Entity.int(2).save.transact
      tx3 <- Entity.int(3).save.transact

      // Current state same as of tx3
      _ <- Entity.int.query.get.map(_ ==> List(1, 2, 3))

      _ <- Entity.int.query.since(tx1).get.map(_ ==> List(2, 3))
      _ <- Entity.int.query.since(tx2).get.map(_ ==> List(3))
      _ <- Entity.int.query.since(tx3).get.map(_ ==> List())
    } yield ()
  }


  "update" - types { implicit conn =>
    for {
      tx1 <- Entity.int(1).save.transact
      e = tx1.id
      _ <- Entity.int.query.since(tx1).get.map(_ ==> List())

      tx2 <- Entity(e).int(2).update.transact
      _ <- Entity.int.query.since(tx1).get.map(_ ==> List(2))
      _ <- Entity.int.query.since(tx2).get.map(_ ==> List())

      tx3 <- Entity(e).int(3).update.transact
      _ <- Entity.int.query.since(tx1).get.map(_ ==> List(3))
      _ <- Entity.int.query.since(tx2).get.map(_ ==> List(3))
      _ <- Entity.int.query.since(tx3).get.map(_ ==> List())

      // Current state same as of tx3
      _ <- Entity.int.query.get.map(_ ==> List(3))
    } yield ()
  }


  "delete" - types { implicit conn =>
    for {
      tx1 <- Entity.int(1).save.transact
      _ <- Entity.int.query.since(tx1).get.map(_ ==> List())

      tx2 <- Entity.int(2).save.transact
      _ <- Entity.int.query.since(tx1).get.map(_ ==> List(2))
      _ <- Entity.int.query.since(tx2).get.map(_ ==> List())

      tx3 <- Entity(tx2.id).delete.transact
      _ <- Entity.int.query.since(tx1).get.map(_ ==> List())
      _ <- Entity.int.query.since(tx2).get.map(_ ==> List())
      _ <- Entity.int.query.since(tx3).get.map(_ ==> List())

      // Current state same as of tx3
      _ <- Entity.int.query.get.map(_ ==> List(1))
    } yield ()
  }


  "Using date" - types { implicit conn =>
    for {
      _ <- Entity.int(1).save.transact
      d1 = new Date()
      _ <- delay(2) // Ensure dateSet are not within the same ms
      _ <- Entity.int(2).save.transact
      d2 = new Date()
      _ <- delay(2)
      _ <- Entity.int(3).save.transact
      d3 = new Date()

      _ <- Entity.int.query.since(d1).get.map(_ ==> List(2, 3))
      _ <- Entity.int.query.since(d2).get.map(_ ==> List(3))
      _ <- Entity.int.query.since(d3).get.map(_ ==> List())
    } yield ()
  }

  "Using transaction entity id" - types { implicit conn =>
    for {
      tx1 <- Entity.int(1).save.transact
      tx2 <- Entity.int(2).save.transact
      tx3 <- Entity.int(3).save.transact

      _ <- Entity.int.query.since(tx1.tx).get.map(_ ==> List(2, 3))
      _ <- Entity.int.query.since(tx2.tx).get.map(_ ==> List(3))
      _ <- Entity.int.query.since(tx3.tx).get.map(_ ==> List())
    } yield ()
  }
}