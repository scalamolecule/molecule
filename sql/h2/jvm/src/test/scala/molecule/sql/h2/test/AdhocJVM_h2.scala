package molecule.sql.h2.test

import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.sql.h2.async._
import molecule.sql.h2.setup.TestSuite_h2
import utest._
import scala.language.implicitConversions

object AdhocJVM_h2 extends TestSuite_h2 {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        _ <- Ns.int.insert(1).transact
        _ <- Ns.int.query.get.map(_ ==> List(1))


      } yield ()
    }

    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        id <- A.i(1).B.i(2).C.i(3).save.transact.map(_.id)
        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((1, 2, 3)))


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