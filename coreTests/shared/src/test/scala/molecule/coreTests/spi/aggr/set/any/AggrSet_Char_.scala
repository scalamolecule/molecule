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

        // Sort by counts

        _ <- Ns.i.charSet(count).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 5),
        ))
        _ <- Ns.i.charSet(count).d1.query.get.map(_ ==> List(
          (2, 5),
          (1, 2),
        ))

        _ <- Ns.i.charSet(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- Ns.i.charSet(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
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

        // Minimum value

        _ <- Ns.charSet(min).query.get.map(_ ==> List(char1))

        // Sort by minimum value
        _ <- Ns.i.charSet(min).a1.query.get.map(_ ==> List(
          (1, char1),
          (2, char2),
        ))
        _ <- Ns.i.charSet(min).d1.query.get.map(_ ==> List(
          (2, char2),
          (1, char1),
        ))

        // Set of minimum values

        _ <- Ns.charSet(min(1)).query.get.map(_ ==> List(Set(char1)))
        _ <- Ns.charSet(min(2)).query.get.map(_ ==> List(Set(char1, char2)))
        _ <- Ns.charSet(min(3)).query.get.map(_ ==> List(Set(char1, char2, char3)))

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

        // Maximum value

        _ <- Ns.charSet(max).query.get.map(_ ==> List(char4))

        // Sort by maximum value
        _ <- Ns.i.charSet(max).a1.query.get.map(_ ==> List(
          (1, char2),
          (2, char4),
        ))
        _ <- Ns.i.charSet(max).d1.query.get.map(_ ==> List(
          (2, char4),
          (1, char2),
        ))

        // Set of maximum values

        _ <- Ns.charSet(max(1)).query.get.map(_ ==> List(Set(char4)))
        _ <- Ns.charSet(max(2)).query.get.map(_ ==> List(Set(char3, char4)))
        _ <- Ns.charSet(max(3)).query.get.map(_ ==> List(Set(char2, char3, char4)))

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

        _ <- Ns.charSet(sample).query.get.map { rows =>
          val singleSampleValue: Char = rows.head
          all.contains(singleSampleValue) ==> true
        }

        // Sort by sample value (not checked here)
        _ <- Ns.i.charSet(sample).a1.query.get
        _ <- Ns.i.charSet(sample).d1.query.get

        // Multiple samples
        _ <- Ns.charSet(sample(1)).query.get.map { rows =>
          val sampleSetWithOneValue: Set[Char] = rows.head
          all.contains(sampleSetWithOneValue.head) ==> true
        }
        _ <- Ns.charSet(sample(2)).query.get.map { rows =>
          val sampleSetWithTwoValues: Set[Char] = rows.head
          all.intersect(sampleSetWithTwoValues).nonEmpty ==> true
        }
      } yield ()
    }


    "distinct sets of values" - types { implicit conn =>
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
  }
}