// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.any

import java.net.URI
import molecule.boilerplate.api.Keywords._
import molecule.boilerplate.api.Keywords._
import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object Aggr_URI_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - cardOne { implicit conn =>
      One.n.uri.insert(List(
        (1, uri1),
        (2, uri2),
        (2, uri2),
        (2, uri3),
      )).transact

      // Distinct values are returned in a List
      One.uri.apply(distinct).query.get.head.sorted ==> List(uri1, uri2, uri3)
      One.n.a1.uri(distinct).query.get.map(r => (r._1, r._2.sorted)) ==> List(
        (1, List(uri1)),
        (2, List(uri2, uri3)),
      )
    }


    "min" - cardOne { implicit conn =>
      One.uri.insert(List(uri1, uri2, uri3)).transact
      One.uri.apply(min).query.get.head ==> uri1
      One.uri(min(1)).query.get.head ==> List(uri1)
      One.uri(min(2)).query.get.head ==> List(uri1, uri2)
    }


    "max" - cardOne { implicit futConn =>
      One.uri.insert(List(uri1, uri2, uri3)).transact
      One.uri(max).query.get.head ==> uri3
      One.uri(max(1)).query.get.head ==> List(uri3)
      One.uri(max(2)).query.get.head ==> List(uri3, uri2)
    }


    "rand" - cardOne { implicit conn =>
      One.uri.insert(List(uri1, uri2, uri3)).transact
      val all = Seq(uri1, uri2, uri3, uri4)
      all.contains(One.uri(rand).query.get.head) ==> true
      all.intersect(One.uri(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(One.uri(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - cardOne { implicit futConn =>
      One.uri.insert(List(uri1, uri2, uri3)).transact
      val all = Seq(uri1, uri2, uri3, uri4)
      all.contains(One.uri(sample).query.get.head) ==> true
      all.intersect(One.uri(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(One.uri(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - cardOne { implicit conn =>
      One.n.uri.insert(List(
        (1, uri1),
        (2, uri2),
        (2, uri2),
        (2, uri3),
      )).transact

      One.n(count).query.get ==> List(4)
      One.n(countDistinct).query.get ==> List(2)

      One.uri(count).query.get ==> List(4)
      One.uri(countDistinct).query.get ==> List(3)

      One.n.a1.uri(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      One.n.a1.uri(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}