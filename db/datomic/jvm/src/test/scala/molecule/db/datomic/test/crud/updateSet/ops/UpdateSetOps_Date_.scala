// GENERATED CODE ********************************
package molecule.db.datomic.test.crud.updateSet.ops

import java.util.Date
import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object UpdateSetOps_Date_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      val eid = Ns.dates(Set(date1, date2)).save.transact.eids.head

      Ns(eid).dates(Set(date3, date4)).update.transact
      Ns.dates.query.get.head ==> Set(date3, date4)

      // Apply Seq of values
      Ns(eid).dates(Set(date4, date5)).update.transact
      Ns.dates.query.get.head ==> Set(date4, date5)

      // Apply empty Seq of values (deleting all values!)
      Ns(eid).dates(Seq.empty[Date]).update.transact
      Ns.dates.query.get ==> Nil

      Ns(eid).dates(Set(date1, date2)).update.transact

      // Delete all (apply no values)
      Ns(eid).dates().update.transact
      Ns.dates.query.get ==> Nil
    }


    "add" - types { implicit conn =>
      val eid = Ns.dates(Set(date1)).save.transact.eids.head

      // Add value
      Ns(eid).dates.add(date2).update.transact
      Ns.dates.query.get.head ==> Set(date1, date2)

      // Add existing value (no effect)
      Ns(eid).dates.add(date2).update.transact
      Ns.dates.query.get.head ==> Set(date1, date2)

      // Add multiple values (vararg)
      Ns(eid).dates.add(date3, date4).update.transact
      Ns.dates.query.get.head ==> Set(date1, date2, date3, date4)

      // Add Iterable of values (existing values unaffected)
      // Seq
      Ns(eid).dates.add(Seq(date4, date5)).update.transact
      Ns.dates.query.get.head ==> Set(date1, date2, date3, date4, date5)
      // Set
      Ns(eid).dates.add(Set(date6)).update.transact
      Ns.dates.query.get.head ==> Set(date1, date2, date3, date4, date5, date6)
      // Iterable
      Ns(eid).dates.add(Iterable(date7)).update.transact
      Ns.dates.query.get.head ==> Set(date1, date2, date3, date4, date5, date6, date7)

      // Add empty Seq of values (no effect)
      Ns(eid).dates.add(Seq.empty[Date]).update.transact
      Ns.dates.query.get.head ==> Set(date1, date2, date3, date4, date5, date6, date7)
    }


    "swap" - types { implicit conn =>
      val eid = Ns.dates(Set(date1, date2, date3, date4, date5, date6)).save.transact.eids.head

      // Replace value
      Ns(eid).dates.swap(date6 -> date8).update.transact
      Ns.dates.query.get.head ==> Set(date1, date2, date3, date4, date5, date8)

      // Replacing value to existing value simply deletes it
      Ns(eid).dates.swap(date5 -> date8).update.transact
      Ns.dates.query.get.head ==> Set(date1, date2, date3, date4, date8)

      // Replace multiple values (vararg)
      Ns(eid).dates.swap(date3 -> date6, date4 -> date7).update.transact
      Ns.dates.query.get.head ==> Set(date1, date2, date6, date7, date8)

      // Missing old value has no effect. The new value is inserted (upsert semantics)
      Ns(eid).dates.swap(date4 -> date9).update.transact
      Ns.dates.query.get.head ==> Set(date1, date2, date6, date7, date8, date9)

      // Replace with Seq of oldValue->newValue pairs
      Ns(eid).dates.swap(Seq(date2 -> date5)).update.transact
      Ns.dates.query.get.head ==> Set(date1, date5, date6, date7, date8, date9)

      // Replacing with empty Seq of oldValue->newValue pairs has no effect
      Ns(eid).dates.swap(Seq.empty[(Date, Date)]).update.transact
      Ns.dates.query.get.head ==> Set(date1, date5, date6, date7, date8, date9)


      // Can't swap duplicate from/to values
      intercept[MoleculeException](
        Ns(42).dates.swap(date1 -> date2, date1 -> date3).update.transact
      ).message ==> "Can't swap from duplicate retract values."

      intercept[MoleculeException](
        Ns(42).dates.swap(date1 -> date3, date2 -> date3).update.transact
      ).message ==> "Can't swap to duplicate replacement values."
    }


    "remove" - types { implicit conn =>
      val eid = Ns.dates(Set(date1, date2, date3, date4, date5, date6)).save.transact.eids.head

      // Retract value
      Ns(eid).dates.remove(date6).update.transact
      Ns.dates.query.get.head ==> Set(date1, date2, date3, date4, date5)

      // Retracting non-existing value has no effect
      Ns(eid).dates.remove(date7).update.transact
      Ns.dates.query.get.head ==> Set(date1, date2, date3, date4, date5)

      // Retracting duplicate values removes the distinct value
      Ns(eid).dates.remove(date5, date5).update.transact
      Ns.dates.query.get.head ==> Set(date1, date2, date3, date4)

      // Retract multiple values (vararg)
      Ns(eid).dates.remove(date3, date4).update.transact
      Ns.dates.query.get.head ==> Set(date1, date2)

      // Retract Seq of values
      Ns(eid).dates.remove(Seq(date2)).update.transact
      Ns.dates.query.get.head ==> Set(date1)

      // Retracting empty Seq of values has no effect
      Ns(eid).dates.remove(Seq.empty[Date]).update.transact
      Ns.dates.query.get.head ==> Set(date1)
    }
  }
}
