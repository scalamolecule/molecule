package molecule.sql.mariadb

import java.net.URI
import java.time.{Duration, Instant, LocalDate, LocalDateTime, LocalTime, OffsetDateTime, OffsetTime, ZonedDateTime}
import java.util.{Date, UUID}
import molecule.base.error.{InsertErrors, ModelError, ValidationErrors}
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs.A
import molecule.sql.mariadb.async._
import molecule.sql.mariadb.setup.TestSuite_mariadb
import utest._
import scala.language.implicitConversions

object AdhocJVM_mariadb extends TestSuite_mariadb {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {

        _ <- Ns.i.stringMap.insert(1, Map(pstring1, pstring2)).i.transact

        _ <- Ns.i.query.get.map(_ ==> List(1))
        _ <- Ns.i.stringMap.query.i.get.map(_ ==> List((1, Map(pstring1, pstring2))))



        _ <- Ns.int.i.stringSeq_?.insert(1, 1, Option.empty[List[String]]).transact

        _ <- Ns.int.i.stringSeq_?.insert(1, 2, Some(List.empty[String])).transact

      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {


        _ <- A.B.Cc.i.insert(1).transact


        //        _ <- rawTransact(
        //          """UPDATE B
        //            |SET
        //            |  iSeq = (
        //            |      SELECT JSON_ARRAYAGG(list.v)
        //            |      FROM   JSON_TABLE(B.iSeq, '$[*]' COLUMNS (v INT PATH '$')) as list
        //            |      WHERE  list.v NOT IN (3, 4) and B.id is not null
        //            |    )
        //            |WHERE
        //            |  B.iSeq is not null and
        //            |  B.id IN(1, 2, 3)
        //            |""".stripMargin)

      } yield ()
    }


//    "unique" - unique { implicit conn =>
//      import molecule.coreTests.dataModels.core.dsl.Uniques._
//
//
//      for {
//

//
//
//
//        _ <- Uniques.int.insert(1, 2).transact
//
//
//      } yield ()
//    }
//
//
//    "validation" - validation { implicit conn =>
//      import molecule.coreTests.dataModels.core.dsl.Validation._
//      for {
//        id <- MandatoryAttr.name("Bob").age(42).hobbies(Set("golf", "stamps")).save.transact.map(_.id)
//
//        // We can remove a value from a Set as long as it's not the last value
//        _ <- MandatoryAttr(id).hobbies.remove("stamps").update.i.transact
//
//        //        // Can't remove the last value of a mandatory attribute Set of values
//        //        _ <- MandatoryAttr(id).hobbies.remove("golf").update.transact
//        //          .map(_ ==> "Unexpected success").recover {
//        //            case ModelError(error) =>
//        //              error ==>
//        //                """Can't delete mandatory attributes (or remove last values of card-many attributes):
//        //                  |  MandatoryAttr.hobbies
//        //                  |""".stripMargin
//        //          }
//
//      } yield ()
//    }
  }
}