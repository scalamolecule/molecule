package molecule.sql.mysql

import java.io.File
import java.net.URI
import java.time.{Duration, Instant, LocalDate, LocalDateTime, LocalTime, OffsetDateTime, OffsetTime, ZonedDateTime}
import java.util.{Date, UUID}
import molecule.base.error.{ExecutionError, ModelError, ValidationErrors}
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Uniques.Uniques
import molecule.sql.mysql.async._
import molecule.sql.mysql.setup.TestSuite_mysql
import utest._
import scala.collection.immutable.List
import scala.io.Source
import scala.language.implicitConversions

object AdhocJVM_mysql extends TestSuite_mysql {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {


        id <- Ns.i(42).save.transact.map(_.id)

        // Map attribute not yet asserted
        _ <- Ns.intMap.query.get.map(_ ==> Nil)

        // When attribute is not already asserted, an update has no effect
        _ <- Ns(id).intMap(Map(pint1, pint2)).update.i.transact
        _ <- Ns.intMap.query.get.map(_ ==> Nil)

      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {

        List(a, b, c, d, e, f) <- A.i.a1.Bb.*?(B.s_?.iMap_?).insert(
          (1, List()),
          (2, List((Some("a"), None))),
          (3, List((Some("b"), None), (Some("c"), None))),
          (4, List((Some("d"), Some(Map(pint1, pint2))))),
          (5, List((Some("e"), Some(Map(pint2, pint3))), (Some("f"), Some(Map(pint3, pint4))))),
          (6, List((Some("g"), Some(Map(pint4, pint5))), (Some("h"), None))),
        ).transact.map(_.ids)

        // Filter by A ids, update B values
        _ <- A(a, b, c, d, e, f).Bb.iMap(Map(pint4, pint5)).update.transact

        _ <- A.i.a1.Bb.*?(B.s_?.a1.iMap).query.get.map(_ ==> List(
          (1, List()), //                                                               no B.i value
          (2, List()), //                                                               no B.i value
          (3, List()), //                                                               no B.i value
          (4, List((Some("d"), Map(pint4, pint5)))), //                                 update in 1 ref entity
          (5, List((Some("e"), Map(pint4, pint5)), (Some("f"), Map(pint4, pint5)))), // update in 2 ref entities
          (6, List((Some("g"), Map(pint4, pint5)))), //                                 already had same value
        ))

        // Filter by A ids, upsert B values
        _ <- A(a, b, c, d, e, f).Bb.iMap(Map(pint5, pint6)).upsert.transact

        _ <- A.i.a1.Bb.*?(B.s_?.a1.iMap).query.get.map(_ ==> List(
          (1, List((None, Map(pint5, pint6)))), //                                      ref + addition
          (2, List((Some("a"), Map(pint5, pint6)))), //                                 addition in 1 ref entity
          (3, List((Some("b"), Map(pint5, pint6)), (Some("c"), Map(pint5, pint6)))), // addition in 2 ref entities
          (4, List((Some("d"), Map(pint5, pint6)))), //                                 update in 1 ref entity
          (5, List((Some("e"), Map(pint5, pint6)), (Some("f"), Map(pint5, pint6)))), // update in 2 ref entities
          (6, List((Some("g"), Map(pint5, pint6)), (Some("h"), Map(pint5, pint6)))), // update in one ref entity and addition in another
        ))
        //        _ <- rawTransact(
        //          """UPDATE B
        //            |SET
        //            |  iSet = JSON_REMOVE(iSet, JSON_UNQUOTE(JSON_SEARCH(iSet, 'one', '3')))
        //            |WHERE
        //            |  B.iSet is not null and
        //            |  B.id IN(1, 2, 3)
        //            |""".stripMargin)

        //        _ <- rawQuery(
        //          """SELECT iSeq,
        //            |  (
        //            |      SELECT JSON_ARRAYAGG(list.v)
        //            |      FROM   JSON_TABLE('[1,2,3]', '$[*]' COLUMNS (v INT PATH '$')) as list
        //            |    ) as x
        //            |FROM B
        //            |""".stripMargin, true)


      } yield ()
    }


    //    "unique" - unique { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Uniques._
    //      for {
    //        _ <- Uniques.i(1).save.transact
    //
    //      } yield ()
    //    }
    //
    //
    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //      for {
    //        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)
    //
    //      } yield ()
    //    }
  }
}