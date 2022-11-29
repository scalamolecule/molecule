// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.number

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOneNum_Float_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - types { implicit conn =>
      Ns.i.float.insert(List(
        (1, float1),
        (2, float2),
        (2, float4),
      )).transact

      Ns.float(sum).query.get ==> List(
        7.700000166893005 // float1 + float2 + float4
      )
      Ns.i.float(sum).query.get ==> List(
        (1, 1.100000023841858),
        (2, 6.6000001430511475), // float2 + float4
      )
    }


    "median" - types { implicit futConn =>
      Ns.i.float.insert(List(
        (1, float1),
        (2, float2),
        (2, float4),
      )).transact

      Ns.float(median).query.get ==> List(
        2.2f
      )
      Ns.i.float(median).query.get ==> List(
        (1, 1.100000023841858),
        (2, 3.0),
      )
      // OBS! Datomic rounds down to nearest whole number
      // when calculating the median for multiple numbers!
      // This is another semantic than described on wikipedia:
      // https://en.wikipedia.org/wiki/Median
      // See also
      // https://forum.datomic.com/t/unexpected-median-rounding/517
    }


    "avg" - types { implicit conn =>
      Ns.i.float.insert(List(
        (1, float1),
        (2, float2),
        (2, float4),
      )).transact

      Ns.float(avg).query.get ==> List(
        2.5666667222976685 // (float1 + float2 + float4) / 3.0
      )
      Ns.i.float(avg).query.get ==> List(
        (1, 1.100000023841858),
        (2, 3.3000000715255737), // (float2 + float4) / 2.0
      )
    }


    "variance" - types { implicit conn =>
      Ns.i.float.insert(List(
        (1, float1),
        (2, float2),
        (2, float4),
      )).transact

      Ns.float(variance).query.get ==> List(
        1.882222303814359
      )
      Ns.i.float(variance).query.get ==> List(
        (1, 0.0),
        (2, 1.210000052452088),
      )
    }


    "stddev" - types { implicit conn =>
      Ns.i.float.insert(List(
        (1, float1),
        (2, float2),
        (2, float4),
      )).transact

      Ns.float(stddev).query.get ==> List(
        1.371941071553133
      )
      Ns.i.float(stddev).query.get ==> List(
        (1, 0.0),
        (2, 1.100000023841858),
      )
    }
  }
}