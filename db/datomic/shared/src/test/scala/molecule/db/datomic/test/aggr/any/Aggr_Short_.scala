// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.any

import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object Aggr_Short_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - cardOne { implicit conn =>
      One.n.short.insert(List(
        (1, short1),
        (2, short2),
        (2, short2),
        (2, short3),
      )).transact

      // Distinct values are returned in a List
      One.short.apply(distinct).query.get.head.sorted ==> List(short1, short2, short3)
      One.n.a1.short(distinct).query.get.map(r => (r._1, r._2.sorted)) ==> List(
        (1, List(short1)),
        (2, List(short2, short3)),
      )
    }


    "min" - cardOne { implicit conn =>
      One.short.insert(List(short1, short2, short3)).transact
      One.short.apply(min).query.get.head ==> short1
      One.short(min(1)).query.get.head ==> List(short1)
      One.short(min(2)).query.get.head ==> List(short1, short2)
    }


    "max" - cardOne { implicit futConn =>
      One.short.insert(List(short1, short2, short3)).transact
      One.short(max).query.get.head ==> short3
      One.short(max(1)).query.get.head ==> List(short3)
      One.short(max(2)).query.get.head ==> List(short3, short2)
    }


    "rand" - cardOne { implicit conn =>
      One.short.insert(List(short1, short2, short3)).transact
      val all = Seq(short1, short2, short3, short4)
      all.contains(One.short(rand).query.get.head) ==> true
      all.intersect(One.short(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(One.short(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - cardOne { implicit futConn =>
      One.short.insert(List(short1, short2, short3)).transact
      val all = Seq(short1, short2, short3, short4)
      all.contains(One.short(sample).query.get.head) ==> true
      all.intersect(One.short(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(One.short(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - cardOne { implicit conn =>
      One.n.short.insert(List(
        (1, short1),
        (2, short2),
        (2, short2),
        (2, short3),
      )).transact

      One.n(count).query.get ==> List(4)
      One.n(countDistinct).query.get ==> List(2)

      One.short(count).query.get ==> List(4)
      One.short(countDistinct).query.get ==> List(3)

      One.n.a1.short(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      One.n.a1.short(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}