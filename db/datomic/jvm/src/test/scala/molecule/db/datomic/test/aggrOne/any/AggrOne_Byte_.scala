// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.any


import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_Byte_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      Ns.i.byte.insert(List(
        (1, byte1),
        (2, byte2),
        (2, byte2),
        (2, byte3),
      )).transact

      Ns.i.a1.byte.query.get.sortBy(_._2) ==> List(
        (1, byte1),
        (2, byte2), // 2 rows coalesced
        (2, byte3),
      )

      // Distinct values are returned in a List
      Ns.i.a1.byte.apply(distinct).query.get ==> List(
        (1, Set(byte1)),
        (2, Set(byte2, byte3)),
      )

      Ns.byte(distinct).query.get.head ==> Set(
        byte1, byte2, byte3
      )
    }


    "min" - types { implicit conn =>
      Ns.byte.insert(List(byte1, byte2, byte3)).transact
      Ns.byte(min).query.get ==> List(byte1)
      Ns.byte(min(1)).query.get ==> List(Set(byte1))
      Ns.byte(min(2)).query.get ==> List(Set(byte1, byte2))
    }


    "max" - types { implicit futConn =>
      Ns.byte.insert(List(byte1, byte2, byte3)).transact
      Ns.byte(max).query.get ==> List(byte3)
      Ns.byte(max(1)).query.get ==> List(Set(byte3))
      Ns.byte(max(2)).query.get ==> List(Set(byte3, byte2))
    }


    "rand" - types { implicit conn =>
      Ns.byte.insert(List(byte1, byte2, byte3)).transact
      val all = Set(byte1, byte2, byte3, byte4)
      all.contains(Ns.byte.apply(rand).query.get.head) ==> true
      all.intersect(Ns.byte(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.byte(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - types { implicit futConn =>
      Ns.byte.insert(List(byte1, byte2, byte3)).transact
      val all = Set(byte1, byte2, byte3, byte4)
      all.contains(Ns.byte(sample).query.get.head) ==> true
      all.intersect(Ns.byte(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.byte(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - types { implicit conn =>
      Ns.i.byte.insert(List(
        (1, byte1),
        (2, byte2),
        (2, byte2),
        (2, byte3),
      )).transact

      Ns.i(count).query.get ==> List(4)
      Ns.i(countDistinct).query.get ==> List(2)

      Ns.byte(count).query.get ==> List(4)
      Ns.byte(countDistinct).query.get ==> List(3)

      Ns.i.a1.byte(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      Ns.i.a1.byte(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}