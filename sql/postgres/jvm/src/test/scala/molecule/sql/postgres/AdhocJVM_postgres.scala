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


      val a = (1, Map("a" -> boolean1, "b" -> boolean2))
      val b = (2, Map("a" -> boolean2, "b" -> boolean3, "c" -> boolean4))
      for {
        _ <- Ns.i.insert(0).transact // Entity without map attribute
        _ <- Ns.i.booleanMap.insert(List(a, b)).transact


        //        _ <- rawTransact(
        //          """UPDATE Ns
        //            |SET
        //            |  intMap = ('{"b": 2}'::jsonb)
        //            |WHERE Ns.id IN(1) AND
        //            |  Ns.intMap IS NOT NULL
        //            |""".stripMargin)


        _ <- rawQuery(
          """SHOW max_connections
            |""".stripMargin, true)

        _ <- rawQuery(
          """SHOW max_locks_per_transaction
            |""".stripMargin, true)



//        _ <- rawQuery(
//          """SELECT DISTINCT
//            |  Ns.i,
//            |  Ns.booleanMap ->> 'a'
//            |FROM Ns
//            |WHERE
//            |  Ns.booleanMap ?? 'a' AND
//            |  Ns.i          IS NOT NULL AND
//            |  Ns.booleanMap IS NOT NULL
//            |ORDER BY Ns.i NULLS FIRST;
//            |""".stripMargin, true)




      } yield ()
    }

    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._

      for {

        _ <- A.i.Bb.*(B.i.C.iSet).insert(
          (0, Nil),
          (1, List(
            (1, Set.empty[Int])
          )),
          (2, List(
            (1, Set.empty[Int]),
            (2, Set(0)),
            (3, Set(1, 2)),
          )),
        ).transact


        _ <- A.i.a1.Bb.*?(B.i.C.iSet).query.get.map(_ ==> List(
          (0, Nil),
          (1, Nil),
          (2, List(
            (2, Set(0)),
            (3, Set(1, 2)),
          )),
        ))

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