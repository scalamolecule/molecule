// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrSet.any


import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSet_Long_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      Ns.n.longs.insert(List(
        (1, Set(long1, long2)),
        (2, Set(long2, long3)),
        (2, Set(long3, long4)),
        (2, Set(long3, long4)),
      )).transact

      // Non-aggregated card-many Set of attribute values coalesce
      Ns.n.a1.longs.query.get ==> List(
        (1, Set(long1, long2)),
        (2, Set(long2, long3, long4)), // 3 rows coalesced
      )

      // Use `distinct` keyword to retrieve unique Sets of values
      Ns.n.a1.longs(distinct).query.get ==> List(
        (1, Set(Set(long1, long2))),
        (2, Set(
          Set(long2, long3),
          Set(long3, long4) // 2 rows coalesced
        ))
      )

      Ns.longs(distinct).query.get ==> List(
        Set(
          Set(long1, long2),
          Set(long2, long3),
          Set(long3, long4),
        )
      )
    }


    "min" - types { implicit conn =>
      Ns.n.longs.insert(List(
        (1, Set(long1, long2)),
        (2, Set(long2, long3)),
        (2, Set(long3, long4)),
        (2, Set(long3, long4)),
      )).transact

      Ns.longs(min).query.get ==> List(Set(long1))
      Ns.longs(min(1)).query.get ==> List(Set(long1))
      Ns.longs(min(2)).query.get ==> List(Set(long1, long2))

      Ns.n.longs(min).query.get ==> List(
        (1, Set(long1)),
        (2, Set(long2)),
      )
      // Same as
      Ns.n.longs(min(1)).query.get ==> List(
        (1, Set(long1)),
        (2, Set(long2)),
      )

      Ns.n.longs(min(2)).query.get ==> List(
        (1, Set(long1, long2)),
        (2, Set(long2, long3)),
      )
    }


    "max" - types { implicit futConn =>
      Ns.n.longs.insert(List(
        (1, Set(long1, long2)),
        (2, Set(long2, long3)),
        (2, Set(long3, long4)),
        (2, Set(long3, long4)),
      )).transact

      Ns.longs(max).query.get ==> List(Set(long4))
      Ns.longs(max(1)).query.get ==> List(Set(long4))
      Ns.longs(max(2)).query.get ==> List(Set(long3, long4))

      Ns.n.longs(max).query.get ==> List(
        (1, Set(long2)),
        (2, Set(long4)),
      )
      // Same as
      Ns.n.longs(max(1)).query.get ==> List(
        (1, Set(long2)),
        (2, Set(long4)),
      )

      Ns.n.longs(max(2)).query.get ==> List(
        (1, Set(long1, long2)),
        (2, Set(long3, long4)),
      )
    }


    "rand" - types { implicit conn =>
      Ns.n.longs.insert(List(
        (1, Set(long1, long2)),
        (2, Set(long2, long3)),
        (2, Set(long3, long4)),
        (2, Set(long3, long4)),
      )).transact
      val all = Set(long1, long2, long3, long4)
      all.contains(Ns.longs(rand).query.get.head.head) ==> true
      all.intersect(Ns.longs(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.longs(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - types { implicit futConn =>
      Ns.n.longs.insert(List(
        (1, Set(long1, long2)),
        (2, Set(long2, long3)),
        (2, Set(long3, long4)),
        (2, Set(long3, long4)),
      )).transact
      val all = Set(long1, long2, long3, long4)
      all.contains(Ns.longs(sample).query.get.head.head) ==> true
      all.intersect(Ns.longs(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.longs(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - types { implicit conn =>
      Ns.n.longs.insert(List(
        (1, Set(long1, long2)),
        (2, Set(long2, long3)),
        (2, Set(long3, long4)),
        (2, Set(long3, long4)),
      )).transact

      Ns.n(count).query.get ==> List(4)
      Ns.n(countDistinct).query.get ==> List(2)

      Ns.longs(count).query.get ==> List(8)
      Ns.longs(countDistinct).query.get ==> List(4)

      Ns.n.a1.longs(count).query.get ==> List(
        (1, 2),
        (2, 6)
      )
      Ns.n.a1.longs(countDistinct).query.get ==> List(
        (1, 2),
        (2, 3)
      )
    }
  }
}