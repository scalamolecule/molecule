// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.any

import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object Aggr_Char_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - cardOne { implicit conn =>
      One.n.char.insert(List(
        (1, char1),
        (2, char2),
        (2, char2),
        (2, char3),
      )).transact

      // Distinct values are returned in a List
      One.char.apply(distinct).query.get.head.sorted ==> List(char1, char2, char3)
      One.n.a1.char(distinct).query.get.map(r => (r._1, r._2.sorted)) ==> List(
        (1, List(char1)),
        (2, List(char2, char3)),
      )
    }


    "min" - cardOne { implicit conn =>
      One.char.insert(List(char1, char2, char3)).transact
      One.char.apply(min).query.get.head ==> char1
      One.char(min(1)).query.get.head ==> List(char1)
      One.char(min(2)).query.get.head ==> List(char1, char2)
    }


    "max" - cardOne { implicit futConn =>
      One.char.insert(List(char1, char2, char3)).transact
      One.char(max).query.get.head ==> char3
      One.char(max(1)).query.get.head ==> List(char3)
      One.char(max(2)).query.get.head ==> List(char3, char2)
    }


    "rand" - cardOne { implicit conn =>
      One.char.insert(List(char1, char2, char3)).transact
      val all = Seq(char1, char2, char3, char4)
      all.contains(One.char(rand).query.get.head) ==> true
      all.intersect(One.char(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(One.char(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - cardOne { implicit futConn =>
      One.char.insert(List(char1, char2, char3)).transact
      val all = Seq(char1, char2, char3, char4)
      all.contains(One.char(sample).query.get.head) ==> true
      all.intersect(One.char(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(One.char(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - cardOne { implicit conn =>
      One.n.char.insert(List(
        (1, char1),
        (2, char2),
        (2, char2),
        (2, char3),
      )).transact

      One.n(count).query.get ==> List(4)
      One.n(countDistinct).query.get ==> List(2)

      One.char(count).query.get ==> List(4)
      One.char(countDistinct).query.get ==> List(3)

      One.n.a1.char(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      One.n.a1.char(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}