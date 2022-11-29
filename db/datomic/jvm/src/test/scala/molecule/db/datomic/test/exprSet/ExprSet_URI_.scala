// GENERATED CODE ********************************
package molecule.db.datomic.test.exprSet

import java.net.URI
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object ExprSet_URI_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(uri1, uri2))
        val b = (2, Set(uri2, uri3, uri4))
        Ns.i.uris.insert(List(a, b)).transact

        Ns.i.a1.uris.query.get ==> List(a, b)
      }


      "apply" - types { implicit conn =>
        val a = (1, Set(uri1, uri2))
        val b = (2, Set(uri2, uri3, uri4))
        Ns.i.uris.insert(List(a, b)).transact

        // Sets with one or more values matching

        // "Has this value"
        Ns.i.a1.uris(uri0).query.get ==> List()
        Ns.i.a1.uris(uri1).query.get ==> List(a)
        Ns.i.a1.uris(uri2).query.get ==> List(a, b)
        Ns.i.a1.uris(uri3).query.get ==> List(b)
        // Same as
        Ns.i.a1.uris(Seq(uri0)).query.get ==> List()
        Ns.i.a1.uris(Seq(uri1)).query.get ==> List(a)
        Ns.i.a1.uris(Seq(uri2)).query.get ==> List(a, b)
        Ns.i.a1.uris(Seq(uri3)).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        Ns.i.a1.uris(uri1, uri2).query.get ==> List(a, b)
        Ns.i.a1.uris(uri1, uri3).query.get ==> List(a, b)
        Ns.i.a1.uris(uri2, uri3).query.get ==> List(a, b)
        Ns.i.a1.uris(uri1, uri2, uri3).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.uris(Seq(uri1, uri2)).query.get ==> List(a, b)
        Ns.i.a1.uris(Seq(uri1, uri3)).query.get ==> List(a, b)
        Ns.i.a1.uris(Seq(uri2, uri3)).query.get ==> List(a, b)
        Ns.i.a1.uris(Seq(uri1, uri2, uri3)).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        Ns.i.a1.uris(Set(uri1)).query.get ==> List(a)
        Ns.i.a1.uris(Set(uri1, uri2)).query.get ==> List(a)
        Ns.i.a1.uris(Set(uri1, uri2, uri3)).query.get ==> List()
        Ns.i.a1.uris(Set(uri2)).query.get ==> List(a, b)
        Ns.i.a1.uris(Set(uri2, uri3)).query.get ==> List(b)
        Ns.i.a1.uris(Set(uri2, uri3, uri4)).query.get ==> List(b)
        // Same as
        Ns.i.a1.uris(Seq(Set(uri1))).query.get ==> List(a)
        Ns.i.a1.uris(Seq(Set(uri1, uri2))).query.get ==> List(a)
        Ns.i.a1.uris(Seq(Set(uri1, uri2, uri3))).query.get ==> List()
        Ns.i.a1.uris(Seq(Set(uri2))).query.get ==> List(a, b)
        Ns.i.a1.uris(Seq(Set(uri2, uri3))).query.get ==> List(b)
        Ns.i.a1.uris(Seq(Set(uri2, uri3, uri4))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        Ns.i.a1.uris(Set(uri1, uri2), Set(uri0)).query.get ==> List(a)
        Ns.i.a1.uris(Set(uri1, uri2), Set(uri0, uri3)).query.get ==> List(a)
        Ns.i.a1.uris(Set(uri1, uri2), Set(uri2, uri3)).query.get ==> List(a, b)
        Ns.i.a1.uris(Set(uri1, uri2), Set(uri2, uri3, uri4)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.uris(Seq(Set(uri1, uri2), Set(uri0))).query.get ==> List(a)
        Ns.i.a1.uris(Seq(Set(uri1, uri2), Set(uri0, uri3))).query.get ==> List(a)
        Ns.i.a1.uris(Seq(Set(uri1, uri2), Set(uri2, uri3))).query.get ==> List(a, b)
        Ns.i.a1.uris(Seq(Set(uri1, uri2), Set(uri2, uri3, uri4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.uris(Set(uri1, uri2), Set.empty[URI]).query.get ==> List(a)
        Ns.i.a1.uris(Seq.empty[URI]).query.get ==> List()
        Ns.i.a1.uris(Set.empty[URI]).query.get ==> List()
        Ns.i.a1.uris(Seq.empty[Set[URI]]).query.get ==> List()
      }


      "not" - types { implicit conn =>
        val a = (1, Set(uri1, uri2))
        val b = (2, Set(uri2, uri3, uri4))
        Ns.i.uris.insert(List(a, b)).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        Ns.i.a1.uris.not(uri0).query.get ==> List(a, b)
        Ns.i.a1.uris.not(uri1).query.get ==> List(b)
        Ns.i.a1.uris.not(uri2).query.get ==> List()
        Ns.i.a1.uris.not(uri3).query.get ==> List(a)
        Ns.i.a1.uris.not(uri4).query.get ==> List(a)
        Ns.i.a1.uris.not(uri5).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.uris.not(Seq(uri0)).query.get ==> List(a, b)
        Ns.i.a1.uris.not(Seq(uri1)).query.get ==> List(b)
        Ns.i.a1.uris.not(Seq(uri2)).query.get ==> List()
        Ns.i.a1.uris.not(Seq(uri3)).query.get ==> List(a)
        Ns.i.a1.uris.not(Seq(uri4)).query.get ==> List(a)
        Ns.i.a1.uris.not(Seq(uri5)).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        Ns.i.a1.uris.not(uri1, uri2).query.get ==> List()
        Ns.i.a1.uris.not(uri1, uri3).query.get ==> List()
        Ns.i.a1.uris.not(uri1, uri4).query.get ==> List()
        Ns.i.a1.uris.not(uri1, uri5).query.get ==> List(b)
        // Same as
        Ns.i.a1.uris.not(Seq(uri1, uri2)).query.get ==> List()
        Ns.i.a1.uris.not(Seq(uri1, uri3)).query.get ==> List()
        Ns.i.a1.uris.not(Seq(uri1, uri4)).query.get ==> List()
        Ns.i.a1.uris.not(Seq(uri1, uri5)).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        Ns.i.a1.uris.not(Set(uri1)).query.get ==> List(b)
        Ns.i.a1.uris.not(Set(uri1, uri2)).query.get ==> List(b)
        Ns.i.a1.uris.not(Set(uri1, uri2, uri3)).query.get ==> List(a, b)
        Ns.i.a1.uris.not(Set(uri2)).query.get ==> List()
        Ns.i.a1.uris.not(Set(uri2, uri3)).query.get ==> List(a)
        Ns.i.a1.uris.not(Set(uri2, uri3, uri4)).query.get ==> List(a)
        // Same as
        Ns.i.a1.uris.not(Seq(Set(uri1))).query.get ==> List(b)
        Ns.i.a1.uris.not(Seq(Set(uri1, uri2))).query.get ==> List(b)
        Ns.i.a1.uris.not(Seq(Set(uri1, uri2, uri3))).query.get ==> List(a, b)
        Ns.i.a1.uris.not(Seq(Set(uri2))).query.get ==> List()
        Ns.i.a1.uris.not(Seq(Set(uri2, uri3))).query.get ==> List(a)
        Ns.i.a1.uris.not(Seq(Set(uri2, uri3, uri4))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        Ns.i.a1.uris.not(Set(uri1, uri2), Set(uri0)).query.get ==> List(b)
        Ns.i.a1.uris.not(Set(uri1, uri2), Set(uri0, uri3)).query.get ==> List(b)
        Ns.i.a1.uris.not(Set(uri1, uri2), Set(uri2, uri3)).query.get ==> List()
        Ns.i.a1.uris.not(Set(uri1, uri2), Set(uri2, uri3, uri4)).query.get ==> List()
        // Same as
        Ns.i.a1.uris.not(Seq(Set(uri1, uri2), Set(uri0))).query.get ==> List(b)
        Ns.i.a1.uris.not(Seq(Set(uri1, uri2), Set(uri0, uri3))).query.get ==> List(b)
        Ns.i.a1.uris.not(Seq(Set(uri1, uri2), Set(uri2, uri3))).query.get ==> List()
        Ns.i.a1.uris.not(Seq(Set(uri1, uri2), Set(uri2, uri3, uri4))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        Ns.i.a1.uris.not(Set(uri1, uri2), Set.empty[URI]).query.get ==> List(b)
        Ns.i.a1.uris.not(Seq.empty[URI]).query.get ==> List(a, b)
        Ns.i.a1.uris.not(Set.empty[URI]).query.get ==> List(a, b)
        Ns.i.a1.uris.not(Seq.empty[Set[URI]]).query.get ==> List(a, b)
        Ns.i.a1.uris.not(Seq(Set.empty[URI])).query.get ==> List(a, b)
      }


      "==" - types { implicit conn =>
        val a = (1, Set(uri1, uri2))
        val b = (2, Set(uri2, uri3, uri4))
        Ns.i.uris.insert(List(a, b)).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        Ns.i.a1.uris.==(Set(uri1)).query.get ==> List()
        Ns.i.a1.uris.==(Set(uri1, uri2)).query.get ==> List(a) // include exact match
        Ns.i.a1.uris.==(Set(uri1, uri2, uri3)).query.get ==> List()
        // Same as
        Ns.i.a1.uris.==(Seq(Set(uri1))).query.get ==> List()
        Ns.i.a1.uris.==(Seq(Set(uri1, uri2))).query.get ==> List(a)
        Ns.i.a1.uris.==(Seq(Set(uri1, uri2, uri3))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.uris.==(Set(uri1), Set(uri2, uri3)).query.get ==> List()
        Ns.i.a1.uris.==(Set(uri1, uri2), Set(uri2, uri3)).query.get ==> List(a)
        Ns.i.a1.uris.==(Set(uri1, uri2), Set(uri2, uri3, uri4)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.uris.==(Seq(Set(uri1), Set(uri2, uri3))).query.get ==> List()
        Ns.i.a1.uris.==(Seq(Set(uri1, uri2), Set(uri2, uri3))).query.get ==> List(a)
        Ns.i.a1.uris.==(Seq(Set(uri1, uri2), Set(uri2, uri3, uri4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.uris.==(Set(uri1, uri2), Set.empty[URI]).query.get ==> List(a)
        Ns.i.a1.uris.==(Set.empty[URI], Set(uri1, uri2)).query.get ==> List(a)
        Ns.i.a1.uris.==(Set.empty[URI]).query.get ==> List()
        Ns.i.a1.uris.==(Seq.empty[Set[URI]]).query.get ==> List()
        Ns.i.a1.uris.==(Seq(Set.empty[URI])).query.get ==> List()
      }


      "!=" - types { implicit conn =>
        val a = (1, Set(uri1, uri2))
        val b = (2, Set(uri2, uri3, uri4))
        Ns.i.uris.insert(List(a, b)).transact

        // Exact Set non-matches

        // AND semantics
        // "Not (exactly this AND that)"
        Ns.i.a1.uris.!=(Set(uri1)).query.get ==> List(a, b)
        Ns.i.a1.uris.!=(Set(uri1, uri2)).query.get ==> List(b) // exclude exact match
        Ns.i.a1.uris.!=(Set(uri1, uri2, uri3)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.uris.!=(Seq(Set(uri1))).query.get ==> List(a, b)
        Ns.i.a1.uris.!=(Seq(Set(uri1, uri2))).query.get ==> List(b)
        Ns.i.a1.uris.!=(Seq(Set(uri1, uri2, uri3))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.uris.!=(Set(uri1), Set(uri2, uri3)).query.get ==> List(a, b)
        Ns.i.a1.uris.!=(Set(uri1, uri2), Set(uri2, uri3)).query.get ==> List(b)
        Ns.i.a1.uris.!=(Set(uri1, uri2), Set(uri2, uri3, uri4)).query.get ==> List()
        // Same as
        Ns.i.a1.uris.!=(Seq(Set(uri1), Set(uri2, uri3))).query.get ==> List(a, b)
        Ns.i.a1.uris.!=(Seq(Set(uri1, uri2), Set(uri2, uri3))).query.get ==> List(b)
        Ns.i.a1.uris.!=(Seq(Set(uri1, uri2), Set(uri2, uri3, uri4))).query.get ==> List()


        // Empty Seq/Sets
        Ns.i.a1.uris.!=(Seq(Set(uri1, uri2), Set.empty[URI])).query.get ==> List(b)
        Ns.i.a1.uris.!=(Set.empty[URI]).query.get ==> List(a, b)
        Ns.i.a1.uris.!=(Seq.empty[Set[URI]]).query.get ==> List(a, b)
      }


      "compare" - types { implicit conn =>
        val a = (1, Set(uri1, uri2))
        val b = (2, Set(uri2, uri3, uri4))
        Ns.i.uris.insert(List(a, b)).transact

        Ns.i.a1.uris.<(uri0).query.get ==> List()
        Ns.i.a1.uris.<(uri1).query.get ==> List()
        Ns.i.a1.uris.<(uri2).query.get ==> List(a)
        Ns.i.a1.uris.<(uri3).query.get ==> List(a, b)

        Ns.i.a1.uris.<=(uri0).query.get ==> List()
        Ns.i.a1.uris.<=(uri1).query.get ==> List(a)
        Ns.i.a1.uris.<=(uri2).query.get ==> List(a, b)
        Ns.i.a1.uris.<=(uri3).query.get ==> List(a, b)

        Ns.i.a1.uris.>(uri0).query.get ==> List(a, b)
        Ns.i.a1.uris.>(uri1).query.get ==> List(a, b)
        Ns.i.a1.uris.>(uri2).query.get ==> List(b)
        Ns.i.a1.uris.>(uri3).query.get ==> List(b)

        Ns.i.a1.uris.>=(uri0).query.get ==> List(a, b)
        Ns.i.a1.uris.>=(uri1).query.get ==> List(a, b)
        Ns.i.a1.uris.>=(uri2).query.get ==> List(a, b)
        Ns.i.a1.uris.>=(uri3).query.get ==> List(b)
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.uris.insert(List(
          (a, Set(uri1, uri2)),
          (b, Set(uri2, uri3, uri4))
        )).transact

        Ns.i.a1.uris_.query.get ==> List(a, b)
      }


      "apply" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.uris.insert(List(
          (a, Set(uri1, uri2)),
          (b, Set(uri2, uri3, uri4))
        )).transact

        // Sets with one or more values matching

        // "Has this value"
        Ns.i.a1.uris_(uri0).query.get ==> List()
        Ns.i.a1.uris_(uri1).query.get ==> List(a)
        Ns.i.a1.uris_(uri2).query.get ==> List(a, b)
        Ns.i.a1.uris_(uri3).query.get ==> List(b)
        // Same as
        Ns.i.a1.uris_(Seq(uri0)).query.get ==> List()
        Ns.i.a1.uris_(Seq(uri1)).query.get ==> List(a)
        Ns.i.a1.uris_(Seq(uri2)).query.get ==> List(a, b)
        Ns.i.a1.uris_(Seq(uri3)).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        Ns.i.a1.uris_(uri1, uri2).query.get ==> List(a, b)
        Ns.i.a1.uris_(uri1, uri3).query.get ==> List(a, b)
        Ns.i.a1.uris_(uri2, uri3).query.get ==> List(a, b)
        Ns.i.a1.uris_(uri1, uri2, uri3).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.uris_(Seq(uri1, uri2)).query.get ==> List(a, b)
        Ns.i.a1.uris_(Seq(uri1, uri3)).query.get ==> List(a, b)
        Ns.i.a1.uris_(Seq(uri2, uri3)).query.get ==> List(a, b)
        Ns.i.a1.uris_(Seq(uri1, uri2, uri3)).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        Ns.i.a1.uris_(Set(uri1)).query.get ==> List(a)
        Ns.i.a1.uris_(Set(uri1, uri2)).query.get ==> List(a)
        Ns.i.a1.uris_(Set(uri1, uri2, uri3)).query.get ==> List()
        Ns.i.a1.uris_(Set(uri2)).query.get ==> List(a, b)
        Ns.i.a1.uris_(Set(uri2, uri3)).query.get ==> List(b)
        Ns.i.a1.uris_(Set(uri2, uri3, uri4)).query.get ==> List(b)
        // Same as
        Ns.i.a1.uris_(Seq(Set(uri1))).query.get ==> List(a)
        Ns.i.a1.uris_(Seq(Set(uri1, uri2))).query.get ==> List(a)
        Ns.i.a1.uris_(Seq(Set(uri1, uri2, uri3))).query.get ==> List()
        Ns.i.a1.uris_(Seq(Set(uri2))).query.get ==> List(a, b)
        Ns.i.a1.uris_(Seq(Set(uri2, uri3))).query.get ==> List(b)
        Ns.i.a1.uris_(Seq(Set(uri2, uri3, uri4))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        Ns.i.a1.uris_(Set(uri1, uri2), Set(uri0)).query.get ==> List(a)
        Ns.i.a1.uris_(Set(uri1, uri2), Set(uri0, uri3)).query.get ==> List(a)
        Ns.i.a1.uris_(Set(uri1, uri2), Set(uri2, uri3)).query.get ==> List(a, b)
        Ns.i.a1.uris_(Set(uri1, uri2), Set(uri2, uri3, uri4)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.uris_(Seq(Set(uri1, uri2), Set(uri0))).query.get ==> List(a)
        Ns.i.a1.uris_(Seq(Set(uri1, uri2), Set(uri0, uri3))).query.get ==> List(a)
        Ns.i.a1.uris_(Seq(Set(uri1, uri2), Set(uri2, uri3))).query.get ==> List(a, b)
        Ns.i.a1.uris_(Seq(Set(uri1, uri2), Set(uri2, uri3, uri4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.uris_(Set(uri1, uri2), Set.empty[URI]).query.get ==> List(a)
        Ns.i.a1.uris_(Seq.empty[URI]).query.get ==> List()
        Ns.i.a1.uris_(Set.empty[URI]).query.get ==> List()
        Ns.i.a1.uris_(Seq.empty[Set[URI]]).query.get ==> List()
      }


      "not" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.uris.insert(List(
          (a, Set(uri1, uri2)),
          (b, Set(uri2, uri3, uri4))
        )).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        Ns.i.a1.uris_.not(uri0).query.get ==> List(a, b)
        Ns.i.a1.uris_.not(uri1).query.get ==> List(b)
        Ns.i.a1.uris_.not(uri2).query.get ==> List()
        Ns.i.a1.uris_.not(uri3).query.get ==> List(a)
        Ns.i.a1.uris_.not(uri4).query.get ==> List(a)
        Ns.i.a1.uris_.not(uri5).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.uris_.not(Seq(uri0)).query.get ==> List(a, b)
        Ns.i.a1.uris_.not(Seq(uri1)).query.get ==> List(b)
        Ns.i.a1.uris_.not(Seq(uri2)).query.get ==> List()
        Ns.i.a1.uris_.not(Seq(uri3)).query.get ==> List(a)
        Ns.i.a1.uris_.not(Seq(uri4)).query.get ==> List(a)
        Ns.i.a1.uris_.not(Seq(uri5)).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        Ns.i.a1.uris_.not(uri1, uri2).query.get ==> List()
        Ns.i.a1.uris_.not(uri1, uri3).query.get ==> List()
        Ns.i.a1.uris_.not(uri1, uri4).query.get ==> List()
        Ns.i.a1.uris_.not(uri1, uri5).query.get ==> List(b)
        // Same as
        Ns.i.a1.uris_.not(Seq(uri1, uri2)).query.get ==> List()
        Ns.i.a1.uris_.not(Seq(uri1, uri3)).query.get ==> List()
        Ns.i.a1.uris_.not(Seq(uri1, uri4)).query.get ==> List()
        Ns.i.a1.uris_.not(Seq(uri1, uri5)).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        Ns.i.a1.uris_.not(Set(uri1)).query.get ==> List(b)
        Ns.i.a1.uris_.not(Set(uri1, uri2)).query.get ==> List(b)
        Ns.i.a1.uris_.not(Set(uri1, uri2, uri3)).query.get ==> List(a, b)
        Ns.i.a1.uris_.not(Set(uri2)).query.get ==> List()
        Ns.i.a1.uris_.not(Set(uri2, uri3)).query.get ==> List(a)
        Ns.i.a1.uris_.not(Set(uri2, uri3, uri4)).query.get ==> List(a)
        // Same as
        Ns.i.a1.uris_.not(Seq(Set(uri1))).query.get ==> List(b)
        Ns.i.a1.uris_.not(Seq(Set(uri1, uri2))).query.get ==> List(b)
        Ns.i.a1.uris_.not(Seq(Set(uri1, uri2, uri3))).query.get ==> List(a, b)
        Ns.i.a1.uris_.not(Seq(Set(uri2))).query.get ==> List()
        Ns.i.a1.uris_.not(Seq(Set(uri2, uri3))).query.get ==> List(a)
        Ns.i.a1.uris_.not(Seq(Set(uri2, uri3, uri4))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        Ns.i.a1.uris_.not(Set(uri1, uri2), Set(uri0)).query.get ==> List(b)
        Ns.i.a1.uris_.not(Set(uri1, uri2), Set(uri0, uri3)).query.get ==> List(b)
        Ns.i.a1.uris_.not(Set(uri1, uri2), Set(uri2, uri3)).query.get ==> List()
        Ns.i.a1.uris_.not(Set(uri1, uri2), Set(uri2, uri3, uri4)).query.get ==> List()
        // Same as
        Ns.i.a1.uris_.not(Seq(Set(uri1, uri2), Set(uri0))).query.get ==> List(b)
        Ns.i.a1.uris_.not(Seq(Set(uri1, uri2), Set(uri0, uri3))).query.get ==> List(b)
        Ns.i.a1.uris_.not(Seq(Set(uri1, uri2), Set(uri2, uri3))).query.get ==> List()
        Ns.i.a1.uris_.not(Seq(Set(uri1, uri2), Set(uri2, uri3, uri4))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        Ns.i.a1.uris_.not(Set(uri1, uri2), Set.empty[URI]).query.get ==> List(b)
        Ns.i.a1.uris_.not(Seq.empty[URI]).query.get ==> List(a, b)
        Ns.i.a1.uris_.not(Set.empty[URI]).query.get ==> List(a, b)
        Ns.i.a1.uris_.not(Seq.empty[Set[URI]]).query.get ==> List(a, b)
        Ns.i.a1.uris_.not(Seq(Set.empty[URI])).query.get ==> List(a, b)
      }


      "==" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.uris.insert(List(
          (a, Set(uri1, uri2)),
          (b, Set(uri2, uri3, uri4))
        )).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        Ns.i.a1.uris_.==(Set(uri1)).query.get ==> List()
        Ns.i.a1.uris_.==(Set(uri1, uri2)).query.get ==> List(a) // include exact match
        Ns.i.a1.uris_.==(Set(uri1, uri2, uri3)).query.get ==> List()
        // Same as
        Ns.i.a1.uris_.==(Seq(Set(uri1))).query.get ==> List()
        Ns.i.a1.uris_.==(Seq(Set(uri1, uri2))).query.get ==> List(a)
        Ns.i.a1.uris_.==(Seq(Set(uri1, uri2, uri3))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.uris_.==(Set(uri1), Set(uri2, uri3)).query.get ==> List()
        Ns.i.a1.uris_.==(Set(uri1, uri2), Set(uri2, uri3)).query.get ==> List(a)
        Ns.i.a1.uris_.==(Set(uri1, uri2), Set(uri2, uri3, uri4)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.uris_.==(Seq(Set(uri1), Set(uri2, uri3))).query.get ==> List()
        Ns.i.a1.uris_.==(Seq(Set(uri1, uri2), Set(uri2, uri3))).query.get ==> List(a)
        Ns.i.a1.uris_.==(Seq(Set(uri1, uri2), Set(uri2, uri3, uri4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.uris_.==(Set(uri1, uri2), Set.empty[URI]).query.get ==> List(a)
        Ns.i.a1.uris_.==(Set.empty[URI]).query.get ==> List()
        Ns.i.a1.uris_.==(Seq.empty[Set[URI]]).query.get ==> List()
        Ns.i.a1.uris_.==(Seq(Set.empty[URI])).query.get ==> List()
      }


      "!=" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.uris.insert(List(
          (a, Set(uri1, uri2)),
          (b, Set(uri2, uri3, uri4))
        )).transact

        // Non-exact Set matches

        // AND semantics
        // "Not (exactly this AND that)"
        Ns.i.a1.uris_.!=(Set(uri1)).query.get ==> List(a, b)
        Ns.i.a1.uris_.!=(Set(uri1, uri2)).query.get ==> List(b) // exclude exact match
        Ns.i.a1.uris_.!=(Set(uri1, uri2, uri3)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.uris_.!=(Seq(Set(uri1))).query.get ==> List(a, b)
        Ns.i.a1.uris_.!=(Seq(Set(uri1, uri2))).query.get ==> List(b)
        Ns.i.a1.uris_.!=(Seq(Set(uri1, uri2, uri3))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.uris_.!=(Set(uri1), Set(uri2, uri3)).query.get ==> List(a, b)
        Ns.i.a1.uris_.!=(Set(uri1, uri2), Set(uri2, uri3)).query.get ==> List(b)
        Ns.i.a1.uris_.!=(Set(uri1, uri2), Set(uri2, uri3, uri4)).query.get ==> List()
        // Same as
        Ns.i.a1.uris_.!=(Seq(Set(uri1), Set(uri2, uri3))).query.get ==> List(a, b)
        Ns.i.a1.uris_.!=(Seq(Set(uri1, uri2), Set(uri2, uri3))).query.get ==> List(b)
        Ns.i.a1.uris_.!=(Seq(Set(uri1, uri2), Set(uri2, uri3, uri4))).query.get ==> List()


        // Empty Seq/Sets
        Ns.i.a1.uris_.!=(Seq(Set(uri1, uri2), Set.empty[URI])).query.get ==> List(b)
        Ns.i.a1.uris_.!=(Set.empty[URI]).query.get ==> List(a, b)
        Ns.i.a1.uris_.!=(Seq.empty[Set[URI]]).query.get ==> List(a, b)
      }


      "compare" - types { implicit conn =>
        val (a, b) = (1, 2)
        Ns.i.uris.insert(List(
          (a, Set(uri1, uri2)),
          (b, Set(uri2, uri3, uri4))
        )).transact

        Ns.i.a1.uris_.<(uri0).query.get ==> List()
        Ns.i.a1.uris_.<(uri1).query.get ==> List()
        Ns.i.a1.uris_.<(uri2).query.get ==> List(a)
        Ns.i.a1.uris_.<(uri3).query.get ==> List(a, b)

        Ns.i.a1.uris_.<=(uri0).query.get ==> List()
        Ns.i.a1.uris_.<=(uri1).query.get ==> List(a)
        Ns.i.a1.uris_.<=(uri2).query.get ==> List(a, b)
        Ns.i.a1.uris_.<=(uri3).query.get ==> List(a, b)

        Ns.i.a1.uris_.>(uri0).query.get ==> List(a, b)
        Ns.i.a1.uris_.>(uri1).query.get ==> List(a, b)
        Ns.i.a1.uris_.>(uri2).query.get ==> List(b)
        Ns.i.a1.uris_.>(uri3).query.get ==> List(b)

        Ns.i.a1.uris_.>=(uri0).query.get ==> List(a, b)
        Ns.i.a1.uris_.>=(uri1).query.get ==> List(a, b)
        Ns.i.a1.uris_.>=(uri2).query.get ==> List(a, b)
        Ns.i.a1.uris_.>=(uri3).query.get ==> List(b)
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(uri1, uri2)))
        val b = (2, Some(Set(uri2, uri3, uri4)))
        val c = (3, None)
        Ns.i.uris_?.insert(a, b, c).transact

        Ns.i.a1.uris_?.query.get ==> List(a, b, c)
      }


      "apply" - types { implicit conn =>
        val a = (1, Some(Set(uri1, uri2)))
        val b = (2, Some(Set(uri2, uri3, uri4)))
        val c = (3, None)
        Ns.i.uris_?.insert(a, b, c).transact

        // Sets with one or more values matching

        // "Has this value"
        Ns.i.a1.uris_?(Some(uri0)).query.get ==> List()
        Ns.i.a1.uris_?(Some(uri1)).query.get ==> List(a)
        Ns.i.a1.uris_?(Some(uri2)).query.get ==> List(a, b)
        Ns.i.a1.uris_?(Some(uri3)).query.get ==> List(b)
        // Same as
        Ns.i.a1.uris_?(Some(Seq(uri0))).query.get ==> List()
        Ns.i.a1.uris_?(Some(Seq(uri1))).query.get ==> List(a)
        Ns.i.a1.uris_?(Some(Seq(uri2))).query.get ==> List(a, b)
        Ns.i.a1.uris_?(Some(Seq(uri3))).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        Ns.i.a1.uris_?(Some(Seq(uri1, uri2))).query.get ==> List(a, b)
        Ns.i.a1.uris_?(Some(Seq(uri1, uri3))).query.get ==> List(a, b)
        Ns.i.a1.uris_?(Some(Seq(uri2, uri3))).query.get ==> List(a, b)
        Ns.i.a1.uris_?(Some(Seq(uri1, uri2, uri3))).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        Ns.i.a1.uris_?(Some(Set(uri1))).query.get ==> List(a)
        Ns.i.a1.uris_?(Some(Set(uri1, uri2))).query.get ==> List(a)
        Ns.i.a1.uris_?(Some(Set(uri1, uri2, uri3))).query.get ==> List()
        Ns.i.a1.uris_?(Some(Set(uri2))).query.get ==> List(a, b)
        Ns.i.a1.uris_?(Some(Set(uri2, uri3))).query.get ==> List(b)
        Ns.i.a1.uris_?(Some(Set(uri2, uri3, uri4))).query.get ==> List(b)
        // Same as
        Ns.i.a1.uris_?(Some(Seq(Set(uri1)))).query.get ==> List(a)
        Ns.i.a1.uris_?(Some(Seq(Set(uri1, uri2)))).query.get ==> List(a)
        Ns.i.a1.uris_?(Some(Seq(Set(uri1, uri2, uri3)))).query.get ==> List()
        Ns.i.a1.uris_?(Some(Seq(Set(uri2)))).query.get ==> List(a, b)
        Ns.i.a1.uris_?(Some(Seq(Set(uri2, uri3)))).query.get ==> List(b)
        Ns.i.a1.uris_?(Some(Seq(Set(uri2, uri3, uri4)))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        Ns.i.a1.uris_?(Some(Seq(Set(uri1, uri2), Set(uri0)))).query.get ==> List(a)
        Ns.i.a1.uris_?(Some(Seq(Set(uri1, uri2), Set(uri0, uri3)))).query.get ==> List(a)
        Ns.i.a1.uris_?(Some(Seq(Set(uri1, uri2), Set(uri2, uri3)))).query.get ==> List(a, b)
        Ns.i.a1.uris_?(Some(Seq(Set(uri1, uri2), Set(uri2, uri3, uri4)))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.uris_?(Some(Seq.empty[URI])).query.get ==> List()
        Ns.i.a1.uris_?(Some(Set.empty[URI])).query.get ==> List()
        Ns.i.a1.uris_?(Some(Seq.empty[Set[URI]])).query.get ==> List()


        // None matches non-asserted values
        Ns.i.a1.uris_?(Option.empty[URI]).query.get ==> List(c)
        Ns.i.a1.uris_?(Option.empty[Seq[URI]]).query.get ==> List(c)
        Ns.i.a1.uris_?(Option.empty[Set[URI]]).query.get ==> List(c)
        Ns.i.a1.uris_?(Option.empty[Seq[Set[URI]]]).query.get ==> List(c)
      }


      "not" - types { implicit conn =>
        val a = (1, Some(Set(uri1, uri2)))
        val b = (2, Some(Set(uri2, uri3, uri4)))
        val c = (3, None)
        Ns.i.uris_?.insert(a, b, c).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        Ns.i.a1.uris_?.not(Some(uri0)).query.get ==> List(a, b)
        Ns.i.a1.uris_?.not(Some(uri1)).query.get ==> List(b)
        Ns.i.a1.uris_?.not(Some(uri2)).query.get ==> List()
        Ns.i.a1.uris_?.not(Some(uri3)).query.get ==> List(a)
        Ns.i.a1.uris_?.not(Some(uri4)).query.get ==> List(a)
        Ns.i.a1.uris_?.not(Some(uri5)).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.uris_?.not(Some(Seq(uri0))).query.get ==> List(a, b)
        Ns.i.a1.uris_?.not(Some(Seq(uri1))).query.get ==> List(b)
        Ns.i.a1.uris_?.not(Some(Seq(uri2))).query.get ==> List()
        Ns.i.a1.uris_?.not(Some(Seq(uri3))).query.get ==> List(a)
        Ns.i.a1.uris_?.not(Some(Seq(uri4))).query.get ==> List(a)
        Ns.i.a1.uris_?.not(Some(Seq(uri5))).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        Ns.i.a1.uris_?.not(Some(Seq(uri1, uri2))).query.get ==> List()
        Ns.i.a1.uris_?.not(Some(Seq(uri1, uri3))).query.get ==> List()
        Ns.i.a1.uris_?.not(Some(Seq(uri1, uri4))).query.get ==> List()
        Ns.i.a1.uris_?.not(Some(Seq(uri1, uri5))).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        Ns.i.a1.uris_?.not(Some(Set(uri1))).query.get ==> List(b)
        Ns.i.a1.uris_?.not(Some(Set(uri1, uri2))).query.get ==> List(b)
        Ns.i.a1.uris_?.not(Some(Set(uri1, uri2, uri3))).query.get ==> List(a, b)
        Ns.i.a1.uris_?.not(Some(Set(uri2))).query.get ==> List()
        Ns.i.a1.uris_?.not(Some(Set(uri2, uri3))).query.get ==> List(a)
        Ns.i.a1.uris_?.not(Some(Set(uri2, uri3, uri4))).query.get ==> List(a)
        // Same as
        Ns.i.a1.uris_?.not(Some(Seq(Set(uri1)))).query.get ==> List(b)
        Ns.i.a1.uris_?.not(Some(Seq(Set(uri1, uri2)))).query.get ==> List(b)
        Ns.i.a1.uris_?.not(Some(Seq(Set(uri1, uri2, uri3)))).query.get ==> List(a, b)
        Ns.i.a1.uris_?.not(Some(Seq(Set(uri2)))).query.get ==> List()
        Ns.i.a1.uris_?.not(Some(Seq(Set(uri2, uri3)))).query.get ==> List(a)
        Ns.i.a1.uris_?.not(Some(Seq(Set(uri2, uri3, uri4)))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        Ns.i.a1.uris_?.not(Some(Seq(Set(uri1, uri2), Set(uri0)))).query.get ==> List(b)
        Ns.i.a1.uris_?.not(Some(Seq(Set(uri1, uri2), Set(uri0, uri3)))).query.get ==> List(b)
        Ns.i.a1.uris_?.not(Some(Seq(Set(uri1, uri2), Set(uri2, uri3)))).query.get ==> List()
        Ns.i.a1.uris_?.not(Some(Seq(Set(uri1, uri2), Set(uri2, uri3, uri4)))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        Ns.i.a1.uris_?.not(Some(Seq.empty[URI])).query.get ==> List(a, b)
        Ns.i.a1.uris_?.not(Some(Set.empty[URI])).query.get ==> List(a, b)
        Ns.i.a1.uris_?.not(Some(Seq.empty[Set[URI]])).query.get ==> List(a, b)
        Ns.i.a1.uris_?.not(Some(Seq(Set.empty[URI]))).query.get ==> List(a, b)


        // Negating None returns all asserted
        Ns.i.a1.uris_?.not(Option.empty[URI]).query.get ==> List(a, b)
        Ns.i.a1.uris_?.not(Option.empty[Seq[URI]]).query.get ==> List(a, b)
        Ns.i.a1.uris_?.not(Option.empty[Set[URI]]).query.get ==> List(a, b)
        Ns.i.a1.uris_?.not(Option.empty[Seq[Set[URI]]]).query.get ==> List(a, b)
      }


      "==" - types { implicit conn =>
        val a = (1, Some(Set(uri1, uri2)))
        val b = (2, Some(Set(uri2, uri3, uri4)))
        val c = (3, None)
        Ns.i.uris_?.insert(a, b, c).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        Ns.i.a1.uris_?.==(Some(Set(uri1))).query.get ==> List()
        Ns.i.a1.uris_?.==(Some(Set(uri1, uri2))).query.get ==> List(a) // include exact match
        Ns.i.a1.uris_?.==(Some(Set(uri1, uri2, uri3))).query.get ==> List()
        // Same as
        Ns.i.a1.uris_?.==(Some(Seq(Set(uri1)))).query.get ==> List()
        Ns.i.a1.uris_?.==(Some(Seq(Set(uri1, uri2)))).query.get ==> List(a)
        Ns.i.a1.uris_?.==(Some(Seq(Set(uri1, uri2, uri3)))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.uris_?.==(Some(Seq(Set(uri1), Set(uri2, uri3)))).query.get ==> List()
        Ns.i.a1.uris_?.==(Some(Seq(Set(uri1, uri2), Set(uri2, uri3)))).query.get ==> List(a)
        Ns.i.a1.uris_?.==(Some(Seq(Set(uri1, uri2), Set(uri2, uri3, uri4)))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        Ns.i.a1.uris_?.==(Some(Set.empty[URI])).query.get ==> List()
        Ns.i.a1.uris_?.==(Some(Seq.empty[Set[URI]])).query.get ==> List()
        Ns.i.a1.uris_?.==(Some(Seq(Set.empty[URI]))).query.get ==> List()


        // None matches non-asserted values
        Ns.i.a1.uris_?.==(Option.empty[Set[URI]]).query.get ==> List(c)
        Ns.i.a1.uris_?.==(Option.empty[Seq[Set[URI]]]).query.get ==> List(c)
      }


      "!=" - types { implicit conn =>
        val a = (1, Some(Set(uri1, uri2)))
        val b = (2, Some(Set(uri2, uri3, uri4)))
        val c = (3, None)
        Ns.i.uris_?.insert(a, b, c).transact

        // Non-exact Set matches

        // AND semantics
        // "Not (exactly this AND that)"
        Ns.i.a1.uris_?.!=(Some(Set(uri1))).query.get ==> List(a, b)
        Ns.i.a1.uris_?.!=(Some(Set(uri1, uri2))).query.get ==> List(b) // exclude exact match
        Ns.i.a1.uris_?.!=(Some(Set(uri1, uri2, uri3))).query.get ==> List(a, b)
        // Same as
        Ns.i.a1.uris_?.!=(Some(Seq(Set(uri1)))).query.get ==> List(a, b)
        Ns.i.a1.uris_?.!=(Some(Seq(Set(uri1, uri2)))).query.get ==> List(b)
        Ns.i.a1.uris_?.!=(Some(Seq(Set(uri1, uri2, uri3)))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.uris_?.!=(Some(Seq(Set(uri1), Set(uri2, uri3)))).query.get ==> List(a, b)
        Ns.i.a1.uris_?.!=(Some(Seq(Set(uri1, uri2), Set(uri2, uri3)))).query.get ==> List(b)
        Ns.i.a1.uris_?.!=(Some(Seq(Set(uri1, uri2), Set(uri2, uri3, uri4)))).query.get ==> List()


        // Empty Seq/Sets
        Ns.i.a1.uris_?.!=(Some(Seq(Set(uri1, uri2), Set.empty[URI]))).query.get ==> List(b)
        Ns.i.a1.uris_?.!=(Some(Set.empty[URI])).query.get ==> List(a, b)
        Ns.i.a1.uris_?.!=(Some(Seq.empty[Set[URI]])).query.get ==> List(a, b)


        // None matches non-asserted values
        Ns.i.a1.uris_?.==(Option.empty[Set[URI]]).query.get ==> List(c)
        Ns.i.a1.uris_?.==(Option.empty[Seq[Set[URI]]]).query.get ==> List(c)
      }


      "compare" - types { implicit conn =>
        val a = (1, Some(Set(uri1, uri2)))
        val b = (2, Some(Set(uri2, uri3, uri4)))
        val c = (3, None)
        Ns.i.uris_?.insert(a, b, c).transact

        Ns.i.a1.uris_?.<(Some(uri0)).query.get ==> List()
        Ns.i.a1.uris_?.<(Some(uri1)).query.get ==> List()
        Ns.i.a1.uris_?.<(Some(uri2)).query.get ==> List(a)
        Ns.i.a1.uris_?.<(Some(uri3)).query.get ==> List(a, b)

        Ns.i.a1.uris_?.<=(Some(uri0)).query.get ==> List()
        Ns.i.a1.uris_?.<=(Some(uri1)).query.get ==> List(a)
        Ns.i.a1.uris_?.<=(Some(uri2)).query.get ==> List(a, b)
        Ns.i.a1.uris_?.<=(Some(uri3)).query.get ==> List(a, b)

        Ns.i.a1.uris_?.>(Some(uri0)).query.get ==> List(a, b)
        Ns.i.a1.uris_?.>(Some(uri1)).query.get ==> List(a, b)
        Ns.i.a1.uris_?.>(Some(uri2)).query.get ==> List(b)
        Ns.i.a1.uris_?.>(Some(uri3)).query.get ==> List(b)

        Ns.i.a1.uris_?.>=(Some(uri0)).query.get ==> List(a, b)
        Ns.i.a1.uris_?.>=(Some(uri1)).query.get ==> List(a, b)
        Ns.i.a1.uris_?.>=(Some(uri2)).query.get ==> List(a, b)
        Ns.i.a1.uris_?.>=(Some(uri3)).query.get ==> List(b)


        // None matches any asserted values
        Ns.i.a1.uris_?.<(None).query.get ==> List(a, b)
        Ns.i.a1.uris_?.>(None).query.get ==> List(a, b)
        Ns.i.a1.uris_?.<=(None).query.get ==> List(a, b)
        Ns.i.a1.uris_?.>=(None).query.get ==> List(a, b)
      }
    }
  }
}