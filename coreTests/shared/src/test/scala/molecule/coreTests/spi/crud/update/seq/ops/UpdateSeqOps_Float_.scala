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

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.floatSeq(List(float1, float2, float2)).save.transact.map(_.id)
        _ <- Ns.floatSeq.query.get.map(_.head ==> List(float1, float2, float2))

        // Applying Seq of values replaces previous Seq
        _ <- Ns(id).floatSeq(List(float3, float4, float4)).update.transact
        _ <- Ns.floatSeq.query.get.map(_.head ==> List(float3, float4, float4))

        // Applying empty Seq of values deletes previous Seq
        _ <- Ns(id).floatSeq(List.empty[Float]).update.transact
        _ <- Ns.floatSeq.query.get.map(_ ==> Nil)

        id <- Ns.floatSeq(List(float1, float2, float2)).save.transact.map(_.id)
        // Applying nothing deletes previous Seq
        _ <- Ns(id).floatSeq().update.transact
        _ <- Ns.floatSeq.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.floatSeq(List(float1)).save.transact.map(_.id)

        // Add value to end of Seq
        _ <- Ns(id).floatSeq.add(float2).update.transact
        _ <- Ns.floatSeq.query.get.map(_.head ==> List(float1, float2))

        // Add existing value
        _ <- Ns(id).floatSeq.add(float1).update.transact
        _ <- Ns.floatSeq.query.get.map(_.head ==> List(float1, float2, float1))

        // Add multiple values (vararg)
        _ <- Ns(id).floatSeq.add(float3, float4).update.transact
        _ <- Ns.floatSeq.query.get.map(_.head ==> List(float1, float2, float1, float3, float4))

        // Add multiple values (Seq)
        _ <- Ns(id).floatSeq.add(List(float4, float5)).update.transact
        _ <- Ns.floatSeq.query.get.map(_.head ==> List(float1, float2, float1, float3, float4, float4, float5))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).floatSeq.add(List.empty[Float]).update.transact
        _ <- Ns.floatSeq.query.get.map(_.head ==> List(float1, float2, float1, float3, float4, float4, float5))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.floatSeq(List(
          float1, float2, float3, float4, float5, float6, float7,
          float1, float2, float3, float4, float5, float6, float7,
        )).save.transact.map(_.id)

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

        // Remove multiple values (vararg)
        _ <- Ns(id).floatSeq.remove(float4, float5).update.transact
        _ <- Ns.floatSeq.query.get.map(_.head ==> List(
          float1, float2, float3,
          float1, float2, float3,
        ))

        // Remove multiple values (Seq)
        _ <- Ns(id).floatSeq.remove(List(float2, float3)).update.transact
        _ <- Ns.floatSeq.query.get.map(_.head ==> List(
          float1,
          float1
        ))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).floatSeq.remove(List.empty[Float]).update.transact
        _ <- Ns.floatSeq.query.get.map(_.head ==> List(float1, float1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).floatSeq.remove(Seq(float1)).update.transact
        _ <- Ns.floatSeq.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
