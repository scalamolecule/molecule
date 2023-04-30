//package molecule.datomic.test.time
//
//import java.util.Date
//import molecule.core.api.Connection
//import molecule.core.util.Executor._
//import molecule.coreTests.dataModels.core.dsl.Types._
//import molecule.datomic.async._
//import molecule.datomic.facade.DatomicTxReport
//import molecule.datomic.setup.DatomicTestSuite
//import utest._
//import scala.concurrent.ExecutionContext
//import scala.language.implicitConversions
//
//
//object GetHistory extends DatomicTestSuite {
//
//  def data(implicit conn: Connection, ec: ExecutionContext) = {
//    for {
//      // First entity - 3 transactions
//      tx1 <- Ns.string("a").int(1).save.transact.map(_.asInstanceOf[DatomicTxReport])
//      e1 = tx1.eid
//      t1 = tx1.tx
//
//      // Adding time separations to ensure that transactions are not within the
//      // same millisecond. Only necessary if using Dates. For precision, use t or tx.
//      tx2 <- Ns(e1).string("b").update.transact.map(_.asInstanceOf[DatomicTxReport])
//      t2 = tx2.tx
//
//      tx3 <- Ns(e1).int(2).update.transact.map(_.asInstanceOf[DatomicTxReport])
//      t3 = tx3.tx
//
//      // Second entity - 2 transactions
//
//      tx4 <- Ns.string("x").int(4).save.transact.map(_.asInstanceOf[DatomicTxReport])
//      e2 = tx4.eid
//      t4 = tx4.tx
//
//      tx5 <- Ns(e2).int(5).update.transact.map(_.asInstanceOf[DatomicTxReport])
//      t5 = tx5.tx
//    } yield {
//      (
//        tx1, e1, t1,
//        tx2, t2,
//        tx3, t3,
//        tx4, e2, t4,
//        tx5, t5
//      )
//    }
//  }
//
//  override lazy val tests = Tests {
//    import molecule.core.util.Executor._
//
//    "1 entity, 1 attr" - types { implicit conn =>
//      val genericInputMolecule = (e: Long) => Ns(e).string.tx.a1.txOp.a2
//      for {
//        (tx1, e1, t1, tx2, t2, tx3, t3, tx4, e2, t4, tx5, t5) <- data
//
//        // Current values are always the last asserted value
//        _ <- Ns(e1).int.txOp_(true).query.get.map(_ ==> List(2))
//        _ <- Ns(e1).int.txOp_(false).query.get.map(_ ==> List())
//
//        // str updated at t2
//        _ <- Ns(e1).string.tx.a1.txOp.a2.query.history.get.map(_ ==> List(
//          ("a", t1, true), // "a" asserted
//          ("a", t2, false), // "a" retracted
//          ("b", t2, true) // "b" asserted
//        ))
//
//        _ <- genericInputMolecule(e1).query.history.get.map(_ ==> List(
//          ("a", t1, true), // "a" asserted
//          ("a", t2, false), // "a" retracted
//          ("b", t2, true) // "b" asserted
//        ))
//
//        // int updated at t3
//        _ <- Ns(e1).int.tx.a1.txOp.a2.query.history.get.map(_ ==> List(
//          (1, t1, true), // 1 asserted
//          (1, t3, false), // 1 retracted
//          (2, t3, true) // 2 asserted
//        ))
//
//        // int history with entity
//        _ <- Ns.e.a1.int.tx.a2.txOp.a3.query.history.get.map(_ ==> List(
//          // e1
//          (e1, 1, t1, true),
//          (e1, 1, t3, false),
//          (e1, 2, t3, true),
//          //e2
//          (e2, 4, t4, true),
//          (e2, 4, t5, false),
//          (e2, 5, t5, true)
//        ))
//
//        // int values over time
//        _ <- Ns(e1).int.query.history.get.map(_ ==> List(1, 2))
//
//        // Asserted int values of entity e1 over time
//        _ <- Ns(e1).int.txOp_(true).query.history.get.map(_ ==> List(1, 2))
//
//        // Retracted int values of entity e1 over time
//        _ <- Ns(e1).int.txOp_(false).query.history.get.map(_ ==> List(1))
//      } yield ()
//    }
//
//
//    "Multiple domain attrs" - types { implicit conn =>
//      for {
//        (tx1, e1, t1, tx2, t2, tx3, t3, tx4, e2, t4, tx5, t5) <- data
//
//        // Mixing the "timeline" of two user-defined "domain" attributes gives
//        // us some redundant repetition from unified attribute values.
//        // To illustrate, let's revisit the str datoms:
//
//        // str updated at t2
//        _ <- Ns(e1).string.tx.a1.txOp.a2.query.history.get.map(_ ==> List(
//          ("a", t1, true), // "a" asserted
//          ("a", t2, false), // "a" retracted
//          ("b", t2, true) // "b" asserted
//        ))
//
//        // Adding the int attribute will cause its historic values 1 and 2 to repeatedly
//        // be unified with each str value from above so that we get 3 x 2 datoms:
//        _ <- Ns(e1).string.tx.a1.txOp.a2.int.a3.query.history.get.map(_ ==> List(
//          ("a", t1, true, 1),
//          ("a", t1, true, 2),
//
//          ("a", t2, false, 1),
//          ("a", t2, false, 2),
//
//          ("b", t2, true, 1),
//          ("b", t2, true, 2)
//        ))
//
//        // Without a given entity, this approach quickly explodes and becomes useless:
//        _ <- Ns.string.tx.a1.txOp.a2.int.a3.query.history.get.map(_ ==> List(
//          ("a", t1, true, 1),
//          ("a", t1, true, 2),
//
//          ("a", t2, false, 1),
//          ("a", t2, false, 2),
//
//          ("b", t2, true, 1),
//          ("b", t2, true, 2),
//
//          // Note how str("x") was never retracted and stays the same for both int values
//          ("x", t4, true, 4),
//          ("x", t4, true, 5)
//        ))
//
//        // Additional attributes are better used to filter the result
//        // "str operations on enties having had an int value 1"
//        _ <- Ns.string.tx.a1.txOp.a2.int(1).query.history.get.map(_ ==> List(
//          ("a", t1, true, 1),
//          ("a", t2, false, 1),
//          ("b", t2, true, 1)
//        ))
//
//        // ..and even better as tacit attributes
//        // "str operations on entities having had an int value of 1"
//        _ <- Ns.string.tx.a1.txOp.a2.int_(1).query.history.get.map(_ ==> List(
//          ("a", t1, true),
//          ("a", t2, false),
//          ("b", t2, true)
//        ))
//
//        // Giving the int value 5 we get to the second entity
//        // "str operations on entities having had an int value of 5"
//        _ <- Ns.string.tx.a1.txOp.a2.int_(5).query.history.get.map(_ ==> List(
//          ("x", t4, true)
//        ))
//
//
//        // This is not so useful. So instead, we might want to use the int
//        // attribute to filter
//
//        // Reversing the attributes we get to the first entity via a or b:
//        // "int operations on entities having had an int value of a"
//        _ <- Ns.int.tx.a1.txOp.a2.string_("a").query.history.get.map(_ ==> List(
//          (1, t1, true),
//          (1, t3, false),
//          (2, t3, true)
//        ))
//
//        // "int operations on entities having had a str value of b"
//        _ <- Ns.int.tx.a1.txOp.a2.string_("b").query.history.get.map(_ ==> List(
//          (1, t1, true),
//          (1, t3, false),
//          (2, t3, true)
//        ))
//
//        // Getting historic operations on second entity via str value x
//        // "int operations on entities having had a str value of x"
//        _ <- Ns.int.tx.a1.txOp.a2.string_("x").query.history.get.map(_ ==> List(
//          (4, t4, true),
//          (4, t5, false),
//          (5, t5, true)
//        ))
//
//        // Order of attributes is free.
//        // All generic attributes always relate to the previous domain attribute (`int` here)
//        _ <- Ns.string_("x").int.tx.a1.txOp.a2.query.history.get.map(_ ==> List(
//          (4, t4, true),
//          (4, t5, false),
//          (5, t5, true)
//        ))
//      } yield ()
//    }
//
//
////    "Multiple attrs" - types { implicit conn =>
////      for {
////        (tx1, e1, t1, tx2, t2, tx3, t3, tx4, e2, t4, tx5, t5) <- data
////
////        // We _can_ combine multiple attrs with generic attributes in a history
////        // query but then two individual attribute history "timelines" of changes
////        // are unified which is quite little use:
////        _ <- Ns(e1).string.a2.tx.a1.txOp.int.tx.a3.txOp.a4.query.history.get.map(_ ==> List(
////          ("a", t1, true, 1, t1, true),
////          ("a", t1, true, 1, t3, false),
////          ("a", t1, true, 2, t3, true),
////          ("a", t2, false, 1, t1, true),
////          ("a", t2, false, 1, t3, false),
////          ("a", t2, false, 2, t3, true),
////          ("b", t2, true, 1, t1, true),
////          ("b", t2, true, 1, t3, false),
////          ("b", t2, true, 2, t3, true),
////        ))
////      } yield ()
////    }
//
//
//    "Entity history" - types { implicit conn =>
//      for {
//        (tx1, e1, t1, tx2, t2, tx3, t3, tx4, e2, t4, tx5, t5) <- data
//
//        // Instead of building a history of an entity with multiple fixed attributes
//        // as a molecule we can also look for _any_ attribute involved in an entity's history:
//
//        // All attribute assertions/retractions of entity e1
//        _ <- Ns(e1).a.a1.v.tx.a2.txOp.a3.query.history.get.map(_ ==> List(
//          (":Ns/int", 1, t1, true),
//          (":Ns/int", 1, t3, false),
//          (":Ns/int", 2, t3, true),
//          (":Ns/str", "a", t1, true),
//          (":Ns/str", "a", t2, false),
//          (":Ns/str", "b", t2, true),
//        ))
//
//        // All attribute assertions of entity e1
//        _ <- Ns(e1).a.a1.v.tx.a2.txOp(true).query.history.get.map(_ ==> List(
//          (":Ns/int", 1, t1, true),
//          (":Ns/int", 2, t3, true),
//          (":Ns/str", "a", t1, true),
//          (":Ns/str", "b", t2, true),
//        ))
//
//        // All attribute retractions of entity e1
//        _ <- Ns(e1).a.a1.v.tx.a2.txOp(false).query.history.get.map(_ ==> List(
//          (":Ns/int", 1, t3, false),
//          (":Ns/str", "a", t2, false),
//        ))
//
//        // All attribute assertions/retractions of entity e1 at t2
//        _ <- Ns(e1).a.v.tx(t2).txOp.a1.query.history.get.map(_ ==> List(
//          // str value was updated from "a" to "b"
//          (":Ns/str", "a", t2, false),
//          (":Ns/str", "b", t2, true)
//        ))
//
//        // All attribute retractions of entity e1 at t2
//        _ <- Ns(e1).a.v.tx(t2).txOp(false).query.history.get.map(_ ==> List(
//          // str value "a" was retracted at t2
//          (":Ns/str", "a", t2, false)
//        ))
//
//        // All attribute assertions of entity e1 at t2
//        _ <- Ns(e1).a.v.tx(t2).txOp(true).query.history.get.map(_ ==> List(
//          // str value "b" was asserted at t2
//          (":Ns/str", "b", t2, true)
//        ))
//
//        // All attribute assertions with value "a" of entity e1
//        _ <- Ns(e1).a.v("a").tx.a1.txOp.query.history.get.map(_ ==> List(
//          (":Ns/str", "a", t1, true),
//          (":Ns/str", "a", t2, false)
//        ))
//
//        // All attribute assertions with value "a" of entity e1 at t2
//        _ <- Ns(e1).a.v("a").tx(t2).txOp.query.history.get.map(_ ==> List(
//          (":Ns/str", "a", t2, false)
//        ))
//
//        // All attribute assertions with value 2 of entity e1
//        _ <- Ns(e1).a.v("2").tx.txOp.query.history.get.map(_ ==> List(
//          (":Ns/int", 2, t3, true)
//        ))
//      } yield ()
//    }
//
//
//    "Tacit generic attrs" - types { implicit conn =>
//      for {
//        (tx1, e1, t1, tx2, t2, tx3, t3, tx4, e2, t4, tx5, t5) <- data
//
//        // Entities with retractions
//        _ <- Ns.e.a.v.tx.a1.txOp_(false).query.history.get.map(_ ==> List(
//          (e1, ":Ns/str", "a", t2),
//          (e1, ":Ns/int", 1, t3),
//          (e2, ":Ns/int", 4, t5)
//        ))
//
//        // Transaction dates
//        date2 = tx2.txDate
//        date3 = tx3.txDate
//        date5 = tx5.txDate
//
//        _ <- Ns.e.a.a1.v.txDate.a2.txOp_(false).query.history.get.map(_ ==> List(
//          (e1, ":Ns/int", 1, date3),
//          (e2, ":Ns/int", 4, date5),
//          (e1, ":Ns/str", "a", date2)
//        ))
//
//        // Entities involved in transaction t2
//        // Note how the transaction itself is included
//        _ <- Ns.e.a.v.tx_(t2).txOp.a1.query.history.get.map(_ ==> List(
//          (e1, ":Ns/str", "a", false),
//          (e1, ":Ns/str", "b", true)
//        ))
//
//        // Using transaction date
//        // Entities involved in transaction as of date2
//        _ <- Ns.e.a.v.txDate_(date2).txOp.a1.query.history.get.map(_ ==> List(
//          (e1, ":Ns/str", "a", false),
//          (e1, ":Ns/str", "b", true)
//        ))
//      } yield ()
//    }
//  }
//}
