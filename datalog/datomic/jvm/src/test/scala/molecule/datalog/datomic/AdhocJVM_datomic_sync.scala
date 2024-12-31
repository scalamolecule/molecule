package molecule.datalog.datomic

import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup.{Test, TestUtils}
import molecule.datalog.datomic.setup.DbProviders_datomic
import molecule.datalog.datomic.sync._
import scala.language.implicitConversions


class AdhocJVM_datomic_sync extends Test with DbProviders_datomic with TestUtils {


  "commit" - types { implicit conn =>
    Entity.int.insert(1 to 7).transact
    Entity.int(count).query.get.head ==> 7

    Entity.int_.delete.transact
    Entity.int(count).query.i.get.head ==> 0
  }
}
