// GENERATED CODE ********************************
package molecule.db.datomic.test.crud.update.set.ops

import java.util.UUID
import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object UpdateSetOps_UUID_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      val eid = Ns.uuids(Set(uuid1, uuid2)).save.transact.eids.head

      Ns(eid).uuids(Set(uuid3, uuid4)).update.transact
      Ns.uuids.query.get.head ==> Set(uuid3, uuid4)

      // Apply Seq of values
      Ns(eid).uuids(Set(uuid4, uuid5)).update.transact
      Ns.uuids.query.get.head ==> Set(uuid4, uuid5)

      // Apply empty Seq of values (deleting all values!)
      Ns(eid).uuids(Seq.empty[UUID]).update.transact
      Ns.uuids.query.get ==> Nil

      Ns(eid).uuids(Set(uuid1, uuid2)).update.transact

      // Delete all (apply no values)
      Ns(eid).uuids().update.transact
      Ns.uuids.query.get ==> Nil
    }


    "add" - types { implicit conn =>
      val eid = Ns.uuids(Set(uuid1)).save.transact.eids.head

      // Add value
      Ns(eid).uuids.add(uuid2).update.transact
      Ns.uuids.query.get.head ==> Set(uuid1, uuid2)

      // Add existing value (no effect)
      Ns(eid).uuids.add(uuid2).update.transact
      Ns.uuids.query.get.head ==> Set(uuid1, uuid2)

      // Add multiple values (vararg)
      Ns(eid).uuids.add(uuid3, uuid4).update.transact
      Ns.uuids.query.get.head ==> Set(uuid1, uuid2, uuid3, uuid4)

      // Add Iterable of values (existing values unaffected)
      // Seq
      Ns(eid).uuids.add(Seq(uuid4, uuid5)).update.transact
      Ns.uuids.query.get.head ==> Set(uuid1, uuid2, uuid3, uuid4, uuid5)
      // Set
      Ns(eid).uuids.add(Set(uuid6)).update.transact
      Ns.uuids.query.get.head ==> Set(uuid1, uuid2, uuid3, uuid4, uuid5, uuid6)
      // Iterable
      Ns(eid).uuids.add(Iterable(uuid7)).update.transact
      Ns.uuids.query.get.head ==> Set(uuid1, uuid2, uuid3, uuid4, uuid5, uuid6, uuid7)

      // Add empty Seq of values (no effect)
      Ns(eid).uuids.add(Seq.empty[UUID]).update.transact
      Ns.uuids.query.get.head ==> Set(uuid1, uuid2, uuid3, uuid4, uuid5, uuid6, uuid7)
    }


    "swap" - types { implicit conn =>
      val eid = Ns.uuids(Set(uuid1, uuid2, uuid3, uuid4, uuid5, uuid6)).save.transact.eids.head

      // Replace value
      Ns(eid).uuids.swap(uuid6 -> uuid8).update.transact
      Ns.uuids.query.get.head ==> Set(uuid1, uuid2, uuid3, uuid4, uuid5, uuid8)

      // Replacing value to existing value simply deletes it
      Ns(eid).uuids.swap(uuid5 -> uuid8).update.transact
      Ns.uuids.query.get.head ==> Set(uuid1, uuid2, uuid3, uuid4, uuid8)

      // Replace multiple values (vararg)
      Ns(eid).uuids.swap(uuid3 -> uuid6, uuid4 -> uuid7).update.transact
      Ns.uuids.query.get.head ==> Set(uuid1, uuid2, uuid6, uuid7, uuid8)

      // Missing old value has no effect. The new value is inserted (upsert semantics)
      Ns(eid).uuids.swap(uuid4 -> uuid9).update.transact
      Ns.uuids.query.get.head ==> Set(uuid1, uuid2, uuid6, uuid7, uuid8, uuid9)

      // Replace with Seq of oldValue->newValue pairs
      Ns(eid).uuids.swap(Seq(uuid2 -> uuid5)).update.transact
      Ns.uuids.query.get.head ==> Set(uuid1, uuid5, uuid6, uuid7, uuid8, uuid9)

      // Replacing with empty Seq of oldValue->newValue pairs has no effect
      Ns(eid).uuids.swap(Seq.empty[(UUID, UUID)]).update.transact
      Ns.uuids.query.get.head ==> Set(uuid1, uuid5, uuid6, uuid7, uuid8, uuid9)


      // Can't swap duplicate from/to values
      intercept[MoleculeException](
        Ns(42).uuids.swap(uuid1 -> uuid2, uuid1 -> uuid3).update.transact
      ).message ==> "Can't swap from duplicate retract values."

      intercept[MoleculeException](
        Ns(42).uuids.swap(uuid1 -> uuid3, uuid2 -> uuid3).update.transact
      ).message ==> "Can't swap to duplicate replacement values."
    }


    "remove" - types { implicit conn =>
      val eid = Ns.uuids(Set(uuid1, uuid2, uuid3, uuid4, uuid5, uuid6)).save.transact.eids.head

      // Retract value
      Ns(eid).uuids.remove(uuid6).update.transact
      Ns.uuids.query.get.head ==> Set(uuid1, uuid2, uuid3, uuid4, uuid5)

      // Retracting non-existing value has no effect
      Ns(eid).uuids.remove(uuid7).update.transact
      Ns.uuids.query.get.head ==> Set(uuid1, uuid2, uuid3, uuid4, uuid5)

      // Retracting duplicate values removes the distinct value
      Ns(eid).uuids.remove(uuid5, uuid5).update.transact
      Ns.uuids.query.get.head ==> Set(uuid1, uuid2, uuid3, uuid4)

      // Retract multiple values (vararg)
      Ns(eid).uuids.remove(uuid3, uuid4).update.transact
      Ns.uuids.query.get.head ==> Set(uuid1, uuid2)

      // Retract Seq of values
      Ns(eid).uuids.remove(Seq(uuid2)).update.transact
      Ns.uuids.query.get.head ==> Set(uuid1)

      // Retracting empty Seq of values has no effect
      Ns(eid).uuids.remove(Seq.empty[UUID]).update.transact
      Ns.uuids.query.get.head ==> Set(uuid1)
    }
  }
}
