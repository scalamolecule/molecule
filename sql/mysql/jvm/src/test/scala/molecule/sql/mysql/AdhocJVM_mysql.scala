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
      val a = (1, Set(1, 2), Set(1, 2, 3), 3)
      val b = (2, Set(2, 3), Set(2, 3), 3)
      val c = (2, Set(4), Set(4), 4)

      val d = (2, Set(4), Set(3), 4)

      for {
        List(_, a2, a3) <- A.i.iSet.B.iSet.i.insert(a, b, c).transact.map(_.ids)


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


    "validation" - validation { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Validation._
      for {
        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)

      } yield ()
    }
  }
}