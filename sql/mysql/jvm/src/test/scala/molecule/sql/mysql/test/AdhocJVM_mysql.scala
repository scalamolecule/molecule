package molecule.sql.mysql.test

import java.io.File
import molecule.base.error.{ExecutionError, ModelError}
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
      for {
//        _ <- Ns.ints.insert(Set(1, 2)).transact
//        _ <- Ns.ints.query.get.map(_ ==> List(Set(1, 2)))


        //        _ <- rawQuery(
        //          """SELECT JSON_ARRAYAGG(t1.v)
        //            |FROM Ns, JSON_TABLE(Ns.ints, '$[*]' COLUMNS (v int PATH '$')) t1
        //            |WHERE t1.v NOT IN(6)
        //            |""".stripMargin, true)

        id <- Ns.ii(Set(int1, int2)).ints(Set(int3, int4)).save.transact.map(_.id)

        // Replace value
        _ <- Ns(id).ii.swap(int2 -> int3).ints.swap(int4 -> int5).update.i.transact
        _ <- Ns.ii.ints.query.get.map(_ ==> List((Set(1, 3), Set(3, 5))))

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
    //        //            _ <- Uniques.i(1).save.transact
    //
    //        //            _ <- Uniques.int.insert(1).transact
    //        _ <- Uniques.int.Ref.int.insert((2, 20), (3, 30)).i.transact
    //
    //      } yield ()
    //    }


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