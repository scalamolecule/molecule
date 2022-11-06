// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.any


import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_Char_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - cardOne { implicit conn =>
      NsOne.n.char.insert(List(
        (1, char1),
        (2, char2),
        (2, char2),
        (2, char3),
      )).transact

      NsOne.n.a1.char.query.get.sortBy(_._2) ==> List(
        (1, char1),
        (2, char2), // 2 rows coalesced
        (2, char3),
      )

      // Distinct values are returned in a List
      NsOne.n.a1.char.apply(distinct).query.get ==> List(
        (1, Set(char1)),
        (2, Set(char2, char3)),
      )

      NsOne.char(distinct).query.get.head ==> Set(
        char1, char2, char3
      )
    }


    "min" - cardOne { implicit conn =>
      NsOne.char.insert(List(char1, char2, char3)).transact
      NsOne.char(min).query.get.head ==> char1
      NsOne.char(min(1)).query.get.head ==> Set(char1)
      NsOne.char(min(2)).query.get.head ==> Set(char1, char2)
    }


    "max" - cardOne { implicit futConn =>
      NsOne.char.insert(List(char1, char2, char3)).transact
      NsOne.char(max).query.get.head ==> char3
      NsOne.char(max(1)).query.get.head ==> Set(char3)
      NsOne.char(max(2)).query.get.head ==> Set(char3, char2)
    }


    "rand" - cardOne { implicit conn =>
      NsOne.char.insert(List(char1, char2, char3)).transact
      val all = Set(char1, char2, char3, char4)
      all.contains(NsOne.char.apply(rand).query.get.head) ==> true
      all.intersect(NsOne.char(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsOne.char(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - cardOne { implicit futConn =>
      NsOne.char.insert(List(char1, char2, char3)).transact
      val all = Set(char1, char2, char3, char4)
      all.contains(NsOne.char(sample).query.get.head) ==> true
      all.intersect(NsOne.char(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsOne.char(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - cardOne { implicit conn =>
      NsOne.n.char.insert(List(
        (1, char1),
        (2, char2),
        (2, char2),
        (2, char3),
      )).transact

      NsOne.n(count).query.get ==> List(4)
      NsOne.n(countDistinct).query.get ==> List(2)

      NsOne.char(count).query.get ==> List(4)
      NsOne.char(countDistinct).query.get ==> List(3)

      NsOne.n.a1.char(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      NsOne.n.a1.char(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}