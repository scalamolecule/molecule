package molecule.db.compliance.test.aggregation.number

import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import org.scalactic.Equality

/** Floating point precision with Scala Float is error prone.
 *
 * Hard to get equal/not equal correct with floating point impressions. To avoid unexpected results we therefore
 * disallow operations on aggregated Float values.
 *
 * @param suite
 * @param api
 */
case class AggrNum_Float(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  val wrong = "Unexpected success"
  val msg   = "sum/median/avg/variance/stddev operations on aggregated Float values are not supported " +
    "to avoid floating precision problems."

  "sum" - types {
    given Equality[Float] = tolerantFloatEquality(toleranceFloat)
    for {
      _ <- Entity.i.float.insert(List(
        (1, float1),
        (1, float2),
        (2, float2),
        (2, float3),
        (2, float4),
      )).transact

      // 1 attribute
      _ <- Entity.float(sum).query.get.map(_.head ==~ float1 + float2 + float2 + float3 + float4)

      _ <- Entity.float(sum)(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
      _ <- Entity.float(sum).not(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
      _ <- Entity.float(sum).<(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
      _ <- Entity.float(sum).>(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
      _ <- Entity.float(sum).<=(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
      _ <- Entity.float(sum).>=(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }


      // n attributes
      _ <- Entity.i.a1.float(sum).query.get.map { res =>
        res(0)._2 ==~ float1 + float2
        res(1)._2 ==~ float2 + float3 + float4
      }
      _ <- Entity.i.float(sum)(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
      _ <- Entity.i.float(sum).not(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
      _ <- Entity.i.float(sum).<(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
      _ <- Entity.i.float(sum).>(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
      _ <- Entity.i.float(sum).<=(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
      _ <- Entity.i.float(sum).>=(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
    } yield ()
  }


  "median" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.i.float.insert(List(
        (1, float1),
        (1, float2),
        (2, float2),
        (2, float5),
        (2, float9),
      )).transact

      // 1 attribute
      _ <- Entity.float(median).query.get.map(_.head ==~ float2.toString.toDouble) // middle number

      // n attributes
      _ <- Entity.i.a1.float(median).query.get.map { res =>
        res(0)._2 ==~ (float1 + float2) / 2.0
        res(1)._2 ==~ float5.toString.toDouble // middle number
      }
    } yield ()
  }


  "median ops" - types {
    if (Seq("mariadb", "mysql", "sqlite").contains(database)) {
      Entity.float(median)(1.0).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on median not implemented for this database."
        }
    } else {
      given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Entity.i.float.insert(List(
          (1, float1),
          (1, float2),
          (2, float2),
          (2, float5),
          (2, float9),
        )).transact

        // 1 attribute
        _ <- Entity.float(median)(double1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
        _ <- Entity.float(median).not(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
        _ <- Entity.float(median).<(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
        _ <- Entity.float(median).>(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
        _ <- Entity.float(median).<=(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
        _ <- Entity.float(median).>=(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }

        // n attributes
        _ <- Entity.i.float(median)(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
        _ <- Entity.i.float(median).not(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
        _ <- Entity.i.float(median).<(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
        _ <- Entity.i.float(median).>(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
        _ <- Entity.i.float(median).<=(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
        _ <- Entity.i.float(median).>=(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
      } yield ()
    }
  }


  "avg" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.i.float.insert(List(
        (1, float1),
        (1, float2),
        (2, float2),
        (2, float3),
        (2, float4),
      )).transact

      // 1 attribute
      _ <- Entity.float(avg).query.get.map(_.head ==~ (float1 + float2 + float2 + float3 + float4) / 5.0)

      _ <- Entity.float(avg)(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
      _ <- Entity.float(avg).not(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
      _ <- Entity.float(avg).<(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
      _ <- Entity.float(avg).>(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
      _ <- Entity.float(avg).<=(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
      _ <- Entity.float(avg).>=(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }

      // n attributes
      _ <- Entity.i.a1.float(avg).query.get.map { res =>
        res(0)._2 ==~ (float1 + float2) / 2.0
        res(1)._2 ==~ (float2 + float3 + float4) / 3.0
      }
      _ <- Entity.i.float(avg)(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
      _ <- Entity.i.float(avg).not(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
      _ <- Entity.i.float(avg).<(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
      _ <- Entity.i.float(avg).>(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
      _ <- Entity.i.float(avg).<=(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
      _ <- Entity.i.float(avg).>=(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
    } yield ()
  }


  "variance" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.i.float.insert(List(
        (1, float1),
        (1, float2),
        (2, float2),
        (2, float3),
        (2, float4),
      )).transact

      // 1 attribute
      _ <- Entity.float(variance).query.get.map(_.head ==~ varianceOf(float1, float2, float2, float3, float4))

      // n attributes
      _ <- Entity.i.a1.float(variance).query.get.map { res =>
        res(0)._2 ==~ varianceOf(float1, float2)
        res(1)._2 ==~ varianceOf(float2, float3, float4)
      }
    } yield ()
  }

  "variance ops" - types {
    if (Seq("mariadb", "mysql", "sqlite").contains(database)) {
      Entity.float(variance)(1.0).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on variance not implemented for this database."
        }
    } else {
      given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Entity.i.float.insert(List(
          (1, float1),
          (1, float2),
          (2, float2),
          (2, float3),
          (2, float4),
        )).transact

        // 1 attribute
        _ <- Entity.float(variance)(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
        _ <- Entity.float(variance).not(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
        _ <- Entity.float(variance).<(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
        _ <- Entity.float(variance).>(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
        _ <- Entity.float(variance).<=(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
        _ <- Entity.float(variance).>=(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }

        // n attributes
        _ <- Entity.i.float(variance)(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
        _ <- Entity.i.float(variance).not(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
        _ <- Entity.i.float(variance).<(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
        _ <- Entity.i.float(variance).>(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
        _ <- Entity.i.float(variance).<=(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
        _ <- Entity.i.float(variance).>=(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
      } yield ()
    }
  }


  "stddev" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    for {
      _ <- Entity.i.float.insert(List(
        (1, float1),
        (1, float2),
        (2, float2),
        (2, float3),
        (2, float4),
      )).transact

      // 1 attribute
      _ <- Entity.float(stddev).query.get.map(_.head ==~ stdDevOf(float1, float2, float2, float3, float4))

      // n attributes
      _ <- Entity.i.a1.float(stddev).query.get.map { res =>
        res(0)._2 ==~ stdDevOf(float1, float2)
        res(1)._2 ==~ stdDevOf(float2, float3, float4)
      }
    } yield ()
  }

  "stddev ops" - types {
    if (Seq("mariadb", "mysql", "sqlite").contains(database)) {
      Entity.float(stddev)(1.0).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on stddev not implemented for this database."
        }
    } else {
      given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Entity.i.float.insert(List(
          (1, float1),
          (1, float2),
          (2, float2),
          (2, float3),
          (2, float4),
        )).transact

        // 1 attribute
        _ <- Entity.float(stddev)(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
        _ <- Entity.float(stddev).not(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
        _ <- Entity.float(stddev).<(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
        _ <- Entity.float(stddev).>(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
        _ <- Entity.float(stddev).<=(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
        _ <- Entity.float(stddev).>=(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }

        // n attributes
        _ <- Entity.i.float(stddev)(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
        _ <- Entity.i.float(stddev).not(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
        _ <- Entity.i.float(stddev).<(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
        _ <- Entity.i.float(stddev).>(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
        _ <- Entity.i.float(stddev).<=(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
        _ <- Entity.i.float(stddev).>=(float1).query.get.map(_ ==> wrong).recover { case ModelError(err) => err ==> msg }
      } yield ()
    }
  }
}