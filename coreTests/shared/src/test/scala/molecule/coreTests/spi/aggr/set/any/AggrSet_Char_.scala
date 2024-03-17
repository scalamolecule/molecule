// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.set.any

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSet_Char_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.charSet.insert(List(
          (1, Set(char1, char2)),
          (2, Set(char2)),
          (2, Set(char3, char4)),
          (2, Set(char3, char4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.charSet.query.get.map(_ ==> List(
          (1, Set(char1, char2)),
          (2, Set(char2, char3, char4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.charSet(distinct).query.get.map(_ ==> List(
          (1, Set(Set(char1, char2))),
          (2, Set(
            Set(char2),
            Set(char3, char4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.charSet(distinct).query.get.map(_ ==> List(
          Set(
            Set(char1, char2),
            Set(char2),
            Set(char3, char4),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.charSet.insert(List(
          (1, Set(char1, char2)),
          (2, Set(char2)),
          (2, Set(char3, char4)),
          (2, Set(char3, char4)),
        )).transact

        // Matching values coalesced charo one Set

        _ <- Ns.charSet(min).query.get.map(_ ==> List(Set(char1)))
        _ <- Ns.charSet(min(1)).query.get.map(_ ==> List(Set(char1)))
        _ <- Ns.charSet(min(2)).query.get.map(_ ==> List(Set(char1, char2)))
        _ <- Ns.charSet(min(3)).query.get.map(_ ==> List(Set(char1, char2, char3)))

        _ <- Ns.i.a1.charSet(min).query.get.map(_ ==> List(
          (1, Set(char1)),
          (2, Set(char2)),
        ))
        // Same as
        _ <- Ns.i.a1.charSet(min(1)).query.get.map(_ ==> List(
          (1, Set(char1)),
          (2, Set(char2)),
        ))

        _ <- Ns.i.a1.charSet(min(2)).query.get.map(_ ==> List(
          (1, Set(char1, char2)),
          (2, Set(char2, char3)),
        ))

        _ <- Ns.i.a1.charSet(min(3)).query.get.map(_ ==> List(
          (1, Set(char1, char2)),
          (2, Set(char2, char3, char4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.charSet.insert(List(
          (1, Set(char1, char2)),
          (2, Set(char2)),
          (2, Set(char3, char4)),
          (2, Set(char3, char4)),
        )).transact

        // Matching values coalesced charo one Set

        _ <- Ns.charSet(max).query.get.map(_ ==> List(Set(char4)))
        _ <- Ns.charSet(max(1)).query.get.map(_ ==> List(Set(char4)))
        _ <- Ns.charSet(max(2)).query.get.map(_ ==> List(Set(char3, char4)))
        _ <- Ns.charSet(max(3)).query.get.map(_ ==> List(Set(char2, char3, char4)))

        _ <- Ns.i.a1.charSet(max).query.get.map(_ ==> List(
          (1, Set(char2)),
          (2, Set(char4)),
        ))
        // Same as
        _ <- Ns.i.a1.charSet(max(1)).query.get.map(_ ==> List(
          (1, Set(char2)),
          (2, Set(char4)),
        ))

        _ <- Ns.i.a1.charSet(max(2)).query.get.map(_ ==> List(
          (1, Set(char1, char2)),
          (2, Set(char3, char4)),
        ))

        _ <- Ns.i.a1.charSet(max(3)).query.get.map(_ ==> List(
          (1, Set(char1, char2)),
          (2, Set(char2, char3, char4)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.charSet.insert(List(
          (1, Set(char1, char2)),
          (2, Set(char2)),
          (2, Set(char3, char4)),
          (2, Set(char3, char4)),
        )).transact
        all = Set(char1, char2, char3, char4)
        _ <- Ns.charSet(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.charSet(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.charSet(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.charSet.insert(List(
          (1, Set(char1, char2)),
          (2, Set(char2)),
          (2, Set(char3, char4)),
          (2, Set(char3, char4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.charSet(count).query.get.map(_ ==> List(7))
        _ <- Ns.charSet(countDistinct).query.get.map(_ ==> List(4))

        _ <- Ns.i.a1.charSet(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 5)
        ))
        _ <- Ns.i.a1.charSet(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}