package molecule.sql.postgres

import java.time.Duration
import molecule.base.error.InsertErrors
import molecule.core.util.Executor._
import molecule.sql.postgres.async._
import molecule.sql.postgres.setup.{TestSuiteArray_postgres, TestSuite_postgres}
import utest._
import scala.concurrent.Future
import scala.language.implicitConversions

//object AdhocJVM_postgres extends TestSuiteArray_postgres {
object AdhocJVM_postgres extends TestSuite_postgres {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.int.insert(1).transact
        _ <- Ns.int.query.get.map(_ ==> List(1))
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

        //        _ <- rawTransact(
        //          """UPDATE B
        //            |SET
        //            |  iSeq = ARRAY_REMOVE(ARRAY_REMOVE(iSeq, ?::integer), ?::integer)
        //            |WHERE
        //            |  iSeq IS NOT NULL AND
        //            |  B.id IN(2, 3)
        //            |""".stripMargin)
        //        _ <- rawQuery(
        //          """SELECT ARRAY_AGG(elem) AS unique_array
        //            |FROM UNNEST('{1, 2, 3, 2, 4, 1}'::int[]) AS elem
        //            |WHERE elem NOT IN(3, 4)
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
    //        _ <- Type.string.insert("a").transact
    //          .map(_ ==> "Unexpected success").recover {
    //            case InsertErrors(errors, _) =>
    //              errors.head._2.head.errors.head ==>
    //                s"""Type.string with value `a` doesn't satisfy validation:
    //                   |_ > "b"
    //                   |""".stripMargin
    //          }
    //      } yield ()
    //    }
  }
}