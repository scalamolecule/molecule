// GENERATED CODE ********************************
package molecule.db.datomic.test.crud.update.set.ops

import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object UpdateSetOps_Short_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      val eid = Ns.shorts(Set(short1, short2)).save.transact.eids.head

      Ns(eid).shorts(Set(short3, short4)).update.transact
      Ns.shorts.query.get.head ==> Set(short3, short4)

      // Apply Seq of values
      Ns(eid).shorts(Set(short4, short5)).update.transact
      Ns.shorts.query.get.head ==> Set(short4, short5)

      // Apply empty Seq of values (deleting all values!)
      Ns(eid).shorts(Seq.empty[Short]).update.transact
      Ns.shorts.query.get ==> Nil

      Ns(eid).shorts(Set(short1, short2)).update.transact

      // Delete all (apply no values)
      Ns(eid).shorts().update.transact
      Ns.shorts.query.get ==> Nil
    }


    "add" - types { implicit conn =>
      val eid = Ns.shorts(Set(short1)).save.transact.eids.head

      // Add value
      Ns(eid).shorts.add(short2).update.transact
      Ns.shorts.query.get.head ==> Set(short1, short2)

      // Add existing value (no effect)
      Ns(eid).shorts.add(short2).update.transact
      Ns.shorts.query.get.head ==> Set(short1, short2)

      // Add multiple values (vararg)
      Ns(eid).shorts.add(short3, short4).update.transact
      Ns.shorts.query.get.head ==> Set(short1, short2, short3, short4)

      // Add Iterable of values (existing values unaffected)
      // Seq
      Ns(eid).shorts.add(Seq(short4, short5)).update.transact
      Ns.shorts.query.get.head ==> Set(short1, short2, short3, short4, short5)
      // Set
      Ns(eid).shorts.add(Set(short6)).update.transact
      Ns.shorts.query.get.head ==> Set(short1, short2, short3, short4, short5, short6)
      // Iterable
      Ns(eid).shorts.add(Iterable(short7)).update.transact
      Ns.shorts.query.get.head ==> Set(short1, short2, short3, short4, short5, short6, short7)

      // Add empty Seq of values (no effect)
      Ns(eid).shorts.add(Seq.empty[Short]).update.transact
      Ns.shorts.query.get.head ==> Set(short1, short2, short3, short4, short5, short6, short7)
    }


    "swap" - types { implicit conn =>
      val eid = Ns.shorts(Set(short1, short2, short3, short4, short5, short6)).save.transact.eids.head

      // Replace value
      Ns(eid).shorts.swap(short6 -> short8).update.transact
      Ns.shorts.query.get.head ==> Set(short1, short2, short3, short4, short5, short8)

      // Replacing value to existing value simply deletes it
      Ns(eid).shorts.swap(short5 -> short8).update.transact
      Ns.shorts.query.get.head ==> Set(short1, short2, short3, short4, short8)

      // Replace multiple values (vararg)
      Ns(eid).shorts.swap(short3 -> short6, short4 -> short7).update.transact
      Ns.shorts.query.get.head ==> Set(short1, short2, short6, short7, short8)

      // Missing old value has no effect. The new value is inserted (upsert semantics)
      Ns(eid).shorts.swap(short4 -> short9).update.transact
      Ns.shorts.query.get.head ==> Set(short1, short2, short6, short7, short8, short9)

      // Replace with Seq of oldValue->newValue pairs
      Ns(eid).shorts.swap(Seq(short2 -> short5)).update.transact
      Ns.shorts.query.get.head ==> Set(short1, short5, short6, short7, short8, short9)

      // Replacing with empty Seq of oldValue->newValue pairs has no effect
      Ns(eid).shorts.swap(Seq.empty[(Short, Short)]).update.transact
      Ns.shorts.query.get.head ==> Set(short1, short5, short6, short7, short8, short9)


      // Can't swap duplicate from/to values
      intercept[MoleculeException](
        Ns(42).shorts.swap(short1 -> short2, short1 -> short3).update.transact
      ).message ==> "Can't swap from duplicate retract values."

      intercept[MoleculeException](
        Ns(42).shorts.swap(short1 -> short3, short2 -> short3).update.transact
      ).message ==> "Can't swap to duplicate replacement values."
    }


    "remove" - types { implicit conn =>
      val eid = Ns.shorts(Set(short1, short2, short3, short4, short5, short6)).save.transact.eids.head

      // Retract value
      Ns(eid).shorts.remove(short6).update.transact
      Ns.shorts.query.get.head ==> Set(short1, short2, short3, short4, short5)

      // Retracting non-existing value has no effect
      Ns(eid).shorts.remove(short7).update.transact
      Ns.shorts.query.get.head ==> Set(short1, short2, short3, short4, short5)

      // Retracting duplicate values removes the distinct value
      Ns(eid).shorts.remove(short5, short5).update.transact
      Ns.shorts.query.get.head ==> Set(short1, short2, short3, short4)

      // Retract multiple values (vararg)
      Ns(eid).shorts.remove(short3, short4).update.transact
      Ns.shorts.query.get.head ==> Set(short1, short2)

      // Retract Seq of values
      Ns(eid).shorts.remove(Seq(short2)).update.transact
      Ns.shorts.query.get.head ==> Set(short1)

      // Retracting empty Seq of values has no effect
      Ns(eid).shorts.remove(Seq.empty[Short]).update.transact
      Ns.shorts.query.get.head ==> Set(short1)
    }
  }
}
