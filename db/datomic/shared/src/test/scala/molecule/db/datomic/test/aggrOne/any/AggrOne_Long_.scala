// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.any

import molecule.boilerplate.api.Keywords._
import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_Long_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - cardOne { implicit conn =>
      One.n.long.insert(List(
        (1, long1),
        (2, long2),
        (2, long2),
        (2, long3),
      )).transact

      // Distinct values are returned in a List
      One.long.apply(distinct).query.get.head.sorted ==> List(long1, long2, long3)
      One.n.a1.long(distinct).query.get.map(r => (r._1, r._2.sorted)) ==> List(
        (1, List(long1)),
        (2, List(long2, long3)),
      )
    }


    "min" - cardOne { implicit conn =>
      One.long.insert(List(long1, long2, long3)).transact
      One.long.apply(min).query.get.head ==> long1
      One.long(min(1)).query.get.head ==> List(long1)
      One.long(min(2)).query.get.head ==> List(long1, long2)
    }


    "max" - cardOne { implicit futConn =>
      One.long.insert(List(long1, long2, long3)).transact
      One.long(max).query.get.head ==> long3
      One.long(max(1)).query.get.head ==> List(long3)
      One.long(max(2)).query.get.head ==> List(long3, long2)
    }


    "rand" - cardOne { implicit conn =>
      One.long.insert(List(long1, long2, long3)).transact
      val all = Seq(long1, long2, long3, long4)
      all.contains(One.long(rand).query.get.head) ==> true
      all.intersect(One.long(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(One.long(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - cardOne { implicit futConn =>
      One.long.insert(List(long1, long2, long3)).transact
      val all = Seq(long1, long2, long3, long4)
      all.contains(One.long(sample).query.get.head) ==> true
      all.intersect(One.long(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(One.long(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - cardOne { implicit conn =>
      One.n.long.insert(List(
        (1, long1),
        (2, long2),
        (2, long2),
        (2, long3),
      )).transact

      One.n(count).query.get ==> List(4)
      One.n(countDistinct).query.get ==> List(2)

      One.long(count).query.get ==> List(4)
      One.long(countDistinct).query.get ==> List(3)

      One.n.a1.long(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      One.n.a1.long(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}