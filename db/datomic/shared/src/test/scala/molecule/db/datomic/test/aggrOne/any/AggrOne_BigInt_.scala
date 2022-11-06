// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.any


import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_BigInt_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - cardOne { implicit conn =>
      NsOne.n.bigInt.insert(List(
        (1, bigInt1),
        (2, bigInt2),
        (2, bigInt2),
        (2, bigInt3),
      )).transact

      NsOne.n.a1.bigInt.query.get.sortBy(_._2) ==> List(
        (1, bigInt1),
        (2, bigInt2), // 2 rows coalesced
        (2, bigInt3),
      )

      // Distinct values are returned in a List
      NsOne.n.a1.bigInt.apply(distinct).query.get ==> List(
        (1, Set(bigInt1)),
        (2, Set(bigInt2, bigInt3)),
      )

      NsOne.bigInt(distinct).query.get.head ==> Set(
        bigInt1, bigInt2, bigInt3
      )
    }


    "min" - cardOne { implicit conn =>
      NsOne.bigInt.insert(List(bigInt1, bigInt2, bigInt3)).transact
      NsOne.bigInt(min).query.get.head ==> bigInt1
      NsOne.bigInt(min(1)).query.get.head ==> Set(bigInt1)
      NsOne.bigInt(min(2)).query.get.head ==> Set(bigInt1, bigInt2)
    }


    "max" - cardOne { implicit futConn =>
      NsOne.bigInt.insert(List(bigInt1, bigInt2, bigInt3)).transact
      NsOne.bigInt(max).query.get.head ==> bigInt3
      NsOne.bigInt(max(1)).query.get.head ==> Set(bigInt3)
      NsOne.bigInt(max(2)).query.get.head ==> Set(bigInt3, bigInt2)
    }


    "rand" - cardOne { implicit conn =>
      NsOne.bigInt.insert(List(bigInt1, bigInt2, bigInt3)).transact
      val all = Set(bigInt1, bigInt2, bigInt3, bigInt4)
      all.contains(NsOne.bigInt.apply(rand).query.get.head) ==> true
      all.intersect(NsOne.bigInt(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsOne.bigInt(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - cardOne { implicit futConn =>
      NsOne.bigInt.insert(List(bigInt1, bigInt2, bigInt3)).transact
      val all = Set(bigInt1, bigInt2, bigInt3, bigInt4)
      all.contains(NsOne.bigInt(sample).query.get.head) ==> true
      all.intersect(NsOne.bigInt(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsOne.bigInt(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - cardOne { implicit conn =>
      NsOne.n.bigInt.insert(List(
        (1, bigInt1),
        (2, bigInt2),
        (2, bigInt2),
        (2, bigInt3),
      )).transact

      NsOne.n(count).query.get ==> List(4)
      NsOne.n(countDistinct).query.get ==> List(2)

      NsOne.bigInt(count).query.get ==> List(4)
      NsOne.bigInt(countDistinct).query.get ==> List(3)

      NsOne.n.a1.bigInt(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      NsOne.n.a1.bigInt(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}