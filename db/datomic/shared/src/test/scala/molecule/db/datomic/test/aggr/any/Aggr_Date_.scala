// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.any

import java.util.Date
import molecule.boilerplate.api.Keywords._
import molecule.boilerplate.api.Keywords._
import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object Aggr_Date_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - cardOne { implicit conn =>
      One.n.date.insert(List(
        (1, date1),
        (2, date2),
        (2, date2),
        (2, date3),
      )).transact

      // Distinct values are returned in a List
      One.date.apply(distinct).query.get.head.sorted ==> List(date1, date2, date3)
      One.n.a1.date(distinct).query.get.map(r => (r._1, r._2.sorted)) ==> List(
        (1, List(date1)),
        (2, List(date2, date3)),
      )
    }


    "min" - cardOne { implicit conn =>
      One.date.insert(List(date1, date2, date3)).transact
      One.date.apply(min).query.get.head ==> date1
      One.date(min(1)).query.get.head ==> List(date1)
      One.date(min(2)).query.get.head ==> List(date1, date2)
    }


    "max" - cardOne { implicit futConn =>
      One.date.insert(List(date1, date2, date3)).transact
      One.date(max).query.get.head ==> date3
      One.date(max(1)).query.get.head ==> List(date3)
      One.date(max(2)).query.get.head ==> List(date3, date2)
    }


    "rand" - cardOne { implicit conn =>
      One.date.insert(List(date1, date2, date3)).transact
      val all = Seq(date1, date2, date3, date4)
      all.contains(One.date(rand).query.get.head) ==> true
      all.intersect(One.date(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(One.date(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - cardOne { implicit futConn =>
      One.date.insert(List(date1, date2, date3)).transact
      val all = Seq(date1, date2, date3, date4)
      all.contains(One.date(sample).query.get.head) ==> true
      all.intersect(One.date(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(One.date(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - cardOne { implicit conn =>
      One.n.date.insert(List(
        (1, date1),
        (2, date2),
        (2, date2),
        (2, date3),
      )).transact

      One.n(count).query.get ==> List(4)
      One.n(countDistinct).query.get ==> List(2)

      One.date(count).query.get ==> List(4)
      One.date(countDistinct).query.get ==> List(3)

      One.n.a1.date(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      One.n.a1.date(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}