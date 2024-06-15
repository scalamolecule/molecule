package molecule.sql.postgres

import java.time.Duration
import molecule.core.util.Executor._
import molecule.sql.postgres.async._
import molecule.sql.postgres.setup.{TestSuiteArray_postgres, TestSuite_postgres}
import utest._
import scala.language.implicitConversions

//object AdhocJVM_postgres extends TestSuiteArray_postgres {
object AdhocJVM_postgres extends TestSuite_postgres {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {

        id1 <- Ns.stringSeq(List(string1, string2, string3)).save.transact.map(_.id)


      } yield ()
    }

    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      val pint20 = "b" -> 20
      val pint30 = "c" -> 30
      for {

        // Will not be updated since entity has no A -> B relationship
        _ <- B.s("x").iSeq(Seq(1, 2, 3)).save.transact


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
        //
        //        _ <- rawQuery(
        //          """SELECT ARRAY_AGG(elem) AS unique_array
        //            |FROM UNNEST('{1, 2, 3, 2, 4, 1}'::int[]) AS elem
        //            |WHERE elem NOT IN(1, 2, 3, 4)
        //            |""".stripMargin, true)
        //
        //        _ <- rawQuery(
        //          """SELECT ARRAY_AGG(elem) AS unique_array
        //            |FROM UNNEST('{1, 2, 3, 2, 4, 1}'::int[]) AS elem
        //            |WHERE not elem = ANY ('{3, 4}'::int[])
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

    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //
    //      for {
    //        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)
    //
    //      } yield ()
    //    }
  }
}