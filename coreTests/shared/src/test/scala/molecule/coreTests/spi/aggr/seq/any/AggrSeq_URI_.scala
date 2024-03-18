// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.seq.any

import java.net.URI
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeq_URI_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.uriSeq.insert(List(
          (1, List(uri1, uri2, uri2)),
          (2, List(uri2)),
          (2, List(uri3, uri4, uri4)),
          (2, List(uri3, uri4, uri4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.uriSeq(count).query.get.map(_ ==> List(10))
        _ <- Ns.uriSeq(countDistinct).query.get.map(_ ==> List(4))

        // We can sort by counts

        _ <- Ns.i.uriSeq(count).a1.query.get.map(_ ==> List(
          (1, 3),
          (2, 7),
        ))
        _ <- Ns.i.uriSeq(count).d1.query.get.map(_ ==> List(
          (2, 7),
          (1, 3),
        ))

        _ <- Ns.i.uriSeq(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- Ns.i.uriSeq(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.uriSeq.insert(List(
          (1, List(uri1, uri2, uri2)),
          (2, List(uri2)),
          (2, List(uri3, uri4, uri4)),
          (2, List(uri3, uri4, uri4)),
        )).transact

        // Minimum value

        _ <- Ns.uriSeq(min).query.get.map(_ ==> List(uri1))

        // We can sort by minimum value
        _ <- Ns.i.uriSeq(min).a1.query.get.map(_ ==> List(
          (1, uri1),
          (2, uri2),
        ))
        _ <- Ns.i.uriSeq(min).d1.query.get.map(_ ==> List(
          (2, uri2),
          (1, uri1),
        ))

        // Set of minimum values

        _ <- Ns.uriSeq.apply(min(1)).query.get.map(_ ==> List(Set(uri1)))
        _ <- Ns.uriSeq(min(2)).query.get.map(_ ==> List(Set(uri1, uri2)))
        _ <- Ns.uriSeq(min(3)).query.get.map(_ ==> List(Set(uri1, uri2, uri3)))

        _ <- Ns.i.a1.uriSeq(min(1)).query.get.map(_ ==> List(
          (1, Set(uri1)),
          (2, Set(uri2)),
        ))
        _ <- Ns.i.a1.uriSeq(min(2)).query.get.map(_ ==> List(
          (1, Set(uri1, uri2)),
          (2, Set(uri2, uri3)),
        ))
        _ <- Ns.i.a1.uriSeq(min(3)).query.get.map(_ ==> List(
          (1, Set(uri1, uri2)),
          (2, Set(uri2, uri3, uri4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.uriSeq.insert(List(
          (1, List(uri1, uri2, uri2)),
          (2, List(uri2)),
          (2, List(uri3, uri4, uri4)),
          (2, List(uri3, uri4, uri4)),
        )).transact

        // Maximum value

        _ <- Ns.uriSeq(max).query.get.map(_ ==> List(uri4))

        // We can sort by maximum value
        _ <- Ns.i.uriSeq(max).a1.query.get.map(_ ==> List(
          (1, uri2),
          (2, uri4),
        ))
        _ <- Ns.i.uriSeq(max).d1.query.get.map(_ ==> List(
          (2, uri4),
          (1, uri2),
        ))

        // Set of maximum values

        _ <- Ns.uriSeq(max(1)).query.get.map(_ ==> List(Set(uri4)))
        _ <- Ns.uriSeq(max(2)).query.get.map(_ ==> List(Set(uri4, uri3)))
        _ <- Ns.uriSeq(max(3)).query.get.map(_ ==> List(Set(uri4, uri3, uri2)))

        // Same as
        _ <- Ns.i.a1.uriSeq(max(1)).query.get.map(_ ==> List(
          (1, Set(uri2)),
          (2, Set(uri4)),
        ))
        _ <- Ns.i.a1.uriSeq(max(2)).query.get.map(_ ==> List(
          (1, Set(uri2, uri1)),
          (2, Set(uri4, uri3)),
        ))
        _ <- Ns.i.a1.uriSeq(max(3)).query.get.map(_ ==> List(
          (1, Set(uri2, uri1)),
          (2, Set(uri4, uri3, uri2)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.uriSeq.insert(List(
          (1, List(uri1, uri2, uri2)),
          (2, List(uri2)),
          (2, List(uri3, uri4, uri4)),
          (2, List(uri3, uri4, uri4)),
        )).transact

        all = Set(uri1, uri2, uri3, uri4)

        _ <- Ns.uriSeq(sample).query.get.map { rows =>
          val singleSampleValue = rows.head
          all.contains(singleSampleValue) ==> true
        }

        // We can sort by sample value (not checked here)
        _ <- Ns.i.uriSeq(sample).a1.query.get
        _ <- Ns.i.uriSeq(sample).d1.query.get

        // Multiple samples
        _ <- Ns.uriSeq(sample(1)).query.get.map { rows =>
          val sampleSetWithOneValue: Set[URI] = rows.head
          all.contains(sampleSetWithOneValue.head) ==> true
        }
        _ <- Ns.uriSeq(sample(2)).query.get.map { rows =>
          val sampleSetWithTwoValues: Set[URI] = rows.head
          all.intersect(sampleSetWithTwoValues).nonEmpty ==> true
        }
      } yield ()
    }


    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.uriSeq.insert(List(
          (1, List(uri1, uri2, uri2)),
          (2, List(uri2)),
          (2, List(uri3, uri4, uri4)),
          (2, List(uri3, uri4, uri4)),
        )).transact

        // Non-aggregated card-many Seq of attribute values coalesce
        _ <- Ns.i.uriSeq.query.get.map(_.sortBy(t => (t._1, t._2.head)) ==> List(
          (1, List(uri1, uri2, uri2)),
          (2, List(uri2)),
          (2, List(uri3, uri4, uri4)), // 2 rows with List(uri3, uri4) coalesced
        ))

        // Use `distinct` keyword to retrieve unique Seqs of Seqs
        _ <- Ns.i.a1.uriSeq(distinct).query.get.map(_ ==> List(
          (1, Set(List(uri1, uri2, uri2))),
          (2, Set(
            List(uri2),
            List(uri3, uri4, uri4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.uriSeq(distinct).query.get.map(_ ==> List(
          Set(
            List(uri1, uri2, uri2),
            List(uri2),
            List(uri3, uri4, uri4),
          )
        ))
      } yield ()
    }
  }
}