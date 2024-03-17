// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.set.any

import java.time.Instant
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSet_Instant_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.instantSet.insert(List(
          (1, Set(instant1, instant2)),
          (2, Set(instant2)),
          (2, Set(instant3, instant4)),
          (2, Set(instant3, instant4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.instantSet.query.get.map(_ ==> List(
          (1, Set(instant1, instant2)),
          (2, Set(instant2, instant3, instant4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.instantSet(distinct).query.get.map(_ ==> List(
          (1, Set(Set(instant1, instant2))),
          (2, Set(
            Set(instant2),
            Set(instant3, instant4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.instantSet(distinct).query.get.map(_ ==> List(
          Set(
            Set(instant1, instant2),
            Set(instant2),
            Set(instant3, instant4),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.instantSet.insert(List(
          (1, Set(instant1, instant2)),
          (2, Set(instant2)),
          (2, Set(instant3, instant4)),
          (2, Set(instant3, instant4)),
        )).transact

        // Matching values coalesced instanto one Set

        _ <- Ns.instantSet(min).query.get.map(_ ==> List(Set(instant1)))
        _ <- Ns.instantSet(min(1)).query.get.map(_ ==> List(Set(instant1)))
        _ <- Ns.instantSet(min(2)).query.get.map(_ ==> List(Set(instant1, instant2)))
        _ <- Ns.instantSet(min(3)).query.get.map(_ ==> List(Set(instant1, instant2, instant3)))

        _ <- Ns.i.a1.instantSet(min).query.get.map(_ ==> List(
          (1, Set(instant1)),
          (2, Set(instant2)),
        ))
        // Same as
        _ <- Ns.i.a1.instantSet(min(1)).query.get.map(_ ==> List(
          (1, Set(instant1)),
          (2, Set(instant2)),
        ))

        _ <- Ns.i.a1.instantSet(min(2)).query.get.map(_ ==> List(
          (1, Set(instant1, instant2)),
          (2, Set(instant2, instant3)),
        ))

        _ <- Ns.i.a1.instantSet(min(3)).query.get.map(_ ==> List(
          (1, Set(instant1, instant2)),
          (2, Set(instant2, instant3, instant4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.instantSet.insert(List(
          (1, Set(instant1, instant2)),
          (2, Set(instant2)),
          (2, Set(instant3, instant4)),
          (2, Set(instant3, instant4)),
        )).transact

        // Matching values coalesced instanto one Set

        _ <- Ns.instantSet(max).query.get.map(_ ==> List(Set(instant4)))
        _ <- Ns.instantSet(max(1)).query.get.map(_ ==> List(Set(instant4)))
        _ <- Ns.instantSet(max(2)).query.get.map(_ ==> List(Set(instant3, instant4)))
        _ <- Ns.instantSet(max(3)).query.get.map(_ ==> List(Set(instant2, instant3, instant4)))

        _ <- Ns.i.a1.instantSet(max).query.get.map(_ ==> List(
          (1, Set(instant2)),
          (2, Set(instant4)),
        ))
        // Same as
        _ <- Ns.i.a1.instantSet(max(1)).query.get.map(_ ==> List(
          (1, Set(instant2)),
          (2, Set(instant4)),
        ))

        _ <- Ns.i.a1.instantSet(max(2)).query.get.map(_ ==> List(
          (1, Set(instant1, instant2)),
          (2, Set(instant3, instant4)),
        ))

        _ <- Ns.i.a1.instantSet(max(3)).query.get.map(_ ==> List(
          (1, Set(instant1, instant2)),
          (2, Set(instant2, instant3, instant4)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.instantSet.insert(List(
          (1, Set(instant1, instant2)),
          (2, Set(instant2)),
          (2, Set(instant3, instant4)),
          (2, Set(instant3, instant4)),
        )).transact
        all = Set(instant1, instant2, instant3, instant4)
        _ <- Ns.instantSet(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.instantSet(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.instantSet(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.instantSet.insert(List(
          (1, Set(instant1, instant2)),
          (2, Set(instant2)),
          (2, Set(instant3, instant4)),
          (2, Set(instant3, instant4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.instantSet(count).query.get.map(_ ==> List(7))
        _ <- Ns.instantSet(countDistinct).query.get.map(_ ==> List(4))

        _ <- Ns.i.a1.instantSet(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 5)
        ))
        _ <- Ns.i.a1.instantSet(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}