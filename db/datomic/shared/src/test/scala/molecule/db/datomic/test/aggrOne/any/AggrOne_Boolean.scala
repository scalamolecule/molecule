// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.any


import molecule.coreTests.dataModels.core.types.dsl.TypesOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import molecule.db.datomic.test.aggrOne.any.AggrOne_Int.{int1, int2, int3}
import utest._

object AggrOne_Boolean extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - typesOne { implicit conn =>
      NsOne.n.boolean.insert(List(
        (1, true),
        (2, false),
        (2, false),
        (2, true)
      )).transact


      NsOne.n.a1.boolean.query.get.sortBy(t => (t._1, t._2)) ==> List(
        (1, true),
        (2, false), // 2 rows coalesced
        (2, true),
      )

      // Distinct values are returned in a List
      NsOne.n.a1.boolean(distinct).query.get ==> List(
        (1, Set(true)),
        (2, Set(false, true)),
      )

      NsOne.boolean(distinct).query.get.head ==> Set(false, true)
    }


    "min" - typesOne { implicit conn =>
      NsOne.boolean.insert(List(true, false, true)).transact
      NsOne.boolean(min).query.get.head ==> false
      NsOne.boolean(min(1)).query.get.head ==> Set(false)
      NsOne.boolean(min(2)).query.get.head ==> Set(false, true)
    }


    "max" - typesOne { implicit futConn =>
      NsOne.boolean.insert(List(true, false, true)).transact
      NsOne.boolean(max).query.get.head ==> true
      NsOne.boolean(max(1)).query.get.head ==> Set(true)
      NsOne.boolean(max(2)).query.get.head ==> Set(true, false)
    }


    "rand" - typesOne { implicit conn =>
      NsOne.boolean.insert(List(true, false, true)).transact
      val all = Set(true, false, true, boolean4)
      all.contains(NsOne.boolean(rand).query.get.head) ==> true
      all.intersect(NsOne.boolean(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsOne.boolean(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - typesOne { implicit futConn =>
      NsOne.boolean.insert(List(true, false, true)).transact
      val all = Set(true, false, true, boolean4)
      all.contains(NsOne.boolean(sample).query.get.head) ==> true
      all.intersect(NsOne.boolean(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsOne.boolean(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - typesOne { implicit conn =>
      NsOne.n.boolean.insert(List(
        (1, true),
        (2, false),
        (2, false),
        (2, true),
      )).transact

      NsOne.n(count).query.get ==> List(4)
      NsOne.n(countDistinct).query.get ==> List(2)

      NsOne.boolean(count).query.get ==> List(4)
      NsOne.boolean(countDistinct).query.get ==> List(2)

      NsOne.n.a1.boolean(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      NsOne.n.a1.boolean(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}