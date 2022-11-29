package molecule.db.datomic.test.composite

import molecule.db.datomic.setup.DatomicTestSuite
import utest._

import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._

object CompositeBasic extends DatomicTestSuite {


  lazy val tests = Tests {

    "basic" - refs { implicit conn =>

      (Ns.i.s + R2.s.i).insert((1, "a"), ("b", 2)).transact

      (Ns.i.s + R2.s.i).query.get ==> List(((1, "a"), ("b", 2)))
      (Ns.i + R2.s.i).query.get ==> List((1, ("b", 2)))
      (Ns.i_ + R2.s.i).query.get ==> List(("b", 2))

    }
  }
}
