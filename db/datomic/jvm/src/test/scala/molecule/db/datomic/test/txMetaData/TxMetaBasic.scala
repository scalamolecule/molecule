package molecule.db.datomic.test.txMetaData

import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.api.Molecule_03
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object TxMetaBasic extends DatomicTestSuite {


  lazy val tests = Tests {

    "basic" - refs { implicit conn =>
      Ns.i.Tx(R2.i).insert(1, 2).transact
      Ns.i.Tx(R2.i).query.get ==> List((1, 2))
    }

//    "multiple attrs" - refs { implicit conn =>
//      Ns.i.Tx(R2.i.s.ii).insert(1, 2, ).transact
//      Ns.i.Tx(R2.i.s.ii).query.get ==> List((1, 2))
//    }
//
//
//    "1 level" - refs { implicit conn =>
//      Ns.i.Rs1.*(R1.i).Tx(R4.i).insert(1, List(2), 3).transact
//      Ns.i.Rs1.*(R1.i).Tx(R4.i).query.get ==> List((1, List(2), 3))
//    }


  }
}
