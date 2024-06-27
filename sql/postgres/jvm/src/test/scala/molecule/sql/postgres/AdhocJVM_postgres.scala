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

        List(ref1, ref2, ref3, ref4) <- Ref.i.insert(1, 2, 3, 4).transact.map(_.ids)
        a = (1, Set(ref1, ref2))
        b = (2, Set(ref2, ref3, ref4))

        _ <- Ns.i.refs.insert(List(a, b)).transact

        _ <- Ns.i.a1.refs.has(ref2).query.i.get.map(_ ==> List(a, b))



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

        _ <- A.i.a1.Bb.*?(B.s_?.a1.iMap).query.i.get.map(_ ==> List(
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