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

        //        _ <- Ref.i.Nss.*(Ns.stringSeq).insert(1, List(List(string1, string2))).transact
        _ <- Ref.i.Nss.*(Ns.intSeq).insert(2, List(List(int1, int2))).transact

        _ <- rawQuery(
          """SELECT DISTINCT
            |  Ref.id,
            |  Ns.intSeq
            |FROM Ref
            |  INNER JOIN Ref_nss_Ns ON Ref.id           = Ref_nss_Ns.Ref_id
            |  INNER JOIN Ns         ON Ref_nss_Ns.Ns_id = Ns.id
            |WHERE
            |  Ref.i     IS NOT NULL AND
            |  Ns.intSeq IS NOT NULL
            |GROUP BY Ref.id, Ns.intSeq
            |;
            |""".stripMargin, true)


        //        _ <- Ref.i_.Nss.*(Ns.stringSeq).query.i.get.map(_ ==> List(List(List(string1, string2))))
        _ <- Ref.i_.Nss.*(Ns.intSeq).query.i.get.map(_ ==> List(List(List(int1, int2))))






        //        _ <- Ns.i.iSet(Ns.intSet).query.i.get.map(_ ==> List(b))

      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {



        //        _ <- A.i(1).save.transact
        //        _ <- A.i(2).B.s("b").save.transact
        _ <- A.i(3).B.s("c").iSeq(Seq(1, 2)).save.transact
        _ <- A.i(4).B.s("c").iSeq(Seq(2, 3)).save.transact
        _ <- A.i(5).B.s("c").iSeq(Seq(3, 4, 4, 4)).save.transact

        //        _ <- rawTransact(
        //          """UPDATE B
        //            |SET
        //            |  iSet = (
        //            |    SELECT JSON_ARRAYAGG(table_1.v)
        //            |    FROM   JSON_TABLE(B.iSet, '$[*]' COLUMNS (v INT PATH '$')) table_1
        //            |    WHERE  table_1.v NOT IN (3, 4)
        //            |  )
        //            |WHERE
        //            |  B.iSet IS NOT NULL AND
        //            |  B.id IN(1, 2, 3, 4)
        //            |""".stripMargin)

        //        _ <- rawTransact(
        //          """UPDATE B
        //            |SET
        //            |  iSet = case (
        //            |    SELECT JSON_ARRAYAGG(table_1.v)
        //            |    FROM   JSON_TABLE(B.iSet, '$[*]' COLUMNS (v INT PATH '$')) table_1
        //            |    WHERE  table_1.v NOT IN (3, 4)
        //            |  )
        //            |  when '' then '[42]'
        //            |  else '[43]'
        //            |  end
        //            |WHERE
        //            |  B.id IN(1, 2, 3, 4, 5)
        //            |""".stripMargin)

        //        _ <- rawTransact(
        //          """UPDATE B
        //            |SET
        //            |  iSet =
        //            |    (
        //            |      SELECT JSON_ARRAYAGG(list.v)
        //            |      FROM   JSON_TABLE(B.iSet, '$[*]' COLUMNS (v INT PATH '$')) list
        //            |      WHERE  list.v NOT IN (3, 4)
        //            |    )
        //            |WHERE
        //            |  B.id IN(1, 2, 3, 4, 5)
        //            |""".stripMargin)
        //
        //        _ <- rawQuery(
        //          """select iSet, isnull(iSet),
        //            |(
        //            |      SELECT JSON_ARRAYAGG(list.v)
        //            |      FROM   JSON_TABLE(B.iSet, '$[*]' COLUMNS (v INT PATH '$')) list
        //            |    ) as x1
        //            |from B
        //            |WHERE
        //            |  B.iSet is not null and
        //            |  B.id IN(1, 2, 3, 4, 5)
        //            |""".stripMargin, true)
        ////            |      WHERE  list.v NOT IN (3, 4)

//                _ <- rawTransact(
//                  """UPDATE B
//                    |SET
//                    |  iSet = case B.iSet when '[3, 4]' then null else (
//                    |      SELECT JSON_ARRAYAGG(list.v)
//                    |      FROM   JSON_TABLE(B.iSet, '$[*]' COLUMNS (v INT PATH '$')) list
//                    |      WHERE  list.v NOT IN (3, 4)
//                    |    ) end
//                    |WHERE
//                    |  B.iSet is not null and
//                    |  B.id IN(2, 3)
//                    |""".stripMargin)

        //        _ <- rawTransact(
        //          """UPDATE B
        //            |SET
        //            |  iSet = (
        //            |      SELECT JSON_ARRAYAGG(list.v)
        //            |      FROM   JSON_TABLE(B.iSet, '$[*]' COLUMNS (v INT PATH '$')) list
        //            |      WHERE  list.v NOT IN (3, 4)
        //            |    )
        //            |WHERE
        //            |  B.iSet is not null and
        //            |  B.id IN(2, 3)
        //            |""".stripMargin)

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

        _ <- rawQuery(
          """SELECT iSeq,
            |  (
            |      SELECT JSON_ARRAYAGG(list.v)
            |      FROM   JSON_TABLE(B.iSeq, '$[*]' COLUMNS (v INT PATH '$')) as list
            |      WHERE  list.v NOT IN (3, 4)
            |    ) as x
            |FROM B
            |""".stripMargin, true)
//
        _ <- rawTransact(
          """UPDATE B
            |SET
            |  iSeq = (
            |      SELECT JSON_ARRAYAGG(list.v)
            |      FROM   JSON_TABLE(B.iSeq, '$[*]' COLUMNS (v INT PATH '$')) as list
            |      WHERE  list.v NOT IN (3, 4)
            |    )
            |WHERE
            |  B.iSeq is not null and
            |  B.id IN(1, 2, 3)
            |""".stripMargin)

        //            |  B.id IN(1, 2, 3, 4, 5)
        //            |  iSet = case B.iSet when isnull(B.iSet) then '[0]' else '[1]' end
        //            |  iSet = case B.iSet when '[1, 2]' then '[0]' else '[1]' end
        //            |  iSet = ifnull(B.iSet, '[0]')

        //            |      FROM   JSON_TABLE(ifnull(B.iSet, '[]'), '$[*]' COLUMNS (v INT PATH '$')) table_1

        // `upsert` has same semantics as `update` with `remove` since we don't insert data
        // Filter by A ids, update/upsert B values
        //        _ <- A.i_.B.iSet.remove(3, 4).update.transact
        //        _ <- A.i_.B.iSet.remove(3, 4).upsert.transact

        // 2 entities left with remaining values
        _ <- A.i.a1.B.iSeq.query.i.get.map(_ ==> List(
          (3, Seq(1, 2)),
          (4, Seq(2)),
        ))

        //        _ <- rawQuery(
        //          """SELECT DISTINCT
        //            |  A.id,
        //            |  A.i,
        //            |  JSON_ARRAYAGG(t_2.vs)
        //            |FROM A
        //            |  LEFT JOIN A_bb_B ON A.id        = A_bb_B.A_id
        //            |  LEFT JOIN B      ON A_bb_B.B_id = B.id,
        //            |  JSON_TABLE(
        //            |    IF(B.iSet is null, '[null]', B.iSet),
        //            |    '$[*]' COLUMNS (vs INT PATH '$')
        //            |  ) t_2
        //            |WHERE
        //            |  A.i IS NOT NULL
        //            |GROUP BY A.id, A.i
        //            |/*HAVING COUNT(*) > 0;*/
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