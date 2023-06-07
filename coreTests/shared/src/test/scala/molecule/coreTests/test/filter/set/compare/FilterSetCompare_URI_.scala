// GENERATED CODE ********************************
package molecule.coreTests.test.filter.set.compare

import java.net.URI
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSetCompare_URI_ extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, Set(uri1, uri2))
      val b = (2, Set(uri2, uri3, uri4))
      for {
        _ <- Ns.i.uris.insert(List(a, b)).transact

        // <
        _ <- Ns.i.a1.uris.hasLt(uri0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.uris.hasLt(uri1).query.get.map(_ ==> List())
        _ <- Ns.i.a1.uris.hasLt(uri2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.uris.hasLt(uri3).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.uris.hasLe(uri0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.uris.hasLe(uri1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.uris.hasLe(uri2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uris.hasLe(uri3).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.uris.hasGt(uri0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uris.hasGt(uri1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uris.hasGt(uri2).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.uris.hasGt(uri3).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.uris.hasGe(uri0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uris.hasGe(uri1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uris.hasGe(uri2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uris.hasGe(uri3).query.get.map(_ ==> List(b))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b) = (1, 2)
      for {
        _ <- Ns.i.uris.insert(List(
          (a, Set(uri1, uri2)),
          (b, Set(uri2, uri3, uri4))
        )).transact

        // <
        _ <- Ns.i.a1.uris_.hasLt(uri0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.uris_.hasLt(uri1).query.get.map(_ ==> List())
        _ <- Ns.i.a1.uris_.hasLt(uri2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.uris_.hasLt(uri3).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.uris_.hasLe(uri0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.uris_.hasLe(uri1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.uris_.hasLe(uri2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uris_.hasLe(uri3).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.uris_.hasGt(uri0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uris_.hasGt(uri1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uris_.hasGt(uri2).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.uris_.hasGt(uri3).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.uris_.hasGe(uri0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uris_.hasGe(uri1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uris_.hasGe(uri2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uris_.hasGe(uri3).query.get.map(_ ==> List(b))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(Set(uri1, uri2)))
      val b = (2, Some(Set(uri2, uri3, uri4)))
      val c = (3, None)
      for {
        _ <- Ns.i.uris_?.insert(a, b, c).transact

        // <
        _ <- Ns.i.a1.uris_?.hasLt(Some(uri0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.uris_?.hasLt(Some(uri1)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.uris_?.hasLt(Some(uri2)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.uris_?.hasLt(Some(uri3)).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.uris_?.hasLe(Some(uri0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.uris_?.hasLe(Some(uri1)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.uris_?.hasLe(Some(uri2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uris_?.hasLe(Some(uri3)).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.uris_?.hasGt(Some(uri0)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uris_?.hasGt(Some(uri1)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uris_?.hasGt(Some(uri2)).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.uris_?.hasGt(Some(uri3)).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.uris_?.hasGe(Some(uri0)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uris_?.hasGe(Some(uri1)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uris_?.hasGe(Some(uri2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uris_?.hasGe(Some(uri3)).query.get.map(_ ==> List(b))


        // None comparison matches any asserted values
        _ <- Ns.i.a1.uris_?.hasLt(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uris_?.hasGt(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uris_?.hasLe(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uris_?.hasGe(None).query.get.map(_ ==> List(a, b))
      } yield ()
    }
  }
}