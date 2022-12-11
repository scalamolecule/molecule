// GENERATED CODE ********************************
package molecule.db.datomic.test.crud.updateSet.ops

import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object UpdateSetOps_Long_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      val eid = Ns.longs(Set(long1, long2)).save.transact.eids.head

      Ns(eid).longs(Set(long3, long4)).update.transact
      Ns.longs.query.get.head ==> Set(long3, long4)

      // Apply Seq of values
      Ns(eid).longs(Set(long4, long5)).update.transact
      Ns.longs.query.get.head ==> Set(long4, long5)

      // Apply empty Seq of values (deleting all values!)
      Ns(eid).longs(Seq.empty[Long]).update.transact
      Ns.longs.query.get ==> Nil

      Ns(eid).longs(Set(long1, long2)).update.transact

      // Delete all (apply no values)
      Ns(eid).longs().update.transact
      Ns.longs.query.get ==> Nil
    }


    "add" - types { implicit conn =>
      val eid = Ns.longs(Set(long1)).save.transact.eids.head

      // Add value
      Ns(eid).longs.add(long2).update.transact
      Ns.longs.query.get.head ==> Set(long1, long2)

      // Add existing value (no effect)
      Ns(eid).longs.add(long2).update.transact
      Ns.longs.query.get.head ==> Set(long1, long2)

      // Add multiple values (vararg)
      Ns(eid).longs.add(long3, long4).update.transact
      Ns.longs.query.get.head ==> Set(long1, long2, long3, long4)

      // Add Iterable of values (existing values unaffected)
      // Seq
      Ns(eid).longs.add(Seq(long4, long5)).update.transact
      Ns.longs.query.get.head ==> Set(long1, long2, long3, long4, long5)
      // Set
      Ns(eid).longs.add(Set(long6)).update.transact
      Ns.longs.query.get.head ==> Set(long1, long2, long3, long4, long5, long6)
      // Iterable
      Ns(eid).longs.add(Iterable(long7)).update.transact
      Ns.longs.query.get.head ==> Set(long1, long2, long3, long4, long5, long6, long7)

      // Add empty Seq of values (no effect)
      Ns(eid).longs.add(Seq.empty[Long]).update.transact
      Ns.longs.query.get.head ==> Set(long1, long2, long3, long4, long5, long6, long7)
    }


    "swap" - types { implicit conn =>
      val eid = Ns.longs(Set(long1, long2, long3, long4, long5, long6)).save.transact.eids.head

      // Replace value
      Ns(eid).longs.swap(long6 -> long8).update.transact
      Ns.longs.query.get.head ==> Set(long1, long2, long3, long4, long5, long8)

      // Replacing value to existing value simply deletes it
      Ns(eid).longs.swap(long5 -> long8).update.transact
      Ns.longs.query.get.head ==> Set(long1, long2, long3, long4, long8)

      // Replace multiple values (vararg)
      Ns(eid).longs.swap(long3 -> long6, long4 -> long7).update.transact
      Ns.longs.query.get.head ==> Set(long1, long2, long6, long7, long8)

      // Missing old value has no effect. The new value is inserted (upsert semantics)
      Ns(eid).longs.swap(long4 -> long9).update.transact
      Ns.longs.query.get.head ==> Set(long1, long2, long6, long7, long8, long9)

      // Replace with Seq of oldValue->newValue pairs
      Ns(eid).longs.swap(Seq(long2 -> long5)).update.transact
      Ns.longs.query.get.head ==> Set(long1, long5, long6, long7, long8, long9)

      // Replacing with empty Seq of oldValue->newValue pairs has no effect
      Ns(eid).longs.swap(Seq.empty[(Long, Long)]).update.transact
      Ns.longs.query.get.head ==> Set(long1, long5, long6, long7, long8, long9)


      // Can't swap duplicate from/to values
      intercept[MoleculeException](
        Ns(42).longs.swap(long1 -> long2, long1 -> long3).update.transact
      ).message ==> "Can't swap from duplicate retract values."

      intercept[MoleculeException](
        Ns(42).longs.swap(long1 -> long3, long2 -> long3).update.transact
      ).message ==> "Can't swap to duplicate replacement values."
    }


    "remove" - types { implicit conn =>
      val eid = Ns.longs(Set(long1, long2, long3, long4, long5, long6)).save.transact.eids.head

      // Retract value
      Ns(eid).longs.remove(long6).update.transact
      Ns.longs.query.get.head ==> Set(long1, long2, long3, long4, long5)

      // Retracting non-existing value has no effect
      Ns(eid).longs.remove(long7).update.transact
      Ns.longs.query.get.head ==> Set(long1, long2, long3, long4, long5)

      // Retracting duplicate values removes the distinct value
      Ns(eid).longs.remove(long5, long5).update.transact
      Ns.longs.query.get.head ==> Set(long1, long2, long3, long4)

      // Retract multiple values (vararg)
      Ns(eid).longs.remove(long3, long4).update.transact
      Ns.longs.query.get.head ==> Set(long1, long2)

      // Retract Seq of values
      Ns(eid).longs.remove(Seq(long2)).update.transact
      Ns.longs.query.get.head ==> Set(long1)

      // Retracting empty Seq of values has no effect
      Ns(eid).longs.remove(Seq.empty[Long]).update.transact
      Ns.longs.query.get.head ==> Set(long1)
    }
  }
}
