// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrSet.any


import molecule.coreTests.dataModels.core.types.dsl.CardSet._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSet_BigInt_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - cardSet { implicit conn =>
      NsSet.n.bigInts.insert(List(
        (1, Set(bigInt1, bigInt2)),
        (2, Set(bigInt2, bigInt3)),
        (2, Set(bigInt3, bigInt4)),
        (2, Set(bigInt3, bigInt4)),
      )).transact

      // Non-aggregated card-many Set of attribute values coalesce
      NsSet.n.a1.bigInts.query.get ==> List(
        (1, Set(bigInt1, bigInt2)),
        (2, Set(bigInt2, bigInt3, bigInt4)), // 3 rows coalesced
      )

      // Use `distinct` keyword to retrieve unique Sets of values
      NsSet.n.a1.bigInts(distinct).query.get ==> List(
        (1, Set(Set(bigInt1, bigInt2))),
        (2, Set(
          Set(bigInt2, bigInt3),
          Set(bigInt3, bigInt4) // 2 rows coalesced
        ))
      )

      NsSet.bigInts(distinct).query.get ==> List(
        Set(
          Set(bigInt1, bigInt2),
          Set(bigInt2, bigInt3),
          Set(bigInt3, bigInt4),
        )
      )
    }


    "min" - cardSet { implicit conn =>
      NsSet.n.bigInts.insert(List(
        (1, Set(bigInt1, bigInt2)),
        (2, Set(bigInt2, bigInt3)),
        (2, Set(bigInt3, bigInt4)),
        (2, Set(bigInt3, bigInt4)),
      )).transact

      NsSet.bigInts(min).query.get ==> List(Set(bigInt1))
      NsSet.bigInts(min(1)).query.get ==> List(Set(bigInt1))
      NsSet.bigInts(min(2)).query.get ==> List(Set(bigInt1, bigInt2))

      NsSet.n.bigInts(min).query.get ==> List(
        (1, Set(bigInt1)),
        (2, Set(bigInt2)),
      )
      // Same as
      NsSet.n.bigInts(min(1)).query.get ==> List(
        (1, Set(bigInt1)),
        (2, Set(bigInt2)),
      )

      NsSet.n.bigInts(min(2)).query.get ==> List(
        (1, Set(bigInt1, bigInt2)),
        (2, Set(bigInt2, bigInt3)),
      )
    }


    "max" - cardSet { implicit futConn =>
      NsSet.n.bigInts.insert(List(
        (1, Set(bigInt1, bigInt2)),
        (2, Set(bigInt2, bigInt3)),
        (2, Set(bigInt3, bigInt4)),
        (2, Set(bigInt3, bigInt4)),
      )).transact

      NsSet.bigInts(max).query.get ==> List(Set(bigInt4))
      NsSet.bigInts(max(1)).query.get ==> List(Set(bigInt4))
      NsSet.bigInts(max(2)).query.get ==> List(Set(bigInt3, bigInt4))

      NsSet.n.bigInts(max).query.get ==> List(
        (1, Set(bigInt2)),
        (2, Set(bigInt4)),
      )
      // Same as
      NsSet.n.bigInts(max(1)).query.get ==> List(
        (1, Set(bigInt2)),
        (2, Set(bigInt4)),
      )

      NsSet.n.bigInts(max(2)).query.get ==> List(
        (1, Set(bigInt1, bigInt2)),
        (2, Set(bigInt3, bigInt4)),
      )
    }


    "rand" - cardSet { implicit conn =>
      NsSet.n.bigInts.insert(List(
        (1, Set(bigInt1, bigInt2)),
        (2, Set(bigInt2, bigInt3)),
        (2, Set(bigInt3, bigInt4)),
        (2, Set(bigInt3, bigInt4)),
      )).transact
      val all = Set(bigInt1, bigInt2, bigInt3, bigInt4)
      all.contains(NsSet.bigInts(rand).query.get.head.head) ==> true
      all.intersect(NsSet.bigInts(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsSet.bigInts(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - cardSet { implicit futConn =>
      NsSet.n.bigInts.insert(List(
        (1, Set(bigInt1, bigInt2)),
        (2, Set(bigInt2, bigInt3)),
        (2, Set(bigInt3, bigInt4)),
        (2, Set(bigInt3, bigInt4)),
      )).transact
      val all = Set(bigInt1, bigInt2, bigInt3, bigInt4)
      all.contains(NsSet.bigInts(sample).query.get.head.head) ==> true
      all.intersect(NsSet.bigInts(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(NsSet.bigInts(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - cardSet { implicit conn =>
      NsSet.n.bigInts.insert(List(
        (1, Set(bigInt1, bigInt2)),
        (2, Set(bigInt2, bigInt3)),
        (2, Set(bigInt3, bigInt4)),
        (2, Set(bigInt3, bigInt4)),
      )).transact

      NsSet.n(count).query.get ==> List(4)
      NsSet.n(countDistinct).query.get ==> List(2)

      NsSet.bigInts(count).query.get ==> List(8)
      NsSet.bigInts(countDistinct).query.get ==> List(4)

      NsSet.n.a1.bigInts(count).query.get ==> List(
        (1, 2),
        (2, 6)
      )
      NsSet.n.a1.bigInts(countDistinct).query.get ==> List(
        (1, 2),
        (2, 3)
      )
    }
  }
}