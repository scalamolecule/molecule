// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.set.any

import java.time.LocalDate
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSet_LocalDate_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.localDateSet.insert(List(
          (1, Set(localDate1, localDate2)),
          (2, Set(localDate2)),
          (2, Set(localDate3, localDate4)),
          (2, Set(localDate3, localDate4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.localDateSet(count).query.get.map(_ ==> List(7))
        _ <- Ns.localDateSet(countDistinct).query.get.map(_ ==> List(4))

        // Sort by counts

        _ <- Ns.i.localDateSet(count).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 5),
        ))
        _ <- Ns.i.localDateSet(count).d1.query.get.map(_ ==> List(
          (2, 5),
          (1, 2),
        ))

        _ <- Ns.i.localDateSet(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- Ns.i.localDateSet(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.localDateSet.insert(List(
          (1, Set(localDate1, localDate2)),
          (2, Set(localDate2)),
          (2, Set(localDate3, localDate4)),
          (2, Set(localDate3, localDate4)),
        )).transact

        // Minimum value

        _ <- Ns.localDateSet(min).query.get.map(_ ==> List(localDate1))

        // Sort by minimum value
        _ <- Ns.i.localDateSet(min).a1.query.get.map(_ ==> List(
          (1, localDate1),
          (2, localDate2),
        ))
        _ <- Ns.i.localDateSet(min).d1.query.get.map(_ ==> List(
          (2, localDate2),
          (1, localDate1),
        ))

        // Set of minimum values

        _ <- Ns.localDateSet(min(1)).query.get.map(_ ==> List(Set(localDate1)))
        _ <- Ns.localDateSet(min(2)).query.get.map(_ ==> List(Set(localDate1, localDate2)))
        _ <- Ns.localDateSet(min(3)).query.get.map(_ ==> List(Set(localDate1, localDate2, localDate3)))

        _ <- Ns.i.a1.localDateSet(min(1)).query.get.map(_ ==> List(
          (1, Set(localDate1)),
          (2, Set(localDate2)),
        ))
        _ <- Ns.i.a1.localDateSet(min(2)).query.get.map(_ ==> List(
          (1, Set(localDate1, localDate2)),
          (2, Set(localDate2, localDate3)),
        ))
        _ <- Ns.i.a1.localDateSet(min(3)).query.get.map(_ ==> List(
          (1, Set(localDate1, localDate2)),
          (2, Set(localDate2, localDate3, localDate4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.localDateSet.insert(List(
          (1, Set(localDate1, localDate2)),
          (2, Set(localDate2)),
          (2, Set(localDate3, localDate4)),
          (2, Set(localDate3, localDate4)),
        )).transact

        // Maximum value

        _ <- Ns.localDateSet(max).query.get.map(_ ==> List(localDate4))

        // Sort by maximum value
        _ <- Ns.i.localDateSet(max).a1.query.get.map(_ ==> List(
          (1, localDate2),
          (2, localDate4),
        ))
        _ <- Ns.i.localDateSet(max).d1.query.get.map(_ ==> List(
          (2, localDate4),
          (1, localDate2),
        ))

        // Set of maximum values

        _ <- Ns.localDateSet(max(1)).query.get.map(_ ==> List(Set(localDate4)))
        _ <- Ns.localDateSet(max(2)).query.get.map(_ ==> List(Set(localDate3, localDate4)))
        _ <- Ns.localDateSet(max(3)).query.get.map(_ ==> List(Set(localDate2, localDate3, localDate4)))

        _ <- Ns.i.a1.localDateSet(max(1)).query.get.map(_ ==> List(
          (1, Set(localDate2)),
          (2, Set(localDate4)),
        ))
        _ <- Ns.i.a1.localDateSet(max(2)).query.get.map(_ ==> List(
          (1, Set(localDate1, localDate2)),
          (2, Set(localDate3, localDate4)),
        ))
        _ <- Ns.i.a1.localDateSet(max(3)).query.get.map(_ ==> List(
          (1, Set(localDate1, localDate2)),
          (2, Set(localDate2, localDate3, localDate4)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.localDateSet.insert(List(
          (1, Set(localDate1, localDate2)),
          (2, Set(localDate2)),
          (2, Set(localDate3, localDate4)),
          (2, Set(localDate3, localDate4)),
        )).transact

        all = Set(localDate1, localDate2, localDate3, localDate4)

        _ <- Ns.localDateSet(sample).query.get.map { rows =>
          val singleSampleValue: LocalDate = rows.head
          all.contains(singleSampleValue) ==> true
        }

        // Sort by sample value (not checked here)
        _ <- Ns.i.localDateSet(sample).a1.query.get
        _ <- Ns.i.localDateSet(sample).d1.query.get

        // Multiple samples
        _ <- Ns.localDateSet(sample(1)).query.get.map { rows =>
          val sampleSetWithOneValue: Set[LocalDate] = rows.head
          all.contains(sampleSetWithOneValue.head) ==> true
        }
        _ <- Ns.localDateSet(sample(2)).query.get.map { rows =>
          val sampleSetWithTwoValues: Set[LocalDate] = rows.head
          all.intersect(sampleSetWithTwoValues).nonEmpty ==> true
        }
      } yield ()
    }


    "distinct sets of values" - types { implicit conn =>
      for {
        _ <- Ns.i.localDateSet.insert(List(
          (1, Set(localDate1, localDate2)),
          (2, Set(localDate2)),
          (2, Set(localDate3, localDate4)),
          (2, Set(localDate3, localDate4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.localDateSet.query.get.map(_ ==> List(
          (1, Set(localDate1, localDate2)),
          (2, Set(localDate2, localDate3, localDate4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.localDateSet(distinct).query.get.map(_ ==> List(
          (1, Set(Set(localDate1, localDate2))),
          (2, Set(
            Set(localDate2),
            Set(localDate3, localDate4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.localDateSet(distinct).query.get.map(_ ==> List(
          Set(
            Set(localDate1, localDate2),
            Set(localDate2),
            Set(localDate3, localDate4),
          )
        ))
      } yield ()
    }
  }
}