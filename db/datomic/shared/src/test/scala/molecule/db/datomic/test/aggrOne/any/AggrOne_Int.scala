package molecule.db.datomic.test.aggrOne.any


import molecule.coreTests.dataModels.core.types.dsl.TypesOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_Int extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - typesOne { implicit conn =>
      NsOne.n.int.insert(List(
        (1, int1),
        (2, int2),
        (2, int2),
        (2, int3),
      )).transact

      NsOne.n.a1.int.query.get.sortBy(_._2) ==> List(
        (1, int1),
        (2, int2), // 2 rows coalesced
        (2, int3),
      )

      // Distinct values are returned in a List
      NsOne.n.a1.int.apply(distinct).query.get ==> List(
        (1, Set(int1)),
        (2, Set(int2, int3)),
      )

      NsOne.int(distinct).query.get.head ==> Set(
        int1, int2, int3
      )
    }


    "min" - typesOne { implicit conn =>
      NsOne.int.insert(List(int1, int2, int3)).transact
      NsOne.int(min).query.get ==> List(int1)
      NsOne.int(min(1)).query.get ==> List(Set(int1))
      NsOne.int(min(2)).query.get ==> List(Set(int1, int2))
    }


    "max" - typesOne { implicit futConn =>
      NsOne.int.insert(List(int1, int2, int3)).transact
      NsOne.int(max).query.get ==> List(int3)
      NsOne.int(max(1)).query.get ==> List(Set(int3))
      NsOne.int(max(2)).query.get ==> List(Set(int3, int2))
    }


    "rand" - typesOne { implicit conn =>
      NsOne.int.insert(List(int1, int2, int3)).transact
      val all = Set(int1, int2, int3, int4)
      all.contains(NsOne.int.apply(rand).query.get.head) ==> true
      all.intersect(NsOne.int(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsOne.int(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - typesOne { implicit futConn =>
      NsOne.int.insert(List(int1, int2, int3)).transact
      val all = Set(int1, int2, int3, int4)
      all.contains(NsOne.int(sample).query.get.head) ==> true
      all.intersect(NsOne.int(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsOne.int(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - typesOne { implicit conn =>
      NsOne.n.int.insert(List(
        (1, int1),
        (2, int2),
        (2, int2),
        (2, int3),
      )).transact

      NsOne.n(count).query.get ==> List(4)
      NsOne.n(countDistinct).query.get ==> List(2)

      NsOne.int(count).query.get ==> List(4)
      NsOne.int(countDistinct).query.get ==> List(3)

      NsOne.n.a1.int(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      NsOne.n.a1.int(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}