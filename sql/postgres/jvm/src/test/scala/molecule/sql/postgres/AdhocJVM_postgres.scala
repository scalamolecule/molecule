package molecule.sql.postgres

import java.time.Duration
import molecule.base.error.InsertErrors
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types.Ns
import molecule.sql.core.facade.JdbcConn_JVM
import molecule.sql.postgres.async._
import molecule.sql.postgres.setup.{TestSuite_postgres_array, TestSuite_postgres}
import utest._
import scala.concurrent.Future
import scala.language.implicitConversions

//object AdhocJVM_postgres extends TestSuite_postgres_array {
object AdhocJVM_postgres extends TestSuite_postgres {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.dsl.Types._
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
      import molecule.coreTests.dataModels.dsl.Refs._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

      for {

        _ <- A.i.insert(1).transact
        _ <- A.i.B.i.insert((2, 20), (3, 30)).transact

        _ <- A.i.a1.query.get.map(_ ==> List(1, 2, 3))
        _ <- A.i.a1.B.i.query.get.map(_ ==> List((2, 20), (3, 30)))

        // Nothing deleted since entity 1 doesn't have a ref
        _ <- A.i_(1).B.i_.delete.transact
        _ <- A.i.a1.query.get.map(_ ==> List(1, 2, 3))

        // Second entity has a ref and will be deleted
        _ <- A.i_(2).B.i_.delete.i.transact
        _ <- A.i.a1.query.get.map(_ ==> List(1, 3))



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
    //      import molecule.coreTests.dataModels.dsl.Uniques._
    //      for {
    //        _ <- Uniques.i(1).save.transact
    //
    //      } yield ()
    //    }
    //
    //
    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.dsl.Validation._
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