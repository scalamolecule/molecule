// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.one.types

import java.net.URI
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class FilterOne_URI_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Mandatory" - types { implicit conn =>
    val a = (1, uri1)
    val b = (2, uri2)
    val c = (3, uri3)
    for {
      _ <- Entity.i.uri.insert(List(a, b, c)).transact

      // Find all attribute values
      _ <- Entity.i.a1.uri.query.get.map(_ ==> List(a, b, c))

      // Find value(s) matching
      _ <- Entity.i.a1.uri(uri0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uri(uri1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uri(Seq(uri0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uri(Seq(uri1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.uri(uri1, uri2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uri(uri1, uri0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uri(Seq(uri1, uri2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uri(Seq(uri1, uri0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.uri(Seq.empty[URI]).query.get.map(_ ==> List())

      // Find values not matching
      _ <- Entity.i.a1.uri.not(uri0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.uri.not(uri1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.uri.not(uri2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.uri.not(uri3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uri.not(Seq(uri0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.uri.not(Seq(uri1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.uri.not(Seq(uri2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.uri.not(Seq(uri3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.uri.not(uri0, uri1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.uri.not(uri1, uri2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.uri.not(uri2, uri3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uri.not(Seq(uri0, uri1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.uri.not(Seq(uri1, uri2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.uri.not(Seq(uri2, uri3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all values
      _ <- Entity.i.a1.uri.not(Seq.empty[URI]).query.get.map(_ ==> List(a, b, c))

      // Find values in range
      _ <- Entity.i.a1.uri.<(uri2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uri.>(uri2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.uri.<=(uri2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uri.>=(uri2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    val (a, b, c, x) = (1, 2, 3, 4)
    for {
      _ <- Entity.i.uri_?.insert(List(
        (a, Some(uri1)),
        (b, Some(uri2)),
        (c, Some(uri3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      _ <- Entity.i.a1.uri_.query.get.map(_ ==> List(a, b, c))

      // Match non-asserted attribute (null)
      _ <- Entity.i.a1.uri_().query.get.map(_ ==> List(x))

      // Match attribute values without returning them
      _ <- Entity.i.a1.uri_(uri0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uri_(uri1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uri_(Seq(uri0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uri_(Seq(uri1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.uri_(uri1, uri2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uri_(uri1, uri0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uri_(Seq(uri1, uri2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uri_(Seq(uri1, uri0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.uri_(Seq.empty[URI]).query.get.map(_ ==> List())

      // Match non-matching values without returning them
      _ <- Entity.i.a1.uri_.not(uri0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.uri_.not(uri1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.uri_.not(uri2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.uri_.not(uri3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uri_.not(Seq(uri0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.uri_.not(Seq(uri1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.uri_.not(Seq(uri2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.uri_.not(Seq(uri3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.uri_.not(uri0, uri1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.uri_.not(uri1, uri2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.uri_.not(uri2, uri3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uri_.not(Seq(uri0, uri1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.uri_.not(Seq(uri1, uri2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.uri_.not(Seq(uri2, uri3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all asserted values (non-null)
      _ <- Entity.i.a1.uri_.not(Seq.empty[URI]).query.get.map(_ ==> List(a, b, c))

      // Match value ranges without returning them
      _ <- Entity.i.a1.uri_.<(uri2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uri_.>(uri2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.uri_.<=(uri2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uri_.>=(uri2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Optional" - types { implicit conn =>
    val a = (1, Some(uri1))
    val b = (2, Some(uri2))
    val c = (3, Some(uri3))
    val x = (4, Option.empty[URI])
    for {
      _ <- Entity.i.uri_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      _ <- Entity.i.a1.uri_?.query.get.map(_ ==> List(a, b, c, x))

      // Find optional values matching
      _ <- Entity.i.a1.uri_?(Some(uri0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uri_?(Some(uri1)).query.get.map(_ ==> List(a))

      // None matches non-asserted/null values
      _ <- Entity.i.a1.uri_?(Option.empty[URI]).query.get.map(_ ==> List(x))
      // Easier to apply nothing to tacit attribute
      _ <- Entity.i.a1.uri_().query.get.map(_ ==> List(4))
    } yield ()
  }


  "Combinations" - types { implicit conn =>
    for {
      _ <- Entity.i.uri.insert(
        (1, uri1),
        (2, uri2),
        (3, uri3),
        (4, uri4),
        (5, uri5),
        (6, uri6),
        (7, uri7),
        (8, uri8),
        (9, uri9),
      ).transact

      _ <- Entity.i.a1.uri_.>(uri2).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8, 9))
      _ <- Entity.i.a1.uri_.>(uri2).uri_.<=(uri8).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8))
      _ <- Entity.i.a1.uri_.>(uri2).uri_.<=(uri8).uri_.not(uri4, uri5).query.get.map(_ ==> List(3, 6, 7, 8))
    } yield ()
  }
}
