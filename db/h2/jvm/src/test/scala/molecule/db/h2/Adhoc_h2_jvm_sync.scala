package molecule.db.h2

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.h2.setup.DbProviders_h2
import molecule.db.h2.sync.*

class Adhoc_h2_jvm_sync extends MUnit with DbProviders_h2 with TestUtils {


  "basic" - types {
    import molecule.db.compliance.domains.dsl.Types.*

    val List(a, b) = Entity.int.insert(1, 2).transact.ids
    Entity.int(3).save.transact
    Entity.int.a1.query.get ==> List(1, 2, 3)
    Entity(a).int(10).update.transact
    Entity(b).delete.transact
    Entity.int.a1.query.get ==> List(3, 10)
  }


  "integer 0-1" - types {
    // Mandatory
    lazy val a1: Long = Entity.long.query.get.head
    lazy val a2: Long = Entity.long.apply(long1).query.get.head
    lazy val a3: Long = Entity.long.apply(?).query(long1).get.head
    lazy val a4: Long = Entity.long.apply(Entity.i_).query.get.head

    // Adding sort should still return the same type
    lazy val a5: Long = Entity.long.a1.query.get.head
    lazy val a6: Long = Entity.long.apply(long1).a1.query.get.head
    lazy val a7: Long = Entity.long.apply(?).a1.query(long1).get.head
    lazy val a8: Long = Entity.long.apply(Entity.i_).a1.query.get.head


    // Aggregates, some change type
    lazy val b1: Int       = Entity.long.apply(count).query.get.head
    lazy val b2: Long      = Entity.long.apply(min).query.get.head
    lazy val b3: Set[Long] = Entity.long.apply(min(3)).query.get.head
    lazy val b4: Double    = Entity.long.apply(avg).query.get.head

    lazy val b5: Int       = Entity.long.apply(count).a1.query.get.head
    lazy val b6: Long      = Entity.long.apply(min).a1.query.get.head
    lazy val b7: Set[Long] = Entity.long.apply(min(3)).a1.query.get.head
    lazy val b8: Double    = Entity.long.apply(avg).a1.query.get.head


    // Optional
    lazy val c1: Option[Long] = Entity.long_?.query.get.head
    lazy val c2: Option[Long] = Entity.long_?.apply(Some(long1)).query.get.head

    lazy val c3: Option[Long] = Entity.long_?.a1.query.get.head
    lazy val c4: Option[Long] = Entity.long_?.apply(Some(long1)).a1.query.get.head
  }


  "integer 1-n" - types {
    // Tacit - stays a String
    val a1: String = Entity.s.long_.query.get.head
    val a2: String = Entity.s.long_.apply(long1).query.get.head
    val a3: String = Entity.s.long_.apply(?).query(long1).get.head
    val a4: String = Entity.s.long_.apply(Entity.i_).query.get.head


    // Mandatory
    val b1: (String, Long) = Entity.s.long.query.get.head
    val b2: (String, Long) = Entity.s.long.apply(long1).query.get.head
    val b3: (String, Long) = Entity.s.long.apply(?).query(long1).get.head
    val b4: (String, Long) = Entity.s.long.apply(Entity.i_).query.get.head

    val b5: (String, Long) = Entity.s.long.a1.query.get.head
    val b6: (String, Long) = Entity.s.long.apply(long1).a1.query.get.head
    val b7: (String, Long) = Entity.s.long.apply(?).a1.query(long1).get.head
    val b8: (String, Long) = Entity.s.long.apply(Entity.i_).a1.query.get.head


    // Aggregates, some change type
    val c1: (String, Int)       = Entity.s.long.apply(count).query.get.head
    val c2: (String, Long)      = Entity.s.long.apply(min).query.get.head
    val c3: (String, Set[Long]) = Entity.s.long.apply(min(3)).query.get.head
    val c4: (String, Double)    = Entity.s.long.apply(avg).query.get.head

    val c5: (String, Int)       = Entity.s.long.apply(count).a1.query.get.head
    val c6: (String, Long)      = Entity.s.long.apply(min).a1.query.get.head
    val c7: (String, Set[Long]) = Entity.s.long.apply(min(3)).a1.query.get.head // shouldn't be allowed
    val c8: (String, Double)    = Entity.s.long.apply(avg).a1.query.get.head


    // Optional
    val d1: (String, Option[Long]) = Entity.s.long_?.query.get.head
    val d2: (String, Option[Long]) = Entity.s.long_?.apply(Some(long1)).query.get.head

    val d3: (String, Option[Long]) = Entity.s.long_?.a1.query.get.head
    val d4: (String, Option[Long]) = Entity.s.long_?.apply(Some(long1)).a1.query.get.head
  }


  "integer n-n" - types {
    // Tacit - stays an (Int, String)
    val a1: (Int, String) = Entity.i.s.long_.query.get.head
    val a2: (Int, String) = Entity.i.s.long_.apply(long1).query.get.head
    val a3: (Int, String) = Entity.i.s.long_.apply(?).query(long1).get.head
    val a4: (Int, String) = Entity.i.s.long_.apply(Entity.i_).query.get.head


    // Mandatory
    val b1: (Int, String, Long) = Entity.i.s.long.query.get.head
    val b2: (Int, String, Long) = Entity.i.s.long.apply(long1).query.get.head
    val b3: (Int, String, Long) = Entity.i.s.long.apply(?).query(long1).get.head
    val b4: (Int, String, Long) = Entity.i.s.long.apply(Entity.i_).query.get.head

    val b5: (Int, String, Long) = Entity.i.s.long.a1.query.get.head
    val b6: (Int, String, Long) = Entity.i.s.long.apply(long1).a1.query.get.head
    val b7: (Int, String, Long) = Entity.i.s.long.apply(?).a1.query(long1).get.head
    val b8: (Int, String, Long) = Entity.i.s.long.apply(Entity.i_).a1.query.get.head


    // Aggregates, some change type
    val c1: (Int, String, Int)       = Entity.i.s.long.apply(count).query.get.head
    val c2: (Int, String, Long)      = Entity.i.s.long.apply(min).query.get.head
    val c3: (Int, String, Set[Long]) = Entity.i.s.long.apply(min(3)).query.get.head
    val c4: (Int, String, Double)    = Entity.i.s.long.apply(avg).query.get.head

    val c5: (Int, String, Int)       = Entity.i.s.long.apply(count).a1.query.get.head
    val c6: (Int, String, Long)      = Entity.i.s.long.apply(min).a1.query.get.head
    val c7: (Int, String, Set[Long]) = Entity.i.s.long.apply(min(3)).a1.query.get.head // shouldn't be allowed
    val c8: (Int, String, Double)    = Entity.i.s.long.apply(avg).a1.query.get.head


    // Optional
    val d1: (Int, String, Option[Long]) = Entity.i.s.long_?.query.get.head
    val d2: (Int, String, Option[Long]) = Entity.i.s.long_?.apply(Some(long1)).query.get.head

    val d3: (Int, String, Option[Long]) = Entity.i.s.long_?.a1.query.get.head
    val d4: (Int, String, Option[Long]) = Entity.i.s.long_?.apply(Some(long1)).a1.query.get.head
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
