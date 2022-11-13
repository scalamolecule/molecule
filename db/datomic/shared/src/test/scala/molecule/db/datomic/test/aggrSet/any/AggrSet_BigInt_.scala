// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrSet.any


import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSet_BigInt_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      Ns.n.bigInts.insert(List(
        (1, Set(bigInt1, bigInt2)),
        (2, Set(bigInt2, bigInt3)),
        (2, Set(bigInt3, bigInt4)),
        (2, Set(bigInt3, bigInt4)),
      )).transact

      // Non-aggregated card-many Set of attribute values coalesce
      Ns.n.a1.bigInts.query.get ==> List(
        (1, Set(bigInt1, bigInt2)),
        (2, Set(bigInt2, bigInt3, bigInt4)), // 3 rows coalesced
      )

      // Use `distinct` keyword to retrieve unique Sets of values
      Ns.n.a1.bigInts(distinct).query.get ==> List(
        (1, Set(Set(bigInt1, bigInt2))),
        (2, Set(
          Set(bigInt2, bigInt3),
          Set(bigInt3, bigInt4) // 2 rows coalesced
        ))
      )

      Ns.bigInts(distinct).query.get ==> List(
        Set(
          Set(bigInt1, bigInt2),
          Set(bigInt2, bigInt3),
          Set(bigInt3, bigInt4),
        )
      )
    }


    "min" - types { implicit conn =>
      Ns.n.bigInts.insert(List(
        (1, Set(bigInt1, bigInt2)),
        (2, Set(bigInt2, bigInt3)),
        (2, Set(bigInt3, bigInt4)),
        (2, Set(bigInt3, bigInt4)),
      )).transact

      Ns.bigInts(min).query.get ==> List(Set(bigInt1))
      Ns.bigInts(min(1)).query.get ==> List(Set(bigInt1))
      Ns.bigInts(min(2)).query.get ==> List(Set(bigInt1, bigInt2))

      Ns.n.bigInts(min).query.get ==> List(
        (1, Set(bigInt1)),
        (2, Set(bigInt2)),
      )
      // Same as
      Ns.n.bigInts(min(1)).query.get ==> List(
        (1, Set(bigInt1)),
        (2, Set(bigInt2)),
      )

      Ns.n.bigInts(min(2)).query.get ==> List(
        (1, Set(bigInt1, bigInt2)),
        (2, Set(bigInt2, bigInt3)),
      )
    }


    "max" - types { implicit futConn =>
      Ns.n.bigInts.insert(List(
        (1, Set(bigInt1, bigInt2)),
        (2, Set(bigInt2, bigInt3)),
        (2, Set(bigInt3, bigInt4)),
        (2, Set(bigInt3, bigInt4)),
      )).transact

      Ns.bigInts(max).query.get ==> List(Set(bigInt4))
      Ns.bigInts(max(1)).query.get ==> List(Set(bigInt4))
      Ns.bigInts(max(2)).query.get ==> List(Set(bigInt3, bigInt4))

      Ns.n.bigInts(max).query.get ==> List(
        (1, Set(bigInt2)),
        (2, Set(bigInt4)),
      )
      // Same as
      Ns.n.bigInts(max(1)).query.get ==> List(
        (1, Set(bigInt2)),
        (2, Set(bigInt4)),
      )

      Ns.n.bigInts(max(2)).query.get ==> List(
        (1, Set(bigInt1, bigInt2)),
        (2, Set(bigInt3, bigInt4)),
      )
    }


    "rand" - types { implicit conn =>
      Ns.n.bigInts.insert(List(
        (1, Set(bigInt1, bigInt2)),
        (2, Set(bigInt2, bigInt3)),
        (2, Set(bigInt3, bigInt4)),
        (2, Set(bigInt3, bigInt4)),
      )).transact
      val all = Set(bigInt1, bigInt2, bigInt3, bigInt4)
      all.contains(Ns.bigInts(rand).query.get.head.head) ==> true
      all.intersect(Ns.bigInts(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.bigInts(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - types { implicit futConn =>
      Ns.n.bigInts.insert(List(
        (1, Set(bigInt1, bigInt2)),
        (2, Set(bigInt2, bigInt3)),
        (2, Set(bigInt3, bigInt4)),
        (2, Set(bigInt3, bigInt4)),
      )).transact
      val all = Set(bigInt1, bigInt2, bigInt3, bigInt4)
      all.contains(Ns.bigInts(sample).query.get.head.head) ==> true
      all.intersect(Ns.bigInts(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.bigInts(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - types { implicit conn =>
      Ns.n.bigInts.insert(List(
        (1, Set(bigInt1, bigInt2)),
        (2, Set(bigInt2, bigInt3)),
        (2, Set(bigInt3, bigInt4)),
        (2, Set(bigInt3, bigInt4)),
      )).transact

      Ns.n(count).query.get ==> List(4)
      Ns.n(countDistinct).query.get ==> List(2)

      Ns.bigInts(count).query.get ==> List(8)
      Ns.bigInts(countDistinct).query.get ==> List(4)

      Ns.n.a1.bigInts(count).query.get ==> List(
        (1, 2),
        (2, 6)
      )
      Ns.n.a1.bigInts(countDistinct).query.get ==> List(
        (1, 2),
        (2, 3)
      )
    }
  }
}