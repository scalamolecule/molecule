package molecule.document.mongodb

import com.mongodb.client.model.Filters
import molecule.base.error.ModelError
import molecule.core.util.Executor._
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
        _ <- Ns.i(1).save.transact
        _ <- Ns.i.query.get.map(_ ==> List(1))


      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        e1 <- A.i.B.i.insert(
          (1, 10),
          (2, 20)
        ).transact.map(_.id)

        // 2 entities, each referencing another entity
        _ <- A.i.a1.B.i.query.get.map(_ ==> List(
          (1, 10),
          (2, 20)
        ))
        // 2 referenced entities
        _ <- B.i.a1.query.get.map(_ ==> List(10, 20))

        _ <- A(e1).delete.transact

        // 1 entity with 1 referenced entity left
        _ <- A.i.B.i.query.get.map(_ ==> List((2, 20)))

        // Referenced entities are not deleted
        _ <- B.i.a1.query.get.map(_ ==> List(10, 20))
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
