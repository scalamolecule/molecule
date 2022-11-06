// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrSet.any


import molecule.coreTests.dataModels.core.types.dsl.CardSet._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSet_Byte_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - cardSet { implicit conn =>
      NsSet.n.bytes.insert(List(
        (1, Set(byte1, byte2)),
        (2, Set(byte2, byte3)),
        (2, Set(byte3, byte4)),
        (2, Set(byte3, byte4)),
      )).transact

      // Non-aggregated card-many Set of attribute values coalesce
      NsSet.n.a1.bytes.query.get ==> List(
        (1, Set(byte1, byte2)),
        (2, Set(byte2, byte3, byte4)), // 3 rows coalesced
      )

      // Use `distinct` keyword to retrieve unique Sets of values
      NsSet.n.a1.bytes(distinct).query.get ==> List(
        (1, Set(Set(byte1, byte2))),
        (2, Set(
          Set(byte2, byte3),
          Set(byte3, byte4) // 2 rows coalesced
        ))
      )

      NsSet.bytes.apply(distinct).query.get ==> List(
        Set(
          Set(byte1, byte2),
          Set(byte2, byte3),
          Set(byte3, byte4),
        )
      )
    }


    "min" - cardSet { implicit conn =>
      NsSet.n.bytes.insert(List(
        (1, Set(byte1, byte2)),
        (2, Set(byte2, byte3)),
        (2, Set(byte3, byte4)),
        (2, Set(byte3, byte4)),
      )).transact

      NsSet.bytes(min).query.get ==> List(Set(byte1))
      NsSet.bytes(min(1)).query.get ==> List(Set(Set(byte1)))
      NsSet.bytes(min(2)).query.get ==> List(Set(Set(byte1, byte2)))

      NsSet.n.bytes(min).query.get ==> List(
        (1, Set(byte1)),
        (2, Set(byte2)),
      )
      NsSet.n.bytes(min(1)).query.get ==> List(
        (1, Set(Set(byte1))),
        (2, Set(Set(byte2))),
      )
      NsSet.n.bytes(min(2)).query.get ==> List(
        (1, Set(Set(byte1, byte2))),
        (2, Set(Set(byte2, byte3))),
      )
    }


    "max" - cardSet { implicit futConn =>
      NsSet.n.bytes.insert(List(
        (1, Set(byte1, byte2)),
        (2, Set(byte2, byte3)),
        (2, Set(byte3, byte4)),
        (2, Set(byte3, byte4)),
      )).transact

      NsSet.bytes(max).query.get ==> List(Set(byte4))
      NsSet.bytes(max(1)).query.get ==> List(Set(Set(byte4)))
      NsSet.bytes(max(2)).query.get ==> List(Set(Set(byte3, byte4)))

      NsSet.n.bytes(max).query.get ==> List(
        (1, Set(byte2)),
        (2, Set(byte4)),
      )
      NsSet.n.bytes(max(1)).query.get ==> List(
        (1, Set(Set(byte2))),
        (2, Set(Set(byte4))),
      )
      NsSet.n.bytes(max(2)).query.get ==> List(
        (1, Set(Set(byte1, byte2))),
        (2, Set(Set(byte3, byte4))),
      )
    }


    "rand" - cardSet { implicit conn =>
      NsSet.n.bytes.insert(List(
        (1, Set(byte1, byte2)),
        (2, Set(byte2, byte3)),
        (2, Set(byte3, byte4)),
        (2, Set(byte3, byte4)),
      )).transact
      val all = Set(byte1, byte2, byte3, byte4)
      all.contains(NsSet.bytes(rand).query.get.head.head) ==> true
      all.intersect(NsSet.bytes(rand(1)).query.get.head.head).nonEmpty ==> true
      all.intersect(NsSet.bytes(rand(2)).query.get.head.head).nonEmpty ==> true
    }


    "sample" - cardSet { implicit futConn =>
      NsSet.n.bytes.insert(List(
        (1, Set(byte1, byte2)),
        (2, Set(byte2, byte3)),
        (2, Set(byte3, byte4)),
        (2, Set(byte3, byte4)),
      )).transact
      val all = Set(byte1, byte2, byte3, byte4)
      all.contains(NsSet.bytes(sample).query.get.head.head) ==> true
      all.intersect(NsSet.bytes(sample(1)).query.get.head.head).nonEmpty ==> true
      all.intersect(NsSet.bytes(sample(2)).query.get.head.head).nonEmpty ==> true
    }


    "count, countDistinct" - cardSet { implicit conn =>
      NsSet.n.bytes.insert(List(
        (1, Set(byte1, byte2)),
        (2, Set(byte2, byte3)),
        (2, Set(byte3, byte4)),
        (2, Set(byte3, byte4)),
      )).transact

      NsSet.n(count).query.get ==> List(4)
      NsSet.n(countDistinct).query.get ==> List(2)

      NsSet.bytes(count).query.get ==> List(8)
      NsSet.bytes(countDistinct).query.get ==> List(4)

      NsSet.n.a1.bytes(count).query.get ==> List(
        (1, 2),
        (2, 6)
      )
      NsSet.n.a1.bytes(countDistinct).query.get ==> List(
        (1, 2),
        (2, 3)
      )
    }
  }
}