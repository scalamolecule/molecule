package molecule.document.mongodb.ownership

import molecule.core.util.Executor._
import molecule.document.mongodb.async._
import molecule.document.mongodb.setup.TestSuite_mongodb
import utest._
import scala.language.implicitConversions


object BackRef extends TestSuite_mongodb {


  override lazy val tests = Tests {

    "ref-ref" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- A.i(1).B.i(2)._A.C.i(3).save.transact
        _ <- A.i.B.i._A.C.i.query.get.map(_ ==> List((1, 2, 3)))
      } yield ()
    }


    "embed-ref" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- A.i(1).OwnB.i(2)._A.C.i(3).save.transact
        _ <- A.i.OwnB.i._A.C.i.query.get.map(_ ==> List((1, 2, 3)))
      } yield ()
    }

    "embed-ref 2" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- A.i(1).OwnB.i(2)._A.OwnC.i(3).D.i(4).save.transact
        _ <- A.i.OwnB.i._A.OwnC.i.D.i.query.get.map(_ ==> List((1, 2, 3, 4)))

        _ <- A.i(1).B.i(2)._A.OwnC.i(3).D.i(4).save.transact
        _ <- A.i.B.i._A.OwnC.i.D.i.query.get.map(_ ==> List((1, 2, 3, 4)))

        _ <- A.i(1).OwnB.i(2)._A.C.i(3).D.i(4).save.transact
        _ <- A.i.OwnB.i._A.C.i.D.i.query.get.map(_ ==> List((1, 2, 3, 4)))
      } yield ()
    }


    "ref-embed" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- A.i(1).B.i(2)._A.OwnC.i(3).save.transact
        _ <- A.i.B.i._A.OwnC.i.query.get.map(_ ==> List((1, 2, 3)))
      } yield ()
    }

    "ref-embed 2" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- A.i(1).B.i(2)._A.C.i(3).OwnD.i(4).save.transact
        _ <- A.i.B.i._A.C.i.OwnD.i.query.get.map(_ ==> List((1, 2, 3, 4)))
      } yield ()
    }


    "embed-embed" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- A.i(1).OwnB.i(2)._A.OwnC.i(3).save.i.transact
        _ <- A.i.OwnB.i._A.OwnC.i.query.get.map(_ ==> List((1, 2, 3)))
      } yield ()
    }
  }
}
