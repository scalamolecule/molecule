package molecule.document.mongodb

import molecule.base.ast._
import molecule.base.error.{ExecutionError, ModelError, ValidationErrors}
import molecule.core.util.AggrUtils
import molecule.core.util.Executor._
import molecule.document.mongodb.async._
import molecule.document.mongodb.setup.TestSuite_mongodb
import utest._
import scala.language.implicitConversions
import molecule.boilerplate.ast.Model._
import molecule.coreTests.dataModels.core.dsl.Refs.A
import scala.collection.immutable.Set
import scala.concurrent.Future
import scala.util.Random

object AdhocJVM_mongodb extends TestSuite_mongodb with AggrUtils {


  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      val a = (1, Some(Set(int1, int2)))
      val b = (2, Some(Set(int2, int3, int4)))
      val c = (3, None)
      for {
        _ <- Ns.i.ints_?.insert(a, b, c).transact

        _ <- Ns.i.a1.ints_?.query.get.map(_ ==> List(a, b, c))


        //        _ <- Ns.i(1).save.transact
        //        _ <- Ns.i.i.query.get.map(_ ==> List(int1))

      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {


        //        _ <- A.i.Bb.*(B.i.C.i.Dd.*(D.i.E.i)).insert(
        //          (0, Nil),
        //          (1, List(
        //            (1, 1, Nil),
        //          )),
        //          (2, List(
        //            (1, 1, Nil),
        //            (2, 2, List((1, 2))),
        //            (3, 3, List((1, 2), (3, 4))),
        //          )),
        //        ).i.transact
        //
        //        _ <- A.i.Bb.*?(B.i.C.i.Dd.*?(D.i.E.i)).query.i.get.map(_ ==> List(
        //          (0, Nil),
        //          (1, List(
        //            (1, 1, Nil),
        //          )),
        //          (2, List(
        //            (1, 1, Nil),
        //            (2, 2, List((1, 2))),
        //            (3, 3, List((1, 2), (3, 4))),
        //          )),
        //        ))
        //
        //        _ <- A.i.Bb.*(B.i.C.i.Dd.*(D.i.E.i)).query.get.map(_ ==> List(
        //          (2, List(
        ////            (1, 1, Nil),
        //            (2, 2, List((1, 2))),
        //            (3, 3, List((1, 2), (3, 4))),
        //          )),
        //        ))











        _ <- A.i.Bb.*(B.i.C.ii).insert(
          (0, Nil),
          (1, List(
            (1, Set.empty[Int])
          )),
          (2, List(
            (1, Set.empty[Int]),
            (2, Set(1)),
            (3, Set(1, 2)),
          )),
        ).transact


//        _ <- A.i.Bb.*?(B.i.C.ii).query.get.map(_ ==> List(
//          (0, Nil),
//          (1, Nil),
//          (2, List(
//            (2, Set(1)),
//            (3, Set(1, 2)),
//          )),
//        ))
//        _ <- A.i.Bb.*(B.i.C.ii).query.get.map(_ ==> List(
//          (2, List(
//            (2, Set(1)),
//            (3, Set(1, 2)),
//          )),
//        ))
//
//        _ <- A.i.a1.Bb.*?(B.C.ii).query.get.map(_ ==> List(
//          (0, Nil),
//          (1, Nil),
//          (2, List(
//            Set(1, 2), // Set(1) and Set(1, 2) coalesced to one Set
//          )),
//        ))
//        _ <- A.i.Bb.*(B.C.ii).query.get.map(_ ==> List(
//          (2, List(
//            Set(1, 2), // Set(1) and Set(1, 2) coalesced to one Set
//          )),
//        ))

        _ <- A.Bb.*?(B.C.ii).query.get.map(_ ==> List(
//          Nil,
          List(
            Set(1, 2), // Set(1) and Set(1, 2) coalesced to one Set
          ),
        ))
        _ <- A.Bb.*(B.C.ii).query.i.get.map(_ ==> List(
          List(
            Set(1, 2), // Set(1) and Set(1, 2) coalesced to one Set
          ),
        ))


      } yield ()
    }


    //    "unique" - unique { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Uniques._
    //      for {
    //
    //        _ <- Uniques.int.insert(1, 2, 3).transact
    //
    //        c1 <- Uniques.int.a1.query.from("").limit(2).get.map { case (List(1, 2), c, true) => c }
    //
    //        // Turning around with first cursor leads nowhere
    //        _ <- Uniques.int.a1.query.from(c1).limit(-2).get.map { case (Nil, _, false) => () }
    //
    //      } yield ()
    //    }
    //
    //
    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //
    //      for {
    //
    //        id <- MandatoryAttr.name("Bob").age(42).hobbies(Set("golf", "stamps")).save.i.transact.map(_.id)
    //
    //        // We can remove a value from a Set as long as it's not the last value
    //        _ <- MandatoryAttr(id).hobbies.remove("stamps").update.transact
    //
    //        // Can't remove the last value of a mandatory attribute Set of values
    //        _ <- MandatoryAttr(id).hobbies.remove("golf").update.transact
    //          .map(_ ==> "Unexpected success").recover {
    //            case ModelError(error) =>
    //              error ==>
    //                """Can't delete mandatory attributes (or remove last values of card-many attributes):
    //                  |  MandatoryAttr.hobbies
    //                  |""".stripMargin
    //          }
    //
    //      } yield ()
    //    }
  }
}
