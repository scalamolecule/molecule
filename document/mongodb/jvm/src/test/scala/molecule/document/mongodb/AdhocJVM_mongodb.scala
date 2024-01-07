package molecule.document.mongodb

import molecule.base.error.ModelError
import molecule.core.util.AggrUtils
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types.{Ns, Ref}
import molecule.document.mongodb.async._
import molecule.document.mongodb.setup.TestSuite_mongodb
import utest._
import scala.collection.mutable.ListBuffer
import scala.concurrent.Future
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

        id <- A.i(1).OwnB.i(2)._A.C.i(3).save.transact.map(_.id)
        _ <- A.i.OwnB.i._A.C.i.query.get.map(_ ==> List((1, 2, 3)))

        // Updating A.OwnB.i and A.C.i
        _ <- A(id).i(10).OwnB.i(20)._A.C.i(30).update.transact
        _ <- A.i.OwnB.i._A.C.i.query.get.map(_ ==> List((10, 20, 30)))


        //        id <- A.i(1).B.i(2)._A.OwnC.i(3).save.transact.map(_.id)
        //        _ <- A.i.B.i._A.OwnC.i.query.get.map(_ ==> List((1, 2, 3)))
        //
        //        // Updating A.B.i and A.OwnC.i
        //        _ <- A(id).i(10).B.i(20)._A.OwnC.i(30).update.transact
        //        _ <- A.i.B.i._A.OwnC.i.query.get.map(_ ==> List((10, 20, 30)))


        //        id <- A.i(1).OwnB.i(2)._A.OwnC.i(3).save.transact.map(_.id)
        //        _ <- A.i.OwnB.i._A.OwnC.i.query.get.map(_ ==> List((1, 2, 3)))
        //
        //        // Updating A.OwnB.i and A.OwnC.i
        //        _ <- A(id).i(10).OwnB.i(20)._A.OwnC.i(30).update.transact
        //        _ <- A.i.OwnB.i._A.OwnC.i.query.get.map(_ ==> List((10, 20, 30)))


        //        _ <- A.i.ownB_?.query.get
        //          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
        //            err ==> "Can't query for non-existing ids of embedded documents in MongoDB."
        //          }

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
