// GENERATED CODE ********************************
package molecule.db.datomic.test.exprOne

import java.net.URI
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object ExprOne_URI_ extends DatomicTestSuite {

  lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, uri1)
      val b = (2, uri2)
      val c = (3, uri3)
      Ns.n.uri.insert(List(a, b, c)).transact

      // Find all attribute values
      Ns.n.a1.uri.query.get ==> List(a, b, c)

      // Find value(s) matching
      Ns.n.a1.uri(uri0).query.get ==> List()
      Ns.n.a1.uri(uri1).query.get ==> List(a)
      Ns.n.a1.uri(Seq(uri0)).query.get ==> List()
      Ns.n.a1.uri(Seq(uri1)).query.get ==> List(a)
      // OR semantics for multiple args
      Ns.n.a1.uri(uri1, uri2).query.get ==> List(a, b)
      Ns.n.a1.uri(uri1, uri0).query.get ==> List(a)
      Ns.n.a1.uri(Seq(uri1, uri2)).query.get ==> List(a, b)
      Ns.n.a1.uri(Seq(uri1, uri0)).query.get ==> List(a)
      // Empty Seq of args matches no values
      Ns.n.a1.uri(Seq.empty[URI]).query.get ==> List()

      // Find values not matching
      Ns.n.a1.uri.not(uri0).query.get ==> List(a, b, c)
      Ns.n.a1.uri.not(uri1).query.get ==> List(b, c)
      Ns.n.a1.uri.not(uri2).query.get ==> List(a, c)
      Ns.n.a1.uri.not(uri3).query.get ==> List(a, b)
      Ns.n.a1.uri.not(Seq(uri0)).query.get ==> List(a, b, c)
      Ns.n.a1.uri.not(Seq(uri1)).query.get ==> List(b, c)
      Ns.n.a1.uri.not(Seq(uri2)).query.get ==> List(a, c)
      Ns.n.a1.uri.not(Seq(uri3)).query.get ==> List(a, b)
      // OR semantics for multiple args
      Ns.n.a1.uri.not(uri0, uri1).query.get ==> List(b, c)
      Ns.n.a1.uri.not(uri1, uri2).query.get ==> List(c)
      Ns.n.a1.uri.not(uri2, uri3).query.get ==> List(a)
      Ns.n.a1.uri.not(Seq(uri0, uri1)).query.get ==> List(b, c)
      Ns.n.a1.uri.not(Seq(uri1, uri2)).query.get ==> List(c)
      Ns.n.a1.uri.not(Seq(uri2, uri3)).query.get ==> List(a)
      // Empty Seq of negation args matches all values
      Ns.n.a1.uri.not(Seq.empty[URI]).query.get ==> List(a, b, c)

      // Find values in range
      Ns.n.a1.uri.<(uri2).query.get ==> List(a)
      Ns.n.a1.uri.>(uri2).query.get ==> List(c)
      Ns.n.a1.uri.<=(uri2).query.get ==> List(a, b)
      Ns.n.a1.uri.>=(uri2).query.get ==> List(b, c)
    }


    "Tacit" - types { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      Ns.n.uri_?.insert(List(
        (a, Some(uri1)),
        (b, Some(uri2)),
        (c, Some(uri3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      Ns.n.a1.uri_.query.get ==> List(a, b, c)

      // Match non-asserted attribute (null)
      Ns.n.a1.uri_().query.get ==> List(x)

      // Match attribute values without returning them
      Ns.n.a1.uri_(uri0).query.get ==> List()
      Ns.n.a1.uri_(uri1).query.get ==> List(a)
      Ns.n.a1.uri_(Seq(uri0)).query.get ==> List()
      Ns.n.a1.uri_(Seq(uri1)).query.get ==> List(a)
      // OR semantics for multiple args
      Ns.n.a1.uri_(uri1, uri2).query.get ==> List(a, b)
      Ns.n.a1.uri_(uri1, uri0).query.get ==> List(a)
      Ns.n.a1.uri_(Seq(uri1, uri2)).query.get ==> List(a, b)
      Ns.n.a1.uri_(Seq(uri1, uri0)).query.get ==> List(a)
      // Empty Seq of args matches no values
      Ns.n.a1.uri_(Seq.empty[URI]).query.get ==> List()

      // Match non-matching values without returning them
      Ns.n.a1.uri_.not(uri0).query.get ==> List(a, b, c)
      Ns.n.a1.uri_.not(uri1).query.get ==> List(b, c)
      Ns.n.a1.uri_.not(uri2).query.get ==> List(a, c)
      Ns.n.a1.uri_.not(uri3).query.get ==> List(a, b)
      Ns.n.a1.uri_.not(Seq(uri0)).query.get ==> List(a, b, c)
      Ns.n.a1.uri_.not(Seq(uri1)).query.get ==> List(b, c)
      Ns.n.a1.uri_.not(Seq(uri2)).query.get ==> List(a, c)
      Ns.n.a1.uri_.not(Seq(uri3)).query.get ==> List(a, b)
      // OR semantics for multiple args
      Ns.n.a1.uri_.not(uri0, uri1).query.get ==> List(b, c)
      Ns.n.a1.uri_.not(uri1, uri2).query.get ==> List(c)
      Ns.n.a1.uri_.not(uri2, uri3).query.get ==> List(a)
      Ns.n.a1.uri_.not(Seq(uri0, uri1)).query.get ==> List(b, c)
      Ns.n.a1.uri_.not(Seq(uri1, uri2)).query.get ==> List(c)
      Ns.n.a1.uri_.not(Seq(uri2, uri3)).query.get ==> List(a)
      // Empty Seq of negation args matches all asserted values (non-null)
      Ns.n.a1.uri_.not(Seq.empty[URI]).query.get ==> List(a, b, c)

      // Match value ranges without returning them
      Ns.n.a1.uri_.<(uri2).query.get ==> List(a)
      Ns.n.a1.uri_.>(uri2).query.get ==> List(c)
      Ns.n.a1.uri_.<=(uri2).query.get ==> List(a, b)
      Ns.n.a1.uri_.>=(uri2).query.get ==> List(b, c)
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(uri1))
      val b = (2, Some(uri2))
      val c = (3, Some(uri3))
      val x = (4, Option.empty[URI])
      Ns.n.uri_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      Ns.n.a1.uri_?.query.get ==> List(a, b, c, x)

      // Find optional values matching
      Ns.n.a1.uri_?(Some(uri0)).query.get ==> List()
      Ns.n.a1.uri_?(Some(uri1)).query.get ==> List(a)
      Ns.n.a1.uri_?(Some(Seq(uri0))).query.get ==> List()
      Ns.n.a1.uri_?(Some(Seq(uri1))).query.get ==> List(a)
      // OR semantics for Ses of multiple args
      Ns.n.a1.uri_?(Some(Seq(uri1, uri2))).query.get ==> List(a, b)
      Ns.n.a1.uri_?(Some(Seq(uri1, uri0))).query.get ==> List(a)
      // Empty Seq of args matches no values
      Ns.n.a1.uri_?(Some(Seq.empty[URI])).query.get ==> List()
      // None matches non-asserted/null values
      Ns.n.a1.uri_?(Option.empty[URI]).query.get ==> List(x)
      Ns.n.a1.uri_?(Option.empty[Seq[URI]]).query.get ==> List(x)

      // Find optional values not matching
      Ns.n.a1.uri_?.not(Some(uri0)).query.get ==> List(a, b, c)
      Ns.n.a1.uri_?.not(Some(uri1)).query.get ==> List(b, c)
      Ns.n.a1.uri_?.not(Some(uri2)).query.get ==> List(a, c)
      Ns.n.a1.uri_?.not(Some(uri3)).query.get ==> List(a, b)
      Ns.n.a1.uri_?.not(Some(Seq(uri0))).query.get ==> List(a, b, c)
      Ns.n.a1.uri_?.not(Some(Seq(uri1))).query.get ==> List(b, c)
      Ns.n.a1.uri_?.not(Some(Seq(uri2))).query.get ==> List(a, c)
      Ns.n.a1.uri_?.not(Some(Seq(uri3))).query.get ==> List(a, b)
      // OR semantics for multiple negation args
      Ns.n.a1.uri_?.not(Some(Seq(uri0, uri1))).query.get ==> List(b, c)
      Ns.n.a1.uri_?.not(Some(Seq(uri1, uri2))).query.get ==> List(c)
      Ns.n.a1.uri_?.not(Some(Seq(uri2, uri3))).query.get ==> List(a)
      // Empty Seq of negation args matches all asserted values (non-null)
      Ns.n.a1.uri_?.not(Some(Seq.empty[URI])).query.get ==> List(a, b, c)
      // None matches all asserted values (non-null)
      Ns.n.a1.uri_?.not(Option.empty[URI]).query.get ==> List(a, b, c)
      Ns.n.a1.uri_?.not(Option.empty[Seq[URI]]).query.get ==> List(a, b, c)

      // Find optional values in range
      Ns.n.a1.uri_?.<(Some(uri2)).query.get ==> List(a)
      Ns.n.a1.uri_?.>(Some(uri2)).query.get ==> List(c)
      Ns.n.a1.uri_?.<=(Some(uri2)).query.get ==> List(a, b)
      Ns.n.a1.uri_?.>=(Some(uri2)).query.get ==> List(b, c)
      // None can't be compared and returns empty result
      Ns.n.a1.uri_?.<(None).query.get ==> List()
      Ns.n.a1.uri_?.<=(None).query.get ==> List()
      Ns.n.a1.uri_?.>(None).query.get ==> List()
      Ns.n.a1.uri_?.>=(None).query.get ==> List()
    }
  }
}
