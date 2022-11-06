// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.number


import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOneNum_Float_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - cardOne { implicit conn =>
      NsOne.n.float.insert(List(
        (1, float1),
        (2, float2),
        (2, float3),
      )).transact

      NsOne.float(sum).query.get.head ==> 6.600000023841858
      NsOne.n.float(sum).query.get ==> List(
        (1, float1),
        (2, float2 + float3),
      )
    }


    "median" - cardOne { implicit futConn =>
      NsOne.n.float.insert(List(
        (1, float1),
        (2, float2),
        (2, float4),
      )).transact

      // OBS! Datomic rounds down to nearest whole number
      // (when calculating the median for multiple numbers)!
      // This is another semantic than described on wikipedia:
      // https://en.wikipedia.org/wiki/Median
      // See also
      // https://forum.datomic.com/t/unexpected-median-rounding/517
      NsOne.float(median).query.get.head ==> float2
      NsOne.n.float(median).query.get ==> List(
        (1, float1),
        (2, 3.0),
      )
    }

    "avg" - cardOne { implicit conn =>
      NsOne.n.float.insert(List(
        (1, float1),
        (2, float2),
        (2, float4),
      )).transact

      NsOne.float(avg).query.get.head ==> 2.5666667222976685
      NsOne.n.float(avg).query.get ==> List(
        (1, 1.100000023841858),
        (2, 3.3000000715255737),
      )
    }


    "variance" - cardOne { implicit conn =>
      NsOne.n.float.insert(List(
        (1, float1),
        (2, float2),
        (2, float4),
      )).transact

      NsOne.float(variance).query.get.head ==> 1.882222303814359
      NsOne.n.float(variance).query.get ==> List(
        (1, 0.0),
        (2, 1.210000052452088),
      )
    }


    "stddev" - cardOne { implicit conn =>
      NsOne.n.float.insert(List(
        (1, float1),
        (2, float2),
        (2, float4),
      )).transact

      NsOne.float(stddev).query.get.head ==> 1.371941071553133
      NsOne.n.float(stddev).query.get ==> List(
        (1, 0.0),
        (2, 1.100000023841858),
      )
    }
  }
}