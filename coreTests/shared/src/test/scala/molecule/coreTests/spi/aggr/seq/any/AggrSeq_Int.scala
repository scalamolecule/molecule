package molecule.coreTests.spi.aggr.seq.any

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeq_Int extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.intSeq.insert(List(
          (1, List(int1, int2, int2)),
          (2, List(int2)),
          (2, List(int3, int4, int4)),
          (2, List(int3, int4, int4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.intSeq(count).query.get.map(_ ==> List(10))
        _ <- Ns.intSeq(countDistinct).query.get.map(_ ==> List(4))

        // We can sort by counts

        _ <- Ns.i.intSeq(count).a1.query.get.map(_ ==> List(
          (1, 3),
          (2, 7),
        ))
        _ <- Ns.i.intSeq(count).d1.query.get.map(_ ==> List(
          (2, 7),
          (1, 3),
        ))

        _ <- Ns.i.intSeq(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- Ns.i.intSeq(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.intSeq.insert(List(
          (1, List(int1, int2, int2)),
          (2, List(int2)),
          (2, List(int3, int4, int4)),
          (2, List(int3, int4, int4)),
        )).transact

        // Minimum value

        _ <- Ns.intSeq(min).query.get.map(_ ==> List(int1))

        // We can sort by minimum value
        _ <- Ns.i.intSeq(min).a1.query.get.map(_ ==> List(
          (1, int1),
          (2, int2),
        ))
        _ <- Ns.i.intSeq(min).d1.query.get.map(_ ==> List(
          (2, int2),
          (1, int1),
        ))

        // Set of minimum values

        _ <- Ns.intSeq.apply(min(1)).query.get.map(_ ==> List(Set(int1)))
        _ <- Ns.intSeq(min(2)).query.get.map(_ ==> List(Set(int1, int2)))
        _ <- Ns.intSeq(min(3)).query.get.map(_ ==> List(Set(int1, int2, int3)))

        _ <- Ns.i.a1.intSeq(min(1)).query.get.map(_ ==> List(
          (1, Set(int1)),
          (2, Set(int2)),
        ))
        _ <- Ns.i.a1.intSeq(min(2)).query.get.map(_ ==> List(
          (1, Set(int1, int2)),
          (2, Set(int2, int3)),
        ))
        _ <- Ns.i.a1.intSeq(min(3)).query.get.map(_ ==> List(
          (1, Set(int1, int2)),
          (2, Set(int2, int3, int4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.intSeq.insert(List(
          (1, List(int1, int2, int2)),
          (2, List(int2)),
          (2, List(int3, int4, int4)),
          (2, List(int3, int4, int4)),
        )).transact

        // Maximum value

        _ <- Ns.intSeq(max).query.get.map(_ ==> List(int4))

        // We can sort by maximum value
        _ <- Ns.i.intSeq(max).a1.query.get.map(_ ==> List(
          (1, int2),
          (2, int4),
        ))
        _ <- Ns.i.intSeq(max).d1.query.get.map(_ ==> List(
          (2, int4),
          (1, int2),
        ))

        // Set of maximum values

        _ <- Ns.intSeq(max(1)).query.get.map(_ ==> List(Set(int4)))
        _ <- Ns.intSeq(max(2)).query.get.map(_ ==> List(Set(int4, int3)))
        _ <- Ns.intSeq(max(3)).query.get.map(_ ==> List(Set(int4, int3, int2)))

        // Same as
        _ <- Ns.i.a1.intSeq(max(1)).query.get.map(_ ==> List(
          (1, Set(int2)),
          (2, Set(int4)),
        ))
        _ <- Ns.i.a1.intSeq(max(2)).query.get.map(_ ==> List(
          (1, Set(int2, int1)),
          (2, Set(int4, int3)),
        ))
        _ <- Ns.i.a1.intSeq(max(3)).query.get.map(_ ==> List(
          (1, Set(int2, int1)),
          (2, Set(int4, int3, int2)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.intSeq.insert(List(
          (1, List(int1, int2, int2)),
          (2, List(int2)),
          (2, List(int3, int4, int4)),
          (2, List(int3, int4, int4)),
        )).transact

        all = Set(int1, int2, int3, int4)

        _ <- Ns.intSeq(sample).query.get.map { rows =>
          val singleSampleValue = rows.head
          all.contains(singleSampleValue) ==> true
        }

        // We can sort by sample value (not checked here)
        _ <- Ns.i.intSeq(sample).a1.query.get
        _ <- Ns.i.intSeq(sample).d1.query.get

        // Multiple samples
        _ <- Ns.intSeq(sample(1)).query.get.map { rows =>
          val sampleSetWithOneValue: Set[Int] = rows.head
          all.contains(sampleSetWithOneValue.head) ==> true
        }
        _ <- Ns.intSeq(sample(2)).query.get.map { rows =>
          val sampleSetWithTwoValues: Set[Int] = rows.head
          all.intersect(sampleSetWithTwoValues).nonEmpty ==> true
        }
      } yield ()
    }


    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.intSeq.insert(List(
          (1, List(int1, int2, int2)),
          (2, List(int2)),
          (2, List(int3, int4, int4)),
          (2, List(int3, int4, int4)),
        )).transact

        // Non-aggregated card-many Seq of attribute values coalesce
        _ <- Ns.i.intSeq.query.get.map(_.sortBy(t => (t._1, t._2.head)) ==> List(
          (1, List(int1, int2, int2)),
          (2, List(int2)),
          (2, List(int3, int4, int4)), // 2 rows with List(int3, int4) coalesced
        ))

        // Use `distinct` keyword to retrieve unique Seqs of Seqs
        _ <- Ns.i.a1.intSeq(distinct).query.get.map(_ ==> List(
          (1, Set(List(int1, int2, int2))),
          (2, Set(
            List(int2),
            List(int3, int4, int4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.intSeq(distinct).query.get.map(_ ==> List(
          Set(
            List(int1, int2, int2),
            List(int2),
            List(int3, int4, int4),
          )
        ))
      } yield ()
    }
  }
}