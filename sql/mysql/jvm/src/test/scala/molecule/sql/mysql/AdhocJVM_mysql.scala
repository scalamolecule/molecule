package molecule.sql.mysql

import java.io.File
import molecule.base.error.{ExecutionError, ModelError, ValidationErrors}
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Uniques.Uniques
import molecule.sql.mysql.async._
import molecule.sql.mysql.setup.TestSuite_mysql
import utest._
import scala.collection.immutable.List
import scala.io.Source
import scala.language.implicitConversions

object AdhocJVM_mysql extends TestSuite_mysql {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.int.insert(List(
          (1, int1),
          (1, int2),
          (2, int2),
          (2, int3),
          (2, int4),
        )).transact

        _ <- Ns.int(avg).query.i.get.map(
          _.head ==~ (int1 + int2 + int2 + int3 + int4).toDouble / 5.0
        )

        _ <- Ns.i.int(avg).query.get.map(_.map {
          case (1, avg) => avg ==~ (int1 + int2).toDouble / 2.0
          case (2, avg) => avg ==~ (int2 + int3 + int4).toDouble / 3.0
        })

        _ <- Ns.s.i.ii.ints.insert(List(
          ("a", 1, Set(1, 2, 3), Set(1, 2, 3)),
          ("b", 1, Set(3, 4, 5), Set(3, 4, 5)),
          ("b", 2, Set(2, 3, 4), Set(2, 3, 4)),
        )).transact





        //        _ <- Ns.ints.insert(Set(1, 2)).transact
        //        _ <- Ns.ints.query.get.map(_ ==> List(Set(1, 2)))


        //        _ <- rawQuery(
        //          """SELECT JSON_ARRAYAGG(t1.v)
        //            |FROM Ns, JSON_TABLE(Ns.ints, '$[*]' COLUMNS (v int PATH '$')) t1
        //            |WHERE t1.v NOT IN(6)
        //            |""".stripMargin, true)

      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      val all  = 1 + 2 + 2 + 3 + 3 + 4 + 3 + 4
      val all2 = 2 + 3 + 3 + 4 + 3 + 4

      for {
        //        id <- A.i(1).B.i(2).C.i(3).save.transact.map(_.id)
        //        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((1, 2, 3)))


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

        /*
SELECT DISTINCT
  B.i,
  JSON_ARRAYAGG(t_2.vs)
FROM A
  INNER JOIN B ON A.b = B.id
  INNER JOIN C ON A.c = C.id,
  JSON_TABLE(C.ii, '$[*]' COLUMNS (vs INT PATH '$')) t_2
WHERE
  B.i  IS NOT NULL AND
  C.ii IS NOT NULL;
         */


        _ <- A.B.i._A.C.ii(sample).query.i.get.map {
          _.map {
            case (_, set) => all.contains(set.head) ==> true
          }
        }

        _ <- A.B.i._A.C.ii(sample(1)).query.get.map {
          _.map {
            case (_, set) => all.intersect(set).nonEmpty ==> true
          }
        }
        _ <- A.B.i._A.C.ii(sample(2)).query.get.map {
          _.map {
            case (_, set) => all.intersect(set).nonEmpty ==> true
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


    "validation" - validation { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Validation._
      for {
        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)

      } yield ()
    }
  }
}