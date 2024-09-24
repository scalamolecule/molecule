package molecule.datalog.datomic

import molecule.base.error.ModelError
import molecule.boilerplate.api.{NestedInit_01, NestedInit_02}
import molecule.core.util.Executor._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.TestSuite_datomic
import utest._
import scala.concurrent.Future
import scala.language.implicitConversions

//object AdhocJVM_datomic extends TestSuite_datomic_array {
object AdhocJVM_datomic extends TestSuite_datomic {
  override lazy val tests = Tests {

        "types" - types { implicit conn =>
          import molecule.coreTests.dataModels.dsl.Types._
          implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

          val (a1, b2) = ("a" -> int1, "b" -> int2)
          val (b3, c4) = ("b" -> int3, "c" -> int4)

          for {

            _ <- Ns.i.intMap.insert(List(
              (1, Map(a1, b2)),
              (2, Map(b3, c4)),
            )).transact

            _ <- Ns.i.a1.intMap_.hasNo(List.empty[Int]).query.get.map(_ ==> List(1, 2))


          } yield ()
        }


    //    "refs" - refs { implicit conn =>
    //      import molecule.coreTests.dataModels.dsl.Refs._
    //      for {
    //        _ <- A.s.i.B.?(B.i.C.?(C.i)).D.?(D.i).insert(List(
    //          ("a", 1, Some((2, Some(3))), Some(4)),
    //        )).transact
    //
    //        _ <- A.s.i.a1.B.?(B.i).D.?(D.i).query.i.get.map(_ ==> List(
    //          ("a", 1, Some(2), Some(4)),
    //        ))
    //
    //      } yield ()
    //    }


    //    "unique" - unique { implicit conn =>
    //      import molecule.coreTests.dataModels.dsl.Uniques._
    //      for {
    //        _ <- Uniques.int.s.i.insert(triples).transact
    //
    //      } yield ()
    //    }
    //
    //
    //        "validation" - validation { implicit conn =>
    //          import molecule.coreTests.dataModels.dsl.Validation._
    //          for {
    //
    //            id <- MandatoryRefB.i(1).RefB.i(2).save.transact.map(_.id)
    //
    //            _ <- MandatoryRefB(id).refB().update.i.transact
    //              .map(_ ==> "Unexpected success").recover {
    //                case ModelError(error) =>
    //                  error ==>
    //                    """Can't delete mandatory attributes (or remove last values of card-many attributes):
    //                      |  MandatoryRefB.refB
    //                      |""".stripMargin
    //              }
    //
    //          } yield ()
    //        }

//
//
//    "validation" - validation { implicit conn =>
//      import molecule.coreTests.dataModels.dsl.Validation._
//      for {
//        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)
//
//        id <- MandatoryRefsB.i(1).refsB(Set(r1, r2)).save.transact.map(_.ids)
//
//        // Mandatory refs can be removed as long as some ref ids remain
//        _ <- MandatoryRefsB(id).refsB.remove(r2).update.transact
//
//        // Last mandatory ref can't be removed. This can prevent creating orphan relationships.
//        _ <- MandatoryRefsB(id).refsB.remove(r1).update.transact
//          .map(_ ==> "Unexpected success").recover {
//            case ModelError(error) =>
//              error ==>
//                """Can't delete mandatory attributes (or remove last values of card-many attributes):
//                  |  MandatoryRefsB.refsB
//                  |""".stripMargin
//          }
//      } yield ()
//    }
  }
}