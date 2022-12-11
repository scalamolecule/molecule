package molecule.db.datomic.test.crud.updateSet

import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Unique._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object UpdateSet_uniqueAttrs extends DatomicTestSuite {


  lazy val tests = Tests {

    "Semantics" - unique { implicit conn =>
      intercept[MoleculeException](
        Unique.ints_(1).s("b").update.transact
      ).message ==> "Can only lookup entity with card-one attribute value. Found:\n" +
        "AttrSetTacInt(Unique,ints,Appl,List(Set(1)),None,None,None)"
    }
  }
}
