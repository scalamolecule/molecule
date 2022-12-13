// GENERATED CODE ********************************
package molecule.db.datomic.test.crud.update.set.ops

import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object UpdateSetOps_ref_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      val eid = Ns.refs(Set(ref1, ref2)).save.transact.eids.head

      Ns(eid).refs(Set(ref3, ref4)).update.transact
      Ns.refs.query.get.head ==> Set(ref3, ref4)

      // Apply Seq of values
      Ns(eid).refs(Set(ref4, ref5)).update.transact
      Ns.refs.query.get.head ==> Set(ref4, ref5)

      // Apply empty Seq of values (deleting all values!)
      Ns(eid).refs(Seq.empty[Long]).update.transact
      Ns.refs.query.get ==> Nil

      Ns(eid).refs(Set(ref1, ref2)).update.transact

      // Delete all (apply no values)
      Ns(eid).refs().update.transact
      Ns.refs.query.get ==> Nil
    }


    "add" - types { implicit conn =>
      val eid = Ns.refs(Set(ref1)).save.transact.eids.head

      // Add value
      Ns(eid).refs.add(ref2).update.transact
      Ns.refs.query.get.head ==> Set(ref1, ref2)

      // Add existing value (no effect)
      Ns(eid).refs.add(ref2).update.transact
      Ns.refs.query.get.head ==> Set(ref1, ref2)

      // Add multiple values (vararg)
      Ns(eid).refs.add(ref3, ref4).update.transact
      Ns.refs.query.get.head ==> Set(ref1, ref2, ref3, ref4)

      // Add Iterable of values (existing values unaffected)
      // Seq
      Ns(eid).refs.add(Seq(ref4, ref5)).update.transact
      Ns.refs.query.get.head ==> Set(ref1, ref2, ref3, ref4, ref5)
      // Set
      Ns(eid).refs.add(Set(ref6)).update.transact
      Ns.refs.query.get.head ==> Set(ref1, ref2, ref3, ref4, ref5, ref6)
      // Iterable
      Ns(eid).refs.add(Iterable(ref7)).update.transact
      Ns.refs.query.get.head ==> Set(ref1, ref2, ref3, ref4, ref5, ref6, ref7)

      // Add empty Seq of values (no effect)
      Ns(eid).refs.add(Seq.empty[Long]).update.transact
      Ns.refs.query.get.head ==> Set(ref1, ref2, ref3, ref4, ref5, ref6, ref7)
    }


    "swap" - types { implicit conn =>
      val eid = Ns.refs(Set(ref1, ref2, ref3, ref4, ref5, ref6)).save.transact.eids.head

      // Replace value
      Ns(eid).refs.swap(ref6 -> ref8).update.transact
      Ns.refs.query.get.head ==> Set(ref1, ref2, ref3, ref4, ref5, ref8)

      // Replacing value to existing value simply deletes it
      Ns(eid).refs.swap(ref5 -> ref8).update.transact
      Ns.refs.query.get.head ==> Set(ref1, ref2, ref3, ref4, ref8)

      // Replace multiple values (vararg)
      Ns(eid).refs.swap(ref3 -> ref6, ref4 -> ref7).update.transact
      Ns.refs.query.get.head ==> Set(ref1, ref2, ref6, ref7, ref8)

      // Missing old value has no effect. The new value is inserted (upsert semantics)
      Ns(eid).refs.swap(ref4 -> ref9).update.transact
      Ns.refs.query.get.head ==> Set(ref1, ref2, ref6, ref7, ref8, ref9)

      // Replace with Seq of oldValue->newValue pairs
      Ns(eid).refs.swap(Seq(ref2 -> ref5)).update.transact
      Ns.refs.query.get.head ==> Set(ref1, ref5, ref6, ref7, ref8, ref9)

      // Replacing with empty Seq of oldValue->newValue pairs has no effect
      Ns(eid).refs.swap(Seq.empty[(Long, Long)]).update.transact
      Ns.refs.query.get.head ==> Set(ref1, ref5, ref6, ref7, ref8, ref9)


      // Can't swap duplicate from/to values
      intercept[MoleculeException](
        Ns(42).refs.swap(ref1 -> ref2, ref1 -> ref3).update.transact
      ).message ==> "Can't swap from duplicate retract values."

      intercept[MoleculeException](
        Ns(42).refs.swap(ref1 -> ref3, ref2 -> ref3).update.transact
      ).message ==> "Can't swap to duplicate replacement values."
    }


    "remove" - types { implicit conn =>
      val eid = Ns.refs(Set(ref1, ref2, ref3, ref4, ref5, ref6)).save.transact.eids.head

      // Retract value
      Ns(eid).refs.remove(ref6).update.transact
      Ns.refs.query.get.head ==> Set(ref1, ref2, ref3, ref4, ref5)

      // Retracting non-existing value has no effect
      Ns(eid).refs.remove(ref7).update.transact
      Ns.refs.query.get.head ==> Set(ref1, ref2, ref3, ref4, ref5)

      // Retracting duplicate values removes the distinct value
      Ns(eid).refs.remove(ref5, ref5).update.transact
      Ns.refs.query.get.head ==> Set(ref1, ref2, ref3, ref4)

      // Retract multiple values (vararg)
      Ns(eid).refs.remove(ref3, ref4).update.transact
      Ns.refs.query.get.head ==> Set(ref1, ref2)

      // Retract Seq of values
      Ns(eid).refs.remove(Seq(ref2)).update.transact
      Ns.refs.query.get.head ==> Set(ref1)

      // Retracting empty Seq of values has no effect
      Ns(eid).refs.remove(Seq.empty[Long]).update.transact
      Ns.refs.query.get.head ==> Set(ref1)
    }
  }
}
