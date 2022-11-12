// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.any


import java.util.Date
import molecule.coreTests.dataModels.core.types.dsl.TypesOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_Date_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - typesOne { implicit conn =>
      NsOne.n.date.insert(List(
        (1, date1),
        (2, date2),
        (2, date2),
        (2, date3),
      )).transact

      NsOne.n.a1.date.query.get.sortBy(_._2) ==> List(
        (1, date1),
        (2, date2), // 2 rows coalesced
        (2, date3),
      )

      // Distinct values are returned in a List
      NsOne.n.a1.date.apply(distinct).query.get ==> List(
        (1, Set(date1)),
        (2, Set(date2, date3)),
      )

      NsOne.date(distinct).query.get.head ==> Set(
        date1, date2, date3
      )
    }


    "min" - typesOne { implicit conn =>
      NsOne.date.insert(List(date1, date2, date3)).transact
      NsOne.date(min).query.get.head ==> date1
      NsOne.date(min(1)).query.get.head ==> Set(date1)
      NsOne.date(min(2)).query.get.head ==> Set(date1, date2)
    }


    "max" - typesOne { implicit futConn =>
      NsOne.date.insert(List(date1, date2, date3)).transact
      NsOne.date(max).query.get.head ==> date3
      NsOne.date(max(1)).query.get.head ==> Set(date3)
      NsOne.date(max(2)).query.get.head ==> Set(date3, date2)
    }


    "rand" - typesOne { implicit conn =>
      NsOne.date.insert(List(date1, date2, date3)).transact
      val all = Set(date1, date2, date3, date4)
      all.contains(NsOne.date.apply(rand).query.get.head) ==> true
      all.intersect(NsOne.date(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsOne.date(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - typesOne { implicit futConn =>
      NsOne.date.insert(List(date1, date2, date3)).transact
      val all = Set(date1, date2, date3, date4)
      all.contains(NsOne.date(sample).query.get.head) ==> true
      all.intersect(NsOne.date(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsOne.date(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - typesOne { implicit conn =>
      NsOne.n.date.insert(List(
        (1, date1),
        (2, date2),
        (2, date2),
        (2, date3),
      )).transact

      NsOne.n(count).query.get ==> List(4)
      NsOne.n(countDistinct).query.get ==> List(2)

      NsOne.date(count).query.get ==> List(4)
      NsOne.date(countDistinct).query.get ==> List(3)

      NsOne.n.a1.date(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      NsOne.n.a1.date(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}