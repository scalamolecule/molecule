// GENERATED CODE ********************************
package molecule.db.datomic.test.crud.updateSet.ops

import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object UpdateSetOps_Byte_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      val eid = Ns.bytes(Set(byte1, byte2)).save.transact.eids.head

      Ns(eid).bytes(Set(byte3, byte4)).update.transact
      Ns.bytes.query.get.head ==> Set(byte3, byte4)

      // Apply Seq of values
      Ns(eid).bytes(Set(byte4, byte5)).update.transact
      Ns.bytes.query.get.head ==> Set(byte4, byte5)

      // Apply empty Seq of values (deleting all values!)
      Ns(eid).bytes(Seq.empty[Byte]).update.transact
      Ns.bytes.query.get ==> Nil

      Ns(eid).bytes(Set(byte1, byte2)).update.transact

      // Delete all (apply no values)
      Ns(eid).bytes().update.transact
      Ns.bytes.query.get ==> Nil
    }


    "add" - types { implicit conn =>
      val eid = Ns.bytes(Set(byte1)).save.transact.eids.head

      // Add value
      Ns(eid).bytes.add(byte2).update.transact
      Ns.bytes.query.get.head ==> Set(byte1, byte2)

      // Add existing value (no effect)
      Ns(eid).bytes.add(byte2).update.transact
      Ns.bytes.query.get.head ==> Set(byte1, byte2)

      // Add multiple values (vararg)
      Ns(eid).bytes.add(byte3, byte4).update.transact
      Ns.bytes.query.get.head ==> Set(byte1, byte2, byte3, byte4)

      // Add Iterable of values (existing values unaffected)
      // Seq
      Ns(eid).bytes.add(Seq(byte4, byte5)).update.transact
      Ns.bytes.query.get.head ==> Set(byte1, byte2, byte3, byte4, byte5)
      // Set
      Ns(eid).bytes.add(Set(byte6)).update.transact
      Ns.bytes.query.get.head ==> Set(byte1, byte2, byte3, byte4, byte5, byte6)
      // Iterable
      Ns(eid).bytes.add(Iterable(byte7)).update.transact
      Ns.bytes.query.get.head ==> Set(byte1, byte2, byte3, byte4, byte5, byte6, byte7)

      // Add empty Seq of values (no effect)
      Ns(eid).bytes.add(Seq.empty[Byte]).update.transact
      Ns.bytes.query.get.head ==> Set(byte1, byte2, byte3, byte4, byte5, byte6, byte7)
    }


    "swap" - types { implicit conn =>
      val eid = Ns.bytes(Set(byte1, byte2, byte3, byte4, byte5, byte6)).save.transact.eids.head

      // Replace value
      Ns(eid).bytes.swap(byte6 -> byte8).update.transact
      Ns.bytes.query.get.head ==> Set(byte1, byte2, byte3, byte4, byte5, byte8)

      // Replacing value to existing value simply deletes it
      Ns(eid).bytes.swap(byte5 -> byte8).update.transact
      Ns.bytes.query.get.head ==> Set(byte1, byte2, byte3, byte4, byte8)

      // Replace multiple values (vararg)
      Ns(eid).bytes.swap(byte3 -> byte6, byte4 -> byte7).update.transact
      Ns.bytes.query.get.head ==> Set(byte1, byte2, byte6, byte7, byte8)

      // Missing old value has no effect. The new value is inserted (upsert semantics)
      Ns(eid).bytes.swap(byte4 -> byte9).update.transact
      Ns.bytes.query.get.head ==> Set(byte1, byte2, byte6, byte7, byte8, byte9)

      // Replace with Seq of oldValue->newValue pairs
      Ns(eid).bytes.swap(Seq(byte2 -> byte5)).update.transact
      Ns.bytes.query.get.head ==> Set(byte1, byte5, byte6, byte7, byte8, byte9)

      // Replacing with empty Seq of oldValue->newValue pairs has no effect
      Ns(eid).bytes.swap(Seq.empty[(Byte, Byte)]).update.transact
      Ns.bytes.query.get.head ==> Set(byte1, byte5, byte6, byte7, byte8, byte9)


      // Can't swap duplicate from/to values
      intercept[MoleculeException](
        Ns(42).bytes.swap(byte1 -> byte2, byte1 -> byte3).update.transact
      ).message ==> "Can't swap from duplicate retract values."

      intercept[MoleculeException](
        Ns(42).bytes.swap(byte1 -> byte3, byte2 -> byte3).update.transact
      ).message ==> "Can't swap to duplicate replacement values."
    }


    "remove" - types { implicit conn =>
      val eid = Ns.bytes(Set(byte1, byte2, byte3, byte4, byte5, byte6)).save.transact.eids.head

      // Retract value
      Ns(eid).bytes.remove(byte6).update.transact
      Ns.bytes.query.get.head ==> Set(byte1, byte2, byte3, byte4, byte5)

      // Retracting non-existing value has no effect
      Ns(eid).bytes.remove(byte7).update.transact
      Ns.bytes.query.get.head ==> Set(byte1, byte2, byte3, byte4, byte5)

      // Retracting duplicate values removes the distinct value
      Ns(eid).bytes.remove(byte5, byte5).update.transact
      Ns.bytes.query.get.head ==> Set(byte1, byte2, byte3, byte4)

      // Retract multiple values (vararg)
      Ns(eid).bytes.remove(byte3, byte4).update.transact
      Ns.bytes.query.get.head ==> Set(byte1, byte2)

      // Retract Seq of values
      Ns(eid).bytes.remove(Seq(byte2)).update.transact
      Ns.bytes.query.get.head ==> Set(byte1)

      // Retracting empty Seq of values has no effect
      Ns(eid).bytes.remove(Seq.empty[Byte]).update.transact
      Ns.bytes.query.get.head ==> Set(byte1)
    }
  }
}
