// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.set.any

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSet_BigInt_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.bigInts.insert(List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2, bigInt3)),
          (2, Set(bigInt3, bigInt4)),
          (2, Set(bigInt3, bigInt4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.bigInts.query.get.map(_ ==> List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2, bigInt3, bigInt4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.bigInts(distinct).query.get.map(_ ==> List(
          (1, Set(Set(bigInt1, bigInt2))),
          (2, Set(
            Set(bigInt2, bigInt3),
            Set(bigInt3, bigInt4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.bigInts(distinct).query.get.map(_ ==> List(
          Set(
            Set(bigInt1, bigInt2),
            Set(bigInt2, bigInt3),
            Set(bigInt3, bigInt4),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.bigInts.insert(List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2, bigInt3)),
          (2, Set(bigInt3, bigInt4)),
          (2, Set(bigInt3, bigInt4)),
        )).transact

        // Matching values coalesced bigInto one Set

        _ <- Ns.bigInts(min).query.get.map(_ ==> List(Set(bigInt1)))
        _ <- Ns.bigInts(min(1)).query.get.map(_ ==> List(Set(bigInt1)))
        _ <- Ns.bigInts(min(2)).query.get.map(_ ==> List(Set(bigInt1, bigInt2)))
        _ <- Ns.bigInts(min(3)).query.get.map(_ ==> List(Set(bigInt1, bigInt2, bigInt3)))

        _ <- Ns.i.a1.bigInts(min).query.get.map(_ ==> List(
          (1, Set(bigInt1)),
          (2, Set(bigInt2)),
        ))
        // Same as
        _ <- Ns.i.a1.bigInts(min(1)).query.get.map(_ ==> List(
          (1, Set(bigInt1)),
          (2, Set(bigInt2)),
        ))

        _ <- Ns.i.a1.bigInts(min(2)).query.get.map(_ ==> List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2, bigInt3)),
        ))

        _ <- Ns.i.a1.bigInts(min(3)).query.get.map(_ ==> List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2, bigInt3, bigInt4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.bigInts.insert(List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2, bigInt3)),
          (2, Set(bigInt3, bigInt4)),
          (2, Set(bigInt3, bigInt4)),
        )).transact

        // Matching values coalesced bigInto one Set

        _ <- Ns.bigInts(max).query.get.map(_ ==> List(Set(bigInt4)))
        _ <- Ns.bigInts(max(1)).query.get.map(_ ==> List(Set(bigInt4)))
        _ <- Ns.bigInts(max(2)).query.get.map(_ ==> List(Set(bigInt3, bigInt4)))
        _ <- Ns.bigInts(max(3)).query.get.map(_ ==> List(Set(bigInt2, bigInt3, bigInt4)))

        _ <- Ns.i.a1.bigInts(max).query.get.map(_ ==> List(
          (1, Set(bigInt2)),
          (2, Set(bigInt4)),
        ))
        // Same as
        _ <- Ns.i.a1.bigInts(max(1)).query.get.map(_ ==> List(
          (1, Set(bigInt2)),
          (2, Set(bigInt4)),
        ))

        _ <- Ns.i.a1.bigInts(max(2)).query.get.map(_ ==> List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt3, bigInt4)),
        ))

        _ <- Ns.i.a1.bigInts(max(3)).query.get.map(_ ==> List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2, bigInt3, bigInt4)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.bigInts.insert(List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2, bigInt3)),
          (2, Set(bigInt3, bigInt4)),
          (2, Set(bigInt3, bigInt4)),
        )).transact
        all = Set(bigInt1, bigInt2, bigInt3, bigInt4)
        _ <- Ns.bigInts(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.bigInts(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.bigInts(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.bigInts.insert(List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2, bigInt3)),
          (2, Set(bigInt3, bigInt4)),
          (2, Set(bigInt3, bigInt4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.bigInts(count).query.get.map(_ ==> List(8))
        _ <- Ns.bigInts(countDistinct).query.get.map(_ ==> List(4))

        _ <- Ns.i.a1.bigInts(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 6)
        ))
        _ <- Ns.i.a1.bigInts(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}