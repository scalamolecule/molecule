// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.seq.any

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeq_Char_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.charSeq.insert(List(
          (1, List(char1, char2, char2)),
          (2, List(char2)),
          (2, List(char3, char4, char4)),
          (2, List(char3, char4, char4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.charSeq(count).query.get.map(_ ==> List(10))
        _ <- Ns.charSeq(countDistinct).query.get.map(_ ==> List(4))

        // We can sort by counts

        _ <- Ns.i.charSeq(count).a1.query.get.map(_ ==> List(
          (1, 3),
          (2, 7),
        ))
        _ <- Ns.i.charSeq(count).d1.query.get.map(_ ==> List(
          (2, 7),
          (1, 3),
        ))

        _ <- Ns.i.charSeq(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- Ns.i.charSeq(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.charSeq.insert(List(
          (1, List(char1, char2, char2)),
          (2, List(char2)),
          (2, List(char3, char4, char4)),
          (2, List(char3, char4, char4)),
        )).transact

        // Minimum value

        _ <- Ns.charSeq(min).query.get.map(_ ==> List(char1))

        // We can sort by minimum value
        _ <- Ns.i.charSeq(min).a1.query.get.map(_ ==> List(
          (1, char1),
          (2, char2),
        ))
        _ <- Ns.i.charSeq(min).d1.query.get.map(_ ==> List(
          (2, char2),
          (1, char1),
        ))

        // Set of minimum values

        _ <- Ns.charSeq.apply(min(1)).query.get.map(_ ==> List(Set(char1)))
        _ <- Ns.charSeq(min(2)).query.get.map(_ ==> List(Set(char1, char2)))
        _ <- Ns.charSeq(min(3)).query.get.map(_ ==> List(Set(char1, char2, char3)))

        _ <- Ns.i.a1.charSeq(min(1)).query.get.map(_ ==> List(
          (1, Set(char1)),
          (2, Set(char2)),
        ))
        _ <- Ns.i.a1.charSeq(min(2)).query.get.map(_ ==> List(
          (1, Set(char1, char2)),
          (2, Set(char2, char3)),
        ))
        _ <- Ns.i.a1.charSeq(min(3)).query.get.map(_ ==> List(
          (1, Set(char1, char2)),
          (2, Set(char2, char3, char4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.charSeq.insert(List(
          (1, List(char1, char2, char2)),
          (2, List(char2)),
          (2, List(char3, char4, char4)),
          (2, List(char3, char4, char4)),
        )).transact

        // Maximum value

        _ <- Ns.charSeq(max).query.get.map(_ ==> List(char4))

        // We can sort by maximum value
        _ <- Ns.i.charSeq(max).a1.query.get.map(_ ==> List(
          (1, char2),
          (2, char4),
        ))
        _ <- Ns.i.charSeq(max).d1.query.get.map(_ ==> List(
          (2, char4),
          (1, char2),
        ))

        // Set of maximum values

        _ <- Ns.charSeq(max(1)).query.get.map(_ ==> List(Set(char4)))
        _ <- Ns.charSeq(max(2)).query.get.map(_ ==> List(Set(char4, char3)))
        _ <- Ns.charSeq(max(3)).query.get.map(_ ==> List(Set(char4, char3, char2)))

        // Same as
        _ <- Ns.i.a1.charSeq(max(1)).query.get.map(_ ==> List(
          (1, Set(char2)),
          (2, Set(char4)),
        ))
        _ <- Ns.i.a1.charSeq(max(2)).query.get.map(_ ==> List(
          (1, Set(char2, char1)),
          (2, Set(char4, char3)),
        ))
        _ <- Ns.i.a1.charSeq(max(3)).query.get.map(_ ==> List(
          (1, Set(char2, char1)),
          (2, Set(char4, char3, char2)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.charSeq.insert(List(
          (1, List(char1, char2, char2)),
          (2, List(char2)),
          (2, List(char3, char4, char4)),
          (2, List(char3, char4, char4)),
        )).transact

        all = Set(char1, char2, char3, char4)

        _ <- Ns.charSeq(sample).query.get.map { rows =>
          val singleSampleValue = rows.head
          all.contains(singleSampleValue) ==> true
        }

        // We can sort by sample value (not checked here)
        _ <- Ns.i.charSeq(sample).a1.query.get
        _ <- Ns.i.charSeq(sample).d1.query.get

        // Multiple samples
        _ <- Ns.charSeq(sample(1)).query.get.map { rows =>
          val sampleSetWithOneValue: Set[Char] = rows.head
          all.contains(sampleSetWithOneValue.head) ==> true
        }
        _ <- Ns.charSeq(sample(2)).query.get.map { rows =>
          val sampleSetWithTwoValues: Set[Char] = rows.head
          all.intersect(sampleSetWithTwoValues).nonEmpty ==> true
        }
      } yield ()
    }


    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.charSeq.insert(List(
          (1, List(char1, char2, char2)),
          (2, List(char2)),
          (2, List(char3, char4, char4)),
          (2, List(char3, char4, char4)),
        )).transact

        // Non-aggregated card-many Seq of attribute values coalesce
        _ <- Ns.i.charSeq.query.get.map(_.sortBy(t => (t._1, t._2.head)) ==> List(
          (1, List(char1, char2, char2)),
          (2, List(char2)),
          (2, List(char3, char4, char4)), // 2 rows with List(char3, char4) coalesced
        ))

        // Use `distinct` keyword to retrieve unique Seqs of Seqs
        _ <- Ns.i.a1.charSeq(distinct).query.get.map(_ ==> List(
          (1, Set(List(char1, char2, char2))),
          (2, Set(
            List(char2),
            List(char3, char4, char4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.charSeq(distinct).query.get.map(_ ==> List(
          Set(
            List(char1, char2, char2),
            List(char2),
            List(char3, char4, char4),
          )
        ))
      } yield ()
    }
  }
}