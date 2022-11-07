// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrSet.any


import molecule.coreTests.dataModels.core.types.dsl.CardSet._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSet_Boolean extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - cardSet { implicit conn =>
      NsSet.n.booleans.insert(List(
        (1, Set(true)),
        (2, Set(false)),
        (2, Set(true, false))
      )).transact

      // Non-aggregated card-many Set of attribute values coalesce
      NsSet.n.a1.booleans.query.get ==> List(
        (1, Set(true)),
        (2, Set(true, false)), // 2 rows coalesced
      )

      // Use `distinct` keyword to retrieve unique Sets of values
      NsSet.n.a1.booleans(distinct).query.get ==> List(
        (1, Set(Set(true))),
        (2, Set(
          Set(false),
          Set(true, false)
        ))
      )

      NsSet.booleans(distinct).query.get ==> List(
        Set(
          Set(true),
          Set(false),
          Set(true, false),
        )
      )
    }


    "min" - cardSet { implicit conn =>
      NsSet.n.booleans.insert(List(
        (1, Set(true)),
        (2, Set(false)),
        (2, Set(true, false))
      )).transact

      NsSet.booleans(min).query.get ==> List(Set(false))
      NsSet.booleans(min(1)).query.get ==> List(Set(false))
      NsSet.booleans(min(2)).query.get ==> List(Set(true, false))

      NsSet.n.booleans(min).query.get ==> List(
        (1, Set(true)),
        (2, Set(false)),
      )
      NsSet.n.booleans(min(1)).query.get ==> List(
        (1, Set(true)),
        (2, Set(false)),
      )
      NsSet.n.booleans(min(2)).query.get ==> List(
        (1, Set(true)),
        (2, Set(false, true)),
      )
    }


    "max" - cardSet { implicit futConn =>
      NsSet.n.booleans.insert(List(
        (1, Set(true)),
        (2, Set(false)),
        (2, Set(true, false))
      )).transact

      NsSet.booleans(max).query.get ==> List(Set(true))
      NsSet.booleans(max(1)).query.get ==> List(Set(true))
      NsSet.booleans(max(2)).query.get ==> List(Set(true, false))

      NsSet.n.booleans(max).query.get ==> List(
        (1, Set(true)),
        (2, Set(true)),
      )
      NsSet.n.booleans(max(1)).query.get ==> List(
        (1, Set(true)),
        (2, Set(true)),
      )
      NsSet.n.booleans(max(2)).query.get ==> List(
        (1, Set(true)),
        (2, Set(true, false)),
      )
    }


    "rand" - cardSet { implicit conn =>
      NsSet.n.booleans.insert(List(
        (1, Set(true)),
        (2, Set(false)),
        (2, Set(true, false))
      )).transact
      val all = Set(true, false)
      all.contains(NsSet.booleans(rand).query.get.head.head) ==> true
      all.intersect(NsSet.booleans(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsSet.booleans(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - cardSet { implicit futConn =>
      NsSet.n.booleans.insert(List(
        (1, Set(true)),
        (2, Set(false)),
        (2, Set(true, false))
      )).transact
      val all = Set(true, false)
      all.contains(NsSet.booleans(sample).query.get.head.head) ==> true
      all.intersect(NsSet.booleans(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsSet.booleans(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - cardSet { implicit conn =>
      NsSet.n.booleans.insert(List(
        (1, Set(true)),
        (2, Set(false)),
        (2, Set(true, false))
      )).transact

      NsSet.n(count).query.get ==> List(3)
      NsSet.n(countDistinct).query.get ==> List(2)

      NsSet.booleans(count).query.get ==> List(4)
      NsSet.booleans(countDistinct).query.get ==> List(2)

      NsSet.n.a1.booleans(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      NsSet.n.a1.booleans(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}