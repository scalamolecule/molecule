// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.set.any

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSet_Byte_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      Ns.i.bytes.insert(List(
        (1, Set(byte1, byte2)),
        (2, Set(byte2, byte3)),
        (2, Set(byte3, byte4)),
        (2, Set(byte3, byte4)),
      )).transact

      // Non-aggregated card-many Set of attribute values coalesce
      Ns.i.a1.bytes.query.get ==> List(
        (1, Set(byte1, byte2)),
        (2, Set(byte2, byte3, byte4)), // 3 rows coalesced
      )

      // Use `distinct` keyword to retrieve unique Sets of values
      Ns.i.a1.bytes(distinct).query.get ==> List(
        (1, Set(Set(byte1, byte2))),
        (2, Set(
          Set(byte2, byte3),
          Set(byte3, byte4) // 2 rows coalesced
        ))
      )

      Ns.bytes(distinct).query.get ==> List(
        Set(
          Set(byte1, byte2),
          Set(byte2, byte3),
          Set(byte3, byte4),
        )
      )
    }


    "min" - types { implicit conn =>
      Ns.i.bytes.insert(List(
        (1, Set(byte1, byte2)),
        (2, Set(byte2, byte3)),
        (2, Set(byte3, byte4)),
        (2, Set(byte3, byte4)),
      )).transact

      Ns.bytes(min).query.get ==> List(Set(byte1))
      Ns.bytes(min(1)).query.get ==> List(Set(byte1))
      Ns.bytes(min(2)).query.get ==> List(Set(byte1, byte2))

      Ns.i.bytes(min).query.get ==> List(
        (1, Set(byte1)),
        (2, Set(byte2)),
      )
      // Same as
      Ns.i.bytes(min(1)).query.get ==> List(
        (1, Set(byte1)),
        (2, Set(byte2)),
      )

      Ns.i.bytes(min(2)).query.get ==> List(
        (1, Set(byte1, byte2)),
        (2, Set(byte2, byte3)),
      )
    }


    "max" - types { implicit futConn =>
      Ns.i.bytes.insert(List(
        (1, Set(byte1, byte2)),
        (2, Set(byte2, byte3)),
        (2, Set(byte3, byte4)),
        (2, Set(byte3, byte4)),
      )).transact

      Ns.bytes(max).query.get ==> List(Set(byte4))
      Ns.bytes(max(1)).query.get ==> List(Set(byte4))
      Ns.bytes(max(2)).query.get ==> List(Set(byte3, byte4))

      Ns.i.bytes(max).query.get ==> List(
        (1, Set(byte2)),
        (2, Set(byte4)),
      )
      // Same as
      Ns.i.bytes(max(1)).query.get ==> List(
        (1, Set(byte2)),
        (2, Set(byte4)),
      )

      Ns.i.bytes(max(2)).query.get ==> List(
        (1, Set(byte1, byte2)),
        (2, Set(byte3, byte4)),
      )
    }


    "rand" - types { implicit conn =>
      Ns.i.bytes.insert(List(
        (1, Set(byte1, byte2)),
        (2, Set(byte2, byte3)),
        (2, Set(byte3, byte4)),
        (2, Set(byte3, byte4)),
      )).transact
      val all = Set(byte1, byte2, byte3, byte4)
      all.contains(Ns.bytes(rand).query.get.head.head) ==> true
      all.intersect(Ns.bytes(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.bytes(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - types { implicit futConn =>
      Ns.i.bytes.insert(List(
        (1, Set(byte1, byte2)),
        (2, Set(byte2, byte3)),
        (2, Set(byte3, byte4)),
        (2, Set(byte3, byte4)),
      )).transact
      val all = Set(byte1, byte2, byte3, byte4)
      all.contains(Ns.bytes(sample).query.get.head.head) ==> true
      all.intersect(Ns.bytes(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.bytes(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - types { implicit conn =>
      Ns.i.bytes.insert(List(
        (1, Set(byte1, byte2)),
        (2, Set(byte2, byte3)),
        (2, Set(byte3, byte4)),
        (2, Set(byte3, byte4)),
      )).transact

      Ns.i(count).query.get ==> List(4)
      Ns.i(countDistinct).query.get ==> List(2)

      Ns.bytes(count).query.get ==> List(8)
      Ns.bytes(countDistinct).query.get ==> List(4)

      Ns.i.a1.bytes(count).query.get ==> List(
        (1, 2),
        (2, 6)
      )
      Ns.i.a1.bytes(countDistinct).query.get ==> List(
        (1, 2),
        (2, 3)
      )
    }
  }
}