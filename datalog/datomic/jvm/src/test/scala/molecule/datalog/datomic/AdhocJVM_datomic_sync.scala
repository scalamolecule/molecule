package molecule.datalog.datomic

import molecule.coreTests.dataModels.dsl.Types._
import molecule.datalog.datomic.setup.TestSuite_datomic
import molecule.datalog.datomic.sync._
import utest._
import scala.language.implicitConversions


object AdhocJVM_datomic_sync extends TestSuite_datomic {

  override lazy val tests = Tests {

    "commit" - types { implicit conn =>
      Ns.int.insert(1 to 7).transact
      Ns.int(count).query.get.head ==> 7

      Ns.int_.delete.transact
      Ns.int(count).query.i.get.head ==> 0
    }
  }
}
