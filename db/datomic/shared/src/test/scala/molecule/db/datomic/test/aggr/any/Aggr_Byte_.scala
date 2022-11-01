// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.any

import molecule.boilerplate.api.Keywords._
import molecule.boilerplate.api.Keywords._
import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object Aggr_Byte_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - cardOne { implicit conn =>
      One.n.byte.insert(List(
        (1, byte1),
        (2, byte2),
        (2, byte2),
        (2, byte3),
      )).transact

      // Distinct values are returned in a List
      One.byte.apply(distinct).query.get.head.sorted ==> List(byte1, byte2, byte3)
      One.n.a1.byte(distinct).query.get.map(r => (r._1, r._2.sorted)) ==> List(
        (1, List(byte1)),
        (2, List(byte2, byte3)),
      )
    }


    "min" - cardOne { implicit conn =>
      One.byte.insert(List(byte1, byte2, byte3)).transact
      One.byte.apply(min).query.get.head ==> byte1
      One.byte(min(1)).query.get.head ==> List(byte1)
      One.byte(min(2)).query.get.head ==> List(byte1, byte2)
    }


    "max" - cardOne { implicit futConn =>
      One.byte.insert(List(byte1, byte2, byte3)).transact
      One.byte(max).query.get.head ==> byte3
      One.byte(max(1)).query.get.head ==> List(byte3)
      One.byte(max(2)).query.get.head ==> List(byte3, byte2)
    }


    "rand" - cardOne { implicit conn =>
      One.byte.insert(List(byte1, byte2, byte3)).transact
      val all = Seq(byte1, byte2, byte3, byte4)
      all.contains(One.byte(rand).query.get.head) ==> true
      all.intersect(One.byte(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(One.byte(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - cardOne { implicit futConn =>
      One.byte.insert(List(byte1, byte2, byte3)).transact
      val all = Seq(byte1, byte2, byte3, byte4)
      all.contains(One.byte(sample).query.get.head) ==> true
      all.intersect(One.byte(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(One.byte(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - cardOne { implicit conn =>
      One.n.byte.insert(List(
        (1, byte1),
        (2, byte2),
        (2, byte2),
        (2, byte3),
      )).transact

      One.n(count).query.get ==> List(4)
      One.n(countDistinct).query.get ==> List(2)

      One.byte(count).query.get ==> List(4)
      One.byte(countDistinct).query.get ==> List(3)

      One.n.a1.byte(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      One.n.a1.byte(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}