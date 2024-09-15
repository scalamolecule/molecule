package molecule.sql.mariadb

import java.net.URI
import java.time.{Duration, Instant, LocalDate, LocalDateTime, LocalTime, OffsetDateTime, OffsetTime, ZonedDateTime}
import java.util.{Date, UUID}
import molecule.base.error.{InsertErrors, ModelError, ValidationErrors}
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Refs.A
import molecule.sql.mariadb.async._
import molecule.sql.mariadb.setup.TestSuite_mariadb
import utest._
import scala.language.implicitConversions

object AdhocJVM_mariadb extends TestSuite_mariadb {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {

        id <- Ns.i(42).save.transact.map(_.id)

        // Map attribute not yet asserted
        _ <- Ns.intMap.query.get.map(_ ==> Nil)

        // Removing pair by key from non-asserted Map has no effect
        _ <- Ns(id).intMap.remove(string1).update.transact
        _ <- Ns.intMap.query.get.map(_ ==> Nil)

        // Start with some pairs
        _ <- Ns(id).intMap.add(pint1, pint2, pint3, pint4, pint5, pint6, pint7).upsert.transact

        // Remove pair by String key
        _ <- Ns(id).intMap.remove(string7).update.i.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4, pint5, pint6))


      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.dsl.Refs._
      for {


        //        _ <- A.i.B.?(B.iSet).insert(
        //          (0, None),
        //          (1, Some(Set(1, 2))),
        //        ).transact
        //
        //        _ <- A.i.B.?(B.iSet).query.i.get.map(_ ==> List(
        //          (0, None),
        //          (1, Some(Set(1, 2))),
        //        ))
//
//        _ <- A.i.B.?(B.iSeq).insert(
//          (0, None),
//          (1, Some(Seq(1, 2, 1))),
//        ).transact
//
//        _ <- rawQuery(
//          """SELECT DISTINCT
//            |  A.i,
//            |  B.iSeq
//            |FROM A
//            |  LEFT JOIN B ON
//            |    A.b = B.id AND
//            |    B.iSeq IS NOT NULL
//            |WHERE
//            |  A.i IS NOT NULL;
//            |""".stripMargin, true)
//
//
//        _ <- A.i.B.?(B.iSeq).query.i.get.map(_ ==> List(
//          (0, None),
//          (1, Some(Seq(1, 2, 1))),
//        ))

                _ <- A.i.B.?(B.iMap).insert(
                  (0, None),
                  (1, Some(Map("a" -> 1, "b" -> 2))),
                ).transact

                _ <- A.i.B.?(B.iMap).query.i.get.map(_ ==> List(
                  (0, None),
                  (1, Some(Map("a" -> 1, "b" -> 2))),
                ))
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
    //      import molecule.coreTests.dataModels.dsl.Uniques._
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
    //      import molecule.coreTests.dataModels.dsl.Validation._
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