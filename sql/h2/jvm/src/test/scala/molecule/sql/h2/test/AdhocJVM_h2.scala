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
        _ <- Ns.int.insert(1).i.transact
        _ <- Ns.int.query.i.get.map(_ ==> List(1))


//        _ <- Ns.int.insert(1, 2).transact

//        _ <- Ns.id(count).a1.s_.query.get.map(_ ==> List(3))


//        _ <- Ns.ints(Set(1, 2)).save.transact
//        _ <- Ns.ints.query.get.map(_ ==> List(Set(1, 2)))

      } yield ()
    }

    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
//        id <- A.i(1).B.i(2).C.i(3).save.transact.map(_.id)
//        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((1, 2, 3)))


        _ <- A.Bb.i.insert(1).transact
//        _ <- A.Bb.i.query.get.map(_ ==> List(1))
//
//        _ <- A.i.Bb.i.insert(1, 2).transact
//        _ <- A.i.Bb.i.query.get.map(_ ==> List((1, 2)))
//
//
//        _ <- A.Bb.Cc.i.insert(1).transact
//        _ <- A.Bb.Cc.i.query.get.map(_ ==> List(1))
//
//        _ <- A.Bb.i.Cc.i.insert(1, 2).transact
//        _ <- A.Bb.i.Cc.i.query.get.map(_ ==> List((1, 2)))
//
//        _ <- A.i.Bb.Cc.i.insert(1, 2).transact
//        _ <- A.i.Bb.Cc.i.query.get.map(_ ==> List((1, 2)))
//
//        _ <- A.i.Bb.i.Cc.i.insert(1, 2, 3).transact
//        _ <- A.i.Bb.i.Cc.i.query.get.map(_ ==> List((1, 2, 3)))

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