// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.any

import molecule.boilerplate.api.Keywords._
import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_Double_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - cardOne { implicit conn =>
      One.n.double.insert(List(
        (1, double1),
        (2, double2),
        (2, double2),
        (2, double3),
      )).transact

      // Distinct values are returned in a List
      One.double.apply(distinct).query.get.head.sorted ==> List(double1, double2, double3)
      One.n.a1.double(distinct).query.get.map(r => (r._1, r._2.sorted)) ==> List(
        (1, List(double1)),
        (2, List(double2, double3)),
      )
    }


    "min" - cardOne { implicit conn =>
      One.double.insert(List(double1, double2, double3)).transact
      One.double.apply(min).query.get.head ==> double1
      One.double(min(1)).query.get.head ==> List(double1)
      One.double(min(2)).query.get.head ==> List(double1, double2)
    }


    "max" - cardOne { implicit futConn =>
      One.double.insert(List(double1, double2, double3)).transact
      One.double(max).query.get.head ==> double3
      One.double(max(1)).query.get.head ==> List(double3)
      One.double(max(2)).query.get.head ==> List(double3, double2)
    }


    "rand" - cardOne { implicit conn =>
      One.double.insert(List(double1, double2, double3)).transact
      val all = Seq(double1, double2, double3, double4)
      all.contains(One.double(rand).query.get.head) ==> true
      all.intersect(One.double(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(One.double(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - cardOne { implicit futConn =>
      One.double.insert(List(double1, double2, double3)).transact
      val all = Seq(double1, double2, double3, double4)
      all.contains(One.double(sample).query.get.head) ==> true
      all.intersect(One.double(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(One.double(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - cardOne { implicit conn =>
      One.n.double.insert(List(
        (1, double1),
        (2, double2),
        (2, double2),
        (2, double3),
      )).transact

      One.n(count).query.get ==> List(4)
      One.n(countDistinct).query.get ==> List(2)

      One.double(count).query.get ==> List(4)
      One.double(countDistinct).query.get ==> List(3)

      One.n.a1.double(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      One.n.a1.double(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}