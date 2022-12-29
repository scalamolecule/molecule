package molecule.db.datomic.test.crud.update.set

import molecule.base.util.exceptions.MoleculeException
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Unique._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object UpdateSet_uniqueAttr extends DatomicTestSuite {


  lazy val tests = Tests {

    "Semantics" - unique { implicit conn =>
      for {
        _ <- Unique.ints_(1).s("b").update.transact
          .map(_ ==> "Unexpected success").recover { case MoleculeException(err, _) =>
          err ==> "Can only lookup entity with card-one attribute value. Found:\n" +
            "AttrSetTacInt(Unique,ints,Appl,List(Set(1)),None,None,None)"
        }
      } yield ()
    }
  }
}
