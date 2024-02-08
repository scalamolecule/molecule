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
      for {


        _ <- Ns.i(1).save.transact
        _ <- Ns.i.i.query.get.map(_ ==> List(int1))

      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {

        List(a1, a2) <- A.i.B.i.insert(
          (1, 2),
          (3, 4),
        ).i.transact.map(_.ids)

        _ <- A(a1).i.query.get.map(_ ==> List(1))
        _ <- A(a2).i.query.get.map(_ ==> List(3))

        // Ref ids
        List(b1, b2) <- A(a1, a2).b.query.get

        _ <- B(b1).i.query.get.map(_ ==> List(2))
        _ <- B(b2).i.query.get.map(_ ==> List(4))

        _ <- A.id.i.a1.b.query.get.map(_ ==> List(
          (a1, 1, b1),
          (a2, 3, b2),
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
