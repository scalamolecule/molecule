package molecule.document.mongodb

import molecule.base.ast._
import molecule.base.error.{ExecutionError, ModelError}
import molecule.core.util.AggrUtils
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.document.mongodb.async._
import molecule.document.mongodb.setup.TestSuite_mongodb
import utest._
import scala.language.implicitConversions
import molecule.boilerplate.ast.Model._

object AdhocJVM_mongodb extends TestSuite_mongodb with AggrUtils {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {


                _ <- Ns.i(1).save.i.transact
                _ <- Ns.i.query.get.map(_ ==> List(1))
      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {

//        _ <- A.s.i.B.i.insert(
//          ("a", 1, 2),
//          ("b", 3, 3),
//          ("c", 5, 4),
//        ).transact
//
//        _ <- A.s.i.B.i(A.i_).query.get.map(_ ==> List(("b", 3, 3)))
//        _ <- A.s.i(B.i_).B.i.query.get.map(_ ==> List(("b", 3, 3)))






        _ <- A.s.i.OwnB.i.insert(
          ("a", 1, 2),
          ("b", 3, 3),
          ("c", 5, 4),
        ).transact

        _ <- A.s.i.<(B.i_).OwnB.i.query.get.map(_ ==> List(("a", 1, 2)))

        _ <- A.s.i.OwnB.i.<(A.i_).query.get.map(_ ==> List(("c", 5, 4)))







//        _ <- A.i.B.i.insert(
//          (2, 3),
//          (1, 4),
//          (1, 3),
//          (7, 3)
//        ).transact
//
//        _ <- A.i.<(B.i_).a1.B.i.a2.query.get.map(_ ==> List((1, 3), (1, 4), (2, 3)))
//
//
//        _ <- A.i.OwnB.i.insert(
//          (2, 3),
//          (1, 4),
//          (1, 3),
//          (7, 3)
//        ).transact
//
//        _ <- A.i.<(B.i_).a1.OwnB.i.a2.query.get.map(_ ==> List((1, 3), (1, 4), (2, 3)))

      } yield ()
    }


    "unique" - unique { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Uniques._
      for {
        //            _ <- Uniques.i(1).save.transact


        _ <- Uniques.i(1).i(2).int_(1).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute Uniques.i"
          }

      } yield ()
    }


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
