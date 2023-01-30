//package molecule.db.datomic.test.pagination.offset
//
//import molecule.base.util.exceptions.MoleculeError
//import molecule.core.api.result.Page
//import molecule.core.util.Executor._
//import molecule.coreTests.dataModels.core.dsl.Types._
//import molecule.db.datomic._
//import molecule.db.datomic.setup.DatomicTestSuite
//import utest._
//
//// Offset with sql dbs is ineffective and should be avoided in favour of cursor pagination.
//// https://medium.com/swlh/sql-pagination-you-are-probably-doing-it-wrong-d0f2719cc166
//
//object OffsetSemantics extends DatomicTestSuite {
//
//  lazy val tests = Tests {
//
//    "Invalid limit/offset" - types { implicit conn =>
//      for {
//        _ <- Ns.int.a1.query.limit(0).get
//          .map(_ ==> "Unexpected success")
//          .recover { case MoleculeError(msg, _) =>
//            msg ==> "Limit cannot be 0. Please use a positive number to get rows " +
//              "from start, or a negative number to get rows from end."
//          }
//
//        _ <- Ns.int.a1.query.offset(0).get
//          .map(_ ==> "Unexpected success")
//          .recover { case MoleculeError(msg, _) =>
//            msg ==> "Offset is 0 by default. Please remove `.offset(0)`."
//          }
//
//        _ <- Ns.int.a1.query.limit(20).offset(-10).get
//          .map(_ ==> "Unexpected success")
//          .recover { case MoleculeError(msg, _) =>
//            msg ==> "Limit and offset should both be positive or negative."
//          }
//      } yield ()
//    }
//
//
//    // General problems with offset pagination (for any db system):
//
//    "Re-seen data" - types { implicit conn =>
//      for {
//        _ <- Ns.int.insert(1, 3, 5).transact
//
//        _ <- Ns.int.a1.query.limit(2).get.map(_ ==> List(1, 3))
//
//        // Data added before next page is fetched
//        _ <- Ns.int(2).save.transact
//
//        // 3 is shown again!
//        _ <- Ns.int.a1.query.limit(2).offset(2).get.map(_.data ==> List(3, 5))
//      } yield ()
//    }
//
//    "Skipped data" - types { implicit conn =>
//      for {
//        eids <- Ns.int.insert(1, 2, 3, 4).transact.map(_.eids)
//
//        _ <- Ns.int.a1.query.limit(2).get.map(_ ==> List(1, 2))
//
//        // First row (1) retracted before next page is fetched
//        _ <- Ns(eids.head).delete.transact
//
//        // 3 is never shown!
//        _ <- Ns.int.a1.query.limit(2).offset(2).get.map(_.data ==> List(4))
//      } yield ()
//    }
//  }
//}
