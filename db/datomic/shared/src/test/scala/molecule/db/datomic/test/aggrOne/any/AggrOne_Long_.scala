// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.any


import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_Long_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      Ns.n.long.insert(List(
        (1, long1),
        (2, long2),
        (2, long2),
        (2, long3),
      )).transact

      Ns.n.a1.long.query.get.sortBy(_._2) ==> List(
        (1, long1),
        (2, long2), // 2 rows coalesced
        (2, long3),
      )

      // Distinct values are returned in a List
      Ns.n.a1.long.apply(distinct).query.get ==> List(
        (1, Set(long1)),
        (2, Set(long2, long3)),
      )

      Ns.long(distinct).query.get.head ==> Set(
        long1, long2, long3
      )
    }


    "min" - types { implicit conn =>
      Ns.long.insert(List(long1, long2, long3)).transact
      Ns.long(min).query.get ==> List(long1)
      Ns.long(min(1)).query.get ==> List(Set(long1))
      Ns.long(min(2)).query.get ==> List(Set(long1, long2))
    }


    "max" - types { implicit futConn =>
      Ns.long.insert(List(long1, long2, long3)).transact
      Ns.long(max).query.get ==> List(long3)
      Ns.long(max(1)).query.get ==> List(Set(long3))
      Ns.long(max(2)).query.get ==> List(Set(long3, long2))
    }


    "rand" - types { implicit conn =>
      Ns.long.insert(List(long1, long2, long3)).transact
      val all = Set(long1, long2, long3, long4)
      all.contains(Ns.long.apply(rand).query.get.head) ==> true
      all.intersect(Ns.long(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.long(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - types { implicit futConn =>
      Ns.long.insert(List(long1, long2, long3)).transact
      val all = Set(long1, long2, long3, long4)
      all.contains(Ns.long(sample).query.get.head) ==> true
      all.intersect(Ns.long(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.long(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - types { implicit conn =>
      Ns.n.long.insert(List(
        (1, long1),
        (2, long2),
        (2, long2),
        (2, long3),
      )).transact

      Ns.n(count).query.get ==> List(4)
      Ns.n(countDistinct).query.get ==> List(2)

      Ns.long(count).query.get ==> List(4)
      Ns.long(countDistinct).query.get ==> List(3)

      Ns.n.a1.long(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      Ns.n.a1.long(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}