// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.number

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class AggrNum_Float_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  // Using tolerant equality so that the test works with decimal number types too

  import api.*
  import suite.*

  "sum" - types { implicit conn =>
    implicit val tolerant = tolerantFloatEquality(toleranceFloat)
    for {
      _ <- Entity.i.float.insert(List(
        (1, float1),
        (1, float2),
        (2, float2),
        (2, float3),
        (2, float4),
      )).transact

      // Sum of all values
      _ <- Entity.float(sum).query.get.map(
        _.head ==~ float1 + float2 + float2 + float3 + float4
      )

      _ <- Entity.i.float(sum).query.get.map(_.collect {
        case (1, sum) => sum ==~ float1 + float2
        case (2, sum) => sum ==~ float2 + float3 + float4
      })
    } yield ()
  }


  "median" - types { implicit futConn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    // Different databases have different ways of calculating a median
    database match {
      case "datomic" =>
        for {
          _ <- Entity.i.float.insert(List(
            (1, float1),
            (1, float2),
            (2, float2),
            (2, float5),
            (2, float9),
          )).transact

          // Median of all values - middle number used if odd number of values
          // 1  2  2  5  9
          //       ^
          _ <- Entity.float(median).query.get.map(_.head ==~ float2.toString.toDouble) // middle number

          _ <- Entity.i.float(median).query.get.map(_.collect {
            case (1, median) => median ==~ float1.toDouble.floor // lower whole number
            case (2, median) => median ==~ float5.toString.toDouble // middle number
          })
        } yield ()

      case _ =>
        for {
          _ <- Entity.i.float.insert(List(
            (1, float1),
            (1, float2),
            (2, float2),
            (2, float5),
            (2, float9),
          )).transact

          _ <- Entity.float(median).query.get.map(_.head ==~ float2.toString.toDouble) // middle number

          _ <- Entity.i.float(median).query.get.map(_.collect {
            case (1, median) => median ==~ (float1 + float2).toDouble / 2.0 // average of 2 middle numbers
            case (2, median) => median ==~ float5.toString.toDouble // middle number
          })
        } yield ()
    }
  }


  "avg" - types { implicit conn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.i.float.insert(List(
        (1, float1),
        (1, float2),
        (2, float2),
        (2, float3),
        (2, float4),
      )).transact

      // Average of all values
      _ <- Entity.float(avg).query.get.map(
        _.head ==~ (float1 + float2 + float2 + float3 + float4).toDouble / 5.0
      )

      _ <- Entity.i.float(avg).query.get.map(_.collect {
        case (1, avg) => avg ==~ (float1 + float2).toDouble / 2.0
        case (2, avg) => avg ==~ (float2 + float3 + float4).toDouble / 3.0
      })
    } yield ()
  }


  "variance" - types { implicit conn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.i.float.insert(List(
        (1, float1),
        (1, float2),
        (2, float2),
        (2, float3),
        (2, float4),
      )).transact

      // Variance of all values
      _ <- Entity.float(variance).query.get.map(
        _.head ==~ varianceOf(float1, float2, float2, float3, float4)
      )

      _ <- Entity.i.float(variance).query.get.map(_.collect {
        case (1, variance) => variance ==~ varianceOf(float1, float2)
        case (2, variance) => variance ==~ varianceOf(float2, float3, float4)
      })
    } yield ()
  }


  "stddev" - types { implicit conn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.i.float.insert(List(
        (1, float1),
        (1, float2),
        (2, float2),
        (2, float3),
        (2, float4),
      )).transact

      // Standard deviation of all values
      _ <- Entity.float(stddev).query.get.map(
        _.head ==~ stdDevOf(float1, float2, float2, float3, float4)
      )

      _ <- Entity.i.float(stddev).query.get.map(_.collect {
        case (1, stddev) => stddev ==~ stdDevOf(float1, float2)
        case (2, stddev) => stddev ==~ stdDevOf(float2, float3, float4)
      })
    } yield ()
  }
}