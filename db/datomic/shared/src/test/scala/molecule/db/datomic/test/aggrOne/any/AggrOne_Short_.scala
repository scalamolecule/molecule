// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.any


import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_Short_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - cardOne { implicit conn =>
      NsOne.n.short.insert(List(
        (1, short1),
        (2, short2),
        (2, short2),
        (2, short3),
      )).transact

      NsOne.n.a1.short.query.get.sortBy(_._2) ==> List(
        (1, short1),
        (2, short2), // 2 rows coalesced
        (2, short3),
      )

      // Distinct values are returned in a List
      NsOne.n.a1.short.apply(distinct).query.get ==> List(
        (1, Set(short1)),
        (2, Set(short2, short3)),
      )

      NsOne.short(distinct).query.get.head ==> Set(
        short1, short2, short3
      )
    }


    "min" - cardOne { implicit conn =>
      NsOne.short.insert(List(short1, short2, short3)).transact
      NsOne.short(min).query.get.head ==> short1
      NsOne.short(min(1)).query.get.head ==> Set(short1)
      NsOne.short(min(2)).query.get.head ==> Set(short1, short2)
    }


    "max" - cardOne { implicit futConn =>
      NsOne.short.insert(List(short1, short2, short3)).transact
      NsOne.short(max).query.get.head ==> short3
      NsOne.short(max(1)).query.get.head ==> Set(short3)
      NsOne.short(max(2)).query.get.head ==> Set(short3, short2)
    }


    "rand" - cardOne { implicit conn =>
      NsOne.short.insert(List(short1, short2, short3)).transact
      val all = Set(short1, short2, short3, short4)
      all.contains(NsOne.short.apply(rand).query.get.head) ==> true
      all.intersect(NsOne.short(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsOne.short(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - cardOne { implicit futConn =>
      NsOne.short.insert(List(short1, short2, short3)).transact
      val all = Set(short1, short2, short3, short4)
      all.contains(NsOne.short(sample).query.get.head) ==> true
      all.intersect(NsOne.short(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsOne.short(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - cardOne { implicit conn =>
      NsOne.n.short.insert(List(
        (1, short1),
        (2, short2),
        (2, short2),
        (2, short3),
      )).transact

      NsOne.n(count).query.get ==> List(4)
      NsOne.n(countDistinct).query.get ==> List(2)

      NsOne.short(count).query.get ==> List(4)
      NsOne.short(countDistinct).query.get ==> List(3)

      NsOne.n.a1.short(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      NsOne.n.a1.short(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}