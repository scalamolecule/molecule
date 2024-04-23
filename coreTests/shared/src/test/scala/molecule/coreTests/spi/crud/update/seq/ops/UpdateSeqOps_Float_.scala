// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.seq.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSeqOps_Float_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {


    "apply new values" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.floatSeq.query.get.map(_ ==> Nil)

        // Applying Seq of values to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).floatSeq(List(float1, float2, float2)).update.transact
        _ <- Ns.floatSeq.query.get.map(_.head ==> List(float1, float2, float2))

        // Applying Seq of values replaces previous values
        _ <- Ns(id).floatSeq(List(float2, float3, float3)).update.transact
        _ <- Ns.floatSeq.query.get.map(_.head ==> List(float2, float3, float3))

        // Add other attribute and update Seq attribute in one go
        _ <- Ns(id).s("foo").floatSeq(List(float3, float4, float4)).update.transact
        _ <- Ns.i.s.floatSeq.query.get.map(_.head ==> (42, "foo", List(float3, float4, float4)))

        // Applying empty Seq of values deletes attribute
        _ <- Ns(id).floatSeq(List.empty[Float]).update.transact
        _ <- Ns.floatSeq.query.get.map(_ ==> Nil)

        _ <- Ns(id).floatSeq(List(float1, float2, float2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).floatSeq().update.transact
        _ <- Ns.floatSeq.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.floatSeq.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Seq attribute adds the attribute with the update
        _ <- Ns(id).floatSeq.add(float1).update.transact
        _ <- Ns.floatSeq.query.get.map(_.head ==> List(float1))

        // Adding existing value to Seq adds it to the end
        _ <- Ns(id).floatSeq.add(float1).update.transact
        _ <- Ns.floatSeq.query.get.map(_.head ==> List(float1, float1))

        // Add new value to end of Seq
        _ <- Ns(id).floatSeq.add(float2).update.transact
        _ <- Ns.floatSeq.query.get.map(_.head ==> List(float1, float1, float2))

        // Add multiple values with varargs
        _ <- Ns(id).floatSeq.add(float3, float4).update.transact
        _ <- Ns.floatSeq.query.get.map(_.head ==> List(float1, float1, float2, float3, float4))

        // Add multiple values with Iterable
        _ <- Ns(id).floatSeq.add(List(float4, float5)).update.transact
        _ <- Ns.floatSeq.query.get.map(_.head ==> List(float1, float1, float2, float3, float4, float4, float5))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).floatSeq.add(Set.empty[Float]).update.transact
        _ <- Ns.floatSeq.query.get.map(_.head ==> List(float1, float1, float2, float3, float4, float4, float5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Seq attribute not yet asserted
        _ <- Ns.floatSeq.query.get.map(_ ==> Nil)

        // Removing value from non-asserted Seq has no effect
        _ <- Ns(id).floatSeq.remove(float1).update.transact
        _ <- Ns.floatSeq.query.get.map(_ ==> Nil)

        // Start with some values
        _ <- Ns(id).floatSeq.add(
          float1, float2, float3, float4, float5, float6, float7,
          float1, float2, float3, float4, float5, float6, float7,
        ).update.transact

        // Remove all instances of a value
        _ <- Ns(id).floatSeq.remove(float7).update.transact
        _ <- Ns.floatSeq.query.get.map(_.head ==> List(
          float1, float2, float3, float4, float5, float6,
          float1, float2, float3, float4, float5, float6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).floatSeq.remove(float9).update.transact
        _ <- Ns.floatSeq.query.get.map(_.head ==> List(
          float1, float2, float3, float4, float5, float6,
          float1, float2, float3, float4, float5, float6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).floatSeq.remove(float6, float6).update.transact
        _ <- Ns.floatSeq.query.get.map(_.head ==> List(
          float1, float2, float3, float4, float5,
          float1, float2, float3, float4, float5,
        ))

        // Remove multiple values with vararg
        _ <- Ns(id).floatSeq.remove(float4, float5).update.transact
        _ <- Ns.floatSeq.query.get.map(_.head ==> List(
          float1, float2, float3,
          float1, float2, float3,
        ))

        // Remove multiple values with Iterable
        _ <- Ns(id).floatSeq.remove(List(float2, float3)).update.transact
        _ <- Ns.floatSeq.query.get.map(_.head ==> List(
          float1,
          float1
        ))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).floatSeq.remove(Vector.empty[Float]).update.transact
        _ <- Ns.floatSeq.query.get.map(_.head ==> List(float1, float1))

        // Removing all remaining values deletes the attribute
        _ <- Ns(id).floatSeq.remove(Set(float1)).update.transact
        _ <- Ns.floatSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
