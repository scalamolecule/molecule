// GENERATED CODE ********************************
package molecule.db.datomic.test.crud.update.set.ops

import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object UpdateSetOps_BigDecimal_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      val eid = Ns.bigDecimals(Set(bigDecimal1, bigDecimal2)).save.transact.eids.head

      Ns(eid).bigDecimals(Set(bigDecimal3, bigDecimal4)).update.transact
      Ns.bigDecimals.query.get.head ==> Set(bigDecimal3, bigDecimal4)

      // Apply Seq of values
      Ns(eid).bigDecimals(Set(bigDecimal4, bigDecimal5)).update.transact
      Ns.bigDecimals.query.get.head ==> Set(bigDecimal4, bigDecimal5)

      // Apply empty Seq of values (deleting all values!)
      Ns(eid).bigDecimals(Seq.empty[BigDecimal]).update.transact
      Ns.bigDecimals.query.get ==> Nil

      Ns(eid).bigDecimals(Set(bigDecimal1, bigDecimal2)).update.transact

      // Delete all (apply no values)
      Ns(eid).bigDecimals().update.transact
      Ns.bigDecimals.query.get ==> Nil
    }


    "add" - types { implicit conn =>
      val eid = Ns.bigDecimals(Set(bigDecimal1)).save.transact.eids.head

      // Add value
      Ns(eid).bigDecimals.add(bigDecimal2).update.transact
      Ns.bigDecimals.query.get.head ==> Set(bigDecimal1, bigDecimal2)

      // Add existing value (no effect)
      Ns(eid).bigDecimals.add(bigDecimal2).update.transact
      Ns.bigDecimals.query.get.head ==> Set(bigDecimal1, bigDecimal2)

      // Add multiple values (vararg)
      Ns(eid).bigDecimals.add(bigDecimal3, bigDecimal4).update.transact
      Ns.bigDecimals.query.get.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4)

      // Add Iterable of values (existing values unaffected)
      // Seq
      Ns(eid).bigDecimals.add(Seq(bigDecimal4, bigDecimal5)).update.transact
      Ns.bigDecimals.query.get.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5)
      // Set
      Ns(eid).bigDecimals.add(Set(bigDecimal6)).update.transact
      Ns.bigDecimals.query.get.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6)
      // Iterable
      Ns(eid).bigDecimals.add(Iterable(bigDecimal7)).update.transact
      Ns.bigDecimals.query.get.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6, bigDecimal7)

      // Add empty Seq of values (no effect)
      Ns(eid).bigDecimals.add(Seq.empty[BigDecimal]).update.transact
      Ns.bigDecimals.query.get.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6, bigDecimal7)
    }


    "swap" - types { implicit conn =>
      val eid = Ns.bigDecimals(Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6)).save.transact.eids.head

      // Replace value
      Ns(eid).bigDecimals.swap(bigDecimal6 -> bigDecimal8).update.transact
      Ns.bigDecimals.query.get.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal8)

      // Replacing value to existing value simply deletes it
      Ns(eid).bigDecimals.swap(bigDecimal5 -> bigDecimal8).update.transact
      Ns.bigDecimals.query.get.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal8)

      // Replace multiple values (vararg)
      Ns(eid).bigDecimals.swap(bigDecimal3 -> bigDecimal6, bigDecimal4 -> bigDecimal7).update.transact
      Ns.bigDecimals.query.get.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal6, bigDecimal7, bigDecimal8)

      // Missing old value has no effect. The new value is inserted (upsert semantics)
      Ns(eid).bigDecimals.swap(bigDecimal4 -> bigDecimal9).update.transact
      Ns.bigDecimals.query.get.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal6, bigDecimal7, bigDecimal8, bigDecimal9)

      // Replace with Seq of oldValue->newValue pairs
      Ns(eid).bigDecimals.swap(Seq(bigDecimal2 -> bigDecimal5)).update.transact
      Ns.bigDecimals.query.get.head ==> Set(bigDecimal1, bigDecimal5, bigDecimal6, bigDecimal7, bigDecimal8, bigDecimal9)

      // Replacing with empty Seq of oldValue->newValue pairs has no effect
      Ns(eid).bigDecimals.swap(Seq.empty[(BigDecimal, BigDecimal)]).update.transact
      Ns.bigDecimals.query.get.head ==> Set(bigDecimal1, bigDecimal5, bigDecimal6, bigDecimal7, bigDecimal8, bigDecimal9)


      // Can't swap duplicate from/to values
      intercept[MoleculeException](
        Ns(42).bigDecimals.swap(bigDecimal1 -> bigDecimal2, bigDecimal1 -> bigDecimal3).update.transact
      ).message ==> "Can't swap from duplicate retract values."

      intercept[MoleculeException](
        Ns(42).bigDecimals.swap(bigDecimal1 -> bigDecimal3, bigDecimal2 -> bigDecimal3).update.transact
      ).message ==> "Can't swap to duplicate replacement values."
    }


    "remove" - types { implicit conn =>
      val eid = Ns.bigDecimals(Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6)).save.transact.eids.head

      // Retract value
      Ns(eid).bigDecimals.remove(bigDecimal6).update.transact
      Ns.bigDecimals.query.get.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5)

      // Retracting non-existing value has no effect
      Ns(eid).bigDecimals.remove(bigDecimal7).update.transact
      Ns.bigDecimals.query.get.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5)

      // Retracting duplicate values removes the distinct value
      Ns(eid).bigDecimals.remove(bigDecimal5, bigDecimal5).update.transact
      Ns.bigDecimals.query.get.head ==> Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4)

      // Retract multiple values (vararg)
      Ns(eid).bigDecimals.remove(bigDecimal3, bigDecimal4).update.transact
      Ns.bigDecimals.query.get.head ==> Set(bigDecimal1, bigDecimal2)

      // Retract Seq of values
      Ns(eid).bigDecimals.remove(Seq(bigDecimal2)).update.transact
      Ns.bigDecimals.query.get.head ==> Set(bigDecimal1)

      // Retracting empty Seq of values has no effect
      Ns(eid).bigDecimals.remove(Seq.empty[BigDecimal]).update.transact
      Ns.bigDecimals.query.get.head ==> Set(bigDecimal1)
    }
  }
}
