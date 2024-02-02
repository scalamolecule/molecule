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
import scala.util.Random

object AdhocJVM_mongodb extends TestSuite_mongodb with AggrUtils {


  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {


//        _ <- Ns.i.string.insert(
//          (1, string1),
//          (1, string2),
//          (1, string3),
//          (2, string4),
//          (2, string5),
//          (2, string6),
//        ).transact
//
//        _ <- Ns.string(min).query.get.map(_ ==> List(string1))
//        _ <- Ns.string(max).query.get.map(_ ==> List(string6))
//        _ <- Ns.string(min).string(max).query.get.map(_ ==> List((string1, string6)))
//
//        _ <- Ns.i.a1.string(min).query.get.map(_ ==> List(
//          (1, string1),
//          (2, string4)
//        ))
//
//        _ <- Ns.i.a1.string(max).query.get.map(_ ==> List(
//          (1, string3),
//          (2, string6)
//        ))
//
//        _ <- Ns.i.a1.string(min).string(max).query.i.get.map(_ ==> List(
//          (1, string1, string3),
//          (2, string4, string6)
//        ))





        _ <- Ns.s.i.ii.ints.insert(List(
          ("a", 1, Set(1, 2, 3), Set(1, 2, 3)),
          ("b", 1, Set(2, 3, 4), Set(2, 3, 4)),
          ("b", 2, Set(3, 4, 5), Set(3, 4, 5)),
        )).transact

        // Multiple cardinality-one aggregations ok
//        _ <- Ns.s(max).i(stddev).query.i.get.map(_ ==> List(
        _ <- Ns.s(max).i(countDistinct).query.i.get.map(_ ==> List(
          ("b", 4),
        ))

//        _ <- Ns.i(1).save.transact
//        _ <- Ns.i.i.query.get.map(_ ==> List(int1))

      } yield ()
    }


    "refs0" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

      val all  = 1 + 2 + 2 + 3+ 3 + 4 + 3 + 4
      val all2 = 2 + 3+ 3 + 4 + 3 + 4

      for {

        _ <- A.i.B.ii.C.ii.insert(List(
          (1, Set(1, 2), Set(1, 2)),
          (2, Set(2, 3), Set(2, 3)),
          (2, Set(3, 4), Set(3, 4)),
          (2, Set(3, 4), Set(3, 4)),
        )).i.transact

        _ <- A.i.B.ii(avg).C.ii(avg).query.i.get.map(_.map {
          case (1, avgB, avgC) =>
            avgB ==~ (1 + 2).toDouble / 2.0
            avgC ==~ (1 + 2).toDouble / 2.0
          case (2, avgB, avgC) =>
            avgB ==~ all2.toDouble / 6.0
            avgC ==~ all2.toDouble / 6.0
        })

      } yield ()
    }

    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

      val all  = 1 + 2 + 2 + 3 + 4 + 3 + 4
      val all2 = 2 + 3 + 4 + 3 + 4

      for {

        _ <- A.i.B.ii.C.ii.insert(List(
          (1, Set(1, 2), Set(1, 2)),
          (2, Set(2), Set(2)),
          (2, Set(3, 4), Set(3, 4)),
          (2, Set(3, 4), Set(3, 4)),
        )).i.transact

        _ <- A.i.B.ii(avg).C.ii(avg).query.i.get.map(_.map {
          case (1, avgB, avgC) =>
            avgB ==~ (1 + 2).toDouble / 2.0
            avgC ==~ (1 + 2).toDouble / 2.0
          case (2, avgB, avgC) =>
            avgB ==~ all2.toDouble / 5.0
            avgC ==~ all2.toDouble / 5.0
        })

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
