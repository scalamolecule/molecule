// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.any

import molecule.boilerplate.api.Keywords._
import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_BigInt_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - cardOne { implicit conn =>
      One.n.bigInt.insert(List(
        (1, bigInt1),
        (2, bigInt2),
        (2, bigInt2),
        (2, bigInt3),
      )).transact

      // Distinct values are returned in a List
      One.bigInt.apply(distinct).query.get.head.sorted ==> List(bigInt1, bigInt2, bigInt3)
      One.n.a1.bigInt(distinct).query.get.map(r => (r._1, r._2.sorted)) ==> List(
        (1, List(bigInt1)),
        (2, List(bigInt2, bigInt3)),
      )
    }


    "min" - cardOne { implicit conn =>
      One.bigInt.insert(List(bigInt1, bigInt2, bigInt3)).transact
      One.bigInt.apply(min).query.get.head ==> bigInt1
      One.bigInt(min(1)).query.get.head ==> List(bigInt1)
      One.bigInt(min(2)).query.get.head ==> List(bigInt1, bigInt2)
    }


    "max" - cardOne { implicit futConn =>
      One.bigInt.insert(List(bigInt1, bigInt2, bigInt3)).transact
      One.bigInt(max).query.get.head ==> bigInt3
      One.bigInt(max(1)).query.get.head ==> List(bigInt3)
      One.bigInt(max(2)).query.get.head ==> List(bigInt3, bigInt2)
    }


    "rand" - cardOne { implicit conn =>
      One.bigInt.insert(List(bigInt1, bigInt2, bigInt3)).transact
      val all = Seq(bigInt1, bigInt2, bigInt3, bigInt4)
      all.contains(One.bigInt(rand).query.get.head) ==> true
      all.intersect(One.bigInt(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(One.bigInt(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - cardOne { implicit futConn =>
      One.bigInt.insert(List(bigInt1, bigInt2, bigInt3)).transact
      val all = Seq(bigInt1, bigInt2, bigInt3, bigInt4)
      all.contains(One.bigInt(sample).query.get.head) ==> true
      all.intersect(One.bigInt(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(One.bigInt(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - cardOne { implicit conn =>
      One.n.bigInt.insert(List(
        (1, bigInt1),
        (2, bigInt2),
        (2, bigInt2),
        (2, bigInt3),
      )).transact

      One.n(count).query.get ==> List(4)
      One.n(countDistinct).query.get ==> List(2)

      One.bigInt(count).query.get ==> List(4)
      One.bigInt(countDistinct).query.get ==> List(3)

      One.n.a1.bigInt(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      One.n.a1.bigInt(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}