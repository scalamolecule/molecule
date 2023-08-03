// GENERATED CODE ********************************
package molecule.coreTests.test.aggr.one.any

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrOne_Char_ extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.char.insert(List(
          (1, char1),
          (2, char2),
          (2, char2),
          (2, char3),
        )).transact

        _ <- Ns.i.char.a1.query.get.map(_ ==> List(
          (1, char1),
          (2, char2), // 2 rows coalesced
          (2, char3),
        ))

        // Distinct values are returned in a Set
        _ <- Ns.i.a1.char(distinct).query.get.map(_ ==> List(
          (1, Set(char1)),
          (2, Set(char2, char3)),
        ))

        _ <- Ns.char(distinct).query.get.map(_.head ==> Set(
          char1, char2, char3
        ))
      } yield ()
    }


    "min/max" - types { implicit conn =>
      for {
        _ <- Ns.i.char.insert(
          (1, char1),
          (1, char2),
          (1, char3),
          (2, char4),
          (2, char5),
          (2, char6),
        ).transact

        _ <- Ns.char(min).query.get.map(_ ==> List(char1))
        _ <- Ns.char(min(1)).query.get.map(_ ==> List(Set(char1)))
        _ <- Ns.char(min(2)).query.get.map(_ ==> List(Set(char1, char2)))

        _ <- Ns.char(max).query.get.map(_ ==> List(char6))
        _ <- Ns.char(max(1)).query.get.map(_ ==> List(Set(char6)))
        _ <- Ns.char(max(2)).query.get.map(_ ==> List(Set(char5, char6)))

        _ <- Ns.i.char(min(2)).query.get.map(_ ==> List(
          (1, Set(char1, char2)),
          (2, Set(char4, char5))
        ))

        _ <- Ns.i.char(max(2)).query.get.map(_ ==> List(
          (1, Set(char2, char3)),
          (2, Set(char5, char6))
        ))

        _ <- Ns.i.char(min(2)).char(max(2)).query.get.map(_ ==> List(
          (1, Set(char1, char2), Set(char2, char3)),
          (2, Set(char4, char5), Set(char5, char6))
        ))
      } yield ()
    }


    "rand" - types { implicit conn =>
      for {
        _ <- Ns.char.insert(List(char1, char2, char3)).transact
        all = Set(char1, char2, char3, char4)
        _ <- Ns.char(rand).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.char(rand(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.char(rand(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.char.insert(List(char1, char2, char3)).transact
        all = Set(char1, char2, char3, char4)
        _ <- Ns.char(sample).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.char(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.char(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count, countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.char.insert(List(
          (1, char1),
          (2, char2),
          (2, char2),
          (2, char3),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.char(count).query.get.map(_ ==> List(4))
        _ <- Ns.char(countDistinct).query.get.map(_ ==> List(3))

        _ <- Ns.i.a1.char(count).query.get.map(_ ==> List(
          (1, 1),
          (2, 3)
        ))
        _ <- Ns.i.a1.char(countDistinct).query.get.map(_ ==> List(
          (1, 1),
          (2, 2)
        ))
      } yield ()
    }
  }
}