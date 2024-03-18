// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.seq.any

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeq_Short_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.shortSeq.insert(List(
          (1, List(short1, short2, short2)),
          (2, List(short2)),
          (2, List(short3, short4, short4)),
          (2, List(short3, short4, short4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.shortSeq(count).query.get.map(_ ==> List(10))
        _ <- Ns.shortSeq(countDistinct).query.get.map(_ ==> List(4))

        // We can sort by counts

        _ <- Ns.i.shortSeq(count).a1.query.get.map(_ ==> List(
          (1, 3),
          (2, 7),
        ))
        _ <- Ns.i.shortSeq(count).d1.query.get.map(_ ==> List(
          (2, 7),
          (1, 3),
        ))

        _ <- Ns.i.shortSeq(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- Ns.i.shortSeq(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.shortSeq.insert(List(
          (1, List(short1, short2, short2)),
          (2, List(short2)),
          (2, List(short3, short4, short4)),
          (2, List(short3, short4, short4)),
        )).transact

        // Minimum value

        _ <- Ns.shortSeq(min).query.get.map(_ ==> List(short1))

        // We can sort by minimum value
        _ <- Ns.i.shortSeq(min).a1.query.get.map(_ ==> List(
          (1, short1),
          (2, short2),
        ))
        _ <- Ns.i.shortSeq(min).d1.query.get.map(_ ==> List(
          (2, short2),
          (1, short1),
        ))

        // Set of minimum values

        _ <- Ns.shortSeq.apply(min(1)).query.get.map(_ ==> List(Set(short1)))
        _ <- Ns.shortSeq(min(2)).query.get.map(_ ==> List(Set(short1, short2)))
        _ <- Ns.shortSeq(min(3)).query.get.map(_ ==> List(Set(short1, short2, short3)))

        _ <- Ns.i.a1.shortSeq(min(1)).query.get.map(_ ==> List(
          (1, Set(short1)),
          (2, Set(short2)),
        ))
        _ <- Ns.i.a1.shortSeq(min(2)).query.get.map(_ ==> List(
          (1, Set(short1, short2)),
          (2, Set(short2, short3)),
        ))
        _ <- Ns.i.a1.shortSeq(min(3)).query.get.map(_ ==> List(
          (1, Set(short1, short2)),
          (2, Set(short2, short3, short4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.shortSeq.insert(List(
          (1, List(short1, short2, short2)),
          (2, List(short2)),
          (2, List(short3, short4, short4)),
          (2, List(short3, short4, short4)),
        )).transact

        // Maximum value

        _ <- Ns.shortSeq(max).query.get.map(_ ==> List(short4))

        // We can sort by maximum value
        _ <- Ns.i.shortSeq(max).a1.query.get.map(_ ==> List(
          (1, short2),
          (2, short4),
        ))
        _ <- Ns.i.shortSeq(max).d1.query.get.map(_ ==> List(
          (2, short4),
          (1, short2),
        ))

        // Set of maximum values

        _ <- Ns.shortSeq(max(1)).query.get.map(_ ==> List(Set(short4)))
        _ <- Ns.shortSeq(max(2)).query.get.map(_ ==> List(Set(short4, short3)))
        _ <- Ns.shortSeq(max(3)).query.get.map(_ ==> List(Set(short4, short3, short2)))

        // Same as
        _ <- Ns.i.a1.shortSeq(max(1)).query.get.map(_ ==> List(
          (1, Set(short2)),
          (2, Set(short4)),
        ))
        _ <- Ns.i.a1.shortSeq(max(2)).query.get.map(_ ==> List(
          (1, Set(short2, short1)),
          (2, Set(short4, short3)),
        ))
        _ <- Ns.i.a1.shortSeq(max(3)).query.get.map(_ ==> List(
          (1, Set(short2, short1)),
          (2, Set(short4, short3, short2)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.shortSeq.insert(List(
          (1, List(short1, short2, short2)),
          (2, List(short2)),
          (2, List(short3, short4, short4)),
          (2, List(short3, short4, short4)),
        )).transact

        all = Set(short1, short2, short3, short4)

        _ <- Ns.shortSeq(sample).query.get.map { rows =>
          val singleSampleValue = rows.head
          all.contains(singleSampleValue) ==> true
        }

        // We can sort by sample value (not checked here)
        _ <- Ns.i.shortSeq(sample).a1.query.get
        _ <- Ns.i.shortSeq(sample).d1.query.get

        // Multiple samples
        _ <- Ns.shortSeq(sample(1)).query.get.map { rows =>
          val sampleSetWithOneValue: Set[Short] = rows.head
          all.contains(sampleSetWithOneValue.head) ==> true
        }
        _ <- Ns.shortSeq(sample(2)).query.get.map { rows =>
          val sampleSetWithTwoValues: Set[Short] = rows.head
          all.intersect(sampleSetWithTwoValues).nonEmpty ==> true
        }
      } yield ()
    }


    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.shortSeq.insert(List(
          (1, List(short1, short2, short2)),
          (2, List(short2)),
          (2, List(short3, short4, short4)),
          (2, List(short3, short4, short4)),
        )).transact

        // Non-aggregated card-many Seq of attribute values coalesce
        _ <- Ns.i.shortSeq.query.get.map(_.sortBy(t => (t._1, t._2.head)) ==> List(
          (1, List(short1, short2, short2)),
          (2, List(short2)),
          (2, List(short3, short4, short4)), // 2 rows with List(short3, short4) coalesced
        ))

        // Use `distinct` keyword to retrieve unique Seqs of Seqs
        _ <- Ns.i.a1.shortSeq(distinct).query.get.map(_ ==> List(
          (1, Set(List(short1, short2, short2))),
          (2, Set(
            List(short2),
            List(short3, short4, short4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.shortSeq(distinct).query.get.map(_ ==> List(
          Set(
            List(short1, short2, short2),
            List(short2),
            List(short3, short4, short4),
          )
        ))
      } yield ()
    }
  }
}