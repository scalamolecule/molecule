//package molecule.sql.jdbc.test
//
//import boopickle.Default._
//import molecule.core.util.Executor._
//import molecule.coreTests.dataModels.core.dsl.Types._
//import molecule.sql.jdbc.async._
//import molecule.sql.jdbc.setup.JdbcTestSuite
//import utest._
//
//
//object AdhocJdbcJs extends JdbcTestSuite {
//
//  override lazy val tests = Tests {
//
//    "types" - types { implicit conn =>
//      for {
//        _ <- Ns.int.insert(1).transact
//        _ <- Ns.int.query.get.map(_ ==> List(1))
//      } yield ()
//    }
//
//
//    //    "refs" - refs { implicit conn =>
//    //      import molecule.coreTests.dataModels.core.dsl.Refs._
//    //      for {
//    //
//    //        _ <- Ns.i.Rs1.*(R1.i).insert(0, List(1)).transact
//    //
//    //      } yield ()
//    //    }
//  }
//}
