// GENERATED CODE ********************************
package molecule.db.datomic.test.crud.updateSet.ops

import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object UpdateSetOps_Char_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      val eid = Ns.chars(Set(char1, char2)).save.transact.eids.head

      Ns(eid).chars(Set(char3, char4)).update.transact
      Ns.chars.query.get.head ==> Set(char3, char4)

      // Apply Seq of values
      Ns(eid).chars(Set(char4, char5)).update.transact
      Ns.chars.query.get.head ==> Set(char4, char5)

      // Apply empty Seq of values (deleting all values!)
      Ns(eid).chars(Seq.empty[Char]).update.transact
      Ns.chars.query.get ==> Nil

      Ns(eid).chars(Set(char1, char2)).update.transact

      // Delete all (apply no values)
      Ns(eid).chars().update.transact
      Ns.chars.query.get ==> Nil
    }


    "add" - types { implicit conn =>
      val eid = Ns.chars(Set(char1)).save.transact.eids.head

      // Add value
      Ns(eid).chars.add(char2).update.transact
      Ns.chars.query.get.head ==> Set(char1, char2)

      // Add existing value (no effect)
      Ns(eid).chars.add(char2).update.transact
      Ns.chars.query.get.head ==> Set(char1, char2)

      // Add multiple values (vararg)
      Ns(eid).chars.add(char3, char4).update.transact
      Ns.chars.query.get.head ==> Set(char1, char2, char3, char4)

      // Add Iterable of values (existing values unaffected)
      // Seq
      Ns(eid).chars.add(Seq(char4, char5)).update.transact
      Ns.chars.query.get.head ==> Set(char1, char2, char3, char4, char5)
      // Set
      Ns(eid).chars.add(Set(char6)).update.transact
      Ns.chars.query.get.head ==> Set(char1, char2, char3, char4, char5, char6)
      // Iterable
      Ns(eid).chars.add(Iterable(char7)).update.transact
      Ns.chars.query.get.head ==> Set(char1, char2, char3, char4, char5, char6, char7)

      // Add empty Seq of values (no effect)
      Ns(eid).chars.add(Seq.empty[Char]).update.transact
      Ns.chars.query.get.head ==> Set(char1, char2, char3, char4, char5, char6, char7)
    }


    "swap" - types { implicit conn =>
      val eid = Ns.chars(Set(char1, char2, char3, char4, char5, char6)).save.transact.eids.head

      // Replace value
      Ns(eid).chars.swap(char6 -> char8).update.transact
      Ns.chars.query.get.head ==> Set(char1, char2, char3, char4, char5, char8)

      // Replacing value to existing value simply deletes it
      Ns(eid).chars.swap(char5 -> char8).update.transact
      Ns.chars.query.get.head ==> Set(char1, char2, char3, char4, char8)

      // Replace multiple values (vararg)
      Ns(eid).chars.swap(char3 -> char6, char4 -> char7).update.transact
      Ns.chars.query.get.head ==> Set(char1, char2, char6, char7, char8)

      // Missing old value has no effect. The new value is inserted (upsert semantics)
      Ns(eid).chars.swap(char4 -> char9).update.transact
      Ns.chars.query.get.head ==> Set(char1, char2, char6, char7, char8, char9)

      // Replace with Seq of oldValue->newValue pairs
      Ns(eid).chars.swap(Seq(char2 -> char5)).update.transact
      Ns.chars.query.get.head ==> Set(char1, char5, char6, char7, char8, char9)

      // Replacing with empty Seq of oldValue->newValue pairs has no effect
      Ns(eid).chars.swap(Seq.empty[(Char, Char)]).update.transact
      Ns.chars.query.get.head ==> Set(char1, char5, char6, char7, char8, char9)


      // Can't swap duplicate from/to values
      intercept[MoleculeException](
        Ns(42).chars.swap(char1 -> char2, char1 -> char3).update.transact
      ).message ==> "Can't swap from duplicate retract values."

      intercept[MoleculeException](
        Ns(42).chars.swap(char1 -> char3, char2 -> char3).update.transact
      ).message ==> "Can't swap to duplicate replacement values."
    }


    "remove" - types { implicit conn =>
      val eid = Ns.chars(Set(char1, char2, char3, char4, char5, char6)).save.transact.eids.head

      // Retract value
      Ns(eid).chars.remove(char6).update.transact
      Ns.chars.query.get.head ==> Set(char1, char2, char3, char4, char5)

      // Retracting non-existing value has no effect
      Ns(eid).chars.remove(char7).update.transact
      Ns.chars.query.get.head ==> Set(char1, char2, char3, char4, char5)

      // Retracting duplicate values removes the distinct value
      Ns(eid).chars.remove(char5, char5).update.transact
      Ns.chars.query.get.head ==> Set(char1, char2, char3, char4)

      // Retract multiple values (vararg)
      Ns(eid).chars.remove(char3, char4).update.transact
      Ns.chars.query.get.head ==> Set(char1, char2)

      // Retract Seq of values
      Ns(eid).chars.remove(Seq(char2)).update.transact
      Ns.chars.query.get.head ==> Set(char1)

      // Retracting empty Seq of values has no effect
      Ns(eid).chars.remove(Seq.empty[Char]).update.transact
      Ns.chars.query.get.head ==> Set(char1)
    }
  }
}
