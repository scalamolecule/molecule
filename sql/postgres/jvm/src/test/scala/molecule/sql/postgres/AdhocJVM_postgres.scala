package molecule.sql.postgres

import molecule.core.util.Executor._
import molecule.coreTests.setup.{Test, TestUtils}
import molecule.sql.postgres.async._
import molecule.sql.postgres.setup.DbProviders_postgres
import scala.language.implicitConversions

class AdhocJVM_postgres extends Test with DbProviders_postgres with TestUtils {

    "types" - types { implicit conn =>
      import molecule.coreTests.domains.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {

        id <- Entity.i(42).save.transact.map(_.id)

        // Map attribute not yet asserted
        _ <- Entity.intMap.query.get.map(_ ==> Nil)

        // When attribute is not already asserted, an update has no effect
        _ <- Entity(id).intMap(Map(pint1, pint2)).update.i.transact
        _ <- Entity.intMap.query.get.map(_ ==> Nil)
      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.domains.dsl.Refs._
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
    //      import molecule.coreTests.domains.dsl.Uniques._
    //      for {
    //        _ <- Uniques.i(1).save.transact
    //
    //      } yield ()
    //    }
    //
    //
    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.domains.dsl.Validation._
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