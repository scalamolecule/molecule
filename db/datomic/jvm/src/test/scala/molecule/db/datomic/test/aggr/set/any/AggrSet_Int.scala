package molecule.db.datomic.test.aggr.set.any

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSet_Int extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      Ns.i.ints.insert(List(
        (1, Set(int1, int2)),
        (2, Set(int2, int3)),
        (2, Set(int3, int4)),
        (2, Set(int3, int4)),
      )).transact

      // Non-aggregated card-many Set of attribute values coalesce
      Ns.i.a1.ints.query.get ==> List(
        (1, Set(int1, int2)),
        (2, Set(int2, int3, int4)), // 3 rows coalesced
      )

      // Use `distinct` keyword to retrieve unique Sets of values
      Ns.i.a1.ints(distinct).query.get ==> List(
        (1, Set(Set(int1, int2))),
        (2, Set(
          Set(int2, int3),
          Set(int3, int4) // 2 rows coalesced
        ))
      )

      Ns.ints(distinct).query.get ==> List(
        Set(
          Set(int1, int2),
          Set(int2, int3),
          Set(int3, int4),
        )
      )
    }


    "min" - types { implicit conn =>
      Ns.i.ints.insert(List(
        (1, Set(int1, int2)),
        (2, Set(int2, int3)),
        (2, Set(int3, int4)),
        (2, Set(int3, int4)),
      )).transact

      Ns.ints(min).query.get ==> List(Set(int1))
      Ns.ints(min(1)).query.get ==> List(Set(int1))
      Ns.ints(min(2)).query.get ==> List(Set(int1, int2))

      Ns.i.ints(min).query.get ==> List(
        (1, Set(int1)),
        (2, Set(int2)),
      )
      // Same as
      Ns.i.ints(min(1)).query.get ==> List(
        (1, Set(int1)),
        (2, Set(int2)),
      )

      Ns.i.ints(min(2)).query.get ==> List(
        (1, Set(int1, int2)),
        (2, Set(int2, int3)),
      )
    }


    "max" - types { implicit futConn =>
      Ns.i.ints.insert(List(
        (1, Set(int1, int2)),
        (2, Set(int2, int3)),
        (2, Set(int3, int4)),
        (2, Set(int3, int4)),
      )).transact

      Ns.ints(max).query.get ==> List(Set(int4))
      Ns.ints(max(1)).query.get ==> List(Set(int4))
      Ns.ints(max(2)).query.get ==> List(Set(int3, int4))

      Ns.i.ints(max).query.get ==> List(
        (1, Set(int2)),
        (2, Set(int4)),
      )
      // Same as
      Ns.i.ints(max(1)).query.get ==> List(
        (1, Set(int2)),
        (2, Set(int4)),
      )

      Ns.i.ints(max(2)).query.get ==> List(
        (1, Set(int1, int2)),
        (2, Set(int3, int4)),
      )
    }


    "rand" - types { implicit conn =>
      Ns.i.ints.insert(List(
        (1, Set(int1, int2)),
        (2, Set(int2, int3)),
        (2, Set(int3, int4)),
        (2, Set(int3, int4)),
      )).transact
      val all = Set(int1, int2, int3, int4)
      all.contains(Ns.ints(rand).query.get.head.head) ==> true
      all.intersect(Ns.ints(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.ints(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - types { implicit futConn =>
      Ns.i.ints.insert(List(
        (1, Set(int1, int2)),
        (2, Set(int2, int3)),
        (2, Set(int3, int4)),
        (2, Set(int3, int4)),
      )).transact
      val all = Set(int1, int2, int3, int4)
      all.contains(Ns.ints(sample).query.get.head.head) ==> true
      all.intersect(Ns.ints(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.ints(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - types { implicit conn =>
      Ns.i.ints.insert(List(
        (1, Set(int1, int2)),
        (2, Set(int2, int3)),
        (2, Set(int3, int4)),
        (2, Set(int3, int4)),
      )).transact

      Ns.i(count).query.get ==> List(4)
      Ns.i(countDistinct).query.get ==> List(2)

      Ns.ints(count).query.get ==> List(8)
      Ns.ints(countDistinct).query.get ==> List(4)

      Ns.i.a1.ints(count).query.get ==> List(
        (1, 2),
        (2, 6)
      )
      Ns.i.a1.ints(countDistinct).query.get ==> List(
        (1, 2),
        (2, 3)
      )
    }
  }
}