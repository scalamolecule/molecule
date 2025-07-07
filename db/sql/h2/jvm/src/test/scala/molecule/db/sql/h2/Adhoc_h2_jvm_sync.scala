package molecule.db.sql.h2

import boopickle.Default.*
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.sql.h2.setup.DbProviders_h2
import molecule.db.sql.h2.sync.*

class Adhoc_h2_jvm_sync extends MUnit with DbProviders_h2 with TestUtils {


  "basic" - types { implicit conn =>
    import molecule.db.compliance.domains.dsl.Types.*

    val List(a, b) = Entity.int.insert.apply(1, 2).transact.ids
    Entity.int(3).save.transact
    Entity.int.a1.query.get ==> List(1, 2, 3)
    Entity(a).int(10).update.transact
    Entity(b).delete.transact
    Entity.int.a1.query.get ==> List(3, 10)
  }


  //  "Subscription" - types { implicit conn =>
  //    var intermediaryCallbackResults = List.empty[List[Int]]
  //
  //    // Initial data
  //    Entity.i(1).save.transact
  //
  //    // Start subscription
  //    Entity.i.query.subscribe { freshResult =>
  //      intermediaryCallbackResults = intermediaryCallbackResults :+ freshResult
  //    }
  //
  //    // Mutations to be monitored by subscription
  //    val id = Entity.i(2).save.transact.id
  //    Entity.i.a1.query.get ==> List(1, 2)
  //
  //    Entity.i.insert(3, 4).transact
  //    Entity.i.a1.query.get ==> List(1, 2, 3, 4)
  //
  //    Entity(id).i(20).update.transact
  //    Entity.i.a1.query.get ==> List(1, 3, 4, 20)
  //
  //    Entity(id).delete.transact
  //    Entity.i.a1.query.get ==> List(1, 3, 4)
  //
  //    // Mutations with no callback-involved attributes don't call back
  //    Entity.string("foo").save.transact
  //
  //    // Callback catched all intermediary results correctly
  //    intermediaryCallbackResults.map(_.sorted) ==> List(
  //      List(1, 2), // query result after 2 was saved
  //      List(1, 2, 3, 4), // query result after 3 and 4 were inserted
  //      List(1, 3, 4, 20), // query result after 2 was updated to 20
  //      List(1, 3, 4), // query result after 20 was deleted
  //    )
  //  }


  //  "commit" - refs { implicit conn =>
  //    import molecule.db.compliance.domains.dsl.Refs.*
  //
  //    A.i.insert(1, 2, 3).transact
  //    A.i.query.stream.toList.sorted ==> List(1, 2, 3)
  //  }
}
