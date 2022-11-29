// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.any


import java.util.Date
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_Date_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      Ns.i.date.insert(List(
        (1, date1),
        (2, date2),
        (2, date2),
        (2, date3),
      )).transact

      Ns.i.a1.date.query.get.sortBy(_._2) ==> List(
        (1, date1),
        (2, date2), // 2 rows coalesced
        (2, date3),
      )

      // Distinct values are returned in a List
      Ns.i.a1.date.apply(distinct).query.get ==> List(
        (1, Set(date1)),
        (2, Set(date2, date3)),
      )

      Ns.date(distinct).query.get.head ==> Set(
        date1, date2, date3
      )
    }


    "min" - types { implicit conn =>
      Ns.date.insert(List(date1, date2, date3)).transact
      Ns.date(min).query.get ==> List(date1)
      Ns.date(min(1)).query.get ==> List(Set(date1))
      Ns.date(min(2)).query.get ==> List(Set(date1, date2))
    }


    "max" - types { implicit futConn =>
      Ns.date.insert(List(date1, date2, date3)).transact
      Ns.date(max).query.get ==> List(date3)
      Ns.date(max(1)).query.get ==> List(Set(date3))
      Ns.date(max(2)).query.get ==> List(Set(date3, date2))
    }


    "rand" - types { implicit conn =>
      Ns.date.insert(List(date1, date2, date3)).transact
      val all = Set(date1, date2, date3, date4)
      all.contains(Ns.date.apply(rand).query.get.head) ==> true
      all.intersect(Ns.date(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.date(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - types { implicit futConn =>
      Ns.date.insert(List(date1, date2, date3)).transact
      val all = Set(date1, date2, date3, date4)
      all.contains(Ns.date(sample).query.get.head) ==> true
      all.intersect(Ns.date(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.date(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - types { implicit conn =>
      Ns.i.date.insert(List(
        (1, date1),
        (2, date2),
        (2, date2),
        (2, date3),
      )).transact

      Ns.i(count).query.get ==> List(4)
      Ns.i(countDistinct).query.get ==> List(2)

      Ns.date(count).query.get ==> List(4)
      Ns.date(countDistinct).query.get ==> List(3)

      Ns.i.a1.date(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      Ns.i.a1.date(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}