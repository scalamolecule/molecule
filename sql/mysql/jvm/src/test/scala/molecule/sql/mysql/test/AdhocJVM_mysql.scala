package molecule.sql.mysql.test

import molecule.base.error.{ExecutionError, ModelError}
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs.A
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.sql.mysql.async._
import molecule.sql.mysql.setup.TestSuite_mysql
import utest._
import scala.collection.immutable.{List, Seq, Set}
import scala.language.implicitConversions

object AdhocJVM_mysql extends TestSuite_mysql {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {

        //        _ <- rawQuery(
        //          """SELECT DISTINCT
        //            |  GROUP_CONCAT(DISTINCT t_1.vs ORDER BY rand() DESC SEPARATOR 0x1D)
        //            |FROM Ns,
        //            |  JSON_TABLE(Ns.ints, '$[*]' COLUMNS (vs int PATH '$')) t_1
        //            |WHERE
        //            |  Ns.ints IS NOT NULL;
        //            |""".stripMargin, true)

        _ <- Ns.ints.insert(Set(1, 2)).transact
        _ <- Ns.ints.query.get.map(_ ==> List(Set(1, 2)))
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