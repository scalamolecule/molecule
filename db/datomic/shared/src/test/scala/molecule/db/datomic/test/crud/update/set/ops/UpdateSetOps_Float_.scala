// GENERATED CODE ********************************
package molecule.db.datomic.test.crud.update.set.ops

import molecule.base.util.exceptions.MoleculeError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object UpdateSetOps_Float_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        eid <- Ns.floats(Set(float1, float2)).save.transact.map(_.eids.head)

        _ <- Ns(eid).floats(Set(float3, float4)).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float3, float4))

        // Apply Seq of values
        _ <- Ns(eid).floats(Set(float4, float5)).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float4, float5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(eid).floats(Seq.empty[Float]).update.transact
        _ <- Ns.floats.query.get.map(_ ==> Nil)

        _ <- Ns(eid).floats(Set(float1, float2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(eid).floats().update.transact
        _ <- Ns.floats.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        eid <- Ns.floats(Set(float1)).save.transact.map(_.eids.head)

        // Add value
        _ <- Ns(eid).floats.add(float2).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2))

        // Add existing value (no effect)
        _ <- Ns(eid).floats.add(float2).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2))

        // Add multiple values (vararg)
        _ <- Ns(eid).floats.add(float3, float4).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2, float3, float4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(eid).floats.add(Seq(float4, float5)).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2, float3, float4, float5))
        // Set
        _ <- Ns(eid).floats.add(Set(float6)).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2, float3, float4, float5, float6))
        // Iterable
        _ <- Ns(eid).floats.add(Iterable(float7)).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2, float3, float4, float5, float6, float7))

        // Add empty Seq of values (no effect)
        _ <- Ns(eid).floats.add(Seq.empty[Float]).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2, float3, float4, float5, float6, float7))
      } yield ()
    }


    "swap" - types { implicit conn =>
      for {
        eid <- Ns.floats(Set(float1, float2, float3, float4, float5, float6)).save.transact.map(_.eids.head)

        // Replace value
        _ <- Ns(eid).floats.swap(float6 -> float8).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2, float3, float4, float5, float8))

        // Replacing value to existing value simply deletes it
        _ <- Ns(eid).floats.swap(float5 -> float8).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2, float3, float4, float8))

        // Replace multiple values (vararg)
        _ <- Ns(eid).floats.swap(float3 -> float6, float4 -> float7).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2, float6, float7, float8))

        // Missing old value has no effect. The new value is inserted (upsert semantics)
        _ <- Ns(eid).floats.swap(float4 -> float9).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2, float6, float7, float8, float9))

        // Replace with Seq of oldValue->newValue pairs
        _ <- Ns(eid).floats.swap(Seq(float2 -> float5)).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float5, float6, float7, float8, float9))

        // Replacing with empty Seq of oldValue->newValue pairs has no effect
        _ <- Ns(eid).floats.swap(Seq.empty[(Float, Float)]).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float5, float6, float7, float8, float9))


        // Can't swap duplicate from/to values
        _ <- Ns(42).floats.swap(float1 -> float2, float1 -> float3).update.transact
          .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
          err ==> "Can't swap from duplicate retract values."
        }

        _ <- Ns(42).floats.swap(float1 -> float3, float2 -> float3).update.transact
          .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
          err ==> "Can't swap to duplicate replacement values."
        }
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        eid <- Ns.floats(Set(float1, float2, float3, float4, float5, float6)).save.transact.map(_.eids.head)

        // Retract value
        _ <- Ns(eid).floats.remove(float6).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2, float3, float4, float5))

        // Retracting non-existing value has no effect
        _ <- Ns(eid).floats.remove(float7).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2, float3, float4, float5))

        // Retracting duplicate values removes the distinct value
        _ <- Ns(eid).floats.remove(float5, float5).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2, float3, float4))

        // Retract multiple values (vararg)
        _ <- Ns(eid).floats.remove(float3, float4).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2))

        // Retract Seq of values
        _ <- Ns(eid).floats.remove(Seq(float2)).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1))

        // Retracting empty Seq of values has no effect
        _ <- Ns(eid).floats.remove(Seq.empty[Float]).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1))
      } yield ()
    }
  }
}
