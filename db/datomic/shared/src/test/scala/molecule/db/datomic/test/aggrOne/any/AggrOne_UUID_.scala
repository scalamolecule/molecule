// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.any

import java.util.UUID
import molecule.boilerplate.api.Keywords._
import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_UUID_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - cardOne { implicit conn =>
      One.n.uuid.insert(List(
        (1, uuid1),
        (2, uuid2),
        (2, uuid2),
        (2, uuid3),
      )).transact

      // Distinct values are returned in a List
      One.uuid.apply(distinct).query.get.head.sorted ==> List(uuid1, uuid2, uuid3)
      One.n.a1.uuid(distinct).query.get.map(r => (r._1, r._2.sorted)) ==> List(
        (1, List(uuid1)),
        (2, List(uuid2, uuid3)),
      )
    }


    "min" - cardOne { implicit conn =>
      One.uuid.insert(List(uuid1, uuid2, uuid3)).transact
      One.uuid.apply(min).query.get.head ==> uuid1
      One.uuid(min(1)).query.get.head ==> List(uuid1)
      One.uuid(min(2)).query.get.head ==> List(uuid1, uuid2)
    }


    "max" - cardOne { implicit futConn =>
      One.uuid.insert(List(uuid1, uuid2, uuid3)).transact
      One.uuid(max).query.get.head ==> uuid3
      One.uuid(max(1)).query.get.head ==> List(uuid3)
      One.uuid(max(2)).query.get.head ==> List(uuid3, uuid2)
    }


    "rand" - cardOne { implicit conn =>
      One.uuid.insert(List(uuid1, uuid2, uuid3)).transact
      val all = Seq(uuid1, uuid2, uuid3, uuid4)
      all.contains(One.uuid(rand).query.get.head) ==> true
      all.intersect(One.uuid(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(One.uuid(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - cardOne { implicit futConn =>
      One.uuid.insert(List(uuid1, uuid2, uuid3)).transact
      val all = Seq(uuid1, uuid2, uuid3, uuid4)
      all.contains(One.uuid(sample).query.get.head) ==> true
      all.intersect(One.uuid(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(One.uuid(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - cardOne { implicit conn =>
      One.n.uuid.insert(List(
        (1, uuid1),
        (2, uuid2),
        (2, uuid2),
        (2, uuid3),
      )).transact

      One.n(count).query.get ==> List(4)
      One.n(countDistinct).query.get ==> List(2)

      One.uuid(count).query.get ==> List(4)
      One.uuid(countDistinct).query.get ==> List(3)

      One.n.a1.uuid(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      One.n.a1.uuid(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}