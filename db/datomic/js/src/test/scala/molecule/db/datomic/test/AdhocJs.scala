package molecule.db.datomic.test

import boopickle.Default._
import molecule.base.util.exceptions.MoleculeException
import molecule.core.util.Executor._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AdhocJs extends DatomicTestSuite {

  lazy val tests = Tests {

    //    "types" - types { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Types._
    //      for {
    //
    //        _ <- Ns.i(1).save.transact
    //
    //      } yield ()
    //    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- (Ns.i(1) + R2.i(2)).insert(1, 2).transact
          .map(_ ==> "Unexpected success").recover { case MoleculeException(err, _) =>
          err ==> "Can't insert attributes with an applied value. Found:\n" +
            "AttrOneManInt(Ns,i,Appl,List(1),None,None,None)"
        }


      } yield ()
    }
  }
}
