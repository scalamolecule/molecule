// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrSet.any


import molecule.coreTests.dataModels.core.types.dsl.CardSet._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSet_Double_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - cardSet { implicit conn =>
      NsSet.n.doubles.insert(List(
        (1, Set(double1, double2)),
        (2, Set(double2, double3)),
        (2, Set(double3, double4)),
        (2, Set(double3, double4)),
      )).transact

      // Non-aggregated card-many Set of attribute values coalesce
      NsSet.n.a1.doubles.query.get ==> List(
        (1, Set(double1, double2)),
        (2, Set(double2, double3, double4)), // 3 rows coalesced
      )

      // Use `distinct` keyword to retrieve unique Sets of values
      NsSet.n.a1.doubles(distinct).query.get ==> List(
        (1, Set(Set(double1, double2))),
        (2, Set(
          Set(double2, double3),
          Set(double3, double4) // 2 rows coalesced
        ))
      )

      NsSet.doubles.apply(distinct).query.get ==> List(
        Set(
          Set(double1, double2),
          Set(double2, double3),
          Set(double3, double4),
        )
      )
    }


    "min" - cardSet { implicit conn =>
      NsSet.n.doubles.insert(List(
        (1, Set(double1, double2)),
        (2, Set(double2, double3)),
        (2, Set(double3, double4)),
        (2, Set(double3, double4)),
      )).transact

      NsSet.doubles(min).query.get ==> List(Set(double1))
      NsSet.doubles(min(1)).query.get ==> List(Set(Set(double1)))
      NsSet.doubles(min(2)).query.get ==> List(Set(Set(double1, double2)))

      NsSet.n.doubles(min).query.get ==> List(
        (1, Set(double1)),
        (2, Set(double2)),
      )
      NsSet.n.doubles(min(1)).query.get ==> List(
        (1, Set(Set(double1))),
        (2, Set(Set(double2))),
      )
      NsSet.n.doubles(min(2)).query.get ==> List(
        (1, Set(Set(double1, double2))),
        (2, Set(Set(double2, double3))),
      )
    }


    "max" - cardSet { implicit futConn =>
      NsSet.n.doubles.insert(List(
        (1, Set(double1, double2)),
        (2, Set(double2, double3)),
        (2, Set(double3, double4)),
        (2, Set(double3, double4)),
      )).transact

      NsSet.doubles(max).query.get ==> List(Set(double4))
      NsSet.doubles(max(1)).query.get ==> List(Set(Set(double4)))
      NsSet.doubles(max(2)).query.get ==> List(Set(Set(double3, double4)))

      NsSet.n.doubles(max).query.get ==> List(
        (1, Set(double2)),
        (2, Set(double4)),
      )
      NsSet.n.doubles(max(1)).query.get ==> List(
        (1, Set(Set(double2))),
        (2, Set(Set(double4))),
      )
      NsSet.n.doubles(max(2)).query.get ==> List(
        (1, Set(Set(double1, double2))),
        (2, Set(Set(double3, double4))),
      )
    }


    "rand" - cardSet { implicit conn =>
      NsSet.n.doubles.insert(List(
        (1, Set(double1, double2)),
        (2, Set(double2, double3)),
        (2, Set(double3, double4)),
        (2, Set(double3, double4)),
      )).transact
      val all = Set(double1, double2, double3, double4)
      all.contains(NsSet.doubles(rand).query.get.head.head) ==> true
      all.intersect(NsSet.doubles(rand(1)).query.get.head.head).nonEmpty ==> true
      all.intersect(NsSet.doubles(rand(2)).query.get.head.head).nonEmpty ==> true
    }


    "sample" - cardSet { implicit futConn =>
      NsSet.n.doubles.insert(List(
        (1, Set(double1, double2)),
        (2, Set(double2, double3)),
        (2, Set(double3, double4)),
        (2, Set(double3, double4)),
      )).transact
      val all = Set(double1, double2, double3, double4)
      all.contains(NsSet.doubles(sample).query.get.head.head) ==> true
      all.intersect(NsSet.doubles(sample(1)).query.get.head.head).nonEmpty ==> true
      all.intersect(NsSet.doubles(sample(2)).query.get.head.head).nonEmpty ==> true
    }


    "count, countDistinct" - cardSet { implicit conn =>
      NsSet.n.doubles.insert(List(
        (1, Set(double1, double2)),
        (2, Set(double2, double3)),
        (2, Set(double3, double4)),
        (2, Set(double3, double4)),
      )).transact

      NsSet.n(count).query.get ==> List(4)
      NsSet.n(countDistinct).query.get ==> List(2)

      NsSet.doubles(count).query.get ==> List(8)
      NsSet.doubles(countDistinct).query.get ==> List(4)

      NsSet.n.a1.doubles(count).query.get ==> List(
        (1, 2),
        (2, 6)
      )
      NsSet.n.a1.doubles(countDistinct).query.get ==> List(
        (1, 2),
        (2, 3)
      )
    }
  }
}