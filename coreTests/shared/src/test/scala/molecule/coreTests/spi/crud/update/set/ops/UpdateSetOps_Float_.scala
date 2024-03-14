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
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).floats(Set(float3, float4)).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float3, float4))

        // Applying empty Set of values deletes previous Set
        _ <- Ns(id).floats(Set.empty[Float]).update.transact
        _ <- Ns.floats.query.get.map(_ ==> Nil)

        id <- Ns.floats(Set(float1, float2)).save.transact.map(_.id)
        // Applying empty value deletes previous Set
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

        // Adding existing value has no effect (Set semantics of only unique values)
        _ <- Ns(id).floats.add(float2).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2))

        // Add multiple values (vararg)
        _ <- Ns(id).floats.add(float3, float4).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2, float3, float4))

        // Add multiple values (Seq)
        _ <- Ns(id).floats.add(Seq(float5, float6)).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2, float3, float4, float5, float6))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).floats.add(Seq.empty[Float]).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2, float3, float4, float5, float6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.floats(Set(float1, float2, float3, float4, float5, float6, float7)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).floats.remove(float7).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2, float3, float4, float5, float6))

        // Removing non-existing value has no effect
        _ <- Ns(id).floats.remove(float9).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2, float3, float4, float5, float6))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).floats.remove(float6, float6).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2, float3, float4, float5))

        // Remove multiple values (vararg)
        _ <- Ns(id).floats.remove(float4, float5).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2, float3))

        // Remove multiple values (Seq)
        _ <- Ns(id).floats.remove(Seq(float2, float3)).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).floats.remove(Seq.empty[Float]).update.transact
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).floats.remove(Seq(float1)).update.transact
        _ <- Ns.floats.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
