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

        _ <- Ns("123456789012345678901234").i(1).Ref.i(2).upsert.transact
          .map(_ ==> "Unexpected success")
          .recover {
            //            case e =>

            case e@ModelError(err) =>
//              e.printStackTrace()
              err ==> "Can't upsert referenced attributes. Please update instead."
          }

        //        _ <- Ns.i(1).save.i.transact
        //        _ <- Ns.i.query.get.map(_ ==> List(1))
      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {

        id <- A.i(1).B.i(2).C.i(3).save.transact.map(_.id)
        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((1, 2, 3)))

        // Add extra B and C entities for checking that these are not updated
        _ <- B.i(42).save.transact
        _ <- C.i(43).save.transact


        // A
        _ <- A(id).i(10).update.transact
        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((10, 2, 3)))

        // A + B
        _ <- B.i.query.get.map(_ ==> List(2, 42))
        _ <- A(id).i(11).B.i(20).update.transact
        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((11, 20, 3)))

        // Only the first B entity was updated
        _ <- B.i.query.get.map(_ ==> List(20, 42))

        // B
        _ <- A(id).B.i(21).update.transact
        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((11, 21, 3)))

        // A + B + C
        _ <- A(id).i(12).B.i(22).C.i(30).update.transact
        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((12, 22, 30)))

        // A + C
        _ <- A(id).i(13).B.C.i(31).update.transact
        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((13, 22, 31)))

        // B + C
        _ <- A(id).B.i(23).C.i(32).update.transact
        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((13, 23, 32)))

        // C
        _ <- A(id).B.C.i(33).update.transact
        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((13, 23, 33)))



        //        _ <- A.i.ownB_?.query.get
        //          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
        //            err ==> "Can't query for non-existing ids of embedded documents in MongoDB."
        //          }

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
