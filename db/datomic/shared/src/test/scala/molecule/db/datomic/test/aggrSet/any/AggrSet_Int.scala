package molecule.db.datomic.test.aggrSet.any


import molecule.coreTests.dataModels.core.types.dsl.CardSet._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSet_Int extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - cardSet { implicit conn =>
      NsSet.n.ints.insert(List(
        (1, Set(int1, int2)),
        (2, Set(int2, int3)),
        (2, Set(int3, int4)),
        (2, Set(int3, int4)),
      )).transact

      // Non-aggregated card-many Set of attribute values coalesce
      NsSet.n.a1.ints.query.get ==> List(
        (1, Set(int1, int2)),
        (2, Set(int2, int3, int4)), // 3 rows coalesced
      )

      // Use `distinct` keyword to retrieve unique Sets of values
      NsSet.n.a1.ints(distinct).query.get ==> List(
        (1, Set(Set(int1, int2))),
        (2, Set(
          Set(int2, int3),
          Set(int3, int4) // 2 rows coalesced
        ))
      )

      NsSet.ints(distinct).query.get ==> List(
        Set(
          Set(int1, int2),
          Set(int2, int3),
          Set(int3, int4),
        )
      )
    }


    "min" - cardSet { implicit conn =>
      NsSet.n.ints.insert(List(
        (1, Set(int1, int2)),
        (2, Set(int2, int3)),
        (2, Set(int3, int4)),
        (2, Set(int3, int4)),
      )).transact

      NsSet.ints(min).query.get ==> List(Set(int1))
      NsSet.ints(min(1)).query.get ==> List(Set(int1))
      NsSet.ints(min(2)).query.get ==> List(Set(int1, int2))

      NsSet.n.ints(min).query.get ==> List(
        (1, Set(int1)),
        (2, Set(int2)),
      )
      // Same as
      NsSet.n.ints(min(1)).query.get ==> List(
        (1, Set(int1)),
        (2, Set(int2)),
      )

      NsSet.n.ints(min(2)).query.get ==> List(
        (1, Set(int1, int2)),
        (2, Set(int2, int3)),
      )
    }


    "max" - cardSet { implicit futConn =>
      NsSet.n.ints.insert(List(
        (1, Set(int1, int2)),
        (2, Set(int2, int3)),
        (2, Set(int3, int4)),
        (2, Set(int3, int4)),
      )).transact

      NsSet.ints(max).query.get ==> List(Set(int4))
      NsSet.ints(max(1)).query.get ==> List(Set(int4))
      NsSet.ints(max(2)).query.get ==> List(Set(int3, int4))

      NsSet.n.ints(max).query.get ==> List(
        (1, Set(int2)),
        (2, Set(int4)),
      )
      // Same as
      NsSet.n.ints(max(1)).query.get ==> List(
        (1, Set(int2)),
        (2, Set(int4)),
      )

      NsSet.n.ints(max(2)).query.get ==> List(
        (1, Set(int1, int2)),
        (2, Set(int3, int4)),
      )
    }


    "rand" - cardSet { implicit conn =>
      NsSet.n.ints.insert(List(
        (1, Set(int1, int2)),
        (2, Set(int2, int3)),
        (2, Set(int3, int4)),
        (2, Set(int3, int4)),
      )).transact
      val all = Set(int1, int2, int3, int4)
      all.contains(NsSet.ints(rand).query.get.head.head) ==> true
      all.intersect(NsSet.ints(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsSet.ints(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - cardSet { implicit futConn =>
      NsSet.n.ints.insert(List(
        (1, Set(int1, int2)),
        (2, Set(int2, int3)),
        (2, Set(int3, int4)),
        (2, Set(int3, int4)),
      )).transact
      val all = Set(int1, int2, int3, int4)
      all.contains(NsSet.ints(sample).query.get.head.head) ==> true
      all.intersect(NsSet.ints(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsSet.ints(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - cardSet { implicit conn =>
      NsSet.n.ints.insert(List(
        (1, Set(int1, int2)),
        (2, Set(int2, int3)),
        (2, Set(int3, int4)),
        (2, Set(int3, int4)),
      )).transact

      NsSet.n(count).query.get ==> List(4)
      NsSet.n(countDistinct).query.get ==> List(2)

      NsSet.ints(count).query.get ==> List(8)
      NsSet.ints(countDistinct).query.get ==> List(4)

      NsSet.n.a1.ints(count).query.get ==> List(
        (1, 2),
        (2, 6)
      )
      NsSet.n.a1.ints(countDistinct).query.get ==> List(
        (1, 2),
        (2, 3)
      )
    }
  }
}