package molecule.sql.h2

import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup.{Test, TestUtils}
import molecule.sql.h2.setup.DbProviders_h2
import molecule.sql.h2.sync._
import scala.language.implicitConversions


class AdhocJVM_h2_sync extends Test with DbProviders_h2 with TestUtils {


  "commit" - types { implicit conn =>
    Entity.int.insert(1 to 7).transact
//    Entity.int.xx.insert(1 to 7).transact
    Entity.int(count).query.get.head ==> 7

    Entity.int_.delete.transact
    Entity.int(count).query.get.head ==> 0
  }


}
