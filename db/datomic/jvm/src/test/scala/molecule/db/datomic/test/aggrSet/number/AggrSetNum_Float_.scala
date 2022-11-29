// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrSet.number

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import molecule.db.datomic.test.aggrSet.number.AggrSetNum_BigDecimal_.bigDecimal1
import utest._
import scala.collection.immutable.Set

object AggrSetNum_Float_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - types { implicit conn =>
      Ns.i.floats.insert(List(
        (1, Set(float1, float2)),
        (2, Set(float2, float3)),
        (2, Set(float3, float4)),
        (2, Set(float3, float4)),
      )).transact

      Ns.floats.apply(sum).query.get ==> List(
        Set(11.00000011920929) // float1 + float2 + float3 + float4
      )
      Ns.i.floats(sum).query.get ==> List(
        (1, Set(3.3000000715255737)), // float1 + float2
        (2, Set(9.900000095367432)), // float2 + float3 + float4
      )
    }


    "median" - types { implicit futConn =>
      Ns.i.floats.insert(List(
        (1, Set(float1, float2)),
        (2, Set(float2, float3)),
        (2, Set(float3, float4)),
        (2, Set(float3, float4)),
      )).transact

      Ns.floats(median).query.get ==> List(
        Set(2.0)
      )
      Ns.i.floats(median).query.get ==> List(
        (1, Set(1.0f)),
        (2, Set(3.3f)),
      )
      // OBS! Datomic rounds down to nearest whole number
      // (when calculating the median for multiple numbers)!
      // This is another semantic than described on wikipedia:
      // https://en.wikipedia.org/wiki/Median
      // See also
      // https://forum.datomic.com/t/unexpected-median-rounding/517
    }


    "avg" - types { implicit conn =>
      Ns.i.floats.insert(List(
        (1, Set(float1, float2)),
        (2, Set(float2, float3)),
        (2, Set(float3, float4)),
        (2, Set(float3, float4)),
      )).transact

      Ns.floats(avg).query.get ==> List(
        Set(2.7500000298023224) // (float1 + float2 + float3 + float4) / 4.0
      )
      Ns.i.floats(avg).query.get ==> List(
        (1, Set(1.6500000357627869)), // (float1 + float2) / 2.0
        (2, Set(3.300000031789144)), // (float2 + float3 + float4) / 3.0
      )
    }


    "variance" - types { implicit conn =>
      Ns.i.floats.insert(List(
        (1, Set(float1, float2)),
        (2, Set(float2, float3)),
        (2, Set(float3, float4)),
        (2, Set(float3, float4)),
      )).transact

      Ns.floats(variance).query.get ==> List(
        Set(1.5125000327825573)
      )
      Ns.i.floats(variance).query.get ==> List(
        (1, Set(0.302500013113022)),
        (2, Set(0.8066667016347285)),
      )
    }


    "stddev" - types { implicit conn =>
      Ns.i.floats.insert(List(
        (1, Set(float1, float2)),
        (2, Set(float2, float3)),
        (2, Set(float3, float4)),
        (2, Set(float3, float4)),
      )).transact

      Ns.floats(stddev).query.get ==> List(
        Set(1.2298374009528892)
      )
      Ns.i.floats(stddev).query.get ==> List(
        (1, Set(0.550000011920929)),
        (2, Set(0.8981462584872959)),
      )
    }
  }
}