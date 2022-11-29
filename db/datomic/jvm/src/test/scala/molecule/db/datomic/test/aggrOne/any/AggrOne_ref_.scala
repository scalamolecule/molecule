// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrOne.any


import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_ref_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      Ns.i.ref.insert(List(
        (1, ref1),
        (2, ref2),
        (2, ref2),
        (2, ref3),
      )).transact

      Ns.i.a1.ref.query.get.sortBy(_._2) ==> List(
        (1, ref1),
        (2, ref2), // 2 rows coalesced
        (2, ref3),
      )

      // Distinct values are returned in a List
      Ns.i.a1.ref.apply(distinct).query.get ==> List(
        (1, Set(ref1)),
        (2, Set(ref2, ref3)),
      )

      Ns.ref(distinct).query.get.head ==> Set(
        ref1, ref2, ref3
      )
    }


    "min" - types { implicit conn =>
      Ns.ref.insert(List(ref1, ref2, ref3)).transact
      Ns.ref(min).query.get ==> List(ref1)
      Ns.ref(min(1)).query.get ==> List(Set(ref1))
      Ns.ref(min(2)).query.get ==> List(Set(ref1, ref2))
    }


    "max" - types { implicit futConn =>
      Ns.ref.insert(List(ref1, ref2, ref3)).transact
      Ns.ref(max).query.get ==> List(ref3)
      Ns.ref(max(1)).query.get ==> List(Set(ref3))
      Ns.ref(max(2)).query.get ==> List(Set(ref3, ref2))
    }


    "rand" - types { implicit conn =>
      Ns.ref.insert(List(ref1, ref2, ref3)).transact
      val all = Set(ref1, ref2, ref3, ref4)
      all.contains(Ns.ref.apply(rand).query.get.head) ==> true
      all.intersect(Ns.ref(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.ref(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - types { implicit futConn =>
      Ns.ref.insert(List(ref1, ref2, ref3)).transact
      val all = Set(ref1, ref2, ref3, ref4)
      all.contains(Ns.ref(sample).query.get.head) ==> true
      all.intersect(Ns.ref(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.ref(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - types { implicit conn =>
      Ns.i.ref.insert(List(
        (1, ref1),
        (2, ref2),
        (2, ref2),
        (2, ref3),
      )).transact

      Ns.i(count).query.get ==> List(4)
      Ns.i(countDistinct).query.get ==> List(2)

      Ns.ref(count).query.get ==> List(4)
      Ns.ref(countDistinct).query.get ==> List(3)

      Ns.i.a1.ref(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      Ns.i.a1.ref(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}