// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_Float_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.floats(Set(float1, float2)).save.transact.map(_.id)

        _ <- Ns(id).floats(Set(float3, float4)).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float3, float4))

        // Apply Seq of values
        _ <- Ns(id).floats(Set(float4, float5)).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float4, float5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(id).floats(Seq.empty[Float]).update.transact
        _ <- Ns.floats.query.get.map(_ ==> Nil)

        _ <- Ns(id).floats(Set(float1, float2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(id).floats().update.transact
        _ <- Ns.floats.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.floats(Set(float1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).floats.add(float2).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2))

        // Add existing value (no effect)
        _ <- Ns(id).floats.add(float2).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2))

        // Add multiple values (vararg)
        _ <- Ns(id).floats.add(float3, float4).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2, float3, float4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(id).floats.add(Seq(float4, float5)).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2, float3, float4, float5))
        // Set
        _ <- Ns(id).floats.add(Set(float6)).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2, float3, float4, float5, float6))
        // Iterable
        _ <- Ns(id).floats.add(Iterable(float7)).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2, float3, float4, float5, float6, float7))

        // Add empty Seq of values (no effect)
        _ <- Ns(id).floats.add(Seq.empty[Float]).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2, float3, float4, float5, float6, float7))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.floats(Set(float1, float2, float3, float4, float5, float6)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).floats.remove(float6).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2, float3, float4, float5))

        // Removing non-existing value has no effect
        _ <- Ns(id).floats.remove(float7).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2, float3, float4, float5))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).floats.remove(float5, float5).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2, float3, float4))

        // Remove multiple values (vararg)
        _ <- Ns(id).floats.remove(float3, float4).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2))

        // Remove Seq of values
        _ <- Ns(id).floats.remove(Seq(float2)).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).floats.remove(Seq.empty[Float]).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).floats.remove(Seq(float1)).update.transact
        _ <- Ns.floats.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
