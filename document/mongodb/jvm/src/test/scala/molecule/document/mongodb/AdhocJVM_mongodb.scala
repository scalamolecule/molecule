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
import scala.util.Random

object AdhocJVM_mongodb extends TestSuite_mongodb with AggrUtils {


  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        _ <- Ns.i(1).save.transact
        _ <- Ns.i.i.query.get.map(_ ==> List(int1))


      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {

        _ <- A.i.B.i.insert(List(
          (1, 1),
          (2, 2),
          (2, 2),
          (2, 3),
        )).transact

        _ <- A.B.i(count).query.get.map(_ ==> List(4))


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


    "validation" - validation { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Validation._

      for {

        id <- MandatoryAttr.name("Bob").age(42).hobbies(Set("golf", "stamps")).save.i.transact.map(_.id)
//        id <- MandatoryAttr.name("Bob").age(42).hobbies(Set("golf")).save.transact.map(_.id)




        // We can remove a value from a Set as long as it's not the last value
        _ <- MandatoryAttr(id).hobbies.remove("stamps").update.transact


//        _ <- MandatoryAttr.hobbies.query.get
//
//        _ <- rawQuery(
//          """{
//            |  "collection": "MandatoryAttr",
//            |  "pipeline": [
//            |    {
//            |      "$match": {
//            |        "$and": [
//            |          {
//            |            "hobbies": {
//            |              "$ne": null
//            |            }
//            |          },
//            |          {
//            |            "hobbies": {
//            |              "$ne": []
//            |            }
//            |          }
//            |        ]
//            |      }
//            |    },
//            |    {
//            |      "$project": {
//            |        "_id": 0,
//            |        "hobbies": 1
//            |      }
//            |    }
//            |  ]
//            |}
//            |""".stripMargin).map(println)


        // Can't remove the last value of a mandatory attribute Set of values
        _ <- MandatoryAttr(id).hobbies.remove("golf").update.transact
          .map(_ ==> "Unexpected success").recover {
            case ModelError(error) =>
              error ==>
                """Can't delete mandatory attributes (or remove last values of card-many attributes):
                  |  MandatoryAttr.hobbies
                  |""".stripMargin
          }



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

      } yield ()
    }
  }
}
