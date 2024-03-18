// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.seq.any

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeq_BigInt_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.bigIntSeq.insert(List(
          (1, List(bigInt1, bigInt2, bigInt2)),
          (2, List(bigInt2)),
          (2, List(bigInt3, bigInt4, bigInt4)),
          (2, List(bigInt3, bigInt4, bigInt4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.bigIntSeq(count).query.get.map(_ ==> List(10))
        _ <- Ns.bigIntSeq(countDistinct).query.get.map(_ ==> List(4))

        // We can sort by counts

        _ <- Ns.i.bigIntSeq(count).a1.query.get.map(_ ==> List(
          (1, 3),
          (2, 7),
        ))
        _ <- Ns.i.bigIntSeq(count).d1.query.get.map(_ ==> List(
          (2, 7),
          (1, 3),
        ))

        _ <- Ns.i.bigIntSeq(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- Ns.i.bigIntSeq(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.bigIntSeq.insert(List(
          (1, List(bigInt1, bigInt2, bigInt2)),
          (2, List(bigInt2)),
          (2, List(bigInt3, bigInt4, bigInt4)),
          (2, List(bigInt3, bigInt4, bigInt4)),
        )).transact

        // Minimum value

        _ <- Ns.bigIntSeq(min).query.get.map(_ ==> List(bigInt1))

        // We can sort by minimum value
        _ <- Ns.i.bigIntSeq(min).a1.query.get.map(_ ==> List(
          (1, bigInt1),
          (2, bigInt2),
        ))
        _ <- Ns.i.bigIntSeq(min).d1.query.get.map(_ ==> List(
          (2, bigInt2),
          (1, bigInt1),
        ))

        // Set of minimum values

        _ <- Ns.bigIntSeq.apply(min(1)).query.get.map(_ ==> List(Set(bigInt1)))
        _ <- Ns.bigIntSeq(min(2)).query.get.map(_ ==> List(Set(bigInt1, bigInt2)))
        _ <- Ns.bigIntSeq(min(3)).query.get.map(_ ==> List(Set(bigInt1, bigInt2, bigInt3)))

        _ <- Ns.i.a1.bigIntSeq(min(1)).query.get.map(_ ==> List(
          (1, Set(bigInt1)),
          (2, Set(bigInt2)),
        ))
        _ <- Ns.i.a1.bigIntSeq(min(2)).query.get.map(_ ==> List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2, bigInt3)),
        ))
        _ <- Ns.i.a1.bigIntSeq(min(3)).query.get.map(_ ==> List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2, bigInt3, bigInt4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.bigIntSeq.insert(List(
          (1, List(bigInt1, bigInt2, bigInt2)),
          (2, List(bigInt2)),
          (2, List(bigInt3, bigInt4, bigInt4)),
          (2, List(bigInt3, bigInt4, bigInt4)),
        )).transact

        // Maximum value

        _ <- Ns.bigIntSeq(max).query.get.map(_ ==> List(bigInt4))

        // We can sort by maximum value
        _ <- Ns.i.bigIntSeq(max).a1.query.get.map(_ ==> List(
          (1, bigInt2),
          (2, bigInt4),
        ))
        _ <- Ns.i.bigIntSeq(max).d1.query.get.map(_ ==> List(
          (2, bigInt4),
          (1, bigInt2),
        ))

        // Set of maximum values

        _ <- Ns.bigIntSeq(max(1)).query.get.map(_ ==> List(Set(bigInt4)))
        _ <- Ns.bigIntSeq(max(2)).query.get.map(_ ==> List(Set(bigInt4, bigInt3)))
        _ <- Ns.bigIntSeq(max(3)).query.get.map(_ ==> List(Set(bigInt4, bigInt3, bigInt2)))

        // Same as
        _ <- Ns.i.a1.bigIntSeq(max(1)).query.get.map(_ ==> List(
          (1, Set(bigInt2)),
          (2, Set(bigInt4)),
        ))
        _ <- Ns.i.a1.bigIntSeq(max(2)).query.get.map(_ ==> List(
          (1, Set(bigInt2, bigInt1)),
          (2, Set(bigInt4, bigInt3)),
        ))
        _ <- Ns.i.a1.bigIntSeq(max(3)).query.get.map(_ ==> List(
          (1, Set(bigInt2, bigInt1)),
          (2, Set(bigInt4, bigInt3, bigInt2)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.bigIntSeq.insert(List(
          (1, List(bigInt1, bigInt2, bigInt2)),
          (2, List(bigInt2)),
          (2, List(bigInt3, bigInt4, bigInt4)),
          (2, List(bigInt3, bigInt4, bigInt4)),
        )).transact

        all = Set(bigInt1, bigInt2, bigInt3, bigInt4)

        _ <- Ns.bigIntSeq(sample).query.get.map { rows =>
          val singleSampleValue = rows.head
          all.contains(singleSampleValue) ==> true
        }

        // We can sort by sample value (not checked here)
        _ <- Ns.i.bigIntSeq(sample).a1.query.get
        _ <- Ns.i.bigIntSeq(sample).d1.query.get

        // Multiple samples
        _ <- Ns.bigIntSeq(sample(1)).query.get.map { rows =>
          val sampleSetWithOneValue: Set[BigInt] = rows.head
          all.contains(sampleSetWithOneValue.head) ==> true
        }
        _ <- Ns.bigIntSeq(sample(2)).query.get.map { rows =>
          val sampleSetWithTwoValues: Set[BigInt] = rows.head
          all.intersect(sampleSetWithTwoValues).nonEmpty ==> true
        }
      } yield ()
    }


    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.bigIntSeq.insert(List(
          (1, List(bigInt1, bigInt2, bigInt2)),
          (2, List(bigInt2)),
          (2, List(bigInt3, bigInt4, bigInt4)),
          (2, List(bigInt3, bigInt4, bigInt4)),
        )).transact

        // Non-aggregated card-many Seq of attribute values coalesce
        _ <- Ns.i.bigIntSeq.query.get.map(_.sortBy(t => (t._1, t._2.head)) ==> List(
          (1, List(bigInt1, bigInt2, bigInt2)),
          (2, List(bigInt2)),
          (2, List(bigInt3, bigInt4, bigInt4)), // 2 rows with List(bigInt3, bigInt4) coalesced
        ))

        // Use `distinct` keyword to retrieve unique Seqs of Seqs
        _ <- Ns.i.a1.bigIntSeq(distinct).query.get.map(_ ==> List(
          (1, Set(List(bigInt1, bigInt2, bigInt2))),
          (2, Set(
            List(bigInt2),
            List(bigInt3, bigInt4, bigInt4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.bigIntSeq(distinct).query.get.map(_ ==> List(
          Set(
            List(bigInt1, bigInt2, bigInt2),
            List(bigInt2),
            List(bigInt3, bigInt4, bigInt4),
          )
        ))
      } yield ()
    }
  }
}