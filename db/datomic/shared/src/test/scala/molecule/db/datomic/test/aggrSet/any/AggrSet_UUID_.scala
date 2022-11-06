// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrSet.any


import java.util.UUID
import molecule.coreTests.dataModels.core.types.dsl.CardSet._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSet_UUID_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - cardSet { implicit conn =>
      NsSet.n.uuids.insert(List(
        (1, Set(uuid1, uuid2)),
        (2, Set(uuid2, uuid3)),
        (2, Set(uuid3, uuid4)),
        (2, Set(uuid3, uuid4)),
      )).transact

      // Non-aggregated card-many Set of attribute values coalesce
      NsSet.n.a1.uuids.query.get ==> List(
        (1, Set(uuid1, uuid2)),
        (2, Set(uuid2, uuid3, uuid4)), // 3 rows coalesced
      )

      // Use `distinct` keyword to retrieve unique Sets of values
      NsSet.n.a1.uuids(distinct).query.get ==> List(
        (1, Set(Set(uuid1, uuid2))),
        (2, Set(
          Set(uuid2, uuid3),
          Set(uuid3, uuid4) // 2 rows coalesced
        ))
      )

      NsSet.uuids.apply(distinct).query.get ==> List(
        Set(
          Set(uuid1, uuid2),
          Set(uuid2, uuid3),
          Set(uuid3, uuid4),
        )
      )
    }


    "min" - cardSet { implicit conn =>
      NsSet.n.uuids.insert(List(
        (1, Set(uuid1, uuid2)),
        (2, Set(uuid2, uuid3)),
        (2, Set(uuid3, uuid4)),
        (2, Set(uuid3, uuid4)),
      )).transact

      NsSet.uuids(min).query.get ==> List(Set(uuid1))
      NsSet.uuids(min(1)).query.get ==> List(Set(Set(uuid1)))
      NsSet.uuids(min(2)).query.get ==> List(Set(Set(uuid1, uuid2)))

      NsSet.n.uuids(min).query.get ==> List(
        (1, Set(uuid1)),
        (2, Set(uuid2)),
      )
      NsSet.n.uuids(min(1)).query.get ==> List(
        (1, Set(Set(uuid1))),
        (2, Set(Set(uuid2))),
      )
      NsSet.n.uuids(min(2)).query.get ==> List(
        (1, Set(Set(uuid1, uuid2))),
        (2, Set(Set(uuid2, uuid3))),
      )
    }


    "max" - cardSet { implicit futConn =>
      NsSet.n.uuids.insert(List(
        (1, Set(uuid1, uuid2)),
        (2, Set(uuid2, uuid3)),
        (2, Set(uuid3, uuid4)),
        (2, Set(uuid3, uuid4)),
      )).transact

      NsSet.uuids(max).query.get ==> List(Set(uuid4))
      NsSet.uuids(max(1)).query.get ==> List(Set(Set(uuid4)))
      NsSet.uuids(max(2)).query.get ==> List(Set(Set(uuid3, uuid4)))

      NsSet.n.uuids(max).query.get ==> List(
        (1, Set(uuid2)),
        (2, Set(uuid4)),
      )
      NsSet.n.uuids(max(1)).query.get ==> List(
        (1, Set(Set(uuid2))),
        (2, Set(Set(uuid4))),
      )
      NsSet.n.uuids(max(2)).query.get ==> List(
        (1, Set(Set(uuid1, uuid2))),
        (2, Set(Set(uuid3, uuid4))),
      )
    }


    "rand" - cardSet { implicit conn =>
      NsSet.n.uuids.insert(List(
        (1, Set(uuid1, uuid2)),
        (2, Set(uuid2, uuid3)),
        (2, Set(uuid3, uuid4)),
        (2, Set(uuid3, uuid4)),
      )).transact
      val all = Set(uuid1, uuid2, uuid3, uuid4)
      all.contains(NsSet.uuids(rand).query.get.head.head) ==> true
      all.intersect(NsSet.uuids(rand(1)).query.get.head.head).nonEmpty ==> true
      all.intersect(NsSet.uuids(rand(2)).query.get.head.head).nonEmpty ==> true
    }


    "sample" - cardSet { implicit futConn =>
      NsSet.n.uuids.insert(List(
        (1, Set(uuid1, uuid2)),
        (2, Set(uuid2, uuid3)),
        (2, Set(uuid3, uuid4)),
        (2, Set(uuid3, uuid4)),
      )).transact
      val all = Set(uuid1, uuid2, uuid3, uuid4)
      all.contains(NsSet.uuids(sample).query.get.head.head) ==> true
      all.intersect(NsSet.uuids(sample(1)).query.get.head.head).nonEmpty ==> true
      all.intersect(NsSet.uuids(sample(2)).query.get.head.head).nonEmpty ==> true
    }


    "count, countDistinct" - cardSet { implicit conn =>
      NsSet.n.uuids.insert(List(
        (1, Set(uuid1, uuid2)),
        (2, Set(uuid2, uuid3)),
        (2, Set(uuid3, uuid4)),
        (2, Set(uuid3, uuid4)),
      )).transact

      NsSet.n(count).query.get ==> List(4)
      NsSet.n(countDistinct).query.get ==> List(2)

      NsSet.uuids(count).query.get ==> List(8)
      NsSet.uuids(countDistinct).query.get ==> List(4)

      NsSet.n.a1.uuids(count).query.get ==> List(
        (1, 2),
        (2, 6)
      )
      NsSet.n.a1.uuids(countDistinct).query.get ==> List(
        (1, 2),
        (2, 3)
      )
    }
  }
}