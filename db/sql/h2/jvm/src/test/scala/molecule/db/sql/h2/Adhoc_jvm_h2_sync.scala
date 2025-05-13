package molecule.db.sql.h2

import boopickle.Default.*
import molecule.db.compliance.domains.dsl.Types.Entity
import molecule.db.compliance.setup.{Test, TestUtils}
import molecule.db.sql
import molecule.db.sql.h2.setup.DbProviders_h2
import molecule.db.sql.h2.sync.*
import scala.collection.{BitSet, immutable}
import scala.collection.immutable.BitSet as IBitSet


class Adhoc_jvm_h2_sync extends Test with DbProviders_h2 with TestUtils {


  //  "basic" - types { implicit conn =>
  //    val List(a, b) = Entity.int.insert(1, 2).transact.ids
  //    Entity.int(3).save.transact
  //    Entity.int.a1.query.get ==> List(1, 2, 3)
  //    Entity(a).int(10).update.transact
  //    Entity(b).delete.transact
  //    Entity.int.a1.query.get ==> List(3, 10)
  //  }

//    import java.util.BitSet as jBitSet
////    import boopickle._
//  //
//    implicit val bitSetPickler: Pickler[jBitSet] = new Pickler[jBitSet] {
//      override def pickle(obj: jBitSet)(implicit state: PickleState): Unit = {
//        state.enc.writeByteArray(obj.toByteArray)
//      }
//
//      override def unpickle(implicit state: UnpickleState): jBitSet = {
//        jBitSet.valueOf(state.dec.readByteArray())
//      }
//    }

  import java.util.BitSet as jBitSet

  import boopickle._

  val q = new jBitSet()
//  val w = new jBitSet()
  val w = jBitSet.valueOf(Array(1.toByte, 3.toByte))


  println(126.toByte)
  println(127.toByte)
  println(128.toByte)
  println(129.toByte)
  println(256.toByte)
  println(257.toByte)
  println(512.toByte)
  println(513.toByte)

  q.set(1)
  q.set(2)

  w.set(1)
  w.set(3)

  println(q.intersects(w))


//  implicit val bitSetPickler: Pickler[BitSet] = new Pickler[BitSet] {
//    override def pickle(bitSet: BitSet)(implicit state: PickleState): Unit = {
////      state.enc.writeIntArray(bitSet.toArray)
//      state.enc.writeIntArray(IArray.apply(1))
//    }
//
//    override def unpickle(implicit state: UnpickleState): BitSet = {
//      BitSet.fromSpecific(state.dec.readIntArray())
//    }
//  }
  //
  //  val a = new java.util.BitSet()
  //  a.set(42)
  //  val b = Pickle.intoBytes[java.util.BitSet](a)
  //  val c = Unpickle[java.util.BitSet].fromBytes(b)
  //  assert(c == a)
  //  assert(!c.get(43))
  //  assert(c.get(42))


  val a = BitSet(42, 2, 3, 4)
  val x = BitSet(42, 2, 3, 5)

  println(a. intersect(x))
//  println(a.intersects(x))

  val b = Pickle.intoBytes[BitSet](a)
  val c = Unpickle[BitSet].fromBytes(b)
  assert(c == a)
  //  assert(!c.get(43))
  //  assert(c.get(42))

  "Subscription" - types { implicit conn =>
    var intermediaryCallbackResults = List.empty[List[Int]]


    // Initial data
    Entity.i(1).save.transact

    // Start subscription
    Entity.i.query.subscribe { freshResult =>
      intermediaryCallbackResults = intermediaryCallbackResults :+ freshResult
    }

    // Mutations to be monitored by subscription
    Entity.i(2).save.transact.id

    // Mutations with no callback-involved attributes don't call back
    Entity.string("foo").save.transact

    // Callback catched all intermediary results correctly
    intermediaryCallbackResults.map(_.sorted) ==> List(
      List(1, 2), // query result after 2 was saved
    )

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
