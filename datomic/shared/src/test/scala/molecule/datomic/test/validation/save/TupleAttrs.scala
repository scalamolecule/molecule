//package molecule.datomic.test.validation.save
//
//import molecule.base.error._
//import molecule.core.util.Executor._
//import molecule.coreTests.dataModels.core.dsl.Validation._
//import molecule.datomic.async._
//import molecule.datomic.setup.DatomicTestSuite
//import utest._
//import scala.language.implicitConversions
//
//object TupleAttrs extends DatomicTestSuite {
//
//
//  override lazy val tests = Tests {
//
//    "Save" - validation { implicit conn =>
//      for {
//        _ <- Tuples.x(1).save.transact
//          .map(_ ==> "Unexpected success").recover {
//          case ModelError(error) =>
//            error ==>
//              """Missing/empty required attributes:
//                |  Tuples.y
//                |  Tuples.z
//                |""".stripMargin
//        }
//        _ <- Tuples.x(1).y(2).save.transact
//          .map(_ ==> "Unexpected success").recover {
//          case ModelError(error) =>
//            error ==>
//              """Missing/empty required attributes:
//                |  Tuples.z
//                |""".stripMargin
//        }
//
//        // All tuple values present
//        _ <- Tuples.x(1).y(2).z(3).save.transact
//
////        // Can't save empty Set for mandatory attribute
////        _ <- Tuples.username("Bob").age(42).hobbies(Set.empty[String]).save.transact
////          .map(_ ==> "Unexpected success").recover {
////          case ModelError(error) =>
////            error ==>
////              """Missing/empty mandatory attributes:
////                |  Tuples.hobbies
////                |""".stripMargin
////        }
////
////        // All mandatory attributes of namespace present and valid
////        _ <- Tuples.name("Bob").age(42).hobbies(Set("golf", "stamps")).save.transact
//      } yield ()
//    }
//  }
//}
