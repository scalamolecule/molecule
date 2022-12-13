// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.set.any

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSet_Boolean extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      Ns.i.booleans.insert(List(
        (1, Set(true)),
        (2, Set(false)),
        (2, Set(true, false))
      )).transact

      // Non-aggregated card-many Set of attribute values coalesce
      Ns.i.a1.booleans.query.get ==> List(
        (1, Set(true)),
        (2, Set(true, false)), // 2 rows coalesced
      )

      // Use `distinct` keyword to retrieve unique Sets of values
      Ns.i.a1.booleans(distinct).query.get ==> List(
        (1, Set(Set(true))),
        (2, Set(
          Set(false),
          Set(true, false)
        ))
      )

      Ns.booleans(distinct).query.get ==> List(
        Set(
          Set(true),
          Set(false),
          Set(true, false),
        )
      )
    }


    "min" - types { implicit conn =>
      Ns.i.booleans.insert(List(
        (1, Set(true)),
        (2, Set(false)),
        (2, Set(true, false))
      )).transact

      Ns.booleans(min).query.get ==> List(Set(false))
      Ns.booleans(min(1)).query.get ==> List(Set(false))
      Ns.booleans(min(2)).query.get ==> List(Set(true, false))

      Ns.i.booleans(min).query.get ==> List(
        (1, Set(true)),
        (2, Set(false)),
      )
      Ns.i.booleans(min(1)).query.get ==> List(
        (1, Set(true)),
        (2, Set(false)),
      )
      Ns.i.booleans(min(2)).query.get ==> List(
        (1, Set(true)),
        (2, Set(false, true)),
      )
    }


    "max" - types { implicit futConn =>
      Ns.i.booleans.insert(List(
        (1, Set(true)),
        (2, Set(false)),
        (2, Set(true, false))
      )).transact

      Ns.booleans(max).query.get ==> List(Set(true))
      Ns.booleans(max(1)).query.get ==> List(Set(true))
      Ns.booleans(max(2)).query.get ==> List(Set(true, false))

      Ns.i.booleans(max).query.get ==> List(
        (1, Set(true)),
        (2, Set(true)),
      )
      Ns.i.booleans(max(1)).query.get ==> List(
        (1, Set(true)),
        (2, Set(true)),
      )
      Ns.i.booleans(max(2)).query.get ==> List(
        (1, Set(true)),
        (2, Set(true, false)),
      )
    }


    "rand" - types { implicit conn =>
      Ns.i.booleans.insert(List(
        (1, Set(true)),
        (2, Set(false)),
        (2, Set(true, false))
      )).transact
      val all = Set(true, false)
      all.contains(Ns.booleans(rand).query.get.head.head) ==> true
      all.intersect(Ns.booleans(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.booleans(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - types { implicit futConn =>
      Ns.i.booleans.insert(List(
        (1, Set(true)),
        (2, Set(false)),
        (2, Set(true, false))
      )).transact
      val all = Set(true, false)
      all.contains(Ns.booleans(sample).query.get.head.head) ==> true
      all.intersect(Ns.booleans(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.booleans(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - types { implicit conn =>
      Ns.i.booleans.insert(List(
        (1, Set(true)),
        (2, Set(false)),
        (2, Set(true, false))
      )).transact

      Ns.i(count).query.get ==> List(3)
      Ns.i(countDistinct).query.get ==> List(2)

      Ns.booleans(count).query.get ==> List(4)
      Ns.booleans(countDistinct).query.get ==> List(2)

      Ns.i.a1.booleans(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      Ns.i.a1.booleans(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}