// GENERATED CODE ********************************
package molecule.db.datomic.test.crud.updateSet.ops

import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object UpdateSetOps_Float_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      val eid = Ns.floats(Set(float1, float2)).save.transact.eids.head

      Ns(eid).floats(Set(float3, float4)).update.transact
      Ns.floats.query.get.head ==> Set(float3, float4)

      // Apply Seq of values
      Ns(eid).floats(Set(float4, float5)).update.transact
      Ns.floats.query.get.head ==> Set(float4, float5)

      // Apply empty Seq of values (deleting all values!)
      Ns(eid).floats(Seq.empty[Float]).update.transact
      Ns.floats.query.get ==> Nil

      Ns(eid).floats(Set(float1, float2)).update.transact

      // Delete all (apply no values)
      Ns(eid).floats().update.transact
      Ns.floats.query.get ==> Nil
    }


    "add" - types { implicit conn =>
      val eid = Ns.floats(Set(float1)).save.transact.eids.head

      // Add value
      Ns(eid).floats.add(float2).update.transact
      Ns.floats.query.get.head ==> Set(float1, float2)

      // Add existing value (no effect)
      Ns(eid).floats.add(float2).update.transact
      Ns.floats.query.get.head ==> Set(float1, float2)

      // Add multiple values (vararg)
      Ns(eid).floats.add(float3, float4).update.transact
      Ns.floats.query.get.head ==> Set(float1, float2, float3, float4)

      // Add Iterable of values (existing values unaffected)
      // Seq
      Ns(eid).floats.add(Seq(float4, float5)).update.transact
      Ns.floats.query.get.head ==> Set(float1, float2, float3, float4, float5)
      // Set
      Ns(eid).floats.add(Set(float6)).update.transact
      Ns.floats.query.get.head ==> Set(float1, float2, float3, float4, float5, float6)
      // Iterable
      Ns(eid).floats.add(Iterable(float7)).update.transact
      Ns.floats.query.get.head ==> Set(float1, float2, float3, float4, float5, float6, float7)

      // Add empty Seq of values (no effect)
      Ns(eid).floats.add(Seq.empty[Float]).update.transact
      Ns.floats.query.get.head ==> Set(float1, float2, float3, float4, float5, float6, float7)
    }


    "swap" - types { implicit conn =>
      val eid = Ns.floats(Set(float1, float2, float3, float4, float5, float6)).save.transact.eids.head

      // Replace value
      Ns(eid).floats.swap(float6 -> float8).update.transact
      Ns.floats.query.get.head ==> Set(float1, float2, float3, float4, float5, float8)

      // Replacing value to existing value simply deletes it
      Ns(eid).floats.swap(float5 -> float8).update.transact
      Ns.floats.query.get.head ==> Set(float1, float2, float3, float4, float8)

      // Replace multiple values (vararg)
      Ns(eid).floats.swap(float3 -> float6, float4 -> float7).update.transact
      Ns.floats.query.get.head ==> Set(float1, float2, float6, float7, float8)

      // Missing old value has no effect. The new value is inserted (upsert semantics)
      Ns(eid).floats.swap(float4 -> float9).update.transact
      Ns.floats.query.get.head ==> Set(float1, float2, float6, float7, float8, float9)

      // Replace with Seq of oldValue->newValue pairs
      Ns(eid).floats.swap(Seq(float2 -> float5)).update.transact
      Ns.floats.query.get.head ==> Set(float1, float5, float6, float7, float8, float9)

      // Replacing with empty Seq of oldValue->newValue pairs has no effect
      Ns(eid).floats.swap(Seq.empty[(Float, Float)]).update.transact
      Ns.floats.query.get.head ==> Set(float1, float5, float6, float7, float8, float9)


      // Can't swap duplicate from/to values
      intercept[MoleculeException](
        Ns(42).floats.swap(float1 -> float2, float1 -> float3).update.transact
      ).message ==> "Can't swap from duplicate retract values."

      intercept[MoleculeException](
        Ns(42).floats.swap(float1 -> float3, float2 -> float3).update.transact
      ).message ==> "Can't swap to duplicate replacement values."
    }


    "remove" - types { implicit conn =>
      val eid = Ns.floats(Set(float1, float2, float3, float4, float5, float6)).save.transact.eids.head

      // Retract value
      Ns(eid).floats.remove(float6).update.transact
      Ns.floats.query.get.head ==> Set(float1, float2, float3, float4, float5)

      // Retracting non-existing value has no effect
      Ns(eid).floats.remove(float7).update.transact
      Ns.floats.query.get.head ==> Set(float1, float2, float3, float4, float5)

      // Retracting duplicate values removes the distinct value
      Ns(eid).floats.remove(float5, float5).update.transact
      Ns.floats.query.get.head ==> Set(float1, float2, float3, float4)

      // Retract multiple values (vararg)
      Ns(eid).floats.remove(float3, float4).update.transact
      Ns.floats.query.get.head ==> Set(float1, float2)

      // Retract Seq of values
      Ns(eid).floats.remove(Seq(float2)).update.transact
      Ns.floats.query.get.head ==> Set(float1)

      // Retracting empty Seq of values has no effect
      Ns(eid).floats.remove(Seq.empty[Float]).update.transact
      Ns.floats.query.get.head ==> Set(float1)
    }
  }
}
