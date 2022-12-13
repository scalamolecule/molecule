// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.one.any

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_Char_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      Ns.i.char.insert(List(
        (1, char1),
        (2, char2),
        (2, char2),
        (2, char3),
      )).transact

      Ns.i.a1.char.query.get.sortBy(_._2) ==> List(
        (1, char1),
        (2, char2), // 2 rows coalesced
        (2, char3),
      )

      // Distinct values are returned in a List
      Ns.i.a1.char.apply(distinct).query.get ==> List(
        (1, Set(char1)),
        (2, Set(char2, char3)),
      )

      Ns.char(distinct).query.get.head ==> Set(
        char1, char2, char3
      )
    }


    "min" - types { implicit conn =>
      Ns.char.insert(List(char1, char2, char3)).transact
      Ns.char(min).query.get ==> List(char1)
      Ns.char(min(1)).query.get ==> List(Set(char1))
      Ns.char(min(2)).query.get ==> List(Set(char1, char2))
    }


    "max" - types { implicit futConn =>
      Ns.char.insert(List(char1, char2, char3)).transact
      Ns.char(max).query.get ==> List(char3)
      Ns.char(max(1)).query.get ==> List(Set(char3))
      Ns.char(max(2)).query.get ==> List(Set(char3, char2))
    }


    "rand" - types { implicit conn =>
      Ns.char.insert(List(char1, char2, char3)).transact
      val all = Set(char1, char2, char3, char4)
      all.contains(Ns.char.apply(rand).query.get.head) ==> true
      all.intersect(Ns.char(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.char(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - types { implicit futConn =>
      Ns.char.insert(List(char1, char2, char3)).transact
      val all = Set(char1, char2, char3, char4)
      all.contains(Ns.char(sample).query.get.head) ==> true
      all.intersect(Ns.char(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.char(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - types { implicit conn =>
      Ns.i.char.insert(List(
        (1, char1),
        (2, char2),
        (2, char2),
        (2, char3),
      )).transact

      Ns.i(count).query.get ==> List(4)
      Ns.i(countDistinct).query.get ==> List(2)

      Ns.char(count).query.get ==> List(4)
      Ns.char(countDistinct).query.get ==> List(3)

      Ns.i.a1.char(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      Ns.i.a1.char(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}