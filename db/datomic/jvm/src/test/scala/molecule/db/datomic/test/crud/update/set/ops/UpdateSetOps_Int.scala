package molecule.db.datomic.test.crud.update.set.ops

import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object UpdateSetOps_Int extends DatomicTestSuite {


  lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      val eid = Ns.ints(Set(int1, int2)).save.transact.eids.head

      Ns(eid).ints(Set(int3, int4)).update.transact
      Ns.ints.query.get.head ==> Set(int3, int4)

      // Apply Seq of values
      Ns(eid).ints(Set(int4, int5)).update.transact
      Ns.ints.query.get.head ==> Set(int4, int5)

      // Apply empty Seq of values (deleting all values!)
      Ns(eid).ints(Seq.empty[Int]).update.transact
      Ns.ints.query.get ==> Nil

      Ns(eid).ints(Set(int1, int2)).update.transact

      // Delete all (apply no values)
      Ns(eid).ints().update.transact
      Ns.ints.query.get ==> Nil
    }


    "add" - types { implicit conn =>
      val eid = Ns.ints(Set(int1)).save.transact.eids.head

      // Add value
      Ns(eid).ints.add(int2).update.transact
      Ns.ints.query.get.head ==> Set(int1, int2)

      // Add existing value (no effect)
      Ns(eid).ints.add(int2).update.transact
      Ns.ints.query.get.head ==> Set(int1, int2)

      // Add multiple values (vararg)
      Ns(eid).ints.add(int3, int4).update.transact
      Ns.ints.query.get.head ==> Set(int1, int2, int3, int4)

      // Add Iterable of values (existing values unaffected)
      // Seq
      Ns(eid).ints.add(Seq(int4, int5)).update.transact
      Ns.ints.query.get.head ==> Set(int1, int2, int3, int4, int5)
      // Set
      Ns(eid).ints.add(Set(int6)).update.transact
      Ns.ints.query.get.head ==> Set(int1, int2, int3, int4, int5, int6)
      // Iterable
      Ns(eid).ints.add(Iterable(int7)).update.transact
      Ns.ints.query.get.head ==> Set(int1, int2, int3, int4, int5, int6, int7)

      // Add empty Seq of values (no effect)
      Ns(eid).ints.add(Seq.empty[Int]).update.transact
      Ns.ints.query.get.head ==> Set(int1, int2, int3, int4, int5, int6, int7)
    }


    "swap" - types { implicit conn =>
      val eid = Ns.ints(Set(int1, int2, int3, int4, int5, int6)).save.transact.eids.head

      // Replace value
      Ns(eid).ints.swap(int6 -> int8).update.transact
      Ns.ints.query.get.head ==> Set(int1, int2, int3, int4, int5, int8)

      // Replacing value to existing value simply deletes it
      Ns(eid).ints.swap(int5 -> int8).update.transact
      Ns.ints.query.get.head ==> Set(int1, int2, int3, int4, int8)

      // Replace multiple values (vararg)
      Ns(eid).ints.swap(int3 -> int6, int4 -> int7).update.transact
      Ns.ints.query.get.head ==> Set(int1, int2, int6, int7, int8)

      // Missing old value has no effect. The new value is inserted (upsert semantics)
      Ns(eid).ints.swap(int4 -> int9).update.transact
      Ns.ints.query.get.head ==> Set(int1, int2, int6, int7, int8, int9)

      // Replace with Seq of oldValue->newValue pairs
      Ns(eid).ints.swap(Seq(int2 -> int5)).update.transact
      Ns.ints.query.get.head ==> Set(int1, int5, int6, int7, int8, int9)

      // Replacing with empty Seq of oldValue->newValue pairs has no effect
      Ns(eid).ints.swap(Seq.empty[(Int, Int)]).update.transact
      Ns.ints.query.get.head ==> Set(int1, int5, int6, int7, int8, int9)


      // Can't swap duplicate from/to values
      intercept[MoleculeException](
        Ns(42).ints.swap(int1 -> int2, int1 -> int3).update.transact
      ).message ==> "Can't swap from duplicate retract values."

      intercept[MoleculeException](
        Ns(42).ints.swap(int1 -> int3, int2 -> int3).update.transact
      ).message ==> "Can't swap to duplicate replacement values."
    }


    "remove" - types { implicit conn =>
      val eid = Ns.ints(Set(int1, int2, int3, int4, int5, int6)).save.transact.eids.head

      // Retract value
      Ns(eid).ints.remove(int6).update.transact
      Ns.ints.query.get.head ==> Set(int1, int2, int3, int4, int5)

      // Retracting non-existing value has no effect
      Ns(eid).ints.remove(int7).update.transact
      Ns.ints.query.get.head ==> Set(int1, int2, int3, int4, int5)

      // Retracting duplicate values removes the distinct value
      Ns(eid).ints.remove(int5, int5).update.transact
      Ns.ints.query.get.head ==> Set(int1, int2, int3, int4)

      // Retract multiple values (vararg)
      Ns(eid).ints.remove(int3, int4).update.transact
      Ns.ints.query.get.head ==> Set(int1, int2)

      // Retract Seq of values
      Ns(eid).ints.remove(Seq(int2)).update.transact
      Ns.ints.query.get.head ==> Set(int1)

      // Retracting empty Seq of values has no effect
      Ns(eid).ints.remove(Seq.empty[Int]).update.transact
      Ns.ints.query.get.head ==> Set(int1)
    }
  }
}
