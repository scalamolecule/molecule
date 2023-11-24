package molecule.document.mongodb

import com.mongodb.client.model.Filters
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs.B
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.document.mongodb.AdhocJVM_mongodb.int2
import molecule.document.mongodb.async._
import molecule.document.mongodb.setup.TestSuite_mongodb
import utest._
import scala.collection.immutable.List
import scala.language.implicitConversions


object AdhocJVM_mongodb extends TestSuite_mongodb {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        _ <- Ns.i.float.insert(List(
          (1, float1)
        )).transact

        _ <- Ns.float.query.get.map(_.head ==> float1)

      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- A.i.B.i.C.i.insert(List(
          (1, 1, 1),
          (2, 2, 2),
          (2, 2, 2),
          (2, 2, 3),
        )).transact

        //        _ <- A.B.C.i(count).query.get.map(_ ==> List(4))
        _ <- A.i.a1.B.i.C.i(count).query.i.get.map(_ ==> List(
          (1, 1, 1),
          (2, 2, 3)
        ))

        _ <- A.B.C.i(countDistinct).query.get.map(_ ==> List(3))
        _ <- A.i.a1.B.i.C.i(countDistinct).query.i.get.map(_ ==> List(
          (1, 1, 1),
          (2, 2, 2)
        ))
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
