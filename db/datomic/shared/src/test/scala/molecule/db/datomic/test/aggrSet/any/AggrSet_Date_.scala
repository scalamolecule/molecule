// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrSet.any


import java.util.Date
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSet_Date_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      Ns.n.dates.insert(List(
        (1, Set(date1, date2)),
        (2, Set(date2, date3)),
        (2, Set(date3, date4)),
        (2, Set(date3, date4)),
      )).transact

      // Non-aggregated card-many Set of attribute values coalesce
      Ns.n.a1.dates.query.get ==> List(
        (1, Set(date1, date2)),
        (2, Set(date2, date3, date4)), // 3 rows coalesced
      )

      // Use `distinct` keyword to retrieve unique Sets of values
      Ns.n.a1.dates(distinct).query.get ==> List(
        (1, Set(Set(date1, date2))),
        (2, Set(
          Set(date2, date3),
          Set(date3, date4) // 2 rows coalesced
        ))
      )

      Ns.dates(distinct).query.get ==> List(
        Set(
          Set(date1, date2),
          Set(date2, date3),
          Set(date3, date4),
        )
      )
    }


    "min" - types { implicit conn =>
      Ns.n.dates.insert(List(
        (1, Set(date1, date2)),
        (2, Set(date2, date3)),
        (2, Set(date3, date4)),
        (2, Set(date3, date4)),
      )).transact

      Ns.dates(min).query.get ==> List(Set(date1))
      Ns.dates(min(1)).query.get ==> List(Set(date1))
      Ns.dates(min(2)).query.get ==> List(Set(date1, date2))

      Ns.n.dates(min).query.get ==> List(
        (1, Set(date1)),
        (2, Set(date2)),
      )
      // Same as
      Ns.n.dates(min(1)).query.get ==> List(
        (1, Set(date1)),
        (2, Set(date2)),
      )

      Ns.n.dates(min(2)).query.get ==> List(
        (1, Set(date1, date2)),
        (2, Set(date2, date3)),
      )
    }


    "max" - types { implicit futConn =>
      Ns.n.dates.insert(List(
        (1, Set(date1, date2)),
        (2, Set(date2, date3)),
        (2, Set(date3, date4)),
        (2, Set(date3, date4)),
      )).transact

      Ns.dates(max).query.get ==> List(Set(date4))
      Ns.dates(max(1)).query.get ==> List(Set(date4))
      Ns.dates(max(2)).query.get ==> List(Set(date3, date4))

      Ns.n.dates(max).query.get ==> List(
        (1, Set(date2)),
        (2, Set(date4)),
      )
      // Same as
      Ns.n.dates(max(1)).query.get ==> List(
        (1, Set(date2)),
        (2, Set(date4)),
      )

      Ns.n.dates(max(2)).query.get ==> List(
        (1, Set(date1, date2)),
        (2, Set(date3, date4)),
      )
    }


    "rand" - types { implicit conn =>
      Ns.n.dates.insert(List(
        (1, Set(date1, date2)),
        (2, Set(date2, date3)),
        (2, Set(date3, date4)),
        (2, Set(date3, date4)),
      )).transact
      val all = Set(date1, date2, date3, date4)
      all.contains(Ns.dates(rand).query.get.head.head) ==> true
      all.intersect(Ns.dates(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.dates(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - types { implicit futConn =>
      Ns.n.dates.insert(List(
        (1, Set(date1, date2)),
        (2, Set(date2, date3)),
        (2, Set(date3, date4)),
        (2, Set(date3, date4)),
      )).transact
      val all = Set(date1, date2, date3, date4)
      all.contains(Ns.dates(sample).query.get.head.head) ==> true
      all.intersect(Ns.dates(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.dates(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - types { implicit conn =>
      Ns.n.dates.insert(List(
        (1, Set(date1, date2)),
        (2, Set(date2, date3)),
        (2, Set(date3, date4)),
        (2, Set(date3, date4)),
      )).transact

      Ns.n(count).query.get ==> List(4)
      Ns.n(countDistinct).query.get ==> List(2)

      Ns.dates(count).query.get ==> List(8)
      Ns.dates(countDistinct).query.get ==> List(4)

      Ns.n.a1.dates(count).query.get ==> List(
        (1, 2),
        (2, 6)
      )
      Ns.n.a1.dates(countDistinct).query.get ==> List(
        (1, 2),
        (2, 3)
      )
    }
  }
}