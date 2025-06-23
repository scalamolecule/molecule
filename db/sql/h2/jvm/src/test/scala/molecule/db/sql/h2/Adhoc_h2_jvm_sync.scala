package molecule.db.sql.h2

import boopickle.Default.*
import molecule.base.metaModel.*
import molecule.core.dataModel as _dm
import molecule.core.dataModel.*
import molecule.core.dataModel.Keywords.Kw
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.core.action.Query
import molecule.db.core.api.*
import molecule.db.core.api.expression.*
import molecule.db.core.ops.ModelTransformations_
import molecule.db.sql.h2.setup.DbProviders_h2
import molecule.db.sql.h2.sync.*
import scala.Tuple.{Append, Init}

class Adhoc_h2_jvm_sync extends MUnit with DbProviders_h2 with TestUtils {


  "basic" - types { implicit conn =>
    import molecule.db.compliance.domains.dsl.Types.*
    Entity.int.i.insert.apply((2, 1), (3, 2))

    //    implicit def toTuple1[A](a: A): Tuple1[A] = Tuple1(a)


    val List(a, b) = Entity.int.insert.apply(1, 2).transact.ids
    Entity.int(3).save.transact
    Entity.int.a1.query.get ==> List(1, 2, 3)
    Entity(a).int(10).update.transact
    Entity(b).delete.transact
    Entity.int.a1.query.get ==> List(3, 10)

  }

  "community" - community { implicit conn =>
    import molecule.db.compliance.domains.dsl.Community.*

//    val a1: List[Tuple1[Int]] = Person.age.query.get
    val a2: List[Int]           = Person.id_.apply(42L).delete.transact
    val a2: List[Int]           = Person.age.query1.get
    val a3: List[(Int, String)] = Person.age.name.queryn.get
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
