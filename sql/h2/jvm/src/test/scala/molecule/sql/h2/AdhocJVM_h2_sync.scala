package molecule.sql.h2

import boopickle.Default._
import molecule.core.ast.DataModel.{AttrOneManInt, V}
import molecule.core.marshalling.deserialize.UnpickleTpls
import molecule.core.marshalling.serialize.PickleTpls
import molecule.coreTests.setup.{Test, TestUtils}
import molecule.sql.h2.setup.DbProviders_h2



import scala.language.implicitConversions


class AdhocJVM_h2_sync extends Test with DbProviders_h2 with TestUtils {

  "commit" - types { implicit conn =>
    //    Entity.int.insert(1 to 7).transact
    //    Entity.int(count).query.get.head ==> 7
    //
    //    Entity.int_.delete.transact
    //    Entity.int(count).query.get.head ==> 0
    //
    //

    1 ==> 1

  }
}
