// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.seq.any

import java.util.Date
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeq_Date_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.dateSeq.insert(List(
          (1, List(date1, date2, date2)),
          (2, List(date2)),
          (2, List(date3, date4, date4)),
          (2, List(date3, date4, date4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.dateSeq(count).query.get.map(_ ==> List(10))
        _ <- Ns.dateSeq(countDistinct).query.get.map(_ ==> List(4))

        // We can sort by counts

        _ <- Ns.i.dateSeq(count).a1.query.get.map(_ ==> List(
          (1, 3),
          (2, 7),
        ))
        _ <- Ns.i.dateSeq(count).d1.query.get.map(_ ==> List(
          (2, 7),
          (1, 3),
        ))

        _ <- Ns.i.dateSeq(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- Ns.i.dateSeq(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.dateSeq.insert(List(
          (1, List(date1, date2, date2)),
          (2, List(date2)),
          (2, List(date3, date4, date4)),
          (2, List(date3, date4, date4)),
        )).transact

        // Minimum value

        _ <- Ns.dateSeq(min).query.get.map(_ ==> List(date1))

        // We can sort by minimum value
        _ <- Ns.i.dateSeq(min).a1.query.get.map(_ ==> List(
          (1, date1),
          (2, date2),
        ))
        _ <- Ns.i.dateSeq(min).d1.query.get.map(_ ==> List(
          (2, date2),
          (1, date1),
        ))

        // Set of minimum values

        _ <- Ns.dateSeq.apply(min(1)).query.get.map(_ ==> List(Set(date1)))
        _ <- Ns.dateSeq(min(2)).query.get.map(_ ==> List(Set(date1, date2)))
        _ <- Ns.dateSeq(min(3)).query.get.map(_ ==> List(Set(date1, date2, date3)))

        _ <- Ns.i.a1.dateSeq(min(1)).query.get.map(_ ==> List(
          (1, Set(date1)),
          (2, Set(date2)),
        ))
        _ <- Ns.i.a1.dateSeq(min(2)).query.get.map(_ ==> List(
          (1, Set(date1, date2)),
          (2, Set(date2, date3)),
        ))
        _ <- Ns.i.a1.dateSeq(min(3)).query.get.map(_ ==> List(
          (1, Set(date1, date2)),
          (2, Set(date2, date3, date4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.dateSeq.insert(List(
          (1, List(date1, date2, date2)),
          (2, List(date2)),
          (2, List(date3, date4, date4)),
          (2, List(date3, date4, date4)),
        )).transact

        // Maximum value

        _ <- Ns.dateSeq(max).query.get.map(_ ==> List(date4))

        // We can sort by maximum value
        _ <- Ns.i.dateSeq(max).a1.query.get.map(_ ==> List(
          (1, date2),
          (2, date4),
        ))
        _ <- Ns.i.dateSeq(max).d1.query.get.map(_ ==> List(
          (2, date4),
          (1, date2),
        ))

        // Set of maximum values

        _ <- Ns.dateSeq(max(1)).query.get.map(_ ==> List(Set(date4)))
        _ <- Ns.dateSeq(max(2)).query.get.map(_ ==> List(Set(date4, date3)))
        _ <- Ns.dateSeq(max(3)).query.get.map(_ ==> List(Set(date4, date3, date2)))

        // Same as
        _ <- Ns.i.a1.dateSeq(max(1)).query.get.map(_ ==> List(
          (1, Set(date2)),
          (2, Set(date4)),
        ))
        _ <- Ns.i.a1.dateSeq(max(2)).query.get.map(_ ==> List(
          (1, Set(date2, date1)),
          (2, Set(date4, date3)),
        ))
        _ <- Ns.i.a1.dateSeq(max(3)).query.get.map(_ ==> List(
          (1, Set(date2, date1)),
          (2, Set(date4, date3, date2)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.dateSeq.insert(List(
          (1, List(date1, date2, date2)),
          (2, List(date2)),
          (2, List(date3, date4, date4)),
          (2, List(date3, date4, date4)),
        )).transact

        all = Set(date1, date2, date3, date4)

        _ <- Ns.dateSeq(sample).query.get.map { rows =>
          val singleSampleValue = rows.head
          all.contains(singleSampleValue) ==> true
        }

        // We can sort by sample value (not checked here)
        _ <- Ns.i.dateSeq(sample).a1.query.get
        _ <- Ns.i.dateSeq(sample).d1.query.get

        // Multiple samples
        _ <- Ns.dateSeq(sample(1)).query.get.map { rows =>
          val sampleSetWithOneValue: Set[Date] = rows.head
          all.contains(sampleSetWithOneValue.head) ==> true
        }
        _ <- Ns.dateSeq(sample(2)).query.get.map { rows =>
          val sampleSetWithTwoValues: Set[Date] = rows.head
          all.intersect(sampleSetWithTwoValues).nonEmpty ==> true
        }
      } yield ()
    }


    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.dateSeq.insert(List(
          (1, List(date1, date2, date2)),
          (2, List(date2)),
          (2, List(date3, date4, date4)),
          (2, List(date3, date4, date4)),
        )).transact

        // Non-aggregated card-many Seq of attribute values coalesce
        _ <- Ns.i.dateSeq.query.get.map(_.sortBy(t => (t._1, t._2.head)) ==> List(
          (1, List(date1, date2, date2)),
          (2, List(date2)),
          (2, List(date3, date4, date4)), // 2 rows with List(date3, date4) coalesced
        ))

        // Use `distinct` keyword to retrieve unique Seqs of Seqs
        _ <- Ns.i.a1.dateSeq(distinct).query.get.map(_ ==> List(
          (1, Set(List(date1, date2, date2))),
          (2, Set(
            List(date2),
            List(date3, date4, date4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.dateSeq(distinct).query.get.map(_ ==> List(
          Set(
            List(date1, date2, date2),
            List(date2),
            List(date3, date4, date4),
          )
        ))
      } yield ()
    }
  }
}