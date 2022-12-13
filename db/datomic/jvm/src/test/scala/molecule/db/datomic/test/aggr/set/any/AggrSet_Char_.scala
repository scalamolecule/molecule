// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.set.any

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSet_Char_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      Ns.i.chars.insert(List(
        (1, Set(char1, char2)),
        (2, Set(char2, char3)),
        (2, Set(char3, char4)),
        (2, Set(char3, char4)),
      )).transact

      // Non-aggregated card-many Set of attribute values coalesce
      Ns.i.a1.chars.query.get ==> List(
        (1, Set(char1, char2)),
        (2, Set(char2, char3, char4)), // 3 rows coalesced
      )

      // Use `distinct` keyword to retrieve unique Sets of values
      Ns.i.a1.chars(distinct).query.get ==> List(
        (1, Set(Set(char1, char2))),
        (2, Set(
          Set(char2, char3),
          Set(char3, char4) // 2 rows coalesced
        ))
      )

      Ns.chars(distinct).query.get ==> List(
        Set(
          Set(char1, char2),
          Set(char2, char3),
          Set(char3, char4),
        )
      )
    }


    "min" - types { implicit conn =>
      Ns.i.chars.insert(List(
        (1, Set(char1, char2)),
        (2, Set(char2, char3)),
        (2, Set(char3, char4)),
        (2, Set(char3, char4)),
      )).transact

      Ns.chars(min).query.get ==> List(Set(char1))
      Ns.chars(min(1)).query.get ==> List(Set(char1))
      Ns.chars(min(2)).query.get ==> List(Set(char1, char2))

      Ns.i.chars(min).query.get ==> List(
        (1, Set(char1)),
        (2, Set(char2)),
      )
      // Same as
      Ns.i.chars(min(1)).query.get ==> List(
        (1, Set(char1)),
        (2, Set(char2)),
      )

      Ns.i.chars(min(2)).query.get ==> List(
        (1, Set(char1, char2)),
        (2, Set(char2, char3)),
      )
    }


    "max" - types { implicit futConn =>
      Ns.i.chars.insert(List(
        (1, Set(char1, char2)),
        (2, Set(char2, char3)),
        (2, Set(char3, char4)),
        (2, Set(char3, char4)),
      )).transact

      Ns.chars(max).query.get ==> List(Set(char4))
      Ns.chars(max(1)).query.get ==> List(Set(char4))
      Ns.chars(max(2)).query.get ==> List(Set(char3, char4))

      Ns.i.chars(max).query.get ==> List(
        (1, Set(char2)),
        (2, Set(char4)),
      )
      // Same as
      Ns.i.chars(max(1)).query.get ==> List(
        (1, Set(char2)),
        (2, Set(char4)),
      )

      Ns.i.chars(max(2)).query.get ==> List(
        (1, Set(char1, char2)),
        (2, Set(char3, char4)),
      )
    }


    "rand" - types { implicit conn =>
      Ns.i.chars.insert(List(
        (1, Set(char1, char2)),
        (2, Set(char2, char3)),
        (2, Set(char3, char4)),
        (2, Set(char3, char4)),
      )).transact
      val all = Set(char1, char2, char3, char4)
      all.contains(Ns.chars(rand).query.get.head.head) ==> true
      all.intersect(Ns.chars(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.chars(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - types { implicit futConn =>
      Ns.i.chars.insert(List(
        (1, Set(char1, char2)),
        (2, Set(char2, char3)),
        (2, Set(char3, char4)),
        (2, Set(char3, char4)),
      )).transact
      val all = Set(char1, char2, char3, char4)
      all.contains(Ns.chars(sample).query.get.head.head) ==> true
      all.intersect(Ns.chars(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.chars(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - types { implicit conn =>
      Ns.i.chars.insert(List(
        (1, Set(char1, char2)),
        (2, Set(char2, char3)),
        (2, Set(char3, char4)),
        (2, Set(char3, char4)),
      )).transact

      Ns.i(count).query.get ==> List(4)
      Ns.i(countDistinct).query.get ==> List(2)

      Ns.chars(count).query.get ==> List(8)
      Ns.chars(countDistinct).query.get ==> List(4)

      Ns.i.a1.chars(count).query.get ==> List(
        (1, 2),
        (2, 6)
      )
      Ns.i.a1.chars(countDistinct).query.get ==> List(
        (1, 2),
        (2, 3)
      )
    }
  }
}