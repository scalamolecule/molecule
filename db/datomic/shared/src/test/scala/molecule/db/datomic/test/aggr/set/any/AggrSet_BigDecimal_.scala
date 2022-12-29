// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.set.any

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._
import molecule.core.util.Executor._

object AggrSet_BigDecimal_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.bigDecimals.insert(List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2, bigDecimal3)),
          (2, Set(bigDecimal3, bigDecimal4)),
          (2, Set(bigDecimal3, bigDecimal4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.bigDecimals.query.get.map(_ ==> List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2, bigDecimal3, bigDecimal4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of values
        _ <- Ns.i.a1.bigDecimals(distinct).query.get.map(_ ==> List(
          (1, Set(Set(bigDecimal1, bigDecimal2))),
          (2, Set(
            Set(bigDecimal2, bigDecimal3),
            Set(bigDecimal3, bigDecimal4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.bigDecimals(distinct).query.get.map(_ ==> List(
          Set(
            Set(bigDecimal1, bigDecimal2),
            Set(bigDecimal2, bigDecimal3),
            Set(bigDecimal3, bigDecimal4),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.bigDecimals.insert(List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2, bigDecimal3)),
          (2, Set(bigDecimal3, bigDecimal4)),
          (2, Set(bigDecimal3, bigDecimal4)),
        )).transact

        _ <- Ns.bigDecimals(min).query.get.map(_ ==> List(Set(bigDecimal1)))
        _ <- Ns.bigDecimals(min(1)).query.get.map(_ ==> List(Set(bigDecimal1)))
        _ <- Ns.bigDecimals(min(2)).query.get.map(_ ==> List(Set(bigDecimal1, bigDecimal2)))

        _ <- Ns.i.bigDecimals(min).query.get.map(_ ==> List(
          (1, Set(bigDecimal1)),
          (2, Set(bigDecimal2)),
        ))
        // Same as
        _ <- Ns.i.bigDecimals(min(1)).query.get.map(_ ==> List(
          (1, Set(bigDecimal1)),
          (2, Set(bigDecimal2)),
        ))

        _ <- Ns.i.bigDecimals(min(2)).query.get.map(_ ==> List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2, bigDecimal3)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.bigDecimals.insert(List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2, bigDecimal3)),
          (2, Set(bigDecimal3, bigDecimal4)),
          (2, Set(bigDecimal3, bigDecimal4)),
        )).transact

        _ <- Ns.bigDecimals(max).query.get.map(_ ==> List(Set(bigDecimal4)))
        _ <- Ns.bigDecimals(max(1)).query.get.map(_ ==> List(Set(bigDecimal4)))
        _ <- Ns.bigDecimals(max(2)).query.get.map(_ ==> List(Set(bigDecimal3, bigDecimal4)))

        _ <- Ns.i.bigDecimals(max).query.get.map(_ ==> List(
          (1, Set(bigDecimal2)),
          (2, Set(bigDecimal4)),
        ))
        // Same as
        _ <- Ns.i.bigDecimals(max(1)).query.get.map(_ ==> List(
          (1, Set(bigDecimal2)),
          (2, Set(bigDecimal4)),
        ))

        _ <- Ns.i.bigDecimals(max(2)).query.get.map(_ ==> List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal3, bigDecimal4)),
        ))
      } yield ()
    }


    "rand" - types { implicit conn =>
      for {
        _ <- Ns.i.bigDecimals.insert(List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2, bigDecimal3)),
          (2, Set(bigDecimal3, bigDecimal4)),
          (2, Set(bigDecimal3, bigDecimal4)),
        )).transact
        all = Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4)
        _ <- Ns.bigDecimals(rand).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.bigDecimals(rand(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.bigDecimals(rand(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.bigDecimals.insert(List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2, bigDecimal3)),
          (2, Set(bigDecimal3, bigDecimal4)),
          (2, Set(bigDecimal3, bigDecimal4)),
        )).transact
        all = Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4)
        _ <- Ns.bigDecimals(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.bigDecimals(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.bigDecimals(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count, countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.bigDecimals.insert(List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2, bigDecimal3)),
          (2, Set(bigDecimal3, bigDecimal4)),
          (2, Set(bigDecimal3, bigDecimal4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.bigDecimals(count).query.get.map(_ ==> List(8))
        _ <- Ns.bigDecimals(countDistinct).query.get.map(_ ==> List(4))

        _ <- Ns.i.a1.bigDecimals(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 6)
        ))
        _ <- Ns.i.a1.bigDecimals(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}