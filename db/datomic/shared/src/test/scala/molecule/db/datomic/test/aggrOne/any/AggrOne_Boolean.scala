// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.any


import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import molecule.db.datomic.test.aggrOne.any.AggrOne_Int.{int1, int2, int3}
import utest._

object AggrOne_Boolean extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      Ns.n.boolean.insert(List(
        (1, true),
        (2, false),
        (2, false),
        (2, true)
      )).transact


      Ns.n.a1.boolean.query.get.sortBy(t => (t._1, t._2)) ==> List(
        (1, true),
        (2, false), // 2 rows coalesced
        (2, true),
      )

      // Distinct values are returned in a List
      Ns.n.a1.boolean(distinct).query.get ==> List(
        (1, Set(true)),
        (2, Set(false, true)),
      )

      Ns.boolean(distinct).query.get.head ==> Set(false, true)
    }


    "min" - types { implicit conn =>
      Ns.boolean.insert(List(true, false, true)).transact
      Ns.boolean(min).query.get.head ==> false
      Ns.boolean(min(1)).query.get.head ==> Set(false)
      Ns.boolean(min(2)).query.get.head ==> Set(false, true)
    }


    "max" - types { implicit futConn =>
      Ns.boolean.insert(List(true, false, true)).transact
      Ns.boolean(max).query.get.head ==> true
      Ns.boolean(max(1)).query.get.head ==> Set(true)
      Ns.boolean(max(2)).query.get.head ==> Set(true, false)
    }


    "rand" - types { implicit conn =>
      Ns.boolean.insert(List(true, false, true)).transact
      val all = Set(true, false)
      all.contains(Ns.boolean(rand).query.get.head) ==> true
      all.intersect(Ns.boolean(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.boolean(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - types { implicit futConn =>
      Ns.boolean.insert(List(true, false, true)).transact
      val all = Set(true, false)
      all.contains(Ns.boolean(sample).query.get.head) ==> true
      all.intersect(Ns.boolean(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.boolean(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - types { implicit conn =>
      Ns.n.boolean.insert(List(
        (1, true),
        (2, false),
        (2, false),
        (2, true),
      )).transact

      Ns.n(count).query.get ==> List(4)
      Ns.n(countDistinct).query.get ==> List(2)

      Ns.boolean(count).query.get ==> List(4)
      Ns.boolean(countDistinct).query.get ==> List(2)

      Ns.n.a1.boolean(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      Ns.n.a1.boolean(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}