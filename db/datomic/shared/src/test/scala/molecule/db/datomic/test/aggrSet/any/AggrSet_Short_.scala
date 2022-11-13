// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrSet.any


import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSet_Short_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      Ns.n.shorts.insert(List(
        (1, Set(short1, short2)),
        (2, Set(short2, short3)),
        (2, Set(short3, short4)),
        (2, Set(short3, short4)),
      )).transact

      // Non-aggregated card-many Set of attribute values coalesce
      Ns.n.a1.shorts.query.get ==> List(
        (1, Set(short1, short2)),
        (2, Set(short2, short3, short4)), // 3 rows coalesced
      )

      // Use `distinct` keyword to retrieve unique Sets of values
      Ns.n.a1.shorts(distinct).query.get ==> List(
        (1, Set(Set(short1, short2))),
        (2, Set(
          Set(short2, short3),
          Set(short3, short4) // 2 rows coalesced
        ))
      )

      Ns.shorts(distinct).query.get ==> List(
        Set(
          Set(short1, short2),
          Set(short2, short3),
          Set(short3, short4),
        )
      )
    }


    "min" - types { implicit conn =>
      Ns.n.shorts.insert(List(
        (1, Set(short1, short2)),
        (2, Set(short2, short3)),
        (2, Set(short3, short4)),
        (2, Set(short3, short4)),
      )).transact

      Ns.shorts(min).query.get ==> List(Set(short1))
      Ns.shorts(min(1)).query.get ==> List(Set(short1))
      Ns.shorts(min(2)).query.get ==> List(Set(short1, short2))

      Ns.n.shorts(min).query.get ==> List(
        (1, Set(short1)),
        (2, Set(short2)),
      )
      // Same as
      Ns.n.shorts(min(1)).query.get ==> List(
        (1, Set(short1)),
        (2, Set(short2)),
      )

      Ns.n.shorts(min(2)).query.get ==> List(
        (1, Set(short1, short2)),
        (2, Set(short2, short3)),
      )
    }


    "max" - types { implicit futConn =>
      Ns.n.shorts.insert(List(
        (1, Set(short1, short2)),
        (2, Set(short2, short3)),
        (2, Set(short3, short4)),
        (2, Set(short3, short4)),
      )).transact

      Ns.shorts(max).query.get ==> List(Set(short4))
      Ns.shorts(max(1)).query.get ==> List(Set(short4))
      Ns.shorts(max(2)).query.get ==> List(Set(short3, short4))

      Ns.n.shorts(max).query.get ==> List(
        (1, Set(short2)),
        (2, Set(short4)),
      )
      // Same as
      Ns.n.shorts(max(1)).query.get ==> List(
        (1, Set(short2)),
        (2, Set(short4)),
      )

      Ns.n.shorts(max(2)).query.get ==> List(
        (1, Set(short1, short2)),
        (2, Set(short3, short4)),
      )
    }


    "rand" - types { implicit conn =>
      Ns.n.shorts.insert(List(
        (1, Set(short1, short2)),
        (2, Set(short2, short3)),
        (2, Set(short3, short4)),
        (2, Set(short3, short4)),
      )).transact
      val all = Set(short1, short2, short3, short4)
      all.contains(Ns.shorts(rand).query.get.head.head) ==> true
      all.intersect(Ns.shorts(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.shorts(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - types { implicit futConn =>
      Ns.n.shorts.insert(List(
        (1, Set(short1, short2)),
        (2, Set(short2, short3)),
        (2, Set(short3, short4)),
        (2, Set(short3, short4)),
      )).transact
      val all = Set(short1, short2, short3, short4)
      all.contains(Ns.shorts(sample).query.get.head.head) ==> true
      all.intersect(Ns.shorts(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.shorts(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - types { implicit conn =>
      Ns.n.shorts.insert(List(
        (1, Set(short1, short2)),
        (2, Set(short2, short3)),
        (2, Set(short3, short4)),
        (2, Set(short3, short4)),
      )).transact

      Ns.n(count).query.get ==> List(4)
      Ns.n(countDistinct).query.get ==> List(2)

      Ns.shorts(count).query.get ==> List(8)
      Ns.shorts(countDistinct).query.get ==> List(4)

      Ns.n.a1.shorts(count).query.get ==> List(
        (1, 2),
        (2, 6)
      )
      Ns.n.a1.shorts(countDistinct).query.get ==> List(
        (1, 2),
        (2, 3)
      )
    }
  }
}