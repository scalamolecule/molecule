package molecule.sql.postgres

import java.time.Duration
import molecule.base.error.InsertErrors
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.sql.core.facade.JdbcConn_JVM
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
//        _ <- Ns.int.insert(1).transact
//        _ <- Ns.int.query.get.map(_ ==> List(1))

//        _ <- Ns.uuid(uuid1).save.transact



        _ <- Ns.i.uuid.insert(List(
          (1, uuid1),
          (2, uuid2),
          (2, uuid2),
          (2, uuid3),
        )).i.transact

      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

      for {

        _ <- A.i.B.i.insert(List(
          //          (1, 1),
          (1, 2),
          //          (2, 2),
          //          (2, 3),
          //          (2, 4),
        )).i.transact

        // Average of all (non-coalesced) values
        //        _ <- A.i.query.get.map(_ ==> List(1, 2))
        _ <- A.i.b.query.get.map(_ ==> List((1, 1)))
        //        _ <- B.i.query.get.map(_ ==> List(1, 2, 3, 4))
        //
        //        _ <- A.B.i.query.i.get.map(_ ==> List(7))


        //        _ <- A.B.i(avg).query.get.map(_ ==> List(7))
        //        _ <- A.B.i(avg).query.get.map(_.head ==~ (1 + 2 + 2 + 3 + 4).toDouble / 5.0)
        //
        //        _ <- A.i.B.i(avg).query.get.map(_.map {
        //          case (1, avg) => avg ==~ (1 + 2).toDouble / 2.0
        //          case (2, avg) => avg ==~ (2 + 3 + 4).toDouble / 3.0
        //        })
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