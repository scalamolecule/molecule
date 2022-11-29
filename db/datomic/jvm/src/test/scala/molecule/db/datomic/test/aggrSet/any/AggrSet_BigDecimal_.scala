// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrSet.any


import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSet_BigDecimal_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      Ns.i.bigDecimals.insert(List(
        (1, Set(bigDecimal1, bigDecimal2)),
        (2, Set(bigDecimal2, bigDecimal3)),
        (2, Set(bigDecimal3, bigDecimal4)),
        (2, Set(bigDecimal3, bigDecimal4)),
      )).transact

      // Non-aggregated card-many Set of attribute values coalesce
      Ns.i.a1.bigDecimals.query.get ==> List(
        (1, Set(bigDecimal1, bigDecimal2)),
        (2, Set(bigDecimal2, bigDecimal3, bigDecimal4)), // 3 rows coalesced
      )

      // Use `distinct` keyword to retrieve unique Sets of values
      Ns.i.a1.bigDecimals(distinct).query.get ==> List(
        (1, Set(Set(bigDecimal1, bigDecimal2))),
        (2, Set(
          Set(bigDecimal2, bigDecimal3),
          Set(bigDecimal3, bigDecimal4) // 2 rows coalesced
        ))
      )

      Ns.bigDecimals(distinct).query.get ==> List(
        Set(
          Set(bigDecimal1, bigDecimal2),
          Set(bigDecimal2, bigDecimal3),
          Set(bigDecimal3, bigDecimal4),
        )
      )
    }


    "min" - types { implicit conn =>
      Ns.i.bigDecimals.insert(List(
        (1, Set(bigDecimal1, bigDecimal2)),
        (2, Set(bigDecimal2, bigDecimal3)),
        (2, Set(bigDecimal3, bigDecimal4)),
        (2, Set(bigDecimal3, bigDecimal4)),
      )).transact

      Ns.bigDecimals(min).query.get ==> List(Set(bigDecimal1))
      Ns.bigDecimals(min(1)).query.get ==> List(Set(bigDecimal1))
      Ns.bigDecimals(min(2)).query.get ==> List(Set(bigDecimal1, bigDecimal2))

      Ns.i.bigDecimals(min).query.get ==> List(
        (1, Set(bigDecimal1)),
        (2, Set(bigDecimal2)),
      )
      // Same as
      Ns.i.bigDecimals(min(1)).query.get ==> List(
        (1, Set(bigDecimal1)),
        (2, Set(bigDecimal2)),
      )

      Ns.i.bigDecimals(min(2)).query.get ==> List(
        (1, Set(bigDecimal1, bigDecimal2)),
        (2, Set(bigDecimal2, bigDecimal3)),
      )
    }


    "max" - types { implicit futConn =>
      Ns.i.bigDecimals.insert(List(
        (1, Set(bigDecimal1, bigDecimal2)),
        (2, Set(bigDecimal2, bigDecimal3)),
        (2, Set(bigDecimal3, bigDecimal4)),
        (2, Set(bigDecimal3, bigDecimal4)),
      )).transact

      Ns.bigDecimals(max).query.get ==> List(Set(bigDecimal4))
      Ns.bigDecimals(max(1)).query.get ==> List(Set(bigDecimal4))
      Ns.bigDecimals(max(2)).query.get ==> List(Set(bigDecimal3, bigDecimal4))

      Ns.i.bigDecimals(max).query.get ==> List(
        (1, Set(bigDecimal2)),
        (2, Set(bigDecimal4)),
      )
      // Same as
      Ns.i.bigDecimals(max(1)).query.get ==> List(
        (1, Set(bigDecimal2)),
        (2, Set(bigDecimal4)),
      )

      Ns.i.bigDecimals(max(2)).query.get ==> List(
        (1, Set(bigDecimal1, bigDecimal2)),
        (2, Set(bigDecimal3, bigDecimal4)),
      )
    }


    "rand" - types { implicit conn =>
      Ns.i.bigDecimals.insert(List(
        (1, Set(bigDecimal1, bigDecimal2)),
        (2, Set(bigDecimal2, bigDecimal3)),
        (2, Set(bigDecimal3, bigDecimal4)),
        (2, Set(bigDecimal3, bigDecimal4)),
      )).transact
      val all = Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4)
      all.contains(Ns.bigDecimals(rand).query.get.head.head) ==> true
      all.intersect(Ns.bigDecimals(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.bigDecimals(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - types { implicit futConn =>
      Ns.i.bigDecimals.insert(List(
        (1, Set(bigDecimal1, bigDecimal2)),
        (2, Set(bigDecimal2, bigDecimal3)),
        (2, Set(bigDecimal3, bigDecimal4)),
        (2, Set(bigDecimal3, bigDecimal4)),
      )).transact
      val all = Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4)
      all.contains(Ns.bigDecimals(sample).query.get.head.head) ==> true
      all.intersect(Ns.bigDecimals(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.bigDecimals(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - types { implicit conn =>
      Ns.i.bigDecimals.insert(List(
        (1, Set(bigDecimal1, bigDecimal2)),
        (2, Set(bigDecimal2, bigDecimal3)),
        (2, Set(bigDecimal3, bigDecimal4)),
        (2, Set(bigDecimal3, bigDecimal4)),
      )).transact

      Ns.i(count).query.get ==> List(4)
      Ns.i(countDistinct).query.get ==> List(2)

      Ns.bigDecimals(count).query.get ==> List(8)
      Ns.bigDecimals(countDistinct).query.get ==> List(4)

      Ns.i.a1.bigDecimals(count).query.get ==> List(
        (1, 2),
        (2, 6)
      )
      Ns.i.a1.bigDecimals(countDistinct).query.get ==> List(
        (1, 2),
        (2, 3)
      )
    }
  }
}