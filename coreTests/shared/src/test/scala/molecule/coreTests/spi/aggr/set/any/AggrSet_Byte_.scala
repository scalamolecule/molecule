// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.set.any

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSet_Byte_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.bytes.insert(List(
          (1, Set(byte1, byte2)),
          (2, Set(byte2)),
          (2, Set(byte3, byte4)),
          (2, Set(byte3, byte4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.bytes.query.get.map(_ ==> List(
          (1, Set(byte1, byte2)),
          (2, Set(byte2, byte3, byte4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.bytes(distinct).query.get.map(_ ==> List(
          (1, Set(Set(byte1, byte2))),
          (2, Set(
            Set(byte2),
            Set(byte3, byte4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.bytes(distinct).query.get.map(_ ==> List(
          Set(
            Set(byte1, byte2),
            Set(byte2),
            Set(byte3, byte4),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.bytes.insert(List(
          (1, Set(byte1, byte2)),
          (2, Set(byte2)),
          (2, Set(byte3, byte4)),
          (2, Set(byte3, byte4)),
        )).transact

        // Matching values coalesced byteo one Set

        _ <- Ns.bytes(min).query.get.map(_ ==> List(Set(byte1)))
        _ <- Ns.bytes(min(1)).query.get.map(_ ==> List(Set(byte1)))
        _ <- Ns.bytes(min(2)).query.get.map(_ ==> List(Set(byte1, byte2)))
        _ <- Ns.bytes(min(3)).query.get.map(_ ==> List(Set(byte1, byte2, byte3)))

        _ <- Ns.i.a1.bytes(min).query.get.map(_ ==> List(
          (1, Set(byte1)),
          (2, Set(byte2)),
        ))
        // Same as
        _ <- Ns.i.a1.bytes(min(1)).query.get.map(_ ==> List(
          (1, Set(byte1)),
          (2, Set(byte2)),
        ))

        _ <- Ns.i.a1.bytes(min(2)).query.get.map(_ ==> List(
          (1, Set(byte1, byte2)),
          (2, Set(byte2, byte3)),
        ))

        _ <- Ns.i.a1.bytes(min(3)).query.get.map(_ ==> List(
          (1, Set(byte1, byte2)),
          (2, Set(byte2, byte3, byte4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.bytes.insert(List(
          (1, Set(byte1, byte2)),
          (2, Set(byte2)),
          (2, Set(byte3, byte4)),
          (2, Set(byte3, byte4)),
        )).transact

        // Matching values coalesced byteo one Set

        _ <- Ns.bytes(max).query.get.map(_ ==> List(Set(byte4)))
        _ <- Ns.bytes(max(1)).query.get.map(_ ==> List(Set(byte4)))
        _ <- Ns.bytes(max(2)).query.get.map(_ ==> List(Set(byte3, byte4)))
        _ <- Ns.bytes(max(3)).query.get.map(_ ==> List(Set(byte2, byte3, byte4)))

        _ <- Ns.i.a1.bytes(max).query.get.map(_ ==> List(
          (1, Set(byte2)),
          (2, Set(byte4)),
        ))
        // Same as
        _ <- Ns.i.a1.bytes(max(1)).query.get.map(_ ==> List(
          (1, Set(byte2)),
          (2, Set(byte4)),
        ))

        _ <- Ns.i.a1.bytes(max(2)).query.get.map(_ ==> List(
          (1, Set(byte1, byte2)),
          (2, Set(byte3, byte4)),
        ))

        _ <- Ns.i.a1.bytes(max(3)).query.get.map(_ ==> List(
          (1, Set(byte1, byte2)),
          (2, Set(byte2, byte3, byte4)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.bytes.insert(List(
          (1, Set(byte1, byte2)),
          (2, Set(byte2)),
          (2, Set(byte3, byte4)),
          (2, Set(byte3, byte4)),
        )).transact
        all = Set(byte1, byte2, byte3, byte4)
        _ <- Ns.bytes(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.bytes(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.bytes(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.bytes.insert(List(
          (1, Set(byte1, byte2)),
          (2, Set(byte2)),
          (2, Set(byte3, byte4)),
          (2, Set(byte3, byte4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.bytes(count).query.get.map(_ ==> List(7))
        _ <- Ns.bytes(countDistinct).query.get.map(_ ==> List(4))

        _ <- Ns.i.a1.bytes(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 5)
        ))
        _ <- Ns.i.a1.bytes(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}