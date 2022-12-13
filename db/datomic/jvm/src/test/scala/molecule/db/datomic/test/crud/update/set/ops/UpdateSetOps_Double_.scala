// GENERATED CODE ********************************
package molecule.db.datomic.test.crud.update.set.ops

import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object UpdateSetOps_Double_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      val eid = Ns.doubles(Set(double1, double2)).save.transact.eids.head

      Ns(eid).doubles(Set(double3, double4)).update.transact
      Ns.doubles.query.get.head ==> Set(double3, double4)

      // Apply Seq of values
      Ns(eid).doubles(Set(double4, double5)).update.transact
      Ns.doubles.query.get.head ==> Set(double4, double5)

      // Apply empty Seq of values (deleting all values!)
      Ns(eid).doubles(Seq.empty[Double]).update.transact
      Ns.doubles.query.get ==> Nil

      Ns(eid).doubles(Set(double1, double2)).update.transact

      // Delete all (apply no values)
      Ns(eid).doubles().update.transact
      Ns.doubles.query.get ==> Nil
    }


    "add" - types { implicit conn =>
      val eid = Ns.doubles(Set(double1)).save.transact.eids.head

      // Add value
      Ns(eid).doubles.add(double2).update.transact
      Ns.doubles.query.get.head ==> Set(double1, double2)

      // Add existing value (no effect)
      Ns(eid).doubles.add(double2).update.transact
      Ns.doubles.query.get.head ==> Set(double1, double2)

      // Add multiple values (vararg)
      Ns(eid).doubles.add(double3, double4).update.transact
      Ns.doubles.query.get.head ==> Set(double1, double2, double3, double4)

      // Add Iterable of values (existing values unaffected)
      // Seq
      Ns(eid).doubles.add(Seq(double4, double5)).update.transact
      Ns.doubles.query.get.head ==> Set(double1, double2, double3, double4, double5)
      // Set
      Ns(eid).doubles.add(Set(double6)).update.transact
      Ns.doubles.query.get.head ==> Set(double1, double2, double3, double4, double5, double6)
      // Iterable
      Ns(eid).doubles.add(Iterable(double7)).update.transact
      Ns.doubles.query.get.head ==> Set(double1, double2, double3, double4, double5, double6, double7)

      // Add empty Seq of values (no effect)
      Ns(eid).doubles.add(Seq.empty[Double]).update.transact
      Ns.doubles.query.get.head ==> Set(double1, double2, double3, double4, double5, double6, double7)
    }


    "swap" - types { implicit conn =>
      val eid = Ns.doubles(Set(double1, double2, double3, double4, double5, double6)).save.transact.eids.head

      // Replace value
      Ns(eid).doubles.swap(double6 -> double8).update.transact
      Ns.doubles.query.get.head ==> Set(double1, double2, double3, double4, double5, double8)

      // Replacing value to existing value simply deletes it
      Ns(eid).doubles.swap(double5 -> double8).update.transact
      Ns.doubles.query.get.head ==> Set(double1, double2, double3, double4, double8)

      // Replace multiple values (vararg)
      Ns(eid).doubles.swap(double3 -> double6, double4 -> double7).update.transact
      Ns.doubles.query.get.head ==> Set(double1, double2, double6, double7, double8)

      // Missing old value has no effect. The new value is inserted (upsert semantics)
      Ns(eid).doubles.swap(double4 -> double9).update.transact
      Ns.doubles.query.get.head ==> Set(double1, double2, double6, double7, double8, double9)

      // Replace with Seq of oldValue->newValue pairs
      Ns(eid).doubles.swap(Seq(double2 -> double5)).update.transact
      Ns.doubles.query.get.head ==> Set(double1, double5, double6, double7, double8, double9)

      // Replacing with empty Seq of oldValue->newValue pairs has no effect
      Ns(eid).doubles.swap(Seq.empty[(Double, Double)]).update.transact
      Ns.doubles.query.get.head ==> Set(double1, double5, double6, double7, double8, double9)


      // Can't swap duplicate from/to values
      intercept[MoleculeException](
        Ns(42).doubles.swap(double1 -> double2, double1 -> double3).update.transact
      ).message ==> "Can't swap from duplicate retract values."

      intercept[MoleculeException](
        Ns(42).doubles.swap(double1 -> double3, double2 -> double3).update.transact
      ).message ==> "Can't swap to duplicate replacement values."
    }


    "remove" - types { implicit conn =>
      val eid = Ns.doubles(Set(double1, double2, double3, double4, double5, double6)).save.transact.eids.head

      // Retract value
      Ns(eid).doubles.remove(double6).update.transact
      Ns.doubles.query.get.head ==> Set(double1, double2, double3, double4, double5)

      // Retracting non-existing value has no effect
      Ns(eid).doubles.remove(double7).update.transact
      Ns.doubles.query.get.head ==> Set(double1, double2, double3, double4, double5)

      // Retracting duplicate values removes the distinct value
      Ns(eid).doubles.remove(double5, double5).update.transact
      Ns.doubles.query.get.head ==> Set(double1, double2, double3, double4)

      // Retract multiple values (vararg)
      Ns(eid).doubles.remove(double3, double4).update.transact
      Ns.doubles.query.get.head ==> Set(double1, double2)

      // Retract Seq of values
      Ns(eid).doubles.remove(Seq(double2)).update.transact
      Ns.doubles.query.get.head ==> Set(double1)

      // Retracting empty Seq of values has no effect
      Ns(eid).doubles.remove(Seq.empty[Double]).update.transact
      Ns.doubles.query.get.head ==> Set(double1)
    }
  }
}
