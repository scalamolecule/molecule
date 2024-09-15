// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.any

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait Aggr_Byte_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.byte.insert(List(
          (1, byte1),
          (2, byte2),
          (2, byte2),
          (2, byte3),
        )).transact

        _ <- Ns.i.byte.a1.query.get.map(_ ==> List(
          (1, byte1),
          (2, byte2), // 2 rows coalesced
          (2, byte3),
        ))

        // Distinct values are returned in a Set
        _ <- Ns.i.a1.byte(distinct).query.get.map(_ ==> List(
          (1, Set(byte1)),
          (2, Set(byte2, byte3)),
        ))

        _ <- Ns.byte(distinct).query.get.map(_.head ==> Set(
          byte1, byte2, byte3
        ))
      } yield ()
    }


    "min/max" - types { implicit conn =>
      for {
        _ <- Ns.i.byte.insert(
          (1, byte1),
          (1, byte2),
          (1, byte3),
          (2, byte4),
          (2, byte5),
          (2, byte6),
        ).transact

        _ <- Ns.byte(min).query.get.map(_ ==> List(byte1))
        _ <- Ns.byte(max).query.get.map(_ ==> List(byte6))
        _ <- Ns.byte(min).byte(max).query.get.map(_ ==> List((byte1, byte6)))

        _ <- Ns.i.a1.byte(min).query.get.map(_ ==> List(
          (1, byte1),
          (2, byte4)
        ))

        _ <- Ns.i.a1.byte(max).query.get.map(_ ==> List(
          (1, byte3),
          (2, byte6)
        ))

        _ <- Ns.i.a1.byte(min).byte(max).query.get.map(_ ==> List(
          (1, byte1, byte3),
          (2, byte4, byte6)
        ))
      } yield ()
    }

    "min/max n" - types { implicit conn =>
      for {
        _ <- Ns.i.byte.insert(
          (1, byte1),
          (1, byte2),
          (1, byte3),
          (2, byte4),
          (2, byte5),
          (2, byte6),
          (2, byte6), // (make sure grouped values coalesce)
        ).transact

        _ <- Ns.byte(min(1)).query.get.map(_ ==> List(Set(byte1)))
        _ <- Ns.byte(min(2)).query.get.map(_ ==> List(Set(byte1, byte2)))

        _ <- Ns.byte(max(1)).query.get.map(_ ==> List(Set(byte6)))
        _ <- Ns.byte(max(2)).query.get.map(_ ==> List(Set(byte5, byte6)))

        _ <- Ns.i.a1.byte(min(2)).query.get.map(_ ==> List(
          (1, Set(byte1, byte2)),
          (2, Set(byte4, byte5))
        ))

        _ <- Ns.i.a1.byte(max(2)).query.get.map(_ ==> List(
          (1, Set(byte2, byte3)),
          (2, Set(byte5, byte6))
        ))

        _ <- Ns.i.a1.byte(min(2)).byte(max(2)).query.get.map(_ ==> List(
          (1, Set(byte1, byte2), Set(byte2, byte3)),
          (2, Set(byte4, byte5), Set(byte5, byte6))
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      val all = Set(byte1, byte2, byte3, byte4)
      for {
        _ <- Ns.byte.insert(List(byte1, byte2, byte3)).transact
        _ <- Ns.byte(sample).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.byte(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.byte(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count" - types { implicit conn =>
      for {
        _ <- Ns.i.byte.insert(List(
          (1, byte1),
          (2, byte2),
          (2, byte2),
          (2, byte3),
        )).transact

        _ <- Ns.byte(count).query.get.map(_ ==> List(4))
        _ <- Ns.i.a1.byte(count).query.get.map(_ ==> List(
          (1, 1),
          (2, 3)
        ))

        _ <- Ns.byte(countDistinct).query.get.map(_ ==> List(3))
        _ <- Ns.i.a1.byte(countDistinct).query.get.map(_ ==> List(
          (1, 1),
          (2, 2)
        ))
      } yield ()
    }
  }
}