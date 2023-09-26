package molecule.sql.mysql.test

import molecule.base.error.{ExecutionError, ModelError}
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs.A
import molecule.sql.mysql.async._
import molecule.sql.mysql.setup.TestSuite_mysql
import utest._
import scala.collection.immutable.List
import scala.language.implicitConversions

object AdhocJVM_mysql extends TestSuite_mysql {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        //        _ <- Ns.int.insert(1).i.transact
        //        _ <- Ns.int.query.get.map(_ ==> List(1))


        _ <- Ns.i.int.insert(List(
          (1, int1),
          (2, int2),
          (2, int2),
          (2, int3),
        )).transact

        _ <- Ns.i.int.a1.query.get.map(_ ==> List(
          (1, int1),
          (2, int2), // 2 rows coalesced
          (2, int3),
        ))

        // Distinct values are returned in a Set
        _ <- Ns.i.a1.int(distinct).query.get.map(_ ==> List(
          (1, Set(int1)),
          (2, Set(int2, int3)),
        ))

        _ <- Ns.int(distinct).query.get.map(_.head ==> Set(
          int1, int2, int3
        ))

        //        _ <- rawTransact(
        //          """insert into Ns_ints(ints) values (3), (4)"""
        //        )
        //
        //        _ <- rawQuery(
        //          """SELECT
        //            |     ints
        //            | FROM Ns_ints
        //            |""".stripMargin)
        //
        //        _ <- rawQuery(
        //          """SELECT
        //            |     Ns_id,
        //            |     Ns_ints_id
        //            | FROM Ns_ints_
        //            |""".stripMargin)
        //
        //        _ <- rawQuery(
        //          """SELECT a.*, JSON_ARRAY(b.category_id, b.category_name, b.category_description) AS cat_info
        //            |FROM product_table a
        //            |LEFT JOIN category b ON a.category_id = b.category_id
        //            |WHERE a.id = 17
        //            |""".stripMargin)
        //
        ////        _ <- rawQuery(
        ////          """SELECT JSON_array(
        ////            |     ints
        ////            | ) FROM Ns_ints
        ////            |
        ////            |""".stripMargin)
        //
        //        _ <- rawQuery(
        //          """SELECT
        //            |(SELECT JSON_array(
        //            |     ints
        //            | ) FROM Ns_ints ) AS _category
        //            |
        //            |;
        //            |""".stripMargin)
        //
        //        _ <- Ns.refs.query.get.map(_ ==> List(Set(1, 2)))
        //        _ <- Ns.ints.query.get.map(_ ==> List(Set(1, 2)))


      } yield ()
    }


    //    "refs" - refs { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Refs._
    //      for {
    //        id <- A.i(1).B.i(2).C.i(3).save.transact.map(_.id)
    //        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((1, 2, 3)))
    //
    //      } yield ()
    //    }
    //
    //
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