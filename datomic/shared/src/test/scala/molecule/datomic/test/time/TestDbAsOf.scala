//package molecule.datomic.test.time
//
//import java.util.Date
//import molecule.core.api.Connection
//import molecule.coreTests.dataModels.core.dsl.Types._
//import molecule.datomic.async._
//import molecule.datomic.setup.DatomicTestSuite
//import utest._
//import scala.concurrent.{ExecutionContext, Future}
//import scala.language.implicitConversions
//
//
//object TestDbAsOf extends DatomicTestSuite {
//
//
//  def data(implicit conn: Connection, ec: ExecutionContext) = {
//    for {
//      txR1 <- Ns.int(1).save.transact
//      txR2 <- Ns.int(2).save.transact
//      txR3 <- Ns.int(3).save.transact
//      e1 = txR1.eid
//      e2 = txR2.eid
//      e3 = txR3.eid
//    } yield {
//      (txR1, txR2, txR3, e1, e2, e3)
//    }
//  }
//
//  override lazy val tests = Tests {
//    import molecule.core.util.Executor._
//
//
//    "as of: operations" - types { implicit conn =>
//      for {
////        conn <- futConn
//        (txR1, txR2, txR3, e1, e2, e3) <- data
//
//        // Live state
//        _ <- Ns.int.query.get.map(_ ==> List(1, 2, 3))
//
//        // Use state as of tx 2 as a test db "branch"
//        _ <- conn.testDbAsOf(txR2)
//
//        // Test state
//        _ <- Ns.int.query.get.map(_ ==> List(1, 2))
//
//        // Test operations:
//
//        // Save
//        _ <- Ns.int(4).save
//        _ <- Ns.int.a1.query.get.map(_ ==> List(1, 2, 4))
//
//        // Insert
//        _ <- Ns.int insert List(5, 6)
//        _ <- Ns.int.a1.query.get.map(_ ==> List(1, 2, 4, 5, 6))
//
//        // Update
//        _ <- Ns(e2).int(0).update
//        _ <- Ns.int.a1.query.get.map(_ ==> List(0, 1, 4, 5, 6))
//
//        // Retract
//        _ <- Ns(e1).delete.transact
//        _ <- Ns.int.a1.query.get.map(_ ==> List(0, 4, 5, 6))
//
//        // Live state unchanged - and we can continue updating
//        _ = conn.useLiveDb()
//        _ <- Ns.int(7).save
//        _ <- Ns.int.a1.query.get.map(_ ==> List(1, 2, 3, 7))
//      } yield ()
//    }
//  }
//}
