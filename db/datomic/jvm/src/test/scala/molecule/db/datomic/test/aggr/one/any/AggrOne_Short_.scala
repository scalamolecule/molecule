// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.one.any

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_Short_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      Ns.i.short.insert(List(
        (1, short1),
        (2, short2),
        (2, short2),
        (2, short3),
      )).transact

      Ns.i.a1.short.query.get.sortBy(_._2) ==> List(
        (1, short1),
        (2, short2), // 2 rows coalesced
        (2, short3),
      )

      // Distinct values are returned in a List
      Ns.i.a1.short.apply(distinct).query.get ==> List(
        (1, Set(short1)),
        (2, Set(short2, short3)),
      )

      Ns.short(distinct).query.get.head ==> Set(
        short1, short2, short3
      )
    }


    "min" - types { implicit conn =>
      Ns.short.insert(List(short1, short2, short3)).transact
      Ns.short(min).query.get ==> List(short1)
      Ns.short(min(1)).query.get ==> List(Set(short1))
      Ns.short(min(2)).query.get ==> List(Set(short1, short2))
    }


    "max" - types { implicit futConn =>
      Ns.short.insert(List(short1, short2, short3)).transact
      Ns.short(max).query.get ==> List(short3)
      Ns.short(max(1)).query.get ==> List(Set(short3))
      Ns.short(max(2)).query.get ==> List(Set(short3, short2))
    }


    "rand" - types { implicit conn =>
      Ns.short.insert(List(short1, short2, short3)).transact
      val all = Set(short1, short2, short3, short4)
      all.contains(Ns.short.apply(rand).query.get.head) ==> true
      all.intersect(Ns.short(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.short(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - types { implicit futConn =>
      Ns.short.insert(List(short1, short2, short3)).transact
      val all = Set(short1, short2, short3, short4)
      all.contains(Ns.short(sample).query.get.head) ==> true
      all.intersect(Ns.short(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.short(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - types { implicit conn =>
      Ns.i.short.insert(List(
        (1, short1),
        (2, short2),
        (2, short2),
        (2, short3),
      )).transact

      Ns.i(count).query.get ==> List(4)
      Ns.i(countDistinct).query.get ==> List(2)

      Ns.short(count).query.get ==> List(4)
      Ns.short(countDistinct).query.get ==> List(3)

      Ns.i.a1.short(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      Ns.i.a1.short(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}