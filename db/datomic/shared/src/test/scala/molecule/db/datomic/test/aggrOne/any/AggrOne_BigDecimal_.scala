// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.any


import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_BigDecimal_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      Ns.n.bigDecimal.insert(List(
        (1, bigDecimal1),
        (2, bigDecimal2),
        (2, bigDecimal2),
        (2, bigDecimal3),
      )).transact

      Ns.n.a1.bigDecimal.query.get.sortBy(_._2) ==> List(
        (1, bigDecimal1),
        (2, bigDecimal2), // 2 rows coalesced
        (2, bigDecimal3),
      )

      // Distinct values are returned in a List
      Ns.n.a1.bigDecimal.apply(distinct).query.get ==> List(
        (1, Set(bigDecimal1)),
        (2, Set(bigDecimal2, bigDecimal3)),
      )

      Ns.bigDecimal(distinct).query.get.head ==> Set(
        bigDecimal1, bigDecimal2, bigDecimal3
      )
    }


    "min" - types { implicit conn =>
      Ns.bigDecimal.insert(List(bigDecimal1, bigDecimal2, bigDecimal3)).transact
      Ns.bigDecimal(min).query.get ==> List(bigDecimal1)
      Ns.bigDecimal(min(1)).query.get ==> List(Set(bigDecimal1))
      Ns.bigDecimal(min(2)).query.get ==> List(Set(bigDecimal1, bigDecimal2))
    }


    "max" - types { implicit futConn =>
      Ns.bigDecimal.insert(List(bigDecimal1, bigDecimal2, bigDecimal3)).transact
      Ns.bigDecimal(max).query.get ==> List(bigDecimal3)
      Ns.bigDecimal(max(1)).query.get ==> List(Set(bigDecimal3))
      Ns.bigDecimal(max(2)).query.get ==> List(Set(bigDecimal3, bigDecimal2))
    }


    "rand" - types { implicit conn =>
      Ns.bigDecimal.insert(List(bigDecimal1, bigDecimal2, bigDecimal3)).transact
      val all = Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4)
      all.contains(Ns.bigDecimal.apply(rand).query.get.head) ==> true
      all.intersect(Ns.bigDecimal(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.bigDecimal(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - types { implicit futConn =>
      Ns.bigDecimal.insert(List(bigDecimal1, bigDecimal2, bigDecimal3)).transact
      val all = Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4)
      all.contains(Ns.bigDecimal(sample).query.get.head) ==> true
      all.intersect(Ns.bigDecimal(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.bigDecimal(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - types { implicit conn =>
      Ns.n.bigDecimal.insert(List(
        (1, bigDecimal1),
        (2, bigDecimal2),
        (2, bigDecimal2),
        (2, bigDecimal3),
      )).transact

      Ns.n(count).query.get ==> List(4)
      Ns.n(countDistinct).query.get ==> List(2)

      Ns.bigDecimal(count).query.get ==> List(4)
      Ns.bigDecimal(countDistinct).query.get ==> List(3)

      Ns.n.a1.bigDecimal(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      Ns.n.a1.bigDecimal(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}