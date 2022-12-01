package molecule.db.datomic.test.crud.save

import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object SaveRef extends DatomicTestSuite {


  lazy val tests = Tests {

    "Card one" - refs { implicit conn =>
      Ns.i(1).R1.i(2).save.transact
      Ns.i.R1.i.query.get ==> List((1, 2))
    }


    "Card many" - refs { implicit conn =>
      Ns.i(1).Rs1.i(2).save.transact
      Ns.i.Rs1.i.query.get ==> List((1, 2))
    }


    "Backref" - refs { implicit conn =>
      Ns.i(1).Rs1.i(2)._Ns.s("a").save.transact
      Ns.i.Rs1.i._Ns.s.query.get ==> List((1, 2, "a"))
    }


    "Composite" - refs { implicit conn =>
      (Ns.i(1) + R2.i(2)).save.transact
      (Ns.i + R2.i).query.get ==> List((1, 2))

      (Ns.R1.i(1) + R2.i(2)).save.transact
      (Ns.R1.i + R2.i).query.get ==> List((1, 2))
    }


    "TxMetaData" - refs { implicit conn =>
      Ns.i(1).Tx(R2.i(2)).save.transact
      Ns.i.Tx(R2.i).query.get ==> List((1, 2))
    }
  }
}
