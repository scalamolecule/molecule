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


//      println(floatMin)
//      println(floatMin.toInt)
//      println(Int.íntValue)
//      println("------")
//      println(floatMax)
//      println(floatMax.toInt)
//      println(Int.MaxValue)

      for {

        _ <- Ns.float.insert(floatMin, float1, floatMax).transact

        _ <- Ns.float.a1.query.get.map(_ ==> List(floatMin, float1, floatMax))

//        _ <- rawTransact(
//          """UPDATE Ns as x
//            |SET
//            |  i = 1
//            |WHERE
//            |  x.i IS NOT NULL AND
//            |  EXISTS (
//            |  SELECT * FROM Ns
//            |    INNER JOIN Ns_refs_Ref ON Ns.id = Ns_refs_Ref.Ns_id
//            |)
//            |""".stripMargin)


      } yield ()
    }
/*
UPDATE Ns as Ns
SET
  i = 1
WHERE
  Ns.i IS NOT NULL AND
  EXISTS (
  SELECT * FROM Ns
    INNER JOIN Ns_refs_Ref ON Ns.id = Ns_refs_Ref.Ns_id
)

Mysql
You can't specify target table 'Ns' for update in FROM clause
 */

    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._

      for {


        _ <- A.i.a1.Bb.*?(B.s_?.iSeq_?).insert(
          (1, List()),
          (2, List((Some("a"), None))),
          (3, List((Some("b"), None), (Some("c"), None))),
          (4, List((Some("d"), Some(Seq(1, 2))))),
          (5, List((Some("e"), Some(Seq(2, 3))), (Some("f"), Some(Seq(3, 4))))),
          (6, List((Some("g"), Some(Seq(4, 5))), (Some("h"), None))),
        ).transact.map(_.ids)

        // Filter by A ids, update B values
        _ <- A.i_.Bb.iSeq(Seq(4, 5)).update.transact

        _ <- A.i.a1.Bb.*?(B.s_?.iSeq).query.get.map(_ ==> List(
          (1, List()), //                                               no B.i value
          (2, List()), //                                               no B.i value
          (3, List()), //                                               no B.i value
          (4, List((Some("d"), Seq(4, 5)))), //                         update in 1 ref entity
          (5, List((Some("e"), Seq(4, 5)), (Some("f"), Seq(4, 5)))), // update in 2 ref entities
          (6, List((Some("g"), Seq(4, 5)))), //                         already had same value
        ))

        //        // Filter by A ids, upsert B values
        //        _ <- A.i_.Bb.iSeq(Seq(5, 6)).upsert.transact
        //
        //        _ <- A.i.a1.Bb.*?(B.s_?.iSeq).query.get.map(_ ==> List(
        //          (1, List((None, Seq(5, 6)))), //                              ref + addition
        //          (2, List((Some("a"), Seq(5, 6)))), //                         addition in 1 ref entity
        //          (3, List((Some("b"), Seq(5, 6)), (Some("c"), Seq(5, 6)))), // addition in 2 ref entities
        //          (4, List((Some("d"), Seq(5, 6)))), //                         update in 1 ref entity
        //          (5, List((Some("e"), Seq(5, 6)), (Some("f"), Seq(5, 6)))), // update in 2 ref entities
        //          (6, List((Some("g"), Seq(5, 6)), (Some("h"), Seq(5, 6)))), // update in one ref entity and addition in another
        //        ))


        //        _ <- rawTransact(
        //          """UPDATE B
        //            |SET
        //            |  iSet = JSON_REMOVE(iSet, JSON_UNQUOTE(JSON_SEARCH(iSet, 'one', '3')))
        //            |WHERE
        //            |  B.iSet is not null and
        //            |  B.id IN(1, 2, 3)
        //            |""".stripMargin)

        //        _ <- rawQuery(
        //          """SELECT iSeq,
        //            |  (
        //            |      SELECT JSON_ARRAYAGG(list.v)
        //            |      FROM   JSON_TABLE('[1,2,3]', '$[*]' COLUMNS (v INT PATH '$')) as list
        //            |    ) as x
        //            |FROM B
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
    //        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)
    //
    //      } yield ()
    //    }
  }
}