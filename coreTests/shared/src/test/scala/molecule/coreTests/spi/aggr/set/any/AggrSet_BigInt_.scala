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
        _ <- Ns.i.bigIntSet.insert(List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2)),
          (2, Set(bigInt3, bigInt4)),
          (2, Set(bigInt3, bigInt4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.bigIntSet.query.get.map(_ ==> List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2, bigInt3, bigInt4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.bigIntSet(distinct).query.get.map(_ ==> List(
          (1, Set(Set(bigInt1, bigInt2))),
          (2, Set(
            Set(bigInt2),
            Set(bigInt3, bigInt4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.bigIntSet(distinct).query.get.map(_ ==> List(
          Set(
            Set(bigInt1, bigInt2),
            Set(bigInt2),
            Set(bigInt3, bigInt4),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.bigIntSet.insert(List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2)),
          (2, Set(bigInt3, bigInt4)),
          (2, Set(bigInt3, bigInt4)),
        )).transact

        // Matching values coalesced bigInto one Set

        _ <- Ns.bigIntSet(min).query.get.map(_ ==> List(Set(bigInt1)))
        _ <- Ns.bigIntSet(min(1)).query.get.map(_ ==> List(Set(bigInt1)))
        _ <- Ns.bigIntSet(min(2)).query.get.map(_ ==> List(Set(bigInt1, bigInt2)))
        _ <- Ns.bigIntSet(min(3)).query.get.map(_ ==> List(Set(bigInt1, bigInt2, bigInt3)))

        _ <- Ns.i.a1.bigIntSet(min).query.get.map(_ ==> List(
          (1, Set(bigInt1)),
          (2, Set(bigInt2)),
        ))
        // Same as
        _ <- Ns.i.a1.bigIntSet(min(1)).query.get.map(_ ==> List(
          (1, Set(bigInt1)),
          (2, Set(bigInt2)),
        ))

        _ <- Ns.i.a1.bigIntSet(min(2)).query.get.map(_ ==> List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2, bigInt3)),
        ))

        _ <- Ns.i.a1.bigIntSet(min(3)).query.get.map(_ ==> List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2, bigInt3, bigInt4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.bigIntSet.insert(List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2)),
          (2, Set(bigInt3, bigInt4)),
          (2, Set(bigInt3, bigInt4)),
        )).transact

        // Matching values coalesced bigInto one Set

        _ <- Ns.bigIntSet(max).query.get.map(_ ==> List(Set(bigInt4)))
        _ <- Ns.bigIntSet(max(1)).query.get.map(_ ==> List(Set(bigInt4)))
        _ <- Ns.bigIntSet(max(2)).query.get.map(_ ==> List(Set(bigInt3, bigInt4)))
        _ <- Ns.bigIntSet(max(3)).query.get.map(_ ==> List(Set(bigInt2, bigInt3, bigInt4)))

        _ <- Ns.i.a1.bigIntSet(max).query.get.map(_ ==> List(
          (1, Set(bigInt2)),
          (2, Set(bigInt4)),
        ))
        // Same as
        _ <- Ns.i.a1.bigIntSet(max(1)).query.get.map(_ ==> List(
          (1, Set(bigInt2)),
          (2, Set(bigInt4)),
        ))

        _ <- Ns.i.a1.bigIntSet(max(2)).query.get.map(_ ==> List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt3, bigInt4)),
        ))

        _ <- Ns.i.a1.bigIntSet(max(3)).query.get.map(_ ==> List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2, bigInt3, bigInt4)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.bigIntSet.insert(List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2)),
          (2, Set(bigInt3, bigInt4)),
          (2, Set(bigInt3, bigInt4)),
        )).transact
        all = Set(bigInt1, bigInt2, bigInt3, bigInt4)
        _ <- Ns.bigIntSet(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.bigIntSet(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.bigIntSet(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.bigIntSet.insert(List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2)),
          (2, Set(bigInt3, bigInt4)),
          (2, Set(bigInt3, bigInt4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.bigIntSet(count).query.get.map(_ ==> List(7))
        _ <- Ns.bigIntSet(countDistinct).query.get.map(_ ==> List(4))

        _ <- Ns.i.a1.bigIntSet(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 5)
        ))
        _ <- Ns.i.a1.bigIntSet(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}