// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.one.any

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_String_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      Ns.i.string.insert(List(
        (1, string1),
        (2, string2),
        (2, string2),
        (2, string3),
      )).transact

      Ns.i.a1.string.query.get.sortBy(_._2) ==> List(
        (1, string1),
        (2, string2), // 2 rows coalesced
        (2, string3),
      )

      // Distinct values are returned in a List
      Ns.i.a1.string.apply(distinct).query.get ==> List(
        (1, Set(string1)),
        (2, Set(string2, string3)),
      )

      Ns.string(distinct).query.get.head ==> Set(
        string1, string2, string3
      )
    }


    "min" - types { implicit conn =>
      Ns.string.insert(List(string1, string2, string3)).transact
      Ns.string(min).query.get ==> List(string1)
      Ns.string(min(1)).query.get ==> List(Set(string1))
      Ns.string(min(2)).query.get ==> List(Set(string1, string2))
    }


    "max" - types { implicit futConn =>
      Ns.string.insert(List(string1, string2, string3)).transact
      Ns.string(max).query.get ==> List(string3)
      Ns.string(max(1)).query.get ==> List(Set(string3))
      Ns.string(max(2)).query.get ==> List(Set(string3, string2))
    }


    "rand" - types { implicit conn =>
      Ns.string.insert(List(string1, string2, string3)).transact
      val all = Set(string1, string2, string3, string4)
      all.contains(Ns.string.apply(rand).query.get.head) ==> true
      all.intersect(Ns.string(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.string(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - types { implicit futConn =>
      Ns.string.insert(List(string1, string2, string3)).transact
      val all = Set(string1, string2, string3, string4)
      all.contains(Ns.string(sample).query.get.head) ==> true
      all.intersect(Ns.string(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.string(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - types { implicit conn =>
      Ns.i.string.insert(List(
        (1, string1),
        (2, string2),
        (2, string2),
        (2, string3),
      )).transact

      Ns.i(count).query.get ==> List(4)
      Ns.i(countDistinct).query.get ==> List(2)

      Ns.string(count).query.get ==> List(4)
      Ns.string(countDistinct).query.get ==> List(3)

      Ns.i.a1.string(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      Ns.i.a1.string(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}