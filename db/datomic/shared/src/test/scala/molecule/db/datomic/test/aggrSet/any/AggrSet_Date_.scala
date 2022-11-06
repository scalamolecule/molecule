// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrSet.any


import java.util.Date
import molecule.coreTests.dataModels.core.types.dsl.CardSet._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSet_Date_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - cardSet { implicit conn =>
      NsSet.n.dates.insert(List(
        (1, Set(date1, date2)),
        (2, Set(date2, date3)),
        (2, Set(date3, date4)),
        (2, Set(date3, date4)),
      )).transact

      // Non-aggregated card-many Set of attribute values coalesce
      NsSet.n.a1.dates.query.get ==> List(
        (1, Set(date1, date2)),
        (2, Set(date2, date3, date4)), // 3 rows coalesced
      )

      // Use `distinct` keyword to retrieve unique Sets of values
      NsSet.n.a1.dates(distinct).query.get ==> List(
        (1, Set(Set(date1, date2))),
        (2, Set(
          Set(date2, date3),
          Set(date3, date4) // 2 rows coalesced
        ))
      )

      NsSet.dates.apply(distinct).query.get ==> List(
        Set(
          Set(date1, date2),
          Set(date2, date3),
          Set(date3, date4),
        )
      )
    }


    "min" - cardSet { implicit conn =>
      NsSet.n.dates.insert(List(
        (1, Set(date1, date2)),
        (2, Set(date2, date3)),
        (2, Set(date3, date4)),
        (2, Set(date3, date4)),
      )).transact

      NsSet.dates(min).query.get ==> List(Set(date1))
      NsSet.dates(min(1)).query.get ==> List(Set(Set(date1)))
      NsSet.dates(min(2)).query.get ==> List(Set(Set(date1, date2)))

      NsSet.n.dates(min).query.get ==> List(
        (1, Set(date1)),
        (2, Set(date2)),
      )
      NsSet.n.dates(min(1)).query.get ==> List(
        (1, Set(Set(date1))),
        (2, Set(Set(date2))),
      )
      NsSet.n.dates(min(2)).query.get ==> List(
        (1, Set(Set(date1, date2))),
        (2, Set(Set(date2, date3))),
      )
    }


    "max" - cardSet { implicit futConn =>
      NsSet.n.dates.insert(List(
        (1, Set(date1, date2)),
        (2, Set(date2, date3)),
        (2, Set(date3, date4)),
        (2, Set(date3, date4)),
      )).transact

      NsSet.dates(max).query.get ==> List(Set(date4))
      NsSet.dates(max(1)).query.get ==> List(Set(Set(date4)))
      NsSet.dates(max(2)).query.get ==> List(Set(Set(date3, date4)))

      NsSet.n.dates(max).query.get ==> List(
        (1, Set(date2)),
        (2, Set(date4)),
      )
      NsSet.n.dates(max(1)).query.get ==> List(
        (1, Set(Set(date2))),
        (2, Set(Set(date4))),
      )
      NsSet.n.dates(max(2)).query.get ==> List(
        (1, Set(Set(date1, date2))),
        (2, Set(Set(date3, date4))),
      )
    }


    "rand" - cardSet { implicit conn =>
      NsSet.n.dates.insert(List(
        (1, Set(date1, date2)),
        (2, Set(date2, date3)),
        (2, Set(date3, date4)),
        (2, Set(date3, date4)),
      )).transact
      val all = Set(date1, date2, date3, date4)
      all.contains(NsSet.dates(rand).query.get.head.head) ==> true
      all.intersect(NsSet.dates(rand(1)).query.get.head.head).nonEmpty ==> true
      all.intersect(NsSet.dates(rand(2)).query.get.head.head).nonEmpty ==> true
    }


    "sample" - cardSet { implicit futConn =>
      NsSet.n.dates.insert(List(
        (1, Set(date1, date2)),
        (2, Set(date2, date3)),
        (2, Set(date3, date4)),
        (2, Set(date3, date4)),
      )).transact
      val all = Set(date1, date2, date3, date4)
      all.contains(NsSet.dates(sample).query.get.head.head) ==> true
      all.intersect(NsSet.dates(sample(1)).query.get.head.head).nonEmpty ==> true
      all.intersect(NsSet.dates(sample(2)).query.get.head.head).nonEmpty ==> true
    }


    "count, countDistinct" - cardSet { implicit conn =>
      NsSet.n.dates.insert(List(
        (1, Set(date1, date2)),
        (2, Set(date2, date3)),
        (2, Set(date3, date4)),
        (2, Set(date3, date4)),
      )).transact

      NsSet.n(count).query.get ==> List(4)
      NsSet.n(countDistinct).query.get ==> List(2)

      NsSet.dates(count).query.get ==> List(8)
      NsSet.dates(countDistinct).query.get ==> List(4)

      NsSet.n.a1.dates(count).query.get ==> List(
        (1, 2),
        (2, 6)
      )
      NsSet.n.a1.dates(countDistinct).query.get ==> List(
        (1, 2),
        (2, 3)
      )
    }
  }
}