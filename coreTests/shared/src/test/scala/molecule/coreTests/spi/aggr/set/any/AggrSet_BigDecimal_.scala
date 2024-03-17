// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.set.any

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSet_BigDecimal_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.bigDecimalSet.insert(List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2)),
          (2, Set(bigDecimal3, bigDecimal4)),
          (2, Set(bigDecimal3, bigDecimal4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.bigDecimalSet.query.get.map(_ ==> List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2, bigDecimal3, bigDecimal4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.bigDecimalSet(distinct).query.get.map(_ ==> List(
          (1, Set(Set(bigDecimal1, bigDecimal2))),
          (2, Set(
            Set(bigDecimal2),
            Set(bigDecimal3, bigDecimal4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.bigDecimalSet(distinct).query.get.map(_ ==> List(
          Set(
            Set(bigDecimal1, bigDecimal2),
            Set(bigDecimal2),
            Set(bigDecimal3, bigDecimal4),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.bigDecimalSet.insert(List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2)),
          (2, Set(bigDecimal3, bigDecimal4)),
          (2, Set(bigDecimal3, bigDecimal4)),
        )).transact

        // Matching values coalesced bigDecimalo one Set

        _ <- Ns.bigDecimalSet(min).query.get.map(_ ==> List(Set(bigDecimal1)))
        _ <- Ns.bigDecimalSet(min(1)).query.get.map(_ ==> List(Set(bigDecimal1)))
        _ <- Ns.bigDecimalSet(min(2)).query.get.map(_ ==> List(Set(bigDecimal1, bigDecimal2)))
        _ <- Ns.bigDecimalSet(min(3)).query.get.map(_ ==> List(Set(bigDecimal1, bigDecimal2, bigDecimal3)))

        _ <- Ns.i.a1.bigDecimalSet(min).query.get.map(_ ==> List(
          (1, Set(bigDecimal1)),
          (2, Set(bigDecimal2)),
        ))
        // Same as
        _ <- Ns.i.a1.bigDecimalSet(min(1)).query.get.map(_ ==> List(
          (1, Set(bigDecimal1)),
          (2, Set(bigDecimal2)),
        ))

        _ <- Ns.i.a1.bigDecimalSet(min(2)).query.get.map(_ ==> List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2, bigDecimal3)),
        ))

        _ <- Ns.i.a1.bigDecimalSet(min(3)).query.get.map(_ ==> List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2, bigDecimal3, bigDecimal4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.bigDecimalSet.insert(List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2)),
          (2, Set(bigDecimal3, bigDecimal4)),
          (2, Set(bigDecimal3, bigDecimal4)),
        )).transact

        // Matching values coalesced bigDecimalo one Set

        _ <- Ns.bigDecimalSet(max).query.get.map(_ ==> List(Set(bigDecimal4)))
        _ <- Ns.bigDecimalSet(max(1)).query.get.map(_ ==> List(Set(bigDecimal4)))
        _ <- Ns.bigDecimalSet(max(2)).query.get.map(_ ==> List(Set(bigDecimal3, bigDecimal4)))
        _ <- Ns.bigDecimalSet(max(3)).query.get.map(_ ==> List(Set(bigDecimal2, bigDecimal3, bigDecimal4)))

        _ <- Ns.i.a1.bigDecimalSet(max).query.get.map(_ ==> List(
          (1, Set(bigDecimal2)),
          (2, Set(bigDecimal4)),
        ))
        // Same as
        _ <- Ns.i.a1.bigDecimalSet(max(1)).query.get.map(_ ==> List(
          (1, Set(bigDecimal2)),
          (2, Set(bigDecimal4)),
        ))

        _ <- Ns.i.a1.bigDecimalSet(max(2)).query.get.map(_ ==> List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal3, bigDecimal4)),
        ))

        _ <- Ns.i.a1.bigDecimalSet(max(3)).query.get.map(_ ==> List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2, bigDecimal3, bigDecimal4)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.bigDecimalSet.insert(List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2)),
          (2, Set(bigDecimal3, bigDecimal4)),
          (2, Set(bigDecimal3, bigDecimal4)),
        )).transact
        all = Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4)
        _ <- Ns.bigDecimalSet(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.bigDecimalSet(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.bigDecimalSet(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.bigDecimalSet.insert(List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2)),
          (2, Set(bigDecimal3, bigDecimal4)),
          (2, Set(bigDecimal3, bigDecimal4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.bigDecimalSet(count).query.get.map(_ ==> List(7))
        _ <- Ns.bigDecimalSet(countDistinct).query.get.map(_ ==> List(4))

        _ <- Ns.i.a1.bigDecimalSet(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 5)
        ))
        _ <- Ns.i.a1.bigDecimalSet(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}