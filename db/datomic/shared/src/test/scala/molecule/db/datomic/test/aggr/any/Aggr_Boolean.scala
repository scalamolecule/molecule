// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.any

import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object Aggr_Boolean extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - cardOne { implicit conn =>
      One.n.boolean.insert(List(
        (1, true),
        (2, false),
        (2, false),
        (2, true),
      )).transact

      // Distinct values are returned in a List
      One.boolean.apply(distinct).query.get.head.sorted ==> List(false, true)
      One.n.a1.boolean(distinct).query.get.map(r => (r._1, r._2.sorted)) ==> List(
        (1, List(true)),
        (2, List(false, true)),
      )
    }


    "min" - cardOne { implicit conn =>
      One.boolean.insert(List(true, false, true)).transact
      One.boolean.apply(min).query.get.head ==> false
      One.boolean(min(1)).query.get.head ==> List(false)
      One.boolean(min(2)).query.get.head ==> List(false, true)
    }


    "max" - cardOne { implicit futConn =>
      One.boolean.insert(List(true, false, true)).transact
      One.boolean(max).query.get.head ==> true
      One.boolean(max(1)).query.get.head ==> List(true)
      One.boolean(max(2)).query.get.head ==> List(true, false)
    }


    "rand" - cardOne { implicit conn =>
      One.boolean.insert(List(true, false, true)).transact
      val all = Seq(true, false, true, boolean4)
      all.contains(One.boolean(rand).query.get.head) ==> true
      all.intersect(One.boolean(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(One.boolean(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - cardOne { implicit futConn =>
      One.boolean.insert(List(true, false, true)).transact
      val all = Seq(true, false, true, boolean4)
      all.contains(One.boolean(sample).query.get.head) ==> true
      all.intersect(One.boolean(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(One.boolean(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - cardOne { implicit conn =>
      One.n.boolean.insert(List(
        (1, true),
        (2, false),
        (2, false),
        (2, true),
      )).transact

      One.n(count).query.get ==> List(4)
      One.n(countDistinct).query.get ==> List(2)

      One.boolean(count).query.get ==> List(4)
      One.boolean(countDistinct).query.get ==> List(2)

      One.n.a1.boolean(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      One.n.a1.boolean(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}