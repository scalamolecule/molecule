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

        _ <- Ns.i.insert(1).transact
        _ <- Ns.i.int_?.query.get.map(_ ==> List((1, None)))

        // Update entities with non-unique `i` attribute with value 1
        // Updating a non-asserted value doesn't insert it (nothing is updated)
        _ <- Ns.i_(1).int(2).update.transact
        _ <- Ns.i.int_?.query.get.map(_ ==> List((1, None)))

        // Upserting a non-asserted value inserts it
        _ <- Ns.i_(1).int(2).upsert.transact
        _ <- Ns.i.int_?.query.get.map(_ ==> List((1, Some(2))))

        // Updating an asserted value updates it
        _ <- Ns.i_(1).int(3).upsert.transact
        _ <- Ns.i.int.query.get.map(_ ==> List((1, 3)))

        // Upserting an asserted value updates it
        _ <- Ns.i_(1).int(4).upsert.transact
        _ <- Ns.i.int.query.get.map(_ ==> List((1, 4)))


                //        _ <- Ns.i(1).save.i.transact
        //        _ <- Ns.i.query.get.map(_ ==> List(1))
      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {

        _ <- A.i(1).i(2).save.transact
          .map(_ ==> "Unexpected success").recover { case e@ModelError(err) =>
            e.printStackTrace()
            err ==> "Can't transact duplicate attribute A.ix"
          }
        /*
------------------ 0  0  -A.i


  List()
------------------ 0  0  -A.i


  List(-A.i)
         */

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
