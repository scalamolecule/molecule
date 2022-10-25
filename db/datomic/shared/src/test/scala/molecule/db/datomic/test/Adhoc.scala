package molecule.db.datomic.test

import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object Adhoc extends DatomicTestSuite {


  lazy val tests = Tests {

    "core" - cardOneTypes { implicit conn =>

      One.str.int.insert(("Bob", 42), ("Liz", 35)).run

      One.str.int.query.get ==> List(("Liz", 35), ("Bob", 42))
    }
  }

}
