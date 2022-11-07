package molecule.db.datomic.test.aggrSet.number

import molecule.coreTests.dataModels.core.types.dsl.CardSet._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import molecule.db.datomic.test.aggrSet.number.AggrSetNum_BigDecimal_.bigDecimal1
import utest._
import scala.collection.immutable.Set

object AggrSetNum_Double extends DatomicTestSuite {


  lazy val tests = Tests {

    "sum" - cardSet { implicit conn =>
      NsSet.n.doubles.insert(List(
        (1, Set(double1, double2)),
        (2, Set(double2, double3)),
        (2, Set(double3, double4)),
        (2, Set(double3, double4)),
      )).transact

      NsSet.doubles.apply(sum).query.get ==> List(
        Set(11) // double1 + double2 + double3 + double4
      )
      NsSet.n.doubles(sum).query.get ==> List(
        (1, Set(3.3000000000000003)), // double1 + double2
        (2, Set(9.9)), // double2 + double3 + double4
      )
    }


    "median" - cardSet { implicit futConn =>
      NsSet.n.doubles.insert(List(
        (1, Set(double1, double2)),
        (2, Set(double2, double3)),
        (2, Set(double3, double4)),
        (2, Set(double3, double4)),
      )).transact

      NsSet.doubles(median).query.get ==> List(
        Set(2.0)
      )
      NsSet.n.doubles(median).query.get ==> List(
        (1, Set(1.0)),
        (2, Set(3.3)),
      )
      // OBS! Datomic rounds down to nearest whole number
      // (when calculating the median for multiple numbers)!
      // This is another semantic than described on wikipedia:
      // https://en.wikipedia.org/wiki/Median
      // See also
      // https://forum.datomic.com/t/unexpected-median-rounding/517
    }


    "avg" - cardSet { implicit conn =>
      NsSet.n.doubles.insert(List(
        (1, Set(double1, double2)),
        (2, Set(double2, double3)),
        (2, Set(double3, double4)),
        (2, Set(double3, double4)),
      )).transact

      NsSet.doubles(avg).query.get ==> List(
        Set(2.75) // (double1 + double2 + double3 + double4) / 4.0
      )
      NsSet.n.doubles(avg).query.get ==> List(
        (1, Set(1.6500000000000001)), // (double1 + double2) / 2.0
        (2, Set(3.3000000000000003)), // (double2 + double3 + double4) / 3.0
      )
    }


    "variance" - cardSet { implicit conn =>
      NsSet.n.doubles.insert(List(
        (1, Set(double1, double2)),
        (2, Set(double2, double3)),
        (2, Set(double3, double4)),
        (2, Set(double3, double4)),
      )).transact

      NsSet.doubles(variance).query.get ==> List(
        Set(1.5125000000000002)
      )
      NsSet.n.doubles(variance).query.get ==> List(
        (1, Set(0.30250000000000005)),
        (2, Set(0.8066666666666668)),
      )
    }


    "stddev" - cardSet { implicit conn =>
      NsSet.n.doubles.insert(List(
        (1, Set(double1, double2)),
        (2, Set(double2, double3)),
        (2, Set(double3, double4)),
        (2, Set(double3, double4)),
      )).transact

      NsSet.doubles(stddev).query.get ==> List(
        Set(1.2298373876248845)
      )
      NsSet.n.doubles(stddev).query.get ==> List(
        (1, Set(0.55)),
        (2, Set(0.8981462390204987)),
      )
    }
  }
}