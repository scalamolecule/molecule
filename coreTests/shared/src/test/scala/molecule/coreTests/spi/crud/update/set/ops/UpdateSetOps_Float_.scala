// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.collection.immutable.Set

trait UpdateSetOps_Float_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Set attribute not yet asserted
        _ <- Ns.floatSet.query.get.map(_ ==> Nil)

        // Applying Set of values to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).floatSet(Set(float1, float2)).update.transact
        _ <- Ns.floatSet.query.get.map(_.head ==> Set(float1, float2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).floatSet(Set(float2, float3)).update.transact
        _ <- Ns.floatSet.query.get.map(_.head ==> Set(float2, float3))

        // Add other attribute and update Set attribute in one go
        _ <- Ns(id).s("foo").floatSet(Set(float3, float4)).update.transact
        _ <- Ns.i.s.floatSet.query.get.map(_.head ==> (42, "foo", Set(float3, float4)))

        // Applying empty Set of values deletes attribute
        _ <- Ns(id).floatSet(Set.empty[Float]).update.transact
        _ <- Ns.floatSet.query.get.map(_ ==> Nil)

        _ <- Ns(id).floatSet(Set(float1, float2)).update.transact
        // Apply nothing delete attribute
        _ <- Ns(id).floatSet().update.transact
        _ <- Ns.floatSet.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.floatSet.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Set attribute adds the attribute with the update
        _ <- Ns(id).floatSet.add(float1).update.transact
        _ <- Ns.floatSet.query.get.map(_.head ==> Set(float1))

        // Adding existing value to Set changes nothing
        _ <- Ns(id).floatSet.add(float1).update.transact
        _ <- Ns.floatSet.query.get.map(_.head ==> Set(float1))

        // Add new value
        _ <- Ns(id).floatSet.add(float2).update.transact
        _ <- Ns.floatSet.query.get.map(_.head ==> Set(float1, float2))

        // Add multiple values with vararg
        _ <- Ns(id).floatSet.add(float3, float4).update.transact
        _ <- Ns.floatSet.query.get.map(_.head ==> Set(float1, float2, float3, float4))

        // Add multiple values with Iterable
        _ <- Ns(id).floatSet.add(List(float5, float6)).update.transact
        _ <- Ns.floatSet.query.get.map(_.head ==> Set(float1, float2, float3, float4, float5, float6))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).floatSet.add(Set.empty[Float]).update.transact
        _ <- Ns.floatSet.query.get.map(_.head ==> Set(float1, float2, float3, float4, float5, float6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.floatSet.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Set has no effect
        _ <- Ns(id).floatSet.remove(float1).update.transact
        _ <- Ns.floatSet.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).floatSet.add(float1, float2, float3, float4, float5, float6, float7).update.transact

        // Remove value
        _ <- Ns(id).floatSet.remove(float7).update.transact
        _ <- Ns.floatSet.query.get.map(_.head ==> Set(float1, float2, float3, float4, float5, float6))

        // Removing non-existing value has no effect
        _ <- Ns(id).floatSet.remove(float9).update.transact
        _ <- Ns.floatSet.query.get.map(_.head ==> Set(float1, float2, float3, float4, float5, float6))

        // Removing duplicate values removes the distinct value (Set semantics)
        _ <- Ns(id).floatSet.remove(float6, float6).update.transact
        _ <- Ns.floatSet.query.get.map(_.head ==> Set(float1, float2, float3, float4, float5))

        // Remove multiple values with varargs
        _ <- Ns(id).floatSet.remove(float4, float5).update.transact
        _ <- Ns.floatSet.query.get.map(_.head ==> Set(float1, float2, float3))

        // Remove multiple values with Iterable
        _ <- Ns(id).floatSet.remove(List(float2, float3)).update.transact
        _ <- Ns.floatSet.query.get.map(_.head ==> Set(float1))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).floatSet.remove(Vector.empty[Float]).update.transact
        _ <- Ns.floatSet.query.get.map(_.head ==> Set(float1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).floatSet.remove(Set(float1)).update.transact
        _ <- Ns.floatSet.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
