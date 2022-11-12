// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrSet.any


import molecule.coreTests.dataModels.core.types.dsl.TypesSet._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSet_String_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - typesSet { implicit conn =>
      NsSet.n.strings.insert(List(
        (1, Set(string1, string2)),
        (2, Set(string2, string3)),
        (2, Set(string3, string4)),
        (2, Set(string3, string4)),
      )).transact

      // Non-aggregated card-many Set of attribute values coalesce
      NsSet.n.a1.strings.query.get ==> List(
        (1, Set(string1, string2)),
        (2, Set(string2, string3, string4)), // 3 rows coalesced
      )

      // Use `distinct` keyword to retrieve unique Sets of values
      NsSet.n.a1.strings(distinct).query.get ==> List(
        (1, Set(Set(string1, string2))),
        (2, Set(
          Set(string2, string3),
          Set(string3, string4) // 2 rows coalesced
        ))
      )

      NsSet.strings(distinct).query.get ==> List(
        Set(
          Set(string1, string2),
          Set(string2, string3),
          Set(string3, string4),
        )
      )
    }


    "min" - typesSet { implicit conn =>
      NsSet.n.strings.insert(List(
        (1, Set(string1, string2)),
        (2, Set(string2, string3)),
        (2, Set(string3, string4)),
        (2, Set(string3, string4)),
      )).transact

      NsSet.strings(min).query.get ==> List(Set(string1))
      NsSet.strings(min(1)).query.get ==> List(Set(string1))
      NsSet.strings(min(2)).query.get ==> List(Set(string1, string2))

      NsSet.n.strings(min).query.get ==> List(
        (1, Set(string1)),
        (2, Set(string2)),
      )
      // Same as
      NsSet.n.strings(min(1)).query.get ==> List(
        (1, Set(string1)),
        (2, Set(string2)),
      )

      NsSet.n.strings(min(2)).query.get ==> List(
        (1, Set(string1, string2)),
        (2, Set(string2, string3)),
      )
    }


    "max" - typesSet { implicit futConn =>
      NsSet.n.strings.insert(List(
        (1, Set(string1, string2)),
        (2, Set(string2, string3)),
        (2, Set(string3, string4)),
        (2, Set(string3, string4)),
      )).transact

      NsSet.strings(max).query.get ==> List(Set(string4))
      NsSet.strings(max(1)).query.get ==> List(Set(string4))
      NsSet.strings(max(2)).query.get ==> List(Set(string3, string4))

      NsSet.n.strings(max).query.get ==> List(
        (1, Set(string2)),
        (2, Set(string4)),
      )
      // Same as
      NsSet.n.strings(max(1)).query.get ==> List(
        (1, Set(string2)),
        (2, Set(string4)),
      )

      NsSet.n.strings(max(2)).query.get ==> List(
        (1, Set(string1, string2)),
        (2, Set(string3, string4)),
      )
    }


    "rand" - typesSet { implicit conn =>
      NsSet.n.strings.insert(List(
        (1, Set(string1, string2)),
        (2, Set(string2, string3)),
        (2, Set(string3, string4)),
        (2, Set(string3, string4)),
      )).transact
      val all = Set(string1, string2, string3, string4)
      all.contains(NsSet.strings(rand).query.get.head.head) ==> true
      all.intersect(NsSet.strings(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsSet.strings(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - typesSet { implicit futConn =>
      NsSet.n.strings.insert(List(
        (1, Set(string1, string2)),
        (2, Set(string2, string3)),
        (2, Set(string3, string4)),
        (2, Set(string3, string4)),
      )).transact
      val all = Set(string1, string2, string3, string4)
      all.contains(NsSet.strings(sample).query.get.head.head) ==> true
      all.intersect(NsSet.strings(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsSet.strings(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - typesSet { implicit conn =>
      NsSet.n.strings.insert(List(
        (1, Set(string1, string2)),
        (2, Set(string2, string3)),
        (2, Set(string3, string4)),
        (2, Set(string3, string4)),
      )).transact

      NsSet.n(count).query.get ==> List(4)
      NsSet.n(countDistinct).query.get ==> List(2)

      NsSet.strings(count).query.get ==> List(8)
      NsSet.strings(countDistinct).query.get ==> List(4)

      NsSet.n.a1.strings(count).query.get ==> List(
        (1, 2),
        (2, 6)
      )
      NsSet.n.a1.strings(countDistinct).query.get ==> List(
        (1, 2),
        (2, 3)
      )
    }
  }
}