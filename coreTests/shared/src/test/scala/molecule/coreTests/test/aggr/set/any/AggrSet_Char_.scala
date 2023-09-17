// GENERATED CODE ********************************
package molecule.coreTests.test.aggr.set.any

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
        _ <- Ns.i.chars.insert(List(
          (1, Set(char1, char2)),
          (2, Set(char2, char3)),
          (2, Set(char3, char4)),
          (2, Set(char3, char4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.chars.query.get.map(_ ==> List(
          (1, Set(char1, char2)),
          (2, Set(char2, char3, char4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.chars(distinct).query.get.map(_ ==> List(
          (1, Set(Set(char1, char2))),
          (2, Set(
            Set(char2, char3),
            Set(char3, char4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.chars(distinct).query.get.map(_ ==> List(
          Set(
            Set(char1, char2),
            Set(char2, char3),
            Set(char3, char4),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.chars.insert(List(
          (1, Set(char1, char2)),
          (2, Set(char2, char3)),
          (2, Set(char3, char4)),
          (2, Set(char3, char4)),
        )).transact

        _ <- Ns.chars(min).query.get.map(_ ==> List(Set(char1)))
        _ <- Ns.chars(min(1)).query.get.map(_ ==> List(Set(char1)))
        _ <- Ns.chars(min(2)).query.get.map(_ ==> List(Set(char1, char2)))

        _ <- Ns.i.a1.chars(min).query.get.map(_ ==> List(
          (1, Set(char1)),
          (2, Set(char2)),
        ))
        // Same as
        _ <- Ns.i.a1.chars(min(1)).query.get.map(_ ==> List(
          (1, Set(char1)),
          (2, Set(char2)),
        ))

        _ <- Ns.i.a1.chars(min(2)).query.get.map(_ ==> List(
          (1, Set(char1, char2)),
          (2, Set(char2, char3)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.chars.insert(List(
          (1, Set(char1, char2)),
          (2, Set(char2, char3)),
          (2, Set(char3, char4)),
          (2, Set(char3, char4)),
        )).transact

        _ <- Ns.chars(max).query.get.map(_ ==> List(Set(char4)))
        _ <- Ns.chars(max(1)).query.get.map(_ ==> List(Set(char4)))
        _ <- Ns.chars(max(2)).query.get.map(_ ==> List(Set(char3, char4)))

        _ <- Ns.i.a1.chars(max).query.get.map(_ ==> List(
          (1, Set(char2)),
          (2, Set(char4)),
        ))
        // Same as
        _ <- Ns.i.a1.chars(max(1)).query.get.map(_ ==> List(
          (1, Set(char2)),
          (2, Set(char4)),
        ))

        _ <- Ns.i.a1.chars(max(2)).query.get.map(_ ==> List(
          (1, Set(char1, char2)),
          (2, Set(char3, char4)),
        ))
      } yield ()
    }


    "rand" - types { implicit conn =>
      for {
        _ <- Ns.i.chars.insert(List(
          (1, Set(char1, char2)),
          (2, Set(char2, char3)),
          (2, Set(char3, char4)),
          (2, Set(char3, char4)),
        )).transact
        all = Set(char1, char2, char3, char4)
        _ <- Ns.chars(rand).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.chars(rand(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.chars(rand(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.chars.insert(List(
          (1, Set(char1, char2)),
          (2, Set(char2, char3)),
          (2, Set(char3, char4)),
          (2, Set(char3, char4)),
        )).transact
        all = Set(char1, char2, char3, char4)
        _ <- Ns.chars(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.chars(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.chars(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.chars.insert(List(
          (1, Set(char1, char2)),
          (2, Set(char2, char3)),
          (2, Set(char3, char4)),
          (2, Set(char3, char4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.chars(count).query.get.map(_ ==> List(8))
        _ <- Ns.chars(countDistinct).query.get.map(_ ==> List(4))

        _ <- Ns.i.a1.chars(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 6)
        ))
        _ <- Ns.i.a1.chars(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}