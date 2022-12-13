package molecule.db.datomic.test.aggr.one.any

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_Int extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      Ns.i.int.insert(List(
        (1, int1),
        (2, int2),
        (2, int2),
        (2, int3),
      )).transact

      Ns.i.a1.int.query.get.sortBy(_._2) ==> List(
        (1, int1),
        (2, int2), // 2 rows coalesced
        (2, int3),
      )

      // Distinct values are returned in a List
      Ns.i.a1.int(distinct).query.get ==> List(
        (1, Set(int1)),
        (2, Set(int2, int3)),
      )

      Ns.int(distinct).query.get.head ==> Set(
        int1, int2, int3
      )
    }


    "min" - types { implicit conn =>
      Ns.int.insert(List(int1, int2, int3)).transact
      Ns.int(min).query.get ==> List(int1)
      Ns.int(min(1)).query.get ==> List(Set(int1))
      Ns.int(min(2)).query.get ==> List(Set(int1, int2))
    }


    "max" - types { implicit futConn =>
      Ns.int.insert(List(int1, int2, int3)).transact
      Ns.int(max).query.get ==> List(int3)
      Ns.int(max(1)).query.get ==> List(Set(int3))
      Ns.int(max(2)).query.get ==> List(Set(int3, int2))
    }


    "rand" - types { implicit conn =>
      Ns.int.insert(List(int1, int2, int3)).transact
      val all = Set(int1, int2, int3, int4)
      all.contains(Ns.int(rand).query.get.head) ==> true
      all.intersect(Ns.int(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.int(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - types { implicit futConn =>
      Ns.int.insert(List(int1, int2, int3)).transact
      val all = Set(int1, int2, int3, int4)
      all.contains(Ns.int(sample).query.get.head) ==> true
      all.intersect(Ns.int(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.int(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - types { implicit conn =>
      Ns.i.int.insert(List(
        (1, int1),
        (2, int2),
        (2, int2),
        (2, int3),
      )).transact

      Ns.i(count).query.get ==> List(4)
      Ns.i(countDistinct).query.get ==> List(2)

      Ns.int(count).query.get ==> List(4)
      Ns.int(countDistinct).query.get ==> List(3)

      Ns.i.a1.int(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      Ns.i.a1.int(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}