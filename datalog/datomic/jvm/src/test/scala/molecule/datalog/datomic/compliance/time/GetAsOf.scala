package molecule.datalog.datomic.compliance.time

import java.util.Date
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup.{MUnitSuite, TestUtils}
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DbProviders_datomic
import scala.language.implicitConversions


class Test_GetAsOf extends MUnitSuite with DbProviders_datomic with TestUtils {

  "save" - types { implicit conn =>
    for {
      tx1 <- Entity.int(1).save.transact
      tx2 <- Entity.int(2).save.transact
      tx3 <- Entity.int(3).save.transact

      _ <- Entity.int.query.asOf(tx1).get.map(_ ==> List(1))
      _ <- Entity.int.query.asOf(tx2).get.map(_ ==> List(1, 2))
      _ <- Entity.int.query.asOf(tx3).get.map(_ ==> List(1, 2, 3))

      // Current state same as of tx3
      _ <- Entity.int.query.get.map(_ ==> List(1, 2, 3))
    } yield ()
  }


  "update" - types { implicit conn =>
    for {
      tx1 <- Entity.int(1).save.transact
      e = tx1.id
      tx2 <- Entity(e).int(2).update.transact
      tx3 <- Entity(e).int(3).update.transact

      _ <- Entity.int.query.asOf(tx1).get.map(_ ==> List(1))
      _ <- Entity.int.query.asOf(tx2).get.map(_ ==> List(2))
      _ <- Entity.int.query.asOf(tx3).get.map(_ ==> List(3))

      // Current state same as of tx3
      _ <- Entity.int.query.get.map(_ ==> List(3))
    } yield ()
  }


  "delete" - types { implicit conn =>
    for {
      tx1 <- Entity.int(1).save.transact
      tx2 <- Entity.int(2).save.transact
      tx3 <- Entity(tx2.id).delete.transact

      _ <- Entity.int.query.asOf(tx1).get.map(_ ==> List(1))
      _ <- Entity.int.query.asOf(tx2).get.map(_ ==> List(1, 2))
      _ <- Entity.int.query.asOf(tx3).get.map(_ ==> List(1))

      // Current state same as of tx3
      _ <- Entity.int.query.get.map(_ ==> List(1))
    } yield ()
  }


  "Using date" - types { implicit conn =>
    for {
      _ <- Entity.int(1).save.transact
      d1 = new Date()
      _ <- delay(2)(()) // Ensure dateSet are not within the same ms
      _ <- Entity.int(2).save.transact
      d2 = new Date()
      _ <- delay(2)(())
      _ <- Entity.int(3).save.transact
      d3 = new Date()

      _ <- Entity.int.query.asOf(d1).get.map(_ ==> List(1))
      _ <- Entity.int.query.asOf(d2).get.map(_ ==> List(1, 2))
      _ <- Entity.int.query.asOf(d3).get.map(_ ==> List(1, 2, 3))
    } yield ()
  }

  "Using transaction entity id" - types { implicit conn =>
    for {
      tx1 <- Entity.int(1).save.transact
      tx2 <- Entity.int(2).save.transact
      tx3 <- Entity.int(3).save.transact

      _ <- Entity.int.query.asOf(tx1.tx).get.map(_ ==> List(1))
      _ <- Entity.int.query.asOf(tx2.tx).get.map(_ ==> List(1, 2))
      _ <- Entity.int.query.asOf(tx3.tx).get.map(_ ==> List(1, 2, 3))
    } yield ()
  }
}
