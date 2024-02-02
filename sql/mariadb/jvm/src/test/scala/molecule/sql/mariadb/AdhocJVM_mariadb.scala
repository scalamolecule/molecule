package molecule.sql.mariadb

import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.sql.mariadb.async._
import molecule.sql.mariadb.setup.TestSuite_mariadb
import utest._
import scala.language.implicitConversions

object AdhocJVM_mariadb extends TestSuite_mariadb {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {


        _ <- Ns.i.ints.insert(List(
          (1, Set(int1, int2)),
          (2, Set(int2, int3)),
          (2, Set(int3, int4)),
          (2, Set(int3, int4)),
        )).transact

//        _ <- Ns.i(count).query.get.map(_ ==> List(4))
//        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.ints(count).query.i.get.map(_ ==> List(8))
//        _ <- Ns.ints(countDistinct).query.get.map(_ ==> List(4))
//
//        _ <- Ns.i.a1.ints(count).query.get.map(_ ==> List(
//          (1, 2),
//          (2, 6)
//        ))
//        _ <- Ns.i.a1.ints(countDistinct).query.get.map(_ ==> List(
//          (1, 2),
//          (2, 3)
//        ))


//        _ <- rawQuery(
//          """SELECT DISTINCT
//            |  Ns.i,
//            |  AVG(t_2.vs)
//            |FROM Ns,
//            |  JSON_TABLE(Ns.ints, '$[*]' COLUMNS (vs DOUBLE PATH '$')) t_2
//            |WHERE
//            |  Ns.i    IS NOT NULL AND
//            |  Ns.ints IS NOT NULL
//            |GROUP BY Ns.i;
//            |""".stripMargin, true)
//
//        _ <- rawQuery(
//          """SELECT distinct
//            |  Ns.i,
//            |  sum(t_2.vs)
//            |FROM Ns,
//            |  JSON_TABLE(Ns.ints, '$[*]' COLUMNS (vs DOUBLE PATH '$')) t_2
//            |WHERE
//            |  Ns.i    IS NOT NULL AND
//            |  Ns.ints IS NOT NULL
//            |GROUP BY Ns.i;
//            |""".stripMargin, true)

        //        _ <- Ns.int.insert(1).i.transact
        //        _ <- Ns.int.query.get.map(_ ==> List(1))
      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._

      val all  = 1 + 2 + 2 + 3 + 3 + 4 + 3 + 4
      val all2 = 2 + 3 + 3 + 4 + 3 + 4

      for {
        //            id <- A.i(1).B.i(2).C.i(3).save.transact.map(_.id)
        //            _ <- A.i.B.i.C.i.query.get.map(_ ==> List((1, 2, 3)))

        _ <- A.i.B.i._A.C.ii.insert(List(
          (1, 1, Set(1, 2)),
          (2, 2, Set(2)),
          (2, 2, Set(3, 4)),
          (2, 2, Set(3, 4)),
        )).transact

        all = Set(1, 2, 3, 4)

        //        _ <- rawQuery(
        //          """SELECT DISTINCT
        //            |  B.i,
        //            |  JSON_ARRAYAGG(t_2.vs)
        //            |FROM A
        //            |  INNER JOIN B ON A.b = B.id
        //            |  INNER JOIN C ON A.c = C.id,
        //            |  JSON_TABLE(C.ii, '$[*]' COLUMNS (vs INT PATH '$')) t_2
        //            |WHERE
        //            |  B.i  IS NOT NULL AND
        //            |  C.ii IS NOT NULL;
        //            |""".stripMargin, true)


        _ <- A.B.i._A.C.ii(sample).query.i.get.map {
          _.map {
            case (_, set) => all.contains(set.head) ==> true
          }
        }



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