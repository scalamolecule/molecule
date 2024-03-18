// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.set.any

import java.net.URI
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSet_URI_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.uriSet.insert(List(
          (1, Set(uri1, uri2)),
          (2, Set(uri2)),
          (2, Set(uri3, uri4)),
          (2, Set(uri3, uri4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.uriSet(count).query.get.map(_ ==> List(7))
        _ <- Ns.uriSet(countDistinct).query.get.map(_ ==> List(4))

        // Sort by counts

        _ <- Ns.i.uriSet(count).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 5),
        ))
        _ <- Ns.i.uriSet(count).d1.query.get.map(_ ==> List(
          (2, 5),
          (1, 2),
        ))

        _ <- Ns.i.uriSet(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- Ns.i.uriSet(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.uriSet.insert(List(
          (1, Set(uri1, uri2)),
          (2, Set(uri2)),
          (2, Set(uri3, uri4)),
          (2, Set(uri3, uri4)),
        )).transact

        // Minimum value

        _ <- Ns.uriSet(min).query.get.map(_ ==> List(uri1))

        // Sort by minimum value
        _ <- Ns.i.uriSet(min).a1.query.get.map(_ ==> List(
          (1, uri1),
          (2, uri2),
        ))
        _ <- Ns.i.uriSet(min).d1.query.get.map(_ ==> List(
          (2, uri2),
          (1, uri1),
        ))

        // Set of minimum values

        _ <- Ns.uriSet(min(1)).query.get.map(_ ==> List(Set(uri1)))
        _ <- Ns.uriSet(min(2)).query.get.map(_ ==> List(Set(uri1, uri2)))
        _ <- Ns.uriSet(min(3)).query.get.map(_ ==> List(Set(uri1, uri2, uri3)))

        _ <- Ns.i.a1.uriSet(min(1)).query.get.map(_ ==> List(
          (1, Set(uri1)),
          (2, Set(uri2)),
        ))
        _ <- Ns.i.a1.uriSet(min(2)).query.get.map(_ ==> List(
          (1, Set(uri1, uri2)),
          (2, Set(uri2, uri3)),
        ))
        _ <- Ns.i.a1.uriSet(min(3)).query.get.map(_ ==> List(
          (1, Set(uri1, uri2)),
          (2, Set(uri2, uri3, uri4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.uriSet.insert(List(
          (1, Set(uri1, uri2)),
          (2, Set(uri2)),
          (2, Set(uri3, uri4)),
          (2, Set(uri3, uri4)),
        )).transact

        // Maximum value

        _ <- Ns.uriSet(max).query.get.map(_ ==> List(uri4))

        // Sort by maximum value
        _ <- Ns.i.uriSet(max).a1.query.get.map(_ ==> List(
          (1, uri2),
          (2, uri4),
        ))
        _ <- Ns.i.uriSet(max).d1.query.get.map(_ ==> List(
          (2, uri4),
          (1, uri2),
        ))

        // Set of maximum values

        _ <- Ns.uriSet(max(1)).query.get.map(_ ==> List(Set(uri4)))
        _ <- Ns.uriSet(max(2)).query.get.map(_ ==> List(Set(uri3, uri4)))
        _ <- Ns.uriSet(max(3)).query.get.map(_ ==> List(Set(uri2, uri3, uri4)))

        _ <- Ns.i.a1.uriSet(max(1)).query.get.map(_ ==> List(
          (1, Set(uri2)),
          (2, Set(uri4)),
        ))
        _ <- Ns.i.a1.uriSet(max(2)).query.get.map(_ ==> List(
          (1, Set(uri1, uri2)),
          (2, Set(uri3, uri4)),
        ))
        _ <- Ns.i.a1.uriSet(max(3)).query.get.map(_ ==> List(
          (1, Set(uri1, uri2)),
          (2, Set(uri2, uri3, uri4)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.uriSet.insert(List(
          (1, Set(uri1, uri2)),
          (2, Set(uri2)),
          (2, Set(uri3, uri4)),
          (2, Set(uri3, uri4)),
        )).transact

        all = Set(uri1, uri2, uri3, uri4)

        _ <- Ns.uriSet(sample).query.get.map { rows =>
          val singleSampleValue: URI = rows.head
          all.contains(singleSampleValue) ==> true
        }

        // Sort by sample value (not checked here)
        _ <- Ns.i.uriSet(sample).a1.query.get
        _ <- Ns.i.uriSet(sample).d1.query.get

        // Multiple samples
        _ <- Ns.uriSet(sample(1)).query.get.map { rows =>
          val sampleSetWithOneValue: Set[URI] = rows.head
          all.contains(sampleSetWithOneValue.head) ==> true
        }
        _ <- Ns.uriSet(sample(2)).query.get.map { rows =>
          val sampleSetWithTwoValues: Set[URI] = rows.head
          all.intersect(sampleSetWithTwoValues).nonEmpty ==> true
        }
      } yield ()
    }


    "distinct sets of values" - types { implicit conn =>
      for {
        _ <- Ns.i.uriSet.insert(List(
          (1, Set(uri1, uri2)),
          (2, Set(uri2)),
          (2, Set(uri3, uri4)),
          (2, Set(uri3, uri4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.uriSet.query.get.map(_ ==> List(
          (1, Set(uri1, uri2)),
          (2, Set(uri2, uri3, uri4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.uriSet(distinct).query.get.map(_ ==> List(
          (1, Set(Set(uri1, uri2))),
          (2, Set(
            Set(uri2),
            Set(uri3, uri4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.uriSet(distinct).query.get.map(_ ==> List(
          Set(
            Set(uri1, uri2),
            Set(uri2),
            Set(uri3, uri4),
          )
        ))
      } yield ()
    }
  }
}