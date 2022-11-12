// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.any


import molecule.coreTests.dataModels.core.types.dsl.TypesOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_Double_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - typesOne { implicit conn =>
      NsOne.n.double.insert(List(
        (1, double1),
        (2, double2),
        (2, double2),
        (2, double3),
      )).transact

      NsOne.n.a1.double.query.get.sortBy(_._2) ==> List(
        (1, double1),
        (2, double2), // 2 rows coalesced
        (2, double3),
      )

      // Distinct values are returned in a List
      NsOne.n.a1.double.apply(distinct).query.get ==> List(
        (1, Set(double1)),
        (2, Set(double2, double3)),
      )

      NsOne.double(distinct).query.get.head ==> Set(
        double1, double2, double3
      )
    }


    "min" - typesOne { implicit conn =>
      NsOne.double.insert(List(double1, double2, double3)).transact
      NsOne.double(min).query.get.head ==> double1
      NsOne.double(min(1)).query.get.head ==> Set(double1)
      NsOne.double(min(2)).query.get.head ==> Set(double1, double2)
    }


    "max" - typesOne { implicit futConn =>
      NsOne.double.insert(List(double1, double2, double3)).transact
      NsOne.double(max).query.get.head ==> double3
      NsOne.double(max(1)).query.get.head ==> Set(double3)
      NsOne.double(max(2)).query.get.head ==> Set(double3, double2)
    }


    "rand" - typesOne { implicit conn =>
      NsOne.double.insert(List(double1, double2, double3)).transact
      val all = Set(double1, double2, double3, double4)
      all.contains(NsOne.double.apply(rand).query.get.head) ==> true
      all.intersect(NsOne.double(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsOne.double(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - typesOne { implicit futConn =>
      NsOne.double.insert(List(double1, double2, double3)).transact
      val all = Set(double1, double2, double3, double4)
      all.contains(NsOne.double(sample).query.get.head) ==> true
      all.intersect(NsOne.double(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsOne.double(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - typesOne { implicit conn =>
      NsOne.n.double.insert(List(
        (1, double1),
        (2, double2),
        (2, double2),
        (2, double3),
      )).transact

      NsOne.n(count).query.get ==> List(4)
      NsOne.n(countDistinct).query.get ==> List(2)

      NsOne.double(count).query.get ==> List(4)
      NsOne.double(countDistinct).query.get ==> List(3)

      NsOne.n.a1.double(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      NsOne.n.a1.double(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}