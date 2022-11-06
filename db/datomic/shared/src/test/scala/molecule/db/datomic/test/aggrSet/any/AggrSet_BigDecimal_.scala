// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrSet.any


import molecule.coreTests.dataModels.core.types.dsl.CardSet._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSet_BigDecimal_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - cardSet { implicit conn =>
      NsSet.n.bigDecimals.insert(List(
        (1, Set(bigDecimal1, bigDecimal2)),
        (2, Set(bigDecimal2, bigDecimal3)),
        (2, Set(bigDecimal3, bigDecimal4)),
        (2, Set(bigDecimal3, bigDecimal4)),
      )).transact

      // Non-aggregated card-many Set of attribute values coalesce
      NsSet.n.a1.bigDecimals.query.get ==> List(
        (1, Set(bigDecimal1, bigDecimal2)),
        (2, Set(bigDecimal2, bigDecimal3, bigDecimal4)), // 3 rows coalesced
      )

      // Use `distinct` keyword to retrieve unique Sets of values
      NsSet.n.a1.bigDecimals(distinct).query.get ==> List(
        (1, Set(Set(bigDecimal1, bigDecimal2))),
        (2, Set(
          Set(bigDecimal2, bigDecimal3),
          Set(bigDecimal3, bigDecimal4) // 2 rows coalesced
        ))
      )

      NsSet.bigDecimals.apply(distinct).query.get ==> List(
        Set(
          Set(bigDecimal1, bigDecimal2),
          Set(bigDecimal2, bigDecimal3),
          Set(bigDecimal3, bigDecimal4),
        )
      )
    }


    "min" - cardSet { implicit conn =>
      NsSet.n.bigDecimals.insert(List(
        (1, Set(bigDecimal1, bigDecimal2)),
        (2, Set(bigDecimal2, bigDecimal3)),
        (2, Set(bigDecimal3, bigDecimal4)),
        (2, Set(bigDecimal3, bigDecimal4)),
      )).transact

      NsSet.bigDecimals(min).query.get ==> List(Set(bigDecimal1))
      NsSet.bigDecimals(min(1)).query.get ==> List(Set(Set(bigDecimal1)))
      NsSet.bigDecimals(min(2)).query.get ==> List(Set(Set(bigDecimal1, bigDecimal2)))

      NsSet.n.bigDecimals(min).query.get ==> List(
        (1, Set(bigDecimal1)),
        (2, Set(bigDecimal2)),
      )
      NsSet.n.bigDecimals(min(1)).query.get ==> List(
        (1, Set(Set(bigDecimal1))),
        (2, Set(Set(bigDecimal2))),
      )
      NsSet.n.bigDecimals(min(2)).query.get ==> List(
        (1, Set(Set(bigDecimal1, bigDecimal2))),
        (2, Set(Set(bigDecimal2, bigDecimal3))),
      )
    }


    "max" - cardSet { implicit futConn =>
      NsSet.n.bigDecimals.insert(List(
        (1, Set(bigDecimal1, bigDecimal2)),
        (2, Set(bigDecimal2, bigDecimal3)),
        (2, Set(bigDecimal3, bigDecimal4)),
        (2, Set(bigDecimal3, bigDecimal4)),
      )).transact

      NsSet.bigDecimals(max).query.get ==> List(Set(bigDecimal4))
      NsSet.bigDecimals(max(1)).query.get ==> List(Set(Set(bigDecimal4)))
      NsSet.bigDecimals(max(2)).query.get ==> List(Set(Set(bigDecimal3, bigDecimal4)))

      NsSet.n.bigDecimals(max).query.get ==> List(
        (1, Set(bigDecimal2)),
        (2, Set(bigDecimal4)),
      )
      NsSet.n.bigDecimals(max(1)).query.get ==> List(
        (1, Set(Set(bigDecimal2))),
        (2, Set(Set(bigDecimal4))),
      )
      NsSet.n.bigDecimals(max(2)).query.get ==> List(
        (1, Set(Set(bigDecimal1, bigDecimal2))),
        (2, Set(Set(bigDecimal3, bigDecimal4))),
      )
    }


    "rand" - cardSet { implicit conn =>
      NsSet.n.bigDecimals.insert(List(
        (1, Set(bigDecimal1, bigDecimal2)),
        (2, Set(bigDecimal2, bigDecimal3)),
        (2, Set(bigDecimal3, bigDecimal4)),
        (2, Set(bigDecimal3, bigDecimal4)),
      )).transact
      val all = Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4)
      all.contains(NsSet.bigDecimals(rand).query.get.head.head) ==> true
      all.intersect(NsSet.bigDecimals(rand(1)).query.get.head.head).nonEmpty ==> true
      all.intersect(NsSet.bigDecimals(rand(2)).query.get.head.head).nonEmpty ==> true
    }


    "sample" - cardSet { implicit futConn =>
      NsSet.n.bigDecimals.insert(List(
        (1, Set(bigDecimal1, bigDecimal2)),
        (2, Set(bigDecimal2, bigDecimal3)),
        (2, Set(bigDecimal3, bigDecimal4)),
        (2, Set(bigDecimal3, bigDecimal4)),
      )).transact
      val all = Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4)
      all.contains(NsSet.bigDecimals(sample).query.get.head.head) ==> true
      all.intersect(NsSet.bigDecimals(sample(1)).query.get.head.head).nonEmpty ==> true
      all.intersect(NsSet.bigDecimals(sample(2)).query.get.head.head).nonEmpty ==> true
    }


    "count, countDistinct" - cardSet { implicit conn =>
      NsSet.n.bigDecimals.insert(List(
        (1, Set(bigDecimal1, bigDecimal2)),
        (2, Set(bigDecimal2, bigDecimal3)),
        (2, Set(bigDecimal3, bigDecimal4)),
        (2, Set(bigDecimal3, bigDecimal4)),
      )).transact

      NsSet.n(count).query.get ==> List(4)
      NsSet.n(countDistinct).query.get ==> List(2)

      NsSet.bigDecimals(count).query.get ==> List(8)
      NsSet.bigDecimals(countDistinct).query.get ==> List(4)

      NsSet.n.a1.bigDecimals(count).query.get ==> List(
        (1, 2),
        (2, 6)
      )
      NsSet.n.a1.bigDecimals(countDistinct).query.get ==> List(
        (1, 2),
        (2, 3)
      )
    }
  }
}