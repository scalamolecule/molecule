// GENERATED CODE ********************************
package molecule.coreTests.test.filter.set.compare

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSetCompare_Float_ extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, Set(float1, float2))
      val b = (2, Set(float2, float3, float4))
      for {
        _ <- Ns.i.floats.insert(List(a, b)).transact

        // <
        _ <- Ns.i.a1.floats.hasLt(float0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.floats.hasLt(float1).query.get.map(_ ==> List())
        _ <- Ns.i.a1.floats.hasLt(float2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.floats.hasLt(float3).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.floats.hasLe(float0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.floats.hasLe(float1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.floats.hasLe(float2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.floats.hasLe(float3).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.floats.hasGt(float0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.floats.hasGt(float1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.floats.hasGt(float2).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.floats.hasGt(float3).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.floats.hasGe(float0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.floats.hasGe(float1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.floats.hasGe(float2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.floats.hasGe(float3).query.get.map(_ ==> List(b))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b) = (1, 2)
      for {
        _ <- Ns.i.floats.insert(List(
          (a, Set(float1, float2)),
          (b, Set(float2, float3, float4))
        )).transact

        // <
        _ <- Ns.i.a1.floats_.hasLt(float0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.floats_.hasLt(float1).query.get.map(_ ==> List())
        _ <- Ns.i.a1.floats_.hasLt(float2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.floats_.hasLt(float3).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.floats_.hasLe(float0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.floats_.hasLe(float1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.floats_.hasLe(float2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.floats_.hasLe(float3).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.floats_.hasGt(float0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.floats_.hasGt(float1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.floats_.hasGt(float2).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.floats_.hasGt(float3).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.floats_.hasGe(float0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.floats_.hasGe(float1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.floats_.hasGe(float2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.floats_.hasGe(float3).query.get.map(_ ==> List(b))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(Set(float1, float2)))
      val b = (2, Some(Set(float2, float3, float4)))
      val c = (3, None)
      for {
        _ <- Ns.i.floats_?.insert(a, b, c).transact

        // <
        _ <- Ns.i.a1.floats_?.hasLt(Some(float0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.floats_?.hasLt(Some(float1)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.floats_?.hasLt(Some(float2)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.floats_?.hasLt(Some(float3)).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.floats_?.hasLe(Some(float0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.floats_?.hasLe(Some(float1)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.floats_?.hasLe(Some(float2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.floats_?.hasLe(Some(float3)).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.floats_?.hasGt(Some(float0)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.floats_?.hasGt(Some(float1)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.floats_?.hasGt(Some(float2)).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.floats_?.hasGt(Some(float3)).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.floats_?.hasGe(Some(float0)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.floats_?.hasGe(Some(float1)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.floats_?.hasGe(Some(float2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.floats_?.hasGe(Some(float3)).query.get.map(_ ==> List(b))


        // None comparison matches any asserted values
        _ <- Ns.i.a1.floats_?.hasLt(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.floats_?.hasGt(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.floats_?.hasLe(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.floats_?.hasGe(None).query.get.map(_ ==> List(a, b))
      } yield ()
    }
  }
}