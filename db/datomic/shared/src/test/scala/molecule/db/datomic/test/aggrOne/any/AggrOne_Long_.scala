// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.any


import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_Long_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - cardOne { implicit conn =>
      NsOne.n.long.insert(List(
        (1, long1),
        (2, long2),
        (2, long2),
        (2, long3),
      )).transact

      NsOne.n.a1.long.query.get.sortBy(_._2) ==> List(
        (1, long1),
        (2, long2), // 2 rows coalesced
        (2, long3),
      )

      // Distinct values are returned in a List
      NsOne.n.a1.long.apply(distinct).query.get ==> List(
        (1, Set(long1)),
        (2, Set(long2, long3)),
      )

      NsOne.long(distinct).query.get.head ==> Set(
        long1, long2, long3
      )
    }


    "min" - cardOne { implicit conn =>
      NsOne.long.insert(List(long1, long2, long3)).transact
      NsOne.long(min).query.get.head ==> long1
      NsOne.long(min(1)).query.get.head ==> Set(long1)
      NsOne.long(min(2)).query.get.head ==> Set(long1, long2)
    }


    "max" - cardOne { implicit futConn =>
      NsOne.long.insert(List(long1, long2, long3)).transact
      NsOne.long(max).query.get.head ==> long3
      NsOne.long(max(1)).query.get.head ==> Set(long3)
      NsOne.long(max(2)).query.get.head ==> Set(long3, long2)
    }


    "rand" - cardOne { implicit conn =>
      NsOne.long.insert(List(long1, long2, long3)).transact
      val all = Set(long1, long2, long3, long4)
      all.contains(NsOne.long.apply(rand).query.get.head) ==> true
      all.intersect(NsOne.long(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsOne.long(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - cardOne { implicit futConn =>
      NsOne.long.insert(List(long1, long2, long3)).transact
      val all = Set(long1, long2, long3, long4)
      all.contains(NsOne.long(sample).query.get.head) ==> true
      all.intersect(NsOne.long(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsOne.long(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - cardOne { implicit conn =>
      NsOne.n.long.insert(List(
        (1, long1),
        (2, long2),
        (2, long2),
        (2, long3),
      )).transact

      NsOne.n(count).query.get ==> List(4)
      NsOne.n(countDistinct).query.get ==> List(2)

      NsOne.long(count).query.get ==> List(4)
      NsOne.long(countDistinct).query.get ==> List(3)

      NsOne.n.a1.long(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      NsOne.n.a1.long(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}