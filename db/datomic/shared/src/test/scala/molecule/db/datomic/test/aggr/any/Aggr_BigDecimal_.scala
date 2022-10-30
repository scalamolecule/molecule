// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.any

import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object Aggr_BigDecimal_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - cardOne { implicit conn =>
      One.n.bigDecimal.insert(List(
        (1, bigDecimal1),
        (2, bigDecimal2),
        (2, bigDecimal2),
        (2, bigDecimal3),
      )).transact

      // Distinct values are returned in a List
      One.bigDecimal.apply(distinct).query.get.head.sorted ==> List(bigDecimal1, bigDecimal2, bigDecimal3)
      One.n.a1.bigDecimal(distinct).query.get.map(r => (r._1, r._2.sorted)) ==> List(
        (1, List(bigDecimal1)),
        (2, List(bigDecimal2, bigDecimal3)),
      )
    }


    "min" - cardOne { implicit conn =>
      One.bigDecimal.insert(List(bigDecimal1, bigDecimal2, bigDecimal3)).transact
      One.bigDecimal.apply(min).query.get.head ==> bigDecimal1
      One.bigDecimal(min(1)).query.get.head ==> List(bigDecimal1)
      One.bigDecimal(min(2)).query.get.head ==> List(bigDecimal1, bigDecimal2)
    }


    "max" - cardOne { implicit futConn =>
      One.bigDecimal.insert(List(bigDecimal1, bigDecimal2, bigDecimal3)).transact
      One.bigDecimal(max).query.get.head ==> bigDecimal3
      One.bigDecimal(max(1)).query.get.head ==> List(bigDecimal3)
      One.bigDecimal(max(2)).query.get.head ==> List(bigDecimal3, bigDecimal2)
    }


    "rand" - cardOne { implicit conn =>
      One.bigDecimal.insert(List(bigDecimal1, bigDecimal2, bigDecimal3)).transact
      val all = Seq(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4)
      all.contains(One.bigDecimal(rand).query.get.head) ==> true
      all.intersect(One.bigDecimal(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(One.bigDecimal(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - cardOne { implicit futConn =>
      One.bigDecimal.insert(List(bigDecimal1, bigDecimal2, bigDecimal3)).transact
      val all = Seq(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4)
      all.contains(One.bigDecimal(sample).query.get.head) ==> true
      all.intersect(One.bigDecimal(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(One.bigDecimal(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - cardOne { implicit conn =>
      One.n.bigDecimal.insert(List(
        (1, bigDecimal1),
        (2, bigDecimal2),
        (2, bigDecimal2),
        (2, bigDecimal3),
      )).transact

      One.n(count).query.get ==> List(4)
      One.n(countDistinct).query.get ==> List(2)

      One.bigDecimal(count).query.get ==> List(4)
      One.bigDecimal(countDistinct).query.get ==> List(3)

      One.n.a1.bigDecimal(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      One.n.a1.bigDecimal(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}