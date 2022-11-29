// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.any


import java.util.UUID
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_UUID_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      Ns.i.uuid.insert(List(
        (1, uuid1),
        (2, uuid2),
        (2, uuid2),
        (2, uuid3),
      )).transact

      Ns.i.a1.uuid.query.get.sortBy(_._2) ==> List(
        (1, uuid1),
        (2, uuid2), // 2 rows coalesced
        (2, uuid3),
      )

      // Distinct values are returned in a List
      Ns.i.a1.uuid.apply(distinct).query.get ==> List(
        (1, Set(uuid1)),
        (2, Set(uuid2, uuid3)),
      )

      Ns.uuid(distinct).query.get.head ==> Set(
        uuid1, uuid2, uuid3
      )
    }


    "min" - types { implicit conn =>
      Ns.uuid.insert(List(uuid1, uuid2, uuid3)).transact
      Ns.uuid(min).query.get ==> List(uuid1)
      Ns.uuid(min(1)).query.get ==> List(Set(uuid1))
      Ns.uuid(min(2)).query.get ==> List(Set(uuid1, uuid2))
    }


    "max" - types { implicit futConn =>
      Ns.uuid.insert(List(uuid1, uuid2, uuid3)).transact
      Ns.uuid(max).query.get ==> List(uuid3)
      Ns.uuid(max(1)).query.get ==> List(Set(uuid3))
      Ns.uuid(max(2)).query.get ==> List(Set(uuid3, uuid2))
    }


    "rand" - types { implicit conn =>
      Ns.uuid.insert(List(uuid1, uuid2, uuid3)).transact
      val all = Set(uuid1, uuid2, uuid3, uuid4)
      all.contains(Ns.uuid.apply(rand).query.get.head) ==> true
      all.intersect(Ns.uuid(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.uuid(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - types { implicit futConn =>
      Ns.uuid.insert(List(uuid1, uuid2, uuid3)).transact
      val all = Set(uuid1, uuid2, uuid3, uuid4)
      all.contains(Ns.uuid(sample).query.get.head) ==> true
      all.intersect(Ns.uuid(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.uuid(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - types { implicit conn =>
      Ns.i.uuid.insert(List(
        (1, uuid1),
        (2, uuid2),
        (2, uuid2),
        (2, uuid3),
      )).transact

      Ns.i(count).query.get ==> List(4)
      Ns.i(countDistinct).query.get ==> List(2)

      Ns.uuid(count).query.get ==> List(4)
      Ns.uuid(countDistinct).query.get ==> List(3)

      Ns.i.a1.uuid(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      Ns.i.a1.uuid(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}