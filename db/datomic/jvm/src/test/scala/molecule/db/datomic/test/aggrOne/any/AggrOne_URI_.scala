// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.any


import java.net.URI
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_URI_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      Ns.i.uri.insert(List(
        (1, uri1),
        (2, uri2),
        (2, uri2),
        (2, uri3),
      )).transact

      Ns.i.a1.uri.query.get.sortBy(_._2) ==> List(
        (1, uri1),
        (2, uri2), // 2 rows coalesced
        (2, uri3),
      )

      // Distinct values are returned in a List
      Ns.i.a1.uri.apply(distinct).query.get ==> List(
        (1, Set(uri1)),
        (2, Set(uri2, uri3)),
      )

      Ns.uri(distinct).query.get.head ==> Set(
        uri1, uri2, uri3
      )
    }


    "min" - types { implicit conn =>
      Ns.uri.insert(List(uri1, uri2, uri3)).transact
      Ns.uri(min).query.get ==> List(uri1)
      Ns.uri(min(1)).query.get ==> List(Set(uri1))
      Ns.uri(min(2)).query.get ==> List(Set(uri1, uri2))
    }


    "max" - types { implicit futConn =>
      Ns.uri.insert(List(uri1, uri2, uri3)).transact
      Ns.uri(max).query.get ==> List(uri3)
      Ns.uri(max(1)).query.get ==> List(Set(uri3))
      Ns.uri(max(2)).query.get ==> List(Set(uri3, uri2))
    }


    "rand" - types { implicit conn =>
      Ns.uri.insert(List(uri1, uri2, uri3)).transact
      val all = Set(uri1, uri2, uri3, uri4)
      all.contains(Ns.uri.apply(rand).query.get.head) ==> true
      all.intersect(Ns.uri(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.uri(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - types { implicit futConn =>
      Ns.uri.insert(List(uri1, uri2, uri3)).transact
      val all = Set(uri1, uri2, uri3, uri4)
      all.contains(Ns.uri(sample).query.get.head) ==> true
      all.intersect(Ns.uri(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.uri(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - types { implicit conn =>
      Ns.i.uri.insert(List(
        (1, uri1),
        (2, uri2),
        (2, uri2),
        (2, uri3),
      )).transact

      Ns.i(count).query.get ==> List(4)
      Ns.i(countDistinct).query.get ==> List(2)

      Ns.uri(count).query.get ==> List(4)
      Ns.uri(countDistinct).query.get ==> List(3)

      Ns.i.a1.uri(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      Ns.i.a1.uri(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}