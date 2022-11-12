// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.any


import molecule.coreTests.dataModels.core.types.dsl.TypesOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_BigDecimal_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - typesOne { implicit conn =>
      NsOne.n.bigDecimal.insert(List(
        (1, bigDecimal1),
        (2, bigDecimal2),
        (2, bigDecimal2),
        (2, bigDecimal3),
      )).transact

      NsOne.n.a1.bigDecimal.query.get.sortBy(_._2) ==> List(
        (1, bigDecimal1),
        (2, bigDecimal2), // 2 rows coalesced
        (2, bigDecimal3),
      )

      // Distinct values are returned in a List
      NsOne.n.a1.bigDecimal.apply(distinct).query.get ==> List(
        (1, Set(bigDecimal1)),
        (2, Set(bigDecimal2, bigDecimal3)),
      )

      NsOne.bigDecimal(distinct).query.get.head ==> Set(
        bigDecimal1, bigDecimal2, bigDecimal3
      )
    }


    "min" - typesOne { implicit conn =>
      NsOne.bigDecimal.insert(List(bigDecimal1, bigDecimal2, bigDecimal3)).transact
      NsOne.bigDecimal(min).query.get.head ==> bigDecimal1
      NsOne.bigDecimal(min(1)).query.get.head ==> Set(bigDecimal1)
      NsOne.bigDecimal(min(2)).query.get.head ==> Set(bigDecimal1, bigDecimal2)
    }


    "max" - typesOne { implicit futConn =>
      NsOne.bigDecimal.insert(List(bigDecimal1, bigDecimal2, bigDecimal3)).transact
      NsOne.bigDecimal(max).query.get.head ==> bigDecimal3
      NsOne.bigDecimal(max(1)).query.get.head ==> Set(bigDecimal3)
      NsOne.bigDecimal(max(2)).query.get.head ==> Set(bigDecimal3, bigDecimal2)
    }


    "rand" - typesOne { implicit conn =>
      NsOne.bigDecimal.insert(List(bigDecimal1, bigDecimal2, bigDecimal3)).transact
      val all = Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4)
      all.contains(NsOne.bigDecimal.apply(rand).query.get.head) ==> true
      all.intersect(NsOne.bigDecimal(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsOne.bigDecimal(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - typesOne { implicit futConn =>
      NsOne.bigDecimal.insert(List(bigDecimal1, bigDecimal2, bigDecimal3)).transact
      val all = Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4)
      all.contains(NsOne.bigDecimal(sample).query.get.head) ==> true
      all.intersect(NsOne.bigDecimal(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsOne.bigDecimal(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - typesOne { implicit conn =>
      NsOne.n.bigDecimal.insert(List(
        (1, bigDecimal1),
        (2, bigDecimal2),
        (2, bigDecimal2),
        (2, bigDecimal3),
      )).transact

      NsOne.n(count).query.get ==> List(4)
      NsOne.n(countDistinct).query.get ==> List(2)

      NsOne.bigDecimal(count).query.get ==> List(4)
      NsOne.bigDecimal(countDistinct).query.get ==> List(3)

      NsOne.n.a1.bigDecimal(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      NsOne.n.a1.bigDecimal(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}