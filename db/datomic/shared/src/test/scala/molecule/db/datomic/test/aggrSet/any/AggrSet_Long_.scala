// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrSet.any


import molecule.coreTests.dataModels.core.types.dsl.CardSet._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSet_Long_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - cardSet { implicit conn =>
      NsSet.n.longs.insert(List(
        (1, Set(long1, long2)),
        (2, Set(long2, long3)),
        (2, Set(long3, long4)),
        (2, Set(long3, long4)),
      )).transact

      // Non-aggregated card-many Set of attribute values coalesce
      NsSet.n.a1.longs.query.get ==> List(
        (1, Set(long1, long2)),
        (2, Set(long2, long3, long4)), // 3 rows coalesced
      )

      // Use `distinct` keyword to retrieve unique Sets of values
      NsSet.n.a1.longs(distinct).query.get ==> List(
        (1, Set(Set(long1, long2))),
        (2, Set(
          Set(long2, long3),
          Set(long3, long4) // 2 rows coalesced
        ))
      )

      NsSet.longs.apply(distinct).query.get ==> List(
        Set(
          Set(long1, long2),
          Set(long2, long3),
          Set(long3, long4),
        )
      )
    }


    "min" - cardSet { implicit conn =>
      NsSet.n.longs.insert(List(
        (1, Set(long1, long2)),
        (2, Set(long2, long3)),
        (2, Set(long3, long4)),
        (2, Set(long3, long4)),
      )).transact

      NsSet.longs(min).query.get ==> List(Set(long1))
      NsSet.longs(min(1)).query.get ==> List(Set(Set(long1)))
      NsSet.longs(min(2)).query.get ==> List(Set(Set(long1, long2)))

      NsSet.n.longs(min).query.get ==> List(
        (1, Set(long1)),
        (2, Set(long2)),
      )
      NsSet.n.longs(min(1)).query.get ==> List(
        (1, Set(Set(long1))),
        (2, Set(Set(long2))),
      )
      NsSet.n.longs(min(2)).query.get ==> List(
        (1, Set(Set(long1, long2))),
        (2, Set(Set(long2, long3))),
      )
    }


    "max" - cardSet { implicit futConn =>
      NsSet.n.longs.insert(List(
        (1, Set(long1, long2)),
        (2, Set(long2, long3)),
        (2, Set(long3, long4)),
        (2, Set(long3, long4)),
      )).transact

      NsSet.longs(max).query.get ==> List(Set(long4))
      NsSet.longs(max(1)).query.get ==> List(Set(Set(long4)))
      NsSet.longs(max(2)).query.get ==> List(Set(Set(long3, long4)))

      NsSet.n.longs(max).query.get ==> List(
        (1, Set(long2)),
        (2, Set(long4)),
      )
      NsSet.n.longs(max(1)).query.get ==> List(
        (1, Set(Set(long2))),
        (2, Set(Set(long4))),
      )
      NsSet.n.longs(max(2)).query.get ==> List(
        (1, Set(Set(long1, long2))),
        (2, Set(Set(long3, long4))),
      )
    }


    "rand" - cardSet { implicit conn =>
      NsSet.n.longs.insert(List(
        (1, Set(long1, long2)),
        (2, Set(long2, long3)),
        (2, Set(long3, long4)),
        (2, Set(long3, long4)),
      )).transact
      val all = Set(long1, long2, long3, long4)
      all.contains(NsSet.longs(rand).query.get.head.head) ==> true
      all.intersect(NsSet.longs(rand(1)).query.get.head.head).nonEmpty ==> true
      all.intersect(NsSet.longs(rand(2)).query.get.head.head).nonEmpty ==> true
    }


    "sample" - cardSet { implicit futConn =>
      NsSet.n.longs.insert(List(
        (1, Set(long1, long2)),
        (2, Set(long2, long3)),
        (2, Set(long3, long4)),
        (2, Set(long3, long4)),
      )).transact
      val all = Set(long1, long2, long3, long4)
      all.contains(NsSet.longs(sample).query.get.head.head) ==> true
      all.intersect(NsSet.longs(sample(1)).query.get.head.head).nonEmpty ==> true
      all.intersect(NsSet.longs(sample(2)).query.get.head.head).nonEmpty ==> true
    }


    "count, countDistinct" - cardSet { implicit conn =>
      NsSet.n.longs.insert(List(
        (1, Set(long1, long2)),
        (2, Set(long2, long3)),
        (2, Set(long3, long4)),
        (2, Set(long3, long4)),
      )).transact

      NsSet.n(count).query.get ==> List(4)
      NsSet.n(countDistinct).query.get ==> List(2)

      NsSet.longs(count).query.get ==> List(8)
      NsSet.longs(countDistinct).query.get ==> List(4)

      NsSet.n.a1.longs(count).query.get ==> List(
        (1, 2),
        (2, 6)
      )
      NsSet.n.a1.longs(countDistinct).query.get ==> List(
        (1, 2),
        (2, 3)
      )
    }
  }
}