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

        _ <- A.s.i
          .B.i._A
          .OwnB.i
          .insert(
            ("a", 1, 1, 0),
            ("b", 1, 0, 1),
          ).transact

        //        // Filter attribute B.i needs qualifying
        //        _ <- A.s.i_(B.i_) // Ambiguous if B points to A.B or A.OwnB
        //          .B.i_._A
        //          .OwnB.i_
        //          .query.get.map(_ ==> "Unexpected success").recover { case ModelError(err) =>
        //            err ==>
        //              """Please qualify filter attribute B.i to an unambiguous path:
        //                |  A.B.i
        //                |  A.OwnB.i""".stripMargin
        //          }


        //        _ <- rawQuery(
        //          """SELECT DISTINCT
        //            |  A.s
        //            |FROM A
        //            |  INNER JOIN B           ON A.b    = B.id
        //            |  INNER JOIN B AS B_ownB ON A.ownB = B_ownB.id
        //            |WHERE
        //            |  A.i      = B.i AND
        //            |  A.s      IS NOT NULL AND
        //            |  A.i      IS NOT NULL AND
        //            |  B.i      IS NOT NULL AND
        //            |  B_ownB.i IS NOT NULL;
        //            |""".stripMargin, true)
        //
        //        _ <- rawQuery(
        //          """SELECT DISTINCT
        //            |  A.s
        //            |FROM A
        //            |  INNER JOIN B           ON A.b    = B.id
        //            |  INNER JOIN B AS B_ownB ON A.ownB = B_ownB.id
        //            |WHERE
        //            |  A.i      = B_ownB.i AND
        //            |  A.s      IS NOT NULL AND
        //            |  A.i      IS NOT NULL AND
        //            |  B.i      IS NOT NULL AND
        //            |  B_ownB.i IS NOT NULL;
        //            |""".stripMargin, true)


        //        _ <- A.s.i_(A.B.i_)
        //          .B.i_._A
        //          .OwnB.i_
        //          .query.i.get.map(_ ==> List("a"))

        _ <- A.s.i_(A.OwnB.i_)
          .B.i_._A
          .OwnB.i_
          .query.i.get.map(_ ==> List("b"))

        _ <- A.s.i_(A.OwnB.i_)
          .OwnB.i_._A
          .B.i_
          .query.i.get.map(_ ==> List("b"))

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
