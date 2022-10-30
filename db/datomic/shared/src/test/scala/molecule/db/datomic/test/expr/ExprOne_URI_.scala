// GENERATED CODE ********************************
package molecule.db.datomic.test.expr

import java.net.URI
import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object ExprOne_URI_ extends DatomicTestSuite {

  lazy val tests = Tests {

    "Mandatory" - cardOne { implicit conn =>
      val a = (1, uri1)
      val b = (2, uri2)
      val c = (3, uri3)
      One.n.uri.insert(List(a, b, c)).transact

      // Apply args to find matching values
      One.n.a1.uri(uri0).query.get ==> List()
      One.n.a1.uri(uri1).query.get ==> List(a)
      One.n.a1.uri(uri1, uri2).query.get ==> List(a, b)
      One.n.a1.uri(uri1, uri0).query.get ==> List(a)
      // Arguments as Seq
      One.n.a1.uri(Seq(uri0)).query.get ==> List()
      One.n.a1.uri(Seq(uri1)).query.get ==> List(a)
      One.n.a1.uri(Seq(uri1, uri2)).query.get ==> List(a, b)
      One.n.a1.uri(Seq(uri1, uri0)).query.get ==> List(a)
      One.n.a1.uri(Seq.empty[URI]).query.get ==> List()


      One.n.a1.uri.not(uri0).query.get ==> List(a, b, c)
      One.n.a1.uri.not(uri1).query.get ==> List(b, c)
      One.n.a1.uri.not(uri2).query.get ==> List(a, c)
      One.n.a1.uri.not(uri3).query.get ==> List(a, b)
      One.n.a1.uri.not(uri0, uri1).query.get ==> List(b, c)
      One.n.a1.uri.not(uri1, uri2).query.get ==> List(c)
      One.n.a1.uri.not(uri2, uri3).query.get ==> List(a)
      // Arguments as Seq
      One.n.a1.uri.not(Seq(uri0)).query.get ==> List(a, b, c)
      One.n.a1.uri.not(Seq(uri1)).query.get ==> List(b, c)
      One.n.a1.uri.not(Seq(uri2)).query.get ==> List(a, c)
      One.n.a1.uri.not(Seq(uri3)).query.get ==> List(a, b)
      One.n.a1.uri.not(Seq(uri0, uri1)).query.get ==> List(b, c)
      One.n.a1.uri.not(Seq(uri1, uri2)).query.get ==> List(c)
      One.n.a1.uri.not(Seq(uri2, uri3)).query.get ==> List(a)
      One.n.a1.uri.not(Seq.empty[URI]).query.get ==> List(a, b, c)


      One.n.a1.uri.<(uri2).query.get ==> List(a)
      One.n.a1.uri.>(uri2).query.get ==> List(c)
      One.n.a1.uri.<=(uri2).query.get ==> List(a, b)
      One.n.a1.uri.>=(uri2).query.get ==> List(b, c)
    }


    "Tacit" - cardOne { implicit conn =>
      One.n.uri_?.insert(List(
        (1, Some(uri1)),
        (2, Some(uri2)),
        (3, Some(uri3)),
        (4, None)
      )).transact

      val a = 1
      val b = 2
      val c = 3
      val d = 4

      // No value asserted
      One.n.a1.uri_.apply().query.get ==> List(d)

      One.n.a1.uri_(uri0).query.get ==> List()
      One.n.a1.uri_.apply(uri1).query.get ==> List(a)
      One.n.a1.uri_(uri1, uri2).query.get ==> List(a, b)
      One.n.a1.uri_(uri1, uri0).query.get ==> List(a)
      // Same as
      One.n.a1.uri_(Seq(uri0)).query.get ==> List()
      One.n.a1.uri_(Seq(uri1)).query.get ==> List(a)
      One.n.a1.uri_(Seq(uri1, uri2)).query.get ==> List(a, b)
      One.n.a1.uri_(Seq(uri1, uri0)).query.get ==> List(a)
      One.n.a1.uri_(Seq.empty[URI]).query.get ==> List()


      One.n.a1.uri_.not(uri0).query.get ==> List(a, b, c)
      One.n.a1.uri_.not(uri1).query.get ==> List(b, c)
      One.n.a1.uri_.not(uri2).query.get ==> List(a, c)
      One.n.a1.uri_.not(uri3).query.get ==> List(a, b)
      One.n.a1.uri_.not(uri0, uri1).query.get ==> List(b, c)
      One.n.a1.uri_.not(uri1, uri2).query.get ==> List(c)
      One.n.a1.uri_.not(uri2, uri3).query.get ==> List(a)
      // Same as
      One.n.a1.uri_.not(Seq(uri0)).query.get ==> List(a, b, c)
      One.n.a1.uri_.not(Seq(uri1)).query.get ==> List(b, c)
      One.n.a1.uri_.not(Seq(uri2)).query.get ==> List(a, c)
      One.n.a1.uri_.not(Seq(uri3)).query.get ==> List(a, b)
      One.n.a1.uri_.not(Seq(uri0, uri1)).query.get ==> List(b, c)
      One.n.a1.uri_.not(Seq(uri1, uri2)).query.get ==> List(c)
      One.n.a1.uri_.not(Seq(uri2, uri3)).query.get ==> List(a)
      One.n.a1.uri_.not(Seq.empty[URI]).query.get ==> List(a, b, c)


      One.n.a1.uri_.<(uri2).query.get ==> List(a)
      One.n.a1.uri_.>(uri2).query.get ==> List(c)
      One.n.a1.uri_.<=(uri2).query.get ==> List(a, b)
      One.n.a1.uri_.>=(uri2).query.get ==> List(b, c)
    }


    "Optional" - cardOne { implicit conn =>
      val a = (1, Some(uri1))
      val b = (2, Some(uri2))
      val c = (3, Some(uri3))
      val x = (4, Option.empty[URI])

      One.n.uri_?.insert(List(a, b, c, x)).transact

      One.n.a1.uri_?(Some(uri0)).query.get ==> List()
      One.n.a1.uri_?.apply(Some(uri1)).query.get ==> List(a)

      One.n.a1.uri_?(Some(Seq(uri0))).query.get ==> List()
      One.n.a1.uri_?(Some(Seq(uri1))).query.get ==> List(a)
      One.n.a1.uri_?(Some(Seq(uri1, uri2))).query.get ==> List(a, b)
      One.n.a1.uri_?(Some(Seq(uri1, uri0))).query.get ==> List(a)
      One.n.a1.uri_?(Some(Seq.empty[URI])).query.get ==> List()

      One.n.a1.uri_?(Option.empty[URI]).query.get ==> List(x)
      One.n.a1.uri_?(Option.empty[Seq[URI]]).query.get ==> List(x)


      // For non-String cardinality-one attributes, `not` and `!=` have same semantics
      // "Not this value"
      One.n.a1.uri_?.not(Some(uri0)).query.get ==> List(a, b, c)
      One.n.a1.uri_?.not(Some(uri1)).query.get ==> List(b, c)
      One.n.a1.uri_?.not(Some(uri2)).query.get ==> List(a, c)
      One.n.a1.uri_?.not(Some(uri3)).query.get ==> List(a, b)
      // Same as
      One.n.a1.uri_?.not(Some(Seq(uri0))).query.get ==> List(a, b, c)
      One.n.a1.uri_?.not(Some(Seq(uri1))).query.get ==> List(b, c)
      One.n.a1.uri_?.not(Some(Seq(uri2))).query.get ==> List(a, c)
      One.n.a1.uri_?.not(Some(Seq(uri3))).query.get ==> List(a, b)

      // "Not this OR that value
      One.n.a1.uri_?.not(Some(Seq(uri0, uri1))).query.get ==> List(b, c)
      One.n.a1.uri_?.not(Some(Seq(uri1, uri2))).query.get ==> List(c)
      One.n.a1.uri_?.not(Some(Seq(uri2, uri3))).query.get ==> List(a)
      One.n.a1.uri_?.not(Some(Seq.empty[URI])).query.get ==> List(a, b, c)

      One.n.a1.uri_?.not(Option.empty[URI]).query.get ==> List(a, b, c)
      One.n.a1.uri_?.not(Option.empty[Seq[URI]]).query.get ==> List(a, b, c)


      One.n.a1.uri_?.<(Some(uri2)).query.get ==> List(a)
      One.n.a1.uri_?.>(Some(uri2)).query.get ==> List(c)
      One.n.a1.uri_?.<=(Some(uri2)).query.get ==> List(a, b)
      One.n.a1.uri_?.>=(Some(uri2)).query.get ==> List(b, c)

      // None can't be compared
      One.n.a1.uri_?.<(None).query.get ==> List()
      One.n.a1.uri_?.<=(None).query.get ==> List()
      One.n.a1.uri_?.>(None).query.get ==> List()
      One.n.a1.uri_?.>=(None).query.get ==> List()
    }
  }
}
