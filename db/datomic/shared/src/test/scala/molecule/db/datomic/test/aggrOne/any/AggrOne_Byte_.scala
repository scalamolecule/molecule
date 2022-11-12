// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.any


import molecule.coreTests.dataModels.core.types.dsl.TypesOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_Byte_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - typesOne { implicit conn =>
      NsOne.n.byte.insert(List(
        (1, byte1),
        (2, byte2),
        (2, byte2),
        (2, byte3),
      )).transact

      NsOne.n.a1.byte.query.get.sortBy(_._2) ==> List(
        (1, byte1),
        (2, byte2), // 2 rows coalesced
        (2, byte3),
      )

      // Distinct values are returned in a List
      NsOne.n.a1.byte.apply(distinct).query.get ==> List(
        (1, Set(byte1)),
        (2, Set(byte2, byte3)),
      )

      NsOne.byte(distinct).query.get.head ==> Set(
        byte1, byte2, byte3
      )
    }


    "min" - typesOne { implicit conn =>
      NsOne.byte.insert(List(byte1, byte2, byte3)).transact
      NsOne.byte(min).query.get.head ==> byte1
      NsOne.byte(min(1)).query.get.head ==> Set(byte1)
      NsOne.byte(min(2)).query.get.head ==> Set(byte1, byte2)
    }


    "max" - typesOne { implicit futConn =>
      NsOne.byte.insert(List(byte1, byte2, byte3)).transact
      NsOne.byte(max).query.get.head ==> byte3
      NsOne.byte(max(1)).query.get.head ==> Set(byte3)
      NsOne.byte(max(2)).query.get.head ==> Set(byte3, byte2)
    }


    "rand" - typesOne { implicit conn =>
      NsOne.byte.insert(List(byte1, byte2, byte3)).transact
      val all = Set(byte1, byte2, byte3, byte4)
      all.contains(NsOne.byte.apply(rand).query.get.head) ==> true
      all.intersect(NsOne.byte(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsOne.byte(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - typesOne { implicit futConn =>
      NsOne.byte.insert(List(byte1, byte2, byte3)).transact
      val all = Set(byte1, byte2, byte3, byte4)
      all.contains(NsOne.byte(sample).query.get.head) ==> true
      all.intersect(NsOne.byte(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsOne.byte(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - typesOne { implicit conn =>
      NsOne.n.byte.insert(List(
        (1, byte1),
        (2, byte2),
        (2, byte2),
        (2, byte3),
      )).transact

      NsOne.n(count).query.get ==> List(4)
      NsOne.n(countDistinct).query.get ==> List(2)

      NsOne.byte(count).query.get ==> List(4)
      NsOne.byte(countDistinct).query.get ==> List(3)

      NsOne.n.a1.byte(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      NsOne.n.a1.byte(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}