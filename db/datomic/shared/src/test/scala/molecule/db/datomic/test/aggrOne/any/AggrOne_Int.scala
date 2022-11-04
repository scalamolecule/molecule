package molecule.db.datomic.test.aggrOne.any

import molecule.boilerplate.api.Keywords._
import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_Int extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - cardOne { implicit conn =>
      One.n.int.insert(List(
        (1, int1),
        (2, int2),
        (2, int2),
        (2, int3),
      )).transact

      // Distinct values are returned in a List
      One.int.apply(distinct).query.get.head.sorted ==> List(int1, int2, int3)
      One.n.a1.int(distinct).query.get.map(r => (r._1, r._2.sorted)) ==> List(
        (1, List(int1)),
        (2, List(int2, int3)),
      )
    }


    "min" - cardOne { implicit conn =>
      One.int.insert(List(int1, int2, int3)).transact
      One.int.apply(min).query.get.head ==> int1
      One.int(min(1)).query.get.head ==> List(int1)
      One.int(min(2)).query.get.head ==> List(int1, int2)
    }


    "max" - cardOne { implicit futConn =>
      One.int.insert(List(int1, int2, int3)).transact
      One.int(max).query.get.head ==> int3
      One.int(max(1)).query.get.head ==> List(int3)
      One.int(max(2)).query.get.head ==> List(int3, int2)
    }


    "rand" - cardOne { implicit conn =>
      One.int.insert(List(int1, int2, int3)).transact
      val all = Seq(int1, int2, int3, int4)
      all.contains(One.int(rand).query.get.head) ==> true
      all.intersect(One.int(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(One.int(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - cardOne { implicit futConn =>
      One.int.insert(List(int1, int2, int3)).transact
      val all = Seq(int1, int2, int3, int4)
      all.contains(One.int(sample).query.get.head) ==> true
      all.intersect(One.int(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(One.int(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - cardOne { implicit conn =>
      One.n.int.insert(List(
        (1, int1),
        (2, int2),
        (2, int2),
        (2, int3),
      )).transact

      One.n(count).query.get ==> List(4)
      One.n(countDistinct).query.get ==> List(2)

      One.int(count).query.get ==> List(4)
      One.int(countDistinct).query.get ==> List(3)

      One.n.a1.int(count).query.get ==> List(
        (1, 1),
        (2, 3)
      )
      One.n.a1.int(countDistinct).query.get ==> List(
        (1, 1),
        (2, 2)
      )
    }
  }
}