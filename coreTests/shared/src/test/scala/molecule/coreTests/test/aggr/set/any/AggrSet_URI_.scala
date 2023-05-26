// GENERATED CODE ********************************
package molecule.coreTests.test.aggr.set.any

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSet_URI_ extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.uris.insert(List(
          (1, Set(uri1, uri2)),
          (2, Set(uri2, uri3)),
          (2, Set(uri3, uri4)),
          (2, Set(uri3, uri4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.uris.query.get.map(_ ==> List(
          (1, Set(uri1, uri2)),
          (2, Set(uri2, uri3, uri4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of values
        _ <- Ns.i.a1.uris(distinct).query.get.map(_ ==> List(
          (1, Set(Set(uri1, uri2))),
          (2, Set(
            Set(uri2, uri3),
            Set(uri3, uri4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.uris(distinct).query.get.map(_ ==> List(
          Set(
            Set(uri1, uri2),
            Set(uri2, uri3),
            Set(uri3, uri4),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.uris.insert(List(
          (1, Set(uri1, uri2)),
          (2, Set(uri2, uri3)),
          (2, Set(uri3, uri4)),
          (2, Set(uri3, uri4)),
        )).transact

        _ <- Ns.uris(min).query.get.map(_ ==> List(Set(uri1)))
        _ <- Ns.uris(min(1)).query.get.map(_ ==> List(Set(uri1)))
        _ <- Ns.uris(min(2)).query.get.map(_ ==> List(Set(uri1, uri2)))

        _ <- Ns.i.uris(min).query.get.map(_ ==> List(
          (1, Set(uri1)),
          (2, Set(uri2)),
        ))
        // Same as
        _ <- Ns.i.uris(min(1)).query.get.map(_ ==> List(
          (1, Set(uri1)),
          (2, Set(uri2)),
        ))

        _ <- Ns.i.uris(min(2)).query.get.map(_ ==> List(
          (1, Set(uri1, uri2)),
          (2, Set(uri2, uri3)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.uris.insert(List(
          (1, Set(uri1, uri2)),
          (2, Set(uri2, uri3)),
          (2, Set(uri3, uri4)),
          (2, Set(uri3, uri4)),
        )).transact

        _ <- Ns.uris(max).query.get.map(_ ==> List(Set(uri4)))
        _ <- Ns.uris(max(1)).query.get.map(_ ==> List(Set(uri4)))
        _ <- Ns.uris(max(2)).query.get.map(_ ==> List(Set(uri3, uri4)))

        _ <- Ns.i.uris(max).query.get.map(_ ==> List(
          (1, Set(uri2)),
          (2, Set(uri4)),
        ))
        // Same as
        _ <- Ns.i.uris(max(1)).query.get.map(_ ==> List(
          (1, Set(uri2)),
          (2, Set(uri4)),
        ))

        _ <- Ns.i.uris(max(2)).query.get.map(_ ==> List(
          (1, Set(uri1, uri2)),
          (2, Set(uri3, uri4)),
        ))
      } yield ()
    }


    "rand" - types { implicit conn =>
      for {
        _ <- Ns.i.uris.insert(List(
          (1, Set(uri1, uri2)),
          (2, Set(uri2, uri3)),
          (2, Set(uri3, uri4)),
          (2, Set(uri3, uri4)),
        )).transact
        all = Set(uri1, uri2, uri3, uri4)
        _ <- Ns.uris(rand).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.uris(rand(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.uris(rand(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.uris.insert(List(
          (1, Set(uri1, uri2)),
          (2, Set(uri2, uri3)),
          (2, Set(uri3, uri4)),
          (2, Set(uri3, uri4)),
        )).transact
        all = Set(uri1, uri2, uri3, uri4)
        _ <- Ns.uris(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.uris(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.uris(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.uris.insert(List(
          (1, Set(uri1, uri2)),
          (2, Set(uri2, uri3)),
          (2, Set(uri3, uri4)),
          (2, Set(uri3, uri4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.uris(count).query.get.map(_ ==> List(8))
        _ <- Ns.uris(countDistinct).query.get.map(_ ==> List(4))

        _ <- Ns.i.a1.uris(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 6)
        ))
        _ <- Ns.i.a1.uris(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}