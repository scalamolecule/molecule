// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrSet.any


import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSet_String_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      Ns.n.strings.insert(List(
        (1, Set(string1, string2)),
        (2, Set(string2, string3)),
        (2, Set(string3, string4)),
        (2, Set(string3, string4)),
      )).transact

      // Non-aggregated card-many Set of attribute values coalesce
      Ns.n.a1.strings.query.get ==> List(
        (1, Set(string1, string2)),
        (2, Set(string2, string3, string4)), // 3 rows coalesced
      )

      // Use `distinct` keyword to retrieve unique Sets of values
      Ns.n.a1.strings(distinct).query.get ==> List(
        (1, Set(Set(string1, string2))),
        (2, Set(
          Set(string2, string3),
          Set(string3, string4) // 2 rows coalesced
        ))
      )

      Ns.strings(distinct).query.get ==> List(
        Set(
          Set(string1, string2),
          Set(string2, string3),
          Set(string3, string4),
        )
      )
    }


    "min" - types { implicit conn =>
      Ns.n.strings.insert(List(
        (1, Set(string1, string2)),
        (2, Set(string2, string3)),
        (2, Set(string3, string4)),
        (2, Set(string3, string4)),
      )).transact

      Ns.strings(min).query.get ==> List(Set(string1))
      Ns.strings(min(1)).query.get ==> List(Set(string1))
      Ns.strings(min(2)).query.get ==> List(Set(string1, string2))

      Ns.n.strings(min).query.get ==> List(
        (1, Set(string1)),
        (2, Set(string2)),
      )
      // Same as
      Ns.n.strings(min(1)).query.get ==> List(
        (1, Set(string1)),
        (2, Set(string2)),
      )

      Ns.n.strings(min(2)).query.get ==> List(
        (1, Set(string1, string2)),
        (2, Set(string2, string3)),
      )
    }


    "max" - types { implicit futConn =>
      Ns.n.strings.insert(List(
        (1, Set(string1, string2)),
        (2, Set(string2, string3)),
        (2, Set(string3, string4)),
        (2, Set(string3, string4)),
      )).transact

      Ns.strings(max).query.get ==> List(Set(string4))
      Ns.strings(max(1)).query.get ==> List(Set(string4))
      Ns.strings(max(2)).query.get ==> List(Set(string3, string4))

      Ns.n.strings(max).query.get ==> List(
        (1, Set(string2)),
        (2, Set(string4)),
      )
      // Same as
      Ns.n.strings(max(1)).query.get ==> List(
        (1, Set(string2)),
        (2, Set(string4)),
      )

      Ns.n.strings(max(2)).query.get ==> List(
        (1, Set(string1, string2)),
        (2, Set(string3, string4)),
      )
    }


    "rand" - types { implicit conn =>
      Ns.n.strings.insert(List(
        (1, Set(string1, string2)),
        (2, Set(string2, string3)),
        (2, Set(string3, string4)),
        (2, Set(string3, string4)),
      )).transact
      val all = Set(string1, string2, string3, string4)
      all.contains(Ns.strings(rand).query.get.head.head) ==> true
      all.intersect(Ns.strings(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.strings(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - types { implicit futConn =>
      Ns.n.strings.insert(List(
        (1, Set(string1, string2)),
        (2, Set(string2, string3)),
        (2, Set(string3, string4)),
        (2, Set(string3, string4)),
      )).transact
      val all = Set(string1, string2, string3, string4)
      all.contains(Ns.strings(sample).query.get.head.head) ==> true
      all.intersect(Ns.strings(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.strings(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - types { implicit conn =>
      Ns.n.strings.insert(List(
        (1, Set(string1, string2)),
        (2, Set(string2, string3)),
        (2, Set(string3, string4)),
        (2, Set(string3, string4)),
      )).transact

      Ns.n(count).query.get ==> List(4)
      Ns.n(countDistinct).query.get ==> List(2)

      Ns.strings(count).query.get ==> List(8)
      Ns.strings(countDistinct).query.get ==> List(4)

      Ns.n.a1.strings(count).query.get ==> List(
        (1, 2),
        (2, 6)
      )
      Ns.n.a1.strings(countDistinct).query.get ==> List(
        (1, 2),
        (2, 3)
      )
    }
  }
}