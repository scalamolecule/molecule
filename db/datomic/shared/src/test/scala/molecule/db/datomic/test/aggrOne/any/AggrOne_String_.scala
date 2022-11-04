// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.any

import molecule.boilerplate.api.Keywords._
import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_String_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - cardOne { implicit conn =>
      One.n.string.insert(List(
        (1, string1),
        (2, string2),
        (2, string2),
        (2, string3),
      )).transact

      // Distinct values are returned in a List
      One.string.apply(distinct).query.get.head.sorted ==> List(string1, string2, string3)
      One.n.a1.string(distinct).query.get.map(r => (r._1, r._2.sorted)) ==> List(
        (1, List(string1)),
        (2, List(string2, string3)),
      )
    }


    "min" - cardOne { implicit conn =>
      One.string.insert(List(string1, string2, string3)).transact
      One.string.apply(min).query.get.head ==> string1
      One.string(min(1)).query.get.head ==> List(string1)
      One.string(min(2)).query.get.head ==> List(string1, string2)
    }


    "max" - cardOne { implicit futConn =>
      One.string.insert(List(string1, string2, string3)).transact
      One.string(max).query.get.head ==> string3
      One.string(max(1)).query.get.head ==> List(string3)
      One.string(max(2)).query.get.head ==> List(string3, string2)
    }


    "rand" - cardOne { implicit conn =>
      One.string.insert(List(string1, string2, string3)).transact
      val all = Seq(string1, string2, string3, string4)
      all.contains(One.string(rand).query.get.head) ==> true
      all.intersect(One.string(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(One.string(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - cardOne { implicit futConn =>
      One.string.insert(List(string1, string2, string3)).transact
      val all = Seq(string1, string2, string3, string4)
      all.contains(One.string(sample).query.get.head) ==> true
      all.intersect(One.string(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(One.string(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - cardOne { implicit conn =>
      One.n.string.insert(List(
        (1, string1),
        (2, string2),
        (2, string2),
        (2, string3),
      )).transact

      One.n(count).query.get ==> List(4)
      One.n(countDistinct).query.get ==> List(2)

      One.string(count).query.get ==> List(4)
      One.string(countDistinct).query.get ==> List(3)

      One.n.a1.string(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      One.n.a1.string(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}