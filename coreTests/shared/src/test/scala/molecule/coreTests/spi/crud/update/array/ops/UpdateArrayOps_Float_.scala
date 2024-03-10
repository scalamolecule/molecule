// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.array.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import molecule.coreTests.util.Array2List
import utest._

trait UpdateArrayOps_Float_ extends CoreTestSuite with Array2List with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.floatArray(Array(float1, float2, float2)).save.transact.map(_.id)
        _ <- Ns.floatArray.query.get.map(_.head ==> Array(float1, float2, float2))

        // Applying Array of values replaces previous Array
        _ <- Ns(id).floatArray(Array(float3, float4, float4)).update.transact
        _ <- Ns.floatArray.query.get.map(_.head ==> Array(float3, float4, float4))

        // Applying empty Array of values deletes previous Array
        _ <- Ns(id).floatArray(Seq.empty[Float]).update.transact
        _ <- Ns.floatArray.query.get.map(_ ==> Nil)

        id <- Ns.floatArray(Array(float1, float2, float2)).save.transact.map(_.id)
        // Applying empty value deletes previous Array
        _ <- Ns(id).floatArray().update.transact
        _ <- Ns.floatArray.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.floatArray(Array(float1)).save.transact.map(_.id)

        // Add value to end of Array
        _ <- Ns(id).floatArray.add(float2).update.transact
        _ <- Ns.floatArray.query.get.map(_.head ==> Array(float1, float2))

        // Add existing value
        _ <- Ns(id).floatArray.add(float1).update.transact
        _ <- Ns.floatArray.query.get.map(_.head ==> Array(float1, float2, float1))

        // Add multiple values (vararg)
        _ <- Ns(id).floatArray.add(float3, float4).update.transact
        _ <- Ns.floatArray.query.get.map(_.head ==> Array(float1, float2, float1, float3, float4))

        // Add Iterable of values
        // Seq
        _ <- Ns(id).floatArray.add(Seq(float4, float5)).update.transact
        _ <- Ns.floatArray.query.get.map(_.head ==> Array(float1, float2, float1, float3, float4, float4, float5))
        // Array
        _ <- Ns(id).floatArray.add(Array(float6)).update.transact
        _ <- Ns.floatArray.query.get.map(_.head ==> Array(float1, float2, float1, float3, float4, float4, float5, float6))
        // Iterable
        _ <- Ns(id).floatArray.add(Iterable(float7)).update.transact
        _ <- Ns.floatArray.query.get.map(_.head ==> Array(float1, float2, float1, float3, float4, float4, float5, float6, float7))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).floatArray.add(Seq.empty[Float]).update.transact
        _ <- Ns.floatArray.query.get.map(_.head ==> Array(float1, float2, float1, float3, float4, float4, float5, float6, float7))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.floatArray(Array(
          float1, float2, float3, float4, float5, float6, float7,
          float1, float2, float3, float4, float5, float6, float7,
        )).save.transact.map(_.id)

        // Remove all instances of a value
        _ <- Ns(id).floatArray.remove(float7).update.transact
        _ <- Ns.floatArray.query.get.map(_.head ==> Array(
          float1, float2, float3, float4, float5, float6,
          float1, float2, float3, float4, float5, float6,
        ))

        // Removing non-existing value has no effect
        _ <- Ns(id).floatArray.remove(float9).update.transact
        _ <- Ns.floatArray.query.get.map(_.head ==> Array(
          float1, float2, float3, float4, float5, float6,
          float1, float2, float3, float4, float5, float6,
        ))

        // Removing duplicate values has same effect as applying the value once
        _ <- Ns(id).floatArray.remove(float6, float6).update.transact
        _ <- Ns.floatArray.query.get.map(_.head ==> Array(
          float1, float2, float3, float4, float5,
          float1, float2, float3, float4, float5,
        ))

        // Remove multiple values (vararg)
        _ <- Ns(id).floatArray.remove(float4, float5).update.transact
        _ <- Ns.floatArray.query.get.map(_.head ==> Array(
          float1, float2, float3,
          float1, float2, float3,
        ))

        // Remove Iterable of values
        _ <- Ns(id).floatArray.remove(Array(float3)).update.transact
        _ <- Ns.floatArray.query.get.map(_.head ==> Array(
          float1, float2,
          float1, float2,
        ))

        _ <- Ns(id).floatArray.remove(Seq(float2)).update.transact
        _ <- Ns.floatArray.query.get.map(_.head ==> Array(
          float1,
          float1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).floatArray.remove(Seq.empty[Float]).update.transact
        _ <- Ns.floatArray.query.get.map(_.head ==> Array(float1, float1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).floatArray.remove(Seq(float1)).update.transact
        _ <- Ns.floatArray.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
