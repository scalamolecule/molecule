// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrSet.any


import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSet_Double_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      Ns.n.doubles.insert(List(
        (1, Set(double1, double2)),
        (2, Set(double2, double3)),
        (2, Set(double3, double4)),
        (2, Set(double3, double4)),
      )).transact

      // Non-aggregated card-many Set of attribute values coalesce
      Ns.n.a1.doubles.query.get ==> List(
        (1, Set(double1, double2)),
        (2, Set(double2, double3, double4)), // 3 rows coalesced
      )

      // Use `distinct` keyword to retrieve unique Sets of values
      Ns.n.a1.doubles(distinct).query.get ==> List(
        (1, Set(Set(double1, double2))),
        (2, Set(
          Set(double2, double3),
          Set(double3, double4) // 2 rows coalesced
        ))
      )

      Ns.doubles(distinct).query.get ==> List(
        Set(
          Set(double1, double2),
          Set(double2, double3),
          Set(double3, double4),
        )
      )
    }


    "min" - types { implicit conn =>
      Ns.n.doubles.insert(List(
        (1, Set(double1, double2)),
        (2, Set(double2, double3)),
        (2, Set(double3, double4)),
        (2, Set(double3, double4)),
      )).transact

      Ns.doubles(min).query.get ==> List(Set(double1))
      Ns.doubles(min(1)).query.get ==> List(Set(double1))
      Ns.doubles(min(2)).query.get ==> List(Set(double1, double2))

      Ns.n.doubles(min).query.get ==> List(
        (1, Set(double1)),
        (2, Set(double2)),
      )
      // Same as
      Ns.n.doubles(min(1)).query.get ==> List(
        (1, Set(double1)),
        (2, Set(double2)),
      )

      Ns.n.doubles(min(2)).query.get ==> List(
        (1, Set(double1, double2)),
        (2, Set(double2, double3)),
      )
    }


    "max" - types { implicit futConn =>
      Ns.n.doubles.insert(List(
        (1, Set(double1, double2)),
        (2, Set(double2, double3)),
        (2, Set(double3, double4)),
        (2, Set(double3, double4)),
      )).transact

      Ns.doubles(max).query.get ==> List(Set(double4))
      Ns.doubles(max(1)).query.get ==> List(Set(double4))
      Ns.doubles(max(2)).query.get ==> List(Set(double3, double4))

      Ns.n.doubles(max).query.get ==> List(
        (1, Set(double2)),
        (2, Set(double4)),
      )
      // Same as
      Ns.n.doubles(max(1)).query.get ==> List(
        (1, Set(double2)),
        (2, Set(double4)),
      )

      Ns.n.doubles(max(2)).query.get ==> List(
        (1, Set(double1, double2)),
        (2, Set(double3, double4)),
      )
    }


    "rand" - types { implicit conn =>
      Ns.n.doubles.insert(List(
        (1, Set(double1, double2)),
        (2, Set(double2, double3)),
        (2, Set(double3, double4)),
        (2, Set(double3, double4)),
      )).transact
      val all = Set(double1, double2, double3, double4)
      all.contains(Ns.doubles(rand).query.get.head.head) ==> true
      all.intersect(Ns.doubles(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.doubles(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - types { implicit futConn =>
      Ns.n.doubles.insert(List(
        (1, Set(double1, double2)),
        (2, Set(double2, double3)),
        (2, Set(double3, double4)),
        (2, Set(double3, double4)),
      )).transact
      val all = Set(double1, double2, double3, double4)
      all.contains(Ns.doubles(sample).query.get.head.head) ==> true
      all.intersect(Ns.doubles(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.doubles(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - types { implicit conn =>
      Ns.n.doubles.insert(List(
        (1, Set(double1, double2)),
        (2, Set(double2, double3)),
        (2, Set(double3, double4)),
        (2, Set(double3, double4)),
      )).transact

      Ns.n(count).query.get ==> List(4)
      Ns.n(countDistinct).query.get ==> List(2)

      Ns.doubles(count).query.get ==> List(8)
      Ns.doubles(countDistinct).query.get ==> List(4)

      Ns.n.a1.doubles(count).query.get ==> List(
        (1, 2),
        (2, 6)
      )
      Ns.n.a1.doubles(countDistinct).query.get ==> List(
        (1, 2),
        (2, 3)
      )
    }
  }
}