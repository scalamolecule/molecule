// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrSet.any


import molecule.coreTests.dataModels.core.types.dsl.CardSet._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSet_Char_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - cardSet { implicit conn =>
      NsSet.n.chars.insert(List(
        (1, Set(char1, char2)),
        (2, Set(char2, char3)),
        (2, Set(char3, char4)),
        (2, Set(char3, char4)),
      )).transact

      // Non-aggregated card-many Set of attribute values coalesce
      NsSet.n.a1.chars.query.get ==> List(
        (1, Set(char1, char2)),
        (2, Set(char2, char3, char4)), // 3 rows coalesced
      )

      // Use `distinct` keyword to retrieve unique Sets of values
      NsSet.n.a1.chars(distinct).query.get ==> List(
        (1, Set(Set(char1, char2))),
        (2, Set(
          Set(char2, char3),
          Set(char3, char4) // 2 rows coalesced
        ))
      )

      NsSet.chars.apply(distinct).query.get ==> List(
        Set(
          Set(char1, char2),
          Set(char2, char3),
          Set(char3, char4),
        )
      )
    }


    "min" - cardSet { implicit conn =>
      NsSet.n.chars.insert(List(
        (1, Set(char1, char2)),
        (2, Set(char2, char3)),
        (2, Set(char3, char4)),
        (2, Set(char3, char4)),
      )).transact

      NsSet.chars(min).query.get ==> List(Set(char1))
      NsSet.chars(min(1)).query.get ==> List(Set(Set(char1)))
      NsSet.chars(min(2)).query.get ==> List(Set(Set(char1, char2)))

      NsSet.n.chars(min).query.get ==> List(
        (1, Set(char1)),
        (2, Set(char2)),
      )
      NsSet.n.chars(min(1)).query.get ==> List(
        (1, Set(Set(char1))),
        (2, Set(Set(char2))),
      )
      NsSet.n.chars(min(2)).query.get ==> List(
        (1, Set(Set(char1, char2))),
        (2, Set(Set(char2, char3))),
      )
    }


    "max" - cardSet { implicit futConn =>
      NsSet.n.chars.insert(List(
        (1, Set(char1, char2)),
        (2, Set(char2, char3)),
        (2, Set(char3, char4)),
        (2, Set(char3, char4)),
      )).transact

      NsSet.chars(max).query.get ==> List(Set(char4))
      NsSet.chars(max(1)).query.get ==> List(Set(Set(char4)))
      NsSet.chars(max(2)).query.get ==> List(Set(Set(char3, char4)))

      NsSet.n.chars(max).query.get ==> List(
        (1, Set(char2)),
        (2, Set(char4)),
      )
      NsSet.n.chars(max(1)).query.get ==> List(
        (1, Set(Set(char2))),
        (2, Set(Set(char4))),
      )
      NsSet.n.chars(max(2)).query.get ==> List(
        (1, Set(Set(char1, char2))),
        (2, Set(Set(char3, char4))),
      )
    }


    "rand" - cardSet { implicit conn =>
      NsSet.n.chars.insert(List(
        (1, Set(char1, char2)),
        (2, Set(char2, char3)),
        (2, Set(char3, char4)),
        (2, Set(char3, char4)),
      )).transact
      val all = Set(char1, char2, char3, char4)
      all.contains(NsSet.chars(rand).query.get.head.head) ==> true
      all.intersect(NsSet.chars(rand(1)).query.get.head.head).nonEmpty ==> true
      all.intersect(NsSet.chars(rand(2)).query.get.head.head).nonEmpty ==> true
    }


    "sample" - cardSet { implicit futConn =>
      NsSet.n.chars.insert(List(
        (1, Set(char1, char2)),
        (2, Set(char2, char3)),
        (2, Set(char3, char4)),
        (2, Set(char3, char4)),
      )).transact
      val all = Set(char1, char2, char3, char4)
      all.contains(NsSet.chars(sample).query.get.head.head) ==> true
      all.intersect(NsSet.chars(sample(1)).query.get.head.head).nonEmpty ==> true
      all.intersect(NsSet.chars(sample(2)).query.get.head.head).nonEmpty ==> true
    }


    "count, countDistinct" - cardSet { implicit conn =>
      NsSet.n.chars.insert(List(
        (1, Set(char1, char2)),
        (2, Set(char2, char3)),
        (2, Set(char3, char4)),
        (2, Set(char3, char4)),
      )).transact

      NsSet.n(count).query.get ==> List(4)
      NsSet.n(countDistinct).query.get ==> List(2)

      NsSet.chars(count).query.get ==> List(8)
      NsSet.chars(countDistinct).query.get ==> List(4)

      NsSet.n.a1.chars(count).query.get ==> List(
        (1, 2),
        (2, 6)
      )
      NsSet.n.a1.chars(countDistinct).query.get ==> List(
        (1, 2),
        (2, 3)
      )
    }
  }
}