// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrSet.any


import java.net.URI
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSet_URI_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      Ns.i.uris.insert(List(
        (1, Set(uri1, uri2)),
        (2, Set(uri2, uri3)),
        (2, Set(uri3, uri4)),
        (2, Set(uri3, uri4)),
      )).transact

      // Non-aggregated card-many Set of attribute values coalesce
      Ns.i.a1.uris.query.get ==> List(
        (1, Set(uri1, uri2)),
        (2, Set(uri2, uri3, uri4)), // 3 rows coalesced
      )

      // Use `distinct` keyword to retrieve unique Sets of values
      Ns.i.a1.uris(distinct).query.get ==> List(
        (1, Set(Set(uri1, uri2))),
        (2, Set(
          Set(uri2, uri3),
          Set(uri3, uri4) // 2 rows coalesced
        ))
      )

      Ns.uris(distinct).query.get ==> List(
        Set(
          Set(uri1, uri2),
          Set(uri2, uri3),
          Set(uri3, uri4),
        )
      )
    }


    "min" - types { implicit conn =>
      Ns.i.uris.insert(List(
        (1, Set(uri1, uri2)),
        (2, Set(uri2, uri3)),
        (2, Set(uri3, uri4)),
        (2, Set(uri3, uri4)),
      )).transact

      Ns.uris(min).query.get ==> List(Set(uri1))
      Ns.uris(min(1)).query.get ==> List(Set(uri1))
      Ns.uris(min(2)).query.get ==> List(Set(uri1, uri2))

      Ns.i.uris(min).query.get ==> List(
        (1, Set(uri1)),
        (2, Set(uri2)),
      )
      // Same as
      Ns.i.uris(min(1)).query.get ==> List(
        (1, Set(uri1)),
        (2, Set(uri2)),
      )

      Ns.i.uris(min(2)).query.get ==> List(
        (1, Set(uri1, uri2)),
        (2, Set(uri2, uri3)),
      )
    }


    "max" - types { implicit futConn =>
      Ns.i.uris.insert(List(
        (1, Set(uri1, uri2)),
        (2, Set(uri2, uri3)),
        (2, Set(uri3, uri4)),
        (2, Set(uri3, uri4)),
      )).transact

      Ns.uris(max).query.get ==> List(Set(uri4))
      Ns.uris(max(1)).query.get ==> List(Set(uri4))
      Ns.uris(max(2)).query.get ==> List(Set(uri3, uri4))

      Ns.i.uris(max).query.get ==> List(
        (1, Set(uri2)),
        (2, Set(uri4)),
      )
      // Same as
      Ns.i.uris(max(1)).query.get ==> List(
        (1, Set(uri2)),
        (2, Set(uri4)),
      )

      Ns.i.uris(max(2)).query.get ==> List(
        (1, Set(uri1, uri2)),
        (2, Set(uri3, uri4)),
      )
    }


    "rand" - types { implicit conn =>
      Ns.i.uris.insert(List(
        (1, Set(uri1, uri2)),
        (2, Set(uri2, uri3)),
        (2, Set(uri3, uri4)),
        (2, Set(uri3, uri4)),
      )).transact
      val all = Set(uri1, uri2, uri3, uri4)
      all.contains(Ns.uris(rand).query.get.head.head) ==> true
      all.intersect(Ns.uris(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.uris(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - types { implicit futConn =>
      Ns.i.uris.insert(List(
        (1, Set(uri1, uri2)),
        (2, Set(uri2, uri3)),
        (2, Set(uri3, uri4)),
        (2, Set(uri3, uri4)),
      )).transact
      val all = Set(uri1, uri2, uri3, uri4)
      all.contains(Ns.uris(sample).query.get.head.head) ==> true
      all.intersect(Ns.uris(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.uris(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - types { implicit conn =>
      Ns.i.uris.insert(List(
        (1, Set(uri1, uri2)),
        (2, Set(uri2, uri3)),
        (2, Set(uri3, uri4)),
        (2, Set(uri3, uri4)),
      )).transact

      Ns.i(count).query.get ==> List(4)
      Ns.i(countDistinct).query.get ==> List(2)

      Ns.uris(count).query.get ==> List(8)
      Ns.uris(countDistinct).query.get ==> List(4)

      Ns.i.a1.uris(count).query.get ==> List(
        (1, 2),
        (2, 6)
      )
      Ns.i.a1.uris(countDistinct).query.get ==> List(
        (1, 2),
        (2, 3)
      )
    }
  }
}