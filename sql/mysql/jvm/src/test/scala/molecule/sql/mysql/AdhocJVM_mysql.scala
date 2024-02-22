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
      val a = (1, Set(0, 1, 2), Set(1, 2, 3))
      val b = (2, Set(2, 3), Set(2, 3))
      val c = (3, Set(4), Set(3))
      for {


        _ <- Ns.i.ii.ints.insert(a, b, c).transact

//        _ <- rawQuery(
//          """SELECT DISTINCT
//            |  Ns.i,
//            |  Ns.ii,
//            |  JSON_ARRAYAGG(t_3.vs)
//            |FROM Ns,
//            |  JSON_TABLE(
//            |    IF(Ns.ints IS NULL, '[null]', Ns.ints),
//            |    '$[*]' COLUMNS (vs INT PATH '$')
//            |  ) t_3
//            |WHERE
//            |  Ns.ii   = Ns.ints AND
//            |  Ns.i    IS NOT NULL AND
//            |  Ns.ii   IS NOT NULL AND
//            |  Ns.ints IS NOT NULL
//            |GROUP BY Ns.i, Ns.ii
//            |HAVING COUNT(*) > 0;
//            |""".stripMargin, true)


//        _ <- Ns.i.ii(Ns.ints).query.i.get.map(_ ==> List(b))
        _ <- Ns.i.ii.has(Ns.ints).query.get.map(_ ==> List(b)) // Ns.ii and Ref.ints

      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      val a = (1, Set(1, 2), Set(1, 2, 3), 3)
      val b = (2, Set(2, 3), Set(2, 3), 3)
      val c = (2, Set(4), Set(4), 4)

      val d = (2, Set(4), Set(3), 4)

      for {
        List(_, a2, a3) <- A.i.ii.B.ii.i.insert(a, b, c).transact.map(_.ids)


        //        _ <- rawQuery(
        //          """SELECT DISTINCT
        //            |  A.id,
        //            |  A.i,
        //            |  JSON_ARRAYAGG(t_2.vs)
        //            |FROM A
        //            |  LEFT JOIN A_bb_B ON A.id        = A_bb_B.A_id
        //            |  LEFT JOIN B      ON A_bb_B.B_id = B.id,
        //            |  JSON_TABLE(
        //            |    IF(B.ii is null, '[null]', B.ii),
        //            |    '$[*]' COLUMNS (vs INT PATH '$')
        //            |  ) t_2
        //            |WHERE
        //            |  A.i IS NOT NULL
        //            |GROUP BY A.id, A.i
        //            |/*HAVING COUNT(*) > 0;*/
        //            |""".stripMargin, true)


        _ <- A.i.ii_(B.ii_).B.ii.query.get.map(_ ==> List(
          (2, Set(2, 3, 4)) // Set(2, 3) and Set(4) are coalesced to one Set
        ))
        _ <- A.i.ii_.B.ii(A.ii_).query.get.map(_ ==> List(
          (2, Set(2, 3, 4))
        ))

        // To get un-coalesced Sets, separate by ids
        _ <- A.id.a1.i.ii_(B.ii_).B.ii.query.get.map(_ ==> List(
          (a2, 2, Set(2, 3)),
          (a3, 2, Set(4))
        ))
        _ <- A.id.a1.i.ii_.B.ii(A.ii_).query.get.map(_ ==> List(
          (a2, 2, Set(2, 3)),
          (a3, 2, Set(4))
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


    "validation" - validation { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Validation._
      for {
        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)

      } yield ()
    }
  }
}