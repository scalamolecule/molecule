// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.one.any

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_Double_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      Ns.i.double.insert(List(
        (1, double1),
        (2, double2),
        (2, double2),
        (2, double3),
      )).transact

      Ns.i.a1.double.query.get.sortBy(_._2) ==> List(
        (1, double1),
        (2, double2), // 2 rows coalesced
        (2, double3),
      )

      // Distinct values are returned in a List
      Ns.i.a1.double.apply(distinct).query.get ==> List(
        (1, Set(double1)),
        (2, Set(double2, double3)),
      )

      Ns.double(distinct).query.get.head ==> Set(
        double1, double2, double3
      )
    }


    "min" - types { implicit conn =>
      Ns.double.insert(List(double1, double2, double3)).transact
      Ns.double(min).query.get ==> List(double1)
      Ns.double(min(1)).query.get ==> List(Set(double1))
      Ns.double(min(2)).query.get ==> List(Set(double1, double2))
    }


    "max" - types { implicit futConn =>
      Ns.double.insert(List(double1, double2, double3)).transact
      Ns.double(max).query.get ==> List(double3)
      Ns.double(max(1)).query.get ==> List(Set(double3))
      Ns.double(max(2)).query.get ==> List(Set(double3, double2))
    }


    "rand" - types { implicit conn =>
      Ns.double.insert(List(double1, double2, double3)).transact
      val all = Set(double1, double2, double3, double4)
      all.contains(Ns.double.apply(rand).query.get.head) ==> true
      all.intersect(Ns.double(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.double(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - types { implicit futConn =>
      Ns.double.insert(List(double1, double2, double3)).transact
      val all = Set(double1, double2, double3, double4)
      all.contains(Ns.double(sample).query.get.head) ==> true
      all.intersect(Ns.double(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.double(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - types { implicit conn =>
      Ns.i.double.insert(List(
        (1, double1),
        (2, double2),
        (2, double2),
        (2, double3),
      )).transact

      Ns.i(count).query.get ==> List(4)
      Ns.i(countDistinct).query.get ==> List(2)

      Ns.double(count).query.get ==> List(4)
      Ns.double(countDistinct).query.get ==> List(3)

      Ns.i.a1.double(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      Ns.i.a1.double(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}