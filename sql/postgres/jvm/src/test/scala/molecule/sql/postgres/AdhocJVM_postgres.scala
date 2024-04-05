package molecule.sql.postgres

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

        //        _ <- Ns.iSeq(List(1)).save.transact
        //        _ <- Ns.iSeq.query.get.map(_ ==> List(List(1)))


        id <- Ns.intMap.insert(Map(pint1)).transact.map(_.id)


//        _ <- rawQuery(
//          """SELECT DISTINCT
//            |  Ns.intSeq
//            |FROM Ns
//            |WHERE
//            |  Ns.intSeq IS NOT NULL AND
//            |  Ns.intSeq != '{}'
//            |""".stripMargin, true).map(println)

//        _ <- rawTransact(
//          """UPDATE Ns
//            |SET
//            |  intMap = ('{"b": 2}'::jsonb)
//            |WHERE Ns.id IN(1) AND
//            |  Ns.intMap IS NOT NULL
//            |""".stripMargin)

        _ <- Ns(id).intMap(Map(pint2)).update.i.transact
        _ <- Ns.intMap.query.get.map(_ ==> List(Map(pint2)))

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