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

        List(r1, r2) <- Ref.i.insert(1, 2).transact.map(_.ids)
        _ <- Ns.refs(Set(r1, r2)).save.i.transact

        _ <- rawQuery(
          """select id from Ns
            |""".stripMargin, true)

        _ <- Ns.refs.query.get.map(_.head ==> Set(r1, r2))


      } yield ()
    }

    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {

        e1 <- A.i.OwnBb.*(B.i).insert(
          (1, Seq(10, 11)),
          (2, Seq(20, 21))
        ).transact.map(_.id)

//        // 2 entities, each with 2 owned sub-entities
//        _ <- A.i.a1.OwnBb.*(B.i.a1).query.get.map(_ ==> List(
//          (1, Seq(10, 11)),
//          (2, Seq(20, 21))
//        ))
//
//        // 4 sub-entities
//        _ <- B.i.a1.query.get.map(_ ==> List(10, 11, 20, 21))

        _ <- A(e1).delete.transact

        // 1 entity with 2 owned sub-entities left
        _ <- A.i.OwnBb.*(B.i.a1).query.get.map(_ ==> List(
          (2, Seq(20, 21))
        ))

        _ <- B.i.a1.query.get.map(_ ==> List(20, 21))

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