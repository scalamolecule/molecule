// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.seq.any

import java.time.LocalDate
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeq_LocalDate_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.localDateSeq.insert(List(
          (1, List(localDate1, localDate2, localDate2)),
          (2, List(localDate2)),
          (2, List(localDate3, localDate4, localDate4)),
          (2, List(localDate3, localDate4, localDate4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.localDateSeq(count).query.get.map(_ ==> List(10))
        _ <- Ns.localDateSeq(countDistinct).query.get.map(_ ==> List(4))

        // We can sort by counts

        _ <- Ns.i.localDateSeq(count).a1.query.get.map(_ ==> List(
          (1, 3),
          (2, 7),
        ))
        _ <- Ns.i.localDateSeq(count).d1.query.get.map(_ ==> List(
          (2, 7),
          (1, 3),
        ))

        _ <- Ns.i.localDateSeq(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- Ns.i.localDateSeq(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.localDateSeq.insert(List(
          (1, List(localDate1, localDate2, localDate2)),
          (2, List(localDate2)),
          (2, List(localDate3, localDate4, localDate4)),
          (2, List(localDate3, localDate4, localDate4)),
        )).transact

        // Minimum value

        _ <- Ns.localDateSeq(min).query.get.map(_ ==> List(localDate1))

        // We can sort by minimum value
        _ <- Ns.i.localDateSeq(min).a1.query.get.map(_ ==> List(
          (1, localDate1),
          (2, localDate2),
        ))
        _ <- Ns.i.localDateSeq(min).d1.query.get.map(_ ==> List(
          (2, localDate2),
          (1, localDate1),
        ))

        // Set of minimum values

        _ <- Ns.localDateSeq.apply(min(1)).query.get.map(_ ==> List(Set(localDate1)))
        _ <- Ns.localDateSeq(min(2)).query.get.map(_ ==> List(Set(localDate1, localDate2)))
        _ <- Ns.localDateSeq(min(3)).query.get.map(_ ==> List(Set(localDate1, localDate2, localDate3)))

        _ <- Ns.i.a1.localDateSeq(min(1)).query.get.map(_ ==> List(
          (1, Set(localDate1)),
          (2, Set(localDate2)),
        ))
        _ <- Ns.i.a1.localDateSeq(min(2)).query.get.map(_ ==> List(
          (1, Set(localDate1, localDate2)),
          (2, Set(localDate2, localDate3)),
        ))
        _ <- Ns.i.a1.localDateSeq(min(3)).query.get.map(_ ==> List(
          (1, Set(localDate1, localDate2)),
          (2, Set(localDate2, localDate3, localDate4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.localDateSeq.insert(List(
          (1, List(localDate1, localDate2, localDate2)),
          (2, List(localDate2)),
          (2, List(localDate3, localDate4, localDate4)),
          (2, List(localDate3, localDate4, localDate4)),
        )).transact

        // Maximum value

        _ <- Ns.localDateSeq(max).query.get.map(_ ==> List(localDate4))

        // We can sort by maximum value
        _ <- Ns.i.localDateSeq(max).a1.query.get.map(_ ==> List(
          (1, localDate2),
          (2, localDate4),
        ))
        _ <- Ns.i.localDateSeq(max).d1.query.get.map(_ ==> List(
          (2, localDate4),
          (1, localDate2),
        ))

        // Set of maximum values

        _ <- Ns.localDateSeq(max(1)).query.get.map(_ ==> List(Set(localDate4)))
        _ <- Ns.localDateSeq(max(2)).query.get.map(_ ==> List(Set(localDate4, localDate3)))
        _ <- Ns.localDateSeq(max(3)).query.get.map(_ ==> List(Set(localDate4, localDate3, localDate2)))

        // Same as
        _ <- Ns.i.a1.localDateSeq(max(1)).query.get.map(_ ==> List(
          (1, Set(localDate2)),
          (2, Set(localDate4)),
        ))
        _ <- Ns.i.a1.localDateSeq(max(2)).query.get.map(_ ==> List(
          (1, Set(localDate2, localDate1)),
          (2, Set(localDate4, localDate3)),
        ))
        _ <- Ns.i.a1.localDateSeq(max(3)).query.get.map(_ ==> List(
          (1, Set(localDate2, localDate1)),
          (2, Set(localDate4, localDate3, localDate2)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.localDateSeq.insert(List(
          (1, List(localDate1, localDate2, localDate2)),
          (2, List(localDate2)),
          (2, List(localDate3, localDate4, localDate4)),
          (2, List(localDate3, localDate4, localDate4)),
        )).transact

        all = Set(localDate1, localDate2, localDate3, localDate4)

        _ <- Ns.localDateSeq(sample).query.get.map { rows =>
          val singleSampleValue = rows.head
          all.contains(singleSampleValue) ==> true
        }

        // We can sort by sample value (not checked here)
        _ <- Ns.i.localDateSeq(sample).a1.query.get
        _ <- Ns.i.localDateSeq(sample).d1.query.get

        // Multiple samples
        _ <- Ns.localDateSeq(sample(1)).query.get.map { rows =>
          val sampleSetWithOneValue: Set[LocalDate] = rows.head
          all.contains(sampleSetWithOneValue.head) ==> true
        }
        _ <- Ns.localDateSeq(sample(2)).query.get.map { rows =>
          val sampleSetWithTwoValues: Set[LocalDate] = rows.head
          all.intersect(sampleSetWithTwoValues).nonEmpty ==> true
        }
      } yield ()
    }


    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.localDateSeq.insert(List(
          (1, List(localDate1, localDate2, localDate2)),
          (2, List(localDate2)),
          (2, List(localDate3, localDate4, localDate4)),
          (2, List(localDate3, localDate4, localDate4)),
        )).transact

        // Non-aggregated card-many Seq of attribute values coalesce
        _ <- Ns.i.localDateSeq.query.get.map(_.sortBy(t => (t._1, t._2.head)) ==> List(
          (1, List(localDate1, localDate2, localDate2)),
          (2, List(localDate2)),
          (2, List(localDate3, localDate4, localDate4)), // 2 rows with List(localDate3, localDate4) coalesced
        ))

        // Use `distinct` keyword to retrieve unique Seqs of Seqs
        _ <- Ns.i.a1.localDateSeq(distinct).query.get.map(_ ==> List(
          (1, Set(List(localDate1, localDate2, localDate2))),
          (2, Set(
            List(localDate2),
            List(localDate3, localDate4, localDate4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.localDateSeq(distinct).query.get.map(_ ==> List(
          Set(
            List(localDate1, localDate2, localDate2),
            List(localDate2),
            List(localDate3, localDate4, localDate4),
          )
        ))
      } yield ()
    }
  }
}