package molecule.sql.h2

import molecule.coreTests.dataModels.dsl.Types._
import molecule.sql.h2.setup.TestSuite_h2
import molecule.sql.h2.sync._
import utest._
import scala.language.implicitConversions


object AdhocJVM_h2_sync extends TestSuite_h2 {


  override lazy val tests = Tests {

    "commit" - types { implicit conn =>
      Ns.int.insert(1 to 7).transact
      Ns.int(count).query.get.head ==> 7

      Ns.int_.delete.transact
      Ns.int(count).query.get.head ==> 0
    }


  }
}
