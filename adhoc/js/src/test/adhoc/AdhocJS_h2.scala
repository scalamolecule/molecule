//package molecule.adhoc
//
//import boopickle.Default._
//import molecule.base.error.{InsertErrors, ModelError, MoleculeError, ValidationErrors}
//import molecule.core.marshalling.Boopicklers._
//import molecule.core.spi.Conn
//import molecule.core.util.Executor._
//import molecule.adhoc.domains.dsl.Types._
//import molecule.adhoc.setup.{MUnitSuite, MUnitSuiteWithArrays, Test_h2, TestData}
//import molecule.sql.h2.async._
//import scala.concurrent.Future
//
//
////class AdhocJS extends MUnitSuiteWithArrays with Test_h2 {
//class AdhocJS_h2 extends MUnitSuite with Test_h2 {
//
//
//  "types" - types { implicit conn =>
//    for {
//      List(a, b) <- Entity.int.insert(1, 2).transact.map(_.ids)
//      _ <- Entity.int(3).save.transact
//      _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2, 3))
//      _ <- Entity(a).int(10).update.transact
//      _ <- Entity(b).delete.transact
//      _ <- Entity.int.a1.query.get.map(_ ==> List(3, 10))
//
//    } yield ()
//  }
//
//}
