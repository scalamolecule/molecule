package molecule.db.datomic.test.ref.nested

import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object NestedRefs extends DatomicTestSuite {


  lazy val tests = Tests {


    "Nested data not allowed in save" - refs { implicit conn =>

    }
  }
}
