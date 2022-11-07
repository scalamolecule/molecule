// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrSet.any


import molecule.coreTests.dataModels.core.types.dsl.CardSet._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSet_Short_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - cardSet { implicit conn =>
      NsSet.n.shorts.insert(List(
        (1, Set(short1, short2)),
        (2, Set(short2, short3)),
        (2, Set(short3, short4)),
        (2, Set(short3, short4)),
      )).transact

      // Non-aggregated card-many Set of attribute values coalesce
      NsSet.n.a1.shorts.query.get ==> List(
        (1, Set(short1, short2)),
        (2, Set(short2, short3, short4)), // 3 rows coalesced
      )

      // Use `distinct` keyword to retrieve unique Sets of values
      NsSet.n.a1.shorts(distinct).query.get ==> List(
        (1, Set(Set(short1, short2))),
        (2, Set(
          Set(short2, short3),
          Set(short3, short4) // 2 rows coalesced
        ))
      )

      NsSet.shorts(distinct).query.get ==> List(
        Set(
          Set(short1, short2),
          Set(short2, short3),
          Set(short3, short4),
        )
      )
    }


    "min" - cardSet { implicit conn =>
      NsSet.n.shorts.insert(List(
        (1, Set(short1, short2)),
        (2, Set(short2, short3)),
        (2, Set(short3, short4)),
        (2, Set(short3, short4)),
      )).transact

      NsSet.shorts(min).query.get ==> List(Set(short1))
      NsSet.shorts(min(1)).query.get ==> List(Set(short1))
      NsSet.shorts(min(2)).query.get ==> List(Set(short1, short2))

      NsSet.n.shorts(min).query.get ==> List(
        (1, Set(short1)),
        (2, Set(short2)),
      )
      // Same as
      NsSet.n.shorts(min(1)).query.get ==> List(
        (1, Set(short1)),
        (2, Set(short2)),
      )

      NsSet.n.shorts(min(2)).query.get ==> List(
        (1, Set(short1, short2)),
        (2, Set(short2, short3)),
      )
    }


    "max" - cardSet { implicit futConn =>
      NsSet.n.shorts.insert(List(
        (1, Set(short1, short2)),
        (2, Set(short2, short3)),
        (2, Set(short3, short4)),
        (2, Set(short3, short4)),
      )).transact

      NsSet.shorts(max).query.get ==> List(Set(short4))
      NsSet.shorts(max(1)).query.get ==> List(Set(short4))
      NsSet.shorts(max(2)).query.get ==> List(Set(short3, short4))

      NsSet.n.shorts(max).query.get ==> List(
        (1, Set(short2)),
        (2, Set(short4)),
      )
      // Same as
      NsSet.n.shorts(max(1)).query.get ==> List(
        (1, Set(short2)),
        (2, Set(short4)),
      )

      NsSet.n.shorts(max(2)).query.get ==> List(
        (1, Set(short1, short2)),
        (2, Set(short3, short4)),
      )
    }


    "rand" - cardSet { implicit conn =>
      NsSet.n.shorts.insert(List(
        (1, Set(short1, short2)),
        (2, Set(short2, short3)),
        (2, Set(short3, short4)),
        (2, Set(short3, short4)),
      )).transact
      val all = Set(short1, short2, short3, short4)
      all.contains(NsSet.shorts(rand).query.get.head.head) ==> true
      all.intersect(NsSet.shorts(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsSet.shorts(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - cardSet { implicit futConn =>
      NsSet.n.shorts.insert(List(
        (1, Set(short1, short2)),
        (2, Set(short2, short3)),
        (2, Set(short3, short4)),
        (2, Set(short3, short4)),
      )).transact
      val all = Set(short1, short2, short3, short4)
      all.contains(NsSet.shorts(sample).query.get.head.head) ==> true
      all.intersect(NsSet.shorts(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsSet.shorts(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - cardSet { implicit conn =>
      NsSet.n.shorts.insert(List(
        (1, Set(short1, short2)),
        (2, Set(short2, short3)),
        (2, Set(short3, short4)),
        (2, Set(short3, short4)),
      )).transact

      NsSet.n(count).query.get ==> List(4)
      NsSet.n(countDistinct).query.get ==> List(2)

      NsSet.shorts(count).query.get ==> List(8)
      NsSet.shorts(countDistinct).query.get ==> List(4)

      NsSet.n.a1.shorts(count).query.get ==> List(
        (1, 2),
        (2, 6)
      )
      NsSet.n.a1.shorts(countDistinct).query.get ==> List(
        (1, 2),
        (2, 3)
      )
    }
  }
}