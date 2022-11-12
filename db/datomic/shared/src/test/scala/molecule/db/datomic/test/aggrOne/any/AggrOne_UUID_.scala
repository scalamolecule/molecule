// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.any


import java.util.UUID
import molecule.coreTests.dataModels.core.types.dsl.TypesOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_UUID_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - typesOne { implicit conn =>
      NsOne.n.uuid.insert(List(
        (1, uuid1),
        (2, uuid2),
        (2, uuid2),
        (2, uuid3),
      )).transact

      NsOne.n.a1.uuid.query.get.sortBy(_._2) ==> List(
        (1, uuid1),
        (2, uuid2), // 2 rows coalesced
        (2, uuid3),
      )

      // Distinct values are returned in a List
      NsOne.n.a1.uuid.apply(distinct).query.get ==> List(
        (1, Set(uuid1)),
        (2, Set(uuid2, uuid3)),
      )

      NsOne.uuid(distinct).query.get.head ==> Set(
        uuid1, uuid2, uuid3
      )
    }


    "min" - typesOne { implicit conn =>
      NsOne.uuid.insert(List(uuid1, uuid2, uuid3)).transact
      NsOne.uuid(min).query.get.head ==> uuid1
      NsOne.uuid(min(1)).query.get.head ==> Set(uuid1)
      NsOne.uuid(min(2)).query.get.head ==> Set(uuid1, uuid2)
    }


    "max" - typesOne { implicit futConn =>
      NsOne.uuid.insert(List(uuid1, uuid2, uuid3)).transact
      NsOne.uuid(max).query.get.head ==> uuid3
      NsOne.uuid(max(1)).query.get.head ==> Set(uuid3)
      NsOne.uuid(max(2)).query.get.head ==> Set(uuid3, uuid2)
    }


    "rand" - typesOne { implicit conn =>
      NsOne.uuid.insert(List(uuid1, uuid2, uuid3)).transact
      val all = Set(uuid1, uuid2, uuid3, uuid4)
      all.contains(NsOne.uuid.apply(rand).query.get.head) ==> true
      all.intersect(NsOne.uuid(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsOne.uuid(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - typesOne { implicit futConn =>
      NsOne.uuid.insert(List(uuid1, uuid2, uuid3)).transact
      val all = Set(uuid1, uuid2, uuid3, uuid4)
      all.contains(NsOne.uuid(sample).query.get.head) ==> true
      all.intersect(NsOne.uuid(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsOne.uuid(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - typesOne { implicit conn =>
      NsOne.n.uuid.insert(List(
        (1, uuid1),
        (2, uuid2),
        (2, uuid2),
        (2, uuid3),
      )).transact

      NsOne.n(count).query.get ==> List(4)
      NsOne.n(countDistinct).query.get ==> List(2)

      NsOne.uuid(count).query.get ==> List(4)
      NsOne.uuid(countDistinct).query.get ==> List(3)

      NsOne.n.a1.uuid(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      NsOne.n.a1.uuid(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}