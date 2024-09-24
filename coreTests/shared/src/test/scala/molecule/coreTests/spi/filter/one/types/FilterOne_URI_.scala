// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.one.types

import java.net.URI
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterOne_URI_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, uri1)
      val b = (2, uri2)
      val c = (3, uri3)
      for {
        _ <- Ns.i.uri.insert(List(a, b, c)).transact

        // Find all attribute values
        _ <- Ns.i.a1.uri.query.get.map(_ ==> List(a, b, c))

        // Find value(s) matching
        _ <- Ns.i.a1.uri(uri0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.uri(uri1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.uri(Seq(uri0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.uri(Seq(uri1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args ("is this or that")
        _ <- Ns.i.a1.uri(uri1, uri2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uri(uri1, uri0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.uri(Seq(uri1, uri2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uri(Seq(uri1, uri0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.uri(Seq.empty[URI]).query.get.map(_ ==> List())

        // Find values not matching
        _ <- Ns.i.a1.uri.not(uri0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.uri.not(uri1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.uri.not(uri2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.uri.not(uri3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uri.not(Seq(uri0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.uri.not(Seq(uri1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.uri.not(Seq(uri2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.uri.not(Seq(uri3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.uri.not(uri0, uri1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.uri.not(uri1, uri2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.uri.not(uri2, uri3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.uri.not(Seq(uri0, uri1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.uri.not(Seq(uri1, uri2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.uri.not(Seq(uri2, uri3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all values
        _ <- Ns.i.a1.uri.not(Seq.empty[URI]).query.get.map(_ ==> List(a, b, c))

        // Find values in range
        _ <- Ns.i.a1.uri.<(uri2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.uri.>(uri2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.uri.<=(uri2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uri.>=(uri2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      for {
        _ <- Ns.i.uri_?.insert(List(
          (a, Some(uri1)),
          (b, Some(uri2)),
          (c, Some(uri3)),
          (x, None)
        )).transact

        // Match asserted attribute without returning its value
        _ <- Ns.i.a1.uri_.query.get.map(_ ==> List(a, b, c))

        // Match non-asserted attribute (null)
        _ <- Ns.i.a1.uri_().query.get.map(_ ==> List(x))

        // Match attribute values without returning them
        _ <- Ns.i.a1.uri_(uri0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.uri_(uri1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.uri_(Seq(uri0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.uri_(Seq(uri1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args ("is this or that")
        _ <- Ns.i.a1.uri_(uri1, uri2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uri_(uri1, uri0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.uri_(Seq(uri1, uri2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uri_(Seq(uri1, uri0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.uri_(Seq.empty[URI]).query.get.map(_ ==> List())

        // Match non-matching values without returning them
        _ <- Ns.i.a1.uri_.not(uri0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.uri_.not(uri1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.uri_.not(uri2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.uri_.not(uri3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uri_.not(Seq(uri0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.uri_.not(Seq(uri1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.uri_.not(Seq(uri2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.uri_.not(Seq(uri3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.uri_.not(uri0, uri1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.uri_.not(uri1, uri2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.uri_.not(uri2, uri3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.uri_.not(Seq(uri0, uri1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.uri_.not(Seq(uri1, uri2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.uri_.not(Seq(uri2, uri3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all asserted values (non-null)
        _ <- Ns.i.a1.uri_.not(Seq.empty[URI]).query.get.map(_ ==> List(a, b, c))

        // Match value ranges without returning them
        _ <- Ns.i.a1.uri_.<(uri2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.uri_.>(uri2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.uri_.<=(uri2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.uri_.>=(uri2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(uri1))
      val b = (2, Some(uri2))
      val c = (3, Some(uri3))
      val x = (4, Option.empty[URI])
      for {
        _ <- Ns.i.uri_?.insert(List(a, b, c, x)).transact

        // Find all optional attribute values
        _ <- Ns.i.a1.uri_?.query.get.map(_ ==> List(a, b, c, x))

        // Find optional values matching
        _ <- Ns.i.a1.uri_?(Some(uri0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.uri_?(Some(uri1)).query.get.map(_ ==> List(a))

        // None matches non-asserted/null values
        _ <- Ns.i.a1.uri_?(Option.empty[URI]).query.get.map(_ ==> List(x))
        // Easier to apply nothing to tacit attribute
        _ <- Ns.i.a1.uri_().query.get.map(_ ==> List(4))
      } yield ()
    }
  }
}
