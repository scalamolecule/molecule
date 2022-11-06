// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.any


import java.net.URI
import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_URI_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - cardOne { implicit conn =>
      NsOne.n.uri.insert(List(
        (1, uri1),
        (2, uri2),
        (2, uri2),
        (2, uri3),
      )).transact

      NsOne.n.a1.uri.query.get.sortBy(_._2) ==> List(
        (1, uri1),
        (2, uri2), // 2 rows coalesced
        (2, uri3),
      )

      // Distinct values are returned in a List
      NsOne.n.a1.uri.apply(distinct).query.get ==> List(
        (1, Set(uri1)),
        (2, Set(uri2, uri3)),
      )

      NsOne.uri(distinct).query.get.head ==> Set(
        uri1, uri2, uri3
      )
    }


    "min" - cardOne { implicit conn =>
      NsOne.uri.insert(List(uri1, uri2, uri3)).transact
      NsOne.uri(min).query.get.head ==> uri1
      NsOne.uri(min(1)).query.get.head ==> Set(uri1)
      NsOne.uri(min(2)).query.get.head ==> Set(uri1, uri2)
    }


    "max" - cardOne { implicit futConn =>
      NsOne.uri.insert(List(uri1, uri2, uri3)).transact
      NsOne.uri(max).query.get.head ==> uri3
      NsOne.uri(max(1)).query.get.head ==> Set(uri3)
      NsOne.uri(max(2)).query.get.head ==> Set(uri3, uri2)
    }


    "rand" - cardOne { implicit conn =>
      NsOne.uri.insert(List(uri1, uri2, uri3)).transact
      val all = Set(uri1, uri2, uri3, uri4)
      all.contains(NsOne.uri.apply(rand).query.get.head) ==> true
      all.intersect(NsOne.uri(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsOne.uri(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - cardOne { implicit futConn =>
      NsOne.uri.insert(List(uri1, uri2, uri3)).transact
      val all = Set(uri1, uri2, uri3, uri4)
      all.contains(NsOne.uri(sample).query.get.head) ==> true
      all.intersect(NsOne.uri(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsOne.uri(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - cardOne { implicit conn =>
      NsOne.n.uri.insert(List(
        (1, uri1),
        (2, uri2),
        (2, uri2),
        (2, uri3),
      )).transact

      NsOne.n(count).query.get ==> List(4)
      NsOne.n(countDistinct).query.get ==> List(2)

      NsOne.uri(count).query.get ==> List(4)
      NsOne.uri(countDistinct).query.get ==> List(3)

      NsOne.n.a1.uri(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      NsOne.n.a1.uri(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}