// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import java.net.URI
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_URI_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        val a = (1, List(uri1, uri2))
        val b = (2, List(uri2, uri3, uri3))
        for {
          _ <- Ns.i.uriSeq.insert(List(a, b)).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.uriSeq.has(uri0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq.has(uri1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uriSeq.has(uri2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq.has(uri3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uriSeq.has(List(uri0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq.has(List(uri1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uriSeq.has(List(uri2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq.has(List(uri3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.uriSeq.has(uri1, uri2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq.has(uri1, uri3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq.has(uri2, uri3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq.has(uri1, uri2, uri3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uriSeq.has(List(uri1, uri2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq.has(List(uri1, uri3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq.has(List(uri2, uri3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq.has(List(uri1, uri2, uri3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.uriSeq.has(List.empty[URI]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, List(uri1, uri2))
        val b = (2, List(uri2, uri3, uri3))
        for {
          _ <- Ns.i.uriSeq.insert(List(a, b)).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.uriSeq.hasNo(uri0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq.hasNo(uri1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uriSeq.hasNo(uri2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq.hasNo(uri3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uriSeq.hasNo(uri3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uriSeq.hasNo(uri5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uriSeq.hasNo(List(uri0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uriSeq.hasNo(List(uri1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uriSeq.hasNo(List(uri2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq.hasNo(List(uri3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uriSeq.hasNo(List(uri3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uriSeq.hasNo(List(uri5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.uriSeq.hasNo(uri1, uri2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq.hasNo(uri1, uri3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq.hasNo(uri1, uri3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq.hasNo(uri1, uri5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uriSeq.hasNo(List(uri1, uri2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq.hasNo(List(uri1, uri3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq.hasNo(List(uri1, uri3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq.hasNo(List(uri1, uri5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.uriSeq.hasNo(List.empty[URI]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.uriSeq.insert(List(
            (1, List(uri1, uri2)),
            (2, List(uri2, uri3, uri3))
          )).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.uriSeq_.has(uri0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_.has(uri1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriSeq_.has(uri2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriSeq_.has(uri3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.uriSeq_.has(List(uri0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_.has(List(uri1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriSeq_.has(List(uri2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriSeq_.has(List(uri3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.uriSeq_.has(uri1, uri2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriSeq_.has(uri1, uri3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriSeq_.has(uri2, uri3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriSeq_.has(uri1, uri2, uri3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.uriSeq_.has(List(uri1, uri2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriSeq_.has(List(uri1, uri3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriSeq_.has(List(uri2, uri3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriSeq_.has(List(uri1, uri2, uri3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.uriSeq_.has(List.empty[URI]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.uriSeq.insert(List(
            (1, List(uri1, uri2)),
            (2, List(uri2, uri3, uri3))
          )).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.uriSeq_.hasNo(uri0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriSeq_.hasNo(uri1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uriSeq_.hasNo(uri2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_.hasNo(uri3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriSeq_.hasNo(uri3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriSeq_.hasNo(uri5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.uriSeq_.hasNo(List(uri0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uriSeq_.hasNo(List(uri1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uriSeq_.hasNo(List(uri2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_.hasNo(List(uri3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriSeq_.hasNo(List(uri3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uriSeq_.hasNo(List(uri5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.uriSeq_.hasNo(uri1, uri2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_.hasNo(uri1, uri3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_.hasNo(uri1, uri3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_.hasNo(uri1, uri5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.uriSeq_.hasNo(List(uri1, uri2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_.hasNo(List(uri1, uri3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_.hasNo(List(uri1, uri3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uriSeq_.hasNo(List(uri1, uri5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.uriSeq_.hasNo(List.empty[URI]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }

    // No filtering on optional Seq attributes
  }
}