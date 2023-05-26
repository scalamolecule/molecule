package molecule.sql.jdbc.test

import molecule.coreTests.dataModels.core.dsl.Types._
//import molecule.sql.jdbc.async._
import molecule.sql.jdbc.setup.JdbcTestSuite
import molecule.sql.jdbc.sync._
import utest._
import scala.language.implicitConversions

object AdhocJdbcSync extends JdbcTestSuite {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
//      Ns.i(3).save.transact
      Ns.i.insert(3).transact
      Ns.i.query.get ==> List(3)
    }


  }
}
