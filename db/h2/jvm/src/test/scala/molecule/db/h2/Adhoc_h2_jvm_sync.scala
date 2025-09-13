package molecule.db.h2

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.h2.setup.DbProviders_h2
import molecule.db.h2.sync.*

class Adhoc_h2_jvm_sync extends MUnit with DbProviders_h2 with TestUtils {


  //  "basic" - types {
  //    import molecule.db.compliance.domains.dsl.Types.*
  //
  //    val List(a, b) = Entity.int.insert(1, 2).transact.ids
  //    Entity.int(3).save.transact
  //    Entity.int.a1.query.get ==> List(1, 2, 3)
  //    Entity(a).int(10).update.transact
  //    Entity(b).delete.transact
  //    Entity.int.a1.query.get ==> List(3, 10)
  //  }


  "accounting" - accounting {
    import molecule.db.compliance.domains.relationship.dsl.Accounting.*


    Invoice.no.Lines.*(InvoiceLine.amount).insert(
      (1, List(10, 20, 30)),
      (2, List(20, 70)),
    ).i.transact

    Invoice.no.a1.Lines.*(InvoiceLine.amount).query.i.get ==> List(
      (1, List(10, 20, 30)),
      (2, List(20, 70)),
    )

  }


  "refs" - refs {
    import molecule.db.compliance.domains.dsl.Refs.*

    A.s.Bb.*(B.i).insert(
      ("a", List(1)),
    ).transact

    A.s.a1.Bb.*?(B.i).query.get ==> List(
      ("a", List(1)),
    )

  }

  //  "integer" - types {
  //    import molecule.db.compliance.domains.dsl.Types.*
  //
  //    // Optional
  //    val s2: (Int, String, Option[Long]) = Entity.i.s.long_?.apply(Some(long1)).query.get.head
  //
  //
  //    val s2: String = Entity.s.i.apply("foo").query.get.head
  //    val s2: String = Entity.s.i.apply("foo").query.get.head
  //    val s2: String = Entity.s.i.apply("foo").query.get.head
  //
  //    val s2a: String = Entity.i_.s.query.get.head
  //
  //    val s2a: (Int, String) = Entity.i.s.query.get.head
  //    val s2a: String        = Entity.i_.s.query.get.head
  //    val s2a: Int           = Entity.i.s_.query.get.head
  //
  //    val s2a: Int            = Entity.i.s_.apply("foo").query.get.head
  //    val s2a: (Boolean, Int) = Entity.boolean.i.s_.apply("foo").query.get.head
  //
  //    val s2a: (Int, Set[Int]) = Entity.i.intSet.has(3).query.get.head
  //    val s2a: Int             = Entity.i.intSet_.has(3).query.get.head
  //
  //    val s3: (Int, String) = Entity.i.s.query.get.head
  //    val s4: (Int, String) = Entity.i.s.apply("foo").query.get.head
  //    val s5: (Int, String) = Entity.i.color.apply(Color.Blue).query.get.head
  //
  //    //    Entity(id2).aget(43).update.transact
  //  }


  //  "Subscription" - types {
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


  //  "commit" - refs {
  //    import molecule.db.compliance.domains.dsl.Refs.*
  //
  //    A.i.insert(1, 2, 3).transact
  //    A.i.query.stream.toList.sorted ==> List(1, 2, 3)
  //  }
}
