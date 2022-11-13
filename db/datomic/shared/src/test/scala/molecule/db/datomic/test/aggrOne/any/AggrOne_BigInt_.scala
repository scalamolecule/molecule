// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.any


import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_BigInt_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      Ns.n.bigInt.insert(List(
        (1, bigInt1),
        (2, bigInt2),
        (2, bigInt2),
        (2, bigInt3),
      )).transact

      Ns.n.a1.bigInt.query.get.sortBy(_._2) ==> List(
        (1, bigInt1),
        (2, bigInt2), // 2 rows coalesced
        (2, bigInt3),
      )

      // Distinct values are returned in a List
      Ns.n.a1.bigInt.apply(distinct).query.get ==> List(
        (1, Set(bigInt1)),
        (2, Set(bigInt2, bigInt3)),
      )

      Ns.bigInt(distinct).query.get.head ==> Set(
        bigInt1, bigInt2, bigInt3
      )
    }


    "min" - types { implicit conn =>
      Ns.bigInt.insert(List(bigInt1, bigInt2, bigInt3)).transact
      Ns.bigInt(min).query.get ==> List(bigInt1)
      Ns.bigInt(min(1)).query.get ==> List(Set(bigInt1))
      Ns.bigInt(min(2)).query.get ==> List(Set(bigInt1, bigInt2))
    }


    "max" - types { implicit futConn =>
      Ns.bigInt.insert(List(bigInt1, bigInt2, bigInt3)).transact
      Ns.bigInt(max).query.get ==> List(bigInt3)
      Ns.bigInt(max(1)).query.get ==> List(Set(bigInt3))
      Ns.bigInt(max(2)).query.get ==> List(Set(bigInt3, bigInt2))
    }


    "rand" - types { implicit conn =>
      Ns.bigInt.insert(List(bigInt1, bigInt2, bigInt3)).transact
      val all = Set(bigInt1, bigInt2, bigInt3, bigInt4)
      all.contains(Ns.bigInt.apply(rand).query.get.head) ==> true
      all.intersect(Ns.bigInt(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.bigInt(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - types { implicit futConn =>
      Ns.bigInt.insert(List(bigInt1, bigInt2, bigInt3)).transact
      val all = Set(bigInt1, bigInt2, bigInt3, bigInt4)
      all.contains(Ns.bigInt(sample).query.get.head) ==> true
      all.intersect(Ns.bigInt(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.bigInt(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - types { implicit conn =>
      Ns.n.bigInt.insert(List(
        (1, bigInt1),
        (2, bigInt2),
        (2, bigInt2),
        (2, bigInt3),
      )).transact

      Ns.n(count).query.get ==> List(4)
      Ns.n(countDistinct).query.get ==> List(2)

      Ns.bigInt(count).query.get ==> List(4)
      Ns.bigInt(countDistinct).query.get ==> List(3)

      Ns.n.a1.bigInt(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      Ns.n.a1.bigInt(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}