// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrSet.any


import java.net.URI
import molecule.coreTests.dataModels.core.types.dsl.CardSet._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSet_URI_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - cardSet { implicit conn =>
      NsSet.n.uris.insert(List(
        (1, Set(uri1, uri2)),
        (2, Set(uri2, uri3)),
        (2, Set(uri3, uri4)),
        (2, Set(uri3, uri4)),
      )).transact

      // Non-aggregated card-many Set of attribute values coalesce
      NsSet.n.a1.uris.query.get ==> List(
        (1, Set(uri1, uri2)),
        (2, Set(uri2, uri3, uri4)), // 3 rows coalesced
      )

      // Use `distinct` keyword to retrieve unique Sets of values
      NsSet.n.a1.uris(distinct).query.get ==> List(
        (1, Set(Set(uri1, uri2))),
        (2, Set(
          Set(uri2, uri3),
          Set(uri3, uri4) // 2 rows coalesced
        ))
      )

      NsSet.uris(distinct).query.get ==> List(
        Set(
          Set(uri1, uri2),
          Set(uri2, uri3),
          Set(uri3, uri4),
        )
      )
    }


    "min" - cardSet { implicit conn =>
      NsSet.n.uris.insert(List(
        (1, Set(uri1, uri2)),
        (2, Set(uri2, uri3)),
        (2, Set(uri3, uri4)),
        (2, Set(uri3, uri4)),
      )).transact

      NsSet.uris(min).query.get ==> List(Set(uri1))
      NsSet.uris(min(1)).query.get ==> List(Set(uri1))
      NsSet.uris(min(2)).query.get ==> List(Set(uri1, uri2))

      NsSet.n.uris(min).query.get ==> List(
        (1, Set(uri1)),
        (2, Set(uri2)),
      )
      // Same as
      NsSet.n.uris(min(1)).query.get ==> List(
        (1, Set(uri1)),
        (2, Set(uri2)),
      )

      NsSet.n.uris(min(2)).query.get ==> List(
        (1, Set(uri1, uri2)),
        (2, Set(uri2, uri3)),
      )
    }


    "max" - cardSet { implicit futConn =>
      NsSet.n.uris.insert(List(
        (1, Set(uri1, uri2)),
        (2, Set(uri2, uri3)),
        (2, Set(uri3, uri4)),
        (2, Set(uri3, uri4)),
      )).transact

      NsSet.uris(max).query.get ==> List(Set(uri4))
      NsSet.uris(max(1)).query.get ==> List(Set(uri4))
      NsSet.uris(max(2)).query.get ==> List(Set(uri3, uri4))

      NsSet.n.uris(max).query.get ==> List(
        (1, Set(uri2)),
        (2, Set(uri4)),
      )
      // Same as
      NsSet.n.uris(max(1)).query.get ==> List(
        (1, Set(uri2)),
        (2, Set(uri4)),
      )

      NsSet.n.uris(max(2)).query.get ==> List(
        (1, Set(uri1, uri2)),
        (2, Set(uri3, uri4)),
      )
    }


    "rand" - cardSet { implicit conn =>
      NsSet.n.uris.insert(List(
        (1, Set(uri1, uri2)),
        (2, Set(uri2, uri3)),
        (2, Set(uri3, uri4)),
        (2, Set(uri3, uri4)),
      )).transact
      val all = Set(uri1, uri2, uri3, uri4)
      all.contains(NsSet.uris(rand).query.get.head.head) ==> true
      all.intersect(NsSet.uris(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsSet.uris(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - cardSet { implicit futConn =>
      NsSet.n.uris.insert(List(
        (1, Set(uri1, uri2)),
        (2, Set(uri2, uri3)),
        (2, Set(uri3, uri4)),
        (2, Set(uri3, uri4)),
      )).transact
      val all = Set(uri1, uri2, uri3, uri4)
      all.contains(NsSet.uris(sample).query.get.head.head) ==> true
      all.intersect(NsSet.uris(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsSet.uris(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - cardSet { implicit conn =>
      NsSet.n.uris.insert(List(
        (1, Set(uri1, uri2)),
        (2, Set(uri2, uri3)),
        (2, Set(uri3, uri4)),
        (2, Set(uri3, uri4)),
      )).transact

      NsSet.n(count).query.get ==> List(4)
      NsSet.n(countDistinct).query.get ==> List(2)

      NsSet.uris(count).query.get ==> List(8)
      NsSet.uris(countDistinct).query.get ==> List(4)

      NsSet.n.a1.uris(count).query.get ==> List(
        (1, 2),
        (2, 6)
      )
      NsSet.n.a1.uris(countDistinct).query.get ==> List(
        (1, 2),
        (2, 3)
      )
    }
  }
}