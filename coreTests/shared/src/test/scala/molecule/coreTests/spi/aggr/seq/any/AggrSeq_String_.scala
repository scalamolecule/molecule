// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.seq.any

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeq_String_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.stringSeq.insert(List(
          (1, List(string1, string2, string2)),
          (2, List(string2)),
          (2, List(string3, string4, string4)),
          (2, List(string3, string4, string4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.stringSeq(count).query.get.map(_ ==> List(10))
        _ <- Ns.stringSeq(countDistinct).query.get.map(_ ==> List(4))

        // We can sort by counts

        _ <- Ns.i.stringSeq(count).a1.query.get.map(_ ==> List(
          (1, 3),
          (2, 7),
        ))
        _ <- Ns.i.stringSeq(count).d1.query.get.map(_ ==> List(
          (2, 7),
          (1, 3),
        ))

        _ <- Ns.i.stringSeq(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- Ns.i.stringSeq(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.stringSeq.insert(List(
          (1, List(string1, string2, string2)),
          (2, List(string2)),
          (2, List(string3, string4, string4)),
          (2, List(string3, string4, string4)),
        )).transact

        // Minimum value

        _ <- Ns.stringSeq(min).query.get.map(_ ==> List(string1))

        // We can sort by minimum value
        _ <- Ns.i.stringSeq(min).a1.query.get.map(_ ==> List(
          (1, string1),
          (2, string2),
        ))
        _ <- Ns.i.stringSeq(min).d1.query.get.map(_ ==> List(
          (2, string2),
          (1, string1),
        ))

        // Set of minimum values

        _ <- Ns.stringSeq.apply(min(1)).query.get.map(_ ==> List(Set(string1)))
        _ <- Ns.stringSeq(min(2)).query.get.map(_ ==> List(Set(string1, string2)))
        _ <- Ns.stringSeq(min(3)).query.get.map(_ ==> List(Set(string1, string2, string3)))

        _ <- Ns.i.a1.stringSeq(min(1)).query.get.map(_ ==> List(
          (1, Set(string1)),
          (2, Set(string2)),
        ))
        _ <- Ns.i.a1.stringSeq(min(2)).query.get.map(_ ==> List(
          (1, Set(string1, string2)),
          (2, Set(string2, string3)),
        ))
        _ <- Ns.i.a1.stringSeq(min(3)).query.get.map(_ ==> List(
          (1, Set(string1, string2)),
          (2, Set(string2, string3, string4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.stringSeq.insert(List(
          (1, List(string1, string2, string2)),
          (2, List(string2)),
          (2, List(string3, string4, string4)),
          (2, List(string3, string4, string4)),
        )).transact

        // Maximum value

        _ <- Ns.stringSeq(max).query.get.map(_ ==> List(string4))

        // We can sort by maximum value
        _ <- Ns.i.stringSeq(max).a1.query.get.map(_ ==> List(
          (1, string2),
          (2, string4),
        ))
        _ <- Ns.i.stringSeq(max).d1.query.get.map(_ ==> List(
          (2, string4),
          (1, string2),
        ))

        // Set of maximum values

        _ <- Ns.stringSeq(max(1)).query.get.map(_ ==> List(Set(string4)))
        _ <- Ns.stringSeq(max(2)).query.get.map(_ ==> List(Set(string4, string3)))
        _ <- Ns.stringSeq(max(3)).query.get.map(_ ==> List(Set(string4, string3, string2)))

        // Same as
        _ <- Ns.i.a1.stringSeq(max(1)).query.get.map(_ ==> List(
          (1, Set(string2)),
          (2, Set(string4)),
        ))
        _ <- Ns.i.a1.stringSeq(max(2)).query.get.map(_ ==> List(
          (1, Set(string2, string1)),
          (2, Set(string4, string3)),
        ))
        _ <- Ns.i.a1.stringSeq(max(3)).query.get.map(_ ==> List(
          (1, Set(string2, string1)),
          (2, Set(string4, string3, string2)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.stringSeq.insert(List(
          (1, List(string1, string2, string2)),
          (2, List(string2)),
          (2, List(string3, string4, string4)),
          (2, List(string3, string4, string4)),
        )).transact

        all = Set(string1, string2, string3, string4)

        _ <- Ns.stringSeq(sample).query.get.map { rows =>
          val singleSampleValue = rows.head
          all.contains(singleSampleValue) ==> true
        }

        // We can sort by sample value (not checked here)
        _ <- Ns.i.stringSeq(sample).a1.query.get
        _ <- Ns.i.stringSeq(sample).d1.query.get

        // Multiple samples
        _ <- Ns.stringSeq(sample(1)).query.get.map { rows =>
          val sampleSetWithOneValue: Set[String] = rows.head
          all.contains(sampleSetWithOneValue.head) ==> true
        }
        _ <- Ns.stringSeq(sample(2)).query.get.map { rows =>
          val sampleSetWithTwoValues: Set[String] = rows.head
          all.intersect(sampleSetWithTwoValues).nonEmpty ==> true
        }
      } yield ()
    }


    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.stringSeq.insert(List(
          (1, List(string1, string2, string2)),
          (2, List(string2)),
          (2, List(string3, string4, string4)),
          (2, List(string3, string4, string4)),
        )).transact

        // Non-aggregated card-many Seq of attribute values coalesce
        _ <- Ns.i.stringSeq.query.get.map(_.sortBy(t => (t._1, t._2.head)) ==> List(
          (1, List(string1, string2, string2)),
          (2, List(string2)),
          (2, List(string3, string4, string4)), // 2 rows with List(string3, string4) coalesced
        ))

        // Use `distinct` keyword to retrieve unique Seqs of Seqs
        _ <- Ns.i.a1.stringSeq(distinct).query.get.map(_ ==> List(
          (1, Set(List(string1, string2, string2))),
          (2, Set(
            List(string2),
            List(string3, string4, string4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.stringSeq(distinct).query.get.map(_ ==> List(
          Set(
            List(string1, string2, string2),
            List(string2),
            List(string3, string4, string4),
          )
        ))
      } yield ()
    }
  }
}