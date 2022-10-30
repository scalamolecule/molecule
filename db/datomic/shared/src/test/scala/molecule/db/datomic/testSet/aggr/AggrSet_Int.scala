//package molecule.db.datomic.testSet.aggr
//
//import molecule.coreTests.dataModels.core.types.dsl.CardOne._
//import molecule.db.datomic._
//import molecule.db.datomic.setup.DatomicTestSuite
//import utest._
//
//object AggrSet_Int extends DatomicTestSuite {
//
//
//  lazy val tests = Tests {
//
//    "count, countDistinct" - cardOne { implicit conn =>
////        One.n.int.ints insert List(
////          (1, int1, Set(int1, int2)),
////          (2, int2, Set(int2, int3)),
////          (2, int2, Set(int3, int4)),
////          (2, int3, Set(int3, int4)),
////        )
//        One.n.int.insert(List(
//          (1, int1),
//          (2, int2),
//          (2, int2),
//          (2, int3),
//        )).transact
//
//        One.n(count).query.get ==> List(4)
//        One.n(countDistinct).query.get ==> List(2)
//
//        One.int(count).query.get ==> List(4)
//        One.int(countDistinct).query.get ==> List(3)
//
//        One.ints(count).query.get ==> List(8)
//        One.ints(countDistinct).query.get ==> List(4)
//
//        One.n.a1.int.apply(count).query.get ==> List(
//          (1, 1),
//          (2, 3)
//        )
//        One.n.a1.int(countDistinct).query.get ==> List(
//          (1, 1),
//          (2, 2)
//        )
//
//        One.n.a1.ints(count).query.get ==> List(
//          (1, 2),
//          (2, 6)
//        )
//        One.n.a1.ints(countDistinct).query.get ==> List(
//          (1, 2),
//          (2, 3)
//        )
//
//        One.int(count).ints(count).query.get ==> List(
//          (8, 8)
//        )
//        One.int(countDistinct).ints(count).query.get ==> List(
//          (3, 8)
//        )
//        One.int(count).ints(countDistinct).query.get ==> List(
//          (8, 4)
//        )
//        One.int(countDistinct).ints(countDistinct).query.get ==> List(
//          (3, 4)
//        ))
//
//        One.n.a1.int(count).ints(count).query.get ==> List(
//          (1, 2, 2),
//          (2, 6, 6)
//        )
//        One.n.a1.int(countDistinct).ints(count).query.get ==> List(
//          (1, 1, 2),
//          (2, 2, 6)
//        )
//        One.n.a1.int(count).ints(countDistinct).query.get ==> List(
//          (1, 2, 2),
//          (2, 6, 3)
//        )
//        One.n.a1.int(countDistinct).ints(countDistinct).query.get ==> List(
//          (1, 1, 2),
//          (2, 2, 3)
//        ))
//    }
//
//
//    "min" - cardOne { implicit futConn =>
//        One.int.ints insert List(
//          (int1, Set(int1, int2)),
//          (int2, Set(int2, int3)),
//          (int3, Set(int2, int4)))
//
//        One.int(min).query.get.head ==> int1)
//        One.int(min(1)).query.get.head ==> List(int1))
//        One.int(min(2)).query.get.head ==> List(int1, int2))
//
//        One.ints(min).query.get.head ==> Set(int1))
//        One.ints(min(1)).query.get.head ==> List(Set(int1)))
//        One.ints(min(2)).query.get.head ==> List(Set(int1, int2)))
//
//
//        // Card-many attribute values coalesce to one Set.
//        // So mixing those with aggregates is likely not so useful.
//        One.int(min).ints.query.get ==> List(
//          (int1, Set(int1, int4, int3, int2))
//        )
//        One.int(min(2)).ints.query.get ==> List(
//          (List(int1, int1), Set(int1, int4, int3, int2))
//        )
//
//
//        // Aggregate card-many attribute values
//
//        One.int.ints(min).query.get ==> List(
//          (int1, Set(int1)),
//          (int2, Set(int2)),
//          (int3, Set(int2))
//        )
//
//        One.int.ints(min(2)).query.get ==> List(
//          (int1, List(Set(int1, int2))),
//          (int2, List(Set(int2, int3))),
//          (int3, List(Set(int2, int4)))
//        )
//
//
//        // Aggregate both card-one and card-many values
//
//        One.int(min).ints(min).query.get ==> List(
//          (int1, Set(int1))
//        )
//
//        One.int(min(2)).ints(min).query.get ==> List(
//          (List(int1, int1), Set(int1))
//        )
//
//        One.int(min).ints(min(2)).query.get ==> List(
//          (int1, List(Set(int1, int2)))
//        )
//
//        One.int(min(2)).ints(min(2)).query.get ==> List(
//          (List(int1, int1), List(Set(int1, int2)))
//        )
//    }
//
//
//    "max" - cardOne { implicit futConn =>
//        One.int.ints insert List(
//          (int1, Set(int1, int2)),
//          (int2, Set(int2, int3)),
//          (int3, Set(int2, int4)))
//
//        One.int(max).query.get.head ==> int3)
//        One.int(max(1)).query.get.head ==> List(int3))
//        One.int(max(2)).query.get.head ==> List(int3, int2))
//
//        One.ints(max).query.get.head ==> Set(int4))
//        One.ints(max(1)).query.get.head ==> List(Set(int4)))
//        One.ints(max(2)).query.get.head ==> List(Set(int3, int4)))
//
//
//        // Card-many attribute values coalesce to one Set.
//        // So mixing those with aggregates is likely not so useful.
//        One.int(max).ints.query.get ==> List(
//          (int3, Set(int1, int4, int3, int2))
//        )
//        One.int(max(2)).ints.query.get ==> List(
//          (List(int3, int3), Set(int1, int4, int3, int2))
//        )
//
//
//        // Aggregate card-many attribute values
//
//        One.int.ints(max).query.get ==> List(
//          (int1, Set(int2)),
//          (int2, Set(int3)),
//          (int3, Set(int4))
//        )
//
//        One.int.ints(max(2)).query.get ==> List(
//          (int1, List(Set(int1, int2))),
//          (int2, List(Set(int2, int3))),
//          (int3, List(Set(int2, int4)))
//        )
//
//
//        // Aggregate both card-one and card-many values
//
//        One.int(max).ints(max).query.get ==> List(
//          (int3, Set(int4))
//        )
//
//        One.int(max(2)).ints(max).query.get ==> List(
//          (List(int3, int3), Set(int4))
//        )
//
//        One.int(max).ints(max(2)).query.get ==> List(
//          (int3, List(Set(int3, int4)))
//        )
//
//        One.int(max(2)).ints(max(2)).query.get ==> List(
//          (List(int3, int3), List(Set(int3, int4)))
//        )
//    }
//
//
//    "rand" - cardOne { implicit conn =>
//        One.int.ints insert List(
//          (int1, Set(int1, int2)),
//          (int2, Set(int2, int3)),
//          (int3, Set(int2, int4)))
//
//        val all = Seq(int1, int2, int3, int4)
//
//        One.int(rand).get.map(rows => all.contains(rows.head) ==> true)
//        One.int(rand(1)).get.map(rows => all.intersect(rows.head).nonEmpty ==> true)
//        One.int(rand(2)).get.map(rows => all.intersect(rows.head).nonEmpty ==> true)
//
//        // Check Set values
//        One.ints(rand).get.map(rows => all.contains(rows.head.head) ==> true)
//        One.ints(rand(1)).get.map(rows => all.intersect(rows.head.head.toSeq).nonEmpty ==> true)
//        One.ints(rand(2)).get.map(rows => all.intersect(rows.head.head.toSeq).nonEmpty ==> true)
//    }
//
//
//    "sample" - cardOne { implicit futConn =>
//      for {
//        One.int.ints insert List(
//          (int1, Set(int1, int2)),
//          (int2, Set(int2, int3)),
//          (int3, Set(int2, int4)))
//
//        val all = Seq(int1, int2, int3, int4)
//
//        One.int(sample).get.map(rows => all.contains(rows.head) ==> true)
//        One.int(sample(1)).get.map(rows => all.intersect(rows.head).nonEmpty ==> true)
//        One.int(sample(2)).get.map(rows => all.intersect(rows.head).nonEmpty ==> true)
//
//        // Check Set values
//        One.ints(sample).get.map(rows => all.contains(rows.head.head) ==> true)
//        One.ints(sample(1)).get.map(rows => all.intersect(rows.head.head.toSeq).nonEmpty ==> true)
//        One.ints(sample(2)).get.map(rows => all.intersect(rows.head.head.toSeq).nonEmpty ==> true)
//    }
//
//
//    "distinct" - cardOne { implicit conn =>
//        One.n.int.ints insert List(
//          (1, int1, Set(int1, int2)),
//          (2, int2, Set(int2, int3)),
//          (2, int2, Set(int3, int4)),
//          (2, int3, Set(int3, int4))
//        )
//
//
//        // Card-one
//
//        One.int.a1.query.get ==> List(
//          int1,
//          int2,
//          int3,
//        )
//        One.n.int.a1.query.get ==> List(
//          (1, int1),
//          (2, int2),
//          (2, int3),
//        )
//
//        // Distinct values are returned in a List
//        One.int(distinct).query.get.head.sorted ==> List(int1, int2, int3))
//        One.n.a1.int(distinct).query.get.map(r => (r._1, r._2.sorted)) ==> List(
//          (1, List(int1)),
//          (2, List(int2, int3)),
//        )
//
//
//        // Card-many
//
//        One.ints.query.get ==> List(
//          Set(int1, int2, int3, int4)
//        )
//        One.n.a1.ints.query.get ==> List(
//          (1, Set(int1, int2)),
//          (2, Set(int2, int3, int4)),
//        )
//
//        // Distinct Set of values are returned in a List
//        One.ints(distinct).query.get ==> List(
//          List(Set(int1, int2, int3, int4))
//        )
//        One.n.a1.ints(distinct).query.get ==> List(
//          (1, List(Set(int1, int2))),
//          (2, List(Set(int2, int3, int4))),
//        )
//
//
//        // Card-one/many
//
//        One.int.a1.ints.query.get ==> List(
//          (int1, Set(int1, int2)),
//          (int2, Set(int2, int3, int4)),
//          (int3, Set(int3, int4)),
//        )
//
//        // Equivalent to
//        One.int.a1.ints(distinct).query.get ==> List(
//          (int1, List(Set(int1, int2))),
//          (int2, List(Set(int2, int3, int4))),
//          (int3, List(Set(int3, int4))),
//        )
//        One.int(distinct).ints.query.get.map(r => (r._1.sorted, r._2)) ==> List(
//          (List(int1, int2, int3), Set(int1, int2, int3, int4))
//        )
//        One.int(distinct).ints(distinct).query.get.map(r => (r._1.sorted, r._2)) ==> List(
//          (List(int1, int2, int3), List(Set(int1, int2, int3, int4)))
//        )
//
//        One.n.int.a1.ints.query.get ==> List(
//          (1, int1, Set(int1, int2)),
//          (2, int2, Set(int2, int3, int4)),
//          (2, int3, Set(int3, int4)),
//        )
//        One.n.int.a1.ints(distinct).query.get ==> List(
//          (1, int1, List(Set(int1, int2))),
//          (2, int2, List(Set(int2, int3, int4))),
//          (2, int3, List(Set(int3, int4))),
//        )
//        One.n.a1.int(distinct).ints.query.get.map(r => (r._1, r._2.sorted, r._3)) ==> List(
//          (1, List(int1), Set(int1, int2)),
//          (2, List(int2, int3), Set(int2, int3, int4)),
//        )
//        One.n.a1.int(distinct).ints(distinct).query.get.map(r => (r._1, r._2.sorted, r._3)) ==> List(
//          (1, List(int1), List(Set(int1, int2))),
//          (2, List(int2, int3), List(Set(int2, int3, int4))),
//        )
//    }
//  }
//}