package molecule.db.datomic.test.attrTypes

import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object CardOneTypes extends DatomicTestSuite {


  lazy val tests = Tests {

    "mandatory" - cardOneTypes { implicit conn =>

      One.str(str1).save

      One.str.int.insert.apply(("Bob", 42), ("Liz", 35)).run

      One.str.int.query.get ==> List(("Liz", 35), ("Bob", 42))
    }
  }

}
