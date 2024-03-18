// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.seq.any

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeq_BigDecimal_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.bigDecimalSeq.insert(List(
          (1, List(bigDecimal1, bigDecimal2, bigDecimal2)),
          (2, List(bigDecimal2)),
          (2, List(bigDecimal3, bigDecimal4, bigDecimal4)),
          (2, List(bigDecimal3, bigDecimal4, bigDecimal4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.bigDecimalSeq(count).query.get.map(_ ==> List(10))
        _ <- Ns.bigDecimalSeq(countDistinct).query.get.map(_ ==> List(4))

        // We can sort by counts

        _ <- Ns.i.bigDecimalSeq(count).a1.query.get.map(_ ==> List(
          (1, 3),
          (2, 7),
        ))
        _ <- Ns.i.bigDecimalSeq(count).d1.query.get.map(_ ==> List(
          (2, 7),
          (1, 3),
        ))

        _ <- Ns.i.bigDecimalSeq(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- Ns.i.bigDecimalSeq(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.bigDecimalSeq.insert(List(
          (1, List(bigDecimal1, bigDecimal2, bigDecimal2)),
          (2, List(bigDecimal2)),
          (2, List(bigDecimal3, bigDecimal4, bigDecimal4)),
          (2, List(bigDecimal3, bigDecimal4, bigDecimal4)),
        )).transact

        // Minimum value

        _ <- Ns.bigDecimalSeq(min).query.get.map(_ ==> List(bigDecimal1))

        // We can sort by minimum value
        _ <- Ns.i.bigDecimalSeq(min).a1.query.get.map(_ ==> List(
          (1, bigDecimal1),
          (2, bigDecimal2),
        ))
        _ <- Ns.i.bigDecimalSeq(min).d1.query.get.map(_ ==> List(
          (2, bigDecimal2),
          (1, bigDecimal1),
        ))

        // Set of minimum values

        _ <- Ns.bigDecimalSeq.apply(min(1)).query.get.map(_ ==> List(Set(bigDecimal1)))
        _ <- Ns.bigDecimalSeq(min(2)).query.get.map(_ ==> List(Set(bigDecimal1, bigDecimal2)))
        _ <- Ns.bigDecimalSeq(min(3)).query.get.map(_ ==> List(Set(bigDecimal1, bigDecimal2, bigDecimal3)))

        _ <- Ns.i.a1.bigDecimalSeq(min(1)).query.get.map(_ ==> List(
          (1, Set(bigDecimal1)),
          (2, Set(bigDecimal2)),
        ))
        _ <- Ns.i.a1.bigDecimalSeq(min(2)).query.get.map(_ ==> List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2, bigDecimal3)),
        ))
        _ <- Ns.i.a1.bigDecimalSeq(min(3)).query.get.map(_ ==> List(
          (1, Set(bigDecimal1, bigDecimal2)),
          (2, Set(bigDecimal2, bigDecimal3, bigDecimal4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.bigDecimalSeq.insert(List(
          (1, List(bigDecimal1, bigDecimal2, bigDecimal2)),
          (2, List(bigDecimal2)),
          (2, List(bigDecimal3, bigDecimal4, bigDecimal4)),
          (2, List(bigDecimal3, bigDecimal4, bigDecimal4)),
        )).transact

        // Maximum value

        _ <- Ns.bigDecimalSeq(max).query.get.map(_ ==> List(bigDecimal4))

        // We can sort by maximum value
        _ <- Ns.i.bigDecimalSeq(max).a1.query.get.map(_ ==> List(
          (1, bigDecimal2),
          (2, bigDecimal4),
        ))
        _ <- Ns.i.bigDecimalSeq(max).d1.query.get.map(_ ==> List(
          (2, bigDecimal4),
          (1, bigDecimal2),
        ))

        // Set of maximum values

        _ <- Ns.bigDecimalSeq(max(1)).query.get.map(_ ==> List(Set(bigDecimal4)))
        _ <- Ns.bigDecimalSeq(max(2)).query.get.map(_ ==> List(Set(bigDecimal4, bigDecimal3)))
        _ <- Ns.bigDecimalSeq(max(3)).query.get.map(_ ==> List(Set(bigDecimal4, bigDecimal3, bigDecimal2)))

        // Same as
        _ <- Ns.i.a1.bigDecimalSeq(max(1)).query.get.map(_ ==> List(
          (1, Set(bigDecimal2)),
          (2, Set(bigDecimal4)),
        ))
        _ <- Ns.i.a1.bigDecimalSeq(max(2)).query.get.map(_ ==> List(
          (1, Set(bigDecimal2, bigDecimal1)),
          (2, Set(bigDecimal4, bigDecimal3)),
        ))
        _ <- Ns.i.a1.bigDecimalSeq(max(3)).query.get.map(_ ==> List(
          (1, Set(bigDecimal2, bigDecimal1)),
          (2, Set(bigDecimal4, bigDecimal3, bigDecimal2)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.bigDecimalSeq.insert(List(
          (1, List(bigDecimal1, bigDecimal2, bigDecimal2)),
          (2, List(bigDecimal2)),
          (2, List(bigDecimal3, bigDecimal4, bigDecimal4)),
          (2, List(bigDecimal3, bigDecimal4, bigDecimal4)),
        )).transact

        all = Set(bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4)

        _ <- Ns.bigDecimalSeq(sample).query.get.map { rows =>
          val singleSampleValue = rows.head
          all.contains(singleSampleValue) ==> true
        }

        // We can sort by sample value (not checked here)
        _ <- Ns.i.bigDecimalSeq(sample).a1.query.get
        _ <- Ns.i.bigDecimalSeq(sample).d1.query.get

        // Multiple samples
        _ <- Ns.bigDecimalSeq(sample(1)).query.get.map { rows =>
          val sampleSetWithOneValue: Set[BigDecimal] = rows.head
          all.contains(sampleSetWithOneValue.head) ==> true
        }
        _ <- Ns.bigDecimalSeq(sample(2)).query.get.map { rows =>
          val sampleSetWithTwoValues: Set[BigDecimal] = rows.head
          all.intersect(sampleSetWithTwoValues).nonEmpty ==> true
        }
      } yield ()
    }


    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.bigDecimalSeq.insert(List(
          (1, List(bigDecimal1, bigDecimal2, bigDecimal2)),
          (2, List(bigDecimal2)),
          (2, List(bigDecimal3, bigDecimal4, bigDecimal4)),
          (2, List(bigDecimal3, bigDecimal4, bigDecimal4)),
        )).transact

        // Non-aggregated card-many Seq of attribute values coalesce
        _ <- Ns.i.bigDecimalSeq.query.get.map(_.sortBy(t => (t._1, t._2.head)) ==> List(
          (1, List(bigDecimal1, bigDecimal2, bigDecimal2)),
          (2, List(bigDecimal2)),
          (2, List(bigDecimal3, bigDecimal4, bigDecimal4)), // 2 rows with List(bigDecimal3, bigDecimal4) coalesced
        ))

        // Use `distinct` keyword to retrieve unique Seqs of Seqs
        _ <- Ns.i.a1.bigDecimalSeq(distinct).query.get.map(_ ==> List(
          (1, Set(List(bigDecimal1, bigDecimal2, bigDecimal2))),
          (2, Set(
            List(bigDecimal2),
            List(bigDecimal3, bigDecimal4, bigDecimal4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.bigDecimalSeq(distinct).query.get.map(_ ==> List(
          Set(
            List(bigDecimal1, bigDecimal2, bigDecimal2),
            List(bigDecimal2),
            List(bigDecimal3, bigDecimal4, bigDecimal4),
          )
        ))
      } yield ()
    }
  }
}