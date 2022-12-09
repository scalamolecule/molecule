package molecule.db.datomic.test.crud.updateOne

import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object UpdateOne_free extends DatomicTestSuite {


  lazy val tests = Tests {



        "Matching" - types { implicit conn =>
          Ns.int_.>(2).string("new value").update.multiple.transact ==> List(2)
        }
  }
}
