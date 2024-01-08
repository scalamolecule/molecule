package molecule.document.mongodb

import molecule.base.error.{ExecutionError, ModelError}
import molecule.core.util.AggrUtils
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.document.mongodb.async._
import molecule.document.mongodb.setup.TestSuite_mongodb
import utest._
import scala.language.implicitConversions


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


        //        id <- A.ownBb(Set(ref1, ref2)).save.transact.map(_.id)



        id <- A.bb(Set(ref1, ref2)).save.transact.map(_.id)

        //        _ <- A(id).bb(Set(ref3, ref4)).update.transact
        //        _ <- A.bb.query.get.map(_.head ==> Set(ref3, ref4))
        //
        //        // Apply Seq of values
        //        _ <- A(id).bb(Set(ref4, ref5)).update.transact
        //        _ <- A.bb.query.get.map(_.head ==> Set(ref4, ref5))
        //
        //        // Apply empty Seq of values (deleting all values!)
        //        _ <- A(id).bb(Seq.empty[String]).update.transact
        //        _ <- A.bb.query.get.map(_ ==> Nil)
        //
        //        _ <- A(id).bb(Set(ref1, ref2)).update.transact
        //
        //        // Delete all (apply no values)
        //        _ <- A(id).bb().update.transact
        //        _ <- A.bb.query.get.map(_ ==> Nil)

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
