// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.one.decimal

import molecule.core.api.Api_async
import molecule.core.spi.{Spi_async, TxReport}
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.concurrent.Future

trait FilterOneDecimal_Float_ extends CoreTestSuite with Api_async { spi: Spi_async =>


  override lazy val tests = Tests {

    "modulo" - types { implicit conn =>
      def load: Future[TxReport] = Ns.i.float.insert(
        (1, float1),
        (2, float2),
        (3, float3),
        (4, float4),
        (5, float5),
        (6, float6),
        (7, float7),
        (8, float8),
        (9, float9),
      ).transact

      if (database == "SQlite") {
        // Remainder in SQlite is a whole number
        for {
          _ <- load
          _ <- Ns.float.%(float2, 0).query.get.map(_ ==> List(float2, float4, float6, float8))
          _ <- Ns.float.%(float2, 1).query.i.get.map(_ ==> List(float1, float3, float5, float7, float9))

          _ <- Ns.float.%(float3, 0).query.get.map(_ ==> List(float3, float6, float9))
          _ <- Ns.float.%(float3, 1).query.get.map(_ ==> List(float1, float4, float7))
          _ <- Ns.float.%(float3, 2).query.get.map(_ ==> List(float2, float5, float8))

          _ <- Ns.i.float_.%(float2, 0).query.get.map(_ ==> List(2, 4, 6, 8))
          _ <- Ns.i.float_.%(float2, 1).query.get.map(_ ==> List(1, 3, 5, 7, 9))

          _ <- Ns.i.float_.%(float3, 0).query.get.map(_ ==> List(3, 6, 9))
          _ <- Ns.i.float_.%(float3, 1).query.get.map(_ ==> List(1, 4, 7))
          _ <- Ns.i.float_.%(float3, 2).query.get.map(_ ==> List(2, 5, 8))
        } yield ()

      } else {
        for {
          _ <- load
          _ <- Ns.float.%(float2, float0).query.get.map(_ ==> List(float2, float4, float6, float8))
          _ <- Ns.float.%(float2, float1).query.i.get.map(_ ==> List(float1, float3, float5, float7, float9))

          _ <- Ns.float.%(float3, float0).query.get.map(_ ==> List(float3, float6, float9))
          _ <- Ns.float.%(float3, float1).query.get.map(_ ==> List(float1, float4, float7))
          _ <- Ns.float.%(float3, float2).query.get.map(_ ==> List(float2, float5, float8))

          _ <- Ns.i.float_.%(float2, float0).query.get.map(_ ==> List(2, 4, 6, 8))
          _ <- Ns.i.float_.%(float2, float1).query.get.map(_ ==> List(1, 3, 5, 7, 9))

          _ <- Ns.i.float_.%(float3, float0).query.get.map(_ ==> List(3, 6, 9))
          _ <- Ns.i.float_.%(float3, float1).query.get.map(_ ==> List(1, 4, 7))
          _ <- Ns.i.float_.%(float3, float2).query.get.map(_ ==> List(2, 5, 8))
        } yield ()
      }
    }


    "comparison" - types { implicit conn =>
      for {
        _ <- Ns.i.float.insert(
          (1, float1),
          (2, float2),
          (3, float3),
          (4, float4),
          (5, float5),
          (6, float6),
          (7, float7),
          (8, float8),
          (9, float9),
        ).transact

        _ <- Ns.i.a1.float_.>(float2).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8, 9))
        _ <- Ns.i.a1.float_.>(float2).float_.<=(float8).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8))
        _ <- Ns.i.a1.float_.>(float2).float_.<=(float8).float_.not(float4, float5).query.get.map(_ ==> List(3, 6, 7, 8))
      } yield ()
    }
  }
}