// GENERATED CODE ********************************
package molecule.coreTests.spi.aggregation.any

import java.net.URI
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait Aggr_URI_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.uri.insert(List(
          (1, uri1),
          (2, uri2),
          (2, uri2),
          (2, uri3),
        )).transact

        _ <- Ns.i.uri.a1.query.get.map(_ ==> List(
          (1, uri1),
          (2, uri2), // 2 rows coalesced
          (2, uri3),
        ))

        // Distinct values are returned in a Set
        _ <- Ns.i.a1.uri(distinct).query.get.map(_ ==> List(
          (1, Set(uri1)),
          (2, Set(uri2, uri3)),
        ))

        _ <- Ns.uri(distinct).query.get.map(_.head ==> Set(
          uri1, uri2, uri3
        ))
      } yield ()
    }


    "min/max" - types { implicit conn =>
      for {
        _ <- Ns.i.uri.insert(
          (1, uri1),
          (1, uri2),
          (1, uri3),
          (2, uri4),
          (2, uri5),
          (2, uri6),
        ).transact

        _ <- Ns.uri(min).query.get.map(_ ==> List(uri1))
        _ <- Ns.uri(max).query.get.map(_ ==> List(uri6))
        _ <- Ns.uri(min).uri(max).query.get.map(_ ==> List((uri1, uri6)))

        _ <- Ns.i.a1.uri(min).query.get.map(_ ==> List(
          (1, uri1),
          (2, uri4)
        ))

        _ <- Ns.i.a1.uri(max).query.get.map(_ ==> List(
          (1, uri3),
          (2, uri6)
        ))

        _ <- Ns.i.a1.uri(min).uri(max).query.get.map(_ ==> List(
          (1, uri1, uri3),
          (2, uri4, uri6)
        ))
      } yield ()
    }

    "min/max n" - types { implicit conn =>
      for {
        _ <- Ns.i.uri.insert(
          (1, uri1),
          (1, uri2),
          (1, uri3),
          (2, uri4),
          (2, uri5),
          (2, uri6),
          (2, uri6), // (make sure grouped values coalesce)
        ).transact

        _ <- Ns.uri(min(1)).query.get.map(_ ==> List(Set(uri1)))
        _ <- Ns.uri(min(2)).query.get.map(_ ==> List(Set(uri1, uri2)))

        _ <- Ns.uri(max(1)).query.get.map(_ ==> List(Set(uri6)))
        _ <- Ns.uri(max(2)).query.get.map(_ ==> List(Set(uri5, uri6)))

        _ <- Ns.i.a1.uri(min(2)).query.get.map(_ ==> List(
          (1, Set(uri1, uri2)),
          (2, Set(uri4, uri5))
        ))

        _ <- Ns.i.a1.uri(max(2)).query.get.map(_ ==> List(
          (1, Set(uri2, uri3)),
          (2, Set(uri5, uri6))
        ))

        _ <- Ns.i.a1.uri(min(2)).uri(max(2)).query.get.map(_ ==> List(
          (1, Set(uri1, uri2), Set(uri2, uri3)),
          (2, Set(uri4, uri5), Set(uri5, uri6))
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      val all = Set(uri1, uri2, uri3, uri4)
      for {
        _ <- Ns.uri.insert(List(uri1, uri2, uri3)).transact
        _ <- Ns.uri(sample).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.uri(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.uri(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count" - types { implicit conn =>
      for {
        _ <- Ns.i.uri.insert(List(
          (1, uri1),
          (2, uri2),
          (2, uri2),
          (2, uri3),
        )).transact

        _ <- Ns.uri(count).query.get.map(_ ==> List(4))
        _ <- Ns.i.a1.uri(count).query.get.map(_ ==> List(
          (1, 1),
          (2, 3)
        ))

        _ <- Ns.uri(countDistinct).query.get.map(_ ==> List(3))
        _ <- Ns.i.a1.uri(countDistinct).query.get.map(_ ==> List(
          (1, 1),
          (2, 2)
        ))
      } yield ()
    }
  }
}