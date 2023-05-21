//package molecule.sql.jdbc.test
//
//import molecule.core.api.gateway.ApiAsyncGateway
//import molecule.coreTests.dataModels.core.dsl.Types._
//import molecule.sql.jdbc.async._
//import molecule.sql.jdbc.setup.JdbcTestSuite2
//import utest._
//import scala.language.implicitConversions
//import molecule.core.util.Executor._
//
//
////object AdhocJdbc extends AdhocCoreTests with JdbcTestSuite {
////object AdhocJdbc extends TestSuite with JdbcTestSuite2 with ApiAsyncProxy{
//object AdhocJdbc extends JdbcTestSuite2 {
//
//
//  override  val tests = Tests {
//    "types" - types { implicit conn =>
//      for {
//        //        _ <- Ns.i(3).save.transact
//        _ <- Ns.i.insert(3).transact
//        //        _ <- Ns.i.query.get.map(_ ==> List(3))
//      } yield ()
//    }
//  }
//}
