//package molecule.db.datomic.testSet.aggr
//
//import molecule.coreTests.dataModels.core.types.dsl.CardOne._
//import molecule.db.datomic._
//import molecule.db.datomic.setup.DatomicTestSuite
//import utest.Tests
//
//class AggrSetNumber_Int extends DatomicTestSuite {
//
//  lazy val tests = Tests {
//
//    "sum" - cardOne { implicit conn =>
//        One.n.int.insert(List(
//          (1, int1),
//          (2, int2),
//          (2, int3)))
//
//        // Card-one
//        One.int(sum).query.get.head ==> (
//          int1 +
//            int2 +
//            int3
//          )
//        One.n.int(sum).query.get ==> List(
//          (1, int1),
//          (2, int2 + int3),
//        )
//
//        // Card-many
//        // Distinct card-many values summed
//        One.ints(sum).query.get.head ==> Set(
//          int1 + int2 + int3 + int4
//        ))
//        One.int.a1.ints(sum).query.get ==> List(
//          (int1, Set(int1 + int2)),
//          (int2, Set(int2 + int3)),
//          (int3, Set(int2 + int4)),
//        ))
//
//        // Card-one/many
//        // Mixing card-one/many attributes can appear un-intuitive
//        One.n.int(sum).ints(sum).query.get ==> List(
//          (1,
//            int1 + int1, // twice since 2 ints values match (1, 2)
//            Set(int1 + int2)),
//          (2,
//            int2 + int2 + // twice since 2 ints values match (2, 3)
//              int3 + int3, // twice since 2 ints values match (2, 4)
//            Set(int2 + int3 + int2 + int4)),
//        ))
//    }
//
//
//    "median" - cardOne { implicit futConn =>
//        One.n.int.ints insert List(
//          (1, int1, Set(int1, int2)),
//          (2, int2, Set(int2, int3)),
//          (2, int4, Set(int2, int4)))
//
//        // OBS! Datomic rounds down to nearest whole number!
//        // This is another semantic than described on wikipedia:
//        // https://en.wikipedia.org/wiki/Median
//        // See also
//        // https://forum.datomic.com/t/unexpected-median-rounding/517
//
//        // Card-one
//        One.int(median).query.get.head ==> int2)
//        One.n.int(median).query.get ==> List(
//          (1, int1),
//          (2, int3),
//        ))
//
//        // Card-many
//        One.ints(median).query.get.head ==> Set(int2))
//        One.int.a1.ints(median).query.get ==> List(
//          (int1, Set(int1)),
//          (int2, Set(int2)),
//          (int4, Set(int3)),
//        ))
//
//        // Card-one/many
//        One.n.int(median).ints(median).query.get ==> List(
//          (1, int1, Set(int1)),
//          (2, int3, Set(int2)), // 2 2 3 4 - not 2.5 but next lower whole number (2)
//        ))
//    }
//
//
//    "avg" - cardOne { implicit conn =>
//        One.n.int.ints insert List(
//          (1, int1, Set(int1, int2)),
//          (2, int2, Set(int2, int3)),
//          (2, int4, Set(int2, int4)))
//
//        // Card-one
//        One.int(avg).query.get.head ==> (int1 + int2 + int4) / 3.0)
//        One.n.int(avg).query.get ==> List(
//          (1, int1 / 1.0),
//          (2, (int2 + int4) / 2.0),
//        ))
//
//        // Card-many
//        // (average of distinct values only)
//        One.ints(avg).query.get.head ==>
//          (int1 + int2 + int3 + int4) / 4.0
//        )
//        One.n.ints(avg).query.get ==> List(
//          (1, (int1 + int2) / 2.0),
//          (2, (int2 + int3 + int4) / 3.0),
//        ))
//
//        // Card-one/many
//        One.n.int(avg).ints(avg).query.get ==> List(
//          (1, int1 / 1.0, (int1 + int2) / 2.0),
//          // OBS: note that int2 is used twice for `ints` here when calculating `int` too
//          (2, (int2 + int4) / 2.0, (int2 + int3 + int2 + int4) / 4.0),
//        ))
//    }
//
//
//    "variance" - cardOne { implicit conn =>
//        One.n.int.ints insert List(
//          (1, int1, Set(int1, int2)),
//          (2, int2, Set(int2, int3)),
//          (2, int4, Set(int2, int4)))
//
//        // Card-one
//        One.int(variance).query.get.head ==> 1.5555555555555554)
//        One.n.int(variance).query.get ==> List(
//          (1, 0.0),
//          (2, 1.0),
//        ))
//
//        // Card-many
//        One.ints(variance).query.get.head ==> 1.25)
//        One.n.ints(variance).query.get ==> List(
//          (1, 0.25),
//          (2, 0.6666666666666666),
//        ))
//
//        // Card-one/many
//        One.n.int(variance).ints(variance).query.get ==> List(
//          (1, 0.0, 0.25),
//          // OBS: note that int2 is used twice for `ints` here when calculating `int` too
//          (2, 1.0, 0.6875),
//        ))
//    }
//
//
//    "stddev" - cardOne { implicit conn =>
//        One.n.int.ints insert List(
//          (1, int1, Set(int1, int2)),
//          (2, int2, Set(int2, int3)),
//          (2, int4, Set(int2, int4)))
//
//        // Card-one
//        One.int(stddev).query.get.head ==> 1.247219128924647)
//        One.n.int(stddev).query.get ==> List(
//          (1, 0.0),
//          (2, 1.0),
//        ))
//
//        // Card-many
//        One.ints(stddev).query.get.head ==> 1.118033988749895)
//        One.n.ints(stddev).query.get ==> List(
//          (1, 0.5),
//          (2, 0.816496580927726),
//        ))
//
//        // Card-one/many
//        One.n.int(stddev).ints(stddev).query.get ==> List(
//          (1, 0.0, 0.5),
//          // OBS: note that int2 is used twice for `ints` here when calculating `int` too
//          (2, 1.0, 0.82915619758885),
//        ))
//    }
//  }
//}