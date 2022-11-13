// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrSet.any


import java.util.UUID
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSet_UUID_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      Ns.n.uuids.insert(List(
        (1, Set(uuid1, uuid2)),
        (2, Set(uuid2, uuid3)),
        (2, Set(uuid3, uuid4)),
        (2, Set(uuid3, uuid4)),
      )).transact

      // Non-aggregated card-many Set of attribute values coalesce
      Ns.n.a1.uuids.query.get ==> List(
        (1, Set(uuid1, uuid2)),
        (2, Set(uuid2, uuid3, uuid4)), // 3 rows coalesced
      )

      // Use `distinct` keyword to retrieve unique Sets of values
      Ns.n.a1.uuids(distinct).query.get ==> List(
        (1, Set(Set(uuid1, uuid2))),
        (2, Set(
          Set(uuid2, uuid3),
          Set(uuid3, uuid4) // 2 rows coalesced
        ))
      )

      Ns.uuids(distinct).query.get ==> List(
        Set(
          Set(uuid1, uuid2),
          Set(uuid2, uuid3),
          Set(uuid3, uuid4),
        )
      )
    }


    "min" - types { implicit conn =>
      Ns.n.uuids.insert(List(
        (1, Set(uuid1, uuid2)),
        (2, Set(uuid2, uuid3)),
        (2, Set(uuid3, uuid4)),
        (2, Set(uuid3, uuid4)),
      )).transact

      Ns.uuids(min).query.get ==> List(Set(uuid1))
      Ns.uuids(min(1)).query.get ==> List(Set(uuid1))
      Ns.uuids(min(2)).query.get ==> List(Set(uuid1, uuid2))

      Ns.n.uuids(min).query.get ==> List(
        (1, Set(uuid1)),
        (2, Set(uuid2)),
      )
      // Same as
      Ns.n.uuids(min(1)).query.get ==> List(
        (1, Set(uuid1)),
        (2, Set(uuid2)),
      )

      Ns.n.uuids(min(2)).query.get ==> List(
        (1, Set(uuid1, uuid2)),
        (2, Set(uuid2, uuid3)),
      )
    }


    "max" - types { implicit futConn =>
      Ns.n.uuids.insert(List(
        (1, Set(uuid1, uuid2)),
        (2, Set(uuid2, uuid3)),
        (2, Set(uuid3, uuid4)),
        (2, Set(uuid3, uuid4)),
      )).transact

      Ns.uuids(max).query.get ==> List(Set(uuid4))
      Ns.uuids(max(1)).query.get ==> List(Set(uuid4))
      Ns.uuids(max(2)).query.get ==> List(Set(uuid3, uuid4))

      Ns.n.uuids(max).query.get ==> List(
        (1, Set(uuid2)),
        (2, Set(uuid4)),
      )
      // Same as
      Ns.n.uuids(max(1)).query.get ==> List(
        (1, Set(uuid2)),
        (2, Set(uuid4)),
      )

      Ns.n.uuids(max(2)).query.get ==> List(
        (1, Set(uuid1, uuid2)),
        (2, Set(uuid3, uuid4)),
      )
    }


    "rand" - types { implicit conn =>
      Ns.n.uuids.insert(List(
        (1, Set(uuid1, uuid2)),
        (2, Set(uuid2, uuid3)),
        (2, Set(uuid3, uuid4)),
        (2, Set(uuid3, uuid4)),
      )).transact
      val all = Set(uuid1, uuid2, uuid3, uuid4)
      all.contains(Ns.uuids(rand).query.get.head.head) ==> true
      all.intersect(Ns.uuids(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.uuids(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - types { implicit futConn =>
      Ns.n.uuids.insert(List(
        (1, Set(uuid1, uuid2)),
        (2, Set(uuid2, uuid3)),
        (2, Set(uuid3, uuid4)),
        (2, Set(uuid3, uuid4)),
      )).transact
      val all = Set(uuid1, uuid2, uuid3, uuid4)
      all.contains(Ns.uuids(sample).query.get.head.head) ==> true
      all.intersect(Ns.uuids(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.uuids(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - types { implicit conn =>
      Ns.n.uuids.insert(List(
        (1, Set(uuid1, uuid2)),
        (2, Set(uuid2, uuid3)),
        (2, Set(uuid3, uuid4)),
        (2, Set(uuid3, uuid4)),
      )).transact

      Ns.n(count).query.get ==> List(4)
      Ns.n(countDistinct).query.get ==> List(2)

      Ns.uuids(count).query.get ==> List(8)
      Ns.uuids(countDistinct).query.get ==> List(4)

      Ns.n.a1.uuids(count).query.get ==> List(
        (1, 2),
        (2, 6)
      )
      Ns.n.a1.uuids(countDistinct).query.get ==> List(
        (1, 2),
        (2, 3)
      )
    }
  }
}