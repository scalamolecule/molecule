// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.any


import molecule.coreTests.dataModels.core.types.dsl.TypesOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_String_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - typesOne { implicit conn =>
      NsOne.n.string.insert(List(
        (1, string1),
        (2, string2),
        (2, string2),
        (2, string3),
      )).transact

      NsOne.n.a1.string.query.get.sortBy(_._2) ==> List(
        (1, string1),
        (2, string2), // 2 rows coalesced
        (2, string3),
      )

      // Distinct values are returned in a List
      NsOne.n.a1.string.apply(distinct).query.get ==> List(
        (1, Set(string1)),
        (2, Set(string2, string3)),
      )

      NsOne.string(distinct).query.get.head ==> Set(
        string1, string2, string3
      )
    }


    "min" - typesOne { implicit conn =>
      NsOne.string.insert(List(string1, string2, string3)).transact
      NsOne.string(min).query.get.head ==> string1
      NsOne.string(min(1)).query.get.head ==> Set(string1)
      NsOne.string(min(2)).query.get.head ==> Set(string1, string2)
    }


    "max" - typesOne { implicit futConn =>
      NsOne.string.insert(List(string1, string2, string3)).transact
      NsOne.string(max).query.get.head ==> string3
      NsOne.string(max(1)).query.get.head ==> Set(string3)
      NsOne.string(max(2)).query.get.head ==> Set(string3, string2)
    }


    "rand" - typesOne { implicit conn =>
      NsOne.string.insert(List(string1, string2, string3)).transact
      val all = Set(string1, string2, string3, string4)
      all.contains(NsOne.string.apply(rand).query.get.head) ==> true
      all.intersect(NsOne.string(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsOne.string(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - typesOne { implicit futConn =>
      NsOne.string.insert(List(string1, string2, string3)).transact
      val all = Set(string1, string2, string3, string4)
      all.contains(NsOne.string(sample).query.get.head) ==> true
      all.intersect(NsOne.string(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsOne.string(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - typesOne { implicit conn =>
      NsOne.n.string.insert(List(
        (1, string1),
        (2, string2),
        (2, string2),
        (2, string3),
      )).transact

      NsOne.n(count).query.get ==> List(4)
      NsOne.n(countDistinct).query.get ==> List(2)

      NsOne.string(count).query.get ==> List(4)
      NsOne.string(countDistinct).query.get ==> List(3)

      NsOne.n.a1.string(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      NsOne.n.a1.string(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}