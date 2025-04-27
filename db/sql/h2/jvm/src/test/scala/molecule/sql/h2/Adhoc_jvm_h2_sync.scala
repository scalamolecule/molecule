package molecule.sql.h2

import boopickle.Default.*
import molecule.coreTests.domains.dsl.Types.Entity
import molecule.coreTests.setup.{Test, TestUtils}
import molecule.sql.h2.setup.DbProviders_h2
import molecule.sql.h2.sync.*


class Adhoc_jvm_h2_sync extends Test with DbProviders_h2 with TestUtils {


  "basic" - types { implicit conn =>
    val List(a, b) = Entity.int.insert(1, 2).transact.ids
    Entity.int(3).save.transact
    Entity.int.a1.query.get ==> List(1, 2, 3)
    Entity(a).int(10).update.transact
    Entity(b).delete.transact
    Entity.int.a1.query.get ==> List(3, 10)
  }


//  "commit" - refs { implicit conn =>
//    import molecule.coreTests.domains.dsl.Refs.*
//
//    A.i.insert(1, 2, 3).transact
//    A.i.query.stream.toList.sorted ==> List(1, 2, 3)
//  }
}
